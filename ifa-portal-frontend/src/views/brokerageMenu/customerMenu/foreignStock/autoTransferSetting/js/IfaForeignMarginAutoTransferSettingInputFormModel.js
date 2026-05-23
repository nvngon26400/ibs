export class IfaForeignMarginAutoTransferSettingInputFormModel {
  constructor() {
    this.screenTitle = '米株信用自動振替設定入力'
    this.priorityTransferMsg = '※同一項目内で米国株式（保護預り）および米ドル預り金を設定した場合、米国株式（保護預り）から優先して振り替えします。'
    this.marginBuyingPowerShortfallSecurities = ''// 建余力不足.米国株式 【初期値】A001.建余力不足 自動振替設定(米ドル)
    this.marginBuyingPowerShortfallCash = ''// 建余力不足.米ドル 【初期値】A001.建余力不足 自動振替設定(米国株式)
    this.marginShortfallSecurities = '' // 保証金不足.米国株式 【初期値】A001.保証金不足 自動振替設定(米ドル)
    this.marginShortfallCash = '' // 保証金不足.米ドル 【初期値】A001.保証金不足 自動振替設定(米国株式)
    this.depositType = ''// 株式自動振替先設定.振替先 【初期値】A001.現物買付時 株式自動振替先設定

    this.hiddenMarginBuyingPowerShortfallCash = ''// 建余力不足 自動振替設定(米ドル)
    this.hiddenMarginBuyingPowerShortfallSecurities = ''// 建余力不足 自動振替設定(米国株式)
    this.hiddenMarginShortfallCash = ''// 保証金不足 自動振替設定(米ドル)
    this.hiddenMarginShortfallSecurities = '' // 保証金不足 自動振替設定(米国株式)
    this.hiddenAutoTransferSettingInfoDepositType = '' // 現物買付時 株式自動振替先設定
  }
}
