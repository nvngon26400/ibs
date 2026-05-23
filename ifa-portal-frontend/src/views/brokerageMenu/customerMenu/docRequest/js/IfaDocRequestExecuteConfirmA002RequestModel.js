import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestExecuteConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bunruiCd = obj.bunruiCd ? obj.bunruiCd : '' // 分類コード
    this.bunruimei = obj.bunruimei ? obj.bunruimei : '' // 分類名
    this.shoruiCd = obj.shoruiCd ? obj.shoruiCd : '' // 書類コード
    this.shoruimei = obj.shoruimei ? obj.shoruimei : '' // 書類名
    this.busuu = obj.busuu ? obj.busuu : '' // 部数
    this.hassouSts = obj.hassouSts ? obj.hassouSts : '' // 交付区分コード
    this.naiyouCaption = obj.naiyouCaption ? obj.naiyouCaption : '' // 内容(名前)
    this.naiyou = obj.naiyou ? obj.naiyou : '' // 内容(入力)
    this.bikouCaption = obj.bikouCaption ? obj.bikouCaption : '' // 備考(名前)
    this.bikou = obj.bikou ? obj.bikou : '' // 備考(入力)
    this.option1 = obj.option1 ? obj.option1 : '' // オプション1(名前)
    this.sentakuMei1 = obj.sentakuMei1 ? obj.sentakuMei1 : '' // オプション1(選択)
    this.option2 = obj.option2 ? obj.option2 : '' // オプション2(名前)
    this.sentakuMei2 = obj.sentakuMei2 ? obj.sentakuMei2 : '' // オプション2(選択)
    this.option3 = obj.option3 ? obj.option3 : '' // オプション3(名前)
    this.sentakuMei3 = obj.sentakuMei3 ? obj.sentakuMei3 : '' // オプション3(選択)
    this.txt1 = obj.txt1 ? obj.txt1 : '' // テキスト1(名前)
    this.txtNaiyou1 = obj.txtNaiyou1 ? obj.txtNaiyou1 : '' // テキスト1(入力)
    this.txt2 = obj.txt2 ? obj.txt2 : '' // テキスト2(名前)
    this.txtNaiyou2 = obj.txtNaiyou2 ? obj.txtNaiyou2 : '' // テキスト2(入力)
    this.txt3 = obj.txt3 ? obj.txt3 : '' // テキスト3(名前)
    this.txtNaiyou3 = obj.txtNaiyou3 ? obj.txtNaiyou3 : '' // テキスト3(入力)
    this.txt4 = obj.txt4 ? obj.txt4 : '' // テキスト4(名前)
    this.txtNaiyou4 = obj.txtNaiyou4 ? obj.txtNaiyou4 : '' // テキスト4(入力)
    this.txt5 = obj.txt5 ? obj.txt5 : '' // テキスト5(名前)
    this.txtNaiyou5 = obj.txtNaiyou5 ? obj.txtNaiyou5 : '' // テキスト5(入力)
  }
}
