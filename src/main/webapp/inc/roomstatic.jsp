<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<link href="http://www.lituanmin.com" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/st.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.3.1.js"></script>
</head>
<body <%-- background="${pageContext.request.contextPath}/img/lgbg.png" --%>>
<div style="width: 1000px;height: 350px; margin-left: 50px;margin-top:0px">
<form action="${pageContext.request.contextPath}/UserServlet?method=InfoEdit&userName=${user.userName}" method="post" onsubmit="return tijiao()">	
  <table class="layui-table">
  <colgroup>
    <col width="50">
    <col width="50">
    <col width="50">
    <col width="50">
  </colgroup>
  <thead>
    <tr>
      <th>房间类型</th>
      <th>房价</th>
      <th>天数</th>
      <th>总额</th>
    </tr> 
  </thead>
  <tbody>
    <tr>     
      <td><div>单人房</div></td>
      <td><div>900</div></td>
      <td><div>${singalDays }</div></td>
      <td><div>${sigalMoney }</div></td>
    </tr>
     <tr>     
      <td><div>双人房</div></td>
      <td><div>1200</div></td>
      <td><div>${doubleDays }</div></td>
      <td><div>${doubleMoney }</div></td>
    </tr>
     <tr>     
      <td><div>情侣房</div></td>
      <td><div>1500</div></td>
      <td><div>${loveDays }</div></td>
      <td><div>${loveMoney }</div></td>
    </tr>
     <tr>     
      <td><div>豪华房</div></td>
      <td><div>2000</div></td>
      <td><div>${beautyDays }</div></td>
      <td><div>${beautyMoney }</div></td>
    </tr>    
  </tbody>
  </table>
</form>
</div>  
</body>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form;
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
</html>