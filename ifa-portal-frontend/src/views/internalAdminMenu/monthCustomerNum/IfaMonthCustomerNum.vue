<template>
  <!-- 画面名：月末口座数 -->
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
            </el-col>
            <!-- バルーンアイコン -->
            <el-col
              :span="1"
              class="right_icon"
              style="padding-top: 12px;"
            >
              <ifa-balloon-icon
                v-if="checkComment"
                icon-type="info"
              >
                <div v-html="form.comment"></div>
              </ifa-balloon-icon>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <!-- 対象年月 -->
              <ifa-month-range-picker
                v-model="form.dateYmRange"
                size="small"
                label="対象年月"
                prop="dateYmRange"
                :required="true"
                :domain="IfaDate6YearMonthDomainModel"
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
                  action-id="SUB0407_01#A002"
                  :form="formRef"
                  :request-model="A002RequestModel"
                  @response-handler="responseHandlerA002($event)"
                  @response-error-handler="responseErrorHandlerA002($event)"
                ></ifa-button>
                <ifa-button
                  id="btnClear"
                  name="btnClear"
                  text="クリア"
                  width="90"
                  color="secondary"
                  small
                  action-type="originalAction"
                  @app-action-handler="clearA003"
                ></ifa-button>
              </span>
            </el-col>
          </el-row>
        </div>
      </el-card>
      <el-row>
        <el-card class="content-card">
          <el-row v-if="isDisabledButton"
                  class="grid_button_area"
          >
            <ifa-button
              id="btnCsvDownload"
              name="btnCsvDownload"
              text="CSV出力"
              :disabled="csvBtnDisabled"
              color="primary"
              small
              width="90"
              csv-file-name="MONTH_CUSTOMER_NUM"
              :form="formRef"
              :request-model="A004RequestModel"
              action-id="SUB0407_01#A004"
              action-type="outputCsvAction"
              :is-check-csv-download-privacy-confirmation="false"
            ></ifa-button>
          </el-row>
          <el-row>
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClickA005"
            ></grid-table>
            <ifa-button
              id="brokerCustomerCsvOutputA005"
              text="月末顧客データ出力"
              style="display: none;"
              :csv-file-name="csvFileName"
              :pre-request-handler="setCsvFileName"
              :form="formRef"
              :request-model="A005RequestModel"
              action-id="SUB0407_01#A005"
              action-type="outputCsvAction"
              :is-check-csv-download-privacy-confirmation="false"
              @response-handler="responseHandlerA005($event)"
            ></ifa-button>
          </el-row>
        </el-card>
      </el-row>
    </el-form>
    <ifa-requester
      id="ifaMonthCustomerNumInitializeA001"
      action-id="SUB0407_01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
    <ifa-ok-cancel-dialog
      :is-visible="confirmVisible"
      title="個人情報について"
      message="管理系メニューの個人情報管理台帳作成画面にて、個人情報管理の記述をお願いします。※内部管理責任者メニュー＞個人情報管理＞個人情報管理台帳をクリックすると「個人情報管理台帳作成画面」が表示されます。"
      @close-modal-ok="handleConfirm"
      @close-modal-cancel="confirmVisible=false"
    ></ifa-ok-cancel-dialog>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaDate6YearMonthDomainModel from '@/resource/domain/IfaDate6YearMonthDomainModel.json'
import { IfaMonthCustomerNumFormModel } from './js/IfaMonthCustomerNumFormModel'
import { IfaMonthCustomerNumA002RequestModel } from './js/IfaMonthCustomerNumA002RequestModel'
import { IfaMonthCustomerNumA004RequestModel } from './js/IfaMonthCustomerNumA004RequestModel'
import { IfaMonthCustomerNumA005RequestModel } from './js/IfaMonthCustomerNumA005RequestModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle,
    IfaOkCancelDialog
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaDate6YearMonthDomainModel: IfaDate6YearMonthDomainModel,
      form: new IfaMonthCustomerNumFormModel(),
      formRef: {},
      result: [],
      pqGridOption: getConvertedOption(obj),
      brokerCustomerCsvOutputParam: {
        dateYm: '', // 年月
        brokerCode: '' // 仲介業者コード
      },
      csvFileName: '', // ダウンロードファイル
      confirmVisible: false
    }
  },
  computed: {
    A002RequestModel() {
      return new IfaMonthCustomerNumA002RequestModel(this.form)
    },
    A004RequestModel() {
      return new IfaMonthCustomerNumA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaMonthCustomerNumA005RequestModel(this.brokerCustomerCsvOutputParam)
    },
    csvBtnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
    },
    isDisabledButton() {
      return this.$store.getters.userAccount.medUsers.privId !== '3'
    },
    checkComment() {
      if (this.form.comment) {
        return this.form.comment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
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
    // Fromは前月の年月
    const fromDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
    fromDate.setMonth(fromDate.getMonth())
    // Toは前月の年月
    const toDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
    toDate.setMonth(toDate.getMonth())
    // 初期値：Fromは前月の年月、Toは前月の年月
    this.form.dateYmRange = [this.formatYearMonth(fromDate), this.formatYearMonth(toDate)]
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView(true)
        document.getElementById('ifaMonthCustomerNumInitializeA001').click()
      })
    },
    handleConfirm() {
      this.$nextTick(() => {
        document.getElementById('brokerCustomerCsvOutputA005').click()
      })
    },
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
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

    // 月末口座数リストを取得
    responseHandlerA002(response) {
      Object.assign(this.form, response.dataList)
      this.pqGridOption.dataModel.data = response.dataList.map(item => ({
        ...item,
        csvBtnEnabled: this.$store.getters.userAccount.medUsers.privId === '3'
      }))
      this.$refs['gridTable'].refreshView()
    },
    responseErrorHandlerA002(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      }
    },
    // クリアボタンによる初期化
    clearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['form']?.clearValidate()
      this.dateYmRangeSet()
    },
    // 仲介業者顧客リストを取得
    responseHandlerA005(data) {
      this.confirmVisible = false
    },
    // ダウンロード
    handleClickA005(ui) {
      if (ui.dataIndx === 'accountNum' && this.isDisabledButton) {
        this.brokerCustomerCsvOutputParam.dateYm = ui.rowData.dateYm
        this.brokerCustomerCsvOutputParam.brokerCode = ui.rowData.brokerCode
        const accountNumWithComma = ui.rowData.accountNum
        const accountNumWithoutComma = accountNumWithComma.replace(/,/g, '')
        if (ui.rowData.downloadFlg === '0' && Number(accountNumWithoutComma) > 0) {
          // 個人情報管理台帳ダウンロード要否：trueの場合、個人情報管理台帳へ登録
          if (this.$store.getters.userAccount.csvDownloadPrivacyConfirmationRequired) {
            this.confirmVisible = true
          } else {
          // CSVダウンロード
            document.getElementById('brokerCustomerCsvOutputA005').click()
          }
        }
      }
    },
    //　年月を文字列に変換する
    formatYearMonth(date) {
      date = date || new Date()
      return date.getFullYear() + '/' + (('0' + (date.getMonth() + 1)).slice(-2))
    },
    dateYmRangeSet() {
      // Fromは前月の年月
      const fromDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
      fromDate.setMonth(fromDate.getMonth())
      // Toは前月の年月
      const toDate = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
      toDate.setMonth(toDate.getMonth())
      // 初期値：Fromは前月の年月、Toは前月の年月
      this.form.dateYmRange = [this.formatYearMonth(fromDate), this.formatYearMonth(toDate)]
    },
    setCsvFileName() {
      const brokerCode = this.brokerCustomerCsvOutputParam.brokerCode
      const dateYm = this.brokerCustomerCsvOutputParam.dateYm
      // csvファイル名の設定
      this.csvFileName = 'MONTH_CUSTOMER_' + brokerCode + '_' + dateYm.replace(/\//g, '')
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '仲介業者月末口座数',
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
    title: '対象年月',
    dataType: 'string',
    dataIndx: 'dateYm',
    width: '120',
    editable: false,
    halign: 'center',
    align: 'center'
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
    width: '300',
    halign: 'center',
    align: 'center'
  },
  {
    title: '月末口座数',
    dataType: 'string',
    dataIndx: 'accountNum',
    width: '680',
    halign: 'center',
    align: 'right',
    render: function(ui) {
      const downloadFlg = ui.rowData.downloadFlg
      const accountNumWithComma = ui.rowData.accountNum
      const accountNumWithoutComma = accountNumWithComma.replace(/,/g, '')
      if (downloadFlg === '0' && Number(accountNumWithoutComma) > 0 && !ui.rowData.csvBtnEnabled) {
        return `<span class='grid-link'><a>` + accountNumWithComma + `</a></span> 
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
      } else {
        return ui.rowData.accountNum ? ui.rowData.accountNum : '-'
      }
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

.grid_button_area {
  margin-bottom: 10px;
}
</style>
