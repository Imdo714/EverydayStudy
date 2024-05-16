const reviewAjaxController = {
    reviewDelete : (data, callback) =>{
        $.ajax({
            data : data,
            type : "POST",
            url : "deleteReview.re",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },

}

