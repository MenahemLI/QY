<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统-注册</title>

</head>
<body>
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<h1 class="text-center">注册</h1>
			<form action="user_add.action"  method="post" >
				<div class="form-group">
					<label class="col-sm-4 control-label">用户名</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="username">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">重复密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" name="re_password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">性别</label>
					<div class="col-sm-8">
						<label class="radio-inline">
							<input type="radio" name="gender" value="男" checked>男
						</label>
						<label class="radio-inline">
							<input type="radio"  name="gender" value="女">女
						</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<input type="submit" value="注册" class="btn btn-default">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>