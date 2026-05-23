export class IfaContactInputFormModel {
  constructor() {
    this.dialogTitle = {
      id: 'SUB0202_0106-03',
      name: ''
    }
    // 検索項目
    this.toiawaseDCd = '' // 分類（大）
    this.toiawaseCd = '' // 分類（中）
    this.toiawaseSCd = '' // 分類（小）
    this.cream = '' // クレーム
    this.creamCheckbox = [ // クレームリスト
      { key: '0', value: '' },
      { key: '1', value: 'クレーム' }
    ]
    this.request = '' // リクエスト
    this.requestCheckbox = [ // リクエストリスト
      { key: '0', value: '' },
      { key: '1', value: 'リクエスト' }
    ]
    this.juuyoudo = '2' // 重要度
    this.sessyokuKeiro = '' // 接触経路
    this.sessyokuKeiroList = '' // 接触経路リスト
    this.taiouSts = '0' // 対応ステータス
    this.houkou = '0' // 入電方向
    this.houmonbi = '' // 訪問日
    this.nextHoumonbi = '' // 次回訪問予定日
    this.toiawaseNaiyou = '' // 内容
    this.kaitouNaiyou = '' // 追加入力
    // データ項目
    this.toiawaseDList = []
    this.toiawaseList = []
    this.toiawaseSList = []
    this.toiawaseNo = '' // hidden_問合せNO
    this.ifaToiawaseNo = '' // hidden_IFA問合せNO
    this.ifaNyuuryokuFlg = '' // IFA入力フラグ
  }
}
