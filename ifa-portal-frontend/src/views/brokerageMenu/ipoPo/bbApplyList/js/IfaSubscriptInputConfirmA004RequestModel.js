import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputConfirmA004RequestModel {
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
    this.orderStatus = obj.orderStatus ? obj.orderStatus : '' // 注文状況
    this.detailNumber = obj.detailNumber ? obj.detailNumber : '' // 明細番号
    this.updateTimeForOrderExclusivity = obj.updateTimeForOrderExclusivity ? obj.updateTimeForOrderExclusivity : '' // 更新時間（注文排他用）
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.contractAmount = obj.contractAmount ? obj.contractAmount : '' // 約定金額
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.jutyuKbn = obj.jutyuKbn ? obj.jutyuKbn : '' // 受注方法
    this.mokuromiKoufuKbn = obj.mokuromiKoufuKbn ? obj.mokuromiKoufuKbn : '' // 目論見書の交付方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.solicitTypeName = obj.solicitTypeName ? obj.solicitTypeName : '' // 訂正前_勧誘区分
    this.receiveOrderTypeName = obj.receiveOrderTypeName ? obj.receiveOrderTypeName : '' // 訂正前_受注方法
    this.prospectusIssueMethodWord = obj.prospectusIssueMethodWord ? obj.prospectusIssueMethodWord : '' // 訂正前_目論見書の交付方法
    this.importantMatterType2 = obj.importantMatterType2 ? obj.importantMatterType2 : '' // 訂正前_重要事項の説明
    this.bbRemark2 = obj.bbRemark2 ? obj.bbRemark2 : '' // 訂正前_備考
    this.orderFlag = obj.orderFlag ? obj.orderFlag : '' // 注文フラグ
  }
}
