<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myinfo</title>
<link rel="stylesheet" href="/resources/css/signup.css">
</head>
<body>
	<div id="container">
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
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
<!--                         <p class="member1"><a href="/customer/register.do">회원가입</a></p> -->
<!--                         <p class="member1"><a href="/Market/login.do"">로그인</a></p> -->
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
<!--             </header> -->
            <main class="contents">
                <div class = "member">
                    <h1 id="signup"><b>회원정보조회</b></h1>
<!--                     <p id="p1">필수입력사항</p> -->
<!--                     <p id="p2">*</p> -->
                    <hr>
                    <!-- 	 1) post로 하면 쿼리스트링이 안보인다. -->
<!--                     <form action="/customer/myInfo.do" method="post"> -->
                    <!-- 2. 필드 -->
                    <div class="field">
                        <label for="customer-id"><b>*아이디</b></label>
                        <span class="placehold-text"><input type="text" id="customer-id" name="customer-id" value="${customer.customerId}"></span> <!--기능을 위한 id, 쿼리스트링 생성을 위한 name -->
                    </div>
                    <div class="field">
                        <label for="customer-pw"><b>*비밀번호</b></label>
                        <input class="userpw" type="password" id="customer-pw" name="customer-pw" value="${customer.customerPw}">
                    </div>
                    <div class="field">
                        <label for="customer-pw"><b>*비밀번호 재확인</b></label>
                        <input class="userpw-confirm" type="password" id="customer-pw" name="customer-pw" value="${customer.customerPw}">
                    </div>
                    <div class="field">
                        <label for="customer-name"><b>*이름</b></label>
                        <input type="text" id="customer-name" name="customer-name" value="${customer.customerName}">
                    </div>
            
                    <!-- 3. 필드(생년월일) -->
                    <div class="field birth">
                        <b>*생년월일</b>
                        <div>
                            <input type="number" placeholder="년(4자)" id="customer-age" name="customer-age-year" >                
                            <select name="customer-age-month">
                                <option value="">월</option>
                                <option value="1">1월</option>
                                <option value="2">2월</option>
                                <option value="3">3월</option>
                                <option value="4">4월</option>
                                <option value="5">5월</option>
                                <option value="6">6월</option>
                                <option value="7">7월</option>
                                <option value="8">8월</option>
                                <option value="9">9월</option>
                                <option value="10">10월</option>
                                <option value="11">11월</option>
                                <option value="12">12월</option>
                            </select>
                            <input type="number" min="1" max="31" name="customer-age-day" placeholder="일" >
                        </div>
                    </div>
            
                    <!-- 4. 필드(성별) -->
                    <div class="field gender">
                        <label for="gender"><b>성별</b></label>
                        <div>
                            <label><input type="radio" name="gender" value="M">남자</label>
                            <label><input type="radio" name="gender" value="F">여자</label>
                            <label><input type="radio" name="gender">선택안함</label>
                        </div>
                    </div>
            
                    <!-- 5. 이메일_전화번호 -->
                    <div class="field">
                        <label for="email"><b id="email">본인 확인 이메일<small>(선택)</small></b></label>
                        <input type="email" id="email" name="email" placeholder="선택입력" value="${customer.customerEmail}">
                    </div>
                    
                    <div class="field tel-number">
                        <label for="phone"><b>*휴대전화</b></label>
                        <select>
                            <option value="">대한민국 +82</option>
                        </select>
                        <div>
                            <input type="tel" id="phone" name="phone" placeholder="전화번호 입력" value="${customer.customerPhone}">
<!--                             <input type="button" value="인증번호 받기"> -->
                        </div>
<!--                         <input type="number" placeholder="인증번호를 입력하세요"> -->
                    </div>
            
                    <!-- 6. 가입하기 버튼 -->
<!--                     <input type="submit" value="가입하기"> -->
<!--                     </form> -->
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