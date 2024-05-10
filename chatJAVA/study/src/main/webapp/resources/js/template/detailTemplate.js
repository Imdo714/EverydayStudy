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

replyCommont = (tno) => {
    console.log(tno)
    data = {
        tno : tno
    }
    templateAjaxController.onloadReply(data, replySucc);
}

const replyFilterValue = {
    tpage : 1
}

choicePage = (page, tno) =>{
    console.log(tno)
    console.log(page)
    replyFilterValue.tpage = page;
    console.log(replyFilterValue)

    var url = 'detailTemplate.te?tno=' + tno + '&tpage=' + replyFilterValue.tpage;
    location.href = url; // 계속 currentPage가 1로 들어감 수정해야 함 
    // data = {
    //     tpage : replyFilterValue,
    //     tno : tno
    // }
    // console.log(data)
    // templateAjaxController.ReplyList(data, replySucc)
}

replySucc = (result) => {
    console.log(result)
    let list = result.model.ReplyList;
    let loginUser = result.model.userNo;
    let pi = result.model.pi;
    let tno = result.model.tno;
    
    let str = "";
    for (let r of list) {
        if(loginUser === r.userNo){
            str += `<div class="comment-container">`
                    + `<div class="reply-container">`
                    + `<div class="profile">`
                        + `<img src="/study/resources/img/빵빵이.jpg" alt="">`
                    + `</div>`
                    + `<div class="reply-center">`
                        +`<div class="name-container">`
                        +`<div class="name-container">`
                            +`<h3 style="font-size: 1.5rem;">`+ r.userName +`</h3>`
                            +`<p>` + r.templateReplyDate +`</p>`
                        +`</div>`
                        +`<div class="btn-container">`
                            +`<button class="edit-btn">edit</button>`
                            +`<button class="del-btn">delete</button>`
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
                + `<img src="/study/resources/img/빵빵이.jpg" alt="">`
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
            // str2 += '<li class="page-item"><button class="page-link" onclick="choicePage(' + (pi.currentPage - 1 ) + ')">Previous</button></li>'
            // str2 += `<li class="page-item"><a class="page-link" href="detailTemplate.te?tno=` + tno + `&tpage=`+ (pi.currentPage - 1) +`"</a></li>`
            str2 += `<li class="page-item"><a class="page-link" onclick="choicePage(`+ (pi.currentPage - 1 ) + `,` + tno + `)" </a></li>`
        }

        for (let i = pi.startPage; i <= pi.endPage; i++) {
            str2 += '<li class="page-item"><button class="page-link" onclick="choicePage('+ i + `,` + tno  +')">' + i + '</button></li>'
            // str2 += `<li class="page-item"><a class="page-link" href="detailTemplate.te?tno=`+ tno +`&tpage=`+ i +`" id="text">` + i + `</a></li>`
            // str2 += `<li class="page-item"><a class="page-link" href="detailTemplate.te?tno=`+ tno +`&tpage=`+ i +`" id="text">` + i + `</a></li>`
        }

        if(pi.currentPage != pi.maxPage){
            str2 += '<li class="page-item"><button class="page-link" onclick="choicePage('+ (pi.currentPage + 1)+ `,` + tno +')">Next</button></li>'
            // str2 += `<li class="page-item"><a class="page-link" href="detailTemplate.te?tno=`+ tno +`&tpage=`+ (pi.currentPage + 1)+`">Next</a></li>`
        } else {
            str2 += '<li class="page-item disabled"><a class="page-link">Next</a>'
        } 

        
        document.querySelector("#ReplyContent").innerHTML = str;
        document.querySelector("#pagingArea ul").innerHTML = str2;

        result = ''
        document.getElementById("text-commet").value = result;
}

