<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>編輯學生</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<style>
		body{
			padding-top: 100px;
			padding-bottom: 40px;
			background-color: #eee;
		}
		form{
			max-width: 330px;
			padding: 15px;
			margin: 0 auto;
		}
	</style>
</head>
<body>

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
				<li><a href="${pageContext.request.contextPath}/adminIndex">首頁</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/studentManage">學生管理</a></li>
				<li><a href="${pageContext.request.contextPath}/courseManage">課程管理</a></li>
				<li><a href="${pageContext.request.contextPath}/chooseManage">選課管理</a></li>
				<li><a href="${pageContext.request.contextPath}/index">返回學生版主頁</a></li>
			</ul>
		</div>
	</div>
</nav>

<%
	Student s = null;
	if(session!=null){
		s = (Student)session.getAttribute("student");
	}
%>

<div class="container">
	<form action="${pageContext.request.contextPath}/changeStudent" method="post">
		<h3>編輯學生</h3>
		<div class="form-group">
			<label for="id">學號</label>
			<input type="text" class="form-control" id="id" name="id" value="<%=s.getId()%>" readonly></input>
		</div>
		<div class="form-group">
			<label for="email">信箱</label>
			<input type="text" class="form-control" id="email" name="email" value="<%=s.getEmail()%>" readonly></input>
		</div>
		<div class="form-group">
			<label for="name">姓名</label>
			<input type="text" class="form-control" id="name" name="name" value="<%=s.getName()%>"></input>
		</div>
		<div class="form-group">
			<label for="major">科系</label>
			<input type="text" class="form-control" id="major" name="major" value="<%=s.getMajor()%>"></input>
		</div>
		<div class="form-group">
			<label for="sex">性別</label>
			<%
				if(s.getSex()=='女'){
			%>
			<input type="radio" id="boy" name="sex" value="男">男</input>
			<input type="radio" id="girl" name="sex" value="女" checked="true">女</input>
			<%
				}else{
			%>
			<input type="radio" id="boy" name="sex" value="男" checked="true">男</input>
			<input type="radio" id="girl" name="sex" value="女">女</input>
			<%
				}
			%>
		</div>
		<div class="form-group">
			<label for="year">年級</label>
			<%!
				String[] years = {"一","二","三","四"};
			%>
			<%
				for(String y:years){
					if(y.equals(s.getYear())){
			%>
			<input type="radio" id="year" name="year" value="<%=y%>" checked="true"><%=y%></input>
			<%
					}else{
			%>
			<input type="radio" id="year" name="year" value="<%=y%>"><%=y%></input>
			<%
					}
				}
			%>
		</div>
		
		<button type="submit" class="btn btn-success">提交</button>
		<a href="${pageContext.request.contextPath}/studentManage" class="btn btn-default">返回</a>
	</form>
</div>


</body>
</html>




