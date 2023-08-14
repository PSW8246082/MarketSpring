package kr.co.MyMarket.customer.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.customer.domain.Customer;

public interface CustomerStore {

	int insertCustomer(SqlSession sqlSession, Customer customer);

	Customer selectCustomerLogin(SqlSession sqlSession, Customer customer);

}
