selectImageFile=(event)=>{
    $('#img-list').empty();
    let fileInput = $('#file-select')[0].files;
    for(let i = 0; i < fileInput.length;i++){
        let clone = $('#original').clone();
        clone.children(".input-img").attr("src",URL.createObjectURL(fileInput[i]));
        clone.children(".card-body").children(".img-title").attr("name","games["+i+"].title");
        clone.css("display","");
        $('#img-list').append(clone);
    }
}