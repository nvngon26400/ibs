import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalDetailA007bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.title = obj.title ? obj.title : '' // タイトル
    this.category = obj.category ? obj.category : '' // カテゴリ
    this.suggestion = obj.suggestion ? obj.suggestion : '' // 要望内容
    this.sbpNo = obj.sbpNo ? obj.sbpNo : '' // あなたの要望No
    this.registeredAttachFile1 = obj.registeredAttachFile1 ? obj.registeredAttachFile1 : '' // 登録済添付ファイル1
    this.registeredAttachFile2 = obj.registeredAttachFile2 ? obj.registeredAttachFile2 : '' // 登録済添付ファイル2
    this.registeredAttachFile3 = obj.registeredAttachFile3 ? obj.registeredAttachFile3 : '' // 登録済添付ファイル3
    this.registeredAttachFile1DeleteFlag = obj.registeredAttachFile1DeleteFlag ? obj.registeredAttachFile1DeleteFlag : '' // 登録済添付ファイル1削除フラグ
    this.registeredAttachFile2DeleteFlag = obj.registeredAttachFile2DeleteFlag ? obj.registeredAttachFile2DeleteFlag : '' // 登録済添付ファイル2削除フラグ
    this.registeredAttachFile3DeleteFlag = obj.registeredAttachFile3DeleteFlag ? obj.registeredAttachFile3DeleteFlag : '' // 登録済添付ファイル3削除フラグ
    this.registerFileName1 = obj.registerFileName1 ? obj.registerFileName1 : '' // 添付ファイル1
    this.registerFileName2 = obj.registerFileName2 ? obj.registerFileName2 : '' // 添付ファイル2
    this.registerFileName3 = obj.registerFileName3 ? obj.registerFileName3 : '' // 添付ファイル3
  }
}
