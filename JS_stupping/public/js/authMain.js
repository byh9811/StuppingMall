const shift = document.querySelectorAll('.shift');
const carousel_container = document.querySelectorAll('.carousel_container');

for(let i=0; i<shift.length; i++){
    shift[i].addEventListener('mouseover',()=>{
        move(i * -100);
    })
}
function move(number){
    //속성값 파라미터임!!
    for(let i=0; i<carousel_container.length; i++){
        carousel_container[i].style.transform = `translateX(${number}%)`;
        carousel_container[i].style.transition = `all 0.8s`;
    }
}

/** scroll ==> subnav change*/

$(window).on('scroll',function(){
    if( $(window).scrollTop() > 120){
        $('.header_contents').addClass('nav_change');
        $('.main_search_2').addClass('search_local_change');
    }else{
        $('.header_contents').removeClass('nav_change');
    }
});

//top8 carousel
const item = document.querySelectorAll('.item_link');
const carousel_3d = document.querySelector('.carousel_3d');

for(let i=0; i<item.length; i++){
    item[i].addEventListener('mouseover',()=>{
        //mouseover잘 돌아감!!!!
        here(i * -45 + 'deg');
        
    })

}

function here(deg){
    carousel_3d.style.transition = "all 1s";
    carousel_3d.style.transform = `rotateY(${deg})`;
}
