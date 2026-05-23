import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterViewerSettingA016RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.repSearch = obj.repSearch ? obj.repSearch : '' // 担当者（検索）
  }
}
