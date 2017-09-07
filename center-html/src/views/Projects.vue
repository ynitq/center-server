<template>
  <div>
    <top-nav/>

    <div class="main-content">
      <!-- 左边的树 -->
      <div class="panel panel-default my-left-div">
        <div class="panel-heading">所有项目
          <button class="btn btn-default btn-sm pull-right" @click="loadProjects">刷新 ({{refresRemainSec}})</button>
        </div>
        <!-- List group -->
        <div class="list-group">

          <a class="list-group-item " v-for="row in projectList" @click="loadDetail(row.po.id)">
            <p :class="getTitleClass(row)" class="project-name">
              <span class="glyphicon" :class="getLogoClass(row)"></span>
              {{row.po.displayName}}
            </p>
            <h5 class="text-muted">{{row.po.startClassName}}<br/>{{row.po.host}}:{{row.po.port}}
            </h5>
          </a>

        </div>
      </div>
      <!-- /左边的树 -->

      <!-- 右边列表区 -->
      <div class="panel panel-default detail-div" v-show="hasData">
        <div class="panel-heading">项目: </div>
        <div class="panel-body">
          <!-- 三个tab标签 -->
          <ul class="nav nav-tabs">
            <li v-for="row in tabList" role="presentation"
                :class="{'active':tabIndex==row.index}"
                @click="switchTab(row.index)"><a class="pointer">{{row.name}}</a></li>
          </ul>
          <!-- /三个tab标签 -->

          <div class="tab-content">
            <project-info v-if="tabIndex===0"/>
            <project-chart v-if="tabIndex===1"/>
            <project-msg-list v-if="tabIndex===2"/>
          </div>
        </div>
      </div>
      <!-- /右边列表区 -->

    </div>
  </div>
</template>

<script>
  import TopNav from '../components/TopNav.vue'
  import ProjectInfo from '../components/projects/ProjectInfo.vue'
  import ProjectChart from '../components/projects/ProjectChart.vue'
  import ProjectMsgList from '../components/projects/ProjectMsgList.vue'

  import infoModel from '../components/projects/ProjectInfoModel'

  import lzUtil from '../util/lzUtil'
  import apiUrl from '../ApiUrl'

  export default {

    /** 本页面用到的组件 */
    components: {
      TopNav,
      ProjectInfo,
      ProjectChart,
      ProjectMsgList,
    },

    /** 本页面的属性 */
    data () {
      return {
        projectList: [], // 列表数据
        refresRemainSec: 30, // 刷新剩余时间
        hasData: true, // 是否有项目

        projectId: -1, // 当前点击的项目id

        /** tab的索引 0:信息，1：图标，2：消息 */
        tabIndex: 0,

        tabList: [
          {index: 0, name: '项目信息'},
          {index: 1, name: '历史图表'},
          {index: 2, name: '消息查看'},
        ],
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

      switchTab (index) {
        this.tabIndex = index
      },

      reloadTab () {
        if (this.tabIndex === 0) {
          infoModel.reload(this.projectId)
        }
      },

      /** 初始化时，加载该项目列表 */
      loadProjects () {
        console.log('加载项目列表')
        let that = this
        that.refresRemainSec = 30

        lzUtil.ajax(apiUrl.project.list, null, function (res) {
          that.afterLoadPorject(res)
        })
      },

      /** 加载详情 */
      loadDetail (id) {
        console.debug(`点击了项目 id=${id}`)
        this.projectId = id

        // 重新加载
        this.reloadTab()
      },

      getTitleClass (row) {
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
          this.hasData = false
        } else {
          this.hasData = true

          if (this.projectId === -1) {
            this.projectId = res.list[0].po.id
          }

          this.reloadTab()
        }
      },
    },
  }
</script>

<!--<style scoped>-->
<style>

</style>
