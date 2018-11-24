<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="index.jsp">报告</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="article.jsp">文章</a></li>
		<li><a href="notice.jsp">公告</a></li>
		<li><a href="comment.jsp">评论</a></li>
		<li><a data-toggle="tooltip" data-placement="bottom"
			title="网站暂无留言功能">留言</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="category.jsp">栏目</a></li>
		<li><a class="dropdown-toggle" id="otherMenu"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">其他</a>
			<ul class="dropdown-menu" aria-labelledby="otherMenu">
				<li><a href="flink.jsp">友情链接</a></li>
				<li><a data-toggle="modal" data-target="#areDeveloping">访问记录</a></li>
			</ul></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a class="dropdown-toggle" id="userMenu"
				data-toggle="dropdown" aria-haspopup="true" 
				aria-expanded="false">用户</a>
			<ul class="dropdown-menu" aria-labelledby="userMenu">
				<li><a data-toggle="modal" data-target="#areDeveloping">管理用户组</a></li>
				<li><a href="manage-user.jsp">管理用户</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="loginlog.jsp">管理登录日志</a></li>
			</ul>
		</li>
		<li><a class="dropdown-toggle" id="settingMenu"
			data-toggle="dropdown" aria-haspopup="true" 
			aria-expanded="false">设置</a>
			<ul class="dropdown-menu" aria-labelledby="settingMenu">
				<li><a href="setting.jsp">基本设置</a></li>
				<li><a href="readset.jsp">阅读设置</a></li>
				<li role="separator" class="divider"></li>
				<li><a data-toggle="modal" data-target="#areDeveloping">安全配置</a></li>
				<li role="separator" class="divider"></li>
				<li class="disabled"><a>扩展菜单</a></li>
			</ul>
		</li>
	</ul>
</aside>
