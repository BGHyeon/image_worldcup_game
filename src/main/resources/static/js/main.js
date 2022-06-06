toggleCollapse=(id)=>{
    console.log(id);
    $('#'+id).collapse('toggle');
}
toGamePage=(id)=>{
    window.location.href='/worldcup/'+id;
}