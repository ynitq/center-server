<template>
  <div>
    <header class="bar bar-nav">
      <h1 class='title'>标题</h1>
    </header>

    <div class="content">
      内容
    </div>
  </div>
</template>

<script>

  export default {
    name: '主键名字', // 只有是组件的时候才有用

    /** 本页面用到的组件 */
    components: {},

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
