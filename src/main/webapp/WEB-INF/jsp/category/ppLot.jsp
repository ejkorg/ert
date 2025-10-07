<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ppLotDiv category">
    <h2 style="text-decoration: underline;">PpLot</h2>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('allPpLotItemsDiv')">List all the PpLot items &#x27A4;</h3>
        <div style="display: none;" id="allPpLotItemsDiv">
            <button class="submitBtn" onclick="listAllPpLotItems()">List all items</button>
            <h4 class="errorLabel" id="allPpLotItemsLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('findPpLotByIdDiv')">Find PpLot by ID &#x27A4;</h3>
        <div style="display: none;" id="findPpLotByIdDiv">
            <form onsubmit="return false;">
                <table>
                    <tr>
                        <td><label>Lot ID:</label></td>
                        <td><input type="number" id="findPpLotByIdInput" name="findPpLotByIdInput" required placeholder="Lot ID"></td>
                    </tr>
                    <tr>
                        <td><button class="submitBtn" onclick="findPpLotById()">Find</button></td>
                    </tr>
                </table>
            </form>
            <h4 class="errorLabel" id="findPpLotByIdLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('findPpLotByLotId')">Find PpLot by lot ID &#x27A4;</h3>
        <div style="display: none;" id="findPpLotByLotId">
            <form onsubmit="return false;">
                <table>
                    <tr>
                        <td><label>Lot ID:</label></td>
                        <td><input type="text" id="findPpLotByLotIdInput" name="findPpLotByLotIdInput" required placeholder="Lot ID"></td>
                    </tr>
                    <tr>
                        <td><button class="submitBtn" onclick="findPpLotByLotID()">Find</button></td>
                    </tr>
                </table>
            </form>
            <h4 class="errorLabel" id="findPpLotByLotIdLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('addNewPpLotDiv')">Add a new PpLot &#x27A4;</h3>
        <div style="display: none;" id="addNewPpLotDiv">
            <form onsubmit="return false;">
                <div id="addNewPpLotInputs">
                    <table>
                        <tr>
                            <td><label>Product:</label></td>
                            <td><input type="text" required name="ppLotProduct" id="ppLotProduct" placeholder="Product"></td>
                        </tr>
                        <tr>
                            <td><label>Lot:</label></td>
                            <td><input type="text" required name="ppLotLot" id="ppLotLot" placeholder="Lot"></td>
                        </tr>
                        <tr>
                            <td><label>Parent lot:</label></td>
                            <td><input type="text" required name="ppLotParentLot" id="ppLotParentLot" placeholder="Parent lot"></td>
                        </tr>
                        <tr>
                            <td><label>Lot owner:</label></td>
                            <td><input type="text" required name="ppLotLotOwner" id="ppLotLotOwner" placeholder="Lot owner"></td>
                        </tr>
                        <tr>
                            <td><label>Parent product:</label></td>
                            <td><input type="text" required name="ppLotParentProduct" id="ppLotParentProduct" placeholder="Parent product"></td>
                        </tr>
                        <tr>
                            <td><label>Source lot:</label></td>
                            <td><input type="text" required name="ppLotSourceLot" id="ppLotSourceLot" placeholder="Source lot"></td>
                        </tr>
                        <tr>
                            <td><label>Insert time:</label></td>
                            <td><input type="text" required name="ppLotInsertTime" id="ppLotInsertTime" placeholder="Insert time"></td>
                        </tr>
                    </table>
                </div>
                <br>
                <button class="submitBtn" onclick="addNewPpLot()">Add</button>
            </form>
            <h4 class="errorLabel" id="addNewPpLotLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('updatePpLotDiv')">Update an existing PpLot &#x27A4;</h3>
        <div style="display: none;" id="updatePpLotDiv">
            <form onsubmit="return false;">
                <div id="updatePpLotInputs">
                    <table>
                        <tr>
                            <td><label>ID of PpLot to be updated:</label></td>
                            <td><input type="number" required name="idToBeUpdatedPpLot" id="idToBeUpdatedPpLot" placeholder="ID of PpLot to be updated"></td>
                        </tr>
                        <tr>
                            <td><label>Product:</label></td>
                            <td><input type="text" required name="newPpLotProduct" id="newPpLotProduct" placeholder="Product"></td>
                        </tr>
                        <tr>
                            <td><label>Lot:</label></td>
                            <td><input type="text" required name="newPpLotLot" id="newPpLotLot" placeholder="Lot"></td>
                        </tr>
                        <tr>
                            <td><label>Parent lot:</label></td>
                            <td><input type="text" required name="newPpLotParentLot" id="newPpLotParentLot" placeholder="Parent lot"></td>
                        </tr>
                        <tr>
                            <td><label>Lot owner:</label></td>
                            <td><input type="text" required name="newPpLotLotOwner" id="newPpLotLotOwner" placeholder="Lot owner"></td>
                        </tr>
                        <tr>
                            <td><label>Parent product:</label></td>
                            <td><input type="text" required name="newPpLotParentProduct" id="newPpLotParentProduct" placeholder="Parent product"></td>
                        </tr>
                        <tr>
                            <td><label>Source lot:</label></td>
                            <td><input type="text" required name="newPpLotSourceLot" id="newPpLotSourceLot" placeholder="Source lot"></td>
                        </tr>
                        <tr>
                            <td><label>Insert time:</label></td>
                            <td><input type="text" required name="newPpLotInsertTime" id="newPpLotInsertTime" placeholder="Insert time"></td>
                        </tr>
                    </table>
                </div>
                <br>
                <button class="submitBtn" onclick="updatePpLot()">Update</button>
            </form>
            <h4 class="errorLabel" id="updatePpLotLabel"></h4>
        </div>
    </div>
</div>