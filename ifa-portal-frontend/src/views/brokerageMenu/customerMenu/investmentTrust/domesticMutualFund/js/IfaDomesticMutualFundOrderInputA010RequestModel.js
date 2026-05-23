import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA010RequestModel {
  constructor(obj, buyMethodSelectedValue) {
    Logger.debug(obj)
    let mutualFundSellBuyTypeTrans = ''
    if (obj.tradeCd === '0' || obj.tradeCd === '1') {
      mutualFundSellBuyTypeTrans = 'K'
    } else if (obj.tradeCd === '8') {
      if (obj.sellDesignatedWord === '1' || obj.sellDesignatedWord === '2') {
        if (buyMethodSelectedValue === '1') {
          mutualFundSellBuyTypeTrans = 'U'
        } else {
          mutualFundSellBuyTypeTrans = 'V'
        }
      } else if (obj.sellDesignatedWord === '3') {
        if (buyMethodSelectedValue === '1') {
          mutualFundSellBuyTypeTrans = 'A'
        } else {
          mutualFundSellBuyTypeTrans = 'B'
        }
      }
    } else {
      if (buyMethodSelectedValue === '1') {
        mutualFundSellBuyTypeTrans = 'U'
      } else {
        mutualFundSellBuyTypeTrans = 'V'
      }
    }
    let tradeCdValue = ''
    if (obj.brand.fundType === '1') {
      if (mutualFundSellBuyTypeTrans === 'U') {
        tradeCdValue = '7'
      } else if (mutualFundSellBuyTypeTrans === 'V') {
        tradeCdValue = '3'
      } else if (mutualFundSellBuyTypeTrans === 'K') {
        tradeCdValue = '0'
      }
    } else {
      if (mutualFundSellBuyTypeTrans === 'U') {
        tradeCdValue = '8'
      } else if (mutualFundSellBuyTypeTrans === 'V') {
        tradeCdValue = '4'
      } else if (mutualFundSellBuyTypeTrans === 'K') {
        tradeCdValue = '1'
      } else if (mutualFundSellBuyTypeTrans === 'A') {
        tradeCdValue = '10'
      } else if (mutualFundSellBuyTypeTrans === 'B') {
        tradeCdValue = '6'
      }
    }
    this.accountType = obj.selectAccountType ? obj.selectAccountType : ''
    this.depositType = obj.selectDepositType ? obj.selectDepositType : '' // 預り区分
    this.tradeCd = obj.tradeCd ? tradeCdValue : '' // 取引種別
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.fundType = obj.brand.fundType ? obj.brand.fundType : '' // ファンドタイプ
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brand.brandName ? obj.brand.brandName : '' // 銘柄名
    this.douitsuTukaKuniKbn = obj.sameCurrencySameCountryTransfers ? obj.sameCurrencySameCountryTransfers : '' // 同一通貨/同一国の乗換
    this.tashaNorikaeKbn = obj.intercompanyMutualFundTransferSolicitation ? obj.intercompanyMutualFundTransferSolicitation : '' // 他社間投信乗換勧誘
    this.kanyuKbn = obj.solicitType ? obj.solicitType : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.unit = obj.unit ? obj.unit : '' // 口数
    this.saleMethod = obj.brand.buyMethodSelect ? obj.brand.buyMethodSelect : '' // 売却方法
    this.sellDesignated = obj.sellDesignatedWord ? obj.sellDesignatedWord : '' // 売却指定
    this.amount = obj.amount ? obj.sellDesignatedWord === 3 ? '0000000000' : obj.amount : '' // 金額
    this.tankiSellKbn = obj.shortTermSaleConfirmSelect ? obj.shortTermSaleConfirmSelect : '' // 短期売却確認
    this.shokanMaeKbn = obj.preRedemptionSellConfirm ? obj.preRedemptionSellConfirm : '' // 償還前売却確認
    this.pointFlg = obj.usingType ? obj.usingType : '' // ポイント利用
    this.pointType = obj.point.pointClass ? obj.point.pointClass : '' // ポイント種別
    this.point = obj.pointInput ? obj.pointInput : ''
    this.distributionReceiveMethodWord = obj.distributionReceivingMethod ? obj.distributionReceivingMethod : '' // 分配金受取方法
    this.mokuromiKoufuKbn = obj.prospectusIssueMethod ? obj.prospectusIssueMethod : '' // 目論見書の交付方法
    this.norikaeYuguKbn = obj.transfersPreferentialQuotaApplicationSelect ? obj.transfersPreferentialQuotaApplicationSelect : '' // 乗換優遇区分
    this.leverageInvestTrust = obj.leverageInvestTrust ? obj.leverageInvestTrust : '' // レバレッジ投資信託
    this.norikaeKanyuKbn = obj.solicitingTransfers ? obj.solicitingTransfers : '' // 乗換勧誘
    this.conflictOfInterestExplain = obj.conflictOfInterestExplain ? obj.conflictOfInterestExplain : '' // 利益相反可能性の説明
    this.checkMokuromi = obj.prospectusSupplementaryDocConfirm ? obj.prospectusSupplementaryDocConfirm : '' // 確認項目.目論見書補完書面の確認
    this.checkMadoAki = obj.windowSpaceFundPrecautionsConsent ? obj.windowSpaceFundPrecautionsConsent : '' // 確認項目.窓空きファンドの注意事項に同意
    this.checkHiyou = obj.costExplained ? obj.costExplained : '' // 確認項目.費用について説明済
    this.checkFukusu = obj.multipleTradeClearlyCommStated ? obj.multipleTradeClearlyCommStated : '' // 確認項目.複数取引業者での手数料等明示済
    this.tradeKbn = obj.mutualFundSellBuyType ? mutualFundSellBuyTypeTrans : '' // 売買区分
    this.shortTermSaleConfirm = obj.shortTermSaleConfirm ? obj.shortTermSaleConfirm : '' // 短期売却確認期間
    this.preRedemptionSellConfirmSelect = obj.preRedemptionSellConfirmSelect ? obj.preRedemptionSellConfirmSelect : '' // 償還前売却確認期間
    this.brandSpecialClassification = obj.brand.brandSpecialClassification ? obj.brand.brandSpecialClassification : ''
    this.dispatchId = obj.dispatchId ? obj.dispatchId : '' // 目論見書チェック区分
  }
}
