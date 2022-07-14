'use strict';

//category toggle
const categoryBox = document.querySelector('.category__container');
const categoryLogo = document.querySelector('.category__logo');
const categoryX = document.querySelector('.close__button');
categoryLogo.addEventListener('click', categoryOpen);
categoryX.addEventListener('click', categoryClose);
function categoryOpen(){
    categoryBox.classList.add('appear');
    categoryBox.classList.remove('disappear');
}
function categoryClose(){
    categoryBox.classList.remove('appear');
    categoryBox.classList.add('disappear');
}

//category hover하면 하나씩 나오기!
//기존에는 없어짐! 
const list = document.querySelectorAll('.category__list > .list');
const content = document.querySelectorAll('.category__contents > .content')
/** 일단 모든 content들을 숨겨놓기 => list hover하면 1개1개 나타남. */
//서로 다른 요소에 addEvent 등록하기!
//list[0]과 content[0]에 있을 때 계속 나타나야해!! 
// click한 것만 보여주고 나머지는 disppear
content.forEach((value,index) => {
   value.style.display = 'none';
})
//선택한 것에만 원하는 속성 주고 그게 아닐경우에는 다른속성주기!
list.forEach((value,index) => {
    value.addEventListener('click',()=>{
        appear(index, ...content);
    })
})
function appear(index, ...arr){ //index와 배열받아! 
    for(let i=0; i<arr.length; i++){
        if(index === 0){
            arr[0].style.display = 'block';
            arr[1].style.display = 'none';
            arr[2].style.display = 'none';
            arr[3].style.display = 'none';
        }
        else if(index === 1){
            arr[0].style.display = 'none';
            arr[1].style.display = 'block';
            arr[2].style.display = 'none';
            arr[3].style.display = 'none';
            
        }
        else if(index === 2){
            arr[0].style.display = 'none';
            arr[1].style.display = 'none';
            arr[2].style.display = 'block';
            arr[3].style.display = 'none';
        }else{
            arr[0].style.display = 'none';
            arr[1].style.display = 'none';
            arr[2].style.display = 'none';
            arr[3].style.display = 'block';
        }
    }
}