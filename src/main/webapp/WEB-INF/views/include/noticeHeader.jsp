<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <header>
     <div id="welcome">친구 초대하면 친구도 나도 적립금 5천원!</div>
         <div id="logo">
             <a href="../index.html">
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
            <p class="member1"><a href="/notice/clist.do"">고객센터</a></p>
            </c:if>
        </div>
     <div>
         <nav>
             <ul id="topMenu">
                     <li><a href="#"><img id="menuicon" src="../resources/images/index/MenuIcon.png" alt="MenuIcon">카테고리</a></li>
<!--                      <li><a href="#">카테고리</a></li> -->
                     <li><a href="#">신상품</a></li>
                     <li><a href="#">베스트</a></li>
                     <li><a href="#">알뜰쇼핑</a></li>
                     <li><a href="#">특가/혜택</a></li>
             </ul>
         </nav>
     </div>

     <div>
         <aside id="left">
             <h1>고객센터</h1>
             <ul>
                 <li><a target="iframe1" href="/notice/clist.do"">공지사항</a></li>
                 <li><a target="iframe1" href="/inquiry/ilist.do"">1:1문의</a></li>
                 <li><a target="iframe1" href="/board/blist.do">자유게시판</a></li>
                 <li><a target="iframe1" href="#">자주하는 질문</a></li>
             </ul>
             </aside>
     </div>


     <div>
         <aside id="right">
             <h5>최근본상품</h5>
             <ul>
                 <li><a target="iframe1" href="#"><img src="../resources/images/index/food_1.png" alt="food1"></a></li>
                 <li><a target="iframe1" href="#"><img src="../resources/images/index/food_3.png" alt="food3"></a></li>
                 <li><a target="iframe1" href="#"><img src="../resources/images/index/food_4.png" alt="food4"></a></li>
             </ul>
             </aside>
     </div>


 </header>
