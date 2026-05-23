export class IfaContactFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB0202_01-06',
      name: '接触履歴'
    }
    this.selectBigclass = []
    // 接触一覧
    this.contactInfoList = [{
      bigClass: '', // 大分類
      midClass: '', // 中分類
      recordDate: '', // 記録日時
      status: '', // ステータス
      contents: '', // 内容
      chargeName: '' // 担当者名
    }]
  }
}
