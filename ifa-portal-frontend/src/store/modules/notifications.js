const getDefaultState = () => {
  return {
    serialNumber: 0,
    notifications: []
  }
}

const state = getDefaultState()

const mutations = {
  // 状態を初期値に戻す
  resetState: (state) => {
    Object.assign(state, getDefaultState())
  },
  // 通知を追加する
  // 入力: notification = {
  //        serialNumber: [Number, String], // シリアル番号 (通知毎にユニークな値を割り振る)
  //        errorLevel: [Number, String], // エラーレベル: 0: success, 1: info, 2: warning (default), -1: error
  //        title: String, // 見出し
  //        message: String, // 内容
  //        timeStamp: [Number, String, Date], // 発生日時 (デフォルト値: PCから取得したローカル日時(日本時間))
  //        duration: [Number, String], // 表示期間 (デフォルト値: 0 (期間指定なし))
  //      }
  addNotification: (state, notification) => {
    // シリアル番号の指定がない場合は自動的に生成した番号を割り当てる
    if (!notification.serialNumber) {
      notification.serialNumber = state.serialNumber++
    }
    // キューの先頭に通知を追加していく
    state.notifications.unshift(notification)
  },
  // 通知を削除する
  // 入力: serialNumber // 通知の追加時に指定したシリアル番号
  removeNotification: (state, serialNumber) => {
    // シリアル番号に一致する通知を除いたリストを再構築する
    state.notifications = state.notifications.filter(notification => notification.serialNumber !== serialNumber)
  }
}

const actions = {
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('resetState')
  },
  // 通知を追加する
  addNotification({ commit }, notification) {
    commit('addNotification', notification)
  },
  // 通知を削除する
  removeNotification({ commit }, serialNumber) {
    commit('removeNotification', serialNumber)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
