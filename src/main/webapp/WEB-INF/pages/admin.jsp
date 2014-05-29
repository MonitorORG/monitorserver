<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Admin Page</title>
	
    <link href='<c:url value="/main/css/ui.jqgrid.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/jquery.ui.theme.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/main.css"/>' type="text/css" rel="stylesheet"></link>
	
	<script type="text/javascript" src='<c:url value="/main/js/jquery-1.11.0.min.js"></c:url>'></script>
    <script type="text/javascript" src='<c:url value="/main/js/jquery.timer.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/jquery.jqGrid.min.js"></c:url>'></script>
	
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-en.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-cn.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/grid.subgrid.js"></c:url>'></script>
	
	<script src="js/window.js" type="text/javascript"></script>
	<script src="js/process.js" type="text/javascript"></script>
	<script src="js/selectcol.js" type="text/javascript"></script>
	<script src="js/i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
	<script src="js/i18n/i18n_load.js" type="text/javascript"></script>
	
    <script type="text/javascript">
    
    	
    
    </script>
</head>
<body>
	
	<div style="text-align:right;margin-right:10px;">
		<a href="toHostManagePage"><button id="hostHManageBtn">主机管理</button></a>&nbsp;
		<a href="toUserManagePage"><button id="userManageBtn">用户管理</button></a>&nbsp;
		<a href="toRegisterUsrPage"><button id="registerBtn">普通用户注册</button></a>&nbsp;
		<a href="index"><button id="indexBtn">主页面</button></a>&nbsp;<a href="logout"><button id="logoutBtn">退出</button></a>&nbsp;
	</div>
	
</body>
</html>
