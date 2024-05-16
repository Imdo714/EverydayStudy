<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/review/review.css">
	<script src="/study/resources/js/review/review.js"></script>
	<script src="/study/resources/js/review/reviewAjax.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	
	<jsp:include page="../common/header.jsp" />
	
    <div class="outer">
		<div class="notice-wrap">
			<div class="notice-header">

                <div class="container">
                    <h1>리뷰</h1>
                </div>

				<div id="board-search">
                    <div class="container">
                        <div class="search-window">
                                <div class="search-wrap">
                                    <label for="search" class="blind">공지사항 내용 검색</label>
                                    <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
                                    <button type="submit" class="btn btn-dark">검색</button>
                                </div>
                        </div>
                    </div>
                </div>
				<c:choose>
					<c:when test="${empty loginUser}">
						<div class="review-insert">
		                    <button type="button" onclick="reviewLogin()" class="btn-write">
		                        리뷰 작성
		                    </button>
		                </div>
					</c:when>
					
					<c:otherwise>
						<div class="review-insert">
		                    <button type="button" class="btn-write" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
		                        리뷰 작성
		                    </button>
		                </div>
					</c:otherwise>
				</c:choose>
                

			</div>
			<hr style="border: 1px color= silver;" width="100%">
			<div class="accordion" id="accordionExample">
				<c:forEach var="i" items="${list}">
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseOne${i.reviewNo}"
								aria-expanded="true" aria-controls="collapseOne${i.reviewNo}">
								${i.reviewTitle}</button>
						</h2>
						<div id="collapseOne${i.reviewNo}" class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="str-div">
									<div class="star">
										<c:choose>
											<c:when test="${i.reviewStar eq 1}">
												<span class="str-span">★</span>
											</c:when>

											<c:when test="${i.reviewStar eq 2}">
												<span class="str-span">★★</span>
											</c:when>

											<c:when test="${i.reviewStar eq 3}">
												<span class="str-span">★★★</span>
											</c:when>

											<c:when test="${i.reviewStar eq 4}">
												<span class="str-span">★★★★</span>
											</c:when>

											<c:when test="${i.reviewStar eq 5}">
												<span class="str-span">★★★★★</span>
											</c:when>
										</c:choose>
									</div>
								
									<div style="width: 10%;">
										<button onclick="reviewDel(`${i.reviewNo}`)" class="btn" style="background: #dc5454;">삭제</button>
									</div>
								</div>
		                        <br>
								${i.reviewContent} 
								<br>
								<c:choose>
									<c:when test="${i.reviewChangName == null || i.reviewChangName eq ''}">
										
									</c:when>
									
									<c:otherwise>
										<img src="${i.reviewChangName}" alt="" style="width: 150px; height: 150px;">
									</c:otherwise>
								</c:choose> 
								
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
    
    <!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">리뷰 작성하시오</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<form action="reviewinsert.re" class="mb-3" name="myform" id="myform" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<fieldset>
							<span class="text-bold">별점을 선택해주세요</span>
							<input type="radio" name="reviewStar" value="5" id="rate1"><label for="rate1">★</label>
							<input type="radio" name="reviewStar" value="4" id="rate2"><label for="rate2">★</label>
							<input type="radio" name="reviewStar" value="3" id="rate3"><label for="rate3">★</label>
							<input type="radio" name="reviewStar" value="2" id="rate4"><label for="rate4">★</label>
							<input type="radio" name="reviewStar" value="1" id="rate5"><label for="rate5">★</label>
						</fieldset>
	
						<div>
							<input id="reviewTitle" type="text" name="reviewTitle" placeholder="제목을 작성해주세요">
						</div>
	
						<div class="desUpimg">
							<img id="preview" />
							<label for="file">
								<div class="btn-upload">파일 업로드하기</div>
							</label>
							<input type="file" onchange="readURL(this)" name="file" id="file">
						</div>
	
						<div>
							<textarea class="col-auto form-control" type="text" id="reviewContents" name="reviewContent" placeholder="리뷰를 남겨주세요"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>