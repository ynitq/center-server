<template>
  <div>
    <top-nav/>

    <div class="main-content">
      <!-- 左边的树 -->
      <div class="panel panel-default leftTreeDiv" id="projectsDiv">
        <div class="panel-heading">所有项目
          <button class="btn btn-default btn-sm pull-right" @click="loadProjects">刷新 ({{refresRemainSec}})</button>
        </div>
        <!-- List group -->
        <div class="list-group">

          <a class="list-group-item" v-for="row in projectList" href="#" @click="load(row.po.id)">
            <p :class="getTitleClass(row)" class="projectName">
              <span class="glyphicon" :class="getLogoClass(row)"></span>
              {{row.po.displayName}}
            </p>
            <h5 class="text-muted">{{row.po.startClassName}}<br/>{{row.po.host}}:{{row.po.port}}
            </h5>
          </a>

        </div>
      </div>
      <!-- /左边的树 -->

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
        projectList: [], // 列表数据
        refresRemainSec: 30, // 刷新剩余时间
      }
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      this.loadProjects()
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
      /** 初始化时，加载该项目列表 */
      loadProjects: function () {
        console.log('加载项目列表')
        let that = this
        that.refresRemainSec = 30

        lzUtil.ajax(apiUrl.project.list, null, function (res) {
          that.afterLoadPorject(res)
        })
      },

      getTitleClass: function (row) {
        let po = row.po
        if (!po.needCheck) {
          return 'text-muted'
        } else {
          if (po.down) {
            return 'text-danger'
          } else {
            return 'text-success'
          }
        }
      },

      /** 标题的logo class */
      getLogoClass: function (row) {
        let po = row.po
        if (!po.needCheck) {
          return 'glyphicon-ban-circle'
        } else {
          if (po.down) {
            return 'glyphicon-remove'
          } else {
            return 'glyphicon-ok'
          }
        }
      },

      afterLoadPorject (res) {
        console.debug('更新 项目列表', res.list)
        this.projectList = res.list

        // 如果没有数据
        if (res.list.length === 0) {
//          that.detailVm.hasData = false;
        } else {
//          if (that.curProjectId == -1) {
//            var id = res.list[0].po.id;
//            that.loadDetail(id);
//          }
        }
      },
    },
  }
</script>

<!--<style scoped>-->
<style>

</style>
