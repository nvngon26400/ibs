import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdNewRegisterA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.menuList = obj.menuList ? obj.menuList : [] // メニューリスト
  }
}
