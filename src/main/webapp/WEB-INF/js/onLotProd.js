function addFindByLotIdOnLotProd() {
    let label = "Nothing to show";

    const lotId = document.getElementById("onLotProdLot").value;
    const errorLabel = document.getElementById("addFindByLotIdOnLotProdLabel");
    const altProduct = document.getElementById("onLotProdAlternateProduct").value;
    const fab = document.getElementById("onLotProdFab").value
    const dataType = document.getElementById("onLotProdDataType").value

    if (lotId === undefined || lotId === "") {
        errorLabel.innerText = "Fill out Lot ID field";
        return;
    }

    const url = "/api/onlotprod/bylotid/" + lotId + "?alternateProduct=" + altProduct + "&fab=" + fab +
        "&dataType=" + dataType;

    let parsedResponse = sendSyncRequest("GET", url, true);

    if (parsedResponse !== null) {
        if (parsedResponse.onLot.status !== "ERROR" && parsedResponse.onLot.status !== "NO_DATA"
        || parsedResponse.onProd.status !== "ERROR" && parsedResponse.onProd.status !== "NO_DATA") {
            label = printObject(parsedResponse.onLot) + "\n----\n";
            label +=printObject(parsedResponse.onProd);

        }
    }

    errorLabel.innerText = label;
}