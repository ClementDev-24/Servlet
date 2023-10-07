<div class="popup-update-container" id="updateContainer">
  <div class="popup-update-content">
    <span class="close-update-popup" onclick="closePopupUpdate()">&times;</span>
    <h2>Update user</h2>
    <form action="ClientController" method="get">
      <input type="hidden" name="command" value="LOAD" />
      <input type="hidden" name="clientId" value="${Client_List.id}" />
        <table>
          <tbody>
            <tr>
              <td><label>User name:</label></td>
              <td><input type="text" name="userName" value="${Client_List.userName}" /></td>
            </tr>
            <tr>
              <td><label>First name:</label></td>
              <td><input type="text" name="firstName" value="${Client_List.firstName}" /></td>
            </tr>
            <tr>
              <td><label>Middle name:</label></td>
              <td><input type="text" name="middleName" value="${Client_List.middleName}" /></td>
            </tr>
            <tr>
              <td><label>Last name:</label></td>
              <td><input type="text" name="lastName" value="${Client_List.lastName}" /></td>
            </tr>             
            <tr>
              <td><label>Email:</label></td>
              <td><input type="text" name="email" value="${Client_List.email}" /></td>
            </tr>
            <tr>
              <td><label>Role</label></td>            
              <td> <select id="options" name="role">
                <option value="admin" <c:if test="${Client_List.role eq 'admin'}"></c:if>>Admin</option>
                <option value="client" <c:if test="${Client_List.role eq 'client'}"></c:if>>Client</option>
            </select>
              </td>
            </tr>
            <tr>
              <td><label></label></td>
              <td><input type="submit" value="Save" class="save" /></td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  </div>