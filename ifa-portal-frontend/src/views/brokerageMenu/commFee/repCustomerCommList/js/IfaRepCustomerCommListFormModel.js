export class IfaRepCustomerCommListFormModel {
  constructor() {
    this.title = {
      id: 'SUB020501-01',
      name: '担当顧客別手数料一覧'
    }
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索オプション
    // 取引コース
    this.courseSelected = [{
      id: '',
      isSelected: ''
    }]
    this.period = [] // 期間指定 【初期値】当月
    this.periodYmFrom = '' // 期間指定_入力 【初期値】当日日付 （From,Toどちらも）
    this.periodYmTo = ''
    this.spotStockPointReferenceAbleList = [] // 現株ポイント参照可能リスト
    this.repCustomerCommComment = '' // 担当顧客別手数料コメント
    this.chargeCustomerCountingUnit = '1' // 集計単位_入力 【初期値】'1':顧客毎
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    // 証券種別
    this.securityClass = [{
      id: '',
      isSelected: ''
    }]
    // 担当顧客別手数料一覧
    this.repCustomerCommList = [
      {
        chargeCustomerCountingUnit: '', // 担当顧客別手数料一覧.集計単位
        brokerCode: '', // 担当顧客別手数料一覧.仲介業者コード
        brokerName: '', // 担当顧客別手数料一覧.仲介業者名
        empCode: '', // 担当顧客別手数料一覧.営業員コード
        brokerChargeName: '', // 担当顧客別手数料一覧.営業員名
        brokerageBranchCode: '', // 担当顧客別手数料一覧.仲介業者支店コード
        brokerBranchName: '', // 担当顧客別手数料一覧.仲介業者支店名
        commTotal: '', // 担当顧客別手数料一覧.手数料/合計(税抜き)
        butenCode: '', // 担当顧客別手数料一覧.部店
        accountNumber: '', // 担当顧客別手数料一覧.口座番号
        customerNameKanji: '', // 担当顧客別手数料一覧.顧客名(漢字)
        customerNameKana: '', // 担当顧客別手数料一覧.顧客名(カナ)
        dealerNumber: '', // 担当顧客別手数料一覧.扱者コード
        courseName: '' // 担当顧客別手数料一覧.取引コース
      }
    ]
  }
}
