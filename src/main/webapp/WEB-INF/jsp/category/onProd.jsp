<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onProdDiv category">
  <h2 style="text-decoration: underline;">OnProd</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('allOnProdItemsDiv')">List all the OnProd items &#x27A4;</h3>
    <div style="display: none;" id="allOnProdItemsDiv">
      <button class="submitBtn" onclick="listAllOnProdItems()">List all items</button>
      <h4 class="errorLabel" id="allOnProdItemsLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnProdByProductDiv')">Find OnProd by product &#x27A4;</h3>
    <div style="display: none;" id="findOnProdByProductDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>Product:</label></td>
            <td><input type="text" id="findOnProdByProductInput" name="findOnProdByProductInput" required placeholder="Product"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnProdByProduct()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnProdByProductLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('findOnProdByIdDiv')">Find OnProd by ID &#x27A4;</h3>
    <div style="display: none;" id="findOnProdByIdDiv">
      <form onsubmit="return false;">
        <table>
          <tr>
            <td><label>ID:</label></td>
            <td><input type="number" id="findOnProdByIdInput" name="findOnProdByIdInput" required placeholder="ID"></td>
          </tr>
          <tr>
            <td><button class="submitBtn" onclick="findOnProdById()">Find</button></td>
          </tr>
        </table>
      </form>
      <h4 class="errorLabel" id="findOnProdByIdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addNewOnProdDiv')">Add a new OnProd &#x27A4;</h3>
    <div style="display: none;" id="addNewOnProdDiv">
      <form onsubmit="return false;">
        <div id="addNewOnProdInputs">
          <table>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="onProdStatus" id="onProdStatus">
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
              <td><input type="text" required name="onProdProduct" id="onProdProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Item type:</label></td>
              <td><input type="text" required name="onProdItemType" id="onProdItemType" placeholder="Item type"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="onProdFab" id="onProdFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Fab desc:</label></td>
              <td><input type="text" required name="onProdFabDesc" id="onProdFabDesc" placeholder="Fab Desc"></td>
            </tr>
            <tr>
              <td><label>Afm:</label></td>
              <td><input type="text" required name="onProdAfm" id="onProdAfm" placeholder="Afm"></td>
            </tr>
            <tr>
              <td><label>Process:</label></td>
              <td><input type="text" required name="onProdProcess" id="onProdProcess" placeholder="Process"></td>
            </tr>
            <tr>
              <td><label>Family:</label></td>
              <td><input type="text" required name="onProdFamily" id="onProdFamily" placeholder="Family"></td>
            </tr>
            <tr>
              <td><label>Gdpw:</label></td>
              <td><input type="number" required name="onProdGdpw" id="onProdGdpw" placeholder="Gdpw"></td>
            </tr>
            <tr>
              <td><label>Wf units:</label></td>
              <td><input type="text" required name="onProdWfUnits" id="onProdWfUnits" placeholder="Wf units"></td>
            </tr>
            <tr>
              <td><label>Wf size:</label></td>
              <td><input type="number" required name="onProdWfSize" id="onProdWfSize" placeholder="Wf size"></td>
            </tr>
            <tr>
              <td><label>Die units:</label></td>
              <td><input type="text" required name="onProdDieUnits" id="onProdDieUnits" placeholder="Die units"></td>
            </tr>
            <tr>
              <td><label>Die width:</label></td>
              <td><input type="number" required name="onProdDieWidth" id="onProdDieWidth" placeholder="Die width"></td>
            </tr>
            <tr>
              <td><label>Die height:</label></td>
              <td><input type="number" required name="onProdDieHeight" id="onProdDieHeight" placeholder="Die height"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="onProdInsertTime" id="onProdInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Pti 4:</label></td>
              <td><input type="text" required name="onProdPti4" id="onProdPti4" placeholder="Pti 4"></td>
            </tr>
            <tr>
              <td><label>Technology:</label></td>
              <td><input type="text" required name="onProdTechnology" id="onProdTechnology" placeholder="Technology"></td>
            </tr>
            <tr>
              <td><label>Mask set:</label></td>
              <td><input type="text" required name="onProdMaskSet" id="onProdMaskSet" placeholder="Mask set"></td>
            </tr>
            <tr>
              <td><label>Package:</label></td>
              <td><input type="text" required name="onProdPackage" id="onProdPackage" placeholder="Package"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addNewOnProd()">Add</button>
      </form>
      <h4 class="errorLabel" id="addNewOnProdLabel"></h4>
    </div>
  </div>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('updateOnProdDiv')">Update an existing OnProd &#x27A4;</h3>
    <div style="display: none;" id="updateOnProdDiv">
      <form onsubmit="return false;">
        <div id="updateOnProdInputs">
          <table>
            <tr>
              <td><label>ID of OnProd to be updated:</label></td>
              <td><input type="number" required name="idToBeUpdatedOnProd" id="idToBeUpdatedOnProd" placeholder="ID of OnProd to be updated"></td>
            </tr>
            <tr>
            <tr>
              <td><label>Status:</label></td>
              <td>
                <select name="newOnProdStatus" id="newOnProdStatus">
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
              <td><input type="text" required name="newOnProdProduct" id="newOnProdProduct" placeholder="Product"></td>
            </tr>
            <tr>
              <td><label>Item type:</label></td>
              <td><input type="text" required name="newOnProdItemType" id="newOnProdItemType" placeholder="Item type"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" required name="newOnProdFab" id="newOnProdFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Fab desc:</label></td>
              <td><input type="text" required name="newOnProdFabDesc" id="newOnProdFabDesc" placeholder="FabDesc"></td>
            </tr>
            <tr>
              <td><label>Afm:</label></td>
              <td><input type="text" required name="newOnProdAfm" id="newOnProdAfm" placeholder="Afm"></td>
            </tr>
            <tr>
              <td><label>Process:</label></td>
              <td><input type="text" required name="newOnProdProcess" id="newOnProdProcess" placeholder="Process"></td>
            </tr>
            <tr>
              <td><label>Family:</label></td>
              <td><input type="text" required name="newOnProdFamily" id="newOnProdFamily" placeholder="Family"></td>
            </tr>
            <tr>
              <td><label>Gdpw:</label></td>
              <td><input type="number" required name="newOnProdGdpw" id="newOnProdGdpw" placeholder="Gdpw"></td>
            </tr>
            <tr>
              <td><label>Wf units:</label></td>
              <td><input type="text" required name="newOnProdWfUnits" id="newOnProdWfUnits" placeholder="Wf units"></td>
            </tr>
            <tr>
              <td><label>Wf size:</label></td>
              <td><input type="number" required name="newOnProdWfSize" id="newOnProdWfSize" placeholder="Wf size"></td>
            </tr>
            <tr>
              <td><label>Die units:</label></td>
              <td><input type="text" required name="newOnProdDieUnits" id="newOnProdDieUnits" placeholder="Die units"></td>
            </tr>
            <tr>
              <td><label>Die width:</label></td>
              <td><input type="number" required name="newOnProdDieWidth" id="newOnProdDieWidth" placeholder="Die width"></td>
            </tr>
            <tr>
              <td><label>Die height:</label></td>
              <td><input type="number" required name="newOnProdDieHeight" id="newOnProdDieHeight" placeholder="Die height"></td>
            </tr>
            <tr>
              <td><label>Insert time:</label></td>
              <td><input type="text" required name="newOnProdInsertTime" id="newOnProdInsertTime" placeholder="Insert time"></td>
            </tr>
            <tr>
              <td><label>Pti 4:</label></td>
              <td><input type="text" required name="newOnProdPti4" id="newOnProdPti4" placeholder="Pti 4"></td>
            </tr>
            <tr>
              <td><label>Technology:</label></td>
              <td><input type="text" required name="newOnProdTechnology" id="newOnProdTechnology" placeholder="Technology"></td>
            </tr>
            <tr>
              <td><label>Mask set:</label></td>
              <td><input type="text" required name="newOnProdMaskSet" id="newOnProdMaskSet" placeholder="Mask set"></td>
            </tr>
            <tr>
              <td><label>Package:</label></td>
              <td><input type="text" required name="newOnProdPackage" id="newOnProdPackage" placeholder="Package"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="updateOnProd()">Update</button>
      </form>
      <h4 class="errorLabel" id="updateOnProdLabel"></h4>
    </div>
  </div>
</div>