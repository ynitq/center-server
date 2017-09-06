
<!-- 导航部分 -->
<nav class="navbar navbar-default  navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

			<a class="navbar-brand"><label class="system-name">监控系统</label></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<!-- 功能菜单区域   开始  -->
			<ul class="nav navbar-nav ">
				<#list menuVo.menus as menu>
				<li${menu.classStr}><a href="${menu.url}"${menu.targetStr}>${menu.name}</a></li> </#list>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">您好：${user.username} <span
							class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="javascript:;" data-toggle="modal" data-target="#change_pwd_mod"> 修改密码</a></li>
						<li><a href="/logout">退出登陆</a></li>
					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
<!-- /. 导航部分End -->

<!-- 修改密码BEGIN -->
<div class="modal fade" id="change_pwd_mod" tabindex="-1" style="display: none" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
				<h4 class="modal-title" id="myModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form class="form-horizontal" role="form" id="change_passowrd_form">
						<div class="form-group">
							<label class="col-sm-3 control-label">旧密码<span class="glyphicon glyphicon-asterisk required"></span></label>
							<div class="col-sm-9">
								<input class="form-control" type="password" required="required" v-model="oldPassword" placeholder="请输入旧密码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">新密码<span class="glyphicon glyphicon-asterisk required"></span></label>
							<div class="col-sm-9">
								<input class="form-control" type="password" required="required" v-model="newPassword" placeholder="请输入新密码" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">重复密码<span class="glyphicon glyphicon-asterisk required"></span></label>
							<div class="col-sm-9">
								<input class="form-control" type="password" required="required" v-model="newPassword2" placeholder="请再次输入新密码" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-9">
								<button type="submit" class="btn btn-primary w100">保存</button>
								<button type="button" class="btn btn-default w100" data-dismiss="modal">关闭</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 修改密码END -->

