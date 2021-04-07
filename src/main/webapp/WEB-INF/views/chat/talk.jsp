<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/jstl.jspf" %>
<!DOCTYPE html>
<html>
<head>
<title>Chat WebSocket</title>
<script src="<c:url value = '/resources/js/sockjs-0.3.4.js'/>"></script> 
<script src="<c:url value = '/resources/js/stomp.js'/>"></script>
<script type="text/javascript">
    var socket = null;
    
    function connect() {
        socket = new SockJS('<c:url value="/ws/${toUser.id}" />');
        socket.onopen = function(evt) {
        	console.log(evt);
        };
        
        socket.onmessage = function(evt) {
        	showMessageOutput(evt.data);
        	console.log(evt);
        };
        
        socket.onclose = function(evt) {
        	console.log(evt);
        }
    }
    
    function sendMessage(data) {
    	var receiver = ${receiver.seqId};
        var receiverId = "${receiver.id}";
    	var text = document.getElementById('text').value;
        
        data = {
        	"receiver" : receiver,
        	"receiverId" : receiverId,
        	"text" : text,
        };
        
        socket.send(JSON.stringify(data));
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
	connect();	
	
	// 이전 채팅 내역 가져오기
	var data = {
		receiver : ${receiver.seqId},
		pageIndex : 0
	};
	
	$.ajax({
		url : '<c:url value="/message" />',
		data : data,
		success : function(result) {
			console.log(result);
		}
	});
});

</script>
</body>
</html>