<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的房间</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css"/>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<style>
     .center{text-align: center;}
	 .li{float: left;text-align: center;list-style-type: none;width: 100px;margin-left: 100px;}
	 .box{display: none;width: 400px;height: 100px;margin-left:470px;margin-top: 60px;}
</style>
<script>	
       //续住
        function xuzhu(n){      
        	var days = document.getElementById("rudays").innerHTML;
        	if(days==0){
        		alert("你还未入住");
        	}else{
        		document.getElementById("xu").style.display=n?'block':'none';
        	}
        	        	      
        }
       //余额不足,不能续住
       function notEnoughMoney(){
    	   if('${notEnough}'=="余额不足"){ 
    		   alert("余额不足，请先充值");
    	   }   	  
       }
       setTimeout('notEnoughMoney()',300);
       
       //退房
       function forword(){

    	   var days = $("#rudays").html();
    	   //如果入住时间为空，则说明没有入住
    	   if(days==""){
    		   alert("你还未入住");
    		   return false;
    	   }
    	   //2019-09-18 10:25:26  ,  拆分为了获得当天的日
    	   var str = $("#tui").html();
    	   var strArray = str.split(" ");
    	   var strDate = strArray[0].split("-");
    	   //退房的日期
    	   var tuiday = strDate[2];

           //2019-09-18 10:25:26  ,  拆分为了获得当天的日
    	   var str1 = $("#ru").html();
    	   var strArray1 = str1.split(" ");
    	   var strDate1 = strArray1[0].split("-");
    	   //入住的日期
    	   var ruday = strDate1[2];
    	  // alert(tuiday+":"+ruday);
    	   var date = new Date();
    	   //今天的日期
    	   var today = date.getDate();
    	   //两者的差，如果为0，则说明没有提前退房
    	   var chaDay = tuiday-today;
           //chaDay==0，说明是在退房当天退房；tuiday-ruday==1，说明只住一天，比如14住，一天，但14号就退房，也不算提前退房
    	 if(chaDay!=0 && tuiday-ruday!=1){
    		 var result = confirm("您提前退房，需要扣除未住天数的20%费用，确定退房？");
    		 if(result){
    			 return true;
    		 }else{
    			 return false;
    		 }
    	 }else{
    		 var result = confirm("确定退房？");
    		 if(result){
    			 return true;
    		 }else{
    			 return false;
    		 }
    	 }
     }
</script>
</head>
<body background="${pageContext.request.contextPath}/img/sea.jpg">
<!-- 引入头部 -->
<%@include file="header.jsp" %>

<div style="width: 500px;height: 180px; margin-left: 420px;margin-top: 30px;">
<form action="#" method="get">
  <table class="layui-table">
  <colgroup>
    <col width="250">
    <col width="250">
    <col width="100">
  </colgroup>
  <tbody class="center">
   <tr>
      <td>姓名</td>
      <td><div>${user.namee }</div></td>
   </tr>
  	<tr>
      <td>房间号</td>
      <td><div>${myRoom.location}</div></td>
   </tr>
    <tr>
      <td>入住时间</td>
      <td><div id="ru">${myRoom.checkDay}</div></td>
    </tr>
    <tr>
      <td>入住天数</td>
      <td><div id="rudays">${myRoom.day}</div></td>
    </tr>
     <tr>
      <td>退房时间</td>
      <td><div id="tui">${myRoom.outDay}</div></td>
    </tr>
  </tbody>
  </table>
</form> <%--  <a href="${pageContext.request.contextPath}/RoomServlet?method=checkOut&location=${myRoom.location}&rudays=${myRoom.day}&outDay=${myRoom.outDay}"> --%>
 <ul>            	
   	<li class="li"> <button onclick="xuzhu(1)" class="layui-btn layui-btn-normal"><font size="2">续住</font></button></li>
   	<li class="li"> <form action="${pageContext.request.contextPath}/find/checkOut?location=${myRoom.location}&rudays=${myRoom.day}&outDay=${myRoom.outDay}&checkDay=${myRoom.checkDay}" method="post" onsubmit="return forword()"><button type="submit" class="layui-btn layui-btn-normal"><font size="2">退房</font></button></form></li>
 </ul>
</div>
<!--续住-->
 <div id="xu" class="box"> 
 	  <div style="float: right;"><a onclick="xuzhu(0)"><font size="3" style="color:white;font-weight: bold;">关闭</font></a></div> 
 <%--<form action="${pageContext.request.contextPath}/RoomServlet?method=xuzhu&day=${myRoom.day}&checkDay=${myRoom.checkDay}&location=${myRoom.location}" method="post">--%>
  <table class="layui-table">
  <colgroup>
    <col width="250"> 
    <col width="250">
    <col width="100">
  </colgroup> 
  <tbody>
    <tr>
      <td>续住天数</td>
      <td><div id="newPassword"><input type="text" id="xuzhuday" name="xuzhuday" class="form-control" placeholder="请输入续住天数"></div></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;"><button onclick="stayOver()" class="layui-btn layui-btn-normal"><font size="2">提交</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>--%>
 </div>
 
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script>
        function stayOver() {
            var sign = true;
            var stayDays = $("#xuzhuday").val();
            var rudays = $("#rudays").html();
            if(stayDays==""){
                alert("续住天数不能为空");
                sign = false;
            }

            //续住天数不为空时，才执行ajax提交
            if(sign){
                $.ajax({
                    url:"${pageContext.request.contextPath}/find/stayOver?location=${myRoom.location}&checkDay=${myRoom.checkDay}"+"&ruday="+rudays+"&xuday="+stayDays,
                    type:"post",
                    data:"null",
                    success:function(result){
                        //result是json的字符串或json数组，把json字符串或json数组转换为json对象
                        var jsonObj = JSON.parse(result);
                        //使用JavaScript或jQuery等实现局部更新
                        $.each(jsonObj,function(i,c){
                               if(i=="notEnoughMoney"){
                                   alert(jsonObj[i]);
                                   //余额不足，跳转个人信息界面，来充值
                                   window.location.href="http://localhost:8080/hotel/jsp/personalInfo.jsp";
                                   //window.location.href="http://129.204.78.107:8080/hotel/jsp/personalInfo.jsp";
                               }else if(i=="checkInStayOver"){
                                   alert("续住成功！！");
                                   $("#rudays").html(c.day);
                                   $("#tui").html(c.outDay);
                               }
                        });
                    },
                    error:function(){
                        alert("失败");
                    }
                });
                xuzhu(0);//点击提交后，续住提示马上消失
                $("#xuzhuday").val(""); //清空
            }
        }
</script>
</body>
</html>