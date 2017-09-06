/**
 * 所有api url的定义，这个定义的内容是直接从后端的开发界面中拷贝出来的，不需要自己手写，没事也不要自己手写
 *
 * Created by liangwj
 */
export default {

  /** 系统管理员登录 */
  adminLogin: {
    getCurUserInfo: '/api/adminLogin/getCurUserInfo', //  获取当前登陆的用户的信息
    login: '/api/adminLogin/login', //  系统管理员登录
    logout: '/api/adminLogin/logout', //  系统管理员登出
  },

}
