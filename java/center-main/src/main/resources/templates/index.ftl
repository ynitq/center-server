<!DOCTYPE html><html><head><meta charset=utf-8><meta http-equiv=X-UA-Compatible content="IE=edge"><title>后台-系统管理</title><link rel=icon href=/static/favicon.ico type=image/x-icon><meta name=viewport content="initial-scale=1,maximum-scale=1"><meta name=apple-mobile-web-app-capable content=yes><meta name=apple-mobile-web-app-status-bar-style content=black><link href=https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css rel=stylesheet><link href=/static/css/app.a5b8ef9cee58e1a2a4189d8690e50935.css rel=stylesheet></head><body><div id=app></div><script type=text/javascript>window.isDevMode = false
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
</#if></script><script src=/dict/dictJs.js charset=utf-8></script><script type=text/javascript src=/static/js/manifest.ab379b229a067e6fb77b.js></script><script type=text/javascript src=/static/js/vendor.73b9b44d80bc696af35a.js></script><script type=text/javascript src=/static/js/app.8af5208ca33b09dbc5ab.js></script></body></html>