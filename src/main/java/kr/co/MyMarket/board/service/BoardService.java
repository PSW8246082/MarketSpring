package kr.co.MyMarket.board.service;

import java.util.List;
import java.util.Map;

import kr.co.MyMarket.board.domain.Board;
import kr.co.MyMarket.board.domain.PageInfo;
import kr.co.MyMarket.inquiry.domain.Inquiry;

public interface BoardService {

	/**
	 * 전체 게시물 갯수 Service
	 * @return
	 */
	Integer getListCount();

	/**
	 * 게시글 전체 조회 Service
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(PageInfo pInfo);

	/**
	 * 게시글 상세조회 Service
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(Integer boardNo);

	/**
	 * 게시글 등록 Service
	 * @param board
	 * @return int
	 */
	int insertBoard(Board board);

	/**
	 * 게시글 검색게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

	/**
	 * 게시글 조건에 따라 키워드로 검색 service
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Inquiry> searchBoardByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	
	/**
	 * 게시글 상세조회
	 * @param boardNo
	 * @return
	 */
	Board showDetailBoard(String boardNo);

	/**
	 * 게시글 수정
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);

	/**
	 * 게시글 삭제
	 * @param boardNo
	 * @return
	 */
	int removeBoard(String boardNo);

	

}
