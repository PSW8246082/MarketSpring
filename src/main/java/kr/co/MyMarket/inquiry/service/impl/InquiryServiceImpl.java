package kr.co.MyMarket.inquiry.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;
import kr.co.MyMarket.inquiry.service.InquiryService;
import kr.co.MyMarket.inquiry.store.InquiryStore;

@Service
public class InquiryServiceImpl implements InquiryService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private InquiryStore iStore;
	
	
	@Override
	public int insertInquiry(Inquiry inquiry) {
		int result = iStore.insertInquiry(session, inquiry);
		return result;
	}

	
	@Override
	public int getListCount() {
		int result = iStore.selectListCount(session);
		return result;
	}


	@Override
	public List<Inquiry> selectInquiryList(PageInfo pInfo) {
		List<Inquiry> iList = iStore.selectInquiryList(session, pInfo);
		return iList;
	}

}
