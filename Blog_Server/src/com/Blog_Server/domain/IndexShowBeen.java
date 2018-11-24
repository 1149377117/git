package com.Blog_Server.domain;

public class IndexShowBeen {
	private String articleCount;  	//文章数dao
	private String commentCount;	//评论数dao
	private String flinkCount;		//友情链接数dao
	private String visitorCount;	//访问量
	private String nowLoginName;	//登陆者姓名dao
	private String loginCount;		//登录次数dao
	private String lastLastTime;	//上次登录时间dao
	private String lastLastIP;		//上次登录IPdao
	private String adminCount;		//管理员数dao
	private String serverSoft;		//服务器软件  -
	private String browser;			//浏览器 
	private String versionsOfPHP;		//PHP版本	-
	private String operatingSystem;	//操作系统 
	private String modeOfPHP;		//PHP运行方式	-
	private String thisLoginIP;		//本次登录IP
	private String versionsOfMySql;	//MySql版本	-
	private String versionsOfProcedore;	//程序版本	-
	private String fileUpload;		//上传文件	-
	private String encode;			//编码	-
	private String nowTime;			//当前时间
	private String versionsHave;	//版权所有	-
	private String loadTime;		//加载时间	-
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public String getFlinkCount() {
		return flinkCount;
	}
	public void setFlinkCount(String flinkCount) {
		this.flinkCount = flinkCount;
	}
	public String getVisitorCount() {
		return visitorCount;
	}
	public void setVisitorCount(String visitorCount) {
		this.visitorCount = visitorCount;
	}
	public String getNowLoginName() {
		return nowLoginName;
	}
	public void setNowLoginName(String nowLoginName) {
		this.nowLoginName = nowLoginName;
	}
	public String getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}
	public String getLastLastTime() {
		return lastLastTime;
	}
	public void setLastLastTime(String lastLastTime) {
		this.lastLastTime = lastLastTime;
	}
	public String getLastLastIP() {
		return lastLastIP;
	}
	public void setLastLastIP(String lastLastIP) {
		this.lastLastIP = lastLastIP;
	}
	public String getAdminCount() {
		return adminCount;
	}
	public void setAdminCount(String adminCount) {
		this.adminCount = adminCount;
	}
	public String getServerSoft() {
		return serverSoft;
	}
	public void setServerSoft(String serverSoft) {
		this.serverSoft = serverSoft;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getVersionsOfPHP() {
		return versionsOfPHP;
	}
	public void setVersionsOfPHP(String versionsOfPHP) {
		this.versionsOfPHP = versionsOfPHP;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getModeOfPHP() {
		return modeOfPHP;
	}
	public void setModeOfPHP(String modeOfPHP) {
		this.modeOfPHP = modeOfPHP;
	}
	public String getThisLoginIP() {
		return thisLoginIP;
	}
	public void setThisLoginIP(String thisLoginIP) {
		this.thisLoginIP = thisLoginIP;
	}
	public String getVersionsOfMySql() {
		return versionsOfMySql;
	}
	public void setVersionsOfMySql(String versionsOfMySql) {
		this.versionsOfMySql = versionsOfMySql;
	}
	public String getVersionsOfProcedore() {
		return versionsOfProcedore;
	}
	public void setVersionsOfProcedore(String versionsOfProcedore) {
		this.versionsOfProcedore = versionsOfProcedore;
	}
	public String getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	public String getVersionsHave() {
		return versionsHave;
	}
	public void setVersionsHave(String versionsHave) {
		this.versionsHave = versionsHave;
	}
	public String getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(String loadTime) {
		this.loadTime = loadTime;
	}
	@Override
	public String toString() {
		return "IndexShowBeen [articleCount=" + articleCount + ", commentCount=" + commentCount + ", flinkCount="
				+ flinkCount + ", visitorCount=" + visitorCount + ", nowLoginName=" + nowLoginName + ", loginCount="
				+ loginCount + ", lastLastTime=" + lastLastTime + ", lastLastIP=" + lastLastIP + ", adminCount="
				+ adminCount + ", serverSoft=" + serverSoft + ", browser=" + browser + ", versionsOfPHP="
				+ versionsOfPHP + ", operatingSystem=" + operatingSystem + ", modeOfPHP=" + modeOfPHP + ", thisLoginIP="
				+ thisLoginIP + ", versionsOfMySql=" + versionsOfMySql + ", versionsOfProcedore=" + versionsOfProcedore
				+ ", fileUpload=" + fileUpload + ", encode=" + encode + ", nowTime=" + nowTime + ", versionsHave="
				+ versionsHave + ", loadTime=" + loadTime + "]";
	}
	public IndexShowBeen(String articleCount, String commentCount, String flinkCount, String visitorCount,
			String nowLoginName, String loginCount, String lastLastTime, String lastLastIP, String adminCount,
			String serverSoft, String browser, String versionsOfPHP, String operatingSystem, String modeOfPHP,
			String thisLoginIP, String versionsOfMySql, String versionsOfProcedore, String fileUpload, String encode,
			String nowTime, String versionsHave, String loadTime) {
		super();
		this.articleCount = articleCount;
		this.commentCount = commentCount;
		this.flinkCount = flinkCount;
		this.visitorCount = visitorCount;
		this.nowLoginName = nowLoginName;
		this.loginCount = loginCount;
		this.lastLastTime = lastLastTime;
		this.lastLastIP = lastLastIP;
		this.adminCount = adminCount;
		this.serverSoft = serverSoft;
		this.browser = browser;
		this.versionsOfPHP = versionsOfPHP;
		this.operatingSystem = operatingSystem;
		this.modeOfPHP = modeOfPHP;
		this.thisLoginIP = thisLoginIP;
		this.versionsOfMySql = versionsOfMySql;
		this.versionsOfProcedore = versionsOfProcedore;
		this.fileUpload = fileUpload;
		this.encode = encode;
		this.nowTime = nowTime;
		this.versionsHave = versionsHave;
		this.loadTime = loadTime;
	}
	public IndexShowBeen() {
		super();
	}

}
