<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login Page</title>
	
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
    
    	$(document).ready(function(){

    	});		
    
    </script>
</head>
<body>
	<div>  
       <form action="/monitorserver/main/login" id="loginform" method="post">  
            <table align="center" style="padding: 100px">  
                <tr>  
                    <td>姓名：<input type="text" name="username" id="username" /> *必需 </td>  
                    <td><form:errors path="*"/></td>  
                </tr>  
                <tr>  
                    <td>密码：<input type="password" name="password" id="password"> *必需 </td>  
                </tr>  
                <tr>  
                    <td><input type="submit"></td>  
                </tr>  
            </table>  
        </form>  
    </div>  
	
</body>
</html>
