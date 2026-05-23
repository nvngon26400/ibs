import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputConfirmA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード（BB）
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.detailNumber = obj.detailNumber ? obj.detailNumber : '' // 明細番号
    this.lotteryResult = obj.lotteryResult ? obj.lotteryResult : '' // 抽選結果
    this.bbQuantityAlloc = obj.bbQuantityAlloc ? obj.bbQuantityAlloc : '' // 当選株数
    this.orderStatus = obj.orderStatus ? obj.orderStatus : '' // 注文状況
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.jutyuKbn = obj.jutyuKbn ? obj.jutyuKbn : '' // 受注方法
    this.mokuromiKoufuKbn = obj.mokuromiKoufuKbn ? obj.mokuromiKoufuKbn : '' // 目論見書の交付方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.bbRemark = obj.bbRemark ? obj.bbRemark : '' // 備考
  }
}
