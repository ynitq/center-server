<!-- 加载提示 -->
<template>
  <div>
    <div id="loading-mask" class="weui-mask_transparent" style="display: none"></div>
    <div id="loading-content" class="weui-toast" style="display: none">
      <i class="weui-loading weui-icon_toast"></i>
      <p class="weui-toast__content">数据加载中</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'pre-loading',
  }
</script>
