const commonProcess = require('./common.js')
const transferProcess = require('./inquiryViewTransfer.js')

exports.transferView = (self, previousView, currentView, clickedButton, param) => {
  // 画面遷移
  const transferObj = transferProcess.getTemplate()
  transferObj.self = self
  transferObj.previousView = previousView
  transferObj.currentView = currentView
  transferObj.clickedButton = clickedButton
  transferObj.param = makeSendParam(previousView, currentView, param)
  transferObj.doTransfer()
}

const makeSendParam = (previousView, currentView, param) => {
  return {
    data: param,
    viewInfo: {
      previousView: previousView,
      currentView: currentView
    }
  }
}

/**
 * 信用返済フロー用ラッパーコンポーネントの表示制御用関数。
 * @param {Object} self - 実行コンテキスト。
 * @param {string} viewName - 信用返済フロー用ラッパーコンポーネントに表示する画面名。
 * @param {Object} params - 信用返済フロー用ラッパーコンポーネントに表示する画面のFormに設定する値。
 * @returns {void} なし。
*/
exports.setInquiryWrapperView = function(self, viewName, params) {
  self.$store.dispatch('customerPortalMenuList/setInquiryWrapperParam', { params })
  self.$nextTick(() => {
    self.$store.dispatch('customerPortalMenuList/setInquiryWrapperCurrentViewName', { viewName })
  })
}

/**
 * 信用返済フロー用ラッパーコンポーネントの画面名取得用関数。
 * @returns {Object} result
 * @returns {string} result.inquiryAnswerChange - 問合せ回答変更画面を表すView名。
 * @returns {string} result.inquiryAnswerConfirm - 問合せ回答確認画面を表すView名。
 * @returns {string} result.inquiryAnswerInput - 問合せ回答入力画面を表すView名。
 * @returns {string} result.inquiryChange - 問合せ変更画面を表すView名。
 * @returns {string} result.inquiryDetail - 問合せ詳細画面を表すView名。
 * @returns {string} result.inquiryList - 問合せ一覧画面を表すView名。
 * @returns {string} result.inquiryNewInput - 問合せ新規入力画面を表すView名。
 * @returns {string} result.inquirySearch - 問合せ検索画面を表すView名。
*/
exports.getInquiryWrapperViewNames = () => {
  return {
    inquiryAnswerChange: 'inquiryAnswerChange',
    inquiryAnswerConfirm: 'inquiryAnswerConfirm',
    inquiryAnswerInput: 'inquiryAnswerInput',
    inquiryChange: 'inquiryChange',
    inquiryDetail: 'inquiryDetail',
    inquiryList: 'inquiryList',
    inquiryNewInput: 'inquiryNewInput',
    inquirySearch: 'inquirySearch'
  }
}

// 他画面からの呼び出し時処理
exports.setup = (params, viewName, self) => {
  if (params !== null && params.data !== null) {
    self.form.no = params.data.no
    self.form.inquiryAnswerDate = params.data.inquiryAnswerDate
    self.form.handler = params.data.handler
    self.form.inquiryCategory = getInquiryCategory(params.data.inquiryCategoryL, params.data.inquiryCategoryM, params.data.inquiryCategoryS)
    self.form.inquiryCategoryL = params.data.inquiryCategoryL
    self.form.inquiryCategoryM = params.data.inquiryCategoryM
    self.form.inquiryCategoryS = params.data.inquiryCategoryS
    self.form.cr = params.data.cr
    self.form.claim = getClaim(params.data.cr, viewName)
    self.form.request = getRequest(params.data.cr, viewName)
    self.form.importance = params.data.importance
    self.form.contactRoute = params.data.contactRoute
    self.form.st = params.data.st
    self.form.incoming = params.data.incoming
    self.form.visitDate = params.data.visitDate
    self.form.nextVisitDate = params.data.nextVisitDate
    self.form.detail = params.data.detail
    self.form.answer = params.data.answer
  }
  if (params !== null && params.viewInfo !== null) {
    self.viewInfo.previousView = params.viewInfo.currentView != null ? params.viewInfo.currentView : ''
    self.viewInfo.currentView = viewName
  }
}

// form リセット処理用データ編集
exports.setupResetValue = (params, viewName, self) => {
  self.resetVal.no = params.data.no
  self.resetVal.inquiryAnswerDate = params.data.inquiryAnswerDate
  self.resetVal.handler = params.data.handler
  self.resetVal.inquiryCategory = getInquiryCategory(params.data.inquiryCategoryL, params.data.inquiryCategoryM, params.data.inquiryCategoryS)
  self.resetVal.inquiryCategoryL = params.data.inquiryCategoryL
  self.resetVal.inquiryCategoryM = params.data.inquiryCategoryM
  self.resetVal.inquiryCategoryS = params.data.inquiryCategoryS
  self.resetVal.cr = params.data.cr
  self.resetVal.claim = getClaim(params.data.cr, viewName)
  self.resetVal.request = getRequest(params.data.cr, viewName)
  self.resetVal.importance = params.data.importance
  self.resetVal.contactRoute = params.data.contactRoute
  self.resetVal.st = params.data.st
  self.resetVal.incoming = params.data.incoming
  self.resetVal.visitDate = params.data.visitDate
  self.resetVal.nextVisitDate = params.data.nextVisitDate
  self.resetVal.detail = params.data.detail
  self.resetVal.answer = params.data.answer
}

// form リセット処理
exports.resetForm = (self) => {
  self.form.no = self.resetVal.no
  self.form.inquiryAnswerDate = self.resetVal.inquiryAnswerDate
  self.form.handler = self.resetVal.handler
  self.form.inquiryCategory = self.resetVal.inquiryCategory
  self.form.inquiryCategoryL = self.resetVal.inquiryCategoryL
  self.form.inquiryCategoryM = self.resetVal.inquiryCategoryM
  self.form.inquiryCategoryS = self.resetVal.inquiryCategoryS
  self.form.cr = self.resetVal.cr
  self.form.claim = self.resetVal.claim
  self.form.request = self.resetVal.request
  self.form.importance = self.resetVal.importance
  self.form.contactRoute = self.resetVal.contactRoute
  self.form.st = self.resetVal.st
  self.form.incoming = self.resetVal.incoming
  self.form.visitDate = self.resetVal.visitDate
  self.form.nextVisitDate = self.resetVal.nextVisitDate
  self.form.detail = self.resetVal.detail
  self.form.answer = self.resetVal.answer
}

// クレーム
const getClaim = (cr, viewName) => {
  if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryDetail') return '※'
  else if (viewName === 'inquiryDetail') return '-'
  if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryAnswerConfirm') return '※'
  else if (viewName === 'inquiryAnswerConfirm') return '-'
  else if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryNewInput') return true
  else if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryChange') return true
  else if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryAnswerInput') return true
  else if ((cr === 'C' || cr === 'CR') && viewName === 'inquiryAnswerChange') return true
  else return false
}

// リクエスト
const getRequest = (cr, viewName) => {
  if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryDetail') return '※'
  else if (viewName === 'inquiryDetail') return '-'
  if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryAnswerConfirm') return '※'
  else if (viewName === 'inquiryAnswerConfirm') return '-'
  else if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryNewInput') return true
  else if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryChange') return true
  else if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryAnswerInput') return true
  else if ((cr === 'R' || cr === 'CR') && viewName === 'inquiryAnswerChange') return true
  else return false
}

// カテゴリ
const getInquiryCategory = (l, m, s) => {
  let returnStr = ''
  returnStr += l
  if ((l != null && l.length > 0) && (m != null && m.length > 0)) returnStr += '・'
  returnStr += m
  if ((m != null && m.length > 0) && (s != null && s.length > 0)) returnStr += '・'
  returnStr += s
  return returnStr
}

// 接触履歴画面から問合せ詳細画面へ遷移時に受け渡す情報を作成する
exports.getDummyInquiryInfoFromContactHistory = (params) => {
  const data = Object
  data['no'] = '10000'
  data['inquiryAnswerDate'] = commonProcess.getBusinessDayYYYYMMDD(0, true)
  data['handler'] = params.userName
  data['inquiryCategory'] = getInquiryCategory('', params.midClass, '')
  data['inquiryCategoryL'] = ''
  data['inquiryCategoryM'] = params.midClass
  data['inquiryCategoryS'] = ''
  data['cr'] = 'C'
  data['claim'] = '※'
  data['request'] = '-'
  data['importance'] = '高'
  data['contactRoute'] = '訪問'
  data['st'] = params.orderStatus
  data['incoming'] = 'イン'
  data['visitDate'] = commonProcess.getBusinessDayYYYYMMDD(-2, true)
  data['nextVisitDate'] = commonProcess.getBusinessDayYYYYMMDD(2, true)
  data['detail'] = 'アンサー'
  data['answer'] = ''
  const viewInfo = Object
  viewInfo['previousView'] = ''
  viewInfo['currentView'] = 'contactHistory'
  const param = Object
  param['data'] = data
  param['viewInfo'] = viewInfo
  return param
}
