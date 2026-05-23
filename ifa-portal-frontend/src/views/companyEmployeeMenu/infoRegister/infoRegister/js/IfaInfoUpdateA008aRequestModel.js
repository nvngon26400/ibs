import Logger from '@/utils/ifaLog.js'
export class IfaInfoUpdateA008aRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerFile1 = obj.registerFile1 ? obj.registerFile1 : '' // 登録ファイル1
    this.registerFileName1 = obj.registerFileName1 ? obj.registerFileName1 : '' // 登録ファイル1（ファイル名）
    this.registerFile2 = obj.registerFile2 ? obj.registerFile2 : '' // 登録ファイル2
    this.registerFileName2 = obj.registerFileName2 ? obj.registerFileName2 : '' // 登録ファイル2（ファイル名）
    this.registerFile3 = obj.registerFile3 ? obj.registerFile3 : '' // 登録ファイル3
    this.registerFileName3 = obj.registerFileName3 ? obj.registerFileName3 : '' // 登録ファイル3（ファイル名）
  }
}
