let ertConfString = '{"confName": "","valueString": "","valueNumber": 0,"valueBoolean": false,"dataType": ""}';
const ertConfObject = JSON.parse(ertConfString);


function listAllErtConfItems() {
    const errorLabel = document.getElementById("allErtConfItemsLabel");
    let label = "Nothing to show.";

    const parsedResponse = sendSyncRequest("GET", "/api/ertconf/all", true);

    if (parsedResponse !== null) {
        parsedResponse.reverse();
        label = "";

        for (let i = 0; i < parsedResponse.length; i++) {
            label += printObject(parsedResponse[i]) + "\n----\n";
        }
    }
    errorLabel.innerText = label;
}

function findErtConfByName() {
    const name = document.getElementById("findErtConfByNameInput").value;
    const errorLabel = document.getElementById("findErtConfByNameLabel");
    const request = "/api/ertconf/byconfname/" + name;
    findBy([name], errorLabel, request);
}

function findErtConfById() {
    const id = document.getElementById("findErtConfByIdInput").value;
    const errorLabel = document.getElementById("findErtConfByIdLabel");
    const request = "/api/ertconf/byid/" + id;
    findBy([name], errorLabel, request);
}

async function addNewErtConf() {
    const errorLabel = document.getElementById("addNewConfLabel");
    let label = "";

    ertConfObject.confName = document.getElementById("confName").value;
    ertConfObject.valueString = document.getElementById("valueStringConf").value;
    ertConfObject.valueNumber = Number(document.getElementById("valueNumberConf").value);
    ertConfObject.valueBoolean = document.getElementById("valueBooleanConf").value;
    ertConfObject.dataType = document.getElementById("dataTypeConf").value;

    if (fieldsInObjectAreEmpty(ertConfObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("POST", "/api/ertconf/create", ertConfObject);

    if (response.ok) {
        label = "Insertion was successful";
    } else {
        label = "Unable to add new record";
    }

    errorLabel.innerText = label;
}

function deleteConf() {
    let label = "Record could not be deleted";
    const id = document.getElementById("deleteConfId").value;
    const errorLabel = document.getElementById("deleteConfLabel");

    if (id === undefined || id === "") {
        errorLabel.innerText = "Fill out id field";
        return;
    }

    if (sendSyncRequest("DELETE", "/api/ertconf/delete/" + id, false).status === 204) {
        label = "Record was deleted successfully";
    }

    errorLabel.innerText = label;
}

async function updateErtConf() {
    const errorLabel = document.getElementById("updateConfLabel");
    let label = "";
    const id = document.getElementById("idToBeUpdatedConf");

    ertConfObject.confName = document.getElementById("newConfName").value;
    ertConfObject.valueString = document.getElementById("newValueStringConf").value;
    ertConfObject.valueNumber = Number(document.getElementById("newValueNumberConf").value);
    ertConfObject.valueBoolean = Boolean(document.getElementById("newValueBooleanConf").value);
    ertConfObject.dataType = document.getElementById("newDataTypeConf").value;

    if (id.value === "" || id.value === undefined || fieldsInObjectAreEmpty(ertConfObject)) {
        errorLabel.innerText = "Fill out all fields";
        return;
    }

    const response = await sendAsyncRequest("PUT", "/api/ertconf/update/" + id.value, ertConfObject);

    if (response.ok) {
        label = "Update was successful";
    } else {
        label = "Unable to update record";
    }

    errorLabel.innerText = label;
}