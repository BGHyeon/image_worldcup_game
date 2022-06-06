// let sessionStorage = window.sessionStorage;
let items = []
let idx= 0;
let winList = [];
let counter = 0;
// 이미지 클릭시 winList으로 클릭한 item push
// items 에 있는 게임을 모두 진행 하였다면 winList를 items으로 복사후 초기화 한다.
// 만약 승자가 나왔으면 그결과를 서버로 보내고 결과 페이지로 이동한다.
winsWorldCupItem=(isLeft)=>{
    let index = isLeft? idx:idx +1;
    winList.push(items[index]);
    idx += 2;
    if(idx >= items.length-1){
        idx = 0;
        items =  Object.assign([], winList);
        counter = 0;
        winList.length = 0;
        items.sort(()=>Math.random() - 0.5);
    }
    if(items.length == 1)
        winGame(items[0]);
    else
        shiftImage(idx);
    setRoundText();
}
// 이미지를 교체하기 위한 Logic
shiftImage=(idx)=>{
    let leftImg = $('#img-left');
    let leftDcs = $('#img-dsc-left');
    let rightImg = $('#img-right');
    let rightDcs = $('#img-dsc-right');
    counter++;
    leftImg.attr("src","/image/"+items[idx].id);
    leftDcs.text(items[idx].title);
    rightImg.attr("src","/image/"+items[idx+1].id);
    rightDcs.text(items[idx+1].title);
}
// 게임을 승리하였을때 Logic
winGame=(item)=>{
    $.ajax({
        url:'/world-cup-game/'+item.id,
        method:'POST',
        success:(response)=>{
            if(response == 'save'){
                window.location.href='/world-cup-game/result/'+item.id
            }
        }
    })

}
// Round 이름 변경하기
setRoundText=()=>{
    $('#worldcup-round').text(' '+getRoundTitle()+' ('+String(items.length/2) +' / ' + String(counter)+')')
}
// 4개팀이 남았을때 준결승전, 2팀만 나왔을땐 결승전을 표시힌다.
getRoundTitle=()=>{
    if(items.length > 4)
        return String(items.length)+'강';
    else if(items.length  == 4)
        return '준결승전';
    else
        return '결승전';
}
// 페이지가 준비되면 input에 담긴 Items를 받아서 JSON으로 파싱, items에 저장한다.
$(window).ready(()=>{
    let tmp = $('#gamedata').val();
    items = JSON.parse(tmp);
    // sessionStorage.setItem("items",JSON.parse(tmp));
    shiftImage(0);
    setRoundText();
});



