export class IfaInfoDetailFormModel {
  constructor() {
    this.title = {
      id: 'SUB01-03',
      name: 'Information詳細'
    }
    this.title = '' // タイトル
    this.contents = '' // 内容
    this.url = '' // URL
    this.urlComment // URLコメント
    this.attachDocument1 = '' // 添付資料（添付ファイル１）
    this.attachDocumentComment1 = '' // コメント（添付ファイル１）
    this.attachDocument2 = '' // 添付資料（添付ファイル２）
    this.attachDocumentComment2 = '' // コメント（添付ファイル２）
    this.attachDocument3 = '' // 添付資料（添付ファイル３）
    this.attachDocumentComment3 = '' // コメント（添付ファイル３）
    this.disclaimer = '' // ディスクレーマー
    this.fileDirectory = '' // ファイルディレクトリ
  }
}
