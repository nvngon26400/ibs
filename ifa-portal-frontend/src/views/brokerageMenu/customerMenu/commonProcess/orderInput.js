// var commonProcess = require('./common.js')

// その他注文内容のセット
exports.setOrderInfo = (self) => {
  // [1] 見積単価に表示する内容を生成する
  const unitPrice = function() {
    if (self.form.method.method === '0') {
      return self.form.method.limitOrderPrice
    } else if (self.form.method.method === '1') {
      return self.stockInfo.current
    } else if (self.form.method.method === '2') {
      return self.form.method.stopOrderExecutePrice
    }
    return '-'
  }
  // [2] 約定金額に表示する内容を生成する
  const contractAmount = function() {
    return self.form.orderInfo.unitPrice * self.form.volume
  }
  // [3] 手数料を計算する
  const charge = function(amount) {
    // TODO: 手数料は約定金額により変動させる
    const charge = 2000
    return charge
  }
  // [4] 消費税を計算する
  const consumptionTax = function(value) {
    // TODO: 消費税は10%固定
    const taxRate = 0.1
    return Math.round(value * taxRate)
  }
  // [5] 譲渡益税を計算する
  const capitalGainsTax = function() {
    if (self.form.tradeType === '1') {
      const amount = self.form.orderInfo.contractAmount
      const charge = self.form.orderInfo.charge
      const conTax = self.form.orderInfo.consumptionTax
      const taxRate = 0.20315
      return Math.round((amount - charge - conTax) * taxRate)
    } else {
      return 0
    }
  }
  // [6] 精算金額に表示する内容を生成する
  const settlementAmount = function() {
    if (self.form.tradeType === '0') {
      const amount = self.form.orderInfo.contractAmount
      const charge = self.form.orderInfo.charge
      const conTax = self.form.orderInfo.consumptionTax
      return amount + charge + conTax
    } else if (self.form.tradeType === '1') {
      const amount = self.form.orderInfo.contractAmount
      const charge = self.form.orderInfo.charge
      const conTax = self.form.orderInfo.consumptionTax
      const capTax = self.form.orderInfo.capitalGainsTax
      return amount - charge - conTax - capTax
    }
    return '-'
  }
  // 約定予定日
  const contractDate = function(dt) {
    return dt.getFullYear() + '/' +
               (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
               ('0' + dt.getDate()).slice(-2)
  }
  // 受渡予定日
  /*
  const deliveryDate = function(dt) {
    const workDateTime = new Date(dt)
    workDateTime.setHours(0, 0, 0, 0)
    // 受渡日は3営業日後
    workDateTime.setDate(workDateTime.getDate() + 3)
    // 3営業日後が土日なら翌月曜日に補正
    if (workDateTime.getDay() === 6) {
      workDateTime.setDate(workDateTime.getDate() + 2)
    } if (workDateTime.getDay() === 0) {
      workDateTime.setDate(workDateTime.getDate() + 1)
    }
    // TODO: 土日以外の非営業日を選択しない処理が必要
    return dt.getFullYear() + '/' +
               (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
               ('0' + dt.getDate()).slice(-2)
  }
  */
  // n営業日後の日付を求める
  const getBusinessDay = function(n, flag) {
    const dt = new Date()
    dt.setDate(dt.getDate() + n)
    // 翌日または翌々日が土日の場合は2日シフトして補正
    if (dt.getDay() === 6 || dt.getDay() === 0) {
      dt.setDate(dt.getDate() + 2)
    }
    // TODO: 算出した日付が非営業日の時に日付を進めて営業日を検索する処理が必要

    const dayOfWeek = ['日', '月', '火', '水', '木', '金', '土']
    return dt.getFullYear() + '/' +
            (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
            ('0' + dt.getDate()).slice(-2) +
            (flag ? '(' + dayOfWeek[dt.getDay()] + ')' : '')
  }
  // 発注日
  const orderDate = function(dt) {
    return dt.getFullYear() + '/' +
               (('0' + (dt.getMonth() + 1)).slice(-2)) + '/' +
               ('0' + dt.getDate()).slice(-2)
  }
  // 発注時刻
  const orderTime = function(dt) {
    return ('0' + dt.getHours()).slice(-2) + ':' +
               ('0' + dt.getMinutes()).slice(-2)
  }
  // EC受注番号
  const ecNo = function() {
    self.count += Math.trunc(Math.random() * 11)
    return 460311 + self.count
  }

  // 以下の6行は､各々依存性があるので呼び出し順に注意
  self.form.orderInfo.unitPrice = unitPrice()
  self.form.orderInfo.contractAmount = contractAmount()
  self.form.orderInfo.charge = charge(self.form.orderInfo.contractAmount)
  self.form.orderInfo.consumptionTax = consumptionTax(self.form.orderInfo.charge)
  self.form.orderInfo.capitalGainsTax = capitalGainsTax()
  self.form.orderInfo.settlementAmount = settlementAmount()

  const dt = new Date()
  self.form.orderInfo.contractDate = contractDate(dt)
  self.form.orderInfo.deliveryDate = getBusinessDay(2, false)
  self.form.orderInfo.orderDate = orderDate(dt)
  self.form.orderInfo.orderTime = orderTime(dt)
  self.form.orderInfo.ecNo = ecNo(dt)
}

// 数量のバリデーションチェック処理
exports.volumeValidator = (rule, value, callback, self) => {
  // 銘柄検索されていない場合はエラー
  if (self.stockInfo.data === null) {
    callback('銘柄を指定してください｡')
    return
  }

  // 数量が0の場合はエラー
  if (value === 0) {
    callback('数量を入力してください｡')
  }

  // 数量が単元単位でない場合はエラー
  if (self.stockInfo.priorityMarket !== '5') {
    if (value % self.stockInfo.unit !== 0) {
      callback('数量は' + self.stockInfo.unit + '単位で入力してください｡')
    }
  }
  // OK
  callback()
}

// 価格/執行方法のバリデーションチェック処理
exports.methodValidator = (rule, value, callback, self) => {
  // 銘柄検索されていない場合はエラー
  if (self.stockInfo.data === null) {
    callback('銘柄を指定してください｡')
    return
  }

  // 執行方法別に入力値をチェック
  if (value.method === '0') {
    // 指値の場合､値幅制限の範囲外が指定された場合はエラー
    if (Number(value.limitOrderPrice) > Number(self.stockInfo.limitHigh) ||
            Number(value.limitOrderPrice) < Number(self.stockInfo.limitLow)) {
      callback('価格/執行方法が正しく有りません｡ [値幅制限]')
      return
    }
  } else if (value.method === '1') {
    // 成行の場合､チェックする項目が無い
  } else if (value.method === '2') {
    // 逆指値の場合､基準となる価格と執行する価格が値幅制限の範囲外が指定された場合はエラー
    if (Number(value.stopOrderPrice) > Number(self.stockInfo.limitHigh) ||
            Number(value.stopOrderPrice) < Number(self.stockInfo.limitLow) ||
            (value.stopOrderMethod === '0' &&
             (Number(value.stopOrderExecutePrice) > Number(self.stockInfo.limitHigh) ||
              Number(value.stopOrderExecutePrice) < Number(self.stockInfo.limitLow)))) {
      callback('価格/執行方法が正しく有りません｡ [値幅制限]')
      return
    }
  }
  // OK
  callback()
}

// 価格/執行方法のバリデーションチェック処理
exports.agreementValidator1 = (rule, value, callback, self) => {
  // 銘柄検索されていない場合はエラー
  if (self.stockInfo.data === null) {
    callback('銘柄を指定してください｡')
    return
  }
  if (self.form.agree.insider === false) {
    callback('インサイダー取引および契約締結前交付書面を確認してください｡')
    return
  }
  // OK
  callback()
}

// 価格/執行方法のバリデーションチェック処理
exports.agreementValidator2 = (rule, value, callback, self) => {
  // 銘柄検索されていない場合はエラー
  if (self.stockInfo.data === null) {
    callback('銘柄を指定してください｡')
    return
  }
  if (self.priorityMarket === '0' && self.form.agree.sor === false) {
    callback('SOR対象銘柄の説明を確認してください｡')
    return
  }
  // OK
  callback()
}

// 特定・一般区分のバリデーションチェック処理
exports.accountTypeValidator = (rule, value, callback, self) => {
  // 銘柄検索されていない場合はエラー
  if (self.form.accountType === '') {
    callback('特定・一般区分を指定してください｡')
    return
  }
  // OK
  callback()
}
