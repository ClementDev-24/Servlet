
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>client Tracker App</title>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administration - D360</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>

<div class="navbar">
    <div class="logo">
    <img src="./D360 logo.png" alt="Logo">
  </div>
  <a href="#"><i class="fa-solid fa-folder fa-2xl"></i><br><br>Applications</a>
  <a href="#"><i class="fa-solid fa-users fa-2xl"></i><br><br>Administration</a>
  <a href="#"><i class="fa-solid fa-tv fa-xl"></i><br><br>Governance</a>
  <a href="#"><i class="fa-solid fa-wallet fa-2xl"></i><br><br>Finance</a>
  <a href="#"><i class="fa-solid fa-chart-pie fa-2xl"></i><br><br>Dashboards</a>        
  <a href="#"><i class="fa-regular fa-comment-dots fa-2xl"></i><br><br>Compliance</a>
  <div class="profile-dropdown dropdown">
    <button class="profile"><i class="fa-solid fa-bars fa-2xl"></i></button>
    <div class="profile-dropdown-content dropdown-content">
      <a href="#">Profile</a>
      <a href="#">Settings</a>
      <a href="./login.html">Sign Out</a>
    </div>
  </div>
</div>

<div style="padding: 20px;">
    <h1 style="text-align: center;">User List</h1>
    <input type="button" value="Add client" onclick="showPopup()">
</div>

<!-- Add user pop-up -->
<div class="popup-container" id="popupContainer">
     <div class="popup-content">
      <span class="close-popup" onclick="closePopup()">&times;</span>
      <h2>Add user</h2>
      <form action="ClientController" method="get">
        <input type="hidden" name="command" value="LOAD" />
        <table id="addusertab">
            <tbody>
                <tr>
                    <td><label>User Name</label></td>
                    <td><input type="text" name="userName" /></td>
                </tr>
                <tr>
                    <td><label>First name:</label></td>
                    <td><input type="text" name="firstName" /></td>
                </tr>
                <tr>                    
                    <td><label>Middle name:</label></td>
                    <td><input type="text" name="middleName" /></td>
                </tr>                
                <tr>
                    <td><label>Last name:</label></td>
                    <td><input type="text" name="lastName" /></td>
                </tr>
                <tr>
                    <td><label>Email:</label></td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td><label>Password</label></td>
                    <td><input type="text" name="password" /></td>
                </tr>
                <tr>
                    <td><label>Confirm password</label></td>
                    <td><input type="text" name="confirmpassword" /></td>
                </tr>
                <tr>
                  <td><label>Role</label></td>
                  <td><select id="options" name="role">
                    <option value="admin">Admin</option>
                      <option value="client">Client</option>
                    </select></td>
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

<!-- User list content -->
<div id="usercontainer">
    <div id="usercontent">
        <label><b>Show:</b></label>
    <select id="itemsPerPage" class="input-box">
      <option value="5">5</option>
      <option value="10">10</option>
      <option value="20">20</option>
      <option value="50">50</option>
    </select><label> entities</label>
    <br /><br />
    <div id="table1">
        <table id="userviewtable" border="1">
            <tr>
                <th>ID</th>
                <th>UserName</th>
                 <th>FirstName</th>
                 <th>MiddleName</th>
                 <th>LastName</th>
                 <th>Email</th>
                 <th>Role</th>
                 <th>Action</th>
            </tr>
            <c:forEach var="tempClient" items="${Client_
            List}">
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="ClientController">
						<c:param name="command" value="LOAD" />
						<c:param name="ClientId" value="${tempClient.id}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="ClientController">
						<c:param name="command" value="DELETE" />
						<c:param name="ClientId" value="${tempClient.id}" />
					</c:url>
					<tr>
						<td>${tempClient.id}</td>
						<td>${tempClient.userName}</td>
						<td>${tempClient.firstName}</td>
						<td>${tempClient.middleName}</td>
						<td>${tempClient.lastName}</td>
						<td>${tempClient.email}</td>
						<td>${tempClient.role}</td>
						<td><a href="${tempLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete</a>
					</tr>
				</c:forEach>
        </table>
    </div>
      <div id="pagination">
      <button id="prevButton">Previous</button>
      <div id="pageNumbers"></div>
      <button id="nextButton">Next</button>
    </div>
  </div>
</div>

<!-- Update client pop-up -->

<script>
function showPopup() {
    document.getElementById('popupContainer').style.display = 'flex';
  }
  function closePopup() {
    document.getElementById('popupContainer').style.display = 'none';
  }
//end of add user pop up
function showPopupUpdate() {
    document.getElementById('updateContainer').style.display = 'flex';
  }
  function closePopupUpdate() {
    document.getElementById('updateContainer').style.display = 'none';
  }
//end of update pop up
//starting of pagenation in user table
const table = document.querySelector("#userviewtable");
    const rows = Array.from(table.querySelectorAll("tr")).slice(1); // Exclude header row
    const itemsPerPageInput = document.getElementById("itemsPerPage");
    const pageNumbersDiv = document.getElementById("pageNumbers");
    const prevButton = document.getElementById("prevButton");
    const nextButton = document.getElementById("nextButton");

    let itemsPerPage = parseInt(itemsPerPageInput.value); // Number of items to display per page
    let currentPage = 0;

    function displayPage(page) {
      rows.forEach((row, index) => {
        row.style.display =
          index >= page * itemsPerPage && index < (page + 1) * itemsPerPage
            ? "table-row"
            : "none";
      });
    }

    function updatePageNumbers() {
      const totalPages = Math.ceil(rows.length / itemsPerPage);
      pageNumbersDiv.innerHTML = "";
      for (let i = 0; i < totalPages; i++) {
        const pageNumber = i + 1;
        const pageNumberButton = document.createElement("button");
        pageNumberButton.textContent = pageNumber;
        pageNumberButton.addEventListener("click", () => {
          goToPage(i);
        });
        pageNumbersDiv.appendChild(pageNumberButton);
      }
    }

    function goToPage(page) {
      currentPage = page;
      displayPage(currentPage);
      updatePageNumbers();
    }

    prevButton.addEventListener("click", () => {
      if (currentPage > 0) {
        goToPage(currentPage - 1);
      }
    });

    nextButton.addEventListener("click", () => {
      const totalPages = Math.ceil(rows.length / itemsPerPage);
      if (currentPage < totalPages - 1) {
        goToPage(currentPage + 1);
      }
    });

    itemsPerPageInput.addEventListener("change", () => {
      itemsPerPage = parseInt(itemsPerPageInput.value);
      currentPage = 0;
      displayPage(currentPage);
      updatePageNumbers();
    });

    displayPage(currentPage);
    updatePageNumbers();
</script>

</body>
</html>

	