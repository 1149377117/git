<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>异清轩博客管理系统</title>
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
  
  <!--引入头部文件  -->
  <jsp:include page="/public/head.jsp"></jsp:include>
  
  <div class="row">
  	<!-- 引入菜单页面 -->
    <jsp:include page="/public/menu.jsp"></jsp:include>
    
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <h1 class="page-header">信息总览</h1>
      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>文章</h4>
          <span class="text-muted">${IndexShowBeen.articleCount } 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>评论</h4>
          <span class="text-muted">${IndexShowBeen.commentCount } 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>友链</h4>
          <span class="text-muted">${IndexShowBeen.flinkCount } 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>访问量</h4>
          <span class="text-muted">${IndexShowBeen.visitorCount }</span> </div>
      </div>
      <h1 class="page-header">状态</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td>登录者: <span style="color:Aqua">${IndexShowBeen.nowLoginName }</span>，这是您第 <span>${IndexShowBeen.loginCount }</span> 次登录</td>
            </tr>
            <tr>
              <td>上次登录时间: ${IndexShowBeen.lastLastTime }, 上次登录IP:${IndexShowBeen.lastLastIP }</td>
            </tr>
          </tbody>
        </table>
      </div>
      <h1 class="page-header">系统信息</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
            <tr> </tr>
          </thead>
          <tbody>
            <tr>
              <td>管理员个数:</td>
              <td>${IndexShowBeen.adminCount } 人</td>
              <td>服务器软件:</td>
              <td>${IndexShowBeen.serverSoft }</td>
            </tr>
            <tr>
              <td>浏览器:</td>
              <td>${IndexShowBeen.browser }</td>
              <td>PHP版本:</td>
              <td>${IndexShowBeen.versionsOfPHP }</td>
            </tr>
            <tr>
              <td>操作系统:</td>
              <td>${IndexShowBeen.operatingSystem }</td>
              <td>PHP运行方式:</td>
              <td>${IndexShowBeen.modeOfPHP }</td>
            </tr>
            <tr>
              <td>登录者IP:</td>
              <td>${IndexShowBeen.thisLoginIP }</td>
              <td>MYSQL版本:</td>
              <td>${IndexShowBeen.versionsOfMySql }</td>
            </tr>
            <tr>
              <td>程序版本:</td>
              <td class="version">${IndexShowBeen.versionsOfProcedore } <font size="-6" color="#BBB">(20160108160215)</font></td>
              <td>上传文件:</td>
              <td>${IndexShowBeen.fileUpload } <font size="-6" color="#BBB">(最大文件：2M ，表单：8M )</font></td>
            </tr>
            <tr>
              <td>程序编码:</td>
              <td>${IndexShowBeen.encode }</td>
              <td>当前时间:</td>
              <td>${IndexShowBeen.nowTime }</td>
            </tr>
          </tbody>
          <tfoot>
            <tr></tr>
          </tfoot>
        </table>
      </div>
      
      <footer>
        <h1 class="page-header">程序信息</h1>
        <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td><span style="display:inline-block; width:8em">版权所有</span> 
              	${IndexShowBeen.versionsHave }</td>
            </tr>
            <tr>
              <td><span style="display:inline-block;width:8em">页面加载时间</span>
              	  ${IndexShowBeen.loadTime }  </td>
            </tr>
          </tbody>
        </table>
        </div>
      </footer>
    </div>
  </div>
</section>

</body>
</html>

