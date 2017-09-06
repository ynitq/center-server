/** 修改密码js */
$(function() {
	changeMyPassword.init();// 初始化控件
});

var changeMyPassword = {

	apiUrlchangePassword : "/api/public/changePassword",// 修改密码
	urlLogin : "/login", // logout的url，密码修改成功后，自动退出

	/** 表单对象 */
	adminVueModel : {},

	init : function() {
		console.log("初始化修改密码");
		
		var that = this;

		// 数据的绑定关系
		this.adminVueModel = new Vue({
			el : '#change_pwd_mod',

			data : {
				oldPassword : "",
				newPassword : "",
				newPassword2 : "",
			},
		});

		$("#change_passowrd_form").submit(function() {
			that.doChangePassword();
			return false;
		});
	},

	doChangePassword : function() {
		// 重置密码
		var that = this;
		
		var pwdStr = that.adminVueModel.newPassword;
		var pwdStr2 = that.adminVueModel.newPassword2;
		if (pwdStr != pwdStr2) {
			lzUtil.showErrorMsg("两次输入密码不一致！");
			return;
		}

		var param = that.adminVueModel.$data;
		console.log("修改密码", param)
		lzUtil.ajax(that.apiUrlchangePassword, param, function(res) {
			$('#change_pwd_mod').modal('hide');

			lzUtil.showMsg("密码修改成");
			
			//自动退出
			window.location.href = that.urlLogin;
		});

	},
};
