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
<script type="text/javascript">
                  $(function(){
                	  //出发ajax执行
                	  $.post("HotelManaServlet",{method:"hotelSell"},function(msg){
                		 //console.log(msg);//调试
                		 //msg是json的字符串或json数组，把json字符串转换为json对象   
                		 var jsonObj = JSON.parse(msg);
                		 //遍历集合对象
                		 $.each(jsonObj,function(i,c){  
                	          //获取ul对象
                	          /* var li = "<li><a href='ProductServlet?method=findProductByCidWithPage&num=1&cid="+c.cid+"'>"+c.cname+"</a></li>";
                	          $("#myUL").append(li); */
                	          var aa = "<td><div>"+${c.checkin.userName}+"</div></td>";
                	          $("#we").html(aa); 
                		 });
                	  });
                  })              	                
        </script>
</head>
<body <%-- background="${pageContext.request.contextPath}/img/lgbg.png" --%>>
<div style="width: 1000px;height: 350px; margin-left: 50px;margin-top:0px">
    <article class="page-container">
		<form action="${pageContext.request.contextPath}/HotelManaServlet?method=findHotelSell" method="post">
			<div style="display: inline">
	                <select name="year" class="input" id="productName" style="height: 36px;width: 245px;"> 
	                     <!--  <input class="input" type="text" name="productName"/> -->
	                     <option value="0">请选择年份</option>
	                     <option value="2017">2017</option>
	                     <option value="2018">2018</option> 
	                     <option value="2019">2019</option>  	                       	                     
	                </select> 	           
	       </div>
	       <div style="display: inline">			    	               	             
	                <select name="month" class="input" id="productName" style="height: 36px;width: 245px;"> 
	                     <!--  <input class="input" type="text" name="productName"/> -->
	                     <option value="0">请选择月份</option>
	                     <option value="1">1</option>
	                     <option value="2">2</option> 
	                     <option value="3">3</option>  
	                     <option value="4">4</option>
	                     <option value="5">5</option>
	                     <option value="6">6</option> 
	                     <option value="7">7</option>  
	                     <option value="8">8</option>
	                     <option value="9">9</option>
	                     <option value="10">10</option> 
	                     <option value="11">11</option>  
	                     <option value="12">12</option>  	                     
	                </select> 	           
	       </div>
	       <button type="submit" class="layui-btn layui-btn-normal"><font size="2">查询</font></button>   				
        </form>
	</article>
<form action="${pageContext.request.contextPath}/UserServlet?method=InfoEdit&userName=${user.userName}" method="post" onsubmit="return tijiao()">	
  <table class="layui-table">
  <colgroup>
    <col width="50">
    <col width="50">
    <col width="50">
    <col width="50">
    <col width="50">
    <col width="50">
  </colgroup>
  <thead>
    <tr>
      <th>用户名</th>
      <th>房间号</th>
      <th>房间类型</th>
      <th>房价</th>
      <th>实际天数</th>
      <th>实际费用</th>
    </tr> 
  </thead>
  <tbody>
    <c:forEach items="${hotelList}" var="t"> 
    <tr>     
      <td><div>${t.checkin.userName}</div></td>
      <td><div>${t.checkin.location}</div></td>
      <td><div>${t.room.roomType}</div></td>
      <td><div>${t.room.price}</div></td>
      <td><div>${t.realDays}</div></td>
      <td><div>${t.totalMoney}</div></td> 
    </tr>  
    </c:forEach>     
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