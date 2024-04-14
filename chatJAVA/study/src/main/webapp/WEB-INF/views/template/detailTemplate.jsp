<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/template/insertTemplate.css">
	 <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/study/resources/js/template/detailTemplate.js"></script>
    <script src="/study/resources/js/summernote/summernote-lite.js"></script>
    <script src="/study/resources/js/summernote/lang/summernote-ko-KR.js"></script>
    <link rel="stylesheet" href="/study/resources/css/summernote/summernote-lite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	
	<!-- https://summernote.org/ -->
    <div class="writer">
        <h1>템플릿</h1>
    </div>

    <div class="in-btn">
        <button class="back-btn" onClick="location.href='main.te'">Back</button>
        <button id="save" onclick="save()" class="cbtn">Save</button>
        <button id="edit" onclick="edit()" class="insertBtn">Update</button>
    </div>

    <div class="detail-container">
        <div class="click2edit">
            요기에 작성한 글 올리기<br>
            &lt;button id="save" onclick="save()" class="cbtn">Save&gt;`<br>
            &lt;button id="edit" onclick="edit()" class="insertBtn">Update&gt;`
        </div>
    </div>
    
    



</body>
</html>