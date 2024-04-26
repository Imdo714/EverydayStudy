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
 
stBtn = () => {
    element = document.getElementById("stbtn");
    paragraph = document.querySelector(".img-text p"); // 수정된 부분: querySelector를 사용하여 p 태그 선택

        element.addEventListener("click", function(e) {
            e.preventDefault();
            
            paragraph.classList.remove("bounce"); // 수정된 부분: paragraph 변수를 사용하여 클래스 제거 및 추가
            
            // reflow 강제하여 애니메이션을 재생합니다.
            void paragraph.offsetWidth;
            
            paragraph.classList.add("bounce"); // 수정된 부분: paragraph 변수를 사용하여 클래스 제거 및 추가
        });
}

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



