var path = require('path')
var webpack = require('webpack')
var utils = require('./utils')
var config = require('../config')
var vueLoaderConfig = require('./vue-loader.conf')

function resolve (dir) {
  return path.join(__dirname, '..', dir)
}

const buildMode = process.env.NODE_ENV === 'production'

module.exports = {
  entry: {
    app: './src/main.js'
  },

  // 配置 chunkFilename 实现懒加载
  output: {
    path: config.build.assetsRoot,
    filename: buildMode ? '[name].js?[chunkhash]' : '[name].js',
    chunkFilename: 'chunk[id].js?[chunkhash]',
    publicPath: buildMode ? config.build.assetsPublicPath : config.dev.assetsPublicPath
  },

  plugins: [
    new webpack.ProvidePlugin({
      $: "jquery",
      jQuery: "jquery"
    })
  ],

  // 设置全局变量,我们在外面用script的方式引用js库后，需要在这说明一下，
  externals: {
    'lzDict': 'lzDict',
    // 'wx': 'wx',
  },

  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      // 将常用的控件定义为别名，方便使用
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),

      // 一些别名
      'zepto': 'n-zepto',
      'wx': 'weixin-js-sdk'
    }
  },

  module: {
    rules: [
      {
        // 必须配置ftl文件用html-loader来加载，因为默认的loader和ftl有冲突，会其他解释里面的占位符
        test: /\.ftl$/,
        use: [ {
          loader: 'html-loader',
          options: {
            minimize: true
          }
        }],
      },
      {
        test: /\.(js|vue)$/,
        loader: 'eslint-loader',
        enforce: 'pre',
        include: [resolve('src'), resolve('test')],
        options: {
          formatter: require('eslint-friendly-formatter')
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: vueLoaderConfig
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        include: [resolve('src'), resolve('test')]
      },
      {
        test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('img/[name].[hash:7].[ext]')
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
        }
      },
    ]
  },
}
