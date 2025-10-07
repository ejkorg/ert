<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onFabConfDiv category">
  <h2 style="text-decoration: underline;">OnFabConf</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnFabConfItemsDiv')">List all the OnFabConf items &#x27A4;</h3>
    <div style="display: none;" id="allOnFabConfItemsDiv">
      <button class="submitBtn" onclick="listAllOnFabConfItems()">List all items</button>
      <h4 class="errorLabel" id="allOnFabConfItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnFabConfByNameDiv')">Find OnFabConf by fab and data type &#x27A4;</h3>
    <div style="display: none;" id="findOnFabConfByNameDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnFabConfByFabInput" name="findOnFabConfByFabInput" required placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnFabConfByDataTypeInput" name="findOnFabConfByDataTypeInput" required placeholder="Data type"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnFabConfByFabDataType()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnFabConfByFabDataTypeLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnFabConfByIdDiv')">Find OnFabConf by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnFabConfByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnFabConfByIdInput" name="findOnFabConfByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnFabConfById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnFabConfByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnFabConfDiv')">Add a new OnFabConf &#x27A4;</h3>
    <div id="addNewOnFabConfDiv" style="display: none;">
      <form onsubmit="return false;">
        <div id="addNewOnFabConfInputs">
          <table>
            <tr>
              <td><label>Foundry fab:</label></td>
              <td><input type="text" required name="onFabConfFoundryFab" id="onFabConfFoundryFab" placeholder="Foundry fab"></td>
            </tr>
            <tr>
              <td><label>Data type:</label></td>
              <td><input type="text" required name="onFabConfDataType" id="onFabConfDataType" placeholder="Data type"></td>
            </tr>
            <tr>
              <td><label>Ltm url:</label></td>
              <td><input type="text" required name="onFabConfLtmUrl" id="onFabConfLtmUrl" placeholder="Ltm url"></td>
            </tr>
            <tr>
              <td><label>Wmc url:</label></td>
              <td><input type="text" required name="onFabConfWmcUrl" id="onFabConfWmcUrl" placeholder="Wmc url"></td>
            </tr>
            <tr>
              <td><label>Vid2Scribe url:</label></td>
              <td><input type="text" required name="onFabConfVid2ScribeUrl" id="onFabConfVid2ScribeUrl" placeholder="Vid2Scribe url"></td>
            </tr>
            <tr>
              <td><label>Ltm url:</label></td>
              <td><input type="text" required name="onFabConfScribe2VidUrl" id="onFabConfScribe2VidUrl" placeholder="Scribe2Vid url"></td>
            </tr>
            <tr>
              <td><label>Wafer ID creation pattern:</label></td>
              <td><input type="text" required name="onFabConfWaferId" id="onFabConfWaferId" placeholder="Wafer ID creation pattern"></td>
            </tr>
            <tr>
              <td><label>Source lot adjustment pattern:</label></td>
              <td><input type="text" required name="onFabConfSourceLot" id="onFabConfSourceLot" placeholder="Source lot adjustment pattern"></td>
            </tr>
            <tr>
              <td><label>Match up url:</label></td>
              <td><input type="text" required name="onFabConfMatchUpUrl" id="onFabConfMatchUpUrl" placeholder="Match up url"></td>
            </tr>
            <tr>
              <td><label>Scribe result type:</label></td>
              <td>
                <select name="onFabConfScribeResultType" id="onFabConfScribeResultType">
                  <c:forEach items="${scribeResultTypes}" var="type">
                    <option value="${type}">${type}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>OnScribe wafer ID Equals scribe ID:</label></td>
              <td>
                <select name="onFabConfWaferIdEqualsScribeId" id="onFabConfWaferIdEqualsScribeId">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Lot ID for onScribe type:</label></td>
              <td>
                <select name="onFabConfLotId" id="onFabConfLotId">
                  <c:forEach items="${lotIds}" var="id">
                    <option value="${id}">${id}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Second lotg query:</label></td>
              <td>
                <select name="onFabConfSecondLotGQuery" id="onFabConfSecondLotGQuery">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnFabConf()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnFabConfLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('deleteOnFabConfDiv')">Delete a OnFabConf &#x27A4;</h3>
    <div style="display: none;" id="deleteOnFabConfDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="deleteOnFabConfId" name="deleteOnFabConfId" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="deleteOnFabConf()">Delete</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="deleteOnFabConfLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnFabConfDiv')">Update an existing OnFabConf &#x27A4;</h3>
    <div id="updateOnFabConfDiv" style="display: none;">
      <form onsubmit="return false;">
        <div id="updateOnFabConfInputs">
          <table>
            <tr>
              <td><label>ID of OnFabConf to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnFabConf" id="idToBeUpdatedOnFabConf" placeholder="ID of OnFabConf to be updated"></td>
            </tr>
            <tr>
              <td><label>Foundry fab:</label></td>
              <td><input type="text" required name="newOnFabConfFoundryFab" id="newOnFabConfFoundryFab" placeholder="Foundry fab"></td>
            </tr>
            <tr>
              <td><label>Data type:</label></td>
              <td><input type="text" required name="newOnFabConfDataType" id="newOnFabConfDataType" placeholder="Data type"></td>
            </tr>
            <tr>
              <td><label>Ltm url:</label></td>
              <td><input type="text" required name="newOnFabConfLtmUrl" id="newOnFabConfLtmUrl" placeholder="Ltm url"></td>
            </tr>
            <tr>
              <td><label>Wmc url:</label></td>
              <td><input type="text" required name="newOnFabConfWmcUrl" id="newOnFabConfWmcUrl" placeholder="Wmc url"></td>
            </tr>
            <tr>
              <td><label>Vid2Scribe url:</label></td>
              <td><input type="text" required name="newOnFabConfVid2ScribeUrl" id="newOnFabConfVid2ScribeUrl" placeholder="Vid2Scribe url"></td>
            </tr>
            <tr>
              <td><label>Ltm url:</label></td>
              <td><input type="text" required name="newOnFabConfScribe2VidUrl" id="newOnFabConfScribe2VidUrl" placeholder="Scribe2Vid url"></td>
            </tr>
            <tr>
              <td><label>Wafer ID creation pattern:</label></td>
              <td><input type="text" required name="newOnFabConfWaferId" id="newOnFabConfWaferId" placeholder="Wafer ID creation pattern"></td>
            </tr>
            <tr>
              <td><label>Source lot adjustment pattern:</label></td>
              <td><input type="text" required name="newOnFabConfSourceLot" id="newOnFabConfSourceLot" placeholder="Source lot adjustment pattern"></td>
            </tr>
            <tr>
              <td><label>Match up url:</label></td>
              <td><input type="text" required name="newOnFabConfMatchUpUrl" id="newOnFabConfMatchUpUrl" placeholder="Match up url"></td>
            </tr>
            <tr>
              <td><label>Scribe result type:</label></td>
              <td>
                <select name="newOnFabConfScribeResultType" id="newOnFabConfScribeResultType">
                  <c:forEach items="${scribeResultTypes}" var="type">
                    <option value="${type}">${type}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>OnScribe wafer ID Equals scribe ID:</label></td>
              <td>
                <select name="newOnFabConfWaferIdEqualsScribeId" id="newOnFabConfWaferIdEqualsScribeId">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Lot ID for onScribe type:</label></td>
              <td>
                <select name="newOnFabConfLotId" id="newOnFabConfLotId">
                  <c:forEach items="${lotIds}" var="id">
                    <option value="${id}">${id}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Second lotG query:</label></td>
              <td>
                <select name="newOnFabConfSecondLotGQuery" id="newOnFabConfSecondLotGQuery">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnFabConf()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnFabConfLabel"></h4>
    </div>
  </div>
</div>
