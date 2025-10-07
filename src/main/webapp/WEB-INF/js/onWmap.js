let onWmapString = '{"status": "","product": "","wfUnits": "","wfSize": 0,"flatType": "","flat": "",' +
    '"dieWidth": 0, "dieHeight": 0, "centerX" : 0, "centerY" : 0, "positiveX" : "", "positiveY" : "",' +
    '"reticleRows" : 0, "reticleCols" : 0, "reticleRowOffset" : 0, "reticleColOffset" : 0, "confirmed" : "", ' +
    '"deviceCount" : 0, "confirmTime" : "", "comments" : "", "insertTime" : "", "inputFile" : "", "cfgId" : 0, ' +
    '"location" : "", "refDieX" : 0, "refDieY" : 0, "refDieInitDt" : "", "wmcDevice" : ""}';
const onWmapObject = JSON.parse(onWmapString);

function listAllOnWmapItems() {
    const errorLabel = document.getElementById("allOnWmapItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onwmap/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnWmapById() {
    const id = document.getElementById("findOnWmapByIdInput").value;
    const errorLabel = document.getElementById("findOnWmapByIdLabel");
    const request = "/api/onwmap/byid/" + id;
    findBy([id], errorLabel, request);
}

function findOnWmapByProductSearchedLotPart() {
    const errorLabel = document.getElementById("findOnWmapByProductSearchedLotPartLabel");
    const wmcServiceKey = document.getElementById("findOnWmapByWmcServiceKeyInput").value;
    const product = document.getElementById("findOnWmapByProductInput").value;
    const searchedLotPart = document.getElementById("findOnWmapBySearchedLotPartInput").value;
    const fab = document.getElementById("findOnWmapByFabInput").value;
    const dataType = document.getElementById("findOnWmapByDataTypeInput").value;
    const scribe = document.getElementById("findOnWmapByScribeInput").value;
    const endDate = document.getElementById("findOnWmapByEndDateInput").value;
    const request = "/api/onwmap/byproduct/" + wmcServiceKey + "/" + product + "/" + searchedLotPart + "?fab=" + fab +
        "&dataType=" + dataType + "&scribe=" + scribe + "&endDate=" + endDate;
    findBy([wmcServiceKey, product, searchedLotPart], errorLabel, request);
}

async function addNewOnWmap() {
    const errorLabel = document.getElementById("addNewOnWmapLabel");
    let label = "";

    onWmapObject.status = document.getElementById("onWmapStatus").value;
    onWmapObject.product = document.getElementById("onWmapProduct").value;
    onWmapObject.wfUnits = document.getElementById("onWmapWfUnits").value;
    onWmapObject.wfSize = Number(document.getElementById("onWmapWfSize").value);
    onWmapObject.flatType = document.getElementById("onWmapFlatType").value;
    onWmapObject.flat = document.getElementById("onWmapFlat").value;
    onWmapObject.dieWidth = Number(document.getElementById("onWmapDieWidth").value);
    onWmapObject.dieHeight = Number(document.getElementById("onWmapDieHeight").value);
    onWmapObject.centerX = Number(document.getElementById("onWmapCenterX").value);
    onWmapObject.centerY = Number(document.getElementById("onWmapCenterY").value);
    onWmapObject.positiveX = document.getElementById("onWmapPositiveX").value;
    onWmapObject.positiveY = document.getElementById("onWmapPositiveY").value;
    onWmapObject.reticleRows = Number(document.getElementById("onWmapReticleRows").value);
    onWmapObject.reticleCols = Number(document.getElementById("onWmapReticleCols").value);
    onWmapObject.reticleRowOffset = Number(document.getElementById("onWmapReticleRowOffset").value);
    onWmapObject.reticleColOffset = Number(document.getElementById("onWmapReticleColOffset").value);
    onWmapObject.confirmed = document.getElementById("onWmapConfirmed").value;
    onWmapObject.deviceCount = Number(document.getElementById("onWmapDeviceCount").value);
    onWmapObject.confirmTime = document.getElementById("onWmapConfirmTime").value;
    onWmapObject.comments = document.getElementById("onWmapComments").value;
    onWmapObject.insertTime = document.getElementById("onWmapInsertTime").value;
    onWmapObject.inputFile = document.getElementById("onWmapInputFile").value;
    onWmapObject.cfgId = Number(document.getElementById("onWmapCfgId").value);
    onWmapObject.location = document.getElementById("onWmapLocation").value;
    onWmapObject.refDieX = Number(document.getElementById("onWmapRefDieX").value);
    onWmapObject.refDieY = Number(document.getElementById("onWmapRefDieY").value);
    onWmapObject.refDieInitDt = document.getElementById("onWmapRefDieInitDt").value;
    onWmapObject.wmcDevice = document.getElementById("onWmapWmcDevice").value;

    if (fieldsInObjectAreEmpty(onWmapObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onwmap/create", onWmapObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updateOnWmap() {
    const id = document.getElementById("idToBeUpdatedOnWmap").value;
    const errorLabel = document.getElementById("updateOnWmapLabel");
    let label = "";

    onWmapObject.status = document.getElementById("newOnWmapStatus").value;
    onWmapObject.product = document.getElementById("newOnWmapProduct").value;
    onWmapObject.wfUnits = document.getElementById("newOnWmapWfUnits").value;
    onWmapObject.wfSize = Number(document.getElementById("newOnWmapWfSize").value);
    onWmapObject.flatType = document.getElementById("newOnWmapFlatType").value;
    onWmapObject.flat = document.getElementById("newOnWmapFlat").value;
    onWmapObject.dieWidth = Number(document.getElementById("newOnWmapDieWidth").value);
    onWmapObject.dieHeight = Number(document.getElementById("newOnWmapDieHeight").value);
    onWmapObject.centerX = Number(document.getElementById("newOnWmapCenterX").value);
    onWmapObject.centerY = Number(document.getElementById("newOnWmapCenterY").value);
    onWmapObject.positiveX = document.getElementById("newOnWmapPositiveX").value;
    onWmapObject.positiveY = document.getElementById("newOnWmapPositiveY").value;
    onWmapObject.reticleRows = Number(document.getElementById("newOnWmapReticleRows").value);
    onWmapObject.reticleCols = Number(document.getElementById("newOnWmapReticleCols").value);
    onWmapObject.reticleRowOffset = Number(document.getElementById("newOnWmapReticleRowOffset").value);
    onWmapObject.reticleColOffset = Number(document.getElementById("newOnWmapReticleColOffset").value);
    onWmapObject.confirmed = document.getElementById("newOnWmapConfirmed").value;
    onWmapObject.deviceCount = Number(document.getElementById("newOnWmapDeviceCount").value);
    onWmapObject.confirmTime = document.getElementById("newOnWmapConfirmTime").value;
    onWmapObject.comments = document.getElementById("newOnWmapComments").value;
    onWmapObject.insertTime = document.getElementById("newOnWmapInsertTime").value;
    onWmapObject.inputFile = document.getElementById("newOnWmapInputFile").value;
    onWmapObject.cfgId = Number(document.getElementById("newOnWmapCfgId").value);
    onWmapObject.location = document.getElementById("newOnWmapLocation").value;
    onWmapObject.refDieX = Number(document.getElementById("newOnWmapRefDieX").value);
    onWmapObject.refDieY = Number(document.getElementById("newOnWmapRefDieY").value);
    onWmapObject.refDieInitDt = document.getElementById("newOnWmapRefDieInitDt").value;
    onWmapObject.wmcDevice = document.getElementById("newOnWmapWmcDevice").value;



    if (id === undefined || id === "" || fieldsInObjectAreEmpty(onWmapObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onwmap/update/" + id, onWmapObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}