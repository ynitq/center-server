// see http://vuejs-templates.github.io/webpack for documentation.
const path = require('path')

let apiServer = 'http://127.0.0.1:30000/'
if (process.env.API_SERVER) {
  // 通过环境变量，可以切换后端的地址，例如 API_SERVER=127.0.0.1:20000
  apiServer = 'http://' + process.env.API_SERVER // + '/'
}
console.log('后端java服务器地址:', apiServer)

module.exports = {
  build: {
    env: require('./prod.env'),
    index: path.resolve(__dirname, '../../java/center-main/src/main/resources/templates/index.ftl'),
    assetsRoot: path.resolve(__dirname, '../../java/center-main/src/main/resources/static/static/'),
    assetsSubDirectory: '',
    assetsPublicPath: '/static/', // 发版时，静态文件存放的目录
    productionSourceMap: false, // 打开源码模式，方便在正式版中调试
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],
    bundleAnalyzerReport: process.env.npm_config_report
  },
  dev: {
    env: require('./dev.env'),
    autoOpenBrowser: false, // 启动开发环境时，不要自动打开浏览器
    assetsSubDirectory: 'app-static',
    assetsPublicPath: '/',

    // 本项目是微信公众，因为微信JS SDK 验签时只认80和443端口，所以我们这里只好用80端口，如果不用微信JS SDK，最后不要用80
    port: 30001,

    // 定义开发环境下，那些目录需要交给后端出来
    proxyTable: {
      '/api': apiServer, //api请求需要重定向到后端服务器
      '/dict': apiServer, //字典也需要重定向到后端服务器
      '/attachments': apiServer, // 上传的附件
      '/jmxInWeb': apiServer, // jmx
    },

    // 我们现在用less写样式表，为了方便查看，我们需要打开源码模式
    cssSourceMap: true
  }
}
