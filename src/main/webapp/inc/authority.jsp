<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限设置</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
<script src="${pageContext.request.contextPath}/js/layui.js"></script>
<link href="http://www.lituanmin.com" rel="icon" type="image/x-icon" />
<link href="${pageContext.request.contextPath}/css/st.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery-3.3.1.js"></script>
<script>
      function we(){
    	  document.getElementById("ha").submit();
      }
</script>
</head>
<body>	
<form id="ha" action="${pageContext.request.contextPath}/HotelManaServlet?method=authorityShow" method="post">
<table class="layui-table">
  <colgroup>
    <col width="200">
  </colgroup>
  <tbody>
        <tr>
        <td colspan="2" style="text-align:center">                
                 <select name="userName" class="input" id="userName" onchange="we()" style="height: 36px;width: 245px;"> 	                 
	                <c:choose>
	                   <c:when test="${not empty userName}">
	                       <option value="${userName }">${userName }</option> 
	                   </c:when>
	                   <c:otherwise> 
	                       <option value="0">请选择设置权限的用户</option>
	                   </c:otherwise>
	                </c:choose>   	                   
	                   <c:forEach items="${listPeople}" var="r">   
	                     <option value="${r }">${r }</option> 
	                   </c:forEach>                        	                     
	             </select> 	 
        </td>
    </tr>
  </tbody>
  </table>
</form>		
<form action="${pageContext.request.contextPath}/HotelManaServlet?method=authoritySet&userName=${userName}" method="post">	
  <table class="layui-table">
  <colgroup>
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
    <col width="200">
  </colgroup>
  <tbody>
    <tr>
     <c:choose>
        <c:when test="${fn:contains(peopleCode,'a')}">      
              <td><input type="checkbox"  checked="checked" name="selectoper" value="a" ><font size="4">登记预收报表</font></td>
        </c:when>
        <c:otherwise> 
                <td><input type="checkbox"  name="selectoper" value="a" ><font size="4">登记预收报表</font></td>
        </c:otherwise>     
     </c:choose>
     
     <c:choose> 
          <c:when test="${fn:contains(peopleCode,'b')}">  
                <td><input type="checkbox"  checked="checked" name="selectoper" value="b" ><font size="4">客房销售报表</font></td>
          </c:when>
          <c:otherwise> 
                <td><input type="checkbox"  name="selectoper" value="b" ><font size="4">客房销售报表</font></td>
          </c:otherwise>
     </c:choose>
    </tr>
    
     <tr> 
     <c:choose> 
          <c:when test="${fn:contains(peopleCode,'c')}">     
                <td><input type="checkbox"  checked="checked" name="selectoper" value="c" ><font size="4">客房销售统计</font></td>
          </c:when>
          <c:otherwise>
                <td><input type="checkbox"  name="selectoper" value="c" ><font size="4">客房销售统计</font></td>
          </c:otherwise>
     </c:choose>    
     <c:choose>
         <c:when test="${fn:contains(peopleCode,'d')}">           
               <td><input type="checkbox" checked="checked" name="selectoper" value="d" ><font size="4">操作员设置</font></td>
         </c:when>
         <c:otherwise> 
               <td><input type="checkbox"  name="selectoper" value="d" ><font size="4">操作员设置</font></td>
         </c:otherwise> 
    </c:choose>    
    </tr>  
    
     <tr>
     <c:choose> 
         <c:when test="${fn:contains(peopleCode,'e')}">      
              <td><input type="checkbox" checked="checked" name="selectoper" value="e" ><font size="4">密码设置</font></td>
         </c:when>
         <c:otherwise>
              <td><input type="checkbox"  name="selectoper" value="e" ><font size="4">密码设置</font></td>
         </c:otherwise>
     </c:choose>
     <c:choose>
         <c:when test="${fn:contains(peopleCode,'f')}">
              <td><input type="checkbox" checked="checked" name="selectoper" value="f" ><font size="4">权限设置</font></td>
         </c:when>
         <c:otherwise>
              <td><input type="checkbox"  name="selectoper" value="f" ><font size="4">权限设置</font></td>
         </c:otherwise>
     </c:choose>
    </tr>
    <tr>
       <c:choose>
            <c:when test="${fn:contains(peopleCode,'g')}">
                <td colspan="2" style="text-align:left"><input type="checkbox"  checked="checked" name="selectoper" value="g"><font size="4">退房信息</font></td>
            </c:when>
            <c:otherwise>
                <td colspan="2" style="text-align:left"><input type="checkbox"  name="selectoper" value="g" ><font size="4">退房信息</font></td>
            </c:otherwise>
       </c:choose>
    </tr> 
    <tr>
       <td colspan="2" style="text-align:center"><button type="submit" class="layui-btn layui-btn-normal">提交</button></td>
    </tr>    
  </tbody>
  </table>
</form>       	 
</body>
</html>