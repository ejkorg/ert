const http = new XMLHttpRequest();


async function sendAsyncRequest(method, url, objectData) {
    return await fetch(url, {
        method: method,
        body: JSON.stringify(objectData),
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
            "accept": "*/*"
        }
    });

}

function sendSyncRequest(method, url, parseResponse) {
    http.open(method, url, false);
    http.send();
    if (parseResponse) {
        return JSON.parse(http.response);
    }
    return http;
}

function findBy(requiredElements = [], labelElement, request) {
    let label = "Nothing to show";
    for (let i = 0; i < requiredElements.length; i++) {
        if (requiredElements[i] === undefined || requiredElements[i] === "") {
            labelElement.innerText = "Fill out all fields"
            return;
        }
    }

    let parsedResponse = sendSyncRequest("GET", request, true);

    if (parsedResponse !== null) {
        if (parsedResponse.status !== "ERROR" && parsedResponse.status !== "NO_DATA") {
            label = printObject(parsedResponse);
        }
    }

    labelElement.innerText = label;
}

function showHideElement(id) {
    const element = document.getElementById(id);
    if (element.style.display === "none") {
        element.style.display = "block";
    } else {
        element.style.display = "none";
    }
}

function fieldsInObjectAreEmpty(object) {
    for (let key in object) {
        if (object[key] === "") {
            return true;
        }
        if (typeof object[key] === "number") {
            if (object[key] === 0) {
                return true;
            }
        }
    }
    return false;
}

function printObject(object) {
    const fields = [object.id, object.confName, object.valueString, object.valueNumber, object.valueBoolean,
        object.dataType, object.status, object.foundryFab, object.ltmUrl, object.wmcUrl, object.vid2ScribeUrl,
        object.scribe2VidUrl, object.scribeResultType, object.onScribeWaferIdEqualsScribeId, object.lotIdForOnScribeType,
        object.waferIdCreationPattern, object.sourceLotAdjustmentPattern, object.secondLotgQuery, object.matchupUrl,
        object.lot, object.parentLot, object.product, object.lotOwner, object.parentProduct, object.sourceLot,
        object.insertTime, object.alternateProduct, object.fab, object.lotType, object.lotClass, object.alternateLot,
        object.subconLot, object.subconProduct, object.productCode, object.mfgLot, object.errorMessage, object.itemType,
        object.fabDesc, object.afm, object.process, object.family, object.gdpw, object.wfUnits, object.wfSize,
        object.dieUnits, object.dieWidth, object.dieHeight, object.pti4, object.technology, object.maskSet, object.package,
        object.scribeId, object.waferIdSource, object.waferNum, object.waferId, object.slice, object.puckId, object.runId,
        object.sliceSourceLot, object.startLot, object.fabWaferId, object.fabSourceLot, object.globalWaferId,
        object.sliceStartTime, object.slicePartname, object.sliceLottype, object.sliceSuplierid, object.puckHeight,
        object.flatType, object.flat, object.centerX, object.centerY, object.positiveX, object.positiveY,
        object.reticleRows, object.reticleCols, object.reticleRowOffset, object.reticleColOffset, object.confirmed,
        object.deviceCount, object.confirmTime, object.comments, object.inputFile, object.cfgId, object.location,
        object.refDieX, object.refDieY, object.refDieInitDt, object.wmcDevice];
    const labels = ["ID", "Conf name", "Value string", "Value number", "Value boolean", "Data type", "Status",
        "Foundry fab", "Ltm url", "Wmc url", "Vid2 scribe url", "Scribe2 vid url", "Scribe result type",
        "On scribe wafer ID equals scribe ID", "Lot ID for on scribe type", "Wafer ID creation pattern",
        "Source lot adjustment pattern", "Second lotg query", "Matchup url", "Lot", "Parent lot", "Product", "Lot owner",
        "Parent product", "Source lot", "Insert time", "Alternate product", "Fab", "Lot type", "Lot class", "Alternate lot",
        "Subcon lot", "Subcon product", "Product code", "Mfg lot", "Error message", "Item type", "Fab desc", "Afm",
        "Process", "Family", "Gdpw", "Wf units", "Wf size", "Die units", "Die width", "Die height", "Pti4", "Technology",
        "Mask set", "Package", "Scribe ID", "Wafer ID source", "Wafer num", "Wafer ID", "Slice", "Puck ID", "Run ID",
        "Slice source lot", "Start lot", "Fab wafer ID", "Fab source lot", "Global wafer ID", "Slice start time",
        "Slice partname", "Slice lottype", "Slice supplier ID", "Puck height", "Flat type", "Flat", "Center x", "Center y",
        "Positive x", "Positive y", "Reticle rows", "Reticle cols", "Reticle row offset", "Reticle col offset", "Confirmed",
        "Device count", "Confirm time", "Comments", "Input file", "Cfg ID", "Location", "Ref die x", "Ref die y",
        "Ref die init dt", "Wmc device"]

    if (labels.length !== fields.length) {
        return "Array length are not equal";
    }
    text = "";
    for (let i = 0; i < labels.length; i++) {
        if (fields[i] !== undefined && fields[i] !== null) {
            if (i === labels.length -1) {
                text += labels[i] + ": " + fields[i];
            } else {
                text += labels[i] + ": " + fields[i] + " | ";
            }
        }
    }
    return text;
}