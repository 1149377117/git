package com.Blog_Server.service;

import java.sql.SQLException;

import com.Blog_Server.Dao.IndexDao;
import com.Blog_Server.Exception.UserLoginException;
import com.Blog_Server.domain.IndexShowBeen;
import com.Blog_Server.domain.User;

public class IndexService {
	private IndexDao indexDao= new IndexDao();

	/**
	 * 获取IndexShowBeen对象
	 * @param isb
	 * @param loginUser
	 * @return
	 * @throws UserLoginException 
	 */
	public IndexShowBeen getIndexShowBeen(IndexShowBeen isb, User loginUser) throws UserLoginException {
		
		try {
			//设置文章数
			String articleCount = indexDao.getArticleCount()+"";
			isb.setArticleCount(articleCount);
			
			//设置评论数
			String commentCount = indexDao.getCommentCount()+"";
			isb.setCommentCount(commentCount);
			
			
			String lastLastTime = indexDao.getLastLastTime(loginUser)+"";//设置上次登录时间
			isb.setLastLastTime(lastLastTime);
			
			String flinkCount = indexDao.getFlinkCount()+"";//设置友情链接
			isb.setFlinkCount(flinkCount);
			
			isb.setNowLoginName(loginUser.getName());//设置当前登录名字
			
			String loginCount= indexDao.getLoginCount(loginUser)+"";
			isb.setLoginCount(loginCount);//设置当前用户登录次数
			
			String lastLastIP= indexDao.getLastLastIP(loginUser)+"";
			isb.setLastLastIP(lastLastIP);//设置当前用户最后一次登录IP
			
			String adminCount = indexDao.getAdminCount()+"";
			isb.setAdminCount(adminCount);//查询管理员个数
			return isb;
		} catch (SQLException e) {
			throw new UserLoginException("系统繁忙，请稍后再试");
		}
	}
	

}
