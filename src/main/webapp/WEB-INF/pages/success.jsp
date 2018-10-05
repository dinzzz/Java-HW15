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
	
	<%
		} 
	%>
	-------------------------------------------------
	Your entry has been added!
	<a href="${pageContext.request.contextPath}/servleti/main">Press here to go back to the main page.</a>
	

</body>
</html>