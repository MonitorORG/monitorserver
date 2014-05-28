<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Manage User List</title>
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
    		
		
			$("#userListTable").jqGrid({
    			url:'/monitorserver/main/getAllUserList',
    			datatype: "json",
    			height: 450,
    			colNames:['ID', $.i18n.prop('username'), $.i18n.prop('email'), $.i18n.prop('role'), $.i18n.prop('enable'), $.i18n.prop('commandCol')], 
				colModel:[ 
					{name:'id',index:'id', width:40, sorttype:"int"}, 
					{name:'username',index:'username', width:150}, 
					{name:'email',index:'email', width:150},
					{name:'role',index:'role', width:150},
					{name:'enable',index:'enable', width:150, formatter: enableColFormatter},
					{name:'commandCol',index:'commandCol', width:250, formatter: commandColFormatter}
				],
    			rowNum:10,
    			rowList:[10,20,30],
    			pager: '#userListPager',
    			sortname: 'id',
    			viewrecords: true,
    			sortorder: "desc",
    			multiselect: false,
				beforeSelectRow: function(rowId, e) {
				},
				onSelectRow: function(id){ 				
				}
    		});
    		jQuery("#userListTable").jqGrid('navGrid','#userListPager',{add:false,edit:false,del:false});
    	});	
    	
    	
    	function commandColFormatter(cellvalue, options, rowdata) {    		
    		return "<button type='button' onclick='showChangePwdPanel(\"" + rowdata.id + "\")'>"+$.i18n.prop('command.changepwd')+"</button>" +
    			"<button type='button' onclick='showDelConfirmPanel(\"" + rowdata.id + "\")'>"+$.i18n.prop('command.delete')+"</button>";
    	}
    	
    	function enableColFormatter(cellvalue, options, rowdata) {    		
    		if (rowdata.enable) {
    			return "<button type='button' onclick='setUserEnable(\"" + rowdata.id + "\", false)'>"+$.i18n.prop('command.disable')+"</button>";
    		} else {
    			return "<button type='button' onclick='setUserEnable(\"" + rowdata.id + "\", true)'>"+$.i18n.prop('command.enable')+"</button>";
    		}
    	}
    	
		function showDelConfirmPanel(id) {
			
			$("#delUserId").val(id);
			$("#deleteMessage").text("Are you sure to delete this User with id '" + macAddress + "'?");
			popDelConfirmWindow();			
		}
		
		function deleteUser() {
			var userGrid = jQuery("#userListTable");
			var userId = $("#delUserId").val();
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/deleteUser?userId=" + userId,
	            complete :function(msg) {
	            	userGrid.trigger('reloadGrid');
	            	closeDelConfirmPopWindowManual();
	            },
	            success: function(msg){
	            	userGrid.trigger('reloadGrid');
	            	closeDelConfirmPopWindowManual();
	            }});
		}
    	
    	
    	function setUserEnable(userId, isEnable) {
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/enableUser?userId="+userId+"&isEnable="+isEnable,
	            complete :function(result) {
	            	jQuery("#userListTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            },
	            success: function(result){
	            	jQuery("#userListTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            }});
    	}
    	
    	
    	function showChangePwdPanel(id) {
    		$("#userId").val(id);
    		$("#newpassword").val("");
    		popAssignUserConfirmWindow();
    	}
    	
    	
    	function changeUserPwd() {
    		var userId = $("#userId").val();
    		var newpassword = $("#newpassword").val();
    		
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/changeUserPwd?userId="+userId+"&newPwd="+newpassword,
	            complete :function(result) {
	            	jQuery("#userListTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            },
	            success: function(result){
	            	jQuery("#userListTable").trigger('reloadGrid');
	            	closeAssignUserPopWindowManual();
	            }});
    	}
    
    </script>
</head>
<body>
	
	<div style="text-align:right;margin-right:10px;">
		<a href="toNewHostManagePage"><button id="newHostManageBtn">主机管理</button></a>&nbsp;
		<a href="toUserManagePage"><button id="userManageBtn">用户管理</button></a>&nbsp;
		<a href="toRegisterUsrPage"><button id="registerBtn">普通用户注册</button></a>&nbsp;
		<a href="index"><button id="indexBtn">主页面</button></a>&nbsp;<a href="logout"><button id="logoutBtn">退出</button></a>&nbsp;
	</div>

	<!-- 分配用户窗口 -->
	<div id="assignUserWindow" style="z-index:999;display:none;">
		<div id="title" class="title">Change Password Window</div> 
		<div class="content">		
			<input type='hidden' id='userId'/>
			
			New Password: <input id="newpassword" type="password">
			
			<table>
				<tr>
					<td style="text-align:right"><button id='okConfigmBtn' type='button' onclick='changeUserPwd()'>Save</button></td>
					<td><button id='cancelAssignUserBtn' class="close" type='button'>Close</button></td>
				</tr>
			</table>
		</div> 
	</div> 
	
	<!-- 删除确认窗口 -->
	<div id="delConfirmWindow" style="z-index:999;display:none;">
		<div id="title" class="title">Delete Window</div> 
		<div class="content">		
			<input type='hidden' id='delUserId'/>
			<span id="deleteMessage">&nbsp;</span>
			<table>
				<tr>
					<td style="text-align:right"><button id='okConfigmBtn' type='button' onclick='deleteUser()'>OK</button></td>
					<td><button id='cancelConfirmBtn' class="close" type='button'>Cancel</button></td>
				</tr>
			</table>
		</div> 
	</div> 

	
	<table id="userListTable"></table>
	<div id="userListPager"></div>
	
</body>
</html>