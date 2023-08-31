package kr.co.MyMarket.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.MyMarket.board.domain.Reply;

public interface ReplyStore {

	/**
	 * �Խñ� ��� ��� Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);
	
	/**
	 * �Խñ� ��� ���� Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int updateReply(SqlSession session, Reply reply);


	/**
	 * ��� ��ü ��ȸ Store
	 * @param session
	 * @return
	 */
	List<Reply> selectReplyList(int refBoardNo, SqlSession session);

	/**
	 * ��� ���� Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int deleteReply(SqlSession session, Reply reply);
	

}
