<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customerCenter</title>
<link rel="stylesheet" href="/resources/css/costomerCenter.css">
</head>
<body>
	 <div id="container">
	 <jsp:include page="/WEB-INF/views/include/noticeHeader.jsp"></jsp:include>
<!--             <header> -->
<!--                 <div id="welcome">친구 초대하면 친구도 나도 적립금 5천원!</div> -->
<!--                     <div id="logo"> -->
<!--                         <a href="../index.html"> -->
<!--                             <img id="logoImg" src="../resources/images/index/logo.png" alt="로고"> -->
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
<!--                         <p class="member1"><a href="../member/signup.html">회원가입</a></p> -->
<!--                         <p class="member1"><a href="../member/login.html">로그인</a></p> -->
<!--                         <p class="member1"><a href="../member/costomerCenter.html">고객센터</a></p> -->
<!--                     </div> -->
<!--                     <div id="headlayer"> -->
<!--                         <a href="../product/shipping.html"><img src="../resources/images/index/location.png" alt="location"></a>  -->
<!--                         <a href="../member/myPage.html"><img src="../resources/images/index/heart.png" alt="heart"></a> -->
<!--                         <a href="../product/cart.html"><img src="../resources/images/index/trolley.png" alt="trolley"></a> -->
<!--                     </div> -->
<!--                 <div> -->
<!--                     <nav> -->
<!--                         <ul id="topMenu"> -->
<!--                                 <li><a href="#"><img id="menuicon" src="../resources/images/index/MenuIcon.png" alt="MenuIcon">카테고리</a></li> -->
<!--                                 <li><a href="#">카테고리</a></li> -->
<!--                                 <li><a href="#">신상품</a></li> -->
<!--                                 <li><a href="#">베스트</a></li> -->
<!--                                 <li><a href="#">알뜰쇼핑</a></li> -->
<!--                                 <li><a href="#">특가/혜택</a></li> -->
<!--                         </ul> -->
<!--                     </nav> -->
<!--                 </div> -->

<!--                 <div> -->
<!--                     <aside id="left"> -->
<!--                         <h1>고객센터</h1> -->
<!--                         <ul> -->
<!--                             <li><a target="iframe1" href="../member/costomerCenter.html">공지사항</a></li> -->
<!--                             <li><a target="iframe1" href="../member/inquire.html">1:1문의</a></li> -->
<!--                             <li><a target="iframe1" href="#">자유게시판</a></li> -->
<!--                             <li><a target="iframe1" href="#">자주하는 질문</a></li> -->
<!--                         </ul> -->
<!--                         </aside> -->
<!--                 </div> -->


<!--                 <div> -->
<!--                     <aside id="right"> -->
<!--                         <h5>최근본상품</h5> -->
<!--                         <ul> -->
<!--                             <li><a target="iframe1" href="#"><img src="../resources/images/index/food_1.png" alt="food1"></a></li> -->
<!--                             <li><a target="iframe1" href="#"><img src="../resources/images/index/food_3.png" alt="food3"></a></li> -->
<!--                             <li><a target="iframe1" href="#"><img src="../resources/images/index/food_4.png" alt="food4"></a></li> -->
<!--                         </ul> -->
<!--                         </aside> -->
<!--                 </div> -->


<!--             </header> -->
            <main class="contents">
                
                <div class="free">
                    <h2>공지사항</h2>
                    <table>
                        <tr>
                            <th class="col1">번호</th>
                            <th class="col2">제목</th>
                            <th class="col3">작성자</th>
                            <th class="col4">조회수</th>
                            <th class="col5">작성일</th>
                        </tr>
                        <tr>
                            <td>공지</td>
                            <td><a href="#">[안내] 6월 배송 휴무 일정 안내</a><span> [6]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>22</td>
                            <td>2023-06-01</td>
                        </tr>
                        <tr>
                            <td>공지</td>
                            <td><a href="#">[안내] 마이마켓 소비자 분쟁해결 기준 안내</a><span> [12]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>41</td>
                            <td>2023-05-28</td>
                        </tr>
                        <tr>
                            <td>318</td>
                            <td><a href="#">[이벤트] 루메나 선풍기 라이브 경품 당첨 안내</a><span> [1]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>7</td>
                            <td>2023-05-15</td>
                        </tr>
                        <tr>
                            <td>317</td>
                            <td><a href="#">[안내] 현대카드 정기 점검 안내</a><span> [21]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>56</td>
                            <td>2023-05-13</td>
                        <tr>
                            <td>316</td>
                            <td><a href="#">[안내] 우리은행/우리카드 시스템 점검 안내</a><span> [15]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>32</td>
                            <td>2023-05-10</td>
                        </tr>
                        <tr>
                            <td>315</td>
                            <td><a href="#">[이벤트] 시슬리 라이브 방송 구매인증 당첨자 안내</a><span> [10]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>38</td>
                            <td>2023-05-04</td>
                        </tr>
                        <tr>
                            <td>314</td>
                            <td><a href="#">[안내] 5월 배송 휴무 일정 안내</a><span> [0]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>5</td>
                            <td>2023-05-01</td>
                        <tr>
                            <td>313</td>
                            <td><a href="#">[이벤트] 클리오 라이브 방송 구매인증 당첨자 안내</a><span> [5]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>15</td>
                            <td>2023-04-23</td>
                        </tr>
                        <tr>
                            <td>312</td>
                            <td><a href="#">[이벤트] 5월 봄날의 펫페어 기부 이벤트 공지</a><span> [6]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>30</td>
                            <td>2023-04-11</td>
                        </tr>
                        <tr>
                            <td>311</td>
                            <td><a href="#">[안내] 적립금 정책 일부 변경 안내</a><span> [3]</span></td>
                            <td>MyMarket <img src="" class="face"></td>
                            <td>24</td>
                            <td>2023-04-01</td>
                        </tr>
                    </table>
         
                    <!-- <div class="button"><a href="write.html">글쓰기</a></div> -->
                    
                    <div class="number">
                        <a href="#"><</a>
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#" class="select">3</a>
                        <a href="#">4</a>
                        <a href="#">...</a>
                        <a href="#">></a>
            
                    </div>
                </div>
                
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