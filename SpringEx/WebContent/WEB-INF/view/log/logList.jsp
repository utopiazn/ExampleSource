<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>KH02_Example012 로그 목록</title>
</head>

<body>
	
	<form:form method="get">
	
		<form:errors path="from" element="div"/>
		<form:errors path="to" element="div"/>
		
		시작일:	
		<form:input path="from"/>
		
		<br/>
	
		종료일:
		<form:input path="to"/>
		
		<input type="submit" value="조회" />
		
	</form:form>
	
	

</body>

</html>