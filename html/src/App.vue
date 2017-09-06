.
<template>
  <div>
    <keep-alive>
      <router-view id="help-router-view"></router-view>
    </keep-alive>
    <!-- 加载提示 -->
    <pre-loading></pre-loading>

  </div>
</template>

<script>
  import PreLoading from './layer/PreLoading.vue'

  /** 导入样式表 */
  import 'izitoast/dist/css/iziToast.css'
  import './assets/iconfont/iconfont.css'
  import './assets/less/app.less'

  export default {
    /** 本页面用到的组件 */
    components: {
      PreLoading, // 加载中
    },

    /** 本页面的属性 */
    data () {
      return {
        isDevMode: window.isDevMode,
      }
    },
  }
</script>
