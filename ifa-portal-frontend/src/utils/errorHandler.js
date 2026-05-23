import axios from 'axios'
import { ElMessageBox } from 'element-plus'
import router from '@/router'
import store from '@/store'
import { resetUserState } from '@/utils/storeHelper'
import messageList from '@/resource/messages/messageList.json'
import { nextTick } from 'vue'

const DEFAULT_DURATION = 0
const ERROR_LEVEL_ERROR = -1
const ERROR_LEVEL_SUCCESS = 0
const ERROR_LEVEL_INFO = 1
const ERROR_LEVEL_WARNING = 2

// ログインに戻るダイアログの表示制御
let isBackToLoginDialogOpen = false

/**
 * 業務エラーメッセージのダイアログ表示
 * @param errorLevel エラーレベル
 * @param message メッセージ
 * @param title ダイアログタイトル
 * @param timeStamp 発生日時
 * @param duration 表示期間
 */
export function notifyMessage(errorLevel, message, title, timeStamp, duration) {
  if (!message) {
    return
  }

  // 複数メッセージ(<sep>タグ)の場合は分割される｡ (変更管理 #3369)
  const messages = message.split(/<sep>/ig)

  // エラーレベルがエラー、ワーニングの場合は×ボタンを押さないとポップアップを閉じない（時間経過により勝手に消えない） (変更管理 #3369)
  const el = errorLevel !== null && errorLevel !== '' && [ERROR_LEVEL_SUCCESS, ERROR_LEVEL_INFO, ERROR_LEVEL_WARNING, ERROR_LEVEL_ERROR].includes(Number(errorLevel))
    ? Number(errorLevel)
    : ERROR_LEVEL_WARNING
  const due = !duration || el === ERROR_LEVEL_ERROR || el === ERROR_LEVEL_WARNING ? DEFAULT_DURATION : duration

  messages.forEach(msg => {
    store.dispatch('notifications/addNotification', {
      errorLevel: el,
      title,
      message: msg,
      timeStamp: timeStamp || Date.now(),
      duration: due
    })
  })
}

/**
 * 業務エラーメッセージのダイアログ表示($notify を置き換えるラッパー)
 * @param item.errorLevel エラーレベル
 * @param item.message メッセージ
 * @param item.title ダイアログタイトル
 * @param item.duration 表示期間
 */
export function notifyWrapper(item) {
  if (!item) {
    return
  }

  let errorLevel
  switch (item.type) {
    case 'success':
      errorLevel = ERROR_LEVEL_SUCCESS
      break
    case 'info':
      errorLevel = ERROR_LEVEL_INFO
      break
    case 'error':
      errorLevel = ERROR_LEVEL_ERROR
      break
    case 'warning':
    default:
      errorLevel = ERROR_LEVEL_WARNING
      break
  }

  notifyMessage(errorLevel, item.message, item.title, undefined, item.duration)
}

/**
 * エラー発生時にサーバー側の情報をクリアするためログアウトのリクエストを投げる
 * (エラーが発生する可能性があるため、エラーが発生しても無視する)
 */
export async function clearServerSession() {
  if (store.getters.frameworkSessionId || store.getters.authToken) {
    const response = await axios.post('/common/logout', {}, {
      baseURL: process.env.VUE_APP_BASE_API,
      headers: {
        'FrameworkSessionId': store.getters.frameworkSessionId || '',
        'AuthToken': store.getters.authToken || '',
        'Content-Type': 'application/json; charset=utf-8'
      }
    })
      // eslint-disable-next-line handle-callback-err
      .catch(error => {
        // エラーハンドリングを行わない。
      })
    if (response && response.data && response.data.dataList && response.data.dataList.length > 0) {
      // ccsLogoutUrlがある場合のみHTTPリクエストを送信
      try {
        const features = 'top=0, left=0, height=100, width=100, location=no, menubar=no, toolbar=no, status=no, scrollbars=no, resizable=no'
        // 新しいウィンドウを開く
        const win = window.open(response.data.dataList[0].ccsLogoutUrl, 'ccsLogout', features)
        setTimeout(() => {
          win.close()
        }, 100)
      } catch (error) {
        // エラーハンドリングを行わない。
      }
    }
    // ログアウト時にセッション情報を空にする
    sessionStorage.clear()
    return true
  }
}

/**
 * ログイン画面に戻りますのダイアログを表示しない
 * サーバー、クライアントの認証情報をクリアし、ログイン画面へ戻る
 */
export async function backToLoginWithoutDialog() {
  // サーバー側の認証情報クリアをリクエストする
  await clearServerSession()
  // クライアント側の認証情報をクリアする
  resetUserState()
  // ログイン画面へ戻る
  router.push('/login')
}

/**
 * ログイン画面に戻りますのダイアログを表示する
 * サーバー、クライアントの認証情報をクリアし、ログイン画面へ戻る
 */
export async function backToLoginWithDialog(message, title) {
  // サーバー側の認証情報クリアをリクエストする
  await clearServerSession()

  if (!isBackToLoginDialogOpen) {
    isBackToLoginDialogOpen = true

    // エラーメッセージを表示しつつボタン押下で、ログイン画面へ戻る
    ElMessageBox.alert(message, title, {
      type: 'error',
      confirmButtonText: 'ログイン画面へ戻る',
      showClose: false
    }).then(() => {
      // ログイン画面へ戻る
      router.push('/login')
    }).finally(() => {
      isBackToLoginDialogOpen = false
    })
  }

  // クライアント側の認証情報をクリアする
  nextTick(() => {
    resetUserState()
  })
}

/**
 * エラーの messageKey からメッセージ内容に変換する
 * 参照: ifa-doc/共通/メッセージ一覧.xlsx : メッセージ一覧シート
 */
export function getMessage(key, params) {
  let message = messageList.find(item => item.key === key)?.message
  if (message && params) {
    for (let i = 0; i < params.length; i++) {
      message = message.replace(`{${i}}`, params[i])
    }
  }
  return message
}
