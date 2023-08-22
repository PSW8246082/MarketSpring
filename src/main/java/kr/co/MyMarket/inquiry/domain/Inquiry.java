package kr.co.MyMarket.inquiry.domain;

import java.sql.Timestamp;

public class Inquiry {
	
	private int inquiryNo;
	private String inquirySubject;
	private String inquiryContent;
	private String inquiryWriter;
	private Timestamp iCreateDate;
	private Timestamp iUpdateDate;
	private String inquiryFilename;
	private String inquiryFilepath;
	private long inquiryFilelength;
	
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public String getInquirySubject() {
		return inquirySubject;
	}
	public void setInquirySubject(String inquirySubject) {
		this.inquirySubject = inquirySubject;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public String getInquiryWriter() {
		return inquiryWriter;
	}
	public void setInquiryWriter(String inquiryWriter) {
		this.inquiryWriter = inquiryWriter;
	}
	public Timestamp getiCreateDate() {
		return iCreateDate;
	}
	public void setiCreateDate(Timestamp iCreateDate) {
		this.iCreateDate = iCreateDate;
	}
	public Timestamp getiUpdateDate() {
		return iUpdateDate;
	}
	public void setiUpdateDate(Timestamp iUpdateDate) {
		this.iUpdateDate = iUpdateDate;
	}
	public String getInquiryFilename() {
		return inquiryFilename;
	}
	public void setInquiryFilename(String inquiryFilename) {
		this.inquiryFilename = inquiryFilename;
	}
	public String getInquiryFilepath() {
		return inquiryFilepath;
	}
	public void setInquiryFilepath(String inquiryFilepath) {
		this.inquiryFilepath = inquiryFilepath;
	}
	public long getInquiryFilelength() {
		return inquiryFilelength;
	}
	public void setInquiryFilelength(long inquiryFilelength) {
		this.inquiryFilelength = inquiryFilelength;
	}
	
	@Override
	public String toString() {
		return "문의사항 [문의번호=" + inquiryNo + ", 제목=" + inquirySubject + ", 내용="
				+ inquiryContent + ", 작성자=" + inquiryWriter + ", 작성날짜=" + iCreateDate
				+ ", 수정날짜=" + iUpdateDate + ", 파일이름=" + inquiryFilename + ", 파일경로="
				+ inquiryFilepath + ", 파일길이=" + inquiryFilelength + "]";
	}
	
	

}
