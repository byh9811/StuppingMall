/** carousel FETCH js => GET /notebooks/:id */
const carousel = document.querySelectorAll('.carousel__item');
const carouselID = [];
carousel.forEach((value,index) => {
    carouselID.push(value.getAttribute('data-id'));
    value.addEventListener('click', ()=>{
        // console.log(carouselID);
        fetchAPI(carouselID[index]); //id를 인자로하는 fetch!
    })
})

function fetchAPI(id){
    fetch(`/notebooks/${id}`)
    .then(() => {
        window.location.href = `/notebooks/${id}`;
    })
    .catch((err) => console.log(err));
}

/** carousel Sliding ==> notebooks */
const carouselSlide = document.querySelector('.carousel__container');
const arrow = document.querySelectorAll('.arrow');
arrow.forEach((value,index) => {
    value.addEventListener
    ('mouseover',()=>{
        carouselMove(-(index * 33.33) + '%')
    })
})
function carouselMove(index){
    carouselSlide.style.transition = 'all 0.5s';
    carouselSlide.style.transform = `translateX(${index})`;
}

/** carousel Sliding ==> advantages */
const advantages = document.querySelector('.advantage__container');
const adArrow = document.querySelectorAll('.advantage__arrow');
adArrow.forEach((value,index) => {
    value.addEventListener
    ('mouseover',()=>{
        adCarouselMove(-(index * 25) + '%')
    })
})
function adCarouselMove(index){
    advantages.style.transition = 'all 0.5s';
    advantages.style.transform = `translateX(${index})`;
}

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

// category click 해서 fetch로 보내기!
const itemList = document.querySelectorAll('.item > p');
const item = document.querySelectorAll('.item');
const category = document.querySelector('.input__box');
let categoryAJAX = ['supplier','cpu','gpu','date'];
const categoryList = [];
itemList.forEach((value,index) => {
    categoryList.push(value.innerHTML);
})
// console.log(categoryList); 

item.forEach((value,index) => {
    value.addEventListener('click', ()=>{
        categoryFetchAPI(index);
    })
})
function categoryFetchAPI(index){
    //카테고리 값을 보내는데 성공! => 이제 그걸 가지고 html로! 
    fetch(`/categories/${categoryAJAX[index]}`) //요청
    .then((response) => {
        return response.text();
    })
    .then((text) => {
        category.innerHTML = text; 
    })
}