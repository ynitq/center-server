<!doctype html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目查看-监控系统</title> <#include "include/style.ftl"/>
</head>
<body>

	<#include "include/header.ftl"/>

	<div class="main-content">
		<!-- 左边的树 -->
		<div class="panel panel-default leftTreeDiv" id="projectsDiv">
			<div class="panel-heading">所有项目 <button class="btn btn-default btn-sm pull-right" @click="refresh">刷新 ({{refresRemainSec}})</button> </div>
			<!-- List group -->
			<div class="list-group">

				<a class="list-group-item" v-for="row in list" href="#" @click="load(row.po.id)">
					<p :class="getTitleClass(row)" class="projectName">
						<span class="glyphicon" :class="getLogoClass(row)"></span>
						{{row.po.displayName}}
					</p>
					<h5 class="text-muted">{{row.po.startClassName}}<br />{{row.po.host}}:{{row.po.port}}
					</h5>
				</a>

			</div>
		</div>
		<!-- /左边的树 -->

		<!-- 右边列表区 -->
		<div class="panel panel-default detailDiv" id="detailDiv" v-show="hasData">
			<div class="panel-heading">项目: {{info.po.displayName}}</div>
			<div class="panel-body">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist" id="detail_nav_tab">
					<li role="presentation" class="active"><a href="#detail_base" role="tab" data-toggle="tab">项目信息</a></li>
					<li role="presentation"><a href="#detail_history" role="tab" data-toggle="tab">历史图表</a></li>
					<li role="presentation"><a href="#detail_msg" role="tab" data-toggle="tab">消息查看</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="detail_base"><#include "projects_base.ftl"/></div>
					<div role="tabpanel" class="tab-pane" id="detail_history"><#include "projects_history.ftl"/></div>
					<div role="tabpanel" class="tab-pane" id="detail_msg"><#include "projects_msg.ftl"/></div>
				</div>
			</div>
		</div>
	</div>

	<#include "include/js.ftl"/>

	<script src="https://cdn.bootcss.com/flot/0.8.3/jquery.flot.min.js"></script>
	<script src="https://cdn.bootcss.com/flot/0.8.3/jquery.flot.time.min.js"></script>
	<script src="/js/page.projects.js"></script>
	
</body>

</html>