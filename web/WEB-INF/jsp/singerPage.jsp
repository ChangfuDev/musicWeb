
<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/5/6
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="js/SingerPage.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/SingerPage.css">
    <script src="/musicweb/js/user-part.js"></script>
    <script src="js/navigationAndHeader.js"></script>
    <link href="css/user-part.css" rel="stylesheet" type="text/css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    <title>Document</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="SingerDetails row mainContent">
    <div class="SingerDetails-bg"></div>
    <div class="Singer-pic col-4">
        <script>
            $(function () {
                $(".SingerDetails-bg").css("background-image","url(/singer/${singer.iamge})")
            });
        </script>
        <img src="/singer/${singer.iamge}" alt="musiclistpic" class="rounded">
    </div>
    <div class="Singer-details col-8">
        <ul>
            <li><h4><c:out value="${singer.name}"/></h4></li>
            <li>歌手歌曲总数:<c:out value="${songs.size()}"/></li>
            <li>
                <button type="button" class="btn  btn-dark" id="playAll"><span class="paly"></span>播放全部</button>
                <button type="button" class="intro-btn btn btn-light">
                    <span class="intro"></span>
                    简介
                </button>
            </li>
            <p id="ML-intro" style="display: none">
                 ${singer.formatIntrodution()}
            </p>

        </ul>

    </div>
</div>




<div class="songlist mainContent">

    <table class="songlisttable table table-striped">
        <thead class="tablehead">
        <tr>
            <th>歌曲</th>
            <th>歌手</th>
            <th>专辑</th>
            <th>时长</th>
        </tr>
        </thead>
        <tbody id="singerSongs">
        <c:forEach items="${songs}" var="song">
            <tr data-songid="${song.songId}">
                <th><c:out value="${song.title}"/></th>
                <th><c:out value="${song.artist}"/></th>
                <th><c:out value="${song.album}"/>
                    <div class="select">
                        <ul>
                            <a href="/musicweb/GetSongs?StrsongId=${song.songId}" class="select-paly"></a>
                        </ul>
                    </div>
                </th>
                <th><c:out value="${song.durationConver()}"/></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>



</body>
</html>