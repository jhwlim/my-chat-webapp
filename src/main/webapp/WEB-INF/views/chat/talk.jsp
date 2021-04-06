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
    
    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility 
          = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
    }
    
    function connect() {
        socket = new SockJS('<c:url value="/ws/${toUser.id}" />');
        socket.onopen = function(evt) {
        	console.log(evt);
        	setConnected(true);
        };
        
        socket.onmessage = function(evt) {
        	showMessageOutput(evt.data);
        	console.log(evt);
        };
        
        socket.onclose = function(evt) {
        	console.log(evt);
        	disconnect();
        }
    }
    
    function disconnect() {
    	if (socket != null) {
	    	socket.close();    		
    	}
    	
    	setConnected(false);
        console.log("Disconnected");
    }
    
    
    function sendMessage(data) {
    	var receiver = ${toUser.seqId};
        var receiverId = "${toUser.id}";
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
    	
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message.senderId + ": " 
          + message.text + " (" + message.sendDateTime + ")"));
        response.appendChild(p);
    }
</script>
</head>
<body onload="connect();">
    <div>
        <div>
            <input type="text" id="from" placeholder="Choose a nickname"/>
        </div>
        <br />
        <div>
            <button id="connect" onclick="connect();">Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">
                Disconnect
            </button>
        </div>
        <br />
        <div id="conversationDiv">
            <input type="text" id="text" placeholder="Write a message..."/>
            <button id="sendMessage" onclick="sendMessage();">Send</button>
            <p id="response"></p>
        </div>
    </div>

</body>
</html>