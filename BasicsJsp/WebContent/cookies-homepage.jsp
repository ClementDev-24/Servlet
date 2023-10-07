<html>

<body>
	<h3>Training portal</h3>
	<!-- read the favorite language programming language -->
	<%
	// the default ... if there is no cookies
	String Favlang = "C#";
	// get the cookies from the browser request
	Cookie[] thecookies = request.getCookies();

	// find our faviorite language cookies
	if (thecookies != null) {
		for (Cookie tempcookie : thecookies) {
			if ("myApp.favoriteLanguage".equals(tempcookie.getName())) {
                Favlang =tempcookie.getValue();
                break;
			}
		}
	}
	%>
	<h4>New books for <%= Favlang %></h4>
	<ul>
	<li> hello bye</li>
	<li> hello bye </li>
	</ul><br>
	<h4>New books for <%=Favlang %></h4>
	<ul>
	<li> hello bye</li>
	<li> hello bye </li>
	</ul><br>
	<h4>New books for <%=Favlang %></h4>
	<ul>
	<li> hello bye</li>
	<li> hello bye </li>
	</ul><br>
	<a href="cookies-personalize-form.html">personalize the page</a>
</body>
</html>