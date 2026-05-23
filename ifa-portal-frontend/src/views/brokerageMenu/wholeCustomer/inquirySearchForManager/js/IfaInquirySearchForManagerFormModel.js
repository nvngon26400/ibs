export class IfaInquirySearchForManagerFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB020304-01',
      name: '接触履歴（入力）検索'
    }
    // 検索項目
    this.comment = '' // 表示用のメッセージ
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // 取引コース
    this.inquiryDate = [] // 問合せ日
    this.inquiryDateYmFrom = '' // 問合せ日From
    this.inquiryDateYmTo = '' // 問合せ日To
    this.toiawaseDCd = '' // カテゴリ（大）
    this.toiawaseDList = [] // カテゴリ（大）リスト
    this.toiawaseCd = '' // カテゴリ（中）
    this.toiawaseList = [] // カテゴリ（中）リスト
    this.toiawaseSCd = '' // カテゴリ（小）
    this.toiawaseSList = [] // カテゴリ（小）リスト
    this.cr = '' // クレーム/リクエスト
    this.crList = [] // クレーム/リクエストリスト
    this.juuyoudo = [] // 重要度
    this.taiouSts = [] // 対応ステータス
    this.nyuuryokuShaId = '' // 入力者(ID)
    this.nyuuryokuShaName = '' // 入力者(氏名)
    // 判断項目
    this.activeCondition = 1 // 詳細検索条件エリアの開く／閉じる
    this.csvbtn = false // 「CSV出力」ボタン表示/非表示
    this.selRow = false // 「接触履歴詳細へ」ボタン表示/非表示
    // データ項目
    this.varVal = {
      maxLines: 3, // 「内容」表示最大行数
      font: '14px "Roboto", "Noto Sans JP", "ヒラギノ角ゴシック W4", "Hiragino Sans W4", "メイリオ", "Meiryo", sans-serif', // 「内容」表示フォント設定
      widthShort: 138, // 「内容」一定の幅表示px
      widthLong: 1562, // 「内容」拡大表示px
      selRowIndex: -1, // 一覧部分選択行
      showNaiyouShort: true, // 「内容」表示モード
      recordSize: 0, // レコード数
      leftSliderPos: 240, // スライダー左幅
      maxHeight: 750 // 最大高さ
    }
  }
}
