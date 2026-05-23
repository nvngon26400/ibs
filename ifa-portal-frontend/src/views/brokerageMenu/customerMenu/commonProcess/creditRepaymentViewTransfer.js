const template = {
  self: null,
  currentView: '',
  currentTab: '',
  clickedButton: '',
  param: null,
  doTransfer: function() {
    // 必ず 1 レコード取れる想定
    const transferInfo = getTabInfoAndEmitId(this.currentView, this.currentTab, this.clickedButton)
    if (transferInfo[0].tabsForClick != null) {
      transferInfo[0].tabsForClick.forEach((tab) => { document.getElementById(tab).click() })
    }
    if (transferInfo[0].emitId != null) {
      this.self.$emit(transferInfo[0].emitId, this.param)
    }
  }
}

exports.getTemplate = () => {
  return template
}

const getTabInfoAndEmitId = (currentView, currentTab, clickedButton) => {
  if (currentView === '') currentView = '*'
  return viewTransferInfoList.filter(function(data) {
    return currentView === data.currentView && currentTab === data.currentTab && clickedButton === data.clickedButton
  })
}

// 画面遷移情報リスト
const toCreditRepayment = ['tab-domestic-stock-order', 'ifa-credit-order-wrapper']
// const toCreditRepayment = ['tab-domestic-stock-order', 'ifa-credit-repayment-wrapper']
// const toCurrentDeliveryOrder = ['tab-domestic-stock-order', 'current-delivery-order']
const toUnexecutedOrderList = ['tab-account-management', 'ifa-unexecuted-order-list']

const viewTransferInfoList = [
  //     openInterestList:建玉一覧サマリータブ下
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '一括返済', tabsForClick: toCreditRepayment, emitId: 'set-open-interest-bulk-repayment' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '返買', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '返売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '現引', tabsForClick: toCreditRepayment, emitId: 'set-current-delivery-order' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '現渡', tabsForClick: toCreditRepayment, emitId: 'set-current-delivery-order' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '返済買', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  { currentView: '*', currentTab: 'openInterestList', clickedButton: '返済売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'openInterestList', clickedButton: '現引', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  // { currentView: '*', currentTab: 'openInterestList', clickedButton: '現渡', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  //     openInterestListDetail:銘柄別建玉一覧（モーダル）
  { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '返買', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '返売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '現引', tabsForClick: toCreditRepayment, emitId: 'set-current-delivery-order' },
  { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '現渡', tabsForClick: toCreditRepayment, emitId: 'set-current-delivery-order' },
  { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '返済売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '現引', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  // { currentView: '*', currentTab: 'openInterestListBrandDetail', clickedButton: '現渡', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  //     creditRepaymentOrder:信用返済タブ下（建玉一覧サマリー画面表示時の遷移情報、注文完了画面からの遷移もこちら）
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '詳細', tabsForClick: null, emitId: 'showOpenInterestBrandDetailList' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '一括返済', tabsForClick: toCreditRepayment, emitId: 'set-open-interest-bulk-repayment' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '返買', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '返売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '現引', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '現渡', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '建玉指定', tabsForClick: null, emitId: 'set-open-interest-bulk-repayment' },
  // { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '建玉一覧', tabsForClick: null, emitId: 'show-open-interest-list' },
  { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '注文情報', tabsForClick: toUnexecutedOrderList, emitId: 'move-to-unexecuted-order-list' },
  { currentView: '*', currentTab: 'creditRepaymentOrder', clickedButton: '閉じる', tabsForClick: null, emitId: 'show-open-interest-list' },
  //     currentDeliveryOrder:現引現渡注文タブ下（建玉一覧サマリー画面表示時の遷移情報もこちら）
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '詳細', tabsForClick: null, emitId: 'showOpenInterestBrandDetailList' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '一括返済', tabsForClick: ['tab-domestic-stock-order', 'tab-newOrder', 'tab-creditRepaymentOrder'], emitId: 'set-open-interest-bulk-repayment' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '返買', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '返売', tabsForClick: toCreditRepayment, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '現引', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '現渡', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-current-delivery-order' },
  // { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '建玉一覧', tabsForClick: null, emitId: 'show-open-interest-list' },
  { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '注文情報', tabsForClick: toUnexecutedOrderList, emitId: 'move-to-unexecuted-order-list' },
  { currentView: '*', currentTab: 'currentDeliveryOrder', clickedButton: '閉じる', tabsForClick: null, emitId: 'show-open-interest-list' },
  //     openInterestBulkRepayment:信用一括返済入力（口座管理 > 残高情報 > 信用一括返済入力 で表示時）
  // { currentView: '*', currentTab: 'openInterestBulkRepayment', clickedButton: '注文入力（一括指定）', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-credit-repayment-order' },
  // { currentView: '*', currentTab: 'openInterestBulkRepayment', clickedButton: '注文入力（個別指定）', tabsForClick: toCurrentDeliveryOrder, emitId: 'set-credit-repayment-order' },
  //     openInterestBulkRepayment:信用一括返済入力（信用返済注文入力画面から建玉指定ボタン押下で表示時）
  { currentView: 'openInterestBulkRepayment', currentTab: 'creditRepaymentOrder', clickedButton: '注文入力（一括指定）', tabsForClick: null, emitId: 'set-credit-repayment-order' },
  { currentView: 'openInterestBulkRepayment', currentTab: 'creditRepaymentOrder', clickedButton: '注文入力（個別指定）', tabsForClick: null, emitId: 'set-credit-repayment-order' },
  //     newCreditOrder:信用新規注文入力
  { currentView: '*', currentTab: 'newCreditOrder', clickedButton: '注文情報', tabsForClick: toUnexecutedOrderList, emitId: 'move-to-unexecuted-order-list' }
]
