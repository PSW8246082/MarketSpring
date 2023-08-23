package kr.co.MyMarket.inquiry.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.MyMarket.customer.domain.Customer;
import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;
import kr.co.MyMarket.inquiry.service.InquiryService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService service;
	
	@RequestMapping(value = "/inquiry/ilist.do", method = RequestMethod.GET)
	public String showInquiryList(
			//required=false 필수가 아니어도 됨, Integer은 null 쓸 수 있음
			//주석처리된 삼항연산자와 같은 내용
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			,Model model) {
		try {
//			int currentPage = page != 0 ? page : 1;
			//SELECT COUNT(*) FROM NOTICE_TBL
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			
			List<Inquiry> iList = service.selectInquiryList(pInfo);
			//List 데이터의 유효성 체크 방법 2가지
			//1. isEmpty()
			//2. size()
			if(iList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("iList",iList);
				return "inquiry/inquireList";
			} else {
				model.addAttribute("msg", "문의사항 목록조회 완료되지 않았습니다.");
				model.addAttribute("error", "문의사항 목록조회 실패");
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
	
	
	@RequestMapping(value = "/inquiry/iinsert.do", method = RequestMethod.GET)
	public String showInsertForm() {
		return "inquiry/inquireInsert";
	}
	
	
	@RequestMapping(value = "/inquiry/iinsert.do", method = RequestMethod.POST)
	public String insertInquiry(
			//@ModelAttribute을 사용하려면 JSP의 NAME값이 필드값과 같아야함
			@ModelAttribute Inquiry inquiry
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				
				//================= 파일이름 =================
				String fileName = uploadFile.getOriginalFilename();
				//(내가 저장한 후에 그 경로르 DB에 저장하도록 준비하는 것)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//폴더가 없을 경우 자동생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
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
				inquiry.setInquiryFilename(fileName);
				inquiry.setInquiryFilepath(savePath);
				inquiry.setInquiryFilelength(fileLength);
			}
			int result = service.insertInquiry(inquiry);
			if(result >0) {
				return "redirect:/inquiry/ilist.do";
			} else {
				model.addAttribute("msg", "문의사항 등록이 완료되지 않았습니다.");
				model.addAttribute("error", "문의사항 등록 실패");
				model.addAttribute("url", "/inquiry/iinsert.do");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의바람");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/inquiry/iinsert.do");
			return "common/serviceFail";
		}
	}
	
	
	
	
	@RequestMapping(value = "/inquiry/search.do", method = RequestMethod.GET)
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
		
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		//put() 메소드를 사용해서 key-value 설정을 하는데 key값(파란색)이 mapper.xml에서 사용된다.
		
				
		List<Inquiry> searchList = service.searchNoticesByKeyword(pInfo, paramMap);
		
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
			return "inquiry/inquireSearch";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "데이터 조회 실패");
			model.addAttribute("url", "/inquiry/ilist.do");
			return "common/serviceFail";
		}
		
	}
	
	
	
	
	@RequestMapping(value = "/inquiry/idetail.do", method = RequestMethod.GET)
	public String showDetailInquiry(
			@RequestParam("inquiryNo") String inquiryNo
			, @ModelAttribute Inquiry inquiry
			, Model model) {
		try {
			Inquiry iOne = service.showDetailInquiry(inquiryNo);
			if(iOne != null) {
				//성공
				model.addAttribute("inquiry", iOne);   //jsp에서 사용하려면 model.addAttribute를 쓰세요
				return "inquiry/inquireDetail";
			} else {
				model.addAttribute("msg", "문의사항 내용 조회 완료되지 않았습니다.");
				model.addAttribute("error", "문의사항 내용조회 실패");
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
	
	
	@RequestMapping(value="/inquiry/idelete.do", method = RequestMethod.GET)  //form태그만 post, a태그는 get방식
	public String removeInquiry(
			@RequestParam("inquiryNo") String inquiryNo
			,Model model) {
		//DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		try {
			int result = service.removeInquiry(inquiryNo);
			if(result>0) {
				//성공 
				return "redirect:/inquiry/ilist.do";
			}else {
				//실패
				model.addAttribute("msg", "문의글 삭제 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	@RequestMapping(value = "/inquiry/update.do", method = RequestMethod.GET)
	public String showModifyInquiry(
			@RequestParam("inquiryNo") String inquiryNo
			, Model model) {
		Inquiry inquiry = service.showDetailInquiry(inquiryNo);
		model.addAttribute("inquiry", inquiry);
		return "inquiry/inquireUpdate";
	}
	
	
	
	@RequestMapping(value = "/inquiry/iupdate.do", method = RequestMethod.POST)
	public String updateInquiry(
			@ModelAttribute Inquiry inquiry
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		
		try {
				if(!uploadFile.getOriginalFilename().equals("")) {
				
				//================= 파일이름 =================
				String fileName = uploadFile.getOriginalFilename();
				//(내가 저장한 후에 그 경로르 DB에 저장하도록 준비하는 것)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//폴더가 없을 경우 자동생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
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
				inquiry.setInquiryFilename(fileName);
				inquiry.setInquiryFilepath(savePath);
				inquiry.setInquiryFilelength(fileLength);
			}


			int result = service.updateInquiry(inquiry);
			
			if(result > 0) {
				return "redirect:/inquiry/idetail.do?inquiryNo=" + inquiry.getInquiryNo();
			} else {
				model.addAttribute("msg", "문의사항 내용 수정 완료되지 않았습니다.");
				model.addAttribute("error", "문의사항 내용수정 실패");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private PageInfo getPageInfo(Integer currentPage, int totalCount) {
		PageInfo pi = null;
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount;
		int startNavi;
		int endNavi;
		
		//강제형변환,자동형변환
		naviTotalCount = (int)((double)totalCount/recordCountPerPage + 0.9);
		//Math.ceil((double)totalCount/recordCountPerPage) 도 사용가능함
		
		//currentPage값이 1~5d일떄 startNavi가 1로 고정되도록 구해주는 식
		startNavi 
			= ((int)((double)currentPage/naviCountPerPage + 0.9)-1)*naviCountPerPage +1;
		
		endNavi = startNavi + naviCountPerPage -1;
		//endNavi는 startNavi에 무조건 naviCountPerPager값을 더하므로 실제 최대값보다 커질 수 있음
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pi = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
	}
	
	
	
}
