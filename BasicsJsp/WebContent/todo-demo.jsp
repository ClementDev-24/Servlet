<%@ page import="java.util.*" %>
<html>
<body>
<!-- Step1: create HTML form -->
<form action="todo-demo.jsp">
Add new item: <input type="text" name="thisitem" required="required"/><br><br>
<input type="submit" value="submit"/>
</form>
<br>
<%-- Item entered: <%=request.getParameter("thisitem") %>--%>
<!-- Step2: Add new item to "to do list" -->
<%
// lIST IS DOWNCASTED from object & getting data from session
List<String> item = (List<String>) session.getAttribute("mytodolist");
// if the to do list didn't created then create
if(item == null){
	item = new ArrayList<String>();
    session.setAttribute("mytodolist", item);
}
// check if there is an form to add then add to the list which is created (item) 
String thisitems = request.getParameter("thisitem");
if(thisitems!=null){
	item.add(thisitems);
}
%>
<!-- Step3: Display all the "to do " item form the list -->

TO DO items:
<br>
<ol>
<%
for(String s: item){
	out.println("<li> "+s+" </li>");
}
%>
</ol>

</body>
</html>