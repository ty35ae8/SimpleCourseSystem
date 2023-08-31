<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="student.ams.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>功課表</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<style>
		.no-border{
			border:none;
		}
		body{
			padding-top: 50px;
			padding-bottom: 40px;
		}
	</style>
</head>
<body>

<%
	String name = (String) session.getAttribute("user");
	List<Course> courses = (List<Course>)session.getAttribute("studyinfos");
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
				<li><a href="${pageContext.request.contextPath}/showMyClasses">我的課程</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/showMySheet">功課表</a></li>
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
		<li class="active">功課表</li>
	</ol>
	
	<%
		if(name==null){
	%>
	<div class="well">
		請先登入!
	</div>
	<%
		}else{
	%>
	
		<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th bgcolor="#E0E0E0"></th>
				<th bgcolor="#E0E0E0"><b>星期一</b></th>
				<th bgcolor="#E0E0E0"><b>星期二</b></th>
				<th bgcolor="#E0E0E0"><b>星期三</b></th>
				<th bgcolor="#E0E0E0"><b>星期四</b></th>
				<th bgcolor="#E0E0E0"><b>星期五</b></th>
				<th bgcolor="#E0E0E0"><b>星期六</b></th>
				<th bgcolor="#E0E0E0"><b>星期日</b></th>
			</tr>
		</thead>
		<tbody>
			<%

				String[][] tableData = new String[8][7];
				String[] times = {"第1節<br>8:00-9:00", "第2節<br>9:00-10:00", "第3節<br>10:00-11:00",
					"第4節<br>11:00-12:00", "第5節<br>13:00-14:00", "第6節<br>14:00-15:00",
					"第7節<br>15:00-16:00", "第8節<br>16:00-17:00"}; 
				
				for (Course c : courses) {
					int dayIndex = 0;
					switch (c.getSemester()) {
						case "一":
							dayIndex = 0;
							break;
						case "二":
							dayIndex = 1;
							break;
						case "三":
							dayIndex = 2;
							break;
						case "四":
							dayIndex = 3;
							break;
						case "五":
							dayIndex = 4;
							break;
						case "六":
							dayIndex = 5;
							break;
						case "日":
							dayIndex = 6;
							break;
					}
					int start = c.getTime1() - 1;
					int end = c.getTime2() - 1;
					int rowspan = end - start + 1;
					
					Random rand = new Random();
					int R = rand.nextInt(256);
					int G = rand.nextInt(156)+100;
					int B = rand.nextInt(106)+100;
					String backgroundColor = " style=\"background-color:rgb("+ R +","+ G + "," + B + ")\"";
					
					for (int i = start; i <= end; i++) {
						if (i == start) {
							tableData[i][dayIndex] = rowspan + "\"" + backgroundColor + ">" + c.getName() + "<br>" + c.getPlace() + "<br>" + c.getBelong();
						} else {
							tableData[i][dayIndex] = "";
						}
					}
				}
				
				
				for (int i = 0; i < times.length; i++) {
					out.println("<tr>");
					out.println("<td  bgcolor=\"#E0E0E0\"><b>" + times[i] + "</b></td>");
					for (int j = 0; j < 7; j++) {
							if(tableData[i][j]!=null&&tableData[i][j]!="")
								out.println("<td rowspan=\"" + tableData[i][j] + "</td>");
							else if(tableData[i][j]==null)
								out.println("<td></td>");

					}
					out.println("</tr>");
				}
			%>
	</div>
	<%
		}
	%>

</body>

<script src="https://accounts.google.com/gsi/client" async defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</html>




