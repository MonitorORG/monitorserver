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
			if ($("#j_username").val() == "admin") {
				$("#j_username").val("");
				$("#j_password").val("");
			}
    	});		
    
    </script>
</head>
<body>
	<div>
  		 <form action="${pageContext.request.contextPath}/main/j_spring_security_check" method="post">
            <table align="center" style="padding: 100px">  
                <tr>  
                    <td>用户： <input id="j_username" type="text" name="j_username" value=""/> </td>  
                    <td><form:errors path="*"/></td>  
                </tr>  
                <tr>  
                    <td>密码： <input id="j_password" type="password" name="j_password" value=""/> </td>  
                </tr>  
                <tr>  
                    <td><input type="submit" value="登陆"/><input type="reset" value="重置"/></td>  
                </tr>  
            </table>  
        </form>  
    </div>  
	
</body>
</html>
