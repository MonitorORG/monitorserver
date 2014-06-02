var newCommandBatchIds = "";
var hostindex = 0;
	
function selectColFormatter(cellvalue, options, rowdata) {
	return "<input id='checkbox"+rowdata.id+"' name='"+rowdata.id+"' class='gridcheckbox' type='checkbox' />";
}

function clickGridParentCheckbox(obj, event) {
	stopBubble(event);
	$(".gridcheckbox").prop("checked", $(obj).prop("checked"));
}

function stopBubble(e)
{
    if (e && e.stopPropagation)
        e.stopPropagation();
    else
        window.event.cancelBubble=true;
}

function runCommandForMultipleHost(commandInputStr) {
	
	
	$("#cmdinput").val("");
	var hostcount = $(".gridcheckbox:checked").length;	
	
	$(".gridcheckbox:checked").each(function() {		
		var rowId = $(this).attr("name");
		var macAddress = $("#hostCommandsTable").getCell(rowId,"macAddress");
		var hostname = $("#hostCommandsTable").getCell(rowId,"hostname");
		
		$.ajax({
			type: "POST",
			url: "/monitorserver/main/createCommand",
			data: {hostMacAddress:macAddress,commandStr:commandInputStr},
			dataType: "json",
			complete: function(data){				
				if (data.responseText != "Failed") {
					var newCommandId = data.responseText;
					newCommandBatchIds += "," + newCommandId + ",";
					$("#commandInputTable").append("<tr class='executingInfo'><td>" + hostname + "</td><td>"+commandInputStr+"</td><td id='executeResult"+newCommandId+"'><span class='executing'>&nbsp;</span></td></tr>");
				}
				hostindex += 1;
				if (hostindex == hostcount) {
					refreshExecutingCommands();
				}
			},
			success: function(data){
			},
			failure: function(errMsg) {
			}
		});
		
	});
	
}

function refreshExecutingCommands() {
	$('body').everyTime('3s','D',function(){
		if (hostindex > 0) {
			$.ajax({
		        type: "get",
		        dataType: "json",
		        url: "/monitorserver/main/findCommandByIds?commandBatchIds=" + newCommandBatchIds,
		        complete :function(data) {
		        	if (data.responseText != "Failed") {
						var executingCommandsArray = data.responseJSON;
						$.each(executingCommandsArray, function(i, command){
							if (command.status == 'Sucess' || command.status == 'Failed') {
								$("#executeResult"+command.id).html("<span title='"+command.resultStr+"'>"+command.status+"</span>");
								
								hostindex -= 1;
								newCommandBatchIds = newCommandBatchIds.replace(","+command.id+",", "");
								if (newCommandBatchIds == "" || hostindex == 0) {
									hostindex = 0;
									newCommandBatchIds = "";
									$('body').stopTime ('D');
									//closePopWindowManual();
								}
							}
						});
		        	}
		        },
		        success: function(executingCommandsArray){
		    }});
			
		} else {
			hostindex = 0;
			newCommandBatchIds = "";
			$('body').stopTime ('D');
			//closePopWindowManual();
		}
	},0,true);
}