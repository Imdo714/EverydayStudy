<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/study/resources/css/template/insertTemplate.css">
    <script src="/study/resources/js/template/template.js"></script>
    <script src="/study/resources/js/template/templateAjax.js"></script>
    <script src="/study/resources/js/summernote/summernote-lite.js"></script>
    <script src="/study/resources/js/summernote/lang/summernote-ko-KR.js"></script>
    <link rel="stylesheet" href="/study/resources/css/summernote/summernote-lite.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body onload="makeNote()">
	
 

	<div class="writer">
        <h1>템플릿 작성</h1>
    </div>
    
	<form action="insertTem.te" method="post">
	    <div class="in-btn">
	        <button class="back-btn" onClick="location.href='main.te'">Back</button>
	        <input type="submit" value="작성" class="insertBtn">
	        <button class="cbtn">Cancel</button>
	    </div>
	    
	    <div class="insert-container">
		    <div class="form-group">
		        <label for="title">Title</label> 
		        <input type="text" class="form-control" placeholder="Enter title" name="templateTitle" id="title" >
		    </div>
		    
		    <div class="select">
		        <input type="radio" id="back" name="categoryNo" value="1" ><label for="back">백앤드</label>
		        <input type="radio" id="and" name="categoryNo" value="2"><label for="and">프론트</label>
		    </div>
		    
		    <div class="form-group">
		        <label for="content">Content</label> 
		        <textarea class="form-control summernote" rows="5" cols="" name="templateContent" id="content"></textarea>
		    </div>
	    </div>
	</form>



</body>
</html>