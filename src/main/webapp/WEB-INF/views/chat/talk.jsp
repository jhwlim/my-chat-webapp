<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Chat WebSocket</title>
<script src="<c:url value = '/resources/js/sockjs-0.3.4.js'/>"></script> 
<script type="text/javascript">
    var socket = null;
 
    function connect() {
        socket = new SockJS('<c:url value="/ws/${chatRoomId}" />');
        socket.onopen = function(evt) {
        	console.log("connected");
        };
        
        socket.onmessage = function(evt) {
        	showMessageOutput(evt.data);
        };
        
        socket.onclose = function(evt) {
        	console.log("Disconnected");
       }
    }
    
    function sendMessage(data) {
    	var text = document.getElementById('text').value;
    
        socket.send(JSON.stringify({"text" : text}));
    }
    
    function showMessageOutput(messageOutput) {
    	var message = JSON.parse(messageOutput);
    	
        var response = document.getElementById('conversation');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message.senderId + ": " 
          + message.text + " (" + message.sendDateTime + ")"));
        response.prepend(p);
    }
    
    window.onload = function() {
    	connect();
    }
</script>
</head>
<body>
<div class="conversation" id="converation"></div>
<div id="send">
	<input type="text" id="text" placeholder="Write a message..."/>
    <button id="sendMessage" onclick="sendMessage();">Send</button>        
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	
	$(document).ready(function() {
		
		var data = {
			pageIndex : 0,
		};
		
		$.ajax({
			url : '<c:url value="/message" />',
			data : data,
			success : function(result) {
				console.log(result);
				
				// 1. 불러온 데이터 html에 추가하기
				// 2. 스크롤 올렸을 때 데이터 더 가져오기
			}
		});
	});
	
</script>

</body>
</html>