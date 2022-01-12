'use strict';



/**
 * 화면 크기문제!!
 * click전까지는 height : 100vh로 해놓고
 * 나머지는 숨긴다. display : none;
 * 
 * click이후에는 display : block으로 다시 바꿈!
 * 
 *  */


function move() {
  var elem = document.querySelector(".door");   
  elem.className = "animation-move";
}