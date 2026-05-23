export class IfaInfoRegisterViewerSettingFormModel {
  constructor() {
    this.corReferenceCondition = '' // 参照範囲
    this.selectedAuthority = [] // 権限選択
    this.registeredNotificationReferenceAuthorityList = [] // 登録済お知らせ参照権限リスト
    this.brokerCodeFuzzySearch = '' // 仲介業者（検索）
    this.broker = [] // 仲介業者
    this.brokerSelect = [] // 仲介業者選択
    this.brokerClear = false // 仲介業者クリア 【初期値】選択以外クリア
    this.repSearch = '' // 担当者（検索）
    this.rep = [] // 担当者
    this.individualRepList = [] // 個別担当者リスト
    this.selectedManager = [] // 担当者選択
    this.registeredIndividualRepList = [] // 登録済個別担当者リスト
    this.repClear = false // 担当者クリア 【初期値】選択以外クリア
    this.notificationId = '' // お知らせID
    this.viewerSetting = '' // 閲覧者

    this.checkedBroker = [] // チェック済み仲介業者（検索）
    this.checkedBrokerBuf = [] // チェック済み仲介業者（検索）のバッファ
    this.repList = [] // 担当者一覧表示で取得する担当者
    this.checkedRep = [] // チェック済み担当者（検索）
    this.checkedRepBuf = [] // チェック済み担当者（検索）のバッファ
    this.settingFlag = false // 設定フラグ
    this.isUpdate = false // Information更新からの遷移フラグ
    this.repSelectList = [] // 個別担当者リスト（表示用）
  }
}
