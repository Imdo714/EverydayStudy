checkNumber = (event) => {
    if(event.key === '.' 
        || event.key === '-'
        || event.key >= 0 && event.key <= 9) {
        return true;
    }
    
    return false;
}

Signin = () => {
    console.log('aaa')
    link = "";
    window.location.href= link;
}

SignUp = () => {
    link = "";
    window.location.href= link;
}



start = () => {
    const startButton = document.getElementById("start");
    const paragraphs = document.querySelectorAll(".first-section p");

    startButton.addEventListener("click", function() {
        // 이전 애니메이션 클래스 삭제
        paragraphs.forEach(paragraph => {
            paragraph.classList.remove("animate");
        });
        
        // 새로운 애니메이션 클래스 추가
        setTimeout(() => {
            paragraphs.forEach(paragraph => {
                paragraph.classList.add("animate");
            });
        }, 10);
    });
}
 
modalClose = () => {
    result = ''

    document.querySelector("#inEmail").value = result;
    document.querySelector("#inPwd").value = result;

    document.querySelector("#upEmail").value = result;
    document.querySelector("#upPwd").value = result;
    document.querySelector("#checkPwd").value = result;
    document.querySelector("#upName").value = result;
    document.querySelector("#upPhone").value = result;
    document.querySelector("#upBirth").value = result;
    $('#pwConfirm').text('*비밀번호 확인').css('color', 'red')
    $('#pwSign').text('*비밀번호는 영문 숫자 특수기호 조합 8자리 이상').css('color', 'red')
}

pwCheck = () => {
    let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/

    const password = document.getElementById('upPwd').value;
    const checkPWD = document.getElementById('checkPwd').value;

    
    if(password == checkPWD && password != ''){
        $('#pwConfirm').text('*비밀번호 일치').css('color', 'green')
    }else{
        $('#pwConfirm').text('*비밀번호 확인').css('color', 'red')
    }

    if (!reg.test(password)) {
        $('#pwSign').text('*비밀번호는 영문 숫자 특수기호 조합 8자리 이상').css('color', 'red')
    } else {
        $('#pwSign').text('*비밀번호는 영문 숫자 특수기호 조합 8자리 이상').css('color', 'green')
    }

    // if($('#upPwd').val() == $('#checkPwd').val()){
    //     $('#pwConfirm').text('*비밀번호 일치').css('color', 'green')
    // }else{
    //     $('#pwConfirm').text('*비밀번호 확인').css('color', 'red')
    // }
}

