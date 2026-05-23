import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA022RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.title = obj.title ? obj.title : '' // タイトル
  }
}
