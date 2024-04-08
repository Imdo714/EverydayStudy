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

