<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/27
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
    <script src="js/vue.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/Admin.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/Admin.css">
    <title>Document</title>
</head>
<script>

</script>
<body>
<!-- <style>
    .nav-pills .nav-link.active, .nav-pills .show > .nav-link
    {
        background-color: #93b7f3;
    }
</style> -->

<div class="row">
    <div class="nav flex-column nav-pills col-2 srceen-height" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <div class="d-flex title-bg mytitle"><h5 class="m-auto">音乐网站后台管理</h5></div>
        <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">用户管理</a>
        <a class="nav-link" id="v-pills-singerManage-tab" data-toggle="pill" href="#singerManage" role="tab" aria-controls="singerManage" aria-selected="false">歌手信息录入</a>
        <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">上传歌词</a>
        <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">曲目管理</a>
        <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">上传歌曲</a>
        <a class="nav-link"  href="index">返回首页</a>


    </div>

    <div class="tab-content col-10 " id="v-pills-tabContent">

        <div class="tab-pane fade" id="singerManage" role="tabpanel" aria-labelledby="v-pills-singerManage-tab">
            <div id="singerMessage" style="display: none">

                <div class="row" id="form-data">
                    <div class="col-8">
                        <form action="" method="POST" role="form" id="singer-Message-Form">
                            <legend>歌手信息</legend>

                            <div class="form-group">
                                <input type="file" name="imageFile" id="singer-Image-file" class="form-control d-none">
                            </div>
                            <div class="form-group">
                                <label>歌手名</label>
                                <input type="text" name="name" id="singer-name" class="form-control" :value="cpsinger.name">
                            </div>
                            <div>
                            </div>
                            <div class="form-group">
                                <label>歌手简介</label>

                                <textarea name="introdution" id="intro-tea" class="bg-mybg form-control" rows="15" cols="10">{{cpsinger.introdution}}</textarea>
                            </div>
                            <button type="reset" id="singerMessge-re" class="d-none"></button>
                            <!-- <button type="button" id="singerMessge-re-over" class="btn btn-secondary">清除</button>   -->
                        </form>
                    </div>
                    <div class="col-4 d-flex">
                        <div class=" text-center m-auto">
                            <img :src="cpsinger.src" alt="" id="singer-Image-show" class="img-size">
                            <br>
                            <button class="btn btn-secondary mt-4" id="singer-Image-show-btn">选择歌手图片</button>
                        </div>

                    </div>
                </div>

                <div class="mt-3">
                    <button type="button" class="btn btn-primary" id="postSingerForm-btn">更新歌手信息</button>
                    <button class="btn btn-dark" id="backSingerSearch-btn">返回搜索页面</button>
                </div>

            </div>

            <div class="row" id="addSinger" style="display: none">

                <div class="col-8">
                    <form action="" method="POST" role="form" id="singer-Create-Form">
                        <legend>歌手信息</legend>

                        <div class="form-group">
                            <input type="file" name="imageFile" id="singer-Image-file-Create" class="form-control d-none">
                        </div>
                        <div class="form-group">
                            <label>歌手名</label>
                            <input type="text" name="name" id="singer-name-Create" class="form-control">
                        </div>
                        <div>
                        </div>
                        <div class="form-group">
                            <label>歌手简介</label>
                            <textarea name="introdution" class="bg-mybg form-control" rows="15" cols="10" ></textarea>
                        </div>
                        <button type="reset" class="btn btn-secondary d-none" id="c-form-re"></button>
                        <button type="button" class="btn btn-secondary" id="c-form-re-over">清除</button>
                    </form>

                </div>
                <div class="col-4 d-flex">
                    <div class=" text-center m-auto">
                        <img src="Image/default.jpg" alt="" id="singer-Image-show-Create" class="img-size change-bg">
                        <br>
                        <button class="btn btn-secondary mt-4" id="singer-Image-show-btn-Create">选择歌手图片</button>
                    </div>

                </div>

                <div class="mt-3 col-8">
                    <button type="button" class="btn btn-primary" id="add-Singer-btn">添加歌手信息</button>
                    <button class="btn btn-dark" id="backSingerSearch-btn-Create">返回搜索页面</button>
                </div>

            </div>

            <div id="singerSearch">
                <div class="search bg">
                    <form action="" method="post" class="">
                        <div class=" form-group row">
                            <div class="form-group col-8">
                                <input type="text" id="singerSearchstr" class=" form-control">
                            </div>
                            <div class="form-group col-3">
                                <input type="button" id="singerSearch-btn" value="搜索歌手" class="btn btn-primary form-control">
                            </div>
                        </div>
                    </form>
                </div>
                <div>

                    <div class="create-singer-item" >
                        <img src="Image/default.jpg" class="singer-image">
                        <h5>添加歌手</h5>
                    </div>

                    <div id="singer-Search-show">
                        <div v-if="hasSinger">
                            <div class="singer-item" v-for="(singer,index) in cpSingers" :data-singerid="singer.singerId" :data-index="index">
                                <img :src="singer.src" class="singer-image">
                                <h5>{{singer.name}}</h5>

                                <div class="select over-select">
                                    <ul>

                                        <a class="delete-singer"></a>

                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="noSinger" v-else>
                            <h1>没有匹配歌手</h1>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">

            <div class="search bg">
                <form action="" method="post" class="">
                    <div class=" form-group row">
                        <div class="form-group col-8">
                            <input type="text"  class=" form-control" id="userSearch">
                        </div>
                        <div class="form-group col-3">
                            <input type="button" value=" 搜索 " id="userSearch-btn"  class="btn btn-primary form-control">
                        </div>
                    </div>
                </form>
            </div>

            <style>

            </style>
            <div id="user-seach-show">

                <div v-if="hasUser">
                    <div class="user-item " v-for="user of cpusers" :data-userid="user.userId">
                        <img :src="user.src" class="user-iamge">
                        <h5>{{user.name}}<span :class="user.sexclass"></span></h5>

                        <div class="select over-select">
                            <ul>

                                <a class="delete-user"></a>

                            </ul>
                        </div>
                    </div>


                </div>

                <div class="noUser" v-else>
                    <h1>没有匹配用户</h1>
                </div>


            </div>




            <div class="modal fade" id="user-edit">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">编辑用户信息</h4>
                        </div>
                        <div class="modal-body" id="user-form-show">

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="userFormPost-btn">修改</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>


        <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
            <div class="File-select">
                <div id="showLrcFiles" class="showFiles">
                    <p v-for="file in cpfiles">歌词:&nbsp;{{file.name}}<br/>大小:&nbsp;{{file.Ksize}}KB</p>
                </div>
                <div class="tittle">
                    <h4>请选择文件上传(.lrc)</h4>
                    <form id="uploadlrcsForm" method="POST"  enctype="multipart/form-data">
                        <div class=" form-group">
                            <input type="file" name="lrcFiles" id="lrcFiles" multiple >
                        </div>

                    </form>
                    <div class="form-group">
                        <button type="reset" id="clearlrc" class="btn btn-dark">清除所选歌词</button>
                    </div>
                    <div class=" form-group">
                        <input type="button" value="上传"  id="uploadLrcBtn" class=" btn btn-primary">
                    </div>

                </div>

            </div>
        </div>

        <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
            <div id="songs-show">
                <div class="search">
                    <form action="" method="post" class="">
                        <div class=" form-group row">
                            <div class="form-group col-8">
                                <input type="text" v-model="searchString" id="searchstr" class=" form-control">
                            </div>
                            <div class="form-group col-3">
                                <input type="button" value=" 搜索 " id="searchbt" class="btn btn-primary form-control">
                            </div>
                        </div>


                    </form>
                </div>


                <div class="searchRT" id="searchRT">
                    <div class="operation"><button class="btn btn-dark" id="dlSelectedSongs">删除</button></div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox">
                            </th>
                            <th>歌手</th>
                            <th>歌名</th>
                            <th>专辑</th>
                            <th>时长</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="song of cpsonglist" v-bind:data-songid=song.songId>
                            <th>
                                <input type="checkbox">
                            </th>
                            <th>{{song.artist}}</th>
                            <th>{{song.title}}

                            </th>
                            <th>{{song.album}}
                                <div class="select">
                                    <ul>

                                        <a class="select-delete"></a>

                                    </ul>
                                </div>
                            </th>
                            <th>{{song.duration}}</th>
                        </tr>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>


        <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">


            <div class="File-select">
                <div  id="showMp3Files" class="showFiles">
                    <p v-for="file in cpfiles">歌曲:&nbsp;{{file.name}}<br/>大小:&nbsp;{{file.msize}}M</p>
                </div>
                <div class="tittle">
                    <h4>请选择文件上传(.mp3)</h4>
                    <form id="uploadSongsForm" method="POST"  enctype="multipart/form-data">
                        <div class=" form-group">
                            <input type="file" name="songsFiles" id="mp3Files" multiple accept=".mp3">
                        </div>
                        <div class=" form-group">
                            <button type="reset" id="clearmp3" class="btn btn-dark">清除选择歌曲</button>
                        </div>
                    </form>
                    <div class=" form-group">
                        <input type="button" value="上传"  id="uploadBtn" class=" btn btn-primary">
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>




<div class="modal fade" id="uploadMessage">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <h5 id="showMessage">正在上传请稍后......</h5>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>

</body>
</html>