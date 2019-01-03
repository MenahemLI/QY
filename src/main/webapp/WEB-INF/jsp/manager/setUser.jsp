<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="theme/css/bootstrap.min.css" rel="stylesheet">
<link href="theme/css/datepicker3.css" rel="stylesheet">
<link href="theme/css/styles.css" rel="stylesheet">
<link rel="stylesheet" href="theme/css/verify.css" />
<link rel="stylesheet" href="theme/css/userSetCommon.css" />
<script type="text/javascript" src="theme/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="theme/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="theme/js/additional-methods.min.js"></script>
<script type="text/javascript" src="theme/js/authentic.js"></script>
<title>设置账户信息</title>
</head>
<body>
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					设置个人信息 || <a href="toLogin.action">登录</a>
				</div>
				<div class="panel-body">
					<form role="form" action="updateUserInfomation.action" method="post" id="">
						<fieldset>
							<fieldset>
								<div class="form-group">
									<label>用户名:</label> <input class="form-control" id="u_name" value="${user_one.u_name }"
										name="u_name" type="text" />
								</div>
								<div class="input-tip">
									<span id="u_name_error"></span>
								</div>
								<div class="form-group">
									<label>性别:</label><br /> <label> <input type="radio"
										name="u_gernder" value="男" checked="checked" /> 男
									</label> <label> <input type="radio" name="u_gernder" value="女" />
										女
									</label>
								</div>
								<div class="form-group">
									<label>职务:</label> <input class="form-control" name="u_post" value="${user_one.u_post }"
										type="text" id="u_post" />
								</div>
								<div class="input-tip">
									<span id="u_post_error"></span>
								</div>
								<div class="form-group">
									<label>职工编号:</label> <input class="form-control"
										value="${user_one.u_number }" name="u_number" type="text" id="u_number" />
								</div>
								<div class="input-tip">
									<span id="u_number_error"></span>
								</div>
								<div class="form-group">
									<label>证件号:</label> <input class="form-control"
										value="${user_one.u_idnum } "name="u_idnum" type="text" id="u_idnum" />
								</div>
								<div class="input-tip">
									<span id="u_idnum_error"></span>
								</div>
								<div class="form-group">
									<label>联系方式:</label> <input class="form-control"
										value="${user_one.u_tel }" name="u_tel" type="text" id="u_tel" />
								</div>
								<div class="input-tip">
									<span id="u_tel_error"></span>
								</div>
								<div class="form-group">
									<label>邮箱:</label> <input class="form-control"
										value="${user_one.u_email }" name="u_email" type="text" id="u_email" />
								<div class="input-tip" style="clear: both;">
									<span id="code_error"></span>
								</div>
								<input type="submit" value="提交" class="btn btn-primary">
								 <input type="reset" value="重置" class="btn btn-primary" />
							</fieldset>
					</form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
</body>

</html>