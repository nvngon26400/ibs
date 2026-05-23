export class IfaComplianceReportFormModel {
  constructor() {
    this.complianceReporTitle = {
      id: 'SUB0302-01',
      name: 'コンプライアンス通信'
    }
    this.slctYmList = [] // コンプライアンス通信リスト reportList
    this.slctYm = '' // 表示年月 【初期値】レスポンス.表示年月 // assignMonth
    this.corCommunicationDate = '' // 通信年月 // reportYearMonth
    this.lecId = '' // LECTURE_ID
    this.fileDir = '' // ファイルディレクトリ
    this.corTitle = '' // タイトル
    this.corBrief = '' // 概要 // overview
    this.warningMessage = '' // 注意文言 【初期値】※「コンプライアンス通信　コンテンツ」をクリックすると、本文ＰＤＦファイルが表示されます。 内容をご確認後、ファイルを閉じたあとに「確認しました」ボタンが活性化されますので、 必ずボタンを押下してください。（ボタン押下後はボタンの表示が「確認済み」と変わります）
    this.corContentsFileName = '' // コンテンツファイル名
    this.confirmFlg = '' // 確認フラグ
    this.corBrowseFlagInd = '' // 個別閲覧要否フラグ
    this.corBrowseFlagBul = '' // 一括閲覧要否フラグ
    this.finishMassage = '' // 完了文言 【初期値】”コンプライアンス通信の確認、お疲れ様でした。"
    this.confirmBtn = '' // 確認ボタン //confirmButton
    this.directory = '' // ファイルパス
    this.corFileDesc1 = ''
    this.corFileDesc2 = ''
    this.corFileDesc3 = ''
    this.corContents = ''
  }
}
