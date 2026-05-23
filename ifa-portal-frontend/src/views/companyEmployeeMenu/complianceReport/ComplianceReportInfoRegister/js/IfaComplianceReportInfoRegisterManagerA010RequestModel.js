import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportInfoRegisterManagerA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.functionId = obj.functionId ? obj.functionId : '' // 機能ID
    this.t9nInfoCat = obj.t9nInfoCat ? obj.t9nInfoCat : '' // カテゴリID
    this.lectureId = obj.lectureId ? obj.lectureId : '' // LECTURE_ID
    // エラー: 「ファイル名1」が、項目辞書に存在しません。
    // エラー: 「ファイル名2」が、項目辞書に存在しません。
    // エラー: 「ファイル名3」が、項目辞書に存在しません。
    // エラー: 「コンテンツファイル名」が、項目辞書に存在しません。
  }
}
