
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script><!--引入jQuery库-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>充值</title>

    <script type="text/javascript">
        //jquery 自动执行
        $(function(){
            $.ajax({
                url:"${pageContext.request.contextPath}/user/recharge?userName=${user.userName}",
                type:"post",
                data:"null",
                success:function(result){
                    //result是json的字符串或json数组，把json字符串或json数组转换为json对象
                    var jsonObj = JSON.parse(result);
                    $.each(jsonObj,function(i,c){
                        if(jsonObj[i]=="充值成功"){
                            //使用了ajax,登陆成功了只能以这样的方式来调整页面
                            window.location.href="http://localhost:8080/hotel/jsp/personalInfo.jsp";
                            //window.location.href="http://129.204.78.107:8080/hotel/jsp/personalInfo.jsp";
                        }
                    });
                },
                error:function(){
                    alert("失败");
                }
            });
        })
    </script>
</head>
<body>

</body>
</html>
