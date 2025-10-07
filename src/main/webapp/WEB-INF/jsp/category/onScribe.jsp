<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onScribeDiv category">
  <h2 style="text-decoration: underline;">OnScribe</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnScribeItemsDiv')">List all the OnScribe items &#x27A4;</h3>
    <div style="display: none;" id="allOnScribeItemsDiv">
      <button class="submitBtn" onclick="listAllOnScribeItems()">List all items</button>
      <h4 class="errorLabel" id="allOnScribeItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnScribeByIdDiv')">Find OnScribe by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnScribeByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnScribeByIdInput" name="findOnScribeByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnScribeById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnScribeByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnScribeByLotIdWafNumDiv')">Find OnScribe by lot ID and wafer number &#x27A4;</h3>
    <div style="display: none;" id="findOnScribeByLotIdWafNumDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Lot ID:</label></td>
            <td><input type="text" id="findOnScribeByLotIdInput" name="findOnScribeByLotIdInput" required placeholder="Lot ID"></td>
          </tr>
          <tr>
            <td><label>Wafer number:</label></td>
            <td><input type="number" id="findOnScribeByWafNumInput" name="findOnScribeByWafNumInput" required placeholder="Wafer number"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnScribeByFabInput" name="findOnScribeByFabInput" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnScribeByDataTypeInput" name="findOnScribeByDataTypeInput" placeholder="Data type"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnScribeByLotIdWafNum()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnScribeByLotIdWafNumLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnScribeByLotIdAndWafNumMfgDiv')">Find OnScribe by Lot ID and Wafer number. The Lot ID can be taken even from Mfg lot &#x27A4;</h3>
    <div style="display: none;" id="findOnScribeByLotIdAndWafNumMfgDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Lot ID:</label></td>
            <td><input type="text" id="findOnScribeByLotIdMfgInput" name="findOnScribeByLotIdMfgInput" placeholder="Lot ID"></td>
          </tr>
          <tr>
            <td><label>Mfg lot:</label></td>
            <td><input type="text" id="findOnScribeByMfgLotMfgInput" name="findOnScribeByMfgLotMfgInput" placeholder="Mfg lot"></td>
          </tr>
          <tr>
            <td><label>Wafer number:</label></td>
            <td><input type="number" id="findOnScribeByWafNumMfgInput" name="findOnScribeByWafNumMfgInput" required placeholder="Wafer number"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnScribeByFabMfgInput" name="findOnScribeByFabMfgInput" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnScribeByDataTypeMfgInput" name="findOnScribeDataTypeMfgInput" placeholder="Data type"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnScribeByByLotIdAndWafNumMfg()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnScribeByLotIdAndWafNumMfgLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnScribeByScribeIdDiv')">Find OnScribe by Scribe ID &#x27A4;</h3>
    <div style="display: none;" id="findOnScribeByScribeIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Scribe ID:</label></td>
            <td><input type="text" id="findOnScribeByScribeIdScribeIdInput" name="findOnScribeByScribeIdScribeIdInput" required placeholder="Scribe ID"></td>
          </tr>
          <tr>
            <td><label>Lot ID:</label></td>
            <td><input type="text" id="findOnScribeByScribeIdLotIdInput" name="findOnScribeByScribeIdLotIdInput" placeholder="Lot ID"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnScribeByScribeIdFabInput" name="findOnScribeByScribeIdFabInput" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnScribeByScribeIdDataTypeInput" name="findOnScribeByScribeIdDataTypeInput" placeholder="Data type"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnScribeByScribeId()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnScribeByScribeIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnScribeByStdfDiv')">Find OnScribe by Stdf info &#x27A4;</h3>
    <div style="display: none;" id="findOnScribeByStdfDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Lot ID:</label></td>
            <td><input type="text" id="findOnScribeByStdfLotIdInput" name="findOnScribeByStdfLotIdInput" required placeholder="Lot ID"></td>
          </tr>
          <tr>
            <td><label>Wafer number:</label></td>
            <td><input type="text" id="findOnScribeByStdfWafNumInput" name="findOnScribeByStdfWafNumInput" required placeholder="Wafer number"></td>
          </tr>
          <tr>
            <td><label>Scribe ID:</label></td>
            <td><input type="text" id="findOnScribeByStdfScribeIdInput" name="findOnScribeByStdfScribeIdInput" required placeholder="Scribe ID"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnScribeByStdfFabInput" name="findOnScribeByStdfFabInput" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnScribeByStdfDataTypeInput" name="findOnScribeByStdfDataTypeInput" placeholder="Data type"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnScribeByStdf()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnScribeByStdfLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnScribeDiv')">Add a new OnScribe &#x27A4;</h3>
    <div style="display: none;" id="addNewOnScribeDiv">
      <form onsubmit="return false;">
        <div id="addNewOnScribeInputs">
          <table>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="onScribeStatus" id="onScribeStatus">
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
              <td><label>Scribe ID:</label></td>
              <td><input type="text" required name="onScribeScribeId" id="onScribeScribeId" placeholder="Scribe ID"></td>
            </tr>
            <tr>
              <td><label>Wafer ID source:</label></td>
              <td>
                <select name="onScribeWafIdSource" id="onScribeWafIdSource">
                  <c:forEach items="${wafIdSources}" var="source">
                    <option value="${source}">${source}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Wafer number:</label></td>
              <td><input type="number" required name="onScribeWafNum" id="onScribeWafNum" placeholder="Wafer number"></td>
            </tr>
            <tr>
              <td><label>Wafer ID:</label></td>
              <td><input type="text" required name="onScribeWafId" id="onScribeWafId" placeholder="Wafer ID"></td>
            </tr>
            <tr>
              <td><label>Lot:</label></td>
              <td><input type="text" required name="onScribeLot" id="onScribeLot" placeholder="Lot"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="onScribeFab" id="onScribeFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="onScribeInsertTime" id="onScribeInsertTime" placeholder="Insert time"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnScribe()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnScribeLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnScribeDiv')">Update an existing OnScribe &#x27A4;</h3>
    <div style="display: none;" id="updateOnScribeDiv">
      <form onsubmit="return false;">
        <div id="updateOnScribeInputs">
          <table>
            <tr>
              <td><label>ID of OnScribe to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnScribe" id="idToBeUpdatedOnScribe" placeholder="ID of OnScribe to be updated"></td>
            </tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="newOnScribeStatus" id="newOnScribeStatus">
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
              <td><label>Scribe ID:</label></td>
              <td><input type="text" required name="newOnScribeScribeId" id="newOnScribeScribeId" placeholder="Scribe ID"></td>
            </tr>
            <tr>
              <td><label>Wafer ID source:</label></td>
              <td>
                <select name="newOnScribeWafIdSource" id="newOnScribeWafIdSource">
                  <c:forEach items="${wafIdSources}" var="source">
                    <option value="${source}">${source}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Wafer number:</label></td>
              <td><input type="number" required name="newOnScribeWafNum" id="newOnScribeWafNum" placeholder="Wafer number"></td>
            </tr>
            <tr>
              <td><label>Wafer ID:</label></td>
              <td><input type="text" required name="newOnScribeWafId" id="newOnScribeWafId" placeholder="Wafer ID"></td>
            </tr>
            <tr>
              <td><label>Lot:</label></td>
              <td><input type="text" required name="newOnScribeLot" id="newOnScribeLot" placeholder="Lot"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="newOnScribeFab" id="newOnScribeFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="newOnScribeInsertTime" id="newOnScribeInsertTime" placeholder="Insert time"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnScribe()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnScribeLabel"></h4>
    </div>
  </div>
</div>