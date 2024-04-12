<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/template/template.css">
</head>
<body>
	<div class="theme-title">
        <div class="big-title">임도현 템플릿</div>
        <div class="small-title">내가 CSS가 부족해서 열심히 만든거야</div>
    </div>

    <div class="select">
        <button class="in-template" onClick="location.href='../insertTemplate/insertTemplate.html'">템플릿 작성</button>
   </div>

    <div class="select">
        <input type="radio" id="select1" name="shop" value="" checked="checked"><label for="select1">All</label>
        <input type="radio" id="select2" name="shop" value="" ><label for="select2">백앤드</label>
        <input type="radio" id="select3" name="shop" value=""><label for="select3">프론트</label>
   </div>

    <div class="designer-container">
        <section class="video-grid">
            <div class="pro-container" onclick="Template()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>

            <div class="pro-container" onclick="Template()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>

            <div class="pro-container" onclick="Template()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>

            <div class="pro-container" onclick="see()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>

            <div class="pro-container" onclick="see()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>

            <div class="pro-container" onclick="see()">
                <img src="/study/resources/img/빵빵이.jpg" alt="프로필" class="pro-img">
                <div class="desig-name"><strong>임도현 스튜디오</strong></div>
            </div>
        </section>
    </div>
</body>
</html>