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
				mv.addObject("msg", "�Խñ� ��ȸ ����");
				mv.addObject("error", "�Խñ� ��ȸ ����");
				mv.addObject("url", "/board/blist.do");
				mv.setViewName("common/serviceFail");
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "�Խñ� ��ȸ ����");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/blist.do");
			mv.setViewName("common/serviceFail");
		}
		
		return mv;
	}
	
	
	

	@RequestMapping(value = "/board/binsert.do", method = RequestMethod.GET)
	//void ��� ModelAndView�� �����, �����͵� �ְ� ��Ʈ���� �ְ�..? ������ �̵��� �ϰ� ������ ������
	public ModelAndView showWriterForm(
			ModelAndView mv) {
		
		mv.setViewName("board/boardInsert");   //= return "board/write"
		return mv;
	}
	
	
	
	@RequestMapping(value = "/board/binsert.do", method = RequestMethod.POST)
	//void ��� ModelAndView�� �����, �����͵� �ְ� ��Ʈ���� �ְ�..? ������ �̵��� �ϰ� ������ ������
	public ModelAndView boardRegister(
			ModelAndView mv
			, @ModelAttribute Board board
			//÷������ ��� �� ����ؾ� �ϴ� Ŭ����
			,@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile
			,HttpServletRequest request
			, HttpSession session) { 
			
		try {
			String boardWriter = (String)session.getAttribute("customerId");  //session���� id �����ͼ� ���¹��
			if(boardWriter != null && !boardWriter.equals("")) {
				board.setBoardWriter(boardWriter);
				
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					//��������(�̸�, ������, ���, ũ��) �� ��������
					//�������� �����͸� �޾Ƽ� �����ϱ� ���� Map���
					Map<String, Object> bMap = this.saveFile(request, uploadFile);
					
					//Object�� longŸ���� �־ ����ߴµ� ���� ����ȯ�� �ʿ���
					board.setBoardFilename((String)bMap.get("fileName"));  //fileName �� �ؿ� saveFile�� �������� ���ϰ��� ��ġ�ؾ���
					board.setBoardFileRename((String)bMap.get("fileRename"));
					board.setBoardFilepath((String)bMap.get("filePath"));
					board.setBoardFileLength((long)bMap.get("fileLength"));
				}
				int result = bService.insertBoard(board);
				mv.setViewName("redirect:/board/blist.do");
			} else {
				mv.addObject("msg", "�α��� ���� �������� �ʽ��ϴ�");
				mv.addObject("error", "�α����� �ʿ��մϴ�.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFail");
			}
		} catch (Exception e) {
			//model.addAttribute("msg", "�Խñ� ��� ����");�� ���� ���
			mv.addObject("msg", "�Խñ� ��� ����");
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
		
		//�ΰ��� ���� �ϳ��� ������ ����ϴ¹� (searchCondition, searchKeyword)
		//1.VOŬ���� �����(�̹��غ�)
		//2. HashMap ���(���غ�)
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		
		int totalCount = bService.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		//put() �޼ҵ带 ����ؼ� key-value ������ �ϴµ� key��(�Ķ���)�� mapper.xml���� ���ȴ�.
		
				
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
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_SUBJECT LIKE '%����%';
//			searchList = service.searchNoticeByTitle(searchKeyword);
//			break;
//		case "content":
//			//SELECT * FROM NOTICE_TBL WHERE NOTICE_CONTENT = ?
//			searchList = service.searchNoticeByContent(searchKeyword);
//			break;
//		}
		if(!searchList.isEmpty()) {
//			model.addAttribute("searchCondition", searchCondition); paramMap���� �ᵵ ��
//			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("paramMap", paramMap);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "board/boardSearch";
		} else {
			model.addAttribute("msg", "�Խñ� ��ȸ�� �Ϸ���� �ʾҽ��ϴ�.");
			model.addAttribute("error", "�Խñ� ��ȸ ����");
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
				
				//================= �����̸� =================
				String fileName = uploadFile.getOriginalFilename();
				//(���� ������ �Ŀ� �� ��θ� DB�� �����ϵ��� �غ��ϴ� ��)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//������ ���� ��� �ڵ�����(���� ���ε��� ������ ������ ����)
				String saveFolder = root + "\\buploadFiles";
				File folder = new File(saveFolder);
				if(!folder.exists()) {
					folder.mkdir();
				}
				
				//================= ���ϰ�� =================
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				// **************** �������� *****************
				uploadFile.transferTo(file);
						
				//================= ����ũ�� =================
				long fileLength = uploadFile.getSize();
				
				//DB�� �����ϱ� ���� notice�� �����͸� Set�ϴ� �κ�
				board.setBoardFilename(fileName);
				board.setBoardFilepath(savePath);
				board.setBoardFileLength(fileLength);
			}


			int result = bService.updateBoard(board);
			
			if(result > 0) {
				return "redirect:/board/bdetail.do?boardNo=" + board.getBoardNo();
			} else {
				model.addAttribute("msg", "�Խñ� ���� ���� �Ϸ���� �ʾҽ��ϴ�.");
				model.addAttribute("error", "�Խñ� ������� ����");
				model.addAttribute("url", "/serviceFail.jsp");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "�����ڿ��� ���ǹٶ�");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFail";
		}
		
	}
	
	
	@RequestMapping(value="/board/bdelete.do", method = RequestMethod.GET)  //form�±׸� post, a�±״� get���
	public String removeInquiry(
			@RequestParam("boardNo") String boardNo
			,Model model) {
		//DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		try {
			int result = bService.removeBoard(boardNo);
			if(result>0) {
				//���� 
				return "redirect:/board/blist.do";
			}else {
				//����
				model.addAttribute("msg", "�Խñ� ���� ����");
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
		
		//resources ��� ���ϱ�
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		//���� ���� ��� ���ϱ�
		String savePath = root + "\\buploadFile";
		
		//���� �̸� ���ϱ�
		String fileName = uploadFile.getOriginalFilename();
		
		//���� Ȯ���� ���ϱ�
		String extension 
		= fileName.substring(fileName.lastIndexOf(".")+1);
		
		//�ð����� ���� �������ϱ�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis()));
		
		//���� ���� �� ���� �����
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		
		//��������
		File saveFile = new File(savePath + "\\" + fileRename);
		uploadFile.transferTo(saveFile);
		long fileLength = uploadFile.getSize();
		
		//�������� ����
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
			= (int)Math.ceil((double)totalCount/recordCountPerPage);   //6.2 -> 7 �ø����ִ� ��
		
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;  //�̰Ź���...
		
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		
		
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		
		return pInfo;
	}
	
	
	
	
	
}
