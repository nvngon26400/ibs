export class IfaPortalNotificationManagerLookupFormModel {
  constructor() {
    this.searchDateYmdFrom = '' // 検索年月日(From)_入力 【初期値】当日の日付
    this.searchDateYmdTo = '' // 検索年月日(To)_入力 【初期値】当日の日付
    this.pastDateExcrude = true // 過去日除外_入力 【初期値】true:除外する
    this.notificationId = ''
    // SBI証券からのご連絡一覧
    this.sbiSecurityNotificationList = [
      {
        registerDayTime: '', // SBI証券からのご連絡一覧.登録日時
        displayPeriodFrom: '', // SBI証券からのご連絡一覧.表示期間(From)
        displayPeriodTo: '', // SBI証券からのご連絡一覧.表示期間(To)
        notificationContent: '', // SBI証券からのご連絡一覧.ご連絡内容
        important: '', // SBI証券からのご連絡一覧.重要
        displayUndisplay: '', // SBI証券からのご連絡一覧.表示/非表示
        notificationId: '' // SBI証券からのご連絡一覧.お知らせID（hidden）
      }
    ]
    this.period = []
  }
}
