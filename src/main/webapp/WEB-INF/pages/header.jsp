<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script>
	$(document).ready(function() {
		initHeaderLinks($("#currentpageIndex").val());
	});
	
	function initHeaderLinks(currentPageLinkId) {
		$(".adminheader a").each(function(){
			if ($(this).attr("id") == currentPageLinkId) {
				$(this).removeClass("adminheader-a");
				$(this).addClass("adminheader_selected-a");
			} else {
				$(this).removeClass("adminheader_selected-a");
				$(this).addClass("adminheader-a");
			}
		});
	}
</script>
<div class="adminheader">	
	<br />
	<a id="toHostManageLink" class="adminheader_selected-a" href="toHostManagePage">主机管理</a>&nbsp;
	<a id="toUserLink" class="adminheader-a" href="toUserManagePage">用户管理</a>&nbsp;
	<a id="toRegisterLink" class="adminheader-a" href="toRegisterUsrPage">普通用户注册</a>&nbsp;
	<a id="toIndexLink" class="adminheader-a" href="index">主页面</a>&nbsp;
    <a id="logout" class="adminheader-a" href="logout">退出</a>&nbsp;
    
    <input type="hidden" value='<%=request.getParameter("currentpageIndex")%>' id="currentpageIndex" />
    <input type="hidden" value="${isIndexPage}" id="isIndexPage" />
</div>