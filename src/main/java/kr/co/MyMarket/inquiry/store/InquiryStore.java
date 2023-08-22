package kr.co.MyMarket.inquiry.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;

public interface InquiryStore {
	
	/**
	 * 문의사항 등록 Store
	 * @param session
	 * @param inquiry
	 * @return int
	 */
	int insertInquiry(SqlSession session, Inquiry inquiry);

	/**
	 * 문의사항 등록 Store
	 * @param session
	 * @param inquiry
	 * @return int
	 */
	int selectListCount(SqlSession session);

	/**
	 * 문의사항 목록조회 store
	 * @param session
	 * @param pInfo
	 * @return List<Notice>
	 */
	List<Inquiry> selectInquiryList(SqlSession session, PageInfo pInfo);

}
