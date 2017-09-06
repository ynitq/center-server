<template>
  <div>

  </div>
</template>

<script>
  export default {
    /** 本页面用到的组件 */
    components: {
    },
    /** 本页面的属性 */
    data () {
      return {
      }
    },
    /** 计算属性 */
    computed: {},

    created () {
    },

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
    methods: {
      doSomething (e) {
        console.log(e.target)
      },
      addNewTodo () {
        if (this.newTodoText !== '') {
          this.object.push({
            id: this.nextTodoId++,
            title: this.newTodoText,
          })
          this.newTodoText = ''
        }
      },
    },
  }
</script>

<!--<style scoped>-->
<style lang="less">
  @media (min-width: 768px) {
    .sidebar {
      position: fixed;
      top: 51px;
      bottom: 0;
      left: 0;
      z-index: 1000;
      display: block;
      padding: 20px;
      overflow-x: hidden;
      overflow-y: auto;
      background-color: #f5f5f5;
      border-right: 1px solid #eee;
    }
  }

  .breadcrumb {
    padding: 10px 15px;
  }

  .main {
    margin-top: 52px;
    margin-left: 260px;
  }

  @media (max-width: 768px) {
    .main {
      margin-left: 20px;
    }
  }
</style>
