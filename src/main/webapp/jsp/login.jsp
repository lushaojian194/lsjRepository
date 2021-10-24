<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<script>
       //找回密码弹窗
       function remind(){
    	   alert("找回密码成功，请登录");
       }    
       if("${pass}"=="找回密码提醒"){
    	  setTimeout('remind()',500);
       }
</script>
    <!--AJAX方法 ,不用加载整个界面，对界面进行局部的部刷新-->
    <script type="text/javascript">
        function isLogin(){
            var loginName = $("#loginName").val();
            var password = $("#password").val();
            var code = $("#code").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/Login?loginName="+loginName+"&password="+password+"&code="+code,
                type:"post",
                data:"null",
                success:function(result){
                    //把son字符串或json数组转换为json对象
                    var json = JSON.parse(result);
                    //i是key值，c是value值，既json[i]。比如 json={"tom":"zs"},i=tom,c=zs=json[i]
                    $.each(json,function(i,c){
                      if(json[i]=="登陆成功"){
                          //使用了ajax,登陆成功了只能以这样的方式来调整页面
                          window.location.href="http://localhost:8080/hotel/jsp/uindex.jsp";
                         //window.location.href="http://129.204.78.107:8080/hotel/jsp/uindex.jsp";
                      }else{
                          if(i=="wrongPassword"){
                              //如果是账号或密码错误，则清空账号、密码,验证码不清空
                              $("#loginName").val("");
                              $("#password").val("");
                             // $("#code").val(" ");
                          }else if(i=="wrongCode"){
                              //如果只是验证码错误，清空验证码，用户名和密码不清空
                              $("#code").val("");
                          }
                          //输出弹窗提示，比如：账号或密码错误 或 验证码错误，请重新输入
                          alert(c);
                      }
                    });
                },
                error:function(){
                     alert("失败");
                     //window.location.href="http://localhost:8080/hotel/jsp/uindex.jsp";
                }
            });
        }
    </script>
</head>
<body>
<%@include file="header.jsp" %>
     <div class="main">
    	<div class="mainin">       	
            <div class="mainin1"> 
             <%-- <form action="${pageContext.request.contextPath}/user/Login" method="post">--%>
            	 <ul>
                	<div style="margin-bottom:40px"><span>用户名：</span><input name="loginName" type="text" id="loginName" class="SearchKeyword2" placeholder="用户名" ></div>
                    <span>密码：</span><input type="password" name="password" id="password" class="SearchKeyword2" placeholder="密码">
                    <li>
                        <span>请输入验证吗：</span><input type="text" name="code" id="code" style="width: 120px;" />&nbsp;&nbsp;
                        <img id="imgObj" alt="验证码" src="${pageContext.request.contextPath}/getCode">
                        &nbsp;&nbsp;<a href="#" onclick="changeImg()">换一张</a>
                    </li>
                     <li>
                         <a href="${pageContext.request.contextPath}/jsp/findPasswordByphone.jsp"><font size="4">忘记密码</font></a>
                         <a href="javascript:void(0);" onclick="isLogin()"><button class="tijiao">登陆</button></a>
                     </li>
               </ul>
             <%-- </form>   --%>
            </div>            
        </div>
    </div>
<img src="img/loading.gif" id="loading" style=" display:none;position:absolute;" />
<div id="POPLoading" class="cssPOPLoading">
    <div style=" height:110px; border-bottom:1px solid #9a9a9a">
        <div class="showMessge"></div>
    </div>
    <div style=" line-height:40px; font-size:14px; letter-spacing:1px;">
        <a onclick="puc()">确定</a>
    </div>
</div>
</body>
<!-- 换一张验证码 -->
<script type="text/javascript">
     $(function() {
 
     });
 
    function changeImg() {        
         var imgSrc = $("#imgObj");    
         var src = imgSrc.attr("src");        
         imgSrc.attr("src", chgUrl(src));
     }
     
     // 时间戳
     // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
     function chgUrl(url) {
         var timestamp = (new Date()).valueOf();
         url = url.substring(0, 20);
         if ((url.indexOf("&") >= 0)) {
             url = url + "×tamp=" + timestamp;
         } else {
             url = url + "?timestamp=" + timestamp;
         }
         return url;
     }
 
</script>
</html>