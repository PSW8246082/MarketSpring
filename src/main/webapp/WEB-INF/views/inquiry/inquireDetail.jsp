<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inquire detail</title>
<link rel="stylesheet" href="/resources/css/inquire.css">
</head>
<body>
<div id="container">
 <jsp:include page="/WEB-INF/views/include/noticeHeader.jsp"></jsp:include>
 <main class="contents">
 <div id="board_write">
 
 
	<h1>1:1문의 상세조회</h1>
	<hr><br>
	
	
	<div id="write_area">
	   		<input type="hidden" name="inquiryNo" value="${inquiry.inquiryNo }">
	   		<div id="in_title">
	           <p>글번호</p><textarea name="number" id="utitle" rows="1" cols="55" readonly>${requestScope.inquiry.inquiryNo }</textarea>
	        </div>
	        <div id="in_title">
	           <p>작성일</p><textarea name="date" id="utitle" rows="1" cols="55" readonly>${requestScope.inquiry.iCreateDate }</textarea>
	        </div>
	   		<div id="in_title">
	           <p>작성자</p><textarea name="writer" id="utitle" rows="1" cols="55" readonly>${requestScope.inquiry.inquiryWriter }</textarea>
	        </div>
	        <div id="in_title">
	            <p>제목<b>*</b></p><textarea name="title" id="utitle" rows="1" cols="55" readonly>${inquiry.inquirySubject }</textarea>
	        </div>
	        <div class="wi_line"></div>
	        <div id="in_content">
	            <p>내용<b>*</b></p><textarea name="content" id="ucontent" readonly>${requestScope.inquiry.inquiryContent }</textarea>
	        </div>
	
	        <div id="select">
	            
				<div class="bt_se">
	                <button onclick="location.href='/inquiry/ilist.do'">목록으로 돌아가기</button>
	            </div>
	            <div class="bt_se">
	                <button onclick="location.href='/inquiry/update.do?inquiryNo=${inquiry.inquiryNo }'">수정</button>
	            </div>
	            <div class="bt_se">
	                <button onclick="location.href='/inquiry/idelete.do?inquiryNo=${inquiry.inquiryNo }'">삭제</button>
	            </div>
	            
	
	        </div>
	
	</div>
	
	
	
	
<!-- 	<ul> -->
<!-- 				<li> -->
<!-- 				<label>글번호</label> -->
<%-- 				<span>${requestScope.inquiry.noticeNo }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>작성일</label> -->
<%-- 				<span>${requestScope.inquiry.noticeDate }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>글쓴이</label> -->
<%-- 				<span>${requestScope.inquiry.noticeWriter }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>제목</label> -->
<%-- 				<span>${inquiry.noticeSubject }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>내용</label> -->
<%-- 				<p>${requestScope.inquiry.noticeContent }</p> --%>
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 			<a href="/inquiry/ilist.do">목록으로 이동</a><br> -->
<%-- 			<a href="/inquiry/iupdate.do?noticeNo=${inquiry.noticeNo }">수정하기</a><br> --%>
<!-- <!-- 			DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?  --> -->
<!-- 			<a href="javascript:void(0)" onclick="deleteCheck();">삭제하기</a><br> -->
<!-- <!-- 			<button id = onclick="">삭제하기</button> --> -->
	
<!-- 	<script> -->
<!-- // 		const deleteCheck = () => { -->
<%-- // 			const noticeNo = "${inquiry.noticeNo }"; --%>
<!-- // 			if(confirm("삭제하시겠습니까?")) { -->
<!-- // 				location.href="/inquiry/idelete.do?noticeNo="+noticeNo; -->
<!-- // 			} -->
<!-- // 		} -->
<!-- 	</script> -->
	
	
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