<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>Registration</h1>
    <%
        if (session.getAttribute("current.user.id") == null) {
    %>  
    <p style="color:red;">${userExists}</p> 
	<form action="register" method = "get">
		Nickname: <input type="text" name="nick"><br>
		Password: <input type="password" name="password"><br>
		First Name: <input type="text" name="name"><br>
		Last Name:	<input type="text" name="surname"><br>
		Email:	<input type="email" name="email"><br>
		<input type="hidden" type="text" name="register" value="true">
		<input type="submit" value="Confirm">
	</form>
	<a href="main">Press here to go back to the main page.</a>
	<%
        } else {
    %>
    	Hello <%=session.getAttribute("current.user.fn")%>!
    	You wandered in a spooky scary place.
    	You can't register if you're already logged in.
    	<a href="main">Press here to go back to the main page.</a>
    <%
        }        
    %>

</body>
</html>