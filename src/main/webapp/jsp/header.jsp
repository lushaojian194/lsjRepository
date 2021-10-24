<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头部</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<style>
	 .deng{display:inline;float:right;margin-right: 120px;margin-top:40px;color:#28A4C9; font-weight: bold;}
	 .myroom{float: right;margin-top: 40px;margin-right: 20px;}
	 #p{display: inline;color: #28A4C9; font-weight: bold; margin-left: 100px;}
	 #time{display: inline;color: #28A4C9;margin-left: 5px;}
	 #k{background-color:whitesmoke;width: 100%;margin: 0px;}
	 .li{float: left;text-align: center;list-style-type: none;width: 100px;}
</style>


	<!--动态显示时间-->
<script type="text/javascript">	
	function getTime(){		
      $("#time").html("<font size='4'>"+new Date().toLocaleString()+"</font>");
      window.setInterval("getTime()",1000); //每隔1s取一次函数值  
}   
      window.onload = getTime();  
   
</script> 
</head>
<body>
 <div id="k">	
	<div id="p"><img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/><font size="6">&nbsp;&nbsp;欢迎入住小海豚自助酒店</font>&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/>
	        <%--<div id="time"></div>	--%>
            <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/personalStar?userName=${user.userName}">我的评价</a></font></div></c:if>
	        <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/myRoom?userName=${user.userName}">我的房间</a></font></div></c:if>
            <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/user/balance?userName=${user.userName}">个人信息</a></font></div></c:if>
            <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/roomRecord?num=1">住房记录</a></font></div></c:if>
            <c:if test="${empty user}"><div class="deng"><font size="3"><a href="${pageContext.request.contextPath}/jsp/login.jsp">登陆</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></font></div></c:if>
            <c:if test="${not empty user}"><div style="float: right;margin-right: 80px;margin-top:40px;font-weight: bold;"><font size="3">欢迎您&nbsp;&nbsp;,&nbsp;&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/Quit">退出</a></font></div></c:if>
	</div>
  </div>
  
  <ul class="layui-nav" lay-filter="">
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/uindex.jsp">首页</a></li> 
</ul>

</body>
</html>