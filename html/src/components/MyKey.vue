<template>
  <span v-html="thisValue" :title="vkey" :class="{'todo':!hasKey}"></span>
</template>
<script type="text/javascript">

  import lzUtil from '../util/lzUtil.js'

  export default {
    props: {
      /** key */
      vkey: {
        type: String,
        required: true,
      },
    },

    data () {
      return {
        thisValue: '', // 根据key找到的值
        hasKey: false, // key是否存在
      }
    },

    mounted: function () {

      let key = this.vkey

      let value = `需要Key:  ${key}`
      try {
        let v = window.lzDict.data[key]
        if (lzUtil.isEmpty(v)) {
          console.warn('缺少key', key)
        } else {
          value = v
          this.hasKey = true
        }
      } catch (e) {
        console.error('获取Key时发生异常:', key, e)
      }
      this.thisValue = value
    },
  }
</script>

<style scoped>

</style>
