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
	public Customer selectCheckLogin(Customer market) {
		
		return null;
	}

	@Override
	public int insertCustomer(Customer customer) {
		int result = cStore.insertCustomer(SqlSession, customer);
		return result;
	}

	@Override
	public Customer selectOneById(String customerId) {
		// TODO Auto-generated method stub
		return null;
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

}
