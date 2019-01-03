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
<script type="text/javascript" src="theme/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="theme/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="theme/js/additional-methods.min.js"></script>
<script type="text/javascript" src="theme/js/authentic.js"></script>
<script type="text/javascript" src="theme/js/uploadPreview.js"></script>
<script type="text/javascript" src="theme/js/uploadPreview.min.js"></script>
<style type="text/css">
#web {
	margin: 0 auto;
	width: 400px
}

.title {
	font-size: 18px;
	padding-left: 25px;
	border-left: solid #999 1px;
	margin-bottom: 40px
}

.form_item {
	width: 398px;
	height: 52px;
	border: solid #ddd 1px;
	position: relative;
}

.form_item label {
	width: 90px;
	height: 52px;
	line-height: 52px;
	float: left;
	padding-left: 20px;
	font-size: 14px;
	color: #666
}

.form_item .code {
	position: absolute;
	right: 0;
	top: 0;
	text-align: center;
}

.form_item input {
	border: 0;
	font-size: 14px;
	width: 190px;
	height: 19px;
	padding-bottom: 11px;
	padding-left: 20px;
	padding-top: 16px
}

.input-tip {
	color: #c5c5c5;
	height: 27px;
	font-size: 12px;
	padding-top: 5px
}

.input-tip span {
	height: 22px;
	line-height: 22px
}

button, #btn {
	width: 100%;
	height: 54px;
	color: #fff;
	background-color: #e22;
	border: 0;
	font-size: 16px;
	font-family: "微软雅黑"
}

.input-tip {
	color: #c5c5c5;
	height: 27px;
	font-size: 12px;
	padding-bottom: 5px;
	margin-bottom: 5px;
	margin-top: -5px;
}

.input-tip span {
	height: 22px;
	line-height: 22px;
	text-align: center;
}
</style>
<title>注册</title>

<script type="text/javascript" >

</script>

 <script type="text/javascript">
 function F_Open_dialog()
 {
    $("#picturePath").click();
 }
  function picture() { 
    	new uploadPreview({ UpBtn: "picturePath", DivShow: "imgdiv", ImgShow: "imgShow" });
	alert("已经启动");
  }
    </script>
</head>
<body  onkeydown="enterLogin(event);">
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					注册 || <a href="toLogin.action">登录</a>
				</div>
				<div class="panel-body">
					<div style="coler: red">${msg }</div>
					<form role="form" action="register.action" method="post" id="tijiao"  name="tijiao " enctype="multipart/form-data">
						<fieldset>
							<fieldset>
								<div class="form-group">
									<label>用户名:</label> <input class="form-control" id="u_name"placeholder="Name"
										name="u_name" type="text" />
								</div>	
								<div class="input-tip">
									<span id="u_name_error"></span>
								</div>	
								<!-- 头像的设置 -->
                           <div class="form-group">
									<label>性别:</label><br /> <label> <input type="radio"
										name="u_gernder" value="男" checked="checked" /> 男
									</label> <label> <input type="radio" name="u_gernder" value="女" />
										女
									</label>
								</div>
								<div class="form-group">
									<label>职务:</label> <input class="form-control" name="u_post" placeholder="Post"
										type="text" id="u_post" />
								</div>
								<div class="input-tip">
									<span id="u_post_error"></span>
								</div>
								<div class="form-group">
									<label>编号:</label> <input class="form-control"
										placeholder="Numbar" name="u_number" type="text" id="u_number" />
								</div>
								<div class="input-tip">
									<span id="u_number_error"></span>
								</div>
								<div class="form-group">
									<label>证件号:</label> <input class="form-control"
										placeholder="Id No" name="u_idnum" type="text" id="u_idnum" />
								</div>
								<div class="input-tip">
									<span id="u_idnum_error"></span>
								</div>
								<div class="form-group">
									<label>联系方式:</label> <input class="form-control"
										placeholder="Telephone" name="u_tel" type="text" id="u_tel" />
								</div>
								<div class="input-tip">
									<span id="u_tel_error"></span>
								</div>
								<div class="form-group">
									<label>邮箱:</label> <input class="form-control"
										placeholder="Email" name="u_email" type="text" id="u_email" />
								</div>
								<div class="input-tip">
									<span id="u_email_error"></span>
								</div>
								<div class="form-group">
									<label>密码:</label> <input class="form-control"
										placeholder="Password" name="u_password" type="password"
										id="u_password" />
								</div>
								<div class="input-tip">
									<span id="u_password_error"></span>
								</div>
								<div class="form-group">
									<label>确认密码:</label> <input class="form-control"
										placeholder="Repetion" name="re_password" type="password"
										id="re_password" />
								</div>
								<div class="input-tip">
									<span id="re_password_error"></span>
								</div>
								<div class="form-group">
									<label>验证码:</label>
								</div>
								<div class="form-group">
									<input class="form-control2" style="float: left;margin-top:15px"
										placeholder="Verification code" name="code" type="text" id="code" />
									<canvas id="canvas" width="110px" height="65px" class="12"
										style="float: left;"></canvas>
								</div>
								<div class="input-tip" style="clear: both;">
									<span id="code_error"></span>
								</div>
								<input type="button" value="注册" class="btn btn-primary" id="register">
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