package kr.co.MyMarket.board.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.inquiry.domain.Inquiry;

public interface BoardStore {

	/**
	 * 전체 게시물 갯수 Store
	 * @param session
	 * @return
	 */
	Integer selectListCount(SqlSession session);

	/**
	 * 게시글 전체 조회 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(SqlSession session, PageInfo pInfo);

	/**
	 * 게시글 상세 조회
	 * @param session
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(SqlSession session, Integer boardNo);

	/**
	 * 게시글 등록 Store
	 * @param session
	 * @param board
	 * @return
	 */
	int insertBoard(SqlSession session, Board board);

	/**
	 * 게시글 검색 게시물 전체 갯수 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);

	/**
	 * 게시글 조건에 따라 키워드로 조회 Store
	 * @param session
	 * @param searchCondition all, writer, title. content
	 * @param searchKeyword
	 * @return List
	 */
	List<Inquiry> searchBoardByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 게시글 상세조회 store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	Board showDetailBoard(SqlSession session, String boardNo);

	/**
	 * 게시글 수정 Store
	 * @param session
	 * @param board
	 * @return
	 */
	int updateBoard(SqlSession session, Board board);

	/**
	 * 게시글 삭제 Store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	int removeBoard(SqlSession session, String boardNo);

}
