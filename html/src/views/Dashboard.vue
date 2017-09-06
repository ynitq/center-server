<!--
  Created by linQC on 2017/9/4 15:33.

需求简要说明：
1、新增用户：统计每天新登录的玩家数量；                                                 2、登录人次：统计每天登录用户的登录次数
3、登录人数：每天登录到游戏的玩家人数                                                4、PCU最高在线人数
5、ACU平均在线人数
6、次日留存 1号新登录的用户在2号再次登录的人数
7、三日留存 1号新登录的用户在3号再次登录的人数
8、七日留存 1号新登录的用户在7号再次登录的人数
9、15日留存 1号新登录的用户在15号再次登录的人数
10、30日留存 1号新登录的用户在30号再次登录的人数
11、APA活跃付费人数 当日有增加付费房卡的人数（排除赠送房卡）
12、CAC付费渗透率 APA/DAU
13、ARPU Average Revenue Per User 平均每用户收入，即可通过 总收入/DAU 计算得出
14、ARPPU Average Revenue Per Paying User 平均每付费用户收入，可通过 总收入/APA 计算得出。
15、游戏人数：统计每天有玩过游戏的人数（至少1局游戏）
收入：当天购卡收入
16、系统产出金币：玩家每日通过创号赠送、救济金、彩池等非充值渠道获得的金币
17、系统消耗金币：抽水+机器人赢的金币
18、一页最少显示32行，超出部分翻页
19、点击字段名称弹出曲线图，曲线图需要包含数据标签、趋势线
-->
<template>
  <div>
    <my-header/>
    <div class="container-fluid">
      <div class="row">
        <my-list :list="list"/>
        <div class="main">
          <div class="row placeholders">
            <ol class="breadcrumb">
              <li><a href="#">数据分析后台</a></li>
              <li><a href="#">数据分析</a></li>
              <li class="active">数据分析摘要</li>
            </ol>
            <form class="form-inline">
              <div class="form-group">
                <label for="exampleInputName2">选择渠道：</label>
                <input type="date" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
              </div>
              <div class="form-group">
                <label for="exampleInputName2">选择客户端：</label>
                <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
              </div>
              <div class="form-group">
                <label for="exampleInputName2">日期：</label>
                <input type="date" class="form-control" id="exampleInputName2" placeholder="Jane Doe">-
                <input type="date" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
              </div>
              <button type="submit" class="btn btn-default">查看表单</button>
            </form>
          </div>
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th>日期</th>
                <th>新增用户</th>
                <th>登录人次</th>
                <th>登陆人数DAU</th>
                <th>PCU</th>
                <th>ACU</th>
                <th>次日留存</th>
                <th>三日留存</th>
                <th>七日留存</th>
                <th>15日留存</th>
                <th>30日留存</th>
                <th>APA</th>
                <th>CAC</th>
                <th>ARPU</th>
                <th>ARPPU</th>
                <th>游戏人数</th>
                <th>系统产出金币</th>
                <th>系统消耗金币</th>
                <th>金币销售量</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>1,001</td>
                <td>Lorem</td>
                <td>ipsum</td>
                <td>dolor</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
                <td>sit</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import MyHeader from '../components/MyHeader.vue'
  import MyList from '../components/MyList.vue'
  export default {
    /** 本页面用到的组件 */
    components: {
      MyHeader,
      MyList,
    },
    /** 本页面的属性 */
    data () {
      return {
        list: [
          {
            name: '数据分析摘要',
            path: '/admin/dashboard',
          },
          {
            name: '充值统计',
            path: '/a',
          },
          {
            name: '消耗统计',
            path: '/a',
          },
          {
            name: '充值分布',
            path: '/a',
          },
          {
            name: '兑换统计',
            path: '/a',
          },
          {
            name: '兑换分布',
            path: '/a',
          },
          {
            name: '留存统计',
            path: '/a',
          },
          {
            name: '在线分布',
            path: '/a',
          },
          {
            name: '充值订单列表',
            path: '/a',
          },
        ],
        id: '145454',
        newTodoText: '34',
        a: {
          a: '12',
          b: '22',
          c: true,
        },
        b: 44,
        seen: true,
        object: [
          {
            id: 1,
            title: 'Do the dishes',
          },
          {
            id: 2,
            title: 'Take out the trash',
          },
          {
            id: 3,
            title: 'Mow the lawn',
          },
        ],
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
