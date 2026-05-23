import Logger from '@/utils/ifaLog.js'
export class IfaContactInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.toiawaseDCd = obj.toiawaseDCd ? obj.toiawaseDCd : '' // 分類（大）
    this.toiawaseDMei = obj.toiawaseDMei ? obj.toiawaseDMei : '' // 分類名（大）
    this.toiawaseCd = obj.toiawaseCd ? obj.toiawaseCd : '' // 分類（中）
    this.toiawaseMei = obj.toiawaseMei ? obj.toiawaseMei : '' // 分類名（中）
    this.toiawaseSCd = obj.toiawaseSCd ? obj.toiawaseSCd : '' // 分類（小）
    this.toiawaseSMei = obj.toiawaseSMei ? obj.toiawaseSMei : '' // 分類名（小）
    this.sessyokuKeiro = obj.sessyokuKeiro ? obj.sessyokuKeiro : '' // 接触経路
    this.juuyoudo = obj.juuyoudo ? obj.juuyoudo : '' // 重要度
    this.cream = obj.cream ? obj.cream : '' // クレーム
    this.request = obj.request ? obj.request : '' // リクエスト
    this.taiouSts = obj.taiouSts ? obj.taiouSts : '' // 対応ステータス
    this.houkou = obj.houkou ? obj.houkou : '' // 入電方向
    this.houmonbi = obj.houmonbi ? obj.houmonbi : '' // 訪問日
    this.nextHoumonbi = obj.nextHoumonbi ? obj.nextHoumonbi : '' // 次回訪問予定日
    this.toiawaseNaiyou = obj.toiawaseNaiyou ? obj.toiawaseNaiyou : '' // 内容
    this.kaitouNaiyou = obj.kaitouNaiyou ? obj.kaitouNaiyou : '' // 追加入力
    this.operationType = obj.operationType ? obj.operationType : '' // 処理区分
    this.toiawaseNo = obj.toiawaseNo ? obj.toiawaseNo : '' // 問合せNO
    this.ifaToiawaseNo = obj.ifaToiawaseNo ? obj.ifaToiawaseNo : '' // IFA問合せNO
  }
}
