<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>new host manage Page</title>
    <link href='<c:url value="/main/css/ui.jqgrid.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/jquery.ui.theme.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/main.css"/>' type="text/css" rel="stylesheet"></link>
	
	<script type="text/javascript" src='<c:url value="/main/js/jquery-1.11.0.min.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/jquery.jqGrid.min.js"></c:url>'></script>
	<script src="js/window.js" type="text/javascript"></script>
    
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
    			url:'/monitorserver/main/getAllHostList',
    			datatype: "json",
    			height: 450,
    			colNames:['ID', $.i18n.prop('hostname'), $.i18n.prop('mac.address'), $.i18n.prop('userFullname'), $.i18n.prop('enable'), $.i18n.prop('assignUserBtn')], 
				colModel:[ 
					{name:'id',index:'id', width:40, sorttype:"int"}, 
					{name:'hostname',index:'hostname', width:150}, 
					{name:'macAddress',index:'macAddress', width:150},
					{name:'userFullname',index:'userFullname', width:150},
					{name:'enable',index:'enable', width:150, formatter: enableColFormatter},
					{name:'assignUserBtn',index:'assignUserBtn', width:150, formatter: assignUserBtnColFormatter}
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
				},
				caption: "<table width='100%'><tr><td><b>主机管理</b></td></tr></table>"
    		});
    		jQuery("#unassignedHostTable").jqGrid('navGrid','#unassignedHostPager',{add:false,edit:false,del:false});
    	});	
    	
    	
    	function assignUserBtnColFormatter(cellvalue, options, rowdata) {    		
    		return "<button type='button' onclick='showAssignPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>"+$.i18n.prop('command.assign')+"</button>";
    	}
    	
    	function enableColFormatter(cellvalue, options, rowdata) {
    		var delBtnHtml = "<button type='button' onclick='showDelConfirmPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>"+$.i18n.prop('command.delete')+"</button>";
    		if (rowdata.enable) {
    			return "<button type='button' onclick='setHostEnable(\"" + rowdata.id + "\", \"" + false + "\")'>"+$.i18n.prop('command.disable')+"</button>" + delBtnHtml;
    		} else {
    			return "<button type='button' onclick='setHostEnable(\"" + rowdata.id + "\", \"" + true + "\")'>"+$.i18n.prop('command.enable')+"</button>" + delBtnHtml;
    		}
    	}
    	
		function showDelConfirmPanel(id, macAddress) {
			
			$("#delHostId").val(id);
			$("#deleteMessage").text("Are you sure to delete this host with Mac Address '" + macAddress + "'?");
			popDelConfirmWindow();			
		}
		
		function deleteHostInfo() {
			var hostGrid = jQuery("#hostCommandsTable");
			var hostId = $("#delHostId").val();
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/deleteHost?hostId=" + hostId,
	            complete :function(msg) {
	            	hostGrid.trigger('reloadGrid');
	            	closeDelConfirmPopWindowManual();
	            },
	            success: function(msg){
	            	hostGrid.trigger('reloadGrid');
	            	closeDelConfirmPopWindowManual();
	            }});
		}
    	
    	
    	function setHostEnable(hostId, isEnable) {
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/enableHost?hostId="+hostId+"&isEnable="+isEnable,
	            complete :function(result) {
	            	jQuery("#unassignedHostTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            },
	            success: function(result){
	            	jQuery("#unassignedHostTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            }});
    	}
    	
    	
    	function showAssignPanel(id, macAddress) {
    		$("#hostId").val(id);
    		popAssignUserConfirmWindow();
    	}
    	
    	
    	function assignUsertoHost() {
    		var hostId = $("#hostId").val();
    		var userId = $("#userlist").val();
    		
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/assginUserToHost?hostId="+hostId+"&userId="+userId,
	            complete :function(result) {
	            	jQuery("#unassignedHostTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            },
	            success: function(result){
	            	jQuery("#unassignedHostTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            }});
    	}
    
    </script>
</head>
<body>
	
	<jsp:include page="header.jsp" flush="true">	
		<jsp:param name="currentpageIndex" value="toHostManageLink" />	
	</jsp:include>

	<!-- 分配用户窗口 -->
	<div id="assignUserWindow" style="z-index:999;display:none;">
		<div id="title" class="title">Assign User Window</div> 
		<div class="content">		
			<input type='hidden' id='hostId'/>
			
			User List: <select id="userlist">
			<c:forEach items="${alluserlist}" var="user">
					<option value=<c:out value="${user.id}" />><c:out value="${user.username}" /></option>
			</c:forEach>
			</select>
			
			<table>
				<tr>
					<td style="text-align:right"><button id='okConfigmBtn' type='button' onclick='assignUsertoHost()'>Save</button></td>
					<td><button id='cancelAssignUserBtn' class="close" type='button'>Close</button></td>
				</tr>
			</table>
		</div> 
	</div> 
	
	<!-- 删除确认窗口 -->
	<div id="delConfirmWindow" style="z-index:999;display:none;">
		<div id="title" class="title">Delete Window</div> 
		<div class="content">		
			<input type='hidden' id='delHostId'/>
			<span id="deleteMessage">&nbsp;</span>
			<table>
				<tr>
					<td style="text-align:right"><button id='okConfigmBtn' type='button' onclick='deleteHostInfo()'>OK</button></td>
					<td><button id='cancelConfirmBtn' class="close" type='button'>Cancel</button></td>
				</tr>
			</table>
		</div> 
	</div> 

	
	<table id="unassignedHostTable"></table>
	<div id="unassignedHostPager"></div>
	
</body>
</html>