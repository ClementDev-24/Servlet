<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>D360 Login Page</title>
    <link rel="icon" href="resource/D360 logo.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <link rel="stylesheet" href="css/login.css">
  </head>
  <body>
    <div class="login-container">
      <img src="resource/D360 logo.png" alt="logo" />
      <h2>Login</h2>
      <form action="ClientController" method="get">
      <input type="hidden" name="command" value="LOGIN">
        <span class="material-symbols-outlined" id="person">person</span>
        <input type="text" class="login-input" placeholder="Username" name="username"/>
        <span class="material-symbols-outlined" id="key">key</span>
        <input type="password" class="login-input" placeholder="Password" name="password" id="password"/>
        <button type="button" onclick="togglePassword()" id="passic">
          <span class="material-symbols-outlined">visibility</span>
        </button>
        <br /><button type="submit" class="login-button">
          <span class="material-symbols-outlined">login</span><br />Login
        </button>
        <p class="forgot-password-link" onclick="showPopup()">
          Forgot Password?
        </p>
      </form>
      <p id="error">${requestScope.errorMessage}</p>
    </div>

    <!-- Forgot Password Popup -->
    <div class="popup-container" id="popupContainer">
      <div class="popup-content">
        <span class="close-popup" onclick="closePopup()">&times;</span>
        <h2>Forgot Password</h2>
        <p>Please provide your details to reset your password:</p>
        <input type="email" class="login-input" placeholder="Email" />
        <button class="login-button">Reset Password</button>
      </div>
    </div>

    <script>
      // POP UP for forgot password
      function showPopup() {
        document.getElementById("popupContainer").style.display = "flex";
      }

      function closePopup() {
        document.getElementById("popupContainer").style.display = "none";
      }
      // view password
      function togglePassword() {
        const passwordInput = document.querySelector("#password");
        const togglePasswordBtn = document.querySelector("#toggle-passic");
        if (passwordInput.type === "password") {
          passwordInput.type = "text";
          togglePasswordBtn.textContent = "Hide";
        } else {
          passwordInput.type = "password";
          togglePasswordBtn.textContent = "Show";
        }
      }
    </script>
  </body>
</html>
