<%@ page import="com.basicCode.jsp.*"%>
<%-- you need to implement all the class from the specific package so use * after the package --%>
<html>
<body>
The letter need to be lower cased FUN FUN FUN: <br>
<%-- First method --%>
<%-- <%=com.basicCode.jsp.FunThings.makeItLower("FUN FUN FUN") %>
 ->this is a method where your don't need to implement the class --%>
<%-- just type the package and then the class then the method--%>
<%-- Second method using import --%>
<%=FunThings.makeItLower("FUN FUN FUN") %><!-- the string is sent to the java class and the result is returned -->
</body>
</html>