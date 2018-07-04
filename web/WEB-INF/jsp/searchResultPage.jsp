<%--
  Created by IntelliJ IDEA.
  User: WJY
  Date: 2018/4/12
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${songs}" var="song" begin="1" end="10">
    <tr>
        <th><input type="checkbox" name=""></th>
        <th><c:out value="${song.artist}"></c:out></th>
        <th><c:out value="${song.title}"></c:out>
        <th><c:out value="${song.title}"></c:out>
            <div class="select">
                <ul>
                    <li class="select-paly"></li>
                    <li class="select-add"></li>
                    <li class="select-down"></li>
                </ul>
            </div>
        </th>
        <th><c:out value="${song.album}"></c:out></th>
        <th><c:out value="${song.duration}"></c:out></th>
    </tr>
</c:forEach>