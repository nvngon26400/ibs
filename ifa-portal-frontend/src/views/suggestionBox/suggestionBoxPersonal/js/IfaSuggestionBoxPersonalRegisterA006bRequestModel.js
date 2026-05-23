import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalRegisterA006bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.sbpNo = obj.sbpNo ? obj.sbpNo : '' // あなたの要望No
    this.title = obj.title ? obj.title : '' // タイトル
    this.category = obj.category ? obj.category : '' // カテゴリ
    this.suggestion = obj.suggestion ? obj.suggestion : '' // 要望内容
    this.registerFileName1 = obj.registerFileName1 ? obj.registerFileName1 : '' // 登録済添付ファイル1
    this.registerFileName2 = obj.registerFileName2 ? obj.registerFileName2 : '' // 登録済添付ファイル2
    this.registerFileName3 = obj.registerFileName3 ? obj.registerFileName3 : '' // 登録済添付ファイル3
  }
}
