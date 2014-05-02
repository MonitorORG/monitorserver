//获取窗口的高度 
var windowHeight; 
//获取窗口的宽度 
var windowWidth; 
//获取弹窗的宽度 
var popWidth; 
//获取弹窗高度 
var popHeight; 
function init(){ 
   windowHeight=$(window).height(); 
   windowWidth=$(window).width(); 
   popHeight=$(".window").height(); 
   popWidth=$(".window").width(); 
} 
//关闭窗口的方法 
function closePopWindow() {	
	$(".close").click(function() {
		closePopWindowManual();
	});
}

function closePopWindowManual() {	
	$(".window").hide("slow");
	
	// for multiple commands executing
	$(".executingInfo").remove();
	$('body').stopTime ('D');
	newCommandBatchIds = "";
	hostindex = 0;
}
	
//定义弹出居中窗口的方法 
function popCenterWindow(){ 
	init(); 
	//计算弹出窗口的左上角Y的偏移量 
	var popY=windowHeight/2 - 200; 
	var popX=windowWidth/2; 
	//alert(popY); 
	//设定窗口的位置 
	$("#center").css("top",popY).css("left",popX).slideToggle("slow");  
	closePopWindow(); 
} 

// ---------------
function closeProcessPopWindowManual() {	
	$("#processWindow").hide("slow"); 	
}

function popProcessWindow(){ 
	init(); 
	var popY=windowHeight/2 - 200; 
	var popX=windowWidth/2; 
	$("#processWindow").css("top",popY).css("left",popX).slideToggle("slow");  
	
	$("#closeProcessBtn").click(function() {
		$("#processWindow").hide("slow");
	});
} 