<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/mail/mail.css">
</head>
<body>
	<div class="top-container">
        <div class="img-section">
            <img src="/src/img/보건소.png" alt="">
        </div>
        <div class="img-section">
            <p>Email</p><p style="color: white;">Magic</p>
        </div>
    </div>


    <nav class="sidebar">
        <div class="sidebar-link" onClick="location.href='insertMail.html'">
           <div>메일 쓰기</div> 
        </div>
        <div class="sidebar-link" onClick="location.href='mail.html'">
            <div>메일 내역</div> 
        </div>
        <div class="sidebar-link" onClick="location.href='../index.html'">
            <div>돌아가기</div> 
        </div>
    </nav>

    <div class="table-container">
        <table class="board-table">
            <thead>
            <tr>
                <th scope="col" class="th-num">번호</th>
                <th scope="col" class="th-title">제목</th>
                <th scope="col" class="th-date">등록일</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>3</td>
                <th style="text-align: center;">[공지사항] 개인정보 처리방침 변경안내처리방침</th>
                <td>2017.07.13</td>
            </tr>
    
            <tr>
                <td>2</td>
                <th style="text-align: center;">공지사항 안내입니다. 이용해주셔서 감사합니다</th>
                <td>2017.06.15</td>
            </tr>
    
            <tr>
                <td>1</td>
                <th style="text-align: center;">공지사항 안내입니다. 이용해주셔서 감사합니다</th>
                <td>2017.06.15</td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>