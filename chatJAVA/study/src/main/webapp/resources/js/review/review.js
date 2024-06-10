chatting// 이미지 미리보기 함수
readURL = (input) => {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
        document.getElementById('preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
}

reviewLogin = () => {
    alert('로그인을 하시오!');
}

reviewDel = (reviewNo) => {
    const reviewNoInt = parseInt(reviewNo); // 문자열을 정수로 변환
    data = {
        reviewNo : reviewNoInt
    }
    reviewAjaxController.reviewDelete(data, deleteReview);
}

deleteReview = (result) => {
    if(result == 1){
        alert('리뷰 삭제 성공!');
        location.href='main.te'
    } else {
        alert('리뷰 삭제 실패!');
    }
}