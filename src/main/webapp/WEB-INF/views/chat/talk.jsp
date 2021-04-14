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
    	
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message.from + ": " 
          + message.text + " (" + message.time + ")"));
        response.appendChild(p);
    }
</script>
</head>
<body onload="connect();">
    <div>
        <div id="conversationDiv">
            <input type="text" id="text" placeholder="Write a message..."/>
            <button id="sendMessage" onclick="sendMessage();">Send</button>
            <p id="response"></p>
        </div>
    </div>

</body>
</html>