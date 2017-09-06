/**
 * 全局上下文，用于各个控件之间共享的变量
 * Created by liangwj on 2017/5/11 0011.
 */

export default {

  /** 参数是 AdminUserInfoResponse */
  updateUserInfo (res) {
    window.curUser.account = res.account
    window.curUser.name = res.name
    window.curUser.logined = true

    console.debug('更新用户信息', window.curUser)
  },
}
