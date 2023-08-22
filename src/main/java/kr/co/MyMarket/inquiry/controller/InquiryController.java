package kr.co.MyMarket.inquiry.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			if(! uploadFile.getOriginalFilename().equals("")) {
				
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
