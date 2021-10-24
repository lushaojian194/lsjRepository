<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间详情</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<style>
	 .deng{float: right;margin-right: 120px;margin-top:40px;color:#28A4C9; font-weight: bold;}
	 .myroom{float: right;margin-top: 40px;margin-right: 20px;color:#28A4C9;}
	 #p{display: inline;color: #28A4C9; font-weight: bold; margin-left: 100px;}
	 #time{display: inline;color: #28A4C9;margin-left: 5px;}
	 #k{background-color:whitesmoke;width: 100%;margin: 0px;}
	/*  body{
	    background-image: url(img/sea.jpg);
		background-size: 1365px 700px;
		background-repeat: no-repeat;
	 }  */	  
</style>
</head>
	<!--动态显示时间-->
<script type="text/javascript">	
	function getTime(){		
      $("#time").html("<font size='4'>"+new Date().toLocaleString()+"</font>");
      window.setInterval("getTime()",1000); //每隔1s取一次函数值  
}   
      window.onload = getTime();    
</script>
</head>
<body background="${pageContext.request.contextPath}/img/sea.jpg">
<div id="k">	
	<div id="p"><img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/><font size="6">&nbsp;&nbsp;欢迎入住小海豚自助酒店</font>&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/>
	      <%-- <div id=time></div>--%>
		    <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/personalStar?userName=${user.userName}">我的评价</a></font></div></c:if>
	        <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/myRoom?userName=${user.userName}">我的房间</a></font></div></c:if>
            <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/user/balance?userName=${user.userName}">个人信息</a></font></div></c:if>
		    <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/roomRecord?num=1">住房记录</a></font></div></c:if>
		    <c:if test="${empty user}"><div class="deng"><font size="3"><a href="${pageContext.request.contextPath}/jsp/login.jsp">登陆</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></font></div></c:if>
            <c:if test="${not empty user}"><div style="float: right;margin-right: 80px;color: #28A4C9;margin-top:40px;font-weight: bold;"><font size="3">欢迎您&nbsp;&nbsp;,&nbsp;&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/Quit">退出</a></font></div></c:if>
	</div>
</div>
 <ul class="layui-nav" lay-filter="">
  <%-- <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/RoomServlet?method=findTypeRoom&room=单人房">单人房</a></li>
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/RoomServlet?method=findTypeRoom&room=双人房">双人房</a></li>
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/RoomServlet?method=findTypeRoom&room=情侣房">情侣房</a></li> 
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/RoomServlet?method=findTypeRoom&room=豪华房">豪华房</a></li> --%>
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/uindex.jsp">首页</a></li>
</ul>  
<div style=" /* border:groove;  background-color:white; */ margin-top:0px display:inline"> 
	 <div style="text-align: center;"><img src="${pageContext.request.contextPath}/${roomDetail.picture }" style="width: 500px; height: 400px;">	    
	    <div style="font-weight: bold;">房间号：${roomDetail.location }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格：${roomDetail.price }</div>	   
	 </div>
	 <div style="margin-left: 430px;">${roomDetail.introduce }</div>
	 <div id="book" style="display: inline;margin-left: 510px;"><button class="layui-btn layui-btn-normal" onclick="yuding()"><font size="2">预定</font></button></div>
	 <div style="display: inline;margin-left: 200px;"><button class="layui-btn layui-btn-normal" onclick="ruzhu()"><font size="2">入住</font></button></div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script>
$(document).ready(function(e) {
	var progress = $(".progress"),li_width = $("#b04 li").length;
    var unslider04 = $('#b04').unslider({
		dots: true,
		complete:function(index){//自己添加的，官方没有
			progress.animate({"width":(100/li_width)*(index+1)+"%"});
		}
	}),
	data04 = unslider04.data('unslider');
	$('.unslider-arrow04').click(function() {
        var fn = this.className.split(' ')[1];
        data04[fn]();
    });
});

   //入住
   function ruzhu(){
	   if('${user}'==""){
		   alert("你还未登陆，请先登陆");
		   window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
	   }else if('${myRoom.location}'!=""){
	   	   alert("你已经入住了房间");
		   window.location.href="${pageContext.request.contextPath}/jsp/myRoom.jsp";
	   }else{
		   window.location.href="${pageContext.request.contextPath}/jsp/checkIn.jsp";
	   }
   }
   //预定
   function yuding(){
	   if('${user}'==""){
		   alert("你还未登陆，请先登陆");
		   window.location.href="${pageContext.request.contextPath}/jsp/login.jsp";
	   }else if('${myRoom.location}'!=""){
	   	   alert("你已经预定了房间");
		   window.location.href="${pageContext.request.contextPath}/jsp/myRoom.jsp";
	   }else{
		   window.location.href="${pageContext.request.contextPath}/jsp/bookRoom.jsp";
	   }
   }

</script>
</body>
</html>