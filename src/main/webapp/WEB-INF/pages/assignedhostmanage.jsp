<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Assigned host manage Page</title>
	<link href='<c:url value="/main/css/jquery.ui.theme.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/main.css"/>' type="text/css" rel="stylesheet"></link>
	
	<script type="text/javascript" src='<c:url value="/main/js/jquery-1.11.0.min.js"></c:url>'></script>
    
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-en.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-cn.js"></c:url>'></script>
	
	<script src="js/i18n/jquery.i18n.properties-1.0.9.js" type="text/javascript"></script>
	<script src="js/i18n/i18n_load.js" type="text/javascript"></script>
	
    <script type="text/javascript">
    
    	$(document).ready(function(){

    		
    		// I18n initlize
    		loadProperties();
    		
    		document.title=$.i18n.prop('index.title'); 
    		
		
			$("#unassignedHostTable").jqGrid({
    			url:'/monitorserver/main/getNewHostList',
    			datatype: "json",
    			height: 450,
    			colNames:['ID', $.i18n.prop('hostname'), $.i18n.prop('mac.address')], 
				colModel:[ 
					{name:'id',index:'id', width:40, sorttype:"int"}, 
					{name:'hostname',index:'hostname', width:150}, 
					{name:'macAddress',index:'macAddress', width:150}
				],
    			rowNum:10,
    			rowList:[10,20,30],
    			pager: '#unassignedHostPager',
    			sortname: 'id',
    			viewrecords: true,
    			sortorder: "desc",
    			multiselect: false,
				beforeSelectRow: function(rowId, e) {
				},
				onSelectRow: function(id){ 				
				}
    		});
    		jQuery("#unassignedHostTable").jqGrid('navGrid','#unassignedHostPager',{add:false,edit:false,del:false});
    		
    	});		
    
    </script>
</head>
<body>

Assigned host manage Page
	
	<table id="unassignedHostTable"></table>
	<div id="unassignedHostPager"></div>

</body>
</html>