<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="yyf" uri="http://www.student.com/common/" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>學生管理</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<style>
		body{
			padding-top: 50px;
			padding-bottom: 40px;
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
	List<Student> students = (List<Student>) session.getAttribute("students");
	String msg = (String) session.getAttribute("msg");
	int num = 1;
%>

<div class="container theme-showcase" role="main">
<div style="padding:15px; margin: 0 auto;">
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/adminIndex">首頁</a></li>
		<li class="active">學生管理</li>
	</ol>
	
	<div class="row" style="width:45%; margin-left:3px;">
    <div class="col-md-6 col-md-offset-0">
      <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/studentSearch">
        <div class="form-group">
          <div class="input-group">
            <input type="text" name="keyword" class="form-control" placeholder="請輸入學號或姓名...">
            <div class="input-group-btn">
              <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
          </div>
        </div>
      </form>
    </div>
	</div>
	
	
	<%
		if(msg!=null){
			if(msg.equals("更新學生成功")||msg.equals("刪除學生成功")){
	%>
	<div class="alert alert-success" style="..." role="alert"> <strong><%=msg%></strong> </div>
	<%
			}else{
	%>
	
	<div class="alert alert-danger" style="..." role="alert"> <strong><%=msg%></strong> </div>
	
	<%
			}
		  session.setAttribute("msg",null);
		}
	%>
	
	
	<table class="table table-striped">
		<thead>
		<tr>
			<th>編號</th>
			<th>學號</th>
			<th>姓名</th>
			<th>信箱</th>
			<th>科系</th>
			<th>性別</th>
			<th>年級</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<%
			for(Student s:students){
		%>
		<tr>
			<td><%=num++%></td>
			<td><%=s.getId()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getEmail()%></td>
			<td><%=s.getMajor()%></td>
			<td><%=s.getSex()%></td>
			<td><%=s.getYear()%></td>
			<td>
				<a href="${pageContext.request.contextPath}/changeStudent?id=<%=s.getId()%>" class="btn btn-primary">修改</a>
				<a href="${pageContext.request.contextPath}/delStudent?id=<%=s.getId()%>" class="btn btn-danger">刪除</a>
			</td>
		</tr>
		<%
			}
		%>
		</tbody>
	</table>
	<div class="col-md-12 text-right">
		<yyf:page url="${pageContext.request.contextPath}/${pageName}"></yyf:page>
	</div>
  </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</body>
</html>




