<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onLotDiv category">
  <h2 style="text-decoration: underline;">OnLot</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnLotItemsDiv')">List all the OnLot items &#x27A4;</h3>
    <div style="display: none;" id="allOnLotItemsDiv">
      <button class="submitBtn" onclick="listAllOnLotItems()">List all items</button>
      <h4 class="errorLabel" id="allOnLotItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnLotByLotIdDiv')">Find OnLot by Lot ID &#x27A4;</h3>
    <div style="display: none;" id="findOnLotByLotIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Lot ID:</label></td>
            <td><input type="text" id="findOnLotByLotIdInput" name="findOnLotByLotIdInput" required placeholder="Lot ID"></td>
          </tr>
          <tr>
            <td><label>Alternate product:</label></td>
            <td><input type="text" name="onLotFindByLotIdAlternateProduct" id="onLotFindByLotIdAlternateProduct" placeholder="Alternate product"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" name="onLotFindByLotIdFab" id="onLotFindByLotIdFab" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td>
              <select name="onLotFindByLotIdDataType" id="onLotFindByLotIdDataType">
                <c:forEach items="${dataTypes}" var="type">
                  <option value="${type}">${type}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnLotByLotId()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnLotByFabDataTypeLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnLotByIdDiv')">Find OnLot by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnLotByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnLotByIdInput" name="findOnLotByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnLotById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnLotByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnLotDiv')">Add a new OnLot &#x27A4;</h3>
    <div style="display: none;" id="addNewOnLotDiv">
      <form onsubmit="return false;">
        <div id="addNewOnLotInputs">
          <table>
            <tr>
              <td><label>Lot:</label></td>
              <td><input type="text" required name="onLotLot" id="onLotLot" placeholder="Lot"></td>
            </tr>
            <tr>
              <td><label>Parent lot:</label></td>
              <td><input type="text" required name="onLotParentLot" id="onLotParentLot" placeholder="Parent lot"></td>
            </tr>
            <tr>
              <td><label>Product:</label></td>
              <td><input type="text" required name="onLotProduct" id="onLotProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Lot owner:</label></td>
              <td><input type="text" required name="onLotLotOwner" id="onLotLotOwner" placeholder="Lot owner"></td>
            </tr>
            <tr>
              <td><label>Parent product:</label></td>
              <td><input type="text" required name="onLotParentProduct" id="onLotParentProduct" placeholder="Parent product"></td>
            </tr>
            <tr>
              <td><label>Source lot:</label></td>
              <td><input type="text" required name="onLotSourceLot" id="onLotSourceLot" placeholder="Source lot"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="onLotInsertTime" id="onLotInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Alternate product:</label></td>
              <td><input type="text" required name="onLotAlternateProduct" id="onLotAlternateProduct" placeholder="Alternate product"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="onLotFab" id="onLotFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Lot type:</label></td>
              <td><input type="text" required name="onLotLotType" id="onLotLotType" placeholder="Lot type"></td>
            </tr>
            <tr>
              <td><label>Lot class:</label></td>
              <td><input type="text" required name="onLotLotClass" id="onLotLotClass" placeholder="Lot class"></td>
            </tr>
            <tr>
              <td><label>Alternate lot:</label></td>
              <td><input type="text" required name="onLotAlternateLot" id="onLotAlternateLot" placeholder="Alternate lot"></td>
            </tr>
            <tr>
              <td><label>Subcon lot:</label></td>
              <td><input type="text" required name="onLotSubconLot" id="onLotSubconLot" placeholder="Subcon lot"></td>
            </tr>
            <tr>
              <td><label>Subcon product:</label></td>
              <td><input type="text" required name="onLotSubconProduct" id="onLotSubconProduct" placeholder="Subcon product"></td>
            </tr>
            <tr>
              <td><label>Product code:</label></td>
              <td><input type="text" required name="onLotProductCode" id="onLotProductCode" placeholder="Product code"></td>
            </tr>
            <tr>
              <td><label>Mfg lot:</label></td>
              <td><input type="text" required name="onLotMfgLot" id="onLotMfgLot" placeholder="Mfg lot"></td>
            </tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="onLotStatus" id="onLotStatus">
                  <c:forEach items="${statuses}" var="status">
                    <c:choose>
                      <c:when test="${status.equals(defaultStatus)}">
                        <option selected value="${status}">${status}</option>
                      </c:when>
                      <c:otherwise>
                        <option value="${status}">${status}</option>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnLot()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnLotLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnLotDiv')">Update an existing OnLot &#x27A4;</h3>
    <div style="display: none;" id="updateOnLotDiv">
      <form onsubmit="return false;">
        <div id="updateOnLotInputs">
          <table>
            <tr>
              <td><label>ID of OnLot to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnLot" id="idToBeUpdatedOnLot" placeholder="ID of OnLot to be updated"></td>
            </tr>
            <tr>
              <td><label>Lot:</label></td>
              <td><input type="text" required name="newOnLotLot" id="newOnLotLot" placeholder="Lot"></td>
            </tr>
            <tr>
              <td><label>Parent lot:</label></td>
              <td><input type="text" required name="newOnLotParentLot" id="newOnLotParentLot" placeholder="Parent lot"></td>
            </tr>
            <tr>
              <td><label>Product:</label></td>
              <td><input type="text" required name="newOnLotProduct" id="newOnLotProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Lot owner:</label></td>
              <td><input type="text" required name="newOnLotLotOwner" id="newOnLotLotOwner" placeholder="Lot owner"></td>
            </tr>
            <tr>
              <td><label>Parent product:</label></td>
              <td><input type="text" required name="newOnLotParentProduct" id="newOnLotParentProduct" placeholder="Parent product"></td>
            </tr>
            <tr>
              <td><label>Source lot:</label></td>
              <td><input type="text" required name="newOnLotSourceLot" id="newOnLotSourceLot" placeholder="Source lot"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="newOnLotInsertTime" id="newOnLotInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Alternate product:</label></td>
              <td><input type="text" required name="newOnLotAlternateProduct" id="newOnLotAlternateProduct" placeholder="Alternate product"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="newOnLotFab" id="newOnLotFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Lot type:</label></td>
              <td><input type="text" required name="newOnLotLotType" id="newOnLotLotType" placeholder="Lot type"></td>
            </tr>
            <tr>
              <td><label>Lot class:</label></td>
              <td><input type="text" required name="newOnLotLotClass" id="newOnLotLotClass" placeholder="Lot class"></td>
            </tr>
            <tr>
              <td><label>Alternate lot:</label></td>
              <td><input type="text" required name="newOnLotAlternateLot" id="newOnLotAlternateLot" placeholder="Alternate lot"></td>
            </tr>
            <tr>
              <td><label>Subcon lot:</label></td>
              <td><input type="text" required name="newOnLotSubconLot" id="newOnLotSubconLot" placeholder="Subcon lot"></td>
            </tr>
            <tr>
              <td><label>Subcon product:</label></td>
              <td><input type="text" required name="newOnLotSubconProduct" id="newOnLotSubconProduct" placeholder="Subcon product"></td>
            </tr>
            <tr>
              <td><label>Product code:</label></td>
              <td><input type="text" required name="newOnLotProductCode" id="newOnLotProductCode" placeholder="Product code"></td>
            </tr>
            <tr>
              <td><label>Mfg lot:</label></td>
              <td><input type="text" required name="newOnLotMfgLot" id="newOnLotMfgLot" placeholder="Mfg lot"></td>
            </tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="newOnLotStatus" id="newOnLotStatus">
                  <c:forEach items="${statuses}" var="status">
                    <c:choose>
                      <c:when test="${status.equals(defaultStatus)}">
                        <option selected value="${status}">${status}</option>
                      </c:when>
                      <c:otherwise>
                        <option value="${status}">${status}</option>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnLot()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnLotLabel"></h4>
    </div>
  </div>
</div>