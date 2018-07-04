<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/26
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--
     <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="/musicweb/js/vue.js"></script>
    <script src="/musicweb/js/user-part.js"></script>
    <script src="js/navigationAndHeader.js"></script>
    <link href="css/user-part.css" rel="stylesheet" type="text/css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    -->

    <title>Title</title>
</head>
<style>

    .navagation {
        width: 100%;
        height: 70px;
        margin: 0px;
        position: relative;
    }

</style>
<body>
<!--导航栏部分-->
<div class="navagation">
    <ul class="navagation-ul">
        <li>
            <a href="index">
                <div class="li-content">
                    <div class="li-content-bg"></div>
                    <div class="li-content-txt">首页</div>
                </div>
            </a>
        </li>
        <li>
            <a href="ListPage">
                <div class="li-content">
                    <div class="li-content-bg"></div>
                    <div class="li-content-txt">榜单</div>
                </div>
            </a>
        </li>
        <li>
            <a href="SearchLoadSL">
                <div class="li-content">
                    <div class="li-content-bg"></div>
                    <div class="li-content-txt">歌单</div>
                </div>
            </a>
        </li>
        <li>
            <a href="singerSearch">
                <div class="li-content">
                    <div class="li-content-bg"></div>
                    <div class="li-content-txt">歌手</div>
                </div>
            </a>
        </li>

    </ul>
    <div id="user-part" class="user-part">
        <div id="user-pic" class="user-pic" v-if="isLogin">
            <img :src="image" >
            <div class="user-details">
                <ul>
                    <li><a :href="Home">我的主页</a></li>
                    <li><a href="/musicweb/Logout">退出登录</a></li>
                </ul>
            </div>
        </div>
        <div id="Login" class="Login" v-else="isLogin">
            <a href="/musicweb/Login">登录</a>
        </div>
    </div>
</div>
</body>
</html>
