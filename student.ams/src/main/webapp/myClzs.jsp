<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Course" %>
<%@ page import="student.ams.entity.Study" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="yyf" uri="http://www.student.com/common/" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>我的課程</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<style>
		body{
			padding-top: 50px;
			padding-bottom: 40px;
		}
	</style>
</head>
<body>

<%
	List<Study> courses = (List<Study>) session.getAttribute("clzs");
	String name = (String) session.getAttribute("user");
	String msg = (String) session.getAttribute("msg");
	int num = 1;
%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">學生線上選課系統</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/index">所有課程</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/showMyClasses">我的課程</a></li>
				<li><a href="${pageContext.request.contextPath}/showMySheet">功課表</a></li>
				<li><a href="${pageContext.request.contextPath}/adminLogin.jsp">管理員登入</a></li>
			</ul>
			<%
				if(name!=null){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a><%=name%> 歡迎您!</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">登出</a></li>
			</ul>
			<%
				}else{
			%>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<div id="g_id_onload"
						data-client_id="638065841277-68v884ftr24s4agjjm0ev0m1ffqe493v.apps.googleusercontent.com"
						data-context="signin"
						data-ux_mode="redirect"
						data-login_uri="${pageContext.request.contextPath}/login"
						data-auto_prompt="false">
					</div>
					<div class="g_id_signin"
						data-type="standard"
						data-shape="rectangular"
						data-theme="filled_black"
						data-text="signin_with"
						data-size="medium"
						data-logo_alignment="left"
						style="position: relative; top: 10px; right: 35px;">
					</div>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>

<div class="container theme-showcase" role="main">
  <div style="padding:15px; margin: 0 auto;">
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/index">首頁</a></li>
		<li class="active">我的課程</li>
	</ol>
	
	
	<%
		if(msg!=null){
			if(msg.equals("退選成功")){
	%>
	<div class="alert alert-warning" role="alert">
		<strong><%=msg%></strong>
	</div>
	<%
			}else{
	%>
	<div class="alert alert-danger" role="alert">
		<strong><%=msg%></strong>
	</div>
	<%
			}
			session.setAttribute("msg",null);
		}
		
		if(courses==null){
	%>
	<div class="well">
		請先登入!
	</div>
	<%
		}else{
	%>
	<table class="table table-striped">
		<thead>
		<tr>
			<th>編號</th>
			<th>課程代碼</th>
			<th>課程名稱</th>
			<th>上課時段</th>
			<th>課程學分</th>
			<th>授課教師</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		
		<%
			for(Study c:courses){
		%>
		<tr>
			<td><%=num++%></td>
			<td><%=c.getC_id()%></td>
			<td><%=c.getC_name()%></td>
			<td><%=c.getC_semester()%> <%=c.getTime1()%>-<%=c.getTime2()%></td>
			<td><%=c.getC_credit()%></td>
			<td><%=c.getC_belong()%></td>
			<td>
				<a href="${pageContext.request.contextPath}/showDetail?id=<%=c.getC_id()%>" class="btn btn-primary">詳情</a>
				<a href="${pageContext.request.contextPath}/delCourse?id=<%=c.getC_id()%>" class="btn btn-danger">退選</a>
			</td>
		</tr>
		<%
			}
		%>
		</tbody>
	</table>
	<%  } %>
	<div class="col-md-12 text-right">
		<yyf:page url="${pageContext.request.contextPath}/courseManage"></yyf:page>
	</div>
	
  </div>
</div>

</body>

<script src="https://accounts.google.com/gsi/client" async defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</html>




