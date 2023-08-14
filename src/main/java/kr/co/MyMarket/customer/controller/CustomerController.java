package kr.co.MyMarket.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.MyMarket.customer.domain.Customer;
import kr.co.MyMarket.customer.service.CustomerService;

@Controller
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
				//���� -> ���������� �̵�
				return "redirect:/index.jsp";
			} else {
				//����
				model.addAttribute("msg", "ȸ������ ����");
				return "common/serviceFail";
			}
		} catch (Exception e) {
			e.printStackTrace();  //�ܼ�â�� ���� ǥ��
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFail";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	

}
