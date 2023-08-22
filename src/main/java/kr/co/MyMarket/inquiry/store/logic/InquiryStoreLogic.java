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
		
		int limit = pInfo.getRecordCountPerPage();  //�� �������� �������� ���� ����(����Ʈ����)
		int offset = (pInfo.getCurrentPage()-1)*limit;;  //���ϴ� �����尪 
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
		
//		//�ΰ��� ���� �ϳ��� ������ ����ϴ¹� (searchCondition, searchKeyword)
//		//1.VOŬ���� �����(�̹��غ�)
//		//2. HashMap ���(���غ�)
//		Map<String, String> paramMap = new HashMap<String, String>();
//		//put() �޼ҵ带 ����ؼ� key-value ������ �ϴµ� key��(�Ķ���)�� mapper.xml���� ���ȴ�.
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
