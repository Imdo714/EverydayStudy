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



choicePage = (page, tno) =>{ // 페이징 번호 바뀌는 메서드 

    data = {
        tpage : page,
        tno : tno
    }

    console.log(data)
    templateAjaxController.onloadReply(data, replySucc)
}

reply = (templateNo, tno) => { // 댓글 작성 하는 메서드
    let templateReplyContent = document.getElementById("text-commet").value;

    data = {
        templateReplyContent : templateReplyContent,
        templateNo : templateNo,
        tno : tno
    }

    templateAjaxController.replyInsert(data, replySucc);
}

replyCommont = (tno) => { // onload 디테일뷰 들어오는 순간 댓글 페이징 바 그려주는 메서드

    data = {
        tno : tno
    }
    templateAjaxController.onloadReply(data, replySucc);
}

delReply = (replyNo, tno) => { // 댓글 삭제
    console.log(replyNo)
    console.log(tno)

    data = {
        templateReplyNo : replyNo,
        templateNo : tno
    }
    templateAjaxController.replyDel(data, replySucc);
}

editReply = (replyNo, tno, templateReplyContent) => { // 댓글 수정
    console.log(replyNo)
    console.log(tno)
    console.log(templateReplyContent)
}

replySucc = (result) => {
    // console.log(result)
    let list = result.model.ReplyList;
    let loginUser = result.model.userNo;
    let pi = result.model.pi;
    let tno = result.model.tno;

    // 댓글 그려주기
    let str = "";
    for (let r of list) {
        if(loginUser === r.userNo){
            str += `<div class="comment-container">`
                    + `<div class="reply-container">`
                    + `<div class="profile">`
                        + `<img src="`+ r.memberImgChangName +`" alt="">`
                    + `</div>`
                    + `<div class="reply-center">`
                        +`<div class="name-container">`
                        +`<div class="name-container">`
                            +`<h3 style="font-size: 1.5rem;">`+ r.userName +`</h3>`
                            +`<p>` + r.templateReplyDate +`</p>`
                        +`</div>`
                        +`<div class="btn-container">`       //JavaScript 함수에 전달된 문자열은 따옴표로 감싸져야 함 그렇지 않으면 JavaScript는 이를 변수나 함수 호출로 인식하려고 시도하며, 해당 변수나 함수가 정의되지 않았기 때문에 undefined가 반환됩니다.
                            +`<button class="edit-btn" onclick="editReply(${r.templateReplyNo}, ${r.templateNo}, '${r.templateReplyContent}')">edit</button>`
                            +`<button class="del-btn" onclick="delReply(`+ r.templateReplyNo + `,`+ r.templateNo + `)">delete</button>`
                        +`</div>`
                        +`</div>`
                
                        +`<div class="reply-comment">`
                        +`<span>` + r.templateReplyContent +`</span>`
                        +`</div>`
                    +`</div>`
                    +`</div>`
                +`</div>`;
        } else {
            str += `<div class="comment-container">`
            + `<div class="reply-container">`
            + `<div class="profile">`
                + `<img src="`+ r.memberImgChangName +`" alt="">`
            + `</div>`
            + `<div class="reply-center">`
                +`<div class="name-container">`
                +`<div class="name-container">`
                    +`<h3 style="font-size: 1.5rem;">`+ r.userName +`</h3>`
                    +`<p>` + r.templateReplyDate +`</p>`
                +`</div>`
                +`</div>`
        
                +`<div class="reply-comment">`
                +`<span>` + r.templateReplyContent +`</span>`
                +`</div>`
            +`</div>`
            +`</div>`
        +`</div>`;
        }
    }

    // 페이징 바 그려주기
    let str2 = "";
    
        if(pi.currentPage == 1){
            str2 += '<li class="page-item disabled"><a class="page-link">Previous</a></li>'
        } else {
            str2 += `<li class="page-item"><a class="page-link" onclick="choicePage(`+ (pi.currentPage - 1 ) + `,` + tno + `)">Previous</a></li>`
        }

        for (let i = pi.startPage; i <= pi.endPage; i++) {
            str2 += '<li class="page-item"><button class="page-link" onclick="choicePage('+ i + `,` + tno  +')">' + i + '</button></li>'
        }

        if(pi.currentPage != pi.maxPage){
            str2 += '<li class="page-item"><button class="page-link" onclick="choicePage('+ (pi.currentPage + 1)+ `,` + tno +')">Next</button></li>'
        } else {
            str2 += '<li class="page-item disabled"><a class="page-link">Next</a>'
        } 

        document.querySelector("#ReplyContent").innerHTML = str;
        document.querySelector("#pagingArea ul").innerHTML = str2;

        result = ''
        document.getElementById("text-commet").value = result;
}

