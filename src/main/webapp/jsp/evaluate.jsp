<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>住房记录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/layui.js"></script>
    <link href="http://www.lituanmin.com" rel="icon" type="image/x-icon" />
    <link href="${pageContext.request.contextPath}/css/st.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-3.3.1.js"></script>
    <style>
        #form{
            margin-left: 0px;
        }
    </style>
     <style type="text/css">
         .box {
             color: #999;
         }
         .box .sel {
             color: gold;
         }
     </style>

</head>
<body <%--background="${pageContext.request.contextPath}/img/sea.jpg"--%> >
<%@include file="header.jsp" %>

<c:forEach items="${stars}" var="record">

<%--<c:forEach var="i" begin="1" end="5">--%>
    <div style="margin-top: 40px">
        <span style="font-size: 20px;">用户名：</span>
        <span style="font-size: 20px;">${record.namee}</span>
        <span style="margin-left:250px;font-size: 20px;">房间类型：<span>${record.roomType}</span></span>
        <span style="margin-left:260px;font-size: 20px;">日期：<span>${record.outDay}</span></span><br/>
       <c:forEach var="i" begin="1" end="${record.starLight}">
           <span style="font-size: 30px;color: gold">★</span>
       </c:forEach>
       <c:forEach var="i" begin="1" end="${record.starDark}">
           <span style="font-size: 30px;color:#999">★</span>
      </c:forEach>
     <span id="score" style="font-size: 30px; color: gold;">${record.starLight}</span><span style="font-size: 25px; color: gold;">星</span>
     <div style="font-size: 20px; color: black;">${record.evaluate}</div>
    </div>
 </c:forEach>
</body>

</html>