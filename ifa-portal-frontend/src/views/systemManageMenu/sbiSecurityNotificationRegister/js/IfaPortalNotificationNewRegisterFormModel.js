export class IfaPortalNotificationNewRegisterFormModel {
  constructor() {
    this.displayPeriodFrom = '' // 表示期間From 【初期値】未入力
    this.displayPeriodTo = '' // 表示期間To 【初期値】未入力
    this.notificationContent = '' // お知らせ内容 【初期値】未入力
    this.inputWordCount = '' // 入力文字数
    this.linkAddress = '' // リンクアドレス 【初期値】未入力
    this.importantNotification = '' // 重要なお知らせ 【初期値】false:対象外
    this.nonDisplay = '' // 非表示 【初期値】false:対象外
    this.appearPeriod = []
    this.period = []
  }
}
