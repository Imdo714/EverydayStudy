<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/study/resources/css/template/template.css">
<script src="/study/resources/js/template/template.js"></script>
<script src="/study/resources/js/template/templateAjax.js"></script>

</head>
<body>

<jsp:include page="../common/header.jsp" />

	<div class="designer-container">
        <section class="video-grid">
	        <c:forEach var="m" items="${list}">
		        <div class="pro-container">
		            <div  onClick="location.href='detailTemplate.te?tno=${m.templateNo}'">
		                <img src="${m.templateChangName}" alt="프로필" class="pro-img">
		                <div class="desig-name"><strong> ${m.templateTitle} </strong></div>
		            </div>
		            
		            <div class="btn-manager">
	                    <button class="del-btn" onclick="deleteTemplate(`${m.templateNo}`)">삭제</button>
	                </div>
	            </div>
			</c:forEach>
        </section>
    </div>
</body>
</html>