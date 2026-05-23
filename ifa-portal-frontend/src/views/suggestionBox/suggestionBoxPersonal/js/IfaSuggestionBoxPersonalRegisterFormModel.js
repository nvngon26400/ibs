export class IfaSuggestionBoxPersonalRegisterFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB00_01-06_2',
      name: '要望事項 新規登録'
    }
    this.title = '' // タイトル
    this.category = '' // カテゴリ
    this.suggestion = '' // 要望内容
    this.sbpNo = '' // あなたの要望No
    this.attachFile1 = '' // 添付ファイル1
    this.attachFile2 = '' // 添付ファイル2
    this.attachFile3 = '' // 添付ファイル3

    this.registerFileName1 = '' // リネーム後ファイル名1（A006aレスポンス）
    this.registerFileName2 = '' // リネーム後ファイル名2（A006aレスポンス）
    this.registerFileName3 = '' // リネーム後ファイル名3（A006aレスポンス）

    this.attachFileName1 = '' // 添付ファイル名1（環境依存文字チェック用）
    this.attachFileName2 = '' // 添付ファイル名2（環境依存文字チェック用）
    this.attachFileName3 = '' // 添付ファイル名3（環境依存文字チェック用）

    // 添付ファイル（ファイルの実態）
    this.attachFileList = [null, null, null]
  }
}
