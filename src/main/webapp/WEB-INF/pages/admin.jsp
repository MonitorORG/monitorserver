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

	<div class="adminheader">
		<br />
		<a id="toHostManageLink" class="adminheader-a" href="toHostManagePage">主机管理</a>&nbsp;
		<a id="toUserLink" class="adminheader-a" href="toUserManagePage">用户管理</a>&nbsp;
		<a id="toRegisterLink" class="adminheader-a" href="toRegisterUsrPage">普通用户注册</a>&nbsp;
		<a id="toIndexLink" class="adminheader-a" href="index">主页面</a>&nbsp;
	    <a id="logout" class="adminheader-a" href="logout">退出</a>&nbsp;
	</div>
	
</body>
</html>
