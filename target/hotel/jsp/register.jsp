<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css"> 
	<title>注册新用户</title>
<script>
function verify(){
	 var userName = document.getElementById("userName").value;
	  var password = document.getElementById("password").value;
	  var repassword = document.getElementById("repassword").value;
	  var name = document.getElementById("name").value;
	  var phone = document.getElementById("phone").value;
	  var email = document.getElementById("email").value;
	  var idCard = document.getElementById("idCard").value;
	  //用户名验证
	  if(userName==""){
	  	alert("用户名不能为空！");
	  	return false;
	  }
	   //密码验证
	   if(password==""){
	  	alert("密码不能为空！");
	  	return false;
	  }	  	  
	   if(password.length<6){
	   	alert("密码的长度不能少于6位");
	  	return false;
	   }
	  if(password!=repassword){
	  	alert("两次密码不一致，请重新输入");
	  	return false;
	  }
	  //姓名验证
	   if(name==""){
	  	alert("姓名不能为空！");
	  	return false;
	  }
	    //电话验证
	   var ptest = /^1(3|4|5|7|8)\d{9}$/;
	  if(phone==""){
	  	alert("电话号码不能为空！");
	  	return false;
	  }
	   if(!ptest.test(phone)){
	  	alert("电话号码格式不正确！！");
	  	return false;
	  }
	 //邮箱验证  	   
	 if(email==""){
	  	alert("邮箱不能为空！"); 
	  	return false;
	  }
	 var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/; 
       if (!reg.test(email)) {
         alert("邮箱格式不正确");
         return false;
    }
    if(idCard==""){
    	alert("身份证不能为空！");
    	return false;
    }
    return true;
}
</script>
</head>
<body background="${pageContext.request.contextPath}/img/sea.jpg"> 

<%@include file="header.jsp" %>

 <div style="width: 500px;height: 400px; margin-left: 420px;"> 
 <form action="${pageContext.request.contextPath}/user/Register" method="post" onsubmit="return verify()">
  <table class="layui-table">
  <colgroup>
    <col width="250">
    <col width="250">
    <col width="100">
  </colgroup>
  <tbody>
  	<tr><td colspan="2"><div style="text-align: center;font-weight: bold;font-size: large;">注册新用户</div></td></tr>
    <tr>
      <td>*用户名</td>
      <td><input type="text" id="userName" name="userName" class="form-control"></td>
    </tr>
    <tr>
      <td>*密码</td>
      <td><input type="password" id="password" name="passwordd" class="form-control"></td>
    </tr>
    <tr>
      <td>*确认密码</td>
      <td><input type="password" id="repassword" name="repassword" class="form-control"></td>    
    </tr>
    <tr>
      <td>*姓名</td>
      <td><input type="text" id="name" name="namee" class="form-control"></td>
    </tr>
    <tr>
      <td>*性别</td>
      <td><input type="radio" checked="男" value="男" name="sex">男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="女" name="sex">女</td>    
    </tr>
    <tr>
      <td>*电话</td>
      <td><input type="text" id="phone" name="phone" class="form-control"></td>    
    </tr>
    <tr>
      <td>*邮箱</td>
      <td><input type="text" id="email" name="email" class="form-control"></td>    
    </tr>
    <tr>
      <td>*身份证</td> 
      <td><input type="text" id="idCard" name="idCard" class="form-control"></td>     
    </tr>
    <tr><td colspan="2"><div style="text-align: center;font-weight: bold;font-size: large;"><button type="submit" class="layui-btn layui-btn-normal">注册</button></div></td></tr>
  </tbody>
  </table>
</form>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/jscript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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