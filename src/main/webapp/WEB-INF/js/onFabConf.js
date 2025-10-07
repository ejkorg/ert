let onFabConfString = '{"foundryFab": "","dataType": "","ltmUrl": "","wmcUrl": "","vid2ScribeUrl": "","scribe2VidUrl": "",' +
    '"scribeResultType": "", "onScribeWaferIdEqualsScribeId": true, "lotIdForOnScribeType": "", "waferIdCreationPattern": "",' +
    '"sourceLotAdjustmentPattern": "", "secondLotgQuery": true, "matchupUrl": ""}';
const onFabConfObject = JSON.parse(onFabConfString);


function listAllOnFabConfItems() {
    const errorLabel = document.getElementById("allOnFabConfItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onfabconf/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnFabConfByFabDataType() {
    const fab = document.getElementById("findOnFabConfByFabInput").value;
    const dataType = document.getElementById("findOnFabConfByDataTypeInput").value;
    const errorLabel = document.getElementById("findOnFabConfByFabDataTypeLabel");
    const request = "/api/onfabconf/byfabanddatatype/" + fab + "/" + dataType;
    findBy([fab, dataType], errorLabel, request);
}

function findOnFabConfById() {
    const id = document.getElementById("findOnFabConfByIdInput").value;
    const errorLabel = document.getElementById("findOnFabConfByIdLabel");
    const request = "/api/onfabconf/byid/" + id;
    findBy([id], errorLabel, request);
}

async function addNewOnFabConf() {
    const errorLabel = document.getElementById("addNewOnFabConfLabel");
    let label = "";

    onFabConfObject.foundryFab = document.getElementById("onFabConfFoundryFab").value;
    onFabConfObject.ltmUrl = document.getElementById("onFabConfLtmUrl").value;
    onFabConfObject.wmcUrl = document.getElementById("onFabConfWmcUrl").value;
    onFabConfObject.vid2ScribeUrl = document.getElementById("onFabConfVid2ScribeUrl").value;
    onFabConfObject.dataType = document.getElementById("onFabConfDataType").value;
    onFabConfObject.scribe2VidUrl = document.getElementById("onFabConfScribe2VidUrl").value;
    onFabConfObject.scribeResultType = document.getElementById("onFabConfScribeResultType").value;
    onFabConfObject.onScribeWaferIdEqualsScribeId = document.getElementById("onFabConfWaferIdEqualsScribeId").value;
    onFabConfObject.lotIdForOnScribeType = document.getElementById("onFabConfLotId").value;
    onFabConfObject.waferIdCreationPattern = document.getElementById("onFabConfWaferId").value;
    onFabConfObject.sourceLotAdjustmentPattern = document.getElementById("onFabConfSourceLot").value;
    onFabConfObject.secondLotgQuery = document.getElementById("onFabConfSecondLotGQuery").value;
    onFabConfObject.matchupUrl = document.getElementById("onFabConfMatchUpUrl").value;

    if (fieldsInObjectAreEmpty(onFabConfObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onfabconf/create", onFabConfObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

function deleteOnFabConf() {
    let label = "Record could not be deleted";
    const id = document.getElementById("deleteOnFabConfId").value;
    const errorLabel = document.getElementById("deleteOnFabConfLabel");

    if (id === undefined || id === "") {
        errorLabel.innerText = "Fill out id field";
        return;
    }

    if (sendSyncRequest("DELETE", "/api/onfabconf/delete/" + id, false).status === 204) {
        label = "Record was deleted successfully";
    }

    errorLabel.innerText = label;
}

async function updateOnFabConf() {
    const errorLabel = document.getElementById("updateOnFabConfLabel");
    const id = document.getElementById("idToBeUpdatedOnFabConf").value;
    let label = "";

    onFabConfObject.foundryFab = document.getElementById("newOnFabConfFoundryFab").value;
    onFabConfObject.ltmUrl = document.getElementById("newOnFabConfLtmUrl").value;
    onFabConfObject.wmcUrl = document.getElementById("newOnFabConfWmcUrl").value;
    onFabConfObject.vid2ScribeUrl = document.getElementById("newOnFabConfVid2ScribeUrl").value;
    onFabConfObject.dataType = document.getElementById("newOnFabConfDataType").value;
    onFabConfObject.scribe2VidUrl = document.getElementById("newOnFabConfScribe2VidUrl").value;
    onFabConfObject.scribeResultType = document.getElementById("newOnFabConfScribeResultType").value;
    onFabConfObject.onScribeWaferIdEqualsScribeId = document.getElementById("newOnFabConfWaferIdEqualsScribeId").value;
    onFabConfObject.lotIdForOnScribeType = document.getElementById("newOnFabConfLotId").value;
    onFabConfObject.waferIdCreationPattern = document.getElementById("newOnFabConfWaferId").value;
    onFabConfObject.sourceLotAdjustmentPattern = document.getElementById("newOnFabConfSourceLot").value;
    onFabConfObject.secondLotgQuery = document.getElementById("newOnFabConfSecondLotGQuery").value;
    onFabConfObject.matchupUrl = document.getElementById("newOnFabConfMatchUpUrl").value;

    if (id === "" || id === undefined || fieldsInObjectAreEmpty(onFabConfObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onfabconf/update/" + id, onFabConfObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}