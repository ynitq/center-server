<template>
  <div>

    <top-nav/>

    <div class="main-content">
      <!-- 左边的树 -->
      <div class="panel panel-default my-left-div">
        <div class="panel-heading">角色</div>
        <!-- List group -->
        <div class="list-group">
          <a class="list-group-item" href="javascript:" v-for="row in list" @click="view(row.po.id)"> <span
            class="text-success">{{row.po.name}}</span>
          </a>

        </div>
      </div>
      <!-- /左边的树 -->

      <!-- 右边列表区 -->
      <div class="panel panel-default detail-div">
        <div class="panel-heading">角色信息</div>
        <div class="panel-body"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import TopNav from '../components/TopNav.vue'
  import lzUtil from '../util/lzUtil'
  import apiUrl from '../ApiUrl'

  export default {
    /** 本页面用到的组件 */
    components: {
      TopNav,
    },

    /** 本页面的属性 */
    data () {
      return {
        list: [], // 角色列表

      }
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      this.loadList()
    },

    /** 本页面可用的方法 */
    methods: {

      /** 初始化时，加载列表 */
      loadList () {
        console.debug('加载角色列表')
        let that = this
        lzUtil.ajax(apiUrl.role.list, '', function (res) {
          that.list = res.list
        })
      },

      view (id) {
        console.debug(`查看角色 id=${id}`)
      },

    },
  }
</script>

<!--<style scoped>-->
<style>

</style>
