const commonProcess = require('./common.js')
const transferProcess = require('./creditRepaymentViewTransfer.js')

// 取引セルのタグ色設定処理
exports.setTagColor = (dealType, tradeType, dealEnable) => {
  if (!dealEnable) {
    return 'secondary'
  } else if (dealType === '現引' || dealType === '返買' || dealType === '返済買') {
    return 'buy'
  } else if (dealType === '現渡' || dealType === '返売' || dealType === '返済売') {
    return 'sell'
  } else if (dealType === '詳細') {
    return 'secondary'
  } else if (dealType === '一括返済') {
    return 'primary'
  } else {
    return 'secondary'
  }
}

const desc = {
  unit: 100,
  current: 7952,
  priceQuotation: 1,
  last: 7897,
  lastDate: commonProcess.getBusinessDay(-1, true),
  diff: '+55',
  ratio: '+0.69',
  diffDateTime: commonProcess.getBusinessDay(0, true) + ' 15:00',
  limitDate: commonProcess.getBusinessDay(0, true),
  limitHigh: 7897 + 1500,
  limitLow: 7897 - 1500,
  start: 7900,
  startTime: '09:00',
  low: 7864,
  lowTime: '11:22',
  high: 8011,
  highTime: '13:31',
  value: 5813540351,
  volume: 73126200,
  volumeTime: '15:00',
  validMarkets: [true, true, false, false, false, false, true],
  tick: '2',
  flag: '"#"'
}

exports.getDesc = () => {
  return desc
}

// 取引セルのリンク押下時処理
exports.dealLinkClickedProcess = (index, dealType, fromCreditRepaymentOrder, fromCurrentDeliveryOrder, defaultTab, self) => {
  const list = (defaultTab === 'openInterestListBrandDetail') ? self.openInterestBrandDetail : self.openInterest
  if (!list[index].dealEnable) {
    return
  }

  // 取引種別の判定
  const decodeTradeType = (dealType, tradeType) => {
    if (dealType === '返買') return '0'
    else if (dealType === '返売') return '1'
    else if (dealType === '現引') return '0'
    else if (dealType === '現渡') return '1'
    else if (dealType === '返済売') return '1'
    else if (dealType === '返済買') return '0'
    else if (dealType === '一括返済' && tradeType === '買建') return '1'
    else if (dealType === '一括返済' && tradeType === '売建') return '0'
  }

  // 遷移元タブの判定
  const decodeFromTab = (fromCreditRepaymentOrder, fromCurrentDeliveryOrder, defaultTab) => {
    // if (fromCreditRepaymentOrder) return 'creditRepaymentOrder'
    if (fromCurrentDeliveryOrder) return 'currentDeliveryOrder'
    else return defaultTab
  }

  // 連携用パラメータ
  const param = {
    tradeType: decodeTradeType(dealType, list[index].tradeType),
    marginTransactionType: list[index].tradeLimit,
    builtMarket: list[index].market,
    constructionDate: list[index].tradeDate,
    parentStockTradeDate: list[index].parentStockTradeDate,
    unit: list[index].unit,
    openInterestNo: list[index].no,
    depositType: list[index].tradeLimit,
    code: list[index].code,
    name: list[index].name,
    repaymentOpenInterestSpecify: '個別指定',
    volume: list[index].stockNum,
    desc: desc,
    currentView: '',
    currentTab: decodeFromTab(fromCreditRepaymentOrder, fromCurrentDeliveryOrder, defaultTab),
    clickedButton: dealType
  }

  // 画面遷移
  // exports.transferView(
  //   self,
  //   '',
  //   decodeFromTab(fromCreditRepaymentOrder, fromCurrentDeliveryOrder, defaultTab),
  //   dealType,
  //   param
  // )

  return param
}

// 他画面からの呼び出し時処理
exports.onShow = (params, self) => {
  // storeの値ごとに場合分けして引数を設定
  // 返ってきた値をここで利用してもらう
  // 信用返済の場合
  let demoParams = exports.dealLinkClickedProcess(0, '返済買', '', '', 'openInterestList', demoSelf)
  switch (self.$store.getters.creditRepaymentWrapperCurrentViewName) {
    case 'IfaMarginRepayOrderInput':
      demoParams = exports.dealLinkClickedProcess(0, '返済買', '', '', 'openInterestList', demoSelf)
      break
    case 'IfaMarginMassRepayInput':
      demoParams = exports.dealLinkClickedProcess(1, '一括返済', '', '', 'openInterestList', demoSelf)
      break
  }

  self.openInterestRepaymentListData = []
  if (demoParams !== null && demoParams.constructor === Object && Object.keys(demoParams).length !== 0) {
    self.source = 'other'
    self.resetForm('form')
    self.form.tradeType = demoParams.tradeType
    self.form.marginTransactionType = demoParams.marginTransactionType
    self.form.builtMarket = demoParams.builtMarket
    self.form.constructionDate = demoParams.constructionDate
    self.form.parentStockTradeDate = demoParams.parentStockTradeDate
    self.form.unit = demoParams.unit
    self.form.openInterestNo = demoParams.openInterestNo
    self.form.repaymentOpenInterestSpecify = demoParams.repaymentOpenInterestSpecify
    if (demoParams.sortOrder != null) self.form.openInterestTable.sortOrder = demoParams.sortOrder
    if (demoParams.orderMode != null) self.form.openInterestTable.orderMode = demoParams.orderMode

    // 一括返済画面（fromBulkRepaymentの定義の有無で判定している）
    if (demoParams.fromBulkRepayment != null) {
      // 一括返済画面から信用返済注文入力画面へ遷移した時、画面構成を変えるフラグをONにセット
      self.fromBulkRepayment = demoParams.fromBulkRepayment
      self.form.volume = demoParams.volume

    // それ以外の画面（国内建玉一覧）
    } else {
      self.fromBulkRepayment = false
      self.form.volume = ''
    }

    // 建玉情報（一括返済時）
    if (demoParams.openInterestList != null) {
      for (let i = 0; i < demoParams.openInterestList.length; i++) {
        const openInterestData = {
          builtMarket: demoParams.openInterestList[i].market,
          constructionDate: demoParams.openInterestList[i].tradeDate,
          parentStockTradeDate: demoParams.openInterestList[i].parentStockTradeDate,
          unit: demoParams.openInterestList[i].unit,
          repaymentVolume: demoParams.openInterestList[i].orderStockNum,
          realtimeValuation: demoParams.openInterestList[i].realtimeValuation,
          no: demoParams.openInterestList[i].no
        }
        self.openInterestRepaymentListData.push(openInterestData)
      }
    }

    // 銘柄情報
    self.form.depositType = demoParams.depositType
    self.stockInfo.code = demoParams.code
    self.stockInfo.name = demoParams.name
    self.stockInfo.unit = Number(demoParams.desc.unit)
    self.stockInfo.current = demoParams.desc.current
    self.stockInfo.priceQuotation = Number(demoParams.desc.priceQuotation)
    self.stockInfo.validMarkets = demoParams.desc.validMarkets.slice()
    self.stockInfo.limitDate = demoParams.desc.limitDate
    self.stockInfo.limitHigh = demoParams.desc.limitHigh
    self.stockInfo.limitLow = demoParams.desc.limitLow

    // ユーザ情報
    demoParams.fromBulkRepayment != null ? self.userInfo.numberOfShareHeld = demoParams.maxOrderableQuantity : self.userInfo.numberOfShareHeld = demoParams.volume
    demoParams.creditRepaymentProcess = true
    self.$refs['ifaBrandSearch'].handleRowClick(demoParams)
    self.changeTradeType()
  }
}

exports.transferView = (self, currentView, currentTab, clickedButton, param) => {
  // 画面遷移
  const transferObj = transferProcess.getTemplate()
  transferObj.self = self
  transferObj.currentView = currentView
  transferObj.currentTab = currentTab
  transferObj.clickedButton = clickedButton
  transferObj.param = param
  transferObj.doTransfer()
}

exports.changeSortOrder = (value, openInterest) => {
  if (value === '0') {
    openInterest.sort((a, b) => {
      return (a.realtimeValuation > b.realtimeValuation) ? -1 : (a.realtimeValuation < b.realtimeValuation) ? 1 : 0
    })
  } else if (value === '1') {
    openInterest.sort((a, b) => {
      return (a.realtimeValuation < b.realtimeValuation) ? -1 : (a.realtimeValuation > b.realtimeValuation) ? 1 : 0
    })
  } else if (value === '2') {
    openInterest.sort((a, b) => {
      return (a.tradeDate < b.tradeDate) ? -1 : (a.tradeDate > b.tradeDate) ? 1 : 0
    })
  } else if (value === '3') {
    openInterest.sort((a, b) => {
      return (a.tradeDate > b.tradeDate) ? -1 : (a.tradeDate < b.tradeDate) ? 1 : 0
    })
  }
}

/**
 * 信用返済フロー用ラッパーコンポーネントの表示制御用関数。
 * @param {Object} self - 実行コンテキスト。
 * @param {string} viewName - 信用返済フロー用ラッパーコンポーネントに表示する画面名。
 * @param {Object} params - 信用返済フロー用ラッパーコンポーネントに表示する画面のFormに設定する値。
 * @returns {void} なし。
*/
exports.setCreditRepaymentWrapperView = function(self, viewName, params) {
  self.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperParam', { params })
  self.$nextTick(() => {
    self.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName })
  })
}

/**
 * 信用返済フロー用ラッパーコンポーネントの画面名取得用関数。
 * @returns {Object} result
 * @returns {string} result.IfaMarginNewOrderInput - 信用建玉一覧画面を表すView名。
 * @returns {string} result.IfaMarginRepayOrderInput - 信用返済注文入力画面を表すView名。
 * @returns {string} result.IfaMarginMassRepayInput - 信用一括返済入力画面を表すView名。
 * @returns {string} result.IfaReceiptDeliveryOrderInput - 現引現渡注文入力画面を表すView名。
*/
exports.getCreditRepaymentWrapperViewNames = () => {
  return {
    IfaMarginNewOrderInput: 'IfaMarginNewOrderInput',
    IfaMarginRepayOrderInput: 'IfaMarginRepayOrderInput',
    IfaMarginMassRepayInput: 'IfaMarginMassRepayInput',
    IfaReceiptDeliveryOrderInput: 'IfaReceiptDeliveryOrderInput'
  }
}

const demoSelf = {
  openInterest: [{
    index: 1,
    code: '1301',
    name: '極洋',
    tradeType: '売建',
    tradeLimit: '6ヶ月',
    market: '東証',
    tradeDate: '2021/04/26',
    repaymentDeadline: '2021/10/25',
    parentStockTradeDate: '2021/04/26',
    accountType: '特定',
    collateral: '100%',
    num: 1,
    stockNum: 3000,
    stockNumOrdering: 0,
    unit: 2933.00,
    previousDayValue: '2726.00',
    realtimeValue: '2995.00',
    price: 8799000,
    previousDayValueTotal: '8178000',
    realtimeValueTotal: '8985000',
    expences: 46283,
    previousDayValuation: 8799000 - 8178000 - 46283,
    realtimeValuation: 8799000 - 8985000 - 46283,
    restraint: 0,
    settlementDate: '2021/12/27',
    no: '00002',
    openInterestsCount: 1,
    deal: ['返済買', '現渡'],
    dealEnable: true,
    shorteningFlag: 0 },
  {
    index: 0,
    code: '1301',
    name: '極洋',
    tradeType: '買建',
    tradeLimit: '6ヶ月',
    market: 'PTS',
    tradeDate: '2021/04/26',
    repaymentDeadline: '2021/10/25',
    parentStockTradeDate: '2021/04/26',
    accountType: '特定',
    collateral: '100%',
    num: 5,
    stockNum: 1000,
    stockNumOrdering: 0,
    unit: 3060.00,
    previousDayValue: '2726.00',
    realtimeValue: '2995.00',
    price: 3060000,
    previousDayValueTotal: '2726000',
    realtimeValueTotal: '2995000',
    expences: 38343,
    previousDayValuation: 2726000 - 3060000 - 38343,
    realtimeValuation: 2995000 - 3060000 - 38343,
    restraint: 0,
    settlementDate: '2021/12/26',
    no: '00007',
    openInterestsCount: 2,
    deal: ['建玉詳細', '一括返済'],
    dealEnable: true,
    shorteningFlag: 1
  }
  ]
}

