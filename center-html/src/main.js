// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import lzUtil from './util/lzUtil'
import routerConfig from './config/RouterConfig'
import vueConfig from './config/AppVueConfig.js'

/** 初始化vue的配置 */
vueConfig.init()

/** 初始化toast */
lzUtil.initToastr()

const router = routerConfig.initRouter()

let app = new Vue({
  el: '#app',
  router: router,
  template: '<App/>',
  components: {App},
})

console.debug(`vue初始化完成。路由页面数量:${app.$router.options.routes.length}`)
