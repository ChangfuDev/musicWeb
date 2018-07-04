<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/21
  Time: 15:29
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
    <script src="js/Musiclist.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="/musicweb/js/user-part.js"></script>
    <script src="js/navigationAndHeader.js"></script>
    <link href="css/user-part.css" rel="stylesheet" type="text/css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/Musiclist.css">
    <title>Document</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<!-- MusicListDetails -->
<div class="MusicListDetails row mainContent">
    <div class="MusicListDetails-bg"></div>
    <script>
        $(function () {
            $(".MusicListDetails-bg").css("background-image","url(/userImg/${songList.pic})")

        });
    </script>
    <div class="List-pic col-4">
        <img src="/userImg/${songList.pic}" alt="musiclistpic" class="rounded">
    </div>
    <div class="List-details col-8">
        <ul>
            <li><h4><c:out value="${songList.name}"/></h4></li>
            <li><img  src="/userImg/${songList.createUserId.image}" alt="Createuserpic" class="CreateUser-pic rounded">
                &nbsp; &nbsp; &nbsp; &nbsp;
                创建用户：<a href="user-a"><c:out value="${songList.createUserId.name}"/></a>
                <br>
                <br>
                <c:out value="${songList.formatTime()} 创建"/>
            </li>
            <li>点击数:${songList.playCount}</li>
            <li>收藏数:${songList.collectCount}</li>
            <li>
                <a href="/musicweb/GetSongs?StrsongListInfoId=${songList.songListId}"><button type="button" class="btn  btn-dark"><span class="paly"></span>播放</button></a>
                <c:if test="${!Isedit}">
                    <button type="button" class="btn btn-light" id="collectBtn" data-songlistid="${songList.songListId}">
                        <span class="add"></span>
                        收藏
                    </button>
                </c:if>
                <button type="button" class="btn btn-light" id="conmmentBtn"> <span class="comment"></span>评论</button>
                <button type="button" class="intro-btn btn btn-light"> <span class="intro"></span>简介</button>
                <c:if test="${Isedit}">
                    <button type="button" class="btn btn-light" data-toggle="modal" href='#alterSL-dialog'><span class="edit"></span>编辑</button>
                </c:if>
            </li>
            <p class="ML-intro">
                <c:out value="${songList.introdution}"/>
            </p>
        </ul>

    </div>

    <c:if test="${Isedit}">
        <!-- alterSL-dialog -->
        <div class="modal fade" id="alterSL-dialog">
            <div class="modal-dialog">

                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">编辑歌单</h4>

                    </div>
                    <div class="modal-body">

                        <div class="CSL-details">
                            <div class="slist-show form-group">
                                <img src="/userImg/${songList.pic}" alt="" class="slist-img">
                            </div>
                            <form action="UpdateSL?songListId=${songList.songListId}" method="post" id="songlist-upload" enctype="multipart/form-data">
                                <div class="form-group slist-img-select">

                                    <label for="">选择图片(PNG, JPG)</label>
                                    <input type="file" name="imageFile" id="imageFile" class="form-control" accept=".jpg, .jpeg, .png">

                                </div>

                                <div class="form-group">
                                    <label for="">歌单名称</label>
                                    <input type="text" class="form-control" name="Name" value="${songList.name}">
                                </div>
                                <div class="form-group">
                                    <label for="">简介</label>
                                    <textarea name="introdution" id="" cols="30" rows="5" class=" form-control"><c:out value="${songList.introdution}"/></textarea>
                                </div>
                                <script>
                                    $(function () {
                                       var selectedTab=$("#tab-select option[value=${songList.tab.trim()}]").get(0);
                                        selectedTab.selected=true;
                                    });
                                </script>
                                <div class="form-group">

                                    <select name="tab" class="form-control" id="tab-select">
                                        <option value="华语">华语</option>
                                        <option value="粤语">粤语</option>
                                        <option value="欧美">欧美</option>
                                        <option value="日语">日语</option>
                                        <option value="韩语">韩语</option>
                                    </select>
                                </div>
                                <div class="form-group text-right">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button id="update-sm" type="submit" class="btn btn-primary">更改歌单</button>
                                </div>
                            </form>
                        </div>


                    </div>
                    <div class="modal-footer">

                    </div>
                </div>
            </div>
        </div>
        <!-- alterSL-dialog  end-->
    </c:if>




</div>
<!-- MusicListDetails end-->


<div class="songlist mainContent">

    <table class="songlisttable table table-striped">
        <thead class="tablehead">
        <tr>
            <th>歌手</th>
            <th>歌名</th>
            <th>专辑</th>
            <th>时长</th>
        </tr>
        </thead>
        <tbody id="songs-show">
        <c:forEach items="${songs}" var="song">
            <tr class="">
                <th><c:out value="${song.songId.title}"/></th>
                <th><c:out value="${song.songId.artist}"/>

                </th>
                <th><c:out value="${song.songId.album}"/>
                    <div class="select">
                        <ul>
                            <a href="/musicweb/GetSongs?StrsongId=${song.songId.songId}" class="select-paly"></a>
                            <c:if test="${Isedit}">
                                <a href="/musicweb/DeleteSLSongs?songListId=${songList.songListId}&&songId=${song.songId.songId}"  class="select-delete"></a>
                            </c:if>

                        </ul>
                    </div>
                </th>
                <th><c:out value="${song.songId.durationConver()}"/></th>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<div class="Comments mainContent">
    <div class="Comment-title">
        <h4>评论</h4>
    </div>
    <div class="Comment">
        <form action="Comment?songListId=${songList.songListId}" method="post">
            <div class="form-group">
                <textarea name="commentContent" class="form-control txt-bg" rows="3" cols="10" id="Comment-txt"></textarea>
            </div>
            <div class="submntComment form-group">
                <button type="submit" class=" btn btn-primary">发表评论</button>
            </div>
        </form>
    </div>
    <div class="Comment-title" id="Comment-title">
        <h4>精彩评论</h4>
    </div>

    <c:forEach items="${commentAndReplyList}" var="commentAndReply">
        <div class="itemAndReply">
            <div class="Conment-item">
                <div class="Comment-user-pic">
                    <img src="/userImg/${commentAndReply.comment.userId.image}" alt="Comment-user-pic">
                </div>
                <div class="Comment-details">
                    <p class="Comment">
                        <a href="Comment-user-a"><c:out value="${commentAndReply.comment.userId.name} :"/></a>
                        <br>&nbsp;&nbsp;&nbsp;&nbsp;
                        <c:out value="${commentAndReply.comment.content}"/>
                    </p>
                    <c:if test="${commentAndReply.comment.replyCommentId>0}">
                        <p class="replide-Com">
                            <a href="Comment-user-a"><c:out value="${commentAndReply.replyComment.userId.name} :"/></a>
                            <br>
                            <c:out value="${commentAndReply.replyComment.content}"/>
                        </p>
                    </c:if>

                    <c:if test="${commentAndReply.comment.replyCommentId==-1}">
                        <p class="replide-Com">
                            评论已删除！
                        </p>
                    </c:if>
                    <div class="Comment-ft">
                        <div class="Conmmet-time"><c:out value="评论时间：${commentAndReply.comment.formatTime()}"/></div>

                        <span class="reply-txt-show">
            <span class="reply"></span>
            </span>
                    </div>



                </div>

            </div>
            <div class="Comment reply-txt unShow">
                <form action="Comment?songListId=${songList.songListId}&&replyCommentId=${commentAndReply.comment.commentId}" method="post">
                    <div class="form-group">
                        <textarea name="commentContent" class="form-control txt-bg" rows="3" cols="10"></textarea>
                    </div>
                    <div class="submntComment form-group">
                        <button type="submit" class=" btn btn-primary">回复</button>
                    </div>
                </form>
            </div>
        </div>


    </c:forEach>

</div>
</body>
</html>
