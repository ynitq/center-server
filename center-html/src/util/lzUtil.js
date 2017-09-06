/**
 * Created by liangwj on 2017/5/11 0011.
 */

import $ from 'jquery'
import toastr from 'toastr'

export default {
  myPreloadTimeOutId: null,

  /** 初始化 IziToast 的默认配置 */
  initToastr () {
    console.log('初始化 bootstrap-toastr')
    toastr.options = {
      'closeButton': true,
      'debug': false,
      'positionClass': 'toast-top-right',
      'onclick': null,
      'showDuration': '1000',
      'hideDuration': '1000',
      'timeOut': '3000',
      'extendedTimeOut': '1000',
      'showEasing': 'swing',
      'hideEasing': 'linear',
      'showMethod': 'fadeIn',
      'hideMethod': 'fadeOut',
    }
  },

  /** 判断字符串是否为空 */
  isEmpty (str) {
    if (str === null || str === undefined) {
      return true
    } else {
      let strValue = str.trim()
      return (strValue === '')
    }
  },

  /** 是否是手机号 */
  isPhone (str) {
    if (this.isEmpty(str)) {
      return false
    }

    let isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/
    let isMobile = /^[1][34578][0-9]{9}$/
    return isMobile.test(str) || isPhone.test(str)
  },

  /** 是否是手机号 */
  isSmsCode (str) {
    if (this.isEmpty(str)) {
      return false
    }

    let isCode = /^[0-9]{6}$/
    return isCode.test(str)
  },

  /** 是否邮箱 */
  isEmail (str) {
    if (this.isEmpty(str)) {
      return false
    }

    let reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
    return reg.test(str)
  },

  /** 是否身份证号 */
  isIDCard (str) {
    if (this.isEmpty(str)) {
      return false
    }

    let reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/
    return reg.test(str)
  },

  /** 为baseJsonResponse显示参数错误 **/
  showErrorForJsonResponse (res) {
    console.debug('调用ajax时，返回错误信息', res.message)
    this.showErrorMsg(res.message)
  },

  /** 显示错误信息 */
  showErrorMsg (msg) {
    toastr.error(msg)
  },

  /** 正常的显示信息 */
  showMsg (msg) {
    toastr.success(msg)
  },

  toStr (obj) {
    return JSON.stringify(obj)
  },

  toObj (strJSON) {
    return JSON.parse(strJSON)
  },

  /** 显示加载条 */
  showPreloader () {
    if (this.myPreloadTimeOutId !== null) {
      clearTimeout(this.myPreloadTimeOutId)
    }
    // 先显示透明的mask，防止双击
    $('#loading-mask').show()

    // 延时一下，如果还是没有加载完成，再显示‘加载中’
    this.myPreloadTimeOutId = setTimeout(function () {
      $('#loading-content').show()
    }, 300)
  },

  /** 隐藏加载条 */
  hidePreloader () {
    if (this.myPreloadTimeOutId !== null) {
      clearTimeout(this.myPreloadTimeOutId)
    }
    this.myPreloadTimeOutId = null

    $('#loading-mask').hide()
    $('#loading-content').hide()
  },

  /** 默认的ajax错误处理方法 */
  onAjaxError (xhr, errorType, error) {
    // 如果没有传入错误处理函数，就用默认的

    let result = this.getJsonFromErrorResponse(xhr)
    if (result !== null) {
      this.showErrorForJsonResponse(result)
    } else {
      this.showErrorMsg('出错了! 状态码:' + xhr.status)
    }
  },

  /** 尝试从错误信息中解析出 json格式的内容， 如果无法解析就返回null */
  getJsonFromErrorResponse (xhr) {
    let result = null
    // 如果没有传入错误处理函数，就用默认的
    if (xhr.readyState === 4) {
      // 服务器有返回内容
      try {
        // api server 返回的是json各式的错误信息
        result = this.toObj(xhr.responseText)
      } catch (e) {
      }
    }
    return result
  },

  /**
   * 重新封装的ajax
   *
   * @param url
   *            ajax访问url
   * @param param
   *            ajax参数
   * @param callback
   *            回调函数，参数是 result, status, xhr
   * @param exOption 用于覆盖回调函数的option，主要是error、complate、apiError、progress
   *
   */
  ajax  (url, param, callback, exOption) {
    if (this.isEmpty(url)) {
      return
    }

    let that = this
    let timeKey = 'lzUtil.ajax(url=' + url + ') 调用花费时间'

    let options = {
      type: 'post', // post get
      url: url,
      data: param,
      dataType: 'json',

      // 请求成功之后调用。传入返回后的数据，以及包含成功代码的字符串
      success: function (result, status, xhr) {
        if (!result.success) {
          // 如果失败， 检查是否有 apiError，如果有就调用传入的方法
          if (exOption && exOption['apiError']) {
            let fn = exOption['apiError']
            if (typeof fn === 'function') {
              fn(result)
            }
          } else {
            // 如果没有 apiError，就调用常规的错误处理
            that.showErrorForJsonResponse(result)
          }
        } else {
          // 如果成功
          try {
            callback(result, status, xhr)
          } catch (ex) {
            console.error('success 回调时发生了错误', ex)
          }
        }
      },

      // 请求出错时调用。 (超时，解析错误，或者状态码不在HTTP 2xx)
      error: function (xhr, errorType, error) {
        let errorFn = function (xhr, errorType, error) {
          that.onAjaxError(xhr, errorType, error)
        }
        if (exOption && exOption['error']) {
          const fn = exOption['error']
          if (typeof fn === 'function') {
            errorFn = fn
          }
        }

        try {
          errorFn(xhr, errorType, error)
        } catch (ex) {
          console.error('error 回调时发生了错误', ex)
        }
      },

      // 请求完成时调用，无论请求失败或成功。
      complete: function (xhr, status) {
        // 结束计时
        console.timeEnd(timeKey)
        // 关闭加载动画
        that.hidePreloader()

        if (exOption && exOption['complete']) {
          const fn = exOption['complete']
          if (typeof fn === 'function') {
            try {
              fn(xhr, status)
            } catch (ex) {
              console.error('complete 回调时发生了错误', ex)
            }
          }
        }
      },
    }

    if (param instanceof self.FormData) {
      console.debug('ajax 是上传文件，修改options:', param)
      options.contentType = false
      options.processData = false
    }

    if (exOption) {
      // 如果有额外的选项
      const progressFn = exOption['progress']
      if (typeof progressFn === 'function') {
        // 如果需要监听进度
        options.xhr = function () {
          const xhr = $.ajaxSettings.xhr()
          if (xhr.upload) {
            xhr.upload.addEventListener('progress', progressFn, false)
            return xhr
          }
        }
      }
    }

    try {
      // 开始计时
      console.time(timeKey)

      // 显示loading
      this.showPreloader()

      const handler = $.ajax(options)
      return handler
    } catch (e) {
      this.hidePreloader()
      console.error('调用ajax时发送了错误', e)
    }
  },

  /** 从url中获取参数 */
  getParamFromUrl (name) {
    let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    let r = window.location.search.substr(1).match(reg)
    if (r !== null) {
      return decodeURI(r[2])
    }
    return null
  },

  /** 生成一个随机id */
  randomId () {
    const len = 20
    const chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz'
    const maxPos = chars.length
    let str = ''
    for (let i = 0; i < len; i++) {
      str += chars.charAt(Math.floor(Math.random() * maxPos))
    }
    return str
  },

  /** 根据目标对象有的属性，拷贝 */
  copyForTarget (src, target) {
    if (src && target) {
      for (let key in target) {
        let value = src[key]
        if (!value) {
          let type = typeof target[key]
          if (type === 'string') {
            value = '无'
          } else if (type === 'number') {
            value = 0
          } else if (type === 'boolean') {
            value = false
          }
          // console.debug(`${key}的类型为空，准备根据${type}设置默认值为 ${value}`)
        }
        target[key] = value
      }
    }
  },

  /**
   * 格式时间
   * - Format('yyyy-MM-dd hh:mm:ss.S') ==> 2006-07-02 08:09:04.423
   * - Format('yyyy-M-d h:m:s.S')      ==> 2006-7-2 8:9:4.18
   * @param date Date对象
   * @param fmt 格式字符串
   * @returns {string}
   */
  dateFormat (date, fmt) {
    const o = {
      'M+': date.getMonth() + 1, // 月份
      'd+': date.getDate(), // 日
      'h+': date.getHours(), // 小时
      'm+': date.getMinutes(), // 分
      's+': date.getSeconds(), // 秒
      'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
      'S': date.getMilliseconds(), // 毫秒
    }
    let res = ''

    if (!this.isEmpty(fmt)) {
      res = fmt

      if (/(y+)/.test(fmt)) {
        res = res.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
      }

      for (let k in o) {
        if (new RegExp('(' + k + ')').test(res)) {
          res = res.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
    }
    return res
  },

  /** 根据参数判断是否为真 */
  isTure (param) {
    if (typeof param === 'string') {
      return param === 'true'
    } else if (typeof param === 'boolean') {
      return param
    }
    return false
  },

}

