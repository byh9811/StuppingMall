'use strict';
//carousel animation
const carousel = document.querySelector('.carousel');
const shift = document.querySelectorAll('.shift');
shift.forEach((value, index)=>{
    value.addEventListener
    ('mouseover',()=>{
        carouselMove(-(index * 33.33) + '%')
    })
})
function carouselMove(number){
    carousel.style.transform = `translateX(${number})`;
    carousel.style.transition = 'all 0.5s';
}


// const shift =
// carousel_item을 누르면 id가 전달되는 fetch만들자!
// item_detail로 한 번에!
const carouselItem = document.querySelectorAll('.carousel_item');
let carouselDataIdArr = [] // 요소의 data-id를 넣는 array!
// 이 아이디를 이제... query문에다가 넣어서 리디렉션을 해주는 과정!
let top8IdArr = [];
const top8Button = document.querySelectorAll('.top8_button');
top8Button.forEach((value,index)=>{
    top8IdArr.push(value.getAttribute('data-id'));
    //value.getAttribute('')로 data-id값을 가져온다!
    value.addEventListener
    ('click', ()=>{
        top8FetchAPI(index)
    })
})
function top8FetchAPI(index){ //fetch매우 중요!! ==> 이걸로 이제 ajax까지!!
    let url = '/user/item-detail';
    fetch(url)
        .then(()=>{
            window.location.href = url + `?id=${top8IdArr[index]}`; //추가로 쿼리문 보낼 수 있음!!
        })
        .catch((err) => console.log(err))
}
carouselItem.forEach((value,index)=>{
    carouselDataIdArr.push(value.getAttribute('data-id'));
    //value.getAttribute('')로 data-id값을 가져온다!
    value.addEventListener
    ('click', ()=>{
        fetchAPI(index)
    })
})

function fetchAPI(index){ //fetch매우 중요!! ==> 이걸로 이제 ajax까지!!
    let url = '/user/item-detail';
    fetch(url)
        .then(()=>{
            window.location.href = url + `?id=${carouselDataIdArr[index]}`; //추가로 쿼리문 보낼 수 있음!!
        })
        .catch((err) => console.log(err))
}
