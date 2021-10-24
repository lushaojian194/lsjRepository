<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小海豚自助酒店</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<style>
	 .deng{float:right;color: red;margin-right: 120px;margin-top:40px;font-weight: bold;}   
	 .myroom{float: right;margin-top: 40px;margin-right: 20px;color: #28A4C9;}
	 #p{display: inline;color: #28A4C9; font-weight: bold; margin-left: 100px;}
	 #time{display: inline;color: #28A4C9;margin-left: 5px;}
	 #k{background-color:whitesmoke;width: 100%;margin: 0px;}
	 #re{margin-left: 20px;color: #28A4C9;}
	 .room{margin-left: 20px;} 
	 .ro{float: left;margin-left: 9px;margin-top: 9px;display: inline;border:groove;}	
</style> 
<!--动态显示时间-->
 <script type="text/javascript">	
	function getTime(){		
      $("#time").html("<font size='4'>"+new Date().toLocaleString()+"</font>");
      window.setInterval("getTime()",1000); //每隔1s取一次函数值  
}   
      window.onload = getTime();   
</script>

    <script type="text/javascript">
        //jquery 自动执行
        $(function(){
            $.ajax({
                url:"${pageContext.request.contextPath}/find/Room?end=10",
                type:"post",
                data:"null",
                success:function(result){
                    //result是json的字符串或json数组，把json字符串或json数组转换为json对象
                    var jsonObj = JSON.parse(result);
                    //局部更新前清空元素，不然会叠加
                    $("#room").html("");
                    //使用JavaScript或jQuery等实现局部更新
                    $.each(jsonObj,function(i,c){
                        var dog = "<div class='ro'><a href='${pageContext.request.contextPath}/find/roomInformation?location="+c.location+"'><img src='${pageContext.request.contextPath}/"+c.picture+"' style='width: 254px;height: 250px;'><div style='text-align: center;'>"+c.roomType+"</div></a></div>";
                        $("#room").append(dog);
                    });
                },
                error:function(){
                    alert("失败");
                }
            });
        })
    </script>

    <!--AJAX方法 ,不用加载整个界面，对界面进行局部的部刷新-->
    <script type="text/javascript">
        function isExist(roomtype){
            $.ajax({
                url:"${pageContext.request.contextPath}/find/RoomType?roomType="+roomtype,
                type:"post",
                data:"null",
                success:function(result){
                    //result是json的字符串或json数组，把json字符串转换为javaScript对象
                    var jsonObj = JSON.parse(result);
                    //局部更新前清空元素，不然会叠加
                    $("#room").html("");
                    //使用JavaScript或jQuery等实现局部更新
                    $.each(jsonObj,function(i,c){
                        var dog = "<div class='ro'><a href='${pageContext.request.contextPath}/find/roomInformation?location="+c.location+"'><img src='${pageContext.request.contextPath}/"+c.picture+"' style='width: 254px;height: 250px;'><div style='text-align: center;'>"+c.roomType+"</div></a></div>";
                        $("#room").append(dog);
                    });
                },
                error:function(){
                    alert("失败");
                }
            });
        }
    </script>
</head>
<body> 
   <div id="k">	   
	<div id="p"><img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/><font size="6">&nbsp;&nbsp;欢迎入住小海豚自助酒店</font>&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/>
	      <%--<div id="time"></div>--%>
          <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/personalStar?userName=${user.userName}">我的评价</a></font></div></c:if>
          <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/myRoom?userName=${user.userName}">我的房间</a></font></div></c:if>
          <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/user/balance?userName=${user.userName}">个人信息</a></font></div></c:if>
          <c:if test="${not empty user}"><div class="myroom"><font size="3"><a href="${pageContext.request.contextPath}/find/roomRecord?num=1">住房记录</a></font></div></c:if>
          <c:if test="${empty user}"><div class="deng"><font size="3"><a href="${pageContext.request.contextPath}/jsp/login.jsp">登陆</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a> <a href="${pageContext.request.contextPath}/find/Star">评价</a> </font></div></c:if>
          <c:if test="${not empty user}"><div style="float: right;margin-right: 80px;margin-top:40px;font-weight: bold;"><font size="3">欢迎您&nbsp;&nbsp;,&nbsp;&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/Quit">退出</a></font></div></c:if>
</div>

 <ul class="layui-nav" lay-filter=""> <%-- "${pageContext.request.contextPath}/RoomServlet?method=findTypeRoom&room=情侣房" --%>
  <li class="layui-nav-item"><a href="javascript:void(0);" onclick="isExist('单人房')">单人房</a></li>
  <li class="layui-nav-item"><a href="javascript:void(0);" onclick="isExist('双人房')">双人房</a></li>
  <li class="layui-nav-item"><a href="javascript:void(0);" onclick="isExist('情侣房')">情侣房</a></li>
  <li class="layui-nav-item"><a href="javascript:void(0);" onclick="isExist('豪华房')">豪华房</a></li>
  <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/jsp/uindex.jsp">首页</a></li>
</ul>  

<div class="banner" id="b04" style="width: 100%;">
	<ul>
		<li class="slider-item"><a href="#"><img src="${pageContext.request.contextPath}/img/4.jpg" alt="" width="100%" height="480"><span class="slider-title"><em><font size="6">单人房</font></em></span></a></li>
		<li class="slider-item"><a href="#"><img src="${pageContext.request.contextPath}/img/5.jpg" alt="" width="100%" height="480"><span class="slider-title"><em><font size="6">情侣房</font></em></span></a></li>
		<li class="slider-item"><a href="#"><img src="${pageContext.request.contextPath}/img/6.jpg" alt="" width="100%" height="480"><span class="slider-title"><em><font size="6">双人房</font></em></span></a></li>
		<li class="slider-item"><a href="#"><img src="${pageContext.request.contextPath}/img/7.jpg" alt="" width="100%" height="480"><span class="slider-title"><em><font size="6">总统房</font></em></span></a></li>		
	</ul>
	<div class="progress"></div>
	<a href="javascript:void(0);" class="unslider-arrow04 prev"><img class="arrow" id="al" src="${pageContext.request.contextPath}/img/arrowl.png" alt="prev" width="20" height="35"></a>
	<a href="javascript:void(0);" class="unslider-arrow04 next"><img class="arrow" id="ar" src="${pageContext.request.contextPath}/img/arrowr.png" alt="next" width="20" height="37"></a>
</div>
<br/>
  <div id="re"><h1>${requestScope.title  }</h1></div>
  <div id="room">

  </div><br/> 
 
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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
</script>
</body>
</html>