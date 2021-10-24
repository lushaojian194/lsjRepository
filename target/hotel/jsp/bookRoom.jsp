<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

<style>
	 .deng{float: right;margin-right: 120px;margin-top:40px;color:#28A4C9; font-weight: bold;}
	 .myroom{float: right;margin-top: 40px;margin-right: 20px;color: #28A4C9;}
	 #p{display: inline;color: #28A4C9; font-weight: bold; margin-left: 100px;}
	 #time{display: inline;color: #28A4C9;margin-left: 5px;}
	 #k{background-color:whitesmoke;width: 100%;margin: 0px;}
	/* .li{float: left;text-align: center;list-style-type: none;width: 100px;}*/
</style>


	<!--动态显示时间-->
<script type="text/javascript">	
	function getTime(){		
      $("#time").html("<font size='4'>"+new Date().toLocaleString()+"</font>");
      window.setInterval("getTime()",1000); //每隔1s取一次函数值  
}   
      window.onload = getTime();   
</script>
    <script>
        function bookRoom() {
            var sign = true;
            var bookDay = $("#test").val();
            var day = $("#day").val();
            if(bookDay==""||day==""){
                alert("日期或天数不能为空");
                sign = false;
            }
            //日期或时间为空时，sign==false,不能提交
            if(sign){
                $.ajax({
                    url:"${pageContext.request.contextPath}/find/bookTime?location=${roomDetail.location}&price=${roomDetail.price}"+"&bookDay="+bookDay+"&day="+day,
                    type:"post",
                    data:"null",
                    success:function(result){
                        //result是json的字符串或json数组，把json字符串转换为json对象
                        var jsonObj = JSON.parse(result);
                        //使用JavaScript或jQuery等实现局部更新
                        $.each(jsonObj,function(i,c){
                            if(i=="notEnough"){
                                //余额不足，跳转个人信息界面，进行充值
                                alert(jsonObj[i])
                                window.location.href="http://localhost:8080/hotel/jsp/personalInfo.jsp";
                                //window.location.href="http://129.204.78.107:8080/hotel/jsp/personalInfo.jsp";
                            }else if(i=="bookSuccess"){
                                alert(jsonObj[i]);
                                //入住成功，跳到我的房间界面，显示信息
                                window.location.href="http://localhost:8080/hotel/jsp/myRoom.jsp";
                                //window.location.href="http://129.204.78.107:8080/hotel/jsp/myRoom.jsp";
                            }else if(i=="alreadyBook"){
                                //该房间已经被预定，跳回首页，重新选择房间
                                alert(jsonObj[i]);
                                window.location.href="http://localhost:8080/hotel/jsp/uindex.jsp";
                                //window.location.href="http://129.204.78.107:8080/hotel/jsp/uindex.jsp";
                            }else if(i=="wrongDate"){
                                alert(jsonObj[i]);  //只能选择当前月或下一个月
                                $("#test").val("");
                                $("#day").val("");
                            }else if(i=="pastDate"){
                                alert(jsonObj[i]); //不能选择过去的时间
                                $("#test").val("");
                                $("#day").val("");
                            }else if(i=="forWordSevenDays"){
                                alert(jsonObj[i]);  //只能提前7天预定
                                $("#test").val("");
                                $("#day").val("");
                            }
                        });
                    },
                    error:function(){
                        alert("失败");
                    }
                });
            }
        }
    </script>
</head>
<body background="${pageContext.request.contextPath}/img/sea.jpg">
<div id="k">	
	<div id="p"><img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/><font size="6">&nbsp;&nbsp;欢迎入住小海豚自助酒店</font>&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/8.png" width="80px" height="60px"/>
	        <%--<div id="time"></div>--%>
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

<div style="width: 500px;height: 400px; margin-left: 420px;margin-top: 80px;">
<%--<form action="${pageContext.request.contextPath}/RoomServlet?method=bookRoom&userName=${user.userName}&location=${roomDetail.location}&price=${roomDetail.price}" method="post">	--%>
  <table class="layui-table">
  <colgroup>
    <col width="250">
    <col width="250">
    <col width="100">
  </colgroup>
  <tbody>
  	<tr>
  		<td>请选择预定日期</td>
  		<td>
            <input type="datetime" id="test" name="bookDay"class="form-control" placeholder="请选择预定日期">
  		</td>
  	</tr>
    <tr>
      <td>请输入预定天数</td>
      <td><input type="text" id="day" name="day" class="form-control" placeholder="请输入预定天数"></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;"><button onclick="bookRoom()" class="layui-btn layui-btn-normal"><font size="4">确定</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>  --%>
</div>
  
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<script>
//执行一个laydate实例
laydate.render({ 
	  elem: '#test'
	  ,type: 'datetime'
});
</script>
</body>
</html>