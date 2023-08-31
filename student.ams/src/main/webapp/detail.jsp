<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Course" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>課程詳情</title>
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
	String name = null;
	String msg = null;
	Course c = null;
	
	if(session!=null){
		name = (String) session.getAttribute("user");
		msg = (String) session.getAttribute("msg");
		c = (Course) session.getAttribute("course");
	}
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
				<li class="active"><a href="${pageContext.request.contextPath}/index">所有課程</a></li>
				<li><a href="${pageContext.request.contextPath}/showMyClasses">我的課程</a></li>
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
			<li class="active">課程詳情</li>
		</ol>
		
		<%
			if(msg!=null){
				if(msg.equals("選課成功!")){
		%>
		<div class="alert alert-success" role="alert">
			<strong>選課成功!已同步到Google日曆</strong>
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
		%>
		
		<div class="container">
			<div class="page-header">
				<h1><%=c.getName()%></h1>
			</div>
			<div class="col-md-8">
				<div class="well">
					<%=c.getDetail()%>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">授課教師</h3>
					</div>
					<div class="panel-body">
						<%=c.getBelong()%>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">課程學分</h3>
					</div>
					<div class="panel-body">
						<%=c.getCredit()%>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">課程已選人數/上限</h3>
					</div>
					<div class="panel-body">
						<%=c.getSelected()%>/<%=c.getAmount()%>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">上課時段</h3>
					</div>
					<div class="panel-body">
						星期<%=c.getSemester()%> <%=c.getTime1()%>-<%=c.getTime2()%>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">上課地點</h3>
					</div>
					<div class="panel-body">
						<%=c.getPlace()%>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">課程操作</h3>
					</div>
					<div class="panel-body">
						<%
							if(session.getAttribute("user")==null){
						%>
						請先登入!
						<%
							}else{
						%>
							<div class="row" style="...">
								<div class="col-sm-2">
									<form action="/selectClz" method="post">
										<button class="btn btn-primary" type="submit">選課</button>
									</form>
								</div>
								<div class="col-sm-2">
									<form action="/showStudent?id=<%=c.getId()%>" method="post">
										<button class="btn btn-default" type="submit">查看已選名單</button>
									</form>
								</div>
							</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>

<script src="https://accounts.google.com/gsi/client" async defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</html>




