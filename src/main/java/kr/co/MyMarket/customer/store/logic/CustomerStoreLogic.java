package kr.co.MyMarket.customer.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.MyMarket.customer.domain.Customer;
import kr.co.MyMarket.customer.store.CustomerStore;

@Repository
public class CustomerStoreLogic implements CustomerStore{

	@Override
	public int insertCustomer(SqlSession sqlSession, Customer customer) {
		int result = sqlSession.insert("CustomerMapper.insertCustomer", customer);
		return result;
	}

	@Override
	public Customer selectCustomerLogin(SqlSession sqlSession, Customer customer) {
		Customer cOne = sqlSession.selectOne("CustomerMapper.selectCustomerLogin", customer);
		return cOne;
	}

	@Override
	public int removeCustomer(SqlSession sqlSession, String customerId) {
		int result = sqlSession.delete("CustomerMapper.removeCustomer", customerId);
		return result;
	}

	@Override
	public Customer showOneById(SqlSession sqlSession, String customerId) {
		Customer cOne = sqlSession.selectOne("CustomerMapper.showOneById", customerId);
		return cOne;
	}

	@Override
	public int modifyCustomer(SqlSession sqlSession, Customer customer) {
		int result = sqlSession.update("CustomerMapper.modifyCustomer", customer);
		return result;
	}

	@Override
	public Customer selectOneById(SqlSession sqlSession, String customerId) {
		Customer cOne = sqlSession.selectOne("CustomerMapper.selectOneById", customerId);
		return cOne;
	}

}
