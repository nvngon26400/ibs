export class IfaSugBoxCommonUpdateFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0511_02-03',
      name: '皆様からの要望更新'
    }
    this.title = '' // タイトル
    this.category = '' // カテゴリ
    this.status = '' // ステータス
    this.suggestion = '' // 要望内容
    this.sbcNo = '' // 皆様からの要望No
    this.attachFile1 = '' // 添付ファイル1
    this.attachFile2 = '' // 添付ファイル2
    this.attachFile3 = '' // 添付ファイル3
    this.answerList = [] // 回答一覧
    this.registeredAttachFile1 = '' // 登録済添付ファイル1
    this.registeredAttachFile2 = '' // 登録済添付ファイル2
    this.registeredAttachFile3 = '' // 登録済添付ファイル3
    this.registeredAttachFile1DeleteFlag = '0' // 登録済添付ファイル1削除フラグ
    this.registeredAttachFile2DeleteFlag = '0' // 登録済添付ファイル2削除フラグ
    this.registeredAttachFile3DeleteFlag = '0' // 登録済添付ファイル3削除フラグ

    this.registerFileName1 = '' // リネーム後ファイル名1（A009aレスポンス）
    this.registerFileName2 = '' // リネーム後ファイル名2（A009aレスポンス）
    this.registerFileName3 = '' // リネーム後ファイル名3（A009aレスポンス）

    this.attachFileName1 = '' // 添付ファイル名1（環境依存文字チェック用）
    this.attachFileName2 = '' // 添付ファイル名2（環境依存文字チェック用）
    this.attachFileName3 = '' // 添付ファイル名3（環境依存文字チェック用）
    
    // 添付ファイル（ファイルの実態）
    this.attachFileList = [null, null, null]
  }
}
