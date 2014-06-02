function addProcessBtnClick() {
	var newProcessInput = $("#processInput").val();
	if (newProcessInput != "") {
		$("#processListTable").append("<tr class='processtr'><td>" + newProcessInput + "</td><td><button onclick='delProcessRow(this)'>"+$.i18n.prop('del.btn')+"</button></td></tr>");
		
		var processlist = $("#processListStr").val();
		processlist = processlist + "#" + newProcessInput + "|";
		$("#processListStr").val(processlist);
	}
	$("#processInput").val("")
}

function commitProcessCommand() {
	var hostId = $("#processInputId").val();
	var macAddress = $("#processInputMacAddress").val();
	var processListStr = $("#processListStr").val();
	
	var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');
	
	if (macAddress != '') {	// processListStr has been changed
	
		$("#hostCommandsTable").setCell(selRowId, 'processList', processListStr);
		//$("#hostCommandsTable").setCell(selRowId, 'processStatusResults', processStatusStr);
	
		$.ajax({
			type: "POST",
			url: "/monitorserver/main/editHostProcessList",
			data: {macAddress:macAddress,processList:processListStr},
			dataType: "json",
			complete :function(data) {
				closeProcessPopWindowManual();
			},
			success: function(data){
			},
			failure: function(errMsg) {
				
			}
		});			
	}  
}

function delProcessRow(delBtn) {
	
	var delProcessName = $(delBtn).parent().parent().find("td:first").text();
	var processlist = $("#processListStr").val();
	processlist = processlist.replace("#" + delProcessName + "|", "");
	$("#processListStr").val(processlist);
	
	$(delBtn).parent().parent().remove();
}
		
function showInputProcessPanel(id, macAddress) {
	var selRowId = $("#hostCommandsTable").jqGrid('getGridParam', 'selrow');
	if (id != selRowId) {				
		jQuery("#hostCommandsTable").setSelection(id);
	}
	
	$(".processtr").remove();	
	$("#processListStr").val("");		
	$("#processInputId").val(id);
	$("#processInputMacAddress").val(macAddress);
	var processlist = $("#hostCommandsTable").getCell(id, 'processList');
	$("#processListStr").val(processlist);
	$("#processListTable").append(processlist.replace(/\#/g, "<tr class='processtr'><td>").replace(/\|/g, "</td><td><button onclick='delProcessRow(this)'>"+$.i18n.prop('del.btn')+"</button></td></tr>"));
	
	popProcessWindow();
}
		
function processStatusFormatter(cellvalue, options, rowdata) {
	return getProcStaTableHtml(rowdata.id, rowdata.processStatusResults);
	//return "";
}

function processCmdFormatter(cellvalue, options, rowdata) {
	return "<button type='button' onclick='showInputProcessPanel(\"" + rowdata.id + "\", \"" + rowdata.macAddress + "\")'>"+$.i18n.prop('process.manage')+"</button>";
}

function getProcStaTableHtml(id, processStatusStr) {
	if (processStatusStr == null) processStatusStr = "";
	return "<div class='procStatus'>" + processStatusStr.replace(/\#\*0/g, "<span class='wait'>&nbsp;&nbsp;&nbsp;&nbsp;</span>").replace(/\#\*1/g, "<span class='running'>&nbsp;&nbsp;&nbsp;&nbsp;</span>").replace(/\#\*2/g, "<span class='stop'>&nbsp;&nbsp;&nbsp;&nbsp;</span>").replace(/\@/g, "&nbsp;<span>").replace(/\|/g, "</span><br/>") + 
		"</div>";
}