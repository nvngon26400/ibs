import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxCommonRegisterA007bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.title = obj.title ? obj.title : '' // タイトル
    this.category = obj.category ? obj.category : '' // カテゴリ
    this.status = obj.status ? obj.status : '' // ステータス
    this.suggestion = obj.suggestion ? obj.suggestion : '' // 要望内容
    this.sbcNo = obj.sbcNo ? obj.sbcNo : '' // 皆様からの要望No
    this.registerFileName1 = obj.registerFileName1 ? obj.registerFileName1 : '' // 登録済添付ファイル1
    this.registerFileName2 = obj.registerFileName2 ? obj.registerFileName2 : '' // 登録済添付ファイル2
    this.registerFileName3 = obj.registerFileName3 ? obj.registerFileName3 : '' // 登録済添付ファイル3

    this.answerList = obj.answerList ? obj.answerList : [] // 回答一覧
  }
}

