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

</head>
<body background="${pageContext.request.contextPath}/img/sea.jpg" >
<%@include file="header.jsp" %>
    <article class="page-container">
    </article>
    <form id="form" action="${pageContext.request.contextPath}/UserServlet?method=InfoEdit&userName=${user.userName}" method="post" onsubmit="return tijiao()">
        <div style="width: 80%;margin-left: 140px;">
        <table class="layui-table">
            <colgroup>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
            <tr>
                <th style="text-align: center;">用户名</th>
                <th style="text-align: center;">房间号</th>
                <th style="text-align: center;">房间类型</th>
                <th style="text-align: center;">房价</th>
                <th style="text-align: center;">入住时间</th>
                <th style="text-align: center;">入住天数</th>
                <th style="text-align: center;">退房时间</th>
                <th style="text-align: center;">提前退房</th>
                <th style="text-align: center;">消费金额</th>
            </tr>
            </thead>
            <tbody>
               <c:forEach items="${page.list}" var="record">
                    <tr style="text-align: center;">
                        <td>${record.userName}</td>
                        <td>${record.location}</td>
                        <td>${record.roomType}</td>
                        <td>${record.price}</td>
                        <td>${record.checkDay}</td>
                        <td>${record.day}</td>
                        <td>${record.outDay}</td>
                        <td>${record.sign}</td>
                        <td>${record.totalMoney}</td>
                    </tr>
               </c:forEach>
            </tbody>
        </table>
        </div>
        <%--<div id="middile"><%@include file="pageFile.jsp" %></div>--%>
        <%@include file="pageFile.jsp" %>
    </form>



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