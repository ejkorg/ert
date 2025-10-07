<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onWmapDiv category">
  <h2 style="text-decoration: underline;">OnWmap</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnWmapItemsDiv')">List all the OnWmap items &#x27A4;</h3>
    <div style="display: none;" id="allOnWmapItemsDiv">
      <button class="submitBtn" onclick="listAllOnWmapItems()">List all items</button>
      <h4 class="errorLabel" id="allOnWmapItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnWmapByIdDiv')">Find OnWmap by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnWmapByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnWmapByIdInput" name="findOnWmapByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnWmapById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnWmapByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnWmapByProductSearchedLotPart')">Find OnWmap by product and searchedLotPart &#x27A4;</h3>
    <div style="display: none;" id="findOnWmapByProductSearchedLotPart">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Wmc service key:</label></td>
            <td><input type="text" id="findOnWmapByWmcServiceKeyInput" name="findOnWmapByWmcServiceKeyInput" required placeholder="Wmc service key"></td>
          </tr>
          <tr>
            <td><label>Product:</label></td>
            <td><input type="text" id="findOnWmapByProductInput" name="findOnWmapByProductInput" required placeholder="Product"></td>
          </tr>
          <tr>
            <td><label>Searched lot part:</label></td>
            <td><input type="text" id="findOnWmapBySearchedLotPartInput" name="findOnWmapBySearchedLotPartInput" required placeholder="Searched lot part"></td>
          </tr>
          <tr>
            <td><label>Fab:</label></td>
            <td><input type="text" id="findOnWmapByFabInput" name="findOnWmapByFabInput" placeholder="Fab"></td>
          </tr>
          <tr>
            <td><label>Data type:</label></td>
            <td><input type="text" id="findOnWmapByDataTypeInput" name="findOnWmapByDataTypeInput" placeholder="Data type"></td>
          </tr>
          <tr>
            <td><label>Scribe:</label></td>
            <td><input type="text" id="findOnWmapByScribeInput" name="findOnWmapByScribeInput" placeholder="Scribe"></td>
          </tr>
          <tr>
            <td><label>End date:</label></td>
            <td><input type="text" id="findOnWmapByEndDateInput" name="findOnWmapByEndDateInput" placeholder="End date"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnWmapByProductSearchedLotPart()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnWmapByProductSearchedLotPartLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnWmapDiv')">Add a new OnWmap &#x27A4;</h3>
    <div style="display: none;" id="addNewOnWmapDiv">
      <form onsubmit="return false;">
        <div id="addNewOnWmapInputs">
          <table>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="onWmapStatus" id="onWmapStatus">
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
              <td><label>Product:</label></td>
              <td><input type="text" required name="onWmapProduct" id="onWmapProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Wf units:</label></td>
              <td><input type="text" required name="onWmapWfUnits" id="onWmapWfUnits" placeholder="Wf units"></td>
            </tr>
            <tr>
              <td><label>Wf size:</label></td>
              <td><input type="number" required name="onWmapWfSize" id="onWmapWfSize" placeholder="Wf size"></td>
            </tr>
            <tr>
              <td><label>Flat type:</label></td>
              <td><input type="text" required name="onWmapFlatType" id="onWmapFlatType" placeholder="Flat type"></td>
            </tr>
            <tr>
              <td><label>Flat:</label></td>
              <td><input type="text" required name="onWmapFlat" id="onWmapFlat" placeholder="Flat"></td>
            </tr>
            <tr>
              <td><label>Die width:</label></td>
              <td><input type="number" required name="onWmapDieWidth" id="onWmapDieWidth" placeholder="Die width"></td>
            </tr>
            <tr>
              <td><label>Die height:</label></td>
              <td><input type="number" required name="onWmapDieHeight" id="onWmapDieHeight" placeholder="Die height"></td>
            </tr>
            <tr>
              <td><label>Center X:</label></td>
              <td><input type="number" required name="onWmapCenterX" id="onWmapCenterX" placeholder="Center X"></td>
            </tr>
            <tr>
              <td><label>Center Y:</label></td>
              <td><input type="number" required name="onWmapCenterY" id="onWmapCenterY" placeholder="Center Y"></td>
            </tr>
            <tr>
              <td><label>Positive X:</label></td>
              <td><input type="text" required name="onWmapPositiveX" id="onWmapPositiveX" placeholder="Positive X"></td>
            </tr>
            <tr>
              <td><label>Positive Y:</label></td>
              <td><input type="text" required name="onWmapPositiveY" id="onWmapPositiveY" placeholder="Positive Y"></td>
            </tr>
            <tr>
              <td><label>Reticle rows:</label></td>
              <td><input type="number" required name="onWmapReticleRows" id="onWmapReticleRows" placeholder="Reticle rows"></td>
            </tr>
            <tr>
              <td><label>Reticle cols:</label></td>
              <td><input type="number" required name="onWmapReticleCols" id="onWmapReticleCols" placeholder="Reticle cols"></td>
            </tr>
            <tr>
              <td><label>Reticle row offset:</label></td>
              <td><input type="number" required name="onWmapReticleRowOffset" id="onWmapReticleRowOffset" placeholder="Reticle row offset"></td>
            </tr>
            <tr>
              <td><label>Reticle col offset:</label></td>
              <td><input type="number" required name="onWmapReticleColOffset" id="onWmapReticleColOffset" placeholder="Reticle col offset"></td>
            </tr>
            <tr>
              <td><label>Confirmed:</label></td>
              <td><input type="text" required name="onWmapConfirmed" id="onWmapConfirmed" placeholder="Confirmed"></td>
            </tr>
            <tr>
              <td><label>Device count:</label></td>
              <td><input type="number" required name="onWmapDeviceCount" id="onWmapDeviceCount" placeholder="Device count"></td>
            </tr>
            <tr>
              <td><label>Confirm time:</label></td>
              <td><input type="text" required name="onWmapConfirmTime" id="onWmapConfirmTime" placeholder="Confirm time"></td>
            </tr>
            <tr>
              <td><label>Comments:</label></td>
              <td><input type="text" required name="onWmapComments" id="onWmapComments" placeholder="Comments"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="onWmapInsertTime" id="onWmapInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Input file:</label></td>
              <td><input type="text" required name="onWmapInputFile" id="onWmapInputFile" placeholder="Input file"></td>
            </tr>
            <tr>
              <td><label>Cfg ID:</label></td>
              <td><input type="number" required name="onWmapCfgId" id="onWmapCfgId" placeholder="Cfg ID"></td>
            </tr>
            <tr>
              <td><label>Location:</label></td>
              <td><input type="text" required name="onWmapLocation" id="onWmapLocation" placeholder="Location"></td>
            </tr>
            <tr>
              <td><label>Ref die X:</label></td>
              <td><input type="number" required name="onWmapRefDieX" id="onWmapRefDieX" placeholder="Ref die X"></td>
            </tr>
            <tr>
              <td><label>Ref die Y:</label></td>
              <td><input type="number" required name="onWmapRefDieY" id="onWmapRefDieY" placeholder="Ref die Y"></td>
            </tr>
            <tr>
              <td><label>Ref die init dt:</label></td>
              <td><input type="text" required name="onWmapRefDieInitDt" id="onWmapRefDieInitDt" placeholder="Ref die init dt"></td>
            </tr>
            <tr>
              <td><label>Wmc device:</label></td>
              <td><input type="text" required name="onWmapWmcDevice" id="onWmapWmcDevice" placeholder="Wmc device"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnWmap()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnWmapLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnWmapDiv')">Update an existing OnWmap &#x27A4;</h3>
    <div style="display: none;" id="updateOnWmapDiv">
      <form onsubmit="return false;">
        <div id="updateOnWmapInputs">
          <table>
            <tr>
              <td><label>ID of OnWmap to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnWmap" id="idToBeUpdatedOnWmap" placeholder="ID of OnWmap to be updated"></td>
            </tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="newOnWmapStatus" id="newOnWmapStatus">
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
              <td><label>Product:</label></td>
              <td><input type="text" required name="newOnWmapProduct" id="newOnWmapProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Wf units:</label></td>
              <td><input type="text" required name="newOnWmapWfUnits" id="newOnWmapWfUnits" placeholder="Wf units"></td>
            </tr>
            <tr>
              <td><label>Wf size:</label></td>
              <td><input type="number" required name="newOnWmapWfSize" id="newOnWmapWfSize" placeholder="Wf size"></td>
            </tr>
            <tr>
              <td><label>Flat type:</label></td>
              <td><input type="text" required name="newOnWmapFlatType" id="newOnWmapFlatType" placeholder="Flat type"></td>
            </tr>
            <tr>
              <td><label>Flat:</label></td>
              <td><input type="text" required name="newOnWmapFlat" id="newOnWmapFlat" placeholder="Flat"></td>
            </tr>
            <tr>
              <td><label>Die width:</label></td>
              <td><input type="number" required name="newOnWmapDieWidth" id="newOnWmapDieWidth" placeholder="Die width"></td>
            </tr>
            <tr>
              <td><label>Die height:</label></td>
              <td><input type="number" required name="newOnWmapDieHeight" id="newOnWmapDieHeight" placeholder="Die height"></td>
            </tr>
            <tr>
              <td><label>Center X:</label></td>
              <td><input type="number" required name="newOnWmapCenterX" id="newOnWmapCenterX" placeholder="Center X"></td>
            </tr>
            <tr>
              <td><label>Center Y:</label></td>
              <td><input type="number" required name="newOnWmapCenterY" id="newOnWmapCenterY" placeholder="Center Y"></td>
            </tr>
            <tr>
              <td><label>Positive X:</label></td>
              <td><input type="text" required name="newOnWmapPositiveX" id="newOnWmapPositiveX" placeholder="Positive X"></td>
            </tr>
            <tr>
              <td><label>Positive Y:</label></td>
              <td><input type="text" required name="newOnWmapPositiveY" id="newOnWmapPositiveY" placeholder="Positive Y"></td>
            </tr>
            <tr>
              <td><label>Reticle rows:</label></td>
              <td><input type="number" required name="newOnWmapReticleRows" id="newOnWmapReticleRows" placeholder="Reticle rows"></td>
            </tr>
            <tr>
              <td><label>Reticle cols:</label></td>
              <td><input type="number" required name="newOnWmapReticleCols" id="newOnWmapReticleCols" placeholder="Reticle cols"></td>
            </tr>
            <tr>
              <td><label>Reticle row offset:</label></td>
              <td><input type="number" required name="newOnWmapReticleRowOffset" id="newOnWmapReticleRowOffset" placeholder="Reticle row offset"></td>
            </tr>
            <tr>
              <td><label>Reticle col offset:</label></td>
              <td><input type="number" required name="newOnWmapReticleColOffset" id="newOnWmapReticleColOffset" placeholder="Reticle col offset"></td>
            </tr>
            <tr>
              <td><label>Confirmed:</label></td>
              <td><input type="text" required name="newOnWmapConfirmed" id="newOnWmapConfirmed" placeholder="Confirmed"></td>
            </tr>
            <tr>
              <td><label>Device count:</label></td>
              <td><input type="number" required name="newOnWmapDeviceCount" id="newOnWmapDeviceCount" placeholder="Device count"></td>
            </tr>
            <tr>
              <td><label>Confirm time:</label></td>
              <td><input type="text" required name="newOnWmapConfirmTime" id="newOnWmapConfirmTime" placeholder="Confirm time"></td>
            </tr>
            <tr>
              <td><label>Comments:</label></td>
              <td><input type="text" required name="newOnWmapComments" id="newOnWmapComments" placeholder="Comments"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="newOnWmapInsertTime" id="newOnWmapInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Input file:</label></td>
              <td><input type="text" required name="newOnWmapInputFile" id="newOnWmapInputFile" placeholder="Input file"></td>
            </tr>
            <tr>
              <td><label>Cfg ID:</label></td>
              <td><input type="number" required name="newOnWmapCfgId" id="newOnWmapCfgId" placeholder="Cfg ID"></td>
            </tr>
            <tr>
              <td><label>Location:</label></td>
              <td><input type="text" required name="newOnWmapLocation" id="newOnWmapLocation" placeholder="Location"></td>
            </tr>
            <tr>
              <td><label>Ref die X:</label></td>
              <td><input type="number" required name="newOnWmapRefDieX" id="newOnWmapRefDieX" placeholder="Ref die X"></td>
            </tr>
            <tr>
              <td><label>Ref die Y:</label></td>
              <td><input type="number" required name="newOnWmapRefDieY" id="newOnWmapRefDieY" placeholder="Ref die Y"></td>
            </tr>
            <tr>
              <td><label>Ref die init dt:</label></td>
              <td><input type="text" required name="newOnWmapRefDieInitDt" id="newOnWmapRefDieInitDt" placeholder="Ref die init dt"></td>
            </tr>
            <tr>
              <td><label>Wmc device:</label></td>
              <td><input type="text" required name="newOnWmapWmcDevice" id="newOnWmapWmcDevice" placeholder="Wmc device"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnWmap()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnWmapLabel"></h4>
    </div>
  </div>
</div>