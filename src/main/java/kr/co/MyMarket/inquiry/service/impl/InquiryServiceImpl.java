package kr.co.MyMarket.inquiry.service.impl;

import java.util.List;
import java.util.Map;

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


	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = iStore.selectListCount(session,paramMap);
		return result;
	}


	@Override
	public List<Inquiry> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Inquiry> iList = iStore.searchInquiryByKeyword(session, pInfo, paramMap);
		return iList;
	}


	@Override
	public Inquiry showDetailInquiry(String inquiryNo) {
		Inquiry inquiry = iStore.showDetailInquiry(session, inquiryNo);
		return inquiry;
	}


	@Override
	public int removeInquiry(String inquiryNo) {
		int result = iStore.removeInquiry(session, inquiryNo);
		return result;
	}


	

}
