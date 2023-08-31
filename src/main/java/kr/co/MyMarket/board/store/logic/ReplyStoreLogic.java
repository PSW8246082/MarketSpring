package kr.co.MyMarket.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.MyMarket.board.domain.Reply;
import kr.co.MyMarket.board.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore{

	@Override
	public int insertReply(SqlSession session, Reply reply) {
		int result = session.insert("ReplyMapper.insertReply", reply);
		return result;
	}
	
	@Override
	public int updateReply(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(int refBoardNo, SqlSession session) {
		List<Reply> rList = session.selectList("ReplyMapper.selectReplyList",refBoardNo);
		return rList;
	}

	//昏力 规过1)
//	@Override
//	public int deleteReply(SqlSession session, Reply reply) {
//	int result = session.delete("ReplyMapper.deleteReply", reply);
//	return result;
//	}
	
	//昏力 规过2)
	public int deleteReply(SqlSession session, Reply reply) {
	int result = session.update("ReplyMapper.deleteReply", reply);
	return result;
	}

}
