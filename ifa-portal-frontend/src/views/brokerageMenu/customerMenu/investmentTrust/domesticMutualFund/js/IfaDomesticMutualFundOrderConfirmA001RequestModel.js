import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountType = obj.a010ApiRequest.accountType ? obj.a010ApiRequest.accountType : '' // 口座
    this.depositType = obj.a010ApiRequest.depositType ? obj.a010ApiRequest.depositType : '' // 預り区分
    this.tradeCd = obj.a010ApiRequest.tradeCd ? obj.a010ApiRequest.tradeCd : '' // 取引種別
    this.fundCodeTimes = obj.a010ApiRequest.fundCodeTimes ? obj.a010ApiRequest.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.a010ApiRequest.fundCodeIssues ? obj.a010ApiRequest.fundCodeIssues : '' // ファンドコード（号）
    this.fundType = obj.a010ApiRequest.fundType ? obj.a010ApiRequest.fundType : '' // ファンドタイプ
    this.douitsuTukaKuniKbn = obj.a010ApiRequest.douitsuTukaKuniKbn ? obj.a010ApiRequest.douitsuTukaKuniKbn : '' // 同一通貨/同一国の乗換
    this.tashaNorikaeKbn = obj.a010ApiRequest.tashaNorikaeKbn ? obj.a010ApiRequest.tashaNorikaeKbn : '' // 他社間投信乗換勧誘
    this.kanyuKbn = obj.a010ApiRequest.kanyuKbn ? obj.a010ApiRequest.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.a010ApiRequest.receiveOrderType ? obj.a010ApiRequest.receiveOrderType : '' // 受注方法
    this.unit = obj.a010ApiRequest.unit ? obj.a010ApiRequest.unit : '' // 口数
    this.saleMethod = obj.a010ApiRequest.saleMethod ? obj.a010ApiRequest.saleMethod : '' // 売却方法
    this.sellDesignated = obj.a010ApiRequest.sellDesignated ? obj.a010ApiRequest.sellDesignated : '' // 売却指定
    this.amount = obj.a010ApiRequest.amount ? obj.a010ApiRequest.amount : '' // 金額
    this.tankiSellKbn = obj.a010ApiRequest.tankiSellKbn ? obj.a010ApiRequest.tankiSellKbn : '' // 短期売却確認
    this.shokanMaeKbn = obj.a010ApiRequest.shokanMaeKbn ? obj.a010ApiRequest.shokanMaeKbn : '' // 償還前売却確認
    this.pointFlg = obj.a010ApiRequest.pointFlg ? obj.a010ApiRequest.pointFlg : '' // ポイント利用
    this.point = obj.point ? Number(obj.point).toString() : '' // 利用ポイント
    this.pointType = obj.a010ApiRequest.pointType ? obj.a010ApiRequest.pointType : '' // ポイント種別
    this.distributionReceiveMethodWord = obj.a010ApiRequest.distributionReceiveMethodWord ? obj.a010ApiRequest.distributionReceiveMethodWord : '' // 分配金受取方法
    this.mokuromiKoufuKbn = obj.a010ApiRequest.mokuromiKoufuKbn ? obj.a010ApiRequest.mokuromiKoufuKbn : '' // 目論見書の交付方法
    this.norikaeYuguKbn = obj.a010ApiRequest.norikaeYuguKbn ? obj.a010ApiRequest.norikaeYuguKbn : '' // 乗換優遇区分
    this.leverageInvestTrust = obj.a010ApiRequest.leverageInvestTrust ? obj.a010ApiRequest.leverageInvestTrust : '' // レバレッジ投資信託
    this.norikaeKanyuKbn = obj.a010ApiRequest.norikaeKanyuKbn ? obj.a010ApiRequest.norikaeKanyuKbn : '' // 乗換勧誘
    this.conflictOfInterestExplain = obj.a010ApiRequest.conflictOfInterestExplain ? obj.a010ApiRequest.conflictOfInterestExplain : '' // 利益相反可能性の説明
    this.checkMokuromi = obj.a010ApiRequest.checkMokuromi ? obj.a010ApiRequest.checkMokuromi : '' // 確認項目.目論見書補完書面の確認
    this.checkMadoAki = obj.a010ApiRequest.checkMadoAki ? obj.a010ApiRequest.checkMadoAki : '' // 確認項目.窓空きファンドの注意事項に同意
    this.checkHiyou = obj.a010ApiRequest.checkHiyou ? obj.a010ApiRequest.checkHiyou : '' // 確認項目.費用について説明済
    this.checkFukusu = obj.a010ApiRequest.checkFukusu ? obj.a010ApiRequest.checkFukusu : '' // 確認項目.複数取引業者での手数料等明示済
    this.tradeKbn = obj.a010ApiRequest.tradeKbn ? obj.a010ApiRequest.tradeKbn : '' // 売買区分
    this.complianceRankCheckConfirm = obj.complianceRankCheck.message ? (obj.alert.complianceRankCheckConfirmCheck ? '1' : '0') : '' // アラート内容確認.コンプラランクチェック確認
    this.complianceRankCheckStartBaseConfirm = obj.complianceRankCheck.startCriteriaConfirmMsg ? (obj.alert.startCriteriaConfirmCheck ? obj.alert.startCriteriaConfirmCheck : '') : '' // アラート内容確認.コンプラランクチェック開始基準確認
    this.shortTermSellAlertConfirm = obj.shortTermSellConfirmMsg ? (obj.alert.shortTermSaleConfirmCheck ? obj.alert.shortTermSaleConfirmCheck : '') : '' // アラート内容確認.短期売却確認アラート確認
    this.preRedemptionSellConfirmAlertConfirm = obj.preRedemptionSellConfirmAlertMsg ? (obj.alert.preRedemptionSellConfirmSelectCheck ? obj.alert.preRedemptionSellConfirmSelectCheck : '') : '' // アラート内容確認.償還前売却確認アラート確認
    this.noticeInfoAlertConfirm = obj.noticeInfoAlert ? (obj.alert.noticeInfoAlertCheck ? obj.alert.noticeInfoAlertCheck : '') : '' // アラート内容確認.注意情報アラート確認
    this.noticeAlertConfirm = obj.noticeAlert ? (obj.alert.noticeAlertCheck ? obj.alert.noticeAlertCheck : '') : '' // アラート内容確認.お知らせアラート確認
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラート
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラート
    this.message = obj.complianceRankCheck.message ? obj.complianceRankCheck.message : '' // コンプラランクチェック.メッセージ
    this.invitationCheck = obj.complianceRankCheck.invitationCheck ? obj.complianceRankCheck.invitationCheck : '' // コンプラランクチェック.チェックボックス文言
    this.fundCharacter = obj.complianceRankCheck.fundCharacter ? obj.complianceRankCheck.fundCharacter : '' // コンプラランクチェック.コンプラチェック用資金性格
    this.startCriteriaConfirmMsg = obj.complianceRankCheck.startCriteriaConfirmMsg ? obj.complianceRankCheck.startCriteriaConfirmMsg : '' // コンプラランクチェック.開始基準確認メッセージ
    this.shortTermSellConfirmMsg = obj.shortTermSellConfirmMsg ? obj.shortTermSellConfirmMsg : '' // 短期売却確認アラートメッセージ
    this.preRedemptionSellConfirmAlertMsg = obj.preRedemptionSellConfirmAlertMsg ? obj.preRedemptionSellConfirmAlertMsg : '' // 償還前売却確認アラートメッセージ
    this.brandName = obj.a010ApiRequest.brandName ? obj.a010ApiRequest.brandName : '' // 銘柄名
    this.shortTermSaleConfirm = obj.a010ApiRequest.shortTermSaleConfirm ? obj.a010ApiRequest.shortTermSaleConfirm : '' // 短期売却確認期間
    this.preRedemptionSellConfirmSelect = obj.a010ApiRequest.preRedemptionSellConfirmSelect ? obj.a010ApiRequest.preRedemptionSellConfirmSelect : '' // 償還前売却確認期間
    this.brandSpecialClassification = obj.a010ApiRequest.brandSpecialClassification ? obj.a010ApiRequest.brandSpecialClassification : '' // 銘柄情報.特殊区分
    this.deliveryDate = obj.deliveryDate ? obj.deliveryDate : '' // 受渡予定日
    this.brandCode = obj.a010ApiRequest.brandCode ? obj.a010ApiRequest.brandCode : '' // 銘柄コード
    this.dispatchId = obj.a010ApiRequest.dispatchId ? obj.a010ApiRequest.dispatchId : '' // 目論見書チェック区分

  }
}
