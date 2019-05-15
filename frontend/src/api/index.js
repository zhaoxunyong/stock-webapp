// 配置API接口地址
// var root = 'https://cnodejs.org/api/v1'
var root = '/'
// 引用axios
// var axios = require('axios')
// import Bus from '../eventBus'
import * as alerts from '../utils/alert.js'

// 自定义判断元素类型JS
function toType(obj) {
    return {}.toString
        .call(obj)
        .match(/\s([a-zA-Z]+)/)[1]
        .toLowerCase()
}

/*
  接口处理函数
*/
function fileUpload(file, url) {
    return new Promise(function(resolve, reject) {
        let param = new FormData() //创建form对象
        param.append('file', file, file.name) //通过append向form对象添加数据
        param.append('chunk', '0') //添加form表单中其他数据

        let config = {
            headers: { 'Content-Type': 'multipart/form-data' }
        }
        alerts.loadingStart()
        axios
            .post(url, param, config)
            .then(response => {
                // console.log(response.data)
                alerts.loadingEnd()
                resolve(response.data)
            })
            .catch(function(err) {
                alerts.loadingEnd()
                // console.log('err--->' + err)
                // let errMsg = err.response.statusText + ' : ' + err.response.data
                reject(err)
            })
    })
}

function apiAxios(method, url, params) {
    return new Promise(function(resolve, reject) {
        alerts.loadingStart()
        axios({
            method: method,
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            },
            url: url,
            data: method === 'POST' || method === 'PUT' ? params : null,
            params: method === 'GET' || method === 'DELETE' ? params : null,
            baseURL: root,
            withCredentials: false
        })
            .then(function(res) {
                // console.log("res.data---->"+res.data)
                alerts.loadingEnd()
                resolve(res.data)
            })
            .catch(function(err) {
                alerts.loadingEnd()
                if (err) {
                    if ('Unauthorized' == err.response.statusText || err.response.data.indexOf('code: 906,') != -1) {
                        alerts.error('會話超時，請重新登錄！')
                        window.location.href = '/#/login'
                    } else {
                        // window.alert('api error, HTTP CODE: ' + err)
                        let errMsg = err.response.statusText + ' : ' + err.response.data
                        alerts.error(errMsg)
                        reject(err)
                    }
                }
            })
    })
}

// 返回在vue模板中的调用接口
export default {
    get: function(url, params) {
        return apiAxios('GET', url, params)
    },
    post: function(url, params) {
        return apiAxios('POST', url, params)
    },
    put: function(url, params) {
        return apiAxios('PUT', url, params)
    },
    delete: function(url, params) {
        return apiAxios('DELETE', url, params)
    },
    fileUpload: function(file, url) {
        return fileUpload(file, url)
    }
}
