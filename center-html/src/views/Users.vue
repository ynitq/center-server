<template>
  <div>
    <top-nav/>

    <div class="main-content" id="userPage">
      <!-- 左边的树 -->
      <div class="panel panel-default my-left-div">
        <div class="panel-heading">用户</div>
        <!-- List group -->
        <div class="list-group">
          <a class="list-group-item" href="javascript:" v-for="(row,index) in list" @click="view(index)"> <span
            class="text-success">{{row.po.name}}</span> ({{row.po.account}})
            <h5 class="text-muted">电话:{{row.po.phone}}</h5>
          </a>
        </div>
      </div>
      <!-- /左边的树 -->

      <!-- 右边列表区 -->
      <div class="panel panel-default detail-div" id="detailDiv">
        <div class="panel-heading">用户信息</div>
        <div class="panel-body">
          <div class="container-fluid">
            <form class="form-horizontal" role="form"
                  v-on:submit.prevent="save">

              <template v-if="editMode"> <!-- 编辑模式 -->
                <input type="hidden" name="id" :value="editForm.id"/>
                <h3>修改/查看用户</h3>
                <div class="form-group">
                  <label class="col-sm-2 control-label">账号</label>
                  <div class="col-sm-8">
                    <p class="form-control-static">{{editForm.account}}</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">创建时间</label>
                  <div class="col-sm-8">
                    <p class="form-control-static">{{createTime}}</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">密码</label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" placeholder="可输入新密码" v-model="editForm.password"/>
                    <p class="help-block">不为空时，表示重置该账号的密码</p>
                  </div>
                </div>
              </template>
              <template v-else> <!-- 新建模式 -->
                <h3>创新新用户</h3>
                <div class="form-group">
                  <label class="col-sm-2 control-label">账号<span class="glyphicon glyphicon-asterisk required"></span></label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" placeholder="请输入账号" v-model="editForm.account" required="required"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">密码<span class="glyphicon glyphicon-asterisk required"></span></label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" placeholder="请输入密码" v-model="editForm.password" required="required"/>
                  </div>
                </div>
              </template>
              <hr/>
              <!-- 新建和编辑的公共部分 -->
              <div class="form-group">
                <label class="col-sm-2 control-label">真实姓名<span class="glyphicon glyphicon-asterisk required"></span>
                </label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="请输入真实姓名" v-model="editForm.name" required="required"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">电话号码</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="请输入接受报警信息的电话号码" v-model="editForm.phone"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" placeholder="请输入接受报警信息的邮箱" v-model="editForm.email"/>
                  <p class="help-block">邮箱和电话用于在服务器故障时，接收报警信息</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                  <textarea class="form-control" rows="2" placeholder="请输入备注" v-model="editForm.memo"></textarea>
                </div>
              </div>
              <!-- /新建和编辑的公共部分 -->

              <template v-if="editMode"> <!-- 编辑模式 -->
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-9">
                    <button type="submit" class="btn btn-success w100">保存</button>
                    <button type="button" class="btn btn-danger w100" @click="confirmDelete(editForm.id)">删除</button>
                  </div>
                </div>
                <hr/>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" @click="switchToNewMode" class="btn btn-warning w100">新建账号</button>
                  </div>
                </div>
              </template>

              <template v-if="!editMode"> <!-- 新增模式 -->
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success w100">新增</button>
                  </div>
                </div>
              </template>

            </form>
          </div>
        </div>
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
        list: [], // 用户列表

        editMode: false, // 是编辑还是新建

        editForm: {// 编辑表单
          name: '',
          password: '',
          account: '',
          memo: '',
          email: '',
          phone: '',
          id: 0,
        },

        createTime: '', // 当前查看中的用户的创建时间
      }
    },

    /** 计算属性 */
    computed: {},

    /** 构建页面时 */
    mounted () {
      this.loadList()
    },

    /** 每次进入页面时 */
    activated () {
    },

    /** 本页面可用的方法 */
    methods: {
      /** 查看 */
      view: function (index) {
        let cur = this.list[index]
        let po = cur.po
        console.log(`查看  index=${index}`, cur)

        let form = this.editForm

        form.name = po.name
        form.password = ''
        form.account = po.account
        form.memo = po.memo
        form.email = po.email
        form.phone = po.phone
        form.id = po.id

        this.createTime = cur.createTime

        this.editMode = true
      },

      confirmDelete: function (id) {
        let that = this
        lzUtil.confirm('请确认', '确认要删除吗？', function () {
          that.doDelete(id)
        })
      },

      save () {
        let that = this
        console.log('保存用户信息', this.editForm)

        lzUtil.ajax(apiUrl.user.save, this.editForm, function (res) {
          lzUtil.showMsg('保存成功')

          // 成功后，列表项要刷新
          that.loadList()

          // 同时切换到新建模式
          that.switchToNewMode()
        })
      },

      /** 删除 */
      doDelete: function (id) {
        console.debug(`删除用户 id=${id}`)
        let that = this
        let param = {
          id: id,
        }
        lzUtil.ajax(apiUrl.user.delete, param, function (res) {
          lzUtil.showMsg('删除成功')

          // 成功后，列表项要刷新
          that.loadList()

          // 同时切换到新建模式
          that.switchToNewMode()
        })
      },

      /** 切换到新建模式 */
      switchToNewMode: function () {
        console.log('切换到新建模式')

        let row = this.editForm
        row.name = ''
        row.password = ''
        row.account = ''
        row.memo = ''
        row.email = ''
        row.phone = ''
        row.id = 0

        this.editMode = false
      },

      /** 初始化时，加载该项目列表 */
      loadList () {
        console.log('加载用户列表')
        let that = this
        lzUtil.ajax(apiUrl.user.list, '', function (res) {
          that.list = res.list
        })
      },
    },
  }
</script>

