<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

		<div id="content">
		<form action="<c:url value='/auth/process/'/>" method="post" name="loginForm" id="loginForm">
			<h1><img src="/common/img/login_tit.gif" alt="admin Login" /></h1>
			<ul>
				<li>
					<input type="text" class="user_id" id="j_username" name="j_username" value=""/>
				</li>
				<li>
					<input type="password" class="user_pw" id="j_password" name="j_password" value=""/>
				</li>
			</ul>
			<span><a href="javascript:void();" class="submitAnchor">
			<input type="image" name="submit" src="/common/img/login_btn.gif" />
			</a></span>
		</form>
		</div>	
		
		<script type="text/javascript">
		$(function(){
			$(".submitAnchor").live({
				click : function (){
					if($("j_username").val() == ""){
						alert("insert your ID!");
						$("j_username").focus();
						return false;
					}
					if($("j_password").val() ==""){
						alert("insert yor password!");
						$("j_password").focus();
						return false;
					}
					
					$("#loginForm").submit();
				}
			});
			if(params.getURLParam("error"))
				alert("Incorrect Account! try Again please!");
		});
		</script>