// 이미지 미리보기 함수
readURL = (input) => {
    console.log('aaaa');
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