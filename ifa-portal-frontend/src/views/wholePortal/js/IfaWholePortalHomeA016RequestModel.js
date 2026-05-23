import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA016RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.viewStatus = obj.viewStatus ? obj.viewStatus : '' // 閲覧状況
    this.viewNecessity = obj.viewNecessity ? obj.viewNecessity : '' // 閲覧要否
    this.titleThisMonth = obj.titleThisMonth ? obj.titleThisMonth : '' // タイトル（当月）
    this.titleLastMonth = obj.titleLastMonth ? obj.titleLastMonth : '' // タイトル（過去月）
  }
}
