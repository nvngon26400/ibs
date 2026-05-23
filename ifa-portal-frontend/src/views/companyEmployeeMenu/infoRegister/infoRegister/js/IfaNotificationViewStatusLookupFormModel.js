export class IfaNotificationViewStatusLookupFormModel {
  constructor() {
    this.infoTitle = '' // 情報タイトル
    this.infoRegisterDate = '' // 情報登録日
    // お知らせ閲覧状況照会
    this.NotificationViewStatusLookup = [
      {
        brokerName: '', // お知らせ閲覧状況照会.仲介業者名
        employeeName: '', // お知らせ閲覧状況照会.担当者
        viewStatus: '', // お知らせ閲覧状況照会.閲覧状況
        readDate: '' // お知らせ閲覧状況照会.閲覧日
      }
    ]
    this.notificationId = '' // お知らせID（hidden）
    this.corReferenceCondition = '' // 参照範囲（hidden）
    this.loginId = '' // ログインID（hidden）
  }
}
