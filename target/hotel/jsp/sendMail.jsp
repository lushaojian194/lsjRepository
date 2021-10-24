<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>找回密码-小海豚自助酒店</title>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库--> --%>

</head>
<body>

<!-- 引入头部 -->
<%@include file="header.jsp" %>

	<div style="text-align: center;width: 300px;height: 200px;margin-left: 550px;">
		<form action="${pageContext.request.contextPath}/findPassword" method="post">
 
            <div style="width=250px,height=50px">
			<input type="email" name="email" id="email" class="form-control" width="100px"
				height="60px" style="margin-top: 100px" placeholder="请输入您的邮箱地址"
				required>
			</div>	
		   <br>
			<br>
 
			<button type="submit" class="btn btn-success" id="button"
				width="100px" height="60px">找回密码</button>
 
		</form>
		<br>
		<br>
 
		<button type="button" class="btn btn-primary" id="button"
			onclick="backLogin()" width="100px" height="60px">返回登录页面</button>
	</div>
	<script type="text/javascript">
		function backLogin() {
			window.location.href = "login.jsp"
		}
	</script>
</body>
</html>
