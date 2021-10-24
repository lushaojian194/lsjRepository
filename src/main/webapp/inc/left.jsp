<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左部</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
</head>
<body id="bg">
	<!-- 左边节点 -->
	<div class="container">
		<div class="leftsidebar_box">
			<a href="${pageContext.request.contextPath}/inc/main.jsp" target="main"><div class="line">
					<img src="${pageContext.request.contextPath}/img/coin01.png" />&nbsp;&nbsp;首页
			</div></a>
			<c:if test="${fn:contains(peopleCode,'a')}">		
			 <dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/HotelManaServlet?method=hotelRegister" target="main">登记预收报表</a>
				</dt>			 
			 </dl>
			</c:if>
			<c:if test="${fn:contains(peopleCode,'b')}">	
			<dl class="system_log">
				<dt>                                                                      
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/HotelManaServlet?method=hotelSell" target="main">客房销售报表</a>
				</dt>		
			</dl>
			</c:if>
			<c:if test="${fn:contains(peopleCode,'c')}"> 
			<dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/HotelManaServlet?method=roomStatic" target="main">客房销售统计</a>
				</dt>		
			</dl>
			</c:if>
			<c:if test="${fn:contains(peopleCode,'g')}">
			<dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/HotelManaServlet?method=findCheckOut" target="main">退房信息</a>
				</dt>		
			</dl>
			</c:if>		
			<c:if test="${fn:contains(peopleCode,'d')}">	
			<dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/inc/person.jsp" target="main">操作员设置</a>
				</dt>		
			</dl>
			</c:if>
			<c:if test="${fn:contains(peopleCode,'e')}">	
			<dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/inc/password.jsp" target="main">密码设置</a>
				</dt>		
			</dl>
			</c:if>
			<c:if test="${fn:contains(peopleCode,'f')}">
			<dl class="system_log">
				<dt>
					<img class="icon1" src="${pageContext.request.contextPath}/img/coin03.png" /><img class="icon2"
						src="${pageContext.request.contextPath}/img/coin04.png" /><a href="${pageContext.request.contextPath}/HotelManaServlet?method=authorityPeople" target="main">权限设置</a>
				</dt>		
			</dl>
			</c:if>
		</div>

	</div>
</body>>
</html>