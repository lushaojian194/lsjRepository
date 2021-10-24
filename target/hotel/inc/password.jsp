<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<link href="http://www.lituanmin.com" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/st.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery-3.3.1.js"></script>
<script>

function verify(){
	  var password = document.getElementById("newPassword").value;
	  var repassword = document.getElementById("reNewPassword").value;
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
    return true;
}
//修改密码成功
function notEnoughMoney(){
	   if('${succeed}'=="修改成功"){ 
		   alert("修改密码成功");
	   }   	  
}
setTimeout('notEnoughMoney()',300);

</script>
</head>
<body>     
<form action="${pageContext.request.contextPath}/HotelManaServlet?method=updatePassword" method="post" onsubmit="return verify()">	
  <table class="layui-table" style="text-align:center">
  <colgroup>
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
  </colgroup>
  <tbody>
    <tr>     
      <td>输入用户名：</td>
      <td><input type="text" id="userName" name="userName" class="input"></td>
    </tr>
     <tr>     
      <td>输入新密码：</td>
      <td><input type="password" id="newPassword" name="newPassword" class="input"></td>
    </tr>  
     <tr>     
      <td>确认新密码：</td>
      <td><input type="password" id="reNewPassword" name="reNewPassword" class="input"></td>
    </tr>
    <tr>
       <td colspan="2" style="text-align:center"><button type="submit" class="layui-btn layui-btn-normal">提交</button></td>
    </tr>    
  </tbody>
  </table>
</form>       	 
</body>
</html>