<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onSliceDiv category">
  <h2 style="text-decoration: underline;">OnSlice</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnSliceItemsDiv')">List all the OnSlice items &#x27A4;</h3>
    <div style="display: none;" id="allOnSliceItemsDiv">
      <button class="submitBtn" onclick="listAllOnSliceItems()">List all items</button>
      <h4 class="errorLabel" id="allOnSliceItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnSliceByIdDiv')">Find OnSlice by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnSliceByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnSliceByIdInput" name="findOnSliceByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnSliceById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnSliceByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnSliceByGlobalWafIdDiv')">Find OnSlice by global wafer ID &#x27A4;</h3>
    <div style="display: none;" id="findOnSliceByGlobalWafIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Global wafer ID:</label></td>
            <td><input type="text" id="findOnSliceByGlobalWafIdInput" name="findOnSliceByGlobalWafIdInput" required placeholder="Global wafer ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnSliceByGlobalWafId()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnSliceByGlobalWafIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnSliceBySliceDiv')">Find OnSlice by slice &#x27A4;</h3>
    <div style="display: none;" id="findOnSliceBySliceDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Slice:</label></td>
            <td><input type="text" id="findOnSliceBySliceInput" name="findOnSliceBySliceInput" required placeholder="Slice"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnSliceBySlice()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnSliceBySliceLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnSliceDiv')">Add a new OnSlice &#x27A4;</h3>
    <div style="display: none;" id="addNewOnSliceDiv">
      <form onsubmit="return false;">
        <div id="addNewOnSliceInputs">
          <table>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="onSliceStatus" id="onSliceStatus">
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
            <tr>
              <td><label>Slice:</label></td>
              <td><input type="text" required name="onSliceSlice" id="onSliceSlice" placeholder="Slice"></td>
            </tr>
            <tr>
              <td><label>Puck ID:</label></td>
              <td><input type="text" required name="onSlicePuckId" id="onSlicePuckId" placeholder="Puck ID"></td>
            </tr>
            <tr>
              <td><label>Run ID:</label></td>
              <td><input type="text" required name="onSliceRunId" id="onSliceRunId" placeholder="Run ID"></td>
            </tr>
            <tr>
              <td><label>Slice source lot:</label></td>
              <td><input type="text" required name="onSliceSliceSourceLot" id="onSliceSliceSourceLot" placeholder="Slice source lot"></td>
            </tr>
            <tr>
              <td><label>Start lot:</label></td>
              <td><input type="text" required name="onSliceStartLot" id="onSliceStartLot" placeholder="Start lot"></td>
            </tr>
            <tr>
              <td><label>Fab wafer ID:</label></td>
              <td><input type="text" required name="onSliceFabWafId" id="onSliceFabWafId" placeholder="Fab wafer ID"></td>
            </tr>
            <tr>
              <td><label>Global wafer ID:</label></td>
              <td><input type="text" required name="onSliceGlobalWafId" id="onSliceGlobalWafId" placeholder="Global wafer ID"></td>
            </tr>
            <tr>
              <td><label>Fab source lot:</label></td>
              <td><input type="text" required name="onSliceFabSourceLot" id="onSliceFabSourceLot" placeholder="Fab source lot"></td>
            </tr>
            <tr>
              <td><label>Slice start time:</label></td>
              <td><input type="text" required name="onSliceSliceStartTime" id="onSliceSliceStartTime" placeholder="Slice start time"></td>
            </tr>
            <tr>
              <td><label>Slice part name:</label></td>
              <td><input type="text" required name="onSliceSlicePartName" id="onSliceSlicePartName" placeholder="Slice part name"></td>
            </tr>
            <tr>
              <td><label>Slice lot type:</label></td>
              <td><input type="text" required name="onSliceSliceLotType" id="onSliceSliceLotType" placeholder="Slice lot type"></td>
            </tr>
            <tr>
              <td><label>Slice supplier ID:</label></td>
              <td><input type="text" required name="onSliceSliceSupplierId" id="onSliceSliceSupplierId" placeholder="Slice supplier ID"></td>
            </tr>
            <tr>
              <td><label>Puck height:</label></td>
              <td><input type="number" required name="onSlicePuckHeight" id="onSlicePuckHeight" placeholder="Puck height"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="onSliceInsertTime" id="onSliceInsertTime" placeholder="Insert time"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnSlice()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnSliceLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnSliceDiv')">Update an existing OnSlice &#x27A4;</h3>
    <div style="display: none;" id="updateOnSliceDiv">
      <form onsubmit="return false;">
        <div id="updateOnSliceInputs">
          <table>
            <tr>
              <td><label>ID of OnSlice to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnSlice" id="idToBeUpdatedOnSlice" placeholder="ID of OnSlice to be updated"></td>
            </tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="newOnSliceStatus" id="newOnSliceStatus">
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
            <tr>
              <td><label>Slice:</label></td>
              <td><input type="text" required name="newOnSliceSlice" id="newOnSliceSlice" placeholder="Slice"></td>
            </tr>
            <tr>
              <td><label>Puck ID:</label></td>
              <td><input type="text" required name="newOnSlicePuckId" id="newOnSlicePuckId" placeholder="Puck ID"></td>
            </tr>
            <tr>
              <td><label>Run ID:</label></td>
              <td><input type="text" required name="newOnSliceRunId" id="newOnSliceRunId" placeholder="Run ID"></td>
            </tr>
            <tr>
              <td><label>Slice source lot:</label></td>
              <td><input type="text" required name="newOnSliceSliceSourceLot" id="newOnSliceSliceSourceLot" placeholder="Slice source lot"></td>
            </tr>
            <tr>
              <td><label>Start lot:</label></td>
              <td><input type="text" required name="newOnSliceStartLot" id="newOnSliceStartLot" placeholder="Start lot"></td>
            </tr>
            <tr>
              <td><label>Fab wafer ID:</label></td>
              <td><input type="text" required name="newOnSliceFabWafId" id="newOnSliceFabWafId" placeholder="Fab wafer ID"></td>
            </tr>
            <tr>
              <td><label>Global wafer ID:</label></td>
              <td><input type="text" required name="newOnSliceGlobalWafId" id="newOnSliceGlobalWafId" placeholder="Global wafer ID"></td>
            </tr>
            <tr>
              <td><label>Fab source lot:</label></td>
              <td><input type="text" required name="newOnSliceFabSourceLot" id="newOnSliceFabSourceLot" placeholder="Fab source lot"></td>
            </tr>
            <tr>
              <td><label>Slice start time:</label></td>
              <td><input type="text" required name="newOnSliceSliceStartTime" id="newOnSliceSliceStartTime" placeholder="Slice start time"></td>
            </tr>
            <tr>
              <td><label>Slice part name:</label></td>
              <td><input type="text" required name="newOnSliceSlicePartName" id="newOnSliceSlicePartName" placeholder="Slice part name"></td>
            </tr>
            <tr>
              <td><label>Slice lot type:</label></td>
              <td><input type="text" required name="newOnSliceSliceLotType" id="newOnSliceSliceLotType" placeholder="Slice lot type"></td>
            </tr>
            <tr>
              <td><label>Slice supplier ID:</label></td>
              <td><input type="text" required name="newOnSliceSliceSupplierId" id="newOnSliceSliceSupplierId" placeholder="Slice supplier ID"></td>
            </tr>
            <tr>
              <td><label>Puck height:</label></td>
              <td><input type="number" required name="newOnSlicePuckHeight" id="newOnSlicePuckHeight" placeholder="Puck height"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="newOnSliceInsertTime" id="newOnSliceInsertTime" placeholder="Insert time"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnSlice()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnSliceLabel"></h4>
    </div>
  </div>
</div>