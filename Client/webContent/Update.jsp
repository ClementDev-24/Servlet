<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administration - D360</title>
    <link rel="icon" href="resource/D360 logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="css/Admin-Index.css"/>
</head>
<body>
<div class="navbar">
    <div class="logo">
        <img src="resource/D360 logo.png" alt="Logo">
    </div>
    <a href="#"><i class="fa-solid fa-folder fa-xl"></i><br><br>Applications</a>
    <a href="#"><i class="fa-solid fa-users fa-xl"></i><br><br>Administration</a>
    <a href="#"><i class="fa-solid fa-tv fa-xl"></i><br><br>Governance</a>
    <a href="#"><i class="fa-solid fa-wallet fa-xl"></i><br><br>Finance</a>
    <a href="#"><i class="fa-solid fa-chart-pie fa-xl"></i><br><br>Dashboards</a>
    <a href="#"><i class="fa-regular fa-comment-dots fa-xl"></i><br><br>Compliance</a>
    <div class="profile-dropdown dropdown">
        <button class="profile"><i class="fa-solid fa-bars fa-2xl"></i></button>
        <div class="profile-dropdown-content dropdown-content">
            <a href="#">Profile</a>
            <a href="#">Settings</a>
            <a href="login.jsp">Sign Out</a>
        </div>
    </div>
</div>
<!-- end of nav bar -->
<div style="padding: 20px;">
    <h1 style="text-align: center;">Update User</h1>
    <!-- update client pop -->
    <div id="usercontainer">
        <div id="usercontent">
            <form action="ClientController" method="get">
                <input type="hidden" name="command" value="UPDATE" />
                <input type="hidden" name="clientId" value="${Client_list.id}" />
                <table>
                    <tbody>
                        <tr>
                            <td><label>User name:</label></td>
                            <td><input type="text" name="userName" value="${Client_list.userName}" /></td>
                        </tr>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><input type="text" name="firstName" value="${Client_list.firstName}" /></td>
                        </tr>
                        <tr>
                            <td><label>Middle name:</label></td>
                            <td><input type="text" name="middleName" value="${Client_list.middleName}" /></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><input type="text" name="lastName" value="${Client_list.lastName}" /></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><input type="text" name="email" value="${Client_list.email}" /></td>
                        </tr>
                        <tr>
                            <td><label>Role</label></td>
                            <td>
                                <select id="options" name="role">
                                    <option value="ADMIN" <c:if test="${Client_list.role eq 'ADMIN'}">selected</c:if>>Admin</option>
                                    <option value="CLIENT" <c:if test="${Client_list.role eq 'CLIENT'}">selected</c:if>>Client</option>
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
</div>
</body>
</html>
