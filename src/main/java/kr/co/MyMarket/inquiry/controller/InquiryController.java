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
			if(! uploadFile.getOriginalFilename().equals("")) {
				
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
