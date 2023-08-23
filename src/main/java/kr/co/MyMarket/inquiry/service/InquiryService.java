package kr.co.MyMarket.inquiry.service;

import java.util.List;
import java.util.Map;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;

public interface InquiryService {

	/**
	 * 문의사항 등록 service
	 * @param notice
	 * @return int
	 */
	int insertInquiry(Inquiry inquiry);

	/**
	 * 문의사항 전체 갯수 조회 service
	 * @return int
	 */
	int getListCount();

	/**
	 * 문의사항 리스트 service
	 * @return List<Notice>
	 */
	List<Inquiry> selectInquiryList(PageInfo pInfo);

	/**
	 * 문의사항 검색게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

	/**
	 * 문의사항 조건에 따라 키워드로 검색 service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Inquiry> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 문의사항 상세조회
	 * @param inquiryNo
	 * @return inquiry
	 */
	Inquiry showDetailInquiry(String inquiryNo);

	/**
	 * 문의글 삭제
	 * @param inquiryNo
	 * @return int
	 */
	int removeInquiry(String inquiryNo);

	/**
	 * 문의사항 수정
	 * @param inquiry
	 * @return
	 */
	int updateInquiry(Inquiry inquiry);

	

	

}
