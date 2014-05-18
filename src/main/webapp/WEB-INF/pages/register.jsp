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
<div>
  		 <form action="${pageContext.request.contextPath}/main/createUser" method="post">
            <table align="center" style="padding: 100px">  
                <tr>  
                    <td>用户名： <input id="username" type="text" name="username" value=""/> </td>  
                    <td><form:errors path="*"/></td>  
                </tr>  
                <tr>  
                    <td>密码： <input id="password" type="password" name="password" value=""/> </td>  
                </tr>
                <tr>  
                    <td>姓名： <input id="firstname" type="text" name="firstname" value=""/> </td>  
                    <td><form:errors path="*"/></td>  
                </tr> 
                <tr>  
                    <td>电话： <input id="phonenumber" type="text" name="phonenumber" value=""/> </td>  
                    <td><form:errors path="*"/></td>  
                </tr>  
                <tr>  
                    <td>公司名称： <input id="companyname" type="text" name="companyname" value=""/> </td>  
                    <td><form:errors path="*"/></td>  
                </tr>    
                <tr>  
                    <td><input type="submit" value="注册"/><input type="reset" value="重置"/></td>  
                </tr>  
            </table>  
        </form>  
    </div>  
   
	
</body>
</html>
   