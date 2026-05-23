import axios from 'axios'
import { ElNotification, ElMessageBox, ElMessage } from 'element-plus'
import router from '@/router'
import store from '@/store'
import { RequestModel } from '@/common/model/RequestModel'
import Logger from '@/utils/ifaLog.js'

axios.defaults.timeout = 1300000
axios.defaults.baseURL = process.env.VUE_APP_BASE_API
axios.defaults.withCredentials = true

const requestBuild = () => {
  return new Promise((resolve, reject) => {
    if (
      !localStorage.getItem('uuid') ||
      !store.state.uuid ||
      store.state.uuid === localStorage.getItem('uuid')
    ) {
      resolve()
    }
  })
}

// export const get = (url, params = {}) => {
//   return new Promise((resolve, reject) => {
//     requestBuild().then(() => {
//       axios({
//         method: 'get',
//         url: url,
//         headers: {
//           'Content-Type': 'application/json',
//           'X-Token': store.getters.user_token
//         }
//       }).then(
//         obj => {
//           resolve(obj.data)
//         },
//         err => {
//           reject(err)
//         }
//       )
//     })
//   })
// }

// POSTリクエスト共通処理。
export const postForm = (url, body = {}) => {
  // 業務処理上のリクエストBodyを共通リクエストモデルの形式に構成。
  const requestBody = new RequestModel(body)
  // Promiseを返却。
  return new Promise((resolve, reject) => {
    const headers = { 'Content-Type': 'application/json' }
    requestBuild().then(() => {
      axios({
        method: 'post',
        url: url,
        data: requestBody,
        headers
      }).then(
        obj => {
          resolve(obj.data)
        },
        err => {
          reject(err)
        }
      )
    })
  })
}

axios.interceptors.request.use(
  config => {
    return config
  },
  error => {
    Logger.error(error)
    Promise.reject(error)
  }
)

axios.interceptors.response.use(
  response => {
    Logger.debug('responce interceptor : ' + response)
    if (!response.data) {
      router.push('/login')
    }
    const code = response.data.rtnCd
    const title = response.data.title
    const msg = response.data.message
    if (code === 0) {
      if (msg) {
        ElNotification.info({
          title: title,
          message: msg
        })
      }
      return response
    } else if (code === 1) {
      if (msg) {
        ElNotification.success({
          title: title,
          message: msg
        })
      }
      return response
    } else if (code === 2) {
      ElNotification.warning({
        title: title,
        message: msg
      })
      return response
    } else if (code === 3) {
      ElMessage.error({
        title: title,
        message: msg
      })
      return response
    } else if (code === 4) {
      ElMessageBox.alert(msg, 'error', {
        confirmButtonText: 'OK',
        showClose: false,
        closeOnClickModal: false,
        closeOnPressEscape: false,
        callback: action => {
          router.push('/login')
        }
      })
    } else if (code === 5) {
      ElMessageBox.alert(msg, 'warn', {
        confirmButtonText: 'OK',
        showClose: false,
        closeOnClickModal: false,
        closeOnPressEscape: false,
        callback: action => {
          router.push('/login')
        }
      })
    } else {
      return response
    }
  },
  error => {
    Logger.error('err' + error)
    ElMessageBox.alert(
      'Network error, please contact your local network administrator.',
      'ERROR',
      {
        confirmButtonText: 'OK',
        showClose: false,
        closeOnClickModal: false,
        closeOnPressEscape: false,
        callback: action => {
          router.push('/login')
        }
      }
    )
  }
)
