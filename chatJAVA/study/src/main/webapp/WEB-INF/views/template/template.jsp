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
</head>
<body>
	
	<jsp:include page="../common/header.jsp" />
	
	<div class="theme-title">
        <div class="big-title">임도현 템플릿</div>
        <div class="small-title">내가 CSS가 부족해서 열심히 만든거야</div>
    </div>

    <div class="select">
    <c:choose>
        	<c:when test="${empty loginUser}">
        		<button class="in-template" onClick="clance()">템플릿 작성</button>
        	</c:when>
        	<c:otherwise>
        		<button class="in-template" onClick="location.href='insertT.te'">템플릿 작성</button>
        	</c:otherwise>
        </c:choose>
        
    </div>

    <div class="select">
        <input type="radio" id="select1" name="shop" value="1" checked="checked"><label for="select1">All</label>
        <input type="radio" id="select2" name="shop" value="2" ><label for="select2">백앤드</label>
        <input type="radio" id="select3" name="shop" value="3"><label for="select3">프론트</label>
    </div>

    <div class="designer-container">
        <section class="video-grid">
	        <c:forEach var="p" items="${list}">
	            <div class="pro-container" onClick="location.href='detailTemplate.te?tno=${p.templateNo}'">
	                <img src="${p.templateChangName}" alt="프로필" class="pro-img">
	                <div class="desig-name"><strong> ${p.templateTitle} </strong></div>
	            </div>
			</c:forEach>
        </section>
    </div>

	<div class="navPage">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:choose>
					<c:when test="${pi.currentPage eq 1}">
						<li class="page-item disabled"><a class="page-link">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="template.te?cpage=${pi.currentPage - 1}">Previous</a></li>
					</c:otherwise>
				</c:choose>

				<c:forEach var="d" begin="${pi.startPage}" end="${pi.endPage}">
					<li class="page-item"><a class="page-link" href="template.te?cpage=${d}" id="text">${d}</a></li>
				</c:forEach>

				<c:choose>
					<c:when test="${pi.currentPage eq pi.maxPage}">
						<li class="page-item disabled"><a class="page-link">Next</a>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="template.te?cpage=${pi.currentPage + 1}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>

</body>
</html>