let onLotString = '{"status": "","lot": "","parentLot": "","product": "","lotOwner": "","parentProduct": "",' +
    '"sourceLot": "", "insertTime": "", "alternateProduct": "", "fab": "", "lotType": "",' +
    '"lotClass": "", "alternateLot": "", "subconLot": "", "subconProduct": "", "productCode": "", "mfgLot": ""}';
const onLotObject = JSON.parse(onLotString);


function listAllOnLotItems() {
    const errorLabel = document.getElementById("allOnLotItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/onlot/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findOnLotByLotId() {
    const lotId = document.getElementById("findOnLotByLotIdInput").value;
    const errorLabel = document.getElementById("findOnLotByFabDataTypeLabel");

    const altProduct = document.getElementById("onLotFindByLotIdAlternateProduct").value;
    const fab = document.getElementById("onLotFindByLotIdFab").value;
    const dataType = document.getElementById("onLotFindByLotIdDataType").value;

    const request = "/api/onlot/bylotid/" + lotId + "?alternateProduct=" + altProduct + "&fab=" + fab +
        "&dataType=" + dataType;
    findBy([lotId], errorLabel, request);
}

function findOnLotById() {
    let label = "Nothing to show";
    const id = document.getElementById("findOnLotByIdInput").value;
    const errorLabel = document.getElementById("findOnLotByIdLabel");
    const request = "/api/onlot/byid/" + id;
    findBy([id], errorLabel, request);
}

async function addNewOnLot() {
    const errorLabel = document.getElementById("addNewOnLotLabel");
    let label = "";

    onLotObject.status = document.getElementById("onLotStatus").value;
    onLotObject.lot = document.getElementById("onLotLot").value;
    onLotObject.parentLot = document.getElementById("onLotParentLot").value;
    onLotObject.product = document.getElementById("onLotProduct").value;
    onLotObject.lotOwner = document.getElementById("onLotLotOwner").value;
    onLotObject.parentProduct = document.getElementById("onLotParentProduct").value;
    onLotObject.sourceLot = document.getElementById("onLotSourceLot").value;
    onLotObject.insertTime = document.getElementById("onLotInsertTime").value;
    onLotObject.alternateProduct = document.getElementById("onLotAlternateProduct").value;
    onLotObject.fab = document.getElementById("onLotFab").value;
    onLotObject.lotType = document.getElementById("onLotLotType").value;
    onLotObject.lotClass = document.getElementById("onLotLotClass").value;
    onLotObject.alternateLot = document.getElementById("onLotAlternateLot").value;
    onLotObject.subconLot = document.getElementById("onLotSubconLot").value;
    onLotObject.subconProduct = document.getElementById("onLotSubconProduct").value;
    onLotObject.productCode = document.getElementById("onLotProductCode").value;
    onLotObject.mfgLot = document.getElementById("onLotMfgLot").value;

    if (fieldsInObjectAreEmpty(onLotObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/onlot/create", onLotObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

async function updateOnLot() {
    const id = document.getElementById("idToBeUpdatedOnLot").value;
    const errorLabel = document.getElementById("updateOnLotLabel");
    let label = "";

    onLotObject.status = document.getElementById("newOnLotStatus").value;
    onLotObject.lot = document.getElementById("newOnLotLot").value;
    onLotObject.parentLot = document.getElementById("newOnLotParentLot").value;
    onLotObject.product = document.getElementById("newOnLotProduct").value;
    onLotObject.lotOwner = document.getElementById("newOnLotLotOwner").value;
    onLotObject.parentProduct = document.getElementById("newOnLotParentProduct").value;
    onLotObject.sourceLot = document.getElementById("newOnLotSourceLot").value;
    onLotObject.insertTime = document.getElementById("newOnLotInsertTime").value;
    onLotObject.alternateProduct = document.getElementById("newOnLotAlternateProduct").value;
    onLotObject.fab = document.getElementById("newOnLotFab").value;
    onLotObject.lotType = document.getElementById("newOnLotLotType").value;
    onLotObject.lotClass = document.getElementById("newOnLotLotClass").value;
    onLotObject.alternateLot = document.getElementById("newOnLotAlternateLot").value;
    onLotObject.subconLot = document.getElementById("newOnLotSubconLot").value;
    onLotObject.subconProduct = document.getElementById("newOnLotSubconProduct").value;
    onLotObject.productCode = document.getElementById("newOnLotProductCode").value;
    onLotObject.mfgLot = document.getElementById("newOnLotMfgLot").value;

    if (id === undefined || id === "" || fieldsInObjectAreEmpty(onLotObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/onlot/update/" + id, onLotObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}

