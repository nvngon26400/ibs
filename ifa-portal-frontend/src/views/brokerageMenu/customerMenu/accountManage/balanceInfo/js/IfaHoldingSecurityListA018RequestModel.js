import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA018RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.productName = obj.productName ? obj.productName : '' // エラー: 「商品名」が、項目辞書に存在しません。
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.securityType = obj.securityType ? obj.securityType : '' // 商品区分
    this.kokunaiGaiKbn = obj.kokunaiGaiKbn ? obj.kokunaiGaiKbn : '' // 国内外国区分
    this.securityClass1 = obj.securityClass1 ? obj.securityClass1 : '' // 商品種別１
    this.securityClass2 = obj.securityClass2 ? obj.securityClass2 : '' // 商品種別2
    this.companyCode = obj.companyCode ? obj.companyCode : '' // 会社ｺｰﾄﾞ
    this.rightType = obj.rightType ? obj.rightType : '' // 権利区分
    this.newOldType = obj.newOldType ? obj.newOldType : '' // 新旧区分
    this.times = obj.times ? obj.times : '' // 回数
    this.issue1 = obj.issue1 ? obj.issue1 : '' // 号1
    this.issue2 = obj.issue2 ? obj.issue2 : '' // 号2
    this.listedCountryCode = obj.listedCountryCode ? obj.listedCountryCode : '' // 上場国ｺｰﾄﾞ
    this.nonSpecificDepositCategory = obj.depositBalanceListSpecificDepositType ? obj.depositBalanceListSpecificDepositType : '' // エラー: 「非特定預り区分」が、項目辞書に存在しません。
    this.getAccountCategory = obj.depositBalanceAccountTypeName ? obj.depositBalanceAccountTypeName : '' // エラー: 「取得口座区分」が、項目辞書に存在しません。
    this.securityClass = obj.securityClass ? obj.securityClass : '' // 商品コード
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
