<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/16
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <script  type="text/javascript"  src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/Login.css">
    <script src="js/Login.js"></script>
    <style>
        body{
            width: 100%;
            height: 100%;
            background-image: url(Image/Loginbg.jpg);
            background-size:cover;
            background-position: 100% 100%;
        }

    </style>


</head>

<body>
<div class="main">
    <div class="login_form">
        <form action="" method="POST" id="loginForm">
            <div class="form-group">
                <label>用户名</label>
                <input type="text" class="form-control" name="name" value="wjy">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="password" class="form-control" name="password" value="123456">
            </div>

            <button type="button" id="Login" class="btn btn-default btn-outline-info">登录</button>
            <button type="button" class="btn btn-default btn-outline-info" data-toggle="modal" data-target="#myModal">
                注册
            </button>
        </form>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content Transparent">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">用户注册</h4>
            </div>
            <div class="modal-body">

                <!--注册表单-->
                <form action="http://localhost:8080/musicweb/param" method="post" id="signForm" >
                    <div class="form-group">
                        <label for="">用户名</label>
                        <input type="text" class="form-control" name="name">
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <div class="form-group">
                        <label for="">性别</label>
                        <select class="form-control" name="sex" >
                            <option value="1">男</option>
                            <option value="0">女</option>

                        </select>

                    </div>
                    <div class="form-group">
                        <label>年龄</label>
                        <input type="number" class="form-control" name="age">

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="sign" type="button" class="btn btn-primary">注册</button>
                        <input type="reset" value="清除" class="btn btn-primary">
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content signsuccess Transparent">
            <h5>正在注册....</h5>
        </div>
    </div>
</div>
</body>
</html>
