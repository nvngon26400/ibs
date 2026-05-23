<template>
  <ifa-button
    v-show="isShow"
    :id="id"
    :action-id="actionId"
    :action-type="actionType"
    :request-model="requestModel"
    :form="form"
    :set-param-func="setParamFunc"
    :csv-file-name="csvFileName"
    :is-check-csv-download-allowed="isCheckCsvDownloadAllowed"
    :is-check-csv-download-privacy-confirmation="isCheckCsvDownloadPrivacyConfirmation"
    :pre-request-handler="preRequestHandler"
    :msg-title="msgTitle"
    :disable-notification="disableNotification"
    text=""
  ></ifa-button>
</template>

<script>
export default {
  name: 'IfaRequester',
  props: {
    id: { type: String, required: true, default: null }, // ID
    actionId: { type: String, required: true, default: null }, // アクションID
    actionType: { type: String, required: true, default: null }, // アクションタイプ
    requestModel: { type: Object, default: () => ({}) }, // リクエストモデル
    form: { type: Object, default: null }, // フォーム
    setParamFunc: { type: Function, default: undefined }, // パラメーターファンクション
    csvFileName: { type: String, required: false, default: '' }, // CSVファイル名
    isCheckCsvDownloadAllowed: { type: Boolean, required: false, default: true }, // CSVダウンロード可否チェック
    isCheckCsvDownloadPrivacyConfirmation: { type: Boolean, required: false, default: true }, // 個人情報管理台帳ダウンロード要否チェック
    preRequestHandler: { type: Function, default: undefined }, // 前処理ファンクション
    msgTitle: { type: String, required: false, default: '' }, // メッセージダイアログのタイトル
    disableNotification: { type: Boolean, required: false, default: false } // レスポンスにメッセージが設定されている場合のトースト表示を抑制。
  },
  data() {
    return {
      isShow: false
    }
  }
}
</script>
