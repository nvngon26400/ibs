const getDefaultState = () => {
  return {
    codeValue: [],
    codeList: []
  }
}

const state = getDefaultState()

const mutations = {
  // 状態を初期値に戻す
  resetState: (state) => {
    Object.assign(state, getDefaultState())
  },
  setCodeValue: (state, codeValue) => {
    state.codeValue = [...codeValue]
  },
  setCodeList: (state, codeList) => {
    state.codeList = [...codeList]
  }
}

const actions = {
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('resetState')
  },
  setCodeValue({ commit }, codeValue) {
    commit('setCodeValue', codeValue)
  },
  setCodeList({ commit }, codeList) {
    commit('setCodeList', codeList)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
