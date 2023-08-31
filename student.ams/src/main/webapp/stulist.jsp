<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Course" %>
<%@ page import="student.ams.entity.Student" %>
<%@ page import="student.ams.entity.Study" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="yyf" uri="http://www.student.com/common/" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>學生列表</title>
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
	List<Study> students = (List<Study>) session.getAttribute("students");
	Course c = (Course) session.getAttribute("course");
	String name = (String) session.getAttribute("user");
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
				<li class="active"><a href="${pageContext.request.contextPath}/index">所有課程</a></li>
				<li><a href="${pageContext.request.contextPath}/showMyClasses">我的課程</a></li>
				<li><a href="${pageContext.request.contextPath}/courseManage">功課表</a></li>
				<li><a href="${pageContext.request.contextPath}/adminLogin.jsp">管理員登入</a></li>
			</ul>
			<%
				if(name!=null){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a><%=name%> 歡迎您!</a></li>
				<li><a id="change" href="#" data-toggle="modal" data-target="#myModal">修改密碼</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">登出</a></li>
			</ul>
			<%
				}else{
			%>
			<form class="navbar-form navbar-right" action="/login" method="post">
				<div class="form-group">
					<input type="text" placeholder="學號" class="form-control" name="id" required />
		
				</div>
				<div class="form-group">
					<input type="password" placeholder="密碼" class="form-control" name="pwd" required />
				</div>
				<button type="submit" class="btn btn-success">登入</button>
			</form>
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
		<li><a href="${pageContext.request.contextPath}/showDetail?id=<%=c.getId()%>"><%=c.getName()%></a></li>
		<li class="active">已選學生列表</li>
	</ol>
	
	<table class="table table-striped">
		<thead>
		<tr>
			<th>編號</th>
			<th>姓名</th>
			<th>學號</th>
			<th>科系</th>
		</tr>
		</thead>
		<tbody>
		
		<%
			for(Study s:students){
		%>
		<tr>
			<td><%=num++%></td>
			<td><%=s.getS_name()%></td>
			<td><%=s.getS_id()%></td>
			<td><%=s.getS_major()%></td>
		</tr>
		<%
			}
		%>
		
		</tbody>
	</table>
	<div class="col-md-12 text-right">
		<yyf:page url="${pageContext.request.contextPath}/courseManage"></yyf:page>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelleby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					x
					</button>
					<h4 class="modal-title" id=""myModalLabel>
						修改密碼
					</h4>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/changePwd" method="post">
						<div class="form-group">
							<label for="old">舊密碼</label>
							<input type="password" class="form-control" id="old" name="old">
						</div>
						<div class="form-group">
							<label for="newpwd">新密碼</label>
							<input type="password" class="form-control" id="newpwd" name="newpwd">
						</div>
						<div class="form-group">
							<label for="newagin">確認新密碼</label>
							<input type="password" class="form-control" id="newagin" name="newagin">
						</div>
						<div class="modal-footer">
							<input type="submit" class="btn btn-success" value="提交" />
							<button type="button"  class="btn btn-default" data-dismiss="modal">
								關閉
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
  </div>
</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>

</html>




