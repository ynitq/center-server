<template>
  <div>
    <nav class="navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                  data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand"><label class="system-name">后台-系统管理</label></a>
        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container-fluid -->
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-md-4 col-md-offset-4">
          <!-- 登录 pannel -->
          <div class="panel panel-default">
            <div class="panel-heading">用户登录</div>
            <div class="panel-body">
              <form v-on:submit.prevent="onSubmit">
                <div class="form-group">
                  <label>账号</label>
                  <input type="text" required="required" class="form-control" placeholder="请输入用户名"
                         v-model.trim="form.account">
                </div>
                <div class="form-group">
                  <label>密码</label>
                  <input type="password" required="required" class="form-control" placeholder="请输入密码"
                         v-model.trim="form.password">
                </div>
                <div class="form-group">
                  <label>保持登录 <input type="checkbox" name="rememberMe" v-model="form.rememberMe"></label>
                </div>
                <input type="submit" name="submit" class="btn btn-info" value="登录"/>
              </form>
            </div>
          </div>
          <!-- /登录 pannel -->
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import lzUtil from '../util/lzUtil'
  import apiUrl from '../ApiUrl'
  import apiContext from '../ApiContext'

  export default {

    /** 本页面的属性 */
    data () {
      return {
        form: {
          account: 'admin',
          password: 'linzi777',
          rememberMe: true,
        },
      }
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      console.debug('mounted()')

    },

    /** 每次进入页面时 */
    activated () {
      console.debug('activated()')
      if (window.curUser.logined) {
        this.$router.push('Projects')
      }
    },

    /** 每次退出页面时 */
    deactivated () {
      console.debug('activated()')
    },

    /** 本页面可用的方法 */
    methods: {
      onSubmit () {
        this.$router.push('Dashboard')
        let that = this
        lzUtil.ajax(apiUrl.public.login, this.form, function (res) {
          that.afterLogin(res)
        })
      },

      afterLogin (res) {
        console.debug('系统管理员登录成功', res)
        apiContext.updateUserInfo(res)
        this.$router.push('Projects')
      },
    },
  }
</script>
