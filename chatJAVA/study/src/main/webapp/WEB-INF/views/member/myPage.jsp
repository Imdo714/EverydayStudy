<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/study/resources/css/member/myPage.css">
</head>
<body>
	<div class="container">
        <div class="head-background">
            <div class="head-container">
                <div class="name-section">
                    <h1>임도현</h1>
                </div>
                <div class="name-section">
                    <h1>Lv. 3</h1>
                </div>
                <div class="name-section">
                    <div class="box">
                        <h2 class="b-t">레벨 올리는 법</h2>
                        <div class="header-box">
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
                <img src="/study/resources/img/빵빵이.jpg" alt="" class="profile">
            </div>
        </div>
	</div>


    <div class="my-container">
        <div class="my-project">
            <div class="head-project">
                <div class="body-project" style="font-weight: bolder;">
                    템플릿
                </div>
                <div class="body-project">
                    10 건
                </div>
                <div class="foot-project">
                    <a onClick="location.href='myTemplate.me'">내 템플릿 보러가기</a>
                </div>
            </div>

            <div class="head-project">
                <div class="body-project" style="font-weight: bolder;">
                    댓글
                </div>
                <div class="body-project">
                    10 건
                </div>
                <div class="foot-project">
                    <a onClick="location.href='myComment.me'">내 댓글 보러가기</a>
                </div>
            </div>
        </div>
    </div>

    <div class="update-page">
        <Button class="update-btn">프로필 수정</Button>
    </div>

</body>
</html>