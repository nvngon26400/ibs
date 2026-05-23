export class IfaResponseStatusUpdateFormModel {
  constructor() {
    this.screenId = 'SUB020301_03-01_1' // 画面ID
    this.title = '対応状況更新' // 画面名
    this.investmentTrustAssociationCode = '' // 投信協会コード
    this.mutualFundBrandName = '' // 投信銘柄名
    this.standardDate = '' // 基準日
    this.price = '' // 基準価額
    this.diff = '' // 前日比
    this.rateOfDecline = '' // 下落率
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerName = '' // 顧客名
    this.responseMethodClassification = '' // 対応方法区分
    this.otherContentsClassification = '' // その他内容区分
    this.otherDetail = '' // 「その他」の詳細_入力 【初期値】未入力
    this.customerResponseDate = '' // 顧客対応日_入力 【初期値】未入力
    this.responseFinishConfirmDate = '' // 対応完了確認日_入力 【初期値】未入力
    this.statusClassification = '' // ステータス区分（hidden）
    this.declineRateKbn = '' // 下落率区分（hidden）
    this.updateTime = '' // 更新日時
  }
}
