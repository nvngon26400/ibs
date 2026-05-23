export class IfaRepAddFormModel {
  constructor() {
    this.title = {
      id: 'SUB0601_01-06_1',
      name: '担当追加'
    }
    this.loginId = '' // ログインID
    this.privId = '' // 所属権限
    // 本店／支店名 【初期値】"-1":--選択してください--
    this.branchNameList = [{
      mainBranchName: '', // 本店／支店名（全角半角）
      sbiSecurityCode: '' // SBI証券支店コード（数字）
    }]
    // 仲介業者名 【初期値】"-1":--選択してください--
    this.brokerNameList = [{
      brokerName: '', // 仲介業者名（全角半角）
      brokerCode: '' // 仲介業者コード（数字）
    }]
    // 仲介業者支店名 【初期値】"-1":--選択してください-
    this.brokerBranchNameList = [{
      brokerBranchName: '', // 仲介業者支店名
      subBrokerId: '' // 仲介業者支店コード（数字）
    }]
    // 担当者名 【初期値】"-1":--選択してください--
    this.employeeNameList = [{
      employeeName: '', // 仲介業者担当者名（全角半角）
      employeeId: '' // 仲介業者担当者コード（数字）
    }]
    this.searchResultCount = '' // 検索結果件数（数値(整数)）
  }
}
