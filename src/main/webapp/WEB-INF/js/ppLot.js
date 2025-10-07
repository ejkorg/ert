let ppLotString = '{"lot": "","parentLot": "","product": "","lotOwner": "","parentProduct": "","sourceLot": "",' +
    '"insertTime": ""}';
const ppLotObject = JSON.parse(ppLotString);


function listAllPpLotItems() {
    const errorLabel = document.getElementById("allPpLotItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/pplot/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findPpLotById() {
    const id = document.getElementById("findPpLotByIdInput").value;
    const errorLabel = document.getElementById("findPpLotByIdLabel");
    const request = "/api/pplot/byid/" + id;
    findBy([id], errorLabel, request);
}

function findPpLotByLotID() {
    const lotId = document.getElementById("findPpLotByLotIdInput").value;
    const errorLabel = document.getElementById("findPpLotByLotIdLabel");
    const request = "/api/pplot/bylotid/" + lotId;
    findBy([lotId], errorLabel, request);
}

async function addNewPpLot() {
    const errorLabel = document.getElementById("addNewPpLotLabel");
    let label = "";

    ppLotObject.product = document.getElementById("ppLotProduct").value;
    ppLotObject.lot = document.getElementById("ppLotLot").value;
    ppLotObject.parentLot = document.getElementById("ppLotParentLot").value;
    ppLotObject.lotOwner = document.getElementById("ppLotLotOwner").value;
    ppLotObject.parentProduct = document.getElementById("ppLotParentProduct").value;
    ppLotObject.sourceLot = document.getElementById("ppLotSourceLot").value;
    ppLotObject.insertTime = document.getElementById("ppLotInsertTime").value;

    if (fieldsInObjectAreEmpty(ppLotObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/pplot/create", ppLotObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updatePpLot() {
    const id = document.getElementById("idToBeUpdatedPpLot").value;
    const errorLabel = document.getElementById("updatePpLotLabel");
    let label = "";

    ppLotObject.product = document.getElementById("newPpLotProduct").value;
    ppLotObject.lot = document.getElementById("newPpLotLot").value;
    ppLotObject.parentLot = document.getElementById("newPpLotParentLot").value;
    ppLotObject.lotOwner = document.getElementById("newPpLotLotOwner").value;
    ppLotObject.parentProduct = document.getElementById("newPpLotParentProduct").value;
    ppLotObject.sourceLot = document.getElementById("newPpLotSourceLot").value;
    ppLotObject.insertTime = document.getElementById("newPpLotInsertTime").value;

    if (id === undefined || id === "" || fieldsInObjectAreEmpty(ppLotObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/pplot/update/" + id, ppLotObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}