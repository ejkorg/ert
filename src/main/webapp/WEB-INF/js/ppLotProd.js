function addFindByLotIdPpLotProd() {
    let label = "Nothing to show";

    const lotId = document.getElementById("ppLotProdLot").value;
    const errorLabel = document.getElementById("addFindByLotIdPpLotProdLabel");

    if (lotId === undefined || lotId === "") {
        errorLabel.innerText = "Fill out Lot ID field";
        return;
    }

    const url = "/api/pplotprod/bylotid/" + lotId;

    let parsedResponse = sendSyncRequest("GET", url, true);

    if (parsedResponse !== null) {
        if (parsedResponse.ppLot.status !== "ERROR" && parsedResponse.ppLot.status !== "NO_DATA"
            || parsedResponse.ppProd.status !== "ERROR" && parsedResponse.ppProd.status !== "NO_DATA") {
            label = printObject(parsedResponse.ppLot) + "\n----\n";
            label += printObject(parsedResponse.ppProd);

        }
    }

    errorLabel.innerText = label;
}