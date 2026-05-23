export class IfaContactDetailFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0106-06',
      name: '接触履歴詳細'
    }
    this.toiawaseNo = '' // 番号(問合せNO)
    this.toiawaseCd = '' // 分類
    this.toiawaseMei = '' // カテゴリー名称（大）
    this.toiawaseDMei = '' // カテゴリー名称（中）
    this.toiawaseSMei = '' // カテゴリー名称（小）
    this.sessyokuKeiro = '' // 接触経路
    this.juuyoudo = '' // 重要度
    this.cream = '' // クレーム
    this.request = '' // リクエスト
    this.taiouSts = '' // 対応ステータス
    this.houkou = '' // 入電方向
    this.houmonbi = '' // 訪問日
    this.nextHoumonbi = '' // 次回訪問予定日
    this.toiawaseNaiyou = '' // 内容
    this.kaitouNaiyou = '' // 追加入力
    this.userNm = '' // 取扱者
    this.groupAnswerList = []
  }
}
