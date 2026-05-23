export class IfaFaqFormModel {
  constructor() {
    this.title = {
      id: 'SUB00-05',
      name: 'IFAポータル  よくある質問'
    }
    this.searchWord = '' // 検索ワード
    this.primaryItem = '' // 大項目リスト
    this.tertiaryItem = '' // 小項目リスト
    this.searchKeyWordList = [] // 検索ワード
    this.msg = '' // メッセージ
    this.searchResultListCount = '' // 検索結果リスト件数
    this.searchResultList = [] // 検索ダイジェスト
    this.faqList = []
    this.contentsNo = '' // コンテンツNo.
    this.heading = '' // 見出し
    this.contentText = '' // コンテンツ本文
  }
}
