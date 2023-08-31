<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail</title>
<link rel="stylesheet" href="/resources/css/inquire.css">
</head>
<body>
<div id="container">
 <jsp:include page="/WEB-INF/views/include/noticeHeader.jsp"></jsp:include>
 <main class="contents">
 <div id="board_write">
 
 
	<h1>자유게시판 상세조회</h1>
	<hr><br>
	
	
	<div id="write_area">
	   		<input type="hidden" name="boardNo" value="${board.boardNo }">
	  
<!-- 	   		<div id="in_title"> -->
<%-- 	           <p>글번호</p><textarea name="number" id="utitle" rows="1" cols="55" readonly>${requestScope.board.boardNo }</textarea> --%>
<!-- 	        </div> -->
<!-- 	        <div id="in_title"> -->
<%-- 	           <p>작성일</p><textarea name="date" id="utitle" rows="1" cols="55" readonly>${requestScope.board.bCreateDate }</textarea> --%>
<!-- 	        </div> -->
<!-- 	   		<div id="in_title"> -->
<%-- 	           <p>작성자</p><textarea name="writer" id="utitle" rows="1" cols="55" readonly>${requestScope.board.boardWriter }</textarea> --%>
<!-- 	        </div> -->
	        <div id="in_title">
	            <p>제목<b>*</b></p><textarea name="title" id="utitle" rows="1" cols="55" readonly>${board.boardTitle }</textarea>
	        </div>
	        <div class="wi_line"></div>
	        <div id="in_content">
	            <p>내용<b>*</b></p><textarea name="content" id="ucontent" readonly>${requestScope.board.boardContent }</textarea>
	        </div>
	        	
	        <div id="in_content"> 
	        	<!-- 다운로드 받을 때 -->
				<%-- <a href="../resources/iuploadFiles/${board.boardFileRename }" download>${board.boardFilename } 다운로드</a>   --%>
	        	<!-- 첨부파일 화면에 띄울때 -->
				<img alt="첨부파일" width="220px" height="100px" src="../resources/iuploadFiles/${board.boardFileRename }">
	        </div>
	
			<br><br>
	
	        <div id="select">
	            <div class="bt_se">
	                <a href="../resources/buploadFiles/${board.boardFileRename }" download><button class="bt_se">이미지 다운로드</button></a>
	            </div>
				<div class="bt_se">
	                <button onclick="location.href='/board/blist.do'">목록으로 돌아가기</button>
	            </div>
	            <div class="bt_se">
	                <button onclick="location.href='/board/update.do?boardNo=${board.boardNo }'">수정</button>
	            </div>
	            <div class="bt_se">
	                <button onclick="location.href='/board/bdelete.do?boardNo=${board.boardNo }'">삭제</button>
	            </div>
	        </div>
	        
	        
	        <br><br>
			<hr>
			<br><br>
			
			<div id="reply">
			<!--댓글등록 -->
				<form action="/reply/add.do" method="post">
				<input type="hidden" name="refBoardNo" value="${board.boardNo }">
					<table width="598" border="1">
						<tr>
							<td><textarea rows="3" cols="55" name="replyContent"></textarea></td>
							<td><input type="submit" value="완료"></td>
						</tr>
					</table>
				</form>
			
			<br>
			
				<!--댓글목록 -->
					<table width="598" border = "1">
					<c:forEach var="reply" items="${rList}">
						<tr> 
							<td>${reply.replyWriter } </td>
							<td>${reply.replyContent } </td>
							<td>${reply.rCreateDate }</td>
							<td>
								<a href="javascript:void(0);" onclick="showModifyForm(this, '${reply.replyContent }');">수정하기</a>
								<c:url var="delUrl" value="/reply/delete.do">
								<c:param name="replyNo" value="${reply.replyNo}"></c:param>
								<!-- 내것(작성자) 것만 지우도록 하기위해서 추가함 -->
								<c:param name="replyWriter" value="${reply.replyWriter}"></c:param>
								<!-- 성공하면 detail로 가기 위한 boardNo 세팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo}"></c:param>
								</c:url>
								<a href="javascript:void(0)" onclick="deleteReply('${delUrl }');">삭제하기</a>
							 </td>
						</tr>
							<!-- 방법1 -->
						<tr id="replyModifyForm" style="display:none">
			<!-- 			form태그 이용방식 -->
			<!-- 			<form action="/reply/update.kh" method="post"> -->
			<%-- 			<input type="hidden" name="replyNo" value="${reply.replyNo }"> --%>
			<%-- 			<input type="hidden" name="refBoardNo" value="${reply.refBoardNo }"> --%>
			<%-- 				<td colspan="3"><input id="replyContent" type="text" size="50" name ="replyContent" value="${reply.replyContent }"></td> --%>
			<!-- 				<td><input type="submit" value="완료"></td>  -->
			<!-- 		     </form> -->
						 
						 
						 <!-- 방법2 -->
						 <td colspan="3"><input id="replyContent" type="text" size="50" name ="replyContent" value="${reply.replyContent }"></td>				
						<td><input type="button" onclick="replyModify(this,'${reply.replyNo }','${reply.refBoardNo }');" value="완료"></td>
						
						
						</tr>
						</c:forEach>
					</table>
				</div>
				
			</div>
						<script>
					
					
					
					
			// 		################################# 댓글 ############################################ 
					//댓글삭제
						function deleteReply(url){
							//DELETE FROM REPLY_TBL WHERE REPLY_NO = 샵{replyNo}, AND R_STATUS = 'Y'
							//UPDEATE REPLY_TBL SET R_STTUS = 'N' WHERE REPLY_NO = 샵{replyNo }
							//alert(url);
							location.href=url;
						}	
						
				
						function replyModify(obj, replyNo, refBoardNo) {
			// 				alert("test");
							//DOM프로그래밍을 이용하는 방법
							const form = document.createElement("form");
							form.action = "/reply/update.do";
							form.method = "post";
							
							const input = document.createElement("input");
							input.type = "hidden";
							input.value= replyNo;
							input.name = "replyNo";
							
							const input2 = document.createElement("input");
							input2.type= "hidden";
							input2.value= refBoardNo;
							input2.name= "refBoardNo";
							
							const input3 = document.createElement("input");
							input3.type= "text";
							//여기를 this를 이용하여 수정해주세요.
			// 				input3.value= document.querySelector("#replyContent").value;
							//this를 이용하여 내가 원하는 노드 찾기
							//방법1
							input3.value = obj.parentElement.previousElementSibling.childNodes[0].value;
							//방법2
							//input3.value = obj.parentElement.previousElementSibling.children[0].value;
							input3.name= "replyContent";
							
							form.appendChild(input);
							form.appendChild(input2);
							form.appendChild(input3);
							
							document.body.appendChild(form);
							form.submit();
						}
						
						
						
						function showModifyForm(obj, replyContent) {
				
			// 		        방법1 : HTML태그, display:none 사용하는 방법
			// 				alert("test");
							obj.parentElement.parentElement.nextElementSibling.style.display="";
						
			//	 			방법2 : DOM프로그래밍을 이용하는 방법
			
			//	 			<tr id="replyModifyForm" style="display:none">
			//	 				<td colspan="3"><input type="text" size="50" value="${reply.replyContent }"></td>
			//	 				<td><input type="button" value="완료"></td>
			//	 			</tr>
			
			
			// 				const trTag = document.createElement("tr");
			// 				const tdTag1 = document.createElement("td");
			// 				tdTag1.colSpan = 3;
			// 				const inputTag1 = document.createElement("input");
			// 				inputTag1.type="text";
			// 				inputTag1.size=50;
			// 				inputTag1.value= replyContent;
			// 				tdTag1.appendChild(inputTag1);
			// 				const tdTag2 = document.createElement("td");
			// 				const inputTag2 = document.createElement("input");
			// 				inputTag2.type="button";
			// 				inputTag2.value="완료";
			// 				tdTag2.appendChild(inputTag2);
			// 				trTag.appendChild(tdTag1);
			// 				trTag.appendChild(tdTag2);
			// // 				console.log(trTag);
			// 				//클릭한 a를 포함하고 있는 tr 다음에 수정폼이 있는 tr 추가하기
			// 				const prevTrTag = obj.parentElement.parentElement;
			// // 				if(prevTrTag.nextElementSibing == null || !prevTrTag.nextElementSibling.querySelector("input")) 오류
			// 				prevTrTag.parentNode.insertBefore(trTag, prevTrTag.nextSibling);
						
						}



			
			

			
		</script>
	
	
	
<!-- 	<ul> -->
<!-- 				<li> -->
<!-- 				<label>글번호</label> -->
<%-- 				<span>${requestScope.board.noticeNo }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>작성일</label> -->
<%-- 				<span>${requestScope.board.noticeDate }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>글쓴이</label> -->
<%-- 				<span>${requestScope.board.noticeWriter }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>제목</label> -->
<%-- 				<span>${board.noticeSubject }</span> --%>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 				<label>내용</label> -->
<%-- 				<p>${requestScope.board.noticeContent }</p> --%>
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 			<a href="/board/ilist.do">목록으로 이동</a><br> -->
<%-- 			<a href="/board/iupdate.do?noticeNo=${board.noticeNo }">수정하기</a><br> --%>
<!-- <!-- 			DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?  -->
<!-- 			<a href="javascript:void(0)" onclick="deleteCheck();">삭제하기</a><br> -->
<!-- <!-- 			<button id = onclick="">삭제하기</button> -->
	
<!-- 	<script> -->
<!-- // 		const deleteCheck = () => { -->
<%-- // 			const noticeNo = "${board.boardNo }"; --%>
<!-- // 			if(confirm("삭제하시겠습니까?")) { -->
<!-- // 				location.href="/board/idelete.do?noticeNo="+noticeNo; -->
<!-- // 			} -->
<!-- // 		}  -->
<!-- 	</script>  -->
	
	
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