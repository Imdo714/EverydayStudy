checkNumber = (event) => {
    if(event.key === '.' 
        || event.key === '-'
        || event.key >= 0 && event.key <= 9) {
        return true;
    }
    
    return false;
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

passCheck = () => {
        const inputPwd = document.querySelector("#upPwd").value;
        const checkInputPwd = document.querySelector("#checkPwd").value;

        let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

        if (!reg.test(inputPwd)) {
            return "fail1";
        }

        if (inputPwd !== checkInputPwd) {
            return "fail12";
        }

        return "ok";
}

insertLogin = () => {
    const result = passCheck();

    const userId = document.querySelector("#upEmail").value;
    const userPwd = document.querySelector("#upPwd").value;
    const userName = document.querySelector("#upName").value;
    const phone = document.querySelector("#upPhone").value;
    const userBirth = document.querySelector("#upBirth").value;
    const gender = document.querySelector(".fmail input[name=gender]:checked").value;

    data = {
        userId : userId,
        userPwd : userPwd,
        userName : userName,
        phone : phone,
        userBirth : userBirth,
        gender : gender,
    }
    console.log(data)

    if(result === "ok"){
        if (data.userId !== '' && data.userPwd !== '' && data.userName !== '' && data.phone !== '' && data.userBirth !== '' && data.gender !== '' ) {
            memberAjaxController.insertMember(data,insertResult);
        } else {
            alert("빈 공백을 채우세요");
        }
    } else if (result === "fail1") {
        alert("비밀번호 형식에 맞추어 작성해주세요");
    } else {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    } 

}

insertResult = (result) => {
    if(result.model.message == "성공"){
        alert('성공적으로 회원가입이 완료되었습니다.');
        location.href = 'main.te';
    } else if(result.model.message == "아이디있음"){
        alert('이미 있는 아이디입니다.');
    } else {
        alert('회원가입 실패');
        location.href = 'main.te';
    }
}

login = () => {
    const userId = document.querySelector("#inEmail").value;
    const userPwd = document.querySelector("#inPwd").value;

    data = {
        userId : userId,
        userPwd : userPwd,
    }
    console.log(data)
    memberAjaxController.loginMember2(data,loginResult);
}

loginResult = (result) => {
    console.log(result)

    if(result.model.message == "로그인 성공"){
        alert('로그인 성공');
        location.href = 'main.te';
    } else {
        alert('로그인 실패');
        location.href = 'main.te';
    }
}