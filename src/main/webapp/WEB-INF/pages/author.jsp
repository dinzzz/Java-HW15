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
	<li>Hello
	<%=session.getAttribute("current.user.fn")%> <%=session.getAttribute("current.user.ln")%>!</li>
	<li><a href="${pageContext.request.contextPath}/servleti/main?logout=true">Log out</a></li>
	</ul>	
	
	<c:if test="${author}">
		<a href="${pageContext.request.contextPath}/servleti/author/${nick}/new">Add new blog article</a>	
	</c:if>
	
	<%
		} else {
	%>
	Hello anonymus user!
	<%
		}
	%>
	<br>
-------------------------------------------------
	<h1>Available blogs</h1>
	<ol>
		<c:forEach var="u" items="${entries}">
			<li><a href="${pageContext.request.contextPath}/servleti/author/${nick}/${u.id}">${u.title}</a></li>
		</c:forEach>
	</ol>
	<a href="${pageContext.request.contextPath}/servleti/main">Press here to go back to the main page.</a>

</body>
</html>