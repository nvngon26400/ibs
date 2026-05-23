const template = {
  self: null,
  previousView: '',
  currentView: '',
  clickedButton: '',
  param: null,
  doTransfer: function() {
    // 必ず 1 レコード取れる想定
    const transferInfo = getTabInfoAndEmitId(this.previousView, this.currentView, this.clickedButton)
    if (transferInfo[0].tabsForClick != null) {
      this.self.$store.dispatch('customerPortalMenuList/setInitInquirytWrapperFlag', { isInit: false })
      transferInfo[0].tabsForClick.forEach((tab) => { document.getElementById(tab).click() })
      this.self.$store.dispatch('customerPortalMenuList/setInitInquirytWrapperFlag', { isInit: true })
    }
    if (transferInfo[0].emitId != null) {
      this.self.$emit(transferInfo[0].emitId, this.param)
    } else {
      this.self.$emit(transferInfo[0].emitId)
    }
  }
}

exports.getTemplate = () => {
  return template
}

const getTabInfoAndEmitId = (previousView, currentView, clickedButton) => {
  const transferInfo = viewTransferInfoList.filter(function(data) {
    return ((previousView === data.previousView) && currentView === data.currentView && clickedButton === data.clickedButton)
  })

  if (transferInfo.length === 1) {
    return transferInfo
  } else {
    return viewTransferInfoList.filter(function(data) {
      return ((data.previousView === '*') && currentView === data.currentView && clickedButton === data.clickedButton)
    })
  }
}

// 画面遷移情報リスト
const viewTransferInfoList = [
  //     inquiryList:問合せ一覧
  { previousView: '*', currentView: 'inquiryList', clickedButton: '詳細', tabsForClick: null, emitId: 'move-to-inquiry-detail' },
  { previousView: '*', currentView: 'inquiryList', clickedButton: '回答', tabsForClick: null, emitId: 'move-to-inquiry-answer-input' },
  { previousView: '*', currentView: 'inquiryList', clickedButton: '問合せ検索画面へ', tabsForClick: null, emitId: 'move-to-inquiry-search' },
  { previousView: '*', currentView: 'inquiryList', clickedButton: '新規問合せ登録画面へ', tabsForClick: null, emitId: 'move-to-inquiry-new-input' },
  //     inquirySearch:問合せ検索
  { previousView: '*', currentView: 'inquirySearch', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-list' },
  { previousView: '*', currentView: 'inquirySearch', clickedButton: '検索', tabsForClick: null, emitId: 'move-to-inquiry-list' },
  //     inquiryDetail:問合せ詳細
  { previousView: '*', currentView: 'inquiryDetail', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-list' },
  { previousView: '*', currentView: 'inquiryDetail', clickedButton: '問合せ修正', tabsForClick: null, emitId: 'move-to-inquiry-change' },
  { previousView: '*', currentView: 'inquiryDetail', clickedButton: '回答修正', tabsForClick: null, emitId: 'move-to-inquiry-answer-change' },
  { previousView: '*', currentView: 'inquiryDetail', clickedButton: '回答入力', tabsForClick: null, emitId: 'move-to-inquiry-answer-input' },
  { previousView: 'contactHistory', currentView: 'inquiryDetail', clickedButton: '戻る', tabsForClick: ['tab-customer-management', 'contact-history'], emitId: null },
  //     inquiryNewInput:問合せ新規入力
  { previousView: '*', currentView: 'inquiryNewInput', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-list' },
  { previousView: '*', currentView: 'inquiryNewInput', clickedButton: '確認画面へ', tabsForClick: null, emitId: 'move-to-inquiry-answer-confirm' },
  //     inquiryChange:問合せ変更
  { previousView: '*', currentView: 'inquiryChange', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-detail' },
  { previousView: '*', currentView: 'inquiryChange', clickedButton: '確認画面へ', tabsForClick: null, emitId: 'move-to-inquiry-answer-confirm' },
  //     inquiryAnswerInput:問合せ回答入力
  { previousView: 'inquiryDetail', currentView: 'inquiryAnswerInput', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-detail' },
  { previousView: 'inquiryList', currentView: 'inquiryAnswerInput', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-list' },
  { previousView: '*', currentView: 'inquiryAnswerInput', clickedButton: '確認画面へ', tabsForClick: null, emitId: 'move-to-inquiry-answer-confirm' },
  //     inquiryAnswerChange:問合せ回答変更
  { previousView: '*', currentView: 'inquiryAnswerChange', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-detail' },
  { previousView: '*', currentView: 'inquiryAnswerChange', clickedButton: '確認画面へ', tabsForClick: null, emitId: 'move-to-inquiry-answer-confirm' },
  //     inquiryAnswerConfirm:問合せ回答確認
  { previousView: 'inquiryNewInput', currentView: 'inquiryAnswerConfirm', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-new-input' },
  { previousView: 'inquiryChange', currentView: 'inquiryAnswerConfirm', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-change' },
  { previousView: 'inquiryAnswerInput', currentView: 'inquiryAnswerConfirm', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-answer-input' },
  { previousView: 'inquiryAnswerChange', currentView: 'inquiryAnswerConfirm', clickedButton: '戻る', tabsForClick: null, emitId: 'move-to-inquiry-answer-change' },
  { previousView: '*', currentView: 'inquiryAnswerConfirm', clickedButton: '入力情報で更新', tabsForClick: null, emitId: 'move-to-inquiry-list' }
  //     接触履歴から問合せ詳細への遷移
  // { previousView: '*', currentView: 'contactHistory', clickedButton: '詳細', tabsForClick: ['tab-customer-management', 'inquiry'], emitId: 'move-to-inquiry-detail' }
]
