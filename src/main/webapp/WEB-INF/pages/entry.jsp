<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h1>Blog</h1>

	<%
		if (session.getAttribute("current.user.id") != null) {
	%>
	<ul>
		<li>Hello <%=session.getAttribute("current.user.fn")%> <%=session.getAttribute("current.user.ln")%>!
		</li>
		<li><a href="${pageContext.request.contextPath}/servleti/main?logout=true">Log out</a></li>
		<li><a href="${pageContext.request.contextPath}/servleti/main">Press here to go back to the main
		page.</a></li>
	</ul>
	<%
		}
	%>
	<a href="${pageContext.request.contextPath}/servleti/author/${nick}/${id}">Back</a>
		<br>
	-------------------------------------------------
	<form action="${pageContext.request.contextPath}/servleti/success" id="usrform" method="post">
		<input type="submit" value="Save entry"><br>
		Title <input type="text" name="title" value = "${title}" required>
		<input type="hidden" name="mode" value="${mode}">
		<input type="hidden" name="nick" value="<%=session.getAttribute("current.user.nick")%>">
		<input type="hidden" name="id" value="${id}">
	</form>
	<br>
	<textarea rows="30" cols="50" name="entry" form="usrform">${existing}</textarea>

	


</body>
</html>