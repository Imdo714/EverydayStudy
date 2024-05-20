<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../common/header.jsp" />
	
	<h1>채팅</h1>

         메세지 : <input type="text" name="msg"><br>
         수신자 : <input type="text" name="target">
    <button onclick="sendMsg();">전송</button>

	<br>
	<div id="msg-container"></div>

    <script>
        // socket연결 요청
        const socket = new WebSocket("ws://localhost:8777/study/server");

        // socket연결 성공기
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