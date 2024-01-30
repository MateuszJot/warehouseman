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
let firstLoadInitialized = false;
let items = undefined;

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

const API = Object.freeze({
    login: () => {
        return request("GET", SERVICES.AUTH, "/token", null).then(r=>updateToken(r.body.token));
    },
    getWarehouses: (page, pageSize)=> {
        return request("GET", SERVICES.WAREHOUSE, `/warehouses`, null)        
    },
    addWarehouse: (item)=> {
        return request("POST", SERVICES.WAREHOUSE, `/warehouses`, item).then(response => response.body)        
    },
    addItemToWarehouse: (warehouseId, itemId)=> {
        return request("POST", SERVICES.WAREHOUSE, `/warehouses/${warehouseId}/items`, {item: itemId, quantity: 1}).then(response => response.body)            
    },
    getItems: ()=> {
        return request("GET", SERVICES.PRODUCT, `/items?pageNumber=${0}&pageSize=${999}`, null).then(response => response.body.items)            
    } 
});


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
            if (!firstLoadInitialized) {
                firstLoadInitialized = true;
                loadWarehouses();
            }
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

    let select = element.querySelector("#select");
    console.log(typeof(items));
    for (let item of items) {
        let option = document.createElement("option");
        option.value = item.id;
        option.innerText = item.name;
        select.appendChild(option);
    }

    // add a button in the select parent that will add the item to the warehouse
    let addButton = document.createElement("button");
    select.parentElement.appendChild(addButton);
    addButton.innerText = "Add";
    addButton.onclick = () => {
        let warehouseId = data['id'];
        let itemId = select.value;
        API.addItemToWarehouse(warehouseId, itemId);
    };
}

function appendItems(items) {
    items.forEach(appendSingleItem);
}

function appendSingleItem(item) {
    let itemElement = initTemplate(TAMEPLATES.item, item);
    itemList.appendChild(itemElement);
}

function loadWarehouses() {
    API.getItems().then(responseItems => {
        items = responseItems;
    }).then(() => {
        API.getWarehouses()
        .then(x => x.body.items)
        .then(appendItems);
    });
}

function addItem() {
    let itemName = document.getElementById("new-item-name").value;
    let itemCountry = document.getElementById("new-item-country").value;
    let itemCode = document.getElementById("new-item-code").value;
    let itemAddress1 = document.getElementById("new-item-address1").value;
    let itemAddress2 = document.getElementById("new-item-address2").value;

    if (itemName == undefined
        || itemCountry == undefined
        || itemCode == undefined
        || itemAddress1 == undefined
        || itemAddress2 == undefined) {
        return;
    }

    let body = {
        code: itemCode,
        name: itemName,
        country: itemCountry,
        address1: itemAddress1,
        address2: itemAddress2,
    };

    API.addWarehouse(body).then(appendSingleItem)

    document.getElementById("new-item-name").value = "";
    document.getElementById("new-item-country").value = "";
    document.getElementById("new-item-code").value = "";
    document.getElementById("new-item-address1").value = "";
    document.getElementById("new-item-address2").value = "";
    document.getElementById("new-item-code").focus();
}



function main() {
    updateSessionInfo();
}
main();