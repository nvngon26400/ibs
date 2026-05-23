import { login, logout, getInfo } from '@/api/user'
import { storeLoginResponse } from '@/utils/storeHelper'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    frameworkSessionId: '', // FWセッションID
    authToken: '', // 認証トークン
    requestTimeOutSec: 0, // リクエストタイムアウト値（秒）
    lastRequestSec: 0, // 前回レスポンス受信日時（ミリ秒)
    idleLimitSec: 0, // 無操作タイムアウト値（秒）
    requestedTime: '', // リクエスト時間(yyyy/MM/dd hh:mm)
    userAccount: '', // ユーザアカウント情報
    rPPOptions: [], // データグリッドページング情報
    previousBusinessDay: '', // 前営業日
    sugBoxUnreadItems: 0, // 目安箱未読件数
    releaseNoteDispFlg: false // リリースノート表示フラグ
  }
}

const state = getDefaultState()

const mutations = {
  resetState: (state) => {
    Object.assign(state, getDefaultState())
  },
  setFrameworkSessionId: (state, frameworkSessionId) => {
    state.frameworkSessionId = frameworkSessionId
  },
  setAuthToken: (state, authToken) => {
    state.authToken = authToken
  },
  setRequestTimeOutSec: (state, requestTimeOutSec) => {
    state.requestTimeOutSec = requestTimeOutSec
  },
  setLastRequestSec: (state, lastRequestSec) => {
    state.lastRequestSec = lastRequestSec
  },
  setIdleLimitSec: (state, idleLimitSec) => {
    state.idleLimitSec = idleLimitSec
  },
  setRequestedTime: (state, requestedTime) => {
    state.requestedTime = requestedTime
  },
  setUserAccount: (state, userAccount) => {
    state.userAccount = userAccount
  },
  setCcsUserPw: (state, newPassword) => {
    state.userAccount.medUsers.ccsUserPw = newPassword
  },
  setRPPOptions: (state, rPPOptions) => {
    state.rPPOptions = rPPOptions
  },
  setPreviousBusinessDay: (state, previousBusinessDay) => {
    state.previousBusinessDay = previousBusinessDay
  },
  setSugBoxUnreadItems: (state, sugBoxUnreadItems) => {
    state.sugBoxUnreadItems = sugBoxUnreadItems
  },
  setReleaseNoteDispFlg: (state, releaseNoteDispFlg) => {
    state.releaseNoteDispFlg = releaseNoteDispFlg
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { userId, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ userId: userId.trim(), password: password }).then(response => {
        storeLoginResponse(response)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        resetRouter()
        commit('resetState')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('resetState')
      resolve()
    })
  },
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('resetState')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

