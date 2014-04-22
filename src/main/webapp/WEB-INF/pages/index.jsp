<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Sample Page</title>
	
    <link href='<c:url value="/main/css/ui.jqgrid.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/jquery.ui.theme.css"/>' type="text/css" rel="stylesheet"></link>
	<link href='<c:url value="/main/css/main.css"/>' type="text/css" rel="stylesheet"></link>
	
	<script type="text/javascript" src='<c:url value="/main/js/jquery-1.11.0.min.js"></c:url>'></script>
    <script type="text/javascript" src='<c:url value="/main/js/jquery.timer.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/jquery.jqGrid.min.js"></c:url>'></script>
	
	<script type="text/javascript" src='<c:url value="/main/js/i18n/grid.locale-en.js"></c:url>'></script>
	<script type="text/javascript" src='<c:url value="/main/js/grid.subgrid.js"></c:url>'></script>
	
		<script src="js/window.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    	$(document).ready(function(){
		
			$("#hostCommandsTable").jqGrid({
    			url:'/monitorserver/services/hostInfo/hostStatusInfoService/allHost/1',
    			datatype: "json",
    			height: 450,
    			colNames:['ID', 'Host Name','Mac Address', 'CPU Used', 'Free Mem', 'Mem Used', 'Command'], 
				colModel:[ 
					{name:'id',index:'id', width:40, sorttype:"int"}, 
					{name:'hostname',index:'hostname', width:150}, 
					{name:'macAddress',index:'macAddress', width:150}, 
					{name:'cpuTotalUsed',index:'cpuTotalUsed', width:80, formatter: cpuUsedFormatter },
					{name:'freeMem',index:'freeMem', hidden:true},
					{name:'totalMem',index:'totalMem', width:80, formatter: memUsedFormatter }, 
					{name:'commandCol',index:'commandCol', width:200, align: 'center', formatter: commandColFormatter}
				],
    			rowNum:10,
    			rowList:[10,20,30],
    			pager: '#hostCommandsPager',
    			sortname: 'id',
    			viewrecords: true,
    			sortorder: "desc",
    			multiselect: false,
				beforeSelectRow: function(rowId, e) {
					var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');
					//if (selRowId) {
					//	alert(selRowId);
					//	//$("#hostCommandsTable").collapseSubGridRow(selRowId);
					//	//$("#hostCommandsTable_" + selRowId + "_t").trigger("reload");
					//	$("#hostCommandsTable").toggleSubGridRow(selRowId);
					//}
				},
				onSelectRow: function(id){ 			
					//var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');				
				},
    			subGrid: true,
    			caption: "Host Commands",
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
		    			url:"/monitorserver/services/command/userCommandService/allcommand/"+hostMacAddress,
		    			datatype: "json",
		    			colNames:['ID', 'Mac Address', 'Command Str', 'Status', 'Result Show'], 
						colModel:[ 
							{name:'id',index:'id', width:40, sorttype:"int"}, 
							{name:'hostMacAddress',index:'hostMacAddress', width:150}, 
							{name:'commandStr',index:'commandStr', width:200 },
							{name:'status',index:'status', width:80 }, 
							{name:'resultShow',index:'resultShow', width:100, align: 'center', formatter: resultShowFormatter}
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
    			// this function is called before removing the data
    			//var subgrid_table_id;
    			//subgrid_table_id = subgrid_id+"_t";
    			//jQuery("#"+subgrid_table_id).remove();
    			}
    		});
    		jQuery("#hostCommandsTable").jqGrid('navGrid','#hostCommandsPager',{add:false,edit:false,del:false});
    		
    		//每10秒执行，无限次，并命名计时器名称为C
    		//若时间间隔抵到，但函式程序仍未完成则需等待执行函式完成后再继续计时
    		//$('body').everyTime('1das','C',function(){
    		$('body').everyTime('10s','C',function(){
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
			return "<button type='button' onclick='showInputCommandPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>Input Command</button>";
			//return "<input type='text' id='" + rowdata.id + "_cmdinput' style='width:200' /><button name='" + rowdata.id + "_cmdinput' type='button' onclick='runCommand(this, \"" + rowdata.macAddress + "\")'>Run Tasklist Command</button>";
		}
		
		function showInputCommandPanel(id, macAddress) {
			$("#cmdinputId").val(id);
			$("#cmdinputMacAddress").val(macAddress);
			$("#cmdinput").val("");
			jQuery("#hostCommandsTable").setSelection(id);
			
			popCenterWindow();
		}
		
		function resultShowFormatter(cellvalue, options, rowdata) {
			//alert(JSON.stringify(rowdata));
			return "<span id='commandResultStr" + rowdata.id + "' title='" + rowdata.resultStr + "'>Result</span>";
		}
    	
    	function refreshAllHost() {
			var hostGrid = jQuery("#hostCommandsTable");
			var ids = hostGrid.getDataIDs();
    		$.ajax({
	            type: "get",
	            dataType: "json",
	            url: "/monitorserver/services/hostInfo/hostStatusInfoService/allHost/1",
	            complete :function() {
	            },
	            success: function(hostInfoArray){
					$.each(hostInfoArray, function(i, hostInfo){   
					
						//hostGrid.setCell(hostInfo.id, hostInfo);
						//hostGrid.setCell(hostInfo.id, hostInfo);
						//hostGrid.setCell(hostInfo.id, hostInfo);
						
						if (jQuery.inArray(hostInfo.id, ids)) {
							hostGrid.setRowData(hostInfo.id, hostInfo);
						} else {
							hostGrid.addRowData(hostInfo.id, hostInfo, first);
						}						
						
					});
					
	            }});
				
				var selRowId = hostGrid.jqGrid('getGridParam', 'selrow');
				if (selRowId) {
					//$("#hostCommandsTable").toggleSubGridRow(selRowId);
					//$("#hostCommandsTable").expandSubGridRow(selRowId);
					//$("#hostCommandsTable_" + selRowId + "_t").trigger("reload");
				}
				
    	}
    	
    	function runCommand() {
			//var commandInputStr = $('#' + $(comBtn).attr('name')).val();
			var commandInputStr = $("#cmdinput").val();
			var macAddress = $("#cmdinputMacAddress").val();
			var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');
			
			if (commandInputStr != '' && macAddress != '') {
			
				$.ajax({
					type: "POST",
					url: "/monitorserver/services/command/userCommandService/create",
					data: JSON.stringify({hostMacAddress:macAddress,commandStr:commandInputStr,status:"Created"}),
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					success: function(data){
						if (selRowId == $("#hostCommandsTable").jqGrid('getGridParam', 'selrow')) {
							$("#hostCommandsTable").toggleSubGridRow(selRowId);
							$("#hostCommandsTable").expandSubGridRow(selRowId);
						}
					},
					failure: function(errMsg) {
						
					}
				});			
			}  
			
			closePopWindowManual();			
			//$("#hostCommandsTable").toggleSubGridRow(selRowId);
			//$("#hostCommandsTable").expandSubGridRow(selRowId);
			//var hostMacAddress = $("#hostCommandsTable").getCell(selRowId,"macAddress");
			//$("#hostCommandsTable_" + selRowId + "_t").jqGrid("setGridParam", {
			//	url: "/monitorserver/services/command/userCommandService/allcommand/"+hostMacAddress, 
			//	datatype: "json"
			//}).trigger("reload");
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
					url: "/monitorserver/services/command/userCommandService/allcommand/" + hostMacAddress,
					complete :function() {
					},
					success: function(commandArray){
						
						$.each(commandArray, function(i, command){   

							if (jQuery.inArray(command.id, ids)) {
								subGrid.setRowData(command.id, command);
								$("#commandResultStr"+command.id).attr("title", command.resultStr);
							} else {
								subGrid.addRowData(command.id, command, first);
							}
							
						});
					}});
			}
    	}
    
    </script>
</head>
<body>

	<div class="window" id="center" style="z-index:999"> 
		<div id="title" class="title">Run Command Window</div> 
		<div class="content">		
			<input type='hidden' id='cmdinputId'/>
			<input type='hidden' id='cmdinputMacAddress'/>
			<table style="">
				<tr>
					<td width="40%">Command Input: </td>
					<td><input type='text' id='cmdinput' style='width:200' /></td>
				</tr>
				<tr>
					<td style="text-align:right"><button id='cmdInputBtn' type='button' onclick='runCommand()'>Run Command</button></td>
					<td><button id='closeBtn' class="close" type='button'>Close</button></td>
				</tr>
			</table>
		</div> 
	</div> 
	
	<div style="text-align:right;margin-right:10px;">
		<a href="agent-download" target="blank"><button>Download Agent 1.0</button></a>
	</div>  
	
	<table id="hostCommandsTable"></table>
	<div id="hostCommandsPager"></div>
	
</body>
</html>
