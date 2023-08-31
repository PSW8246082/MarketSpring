package kr.co.MyMarket.board.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.board.service.BoardService;
import kr.co.MyMarket.board.store.BoardStore;
import kr.co.MyMarket.inquiry.domain.Inquiry;



@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private BoardStore bStore;
	
	
	@Override
	public Integer getListCount() {
		Integer result = bStore.selectListCount(session);
		return result;
	}


	@Override
	public List<Board> selectBoardList(PageInfo pInfo) {
		List<Board> bList = bStore.selectBoardList(session,pInfo);
		return bList;
	}


	@Override
	public Board selectBoardByNo(Integer boardNo) {
		Board board = bStore.selectBoardByNo(session,boardNo);
		return board;
	}


	@Override
	public int insertBoard(Board board) {
		int result = bStore.insertBoard(session, board);
		return result;
	}


	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = bStore.selectListCount(session,paramMap);
		return result;
	}


	@Override
	public List<Inquiry> searchBoardByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Inquiry> iList = bStore.searchBoardByKeyword(session, pInfo, paramMap);
		return iList;
	}


	@Override
	public Board showDetailBoard(String boardNo) {
		Board board = bStore.showDetailBoard(session, boardNo);
		return board;
	}


	@Override
	public int updateBoard(Board board) {
		int result = bStore.updateBoard(session, board);
		return result;
	}


	@Override
	public int removeBoard(String boardNo) {
		int result = bStore.removeBoard(session, boardNo);
		return result;
	}

}
