export class IfaMatchedTradeDetailFormModel {
  constructor() {
    this.screenId = 'SUB0202_0105-02' // 画面ID
    this.title = '出来明細' // 画面名
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.brandCode = '' // 銘柄コード //
    this.brandName = '' // 銘柄名 //
    this.domesticTradeNameText = '' // 取引種別
    this.marginTradeTypeText = '' // 信用取引区分 //
    this.tradeDate = '' // 約定日 //
    this.settlementDate = '' // 受渡日
    this.updateTime = '' // 更新日時 //
    this.tradeList = [
      {
        tradeTime: '', // 約定一覧.約定時刻 //
        tradeQuantity: '', // 約定一覧.約定数量 //
        tradePrice: '', // 約定一覧.約定単価 //
        contractAmount: '', // 約定一覧.約定金額 //
        market: '', // 約定一覧.市場 //
        depositType: '', // 約定一覧.預り区分 //
        ecOrderNo: '', // 約定一覧.EC受注番号 //
        cancelStatus: '' // 約定一覧.取消ステータス //
      }
    ] // 約定リスト //
    this.settlementPositionList = [
      {
        openTradeDate: '', // 決済建玉一覧.新規約定日
        lastTradeDate: '', // 決済建玉一覧.決済期日
        bargainMarket: '', // 決済建玉一覧.建市場
        orgNewTradeDate: '', // 決済建玉一覧.親株新規約定日
        builtPrice: '', // 決済建玉一覧.建単価
        orderQuantity: '', // 決済建玉一覧.注文数量
        actualQuantity: '', // 決済建玉一覧.約定数量
        averageTradePrice: '', // 決済建玉一覧.平均約定単価
        costTotal: '', // 決済建玉一覧.諸経費合計
        settlementLossProfit: '' // 決済建玉一覧.決済損益
      }
    ] // 決済建玉一覧 //
    this.deliveryDate = '' // 受渡予定日（hidden）//
    this.tradeClassification = '' // 取引区分（hidden）//
    this.paymentDeadline = '' // 弁済期限（hidden）//
    this.notSpecificDepositTradeType = '' // 非特定預り売買区分（hidden）//
  }
}
