<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board insert</title>
<link rel="stylesheet" href="/resources/css/inquire.css">
</head>
<body>
	 <div id="container">
	 <jsp:include page="/WEB-INF/views/include/noticeHeader.jsp"></jsp:include>
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
<!--                         <p class="member1"><a href="#">고객센터</a></p> -->
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


                


<!--             </header> -->
            <main class="contents">
                
                <!-- <?$board_id=$_GET['board_id'];?> -->
                <div id="board_write">
                    <h2>자유게시판</h2>
                    <hr><br>
                        <div id="write_area">
                            <form action="/board/binsert.do" method="post" enctype="multipart/form-data">
                            	<div id="in_title">
                                    <p>작성자</p><textarea name="boardWriter" id="utitle" rows="1" cols="55">${customerId }</textarea>
                                </div>
                                <div id="in_title">
                                    <p>제목<b>*</b></p><textarea name="boardTitle" id="utitle" rows="1" cols="55" placeholder="제목을 입력해주세요." maxlength="100" required></textarea>
                                </div>
                                <div class="wi_line"></div>
                                <div id="in_content">
                                    <p>내용<b>*</b></p><textarea name="boardContent" id="ucontent" placeholder="내용을 입력해주세요." required></textarea>
                                </div>

                                <div id="select">
                                    <label for="file">
                                        <div class="btn-upload">파일 업로드하기</div>
                                    </label>
                                    <input type="file" name="uploadFile" id="file">
    
    
                                    <div class="bt_se">
                                        <button type="submit">글 작성</button>
                                    </div>
                                </div>

                            </form>
                        </div>
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