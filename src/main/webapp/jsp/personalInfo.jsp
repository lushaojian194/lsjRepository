<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<style>
     .center{text-align: center;}
	 .li{float: left;text-align: center;list-style-type: none;width: 100px;margin-left: 50px;}
	 .box{display: none;width: 400px;height: 100px;margin-left:470px;margin-top: 0px;}
</style>
<script>
        //修改密码
        function xiu(n){
        	document.getElementById("xiu").style.display=n?'block':'none';
        }
        //修改密码
        function modifyPassword() {
            //标记开始为true,如果不满足则改为false,就不进行操作
            var passSign = true;
            //旧密码
            var oldPass = $("#oldPass").val();
            //修改的密码和确认密码
            var newPass = $("#newPass").val();
            var renewPass = $("#renewPass").val();
           if(oldPass==""){
             passSign = false;
             alert("旧密码不能为空");
           }else if('${user.passwordd}'!=oldPass){
             passSign = false;
             $("#oldPass").val(""); //旧密码错误，清空
             alert("旧密码不正确，请重新输入");
           }else if(newPass==""){
              passSign = false;
              alert("新密码不能空");
            }else if(newPass.length<6){
              passSign = false;
              $("#newPass").val("");
              $("#renewPass").val("");
              alert("密码的长度不能少于6位,请重新输入");
            }else if(newPass != renewPass){
              passSign = false;
              $("#newPass").val("");
              $("#renewPass").val("");
              alert("两次密码不一致，请重新输入");
            }else if('${user.passwordd}'==newPass){
              alert("新密码不能与旧密码相同，请重新输入");
              passSign = false;
              $("#newPass").val("");
              $("#renewPass").val("");
            }

              //只有验证通过了,passSign为true时，才能执行ajax进行修改密码操作
              if(passSign){
                xiu(0);
                $.ajax({
                  url:"${pageContext.request.contextPath}/user/modifyPassword?userName=${user.userName}"+"&newPassword="+newPass,
                  type:"post",
                  data:"null",
                  success:function(result){
                    //result是json的字符串或json数组，把json字符串转换为json对象
                    var json = JSON.parse(result);
                    //使用JavaScript或jQuery等实现局部更新
                    $.each(json,function(i,c){
                         if(i=="modifySuccess"){
                            alert(c);
                         }
                    });
                  },
                  error:function(){
                    alert("失败");
                  }
                });
              }
        }

        //编辑
        function edit(){
          $("#phone").html("<input type='text' name='newPhone' id='newPhone' class='form-control' placeholder='请输入电话号码'>");
          $("#email").html("<input type='text' name='newEmail' id='newEmail' class='form-control' placeholder='请输入邮箱'>");
        }

        //取消
        function cancel(){
          $.ajax({
            url:"${pageContext.request.contextPath}/user/cancelModifyPhoneAndEmail?userName=${user.userName}",
            type:"post",
            data:"null",
            success:function(result){
              //把son字符串或json数组转换为json对象
              var json = JSON.parse(result);
              //i是key值，c是value值，既json[i]。比如 json={"tom":"zs"},i=tom,c=zs=json[i]
              $.each(json,function(i,c){
                if(i == "cancelPhone") {
                  $("#phone").html("");
                  $("#phone").html(json[i]);
                }else if(i == "cancelEmail"){
                  $("#email").html("");
                  $("#email").html(json[i]);
                }
              });
            },
            error:function(){
              alert("失败");
            }
          });
        }

        //修改手机号和密码
        function modifyPhoneAndEmail(){
          //sign作为标记，如果条件不满足，则为false,就不执行提交操作
          var sign = true;
          var newPhone = $("#newPhone").val();
          var newEmail = $("#newEmail").val();
          var ptest = /^1(3|4|5|7|8)\d{9}$/;
          var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
          //没编辑，不能直接提交,执行else
          if(typeof newPhone == "undefined"){
            sign = false;
          }else{
            if(newPhone==""||newEmail==""){
              sign = false;
              alert("电话或邮箱不能为空！");
            }else if(!ptest.test(newPhone)){
              sign = false;
              alert("电话的格式不正确");
            }else if(!reg.test(newEmail)){
              sign = false;
              alert("邮箱的格式不正确");
            }
          }

          //只有验证通过了,sign为true时，才能执行ajax进行修改操作
          if(sign==true){
            $.ajax({
              url:"${pageContext.request.contextPath}/user/modifyPhoneAndEmail?userName=${user.userName}"+"&newPhone="+newPhone+"&newEmail="+newEmail,
              type:"post",
              data:"null",
              success:function(result){
                //result是json的字符串或json数组，把json字符串转换为json对象
                var json = JSON.parse(result);
                //使用JavaScript或jQuery等实现局部更新
                $.each(json,function(i,c){
                    if(i=="newPhone"){
                       $("#phone").html("");
                       $("#phone").html(json[i]);
                    }else if(i=="newEmail"){
                      $("#email").html("");
                      $("#email").html(json[i]);
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
<body  background="${pageContext.request.contextPath}/img/sea.jpg" >

<%@include file="header.jsp" %>

<div style="width: 500px;height: 350px; margin-left: 420px;">
<%--<form action="${pageContext.request.contextPath}/UserServlet?method=InfoEdit&userName=${user.userName}" method="post" onsubmit="return tijiao()">--%>
  <table class="layui-table">
  <colgroup>
    <col width="250">
    <col width="250">
    <col width="100">
  </colgroup>
  <tbody class="center">
  <c:if test="${not empty user}">
    <tr>
      <td>用户名</td>
      <td><div>${user.userName }</div></td>
    </tr>
    <tr>
      <td>姓名</td>
      <td>${user.namee }</td>
    </tr>
    <tr>
      <td>电话</td>
      <td><div id="phone">${user.phone }</div></td>
    </tr>
    <tr>
      <td>邮箱</td>
      <td><div id="email">${user.email }</div></td>
    </tr>
  </c:if>  
    <tr>
      <td>余额</td>
      <td>${balance}</td>
    </tr>
 
    <tr>
      <td><button onclick="modifyPhoneAndEmail()" class="layui-btn layui-btn-normal"><font size="2">提交</font></button></td>
      <td><button onclick="cancel()" class="layui-btn layui-btn-normal"><font size="2">取消</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>  --%>
   <ul>        
        <li class="li"> <button onclick="edit()" class="layui-btn layui-btn-normal"><font size="2">编辑</font></button></li>
        <li class="li"> <button onclick="xiu(1)" class="layui-btn layui-btn-normal"><font size="2">修改密码</font></button></li>
        <li class="li"> <a href="${pageContext.request.contextPath}/jsp/index.jsp"><button class="layui-btn layui-btn-normal"><font size="2">充值</font></button></a></li>
   </ul>
</div>
<div id="xiu" class="box">
	<div style="float: right;"><a onclick="xiu(0)"><font size="3" style="color:white;font-weight: bold;">关闭</font></a></div>
<%--<form action="${pageContext.request.contextPath}/UserServlet?method=passwordModify&userName=${user.userName}" method="post" onsubmit="return passwordIncom()">	--%>
  <table class="layui-table">
  <colgroup>
    <col width="250">
    <col width="250">
    <col width="250">
    <col width="100">
  </colgroup>
  <tbody>
    <tr>
      <td style="text-align: center">旧密码：</td>
      <td><input type="password" id="oldPass" name="oldPassword" class="form-control" placeholder="请输入旧密码"></td>
    </tr>
    <tr>
      <td style="text-align: center">新密码：</td>
      <td><input type="password" id="newPass" name="newPassword" class="form-control" placeholder="请输入新密码"></td>
    </tr>
    <tr>
      <td style="text-align: center">再次确认新密码：</td>
      <td><input type="password" id="renewPass" name="renewPassword" class="form-control" placeholder="再次确认新密码"></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align: center;"><button onclick="modifyPassword()" class="layui-btn layui-btn-normal"><font size="2">提交</font></button></td>
    </tr>
  </tbody>
  </table>
<%--</form>  --%>
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
</script>
</body>
</html>