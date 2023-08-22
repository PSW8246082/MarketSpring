package kr.co.MyMarket.inquiry.store;

import java.util.List;
import java.util.Map;

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

	/**
	 * 문의사항 검색 게시물 전체 갯수 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);

	/**
	 * 문의사항 조건에 따라 키워드로 조회 Store
	 * @param session
	 * @param searchCondition all, writer, title. content
	 * @param searchKeyword
	 * @return List
	 */
	List<Inquiry> searchInquiryByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 문의사항 상세조회 store
	 * @param session
	 * @param inquiryNo
	 * @return Inquiry
	 */
	Inquiry showDetailInquiry(SqlSession session, String inquiryNo);

	/**
	 * 문의글 삭제 store
	 * @param session
	 * @param inquiryNo
	 * @return int
	 */
	int removeInquiry(SqlSession session, String inquiryNo);

}
