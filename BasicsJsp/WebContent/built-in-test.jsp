<html>
<body>
<h3>Jsp built in object</h3>
<br><br>
Request user agent: <%= request.getHeader("User-Agent") %>
<%--what type of browser and operating system the client is using  --%>
<br><br>
Request language: <%= request.getLocale()%>
<%--what language is the user using --%> 
</body>
</html>