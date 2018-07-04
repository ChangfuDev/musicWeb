<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/17
  Time: 3:15
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
    <script src="js/userHomePage.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/navigationAndHeader.js"></script>
    <script src="/musicweb/js/user-part.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/useHomePage.css">

    <link href="css/user-part.css" rel="stylesheet" type="text/css">
    <link href="css/navigationmodule.css" rel="stylesheet" type="text/css">
    <title>Document</title>
    <style>
        .navagation {
            width: 100%;
            height: 70px;
            margin-bottom: 50px;
        }
    </style>
</head>
<body>

  <jsp:include page="header.jsp"></jsp:include>


    <div class="user-detail row align">
            <div class="col-4 user-pic-show">
                <img src=<c:url value="http://localhost:8080/userImg/${user.image}"/> alt="toux">
            </div>
            <div class="col-8 user-details-show">
                <div class="uds-show">
                    <h3><c:out value="${user.name}"/>
                        <span>
                            <c:if test="${user.sex}">男</c:if>
                            <c:if test="${!user.sex}">女</c:if>
                        </span>
                        <button type="button" class=" btn btn-deflault"  data-toggle="modal" href='#modal-id' data-uerid="8">修改资料</button>
                    </h3>

                    <h4>年龄:<c:out value="${user.age}"/></h4>

                    <h4>个人介绍:</h4>
                    <br>
                    <br>
                    <p><c:out value="${user.introdution}"/></p>
                </div>
            </div>

            <!--dialog model -->


            <div class="modal fade" id="modal-id">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">用户资料</h4>
                        </div>
                        <div class="modal-body">
                            <div class="slist-show form-group">
                                <img src=<c:url value="http://localhost:8080/userImg/${user.image}"/> alt="" class="slist-img">
                            </div>
                            <form action="/musicweb/UserAlter?userId=${user.userId}" method="post" id="userForm" enctype="multipart/form-data">
                                <div class="form-group slist-img-select">

                                    <label for="">选择图片上传 (PNG, JPG)</label>
                                    <input type="file" name="imageFile" id="imageFile" class="form-control" accept=".jpg, .jpeg, .png">

                                </div>

                                <div class="form-group">
                                    <label for="">用户名</label>
                                    <input type="text" class="form-control" name="name" value="${user.name}" disabled>
                                </div>
                                <div class="form-group">
                                    <label>密码</label>
                                    <input type="password" class="form-control" name="password" value="${user.password}">
                                </div>
                                <div class="form-group row ">
                                    <div class=" form-group  col-6">
                                        <label for="">性别</label>
                                        <select name="sex" class=" form-control">
                                            <c:if test="${user.sex}">
                                                <option value="1"  selected="selected">男</option>
                                                <option value="0">女</option>
                                            </c:if>
                                            <c:if test="${!user.sex}">
                                                <option value="1">男</option>
                                                <option value="0" selected="selected">女</option>
                                            </c:if>


                                        </select>

                                    </div>
                                    <div class="form-group  col-6">
                                        <label>年龄</label>
                                        <input  type="number" min="0" max="100" name="age"class=" form-control" value="${user.age}">
                                    </div>
                                </div>
                                <div class=" form-group">
                                    <label>个人介绍</label>
                                    <textarea name="introdution" id="" cols="30" rows="3" class=" form-control"><c:out value="${user.introdution}"/></textarea>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <input type="reset" value="取消" class="btn btn-default">
                                    <button type="submit" class="btn btn-primary">修改</button>

                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>
            </div>

        </div>



    <div class="CreateList align" id="CreateList">
        <h3 class="listtitle">我创建的歌单<button type="button" class=" btn btn-dark" data-toggle="modal" href='#songlist-select'>编辑歌单</button></h3>





        <!--用户歌单显示 Sonlist Dialog component(歌单弹窗组件)-->
        <div class="modal fade" id="songlist-select">
            <div class="modal-dialog">
                <div class="modal-content" id="songlist" >
                    <div class="modal-header">
                        <h4 class="modal-title">我的歌单</h4>
                    </div>
                    <div class="modal-body">
                        <!-- 登录 -->
                        <div class="songlist">


                            <div class="songlist-item"
                                 v-for="songListInfo of cpsongListInfoList"
                                 v-bind:data-songlistid="songListInfo.songListId"
                                 v-on:click="showListInfo()">

                                <img v-bind:src="songListInfo.src" alt="">
                                <h6>{{songListInfo.name}}</h6>
                                <a :href="songListInfo.deleteUrl" class="deleteSL">

                                </a>
                            </div>



                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

                    </div>
                </div>

            </div>
        </div>
        <!-- END -->



        <div class="CreateList-show row" id="CreateList-show">

        <!--
             ######  ########  ########    ###    ######## ######## ##       ####  ######  ########
            ##    ## ##     ## ##         ## ##      ##    ##       ##        ##  ##    ##    ##
            ##       ##     ## ##        ##   ##     ##    ##       ##        ##  ##          ##
            ##       ########  ######   ##     ##    ##    ######   ##        ##   ######     ##
            ##       ##   ##   ##       #########    ##    ##       ##        ##        ##    ##
            ##    ## ##    ##  ##       ##     ##    ##    ##       ##        ##  ##    ##    ##
             ######  ##     ## ######## ##     ##    ##    ######## ######## ####  ######     ##
         -->
        <c:forEach items="${songLists}" var="songList">
            <div class="col-3">
                <a href="/musicweb/GetSongs?StrsongListInfoId=${songList.songListId}">
                    <img src=<c:url value="http://localhost:8080/userImg/${songList.pic}"/> alt="">
                </a>
                <h6><c:out value="${songList.name}"/></h6>
            </div>
        </c:forEach>
    </div>

    </div>


    <div class="UserConllection align">
    <h3 class="listtitle">收藏的歌单
        <button type="button" class="btn btn-dark" data-toggle="modal" href='#collected-songlist'>编辑收藏歌单</button>
    </h3>
        <div class="modal fade" id="collected-songlist">
            <div class="modal-dialog">
                <div class="modal-content" id="songlist" >
                    <div class="modal-header">
                        <h4 class="modal-title">收藏的歌单</h4>
                    </div>
                    <div class="modal-body">
                        <!-- 登录 -->
                        <div class="songlist">


                            <div class="songlist-item"
                                 v-for="songListInfo of cpsongListInfoList"
                                 v-bind:data-songlistid="songListInfo.songListId"
                                 v-on:click="showListInfo()">

                                <img v-bind:src="songListInfo.src" alt="">
                                <h6>{{songListInfo.name}}</h6>
                                <a :href="songListInfo.DLCollectionUrl" class="deleteSL">

                                </a>
                            </div>



                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

                    </div>
                </div>

            </div>
        </div>


    <div class="UserConllection-show row" id="Conllection-show">
        <!--
             #     #                       #####
             #     #  ####  ###### #####  #     #  ####  #    # #      #      ######  ####  ##### #  ####  #    #
             #     # #      #      #    # #       #    # ##   # #      #      #      #    #   #   # #    # ##   #
             #     #  ####  #####  #    # #       #    # # #  # #      #      #####  #        #   # #    # # #  #
             #     #      # #      #####  #       #    # #  # # #      #      #      #        #   # #    # #  # #
             #     # #    # #      #   #  #     # #    # #   ## #      #      #      #    #   #   # #    # #   ##
              #####   ####  ###### #    #  #####   ####  #    # ###### ###### ######  ####    #   #  ####  #    #

         -->

        <c:forEach items="${collectSonglists}" var="songList">
            <div class="col-3">
                <a href="/musicweb/GetSongs?StrsongListInfoId=${songList.songListId}">
                    <img src=<c:url value="http://localhost:8080/userImg/${songList.pic}"/> alt="">
                </a>
                <h6><c:out value="${songList.name}"/></h6>
            </div>
        </c:forEach>

    </div>

</div>

</body>
</html>