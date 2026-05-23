export class IfaLoginIdUpdateRegisterFormModel {
  constructor() {
    this.title = 'ログインID更新登録' // タイトル
    this.loginId = '' // ログインID
    this.password = '' // パスワード
    this.userName = '' // ユーザ名
    this.mailAddress = '' // メールアドレス
    this.ccsId = '' // CCSID
    this.ccsPassword = '' // CCS PW
    this.privId = '' // 所属権限
    this.branchCode = '' // 本店／支店名
    this.headOfficeBranchNameList = [] // 本店／支店名リスト
    this.brokerCode = '' // 仲介業者名
    this.brokerNameList = [] // 仲介業者名リスト
    this.subBrokerId = '' // 仲介業者支店名
    this.branchNameList = [] // 仲介業者支店名リスト
    this.chargeName = '' // 担当者名
    this.chargeNameList = [] // 担当者名リスト
    this.unDisplayAllCheck = '' // 非表示全チェック
    this.unDisplayList = [] // 非表示
    this.displayAllCheck = '' // 表示全チェック
    this.displayList = [] // 表示
    this.repNumber = '' // 担当者数
    this.beforeLoginId = '' // 変更前.ログインID
    this.beforePrivId = '' // 変更前.権限コード
    this.beforeBranchCode = '' // 変更前.本支店コード
    this.beforeBrokerCode = '' // 変更前.仲介業者コード
    this.beforeSubBrokerId = '' // 変更前.仲介業者支店コード
    this.beforeEmployeeId = '' // 変更前.仲介業者担当者コード
    this.beforeUserName = '' // 変更前.社員名担当者名
    this.menuTitle = '' // 変更前.メニュータイトル
    this.menuId = '' // 変更前.メニューコード
  }
}
