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

