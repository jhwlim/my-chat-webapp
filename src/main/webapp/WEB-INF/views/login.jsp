<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/jstl.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="resources/css/static/reset.css">
<link rel="stylesheet" href="resources/css/static/index.css">
</head>
<body>
<div class="container">
    <div class="login">
        <h1 class="login__title">TALK</h1>
        <form action="<c:url value='/login'/>" method="post">
            <input type="text" name="id" placeholder="아이디" class="login__input">
            <input type="submit" value="로그인" class="login__input login__input--submit">
        </form>
    </div>
</div>
</body>
</html>