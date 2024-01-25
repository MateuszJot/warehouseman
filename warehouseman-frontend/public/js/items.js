const LS_KEY_TOKEN = "token";

const SERVICES = {
    AUTH: Symbol("AUTH"),
    PRODUCT: Symbol("PRODUCT"),
    WAREHOUSE: Symbol("WAREHOUSE"),
};

const URLS = {
    [SERVICES.AUTH]: "http://localhost:8000",
    [SERVICES.PRODUCT]: "http://localhost:8010",
    [SERVICES.WAREHOUSE]: "http://localhost:8030"
};

const TAMEPLATES = {
    "item": document.getElementById("template-item")
}

const itemList = document.getElementById("item-list");


let sessionInfoUpdateTimeout = undefined;
const pageSize = 10;
let itemPage = 0;
let suppliers = [];
let firstDraw = true;


function getToken() {
    return localStorage.getItem(LS_KEY_TOKEN);
}
function updateToken(token) {
    console.log("Update Token");
    debugPrintToken(token);
    localStorage.setItem(LS_KEY_TOKEN, token);
}

/**
 * @param {string} token 
 */
function debugPrintToken(token) {
    let parts = token.split(".");
    let header = JSON.parse(atob(parts[0]));
    let payload = JSON.parse(atob(parts[1]));
    console.log("token", {header, payload});
}

/**
 * @typedef {object} Response
 * @property {number} status
 * @property {object} body
 */


/**
 * @param {string} method 
 * @param {"AUTH"|"PRODUCT"|"WAREHOUSE"} service
 * @param {string} path
 * @param {object|string|undefined} body 
 * @returns {Future<Response>}
 */
function request(method, service, path, body) {
    if (!(service in URLS)) {
        throw new Error("illegal enum value")
    }
    let URL = URLS[service];
    let joinedPath = URL + (path.startsWith("/") ? "" : "/") + path;
    let requestBody;
    if (body == undefined) requestBody = undefined;
    else if (typeof(body) === "string") requestBody = body;
    else requestBody = JSON.stringify(body);

    return fetch(joinedPath, {
        method,
        headers: {
            "content-type": "application/json",
            "Authorization": getToken() ?? ""
        },
        credentials: "omit",
        body: requestBody
    })
    .then(parseFetchResponse);
}

async function parseFetchResponse(response) {
    try {
        if (response.status === 204) return {status: response.status, body: {}};
        let body = await response.json();
        let responseObject = {
            status: response.status,
            body
        };
        if (response.status < 100 || response.status > 399) {
            throw responseObject;
        }
        return responseObject;
    } catch (_) {
        throw new Error("not json");
    }
}

API = Object.freeze({
    login: ()=>{
        return request("GET", SERVICES.AUTH, "/token", null).then(r=>updateToken(r.body.token));
    },
    getItems: (page, pageSize)=> {
        return request("GET", SERVICES.PRODUCT, `/items?pageNumber=${page}&pageSize=${pageSize}`, null)        
    },
    deleteItem: (id)=> {
        return request("DELETE", SERVICES.PRODUCT, `/items/${id}`, null)        
    },
    addItem: (item)=> {
        return request("POST", SERVICES.PRODUCT, `/items`, item).then(response => response.body)        
    },
    getSuppliers: ()=> {
        return request("GET", SERVICES.PRODUCT, "/suppliers", null).then(response => response.body.items);
    }
});

function drawFirstTime() {
    if (!firstDraw) {
        return;
    }
    firstDraw = false;
    loadSuppliers()
    loadMore();
}


function updateSessionInfo() {
    let token = getToken();
    let sessionInfo = document.getElementById("session-info");
    if (token == undefined) {
        sessionInfo.innerText = "No session";
    } else {
        let parts = token.split(".");
        let payload = JSON.parse(atob(parts[1]));
        let expDate = payload.exp * 1000;
        let now = Date.now();
        let diff = (expDate - now) / 1000;
        if (diff < 0) {
            sessionInfo.innerText = "Session Expired";
        } else {
            drawFirstTime();
            let mins = Math.floor(diff / 60);
            let secs = Math.floor(diff % 60);
            sessionInfo.innerText = `Session expires in ${mins}:${secs}`;

            clearTimeout(sessionInfoUpdateTimeout);
            sessionInfoUpdateTimeout = setTimeout(updateSessionInfo, 1000);
        }
    }

}

function refreshSession() {
    API.login().then(updateSessionInfo);
}

/**
 * 
 * @param {HTMLTemplateElement} template 
 * @param {object} data 
 */
function initTemplate(template, data) {
    /** @type {HTMLElement} */
    let element = template.content.firstElementChild.cloneNode(true);
    initTemplateFiels(element, data);
    return element;
}

function initTemplateFiels(element, data, prevKey=undefined) {
    let keys = Object.keys(data);

    // check for root element
    let key;
    if((key = element.getAttribute("template-set-attr")) != undefined) {
        if(prevKey == undefined) 
            element.setAttribute(`entity-${key}`, data[key]);
        else if(key.startsWith(prevKey)) 
            element.setAttribute(`entity-${key}`, data[key.substring(prevKey.length + 1)]);
            
    }
    if((key = element.getAttribute("template-set")) != undefined) {
        if(prevKey == undefined) 
            element.innerText = data[key];
        else if(key.startsWith(prevKey)) 
            element.innerText = data[key.substring(prevKey.length + 1)]
    }

    for (let key of keys) {
        let searchKey = prevKey == undefined ? key : `${prevKey}-${key}`;
        let value = data[key];
        if (typeof(value) === "object"){
            if(Array.isArray(value)) {
                value = value.join(", ");
            }
            else {
                initTemplateFiels(element, value, key);
            }
            continue;
        }

        element
            .querySelectorAll(`*[template-set=${searchKey}]`)
            .forEach(element => element.innerText = value);
        element
            .querySelectorAll(`*[template-set-attr=${searchKey}]`)
            .forEach(element => element.setAttribute(`entity-${searchKey}`, value));
    }
}

function appendItems(items) {
    items.forEach(appendSingleItem);
}

function appendSingleItem(item) {
    let itemElement = initTemplate(TAMEPLATES.item, item);
    itemList.appendChild(itemElement);
}


/**
 * 
 * @param {HTMLButtonElement} button 
 */
function extractButtonId(button) {
    return button.getAttribute("entity-id");
}

/**
 * 
 * @param {HTMLButtonElement} button 
 */
function removeItem(button) {
    let idToDelete = extractButtonId(button);
    console.log(`Remove Item id: ${idToDelete}`);
    API.deleteItem(idToDelete)
        .then(() => 
            document.querySelector(`.item[entity-id='${idToDelete}']`)
            .remove()
            );
}


function loadMore() {
    API.getItems(itemPage, pageSize)
        .then(x => x.body.items)
        .then(appendItems);


    itemPage++;
}

function loadSuppliers() {
    API.getSuppliers().then(supplierList => {
        suppliers = supplierList;
        updateSupplierSelect();
    });
}

function updateSupplierSelect() {
    let select = document.getElementById("new-item-supplier");
    select.innerHTML = "";
    suppliers.forEach(supplier => {
        let option = document.createElement("option");
        option.value = supplier.id;
        option.innerText = `${supplier.name} (${supplier.code})`;
        select.appendChild(option);
    });

}

function addItem() {
    let itemNewItemCode = document.getElementById("new-item-code").value;
    let itemNewItemName = document.getElementById("new-item-name").value;
    let itemNewItemSupplier = document.getElementById("new-item-supplier").value;
    let itemNewItemPrice = Number(document.getElementById("new-item-price").value);
    let itemNewItemWarehouseable = document.getElementById("new-item-warehouseable").value === "on";
    let itemNewItemDescription = document.getElementById("new-item-description").value;


    if (itemNewItemCode == undefined
    || itemNewItemName == null        
    || itemNewItemSupplier == null        
    || itemNewItemPrice == null
    || itemNewItemWarehouseable == null        
    || itemNewItemDescription == null) {
        return;
    }

    let body = {
        code: itemNewItemCode,
        name: itemNewItemName,
        supplier: itemNewItemSupplier,
        price: itemNewItemPrice,
        warehouseable: itemNewItemWarehouseable,
        description: itemNewItemDescription,
    };
    API.login();
    API.addItem(body).then(appendSingleItem)

    document.getElementById("new-item-code").value = "";
    document.getElementById("new-item-name").value = "";
    document.getElementById("new-item-supplier").value = "";
    document.getElementById("new-item-price").value = "";
    document.getElementById("new-item-warehouseable").value = false;
    document.getElementById("new-item-description").value = "";
    document.getElementById("new-item-code").focus();
}



function main() {
    updateSessionInfo();
}
main();