export class IfaAuthMailAddressChangeListFormModel {
  constructor() {
    this.loginId = '' // ログインID 【初期値】ブランク
    this.branchNameBrokerName = '' // 支店名/仲介業者名 【初期値】ブランク
    this.employeeNameChargeName = '' // 社員名/担当者名 【初期値】ブランク
    this.userId = '' // 認証用メールアドレス一覧.ログインID
    this.branchName = '' // 認証用メールアドレス一覧.支店名
    this.brokerName = '' // 認証用メールアドレス一覧.仲介業者名
    this.userName = '' // 認証用メールアドレス一覧.社員名/担当者名
    this.mailAddress = '' // 認証用メールアドレス一覧.認証用メールアドレス
  }
}
