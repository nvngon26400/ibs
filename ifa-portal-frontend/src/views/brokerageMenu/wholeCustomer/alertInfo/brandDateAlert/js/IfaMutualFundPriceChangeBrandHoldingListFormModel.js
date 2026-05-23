export class IfaMutualFundPriceChangeBrandHoldingListFormModel {
  constructor() {
    this.title = {
      id: 'SUB020301_03-01',
      name: '投信基準価額変動の銘柄保有一覧'
    }

    // 検索条件
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // 取引コース
    this.compliantStatusType = '$NULL' // ステータス_入力 【初期値】"NULL：(未設定)"
    this.investmentTrustAssociationCode = '' // 投信協会コード_入力 【初期値】""
    this.dateRange = [] //  期間指定_入力 【初期値】前営業日の日付（From,Toどちらも）
    this.periodMsg = '※期間は6ヶ月以内を指定してください。１ヶ月10%下落銘柄一覧の検索を期間指定で行う場合、基準日（Ｔ）が日付の範囲内となるように指定してください。' // 期間指定_メッセージ
    this.periodAlert = '※投信委託会社作成レポートが提供された場合、速やかに顧客へレポート内容をご連携ください。' // 対応期日固定文言

    // 前日比
    this.pqGrid5perSelectedInfo = {
      declineRateKbn: '', // 前日比_下落率区分（hidden）
      butenCode: '', // 前日比_部店コード（hidden）
      accountNumber: '', // 前日比_口座番号（hidden）
      investmentTrustAssociationCode: '', // 前日比_投信協会コード（hidden）
      standardDateTo: '' // 前日比_基準日（To）（hidden）
    }
  }
}
