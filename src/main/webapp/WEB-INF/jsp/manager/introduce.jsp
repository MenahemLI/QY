<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
	<head>
		<title>个人资料</title>
		<link href="theme/css/introduce.css" rel='stylesheet' type='text/css' />
		<script src="theme/js/jquery.min.js"></script>
		<link href="theme/css/introduce_two.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<div id="home" class="header">
			<div class="container">
				<!--头部-->
				<div class="top-header">
					<div class="logo">
						<h1>三人行集团</h1>
					</div>
				</div>
				<div class="banner-info">
					<div class="col-md-7 header-right">
						<h2 style="color:#FFFFFF">个人信息</h2>
						<ul class="address">
							<li>
								<ul class="address-text">
									<li><b>姓名</b></li>
									<li>${session.user.u_name }</li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>性别</b></li>
									<li>${session.user.u_gernder }</li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>编号</b></li>
									<li>${session.user.u_number }</li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>身份证编号</b></li>
									<li>${session.user.u_idnum }</li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>部门</b></li>
									<li>财务部</a></li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>职位</b></li>
									<li>${session.user.u_post }</li>
								</ul>
							</li>
							<li>
								<ul class="address-text">
									<li><b>联系方式</b></li>
									<li>${session.user.u_tel }${session.user.picturePath }</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>

</html>