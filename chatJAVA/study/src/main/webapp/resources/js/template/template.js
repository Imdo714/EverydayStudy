makeNote = () => {
    $('.summernote').summernote({
      placeholder: 'Hello stand alone ui',
        tabsize: 2,
        height: 500,

        callbacks:{
          onImageUpload : function(files, editor,	welEditable) {
            console.log("이미지 첨부 됨");
            console.log(files);
            for (var i = files.length - 1; i >= 0; i--) {
                console.log(files[i],this);
                sendFile(files[i],this);
            }
          },
          onMediaDelete : function ($target, editor, $editable) {
            var deletedImageUrl = $target

            .attr('src')  // $target.attr('src'): 삭제된 미디어 요소의 src 속성을 통해 삭제된 이미지의 URL을 가져옵니다.
            .split('/')   // .split('/'): URL을 / 기준으로 분할합니다.
            .pop()        // .pop(): 분할된 URL에서 마지막 요소를 가져옵니다. 이것은 파일의 이름이 될 것입니다.

            console.log(deletedImageUrl)

            data = new FormData()
            data.append('file', deletedImageUrl)
          
            console.log('aaaaa', data)

            // summernote에서 이미지 삭제시 실행할 함수 
            templateAjaxController.deleteFile2(data);
          }
        },
      });
}

sendFile = (file, editor) => {
  data = new FormData();
  data.append("file", file);
  console.log(data)

  templateAjaxController.insertTemplateImg(data, editor);
}

clickImg = (num) => {
  $("#fileImgFile" + num).click();
}


loadImg = (inputFile,num) => {
  if(inputFile.files.length == 1){ 
      // FileReader 객체를 생성합니다. FileReader 객체는 파일을 비동기적으로 읽는 데 사용됩니다.
      const reader = new FileReader(); 
      // FileReader 객체를 사용하여 선택된 파일을 읽습니다. readAsDataURL() 메서드를 사용하면 파일을 Data URL로 읽을 수 있습니다.
      reader.readAsDataURL(inputFile.files[0]);
      // 파일이 성공적으로 읽혀지면 이 부분의 코드가 실행됩니다. 
      reader.onload = function(ev){
          switch(num){
              case 1: document.getElementById('file-img1').src = ev.target.result; break;
          }
      }
  } else {
      switch(num){
          case 1: document.getElementById('file-img1').src = null; break;
      }  
  }
}

const name = []; // 전역 변수

const test = (data) => { // 파일 이름 받아오기
  console.log('제발', data)

  name.push(data)

  console.log('데이터가 저장되었습니다:', name);
}

subBtn = () => {
   // 입력 필드의 값을 가져옵니다.
   let inputFile = document.getElementById("fileImgFile1").value;
   let inputTitle = document.getElementById("title").value;
   let inputText = document.getElementById("content").value;

   // 입력 필드의 값이 공백이 아닌지 확인합니다.
   if (inputFile.trim() === "") {
       alert('썸네일을 기입하시오!');
       return false;
   } else if (inputTitle.trim() === "") {
       alert('제목을 작성하시오!');
       return false;
   } else if (inputText.trim() === "") {
       alert('내용을 입력하시오');
       return false;
   }

   // 추가 데이터를 포함한 FormData 객체 생성
   const form = document.getElementById("templateForm");
   const formData = new FormData(form);

   // name 배열을 formData에 추가
   name.forEach((data) => {
      formData.append(`name`, data);
  });

  //  templateAjaxController.formTemplate(formData, Resultform);

  $.ajax({
    data: formData,
    type: "POST",
    url: form.action,
    processData: false,
    contentType: false,
    success: (result) => {
        // 성공 처리
        if(result == 2 ){
          alert('템플릿 등록 성공!');
          location.href='main.te'
        } else {
          alert('템플릿 등록 실패!');
        }
    },
    error: (err) => {
        console.log(err);
    }
});

   // 폼의 기본 제출 동작을 막음
   return false;
}

clance = () => {
  alert('로그인 이후 사용이 가능합니다');
}

deleteTemplate = (templateNo) => { // 템플릿 삭제 
  console.log(templateNo)
  data = {
    templateNo : templateNo
  }

  templateAjaxController.delTemplate(data, res);
}

res = (result) => {
  console.log(result)
  if(result == 3 ){
    alert('템플릿 삭제 성공!');
    location.href='main.te'
  } else {
    alert('템플릿 삭제 실패!');
  }
}

