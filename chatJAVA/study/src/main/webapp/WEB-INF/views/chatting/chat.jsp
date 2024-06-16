<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/study/resources/css/chatting/chat.css">
<script src="/study/resources/js/chatting/chatting.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>
	
	<h1>채팅</h1>
         <!-- 메세지 : <input type="text" name="msg"><br> -->
         수신자 : <input type="text" name="target">
    <button onclick="sendMsg();">전송</button>
	
	<div class="wrap" id="msg-container">
        <div class="chat ch1">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. 무엇이든 물어 봐주세요.</div>
        </div>
        
        <div class="chat ch2">
            <div class="icon"><i class="fa-solid fa-user"></i></div>
            <div class="textbox">안녕하세요. </div>
        </div>
    </div>

    <div>
        <div class="aaa">
            <input type="text" name="msg" class="send-input">
            <button class="send-btn" onclick="sendMsg();">전송</button>
        </div>
    </div>
	
	<br>
	<div id="msg-container"></div>

	<script src="/study/resources/js/chatting/chatting.js"></script>
    <!-- <script>
        // socket연결 요청
        const socket = new WebSocket("ws://localhost:8777/study/chat");
        console.log("소켓" + socket);
        
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
            // msgContainer.innerHTML += (receive.name + "(" + receive.time + ") <br> " + receive.msg + "<br>");
            msgContainer.innerHTML += `<div class="chat ch1">`
                                    +`<div class="icon"><i class="fa-solid fa-user"></i></div>`
                                    +`<div class="textbox">`+receive.msg+`</div>`
                                    +`</div>`
        }   
        
        function sendMsg(){
        	
        	const msgData = {
        			message : document.querySelector("input[name=msg]").value,
        			target : document.querySelector("input[name=target]").value,
        	}
            const msgContainer = document.querySelector("#msg-container");

            socket.send(JSON.stringify(msgData));
        	
        	document.querySelector("input[name=msg]").value = "";
        }

    </script> -->
</body>
</html>