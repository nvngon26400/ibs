import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.sendCorrectLogicJudgeFlag = obj.sendCorrectLogicJudgeFlag ? obj.sendCorrectLogicJudgeFlag : '' // 送信・訂正用ロジック処理判定フラグ
    this.presentationDate = obj.presentationDate ? obj.presentationDate : '' // 上場日
    this.bbPeriodTo = obj.bbPeriodTo ? obj.bbPeriodTo : '' // 募集期間（To）
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.unit = obj.unit ? obj.unit : '' // 売買単位
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.bbQuantityAlloc = obj.bbQuantityAlloc ? obj.bbQuantityAlloc : '' // 当選株数
    this.maxAllocation = obj.maxAllocation ? obj.maxAllocation : '' // 配分上限株数
    this.noteInfoCheckbox = obj.noteInfoCheckbox ? obj.noteInfoCheckbox : '' // 注意情報アラート確認
    this.noteLimitCheck = obj.noteLimitCheck ? obj.noteLimitCheck : '' // お知らせアラート確認
    this.customerCode = obj.customerCode ? obj.customerCode : '' // 顧客コード
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.depositTypeConfirm = obj.depositTypeConfirm ? obj.depositTypeConfirm : '' // 預り区分アラート確認
    this.invitationCheck = obj.invitationCheck ? obj.invitationCheck : '' // コンプラランクチェック確認
    this.complianceCheckMsg = obj.complianceCheckMsg ? obj.complianceCheckMsg : '' // コンプラランクチェック.チェックボックス文言
    this.contractAmount = obj.contractAmount ? obj.contractAmount : '' // 約定金額
    this.bbRemark = obj.bbRemark ? obj.bbRemark : '' // 備考
    this.bbRemark2 = obj.bbRemark2 ? obj.bbRemark2 : '' // 訂正前_備考
    this.edelivAgreementDate = obj.edelivAgreementDate ? obj.edelivAgreementDate : '' // 電子交付同意
    this.readTime = obj.readTime ? obj.readTime : '' // 目論見書閲覧
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerChargeCode = obj.brokerChargeCode ? obj.brokerChargeCode : '' // 仲介業者営業員コード
    this.dealerNumber = obj.dealerNumber ? obj.dealerNumber : '' // 扱者コード
    this.lotteryResult = obj.lotteryResult ? obj.lotteryResult : '' // 抽選結果
    this.issueBbPrice = obj.issueBbPrice ? obj.issueBbPrice : '' // 発行価格
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.jutyuKbn = obj.jutyuKbn ? obj.jutyuKbn : '' // 受注方法
    this.mokuromiKoufuKbn = obj.mokuromiKoufuKbn ? obj.mokuromiKoufuKbn : '' // 目論見書の交付方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.orderStatus = obj.orderStatus ? obj.orderStatus : '' // 注文状況
    this.orderFlag = obj.orderFlag ? obj.orderFlag : '' // 注文フラグ
  }
}
