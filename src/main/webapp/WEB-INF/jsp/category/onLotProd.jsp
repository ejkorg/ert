<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="onLotProdDiv category">
  <h2 style="text-decoration: underline;">OnLotProd</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addFindByLotIdOnLotProdDiv')">Find OnLotProd by lot ID &#x27A4;</h3>
    <div id="addFindByLotIdOnLotProdDiv" style="display: none;">
      <form onsubmit="return false;">
        <div id="addFindByLotIdOnLotProdInputs">
          <table>
            <tr>
              <td><label>Lot ID:</label></td>
              <td><input type="text" required name="onLotProdLot" id="onLotProdLot" placeholder="Lot ID"></td>
            </tr>
            <tr>
              <td><label>Alternate product:</label></td>
              <td><input type="text" name="onLotProdAlternateProduct" id="onLotProdAlternateProduct" placeholder="Alternate product"></td>
            </tr>
            <tr>
              <td><label>Fab:</label></td>
              <td><input type="text" name="onLotProdFab" id="onLotProdFab" placeholder="Fab"></td>
            </tr>
            <tr>
              <td><label>Data type:</label></td>
              <td>
                <select name="onLotProdDataType" id="onLotProdDataType">
                  <c:forEach items="${dataTypes}" var="type">
                    <option value="${type}">${type}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addFindByLotIdOnLotProd()">Find</button>
      </form>
      <h4 class="errorLabel" id="addFindByLotIdOnLotProdLabel"></h4>
    </div>
  </div>
</div>
