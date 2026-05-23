export class IfaLevelFeeFormModel {
  constructor() {
    this.title = {
      id: 'SUB020505-01',
      name: 'レベルフィー'
    }
    this.levelFeeComment = '' // 残高連動手数料コメント

    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = '0' // 仲介業者除外(true: '1', false: '0')
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.dailyMonthlyCountingUnit = '0' // 集計単位(日次/月次)_入力 【初期値】'0':日次
    this.brokerChargeBranchCountingUnit = '0' // 集計単位(仲介業者/営業員/支店)_入力 【初期値】'0':仲介業者毎
    this.periodDate = [] // 期間指定（日次指定）_入力 【初期値】前営業日の日付（From,Toどちらも）
    this.yearMonth = [] // 期間指定（月次指定）_入力 【初期値】当月 （From,Toどちらも）
    
    // 残高連動手数料一覧
    this.levelFeeList = [
      {
        dateYmd: '', // 残高連動手数料一覧.年月日
        dateYm: '', // 残高連動手数料一覧.年月
        brokerCode: '', // 残高連動手数料一覧.仲介業者コード
        brokerName: '', // 残高連動手数料一覧.仲介業者名
        empCode: '', // 残高連動手数料一覧.営業員コード
        brokerChargeName: '', // 残高連動手数料一覧.営業員名
        butenCode: '', // 残高連動手数料一覧.部店コード
        accountNumber: '', // 残高連動手数料一覧.口座番号
        customerNameKanji: '', // 残高連動手数料一覧.顧客名_姓名(漢字)
        customerNameKana: '', // 残高連動手数料一覧.顧客名_姓名(カナ)
        dealerNumber: '', // 残高連動手数料一覧.扱者コード
        valuationTotalJpyAmount: '', // 残高連動手数料一覧.計算対象残高
        fee: '', // 残高連動手数料一覧.徴収額(税抜)
        branchCode: '', // 残高連動手数料一覧.支店コード
        branchName: '' // 残高連動手数料一覧.支店名
      }
    ]
  }
}
