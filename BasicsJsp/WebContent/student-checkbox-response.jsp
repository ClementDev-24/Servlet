<html>
<head><title>Student confirmation</title></head>
<body>
The student is confirmed: ${param.firstname} ${param.lastname}
<br><br>
<%-- display list of favorite language --%>
<ul><%-- you can change this into ol which is ordered list --%>
<% 
 String[] langs =request.getParameterValues("favlang");
if (langs != null) {
out.println("The student's Favorite languages: ");
 for(String templang:langs){
out.println("<li> "+templang+" </li>");
 }
}
else{
	out.println("There is no check boxes selected");
}
%>
<%-- check boxes are not compulsory to select so we are using if condition--%>
</ul>
</body>
</html>