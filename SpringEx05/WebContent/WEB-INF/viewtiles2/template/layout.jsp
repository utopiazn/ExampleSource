<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<tiles:insertAttribute name="header" />
	<br/>
	<tiles:insertAttribute name="body"/>
	<br/>
	<tiles:insertAttribute name="footer"/>
	

</body>
</html>