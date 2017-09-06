<template>
  <div>
    <top-nav/>
  </div>
</template>

<script>
  import TopNav from '../components/TopNav.vue'

  export default {

    /** 本页面用到的组件 */
    components: {
      TopNav,
    },

    /** 本页面的属性 */
    data () {
      return {}
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      console.debug('mounted()')
    },

    /** 每次进入页面时 */
    activated () {
      console.debug('activated()')
    },

    /** 每次退出页面时 */
    deactivated () {
      console.debug('activated()')
    },

    /** 本页面可用的方法 */
    methods: {},
  }
</script>

<!--<style scoped>-->
<style>

</style>
