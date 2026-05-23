import store from '@/store'
import { IfaCodeListDisplayPatternModel } from '@/components/Input/js/IfaCodeListDisplayPatternModel'
import { IfaCodeListSelectPatternModel } from '@/components/Input/js/IfaCodeListSelectPatternModel'

/**
 * ログイン時のレスポンスをストアに格納する
 */
export const storeLoginResponse = function(response) {
  // FWセッションID
  store.commit('user/setFrameworkSessionId', response.frameworkSessionId)
  // 認証トークン
  store.commit('user/setAuthToken', response.authToken)
  // リクエストタイムアウト値（秒数）
  store.commit('user/setRequestTimeOutSec', response.requestTimeOutSec)
  // 無操作タイムアウト値（秒数）
  store.commit('user/setIdleLimitSec', response.idleLimitSec)
  // ユーザアカウント情報
  store.commit('user/setUserAccount', response.userAccount)
  //  データグリッドページング情報
  store.commit('user/setRPPOptions', JSON.parse('[' + response.rPPOptions + ']'))
  //  前営業日
  store.commit('user/setPreviousBusinessDay', response.previousBusinessDay)
  //  目安箱未読件数
  store.commit('user/setSugBoxUnreadItems', response.sugBoxUnreadItems)
  // リリースノート表示フラグ
  store.commit('user/setReleaseNoteDispFlg', response.releaseNoteDispFlg)
  // 全ての CodeValue をロードして追加
  store.commit('code/setCodeValue', IfaCodeListDisplayPatternModel.displayPatternModel)
  // 全ての CodeList をロードして追加
  store.commit('code/setCodeList', IfaCodeListSelectPatternModel.selectPatternModel)
}

/**
 * ログイン時のレスポンスをストアに格納する
 * レスポンスに含まれるユーザーアカウント情報で更新する際に利用する
 */
export const updateUserAccount = function(response) {
  // ユーザアカウント情報
  store.commit('user/setUserAccount', response.userAccount)
}

/**
 * リクエスト日時を更新する。
 */
export const updateRequestedTime = function(response) {
  // 前回レスポンス受信日時（秒数）
  store.commit('user/setLastRequestSec', new Date().getTime())
  if (response && response.requestedTime) {
    store.commit('user/setRequestedTime', response.requestedTime)
  }
}

/**
 * ユーザー関連のstore情報をリセットする。
 */
export const resetUserState = function() {
  store.commit('user/resetState')
}

/**
 * 目安箱未読件数を更新する。
 */
export const updateSugBoxUnreadItems = function(sugBoxUnreadItems) {
  store.commit('user/setSugBoxUnreadItems', sugBoxUnreadItems)
}

/**
 * リリースノート表示フラグを更新する。
 */
export const updateReleaseNoteDispFlg = function(releaseNoteDispFlg) {
  store.commit('user/setReleaseNoteDispFlg', releaseNoteDispFlg)
}
