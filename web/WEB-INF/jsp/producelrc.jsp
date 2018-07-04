<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import='com.musicweb.fileOperation.lycRead' %>

<div>
    <c:forEach items="${lyc}" var="line">
		<p ><span><c:out value="${line.min}"/></span><c:out value="${line.lyc}"/></p>
	</c:forEach>
</div>
