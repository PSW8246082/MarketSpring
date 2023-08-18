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

	/**
	 * 회원탈퇴 service
	 * @param customerId
	 * @return int
	 */
	public int removeCustomer(String customerId);

	/**
	 * 회원상세조회
	 * @param memberId
	 * @return Customer
	 */
	public Customer showOneById(String customerId);

	/**
	 * 회원정보수정
	 * @param customer
	 * @return int
	 */
	public int modifyCustomer(Customer customer);
	
}
