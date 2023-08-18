package kr.co.MyMarket.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String removeCustomer(
			@RequestParam("customerId") String customerId
			,Model model) {
		//DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		try {
			int result = service.removeCustomer(customerId);
			if(result>0) {
				//성공 메인페이지로 가고 세션파괴되어야함 로그아웃에  SessionStatus session이 있으니까 /member/logout.do을 이용해서 씀
				return "redirect:/customer/logout.do";
			}else {
				//실패
				model.addAttribute("msg", "회원탈퇴실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/customer/myInfo.do", method = RequestMethod.GET) 
	public String myInfo(
			@RequestParam("costomer-id") String customerId
			, @ModelAttribute Customer customer
			, Model model) {
		try {
			//SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?
			Customer cOne = service.showOneById(customerId);
			if(cOne != null) {
				model.addAttribute("customer", cOne);  //modify.jsp에서 사용할 수 있게 해줌
				return "costomer/MyInfo";
			}else {
				model.addAttribute("msg", "데이터조회 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	@RequestMapping(value = "/customer/update.do", method = RequestMethod.GET)
	public String showModifyView(
			@RequestParam("costomer-id") String customerId
			, Model model) {
		Customer customer = service.showOneById(customerId);
		model.addAttribute("customer", customer);
		return "costomer/CustomerModify";
	}
	
	
	
	@RequestMapping(value = "/customer/update.do", method = RequestMethod.POST)
	public String modifyCustomer(
			@RequestParam("customer-id") String customerId
			, @RequestParam("customer-pw") String customerPw
			, @RequestParam("customer-name") String customerName
			, @RequestParam("phone") String customerPhone
			, @RequestParam("email") String customerEmail
			, RedirectAttributes redirect
			, Model model) {
		//UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ? WHERE MEMBER_ID = ?

		try {
			Customer customer = new Customer(customerId, customerPw, customerName, customerEmail, customerPhone);
			int result = service.modifyCustomer(customer);
			
			if(result>0) {
				//성공 -> 마이페이지로 이동
				//costomer-id 마이페이지에서 불러온 값과 같아야 함(정보조회에서 불러온 아이디값)
				redirect.addAttribute("costomer-id", customerId);  //redirect시 쿼리스트링 보내주는 법 //customer/myInfo.do로 이동할떄 ? 뒤에 필요한 쿼리스트링임
				return "redirect:/customer/myInfo.do";  //$는 JSP에서만 쓰는것  , ?가 있을때는 +로 값을 넣어줌
			} else {
				//실패 -> 에러페이지 이동
				model.addAttribute("msg", "정보수정 완료 실패");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

	

}
