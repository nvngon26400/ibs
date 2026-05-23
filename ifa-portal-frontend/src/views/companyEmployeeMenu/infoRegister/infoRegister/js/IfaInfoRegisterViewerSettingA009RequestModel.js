import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterViewerSettingA009RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCodeFuzzySearch = obj.brokerCodeFuzzySearch ? obj.brokerCodeFuzzySearch : '' // 仲介業者（検索）
  }
}
