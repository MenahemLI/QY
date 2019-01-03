var pwd_ok = false;
$(function() {
	$("#old_u_password").blur(function() {
		$.ajax({
			type : "POST",
			async : false,
			cache : false,
			url : "setPassword.action",
			data : {
				old_u_password : $("#old_u_password").val(),
			},
			success : function(res) {
				if (res[0] == "E") {
					$("#old_u_password_span").css("display", "block");
				} else {
					$("#old_u_password_span").css("display", "none");
				}
			}

		});
	});
	// 密码判断位数
	var pwd_reg = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,20}$/;

	$("#new_u_password").focus(function() {
		$(this).css("outline", "none");
		$(this).attr("placeholder", "");
		$(this).parent().css("border-color", "#999");
		$("#new_u_password_span").css("font-size", "15px");
		$("#new_u_password_span").css("color", "#E38D13");
		$("#new_u_password_span").html("建议使用字母, 数字和符号两种及以上的组合, 6-20个字符");
	});
	$("#new_u_password").blur(function() {
		if ($(this).val() == "") {
			$(this).attr("placeholder", "Password");
			$("#new_u_password_span").html("");
			pwd_ok = false;
		} else if ($(this).val().length < 6 || $(this).val().length > 20) {
			// 长度不对
			$("#new_u_password_span").html("长度只能在6-20个字符之间");
			$("#new_u_password_span").css("color", "red");
			$(this).parent().css("border-color", "red");
			pwd_ok = false;
		} else if (!$(this).val().match(pwd_reg)) {
			// 不是两种及以上的组合
			$("#new_u_password_span").html("可能有被盗风险, 建议使用字母, 数字和符号两种及以上组合");
			$("#new_u_password_span").css("color", "#E38D13");
			$(this).parent().css("border-color", "#E38D13");
			pwd_ok = true;
		} else {
		}
	});
	// 重复密码判断位置1
	$("#new_u_password").blur(function() {
		var password_one = $("#new_re_password").val();
		var password_two = $("#new_u_password").val();
		if (password_one == password_two) {
			$("#new_re_password_span").css("display", "none");
		}

	});
	// 重复密码判断位置2
	$("#new_re_password").blur(function() {
		var password_one = $("#new_re_password").val();
		var password_two = $("#new_u_password").val();
		if (password_one == password_two) {
			$("#new_re_password_span").css("display", "none");
		} else {
			$("#new_re_password_span").css("display", "block");
		}
	});
});
function raemo() {
	$.ajax({
		type : "POST",
		async : false,
		cache : false,
		url : "setPassword.action",
		data : {
			old_u_password : $("#old_u_password").val(),
		},
		success : function(res) {
			if (res[0] == "E") {
				$("#old_u_password_span").css("display", "block");
			} else {

				$("#old_u_password_span").css("display", "none");
				var password_one = $("#new_re_password").val();
				var password_two = $("#new_u_password").val();
				var old_password = $("#old_u_password").val();
				if (password_one == password_two) {
					var i = 0;
					$("#new_re_password_span").css("display", "none");
					$("#tijiao input[type='password']").each(function() { // 循环遍历input
						if ($(this).val() == "")
							i++;
					})
					if (i == 0) {
						// 判断是否与旧密码相同
						if (old_password != password_two) {// 判断正则表达
					if (pwd_ok) {
								$("#tijiao").submit();
							} else {
								$("#register_span").html("密码格式错误");
								$("#register_span").css("color", "red");
							}
						} else {
							$("#register_span").html("与原密码相同，不可提交");
							$("#register_span").css("color", "red");
						}
					}
				} else {
					$("#new_re_password_span").css("display", "block");

				}
			}
		}

	});
}