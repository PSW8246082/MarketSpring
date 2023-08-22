<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inquire update</title>
<link rel="stylesheet" href="/resources/css/inquire.css">
</head>
<body>
<div id="container">
 <jsp:include page="/WEB-INF/views/include/noticeHeader.jsp"></jsp:include>
 
 <main class="contents">
 
 
 <div id="board_write">
    <h2>1:1 문의글 수정</h2>
    <hr><br>
        <div id="write_area">
	 		<form action="/inquiry/iupdate.do" method="post">
			<!-- 	컨트롤러에 No가 필요하니까 hidden으로 만들어줌 -->
			<input type="hidden" name="inquiryNo" value="${inquiry.inquiryNo }"> 
		
			<div id="in_title">
	     		<p>작성자</p><textarea name="writer" id="utitle" rows="1" cols="55" readonly>${requestScope.inquiry.inquiryWriter }</textarea>
			</div>
			<div id="in_title">
			    <p>제목<b>*</b></p><textarea name="title" id="utitle" rows="1" cols="55" placeholder="제목을 입력해주세요." maxlength="100" required>${inquiry.inquirySubject }</textarea>
			</div>
			<div class="wi_line"></div>
			<div id="in_content">
			    <p>내용<b>*</b></p><textarea name="content" id="ucontent" placeholder="내용을 입력해주세요." required>${inquiry.inquiryContent }</textarea>
			</div>
			<div id="select">
			<label for="file">
			    <div class="btn-upload">파일 업로드하기</div>
			</label>
			<input type="file" name="file" id="file">
			   <div class="bt_se">
			       <button type="submit">수정</button>
			   </div>
			</div>
			</form>
		</div>	
	
	
	 
<!-- 		<fieldset> -->
<!-- 			<legend>공지사항 수정</legend> -->
<!-- 			<ul> -->
<!-- 				<li> -->
<!-- 				<label>제목</label> -->
<%-- 				<input type="text" id="" name="noticeSubject" value=${inquiry.noticeSubject }> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>내용</label> -->
<%-- 				<textarea rows="30" cols="40"  id="" name="noticeContent">${inquiry.noticeContent }</textarea> --%>
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</fieldset> -->
<!-- 		<div> -->
<!-- 			<input type="submit" value="수정">  -->
<!-- 			<input type="reset"value="초기화"> -->
<!-- 		</div> -->
		

		<div>
			<aside id="right">
				<h5>최근본상품</h5>
				<ul>
					<li><a target="iframe1" href="#"><img
							src="../resources/images/index/food_1.png" alt="food1"></a></li>
					<li><a target="iframe1" href="#"><img
							src="../resources/images/index/food_3.png" alt="food3"></a></li>
					<li><a target="iframe1" href="#"><img
							src="../resources/images/index/food_4.png" alt="food4"></a></li>
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