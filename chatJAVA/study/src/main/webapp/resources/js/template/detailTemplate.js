save = () => {
    var markup = $('.click2edit').summernote('code');
    $('.click2edit').summernote('destroy');
}

edit = () => {
    $('.click2edit').summernote({
        width: 1400,
        height: 600, 
        focus: true});
}