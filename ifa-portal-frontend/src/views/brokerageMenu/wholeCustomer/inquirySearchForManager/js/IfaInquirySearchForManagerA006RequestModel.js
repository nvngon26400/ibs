import Logger from '@/utils/ifaLog.js'
export class IfaInquirySearchForManagerA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : ''// 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名（漢字／カナ）_条件
    this.inquiryDateYmFrom = obj.inquiryDateYmFrom ? obj.inquiryDateYmFrom : '' // 問合せ日From
    this.inquiryDateYmTo = obj.inquiryDateYmTo ? obj.inquiryDateYmTo : '' // 問合せ日To
    this.toiawaseDCd = obj.toiawaseDCd ? obj.toiawaseDCd : '' // 問合せカテゴリコード（大）
    this.toiawaseCd = obj.toiawaseCd ? obj.toiawaseCd : '' // 問合せカテゴリコード（中）
    this.toiawaseSCd = obj.toiawaseSCd ? obj.toiawaseSCd : '' // 問合せカテゴリコード（小）
    this.cr = obj.cr ? obj.cr : '' // クレーム/リクエスト
    this.juuyoudo = obj.juuyoudo ? obj.juuyoudo : '' // 重要度
    this.taiouSts = obj.taiouSts ? obj.taiouSts : '' // 対応ステータス
    this.nyuuryokuShaId = obj.nyuuryokuShaId ? obj.nyuuryokuShaId : '' // 入力者(ID)
    this.nyuuryokuShaName = obj.nyuuryokuShaName ? obj.nyuuryokuShaName : '' // 入力者(氏名)
  }
}
