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
            "ContentType": "application/json",
            "Authorization": getToken() ?? ""
        },
        credentials: "omit",
        body: requestBody
    })
    .then(parseFetchResponse);
}

async function parseFetchResponse(response) {
    try {
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
    login: ()=>{
        request("GET", SERVICES.AUTH, "/token", null).then(r=>updateToken(r.body.token));
    },
    getItems: (page, pageSize)=> {
        return request("GET", SERVICES.PRODUCT, `/items?pageNumber=${page}&pageSize=${pageSize}`, null)        
    }
});


const TAMEPLATES = {
    "item": document.getElementById("template-item")
}
const itemList = document.getElementById("item-list");

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

function initTemplateFiels(element, data) {
    let keys = Object.keys(data);

    // check for root element
    let key;
    if((key = element.getAttribute("template-set-attr")) != undefined) {
        element.setAttribute(`entity-${key}`, data[key]);
    }
    if((key = element.getAttribute("template-set")) != undefined) {
        element.innerText = data[key];
    }

    for (let key of keys) {
        let value = data[key];
        if (typeof(value) === "object"){
            if(Array.isArray(value)) {
                value = value.join(", ");
            }
            else {
                initTemplateFiels(element, value);
            }
            continue;
        }

        element
            .querySelectorAll(`*[template-set=${key}]`)
            .forEach(element => element.innerText = value);
        element
            .querySelectorAll(`*[template-set-attr=${key}]`)
            .forEach(element => element.setAttribute(`entity-${key}`, value));
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
    // TODO SEND REQUEST TO SERVER 
    //document.querySelector(`.item[entity-id='${idToDelete}']`).remove();
}

const pageSize = 10;
let itemPage = 0;

function loadMore() {
    API.getItems(itemPage, pageSize)
            .then(x => x.body.items)
            .then(appendItems) 

    itemPage++;
}