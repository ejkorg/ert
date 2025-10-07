<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ppProdDiv category">
    <h2 style="text-decoration: underline;">PpProd</h2>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('allPpProdItemsDiv')">List all the PpProd items &#x27A4;</h3>
        <div style="display: none;" id="allPpProdItemsDiv">
            <button class="submitBtn" onclick="listAllPpProdItems()">List all items</button>
            <h4 class="errorLabel" id="allPpProdItemsLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('findPpProdByIdDiv')">Find PpProd by ID &#x27A4;</h3>
        <div style="display: none;" id="findPpProdByIdDiv">
            <form onsubmit="return false;">
                <table>
                    <tr>
                        <td><label>Lot ID:</label></td>
                        <td><input type="number" id="findPpProdByIdInput" name="findPpProdByIdInput" required placeholder="Lot ID"></td>
                    </tr>
                    <tr>
                        <td><button class="submitBtn" onclick="findPpProdById()">Find</button></td>
                    </tr>
                </table>
            </form>
            <h4 class="errorLabel" id="findPpProdByIdLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('findPpProdByProduct')">Find PpProd by product &#x27A4;</h3>
        <div style="display: none;" id="findPpProdByProduct">
            <form onsubmit="return false;">
                <table>
                    <tr>
                        <td><label>Product:</label></td>
                        <td><input type="text" id="findPpProdByProductInput" name="findPpProdByProductInput" required placeholder="Product"></td>
                    </tr>
                    <tr>
                        <td><button class="submitBtn" onclick="findPpProdByProduct()">Find</button></td>
                    </tr>
                </table>
            </form>
            <h4 class="errorLabel" id="findPpProdByProductLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('addNewPpProdDiv')">Add a new PpProd &#x27A4;</h3>
        <div style="display: none;" id="addNewPpProdDiv">
            <form onsubmit="return false;">
                <div id="addNewPpProdInputs">
                    <table>
                        <tr>
                            <td><label>Product:</label></td>
                            <td><input type="text" required name="ppProdProduct" id="ppProdProduct" placeholder="Product"></td>
                        </tr>
                        <tr>
                            <td><label>Item type:</label></td>
                            <td><input type="text" required name="ppProdItemType" id="ppProdItemType" placeholder="Item type"></td>
                        </tr>
                        <tr>
                            <td><label>Fab:</label></td>
                            <td><input type="text" required name="ppProdFab" id="ppProdFab" placeholder="Fab"></td>
                        </tr>
                        <tr>
                            <td><label>Fab desc:</label></td>
                            <td><input type="text" required name="ppProdFabDesc" id="ppProdFabDesc" placeholder="Fab desc"></td>
                        </tr>
                        <tr>
                            <td><label>Afm:</label></td>
                            <td><input type="text" required name="ppProdAfm" id="ppProdAfm" placeholder="Afm"></td>
                        </tr>
                        <tr>
                            <td><label>Process:</label></td>
                            <td><input type="text" required name="ppProdProcess" id="ppProdProcess" placeholder="Process"></td>
                        </tr>
                        <tr>
                            <td><label>Family:</label></td>
                            <td><input type="text" required name="ppProdFamily" id="ppProdFamily" placeholder="Family"></td>
                        </tr>
                        <tr>
                            <td><label>Gdpw:</label></td>
                            <td><input type="number" required name="ppProdGdpw" id="ppProdGdpw" placeholder="Gdpw"></td>
                        </tr>
                        <tr>
                            <td><label>Wf units:</label></td>
                            <td><input type="text" required name="ppProdWfUnits" id="ppProdWfUnits" placeholder="Wf units"></td>
                        </tr>
                        <tr>
                            <td><label>Wf size:</label></td>
                            <td><input type="number" required name="ppProdWfSize" id="ppProdWfSize" placeholder="Wf size"></td>
                        </tr>
                        <tr>
                            <td><label>Die units:</label></td>
                            <td><input type="text" required name="ppProdDieUnits" id="ppProdDieUnits" placeholder="Die units"></td>
                        </tr>
                        <tr>
                            <td><label>Die width:</label></td>
                            <td><input type="number" required name="ppProdDieWidth" id="ppProdDieWidth" placeholder="Die width"></td>
                        </tr>
                        <tr>
                            <td><label>Die height:</label></td>
                            <td><input type="number" required name="ppProdDieHeight" id="ppProdDieHeight" placeholder="Die height"></td>
                        </tr>
                        <tr>
                            <td><label>Insert time:</label></td>
                            <td><input type="text" required name="ppProdInsertTime" id="ppProdInsertTime" placeholder="Insert time"></td>
                        </tr>
                        <tr>
                            <td><label>Package:</label></td>
                            <td><input type="text" required name="ppProdPackage" id="ppProdPackage" placeholder="Package"></td>
                        </tr>
                    </table>
                </div>
                <br>
                <button class="submitBtn" onclick="addNewPpProd()">Add</button>
            </form>
            <h4 class="errorLabel" id="addNewPpProdLabel"></h4>
        </div>
    </div>
    <div>
        <h3 class="linkTitle" onclick="showHideElement('updatePpProdDiv')">Update an existing PpProd &#x27A4;</h3>
        <div style="display: none;" id="updatePpProdDiv">
            <form onsubmit="return false;">
                <div id="updatePpProdInputs">
                    <table>
                        <tr>
                            <td><label>ID of PpProd to be updated:</label></td>
                            <td><input type="number" required name="idToBeUpdatedPpProd" id="idToBeUpdatedPpProd" placeholder="ID of PpProd to be updated"></td>
                        </tr>
                        <tr>
                            <td><label>Product:</label></td>
                            <td><input type="text" required name="newPpProdProduct" id="newPpProdProduct" placeholder="Product"></td>
                        </tr>
                        <tr>
                            <td><label>Item type:</label></td>
                            <td><input type="text" required name="newPpProdItemType" id="newPpProdItemType" placeholder="Item type"></td>
                        </tr>
                        <tr>
                            <td><label>Fab:</label></td>
                            <td><input type="text" required name="newPpProdFab" id="newPpProdFab" placeholder="Fab"></td>
                        </tr>
                        <tr>
                            <td><label>Fab desc:</label></td>
                            <td><input type="text" required name="newPpProdFabDesc" id="newPpProdFabDesc" placeholder="Fab desc"></td>
                        </tr>
                        <tr>
                            <td><label>Afm:</label></td>
                            <td><input type="text" required name="newPpProdAfm" id="newPpProdAfm" placeholder="Afm"></td>
                        </tr>
                        <tr>
                            <td><label>Process:</label></td>
                            <td><input type="text" required name="newPpProdProcess" id="newPpProdProcess" placeholder="Process"></td>
                        </tr>
                        <tr>
                            <td><label>Family:</label></td>
                            <td><input type="text" required name="newPpProdFamily" id="newPpProdFamily" placeholder="Family"></td>
                        </tr>
                        <tr>
                            <td><label>Gdpw:</label></td>
                            <td><input type="number" required name="newPpProdGdpw" id="newPpProdGdpw" placeholder="Gdpw"></td>
                        </tr>
                        <tr>
                            <td><label>Wf units:</label></td>
                            <td><input type="text" required name="newPpProdWfUnits" id="newPpProdWfUnits" placeholder="Wf units"></td>
                        </tr>
                        <tr>
                            <td><label>Wf size:</label></td>
                            <td><input type="number" required name="newPpProdWfSize" id="newPpProdWfSize" placeholder="Wf size"></td>
                        </tr>
                        <tr>
                            <td><label>Die units:</label></td>
                            <td><input type="text" required name="newPpProdDieUnits" id="newPpProdDieUnits" placeholder="Die units"></td>
                        </tr>
                        <tr>
                            <td><label>Die width:</label></td>
                            <td><input type="number" required name="newPpProdDieWidth" id="newPpProdDieWidth" placeholder="Die width"></td>
                        </tr>
                        <tr>
                            <td><label>Die height:</label></td>
                            <td><input type="number" required name="newPpProdDieHeight" id="newPpProdDieHeight" placeholder="Die height"></td>
                        </tr>
                        <tr>
                            <td><label>Insert time:</label></td>
                            <td><input type="text" required name="newPpProdInsertTime" id="newPpProdInsertTime" placeholder="Insert time"></td>
                        </tr>
                        <tr>
                            <td><label>Package:</label></td>
                            <td><input type="text" required name="newPpProdPackage" id="newPpProdPackage" placeholder="Package"></td>
                        </tr>
                    </table>
                </div>
                <br>
                <button class="submitBtn" onclick="updatePpProd()">Update</button>
            </form>
            <h4 class="errorLabel" id="updatePpProdLabel"></h4>
        </div>
    </div>
</div>