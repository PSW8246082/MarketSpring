package kr.co.MyMarket.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.MyMarket.board.domain.Reply;
import kr.co.MyMarket.board.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService rService;

	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public ModelAndView insertReply(
			ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {
		
		String url = "";
		
		try {
			String replyWriter = (String)session.getAttribute("customerId");
			reply.setReplyWriter(replyWriter);
			int result = rService.insertReply(reply);
			url = "/board/bdetail.do?boardNo="+ reply.getRefBoardNo();
			
			if(result>0) {
				mv.setViewName("redirect:" + url);
			} else {
				mv.addObject("msg", "��۵�� ��ȸ ����");
				mv.addObject("error", "��۵�� ��ȸ ����");
				mv.addObject("url", url);
				mv.setViewName("common/serviceFail");
			}
		} catch (Exception e) {
			mv.addObject("msg", "�����ڿ��� ���� �ٶ��ϴ�");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFail");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView updateReply(
			ModelAndView mv
			,@ModelAttribute Reply reply
			, HttpSession session) {
		
		String url = "";
		
		try {
			String replyWriter = (String)session.getAttribute("customerId");
			if(replyWriter != null && !replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				url = "/board/bdetail.do?boardNo="+ reply.getRefBoardNo();
				int result = rService.updateReply(reply);
				mv.setViewName("redirect:" + url);
			} else {
				mv.addObject("msg", "��ۼ��� ����");
				mv.addObject("error", "��ۼ��� ����");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFail");
			}
		} catch (Exception e) {
			mv.addObject("msg", "�����ڿ��� ���� �ٶ��ϴ�");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFail");
		}
		return mv;
	}
	
	
	
	
	//��ۻ���
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteReply(
			ModelAndView mv
			, @ModelAttribute Reply reply
//			, @RequestParam("replyNo") Integer replyNo  //Integer nullüũ ������
//			, @RequestParam("replyWriter") String replyWriter
			, HttpSession session) {  //���ǿ��� ���̵� �������� 1
		
		String url = "";
		
		try {
			String customerId = (String)session.getAttribute("customerId"); //���ǿ��� ���̵� �������� 2
			String replyWriter = reply.getReplyWriter();
			url = "/board/bdetail.do?boardNo=" + reply.getRefBoardNo();
			
			if(replyWriter != null && replyWriter.equals(customerId)) {
//				Reply reply = new Reply();
//				reply.setReplyNo(replyNo);
//				reply.setReplyWriter(replyWriter);
				
				int result = rService.deleteReply(reply);
				
				if(result > 0) {
					//����
					mv.setViewName("redirect:" + url);
				} else {
					mv.addObject("msg", "��ۻ��� ����");
					mv.addObject("error", "��ۻ��� ����");
					mv.addObject("url", url);
					mv.setViewName("common/serviceFail");
				}
			} else {
				mv.addObject("msg", "�ڽ��� ��۸� ������ �� �ֽ��ϴ�.");
				mv.addObject("error", "��ۻ��� �Ұ�");
				mv.addObject("url", url);
				mv.setViewName("common/serviceFail");
			}
			
			
		} catch (Exception e) {
			mv.addObject("msg", "�����ڿ��� ���� �ٶ��ϴ�");
			mv.addObject("error", e.getMessage());
//			mv.addObject("url", "board/detail.do?boardNo=");
			mv.addObject("url", url);
			mv.setViewName("common/serviceFail");
		}
 		
		return mv;
	}
	
	
}
