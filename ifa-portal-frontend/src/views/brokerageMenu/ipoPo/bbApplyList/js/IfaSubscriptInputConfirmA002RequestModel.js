import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.sendCorrectLogicJudgeFlag = obj.sendCorrectLogicJudgeFlag ? obj.sendCorrectLogicJudgeFlag : '' // 送信・訂正用ロジック処理判定フラグ
    this.presentationDate = obj.presentationDate ? obj.presentationDate : '' // 上場日
    this.lotteryResult = obj.lotteryResult ? obj.lotteryResult : '' // 抽選結果
    this.bbQuantityAlloc = obj.bbQuantityAlloc ? obj.bbQuantityAlloc : '' // 当選株数
    this.unit = obj.unit ? obj.unit : '' // 売買単位
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerChargeCode = obj.brokerChargeCode ? obj.brokerChargeCode : '' // 仲介業者営業員コード
    this.dealerNumber = obj.dealerNumber ? obj.dealerNumber : '' // 扱者コード
    this.orderStatus = obj.orderStatus ? obj.orderStatus : '' // 注文状況
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名（漢字）
    this.customerNameKana = obj.customerNameKana ? obj.customerNameKana : '' // 顧客名（カナ）
    this.noticeInfoLevel = obj.noticeInfoLevel ? obj.noticeInfoLevel : '' // 注意情報レベル
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.contractAmount = obj.contractAmount ? obj.contractAmount : '' // 約定金額
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.jutyuKbn = obj.jutyuKbn ? obj.jutyuKbn : '' // 受注方法
    this.mokuromiKoufuKbn = obj.mokuromiKoufuKbn ? obj.mokuromiKoufuKbn : '' // 目論見書の交付方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.orderFlag = obj.orderFlag ? obj.orderFlag : '' // 注文フラグ
  }
}
