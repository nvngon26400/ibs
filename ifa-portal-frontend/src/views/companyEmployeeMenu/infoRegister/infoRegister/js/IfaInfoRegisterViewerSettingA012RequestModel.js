import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterViewerSettingA012RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerSelect = obj.brokerSelect ? obj.brokerSelect : '' // 仲介業者選択
  }
}
