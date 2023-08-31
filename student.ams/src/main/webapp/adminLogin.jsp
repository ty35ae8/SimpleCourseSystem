<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>管理員登入</title>
	<link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">

	<style>
		body{
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
		}
		.form-signin{
			max-width: 330px;
			padding: 15px;
			margin: 0 auto;
		}
		.form-signin .form-signin-heading,
		.form-signin .checkbox{
			margin-bottom: 10px;
		}
		.form-signin .checkbox{
			font-weight: normal;
		}
		.form-signin .form-control{
			position: relative;
			height: auto;
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
			padding: 10px;
			font-size: 16px;
		}
		.form-signin .form-control:focus{
			z-index: 2;
		}
		.form-signin input[type="email"]{
			margin-bottom: -1px;
			border-bottom-right-radius: 0;
			border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"]{
			margin-bottom: 10px;
			border-top-right-radius: 0;
			border-top-left-radius: 0;
		}
	</style>

</head>
<body>
	<div class="container">
		<form class="form-signin" action="/adminLogin" method="post">
			<h2 class="form-signin-heading">管理員登入</h2>
			<label for="inputUsername" class="sr-only">Username</label>
			<input type="text" name="username" id="inputUsername" class="form-control" placeholder="請輸入學號">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" name="pwd" id="inputPassword" class="form-control" placeholder="請輸入密碼">
			<button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
		</form>
	</div>
</body>
</html>