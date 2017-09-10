/**
 * 所有api url的定义，这个定义的内容是直接从后端的开发界面中拷贝出来的，不需要自己手写，没事也不要自己手写
 *
 * Created by liangwj
 */
export default {

  /** 监控-信息 */
  msgLog: {
    delete: '/api/msgLog/delete', //  删除
    list: '/api/msgLog/list', //  列表
    save: '/api/msgLog/save', //  保存
    view: '/api/msgLog/view', //  查看
  },
  /** 监控-项目管理 */
  project: {
    chartOfAction: '/api/project/chartOfAction', //  请求数量
    chartOfMemory: '/api/project/chartOfMemory', //  内存使用线图
    chartOfSystemLoad: '/api/project/chartOfSystemLoad', //  系统负载
    delete: '/api/project/delete', //  删除
    list: '/api/project/list', //  列表
    save: '/api/project/save', //  保存
    userSubscribe: '/api/project/userSubscribe', //  用户订阅或者取消订阅项目
    view: '/api/project/view', //  查看
  },
  /** 监控-登录登出 */
  public: {
    changePassword: '/api/public/changePassword', //  修改密码
    login: '/api/public/login', //  登录
    logout: '/api/public/logout', //  登出
  },
  /** 监控-角色 */
  role: {
    delete: '/api/role/delete', //  删除
    list: '/api/role/list', //  列表
    save: '/api/role/save', //  保存
    view: '/api/role/view', //  查看
  },
  /** 监控-用户管理 */
  user: {
    delete: '/api/user/delete', //  删除
    list: '/api/user/list', //  列表
    lockOrUnlock: '/api/user/lockOrUnlock', //  封号或者解封
    resetPassword: '/api/user/resetPassword', //  重置密码
    save: '/api/user/save', //  保存
    view: '/api/user/view', //  查看
  },

}
