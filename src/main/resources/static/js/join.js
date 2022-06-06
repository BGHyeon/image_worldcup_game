
let isCheck = false;
function checkPw(){
    let pw = $('#login-pw');
    let pwCf = $('#login-pw-confirm');
    if(pw.val().length > 3 && pw.val().length > 3) {
        if (pw.val() === pwCf.val() && isCheck) {
            submitHidden(false);
        } else {
            submitHidden(true);
        }
    }
}
function getParameter(name){
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function submitHidden (hidden){
    let submit = $('#submit');
    if(hidden)
        submit.css("display","none");
    else
        submit.css("display","");
}
function idCheck(){
    let id = $('#login-id');

    $.ajax({
        url:'/member/'+id.val(),
        method:'POST',
        success:(response)=>{
            let res = JSON.parse(response)
            if(res['ret'] == 0) {
                isCheck = true;
            }else {
                isCheck = false;
            }
            alert(res['msg'])
            checkPw();
        }
    })
}
$(document).ready(()=>{
    $(window).keydown((event)=>{
        if(event.keyCode == 13){
            event.preventDefault();
            return false;
        }
    });
    let msg = getParameter("msg");
    if(msg != '')
        alert(msg);
})

