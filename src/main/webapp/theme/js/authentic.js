var code_ok = false;
var uname_ok = false;
var upost_ok = false;
var phone_ok = false;
var number_ok = false;
var idnum_ok = false;
var pwd_ok = false;
var confirm_pwd_ok = false;
var email_ok = false;
$(function() {
	var serp = "";
	// 用户名正则表达式 
	var a = 0;
	var flag = "error";
	var stri = "";
	var uname_reg = /^[\u4e00-\u9fa5]{2,20}$|^[\dA-Za-z_\-]{3,20}$/;
	var uname_ok = false;
	function gain(ops) {
		return $.ajax({
			type : "GET",
			async : false,
			cache : false,
			url : "findinf.action",
			data : {
				u_name : ops.val(),
				namec : ops.attr("name")
			},
			success : function(res) {

				serp = String(res);
			},
			error : function() {
				serp = res;
			}
		});
	}
	$("#u_name").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_name_error").css("font-size", "15px");
		$("#u_name_error").css("color", "#E38D13");
		$("#u_name_error").html("支持中文, 字母, 数字, \"-\", \"_\", 的组合, 2-20个字符");
	});
	$("#u_name").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Name");
			$("#u_name_error").html("");
			uname_ok = false;
		} else if (!$(this).val().match(uname_reg)) {
			$("#u_name_error").html("格式错误, 仅支持中文, 字母, 数字, \"-\", \"_\"的组合");
			$("#u_name_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			uname_ok = false;
		} else {
			uname_ok = true;
			$("#u_name_error").css("color", "#4CAE4C");
			$("#u_name_error").html("通过");
			a++;
		}
	});
	// 职务  
	var upost_ok = false;
	$("#u_post").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_post_error").css("font-size", "15px");
		$("#u_post_error").css("color", "#E38D13");
		$("#u_post_error").html("财务部职务只有经理和会计，注册前请核实");
	});
	$("#u_post").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Position");
			$("#u_post_error").html("");
			upost_ok = false;
		} else if ($(this).val() == "经理" || $(this).val() == "会计") {
			upost_ok = true;
			$("#u_post_error").css("color", "#4CAE4C");
			$("#u_post_error").html("通过");
			a++;
		} else {
			$("#u_post_error").html("只允许输入“经理““会计”");
			$("#u_post_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			upost_ok = false;

		}
	});

	// 手机号码输入框获取焦点  
	var phone_reg = /^1[3|4|5|7|8]\d{9}$/;
	var phone_ok = false;
	$("#u_tel").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_tel_error").css("font-size", "15px");
		$("#u_tel_error").css("color", "#E38D13");
		$("#u_tel_error").html("请输入您常用的手机号码：");
	});
	$("#u_tel").blur(function() {
		gain($("#u_tel"));
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Telephone");
			$("#u_tel_error").html("");
			phone_ok = false;
		} else if (!$(this).val().match(phone_reg)) {
			// 输入的不是手机号码  
			$("#u_tel_error").html("格式有错");
			$("#u_tel_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			phone_ok = false;
		} else if (serp[0] == '0') {

			$("#u_tel_error").html("被占用");
			$("#u_tel_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			phone_ok = false;
		} else {
			$("#u_tel_error").css("color", "#4CAE4C");
			$("#u_tel_error").html("通过");
			phone_ok = true;
			a++;
		}
	});
	//编码
	var number_reg = /\d{6}$/;
	var number_ok = false;
	$("#u_number").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_number_error").css("font-size", "15px");
		$("#u_number_error").css("color", "#E38D13");
		$("#u_number_error").html("请输入你的编号");
	});
	$("#u_number").blur(function() {
		gain($("#u_number"));
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Number");
			$("#u_number_error").html("");
			number_ok = false;
		} else if ($(this).val().length != 6) {
			// 长度不对  
			$("#u_number_error").html("只能输入6位数编码");
			$("#u_number_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			number_ok = false;
		} else if (!$(this).val().match(number_reg)) {
			// 有特殊字符  
			$("#u_number_error").html("您的编号格式不对");
			$("#u_number_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			number_ok = false;
		} else if (serp[0] == '0') {

			$("#u_number_error").html("被占用");
			$("#u_number_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			phone_ok = false;
		} else {
			$("#u_number_error").css("color", "#4CAE4C");
			$("#u_number_error").html("通过");
			number_ok = true;
			a++;
		}
	});
	//身份证号
	var idnum_reg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	var idnum_ok = false;
	$("#u_idnum").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_idnum_error").css("font-size", "15px");
		$("#u_idnum_error").css("color", "#E38D13");
		$("#u_idnum_error").html("请输入您的证件号");
	});
	$("#u_idnum").blur(function() {
		gain($("#u_idnum"));
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Number");
			$("#u_idnum_error").html("");
			idnum_ok = false;
		} else if (!$(this).val().match(idnum_reg)) {
			// 有特殊字符  
			$("#u_idnum_error").html("您输入的格式不对");
			$("#u_idnum_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			idnum_ok = false;
		} else if (serp[0] == '0') {
			$("#u_idnum_error").html("此身份证以注册");
			$("#u_idnum_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			idnum_ok = false;
		} else {
			$("#u_idnum_error").css("color", "#4CAE4C");
			$("#u_idnum_error").html("通过");
			idnum_ok = true;
			a++;
		}
	});
	//邮箱 
	var email_reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var email_ok = false;
	$("#u_email").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_email_error").css("font-size", "15px");
		$("#u_email_error").css("color", "#E38D13");
		$("#u_email_error").html("请输入您的邮箱地址");
	});
	$("#u_email").blur(function() {
		gain($("#u_email"));
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Number");
			$("#u_email_error").html("");
			email_ok = false;
		} else if (!$(this).val().match(email_reg)) {
			// 有特殊字符  
			$("#u_email_error").html("您输入的格式不对");
			$("#u_email_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			email_ok = false;
		} else if (serp[0] == '0') {
			$("#u_email_error").html("此邮箱以注册");
			$("#u_email_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			phone_ok = false;
		} else {
			$("#u_email_error").css("color", "#4CAE4C");
			$("#u_email_error").html("通过");
			email_ok = true;
			a++;
		}
	});
	//密码
	var pwd_reg = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/;
	var pwd_ok = false;
	$("#u_password").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#u_password_error").css("font-size", "15px");
		$("#u_password_error").css("color", "#E38D13");
		$("#u_password_error").html("建议使用字母, 数字和符号两种及以上的组合, 6-20个字符");
	});
	$("#u_password").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Password");
			$("#u_password_error").html("");
			pwd_ok = false;
		} else if ($(this).val().length < 6 || $(this).val().length > 20) {
			// 长度不对  
			$("#u_password_error").html("长度只能在6-20个字符之间");
			$("#u_password_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			pwd_ok = false;
		} else if (!$(this).val().match(pwd_reg)) {
			// 不是两种及以上的组合  
			$("#u_password_error").html("可能有被盗风险, 建议使用字母, 数字和符号两种及以上组合");
			$("#u_password_error").css("color", "#E38D13");
			$(this).parent().css("border-color", "#E38D13");
			pwd_ok = true;
		} else {
			$("#u_password_error").css("color", "#4CAE4C");
			$("#u_password_error").html("通过");
			pwd_ok = true;
			a++;
		}
	});

	var confirm_pwd_ok = false;
	$("#re_password").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#re_password_error").css("font-size", "15px");
		$("#re_password_error").css("color", "#E38D13");
		$("#re_password_error").html("请再次输入密码");
	});
	$("#re_password").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Password");
			$("#re_password_error").html("");
			confirm_pwd_ok = false;
		} else if ($(this).val() != $("#u_password").val()) {
			$("#re_password_error").html("两次输入的密码不一致");
			$("#re_password_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			confirm_pwd_ok = false;
		} else {
			$("#re_password_error").css("color", "#4CAE4C");
			$("#re_password_error").html("通过");
			confirm_pwd_ok = true;
			a++;
		}
	});
	/**生成一个随机数**/
	function randomNum(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	/**生成一个随机色**/
	function randomColor(min, max) {
		var r = randomNum(min, max);
		var g = randomNum(min, max);
		var b = randomNum(min, max);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	drawPic();
	document.getElementById("canvas").onclick = function(e) {
		e.preventDefault();
		drawPic();
	}

	/**绘制验证码图片**/
	function drawPic() {
		var canvas = document.getElementById("canvas");
		var width = canvas.width;
		var height = canvas.height;
		var ctx = canvas.getContext('2d');
		ctx.textBaseline = 'bottom';

		/**绘制背景色**/
		ctx.fillStyle = randomColor(180, 240); //颜色若太深可能导致看不清
		ctx.fillRect(0, 0, width, height);
		/**绘制文字**/
		var str = 'ABCEFGHJKLMNPQRSTWXY123456789';
		stri = "";
		for (var i = 0; i < 4; i++) {
			var txt = str[randomNum(0, str.length)];
			stri += txt;
			ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色
			ctx.font = randomNum(15, 40) + 'px SimHei'; //随机生成字体大小
			var x = 10 + i * 25;
			var y = randomNum(25, 45);
			var deg = randomNum(-45, 45);
			//修改坐标原点和旋转角度
			ctx.translate(x, y);
			ctx.rotate(deg * Math.PI / 180);
			ctx.fillText(txt, 0, 0);
			//恢复坐标原点和旋转角度
			ctx.rotate(-deg * Math.PI / 180);
			ctx.translate(-x, -y);
		}
		/**绘制干扰线**/
		for (var i = 0; i < 8; i++) {
			ctx.strokeStyle = randomColor(40, 180);
			ctx.beginPath();
			ctx.moveTo(randomNum(0, width), randomNum(0, height));
			ctx.lineTo(randomNum(0, width), randomNum(0, height));
			ctx.stroke();
		}
		/**绘制干扰点**/
		for (var i = 0; i < 100; i++) {
			ctx.fillStyle = randomColor(0, 255);
			ctx.beginPath();
			ctx.arc(randomNum(0, width), randomNum(0, height), 1, 0,
					2 * Math.PI);
			ctx.fill();
		}
	}
	var code_ok = false;
	$("#code").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#code_error").css("font-size", "15px");
		$("#code_error").css("color", "#E38D13");
		$("#code_error").html("看不清? 点击更换验证码");
	});
	$("#code").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "输入验证码");
			$("#code_error").html("验证码输入为空");
			$("#code_error").css("color", "red");
			code_ok = false;
		} else if ($(this).val().toLowerCase() == (stri.toLowerCase())) {
			// 输入错误的验证码  
			$("#code_error").html("验证码输入成功");
			$("#code_error").css("color", "green");
			code_ok = true;
			a++;

		} else {
			$("#code_error").html("验证码输入错误");
			$("#code_error").css("color", "red");
			$(this).parent().css("border-color", "red");
			code_ok = false;
		}
	});
	$("#register").click(
			function() {
				if (code_ok && confirm_pwd_ok && pwd_ok && email_ok && idnum_ok
						&& number_ok && phone_ok && upost_ok && uname_ok) {
					$("#tijiao").submit();
				} else {
					alert("您输入的信息不完整或有误");
				}
			});
});
function enterLogin(e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		if (code_ok && confirm_pwd_ok && pwd_ok && email_ok && idnum_ok
				&& number_ok && phone_ok && upost_ok && uname_ok) {
			$("#tijiao").submit();
		} else {
			alert("您输入的信息不完整或有误");
		}
	}
}
