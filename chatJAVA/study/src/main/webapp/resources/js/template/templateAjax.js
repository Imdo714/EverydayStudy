const templateAjaxController = {
    insertTemplateImg : (data, editor) =>{
        $.ajax({
            data : data,
            type : "POST",
            url : "insertTemplateImg.te",  
            contentType : false,
            processData : false,
            enctype : 'multipart/form-data',   
            success: function (data) { // 처리가 성공할 경우
                console.log(data)
                // 에디터에 이미지 출력
                $(editor).summernote('editor.insertImage', data);
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    deleteFile2 : (data, callback) =>{
        $.ajax({
            data : data,
            type : "POST",
            url : "deleteTemplateImage.te",  
            contentType : false,
            processData : false,
            enctype : 'multipart/form-data',   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    updateTemplate : (data, callback) =>{
        $.ajax({
            data : data,
            type : "POST",
            url : "updateTemplate.te",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    replyInsert : (data, callback) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            url : "repltInsert.te",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    onloadReply : (data, callback) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            url : "onloadReply.te",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    ReplyList : (data, callback) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            url : "ReplyList.te",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

}

