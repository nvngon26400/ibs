export class IfaForeignMarginAutoTransferSettingConfirmFormModel {
  constructor() {
    this.priorityTransferMsg = '' // 優先振替メッセージ
    this.multipleSelectAble = ''// 建余力不足.複数選択可
    this.reservePowerDeficientExplainWord = '' // 建余力不足.説明文
    this.marginBuyingPowerShortfallSecurities = '' // 建余力不足.米国株式 【初期値】A001.建余力不足 自動振替設定(米ドル)
    this.marginBuyingPowerShortfallCash = '' // 建余力不足.米ドル 【初期値】A001.建余力不足 自動振替設定(米国株式)
    this.multipleSelectAble = '' // 保証金不足.複数選択可
    this.explainWord = '' // 保証金不足.説明文
    this.marginShortfallSecurities = '' // 保証金不足.米国株式 【初期値】A001.保証金不足 自動振替設定(米ドル)
    this.marginShortfallCash = '' // 保証金不足.米ドル 【初期値】A001.保証金不足 自動振替設定(米国株式)
    this.stockAutoTransferExplainWordSetting = '' // 株式自動振替先設定.説明文
    this.depositType = ''// 株式自動振替先設定.振替先 【初期値】A001.現物買付時 株式自動振替先設定

    this.hiddenMarginBuyingPowerShortfallCash = ''// 建余力不足 自動振替設定(米ドル)
    this.hiddenMarginBuyingPowerShortfallSecurities = ''// 建余力不足 自動振替設定(米国株式)
    this.hiddenMarginShortfallCash = ''// 保証金不足 自動振替設定(米ドル)
    this.hiddenMarginShortfallSecurities = '' // 保証金不足 自動振替設定(米国株式)
    this.hiddenAutoTransferSettingInfoDepositType = '' // 現物買付時 株式自動振替先設定
  }
}
