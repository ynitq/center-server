<template>
  <div class="container-fluid">
    <div class="row">
      <h3>服务器信息</h3>
    </div>
    <div class="row">
      <div class="col-md-6">
        <p>
          <label>服务器信息:</label>{{info.po.startClassName}}@{{info.po.host}}:{{info.po.port}}{{info.po.contextPath}}
        </p
        <p>
          <label>最后检查时间:</label> {{info.lastCheckTime}}
        </p>
        <p>
          <label>发现时间:</label> {{info.createTime}}
        </p>
        <p>
          <label>系统启动时间:</label> {{info.startTime}}
        </p>
        <p>
          <label>程序Build时间:</label> {{info.startClassBuildTime}}
        </p>
        <p v-show="info.po.needCheck">
          <label>状态:</label>
          <span v-if="info.po.down" class="text-danger">
					<span class="glyphicon glyphicon-remove"></span>
					宕机
				</span>
          <span v-else class="text-success">
					<span class="glyphicon glyphicon-ok"></span>
					在线
				</span>
        </p>
      </div>

      <div class="col-md-6">
        <div class="row">
          <div class="col-md-4">
            <label>已使用:</label> {{info.usedMemoryM}} M
          </div>
          <div class="col-md-4">
            <label>已分配内存:</label> {{info.totalMemoryM}} M
          </div>
          <div class="col-md-4">
            <label>最大内存:</label> {{info.maxMemoryM}} M
          </div>
          <div class="col-md-12">
            <div class="progress">
              <div class="progress-bar progress-bar-success" :style="{width: info.usedMemoryPrecent+'%'}">
                {{info.usedMemoryPrecent}}%
              </div>
              <div class="progress-bar progress-bar-warning" :style="{width: (info.totalMemoryPrecent-info.usedMemoryPrecent)+'%'}">
                {{info.totalMemoryPrecent}}%
              </div>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-4">
            <label>可使用空间:</label> {{info.usableSpaceM}} M
          </div>
          <div class="col-md-4">
            <label>总空间:</label> {{info.totalSpaceM}} M
          </div>
          <div class="col-md-12">
            <div class="progress">
              <div class="progress-bar progress-bar-info" :style="{width: info.usableSpacePrecent+'%'}">
                {{info.usableSpacePrecent}}%
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

    <hr/>
    <div class="row">
      <div class="col-md-8">
        <form role="form" class="form-horizontal" v-on:submit.prevent="saveInfo">
          <input type="hidden" name="id" v-model="editForm.id"/>
          <div class="form-group">
            <!---->
            <label class="col-sm-2 control-label">显示名称<span class="glyphicon glyphicon-asterisk required"></span></label>
            <div class="col-sm-9">
              <input v-model="editForm.displayName" type="text" name="displayName" placeholder="请输入显示名称" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">备注</label>
            <div class="col-sm-9">
              <textarea v-model="editForm.memo" rows="3" name="memo" placeholder="请输备注" required="required" class="form-control"></textarea>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-9">
              <div class="checkbox">
                <label><input v-model="editForm.needCheck" type="checkbox" name="needCheck" value="true">是否需要检查状态 </label>
                <p class="help-block">内网开发服务器、无需定期检查状态</p>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-9">
              <div class="checkbox">
                <label><input v-model="editForm.sendWarn" type="checkbox" name="sendWarn" value="true">宕机时是否需要报警给相关人员 </label>
                <p class="help-block">测试服务器等，无需报警</p>
              </div>
            </div>
          </div>
          <!---->
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-success w100">保存</button>
            </div>
          </div>
        </form>
      </div>

      <div class="col-md-4" style="height: 350px;">
        <div class="panel panel-default">
          <div class="panel-heading">关注的用户</div>
          <div class="list-group">
            <a v-for="row in userSubscribe" @click="subscribe(row.po.id, row.subscribed)" class="list-group-item"> {{row.po.name}}
              <span v-if="row.subscribed" class="label label-success pull-right">已关注</span>
              <span v-else class="label label-default pull-right">未关注</span>
            </a>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

  import infoModel from './ProjectInfoModel'
  import lzUtil from '../../util/lzUtil'
  import apiUrl from '../../ApiUrl'

  export default {
    name: 'project-info', // 只有是组件的时候才有用

    /** 本页面用到的组件 */
    components: {},

    /** 本页面的属性 */
    data () {
      return infoModel.data
    },

    /** 计算属性 */
    computed: {},

    /** 本页面可用的方法 */
    methods: {
      /** 订阅或者取消订阅 */
      subscribe: function (userId, subscribed) {
        infoModel.subscribe(userId, subscribed)
      },

      /** 保存表单 */
      saveInfo () {
        let that = this
        lzUtil.ajax(apiUrl.project.save, this.editForm, function (res) {
          that.saveInfoCallBack(res)
        })
      },

      /** 保存表单 cb */
      saveInfoCallBack (res) {
        lzUtil.showMsg('保存成功')

        this.$emit('changed')
      },
    },
  }
</script>
