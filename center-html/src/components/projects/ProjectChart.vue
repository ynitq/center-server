<template>
  <div>
    图表
  </div>
</template>

<script>

  export default {
    name: 'project-chart', // 只有是组件的时候才有用

    /** 本页面用到的组件 */
    components: {},

    props: {
      projectId: {},
    },

    watch: {
      // 如果 projectId 发生改变，这个函数就会运行
      projectId: function (newValue) {
        console.debug('projectId变更为:', newValue)
      },
    },

    /** 本页面的属性 */
    data () {
      return {}
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      console.debug('mounted()', this.projectId)
    },

    /** 每次进入页面时 */
    activated () {
      console.debug('activated()', this.projectId)
    },

    /** 每次退出页面时 */
    deactivated () {
      console.debug('deactivated()')
    },

    /** 本页面可用的方法 */
    methods: {},
  }
</script>

<!--<style scoped>-->
<style>

</style>
