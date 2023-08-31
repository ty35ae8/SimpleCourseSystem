<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>新增課程</title>
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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">學生線上選課系統</a>
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
	<form action="${pageContext.request.contextPath}/addCourse" method="post">
		<h3>新增課程</h3>
		<div class="form-group">
			<label for="id">課程代碼</label>
			<input type="text" class="form-control" id="id" name="id" placeholder="">
		</div>
		<div class="form-group">
			<label for="name">課程名稱</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="">
		</div>
		<div class="form-group">
			<label for="semester">星期</label>
			<select class="form-control" id="semester" name="semester">
				<option value="error">請選擇</option>
				<option value="一">星期一</option>
				<option value="二">星期二</option>
				<option value="三">星期三</option>
				<option value="四">星期四</option>
				<option value="五">星期五</option>
				<option value="六">星期六</option>
				<option value="日">星期日</option>
			</select>
		</div>
		<div class="form-group">
			<label for="time1">上課時間</label>
			<select class="form-control" id="time1" name="time1">
				<option value="error">請選擇</option>
				<option value="1">8:00</option>
				<option value="2">9:00</option>
				<option value="3">10:00</option>
				<option value="4">11:00</option>
				<option value="5">13:00</option>
				<option value="6">14:00</option>
				<option value="7">15:00</option>
				<option value="8">16:00</option>
			</select>
		</div>
		<div class="form-group">
			<label for="time2">下課時間</label>
			<select class="form-control" id="time2" name="time2">
				<option value="error">請選擇</option>
				<option value="1">9:00</option>
				<option value="2">10:00</option>
				<option value="3">11:00</option>
				<option value="4">12:00</option>
				<option value="5">14:00</option>
				<option value="6">15:00</option>
				<option value="7">16:00</option>
				<option value="8">17:00</option>
			</select>
		</div>
		<div class="form-group">
			<label for="credit">課程學分</label>
			<input type="text" class="form-control" id="credit" name="credit" placeholder="">
		</div>
		<div class="form-group">
			<label for="belong">授課教師</label>
			<input type="text" class="form-control" id="belong" name="belong" placeholder="">
		</div>
		<div class="form-group">
			<label for="place">上課地點</label>
			<input type="text" class="form-control" id="place" name="place" placeholder="">
		</div>
		<div class="form-group">
			<label for="amount">上限人數</label>
			<input type="text" class="form-control" id="amount" name="amount" placeholder="">
		</div>
		<div class="form-group">
			<label for="detail">課程描述</label>
			<input type="text" class="form-control" id="detail" name="detail" placeholder="">
		</div>
		<button type="submit" class="btn btn-success">提交</button>
		<a href="${pageContext.request.contextPath}/courseManage" class="btn btn-default">返回</a>
	</form>
</div>


</body>
</html>




