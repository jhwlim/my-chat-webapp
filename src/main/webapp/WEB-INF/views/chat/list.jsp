<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/jstl.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/static/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/static/gnb.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/chat/list.css'/>">
</head>
<body>
<%@ include file="/WEB-INF/include/gnb.jspf" %>
<div class="container">
    <div class="search">
        <div class="search__figure search__figure--left">
            <img src="<c:url value='/resources/image/chat/search.png'/>" alt="" class="search__img">
        </div>
        <input type="text" placeholder="검색" class="search__input" spellcheck="false">
        <div class="search__figure search__figure--clear">
            <img src="<c:url value='/resources/image/chat/clear_white.png'/>" alt="" class="search__img">
        </div>
        <ul class="search__list">
            <li class="search__item">item1</li>
            <li class="search__item">item2</li>
            <li class="search__item">item3</li>
            <li class="search__item">item4</li>
            <li class="search__item">item5</li>
            <li class="search__item">item6</li>
            <li class="search__item">item7</li>
        </ul>
    </div>
    <div class="chat">
        <h2 class="chat__title">최근 대화 목록</h2>
        <ul class="chat__users">
            <li class="chat__user" ondblclick="location.href='/talk/test01;'">
                <div class="chat__figure">
                    <img src="<c:url value='/resources/image/chat/user1.png'/>" alt="" class="chat__img">
                </div>
                <div class="chat__info">
                    <div class="chat__info-row">
                        <span class="chat__id">test01</span>
                        <span class="chat__date">2021-03-18</span>
                    </div>
                    <div class="chat__info-row">
                        <span class="chat__name">테스트01</span>
                    </div>    
                </div>
            </li>
            <li class="chat__user">
                <div class="chat__figure" ondblclick="loation.href='/talk/test02;'">
                    <img src="<c:url value='/resources/image/chat/user1.png'/>" alt="" class="chat__img">
                </div>
                <div class="chat__info">
                    <div class="chat__info-row">
                        <span class="chat__id">test02</span>
                        <span class="chat__date">2021-03-20</span>
                    </div>
                    <div class="chat__info-row">
                        <span class="chat__name">테스트02</span>
                    </div>    
                </div>
            </li>
        </ul>
    </div>
</div>
<script src="<c:url value='/resources/js/chat/list.js'/>"></script>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
<script>
	function setSearchItemClickEvent(item) {
		var currentIndex = getCurrentIndexOfSearchItem();
        if (currentIndex >= 0) {
            removeSearchItemSelected(currentIndex);
        }
        setSearchItemSelected($(item).index());
        $('.search__input').focus();
	}
	
    $('.search__item').on('click', function() {
    	setSearchItemClickEvent(this);
    });

    var testList = [];
    for (var i = 0; i < 5; i++) {
        testList.push('new' + i);
    }

    
    $('.search__input').on('click', function() {
        var searchResult = $('.search__list');
        $(searchResult).html('');
        for (item of testList) {
            $(searchResult).append('<li class="search__item">' + item + '</li>'); 
        }
        $('.search__item').on('click', function() {
        	setSearchItemClickEvent(this);
        });
    });
</script>
</body>
</html>