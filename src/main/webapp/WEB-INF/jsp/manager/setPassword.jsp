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
<script src="theme/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="theme/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="theme/js/additional-methods.min.js"></script>
<script type="text/javascript" src="theme/js/userSetCommon.js"></script>
<title>三人行</title>
</head>
<body>
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					修改密码 
				</div>
				<div class="panel-body">
					<form role="form" action="updatePassword.action" method="post" id="tijiao">
						<fieldset>
							<fieldset>
							  <div class="form-group">
									<label>旧密码:</label> <input class="form-control"
										placeholder="Password" name="password" type="password"
										id="old_u_password" autocomplete="off"/>
										<div style="display:none ;color:red" id="old_u_password_span">密码输入与原密码不一致</div>
								</div>
								<div class="form-group">
									<label>密码:</label> <input class="form-control"
										placeholder="Password" name="u_password" type="password"
										id="new_u_password" autocomplete="off" />
								</div>
								<span id="new_u_password_span"></span>
								<div class="form-group">
									<label>确认密码:</label> <input class="form-control"
										placeholder="Repetion" name="re_password" type="password"
										id="new_re_password" autocomplete="off" />
										<div style="display:none ;color:red"  id="new_re_password_span">两次密码输入不一致</div>
								</div>
								<input type="button" value="提交" class="btn btn-primary" onclick="raemo()">
								 <input type="reset" value="重置" class="btn btn-primary" />
								 <span id="register_span"></span>
							</fieldset>
					</form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
</body>

</html>