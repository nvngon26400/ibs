export class IfaReleaseNoteFormModel {
  constructor() {
    this.screenTitle = {
      id: 'SUB00-07_1',
      name: 'リリースノート'
    }
    this.displayYear = '' // 表示対象年
    this.displayYearList = [] // 表示対象年リスト
    this.nextDispFlg = '' // 次回表示フラグ
    this.releaseNoteDataList = [] // リリースノート一覧
  }
}
