const memberAjaxController = {
    insertMember : (data, callback) =>{
        $.ajax({
            url: "insert.me",
            type: "post",
            data,
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

    loginMember2 : (data, callback) =>{
        $.ajax({
            url: "loginMember.me",
            type: "post",
            data,
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },
}