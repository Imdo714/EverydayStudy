<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	
</head>
<body>
	
	<h1>채팅</h1>
         메세지 : <input type="text" name="msg"><br>
         수신자 : <input type="text" name="target">
    <button onclick="sendMsg();">전송</button>

	<br>
	<div id="msg-container"></div>

    <script>
        // socket연결 요청
        const socket = new WebSocket("ws://localhost:8777/study/chat");
		
        // socket연결 성공시
        socket.onopen = function(){
            console.log("웹 소켓 연결");
        }
	
        // socket연결 끊어졌을 시
        socket.onclose = function(){
            console.log("웹 소켓 끊어짐");
        }

        // socket연결 실패했을 시
        socket.onerror = function(){
            console.log("웹 소켓 연결 실패");
            alert("웹 소캣 연결 실패");
        }

        // socket연결로 부터 데이터가 도착했을때
        // 서버로부터 데이터가 도착했을 때 
        socket.onmessage = function(ev){
        	
        	const receive = JSON.parse(ev.data);
            
            const msgContainer = document.querySelector("#msg-container");
            msgContainer.innerHTML += (receive.name + "(" + receive.time + ") <br> " + receive.msg + "<br>");
        }   
        
        function sendMsg(){
        	
        	const msgData = {
        			message : document.querySelector("input[name=msg]").value,
        			target : document.querySelector("input[name=target]").value,
        	}
            
            socket.send(JSON.stringify(msgData));
        	
        	document.querySelector("input[name=msg]").value = "";
        }

    </script>
</body>
</html>