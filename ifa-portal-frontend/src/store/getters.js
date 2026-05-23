const getters = {
  sidebar: state => state.app.sidebar,
  customerPortalMenuList: state => state.customerPortalMenuList.customerPortalMenuList,
  creditRepaymentWrapperCurrentViewName: state => state.customerPortalMenuList.creditRepaymentWrapperCurrentViewName,
  creditRepaymentWrapperParam: state => state.customerPortalMenuList.creditRepaymentWrapperParam,
  inquiryWrapperCurrentViewName: state => state.customerPortalMenuList.inquiryWrapperCurrentViewName,
  inquiryWrapperParam: state => state.customerPortalMenuList.inquiryWrapperParam,
  initInquiryInitFlag: state => state.customerPortalMenuList.isInitInquiryWrapper,
  frameworkSessionId: state => state.user.frameworkSessionId,
  authToken: state => state.user.authToken,
  requestTimeOutSec: state => state.user.requestTimeOutSec,
  lastRequestSec: state => state.user.lastRequestSec,
  idleLimitSec: state => state.user.idleLimitSec,
  requestedTime: state => state.user.requestedTime,
  userAccount: state => state.user.userAccount,
  rPPOptions: state => state.user.rPPOptions,
  previousBusinessDay: state => state.user.previousBusinessDay,
  sugBoxUnreadItems: state => state.user.sugBoxUnreadItems,
  releaseNoteDispFlg: state => state.user.releaseNoteDispFlg,
  // 現在表示している画面の情報
  pageInfo: state => state.page.pageInfo,
  route: state => state.page.route,
  customerInfo: state => state.page.customerInfo,
  // 外国株式 信用返済
  foreignStockCreditRepayWrapperCurrentViewName: state => state.customerPortalMenuList.foreignStockCreditRepayWrapperCurrentViewName, // 表示画面
  codeValue: state => state.code.codeValue,
  codeList: state => state.code.codeList,
  // 通知
  notifications: state => state.notifications
}
export default getters
