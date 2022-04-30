const shift = document.querySelectorAll('.shift');
const carousel_container = document.querySelector('.carousel_container');

for(let i=0; i<shift.length; i++){
    shift[i].addEventListener('mouseover',()=>{
        move(i * -33.3);
    })
}
function move(number){
    //속성값 파라미터임!!
    carousel_container.style.transform = `translateX(${number}%)`;
    carousel_container.style.transition = `all 0.8s`;
}
// carousel js임!!

//이제 fixed된 sub_nav 조금 떨어지면 변하는 애니메이션 주기!!


$(window).on('scroll',function(){
    if( $(window).scrollTop() > 120){
        $('.sub_nav').addClass('nav_change');
    }else{
        $('.sub_nav').removeClass('nav_change');
    }
});
// 여기까지가 sub_manu 스크롤 시 변하는 동작!!!

const hover = document.querySelectorAll('.hover');

for(let i=0; i<hover.length; i++){
    //content의 top좌표를 변수에 저장해둔다!!!!
    hover[i].addEventListener('mouseover',()=>{
        const content = document.querySelectorAll('.content');
        const go = content[i].offsetTop; //content들의 offsetTop좌표를 전부 넣었음!!!
        const px = 700;
        
        window.scroll({top:go+px,behavior:'smooth'});
        
        //go-px의 위치에 도달했을 때 어떤 이벤트를 걸면 슥 하고 나타난다!!!!!
        //아주 중요한 이벤트 scroll이벤트!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    })
}
//이따 go위치에 도달하면 슥 되는 애니메이션 만들거라서 좌표를 변수에 넣어둔 거 상당히 중요.

/**
 * items ==> sticky
 */
/**
 * 그냥 비교할 수 있게 안 사라지게 하는 게 좋을 것 같음!!
 */


// const ex = document.querySelectorAll('.ex');
// const real = document.querySelectorAll('.real');
// for(let i=0; i<ex.length; i++){
//     ex[i].addEventListener('mouseover',()=>{
//         real[i].style.display = 'block';
//         real[i].style.transition = 'all 0.5s';
//     });
// }

//노트북 실제 객체!!!!! 하....ㅜ 이거 프론트단에서도 만들고싶은데.... 
//객체의 값을 웹에다가 띄울 수 없나.... 

const panel = document.querySelectorAll('.panel');
const carousel_3d = document.querySelector('.carousel_3d');

for(let i=0; i<panel.length; i++){
    panel[i].addEventListener('mouseover',()=>{
        //mouseover잘 돌아감!!!!
        here(i * -45 + 'deg');
        
    })

}

function here(deg){
    carousel_3d.style.transition = "all 1s";
    carousel_3d.style.transform = `rotateY(${deg})`;
}



// ======================= hover하면 속성 더 보이기!!!! =======================
const panel_box = document.querySelectorAll('.panel_box');

for(let i=0; i<panel_box.length; i++){
    panel_box[i].addEventListener('mouseover',()=>{
        // .look class를 추가해주세요!!!
        panel_box[i].className.add('look');
    })
    panel_box[i].addEventListener('mouseleave',()=>{
        // .look class를 추가해주세요!!!
        panel_box[i].className.remove('look');
    })
}
//toggle함수!!!!
// 