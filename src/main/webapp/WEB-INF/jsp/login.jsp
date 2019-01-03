<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="theme/js/jquery-1.11.1.min.js"></script>

<title>Insert title here</title>
</head>

<body onkeydown="keyLogin();">
	<div class="row">
		<div
			class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">登录</div>
				<div class="panel-body">
					<form action="login.action" method="POST" id="login">
						<fieldset>
							<div class="form-group">
								<label>用户名:</label> <input class="form-control" placeholder="工号"
									name="u_number" id="u_number" type="text" autofocus="" />
							</div>
							<div class="form-group">
								<label>密码:</label> <input class="form-control" placeholder="密码"
									name="u_password"id="u_password" type="password" value="">
							</div>
							<input type="button" class="btn btn-primary"
								onclick="loginJudge()" id="loginfocus" value="登录" > 
								<a href="toRegister.action" class="btn btn-primary">注册</a> 
								<span id="login_span"></span>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<!-- /.col-->
	</div>
	<!-- /.row -->
	<script type="text/javascript">
	document.onkeydown = function(event_e){  
	    if(window.event) {  
	        event_e = window.event;    
	    }  
	    var int_keycode = event_e.charCode||event_e.keyCode;
	    if( int_keycode == '13' ) {
	    		loginJudge();
	    		}
	    else
	    	return event_e;
	    return false;
	    }  
	function loginJudge() {
		 var judge_user = false;
		    var judge_password = false;
		    if($("#u_number").val()=="")
		    	judge_user = true;
		    if($("#u_password").val()=="")
		    	judge_password = true;
		    if(judge_user||judge_password)
		    		{
		    		$("#login_span").html("请将信息补充完整");
					$("#login_span").css("color", "red");
		    		}
		$.ajax({
			type: "POST",
			url: "loginJudge.action",
			data: {
				number : $("#u_number").val(),
				password : $("#u_password").val()
			},
			success: function(res) {		
				if (res[0] == "S") {
					$("#login_span").html("");
					$("#login").submit();
				} else {
					$("#login_span").html("账号未注册或密码错误");
					$("#login_span").css("color", "red");
				}
			},
			error: function() {
			
			}
		})
	}</script>
	<script>
		!function($) {
			$(document).on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
							});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function() {
			if ($(window).width() > 768)
				$('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function() {
			if ($(window).width() <= 767)
				$('#sidebar-collapse').collapse('hide')
		})
	</script>

</body>
</html>