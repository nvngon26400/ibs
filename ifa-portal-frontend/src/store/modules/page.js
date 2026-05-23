// 顧客未選択のときでも､IfaCustomerPortal はマウントされているので
// 空きの情報を参照させることで undefined になるのを防ぐ
const emptyCustomerInfo = {
  customerCode: '',
  butenCode: '',
  accountNumber: '',
  customerNameKanji: '',
  customerNameKana: '',
  complianceLank: '',
  customerAttribute: '',
  customerAttributeName: '',
  dealerNumber: '',
  corBirthFlg: '',
  age: '',
  brokerCode: '',
  brokerChargeCode: '',
  corporationType: '',
  homePhoneNumbar: '',
  phoneNumber2: '',
  representativeName: '',
  representativeNameKana: '',
  agentName: '',
  agentNameKana: '',
  titleOfAgent: '',
  specificAccountType: '',
  dividendReceiptType: '',
  taxExemptQualifiedPersonType: '',
  isaContractType: '',
  jrIsaContractType: '',
  jrNisaAccountNumber: '',
  jrTokuteiKouzaKbn: '',
  brokerName: '',
  brokerBranchName: '',
  brokerChargeName: '',
  noticeInfoLevel: '',
  ifaMemoUpdateDateTime: '',
  ifaMemoContent: '',
  isaBuyLimitYear: ' ',
  isaBuyLimitYearNext: ' ',
  isaBuyAbleYear: '',
  isaBuyAbleYearNext: '',
  nisaType: '',
  nisaTypeYearNext: '',
  isaBuyLimitYearJuniorNisa: '',
  isaBuyLimitYearNextJuniorNisa: '',
  isaBuyAbleYearJuniorNisa: '',
  isaBuyAbleYearNextJuniorNisa: '',
  withdrawalRestrictionCancelFlag: '',
  yenBuyingPower: '',
  domesticMarginAccountType: '',
  newBuildingCapacity: '',
  yenCustomerInfoDetentionRate: '',
  foreignBuyPowerFlag: '',
  foreignBuyingPowerCountryCount: '',
  foreignNewBuildingCapacity: '',
  foreignCustomerInfoDetentionRate: '',
  foreignStockTradeAccountOpenStatus: '',
  foreignSecurityTradeAccountOpenStatus: '',
  foreignMarginAccountType: '',
  ccsMemo: '',
  customerInfoCourse: ''
}

const getDefaultState = () => {
  return {
    pageInfo: {},
    route: {},
    customerInfo: emptyCustomerInfo
  }
}

const state = getDefaultState()

const mutations = {
  // 状態を初期値に戻す
  resetState: (state) => {
    Object.assign(state, getDefaultState())
  },
  setPageInfo: (state, pageInfo) => {
    state.pageInfo = { ...pageInfo }
  },
  setRoute: (state, params) => {
    state.route.params = { ...params }
  },
  setCustomerInfo: (state, customerInfo) => {
    state.customerInfo = { ...customerInfo }
  },
  removeCustomerInfo: (state) => {
    state.customerInfo = emptyCustomerInfo
  },
  setFaqParam: (state, param) => {
    state.pageInfo.faqParam = param
  }
}

const actions = {
  // 状態を初期値に戻す
  resetState({ commit }) {
    commit('resetState')
  },
  setPageInfo({ commit }, pageInfo) {
    commit('setPageInfo', pageInfo)
  },
  setRoute({ commit }, params) {
    commit('setRoute', params)
  },
  setCustomerInfo({ commit }, customerInfo) {
    commit('setCustomerInfo', customerInfo)
  },
  removeCustomerInfo({ commit }) {
    commit('removeCustomerInfo')
  },
  setFaqParam({ commit }, param) {
    commit('setFaqParam', param)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
