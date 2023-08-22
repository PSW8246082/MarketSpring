package kr.co.MyMarket.inquiry.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.MyMarket.inquiry.domain.Inquiry;
import kr.co.MyMarket.inquiry.domain.PageInfo;
import kr.co.MyMarket.inquiry.store.InquiryStore;

@Repository
public class InquiryStoreLogic implements InquiryStore{

	@Override
	public int insertInquiry(SqlSession session, Inquiry inquiry) {
		int result = session.insert("InquiryMapper.insertInquiry", inquiry);
		return result;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("InquiryMapper.selectListCount");
		return result;
	}

	@Override
	public List<Inquiry> selectInquiryList(SqlSession session, PageInfo pInfo) {
		
		int limit = pInfo.getRecordCountPerPage();  //한 페이지에 가져오는 행의 갯수(리스트개수)
		int offset = (pInfo.getCurrentPage()-1)*limit;;  //변하는 디폴드값 
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Inquiry> iList 
			= session.selectList("InquiryMapper.selectInquiryList", null, rowBounds);
		return iList;
	}

	@Override
	public int selectListCount(SqlSession session, Map<String, String> paramMap) {
		int result = session.selectOne("InquiryMapper.selectListByKeywordCount", paramMap);
		return result;
	}

	@Override
	public List<Inquiry> searchInquiryByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {
		
//		//두가지 값을 하나의 변수로 사용하는법 (searchCondition, searchKeyword)
//		//1.VO클래스 만들기(이미해봄)
//		//2. HashMap 사용(안해봄)
//		Map<String, String> paramMap = new HashMap<String, String>();
//		//put() 메소드를 사용해서 key-value 설정을 하는데 key값(파란색)이 mapper.xml에서 사용된다.
//		paramMap.put("searchCondition", searchCondition);
//		paramMap.put("searchKeyword", searchKeyword);
		
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Inquiry> searchList = session.selectList("InquiryMapper.searchInquiryByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public Inquiry showDetailInquiry(SqlSession session, String inquiryNo) {
		Inquiry inquiry = session.selectOne("InquiryMapper.showDetailInquiry", inquiryNo);
		return inquiry;
	}

	@Override
	public int removeInquiry(SqlSession session, String inquiryNo) {
		int result = session.delete("InquiryMapper.removeInquiry", inquiryNo);
		return result;
	}

}
