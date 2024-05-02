makeNote = () => {
    $('.summernote').summernote({
        tabsize: 2,
        height: 500
      });

}

upTemplate = () => {
  const title = document.querySelector("#title").value;
  const content = document.querySelector("#content").value;

  console.log(title)
  console.log(content)
}