export class IfaPersonalInfoManageLedgerListFormModel {
  constructor() {
    this.screenId = 'SUB0403-01' // 画面ID
    this.title = '個人情報管理台帳一覧' // 画面タイトル
    this.explainWord = '' // 説明文言
    this.processTargetPeriod = [] // 処理対象期間 【初期値】Fromは当日の3ヶ月前の日付+1 Toは当日の日付
    this.periodAlert = '' // 期間指定メッセージ
    this.internalAdmin = '' // 内部管理責任者
    this.finishingWording = '' // 完了文言
    this.fileDirectory = ''// ファイルディレクトリ
    // 個人情報管理台帳リスト
    this.personalInfoManageLedgerListInfo = [{
      processDayTime: '', // 個人情報管理台帳リスト.処理日時
      name: '', // 個人情報管理台帳リスト.名前
      adress: '', // 個人情報管理台帳リスト.住所
      tel: '', // 個人情報管理台帳リスト.TEL
      office: '', // 個人情報管理台帳リスト.勤務先
      corBirthFlg: '', // 個人情報管理台帳リスト.生年月日
      gender: '', // 個人情報管理台帳リスト.性別
      assets: '', // 個人情報管理台帳リスト.資産
      profession: '', // 個人情報管理台帳リスト.職業
      butenAccount: '', // 個人情報管理台帳リスト.部店口座
      email: '', // 個人情報管理台帳リスト.Ｅメール
      withdrawAccount: '', // 個人情報管理台帳リスト.出金口座
      docNameFileName: '', // 個人情報管理台帳リスト.書類名・ファイル名
      acquireDataCustomerCount: '', // 個人情報管理台帳リスト.取得データ顧客数
      processContent: '', // 個人情報管理台帳リスト.処理内容
      personalInfoAcquire: '', // 個人情報管理台帳リスト.個人情報取得者
      loginId: '', // 個人情報管理台帳リスト.ログインID
      chargeName: '', // 個人情報管理台帳リスト.営業員名
      storageSendingMedium: '', // 個人情報管理台帳リスト.保管/送付媒体
      depositDestinations: '', // 個人情報管理台帳リスト.預託先
      destination: '', // 個人情報管理台帳リスト.提供先
      storageSpace: '', // 個人情報管理台帳リスト.保管場所
      preservePeriod: '', // 個人情報管理台帳リスト.保管期間
      disposeMethod: '', // 個人情報管理台帳リスト.廃棄方法
      notDispose: '', // 個人情報管理台帳リスト.破棄しない
      disposeDateYmd: '', // 個人情報管理台帳リスト.廃棄した年月日
      corDepositOutline: '', // 個人情報管理台帳リスト.摘要(預託先詳細)
      corDonationOutline: '', // 個人情報管理台帳リスト.摘要(提供先詳細)
      corDepositoryOutline: '', // 個人情報管理台帳リスト.摘要(保管場所詳細)
      infoUpdateCheckBox: false, // 個人情報管理台帳リスト.情報更新チェックボックス 【初期値】チェックオフ
      personalInfoManageId: '' // 個人情報管理台帳リスト.個人情報管理ID
    }]
    this.personalInfoManageLedger = []
  }
}
