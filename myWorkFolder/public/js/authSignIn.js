'use strict';

    const menu = document.querySelector(".main_logo");
        //메뉴 아이콘 연결
    const main = document.querySelector(".main");
    //메인메뉴!
    
    menu.addEventListener("click",()=>{
        main.classList.toggle('active');
        //뭐지...
    })