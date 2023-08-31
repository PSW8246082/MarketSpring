<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
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
                	<div class="free_header">

	                    <span class="left">
	                    	자유게시판
	                    </span>
						<span class="button">
							<a href="/board/binsert.do">글쓰기</a>
						</span>
					</div> 
                    <table>
                        <tr>
                            <th class="col1">번호</th>
                            <th class="col2">제목</th>
                            <th class="col3">작성자</th>
                            <th class="col4">작성날짜</th>
                            <th class="col5">첨부파일</th>
                            <th class="col6">조회수</th>
                        </tr>
                        <c:forEach var="board" items="${requestScope.bList }" varStatus="i">
                        <tr>
                            <td>${board.boardNo }</td>
<!--                             css수정하고 여기 주석 다시 풀어주기 -->
                            <td><a href="/board/bdetail.do?boardNo=${board.boardNo }">${board.boardTitle }</a></td>
                            <td>${board.boardWriter } <img src="" class="face"></td>
                            <td>
								<fmt:formatDate pattern="YYYY-MM-dd" value="${board.bCreateDate }"/>
<%-- 								${notice.nCreateDate} --%>
							</td>
                            <td>
								<c:if test="${!empty board.boardFilename }">o</c:if>
								<c:if test="${empty board.boardFilename }">x</c:if>
							</td>
							<td>${i.count }</td>
                        </tr>
                        </c:forEach>
                    </table>
<!------------------------------------------------------------------------>
<!-- 	<tfoot></tfoot> -->
				<div class="number">
		<!-- 			<tr> -->
		<!-- 				<td colspan="6"> -->
					<div class="pagination">
						<c:if test="${pInfo.startNavi != 1 }">
							<c:url var="prevUrl" value="/board/blist.do">
								<c:param name="page" value=""></c:param>
							</c:url>
							<a href="${prevUrl }">이전</a>
						</c:if>
							<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
							<c:url var="pageUrl" value="/board/blist.do">
								<c:param name="page" value="${p }"></c:param>
							</c:url>
							<a href="${pageUrl }">${p }</a>&nbsp;
		<%-- 					<a href="/notice/list.kh?page=${p }">${p }</a>&nbsp; --%>
						</c:forEach> 
						<%-- 				${pInfo } --%>
						<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
							<c:url var="nextUrl" value="/board/blist.do"> 
								<c:param name="page" value="${pInfo.endNavi + 1 }"></c:param>
							</c:url>
							<a href="${nextUrl }">다음</a>
						</c:if>
					</div>
		<!-- 			<div class=""> -->
		<!-- 				<a href="/inquiry/iinsert.do">글쓰기</a> -->
		<!-- 			</div> -->
		<!-- 				</td> -->
		<!-- 			</tr> -->
					
					<br><br><br>
					
		<!-- 			<tr> -->
		<!-- 				<td colspan="5"> -->
		
				<div class="boardSearch">
					<form action="/board/search.do" method="get">
						<select name="searchCondition">
							<option value="all">전체</option>
							<option value="writer">작성자</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select> <input type="text" name="searchKeyword"
							placeholder="검색어를 입력하세요."> <input type="submit"
							value="검색">
					</form>
				</div>
		<!-- 			</tr> -->
				</div>
			</div>
<!-------------------------------------------------------------------------->
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