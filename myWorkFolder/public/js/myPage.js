'use strict';

$(window).on('scroll',function(){
    if( $(window).scrollTop() > 120){
        $('.sub_nav').addClass('nav_change');
    }else{
        $('.sub_nav').removeClass('nav_change');
    }
});