<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/9
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
    <script type="text/javascript" src="/musicweb/js/jquery-1.6.4.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="/musicweb/js/index.js"></script>
    <script src="/musicweb/js/vue.js"></script>
    <script src="/musicweb/js/user-part.js"></script>
    <link href="/musicweb/css/display-module.css" rel="stylesheet" type="text/css">
    <link href="/musicweb/css/navigationmodule.css" rel="stylesheet" type="text/css">
    <link href="/musicweb/css/music-commend-module.css" rel="stylesheet" type="text/css">
    <link href="/musicweb/css/main-header.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/musicweb/css/user-part.css">
    <link href="/musicweb/css/music-td.css" rel="stylesheet" type="text/css">
    <link href="/musicweb/css/footer.css" rel="stylesheet" type="text/css">
    <link href="/musicweb/css/bootstrap.css" rel="stylesheet" type="text/css">
    <style>
        body {
            margin: 0px auto;
            width: 100%;
            min-width: 1326px;

        }

        .navagation {
            width: 100%;
            height: 70px;
            margin: 0px;
        }

        .display-module {
            position: relative;
            width: 100%;
            height: 500px;
            overflow: hidden;
            margin-top: 0px;

        }

        /*border: 1px solid rgba(233,31,34,1.00);*/

        .music-tb {
            margin: 50px auto;
            width: 1200px;
            height: 350px;
            background-color: #f3f3f3;
            overflow: hidden;

        }

        .music-commend {
            min-width: 100%;
            position: relative;
            width: 1200px;
            height: 610px;

        }

        .main-header {
            margin: 0px;
            width: 100%;
            height: 100px;
            text-align: center;
            margin: 25px 0;
            position: relative;

        }

        .footer {

            bottom: 0px;
            width: 100%;
            height: 200px;
            background-color: #222222;

        }
    </style>

</head>

<body>
<!--头部搜索部分-->
<div class="main-header">
    <div class="seach-part">
        <form action="http://localhost:8080/musicweb/searchResult" method="get" id="searchform">
            <input type="text" name="searchStr" class="user-seach">
            <input type="submit" class="user-seach-click" value="搜索">
        </form>
    </div>
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

<!--导航栏部分-->
<jsp:include page="navigation.jsp"></jsp:include>

<!--图片轮播部分-->
<div class="display-module">
    <div class="bg-Content">
        <div id="bg0" class="bg0"></div>
        <div id="bg1" class="bg1"></div>
        <div id="bg2" class="bg2"></div>
        <div id="bg3" class="bg3"></div>
        <div id="bg4" class="bg4"></div>
    </div>
    <div id="left" class="">
        <div class="left-icon"></div>
    </div>
    <div id="right" class="">
        <div class="right-icon"></div>
    </div>
    <div id="disnum" class="">
        <span class="nodis"></span>
        <span class="nodis"></span>
        <span class="nodis"></span>
        <span class="nodis"></span>
        <span class="nodis"></span>
    </div>
</div>

<!--歌曲推荐-->
<div class="music-tb">
    <div class="music-tb-content">
        <div class="day-commend">
            <div class="title">每日推荐</div>
            <ul>

                <c:forEach items="${randomSongs}" var="song" begin="0" end="9">
                    <li><a href="GetSongs?StrsongId=${song.songId}"><c:out value="${song.artist} - ${song.title}"/></a></li>
                </c:forEach>
            </ul>


        </div>
        <div class="charts">
            <div class="title" id="clickRank">点击排行</div>
            <ul>
                <style>
                    .f-right
                    {
                        float: right;
                    }
                </style>
                <c:forEach items="${songsOrderClick}" var="song">


                    <li>
                        <a href="GetSongs?StrsongId=${song.songId}"><c:out value="${song.artist} - ${song.title}"/></a>
                        <div class="f-right"><span>点击量:<c:out value="${song.playCount}"/></span></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>


<div class="commendtitle">
    <h2 class="title">推荐歌单</h2>
    <h5 class="Listkind commendtitle">
        <span class="Listkindselected">华语</span>
        <span>粤语</span>
        <span>欧美</span>
        <span>日语</span>
    </h5>
</div>
<!--歌单推荐-->
<div id="musiclist-commend" class="music-commend">
    <script>
        $(function () {
            $(".commend").css("display","none");
            $(".commend").first().css("display","block");
        });
    </script>

    <style>
        .Nounderline
        {
            text-decoration: none;
        }
    </style>
    <div class="commend-all">
        <c:forEach items="${songLists}" var="lists">
                <div class="commend">
                    <div class="page-1">
                        <div class="row">
                            <div class="content col-12  paddingzero">
                                <div class="row">
                                   <c:forEach items="${lists}" var="list" begin="0" end="7">
                                       <a  href=<c:url value="/GetSongs?StrsongListInfoId=${list.songListId}"/> class="col-3"><div class="content-num "><div class="Content-pic"  style="background-image: url(<c:url value="http://localhost:8080/userImg/${list.pic}"/>)"><div class=""></div></div><h6><c:out value="${list.name}"/></h6></div></a>
                                   </c:forEach>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="page-2">
                        <div class="row">
                            <div class="content col-12  paddingzero">
                                <div class="row">
                                    <c:forEach items="${lists}" var="list" begin="8" end="15">
                                        <a   href=<c:url value="/GetSongs?StrsongListInfoId=${list.songListId}"/> class="col-3"><div class="content-num "><div class="Content-pic"  style="background-image: url(<c:url value="http://localhost:8080/userImg/${list.pic}"/>)"><div class=""></div></div><h6><c:out value="${list.name}"/></h6></div></a>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
        </c:forEach>

    </div>
    <div id="d0-left" class="d0-left" ><div class="d0-left-icon"></div></div>
    <div id="d0-right" class="d0-right"><div class="d0-right-icon"></div></div>
</div>


<!--尾部-->
<div class="footer">
    <div class="ft-Content">
        <p>
            <a href="#">服务条款|</a>
            <a href="#">用户服务协议|</a>
            <a href="#">隐私政策|</a>
            <a href="#">权利声明|</a>
            <a href="#">广告服务|</a>
            <a href="#">客服中心|</a>
            <a href="#">网站导航|</a>
            <a href="#">广告服务</a>
        </p>
    </div>
</div>


</body>

</html>
