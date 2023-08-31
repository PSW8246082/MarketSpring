package kr.co.MyMarket.board.domain;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private int refBoardNo;
	private String replyContent;
	private String replyWriter;
	private Date rCreateDate;
	private Date rUpdateDate;
	private char updateYn;
	private char rStatus;
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRefBoardNo() {
		return refBoardNo;
	}
	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public Date getrCreateDate() {
		return rCreateDate;
	}
	public void setrCreateDate(Date rCreateDate) {
		this.rCreateDate = rCreateDate;
	}
	public Date getrUpdateDate() {
		return rUpdateDate;
	}
	public void setrUpdateDate(Date rUpdateDate) {
		this.rUpdateDate = rUpdateDate;
	}
	public char getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(char updateYn) {
		this.updateYn = updateYn;
	}
	public char getrStatus() {
		return rStatus;
	}
	public void setrStatus(char rStatus) {
		this.rStatus = rStatus;
	}
	
	@Override
	public String toString() {
		return "Reply [��۹�ȣ=" + replyNo + ", �Խñ۹�ȣ=" + refBoardNo + ", ����=" + replyContent
				+ ", �ۼ���=" + replyWriter + ", �ۼ���=" + rCreateDate + ", ������=" + rUpdateDate
				+ ", ��������=" + updateYn + ", ��뿩��=" + rStatus + "]";
	}
}
