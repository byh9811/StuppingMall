/**
 * 상품 click하면
 * GET /notebooks/:id로 이동하는 fetch함수! 
 */
const card = document.querySelectorAll('.item__card');
const cardID = [];

card.forEach((value,index) => {
    cardID.push(value.getAttribute('data-id'));
    value.addEventListener('click',()=>{
        top8FetchAPI(index);
    })
})
function top8FetchAPI(index){
    fetch(`/notebooks/${cardID[index]}`)
    .then(() => {
        window.location.href = `/notebooks/${cardID[index]}`;
    })
    .catch((err) => console.log(err))
}