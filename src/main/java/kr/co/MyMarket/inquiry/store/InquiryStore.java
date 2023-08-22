package kr.co.MyMarket.inquiry.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;

public interface InquiryStore {
	
	/**
	 * ���ǻ��� ��� Store
	 * @param session
	 * @param inquiry
	 * @return int
	 */
	int insertInquiry(SqlSession session, Inquiry inquiry);

	/**
	 * ���ǻ��� ��� Store
	 * @param session
	 * @param inquiry
	 * @return int
	 */
	int selectListCount(SqlSession session);

	/**
	 * ���ǻ��� �����ȸ store
	 * @param session
	 * @param pInfo
	 * @return List<Notice>
	 */
	List<Inquiry> selectInquiryList(SqlSession session, PageInfo pInfo);

}
