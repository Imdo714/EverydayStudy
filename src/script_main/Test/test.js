loadImg = (inputFile,num) => {

    if(inputFile.files.length == 1){ 
        const reader = new FileReader();
        reader.readAsDataURL(inputFile.files[0]);
        reader.onload = function(ev){
            switch(num){
                case 1: document.getElementById('file-img1').src = ev.target.result; break;
                case 2: document.getElementById('file-img2').src = ev.target.result; break;
                case 3: document.getElementById('file-img3').src = ev.target.result; break;
                case 4: document.getElementById('file-img4').src = ev.target.result;  
            }
            
        }
    } else {
        switch(num){
            case 1: document.getElementById('file-img1').src = null; break;
            case 2: document.getElementById('file-img2').src = null; break;
            case 3: document.getElementById('file-img3').src = null; break;
            case 4: document.getElementById('file-img4').src = null;  
        }  
    }
    }