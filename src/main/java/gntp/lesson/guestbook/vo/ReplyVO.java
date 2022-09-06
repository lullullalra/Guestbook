package gntp.lesson.guestbook.vo;

import java.sql.Timestamp;

public class ReplyVO {
	private int replySeq;
	private String content;
	private Timestamp regDate;
	private int seq;
	
	public ReplyVO() {}
	public ReplyVO(int replySeq, String content, Timestamp regDate, int seq) {
		this.replySeq = replySeq;
		this.content = content;
		this.regDate = regDate;
		this.seq = seq;
	}
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
