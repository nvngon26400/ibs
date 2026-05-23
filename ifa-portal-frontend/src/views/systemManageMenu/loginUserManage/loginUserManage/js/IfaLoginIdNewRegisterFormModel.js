export class IfaLoginIdNewRegisterFormModel {
  constructor() {
    this.title = 'ログインID新規登録' // タイトル
    this.loginId = '' // ログインID
    this.password = '' // パスワード
    this.userName = '' // ユーザ名
    this.mailAddress = '' // メールアドレス
    this.privId = '' // 所属権限
    this.branchCode = '' // 本店／支店名（コード）
    this.headOfficeBranchNameList = [] // 本店／支店名リスト
    this.brokerCode = '' // 仲介業者名（コード）
    this.brokerNameList = [] // 仲介業者名リスト
    this.subBrokerId = '' // 仲介業者支店名（コード）
    this.branchNameList = [] // 仲介業者支店名リスト
    this.employeeId = '' // 担当者名（コード）
    this.chargeName = '' // 担当者名(A011で送信する担当者名)
    this.chargeNameList = [] // 担当者名リスト
    this.unDisplayAllCheck = '' // 非表示全チェック
    this.unDisplayList = [] // 非表示
    this.displayAllCheck = '' // 表示全チェック
    this.displayList = [] // 表示
    this.menuList = [] // 変更前.メニューリスト
  }
}
