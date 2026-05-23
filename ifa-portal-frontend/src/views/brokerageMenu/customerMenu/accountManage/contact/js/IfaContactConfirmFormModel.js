export class IfaContactConfirmFormModel {
  constructor() {
    this.dialogTitle = {
      id: 'SUB0202_0106-04',
      name: ''
    }
    this.beforeData = {
      toiawaseDCd: '', // 分類（大）
      toiawaseCd: '', // 分類（中）
      toiawaseSCd: '', // 分類（小）
      sessyokuKeiro: '', // 接触経路
      juuyoudo: '', // 重要度
      cream: '', // クレーム
      request: '', // リクエスト
      taiouSts: '', // 対応ステータス
      houkou: '', // 入電方向
      houmonbi: '', // 訪問日
      nextHoumonbi: '', // 次回訪問予定日
      toiawaseList: [], // カテゴリリスト（中）
      toiawaseSList: [] // カテゴリリスト（小）
    }
    this.afterData = {
      toiawaseDCd: '', // 分類（大）
      toiawaseCd: '', // 分類（中）
      toiawaseSCd: '', // 分類（小）
      sessyokuKeiro: '', // 接触経路
      juuyoudo: '', // 重要度
      cream: '', // クレーム
      request: '', // リクエスト
      taiouSts: '', // 対応ステータス
      houkou: '', // 入電方向
      houmonbi: '', // 訪問日
      nextHoumonbi: '', // 次回訪問予定日
      toiawaseDList: [], // カテゴリリスト（大）
      toiawaseList: [], // カテゴリリスト（中）
      toiawaseSList: [] // カテゴリリスト（小）
    }
    this.toiawaseNaiyou = '' // 内容
    this.kaitouNaiyou = '' // 追加入力
  }
}
