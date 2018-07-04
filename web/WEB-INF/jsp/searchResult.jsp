<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/9
  Time: 6:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>searchPage</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script type="text/javascript" src="js/jquery.json.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/user-part.js"></script>
    <script type="text/javascript" src="js/searchResult.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/user-part.css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    <link href="css/main-header.css" rel="stylesheet" type="text/css">
    <link href="css/seachResult.css" rel="stylesheet" type="text/css">
    <link href="css/window.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">

    <style>
        .main-header {
            position: relative;
            right: 0px;
            top: 0px;
            margin: 0px;
            width: 100%;
            height: 100px;
            text-align: center;
            background-color: #f3f3f3;

        }

        .navagation {


            width: 100%;
            height: 70px;
            margin: 0px;
        }

        .seach-Result {
            margin-top: 20px;
            width: 100%;
            border-top: 1px solid #a8a8a8;


        }
    </style>

</head>

<body>

<!-- 8b    d8    db    88 88b 88          88  88 888888    db    8888b.  888888 88""Yb
<!-- 88b  d88   dPYb   88 88Yb88 ________ 88  88 88__     dPYb    8I  Yb 88__   88__dP
<!-- 88YbdP88  dP__Yb  88 88 Y88 """""""" 888888 88""    dP__Yb   8I  dY 88""   88"Yb
<!-- 88 YY 88 dP""""Yb 88 88  Y8          88  88 888888 dP""""Yb 8888Y"  888888 88  Yb  -->
<div class="main-header">
    <div class="seach-part">
        <form action="" method="get" id="searchform">
            <input type="text" name="searchStr" class="user-seach" id="user-search" ">
            <input type="button" class="user-seach-click" value="搜索">
        </form>
    </div>
    <div id="user-part" class="user-part">
        <div id="user-pic" class="user-pic" v-if="isLogin">
            <img :src="image">
            <div class="user-details">
                <ul>
                    <li><a :href="Home">我的主页</a></li>
                    <li><a href="Logout">退出登录</a></li>
                </ul>
            </div>
        </div>
        <div id="Login" class="Login" v-else="isLogin">
            <a href="Login">登录</a>
        </div>
    </div>
</div>


<!-- 88b 88    db    Yb    dP    db     dP""b8    db    888888 88  dP"Yb  88b 88
<!-- 88Yb88   dPYb    Yb  dP    dPYb   dP   `"   dPYb     88   88 dP   Yb 88Yb88
<!-- 88 Y88  dP__Yb    YbdP    dP__Yb  Yb  "88  dP__Yb    88   88 Yb   dP 88 Y88
<!-- 88  Y8 dP""""Yb    YP    dP""""Yb  YboodP dP""""Yb   88   88  YbodP  88  Y8  -->
<jsp:include page="navigation.jsp"></jsp:include>

<!-- .dP"Y8 888888    db     dP""b8 88  88          88""Yb 888888 .dP"Y8 88   88 88     888888
<!-- `Ybo." 88__     dPYb   dP   `" 88  88 ________ 88__dP 88__   `Ybo." 88   88 88       88
<!-- o.`Y8b 88""    dP__Yb  Yb      888888 """""""" 88"Yb  88""   o.`Y8b Y8   8P 88  .o   88
<!-- 8bodP' 888888 dP""""Yb  YboodP 88  88          88  Yb 888888 8bodP' `YbodP' 88ood8   88    -->
<div class="seach-Result" id="seach-Result">


    <div class="all-selected">
        <button type="button" class="btn btn-light" id="selected-play">
            <span class="palyall"></span>播放</button>

        <button type="button" class="btn btn-light" data-toggle="modal" href='#songlist-select'>
            <span class="collectall"></span>添加到歌单</button>
    </div>

    <!--选择加入歌单弹窗-->
    <div class="modal fade" id="songlist-select">
        <div class="modal-dialog">
            <div class="modal-content" id="songlist">
                <div class="modal-header">
                    <h4 class="modal-title">请选择你要加入的歌单</h4>
                </div>
                <div class="modal-body">
                    <div class="songlist"  v-if="isLogin">
                        <div class="Create-songlist-item">
                            <div class="Create-songlist">
                            </div>
                            <h6>新建歌单</h6>
                        </div>
                        <div class="songlist-item" v-for="songListInfo of cpsongListInfoList" v-bind:data-songlistid="songListInfo.songListId">

                            <img v-bind:src="songListInfo.src" alt="">
                            <h6>{{songListInfo.name}}</h6>
                        </div>
                    </div>
                    <!-- 未登录 -->
                    <div class="NoLogin" v-else>
                        <h4>请先登录</h4>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

                </div>
            </div>
        </div>
    </div>



    <!--创建歌单弹窗-->
    <div class="modal fade" id="CreateSL-dialog">
        <div class="modal-dialog">

            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">创建歌单</h4>

                </div>
                <div class="modal-body">

                    <div class="CSL-details">
                        <div class="slist-show form-group">
                            <img src="" alt="" class="slist-img">


                        </div>
                        <form action="" method="post" id="songlist-upload" enctype="multipart/form-data">
                            <div class="form-group slist-img-select">

                                <label for="">Choose images to upload (PNG, JPG)</label>
                                <input type="file" name="imageFile" id="imageFile" class="form-control" accept=".jpg, .jpeg, .png">

                            </div>

                            <div class="form-group">
                                <label for="">歌单名称</label>
                                <input type="text" class="form-control" name="Name">
                            </div>
                            <div class="form-group">
                                <select name="tab" class="form-control">
                                    <option value="华语">华语</option>
                                    <option value="粤语">粤语</option>
                                    <option value="欧美">欧美</option>
                                    <option value="日语">日语</option>
                                    <option value="韩语">韩语</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button id="sumit-sl" type="submit" class="btn btn-primary">新建歌单</button>
                        </form>
                    </div>


                </div>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>



    <!--
        .dP"Y8  dP"Yb  88b 88  dP""b8 88     88 .dP"Y8 888888 888888    db    88""Yb 88     888888
        `Ybo." dP   Yb 88Yb88 dP   `" 88     88 `Ybo."   88     88     dPYb   88__dP 88     88__
        o.`Y8b Yb   dP 88 Y88 Yb  "88 88  .o 88 o.`Y8b   88     88    dP__Yb  88""Yb 88  .o 88""
        8bodP'  YbodP  88  Y8  YboodP 88ood8 88 8bodP'   88     88   dP""""Yb 88oodP 88ood8 888888
      -->
    <div class="RT-content">
        <div class="songlist">
            <table class="songlisttable table table-striped" id="songListTable">
                <thead class="tablehead">
                <tr>
                    <th>
                        <input type="checkbox" name="" id="">
                    </th>
                    <th style="width: 200px">歌曲</th>
                    <th  style="width: 400px">歌手</th>
                    <th>专辑</th>
                    <th>时长</th>
                </tr>
                </thead>
                <tbody>
                <!--vue把结果渲染table-->



                <c:forEach items="${songs}" var="song">
                    <tr data-songid="${song.songId}">
                        <th><input type="checkbox" name=""></th>
                        <th><c:out value="${song.title}"></c:out></th>
                        <th><c:out value="${song.artist}"></c:out>
                            <div class="select">
                                <ul>
                                   <a href="GetSongs?StrsongId=${song.songId}" class="select-paly"></a>
                                   <a class="select-add"></a>
                                    <a class="select-down" href="/music/${song.artist} - ${song.title}.mp3" download="${song.artist} - ${song.title}.mp3"></a>
                                </ul>
                            </div>
                        </th>
                        <th ><c:out value="${song.album}"></c:out></th>
                        <th><c:out value="${song.durationConver()}"></c:out></th>
                    </tr>

                </c:forEach>
                <tr v-for="song of cpsonglist" v-bind:data-songid="song.songId">
                    <th>
                        <input type="checkbox" name="" id="">
                    </th>
                    <th>{{song.artist}}</th>
                    <th>{{song.title}}
                        <div class="select">
                            <ul>
                                <a :href="song.href" class="select-paly"></a>
                                <a class="select-add"></a>
                                <a class="select-down"></a>
                            </ul>
                        </div>
                    </th>
                    <th>{{song.album}}</th>
                    <th>{{song.duration}}</th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>

</html>


