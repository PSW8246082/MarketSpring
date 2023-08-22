package kr.co.MyMarket.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.MyMarket.notice.service.NoticeService;

@Controller
public class NoticeController {

//	@Autowired
//	private NoticeService service;
	
	
	@RequestMapping(value = "/notice/clist.do", method = RequestMethod.GET)
	public String showNoticeCList(
			Model model) {
		return "notice/customerCenterList";
	}
}
