export class IfaRegisterInfoModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0701-01',
      name: '登録情報一覧'
    }

    // 分類一覧エリア
    this.categoryExtensionNameList = [
      {
        categoryExtensionCd: '', // 分類一覧コード
        categoryExtensionName: '', // 分類一覧名称
        remarks: '', // 分類名補足説明
        displaySeq: '' // 順序
      }
    ]

    // 登録情報分類エリア_ヘッダ情報
    this.registerInfoHeaderValueList = [
      {
        valueCount: '', // 列件数
        extensionDetailCd: '', // 情報分類枝番
        inforExtensionCd: '', // 登録情報分類コード
        inforExtensionName: '', // 登録情報分類名称
        remarks: '', // 分類名補足説明
        maxColumn: '', // 縦の最大行数
        headerValue01: '', // 詳細情報ヘッダ01
        headerValue02: '', // 詳細情報ヘッダ02
        headerValue03: '', // 詳細情報ヘッダ03
        headerValue04: '', // 詳細情報ヘッダ04
        headerValue05: '', // 詳細情報ヘッダ05
        headerValue06: '', // 詳細情報ヘッダ06
        headerValue07: '', // 詳細情報ヘッダ07
        headerValue08: '', // 詳細情報ヘッダ08
        headerValue09: '', // 詳細情報ヘッダ09
        headerValue10: '', // 詳細情報ヘッダ10
        headerValue11: '', // 詳細情報ヘッダ11
        headerValue12: '', // 詳細情報ヘッダ12
        headerValue13: '', // 詳細情報ヘッダ13
        headerValue14: '', // 詳細情報ヘッダ14
        headerValue15: '', // 詳細情報ヘッダ15
        headerValue16: '', // 詳細情報ヘッダ16

        // 登録情報分類エリア_詳細情報01~16
        registerInfoValueList: [{
          extensionDetailCd: '', // 情報分類枝番
          inforExtensionCd: '', // 登録情報分類コード
          detailValue01: '', // 詳細情報01
          detailValue02: '', // 詳細情報02
          detailValue03: '', // 詳細情報03
          detailValue04: '', // 詳細情報04
          detailValue05: '', // 詳細情報05
          detailValue06: '', // 詳細情報06
          detailValue07: '', // 詳細情報07
          detailValue08: '', // 詳細情報08
          detailValue09: '', // 詳細情報09
          detailValue10: '', // 詳細情報10
          detailValue11: '', // 詳細情報11
          detailValue12: '', // 詳細情報12
          detailValue13: '', // 詳細情報13
          detailValue14: '', // 詳細情報14
          detailValue15: '', // 詳細情報15
          detailValue16: '' // 詳細情報16
        }]
      }]
    this.ccsOpId = ''
  }
}
