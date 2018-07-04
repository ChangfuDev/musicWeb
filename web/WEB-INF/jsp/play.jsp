<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/15
  Time: 7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>无标题文档</title>
    <link href="css/Contronls-right.css" rel="stylesheet" type="text/css">
    <link href="css/Controls-leftACenter.css" rel="stylesheet" type="text/css">
    <link href="css/music-bg.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery-1.6.4.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/playVue.js"></script>

    <script src="js/play.js"></script>
    <script src="js/Controls.js"></script>
    <script>

    </script>
    <style>
        body{

            width: 100%;
            margin: 0px;
            padding: 0px;
            overflow: hidden;
            position: relative;
        }
        .lyc
        {
            color: #4d90fe;
            font-size: larger;
        }
        #lyc-content span
        {
            display: none;

        }
        .module-player{

            position:absolute;
            bottom: 0px;
            width: 1366px;
            height: 80px;
            animation: module-player-AM .5s ease-out;




        }
        #player-controls
        {
            width: 75%;
            height: 100%;
            margin: 0 auto;


        }
        @keyframes module-player-AM
        {
            from{bottom: -80px;}
            to{bottom: 0px;}
        }
        #music-bg
        {

            position: absolute;
            bottom: 0px;
            width: 100%;
            height:100%;
            overflow: hidden;

        }
        .music-page
        {
            position: absolute;
            width: 1366px;
            height: 100%;
            bottom: 0px;
        }
        .blur-ad
        {
            width: 100%;
            height: 100%;
            background: rgba(34, 34, 34, 0.5);
        }
    </style>

</head>
<body>

<audio id="audio1" src="music/eng.mp3" autoplay="autoplay"></audio>
<div class="music-page">

    <div id="music-bg">

        <div id="pic-blur" class="pic-blur">
            <div class="blur-ad"></div>
        </div>
        <div id="music-content">
            <div id="mcontent-left">
                <div class="backWrapper"><span class="back" id="back"></span></div>
                <div id="singer-pic"></div>
                <div id="msdownload">
                    <a id="musicbg-load">下载这首歌</a>
                </div>

            </div>
            <div id="mcontent-right">


                <div id="lyc-content">

                    <!--
                          888888 88  88 88 .dP"Y8     88 .dP"Y8     88     88""Yb  dP""b8      dP""b8  dP"Yb  88b 88 888888 888888 88b 88 888888
                            88   88  88 88 `Ybo."     88 `Ybo."     88     88__dP dP   `"     dP   `" dP   Yb 88Yb88   88   88__   88Yb88   88
                            88   888888 88 o.`Y8b     88 o.`Y8b     88  .o 88"Yb  Yb          Yb      Yb   dP 88 Y88   88   88""   88 Y88   88
                            88   88  88 88 8bodP'     88 8bodP'     88ood8 88  Yb  YboodP      YboodP  YbodP  88  Y8   88   888888 88  Y8   88
                       -->

                </div>


            </div>
        </div>


    </div>
    <div class="module-player">

        <div id="player-controls">
            <div id="controls-left">


                <div id="Previous" class="PreviousLeave">

                </div>
                <div id="Pause" class="Pause">

                </div>
                <div id="Next" class="NextLeave">

                </div>

            </div>

            <div id="controls-center">
                <div id="singerpic"></div>
                <div id="progress-bar">
                    <span id="song"></span>
                    <span id="songtime">00:00</span>
                    <div id="header"></div>
                </div>

            </div>

            <div id="Controls-right">

                <%--<div id="volume" class="volumeLeave">--%>
                    <%--<div id="volume-Controls" class="volume-Controls-hide">--%>
                        <%--<div id="VC-bg">--%>
                            <%--<div id="pg-Controls">--%>
                                <%--<div id="mov-module">--%>
                                    <%--<div id="mov"></div>--%>
                                <%--</div>--%>
                                <%--<div id="Vo-header">--%>

                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div id="mode" class="modeLeave">
                    <div id="mode-bg" class="mode-bg">
                        <ul>
                            <li id="cycle">单曲循环</li>
                            <li id="listcycle">列表循坏</li>
                            <li id="Random">随机播放</li>
                        </ul>
                    </div>
                </div>
                <a id="downloada">
                    <div id="download" class="downloadLeave"></div>
                </a>
                <div id="list" class="listLeave">
                    <span></span>
                    <div id="music-list-bg" class="music-list-bg" >
                        <!--
                            88""Yb 88        db    Yb  dP 88     88 .dP"Y8 888888
                            88__dP 88       dPYb    YbdP  88     88 `Ybo."   88
                            88"""  88  .o  dP__Yb    8P   88  .o 88 o.`Y8b   88
                            88     88ood8 dP""""Yb  dP    88ood8 88 8bodP'   88
                         -->
                        <ul id="playlist" >
                            <!-- 播放列表 -->

                            <c:forEach items="${songs}" var="song">
                                <li data-songid="${song.songId}"
                                    data-song="${song.artist} - ${song.title}"
                                    data-musicname="${song.title}"
                                    data-singer="${song.artist}"
                                    data-album="${song.album}">
                                    <c:out value="${song.artist}"/>
                                    <c:out value=" - "/>
                                    <c:out value="${song.title}"/>
                                </li>
                            </c:forEach>
                            <li v-for="song of cpplaySongs":key="song.id" v-on:click="change(index)"
                                v-bind:data-songid="song.songId"
                                v-bind:data-song="song.songName"
                                v-bind:data-musicname="song.title"
                                v-bind:data-singer="song.artist"
                                v-bind:data-album="song.album"
                            >{{song.songName}}</li>
                        </ul>
                    </div>
                </div>
            </div>



        </div>


    </div>
</div>
<div class="mymusicList">

    <div class="show"></div>
    <div class="hide">
        <div class="content" >

            <div class="Uersonglist" v-if="isLogin">
                <div class="user-part">

                    <img :src="image" >
                    <div class="user-details">
                        <h5>{{user.name}}</h5>
                        <c:if test="${user.sex}"><h5>男</h5></c:if>
                        <c:if test="${!user.sex}"><h5>女</h5></c:if>

                    </div>
                </div>
                <div id="songlist-item" >
                    <div class="songlist-item" v-for="songListInfo of cpsongListInfoList" :data-songlistid="songListInfo.songListId">
                        <img :src="songListInfo.src" alt="">
                        <h6>{{songListInfo.name}}</h6>
                    </div>
                </div>
            </div>
            <div class="Nologin" v-else>
                <h4><a href="Login">请先登录</a></h4>
            </div>
        </div>
    </div>
</div>
</body>
</html>
