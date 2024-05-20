 // socket연결 요청
 const socket = new WebSocket("ws://localhost:8777/study/chatting.ch");

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