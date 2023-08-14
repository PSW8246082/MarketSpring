package kr.co.MyMarket.customer.domain;

import java.sql.Timestamp;

public class Customer {
	private String customerId;
	private String customerPw;
	private String customerName;
	private String customerNo;
	private String customerGender;
	private String customerEmail;
	private String customerPhone;
	private Timestamp customerDate;
	private Timestamp updateDate;
	private String customerYn;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	

	public Customer(String customerId, String customerPw) {
		super();
		this.customerId = customerId;
		this.customerPw = customerPw;
	}
	

	public Customer(String customerId, String customerPw, String customerName, String customerNo, String customerGender,
			String customerEmail, String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.customerName = customerName;
		this.customerNo = customerNo;
		this.customerGender = customerGender;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}

	

	public Customer(String customerId, String customerPw, String customerName, String customerEmail,
			String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getCustomerPw() {
		return customerPw;
	}


	public void setCustomerPw(String customerPw) {
		this.customerPw = customerPw;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerNo() {
		return customerNo;
	}


	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerGender() {
		return customerGender;
	}


	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public Timestamp getCustomerDate() {
		return customerDate;
	}


	public void setCustomerDate(Timestamp customerDate) {
		this.customerDate = customerDate;
	}


	public Timestamp getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}


	public String getCustomerYn() {
		return customerYn;
	}


	public void setCustomerYn(String customerYn) {
		this.customerYn = customerYn;
	}

}
