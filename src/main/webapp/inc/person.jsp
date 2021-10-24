<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<link href="http://www.lituanmin.com" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/st.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery-3.3.1.js"></script>
<title>注册新用户</title>
<script>
function zhuce(){
	  /* var userName = document.getElementById("userName").value;
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
    }  */
    return true;
}
//注册成功
function OK(){
	   if('${OK}'=="注册成功"){ 
		   alert("注册成功");
	   }   	  
}
setTimeout('OK()',300);
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/HotelManaServlet?method=adRegister" method="post" onsubmit="return zhuce()">	
  <table class="layui-table" style="text-align:center">
  <colgroup>
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
  </colgroup>
  <tbody>
    <tr>     
      <td>*用户名：</td>
      <td><input type="text" id="userName" name="userName" class="input"></td>
    </tr>
     <tr>     
      <td>*用户类型：</td>
      <td>
              <select name="adName" class="input" id="userName" style="height: 36px;width: 245px;"> 	                 	              	                   	                   
	                     <option value="0">请选择设置权限的用户</option>
	                     <option value="普通管理员">普通管理员</option>
	                     <option value="中级管理员">中级管理员</option>
	                     <option value="超级管理员">超级管理员</option>	                 	                	                   	                                           	                     
	          </select> 
      </td>
    </tr>
     <tr>     
      <td>*密码：</td>
      <td><input type="password" id="password" name="password" class="input"></td>
    </tr>  
     <tr>     
      <td>*确认密码：</td>
      <td><input type="password" id="repassword" name="repassword" class="input"></td>
    </tr>
    <tr>     
      <td>*姓名：</td>
      <td><input type="text" id="name" name="name" class="input"></td>
    </tr>
    <tr>     
      <td>*性别：</td>
      <td><input type="radio" checked="男" value="男" name="sex">男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="女" name="sex">女</td>    
    </tr>
    <tr>     
      <td>*电话：</td>
      <td><input type="text" id="phone" name="phone" class="input"></td>
    </tr>
    <tr>     
      <td>*邮箱：</td>
      <td><input type="text" id="email" name="email" class="input"></td>
    </tr>
     <tr>     
      <td>*身份证：</td>
      <td><input type="text" id="idCard" name="idCard" class="input"></td>
    </tr> 
    <tr>
       <td colspan="2" style="text-align:center"><button type="submit" class="layui-btn layui-btn-normal">提交</button></td>
    </tr>      
  </tbody>
</table>
</form>   
</body>
</html>