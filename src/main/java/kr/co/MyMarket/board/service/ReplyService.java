package kr.co.MyMarket.board.service;

import java.util.List;

import kr.co.MyMarket.board.domain.Reply;

public interface ReplyService {

	/**
	 * �Խñ� ��� ��� Service
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);
	
	/**
	 * �Խñ� ��� ���� Service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);

	/**
	 * ��� ��ü ��ȸ Service
	 * @return
	 */
	List<Reply> selectReplyList(int refBoardNo);

	/**
	 * ��� ���� Service
	 * @param reply
	 * @return
	 */
	int deleteReply(Reply reply);

}
