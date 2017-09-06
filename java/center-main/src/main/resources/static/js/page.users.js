/** 项目查看后台用户管理js */

$(function() {
	usersPage.init();// 初始化控件
});

var usersPage = {

	apiUrlList : "/api/user/list",// 获取所有的用户
	apiUrlView : "/api/user/view",// 查看用户
	apiUrlSave : "/api/user/save",// 保存用户
	apiUrlDelete : "/api/user/delete", // 删除用户
	apiUrlResetPassword : "/api/user/resetPassword", // 重置密码
	apiUrlLockOrUnlock : "/api/user/lockOrUnlock", // 解封或者冻结

	pageVm : {},// 整个页面的model

	/** 初始化 */
	init : function() {
		this.initPage(); // 整个页面

		this.loadList(); // 加载列表

		this.switchToNewMode();
	},

	/** 初始化左边用户列表 */
	initPage : function() {
		var that = this;
		this.pageVm = new Vue({
			el : '#userPage',

			data : {
				list : [],// 用户列表

				editMode : false, // 是编辑还是新建

				editRow : {// 编辑表单
					name : "",
					password : "",
					account : "",
					memo : "",
					email : "",
					phone : "",
					id : 0,
				},

				createTime : "",// 当前查看中的用户的创建时间
			},

			methods : {
				/** 查看 */
				view : function(index) {
					var cur = this.list[index]
					var po = cur.po;
					console.log("查看 index=%d", index, cur);

					this.editRow.name = po.name;
					this.editRow.password = "";
					this.editRow.account = po.account;
					this.editRow.memo = po.memo;
					this.editRow.email = po.email;
					this.editRow.phone = po.phone;
					this.editRow.id = po.id;

					this.createTime = cur.createTime;

					this.editMode = true;
				},

				newRow : function() {
					that.switchToNewMode();
				},

				deleteUser : function(id) {
					lzUtil.confirm("请确认", "确认要删除吗？", function() {
						that.doDelete(id);
					});
				},
			}
		});

		$("#edit_form").submit(function() {
			var param = that.pageVm.editRow;
			console.log("保存用户信息", param)

			lzUtil.ajax(that.apiUrlSave, param, function(res) {
				lzUtil.showMsg("保存成功");

				// 成功后，列表项要刷新
				that.loadList();

				// 同时切换到新建模式
				that.switchToNewMode();
			});

			return false;
		});
	},

	/** 删除 */
	doDelete : function(id) {
		var that = this;
		var param = {
			id : id,
		};
		lzUtil.ajax(that.apiUrlDelete, param, function(result, status, xhr) {
			lzUtil.showMsg("删除成功");

			// 成功后，列表项要刷新
			that.loadList();

			// 同时切换到新建模式
			that.switchToNewMode();
		});
	},

	/** 切换到新建模式 */
	switchToNewMode : function() {
		console.log("切换到新建模式");

		var row = this.pageVm.editRow;
		row.name = "";
		row.password = "";
		row.account = "";
		row.memo = "";
		row.email = "";
		row.phone = "";
		row.id = 0;

		this.pageVm.editMode = false;
	},

	/** 初始化时，加载该项目列表 */
	loadList : function() {
		console.log("加载用户列表");
		var that = this;
		lzUtil.ajax(that.apiUrlList, "", function(res) {
			that.pageVm.list = res.list;
		});
	},
};
