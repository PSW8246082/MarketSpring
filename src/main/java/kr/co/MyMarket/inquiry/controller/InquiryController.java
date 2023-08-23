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
			//required=false �ʼ��� �ƴϾ ��, Integer�� null �� �� ����
			//�ּ�ó���� ���׿����ڿ� ���� ����
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			,Model model) {
		try {
//			int currentPage = page != 0 ? page : 1;
			//SELECT COUNT(*) FROM NOTICE_TBL
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			
			List<Inquiry> iList = service.selectInquiryList(pInfo);
			//List �������� ��ȿ�� üũ ��� 2����
			//1. isEmpty()
			//2. size()
			if(iList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("iList",iList);
				return "inquiry/inquireList";
			} else {
				model.addAttribute("msg", "���ǻ��� �����ȸ �Ϸ���� �ʾҽ��ϴ�.");
				model.addAttribute("error", "���ǻ��� �����ȸ ����");
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
	
	
	@RequestMapping(value = "/inquiry/iinsert.do", method = RequestMethod.GET)
	public String showInsertForm() {
		return "inquiry/inquireInsert";
	}
	
	
	@RequestMapping(value = "/inquiry/iinsert.do", method = RequestMethod.POST)
	public String insertInquiry(
			//@ModelAttribute�� ����Ϸ��� JSP�� NAME���� �ʵ尪�� ���ƾ���
			@ModelAttribute Inquiry inquiry
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				
				//================= �����̸� =================
				String fileName = uploadFile.getOriginalFilename();
				//(���� ������ �Ŀ� �� ��θ� DB�� �����ϵ��� �غ��ϴ� ��)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//������ ���� ��� �ڵ�����(���� ���ε��� ������ ������ ����)
				String saveFolder = root + "\\nuploadFiles";
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
				inquiry.setInquiryFilename(fileName);
				inquiry.setInquiryFilepath(savePath);
				inquiry.setInquiryFilelength(fileLength);
			}
			int result = service.insertInquiry(inquiry);
			if(result >0) {
				return "redirect:/inquiry/ilist.do";
			} else {
				model.addAttribute("msg", "���ǻ��� ����� �Ϸ���� �ʾҽ��ϴ�.");
				model.addAttribute("error", "���ǻ��� ��� ����");
				model.addAttribute("url", "/inquiry/iinsert.do");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "�����ڿ��� ���ǹٶ�");
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
		
		//�ΰ��� ���� �ϳ��� ������ ����ϴ¹� (searchCondition, searchKeyword)
		//1.VOŬ���� �����(�̹��غ�)
		//2. HashMap ���(���غ�)
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		//put() �޼ҵ带 ����ؼ� key-value ������ �ϴµ� key��(�Ķ���)�� mapper.xml���� ���ȴ�.
		
				
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
			return "inquiry/inquireSearch";
		} else {
			model.addAttribute("msg", "������ ��ȸ�� �Ϸ���� �ʾҽ��ϴ�.");
			model.addAttribute("error", "������ ��ȸ ����");
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
				//����
				model.addAttribute("inquiry", iOne);   //jsp���� ����Ϸ��� model.addAttribute�� ������
				return "inquiry/inquireDetail";
			} else {
				model.addAttribute("msg", "���ǻ��� ���� ��ȸ �Ϸ���� �ʾҽ��ϴ�.");
				model.addAttribute("error", "���ǻ��� ������ȸ ����");
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
	
	
	@RequestMapping(value="/inquiry/idelete.do", method = RequestMethod.GET)  //form�±׸� post, a�±״� get���
	public String removeInquiry(
			@RequestParam("inquiryNo") String inquiryNo
			,Model model) {
		//DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		try {
			int result = service.removeInquiry(inquiryNo);
			if(result>0) {
				//���� 
				return "redirect:/inquiry/ilist.do";
			}else {
				//����
				model.addAttribute("msg", "���Ǳ� ���� ����");
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
				
				//================= �����̸� =================
				String fileName = uploadFile.getOriginalFilename();
				//(���� ������ �Ŀ� �� ��θ� DB�� �����ϵ��� �غ��ϴ� ��)
				String root =
						request.getSession().getServletContext().getRealPath("resources");
				//������ ���� ��� �ڵ�����(���� ���ε��� ������ ������ ����)
				String saveFolder = root + "\\nuploadFiles";
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
				inquiry.setInquiryFilename(fileName);
				inquiry.setInquiryFilepath(savePath);
				inquiry.setInquiryFilelength(fileLength);
			}


			int result = service.updateInquiry(inquiry);
			
			if(result > 0) {
				return "redirect:/inquiry/idetail.do?inquiryNo=" + inquiry.getInquiryNo();
			} else {
				model.addAttribute("msg", "���ǻ��� ���� ���� �Ϸ���� �ʾҽ��ϴ�.");
				model.addAttribute("error", "���ǻ��� ������� ����");
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private PageInfo getPageInfo(Integer currentPage, int totalCount) {
		PageInfo pi = null;
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount;
		int startNavi;
		int endNavi;
		
		//��������ȯ,�ڵ�����ȯ
		naviTotalCount = (int)((double)totalCount/recordCountPerPage + 0.9);
		//Math.ceil((double)totalCount/recordCountPerPage) �� ��밡����
		
		//currentPage���� 1~5d�ϋ� startNavi�� 1�� �����ǵ��� �����ִ� ��
		startNavi 
			= ((int)((double)currentPage/naviCountPerPage + 0.9)-1)*naviCountPerPage +1;
		
		endNavi = startNavi + naviCountPerPage -1;
		//endNavi�� startNavi�� ������ naviCountPerPager���� ���ϹǷ� ���� �ִ밪���� Ŀ�� �� ����
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		pi = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
	}
	
	
	
}
