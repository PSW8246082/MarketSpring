package kr.co.MyMarket.inquiry.service;

import java.util.List;
import java.util.Map;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;

public interface InquiryService {

	/**
	 * ���ǻ��� ��� service
	 * @param notice
	 * @return int
	 */
	int insertInquiry(Inquiry inquiry);

	/**
	 * ���ǻ��� ��ü ���� ��ȸ service
	 * @return int
	 */
	int getListCount();

	/**
	 * ���ǻ��� ����Ʈ service
	 * @return List<Notice>
	 */
	List<Inquiry> selectInquiryList(PageInfo pInfo);

	/**
	 * ���ǻ��� �˻��Խù� ��ü ���� Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

	/**
	 * ���ǻ��� ���ǿ� ���� Ű����� �˻� service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Inquiry> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * ���ǻ��� ����ȸ
	 * @param inquiryNo
	 * @return inquiry
	 */
	Inquiry showDetailInquiry(String inquiryNo);

	/**
	 * ���Ǳ� ����
	 * @param inquiryNo
	 * @return int
	 */
	int removeInquiry(String inquiryNo);

	/**
	 * ���ǻ��� ����
	 * @param inquiry
	 * @return
	 */
	int updateInquiry(Inquiry inquiry);

	

	

}
