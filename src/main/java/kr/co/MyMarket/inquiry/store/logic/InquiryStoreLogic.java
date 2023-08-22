package kr.co.MyMarket.inquiry.store.logic;

import java.util.List;

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

}
