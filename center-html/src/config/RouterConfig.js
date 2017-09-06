/**
 * 路由配置
 * Created by liangwj on 2017/6/13 0013.
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import apiContext from '../ApiContext'

import layers from '../layer/LayerIndex.js'

const PATH_PREFIX = '/admin/'
const INDEX_PATH = getMyPath('')

function getMyPath (path) {
  return PATH_PREFIX + path
}

function getRouteDefine () {

  // 路由配置
  const routers = []

  /** 欢迎页 */
  const index = newRoute('Login')
  index.alias = INDEX_PATH
  routers.push(index)

  routers.push(newRoute('Dashboard')) // 仪表盘

  return routers
}

function newRoute (name) {
  /* eslint-disable */
  return {
    component: resolve => require([`../views/${name}.vue`], resolve),
    path: getMyPath(name),
  }
}

/** 路由配置 */
export default {

  /** 获取全路径 */
  getRoutePath (path) {
    return getMyPath(path)
  },

  /** 初始化路由 */
  initRouter () {

    Vue.use(VueRouter)

    const router = new VueRouter({
      mode: 'history',
      routes: getRouteDefine(),
    })

    router.afterEach(function (to) {
      console.log(`成功浏览到: ${to.path}`)

      // 进入一个页面时，关闭所有层
      layers.closeAll()
    })

    router.beforeEach(function (to, from, next) {
      if (to.path !== INDEX_PATH && !apiContext.curUser.logined) {
        console.debug('要访问的界面不是登录界面，但是没找到当前用户的信息，导航回到登录界面')
        next(INDEX_PATH)
      } else {
        if (to.matched.length === 0) {
          console.debug(`From:`, from)
          if (from.matched.length === 0) {
            const msg = `准备导航到: ${to.path}，但此页面不存在，并且from不存在，自动重定向到首页`
            console.debug(msg)
            next(INDEX_PATH)
          } else {
            const msg = `页面 ${to.path} 不存在`
            console.debug(msg)
            // lzUtil.showErrorMsg(msg)
          }
        } else {
          next()
        }
      }
    })

    return router
  }

}
