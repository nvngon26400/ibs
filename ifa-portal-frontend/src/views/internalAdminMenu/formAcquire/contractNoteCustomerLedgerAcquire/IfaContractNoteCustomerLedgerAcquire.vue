<template>
  <!-- 画面名：取引日記帳・顧客勘定元帳取得 -->
  <div
    class="ifa-contract-note-customer-leger-acuire__wrapper"
  >
    <screen-title :text="form.title.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card
        class="content-card"
        style="min-width: 740px;"
      >
        <div class="filter-container">
          <el-row>
            <el-col :span="23">
              <ifa-common-search
                ref="commonSearch"
                :form="form"
                display-pattern="pt3"
              ></ifa-common-search>
            </el-col>
          </el-row>

          <el-row>
            <!-- 作成日 /-->
            <div class="form_label">
              <ifa-date-range-picker
                v-model="form.createDate"
                label="作成日"
                prop="createDate"
                size="small"
                :picker-options="pickerOptions"
                required
              ></ifa-date-range-picker>
              <div style="margin-top: 5px; margin-left: 9.5rem;">
                ※期間は3ヶ月以内を指定してください。（保管している全ての帳簿を照会いただけます。）
              </div>
            </div>

            <!-- 帳簿種別 /-->
            <div class="form_label">
              <ifa-input-select
                v-model="form.ledgerClass"
                label="帳簿種別"
                code-list-id="original"
                :original-list="ledgerClassList"
                size="small"
                :clearable="false"
                placeholder=" "
              ></ifa-input-select>
            </div>
          </el-row>

          <!-- 検索用ボタン -->
          <el-row style="margin-left: 20px; margin-top: 20px;">
            <el-col>
              <span
                id="indicator-display"
                class="form_button"
              >
                <ifa-button
                  id="btnDisplay"
                  name="btnDisplay"
                  text="表示"
                  width="90"
                  search
                  small
                  :form="formRef"
                  :request-model="ifaContractNoteCustomerLedgerAcquireA002RequestModel"
                  action-id="SUB0402_02-01#A002"
                  action-type="requestAction"
                  @response-handler="responseHandlerA002"
                  @response-error-handler="responseErrorHandlerA002"
                ></ifa-button>
              </span>
            </el-col>
          </el-row>
          <!-- /検索用ボタン -->
        </div>
      </el-card>
      <el-row>
        <el-card
          class="content-card"
          style="min-width: 740px;"
        >
          <div class="pq-grid-title">取引日記帳・顧客勘定元帳一覧</div>
          <grid-table
            ref="grid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>

    <ifa-requester
      id="ifaContractNoteCustomerLedgerAcquireInitializeA001"
      action-id="SUB0402_02-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>
    <ifa-requester
      id="ifaContractNoteCustomerLedgerAcquireGetPDFA003"
      action-id="SUB0402_02-01#A003"
      action-type="outputPdfAction"
      :request-model="ifaContractNoteCustomerLedgerAcquireA003RequestModel"
      @response-handler="responseHandlerA003"
      @response-error-handler="responseErrorHandlerA003"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getDefaultOption } from '@/utils/pqgridHelper'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaContractNoteCustomerLedgerAcquireFormModel } from './js/IfaContractNoteCustomerLedgerAcquireFormModel'
import { IfaContractNoteCustomerLedgerAcquireA002RequestModel } from './js/IfaContractNoteCustomerLedgerAcquireA002RequestModel'
import { IfaContractNoteCustomerLedgerAcquireA003RequestModel } from './js/IfaContractNoteCustomerLedgerAcquireA003RequestModel'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaCommonSearch
  },
  emits: ['initializeError'],
  data() {
    return {
      pqGridOption: null,
      form: new IfaContractNoteCustomerLedgerAcquireFormModel(),
      formRef: null,
      rules: {
        createDate: [
          {
            validator: this.periodDateValidator,
            trigger: 'blur'
          }
        ]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setDate(1)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate() - 1)
              return [startDate, endDate]
            }
          }, {
            // 1ヶ月前～前日
            text: '1ケ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate() - 1)
              return [startDate, endDate]
            }
          }, {
            // 2ヶ月前～前日
            text: '2ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 2)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate() - 1)
              return [startDate, endDate]
            }
          }, {
            // 3ヶ月前～前日
            text: '3ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate() - 1)
              return [startDate, endDate]
            }
          }
        ]
      }
    }
  },
  computed: {
    // 帳票種別リストをドロップダウンのオプション形式({key, value})のリストに変換する
    ledgerClassList() {
      return this.form.ledgerClassList.map(obj => { return { key: obj.codeId, value: obj.codeName } })
    },
    ifaContractNoteCustomerLedgerAcquireA002RequestModel() {
      const model = new IfaContractNoteCustomerLedgerAcquireA002RequestModel(this.form)
      model.createDateFrom = this.getYYYYMMDDFormat(this.form.createDate[0])
      model.createDateTo = this.getYYYYMMDDFormat(this.form.createDate[1])
      return model
    },
    ifaContractNoteCustomerLedgerAcquireA003RequestModel() {
      return new IfaContractNoteCustomerLedgerAcquireA003RequestModel(this.form.tradeDiaryCustomerLedger)
    }
  },
  created() {
    this.pqGridOption = getDefaultOption(columns)
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  methods: {
    onShow() {
      // form のインスタンスを初期化
      this.formRef = this.$refs['form']
      this.$refs.commonSearch.formClear()
      // データグリッドを初期化
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['grid'].refreshView()
      })

      // 作成日に初期値を設定する
      // 作成日: Fromは当日の3ヶ月前の日付､Toは前日の日付
      const startDate = new Date()
      startDate.setMonth(startDate.getMonth() - 3)
      const endDate = new Date()
      endDate.setDate(endDate.getDate() - 1)
      this.form.createDate = [this.formatDate(startDate), this.formatDate(endDate)]

      // A001: 初期化
      document.getElementById('ifaContractNoteCustomerLedgerAcquireInitializeA001').click()
    },
    // A001: 初期化のレスポンス処理
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    // A001: 初期化のレスポンスエラー処理
    responseErrorHandlerA001(response) {
      this.$_logDebug('A001', response)
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // A002: 表示のレスポンス処理
    responseHandlerA002(response) {
      // 取引日記帳・顧客勘定元帳一覧をグリッドテーブルに表示
      this.pqGridOption.dataModel.data = response.dataList.sort((a, b) => {
        return b.createDateA - a.createDateA
      })
      this.$nextTick(() => {
        this.$refs['grid'].refreshView()
      })
    },
    // A002: 表示のレスポンスエラー処理
    responseErrorHandlerA002(response) {
      this.$_logDebug('A002', response)
    },
    // A003: ダウンロードのレスポンス処理
    responseHandlerA003(response) {
      this.$_logDebug('A003', response)
    },
    // A003: ダウンロードのレスポンスエラー処理
    responseErrorHandlerA003(response) {
      this.$_logDebug('A003', response)
    },
    handleClick(ui) {
      if (ui.dataIndx === 'dl') {
        this.form.tradeDiaryCustomerLedger = {
          fileDirectory: this.form.fileDirectory, // ファイルディレクトリ
          ledgerName: ui.rowData['targetDoc'], // 帳票名
          ledgerClass: ui.rowData['docClass'], // 帳票種別
          createDate: ui.rowData['createDateB'], // 作成日
          downloadFileName: ui.rowData['downloadFileName'] // ダウンロードファイル名
        }
        document.getElementById('ifaContractNoteCustomerLedgerAcquireGetPDFA003').click()
      }
    },
    // 日付(Dateオブジェクト)を文字列に変換する
    formatDate(date) {
      date = date || new Date()
      return date.toLocaleDateString('ja-JP', { year: 'numeric', month: '2-digit', day: '2-digit' })
    },
    // 日付のフォーマットをYYYY/MM/DD → YYYYMMDDに変更
    getYYYYMMDDFormat(date) {
      return (!date ? '' : date.split('/').join(''))
    },
    // 作成日のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.作成日（From）とリクエスト.作成日（To）の差が3ヶ月より大きい
      if (validateDateRangeFromTo(this.form.createDate, 3)) {
        callback(getMessage('errors.dateRange', ['作成日に', '3ヶ月']))
        return
      }
      // OK
      callback()
    }
  }
}

const columns = [
  { title: '作成日', dataType: 'string', dataIndx: 'createDateA', width: '234', halign: 'center', align: 'left',
    render: function(ui) {
      return getFormattedDateValue(ui.rowData.createDateA) || '-'
    }
  },
  { title: '作成日B', dataType: 'string', dataIndx: 'createDateB', hidden: true },
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '260', halign: 'center', align: 'center' },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '400', halign: 'center', align: 'left' },
  { title: '対象帳簿', dataType: 'string', dataIndx: 'targetDoc', width: '400', halign: 'center', align: 'left' },
  { title: '対象帳簿コードID', dataType: 'string', dataIndx: 'docClass', hidden: true },
  { title: 'ダウンロードファイル名', dataType: 'string', dataIndx: 'downloadFileName', hidden: true },
  { title: 'DL', dataType: 'string', dataIndx: 'dl', width: '245', halign: 'center', align: 'center',
    render: function(ui) {
      const dl = ui.rowData.dl
      if (!dl) {
        return '-'
      } else {
        return `<a><span style='color: #409EFF; text-decoration: underline; text-underline-offset: 0.2em;'>${dl}</span></a>
        <style>
          a:hover {
            cursor: pointer;
          }
        </style>`
      }
    }
  },
  { title: '&nbsp;', dataIndx: '', width: 0, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function() {
      return ''
    }
  }
]
</script>

<style scoped>
.ifa-contract-note-customer-leger-acuire__wrapper {
  width: 100%;
  overflow-x: auto;
    :deep(.form_label) .el-form-item__label {
    width: 135px;
  }
  .form_button {
    padding: 0 2rem 0 2rem;
  }
  .content-card {
    margin: 0.5rem 1rem;
  }
  .filter-container {
    margin: 1rem 0 1rem;
    --created-date__message--top-margin: 0rem;
    &:has(.is-error) {
      /* バリデーションエラー表示中に文字が重ならないようにする */
      --created-date__message--top-margin: 0.6rem;
    }
  }
  .created-date__message {
    width: 420px; /* 帳簿種別の表示位置を調整する */
    white-space: nowrap;
    margin: var(--created-date__message--top-margin) 0 1rem 9.5rem;
  }
}
</style>
