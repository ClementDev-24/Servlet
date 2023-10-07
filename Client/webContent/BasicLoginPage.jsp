<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>D360 Login Page</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background: url('background-image.jpg') no-repeat center center fixed;
	background-size: cover;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.login-container {
	background: rgba(255, 255, 255, 0.8);
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	text-align: center;
	max-width: 300px;
	width: 50%;
}

.login-container h2 {
	margin-bottom: 20px;
}

.login-input {
	width: 90%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

.login-button {
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
}

.login-button:hover {
	background-color: #0056b3;
}

.forgot-password-link {
	text-decoration: underline;
	color: #007bff;
	cursor: pointer;
}

/* Popup styles */
.popup-container {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	justify-content: center;
	align-items: center;
}

.popup-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	max-width: 400px;
	width: 80%;
	text-align: center;
}

/* close button in the pop up     */
.close-popup {
	position: relative;
	left: 52%;
	bottom: 12px;
	cursor: pointer;
	color: black;
}

.close-popup:hover {
	padding: 2%;
	border-radius: 6px;
	background-color: red;
}
</style>
</head>
<body>
	<div class="login-container">
		<img src="" alt="" srcset="">
		<h2>Login</h2>
		<form action="ClientController" method="get">
			<input type="hidden" name="command" value="LOGIN"> <input
				type="text" class="login-input" name="username"
				placeholder="Username"> <input type="password"
				class="login-input" name="password" placeholder="Password">
			<button type="submit" class="login-button">Login</button>
			<p class="forgot-password-link" onclick="showPopup()">Forgot
				Password?</p>
		</form>
		<p>${requestScope.errorMessage}</p>
	</div>

	<!-- Forgot Password Popup -->
	<div class="popup-container" id="popupContainer">
		<div class="popup-content">
			<span class="close-popup" onclick="closePopup()">&times;</span>
			<h2>Forgot Password</h2>
			<p>Please provide your details to reset your password:</p>
			<input type="email" class="login-input" placeholder="Email">
			<button class="login-button">Reset Password</button>
		</div>
	</div>

	<script>
    function showPopup() {
      document.getElementById('popupContainer').style.display = 'flex';
    }

    function closePopup() {
      document.getElementById('popupContainer').style.display = 'none';
    }
  </script>
</body>
</html>