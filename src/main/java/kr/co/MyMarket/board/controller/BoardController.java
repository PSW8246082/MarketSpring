package kr.co.MyMarket.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.board.domain.Reply;
import kr.co.MyMarket.board.service.BoardService;
import kr.co.MyMarket.board.service.ReplyService;
import kr.co.MyMarket.inquiry.domain.Inquiry;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private ReplyService rService;
	
	
	
	@RequestMapping(value = "/board/blist.do", method = RequestMethod.GET)
	public ModelAndView showBoardList(
			ModelAndView mv
			,@RequestParam(value = "page", required=false, defaultValue = "1") Integer currentPage) {
		
		Integer totalCount = bService.getListCount();
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Board> bList = bService.selectBoardList(pInfo);
		
		mv.addObject("bList", bList).addObject("pInfo", pInfo).setViewName("/board/boardList");
		
//		mv.addObject("bList", bList);
//		mv.addObject("pInfo", pInfo);
//		mv.setViewName("/board/list");
		
		return mv;
	}

	
	@RequestMapping(value = "/board/bdetail.do", method = RequestMethod.GET)
	public ModelAndView showBoardDetail(
			ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo) {
		
		try {
			Board boardOne = bService.selectBoardByNo(boardNo);
			
			if(boardOne != null) {
				List<Reply> rList = rService.selectReplyList(boardNo);
				if(rList.size() > 0) {
					mv.addObject("rList", rList);
				}
				mv.addObject("board", boardOne);
				mv.setViewName("board/boardDetail");
			} else {
				mv.addObject("msg", "게시글 조회 실패");
				mv.addObject("error", "게시글 조회 실패");
				mv.addObject("url", "/board/blist.do");
				mv.setViewName("common/serviceFail");
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "게시글 조회 실패");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/blist.do");
			mv.setViewName("common/serviceFail");
		}
		
		return mv;
	}
	
	
	

	@RequestMapping(value = "/board/binsert.do", method = RequestMethod.GET)
	//void 대신 ModelAndView를 사용함, 데이터도 넣고 스트링도 넣고..? 페이지 이동도 하고 리턴할 수있음
	public ModelAndView showWriterForm(
			ModelAndView mv) {
		
		mv.setViewName("board/boardInsert");   //= return "board/write"
		return mv;
	}
	
	
	
	@RequestMapping(value = "/board/binsert.do", method = RequestMethod.POST)
	//void 대신 ModelAndView를 사용함, 데이터도 넣고 스트링도 넣고..? 페이지 이동도 하고 리턴할 수있음
	public ModelAndView boardRegister(
			ModelAndView mv
			, @ModelAttribute Board board
			//첨부파일 사용 시 사용해야 하는 클래스
			,@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile
			,HttpServletRequest request
			, HttpSession session) { 
			
		try {
			String boardWriter = (String)session.getAttribute("customerId");  //session에서 id 가져와서 쓰는방법
			if(boardWriter != null && !boardWriter.equals("")) {
				board.setBoardWriter(boardWriter);
				
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					//파일정보(이름, 리네임, 경로, 크기) 및 파일저장
					//여러가지 데이터를 받아서 리턴하기 위해 Map사용
					Map<String, Object> bMap = this.saveFile(request, uploadFile);
					
					//Object는 long타입이 있어서 사용했는데 따라서 형변환이 필요함
					board.setBoardFilename((String)bMap.get("fileName"));  //fileName 은 밑에 saveFile에 파일정보 리턴값과 일치해야함
					board.setBoardFileRename((String)bMap.get("fileRename"));
					board.setBoardFilepath((String)bMap.get("filePath"));
					board.setBoardFileLength((long)bMap.get("fileLength"));
				}
				int result = bService.insertBoard(board);
				mv.setViewName("redirect:/board/blist.do");
			} else {
				mv.addObject("msg", "로그인 정보 존재하지 않습니다");
				mv.addObject("error", "로그인이 필요합니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFail");
			}
		} catch (Exception e) {
			//model.addAttribute("msg", "게시글 등록 실패");와 같은 기능
			mv.addObject("msg", "게시글 등록 실패");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/binsert.do");
			mv.setViewName("common/serviceFail");
	}
		return mv;
	
	
	}
	
	
	
	
	
	@RequestMapping(value = "/board/search.do", method = RequestMethod.GET)
	public String searchInquiryList(
			@RequestParam("searchCondition") String searchCondition
			, @ RequestParam("searchKeyword") String searchKeyword
			, @ RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, Model model) {
		
		//두가지 값을 하나의 변수로 사용하는법 (searchCondition, searchKeyword)
		//1.VO클래스 만들기(이미해봄)
		//2. HashMap 사용(안해봄)
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		
		int totalCount = bService.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		//put() 메소드를 사용해서 key-value 설정을 하는데 key값(파란색)이 mapper.xml에서 사용된다.
		
				
		List<Inquiry> searchList = bService.searchBoardByKeyword(pInfo, paramMap);
		
//		List<Notice> searchList = new ArrayList<Notice>();
//		switch (searchCondition) {
//		case "all":
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT = ? OR NOTICE_CONTENT = ? OR NOTICE_WRITER =?
//			searchList = service.searchNoticeByAll(searchKeyword);
//			break;
//		case "writer":
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_WRITER = ?
//			searchList = service.searchNoticeByWriter(searchKeyword);
//			break;
//		case "title":
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%공지%';
//			searchList = service.searchNoticeByTitle(searchKeyword);
//			break;
//		case "content":
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_CONTENT = ?
//			searchList = service.searchNoticeByContent(searchKeyword);
//			break;
//		}
		if(!searchList.isEmpty()) {
//			model.addAttribute("searchCondition", searchCondition); paramMap으로 써도 됨
//			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "board/boardSearch";
		} else {
			model.addAttribute("msg", "게시글 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "게시글 조회 실패");
			model.addAttribute("url", "/board/blist.do");
			return "common/serviceFail";
		}
		
	}
	
	
	@RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
	public String showModifyBoard(
			@RequestParam("boardNo") String boardNo
			, Model model) {
		Board board = bService.showDetailBoard(boardNo);
		model.addAttribute("board", board);
		return "board/boardUpdate";
	}
	
	
	
	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	public String updateBoard(
			@ModelAttribute Board board
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		
		try {
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				
				//================= 파일이름 =================
				String fileName = uploadFile.getOriginalFilename();
				//(내가 저장한 후에 그 경로르 DB에 저장하도록 준비하는 것)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//폴더가 없을 경우 자동생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\buploadFiles";
				File folder = new File(saveFolder);
				if(!folder.exists()) {
					folder.mkdir();
				}
				
				//================= 파일경로 =================
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				// **************** 파일저장 *****************
				uploadFile.transferTo(file);
						
				//================= 파일크기 =================
				long fileLength = uploadFile.getSize();
				
				//DB에 저장하기 위해 notice에 데이터를 Set하는 부분
				board.setBoardFilename(fileName);
				board.setBoardFilepath(savePath);
				board.setBoardFileLength(fileLength);
			}


			int result = bService.updateBoard(board);
			
			if(result > 0) {
				return "redirect:/board/bdetail.do?boardNo=" + board.getBoardNo();
			} else {
				model.addAttribute("msg", "게시글 내용 수정 완료되지 않았습니다.");
				model.addAttribute("error", "게시글 내용수정 실패");
				model.addAttribute("url", "/serviceFail.jsp");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의바람");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFail";
		}
		
	}
	
	
	@RequestMapping(value="/board/bdelete.do", method = RequestMethod.GET)  //form태그만 post, a태그는 get방식
	public String removeInquiry(
			@RequestParam("boardNo") String boardNo
			,Model model) {
		//DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		try {
			int result = bService.removeBoard(boardNo);
			if(result>0) {
				//성공 
				return "redirect:/board/blist.do";
			}else {
				//실패
				model.addAttribute("msg", "게시글 삭제 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	
	
		private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		
		//resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		//파일 저장 경로 구하기
		String savePath = root + "\\buploadFile";
		
		//파일 이름 구하기
		String fileName = uploadFile.getOriginalFilename();
		
		//파일 확장자 구하기
		String extension 
		= fileName.substring(fileName.lastIndexOf(".")+1);
		
		//시간으로 파일 리네임하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis()));
		
		//파일 저장 전 폴더 만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		
		//파일저장
		File saveFile = new File(savePath + "\\" + fileRename);
		uploadFile.transferTo(saveFile);
		long fileLength = uploadFile.getSize();
		
		//파일정보 리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/buploadFiles/" + fileRename);
		fileMap.put("fileLength", fileLength);
		
		return fileMap;
	}	
	


	
	
	
	
	
	
	
	
	
	private PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		int naviTotalCount;
		naviTotalCount 
			= (int)Math.ceil((double)totalCount/recordCountPerPage);   //6.2 -> 7 올림해주는 식
		
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;  //이거뭐냐...
		
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		
		
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		
		return pInfo;
	}
	
	
	
	
	
}
