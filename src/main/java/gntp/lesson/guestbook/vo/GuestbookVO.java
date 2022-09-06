package gntp.lesson.guestbook.vo;

import java.sql.Timestamp;
import java.util.ArrayList;

public class GuestbookVO {
	private int seq;
	private String userId;
	private String title;
	private String content;
	private Timestamp regDate;
	private int readCount;
	private ArrayList<ReplyVO> replyList;
	
	public GuestbookVO() {}
	public GuestbookVO(int seq,String userId, String title, String content, Timestamp regDate, int readCount) {
		this.seq = seq;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
	}
	
	public ArrayList<ReplyVO> getReplyList() {
		return replyList;
	}
	public void setReplyList(ArrayList<ReplyVO> replyList) {
		this.replyList = replyList;
	}
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
}
