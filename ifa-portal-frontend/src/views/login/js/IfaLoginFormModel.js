export class IfaLoginFormModel {
  constructor() {
    this.screenId = 'SUB00-00'
    this.screenTitle = 'ログイン'
    // お知らせ一覧
    this.notification = [{
      infoId: '',
      infoDetail: '', // お知らせ情報
      importantFlg: '',
      displayOrder: '',
      deleteFlg: '',
      createTime: '',
      createUser: '',
      updateTime: '',
      updateUser: ''
    }]
  }
}
