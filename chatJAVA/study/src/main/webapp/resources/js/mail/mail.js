keup = () => {
    let post = document.querySelector('#post').value;
    let title = document.querySelector('#title').value;
    let mail = document.querySelector('#text-mail').value;

    console.log(post);
    console.log(title);
    console.log(mail);  

    if(post == "" || title == "" || mail == ""){
        alert('작성을 하시오');
        return false;
    } else {
        return true;
    }
    
}