<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h1>Blog</h1>
	<%
		if (session.getAttribute("current.user.id") == null) {
	%>

	<p style="color: red;">${wrongpw}</p>
	<p style="color: red;">${noUser}</p>

	<form action="main" method="post">
		Nickname: <input type="text" name="nick"
			<%if (session.getAttribute("current.user.nick") != null) {%>
			value="<%=session.getAttribute("current.user.nick")%>"
			<%} else {%> value="" <%}%> required><br>
		Password: <input type="password" name="password" required><br> <input
			type="hidden" type="text" name="login" value="true"> <input
			type="submit" value="Confirm">
	</form>
	<%
		session.invalidate();
	%>

	<a href="register">Sign up</a>
	<%
		} else {
	%>
	Hello
	<%=session.getAttribute("current.user.fn")%> <%=session.getAttribute("current.user.ln")%>!
	<a href="main?logout=true">Log out</a>
	<%
		}
	%>
	<br>
-------------------------------------------------
	<h1>Available authors</h1>
	<ol>
		<c:forEach var="u" items="${authors}">
			<li><a href="author/${u.nick}">${u.nick}</a></li>
		</c:forEach>
	</ol>

</body>
</html>