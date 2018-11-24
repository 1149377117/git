package com.Blog_Server.domain;

import java.sql.Date;
/**
 * 评论
 * @author Administrator
 *
 */
public class Comment implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//
	private Long titleId;//文章Id
	private Long userId;//评论人Id
	private String content;//评论内容
	private Date commentTime;//评论时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTitleId() {
		return titleId;
	}
	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", titleId=" + titleId + ", userId=" + userId + ", content=" + content
				+ ", commentTime=" + commentTime + "]";
	}
	public Comment(Long id, Long titleId, Long userId, String content, Date commentTime) {
		super();
		this.id = id;
		this.titleId = titleId;
		this.userId = userId;
		this.content = content;
		this.commentTime = commentTime;
	}
	public Comment() {
		super();
	}
	
}
