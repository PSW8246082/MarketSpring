package kr.co.MyMarket.inquiry.service;

import java.util.List;

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

}
