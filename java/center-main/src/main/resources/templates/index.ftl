<!DOCTYPE html><html><head><meta charset=utf-8><meta http-equiv=X-UA-Compatible content="IE=edge"><title>后台-系统管理</title><link rel=icon href=/static/favicon.ico type=image/x-icon><meta name=viewport content="initial-scale=1,maximum-scale=1"><meta name=apple-mobile-web-app-capable content=yes><meta name=apple-mobile-web-app-status-bar-style content=black><link href=https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css rel=stylesheet><link href=/static/css/app.bde4e684171cf098dffb3a7ebced6cbc.css rel=stylesheet></head><body><div id=app></div><script type=text/javascript>window.isDevMode = false
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
</#if></script><script src=/dict/dictJs.js charset=utf-8></script><script type=text/javascript src=/static/js/manifest.433690b4c198d5ae3347.js></script><script type=text/javascript src=/static/js/vendor.73b9b44d80bc696af35a.js></script><script type=text/javascript src=/static/js/app.30050b9c4353489b7bb9.js></script></body></html>