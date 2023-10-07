<html>
<head>
<title>confirmation</title>
</head>
<%
String favlangs = request.getParameter("favlang");
//create  the cookie
Cookie thecookie = new Cookie("myApp.favoriteLanguage", favlangs);
// set the life span for cookies
thecookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(thecookie);
%>
<body>
	Thanks! We set your favorite language to : ${param.favlang}
	<br>
	<br>
	<a href="cookies-homepage.jsp">Return to home page</a>
</body>
</html>