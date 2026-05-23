import defaultSettings from '@/settings'

const { showSettings, fixedHeader, sidebarLogo } = defaultSettings

const getDefaultState = () => {
  return {
    showSettings: showSettings,
    fixedHeader: fixedHeader,
    sidebarLogo: sidebarLogo
  }
}

const state = getDefaultState()

const mutations = {
  // 状態を初期値に戻す
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('RESET_STATE')
  },
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

