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

                .attr('src')
                .split('/')
                .pop()

                // $target.attr('src'): 삭제된 미디어 요소의 src 속성을 통해 삭제된 이미지의 URL을 가져옵니다.
                // .split('/'): URL을 / 기준으로 분할합니다.
                // .pop(): 분할된 URL에서 마지막 요소를 가져옵니다. 이것은 파일의 이름이 될 것입니다.
                console.log(deletedImageUrl)

                data = new FormData()
                data.append('file', deletedImageUrl)
              
                console.log('aaaaa', data)

            // summernote에서 이미지 삭제시 실행할 함수 
            templateAjaxController.deleteFile2(data, fileDele);
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

fileDele = (result) => {
  console.log(result)
}