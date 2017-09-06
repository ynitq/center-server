/** 项目查看后台用户管理js */

$(function() {
	projectsPage.init();// 初始化控件
});

var projectsPage = {

	apiUrlProjectList : "/api/project/list",// 获取所有的项目
	apiUrlViewProject : "/api/project/view",// 查看项目
	apiUrlSaveProject : "/api/project/save",// 保存项目
	apiUrlSubscribe : "/api/project/userSubscribe", // 改变某用户对该项目的订阅状态

	apiUrlChartOfMemory : "/api/project/chartOfMemory", // 获取内存使用线图
	apiUrlChartOfAction : "/api/project/chartOfAction", // 获取请求数线图
	apiUrlChartOfSystemLoad : "/api/project/chartOfSystemLoad", // 获取系统负载线图

	curProjectId : -1, // 当前选择的项目id
	curPageNo : 1, // 当前页码

	detailVm : {},// 详情页的model
	projectVm : {},// 左边列表的model

	/** 初始化 */
	init : function() {
		this.initDetailModel();// 详情页
		this.initProjects();// 左边列表页

		// 在所有内容初始化完成后，加载数据
		this.loadProjects();

		// 启动定时刷新定时器
		this.startRefresh();
	},

	/** 启动自动刷新定时器 */
	startRefresh : function() {
		var that = this;
		var updateInterval = 1000;

		function update() {
			that.checkRefresh();
		}

		setInterval(update, updateInterval);
	},

	checkRefresh : function() {
		var remain = this.projectVm.refresRemainSec;
		if (remain <= 0) {
			this.loadProjects();
		} else {
			remain--;
			this.projectVm.refresRemainSec = remain;
		}
	},

	/** 初始化列表的翻页控件 */
	initPageBar : function() {
		var that = this;
		// 分页控件
		this.pageVueModel = new Vue({
			el : '#vm_invDataList_pagination',
			data : {
				pageList : [],
				prev : {},
				next : {},
				pageNo : 1,
				pageSize : 10,
				pageTotal : 1,
			},
			/** 绑定对象的方法 */
			methods : {
				goPage : function(pageNo, enable) {
					if (enable) {
						console.log("加载到指定页:", pageNo);
						that.curPageNo = pageNo;
						that.loadData();
					}
				},

				/** 更新服务器过来的数据，更新控件model的内容。对应服务器PageQueryResult类 */
				updateData : function(pageQueryResult) {
					this.pageList = pageQueryResult.pageList;
					this.pageNo = pageQueryResult.pageNo;
					this.pageTotal = pageQueryResult.pageTotal;
					this.next = pageQueryResult.next;
					this.prev = pageQueryResult.prev;
				}
			},
		});
	},

	/** 初始化详情页 modal_detail */
	initProjects : function() {
		var that = this;
		this.projectVm = new Vue({
			el : "#projectsDiv",

			data : {
				list : [],// 列表数据
				refresRemainSec : 30, // 刷新剩余时间
			},

			methods : {
				/** 点击时，加载项目数据 */
				load : function(id) {
					that.loadDetail(id);
				},

				refresh : function() {
					that.loadProjects();
				},

				/** 标题的class */
				getTitleClass : function(row) {
					var po = row.po;
					if (!po.needCheck) {
						return "text-muted";
					} else {
						if (po.down) {
							return "text-danger";
						} else {
							return "text-success";
						}
					}
				},

				/** 标题的logo class */
				getLogoClass : function(row) {
					var po = row.po;
					if (!po.needCheck) {
						return "glyphicon-ban-circle";
					} else {
						if (po.down) {
							return "glyphicon-remove";
						} else {
							return "glyphicon-ok";
						}
					}
				},
			}
		});
	},

	/** 初始化详情页 modal_detail */
	initDetailModel : function() {
		var that = this;
		this.detailVm = new Vue({
			el : "#detailDiv",

			data : {
				hasData : false, // 是否有数据

				info : {
					po : {},
				},// 数据

				userSubscribe : [],// 所有用户对该项目的订阅情况

				editRow : { // 用于和编辑项目的表单绑定
					displayName : "",
					memo : "",
					needCheck : false,
					sendWarn : false,
					id : 0,
				},
			},

			methods : {
				/** 订阅或者取消订阅 */
				subscribe : function(userId, subscribed) {
					that.doSubscribe(userId, subscribed);
				},
			}
		});

		$("#project_edit_form").submit(function() {
			lzUtil.ajaxSubmit("project_edit_form", that.apiUrlSaveProject, function(res) {

				lzUtil.showMsg("保存成功");

				// 成功后，列表项要刷新
				that.loadProjects();
				that.loadDetail(that.curProjectId);
			});

			return false;
		});
	},

	/** 订阅或者取消订阅 */
	doSubscribe : function(userId, subscribed) {
		var that = this;

		console.log("用户id=%d, 改变项目id=%d 的状态", userId, that.curProjectId);

		var param = {
			id : that.curProjectId,
			userId : userId,
		};

		lzUtil.ajax(that.apiUrlSubscribe, param, function(res) {
			that.detailVm.userSubscribe = res.userSubscribe;

			if (subscribed) {
				lzUtil.showMsg("已取消订阅");
			} else {
				lzUtil.showMsg("订阅成功");
			}
		});
	},

	/** 初始化时，加载该项目列表 */
	loadProjects : function() {
		console.log("加载项目列表")
		var that = this;
		that.projectVm.refresRemainSec = 30;

		lzUtil.ajax(that.apiUrlProjectList, "", function(res) {
			that.projectVm.list = res.list;

			// 如果没有数据
			if (res.list.length == 0) {
				that.detailVm.hasData = false;
			} else {
				if (that.curProjectId == -1) {
					var id = res.list[0].po.id;
					that.loadDetail(id);
				}
			}

		});
	},

	loadDetail : function(id) {
		console.log("查看数据 id=", id);
		var that = this;

		that.curProjectId = id;

		var param = {
			id : id
		};
		lzUtil.ajax(that.apiUrlViewProject, param, function(res) {
			that.detailVm.info = res.info;
			that.detailVm.hasData = true;
			that.detailVm.userSubscribe = res.info.userSubscribe;

			// 绑定表单
			var er = that.detailVm.editRow;
			er.displayName = res.info.po.displayName
			er.memo = res.info.po.memo
			er.needCheck = res.info.po.needCheck
			er.sendWarn = res.info.po.sendWarn
			er.id = res.info.po.id

		});

		that.loadChartData();
	},

	/** 加载线图数据 */
	loadChartData : function() {
		var that = this;

		var param = {
			projectId : that.curProjectId,
		}

		console.log("加载内存使用线图数据，id=", that.curProjectId);

		lzUtil.ajax(that.apiUrlChartOfMemory, param, function(res) {
			that.drawChartOfMemory(res.data);
		});
		lzUtil.ajax(that.apiUrlChartOfAction, param, function(res) {
			that.drawChartOfAction(res.data);
		});
		lzUtil.ajax(that.apiUrlChartOfSystemLoad, param, function(res) {
			that.drawChartOfSystemLoad(res.data);
		});
	},

	/** 画内存使用图 */
	drawChartOfMemory : function(data) {
		console.log("画内存使用图");
		var that = this;
		var chartId = "#chart_memory";

		var min = 0;
		var max = 0;
		if (data.length > 0) {
			min = data[0][0];
			max = data[data.length - 1][0];
		}

		var options = {
			xaxis : {
				show : true,
				mode : "time",
				// minTickSize : [ 1, "hour" ],
				min : min,
				max : max,
				timeformat : "%d %H:%M"
			},
			yaxis : {
				min : 0,
				tickFormatter : function(v) {
					return v + "M";
				}
			},
			series : {
				lines : {
					show : true
				},
				points : {
					show : true,
					radius : 2,
					lineWidth : 1
				},
			},
			colors : [ "#6ef146" ],
			grid : {
				// hoverable : true,
				tickColor : "#eee",
				borderColor : "#eee",
				borderWidth : 1
			},
		};

		$.plot($(chartId), [ data ], options);
	},

	/** 画请求数使用图 */
	drawChartOfAction : function(data) {
		console.log("画请求数使用图");
		var that = this;
		var chartId = "#chart_request";

		var min = 0;
		var max = 0;
		if (data.length > 0) {
			min = data[0][0];
			max = data[data.length - 1][0];
		}

		var options = {
			xaxis : {
				show : true,
				mode : "time",
				// minTickSize : [ 1, "hour" ],
				min : min,
				max : max,
				timeformat : "%d %H:%M"
			},
			yaxis : {
				min : 0,
				tickFormatter : function(v) {
					return v + "次";
				}
			},
			colors : [ "#4B8DF8" ],
			series : {
				lines : {
					show : true
				},
				points : {
					show : true,
					radius : 2,
					lineWidth : 1
				},
			},
			grid : {
				// hoverable : true,
				tickColor : "#eee",
				borderColor : "#eee",
				borderWidth : 1
			},
		};

		$.plot($(chartId), [ data ], options);
	},

	/** 画系统负载图 */
	drawChartOfSystemLoad : function(data) {
		console.log("画系统负载图");
		var that = this;
		var chartId = "#chart_system_load";

		var min = 0;
		var max = 0;
		if (data.length > 0) {
			min = data[0][0];
			max = data[data.length - 1][0];
		}

		var options = {
			xaxis : {
				show : true,
				mode : "time",
				// minTickSize : [ 1, "hour" ],
				min : min,
				max : max,
				timeformat : "%d %H:%M"
			},
			yaxis : {
				min : 0,
			},
			series : {
				lines : {
					show : true
				},
				points : {
					show : true,
					radius : 2,
					lineWidth : 1
				},
			},
			grid : {
				// hoverable : true,
				tickColor : "#eee",
				borderColor : "#eee",
				borderWidth : 1
			},
		};

		$.plot($(chartId), [ data ], options);
	},
};
