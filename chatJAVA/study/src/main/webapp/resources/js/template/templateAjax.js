const templateAjaxController = {
    insertTemplateImg : (data, callback) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            // url은 자신의 이미지 업로드 처리 컨트롤러 경로로 설정해주세요.
            url : "insertTemplateImg.te",  
            contentType : false,
            processData : false,
            enctype : 'multipart/form-data',                                  
            // success : function(data) { // 처리가 성공할 경우
            //     // 에디터에 이미지 출력
            //      $(editor).summernote('editor.insertImage', data);
            // }
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },


}

