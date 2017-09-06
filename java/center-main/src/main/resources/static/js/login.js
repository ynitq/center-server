$(document).ready(function() {
	// 页面初始化
	console.log("初始化 登录表单");
	$("#login_form").submit(function() {

		var param = $(this).serialize();
		var url = "/api/public/login";

		console.log("登录", param);

		lzUtil.ajax(url, param, function(res) {
			window.location.href = "/";
		});

		return false;
	});
});
