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
        

//이제 scroll event2
/**
 * 1. 얼마 스크롤이 됐을 때 event가 나타나는 코드
 * 2. 뭘 눌렀을 때 그 위치로 자동으로 scroll이 되는 코드!!
 * (자기소개서 페이지 만들 때 반드시 필요한 기술!!)
 * 
 * 변수 scope제대로 공부 안 하면 코드 더러워짐...
 * 
 */


const menu = document.querySelectorAll('.menu');
const content = document.querySelectorAll('.content');
//각 content의 top위치를 저장하는 변수를 만들어줘야함!!!
for(let i=0; i<content.length; i++){
    menu[i].addEventListener('mouseover',()=>{
        const topOffset = (content[i+1].offsetTop); //위치 저장!
        window.scroll({top : (topOffset), behavior : 'smooth'});
        //갔을 때!!!
    })
    menu[i].addEventListener('mouseleave',()=>{
        const topOffset = content[i+1].offsetTop; //위치 저장!
        window.scroll({top : (topOffset), behavior : 'smooth'});
    })
}


//원리 일단 동작을 시킬 요소들을 전부 연결한다.
/**
 * 2. 그 이동할 위치를 offsetTop함수를 이용해서 변수에 저장한다.
 * 3. 이벤트를 준다!!
 * 
 * ..4. 추가적으로 그 위치에 이동했을 때 classList.add를 이용해서.
 * 어떤 css animation을 줄 생각이다.
 */


/**
 * top8상품 animation
 * 
 */

const items = document.querySelectorAll('.items');
for(let i=0; i<items.length; i++){
    const item = items[i].childNodes; //자식들!!
    //[0 3]까지는 item[0]의 자식!!
    item[i].addEventListener('click',()=>{
        item[i].classList.add('change');
    })
}

