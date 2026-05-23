import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名称
    this.issueBbPrice = obj.issueBbPrice ? obj.issueBbPrice : '' // 発行価格
    this.bbPeriodTo = obj.bbPeriodTo ? obj.bbPeriodTo : '' // 募集期間（To）
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名（漢字）
    this.edelivAgreementDate = obj.edelivAgreementDate ? obj.edelivAgreementDate : '' // 電子交付同意
    this.readTime = obj.readTime ? obj.readTime : '' // 目論見書閲覧
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.detailNumber = obj.detailNumber ? obj.detailNumber : '' // 明細番号
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerChargeCode = obj.brokerChargeCode ? obj.brokerChargeCode : '' // 仲介業者営業員コード
    this.dealerNumber = obj.dealerNumber ? obj.dealerNumber : '' // 扱者コード
    this.customerCode = obj.customerCode ? obj.customerCode : '' // 顧客コード
    this.presentationDate = obj.presentationDate ? obj.presentationDate : '' // 上場日
    this.updateTimeForOrderExclusivity = obj.updateTimeForOrderExclusivity ? obj.updateTimeForOrderExclusivity : '' // 更新時間（注文排他用）
    this.unit = obj.unit ? obj.unit : '' // 売買単位
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.maxAllocation = obj.maxAllocation ? obj.maxAllocation : '' // 配分上限株数
    this.sendCorrectLogicJudgeFlag = obj.sendCorrectLogicJudgeFlag ? obj.sendCorrectLogicJudgeFlag : '' // 送信・訂正用ロジック処理判定フラグ
    this.juniorNisaFlag = obj.juniorNisaFlag ? obj.juniorNisaFlag : '' // ジュニアNISAフラグ
    this.accumulateNisaFlag = obj.accumulateNisaFlag ? obj.accumulateNisaFlag : '' // つみたてNISAフラグ
    this.isaBuyAbleJudgeClassificationYear = obj.isaBuyAbleJudgeClassificationYear ? obj.isaBuyAbleJudgeClassificationYear : '' // ISA買付可能判定区分（当年）
    this.isaContractType = obj.isaContractType ? obj.isaContractType : '' // ISA契約区分
    this.nisaBuyPotentialAmount = obj.nisaBuyPotentialAmount ? obj.nisaBuyPotentialAmount : '' // NISA買付可能額
    this.tokuteiKouzaKbn = obj.tokuteiKouzaKbn ? obj.tokuteiKouzaKbn : ''
    // 注文がある場合は､銘柄コード（対面）｡注文が無い場合は､銘柄コード（BB）
    this.brandCode = '' // 銘柄コード
    // 注文が登録済みの場合は､約定金額｡注文が未登録の場合は､数量*発行価格
    this.contractAmount = '' // 約定金額

    // 入力項目
    this.lotteryResult = '' // 抽選結果
    this.bbQuantityAlloc = '' // 当選株数
    this.orderStatus = '' // 注文状況
    this.quantity = '' // 数量
    this.depositType = '' // 預り区分
    this.kanyuKbn = '' // 勧誘区分
    this.jutyuKbn = '' // 受注方法
    this.mokuromiKoufuKbn = '' // 目論見書の交付方法
    this.importantMatterType = '' // 重要事項の説明
    this.bbRemark = '' // 備考

    // Hidden
    this.solicitTypeName = '' // 訂正前_勧誘区分
    this.receiveOrderTypeName = '' // 訂正前_受注方法
    this.prospectusIssueMethodWord = '' // 訂正前_目論見書の交付方法
    this.importantMatterType2 = '' // 訂正前_重要事項の説明
    this.bbRemark2 = '' // 訂正前_備考
    this.domesticQuantityInput = '' // 訂正前_数量
    this.subscriptTradeAmount = '' // 訂正前_約定金額
    this.depositType2 = '' // 訂正前_預り区分
  }
}
