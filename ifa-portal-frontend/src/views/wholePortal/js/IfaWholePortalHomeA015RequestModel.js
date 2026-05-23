import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA015RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.viewStatus = obj.viewStatus ? obj.viewStatus : '' // 閲覧状況
    this.viewTarget = obj.viewTarget ? obj.viewTarget : '' // 閲覧対象
    this.titleThisMonth = obj.titleThisMonth ? obj.titleThisMonth : '' // タイトル（当月）
    this.titleLastMonth = obj.titleLastMonth ? obj.titleLastMonth : '' // タイトル（過去月）
  }
}
