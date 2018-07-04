<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/26
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/SongListSearch.js"></script>
    <script src="js/user-part.js"></script>
    <script src="js/navigationAndHeader.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/user-part.css">
    <link href="css/main-header.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/SongListSearch.css">
    <title>Document</title>
</head>
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

</style>
<body>
<div class="main-header">
    <div class="seach-part">
        <form action="" method="get" id="searchform">
            <input type="text" name="searchStr" class="user-seach">
            <input type="button" class="user-seach-click" id="searchBtn" value="搜索">
        </form>
    </div>
    <div id="user-part" class="user-part">
        <div id="user-pic" class="user-pic" v-if="isLogin">
            <img :src="image" alt="这是头像">
            <div class="user-details">
                <ul>
                    <ul>
                        <li><a :href="Home">我的主页</a></li>
                        <li><a href="/musicweb/Logout">退出登录</a></li>
                    </ul>
                </ul>
            </div>
        </div>
        <div id="Login" class="Login" v-else="isLogin">
            <a href="#">登录</a>
        </div>
    </div>
</div>
<jsp:include page="navigation.jsp"></jsp:include>
<div class="tabs">
    <span class="selected-tab">全部</span>
    <span>华语</span>
    <span>粤语</span>
    <span>欧美</span>
    <span>日语</span>
    <span>韩语</span>
</div>

<div id="songList-show" class="align">
    <div class="row">
       <c:forEach items="${songListInfos}" var="songListInfo">
           <div class="col-3">
               <a href="MusicList?songListId=${songListInfo.songListId}">
                   <img src="/userImg/${songListInfo.pic}" alt="">
                   <p>
                       <c:out value="${songListInfo.name}"></c:out><br>
                       <c:out value="${songListInfo.createUserId.name}"></c:out>
                   </p>
               </a>
           </div>
       </c:forEach>

        <div class="col-3" v-for="songList of cpsongListInfoList">
            <a :href="songList.href">
                <img :src="songList.src" alt="">
                <p>{{songList.name}}<br>{{songList.createUserId.name}}</p>
            </a>
        </div>
    </div>
</div>
</body>
</html>
