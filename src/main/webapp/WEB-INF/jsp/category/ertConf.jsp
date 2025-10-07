<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ertConfDiv">
  <h2 style="text-decoration: underline;">ErtConf</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allErtConfItemsDiv')">List all the ErtConf items &#x27A4;</h3>
    <div style="display: none;" id="allErtConfItemsDiv">
      <button class="submitBtn" onclick="listAllErtConfItems()">List all items</button>
      <h4 class="errorLabel" id="allErtConfItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allErtConfItemsDiv')">Find ErtConf by name &#x27A4;</h3>
    <div style="display: none;" id="findErtConfByNameDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Name:</label></td>
            <td><input type="text" id="findErtConfByNameInput" name="findErtConfByNameInput" required placeholder="Name"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findErtConfByName()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findErtConfByNameLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findErtConfByIdDiv')">Find ErtConf by ID &#x27A4;</h3>
    <div style="display: none;" id="findErtConfByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findErtConfByIdInput" name="findErtConfByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findErtConfById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findErtConfByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewConfDiv')">Add a new ErtConf &#x27A4;</h3>
    <div style="display: none;" id="addNewConfDiv" >
      <form onsubmit="return false;">
        <div id="addNewConfInputs">
          <table>
            <tr>
              <td><label>Configuration name:</label></td>
              <td><input type="text" required name="confName" id="confName" placeholder="Configuration name"></td>
            </tr>
            <tr>
              <td><label>Value string:</label></td>
              <td><input type="text" required name="valueStringConf" id="valueStringConf" placeholder="Value string"></td>
            </tr>
            <tr>
              <td><label>Value number:</label></td>
              <td><input type="number" name="valueNumberConf" id="valueNumberConf" placeholder="Value number"></td>
            </tr>
            <tr>
              <td><label>Value boolean:</label></td>
              <td>
                <select name="valueBooleanConf" id="valueBooleanConf">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Data type:</label></td>
              <td>
                <select name="dataTypeConf" id="dataTypeConf">
                  <c:forEach items="${dataTypes}" var="dataType">
                    <option value="${dataType}">${dataType}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewErtConf()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewConfLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('deleteConfDiv')">Delete a ErtConf &#x27A4;</h3>
    <div style="display: none;" id="deleteConfDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="deleteConfId" name="deleteConfId" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="deleteConf()">Delete</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="deleteConfLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateConfDiv')">Update an existing ErtConf &#x27A4;</h3>
    <div style="display: none;" id="updateConfDiv">
      <form onsubmit="return false;">
        <div id="updateConfInputs">
          <table>
            <tr>
              <td><label>ID of ErtConf to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedConf" id="idToBeUpdatedConf" placeholder="ID of ErtConf to be updated"></td>
            </tr>
            <tr>
              <td><label>Configuration name:</label></td>
              <td><input type="text" required name="newConfName" id="newConfName" placeholder="Configuration name"></td>
            </tr>
            <tr>
              <td><label>Value string:</label></td>
              <td><input type="text" required name="newValueStringConf" id="newValueStringConf" placeholder="Value string"></td>
            </tr>
            <tr>
              <td><label>Value number:</label></td>
              <td><input type="number" required name="newValueNumberConf" id="newValueNumberConf" placeholder="Value number"></td>
            </tr>
            <tr>
              <td><label>Value boolean:</label></td>
              <td>
                <select name="newValueBooleanConf" id="newValueBooleanConf">
                  <option value="true">True</option>
                  <option value="false">False</option>
                </select>
              </td>
            </tr>
            <tr>
              <td><label>Data type:</label></td>
              <td>
                <select name="newDataTypeConf" id="newDataTypeConf">
                  <c:forEach items="${dataTypes}" var="dataType">
                    <option value="${dataType}">${dataType}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateErtConf()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateConfLabel"></h4>
    </div>
  </div>
</div>