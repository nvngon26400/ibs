export class IfaCommFeeFormModel {
  constructor() {
    this.title = {
      id: 'SUB020502-01',
      name: '手数料・報酬'
    }
    this.chkBrokerCodeExclude = '0' // 仲介業者除外(true: '1', false: '0')
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.displayContent = '0' // 表示内容_入力 【初期値】'0':手数料
    this.dailyMonthlyCountingUnit = '0' // 集計単位(日次/月次)_入力 【初期値】'0':日次
    this.brokerChargeBranchCountingUnit = '0' // 集計単位(仲介業者/営業員/支店)_入力 【初期値】'0':仲介業者毎
    this.aggregatorHandlerCountingUnit = '0' // 集計単位(集計/扱者)_入力 【初期値】'0':集計
    this.periodDate = [] // 期間指定（日次指定）_入力 【初期値】前営業日の日付（From,Toどちらも）
    this.yearMonth = [] // 期間指定（月次指定）_入力 【初期値】当月 （From,Toどちらも
    this.periodAlert = '' // 期間指定_メッセージ 【初期値】"※期間は3ヶ月以内を指定してください。（過去6ヶ月の履歴を照会いただけます。）"
    // 手数料報酬一覧
    this.commFeeList = [
      {
        dateYmd: '', // 手数料報酬一覧.年月日
        dateYm: '', // 手数料報酬一覧.年月
        brokerCode: '', // 手数料報酬一覧.仲介業者コード
        brokerName: '', // 手数料報酬一覧.仲介業者名
        empCode: '', // 手数料報酬一覧.営業員コード
        brokerChargeName: '', // 手数料報酬一覧.営業員名
        dealerNumber: '', // 手数料報酬一覧.扱者コード
        course: '', // 手数料報酬一覧.取引コース
        domesticStockSpot: '', // 手数料報酬一覧.国内株式 現物
        domesticStockMargin: '', // 手数料報酬一覧.国内株式 信用
        domesticStockIpoPo: '', // 手数料報酬一覧.国内株式 IPO・PO
        domesticCb: '', // 手数料報酬一覧.国内CB
        domesticMutualFundSales: '', // 手数料報酬一覧.国内投信 販売
        domesticMutualFundTrustFee: '', // 手数料報酬一覧.国内投信 信報
        foreignMutualFundSales: '', // 手数料報酬一覧.外国投信 販売
        foreignMutualFundTrustFee: '', // 手数料報酬一覧.外国投信 信報
        foreignMutualFundOtherTrustFee: '', // 手数料報酬一覧.外国投信 信報（その他）
        futuresOptions: '', // 手数料報酬一覧.先物・OP
        domesticBond: '', // 手数料報酬一覧.国内債券
        jpyForeignBond: '', // 手数料報酬一覧.外国債券（円建）
        foreignBond: '', // 手数料報酬一覧.外国債券（外貨建）
        foreignStock: '', // 手数料報酬一覧.外国株式
        americaStockMargin: '', // 手数料報酬一覧.米株信用
        st: '', // 手数料報酬一覧.ST
        stSintaku: '', // 手数料報酬一覧.ST信報
        foreignMmfTrustFee: '', // 手数料報酬一覧.外貨建MMF 信報
        fxTrade: '', // 手数料報酬一覧.為替取引
        mutualFundSbiWrapMileage: '', // 手数料報酬一覧.投信（SBIラップ）マイレージ
        mutualFundMileage: '', // 手数料報酬一覧.投信マイレージ
        spotStockPoint: '', // 手数料報酬一覧.現株ポイント
        sbiRapManageFeeNet: '', // 手数料報酬一覧.SBIラップ管理報酬(ネット)
        sbiRapManageFeeStore: '', // 手数料報酬一覧.SBIラップ管理報酬(店頭)
        sbiRapManageFeeTakumi: '', // 手数料報酬一覧.SBIラップ管理報酬(匠)
        sbiRapManageFeeInvestment: '', // 手数料報酬一覧.SBIラップ管理報酬(x投資)
        rewardSubtotal: '', // 手数料報酬一覧.小計
        commTotal: '', // 手数料報酬一覧.手数料合計
        total: '', // 手数料報酬一覧.合計
        consumptionTax: '', // 手数料報酬一覧.消費税
        paymentFeeAmount: '', // 手数料報酬一覧.支払報酬額
        branchCode: '', // 手数料報酬一覧.支店コード
        branchName: '', // 手数料報酬一覧.支店名
        brokerCodeHidden: '', // 手数料報酬一覧.仲介業者コード（hidden）
        dateYmHidden: '' // 手数料報酬一覧.年月（hidden）
      }
    ]
  }
}
