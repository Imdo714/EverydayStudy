<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/study/resources/css/member/myPage.css">
<script src="/study/resources/js/common/login.js"></script>
<script src="/study/resources/js/common/loginAjax.js"></script>

</head>
<body onload="myLevel(`${loginUser.gender}`)">

<jsp:include page="../common/header.jsp" />
	
	<div class="containerMy">
        <div class="head-background">
            <div class="head-container">
                <div class="name-section">
                    <h1>임도현</h1>
                </div>
                <div class="name-section" id="level-section">
                    
                </div>
                <div class="name-section">
                    <div class="boxMy">
                        <h2 class="b-tMy">레벨 올리는 법</h2>
                        <div class="header-boxMy">
                            <li>Lv.1 회원가입</li>
                            <li>Lv.2 댓글2, 템플릿1</li>
                            <li>Lv.3 댓글10, 템플릿2</li>
                            <li>Lv.4 댓글15, 템플릿5</li>
                            <li>Lv.5 댓글20, 템플릿10 작성</li>
                        </div>
                    </div>
                </div>
            </div>

            <div class="img-section">
	            <c:choose>
					<c:when test="${empty loginUser}">
						<img src="/study/resources/img/빵빵이.jpg" alt="" class="profile">
					</c:when>
					<c:otherwise>
						<img src="${memberImg.memberImgUrl}" alt="" class="profile">
					</c:otherwise>
				</c:choose>
            </div>
        </div>
	</div>


    <div class="my-container">
        <div class="update-page">
            <button type="button" class="update-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">
                프로필 수정하기
            </button>
    
            <button type="button" onClick="location.href='myTemplate.te'" class="update-btn" style="background-color: rgb(174, 243, 249);">
                내 템플릿
            </button>
    
            <button type="button" onclick="logout()" class="update-btn" style="background-color: rgb(228, 65, 65);">
                로그아웃
            </button>
    
            <!-- Button trigger modal -->
            <button type="button" class="update-btn" style="background-color: rgb(192, 232, 83);" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                디자이너 등록하기
            </button>
        </div>
    </div>

    

	<!-- 프로필 수정 Modal -->
	<div class="modal fade" id="exampleModal" data-bs-backdrop="static"
		tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="exampleModalLabel">프로필 변경</h2>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<div class="modal-body">
				<form action="myUpdate.me" method="post"  enctype="multipart/form-data">
					<div class="update-information">
						<div class="desUpimg">
							<img id="preview" />
							<label for="file">
								<div class="btn-upload">파일 업로드하기</div>
							</label> 
							<input type="file" onchange="readURL(this)" name="upfile" id="file">
						</div>

						<input type="text" name="userId" value="${user.userId}" placeholder="아이디"> 
						<input type="password" name="userPwd" value="" placeholder="비밀번호"> 
						<input type="text" name="userName" value="${user.userName}" placeholder="이름"> 
						<input type="text" name="phone" value="${user.phone}" placeholder="전화번호"> 
						<input type="date" name="userBirth" value="${user.userBirth}" placeholder="생년월일">
						<div class="select">
		                    <input type="radio" id="upMail" name="gender" value="남자" ><label for="upMail">남자</label>
		                    <input type="radio" id="upFmail" name="gender" value="여자"><label for="upFmail">여자</label>
		                </div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Send message">
				</div>
				</form>
			</div>
		</div>
	</div>



	<!-- 디자이너 등록 Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">디자이너 등록</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="desUpimg">
                    <img id="preview"/>

                    <label for="file">
                        <div class="btn-upload">파일 업로드하기</div>
                    </label>
                    <input type="file" onchange="readURL(this);" name="file" id="file">
                </div>

                <div>
                    <div>
                        <textarea class="col-auto form-control" type="text"
                            placeholder="한줄 소개를 작성하시오">
                        </textarea>
                    </div>
                    <div class="select">
                        <input type="radio" id="backand" name="shop" value="" ><label for="backand">백앤드</label>
                        <input type="radio" id="fornt" name="shop" value=""><label for="fornt">프론트</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Understood</button>
            </div>
        </div>
        </div>
    </div>
	

</body>
</html>