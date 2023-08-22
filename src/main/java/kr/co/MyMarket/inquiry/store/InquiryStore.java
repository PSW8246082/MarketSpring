package kr.co.MyMarket.inquiry.store;

import java.util.List;
import java.util.Map;

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

	/**
	 * ���ǻ��� �˻� �Խù� ��ü ���� Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);

	/**
	 * ���ǻ��� ���ǿ� ���� Ű����� ��ȸ Store
	 * @param session
	 * @param searchCondition all, writer, title. content
	 * @param searchKeyword
	 * @return List
	 */
	List<Inquiry> searchInquiryByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * ���ǻ��� ����ȸ store
	 * @param session
	 * @param inquiryNo
	 * @return Inquiry
	 */
	Inquiry showDetailInquiry(SqlSession session, String inquiryNo);

	/**
	 * ���Ǳ� ���� store
	 * @param session
	 * @param inquiryNo
	 * @return int
	 */
	int removeInquiry(SqlSession session, String inquiryNo);

}
