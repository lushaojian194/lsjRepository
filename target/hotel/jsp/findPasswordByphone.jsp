<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<style>
	 div{
	      padding:0px;
	      magrin:0px;
	 }
</style>
    <script>
        //利用手机号找回密码和用户名
        function findPasswordAndUserNameByPhone() {
            var sign = true;
            var phone = $("#phone").val();
            //电话验证
            var ptest = /^1(3|4|5|7|8)\d{9}$/;
            if(phone==""){
                alert("电话号码不能为空！");
                sign = false;
            }
            if(phone!="" && (!ptest.test(phone))){
                alert("电话号码格式不正确！！");
                sign = false;
            }

        //只有验证通过了,sign为true时，才能执行ajax进行修改操作
        if(sign==true){
            $.ajax({
                url:"${pageContext.request.contextPath}/user/findPasswordByPhone?phone="+phone,
                type:"post",
                data:"null",
                success:function(result){
                    //result是json的字符串或json数组，把json字符串转换为json对象
                    var json = JSON.parse(result);
                    //使用JavaScript或jQuery等实现局部更新
                    $.each(json,function(i,c){
                        if(i=="wrongPhone"){
                            $("#phone").val("");//号码错误就清空，让用户重新输入
                            alert(json[i]);
                        }else if(i=="success"){
                            alert(json[i]);
                            window.location.href="http://localhost:8080/hotel/jsp/login.jsp";
                            //window.location.href="http://129.204.78.107:8080/hotel/jsp/login.jsp";
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

    <script>
         function findPasswordAndUserNameByEmail() {
             var emialSign = true;
             //邮箱验证
             var email = $("#email").val();
             if(email==""){
                 alert("邮箱不能为空！");
                 emialSign = false;
             }
             var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
             if (email!=""&&(!reg.test(email))) {
                 alert("邮箱格式不正确");
                 emialSign = false;
             }

             //只有验证通过了,emialSign为true，才能执行ajax进行修改操作
             if(emialSign==true){
                 $.ajax({
                     url:"${pageContext.request.contextPath}/user/findPasswordByEmail?email="+email,
                     type:"post",
                     data:"null",
                     success:function(result){
                         //result是json的字符串或json数组，把json字符串转换为json对象
                         var json = JSON.parse(result);
                         //使用JavaScript或jQuery等实现局部更新
                         $.each(json,function(i,c){
                             if(i=="wrongEmail"){
                                 $("#email").val("");  //邮件错误就清空，让用户重新输入
                                 alert(json[i])
                             }else if(i=="success"){
                                 alert(json[i]);
                                 window.location.href="http://localhost:8080/hotel/jsp/login.jsp";
                                 //window.location.href="http://129.204.78.107:8080/hotel/jsp/login.jsp";
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

<!-- 引入头部 -->
<%@include file="header.jsp" %>
<div style="width: 500px;height: 400px; margin-left: 160px;margin-top: 80px;float:left">
<%--<form action="${pageContext.request.contextPath}/UserServlet?method=findPasswordByPhone" method="post">	--%>
  <table class="layui-table">
  <colgroup>
    <col width="250"> 
    <col width="250">
    <col width="100"> 
  </colgroup>
  <tbody>
  	<tr>
  		<td style="text-align: center">请输入电话号码</td>
  		<td>
            <input type="text"  class="form-control" id="phone" name="phone" placeholder="请输入电话">
  		</td>
  	</tr>
    <tr>
      <td colspan="2" style="text-align: center;"><button onclick="findPasswordAndUserNameByPhone()" class="layui-btn layui-btn-normal"><font size="4">找回密码</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>  --%>
</div>

<div style="width: 500px;height: 400px; margin-left: 20px;margin-top: 80px;float:left">
<%--<form action="${pageContext.request.contextPath}/UserServlet?method=findPasswordByEmail" method="post">	--%>
  <table class="layui-table"> 
  <colgroup> 
    <col width="250"> 
    <col width="250">
    <col width="100">  
  </colgroup>
  <tbody>
  	<tr>
  		<td style="text-align: center">请输入电子邮箱</td>
  		<td>
            <input type="text"  id="email" class="form-control" name="email" placeholder="请输入电子邮箱">      
  		</td>
  	</tr>
    <tr>
      <td colspan="2" style="text-align: center;"><button onclick="findPasswordAndUserNameByEmail()" class="layui-btn layui-btn-normal"><font size="4">找回密码</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>  --%>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script>
//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
</script>
</body>
</html>