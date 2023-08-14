<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>MyMarket</title> -->
<!-- <link rel="stylesheet" href="/resources/css/index.css"> -->
<!-- </head> -->
<body>
	 <div id="container">
	 <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!--             <header> -->
<!--                 <div id="welcome">친구 초대하면 친구도 나도 적립금 5천원!</div> -->
<!--                     <div id="logo"> -->
<!--                         <a href="/index.jsp"> -->
<!--                             <img id="logoImg" src="/resources/images/index/logo.png" alt="로고"> -->
<!--                         </a> -->
<!--                     </div> -->
<!--                     <section> -->
<!--                         <form action="https://search.naver.com/search.naver"> -->
<!--                             <div class="search"> -->
<!--                                 <input type="text" name="query" value="" placeholder="   검색어를 입력하세요."> -->
<!--                                 <button type="submit">검색</button> -->
<!--                             </div> -->
<!--                         </form> -->
<!--                     </section> -->
<!--                     <div> -->
<!--                         <p class="member1"><a href="/customer/register.do">회원가입</a></p> -->
<!--                         <p class="member1"><a href="/Market/login.do">로그인</a></p> -->
<!--                         <p class="member1"><a href="../MyMarket/member/costomerCenter.html">고객센터</a></p> -->
<!--                     </div> -->
<!--                     <div id="headlayer"> -->
<!--                         <a href="../MyMarket/product/shipping.html"><img src="/resources/images/index/location.png" alt="location"></a>  -->
<!--                         <a href="../MyMarket/member/myPage.html"><img src="/resources/images/index/heart.png" alt="heart"></a> -->
<!--                         <a href="../MyMarket/product/cart.html"><img src="/resources/images/index/trolley.png" alt="trolley"></a> -->
<!--                     </div> -->
<!--                 <div> -->
<!--                     <nav> -->
<!--                         <ul id="topMenu"> -->
<!--                                 <li><a href="#"><img id="menuicon" src="/resources/images/index/MenuIcon.png" alt="메뉴아이콘">카테고리</a></li> -->
<!--                                 <li><a href="#">카테고리</a></li> -->
<!--                                 <li><a href="#">신상품</a></li> -->
<!--                                 <li><a href="#">베스트</a></li> -->
<!--                                 <li><a href="#">알뜰쇼핑</a></li> -->
<!--                                 <li><a href="#">특가/혜택</a></li> -->
<!--                         </ul> -->
<!--                     </nav> -->
<!--                 </div> -->
<!--             </header> -->
            <main class="contents">
                <section>
                    <div class="banner">
                    <img src="/resources/images/index/index_Banner.png" alt="index_Banner">
                    </div>
                </section>
                <section>
                    <div>
                        <h2 class="proTitle">신상품</h2>
                        <p class="h3">매주 업데이트! 이 주의 신상랭킹!</p>
                        <div class="Product">
                            <a href="#"><img src="/resources/images/index/dog.png" alt="dog"></a>
                            <a href="#"><img src="/resources/images/index/food_1.png" alt="food_1"></a>
                            <a href="#"><img src="/resources/images/index/happy.png" alt="happy"></a>
                            <a href="#"><img src="/resources/images/index/food_2.png" alt="food_2"></a>
                        </div>
                        <div class="ProductName">
                            <a href="#"><p>반려동물 자동급식기</p></a>
                            <a href="#"><p>트러플 크림 라비올리</p></a>
                            <a href="#"><p>해피바스 오리지널 바디로션</p></a>
                            <a href="#"><p>채담카레 3종</p></a>   
                        </div>
                        <div class="ProductName1">
                            <a href="#"><p>원하는 시간에 원하는 양만큼</p></a>
                            <a href="#"><p>집에서 즐기는 파인다이닝</p></a>
                            <a href="#"><p>보드랍고 향기로운 바디피부</p></a>
                            <a href="#"><p>농학박사가 만든 건강한 카레</p></a>   
                        </div>
                        <div class="Price">
                            <a href="#"><p><b>~10%할인</b>134,000원</p></a>
                            <a href="#"><p>28,000원</p></a>
                            <a href="#"><p><b>~14%할인</b>8,500원</p></a>
                            <a href="#"><p>10,000원</p></a>   
                        </div>
                    </div>
                    <div>
                        <h2 class="proTitle">베스트상품</h2>
                        <p class="h3">최근 2주간 가장 판매량이 많았어요♡</p>
                        <div class="Product">
                            <a href="#"><img src="/resources/images/index/food_3.png" alt="food_3"></a>
                            <a href="#"><img src="/resources/images/index/tissue.png" alt="tissue"></a>
                            <a href="#"><img src="/resources/images/index/food_4.png" alt="food_4"></a>
                            <a href="#"><img src="/resources/images/index/pan.png" alt="pan"></a>
                        </div>
                        <div class="ProductName">
                            <a href="#"><p>국산 블루베리 200g</p></a>
                            <a href="#"><p>물티슈 엠보싱 캡형</p></a>
                            <a href="#"><p>호주산 목초육 치마살 구이용</p></a>
                            <a href="#"><p>무쇠 주물 후라이팬 2종(택1)</p></a>   
                        </div>
                        <div class="ProductName1">
                            <a href="#"><p>유기농으로 즐기는 새콤한 매력</p></a>
                            <a href="#"><p>믿고 쓰는 우리집 물티슈</p></a>
                            <a href="#"><p>100g당 5,166원!</p></a>
                            <a href="#"><p>볶고 끓이는 요리에 좋은</p></a>   
                        </div>
                        <div class="Price">
                            <a href="#"><p>12,900원</p></a>
                            <a href="#"><p>11,900원</p></a>
                            <a href="#"><p><b>~15%할인</b>13,600원</p></a>
                            <a href="#"><p><b>~27%할인</b>79,600원</p></a>   
                        </div>
                    </div>
                    <div>
                        <h2 class="proTitle">마이레시피</h2>
                        <p class="h3">나만의 레시피 자랑하기!</p>
                        <div class="customer">
                            <a href="#"><img src="/resources/images/index/food_5.png" alt="food_5"></a>
                            <a href="#"><img src="/resources/images/index/food_6.png" alt="food_6"></a>
                            <a href="#"><img src="/resources/images/index/food_7.png" alt="food_7"></a>
                        </div>
                        <div class="ProductName">
                            <a href="#"><h5>메밀 배추전</h5></a>
                            <a href="#"><h5>뽈뽀 스테이크</h5></a>
                            <a href="#"><h5>모둠버섯전골</h5></a>  
                        </div>  
                    </div>
                    <div>
                        <h2 class="proTitle">인스타그램 고객 후기</h2>
                        <p class="h3">더 많은 고객 후기가 궁금하다면?</p>
                        <div class="customer">
                            <a href="#"><img src="/resources/images/index/food_8.png" alt="food_8"></a>
                            <a href="#"><img src="/resources/images/index/food_9.png" alt="food_9"></a>
                            <a href="#"><img src="/resources/images/index/food_10.png" alt="food_10"></a>
                        </div>
                        <div class="ProductName">
                            <a href="#"><h5>아란치니 트러플 크림소스</h5></a>
                            <a href="#"><h5>롤 유부초밥</h5></a>
                            <a href="#"><h5>쌈밥</h5></a>  
                        </div>
                    </div>
                </section>
            </main>
            <footer>
                <section id="bottomMenu">
                    <ul>
                        <li><a href="#">회사 소개</a></li>
                        <li><a href="#">개인정보처리방침</a></li>
                        <li><a href="#">이용약관</a></li>
                        <li><a href="#">이용안내</a></li>
                        <li><a href="#">인재채용</a></li>
                    </ul>
                    </section>
                    <section id="bottomMenu2">
                        <ul>
                            <li>사업자등록번호 : 276-18-29854</li>
                            <li>주소 : 서울시 종로구 가나다라마바사 207-64</li>
                            <li>법인명(상호) : 주식회사 마이마켓</li>
                            <li>대표이사 : 박선우</li>
                            <li>제휴문의 : abc@naver.com</li>
                        </ul>  
                    </section>
            </footer>
        </div>
</body>
</html>