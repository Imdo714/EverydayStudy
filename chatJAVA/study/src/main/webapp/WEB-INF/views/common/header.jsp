<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/main/header.css">
	<script src="/study/resources/js/common/login.js"></script>
	<script src="/study/resources/js/common/loginAjax.js"></script>
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	
</head>
<body>
	<header class="header">
        <div class="main-log">
            <div class="box1">
                <strong onClick="location.href='main.te'">imdo714</strong>
                <div class="header-box1">
                    <li>
                    	<c:choose>
							<c:when test="${empty loginUser}">
								<!-- Button trigger modal -->
					            <button type="button" class="login-btn"data-bs-toggle="modal" href="#exampleModalToggle" role="button">
		                        	로그인
		                        </button>
							</c:when>
							<c:otherwise>
								 <img src="/study/resources/img/user.png" class="succimg" alt="" onClick="location.href='myPage.me'">
							</c:otherwise>
						</c:choose>
                        
                    </li><br>
                    <li><button class="midea-btn" onClick="location.href='template.te'">템플릿</button></li> <br>
                    <li><button class="midea-btn" onClick="location.href='review.te'">고객리뷰</button></li> <br>
                    <li><button class="midea-btn" onClick="location.href=''">챗 봇</button></li> <br>
                    <li><button class="midea-btn" onClick="location.href='designer.di'">디자이너 찾기</button></li> <br>
                    <li><button class="midea-btn" onClick="location.href='mail.ml'">관리자 메일</button></li> <br>
                </div>
            </div>
        </div>
        <div class="left-section">
            <strong onClick="location.href='main.te'">imdo714</strong>

            <div class="box">
                <a class="b-t">주요기능</a>
                <div class="header-box">
                    <li>JAVA</li> <br>
                    <li>Eclipse</li> <br>
                    <li>Spring</li> <br>
                    <li>HTML/CSS/JS</li> <br>
                    <li>Oracle</li> <br>
                    <li>Jquery</li> <br>
                    <li>github</li> <br>
                    <li>카카오 API</li> <br>
                    <li>apache-tomcat 8.5</li>
                </div>
            </div>

            <button class="head-btn" onClick="location.href='template.te'">템플릿</button>
            <button class="head-btn" onClick="location.href='review.te'">고객리뷰</button>
            <button class="head-btn" onClick="location.href=''">챗 봇</button>
            <button class="head-btn" onClick="location.href='designer.di'">디자이너 찾기</button>
        </div>

        <div class="right-section">
            <button class="head-btn" onClick="location.href='mail.ml'">관리자 메일</button>
            <c:choose>
				<c:when test="${empty loginUser}">
					<!-- Button trigger modal -->
		            <button type="button" class="login-btn"data-bs-toggle="modal" href="#exampleModalToggle" role="button">
		                로그인
		            </button>
				</c:when>
				<c:otherwise>
					 <img src="/study/resources/img/user.png" alt="" class="succimg" onClick="location.href='myPage.me'">
				</c:otherwise>
			</c:choose>
            
         </div>
    </header>
    
    
    <!-- 1번째 모달 -->
    <div class="modal fade" id="exampleModalToggle" data-bs-backdrop="static" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-centered modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-body">
              <div class="main-container">
                    <div class="img-container">
                        <img src="/study/resources/img/바다.jpeg" alt="" class="log-img">
                    </div>
                    <div class="form-container">
                        <div class="top-title">
                            <h1>Sign in</h1>
                            <span>or use your email for registration</span>
                        </div>
                        <div class="input-container">
                            <!-- <form action="loginMember.me" method="post"> -->
                                <input type="email" id="inEmail" placeholder="Email" />
                                <input type="password" id="inPwd" placeholder="Password" />
                                <button class="Sign-btn" onclick="login()">Sign In</button>
                                <button class="SignUp-btn" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal" data-bs-dismiss="modal">Sign Up</button>
                                <a href="">아이디/비번 찾기</a>
                            <!-- </form> -->
                        </div>
                    </div>
                    <button type="button" class="btn-close" onclick="modalClose()" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 2번째 모달 -->
      <div class="modal fade" id="exampleModalToggle2" data-bs-backdrop="static" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-centered modal-dialog-scrollable">
          <div class="modal-content">

            <div class="modal-body">
                <div class="main-container">
                   
                    <div class="form-container">
                        <div class="top-title">
                            <h1>Sign Up</h1>
                            <span>or use your email for registration</span>
                        </div>
                        <div class="input-container">
                            <input type="email" id="upEmail" name="userId" placeholder="Email" />
                            <input type="password" id="upPwd" name="userPwd" class="form-control" oninput="pwCheck()" placeholder="Password" />
                            <div id="checkResult2" class="text-pwd"><span id="pwSign">*비밀번호는 영문 숫자 특수기호 조합 8자리 이상</span></div>

                            <div id="checkResult3" class="text-pwd" id="textCheck"><span id="pwConfirm">*비밀번호 확인</span></div>
                            <input type="password" id="checkPwd" oninput="pwCheck()" placeholder="Check Password" />

                            <input type="text" id="upName" name="userName" placeholder="Name" />
                            <input type="text" id="upPhone" name="phone" onkeypress='return checkNumber(event)' placeholder="Phone" />
                            <input type="date" id="upBirth" name="userBirth" placeholder="Birth" />
                            
                            <div class="fmail">
                                <input type="radio" id="gender1" name="gender" value="남자" checked="checked"><label for="gender1">남자</label>
                                <input type="radio" id="gender2" name="gender" value="여자"><label for="gender2">여자</label>
                           </div>

                            <button class="Sign-btn" onclick="insertLogin()">Sign Up</button>
                            <button class="SignUp-btn" data-bs-target="#exampleModalToggle" data-bs-toggle="modal" data-bs-dismiss="modal">Sign In</button>
                            <a href="">아이디/비번 찾기</a>
                        </div>
                    </div>

                    <button type="button" class="btn-close" onclick="modalClose()" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>