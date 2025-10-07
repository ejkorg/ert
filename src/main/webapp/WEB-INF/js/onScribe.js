let onScribeString = '{"status": "","scribeId": "","waferIdSource": "","waferNum": 0,"waferId": "","lot": "",' +
    '"fab": "", "insertTime": ""}';
const onScribeObject = JSON.parse(onScribeString);


function listAllOnScribeItems() {
    const errorLabel = document.getElementById("allOnScribeItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onscribe/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnScribeById() {
    const id = document.getElementById("findOnScribeByIdInput").value;
    const errorLabel = document.getElementById("findOnScribeByIdLabel");
    const request = "/api/onscribe/byid/" + id;
    findBy([id], errorLabel, request);
}

function findOnScribeByLotIdWafNum() {
    const lotId = document.getElementById("findOnScribeByLotIdInput").value;
    const errorLabel = document.getElementById("findOnScribeByLotIdWafNumLabel");

    const wafNum = document.getElementById("findOnScribeByWafNumInput").value;
    const fab = document.getElementById("findOnScribeByFabInput").value;
    const dataType = document.getElementById("findOnScribeByDataTypeInput").value;
    const request = "/api/onscribe/bylotidandwafernum/" + lotId + "/" + wafNum;
    findBy([lotId, fab, dataType, wafNum], errorLabel, request);
}

function findOnScribeByByLotIdAndWafNumMfg() {
    const lotId = document.getElementById("findOnScribeByLotIdMfgInput").value;
    const errorLabel = document.getElementById("findOnScribeByLotIdAndWafNumMfgLabel");

    const wafNum = Number(document.getElementById("findOnScribeByWafNumMfgInput").value);
    const mfgLot = document.getElementById("findOnScribeByMfgLotMfgInput").value;
    const fab = document.getElementById("findOnScribeByFabMfgInput").value;
    const dataType = document.getElementById("findOnScribeByDataTypeMfgInput").value;
    const request = "/api/onscribe/bylotidsandwafernum?lotId=" + lotId + "&mfgLot=" + mfgLot + "&waferNum=" + wafNum +
        "&fab=" + fab + "&dataType=" + dataType;
    findBy([wafNum], errorLabel, request);
}

function findOnScribeByScribeId() {
    const errorLabel = document.getElementById("findOnScribeByScribeIdLabel");

    const lotId = document.getElementById("findOnScribeByScribeIdLotIdInput").value;
    const scribeId = document.getElementById("findOnScribeByScribeIdScribeIdInput").value;
    const fab = document.getElementById("findOnScribeByScribeIdFabInput").value;
    const dataType = document.getElementById("findOnScribeByScribeIdDataTypeInput").value;
    const request = "/api/onscribe/byscribeid/" + scribeId + "?lotId=" + lotId + "&fab=" + fab +
        "&dataType=" + dataType;
    findBy([scribeId], errorLabel, request);
}

function findOnScribeByStdf() {
    const errorLabel = document.getElementById("findOnScribeByStdfLabel");

    const lotId = document.getElementById("findOnScribeByStdfLotIdInput").value;
    const wafNum = document.getElementById("findOnScribeByStdfWafNumInput").value;
    const scribeId = document.getElementById("findOnScribeByStdfScribeIdInput").value;
    const fab = document.getElementById("findOnScribeByStdfFabInput").value;
    const dataType = document.getElementById("findOnScribeByStdfDataTypeInput").value;
    const request = "/api/onscribe/bystdfinfo/" + lotId + "/" + wafNum + "/" + scribeId + "?fab=" + fab + "&dataTyp=" + dataType;
    findBy([scribeId, wafNum, lotId], errorLabel, request);
}

async function addNewOnScribe() {
    const errorLabel = document.getElementById("addNewOnScribeLabel");
    let label = "";

    onScribeObject.status = document.getElementById("onScribeStatus").value;
    onScribeObject.scribeId = document.getElementById("onScribeScribeId").value;
    onScribeObject.waferIdSource = document.getElementById("onScribeWafIdSource").value;
    onScribeObject.waferNum = document.getElementById("onScribeWafNum").value;
    onScribeObject.waferId = document.getElementById("onScribeWafId").value;
    onScribeObject.lot = document.getElementById("onScribeLot").value;
    onScribeObject.fab = document.getElementById("onScribeFab").value;
    onScribeObject.insertTime = document.getElementById("onScribeInsertTime").value;

    if (fieldsInObjectAreEmpty(onScribeObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onscribe/create", onScribeObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updateOnScribe() {
    const id = document.getElementById("idToBeUpdatedOnScribe").value;
    const errorLabel = document.getElementById("updateOnScribeLabel");
    let label = "";

    onScribeObject.status = document.getElementById("newOnScribeStatus").value;
    onScribeObject.scribeId = document.getElementById("newOnScribeScribeId").value;
    onScribeObject.waferIdSource = document.getElementById("newOnScribeWafIdSource").value;
    onScribeObject.waferNum = document.getElementById("newOnScribeWafNum").value;
    onScribeObject.waferId = document.getElementById("newOnScribeWafId").value;
    onScribeObject.lot = document.getElementById("newOnScribeLot").value;
    onScribeObject.fab = document.getElementById("newOnScribeFab").value;
    onScribeObject.insertTime = document.getElementById("newOnScribeInsertTime").value;



    if (id === undefined || id === "" || fieldsInObjectAreEmpty(onScribeObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onscribe/update/" + id, onScribeObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}