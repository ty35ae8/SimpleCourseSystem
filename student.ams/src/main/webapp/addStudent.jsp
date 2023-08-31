<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>新增學生</title>
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
	String userId = null,email = null,name = null;
	if(session!=null){
		userId = (String)session.getAttribute("userId");
		email = (String)session.getAttribute("email");
		name = (String)session.getAttribute("name");
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
				<li><a href="${pageContext.request.contextPath}/courseManage">功課表</a></li>
				<li><a href="${pageContext.request.contextPath}/adminLogin.jsp">管理員登入</a></li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<form action="${pageContext.request.contextPath}/addStudent" method="post">
		<h3>註冊</h3>
		<div class="form-group">
			<label for="id">學號</label>
			<input type="text" class="form-control" id="id" name="id" value=<%=userId%> readonly></input>
		</div>
		<div class="form-group">
			<label for="email">信箱</label>
			<input type="text" class="form-control" id="email" name="email" value=<%=email%> readonly></input>
		</div>
		<div class="form-group">
			<label for="name">姓名</label>
			<input type="text" class="form-control" id="name" name="name" value=<%=name%>></input>
		</div>
		<div class="form-group">
			<label for="major">科系</label>
			<input type="text" class="form-control" id="major" name="major"></input>
		</div>
		<div class="form-group">
			<label for="sex">性別  </label>
			<input type="radio" id="boy" name="sex" value="男">男 </input>
			<input type="radio" id="girl" name="sex" value="女">女</input>
		</div>
		<div class="form-group">
			<label for="year">年級  </label>
			<input type="radio" id="one" name="year" value="一">一 </input>
			<input type="radio" id="two" name="year" value="二">二 </input>
			<input type="radio" id="three" name="year" value="三">三 </input>
			<input type="radio" id="four" name="year" value="四">四 </input>
		</div>
		<button type="submit" class="btn btn-success">提交</button>
		<a href="${pageContext.request.contextPath}/index" class="btn btn-default">返回</a>
	</form>
</div>


</body>
</html>




