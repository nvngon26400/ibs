export class IfaReleaseNoteDetailFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB00-07_2',
      name: 'リリースノート詳細'
    }
    this.fileDir = '' // ファイルディレクトリ
    this.fileType = '' // ファイルタイプ
    this.pdfSize = '' // PDFサイズ
    this.pdfDirection = '' // PDF向き
    this.detailedFile = '' // 詳細ファイル
    this.MAX_WIDTH = 1850 // 最大幅
    this.DIALOG_PADDING_WIDTH = 50 // ダイアログ幅の余白（パディング）
  }
}
