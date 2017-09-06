<template>
  <div>

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

          <a class="navbar-brand"><label class="system-name">中心服务器</label></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <!-- 功能菜单区域   开始  -->
          <ul class="nav navbar-nav ">
            <router-link activeClass="active" tag="li" v-for="row in menus" :to="row.path" :key="row.path"><a href="javascript:">{{row.name}}</a></router-link>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">您好：{{username}} <span
              class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="javascript:" data-toggle="modal" data-target="#change_pwd_mod"> 修改密码</a></li>
                <li><a href="javascript:" @click="logout">退出登陆</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="/dict/manager" target="_blank">页面字典</a></li>
                <li><a href="/jmxInWeb/" target="_blank">JMX</a></li>

              </ul>
            </li>
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
              <form class="form-horizontal" role="form" v-on:submit.prevent="doChangePassword">
                <div class="form-group">
                  <label class="col-sm-3 control-label">旧密码<span class="glyphicon glyphicon-asterisk required"></span></label>
                  <div class="col-sm-9">
                    <input class="form-control" type="password" required="required" v-model="forgotPwdForm.oldPassword" placeholder="请输入旧密码"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">新密码<span class="glyphicon glyphicon-asterisk required"></span></label>
                  <div class="col-sm-9">
                    <input class="form-control" type="password" required="required" v-model="forgotPwdForm.newPassword" placeholder="请输入新密码"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">重复密码<span class="glyphicon glyphicon-asterisk required"></span></label>
                  <div class="col-sm-9">
                    <input class="form-control" type="password" required="required" v-model="forgotPwdForm.newPassword2" placeholder="请再次输入新密码"/>
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

  </div>
</template>

<script>

  import apiContext from '../ApiContext'
  import lzUtil from '../util/lzUtil'
  import apiUrl from '../ApiUrl'
  import $ from 'jquery'

  require('bootstrap/js/dropdown.js')
  require('bootstrap/js/modal.js')

  export default {
    name: 'top-nav', // 只有是组件的时候才有用

    /** 本页面用到的组件 */
    components: {},

    /** 本页面的属性 */
    data () {
      return {
        menus: [
          {name: '项目查看', path: 'Projects'},
          {name: '用户管理', path: 'Users'},
        ],

        username: '', // 用户名

        forgotPwdForm: {
          oldPassword: '',
          newPassword: '',
          newPassword2: '',
        },

        rightMenuOpen: false,
      }
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      this.username = apiContext.getCurUser()
      console.debug('当前用户', this.username)

    },

    /** 每次进入页面时 */
    activated () {
    },

    /** 每次退出页面时 */
    deactivated () {
      console.debug('activated()')
    },

    /** 本页面可用的方法 */
    methods: {

      logout () {
        lzUtil.ajax(apiUrl.public.logout, null, function (res) {
        })

        window.curUser.logined = false
        this.$router.push('Login')
      },

      doChangePassword: function () {
        // 重置密码
        let that = this

        let pwdStr = that.forgotPwdForm.newPassword
        let pwdStr2 = that.forgotPwdForm.newPassword2
        if (pwdStr !== pwdStr2) {
          lzUtil.showErrorMsg('两次输入密码不一致！')
          return
        }

        console.log('修改密码')
        lzUtil.ajax(apiUrl.public.changePassword, this.forgotPwdForm, function (res) {
          that.afterChangePwd()
        })
      },

      afterChangePwd () {
        $('#change_pwd_mod').modal('hide')

        lzUtil.showMsg('密码修改成')

        // 自动退出
        this.logout()
      },
    },
  }
</script>

<!--<style scoped>-->
<style>

</style>
