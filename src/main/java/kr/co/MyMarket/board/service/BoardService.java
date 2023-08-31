package kr.co.MyMarket.board.service;

import java.util.List;
import java.util.Map;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.inquiry.domain.Inquiry;

public interface BoardService {

	/**
	 * ��ü �Խù� ���� Service
	 * @return
	 */
	Integer getListCount();

	/**
	 * �Խñ� ��ü ��ȸ Service
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(PageInfo pInfo);

	/**
	 * �Խñ� ����ȸ Service
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(Integer boardNo);

	/**
	 * �Խñ� ��� Service
	 * @param board
	 * @return int
	 */
	int insertBoard(Board board);

	/**
	 * �Խñ� �˻��Խù� ��ü ���� Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

	/**
	 * �Խñ� ���ǿ� ���� Ű����� �˻� service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Inquiry> searchBoardByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	
	/**
	 * �Խñ� ����ȸ
	 * @param boardNo
	 * @return
	 */
	Board showDetailBoard(String boardNo);

	/**
	 * �Խñ� ����
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);

	/**
	 * �Խñ� ����
	 * @param boardNo
	 * @return
	 */
	int removeBoard(String boardNo);

	

}
