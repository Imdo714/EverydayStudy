<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/mail/insertMail.css">
</head>
<body>
	
    <nav class="sidebar">
        <div class="sidebar-link" onClick="location.href='insertMail.html'">
           <div>메일 쓰기</div> 
        </div>
        <div class="sidebar-link" onClick="location.href='mail.html'">
            <div>메일 내역</div> 
        </div>
        <div class="sidebar-link" onclick="keup()">
            <div>메일 보내기</div> 
        </div>
        <div class="sidebar-link" onClick="location.href='../index.html'">
            <div>돌아가기</div> 
        </div>
    </nav>

    <div class="table-container">
        <table class="board-table">
            <thead>
            <tr>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>받는 사람</td>
                <th><input type="email" id="post"></th>
            </tr>
            <tr>
                <td>제목</td>
                <th><input type="text" id="title"></th>
            </tr>
    
            <tr>
                <td>파일첨부</td>
                <th><input type="file"></th>
            </tr>
            </tbody>
        </table>
    </div>


    <div class="text-section">
        <textarea name="" id="text-mail" class="text-mail"></textarea>
    </div>

    <Script>
        keup = () => {
            let post = document.querySelector('#post').value;
            let title = document.querySelector('#title').value;
            let mail = document.querySelector('#text-mail').value;

            console.log(post);
            console.log(title);
            console.log(mail);  

            if(post == "" && title == "" && mail == ""){
                alert('작성을 하시오');
            }
        }
        
    </Script>
</body>
</html>