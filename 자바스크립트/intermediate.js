// =========================== 자바스크립트 중급 강좌 ===========================
/**
###1. 변수(es6부터 let const가 생김!)
(let) vs (const) vs (var)
중급 단계니까 값을 저장하기위한 이름!! 이런거 안 돼!!
정의 : 하나의 값을 저장하기 위해 확보한 공간을 식별하기위해 붙인 이름!!
       식별자!!!! 라는 게 ㅈㄴ중요함. 어차피 컴퓨터 껐다 키면 
       메모리 위치가 바뀜. 따라서 그 변수가 어느 위치에 있든지 식별하기 위한.
       식별자가 필요함.

@@<<div 변수 생성 3단계 >>@@ 
1. 선언 : 메모리에 변수의 존재를 알린다.

2. 초기화 : 메모리 공간을 확보한다.

3. 할당 : 그 공간에 값을 넣는다!!

js는 코드 전체를 훑으면서 문제가 있는지 없는지 검사하는데 
이를 실행 컨텍스트라고 한다!!!!!!!!!!
이 단계에서 js는 변수와 함수를 메모리에 등록한다.
=== 1. 즉 실행 컨텍스트 단계에서 실행 컨텍스트에 변수와 함수를 선언한다.
(아직 메모리 할당 전. 그냥 아 이 변수 생성해달라는 거네 라고 인식!)
(초기화 단계에서 실제로 메모리가 만들어지고 undefined가 할당 됨.)
=== 2. 콜 스택의 메모리 저장 공간에 메모리 공간을 확보한다.
=== 3. 콜 스텍에 undefined(아직 할당 전)를 확보하는 것으로 초기화 단계를 마침.
=== 4. 실제로 원하는 값을 넣는다.(할당 단계이다.)

선언 : 내가 쓴 변수와 함수의 존재를 알리는 단계이다.
실행 컨텍스트 단계에서 실행이 된다.

초기화 : 이름이 등록된 변수에 값을 저장하기 위한 공간을 확보하고 
암묵적으로 undefined를 할당한다.
(var과 let만! 대신const는 초기화와 할당이 동시에 이루어져야함.)

 */

const { append } = require("express/lib/response");

console.log(hello);
var hello; //초기화가 된 변수만 참조가 가능하다.
//근데 let const는 호이스팅은 되는데 선언과 초기화가 따로 진행돼서 
//참조가 안 되는거야!!!

/**
 * ###2. 생성자 함수 ==> 자바 c++같은 객체 한 객체에서 여러 개 생성.
 *  객체 리터럴 방법은 정말 하나의 대상만 만들 때 쓰임.
 * 생성자 함수를 쓰면 객체 리터럴을 쓰는 것보다 훨씬 빠르게 만들 수 있다.
 * user1,user2이렇게 객체 리터럴을 쓰는 것 보다. 생성자 함수를 써서,
 * 빠르게 생성해줄 수 있다.
 * 
 * 정리 :: 비슷한 객체 여러 개 만들 때 쓰인다. 
 */


/**
 * ###3. 객체 메서드
 * 
 * 1. .assign({합칠 값},복제할 객체) : 기존 복제 객체에다가 어떤 값을 합친다. 
 * {}빈 객체에다가 item1이렇게 해주면 사실상 item1을 복제!(같이 쓰는 거 아님)
 * 2. .keys(); : 객체의 key들을 배열로 반환한다.
 * 3. .values(); : 객체의 값들을 배열로 반환한다.
 * 4. .entries(); : 키와 값을 모두 배열로 반환한다!!
 * 5. .fromEntries();
 */

function Item(name,price,count){
       this.name = name; //상품 이름
       this.price = price; //상품 가격
       this.count = count; //상품 수
       this.intro = function(){ //intro함수!!
              console.log(`${this.name}, ${this.price}, ${this.count}`);
       }
}
// console.log(item1.name); //상품1이 제대로 담긴다.
// const item3 = item1; //하나의 변수를 여러개의 변수가 공유한다.
// item3.name = "나도 쓰고 있지롱~";
// console.log(item1.name);

//객체를 동일하게 복사하려면 assign변수를 써야한다!!
const item1 = new Item("상품1",1000,100);
const item2 = Object.assign({age:25},item1);// {}값이 제일 위로!
const item3 = Object.assign({},item1,item2);

console.log(Object.keys(item3)); //객체의 key들을 배열로 반환한다.
console.log(Object.keys(item3)[2]); //이렇게 배열을 출력할 수 있음.

console.log(Object.values(item3)[1]);

console.log(Object.entries(item3)[1][0]);
//키와 값을 배열 0 1이렇게 묶어서!!!!


function makeObj(key,value){
       return {
              [key] : value
       }
}

const obj1 = makeObj("name","김지성");

const obj2 = Object.assign({sayHello : function(){
       console.log(`안녕하세요!! ${this.name}`);
       //이 함수가 제일 위로 올라오게된다!!!
}},obj1);

const obj3 = Object.assign({height : 175},obj2);
console.log(obj3);

console.log(Object.keys(obj3));

console.log(Object.entries(obj3)[0]);

/**
 * ###4. Symbol : new를 붙이지 않음.
 * 문자열로 설명을 붙이는데, 같게 해 줘도 다르게 나옴. 어떤 영향도 끼치지 않음.
 * 정말 그 객체만의 유일한 값을 생성하고싶을 때 Symbol을 사용해라!!
 * 
 */

/**
 * ###5. 숫자와 수학
 * 1. toString(2,8,16); //10진수를 2 8 16진수로 바꾸기!
 * 2. 다양한 숫자 관련 메서드 Math내장 객체!!!!!!!
 * - Math.PI : 파이 값!!
 * - Math.ceil() : 소수점 상관없이 그냥 올려줌(5.1->6);
 * - Math.floor() : 소수점 상관없이 그냥 내려줌(5.9 -> 5); -보다 작은 숫자 출력할 때 좋음.
 * - Math.round() : 반올림!
 * - Number(Math.toFixed()) : 반올림인데 이건 문자열 반환! 따라서 Number와 같이!
 * - Math.random() : 0 - 1사이 무작위 숫자 반환!
 * 따라서 Math.random()*100하면 99.99생각하고 Math.floor로 내린다!
 * 여기서 100은 우리가 뽑고싶은 수가 100개라는 뜻이다!
 * 그럼 99가 된다!
 * - Math.max() : 제일 큰 수 구해주기
 * - Math.min() : 제일 작은 수 구해주기
 * - Math.abs() : 절대값
 * - Math.pow(n,m) : n의 m제곱
 * - Math.sqrt(n) : 루트 n 
 */
let num = 10;
console.log(num.toString(2)); //2진수
console.log(num.toString(16)); //16진수
console.log(num.toString(8)); //8진수
console.log(Math.PI);
console.log(Math.floor(5.9));
const userRate = 30.1284; //셋 째 자리에서 반올림해서 둘 째 자리까지 출력해라.
console.log(typeof (userRate.toFixed(6)));
//toFixed(2) ==> 3째 자리에서 반올림해서 2째 자리까지 나타내라.
//다만 기존 소수보다 많으면 ==> 0으로 채워준다
//toFixed() => 많이 쓰임. 다만 문자열로 변환을 해줘서 Number를 이용해서!!
let x = 30.123;
console.log(Number(x.toFixed(2)),typeof Number(x.toFixed(3)));
console.log(typeof(parseInt(x)));
//0부터 5까지 랜덤수 
const rand = Math.floor(Math.random()*100)+1;
//Math.floor = 99.999 도 깔끔하게 정수로 99로 만들어줌!!

console.log(Math.pow(2,8));
console.log(Math.sqrt(16));
/**
 *  ###6. 문자열 메서드(개중요해 진짜로.)
 * -0 : indexOf() : 문자열 포함여부!
 * -1. .length : 문자열의 길이 반환. 문자의 수!!
 * : 비밀번호 몇 자 이상!! 몇 자 이하로 할 때 체크해라!!
 * -2. toUpperCase() : 모든 영어를 대문자로!
 * -3. toLowerCase() : 모든 영어를 소문자로!
 * -4. str.slice(n,m) : n부터 m 전 까지의 문자를 반환한다.
 *     slice(1,3)이면 인덱스 1,2번만 반환한다.
 *     m이 없다면 문자열 끝까지를 의미한다.
 *     slice(1,-2) : [1]부터 끝에서 2번째까지를 반환한다.
 *     ==> 즉 문자열을 일부만 포함하고싶을 때! 아니면 잘라서 조작할 때
 * 
 * -5. trim() : 문자열 앞,뒤의 모든 공백을 없애준다.
 * -6. repeat(n) : 문자열을 n번 반복한다!
 * -7. includes() : 문자가 있으면 true, 없으면 false를
 *     ex) 아이디에 -가 들어있으면 안 돼!
 */

/**
 * ###7. 배열 메서드(개중요해 진짜로.)
 * 1. arr.push() : 뒤에 삽입(시간 짧음. 앞에 하려면 그 뒤에꺼 전부 옯겨주고 해야해.)
 * 2. .pop() : 맨 뒤부터 삭제한다.
 * 3. .unshift() : 맨 앞부터 삽입(알고리즘 상 시간 오래걸림)
 * 4. .shift() : 앞부터 삭제(알고리즘 상 시간 오래걸림).
 * 5. arr.splice(n,m) : n부터 m개 삭제한다. m없으면 전체!
 *    arr.splice(n,m,x) : x를 추가한다!!!
 *    splice는 삭제된 값을 반환한다!
 * 6. slice(n,m) : n부터 m전까지를 반환한다.
 *    //n과 m은 indexNumber이다!! 
 * 
 * 7. concat() : 배열을 합쳐서 새로운 배열을 반환한다!!!
 * 8. forEach(함수) 
 *   1. name : 배열 원소
 *   2. index : 그 원소의 index값
 *   즉 배열에 내가 작성한 함수를 해준다!!!
 *   배열 전체 순환한다!! forEach는 필수이다.
 * 9. indexOf(index) : 해당 원소가 있다면 그 인덱스를 반환한다.
 *    만약 발견하지 못 한다면 -1을 반환한다!!!!
 * 10. includes(찾고싶은 값) : 굳이 index확인 할 필요없고,
 * 그냥 포함이 됐는 지 만 알고싶으면 includes를 사용해라!
 * 
 * 11. find(함수) : 더 복잡한 걸 찾을 수 있다. 짝수를 찾는다던가.
 *     단!!! find는 첫 번째 값만 찾아서 반환해준다!
 * //미성년자 찾기!!!!
 */
const arr = [1,2,3,4,5,6];
const spliceResult = arr.splice(1,3);//2 3 4삭제!
console.log(spliceResult); //2 3 4를 배열로 반환한다!
//즉 splice로 삭제하고 그 삭제 한 것 중에 값 찾기!!!

// if(spliceResult.includes(Number(3))){
//        console.log('삭제 된 수에 3이 존재!');
// }else{
//        console.log('3없어!');
// } 
// function concatArr(arr1,arr2){
//        return arr1.concat(arr2);
// }
// console.log(concatArr([1,2,3],[4,5,6]));
// //concat배열 함수 굉장히 중요하다.
let users = ['user1','user2','user3'];
users.forEach((list,index)=>{
     console.log(list + "1");
})

//미성년자 찾기!!!

const userList = [
       {name : "김지성", age : 25},
       {name : "지성", age : 19},
       {name : "jisung", age : 24},
       {name : "king", age : 27},
]

userList.find((item)=>{
     if(item.age < 20){
            return console.log('미성년자입니다!');
     }else{
            return console.log('미성년자는 없습니다!');
     }
})