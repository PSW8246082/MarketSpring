package kr.co.MyMarket.inquiry.service;

import java.util.List;

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

}
