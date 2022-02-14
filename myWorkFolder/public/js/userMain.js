'use strict'

 // ### nav hover이벤트 발생!!!
        const list_1 = document.querySelector('.list_1');
        const li = list_1.childNodes;

        for(let i=0; i<li.length; i++){
            li[i].addEventListener('mouseover',()=>{
                li[i].classList.add('real_nav_hover');
                li[i].style.transition = 'all 0.5s';
            })
            li[i].addEventListener('mouseleave', () => {
                li[i].classList.remove('real_nav_hover');
            })
        }
        // ### nav hover이벤트 발생!!! 마무리
        //스크롤바를 내리면 nav_menu가 변함!! 내가 원하는 속성으로!
        $(window).on('scroll',function(){
            //만약에 스크롤 위치가 100px일떄!!
            if($(window).scrollTop() >= 200){
                //scrollTop : top부터 얼만큼 scroll됐는지!!
                $('.logo').addClass('logo_change');
                $('.header_main').addClass('nav_change');
                $('.list_1 > *').addClass('real_nav_change');
                
            }else{
                //scrollTop이 200보다 작으면 다시 원래대로!!
                $('.logo').removeClass('logo_change');
                $('.header_main').removeClass('nav_change');
                $('.list_1 > *').removeClass('real_nav_change');
                
            }
        })
        
/** 캐러셀 이벤트!!!!! */
const carousel_box = document.querySelector('.carousel_box');
const shift = document.querySelectorAll('.shift');
//자식요소 전체 선택자도 썼음.. 위에!!!

for(let i=0; i<shift.length; i++){
    shift[i].addEventListener('mouseover',()=>{
        move((i * -100));
    })
}

function move(number){
    if(number === 0){
        carousel_box.style.transform = 'translateX(0vw)';
        carousel_box.style.transition = 'all 1s';
    }else if(number === -100){
        carousel_box.style.transform = 'translateX(-33.3%)';
        carousel_box.style.transition = 'all 1s';
    }else{
        carousel_box.style.transform = 'translateX(-66.6%)';
        carousel_box.style.transition = 'all 1s';
    }
}
        
        