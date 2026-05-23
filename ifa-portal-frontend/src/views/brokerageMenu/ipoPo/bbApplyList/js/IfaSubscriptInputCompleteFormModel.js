export class IfaSubscriptInputCompleteFormModel {
  constructor() {
    this.title = {
      id: 'SUB0204_02-04_3',
      name: '募集注文完了'
    }
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.lotteryResult = '' // 抽選結果
    this.bbQuantityAlloc = '' // 当選株数
    this.orderStatus = '' // 注文状況
    this.quantity = '' // 数量
    this.contractAmount = '' // 約定金額
    this.depositType = '' // 預り区分
    this.kanyuKbn = '' // 勧誘区分
    this.jutyuKbn = '' // 受注方法
    this.mokuromiKoufuKbn = '' // 目論見書の交付方法
    this.importantMatterType = '' // 重要事項の説明
    this.bbRemark = '' // 備考
    this.domesticQuantityInput = '' // 訂正前_数量
    this.subscriptTradeAmount = '' // 訂正前_約定金額
    this.depositType2 = '' // 訂正前_預り区分
    this.solicitTypeName = '' // 訂正前_勧誘区分
    this.receiveOrderTypeName = '' // 訂正前_受注方法
    this.prospectusIssueMethodWord = '' // 訂正前_目論見書の交付方法
    this.importantMatterType2 = '' // 訂正前_重要事項の説明
    this.bbRemark2 = '' // 訂正前_備考
    this.complianceRankCheck = {
      message: '', // コンプラランクチェック確認
      chkBoxLabel: '' // コンプラランクチェック確認見出し
    }
    this.noticeInfoAlert = '' // 注意情報アラート確認
    this.noticeAlert = '' // お知らせアラート確認
    this.depositTypeConfirm = '' // 預り区分アラート確認
  }
}
