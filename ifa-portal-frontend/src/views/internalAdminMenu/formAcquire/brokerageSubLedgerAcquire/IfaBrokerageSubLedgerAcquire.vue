<template>
  <!-- 画面名：仲介業補助簿取得 -->
  <div>
    <screen-title text="仲介業補助簿取得"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
      :rules="rules"
    >
      <el-card class="content-card">
        <div class="filter-container">
          <el-row>
            <el-col :span="23">
              <!-- 共通検索項目 -->
              <ifa-common-search
                ref="commonSearchItem"
                :form="form"
                display-pattern="pt3"
              ></ifa-common-search>
            </el-col>
          </el-row>

          <el-row>
            <!-- 作成日 -->
            <div class="form_label">
              <ifa-date-range-picker
                ref="datePicker"
                v-model="form.createDate"
                label="作成日"
                size="small"
                prop="createDate"
                unlink-panels
                :required="true"
                :picker-options="pickerOptions"
              ></ifa-date-range-picker>
              <div style="margin-top: 5px; margin-left: 9.5rem;">
                ※期間は3ヶ月以内を指定してください。（保管している全ての帳簿を照会いただけます。）
              </div>
            </div>
          </el-row>

          <!-- 検索用ボタン -->
          <el-row
            style="margin-left: 20px; margin-top: 20px;"
          >
            <span
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
                :pre-request-handler="preRequestHandlerA002"
                :request-model="IfaBrokerageSubLedgerAcquireA002RequestModel"
                action-id="SUB0402_01-01#A002"
                action-type="requestAction"
                @response-handler="responseHandlerA002($event)"
              ></ifa-button>
            </span>
          </el-row>
        </div>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <ifa-requester
      id="ifaBrokerageSubLedgerAcquireinitializeA001"
      action-type="requestAction"
      action-id="SUB0402_01-01#A001"
      @response-handler="responseHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaBrokerageSubLedgerAcquireDownloadA003"
      action-id="SUB0402_01-01#A003"
      action-type="outputPdfAction"
      :request-model="IfaBrokerageSubLedgerAcquireA003RequestModel"
    ></ifa-requester>

  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaBrokerageSubLedgerAcquireFormModel } from './js/IfaBrokerageSubLedgerAcquireFormModel'
import { IfaBrokerageSubLedgerAcquireA002RequestModel } from './js/IfaBrokerageSubLedgerAcquireA002RequestModel'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    screenTitle,
    IfaCommonSearch
  },
  data() {
    return {
      result: [],
      pqGridOption: getConvertedOption(obj),
      form: new IfaBrokerageSubLedgerAcquireFormModel(),
      formRef: {},
      IfaBrokerageSubLedgerAcquireA003RequestModel: {},
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
              // startDateに当月の月初を設定
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに当日を設定
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setHours(0, 0, 0, 0)
              return [startDate, endDate]
            }
          }, {
            text: '1ケ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '2ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 2)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }, {
            text: '3ヶ月前',
            value: () => {
              const startDate = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setDate(endDate.getDate())
              return [startDate, endDate]
            }
          }
        ]
      }
    }
  },
  computed: {
    IfaBrokerageSubLedgerAcquireA002RequestModel() {
      return new IfaBrokerageSubLedgerAcquireA002RequestModel(this.form)
    }
  },
  created() {
    this.pqGridOption.scrollModel = {
      autoFit: true,
      horizontal: true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
    // 本日までの3か月間を取得
    const endDate = new Date(this.$store.getters.requestedTime)
    const startDate = new Date(this.$store.getters.requestedTime)
    startDate.setMonth(startDate.getMonth() - 3)
    endDate.setDate(startDate.getDate())
    this.form.createDate = [this.formatDate(startDate), this.formatDate(endDate)]
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaBrokerageSubLedgerAcquireinitializeA001').click()
      })
      this.$refs.commonSearchItem.formClear()
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
    },
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    preRequestHandlerA002() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
    },
    responseHandlerA002(response) {
      this.$_logDebug(response)
      Object.assign(this.form.brokerageSubLedgerList, response.dataList)
      this.pqGridOption.dataModel.data = response.dataList.sort((a, b) => {
        return b.createDate - a.createDate
      })
      this.$refs['gridTable'].refreshView()
    },
    handleClick(ui) {
      if (ui.dataIndx === 'dl') {
        this.downLoadPdf(ui)
      }
    },
    downLoadPdf(ui) {
      this.$_logDebug(ui)
      this.$nextTick(() => {
        this.IfaBrokerageSubLedgerAcquireA003RequestModel = {
          fileDirectory: this.form.fileDirectory,
          createDate: ui.rowData.createDate,
          fileName: ui.rowData.fileName
        }
        document.getElementById('ifaBrokerageSubLedgerAcquireDownloadA003').click()
      })
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
            (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
            ('0' + date.getDate()).slice(-2) +
            (f ? ' ' + ('0' + date.getHours()).slice(-2) + ':' +
              ('0' + date.getMinutes()).slice(-2) : '')
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
const obj = {
  width: 0,
  height: 0,
  title: '仲介業補助簿取得',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' }
}
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
obj.colModel = [
  { title: '作成日', dataType: 'string', dataIndx: 'createDate', width: '120', editable: false, halign: 'center', minWidth: '85',
    render: function(ui) {
      return ui.rowData.createDate ? getFormattedDateValue(ui.rowData.createDate) : '-'
    } },
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '130', halign: 'center', minWidth: '110' },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '652', halign: 'center', minWidth: '300' },
  { title: '対象商品', dataType: 'string', dataIndx: 'targetSecurity', width: '350', halign: 'center', minWidth: '150' },
  { title: 'DL', dataType: 'string', dataIndx: 'dl', width: '120', halign: 'center', align: 'center',
    render: function(ui) {
      return `<span class='grid-link'><a>` + ui.rowData.dl + `</a></span> 
        <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
           }
         }
        </style>`
    } },
  { title: 'ファイル名', dataType: 'string', dataIndx: 'fileName', hidden: true },
  { title: '&nbsp;', dataType: 'string', dataIndx: 'space', halign: 'center', align: 'center',
    render: function(ui) {
      return ''
    }
  }
]

</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
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
}
:deep(.el-checkbox__label) {
   font-weight: bold;
 }
 .input-area {
  margin-bottom: 0.4rem;
}
</style>
