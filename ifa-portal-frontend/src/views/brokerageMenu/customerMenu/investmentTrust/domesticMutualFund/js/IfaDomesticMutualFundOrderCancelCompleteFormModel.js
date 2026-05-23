export class IfaDomesticMutualFundOrderCancelCompleteFormModel {
  constructor() {
    this.screenId = 'SUB0202_0401-04_2'
    this.screenTitle = '国内投信注文取消完了'

    this.finishMassage = '下記の内容で取消を受け付けました。' // 取消完了メッセージ
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.tradeCd = '' // 取引種別
    this.amount = '' // 金額
    this.unit = '' // 口数
    this.depositType = '' // 預り区分
    this.pointType = '' // 利用ポイント文言
    this.point = '' // 利用ポイント
    this.orderDayTime = '' // 受注日時
  }
}
