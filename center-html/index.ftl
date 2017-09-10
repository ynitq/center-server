<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>${dictRaw["system.name"]}</title>
  <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="app"></div>
<!-- htmlmin:ignore -->
<script type="text/javascript">
  window.isDevMode = false
<#if logined>
  window.curUser = {
      account: '${user.username}',
      name: '${user.name}',
      logined: true,
    }
<#else>
  window.curUser = {
      account: '',
      name: '',
      logined: false,
    }
</#if>
</script>
<!-- htmlmin:ignore -->
<script type='text/javascript' src='/dict/dictJs.js' charset='utf-8'></script>
</body>
</html>
