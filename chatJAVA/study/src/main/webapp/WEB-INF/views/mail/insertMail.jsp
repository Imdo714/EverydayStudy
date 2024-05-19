<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/mail/insertMail.css">
	<script src="/study/resources/js/mail/mail.js"></script>
</head>
<body>

<form action="sendM.ml" method="post"  onsubmit="return keup()">	
    <nav class="sidebar">
        <div class="sidebar-link" onClick="location.href='insertM.ml'">
           <div>메일 쓰기</div> 
        </div>
        <div class="sidebar-link" onClick="location.href='mail.ml'">
            <div>메일 내역</div> 
        </div>
        <div class="sidebar-link" >  <!-- apykey.js에서 꺼내 와 -->
            <div>
            	<input type="submit" class="send-btn" value="작성">
            </div> 
            
        </div>
        <div class="sidebar-link" onClick="location.href='main.te'">
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
	                <th><input type="email" name="mailGetName" id="post"></th>
	            </tr>
	            <tr>
	                <td>제목</td>
	                <th><input type="text" name="mailTitle" id="title"></th>
	            </tr>
	    
	            <tr>
	                <td>내용</td>
	            </tr>
	            </tbody>
	        </table>
	    </div>
	
	
	    <div class="text-section">
	        <textarea name="mailContent" id="text-mail" class="text-mail"></textarea>
	    </div>
	</form>
</body>
</html>