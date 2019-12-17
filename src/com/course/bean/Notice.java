package com.course.bean;

/**
 * 公告、通知表<br>
 * 存储通知信息
 * @author Thingcor
 *
 */
public class Notice extends TableHead{
	private String noticeContent;
	private String noticeTime;

	public Notice() {
		
	}
	public Notice(String noticeContent,String noticeTime) {
		this.noticeContent = noticeContent;
		this.noticeTime = noticeTime;
		
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	@Override
	public String toString() {
		return "Notice [noticeContent=" + noticeContent + "]";
	}
	
	
}
