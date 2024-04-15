<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/main/main.css">
	<link rel="stylesheet" href="/study/resources/css/main/background.css">
	<link rel="stylesheet" href="/study/resources/css/main/under.css">
	<script src="/study/resources/js/main/main.js"></script>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

	<jsp:include page="common/header.jsp" />


    <div class="background-size">
        <div class="background-teul">
            <div class="background-text">
                    <p>
                        "Votre considers the diverse lifestyles of women, offering a variety<br>
                        of sizes and styles. We aim to share in your various moments,<br>
                        helping women express their beauty even more."<br><br>
                        votre
                    </p>
            </div>
        </div>

        <div class="main-title">
            <h1>VOTRE</h1>
        </div>
    </div>

    <main>
            <div class="main-size">
                <div class="first-section">
                    <p>Start Your Brand</p>
                </div>
                <div class="secend-section">
                    <p>나의 시작과 성장을 위한 모든 것</p>
                </div>
                <div class="secend-section">
                    <button class="start-btn" id="start" onclick="start()">시작하기</button>
                </div>
            </div>
            
            <section class="video-grid">
                <div class="title-advertisement">
                    <div class="advertisement-size">
                        <p class="titile-font">왜, 많은 사람들은 <br>imdo714를 찾아오는걸까?</p>
                    </div>
                    <div class="advertisement-size" style="height: 40px;">
                        <p class="titile-font2">우리는 누구나 쉽게 <br> 따라할 수 있도록 만들었거든.</p>
                    </div>
                    <div class="advertisement-size" style="height: 160px;">
                        <button class="start-btn" id="stbtn" onclick="stBtn()">시작하기</button>
                    </div>

                    <div class="img-text">
                        <p>모두 미디어쿼리(반응형)이네 <br>화면을 줄여도 css가 깨지지가 않아</p>
                    </div>
                </div>
    
                <div class="sube-advertisement">
                    <div class="img-teul">
                        <img src="/study/resources/img/바다.jpeg" class="img-advertisement">
                    </div>
                </div>
            </section>
    </main>


    <nav class="section">
        <section class="video-grid">
            <div class="left-Relation">
                <p>Follow imdo714 as we create special moments together.</p>
                <div class="back-Relation">
                    <p>@Template</p>
                </div>
            </div>

            <div class="right-Relation">
                <section class="video-grid2">
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                    <div class="relation-teul">
                        <img src="/study/resources/img/빵빵이.jpg" alt="" class="relation-img">
                    </div>
                </section>
            </div>
        </section>
    </nav>


    <nav>
        <div class="under">
            <div class="under-size">
                <p class="under-font">많은 개발자가 선택하는 이유, <br>직접 경험하세요</p>
            </div>

            <div class="under-size">
                <p class="under-font2">imdo714에서 둘러보고 자극을 받고<br> 성정시켜 보세요</p>
            </div>

            <div class="under-foot">
                <section class="video-grid2">
                    <div class="under-section">
                        <h1>1M</h1>
                        <h2>누적 사이트 이용 수</h2>
                        <p>누적 사이트 이용 수</p>
                    </div>

                    <div class="under-section">
                        <h1>1M</h1>
                        <h2>누적 사이트 이용 수</h2>
                        <p>누적 사이트 이용 수</p>
                    </div>

                    <div class="under-section">
                        <h1>1M</h1>
                        <h2>누적 사이트 이용 수</h2>
                        <p>누적 사이트 이용 수</p>
                    </div>
                </section>
            </div>
        </div>
    </nav>

</body>
</html>