<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <style>
        .li{float: left;text-align: center;list-style-type: none;width: 100px;margin-left: 50px;}
        .box {
            color: #999;
        }
        .box .sel {
            color: gold;
        }
    </style>
</head>
<body>

<%@include file="header.jsp" %>
<span style="font-size: 20px;">用户名：</span>
<div id="userName"  style="font-size: 20px;display: inline">${myRoom.userName}</div>
<span style="margin-left:250px;font-size: 20px;">房间类型：</span>
<div id="roomType" style="font-size: 20px;display: inline;">${type}</div>
<span style="margin-left:260px;font-size: 20px;">日期：</span>
<div id="outDay" style="font-size: 20px;display: inline;">${myRoom.outDay}</div><br/>
<span class="box">
    <span style="font-size: 30px;">★</span>
    <span style="font-size: 30px;">★</span>
    <span style="font-size: 30px;">★</span>
    <span style="font-size: 30px;">★</span>
    <span style="font-size: 30px;">★</span>
</span>
<div id="stars" name="stars" style="font-size: 30px; color: gold;display: inline;">0</div><span style="font-size: 25px; color: gold;">星</span>
<div style="font-size: 20px; color: gold;display: inline;"><textarea  id="evaluate" rows="10" cols="30" placeholder="填写您的评价" style="width: 1363px;"></textarea></div>

 <div style="margin-left: 480px">
    <ul>
        <li class="li"> <button onclick="return again()" class="layui-btn layui-btn-normal"><font size="2">重置</font></button></li>
        <li class="li"> <button onclick="yourStars()" class="layui-btn layui-btn-normal"><font size="2">提交</font></button></li>
    </ul>
 </div>



</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/unslider.min.js"></script>
<script type="text/javascript">
    $(function(){
        $(".box span").click(function(event){
            $(this).parent().find("span").addClass("sel");
            $(this).nextAll().removeClass("sel");
            $(this).parent().next().text($(this).index()+1);
            //alert($("#score").text());
            /*if($("#score").text()<2){
                alert("差评");
            }else if($("#score").text()>=2 && $("#score").text()<=4){
                alert("中评");
            }else{
                alert("好评");
            }*/
        });
    });
</script>
<script>
    //看是否选择了星星
    function again(){
        $("#evaluate").val("");
        return false;
    }
    //我的评价
    function yourStars(){
        var stars=$("#stars").text();

        if(stars==0){
            alert("您还没有选择星星！！");
        }else{
            /*var userName = $("#userName").val();
            var roomType = $("#roomType").val();
            var outDay = $("#outDay").val();*/
            var stars = $("#stars").text();
            var evaluate =$("#evaluate").val();
            $.ajax({
                url:"${pageContext.request.contextPath}/find/myStars?userName=${myRoom.userName}&roomType=${type}&outDay=${myRoom.outDay}&stars="+stars+"&evaluate="+evaluate,
                type:"post",
                data:"null",
                success:function(result){
                    //把son字符串或json数组转换为json对象
                    var json = JSON.parse(result);
                    //i是key值，c是value值，既json[i]。比如 json={"tom":"zs"},i=tom,c=zs=json[i]
                    $.each(json,function(i,c){
                        if(json[i]=="我的评价"){
                            //使用了ajax,登陆成功了只能以这样的方式来调整页面
                            window.location.href="http://localhost:8080/hotel/jsp/personalInfo.jsp";
                            //window.location.href="http://129.204.78.107:8080/hotel/jsp/uindex.jsp";
                        }
                    });
                },
                error:function(){
                    alert("失败");
                }
            });
        }
    }
</script>
</html>