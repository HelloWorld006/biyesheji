<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>加入我们</title>
    <link rel="stylesheet" href='<c:url value="/static/css/add.css" ></c:url> '>
</head>
<body>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    body {
        background: url(/static/img/61.jpg) no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }




</style>
<%--<!--背景-->--%>
<%--<div class="wel" id="background-3" >--%>
<%--<img src='<c:url value="/01-register/60.jpg" ></c:url> '>--%>
<%--</div>--%>
<%--<!--左右两边云-->--%>
<%--<div class="wel" id="box">--%>
<%--<div class="box-1 lefp"></div>--%>
<%--<div class="box-1">--%>
<%--<div class="righp"></div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!--荧光点点-->--%>
<%--<div class="wel" id="git"></div>--%>
<!--登录注册表-->
<div class="wel" id="from">
    <div class="box-2 le-1">
        <form id="iform" action="/front/caller.do" enctype="multipart/form-data" method="post">

            <div class="flrg">
                <h3>预约</h3>
                <div class="a">
                    <input type="text" class="in-1" name="visitorName" placeholder="请输入姓名">
                </div>
                <div class="a">
                    <input type="number" class="in-1" name="tel" placeholder="请输入手机号">
                </div>

                <div class="a">
                    <input type="radio" name="sex" value="女">女
                    <input type="radio" name="sex" value="男">男
                </div>

                <div class="a">
                    <select class="in-1" name="intent" id="intent">
                        <option value="Web前端教程">Web前端教程</option>
                        <option value="UI设计教程">UI设计教程</option>
                        <option value="java基础">java基础</option>
                        <option value="Python教程">Python教程</option>
                        <option value="React教程">React教程</option>
                        <option value="PHP教程">PHP教程</option>
                    </select>
                </div>

                <div class="a">
                    <input type="text" class="in-1" name="age" placeholder="请输入年龄">
                </div>
                <div class="a">
                    <button type="submit">预&nbsp;&nbsp;约</button>
                </div>
                <div class="a">
                    <div class="hr"></div>
                </div>
                <div class="a"><a href='<c:url value="/index.do"></c:url> '>返&nbsp;回&nbsp;首&nbsp;页</a></div>
            </div>
        </form>
        <%--<div>${messages}</div>--%>
    </div>

</div>

<%--<!-- jquery必须放在第一个加载 -->--%>
<%--<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>--%>
<%--<script type="text/javascript" src="static/js/jquery.validate.min.js"></script>--%>
<%--<script type="text/javascript">--%>
    <%--$(document).ready(function(){--%>
        <%--//js的方式进行校验--%>
        <%--$('#iform').validate({--%>
            <%--rules:{//写校验规则的--%>
                <%--visitorName:{//loginName是input的name属性，不是id--%>
                    <%--required:true,--%>
                    <%--minlength:3--%>
                <%--},--%>
                <%--tel:{--%>
                    <%--required:true,--%>
                    <%--minlength:6,--%>
                    <%--maxlength:11--%>
                <%--}--%>
            <%--},--%>
            <%--messages:{//写提示信息的--%>
                <%--visitorName:{//loginName是input的name属性，不是id--%>
                    <%--required:'用户名是必须的',--%>
                    <%--minlength:'用户名最少3个字符'--%>
                <%--},--%>
                <%--tel:'手机号是必须填写的，至少6个字符，最多11个字符'--%>
                <%--//如果不需要给每个校验规则都写提示信息，就只写一个字符串--%>
                <%--/*{--%>
                   <%--required:'密码是必须填写的',--%>
                   <%--minlength:'密码最少5个字符',--%>
                   <%--maxlength:'密码最多10个字符'--%>
               <%--}*/--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

<%--</script>--%>

</body>
</html>