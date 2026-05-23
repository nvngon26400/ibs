export class IfaExternalLinkModel {
  constructor() {
    this.titleList = {
      title: '外部リンク'
    }

    this.butenCode = ''
    this.accountNumber = ''
    this.tenGunId = ''
    this.ccsOpId = ''
    this.customerAttribute = ''
    // 分類エリア
    this.categoryList = []
    this.newMainSiteParamList = []
    this.linkUrl = ''
    this.postRequest = {}
  }
}
