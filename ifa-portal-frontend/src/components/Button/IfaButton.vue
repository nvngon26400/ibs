<template>
  <span class="padding">
    <el-button
      v-if="ctrlVisible"
      :id="id"
      :size="buttonSize"
      :icon="iconName"
      class="ifa-button"
      :class="{
        'primary-class': isPrimary,
        'secondary-class': isSecondary,
        'buy-class': isBuy,
        'sell-class': isSell,
      }"
      :style="isWidthSpecified ? { width: widthSpec } : {}"
      :disabled="isDisabled"
      @click="execute"
    >{{ text }}</el-button>

    <el-dialog
      id="modalCancel"
      v-model="isVisible"
      :title="dialogTitle"
      :show-close="false"
      :close-on-click-modal="false"
      :center="true"
    >
      <label>終了する場合は「キャンセル」をクリックして下さい</label>
      <div>
        <ifa-button
          id="btnCancel"
          name="btnCancel"
          text="キャンセル"
          width="100"
          color="secondary"
          small
          style="margin-top: 1.5rem"
          action-type="originalAction"
          @app-action-handler="cancelAction"
        ></ifa-button>
      </div>
    </el-dialog>

    <ifa-ok-cancel-dialog
      :is-visible="isOkCancelDialogVisible"
      title="個人情報について"
      message="管理系メニューの個人情報管理台帳作成画面にて、個人情報管理の記述をお願いします。※内部管理責任者メニュー＞個人情報管理＞個人情報管理台帳をクリックすると「個人情報管理台帳作成画面」が表示されます。"
      @close-modal-ok="handleDialogClose(false)"
      @close-modal-cancel="handleDialogClose(true)"
    ></ifa-ok-cancel-dialog>
  </span>
</template>

<script>
import actionMappings from '@/resource/action/ActionMappings.json'
import { controlAuthorization } from '@/utils/controlAuth'
import { notifyMessage, backToLoginWithDialog } from '@/utils/errorHandler'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { GET_CSV_ACTION, GET_PDF_ACTION, UPLOAD_ACTION } from '@/utils/request'
const path = require('path')

const searchIconName = 'Search'
let loadingCounter = 0

export default {
  name: 'IfaButton',
  components: {
    IfaOkCancelDialog
  },
  props: {
    id: { type: String, required: false, default: null }, // ID
    text: { required: true, type: String }, // ボタン文字列
    color: { required: false, type: String, default: '' }, // ボタン色
    icon: { required: false, type: String, default: null }, // アイコン
    search: { required: false, type: Boolean, default: false }, // サーチアイコン
    disabled: { required: false, type: Boolean, default: false }, // 非活性
    width: { required: false, type: String, default: '0' }, // ボタン幅
    small: { required: false, type: Boolean, default: false }, // スモールスタイル
    actionId: { type: String, default: null }, // アクションID
    requestModel: { type: Object, default: () => ({}) }, // リクエストモデル
    actionType: { type: String, default: null }, // アクションタイプ
    form: { type: Object, default: null }, // フォーム
    setParamFunc: { type: Function, default: undefined }, // パラメーターファンクション
    csvFileName: { type: String, required: false, default: '' }, // CSVファイル名
    isCheckCsvDownloadAllowed: { type: Boolean, required: false, default: true }, // CSVダウンロード可否チェック
    isCheckCsvDownloadPrivacyConfirmation: { type: Boolean, required: false, default: true }, // 個人情報管理台帳ダウンロード要否チェック
    preRequestHandler: { type: Function, default: undefined }, // 前処理ファンクション
    msgTitle: { type: String, required: false, default: '' }, // メッセージダイアログのタイトル
    controlAuthEnabled: { type: Boolean, required: false, default: true }, // 認可制御不使用フラグ（true: 認可制御使用する,false: 認可制御使用しない）
    disableNotification: { type: Boolean, required: false, default: false }, // レスポンスにメッセージが設定されている場合のトースト表示を抑制。
    isCsvFileNameDateTime: { type: Boolean, required: false, default: false } // CSVファイル名にyyyyMMdd-HHmmを付与するフラグ（true（STARアップロードファイル出力一覧で使用）: csvFileName+yyyyMMdd-HHmm, false:  csvFileName+'_'+yyyyMMdd）
  },
  emits: [
    'click',
    'app-action-handler',
    'response-handler',
    'response-error-handler',
    'finish-action-handler'
  ],
  data() {
    return {
      actionMappings: actionMappings,
      isVisible: false,
      cancel: false,
      loading: null,
      ctrlReadonly: false, // 認可制御(読取専用)
      ctrlVisible: true, // 認可制御(表示/非表示)
      ctrlDisabled: false, // 認可制御(編集不可)
      processing: false,
      isOkCancelDialogVisible: false
    }
  },
  computed: {
    title() {
      if (this.msgTitle) {
        return this.msgTitle
      } else {
        // メッセージダイアログのタイトルをIfaRouterの情報から設定
        return this.$store.getters.pageInfo?.label ?? ''
      }
    },
    dialogTitle() {
      const title = this.title ? this.title : ''
      const text = this.text ? this.text : ''
      return title + ' ' + text
    },
    buttonSize() {
      return this.small ? 'small' : ''
    },
    iconName() {
      if (this.icon) {
        return this.icon
      } else {
        return this.search ? searchIconName : ''
      }
    },
    isWidthSpecified() {
      return this.width !== '0'
    },
    widthSpec() {
      return this.width + 'px'
    },
    isPrimary() {
      return (this.color === '' || this.color === 'primary') && !(this.disabled || this.ctrlDisabled || this.ctrlReadonly || this.csvDownloadDisabled)
    },
    isSecondary() {
      return this.color === 'secondary' && !(this.disabled || this.ctrlDisabled || this.ctrlReadonly || this.csvDownloadDisabled)
    },
    isBuy() {
      return this.color === 'buy' && !(this.disabled || this.ctrlDisabled || this.ctrlReadonly || this.csvDownloadDisabled)
    },
    isSell() {
      return this.color === 'sell' && !(this.disabled || this.ctrlDisabled || this.ctrlReadonly || this.csvDownloadDisabled)
    },
    isDisabled() {
      return this.disabled || this.ctrlDisabled || this.ctrlReadonly || this.processing || this.csvDownloadDisabled
    },
    csvDownloadDisabled() {
      if (['outputCsvAction', 'outputCsvFromGridAction'].includes(this.actionType)) {
        if (this.isCheckCsvDownloadAllowed &&
            !this.$store.getters.userAccount.csvDownloadAllowed) {
          return true
        }
      }
      return false
    }
  },
  mounted() {
    if (this.controlAuthEnabled) {
      controlAuthorization.bind(this)() // 認可制御
    }
  },
  unmounted() {
    if (this.loading) {
      if (loadingCounter === 0) {
        this.loading.close()
      }
      this.isOkCancelDialogVisible = false
    }
  },
  methods: {
    async execute(event) {
      if (this.actionType !== 'originalAction') {
        // クリック イベントが親コンポーネントに伝播するのを防ぐ設定
        if (event) {
          event.stopPropagation()
        }
      }
      this.cancel = false
      if (this.processing) {
        return
      }
      this.processing = true
      /*
       * 以下の処理パターン(actionType)に応じた処理を行う。
       * 業務独自実装   ：originalAction
       * リクエスト     ：requestAction
       * CSV出力       ：outputCsvAction
       * APIフェッチ処理:	fetchApiAction
       * PDFダウンロード : ouputPdfAction
       * アップロード:	uploadAction
       */
      // 1.業務側独自実装ケースへの対応
      if (this.actionType === 'originalAction') {
        this.$emit('app-action-handler')
        this.processing = false
        return
      }
      // 2.バリデーションチェック実行(formなど必要なものが渡されない場合はチェックしない)
      const valid = await this.validate()
      if (!valid) {
        this.processing = false
        return
      }
      // 3.リクエスト発行前の処理(呼び出し元で当該関数を設定)
      if (this.preRequestHandler) {
        try {
          this.preRequestHandler()
        } catch (error) {
          // 呼び出し元で設定した関数でエラーが発生した場合は処理を中断する
          this.processing = false
          return
        }
      }
      // 4.アクションタイプ別のリクエスト発行処理
      // CSVダウンロードの場合は個人情報の確認後にロード画面を表示させる
      if (this.actionType !== 'outputCsvAction' && this.actionType !== 'outputCsvFromGridAction') {
        this.loading = this.$loading({
          lock: false,
          text: '処理中',
          background: 'rgba(0, 0, 0, 0.4)'
        })
      }
      loadingCounter++
      try {
        switch (this.actionType) {
          case 'requestAction':
            await this.requestAction()
            break
          case 'outputCsvAction':
            await this.outputCsvAction(this.requestModel)
            break
          case 'outputCsvFromGridAction':
            await this.outputCsvAction({ 'csvData': this.csvDataFrom(this.requestModel) })
            break
          case 'fetchApiAction':
            this.isVisible = true
            await this.fetchApiAction()
            break
          case 'outputPdfAction':
            await this.outputPdfAction(this.requestModel)
            break
          case 'uploadAction':
            await this.uploadAction(this.requestModel)
            break
          default:
            break
        }
      } catch (error) {
        // request.js にて既に通知の表示とログイン画面に戻る処理が入っているため特別な処理は不要
      } finally {
        loadingCounter--
        if (loadingCounter === 0) {
          if (this.loading) {
            this.loading.close()
          }
        }
        this.isVisible = false
        this.processing = false
        this.$emit('finish-action-handler')
      }
    },
    async validate() {
      if (this.form) {
        return await this.form.validate().catch(() => { return false })
      }
      return true
    },
    async requestAction() {
      const data = await this.$_request(this.actionId, this.requestModel)
        .catch((error) => { throw error })
      if (this.checkResponse(data)) {
        this.$emit('response-handler', data)
      }
    },
    async outputCsvAction(requestModel) {
      // CSVダウンロード可否チェック
      if (this.isCheckCsvDownloadAllowed &&
          !this.$store.getters.userAccount.csvDownloadAllowed) {
        return
      }
      // 個人情報管理台帳ダウンロード要否チェック
      if (this.isCheckCsvDownloadPrivacyConfirmation &&
          this.$store.getters.userAccount.csvDownloadPrivacyConfirmationRequired) {
        this.isOkCancelDialogVisible = true
        await new Promise(resolve => { this.resolveFunction = resolve })
        this.isOkCancelDialogVisible = false
        // キャンセルボタンが押されていたら中断する。
        if (this.cancel) return
      }
      // ロード画面を表示
      this.loading = this.$loading({
        lock: false,
        text: '処理中',
        background: 'rgba(0, 0, 0, 0.4)'
      })
      // 件数取得
      const data = await this.$_request(this.actionId, requestModel, 0)
        .catch((error) => { throw error })

      // CSVデータ取得
      if (this.checkResponse(data)) {
        if (data.totalSize > 0) {
          const params = { csvDownloadFile: data.title }
          if (data.dataList[0]) {
            params.pattern = data.dataList[0].pattern
          }
          const csvData = await this.$_request(this.actionId, params, 1, GET_CSV_ACTION)
            .catch((error) => { throw error })
          // csvData は ファイル内容が BLOB で送信されてくるため､レスポンスのチェックは不要
          this.outputCsvToFile(csvData)
        }
        this.$emit('response-handler', data)
      }
    },
    async fetchApiAction() {
      // 親情報取得
      const parentData = await this.$_request(this.actionId, this.requestModel, 0)
        .catch((error) => { throw error })

      // 子情報取得
      const result = []
      if (this.checkResponse(parentData)) {
        let isSysError = false
        for (const data of parentData.dataList) {
          // 画面側の関数で2度目以降のリクエストパラメータを編集する
          const param = this.setParamFunc(data)
          const childData = await this.$_request(this.actionId, param, 1)
            .catch((error) => { throw error })

          // レスポンスの errorLevel が -2 またはキャンセルボタンが押されていたら中断する。
          if (!this.checkResponse(childData, true)) {
            isSysError = true
            break
          } else if (this.cancel) {
            break
          }

          for (const item of childData.dataList) {
            if (Object.keys(item).length !== 0) {
              result.push(item)
            }
          }
        }
        // システムエラーの場合responseHandlerを呼び出さない
        if (!isSysError) {
          const isSecondRequest = parentData.dataList && parentData.dataList.length > 0
          this.$emit('response-handler', result, this.cancel, isSecondRequest)
        }
      }
    },
    async outputPdfAction(requestModel) {
      // PDF作成
      const data = await this.$_request(this.actionId, requestModel, 0)
        .catch((error) => { throw error })

      // PDFデータ取得
      if (this.checkResponse(data)) {
        if (data.totalSize > 0) {
          const pdfFileName = data.dataList[0].pdfFileName
          const pdfFileOutputName = data.dataList[0].pdfFileOutputName
          const pdfData = await this.$_request(this.actionId, { pdfFileName }, 1, GET_PDF_ACTION)
            .catch((error) => { throw error })
          // pdfData は ファイル内容が BLOB で送信されてくるため､レスポンスのチェックは不要
          // ダウンロード時のファイル名に指定がある場合は"pdfFileOutputName"でファイル名を指定
          if (pdfFileOutputName) {
            this.outputPdfToFile(pdfData, pdfFileOutputName)
          } else {
            this.outputPdfToFile(pdfData, pdfFileName)
          }
        }
        this.$emit('response-handler', data)
      }
    },
    async uploadAction(requestModel) {
      // requestModel を一律で配列にして null データを除外する
      const models = (Array.isArray(requestModel) ? requestModel : [requestModel]).filter(obj => obj.file)

      // undefined や 配列の長さが 0 の場合はエラー
      if (!models || !models.length) {
        this.notifyMessage(-1, 'ファイルが選択されていません。', new Date().toLocaleString('ja-JP'))
        return
      }

      // requestModel を formData にバインドする
      const formData = new FormData()
      models.forEach(obj => {
        formData.append(obj.filename, obj.file.raw)
      })

      // file以外のパラメータ
      const modelsKeyValue = (Array.isArray(requestModel) ? requestModel : [requestModel]).filter(obj => obj.key && obj.value)

      modelsKeyValue.forEach(obj => {
        formData.append(obj.key, obj.value)
      })

      // アップロード開始
      const data = await this.$_request(this.actionId, formData, 0, UPLOAD_ACTION)
        .catch((error) => { throw error })
      if (this.checkResponse(data)) {
        this.$emit('response-handler', data, models.length)
      }
    },
    notifyMessage(errorLevel, message, requestedTime) {
      notifyMessage(errorLevel, message, this.title, requestedTime)
    },
    cancelAction() {
      this.cancel = true
    },
    // レスポンス共通ハンドラー
    // System error 発生時はログイン画面に遷移させる
    // 致命的エラー(FATAL)が発生した場合は､response-error-handler イベントを発行する
    // @param response サーバのレスポンス(JSON) errorLevel と message が含まれていること
    // @param forceContinue (option) true を指定した場合､errorLevel が -1 でも true (エラーなし)を返す
    // @return true: エラーなし､ false: エラーあり
    checkResponse(response, forceContinue = false) {
      if (response.errorLevel === -2) {
        // System error 時はログイン画面に遷移させる
        backToLoginWithDialog(response.message, this.title)
        return false
      }

      if (!this.disableNotification) {
        // System error 以外は通知をポップアップする(propsで抑制されている場合を除く)
        this.notifyMessage(response.errorLevel, response.message, response.requestedTime)
      }

      if (response.errorLevel === -1) {
        // FATAL の場合は､response-error-handler を発行する
        this.$emit('response-error-handler', response)
        return forceContinue
      }
      return true
    },
    handleDialogClose(cancelled) {
      this.cancel = cancelled
      this.resolveFunction()
    },
    csvDataFrom(grid) {
      const data = grid.dataModel.data
      const colModel = grid.colModel
      const header = colModel.map(col => col.title).join(',') + '\n'
      let csvData = ''
      csvData += data.map(rowData =>
        colModel.map(col =>
          rowData[col.dataIndx] ?? ''
        ).join(',')
      ).join('\n')
      return header + csvData
    },
    outputCsvToFile(csvData) {
      const blob = new Blob([csvData], { type: 'text/csv' })
      const link = document.createElement('a')
      link.href = window.URL.createObjectURL(blob)
      const requestedTime = this.$store.getters.requestedTime
      const today = requestedTime ? new Date(requestedTime) : new Date()
      if (this.isCsvFileNameDateTime) {
        link.download =
        this.csvFileName +
        today.getFullYear() +
        ('0' + (today.getMonth() + 1)).slice(-2) +
        ('0' + today.getDate()).slice(-2) +
        '-' +
        ('0' + today.getHours()).slice(-2) +
        ('0' + today.getMinutes()).slice(-2) +
        '.csv'
      } else {
        link.download =
        this.csvFileName +
        '_' +
        today.getFullYear() +
        ('0' + (today.getMonth() + 1)).slice(-2) +
        ('0' + today.getDate()).slice(-2) +
        '.csv'
      }
      link.click()
    },
    outputPdfToFile(pdfData, pdfFileName) {
      const link = document.createElement('a')
      link.href = window.URL.createObjectURL(pdfData)
      link.download = path.basename(pdfFileName)
      link.click()
    }
  }
}
</script>

<style lang="scss" scoped>
.ifa-button {
  box-shadow: 0px 1px 3px #02113b4d;
  font-size: 16px;
  height: 40px;
  &.primary-class {
    color: #ffffff;
    background-color: #092987;
    border-color: #092987;

    &:hover,
    &:active {
      opacity: 0.7;
    }
  }
  &.secondary-class {
    color: #092987;
    background-color: #ffffff;
    border-color: #092987;

    &:hover,
    &:active {
      background-color: #e6e8f0;
      opacity: 0.7;
    }
  }
  &.sell-class {
    color: #ffffff;
    background-color: #00847f;
    border-color: #00847f;

    &:hover,
    &:active {
      opacity: 0.7;
    }
  }

  &.buy-class {
    color: #ffffff;
    background-color: #e5004c;
    border-color: #e5004c;

    &:hover,
    &:active {
      opacity: 0.7;
    }
  }
}

.small {
  font-size: 14px;
  height: 30px;
}

.padding {
  padding-left: 5px;
  padding-right: 5px;
}
</style>
