export class IfaPayNotificationDocDownloadFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0405-01',
      name: '支払通知書ダウンロード'
    }
    this.brokerCode = '' //  仲介業者コード
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.dateYmRange = [] // 対象年月 【初期値】Fromは3ヶ月前の年月、 Toは前月の年月
    this.periodAlert = '※期間は2年以内を指定してください。（2年前から前月までを照会いただけます。）' // 期間指定メッセージ

    this.fileDirectory = '' // ファイルディレクトリ

    this.brokerPayNotificationDocInfoDateList = [ // 支払通知書一覧
      // {
      // dateYm: '', // 年月
      // brokerCode: '', // 仲介業者コード
      // brokerName: '', // 仲介業者名
      // firstConfirmDateTime: '', // 初回確認日時
      // lastConfirmDateTime: '', // 最終確認日時
      // dl: '', // DL
      // versionNumber: '' // バージョン番号
      // }
    ]
    this.beforeSearchTargetDateYmFrom = '' // 前回検索時の対象年月From
    this.beforeSearchTargetDateYmTo = '' // 前回検索時の対象年月To
    this.beforeSearchBrokerCode = '' // 前回検索時の仲介業者コード
    this.beforeSearchChkBrokerCodeExclude = '' // 前回検索時の仲介業者除外
  }
}
