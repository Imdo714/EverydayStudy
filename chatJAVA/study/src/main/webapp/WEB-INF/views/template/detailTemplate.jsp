<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/template/insertTemplate.css">
	<script src="/study/resources/js/template/detailTemplate.js"></script>
	<script src="/study/resources/js/template/templateAjax.js"></script>
	<!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/study/resources/js/summernote/summernote-lite.js"></script>
    <script src="/study/resources/js/summernote/lang/summernote-ko-KR.js"></script>
    <link rel="stylesheet" href="/study/resources/css/summernote/summernote-lite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body onload="replyCommont(${tno})">
	
	<!-- https://summernote.org/ -->
    <div class="writer">
        <h1>템플릿</h1>
    </div>

<c:forEach var="p" items="${list}">
  <div class="btn-box">
    <div class="in-btn">
        <button class="back-btn" onClick="location.href='template.te'">Back</button>
        <c:choose>
        	<c:when test="${loginUser.userNo eq p.userNo}">
        	<button id="edit" onclick="edit()" class="insertBtn">Edit</button>
        		<button id="save" onclick="summerSave(${p.templateNo})" class="cbtn">Save</button>
        	</c:when>
        	<c:otherwise>
        	
        	</c:otherwise>
        </c:choose>
    </div>
  </div>

	<div class="detail-container">
		
			<div class="click2edit" id="saveDetail">${p.templateContent}</div>
		
	</div>


	<!-- 댓글 입력 -->
  <div class="comment-container">
    <div class="title">
      <p>Leave a Comment</p>
    </div>

    <div class="comment-teul">
      <div class="comment-area">
        <textarea class="text-commet" id="text-commet"></textarea>
      </div>
      <div class="comment-btn">
        <button class="submit-btn" onclick="reply('${p.templateNo}', '${tno}')">Submit</button>
      </div>
    </div>
  </div>
</c:forEach>
  
  	
	
	<!-- 댓글 -->
	<div class="comment-container" id="ReplyContent"></div>

	<!-- 페이징 바 -->
	<div class="navPage">
		<nav aria-label="Page navigation example" id="pagingArea">
			<ul class="pagination justify-content-center">
			
			</ul>
		</nav>
	</div>
	

</body>
</html>