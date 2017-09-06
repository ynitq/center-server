<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理-监控系统</title> <#include "include/style.ftl"/>
</head>
<body>

	<#include "include/header.ftl"/>

	<div class="main-content" id="userPage">
		<!-- 左边的树 -->
		<div class="panel panel-default leftTreeDiv">
			<div class="panel-heading">用户</div>
			<!-- List group -->
			<div class="list-group">
				<a class="list-group-item" href="javascript:;" v-for="(row,index) in list" @click="view(index)"> <span
						class="text-success">{{row.po.name}}</span> ({{row.po.account}})
					<h5 class="text-muted">电话:{{row.po.phone}}</h5>
				</a>
			</div>
		</div>
		<!-- /左边的树 -->

		<!-- 右边列表区 -->
		<div class="panel panel-default detailDiv" id="detailDiv">
			<div class="panel-heading">用户信息</div>
			<div class="panel-body">
				<div class="container-fluid">
					<form class="form-horizontal" id="edit_form" role="form">

						<template v-if="editMode"> <!-- 编辑模式 -->
						<input type="hidden" name="id" :value="editRow.id" />
						<h3>修改/查看用户</h3>
						<div class="form-group">
							<label class="col-sm-2 control-label">账号</label>
							<div class="col-sm-8">
								<p class="form-control-static">{{editRow.account}}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">创建时间</label>
							<div class="col-sm-8">
								<p class="form-control-static">{{createTime}}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" placeholder="可输入新密码" v-model="editRow.password" />
								<p class="help-block">不为空时，表示重置该账号的密码</p>
							</div>
						</div>
						</template>
						<template v-else> <!-- 新建模式 -->
						<h3>创新新用户</h3>
						<div class="form-group">
							<label class="col-sm-2 control-label">账号<span class="glyphicon glyphicon-asterisk required"></span></label>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="请输入账号" v-model="editRow.account" required="required" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码<span class="glyphicon glyphicon-asterisk required"></span></label>
							<div class="col-sm-9">
								<input type="password" class="form-control" placeholder="请输入密码" v-model="editRow.password" required="required" />
							</div>
						</div>
						</template>
<hr/>
						<!-- 新建和编辑的公共部分 -->
						<div class="form-group">
							<label class="col-sm-2 control-label">真实姓名<span class="glyphicon glyphicon-asterisk required"></span>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="请输入真实姓名" v-model="editRow.name" required="required" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">电话号码</span>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="请输入接受报警信息的电话号码" v-model="editRow.phone" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱</span>
							</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" placeholder="请输入接受报警信息的邮箱" v-model="editRow.email" />
								<p class="help-block">邮箱和电话用于在服务器故障时，接收报警信息</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="2" placeholder="请输入备注" v-model="editRow.memo"></textarea>
							</div>
						</div>
						<!-- /新建和编辑的公共部分 -->

						<template v-if="editMode"> <!-- 编辑模式 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-9">
								<button type="submit" class="btn btn-success w100">保存</button>
								<button type="button" class="btn btn-danger w100" @click="deleteUser(editRow.id)">删除</button>
							</div>
						</div>
						<hr />
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" @click="newRow" class="btn btn-warning w100">新建账号</button>
							</div>
						</div>
						</template>

						<template v-if="!editMode"> <!-- 新增模式 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success w100">新增</button>
							</div>
						</div>
						</template>

					</form>
				</div>
			</div>
		</div>
	</div>

	<#include "include/js.ftl"/>

	<script src="/js/page.users.js"></script>

</body>

</html>