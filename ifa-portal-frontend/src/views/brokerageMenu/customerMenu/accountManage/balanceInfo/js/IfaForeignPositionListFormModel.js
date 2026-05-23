export class IfaForeignPositionListFormModel {
  constructor() {
    this.screenId = 'SUB0202_010203-01' // 画面ID
    this.screenTitle = '米株建玉一覧' // 画面名
    this.positionListList = [
      {
        countryCode: '', // 米株建玉一覧.国コード
        brandCode: '', // 米株建玉一覧.銘柄コード
        brandName: '', // 米株建玉一覧.銘柄名
        marketAbbreviatedName: '', // 米株建玉一覧.市場略名
        tradeKbn: '', // 米株建玉一覧.売買区分
        marginDueDate: '', // 米株建玉一覧.信用期日
        openTradeDate: '', // 米株建玉一覧.建日
        depositType: '', // 米株建玉一覧.預り区分
        lastTradeDate: '', // 米株建玉一覧.返済期限
        positionNecessaryDepositRate: '', // 米株建玉一覧.建玉必要保証金率
        additionalSecurityRegulationPositionFlag: '', // 米株建玉一覧.増担保規制建玉フラグ
        positionRemainingQuantity: '', // 米株建玉一覧.建玉残数量
        unactualQuantity: '', // 米株建玉一覧.注文中
        newPositionPriceForeign: '', // 米株建玉一覧.新規建単価（外貨）
        currentPriceOrPreviousDayEndPrice: '', // 米株建玉一覧.現在値or前日終値
        foreignNewPositionAmount: '', // 米株建玉一覧.新規建代金（外貨）
        expensesTotalAmountForeign: '', // 米株建玉一覧.諸経費合計額（外貨）
        customerListProfitAndLossForeign: '', // 米株建玉一覧.評価損益（外貨）
        valuationRate: '', // 米株建玉一覧.評価割合
        repaySellButton: '', // 米株建玉一覧.返済売ボタン
        repayBuyButton: '', // 米株建玉一覧.返済買ボタン
        tradeButtonDeactivation: '', // 米株建玉一覧.取引ボタン非活性
        domesticTradeDate: '', // 米株建玉一覧.国内約定日
        localTradeDate: '', // 米株建玉一覧.現地約定日
        jpyAmountNewPositionPrice: '' // 米株建玉一覧.新規建単価（円貨）
      }
    ]
    this.intermediaryValueList = [{
      tradeClass: '', // 取引種別
      intermediaryValue: '' // 媒介可否
    }] // 媒介可否リスト
    this.tradeSuspendFlag = '' // 取引停止フラグ
  }
}
