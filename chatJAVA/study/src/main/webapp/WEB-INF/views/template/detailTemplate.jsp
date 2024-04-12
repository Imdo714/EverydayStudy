<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/template/detailTemplate.css">
</head>
<body>
	   <!-- https://summernote.org/ -->
    <div class="writer">
        <h1>템플릿</h1>
    </div>

    <div class="in-btn">
        <button class="back-btn" onclick="theme()">Back</button>
        <button id="save" onclick="save()" class="cbtn">Save</button>
        <button id="edit" onclick="edit()" class="insertBtn">Update</button>
    </div>

    <div class="container">
        <div class="click2edit">
            요기에 작성한 글 올리기<br>
            &lt;button id="save" onclick="save()" class="cbtn">Save&gt;`<br>
            &lt;button id="edit" onclick="edit()" class="insertBtn">Update&gt;`
        </div>
    </div>
    


    <script>

        var edit = function() {
        $('.click2edit').summernote({
            width: 1400,
            height: 600, 
            focus: true});
        };

        var save = function() {
        var markup = $('.click2edit').summernote('code');
        $('.click2edit').summernote('destroy');
        };

    </script>
</body>
</html>