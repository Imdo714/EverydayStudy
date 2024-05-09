edit = () => {
    $('.click2edit').summernote({
        width: 1400,
        height: 600, 
        focus: true,
        callbacks:{
            onImageUpload : function(files, editor,	welEditable) {
              console.log("이미지 첨부 됨");
              console.log(files);
              for (var i = files.length - 1; i >= 0; i--) {
                  console.log(files[i],this);
                  updateFile(files[i],this);
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


updateFile = (file, editor) => {
    data = new FormData();
    data.append("file", file);
    console.log(data)

    templateAjaxController.insertTemplateImg(data, editor);
}

summerSave = (templateNo) => {
    let templateContent = $('.click2edit').summernote('code');

    data = {
        templateContent : templateContent,
        templateNo : templateNo
    }

    templateAjaxController.updateTemplate(data, updateT);
}

updateT = (result) => {
    if(result == 'success'){
        var markup = $('.click2edit').summernote('code');
        $('.click2edit').summernote('destroy');
    } else {
        alert('템플릿 수정에 실패하였습니다.');
    }
}

reply = (templateNo) => {
    let templateReplyContent = document.getElementById("text-commet").value;

    data = {
        templateReplyContent : templateReplyContent,
        templateNo : templateNo
    }

    templateAjaxController.replyInsert(data, replySucc);
}

replySucc = (result) => {
    console.log(result)
    if(result == 'success'){
        alert('댓글 달기 성공 !');
    } else {
        alert('템플릿 수정에 실패하였습니다.');
    }
}