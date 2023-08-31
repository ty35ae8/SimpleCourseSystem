<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" context="text/html; charset=utf-8">
	<title>系統後台管理頁面</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/static/css/bamboo.css" rel="stylesheet">
	<link href="static/css/bamboo.css" rel="stylesheet">
</head>
<body>
<%
	if(session.getAttribute("id")==null){
		request.getRequestDispatcher("adminLogin.jsp").forward(request,response);
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
				<li class="active"><a href="/adminIndex">首頁</a></li>
				<li><a href="${pageContext.request.contextPath}/studentManage">學生管理</a></li>
				<li><a href="${pageContext.request.contextPath}/courseManage">課程管理</a></li>
				<li><a href="${pageContext.request.contextPath}/chooseManage">選課管理</a></li>
				<li><a href="${pageContext.request.contextPath}/index">返回學生版主頁</a></li>
			</ul>
		</div>
	</div>
</nav>

<div class="container" style="margin-top:150px">
	<div id="chart" class="chart" style="width:100%; height:450px;"></div>	
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bamboo.0.1.js"></script>

<script>
	var myChart = echarts.init(document.getElementById('chart'));
	var option = {
		title: {
			text: '課程選課統計表'
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: ['已選人數','可選人數']
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'value'
		},
		yAxis: {
			type: 'category',
			data: <%=session.getAttribute("listX")%>
		},
		series:[
			{
				name: '已選人數',
				type: 'bar',
				stack: '總數',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: <%=session.getAttribute("listSelected")%>
			},
			{
				name: '可選人數',
				type: 'bar',
				stack: '總數',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: <%=session.getAttribute("listLeft")%>
			}
		]
	};
	
	myChart.setOption(option);
	window.onresize = myChart.resize;
	window.onload = myChart.resize;
	
</script>


</body>
</html>