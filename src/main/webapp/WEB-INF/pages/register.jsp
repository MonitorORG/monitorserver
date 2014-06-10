<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login Page</title>
	<link href='<c:url value="/main/css/jquery.ui.theme.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/main.css"/>' type="text/css" rel="stylesheet"></link>
	
	<script type="text/javascript" src='<c:url value="/main/js/jquery-1.11.0.min.js"></c:url>'></script>
    
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-en.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-cn.js"></c:url>'></script>
	
	<script src="js/i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
	<script src="js/i18n/i18n_load.js" type="text/javascript"></script>
	
    <script type="text/javascript">
    
    	$(document).ready(function(){

    	});		
    
    </script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true">	
		<jsp:param name="currentpageIndex" value="toRegisterLink" />	
	</jsp:include>
	
<div>
	<form:form method="Post" action="${pageContext.request.contextPath}/main/createUser" commandName="user" modelAttribute="user">
		<table align="center" style="padding: 100px">
			<tr>
				<td>用户名: </td>
			</tr>
		<tr>
			<td><form:input path="username" /> <FONT color="red"><form:errors path="username" /></FONT></td>
		</tr>
		
		<tr>
			<td>密码: </td>
		</tr>
		<tr>
			<td><form:password path="password" /> <FONT color="red"><form:errors path="password" /></FONT></td>
		</tr>
		
		<tr>
			<td>确认密码: </td>
		</tr>
		<tr>
			<td><form:password path="confirmPassword" /> <FONT color="red"><form:errors path="confirmPassword" /></FONT></td>
		</tr>
		
		<tr>
			<td>邮箱: </td>
		</tr>
		<tr>
			<td><form:input path="email" /> <FONT color="red"><form:errors path="email" /></FONT></td>
		</tr>
		
		<tr>
		<td><input type="submit" value="提交" /></td>
		</tr>
		</table>
	</form:form>
</div>  
	
</body>
</html>
   