package kr.co.MyMarket.customer.service;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.customer.domain.Customer;

public interface CustomerService {
	
	

	/**
	 * ����� Service
	 * @param customer
	 * @return  int
	 */
	public int insertCustomer(Customer customer);

	public Customer selectOneById(String customerId);

	public int deleteCustomer(String customerId);

	public int updateCustomer(Customer customer);

	/**
	 * �α��� Service
	 * @param customer
	 * @return Customer
	 */
	public Customer customerLoginCheck(Customer customer);
	
}
