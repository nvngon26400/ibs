export class IfaWithdrawExecuteConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0601-02_1' // 画面ID
    this.title = '出金確認' // 画面名
    this.payDate = '' // 出金日
    this.payAmount = '' // 出金額
    this.acBalance = '' // 受付前出金可能金額
    this.bankKanji = '' // 振込先金融機関
    this.bankInfo = '' // 振込先金融機関
    this.noticeInfoAlert = '' // 注意情報アラート
    this.noticeAlert = '' // お知らせアラート
  }
}
