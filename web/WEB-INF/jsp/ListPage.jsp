
<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/5/6
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/List.js"></script>
    <%--header js--%>
    <script src="/musicweb/js/user-part.js"></script>
    <script src="js/navigationAndHeader.js"></script>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/List.css">
    <%--header css--%>
    <link href="css/user-part.css" rel="stylesheet" type="text/css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">


    <title>Document</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="m-auto width-1k ">
    <div class="row mt-5">
        <div class="nav flex-column nav-pills col-2" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link active" id="songsList-tab" data-toggle="pill" href="#songsList" role="tab" aria-controls="songsList" aria-selected="true">歌曲排行</a>
            <a class="nav-link" id="songlistClick-rank-tab" data-toggle="pill" href="#songlistClick-rank" role="tab" aria-controls="songlistClick-rank" aria-selected="false">歌单播放排行</a>
            <a class="nav-link" id="songlistCollect-rank-tab" data-toggle="pill" href="#songlistCollect-rank" role="tab" aria-controls="songlistCollect-rank" aria-selected="false">歌单收藏排行</a>
        </div>
        <div class="tab-content col-10" id="v-pills-tabContent">

            <!-- songsList -->
            <div class="tab-pane fade show active" id="songsList" role="tabpanel" aria-labelledby="songsList-tab">

                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>排名</th>
                        <th>歌名</th>
                        <th>歌手</th>
                        <th>点击量</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${songs}" var="song" varStatus="statu">
                        <tr class="" onclick="location.href='GetSongs?StrsongId=${song.songId}'">

                            <th>${statu.index+1}</th>
                            <th>${song.title}</th>
                            <th>${song.artist}</th>
                            <th>${song.playCount}</th>




                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
            <!-- songsList end-->



            <!-- songlistClick-rank -->
            <div class="tab-pane fade content-mt" id="songlistClick-rank" role="tabpanel" aria-labelledby="songlistClick-rank-tab">
                <c:forEach items="${songListInfoPlayList}" var="songListInfo" begin="0" end="9" varStatus="statu">
                    <div class="d-flex hover songlist-item"  onclick="location.href='GetSongs?StrsongListInfoId=${songListInfo.songListId}'">
                        <h1 class="mh-auto">${statu.index+1}</h1>
                        <img src="/userImg/${songListInfo.pic}" alt="" class="imgsize">
                        <div style="flex: 8">
                            <p class="m-auto text-center">
                                    ${songListInfo.name}
                                    <br>
                                    (by &nbsp; ${songListInfo.createUserId.name})
                            </p>
                        </div>
                        <div style="flex: 2">
                            <p class="m-auto text-center">
                                播放数：${songListInfo.playCount}

                            </p>
                        </div>

                    </div>
                </c:forEach>
            </div>
            <!-- songlistClick-rank end-->


            <!-- songlistCollect-rank-->
                <div class="tab-pane fade content-mt" id="songlistCollect-rank" role="tabpanel" aria-labelledby="songlistCollect-rank-tab">
                    <!-- songlistCollect-rank-->
                    <c:forEach items="${songListInfoCollectList}" var="songListInfo" begin="0" end="9" varStatus="statu">
                        <div class="d-flex hover songlist-item"  onclick="location.href='GetSongs?StrsongListInfoId=${songListInfo.songListId}'">
                            <h1 class="mh-auto">${statu.index+1}</h1>
                            <img src="/userImg/${songListInfo.pic}" alt="" class="imgsize">
                            <div style="flex: 8">
                                <p class="m-auto text-center">
                                        ${songListInfo.name}
                                    <br>
                                    (by &nbsp; ${songListInfo.createUserId.name})
                                </p>
                            </div>
                            <div style="flex: 2">
                                <p class="m-auto text-center">
                                    收藏数：${songListInfo.collectCount}

                                </p>
                            </div>

                        </div>
                    </c:forEach>
                </div>
            <!-- songlistCollect-rank end-->


        </div>
    </div>

</div>


</body>
</html>
