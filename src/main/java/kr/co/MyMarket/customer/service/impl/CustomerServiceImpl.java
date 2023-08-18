package kr.co.MyMarket.customer.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.MyMarket.customer.domain.Customer;
import kr.co.MyMarket.customer.service.CustomerService;
import kr.co.MyMarket.customer.store.CustomerStore;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerStore cStore;
	@Autowired
	private SqlSession SqlSession;

	@Override
	public int insertCustomer(Customer customer) {
		int result = cStore.insertCustomer(SqlSession, customer);
		return result;
	}

	@Override
	public Customer selectOneById(String customerId) {
		Customer cOne = cStore.selectOneById(SqlSession,customerId);
		return cOne;
	}

	@Override
	public int deleteCustomer(String customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Customer customerLoginCheck(Customer customer) {
		Customer cOne = cStore.selectCustomerLogin(SqlSession, customer);
		return cOne;
	}

	@Override
	public int removeCustomer(String customerId) {
		int result = cStore.removeCustomer(SqlSession, customerId);
		return result;
	}

	@Override
	public Customer showOneById(String customerId) {
		Customer cOne = cStore.showOneById(SqlSession, customerId);
		return cOne;
	}

	@Override
	public int modifyCustomer(Customer customer) {
		int result = cStore.modifyCustomer(SqlSession, customer);
		return result;
	}

	

}
