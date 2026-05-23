<template>
  <!-- 画面名：支払通知書ダウンロード -->
  <div>
    <screen-title :text="form.pageTitle.name"></screen-title>
    <el-form
      ref="form"
      :model="form"
      :inline="true"
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

              <!-- 対象年月/ -->
              <ifa-month-range-picker
                v-model="form.dateYmRange"
                size="small"
                label="対象年月"
                prop="dateYmRange"
                :picker-options="yeahMonthPickerOptions"
                :required="true"
                :domain="IfaDate6YearMonthKanjiDomainModel"
              ></ifa-month-range-picker>
              <div style="margin-left: 150px">
                {{ form.periodAlert }}
              </div>
            </el-col>
          </el-row>
          <!-- 検索用ボタン -->
          <el-row style="margin-left: 20px; margin-top: 20px;">
            <el-col>
              <span
                id="indicator-display"
                class="form_button"
              >
                <ifa-button
                  id="btnSearch"
                  text="表示"
                  width="90"
                  search
                  small
                  action-type="requestAction"
                  action-id="SUB0405-01#A002"
                  :form="formRef"
                  :request-model="A002RequestModel"
                  @response-handler="responseHandlerA002($event)"
                  @response-error-handler="responseErrorHandlerA002($event)"
                ></ifa-button>
              </span>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClickA003"
          ></grid-table>
        </el-card>
      </el-row>
    </el-form>
    <ifa-requester
      id="ifaPayNotificationDocDownloadInitializeA001"
      action-id="SUB0405-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaPayNotificationDocDownloadDownloadPdfA003"
      action-id="SUB0405-01#A003"
      action-type="outputPdfAction"
      :request-model="A003RequestModel"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaDate6YearMonthKanjiDomainModel from '@/resource/domain/IfaDate6YearMonthDomainModel.json'
import { IfaPayNotificationDocDownloadFormModel } from './js/IfaPayNotificationDocDownloadFormModel'
import { IfaPayNotificationDocDownloadA002RequestModel } from './js/IfaPayNotificationDocDownloadA002RequestModel'
import { IfaPayNotificationDocDownloadA003RequestModel } from './js/IfaPayNotificationDocDownloadA003RequestModel'
import { getFormattedDateTimeValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaDate6YearMonthKanjiDomainModel: IfaDate6YearMonthKanjiDomainModel,
      form: new IfaPayNotificationDocDownloadFormModel(),
      formRef: {},
      result: [],
      pqGridOption: getConvertedOption(obj),
      yeahMonthPickerOptions: {
        disabledDateFrom(date) {
          // 最小値: From、Toともに2年前の年月
          // 最大値: From、Toともに前月の年月
          const twoYearsAgo = monthsBefore(new Date(), 24)
          const lastMonth = monthsBefore(new Date(), 1)
          return date < twoYearsAgo || date > lastMonth
        },
        disabledDateTo(date) {
          // 最小値: From、Toともに2年前の年月
          // 最大値: From、Toともに前月の年月
          const twoYearsAgo = monthsBefore(new Date(), 24)
          const lastMonth = monthsBefore(new Date(), 1)
          return date < twoYearsAgo || date > lastMonth
        }
      },
      pdfDlParam: {
        dateYm: '', // 年月
        brokerCode: '', // 仲介業者コード
        versionNumber: '', // バージョン番号
        fileDirectory: '', // ファイルディレクトリ
        beforeSearchTargetDateYmFrom: '', // 前回検索時の対象年月From
        beforeSearchTargetDateYmTo: '', // 前回検索時の対象年月To
        beforeSearchBrokerCode: '', // 前回検索時の仲介業者コード
        beforeSearchChkBrokerCodeExclude: '' // 前回検索時の仲介業者除外
      }
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaPayNotificationDocDownloadA002RequestModel(this.form)
    },
    A003RequestModel() {
      return new IfaPayNotificationDocDownloadA003RequestModel(this.pdfDlParam)
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
    // Fromは3ヶ月前の年月
    const fromDate = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
    // Toは前月の年月
    const toDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
    this.form.dateYmRange = [this.formatYearMonth(fromDate), this.formatYearMonth(toDate)] // 初期値：Fromは3ヶ月前の年月、Toは前月の年月
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaPayNotificationDocDownloadInitializeA001').click()
      })
    },

    // 仲介業者支払通知書の格納先ファイルディレクトリを取得
    responseHandlerA001(response) {
      this.form.fileDirectory = response.dataList[0].fileDirectory
    },
    responseErrorHandlerA001(error) {
      if (error.errorLevel === -1) {
        const errorInfo = {
          title: this.form.pageTitle.name,
          message: error.message
        }
        this.$emit('initializeError', errorInfo)
      }
    },

    // 仲介業者支払通知書情報を取得
    responseHandlerA002(response) {
      Object.assign(this.form, response.dataList[0])
      this.pqGridOption.dataModel.data = response.dataList[0].brokerPayNotificationDocInfoDateList
      this.$refs['gridTable'].refreshView()
    },
    responseErrorHandlerA002(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      }
    },
    // ダウンロード
    handleClickA003(ui) {
      if (ui.dataIndx === 'dl') {
        this.pdfDlParam.dateYm = ui.rowData.dateYm
        this.pdfDlParam.brokerCode = ui.rowData.brokerCode
        this.pdfDlParam.versionNumber = ui.rowData.versionNumber
        this.pdfDlParam.fileDirectory = this.form.fileDirectory
        this.pdfDlParam.beforeSearchTargetDateYmFrom = this.form.beforeSearchTargetDateYmFrom
        this.pdfDlParam.beforeSearchTargetDateYmTo = this.form.beforeSearchTargetDateYmTo
        this.pdfDlParam.beforeSearchBrokerCode = this.form.beforeSearchBrokerCode
        this.pdfDlParam.beforeSearchChkBrokerCodeExclude = this.form.beforeSearchChkBrokerCodeExclude
        this.$nextTick(() => {
          document.getElementById('ifaPayNotificationDocDownloadDownloadPdfA003').click()
        })
      }
    },
    //　年月を文字列に変換する
    formatYearMonth(date) {
      date = date || new Date()
      return date.getFullYear() + '/' +
            (('0' + (date.getMonth() + 1)).slice(-2))
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '支払通知書一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false,
  pageModel: {
    type: 'local',
    curPage: 1,
    rPP: 30,
    rPPOptions: []
  }
}

obj.colModel = [
  {
    title: '年月',
    dataType: 'string',
    dataIndx: 'dateYm',
    width: '120',
    editable: false,
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.dateYm) {
        return getFormattedMonthValue(ui.rowData.dateYm, 'date6YearMonth')
      } else {
        return '-'
      }
    }
  },
  {
    title: '仲介業者コード',
    dataType: 'string',
    dataIndx: 'brokerCode',
    width: '130',
    halign: 'center',
    align: 'center'
  },
  {
    title: '仲介業者名',
    dataType: 'string',
    dataIndx: 'brokerName',
    width: '600',
    halign: 'center',
    align: 'left'
  },
  {
    title: '初回確認日時',
    dataType: 'string',
    dataIndx: 'firstConfirmDateTime',
    width: '200',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.firstConfirmDateTime) {
        return getFormattedDateTimeValue(ui.rowData.firstConfirmDateTime, 'datetime12')
      } else {
        return '-'
      }
    }
  },
  {
    title: '最終確認日時',
    dataType: 'string',
    dataIndx: 'lastConfirmDateTime',
    width: '200',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      if (ui.rowData.lastConfirmDateTime) {
        return getFormattedDateTimeValue(ui.rowData.lastConfirmDateTime, 'datetime12')
      } else {
        return '-'
      }
    }
  },
  {
    title: 'DL',
    dataType: 'string',
    dataIndx: 'dl',
    width: '180',
    halign: 'center',
    align: 'center',
    render: function(ui) {
      const dl = ui.rowData.dl
      if (dl) {
        return `<span class='grid-link'><a>` + dl + `</a></span> 
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
        </style>`
      } else return '-'
    }
  }
]
</script>

<style scoped>
.form_button {
  padding: 0.4rem 2rem 1.2rem 2.3rem;
}
.filter-container {
  margin: 1rem 0 1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
</style>
