package com.Blog_Server.domain;

import java.sql.Timestamp;

public class Userparent {
	public int articlecount;
	public Timestamp userlasttime;
	public int getArticlecount() {
		return articlecount;
	}
	public void setArticlecount(int articlecount) {
		this.articlecount = articlecount;
	}
	public Timestamp getUserlasttime() {
		return userlasttime;
	}
	public void setUserlasttime(Timestamp userlasttime) {
		this.userlasttime = userlasttime;
	}
	@Override
	public String toString() {
		return "Userparent [articlecount=" + articlecount + ", userlasttime=" + userlasttime + "]";
	}
	
}
