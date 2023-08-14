<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberModify</title>
 <link rel="stylesheet" href="/resources/css/memberModify.css">
 <link rel="stylesheet" href="/resources/css/memberModifyReset.css">
</head>
<body>
	  <div id="container">
	  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<!--             <header> -->
<!--                 <div id="welcome">친구 초대하면 친구도 나도 적립금 5천원!</div> -->
<!--                     <div id="logo"> -->
<!--                         <a href="../index.html"> -->
<!--                             <img id="logoImg" src="../resources/images/index/logo.png" alt="logo"> -->
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
<!--             </header> -->
            <main class="contents">



                <div class="outer">
                    <div id="joinInfoArea">
                        <form action="/customer/update.do" id="joinForm" 
                        method="post" onsubmit="return validate();">
                            <h2>회원정보수정</h2>
                            <hr>
                            
                            <div><h4>* 아이디</h4></div>
                            <div><span class="input_area"><input type="text" maxlength="13" name="customer-id" value="${customer.customerId}" readonly></span></div>
                            
                            
<%--                             <%= userLogin.getUserPwd() %> --%>
                            <h4>* 현재 비밀번호</h4>
                            <span class="input_area"><input type="password" maxlength="15" name="customer-pw" value=""></span>
                            
<%-- 								<%= userLogin.getUserPwd() %> --%>
                            <h4>* 새 비밀번호</h4>
                            <span class="input_area"><input type="password" maxlength="15" name="customer-pw" value=""></span>
                            
                                            
                            <h4>* 이름</h4>
                            <span class="input_area"><input type="text" maxlength="5" name="customer-name" value="${customer.customerName}"readonly"></span>
                            
                            <h4>연락처</h4>
                            <span class="input_area"><input type="tel" maxlength="11" name="phone"
                                                    placeholder="(-없이)01012345678" ></span>
                                                    
                            <h4>이메일</h4>
                            <span class="input_area"><input type="email" name="email" placeholder="kh정보@naver.com"></span>
                            
                
                            <h4>우편번호</h4>
                            <span class="input_area"><input type="text" name="address" class="postcodify_postcode5" value="04540" readonly></span>
                            <button type="button" id="postcodify_search_button">검색</button>
                            <h4>도로명주소</h4>
                            <span class="input_area"><input type="text" name="address" class="postcodify_address" value="서울 중구 남대문로 120" readonly></span>
                            <h4>상세주소</h4>
                            <span class="input_area"><input type="text" name="address" class="postcodify_details" value="대일빌딩 2층, 3층"></span>
                            
                            <h4>관심분야</h4>
                            <span class="input_area">
                                <input type="checkbox" id="sports" name="interest" value="운동">
                                <label for="sports">운동</label>
                                <input type="checkbox" id="climbing" name="interest" value="등산">
                                <label for="climbing">패션</label>
                                <input type="checkbox" id="cooking" name="interest" value="요리">
                                <label for="cooking">요리</label>
                                <input type="checkbox" id="game" name="interest" value="게임">
                                <label for="game">게임</label>
                                <input type="checkbox" id="etc" name="interest" value="기타">
                                <label for="etc">기타</label>
                            </span>
                            <div class="btnArea">
                                <button id="joinBtn"><a href="javascript:void(0)" onclick="checkDelete();">탈퇴하기</a></button>
                                <button type="submit" id="joinBtn"><a href="/customer/update.do?costomer-id=${customerId }">수정하기</a></button>
                            </div>
                        </form>
                    </div>
                </div>

				<script>
					function checkDelete() {
						const customerId = "${sessionScope.customerId}";
						if(confirm("탈퇴하시겠습니까?")) {
							location.href = "/customer/delete.do?customerId=" + customerId;
						}
					}
				</script>
















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