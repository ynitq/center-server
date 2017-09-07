/**
 * Created by liangwj on 2017/9/8 0008.
 */

import lzUtil from '../../util/lzUtil'
import apiUrl from '../../ApiUrl'

export default {

  /** 页面中的数据 */
  data: {
    projectId: 0,
    info: {
      po: {},
    }, // 数据

    userSubscribe: [], // 所有用户对该项目的订阅情况

    editForm: {
      // 用于和编辑项目的表单绑定
      displayName: '',
      memo: '',
      needCheck: false,
      sendWarn: false,
      id: 0,
    },
  },

  /** 加载项目信息 */
  reload (projectId) {
    this.data.projectId = projectId

    console.debug(`加载项目 ${projectId} 的信息`)

    let that = this
    let param = {
      id: projectId,
    }

    lzUtil.ajax(apiUrl.project.view, param, function (res) {
      that.reLoadCallback(res)
    })
  },

  /** 加载的回调 */
  reLoadCallback (res) {
    this.data.info = res.info
    this.data.userSubscribe = res.info.userSubscribe

    // 绑定表单
    let er = this.data.editForm
    er.displayName = res.info.po.displayName
    er.memo = res.info.po.memo
    er.needCheck = res.info.po.needCheck
    er.sendWarn = res.info.po.sendWarn
    er.id = res.info.po.id

  },
}
