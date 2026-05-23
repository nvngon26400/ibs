
const getDefaultState = () => {
  return {
    customerPortalMenuList: [],
    creditRepaymentWrapperCurrentViewName: '',
    creditRepaymentWrapperParam: {},
    inquiryWrapperCurrentViewName: '',
    inquiryWrapperParam: {},
    foreignStockCreditRepayWrapperCurrentViewName: ''
  }
}

const state = getDefaultState()

const mutations = {
  // 状態を初期値に戻す
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  RESET_MENU_LIST: (state) => {
    // Object.assign(state, getDefaultState())
    Object.assign(state, [{ redirect: 'noRedirect', meta: { title: 'reset' }, path: 'dummyPath' }])
  },
  SET_MENU_LIST: (state, menuList) => {
    state.customerPortalMenuList.splice(0)
    Object.assign(state.customerPortalMenuList, menuList)
  },
  SET_CREDIT_REPAYMENT_WRAPPER_CURRENT_VIEW_NAME: (state, viewName) => {
    state.creditRepaymentWrapperCurrentViewName = viewName
  },
  SET_CREDIT_REPAYMENT_WRAPPER_PARAM: (state, param) => {
    state.creditRepaymentWrapperParam = {}
    Object.assign(state.creditRepaymentWrapperParam, param)
  },
  SET_INQUIRY_WRAPPER_CURRENT_VIEW_NAME: (state, viewName) => {
    state.inquiryWrapperCurrentViewName = viewName
  },
  SET_INQUIRY_WRAPPER_PARAM: (state, param) => {
    state.inquiryWrapperParam = {}
    Object.assign(state.inquiryWrapperParam, param)
  },
  SET_INIT_INQUIRY_WRAPPER_FLAG: (state, isInit) => {
    state.isInitInquiryWrapper = isInit
  },

  // 外国株式 信用返済：表示画面
  SET_FOREIGN_STOCK_CREDIT_REPAY_WRAPPER_CURRENT_VIEW_NAME: (state, viewName) => {
    state.foreignStockCreditRepayWrapperCurrentViewName = viewName
  }
}

const actions = {
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('RESET_STATE')
  },
  setMenuList({ commit }, { menuList }) {
    commit('SET_MENU_LIST', menuList)
  },
  resetMenuList({ commit }) {
    commit('RESET_MENU_LIST')
  },
  setCreditRepaymentWrapperCurrentViewName({ commit }, { viewName }) {
    commit('SET_CREDIT_REPAYMENT_WRAPPER_CURRENT_VIEW_NAME', viewName)
  },
  setCreditRepaymentWrapperParam({ commit }, { params }) {
    commit('SET_CREDIT_REPAYMENT_WRAPPER_PARAM', params)
  },
  setInquiryWrapperCurrentViewName({ commit }, { viewName }) {
    commit('SET_INQUIRY_WRAPPER_CURRENT_VIEW_NAME', viewName)
  },
  setInquiryWrapperParam({ commit }, { params }) {
    commit('SET_INQUIRY_WRAPPER_PARAM', params)
  },
  setInitInquiryWrapperFlag({ commit }, { isInit }) {
    commit('SET_INIT_INQUIRY_WRAPPER_FLAG', isInit)
  },

  // 外国株式 信用返済：表示画面
  setForeignStockCreditRepayWrapperCurrentViewName({ commit }, { viewName }) {
    commit('SET_FOREIGN_STOCK_CREDIT_REPAY_WRAPPER_CURRENT_VIEW_NAME', viewName)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

