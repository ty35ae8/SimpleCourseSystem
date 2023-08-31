<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Course" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>編輯課程</title>
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
<%
	Course c = null;
	if(session!=null){
		c = (Course) session.getAttribute("course");
		session.setAttribute("id",c.getId());
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
				<li><a href="${pageContext.request.contextPath}/adminIndex">首頁</a></li>
				<li><a href="${pageContext.request.contextPath}/studentManage">學生管理</a></li>
				<li class="active"><a href="/courseManage">課程管理</a></li>
				<li><a href="${pageContext.request.contextPath}/chooseManage">選課管理</a></li>
				<li><a href="${pageContext.request.contextPath}/index">返回學生版主頁</a></li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<form action="/editCourse" method="post">
		<h3>編輯課程</h3>
		<div class="form-group">
			<label for="name">課程名稱</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="" value="${sessionScope.course.name}">
		</div>
		<div class="form-group">
			<label for="semester">星期</label>
			<select class="form-control" id="semester" name="semester">
				<%!
					String[] weeks = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
				%>
				
				<option value="${sessionScope.course.semester}">星期${sessionScope.course.semester}</option>
				
				<%
					for(String w:weeks){
						if(!(w.substring(2).equals(c.getSemester()))){
				
				%>
							<option value=<%=w.substring(2)%>><%=w%></option>
				<%
						}
					}
				%>		
			</select>
		</div>
		<div class="form-group">
			<label for="time1">上課時間</label>
			<select class="form-control" id="time1" name="time1">
				<%!
					String[] times1 = {"","8:00","9:00","10:00","11:00","13:00","14:00","15:00","16:00"};
				%>
				
				<option value="${sessionScope.course.time1}"><%=times1[c.getTime1()]%></option>
				
				<%
					for(int t=1;t<9;t++){
						if(t!=c.getTime1()){
				%>
							<option value=<%=t%>><%=times1[t]%></option>
				<%
						}
					}
				%>		
			</select>
		</div>
		<div class="form-group">
			<label for="time2">下課時間</label>
			<select class="form-control" id="time2" name="time2">
				<%!
					String[] times2 = {"","9:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00"};
				%>
				
				<option value="${sessionScope.course.time2}"><%=times2[c.getTime2()]%></option>
				
				<%
					for(int t=1;t<9;t++){
						if(t!=c.getTime2()){
				%>
							<option value=<%=t%>><%=times2[t]%></option>
				<%
						}
					}
				%>		
			</select>
		</div>
		<div class="form-group">
			<label for="credit">課程學分</label>
			<input type="text" class="form-control" id="credit" name="credit" placeholder="" value="${sessionScope.course.credit}">
		</div>
		<div class="form-group">
			<label for="belong">授課教師</label>
			<input type="text" class="form-control" id="belong" name="belong" placeholder="" value="${sessionScope.course.belong}">
		</div>
		<div class="form-group">
			<label for="place">上課地點</label>
			<input type="text" class="form-control" id="place" name="place" placeholder="" value="${sessionScope.course.place}">
		</div>
		<div class="form-group">
			<label for="amount">上限人數</label>
			<input type="text" class="form-control" id="amount" name="amount" placeholder="" value="${sessionScope.course.amount}">
		</div>
		<div class="form-group">
			<label for="detail">課程描述</label>
			<input type="text" class="form-control" id="detail" name="detail" placeholder="" value="${sessionScope.course.detail}">
		</div>
		<button type="submit" class="btn btn-success">提交</button>
		<a href="/courseManage" class="btn btn-default">返回</a>
	</form>
</div>

</body>
</html>

