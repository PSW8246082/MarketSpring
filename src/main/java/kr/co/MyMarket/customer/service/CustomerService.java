package kr.co.MyMarket.customer.service;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.customer.domain.Customer;

public interface CustomerService {
	
	

	/**
	 * 고객등록 Service
	 * @param customer
	 * @return  int
	 */
	public int insertCustomer(Customer customer);

	public Customer selectOneById(String customerId);

	public int deleteCustomer(String customerId);

	public int updateCustomer(Customer customer);

	/**
	 * 로그인 Service
	 * @param customer
	 * @return Customer
	 */
	public Customer customerLoginCheck(Customer customer);
	
}
