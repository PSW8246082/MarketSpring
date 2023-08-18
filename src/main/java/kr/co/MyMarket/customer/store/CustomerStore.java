package kr.co.MyMarket.customer.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.customer.domain.Customer;

public interface CustomerStore {

	int insertCustomer(SqlSession sqlSession, Customer customer);

	Customer selectCustomerLogin(SqlSession sqlSession, Customer customer);

	int removeCustomer(SqlSession sqlSession, String customerId);

	Customer showOneById(SqlSession sqlSession, String customerId);

	int modifyCustomer(SqlSession sqlSession, Customer customer);

	Customer selectOneById(SqlSession sqlSession, String customerId);

}
