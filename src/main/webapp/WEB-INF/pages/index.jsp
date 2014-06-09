<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Index Page</title>
	
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
	
		var isBatchInputCommand = false;
		var loggedUserID;
    
    	$(document).ready(function(){
    		
    		loggedUserID = $("#loggedUserID").val();
    		
    		// I18n initlize
    		loadProperties();
    		
    		document.title=$.i18n.prop('index.title'); 
    		$(".window #title").html($.i18n.prop('run.command.title'));
        	$("#commandInputLabel").html($.i18n.prop('command.input.label') + ": ");
        	$("#cmdInputBtn").html($.i18n.prop('run.command.btn'));
        	$("#closeBtn").html($.i18n.prop('close'));
        	
        	$("#processWindow #title").html($.i18n.prop('process.management.title'));
        	$("#addProcessBtn").html($.i18n.prop('add.btn'));
        	$("#submitProcessBtn").html($.i18n.prop('commit.btn'));
        	$("#closeProcessBtn").html($.i18n.prop('close'));
        	$("#downloadAgentBtn").html($.i18n.prop('download.agent.label'));
		
			$("#hostCommandsTable").jqGrid({
    			url:'/monitorserver/main/getAssignedHostList',
    			datatype: "json",
    			height: 450,
    			colNames:['Process List', 'Free Mem', 'ID', $.i18n.prop('hostname'), $.i18n.prop('mac.address'), $.i18n.prop('cpu.used'), $.i18n.prop('mem.used'), $.i18n.prop('host.status'), $.i18n.prop('process.status'), $.i18n.prop('process.manage'), $.i18n.prop('command.execute'), '<input class="gridparentcheckbox" onclick="clickGridParentCheckbox(this, event)" type="checkbox" />'], 
				colModel:[ 
					{name:'processList',index:'processList', hidden:true},
					{name:'freeMem',index:'freeMem', hidden:true},
					{name:'id',index:'id', width:40, sorttype:"int"}, 
					{name:'hostname',index:'hostname', width:150}, 
					{name:'macAddress',index:'macAddress', width:150}, 
					{name:'cpuTotalUsed',index:'cpuTotalUsed', width:90, formatter: cpuUsedFormatter },
					{name:'totalMem',index:'totalMem', width:90, formatter: memUsedFormatter }, 
					{name:'status',index:'status', width:80, align: 'center', formatter: hostStatusFormatter }, 
					{name:'processStatusResults',index:'processStatusResults', width:200, align: 'center', formatter: processStatusFormatter},
					{name:'processCmd',index:'processCmd', width:200, align: 'center', formatter: processCmdFormatter},
					{name:'commandCol',index:'commandCol', width:200, align: 'center', formatter: commandColFormatter},
					{name:'selectCol',index:'selectCol', width:25, align: 'center', sortable: false, formatter: selectColFormatter}
					
				],
    			rowNum:10,
    			rowList:[10,20,30],
    			pager: '#hostCommandsPager',
    			sortname: 'id',
    			viewrecords: true,
    			sortorder: "desc",
    			multiselect: false,
				beforeSelectRow: function(rowId, e) {
				},
				onSelectRow: function(id){ 				
				},
    			subGrid: true,
    			caption: "<table width='100%'><tr><td><b>"+$.i18n.prop('host.commands')+"</b></td><td>&nbsp;</td><td style='text-align:right'><button type='button' onclick='showInputCommandPanel(undefined, undefined)' style='margin-right:20px;'>"+$.i18n.prop('command.input')+"</button></td></tr></table",
				subGridBeforeExpand: function(subgrid_id, row_id) {
					
				},
    			subGridRowExpanded: function(subgrid_id, row_id) {
	    			// we pass two parameters
	    			// subgrid_id is a id of the div tag created whitin a table data
	    			// the id of this elemenet is a combination of the "sg_" + id of the row
	    			// the row_id is the id of the row
	    			// If we wan to pass additinal parameters to the url we can use
	    			// a method getRowData(row_id) - which returns associative array in type name-value
	    			// here we can easy construct the flowing
					var subgrid_table_id, pager_id;
	    			subgrid_table_id = subgrid_id+"_t";
	    			pager_id = "p_"+subgrid_table_id;
	    			$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
	    			
	    			var hostMacAddress = $("#hostCommandsTable").getCell(row_id,"macAddress");
	    			
	    			jQuery("#"+subgrid_table_id).jqGrid({
		    			url:"/monitorserver/main/getHostCommands?hostMacAddress="+hostMacAddress,
		    			datatype: "json",
		    			colNames:['ID', $.i18n.prop('mac.address'), $.i18n.prop('command.content'), $.i18n.prop('status'), $.i18n.prop('command.result')], 
						colModel:[ 
							{name:'id',index:'id', width:40, sorttype:"int"}, 
							{name:'hostMacAddress',index:'hostMacAddress', width:150}, 
							{name:'commandStr',index:'commandStr', width:200 },
							{name:'status',index:'status', width:80 }, 
							{name:'resultShow',index:'resultShow', width:110, align: 'center', formatter: resultShowFormatter}
						], 
		    			rowNum:20,
		    			pager: pager_id,
		    			sortname: 'id',
		    			sortorder: "desc",
		    			height: '100%'
	    			});
	    			jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false,refresh:true});
					
					// Only one row is expanded
					var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');		
					if (selRowId && selRowId != row_id) {
						$("#hostCommandsTable").collapseSubGridRow(selRowId);
					}
					
					// Selected current row
					jQuery("#hostCommandsTable").setSelection(row_id);
    			},
    			subGridRowColapsed: function(subgrid_id, row_id) {
    			}
    		});
    		jQuery("#hostCommandsTable").jqGrid('navGrid','#hostCommandsPager',{add:false,edit:false,del:false});
    		
    		$("#addProcessBtn").click(function() {
				addProcessBtnClick();
    		});
    		
    		//每10秒执行，无限次，并命名计时器名称为C
    		//若时间间隔抵到，但函式程序仍未完成则需等待执行函式完成后再继续计时
    		//$('body').everyTime('1das','C',function(){
    		$('body').everyTime('5s','C',function(){
    			//jQuery("#hostCommandsTable").trigger('reloadGrid');
				refreshAllHost();
				refreshCommand();
    		},0,true);
    	});
		
		function cpuUsedFormatter(cellvalue, options, rowdata) {
			return Number(rowdata.cpuTotalUsed).toFixed(2);
		}
		
		function memUsedFormatter(cellvalue, options, rowdata) {
			return Number(rowdata.freeMem/rowdata.totalMem).toFixed(2);
		}
		
		function commandColFormatter(cellvalue, options, rowdata) {
			return "<button type='button' onclick='showInputCommandPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>"+$.i18n.prop('command.input')+"</button>" + 
				   "<button type='button' onclick='showDelConfirmPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>"+$.i18n.prop('command.delete')+"</button>";
		}
		
		function hostStatusFormatter(cellvalue, options, rowdata) {
			var status = rowdata.status;
			if (status == null) status = "";
			return status.replace("uninitial", "<span class='wait' title='" + $.i18n.prop('status.uninitial') + "'>&nbsp; &nbsp; </span>")
				.replace("running", "<span class='running' title='" + $.i18n.prop('status.running') + "'>&nbsp; &nbsp; </span>")
				.replace("unconnected", "<span class='stop' title='" + $.i18n.prop('status.unconnected') + "'>&nbsp; &nbsp; </span>");
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
		
		function showInputCommandPanel(id, macAddress) {
			
			$("#cmdinput").val("");			
			$("#cmdinputId").val(id);
			$("#cmdinputMacAddress").val(macAddress);
			jQuery("#hostCommandsTable").setSelection(id);
			if (id == undefined) {
				isBatchInputCommand = true;
			} else {
				isBatchInputCommand = false;
			}
			
			popCenterWindow();
		}
		
		function resultShowFormatter(cellvalue, options, rowdata) {
			return "<span id='commandResultStr" + rowdata.id + "' title='" + rowdata.resultStr + "'>"+$.i18n.prop('result')+"</span>";
		}
    	
    	function refreshAllHost() {
			var hostGrid = jQuery("#hostCommandsTable");
			var ids = hostGrid.getDataIDs();
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/main/getAssignedHostList",
	            complete :function(data) {
		            if (data.responseText != "Failed") {
						var hostInfoArray = data.responseJSON;//JSON.parse(data.responseText);
						$.each(hostInfoArray, function(i, hostInfo){  
							if (jQuery.inArray(hostInfo.id, ids)) {
								hostGrid.setRowData(hostInfo.id, hostInfo);
							} else {
								hostGrid.addRowData(hostInfo.id, hostInfo, first);
							}						
							
						});
		            }
	            },
	            success: function(hostInfoArray){					
	            }});
    	}
    	
    	function runCommand() {
			var commandInputStr = $("#cmdinput").val();
			if (commandInputStr != '' && macAddress != '') {
				if (isBatchInputCommand == false) {
					var macAddress = $("#cmdinputMacAddress").val();
					var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');					
					$.ajax({
						type: "POST",
						url: "/monitorserver/main/createCommand",
						data: {hostMacAddress:macAddress,commandStr:commandInputStr},
						dataType: "json",
						complete: function(data){
							if (selRowId == $("#hostCommandsTable").jqGrid('getGridParam', 'selrow')) {
								$("#hostCommandsTable").toggleSubGridRow(selRowId);
								$("#hostCommandsTable").expandSubGridRow(selRowId);
							}
						},
						success: function(data){
							//if (selRowId == $("#hostCommandsTable").jqGrid('getGridParam', 'selrow')) {
							//	$("#hostCommandsTable").toggleSubGridRow(selRowId);
							//	$("#hostCommandsTable").expandSubGridRow(selRowId);
							//}
						},
						failure: function(errMsg) {
							
						}
					});	
					closePopWindowManual();	
					
				} else {
					runCommandForMultipleHost(commandInputStr);
				}
			} else {
				closePopWindowManual();		
			}
			
			
    	}
    	
    	function refreshCommand() {
			var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');
			if (selRowId) {
				var hostMacAddress = $("#hostCommandsTable").getCell(selRowId,"macAddress");
				var subGrid = $("#hostCommandsTable_" + selRowId + "_t");
				var ids = subGrid.getDataIDs();
				$.ajax({
					type: "get",
					dataType: "json",
					url: "/monitorserver/main/getHostCommands?hostMacAddress=" + hostMacAddress,
					complete :function(data) {						
						if (data.responseText != "Failed") {
							var commandArray = data.responseJSON;
							$.each(commandArray, function(i, command){
								if (jQuery.inArray(command.id, ids)) {
									subGrid.setRowData(command.id, command);
									$("#commandResultStr"+command.id).attr("title", command.resultStr);
								} else {
									subGrid.addRowData(command.id, command, first);
								}								
							});
						}
					},
					success: function(commandArray){
					}});
			}
    	}
    
    </script>
</head>
<body>

	<input type="hidden" value="${userEntity.id}" id="loggedUserID" />
		
	<!-- 命令运行窗口 -->
	<div class="window" id="center" style="z-index:999"> 
		<div id="title" class="title">Run Command Window</div> 
		<div class="content">		
			<input type='hidden' id='cmdinputId'/>
			<input type='hidden' id='cmdinputMacAddress'/>
			<table id="commandInputTable">
				<tr>
					<td id="commandInputLabel" width="40%">Command Input: </td>
					<td colspan="2"><input type='text' id='cmdinput' style='width:200' /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td style="text-align:right"><button id='cmdInputBtn' type='button' onclick='runCommand()'>Run Command</button></td>
					<td><button id='closeBtn' class="close" type='button'>Close</button></td>
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
	
	<!-- 进程管理窗口 -->
	<div id="processWindow" style="z-index:999;display:none;">
		<div id="title" class="title">Process Management Window</div>
		<div class="content">
			<input type='hidden' id='processInputId'/>
			<input type='hidden' id='processInputMacAddress'/>
			<input type='hidden' id='processListStr'/>
			<table id="processListTable">
				<tr>
					<td><input type='text' id='processInput' style='width:200' /></td>
					<td><button id='addProcessBtn' type='button'>Add</button></td>
				</tr>
			</table>			
			<table style="">
				<tr>
					<td style="text-align:right"><button id='submitProcessBtn' type='button' onclick='commitProcessCommand()'>Commit</button></td>
					<td><button id='closeProcessBtn' class="close" type='button'>Close</button></td>
				</tr>
			</table>
		</div> 
	</div>
	
	
	<div class="adminheader">
		<br />
		<a id="logout" class="adminheader-a" href="logout">退出</a>&nbsp;
		<a id="agentdownload" class="adminheader-a" href="agent-download" target="blank"><span id="downloadAgentBtn">Download Agent</span></a>&nbsp;
		<c:if test="${roleName.equals('ROLE_ADMIN')}">
		    <a id="toAdminIndex" class="adminheader-a" href="toAdminPage">进入管理页面</a>
		</c:if>
	</div>
	
	<table id="hostCommandsTable"></table>
	<div id="hostCommandsPager"></div>
	
</body>
</html>
