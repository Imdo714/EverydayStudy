<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="/study/resources/css/template/insertTemplate.css">
</head>
<body>
	<div class="writer">
        <h1>템플릿 작성</h1>
    </div>

    <div class="in-btn">
        <button class="back-btn" onClick="location.href='../index.html'">Back</button>
        <button class="insertBtn">UP Load</button>
        <button class="cbtn">Cancel</button>
    </div>
    
	
	<div id="summernote" class="summernote"></div>

    <script>
        $('#summernote').summernote({
          placeholder: '원하시는 문구를 작성하시오',
          tabsize: 2,
          height: 600,
          toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
          ]
        });
  
          var edit = function() {
          $('.click2edit').summernote({focus: true});
          };
  
          var save = function() {
          var markup = $('.click2edit').summernote('code');
          $('.click2edit').summernote('destroy');
          };
  
      </script>
	
</body>
</html>