makeNote = () => {
    $('.summernote').summernote({
      placeholder: 'Hello stand alone ui',
        tabsize: 2,
        height: 500,

        callbacks:{
          onImageUpload : function(files, editor,	welEditable) {
            console.log("이미지 첨부 됨");
            console.log(files);
            for (var i = files.length - 1; i >= 0; i--) 
              {
                console.log(files[i],this);
                sendFile(files[i],this);
              }
          }
        },

      });

}


// form으로 데이터 보내기(get, post), queryString(주소창에 값 실어 보내기... get)
sendFile = (file,editor) => {
		
  // const sendData = new FormData();  // key:value  <form><input type="file" name="uploadFile"></form>
  // sendData.append("uploadFile",file);
  // console.log(sendData);

  data = new FormData();
  data.append("file", file);
  console.log(data)

  templateAjaxController.insertTemplateImg(data, resImgUP);
}

resImgUP = (result) => {
  console.log(result)
  $(editor).summernote('editor.insertImage', data); // 이미지 성공하면 이미지 보여주는거 
}
