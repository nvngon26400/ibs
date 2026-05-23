import axios from 'axios'
import store from '@/store'
import { updateRequestedTime } from '@/utils/storeHelper'
import { backToLoginWithDialog } from '@/utils/errorHandler'

const TITLE_BAD_REQUEST = 'Bad Request'
const TITLE_UNAUTHORIZED = 'アクセス権限エラー'
const TITLE_REQUEST_ERROR = 'リクエストエラー'
const TITLE_SYSTEM_ERROR = 'システムエラー'
const TITLE_SESSION_TIMEOUT = 'セッションタイムアウト'
const MSG_BAD_REQUEST = 'The request sent by the client was syntactically incorrect.'
const MSG_UNAUTHORIZED = 'アクセス権限がありません。'
const MSG_SESSION_TIMEOUT = 'セッションがタイムアウトしました。再度ログインして下さい。'
const MSG_NOT_FOUND = '404  ページが見つかりません。'
const MSG_METHOD_NOT_ALLOWED = 'リクエストが正しくありません。'
const MSG_INTERNAL_SERVER_ERROR = '予期しないエラーが発生しました。'
const MSG_SERVICE_UNAVAILABLE = 'ご選択されたサービスは現在ご利用いただけません。'
export const UPLOAD_ACTION = 'uploadAction'
export const GET_CSV_ACTION = 'getCsvAction'
export const GET_PDF_ACTION = 'getPdfAction'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  timeout: store ? (store.getters.requestTimeOutSec * 1000 || 1800000) : 1800000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  request => {
    // 無操作タイムアウトチェック
    const lastRequestSec = store.getters.lastRequestSec || 0
    const idleLimitSec = store.getters.idleLimitSec * 1000 || 0
    const currentTime = new Date().getTime()
    if (idleLimitSec !== 0 && currentTime - lastRequestSec > idleLimitSec) {
      throw new axios.Cancel(MSG_SESSION_TIMEOUT)
    }

    // セッションID,認証トークンをヘッダに設定する
    request.headers['FrameworkSessionId'] = store.getters.frameworkSessionId || ''
    request.headers['AuthToken'] = store.getters.authToken || ''
    request.headers['Content-Type'] = request.headerContentType

    // 顧客共通情報.部店コードと顧客共通情報.口座番号をヘッダに設定する
    const cc = store.getters.customerInfo
    request.headers['ButenCode'] = cc?.butenCode || ''
    request.headers['AccountNumber'] = cc?.accountNumber || ''

    switch (request.actionType) {
      // ファイルアップロードする際、ヘッダのContent-Typeを設定する
      case UPLOAD_ACTION:
        request.headers['Content-Type'] = 'multipart/form-data'
        break
      case GET_CSV_ACTION:
      case GET_PDF_ACTION:
        request.headers['Content-Type'] = 'application/json; charset=utf-8'
        // CSVファイルをダウンロードする際、responseTypeを設定する
        request.responseType = 'blob'
        // JSON-Server を利用する場合に以下のコードを有効にする
        // request.mockJsonServer = 'Yes'
        break
      default:
        request.headers['Content-Type'] = 'application/json; charset=utf-8'
    }

    return request
  },
  error => {
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  async response => {
    const data = response.data
    // 最終リクエスト時刻を更新する。
    updateRequestedTime(data)
    if (response.config.mockJsonServer === 'Yes') {
      return await convertBlobToFile(response.data, 'application/pdf')
    }
    return data
  },
  error => {
    let title = ''
    let message = ''
    if (error instanceof axios.Cancel) {
      title = TITLE_SESSION_TIMEOUT
      message = MSG_SESSION_TIMEOUT
    } else {
      switch (error.response.status) {
        case 400:
          title = TITLE_BAD_REQUEST
          message = MSG_BAD_REQUEST
          break
        case 403:
          title = TITLE_UNAUTHORIZED
          message = MSG_UNAUTHORIZED
          break
        case 404:
          title = TITLE_REQUEST_ERROR
          message = MSG_NOT_FOUND
          break
        case 405:
          title = TITLE_REQUEST_ERROR
          message = MSG_METHOD_NOT_ALLOWED
          break
        case 500:
          title = TITLE_SYSTEM_ERROR
          message = MSG_INTERNAL_SERVER_ERROR
          break
        case 503:
          title = TITLE_SYSTEM_ERROR
          message = MSG_SERVICE_UNAVAILABLE
          break
        default:
          title = TITLE_SYSTEM_ERROR
          message = MSG_INTERNAL_SERVER_ERROR
          break
      }
    }
    // エラーメッセージを表示しつつボタン押下で、ログイン画面へ戻る
    backToLoginWithDialog(message, title)

    return Promise.reject(error)
  }
)

// > request 時に request.responseType = 'blob' を指定すると､
// > JSONで指定したファイル(.json)全体が base64 エンコードされて Blob 形式で送信されてくる｡
// > その為､Blob から base64 文字列を取り出してからデコードして､.json ファイルに変換､
// > さらに .json ファイルの blob にある文字列をもう一度 base64 デコードして Blob に変換する｡
// > 最終的に出来上がった Blob がファイルとして保存されて始めて元のPDF形式のファイルが取り出せる｡
function convertBlobToFile(blob, contentType) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.onloadend = function() {
      const base64EncodedData = reader.result
      const jsonString = atob(base64EncodedData.split(',')[1])
      const binaryString = JSON.parse(jsonString)
      const data = atob(binaryString.blob)
      const len = data.length
      const bytes = new Uint8Array(len)
      for (let i = 0; i < len; i++) {
        bytes[i] = data.charCodeAt(i)
      }
      const newBlob = new Blob([bytes], { type: contentType })
      resolve(newBlob)
    }
    reader.onerror = function(error) {
      reject(error)
    }
    reader.readAsDataURL(blob)
  })
}

export default service
