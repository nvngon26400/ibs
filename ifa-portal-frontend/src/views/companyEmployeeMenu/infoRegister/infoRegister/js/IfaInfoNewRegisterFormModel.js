export class IfaInfoNewRegisterFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0501_01-02_1',
      name: 'インフォメーション登録'
    }
    this.viewerSetting = '' // 閲覧者
    this.title = '' // タイトル
    this.categoryIdList = [] // カテゴリIDリスト
    this.content = '' // 内容
    this.url = '' // URL
    this.urlComment = '' // URL（コメント）
    this.registerFile1 = '' // 登録ファイル1
    this.fileComment1 = '' // 登録ファイル1（コメント）
    this.registerFile2 = '' // 登録ファイル2
    this.fileComment2 = '' // 登録ファイル2（コメント）
    this.registerFile3 = '' // 登録ファイル3
    this.fileComment3 = '' // 登録ファイル3（コメント）
    this.disclaimer = '' // ディスクレーマー 【初期値】要
    this.readManage = '' // 既読管理 【初期値】要
    this.subjectSendFlag = '' // メール送信 【初期値】送信しない
    this.subject = '' // 件名
    this.notificationReferenceAuthorityList = [] // お知らせ参照権限リスト
    this.individualRepList = [] // 個別担当者リスト
    this.settingFlag = false // 設定フラグ 【初期値】FALSE

    // 画面内
    this.largeCategory = '' // 大カテゴリ 【初期値】"-1":""
    this.repSelectList = [] // 担当者リスト（情報登録閲覧者設定　再表示用）

    // ファイルアップロード用
    this.attachFileList = [null, null, null]
  }
}
