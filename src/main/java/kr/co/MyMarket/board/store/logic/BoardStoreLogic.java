package kr.co.MyMarket.board.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.board.store.BoardStore;
import kr.co.MyMarket.inquiry.domain.Inquiry;

@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public Integer selectListCount(SqlSession session) {
		Integer result = session.selectOne("BoardMapper.selectListCount");
		return result;
	}

	@Override
	public List<Board> selectBoardList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		
		RowBounds rowbounds = new RowBounds(offset, limit);
		
		List<Board> bList = session.selectList("BoardMapper.selectBoardList", null, rowbounds);
		return bList;
	}

	@Override
	public Board selectBoardByNo(SqlSession session, Integer boardNo) {
		Board board = session.selectOne("BoardMapper.selectBoardByNo", boardNo);
		return board;
	}

	@Override
	public int insertBoard(SqlSession session, Board board) {
		int result = session.insert("BoardMapper.insertBoard", board);
		return result;
	}

	@Override
	public int selectListCount(SqlSession session, Map<String, String> paramMap) {
		int result = session.selectOne("BoardMapper.selectListByKeywordCount", paramMap);
		return result;
	}

	@Override
	public List<Inquiry> searchBoardByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {

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
		List<Inquiry> searchList = session.selectList("BoardMapper.searchBoardByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public Board showDetailBoard(SqlSession session, String boardNo) {
		Board board = session.selectOne("BoardMapper.showDetailBoard", boardNo);
		return board;
	}

	@Override
	public int updateBoard(SqlSession session, Board board) {
		int result = session.update("BoardMapper.updateBoard", board);
		return result;
	}

	@Override
	public int removeBoard(SqlSession session, String boardNo) {
		int result = session.delete("BoardMapper.removeBoard", boardNo);
		return result;
	}

}
