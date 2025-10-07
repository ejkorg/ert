let onSliceString = '{"slice": "","puckId": "","runId": 0,"sliceSourceLot": "","startLot": "",' +
    '"fabWaferId": "", "globalWaferId": "", "fabSourceLot" : "", "sliceStartTime" : "", "slicePartname" : "", "sliceLottype" : "",' +
    '"sliceSuplierid" : "", "puckHeight" : 0, "insertTime" : ""}';
const onSliceObject = JSON.parse(onSliceString);


function listAllOnSliceItems() {
    const errorLabel = document.getElementById("allOnSliceItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onslice/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnSliceById() {
    const id = document.getElementById("findOnSliceByIdInput").value;
    const errorLabel = document.getElementById("findOnSliceByIdLabel");
    const request = "/api/onslice/byid/" + id;
    findBy([id], errorLabel, request);
}

function findOnSliceByGlobalWafId() {
    const id = document.getElementById("findOnSliceByGlobalWafIdInput").value;
    const errorLabel = document.getElementById("findOnSliceByGlobalWafIdLabel");
    const request = "/api/onslice/byglobalwaferid/" + id;
    findBy([id], errorLabel, request);
}

function findOnSliceBySlice() {
    const slice = document.getElementById("findOnSliceBySliceInput").value;
    const errorLabel = document.getElementById("findOnSliceBySliceLabel");
    const request = "/api/onslice/byslice/" + slice;
    findBy([slice], errorLabel, request);
}

async function addNewOnSlice() {
    const errorLabel = document.getElementById("addNewOnSliceLabel");
    let label = "";

    onSliceObject.slice = document.getElementById("onSliceSlice").value;
    onSliceObject.puckId = document.getElementById("onSlicePuckId").value;
    onSliceObject.runId = document.getElementById("onSliceRunId").value;
    onSliceObject.sliceSourceLot = document.getElementById("onSliceSliceSourceLot").value;
    onSliceObject.startLot = document.getElementById("onSliceStartLot").value;
    onSliceObject.fabWaferId = document.getElementById("onSliceFabWafId").value;
    onSliceObject.globalWaferId = document.getElementById("onSliceGlobalWafId").value;
    onSliceObject.fabSourceLot = document.getElementById("onSliceFabSourceLot").value;
    onSliceObject.sliceStartTime = document.getElementById("onSliceSliceStartTime").value;
    onSliceObject.slicePartname = document.getElementById("onSliceSlicePartName").value;
    onSliceObject.sliceLottype = document.getElementById("onSliceSliceLotType").value;
    onSliceObject.sliceSuplierid = document.getElementById("onSliceSliceSupplierId").value;
    onSliceObject.puckHeight = Number(document.getElementById("onSlicePuckHeight").value);
    onSliceObject.insertTime = document.getElementById("onSliceInsertTime").value;


    if (fieldsInObjectAreEmpty(onSliceObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onslice/create", onSliceObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updateOnSlice() {
    const id = document.getElementById("idToBeUpdatedOnSlice").value;
    const errorLabel = document.getElementById("updateOnSliceLabel");
    let label = "";

    onSliceObject.slice = document.getElementById("newOnSliceSlice").value;
    onSliceObject.puckId = document.getElementById("newOnSlicePuckId").value;
    onSliceObject.runId = document.getElementById("newOnSliceRunId").value;
    onSliceObject.sliceSourceLot = document.getElementById("newOnSliceSliceSourceLot").value;
    onSliceObject.startLot = document.getElementById("newOnSliceStartLot").value;
    onSliceObject.fabWaferId = document.getElementById("newOnSliceFabWafId").value;
    onSliceObject.globalWaferId = document.getElementById("newOnSliceGlobalWafId").value;
    onSliceObject.fabSourceLot = document.getElementById("newOnSliceFabSourceLot").value;
    onSliceObject.sliceStartTime = document.getElementById("newOnSliceSliceStartTime").value;
    onSliceObject.slicePartname = document.getElementById("newOnSliceSlicePartName").value;
    onSliceObject.sliceLottype = document.getElementById("newOnSliceSliceLotType").value;
    onSliceObject.sliceSuplierid = document.getElementById("newOnSliceSliceSupplierId").value;
    onSliceObject.puckHeight = Number(document.getElementById("newOnSlicePuckHeight").value);
    onSliceObject.insertTime = document.getElementById("newOnSliceInsertTime").value;



    if (id === undefined || id === "" || fieldsInObjectAreEmpty(onSliceObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onslice/update/" + id, onSliceObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}