/**
 * 全局上下文，用于各个控件之间共享的变量
 * Created by liangwj on 2017/5/11 0011.
 */

export default {
  /** 当前用户信息 */
  curUser: {
    logined: true, // 是否已经登录
    info: { // 和AdminUserInfoBean 同构
      account: '',
      name: '',
    },
  },

  /** 参数是 AdminUserInfoResponse */
  updateUserInfo (res) {
    this.curUser.logined = res.logined
    this.curUser.info = res.info
    console.debug('更新用户信息', this.curUser)
  },
}
