export class IfaInfoUpdateFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0501_01-03_1',
      name: 'インフォメーション更新'
    }
    this.notificationCategoryList = [] // お知らせカテゴリリスト
    this.documentKindInput1 = '' // 資料種別1
    this.updateTime = '' // 更新日時
    this.title = '' // タイトル
    this.createBy = '' // 登録者
    this.updateUser = '' // 更新者
    this.registerDayTime = '' // 登録日時
    this.contents = '' // 内容
    this.attachFileComment1 = '' // 添付ファイルコメント1
    this.attachFileComment2 = '' // 添付ファイルコメント2
    this.attachFileComment3 = '' // 添付ファイルコメント3
    this.url = '' // URL
    this.urlComment = '' // URL（コメント）
    this.corReferenceCondition = '' // 参照範囲
    this.disclaimer = '' // ディスクレーマー
    this.corReadFlg = '' // 既読管理フラグ
    this.attachFileName1 = '' // 添付ファイル名1
    this.attachFileName2 = '' // 添付ファイル名2
    this.attachFileName3 = '' // 添付ファイル名3
    this.individualRepList = [
      // {
      //   userId: '', // 個別担当者リスト.ユーザーID
      //   userName: '' // 個別担当者リスト.仲介業者担当者名
      // }
    ] // 個別担当者リスト　・　hidden項目
    this.notificationReferenceAuthorityList = [] // お知らせ参照権限リスト　・　hidden項目

    this.largeCategory = '' // 大カテゴリ
    this.contentsLength = '' // 内容_桁数
    this.mailSend = false // メール送信 【初期値】送信しない
    this.t9nName = '' // お知らせカテゴリリスト.カテゴリ名
    this.requiredFlag = '' // 必須フラグ
    // fileListの初期設定（3つファイルをアップロードする場合）
    this.attachFileList = [null, null, null]

    this.notificationId = '' // お知らせID
    this.importantCheckbox = '' // 重要
    this.viewerSetting = '' // 閲覧者
    this.categoryIdList = '' // カテゴリIDリスト
    this.registerFileName1 = '' // 登録ファイル1（ファイル名）
    this.fileDelete1 = '' // 登録ファイル1（削除）
    this.fileComment1 = '' // 登録ファイル1（コメント）
    this.registerFileName2 = '' // 登録ファイル2（ファイル名）
    this.fileDelete2 = '' // 登録ファイル2（削除）
    this.fileComment2 = '' // 登録ファイル2（コメント）
    this.registerFileName3 = '' // 登録ファイル3（ファイル名）
    this.fileDelete3 = '' // 登録ファイル3（削除）
    this.fileComment3 = '' // 登録ファイル3（コメント）
    this.readManage = '' // 既読管理
    this.t5nReadFlg = '0' // 既読フラグ
    this.registerFile1 = '' // 登録ファイル1
    this.registerFile2 = '' // 登録ファイル2
    this.registerFile3 = '' // 登録ファイル3
    this.fileDeleteName1 = '' // 登録ファイル1名（削除）
    this.fileDeleteName2 = '' // 登録ファイル2名（削除）
    this.fileDeleteName3 = '' // 登録ファイル3名（削除）
    this.fileDirectory = '' // fileDirectory

    this.settingFlag = false // 設定フラグ 【初期値】FALSE
    this.repSelectList = [] // 担当者リスト（情報登録閲覧者設定　再表示用）
  }
}
