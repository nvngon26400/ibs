import Logger from '@/utils/ifaLog.js'
export class IfaSubscriptInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名称
    this.bbPeriodTo = obj.bbPeriodTo ? obj.bbPeriodTo : '' // 募集期間（To）
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名（漢字）
    this.recruitmentOrderDate = obj.recruitmentOrderDate ? obj.recruitmentOrderDate : '' // 募集受注日時
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.detailNumber = obj.detailNumber ? obj.detailNumber : '' // 明細番号
    this.updateTimeForOrderExclusivity = obj.updateTimeForOrderExclusivity ? obj.updateTimeForOrderExclusivity : '' // 更新時間（注文排他用）
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.sendCorrectLogicJudgeFlag = obj.sendCorrectLogicJudgeFlag ? obj.sendCorrectLogicJudgeFlag : '' // 送信・訂正用ロジック処理判定フラグ

    // 注文がある場合は､銘柄コード（対面）｡注文が無い場合は､銘柄コード（BB）
    this.brandCode = '' // 銘柄コード

    // hidden 項目
    this.lotteryResult = '' // 訂正前_抽選結果 (v1.04)
    this.bbQuantityAlloc = '' // 訂正前_当選株数 (v1.04)
    this.orderStatus = '' // 訂正前_注文状況 (v1.04)
    this.domesticQuantityInput = '' // 訂正前_数量
    this.contractAmount = '' // 訂正前_約定金額
    this.depositType = '' // 訂正前_預り区分
    this.kanyuKbn = '' // 訂正前_勧誘区分
    this.jutyuKbn = '' // 訂正前_受注方法
    this.mokuromiKoufuKbn = '' // 訂正前_目論見書の交付方法
    this.importantMatterType = '' // 訂正前_重要事項の説明
    this.bbRemark = '' // 訂正前_備考
  }
}
