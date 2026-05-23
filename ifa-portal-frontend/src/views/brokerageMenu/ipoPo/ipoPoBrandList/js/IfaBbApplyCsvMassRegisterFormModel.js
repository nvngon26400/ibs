export class IfaBbApplyCsvMassRegisterFormModel {
  constructor() {
    this.title = {
      id: 'SUB0204_01-03_1',
      name: 'BB申込(一括登録)'
    }
    this.bbApplyList = []
    this.filePath = '' // ファイルパス
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.customerNameKanji = '' // 顧客名
    this.desiredStock = '' // 希望株数
    this.bbPrice = '' // 申込価格
    this.bbBrandIssuePriceTypeValue = '' // 発行価格区分
    this.investorAttributeName = '' // 投資家属性
    this.investorAttributeValue = '' // 投資家属性順序
    this.quantitySairyou = '' // 裁量希望株数
    this.discretionSelectReason = '' // 裁量選定理由
    this.remark400 = '' // 備考
    this.solicitTypeName = '' // 勧誘区分
    this.receiveOrderTypeName = '' // 受注方法
    this.checkResultSet = '' // チェック結果セット
  }
}
