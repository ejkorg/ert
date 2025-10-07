let onProdString = '{"status": "","product": "","itemType": "","fab": "","fabDesc": "","afm": "",' +
    '"process": "", "family": "", "package" : "", "gdpw": 0, "wfUnits": "", "wfSize": 0, "dieUnits": "", "dieWidth": 0, ' +
    '"dieHeight": 0, "insertTime": "", "pti4": "", "technology": "", "maskSet" : ""}';
const onProdObject = JSON.parse(onProdString);


function listAllOnProdItems() {
    const errorLabel = document.getElementById("allOnProdItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onprod/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnProdByProduct() {
    const product = document.getElementById("findOnProdByProductInput").value;
    const errorLabel = document.getElementById("findOnProdByProductLabel");
    const request = "/api/onprod/byproduct/" + product;
    findBy([product], errorLabel, request);
}

function findOnProdById() {
    const id = document.getElementById("findOnProdByIdInput").value;
    const errorLabel = document.getElementById("findOnProdByIdLabel");
    const request = "/api/onprod/byid/" + id;
    findBy([id], errorLabel, request);
}

async function addNewOnProd() {
    const errorLabel = document.getElementById("addNewOnProdLabel");
    let label = "";

    onProdObject.status = document.getElementById("onProdStatus").value;
    onProdObject.product = document.getElementById("onProdProduct").value;
    onProdObject.itemType = document.getElementById("onProdItemType").value;
    onProdObject.fab = document.getElementById("onProdFab").value;
    onProdObject.fabDesc = document.getElementById("onProdFabDesc").value;
    onProdObject.afm = document.getElementById("onProdAfm").value;
    onProdObject.process = document.getElementById("onProdProcess").value;
    onProdObject.family = document.getElementById("onProdFamily").value;
    onProdObject.gdpw = Number(document.getElementById("onProdGdpw").value);
    onProdObject.wfUnits = document.getElementById("onProdWfUnits").value;
    onProdObject.wfSize = Number(document.getElementById("onProdWfSize").value);
    onProdObject.dieUnits = document.getElementById("onProdDieUnits").value;
    onProdObject.dieWidth = Number(document.getElementById("onProdDieWidth").value);
    onProdObject.dieHeight = Number(document.getElementById("onProdDieHeight").value);
    onProdObject.insertTime = document.getElementById("onProdInsertTime").value;
    onProdObject.pti4 = document.getElementById("onProdPti4").value;
    onProdObject.technology = document.getElementById("onProdTechnology").value;
    onProdObject.maskSet = document.getElementById("onProdMaskSet").value;
    onProdObject.package = document.getElementById("onProdPackage").value;

    if (fieldsInObjectAreEmpty(onProdObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onprod/create", onProdObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updateOnProd() {
    const id = document.getElementById("idToBeUpdatedOnProd").value;
    const errorLabel = document.getElementById("updateOnProdLabel");
    let label = "";

    onProdObject.status = document.getElementById("newOnProdStatus").value;
    onProdObject.product = document.getElementById("newOnProdProduct").value;
    onProdObject.itemType = document.getElementById("newOnProdItemType").value;
    onProdObject.fab = document.getElementById("newOnProdFab").value;
    onProdObject.fabDesc = document.getElementById("newOnProdFabDesc").value;
    onProdObject.afm = document.getElementById("newOnProdAfm").value;
    onProdObject.process = document.getElementById("newOnProdProcess").value;
    onProdObject.family = document.getElementById("newOnProdFamily").value;
    onProdObject.gdpw = Number(document.getElementById("newOnProdGdpw").value);
    onProdObject.wfUnits = document.getElementById("newOnProdWfUnits").value;
    onProdObject.wfSize = Number(document.getElementById("newOnProdWfSize").value);
    onProdObject.dieUnits = document.getElementById("newOnProdDieUnits").value;
    onProdObject.dieWidth = Number(document.getElementById("newOnProdDieWidth").value);
    onProdObject.dieHeight = Number(document.getElementById("newOnProdDieHeight").value);
    onProdObject.insertTime = document.getElementById("newOnProdInsertTime").value;
    onProdObject.pti4 = document.getElementById("newOnProdPti4").value;
    onProdObject.technology = document.getElementById("newOnProdTechnology").value;
    onProdObject.maskSet = document.getElementById("newOnProdMaskSet").value;
    onProdObject.package = document.getElementById("newOnProdPackage").value;

    if (id === undefined || id === "" || fieldsInObjectAreEmpty(onProdObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onprod/update/" + id, onProdObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}