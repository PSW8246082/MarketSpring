package kr.co.MyMarket.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.MyMarket.customer.domain.Customer;
import kr.co.MyMarket.customer.service.CustomerService;

@Controller
@SessionAttributes({"customerId", "customerName"})
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value="/customer/register.do", method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "costomer/Signup";
	}
	
	@RequestMapping(value="/customer/register.do", method = RequestMethod.POST)
	public String insertCustomer(
			HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam("customer-id") String customerId
			, @RequestParam("customer-pw") String customerPw
			, @RequestParam("customer-name") String customerName
			, @RequestParam("customer-age-year") String customerAgeYear
			, @RequestParam("customer-age-month") String customerAgeMonth
			, @RequestParam("customer-age-day") String customerAgeDay
			, @RequestParam("gender") String customerGender
			, @RequestParam("email") String customerEmail
			, @RequestParam("phone") String customerPhone
			, Model model) {
		
		String customerNo = customerAgeYear + "-" + customerAgeMonth + "-" + customerAgeDay;
		try {
			
			Customer customer = new Customer(customerId, customerPw, customerName, customerNo, customerGender, customerEmail, customerPhone);
			
			int result = service.insertCustomer(customer);
			if(result>0) {
				//성공 -> 메인페이지 이동
				return "redirect:/index.jsp";
			} else {
				//실패
				model.addAttribute("msg", "회원가입 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();  //콘솔창에 오류 표시
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	@RequestMapping(value="/customer/login.do", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		return "costomer/Login";
	}
	
	@RequestMapping(value="/customer/login.do", method = RequestMethod.POST)
	public String customerLogin(
			HttpServletRequest request
			, @RequestParam("costomer-id") String customerId
			, @RequestParam("costomer-pw") String customerPw
			, Model model) {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customer.setCustomerPw(customerPw);
			
			Customer cOne = service.customerLoginCheck(customer);
			
			if(cOne != null) { //session에 담아야 할 내용..
				model.addAttribute("customerId", cOne.getCustomerId());
				model.addAttribute("customerName", cOne.getCustomerName());
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "로그인 실패");
				return "common/serviceFail.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	@RequestMapping(value="/customer/logout.do", method = RequestMethod.GET)
	public String customerLoout(
			SessionStatus session
			, Model model) {
		try {
			if(session != null) {
				session.setComplete();
				if(session.isComplete()) {
					//세션 만료 유효성 체크
					//안에 넣으면 else만들어야 하니까 isComplete() 참고만.
				}
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "로그아웃 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	@RequestMapping(value="/customer/mypage.do", method = RequestMethod.GET)
	public String showMyPageForm(Model model) {
		return "costomer/MyPage";
	}
	
	
	
	
	@RequestMapping(value="/customer/delete.do", method = RequestMethod.GET)  //form태그만 post, a태그는 get방식
	public void removeCustomer() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
