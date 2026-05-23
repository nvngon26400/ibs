export class IfaIpoPoBrandListFormModel {
  constructor() {
    this.title = {
      id: 'SUB0204_01-01',
      name: 'IPO/PO銘柄一覧'
    }
    this.ipoPoTypeName = '' // 区分
    this.brandCode12 = '' // 銘柄コード
    this.brandCodeWith1Name = '' // 1付き
    this.smokingCigarette = '' // 開示有無
    this.onlyElectronicDelivery = '' // 電子交付のみ
    this.changeBbPeriodFlag = '' // 期間変更
    this.changePriceFlag = '' // 価格変更
    this.brandName = '' // 銘柄名
    this.maxAllocation = '' // 配分上限株数
    this.unit = '' // 売買単位
    this.bbPresentationFromTo = '' // BB申込期間
    this.acceptStatus = '' // ステータス
    this.recruitmentPeriodFromTo = '' // 募集期間
    this.paymentDate = '' // 入金予定日（募集最終日）
    this.bookBuildingPresentationFrom = '' // ブックビルディング申込期間（開始）
    this.sellBuyUnitType = '' // 売買単位区分
  }
}
