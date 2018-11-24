package com.Blog_Server.domain;

import java.sql.Date;
/**
 * 文章
 * @author Administrator
 *
 */
public class Article implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//
	private String title;//标题
	private String content;//内容
	private String keyWords;//关键字
	private String description;//描述
	private Long columnld;//栏目
	private String lable;//标签
	private String titlelmgs;//标题图片列表
	private String status;//状态
	private String type;//公开度
	private  Date releaseTime;//发布时间
	private String author;//作者
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getColumnld() {
		return columnld;
	}
	public void setColumnld(Long columnld) {
		this.columnld = columnld;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getTitlelmgs() {
		return titlelmgs;
	}
	public void setTitlelmgs(String titlelmgs) {
		this.titlelmgs = titlelmgs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", keyWords=" + keyWords
				+ ", description=" + description + ", columnld=" + columnld + ", lable=" + lable + ", titlelmgs="
				+ titlelmgs + ", status=" + status + ", type=" + type + ", releaseTime=" + releaseTime + ", author="
				+ author + "]";
	}
	public Article(Long id, String title, String content, String keyWords, String description, Long columnld,
			String lable, String titlelmgs, String status, String type, Date releaseTime, String author) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.keyWords = keyWords;
		this.description = description;
		this.columnld = columnld;
		this.lable = lable;
		this.titlelmgs = titlelmgs;
		this.status = status;
		this.type = type;
		this.releaseTime = releaseTime;
		this.author = author;
	}
	public Article() {
		super();
	}
	
	
}
