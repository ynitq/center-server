/**
 * Vue 的各种配置
 * Created by liangwj on 2017/6/20 0020.
 */

import Vue from 'vue'

import VueScroller from 'vue-scroller'

import MyKey from '../components/MyKey.vue'

/** 配置组件 */
function initComp () {
  /** 我们可以再这个地方注入全局的组件，这样就不需要在每个页面单独的声明了 */

  // 字典组件
  Vue.component('my-key', MyKey)

}

export default {
  /** 初始化所有 */
  init () {
    Vue.config.productionTip = false

    // 下拉刷新和无限加载
    Vue.use(VueScroller)

    initComp()
  },

}
