let ppProdString = '{"product": "", "itemType": "", "fab": "", "fabDesc": "", "afm": "", "process": "",' +
    '"family": "", "gdpw": 0, "wfUnits" : "", "wfSize" : 0, "dieUnits" : "", "dieWidth" : 0, "dieHeight" : 0,' +
    '"insertTime" : "", "package" : ""}';
const ppProdObject = JSON.parse(ppProdString);

function listAllPpProdItems() {
    const errorLabel = document.getElementById("allPpProdItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/ppprod/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findPpProdById() {
    const id = document.getElementById("findPpProdByIdInput").value;
    const errorLabel = document.getElementById("findPpProdByIdLabel");
    const request = "/api/ppprod/byid/" + id;
    findBy([id], errorLabel, request);
}

function findPpProdByProduct() {
    const product = document.getElementById("findPpProdByProductInput").value;
    const errorLabel = document.getElementById("findPpProdByProductLabel");
    const request = "/api/ppprod/byproduct/" + product;
    findBy([product], errorLabel, request);
}

async function addNewPpProd() {
    const errorLabel = document.getElementById("addNewPpProdLabel");
    let label = "";

    ppProdObject.product = document.getElementById("ppProdProduct").value;
    ppProdObject.itemType = document.getElementById("ppProdItemType").value;
    ppProdObject.fab = document.getElementById("ppProdFab").value;
    ppProdObject.fabDesc = document.getElementById("ppProdFabDesc").value;
    ppProdObject.afm = document.getElementById("ppProdAfm").value;
    ppProdObject.process = document.getElementById("ppProdProcess").value;
    ppProdObject.family = document.getElementById("ppProdFamily").value;
    ppProdObject.gdpw = Number(document.getElementById("ppProdGdpw").value);
    ppProdObject.wfUnits = document.getElementById("ppProdWfUnits").value;
    ppProdObject.wfSize = Number(document.getElementById("ppProdWfSize").value);
    ppProdObject.dieUnits = document.getElementById("ppProdDieUnits").value;
    ppProdObject.dieWidth = Number(document.getElementById("ppProdDieWidth").value);
    ppProdObject.dieHeight = Number(document.getElementById("ppProdDieHeight").value);
    ppProdObject.insertTime = document.getElementById("ppProdInsertTime").value;
    ppProdObject.package = document.getElementById("ppProdPackage").value;

    if (fieldsInObjectAreEmpty(ppProdObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/ppprod/create", ppProdObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updatePpProd() {
    const id = document.getElementById("idToBeUpdatedPpProd").value;
    const errorLabel = document.getElementById("updatePpProdLabel");
    let label = "";

    ppProdObject.product = document.getElementById("newPpProdProduct").value;
    ppProdObject.itemType = document.getElementById("newPpProdItemType").value;
    ppProdObject.fab = document.getElementById("newPpProdFab").value;
    ppProdObject.fabDesc = document.getElementById("newPpProdFabDesc").value;
    ppProdObject.afm = document.getElementById("newPpProdAfm").value;
    ppProdObject.process = document.getElementById("newPpProdProcess").value;
    ppProdObject.family = document.getElementById("newPpProdFamily").value;
    ppProdObject.gdpw = Number(document.getElementById("newPpProdGdpw").value);
    ppProdObject.wfUnits = document.getElementById("newPpProdWfUnits").value;
    ppProdObject.wfSize = Number(document.getElementById("newPpProdWfSize").value);
    ppProdObject.dieUnits = document.getElementById("newPpProdDieUnits").value;
    ppProdObject.dieWidth = Number(document.getElementById("newPpProdDieWidth").value);
    ppProdObject.dieHeight = Number(document.getElementById("newPpProdDieHeight").value);
    ppProdObject.insertTime = document.getElementById("newPpProdInsertTime").value;
    ppProdObject.package = document.getElementById("newPpProdPackage").value;



    if (id === undefined || id === "" || fieldsInObjectAreEmpty(ppProdObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/ppprod/update/" + id, ppProdObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}