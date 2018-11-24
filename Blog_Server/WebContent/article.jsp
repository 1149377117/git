<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文章管理</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="images/icon/icon.png">
<link rel="shortcut icon" href="images/icon/favicon.ico">
<script src="js/jquery-2.1.4.min.js"></script>
<!--[if gte IE 9]>
  <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="js/html5shiv.min.js" type="text/javascript"></script>
  <script src="js/respond.min.js" type="text/javascript"></script>
  <script src="js/selectivizr-min.js" type="text/javascript"></script>
<![endif]-->
<!--[if lt IE 9]>
  <script>window.location.href='upgrade-browser.html';</script>
<![endif]-->
</head>

<body class="user-select">
<section class="container-fluid">
  
  <!-- 引入头部文件 -->
  <jsp:include page="/public/head.jsp"></jsp:include>
  <!-- 引入头部文件 -->
  <div class="row">
    <!-- 引入菜单文件 -->
	<jsp:include page="/public/menu.jsp"></jsp:include>    
    <!-- 引入菜单文件 -->
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <form action="/Article/checkAll" method="post" >
        <h1 class="page-header">操作</h1>
        <ol class="breadcrumb">
          <li><a href="article_add.jsp">增加文章</a></li>
        </ol>
        <h1 class="page-header">管理 <span class="badge">7</span></h1>
        <div class="table-responsive">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th><span class="glyphicon glyphicon-th-large"></span> <span class="visible-lg">选择</span></th>
                <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">标题</span></th>
                <th><span class="glyphicon glyphicon-list"></span> <span class="visible-lg">栏目</span></th>
                <th class="hidden-sm"><span class="glyphicon glyphicon-tag"></span> <span class="visible-lg">标签</span></th>
                <th class="hidden-sm"><span class="glyphicon glyphicon-comment"></span> <span class="visible-lg">评论</span></th>
                <th><span class="glyphicon glyphicon-time"></span> <span class="visible-lg">日期</span></th>
                <th><span class="glyphicon glyphicon-pencil"></span> <span class="visible-lg">操作</span></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="update-article.html">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
              <tr>
                <td><input type="checkbox" class="input-control" name="checkbox[]" value="" /></td>
                <td class="article-title">这是测试的文章标题这是测试的文章标题这是测试的文章标题这是测试的文章标题</td>
                <td>这个是栏目</td>
                <td class="hidden-sm">PHP、JavaScript</td>
                <td class="hidden-sm">0</td>
                <td>2015-12-03</td>
                <td><a href="">修改</a> <a rel="6">删除</a></td>
              </tr>
            </tbody>
          </table>
        </div>
        
        <footer class="message_footer">
          <nav>
            <div class="btn-toolbar operation" role="toolbar">
              <div class="btn-group" role="group"> 
              	<a class="btn btn-default" onClick="select()">全选</a> 
              	<a class="btn btn-default" onClick="reverse()">反选</a> 
              	<a class="btn btn-default" onClick="noselect()">不选</a> 
              </div>
              <div class="btn-group" role="group">
                <button type="submit" class="btn btn-default" data-toggle="tooltip" 
                	data-placement="bottom" title="删除全部选中" 
                	name="checkbox_delete">删除</button>
              </div>
            </div>
            <ul class="pagination pagenav">
              <li class="disabled">
              	<a aria-label="Previous"> 
              		<span aria-hidden="true">&laquo;</span> 
              	</a> 
              </li>
              <li class="active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#" aria-label="Next"> 
              	<span aria-hidden="true">&raquo;</span> </a> 
              </li>
            </ul>
          </nav>
        </footer>
      </form>
    </div>
  </div>
</section>

<script>
//是否确认删除
$(function(){   
	$("#main table tbody tr td a").click(function(){
		var name = $(this);
		var id = name.attr("rel"); //对应id  
		if (event.srcElement.outerText == "删除") 
		{
			if(window.confirm("此操作不可逆，是否确认？"))
			{
				$.ajax({
					type: "POST",
					url: "/Article/delete",
					data: "id=" + id,
					cache: false, //不缓存此页面   
					success: function (data) {
						window.location.reload();
					}
				});
			};
		};
	});   
});
</script>
</body>
</html>

