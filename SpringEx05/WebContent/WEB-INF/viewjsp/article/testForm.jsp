<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<%
	String servletPath = request.getRequestURI();
%>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTR-8">
	<title>KH05_Example003 테스트 코드</title>
</head>
<body>

	<form:form action="/article/${article.id}" commandName="article" method="delete">
		<input type="submit" value="삭제">
	</form:form>
	
	<form:form action="/article/${article.id}" commandName="article" method="put">
		<input type="submit" value="수정">
	</form:form>

	<form:form action="/SpringEx05/article" commandName="article" method="post">
		<input type="submit" value="생성">
	</form:form>

</body>

</html>