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
		} else {
	%>
	Hello anonymus user!<br>
	<a href="${pageContext.request.contextPath}/servleti/main">Press here to go back to the main
		page.</a>
	<%
		}
	%>
	<br>
	<a href="${pageContext.request.contextPath}/servleti/author/${nick}">Back</a>
		<br>
	-------------------------------------------------
	<c:if test="${author}">
		<a href="${pageContext.request.contextPath}/servleti/author/${nick}/${id}/edit">Edit this blog article</a>	
	</c:if>
	
	<h1>${title}</h1>
	<p>${text}</p>
	
	<h3>Comment section</h3>
	<ul>
		<c:forEach var="u" items="${comments}">
			<li>${u.message} - ${u.usersEMail} - ${u.postedOn}</li>
		</c:forEach>
	</ul>	
	<h5>Add a new comment</h5>
		<form action="${pageContext.request.contextPath}/servleti/comment" id="usrform" method="post">
		<input type="submit" value="Add comment"><br>
		Email <input type="email" name="email" value = "${email}" required>
		<input type="hidden" value ="${id}" name="id">
		<input type="hidden" value = "${nick}" name = "nick">
	</form>
	<br>
	<textarea rows="10" cols="50" name="com" form="usrform">Add your comment here</textarea>
	
	


</body>
</html>