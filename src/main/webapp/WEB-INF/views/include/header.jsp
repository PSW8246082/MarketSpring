<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <div id="welcome">친구 초대하면 친구도 나도 적립금 5천원!</div>
        <div id="logo">
            <a href="/index.jsp">
                <img id="logoImg" src="/resources/images/index/logo.png" alt="로고">
            </a>
        </div>
        <section>
            <form action="https://search.naver.com/search.naver">
                <div class="search">
                    <input type="text" name="query" value="" placeholder="   검색어를 입력하세요.">
                    <button type="submit">검색</button>
                </div>
            </form>
        </section>
        <div>
        	<c:if test = "${sessionScope.customerId ne null }">
        	 <p class="member1"><a href="/customer/mypage.do?costomer-id=${customerId }">${sessionScope.customerName } 님</a></p>
        	 <p class="member1"><a href="/customer/logout.do">로그아웃</a></p>
        	 <p class="member1"><a href="/notice/clist.do">고객센터</a></p>
        	</c:if>
        	<c:if test ="${sessionScope.customerId eq null }"> 
            <p class="member1"><a href="/customer/register.do">회원가입</a></p>
            <p class="member1"><a href="/customer/login.do">로그인</a></p>
            <p class="member1"><a href="/notice/clist.do">고객센터</a></p>
            </c:if>
        </div>
        <div id="headlayer">
            <a href="../MyMarket/product/shipping.html"><img src="/resources/images/index/location.png" alt="location"></a> 
            <a href="../MyMarket/member/myPage.html"><img src="/resources/images/index/heart.png" alt="heart"></a>
            <a href="../MyMarket/product/cart.html"><img src="/resources/images/index/trolley.png" alt="trolley"></a>
        </div>
    <div>
        <nav>
            <ul id="topMenu">
                    <li><a href="#"><img id="menuicon" src="/resources/images/index/MenuIcon.png" alt="메뉴아이콘">카테고리</a></li>
                    <!-- <li><a href="#">카테고리</a></li> -->
                    <li><a href="#">신상품</a></li>
                    <li><a href="#">베스트</a></li>
                    <li><a href="#">알뜰쇼핑</a></li>
                    <li><a href="#">특가/혜택</a></li>
            </ul>
        </nav>
    </div>
</header>
