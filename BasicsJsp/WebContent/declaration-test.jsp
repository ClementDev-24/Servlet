<html>
<body>
<h3>Making the method declaration</h3>
<%! String makeItLower(String data){  
    return data.toLowerCase();
}%>
<%--The above is a jsp declaration which has a method --%>
the lower case of the word HEllo WoRld <br><%=makeItLower("HEllo WoRld")  %>
</body>
</html>