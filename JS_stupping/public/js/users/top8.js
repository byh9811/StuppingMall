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



/** 정렬순 ajax로 끝내버리기! */
// option을 클릭하면 fetch ajax로 /url을 찾는다!
// 서버에서 값을 find().sort(입력받은 값).limit(8)해서 return하면
// then에서 이제 페이지 처리를 해준다!
let cardSort = document.querySelectorAll('.item__card');
let set = document.querySelectorAll('.item__card .title');
set.forEach((value,index) => {
    value.addEventListener('mouseover',()=>{
        console.log(index);
    })
})
//title 제대로 선택이 됨! 

function changeSelect(){
    const sort = document.querySelector('#sort__input');
    
    fetch(`/top8/${sort.options[sort.selectedIndex].value}`)
    .then(( response ) => {
        return response.json();
    })
    .then((data) => { // arr하나에 객체 8개!
        // data = [{},{},{},{},{},{},{},{}];
        //data[i]면 하나의 객체로...
        // data[0].cpuName이런식으로! 
        // console.log(data[0].price);
        //이 데이터들을 이제  innerHTML로 넣어주기만 하면 돼!!
        cardSort.forEach((value,index) => {
            // value.setAttribute('data-id',data[index]._id);
            console.log(set[index]);
        }) 
    })
    .catch((err) => console.log(err))
}


