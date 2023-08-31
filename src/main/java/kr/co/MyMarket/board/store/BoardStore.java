package kr.co.MyMarket.board.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.inquiry.domain.Inquiry;

public interface BoardStore {

	/**
	 * ��ü �Խù� ���� Store
	 * @param session
	 * @return
	 */
	Integer selectListCount(SqlSession session);

	/**
	 * �Խñ� ��ü ��ȸ Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(SqlSession session, PageInfo pInfo);

	/**
	 * �Խñ� �� ��ȸ
	 * @param session
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(SqlSession session, Integer boardNo);

	/**
	 * �Խñ� ��� Store
	 * @param session
	 * @param board
	 * @return
	 */
	int insertBoard(SqlSession session, Board board);

	/**
	 * �Խñ� �˻� �Խù� ��ü ���� Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);

	/**
	 * �Խñ� ���ǿ� ���� Ű����� ��ȸ Store
	 * @param session
	 * @param searchCondition all, writer, title. content
	 * @param searchKeyword
	 * @return List
	 */
	List<Inquiry> searchBoardByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * �Խñ� ����ȸ store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	Board showDetailBoard(SqlSession session, String boardNo);

	/**
	 * �Խñ� ���� Store
	 * @param session
	 * @param board
	 * @return
	 */
	int updateBoard(SqlSession session, Board board);

	/**
	 * �Խñ� ���� Store
	 * @param session
	 * @param boardNo
	 * @return
	 */
	int removeBoard(SqlSession session, String boardNo);

}
