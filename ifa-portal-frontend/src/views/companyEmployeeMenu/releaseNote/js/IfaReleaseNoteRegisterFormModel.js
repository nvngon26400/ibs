export class IfaReleaseNoteRegisterFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0512-02',
      name: 'リリースノート登録'
    }
    this.title = '' // タイトル
    this.displayedDate = '' // 画面表示開始日
    this.contentItemList = [{ // 内容一覧
      menuName: '', // メニュー名
      screenName: '', // 画面名
      content: '' // 内容
    }]
    this.fileType = '0' // ファイルタイプ
    this.pdfSize = '' // PDFサイズ
    this.pdfDirection = '' // 向き
    this.detailedFileName = '' // 詳細ファイル名
    this.detailedFile = null // ファイルアップロード用
    this.initDisplayCount = 5 // 内容リスト初期データ件数
    this.maxDisplayCount = 99 // 内容リスト最大データ件数
    this.maxLengthMenuName = 50 // 内容エリア.内容.メニュー名最大長さ
    this.maxLengthScreenName = 50 // 内容エリア.内容.画面名最大長さ
    this.maxLengthContent = 255 // 内容エリア.内容.内容最大長さ
  }
}
