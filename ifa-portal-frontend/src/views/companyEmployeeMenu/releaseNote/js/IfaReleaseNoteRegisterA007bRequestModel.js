import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteRegisterA007bRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.releaseNoteNo = obj.releaseNoteNo ? obj.releaseNoteNo : '' // リリースノートNo
    this.title = obj.title ? obj.title : '' // タイトル
    this.displayedDate = obj.displayedDate ? obj.displayedDate : '' // 画面表示開始日
    this.fileType = obj.fileType ? obj.fileType : '' // ファイルタイプ
    this.contentItemList = obj.contentItemList ? obj.contentItemList : '' // 内容一覧
    this.pdfSize = obj.pdfSize ? obj.pdfSize : '' // PDFサイズ
    this.pdfDirection = obj.pdfDirection ? obj.pdfDirection : '' // PDF向き
    this.detailedFileName = obj.detailedFileName ? obj.detailedFileName : '' // 詳細ファイル名
  }
}
