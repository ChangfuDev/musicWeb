<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/5/4
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
 $(function () {

        $(".slist-img-select input[type='file']").change(function (e) {
            e.preventDefault();
            var file=$("#imageFile")[0].files;
            var reader=new FileReader();
            reader.readAsDataURL(file[0]);
            reader.onload=function(e){
                //alert("文件读取完成");
                $(".slist-img").attr("src", e.target.result);

            }

        });
    });
</script>
<div class="slist-show form-group">
    <img src=<c:url value="http://localhost:8080/userImg/${user.image}"/> alt="" class="slist-img">
</div>
<form action="/musicweb/UserAlter?userId=${user.userId}" method="post" id="userForm" enctype="multipart/form-data" data-userid="${user.userId}">
    <div class="form-group slist-img-select">

        <label for="">选择图片上传 (PNG, JPG)</label>
        <input type="file" name="imageFile" id="imageFile" class="form-control" accept=".jpg, .jpeg, .png">

    </div>

    <div class="form-group">
        <label for="">用户名</label>
        <input type="text" class="form-control" name="name" value="${user.name}">
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
</form>
