.
<template>
  <div>
    <router-view id="help-router-view"></router-view>
    <!-- 加载提示 -->
    <pre-loading></pre-loading>

  </div>
</template>

<script>
  import PreLoading from './layer/PreLoading.vue'

  /** 导入样式表 */
  import 'toastr/toastr.less'
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
