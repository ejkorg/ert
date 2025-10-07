<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="ppLotProdDiv category">
  <h2 style="text-decoration: underline;">PpLotProd</h2>
  <div>
    <h3 class="linkTitle" onclick="showHideElement('addFindByLotIdPpLotProdDiv')">Find PpLotProd by lot ID &#x27A4;</h3>
    <div id="addFindByLotIdPpLotProdDiv" style="display: none;">
      <form onsubmit="return false;">
        <div id="addFindByLotIdPpLotProdInputs">
          <table>
            <tr>
              <td><label>Lot ID:</label></td>
              <td><input type="text" required name="ppLotProdLot" id="ppLotProdLot" placeholder="Lot ID"></td>
            </tr>
          </table>
        </div>
        <br>
        <button class="submitBtn" onclick="addFindByLotIdPpLotProd()">Find</button>
      </form>
      <h4 class="errorLabel" id="addFindByLotIdPpLotProdLabel"></h4>
    </div>
  </div>
</div>
