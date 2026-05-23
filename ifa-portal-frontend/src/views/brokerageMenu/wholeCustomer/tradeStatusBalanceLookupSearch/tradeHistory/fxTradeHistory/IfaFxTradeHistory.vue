<template>
  <!-- 画面名：為替取引履歴 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <!-- 検索条件 -->
    <el-card
      class="content-card"
      shadow="always"
    >
      <div class="filter-container">
        <el-form
          ref="form"
          :model="form"
          :inline="true"
          :rules="rules"
        >
          <el-row>
            <el-col
              :span="23"
            >
              <ifa-common-search
                ref="commonSearch"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :broker-code-validate="false"
                :emp-code-validate="false"
                :course-validate="true"
                original-screen-id="SUB020302_0202-01"
                @mediate-user-privacy="handleMediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
            <el-col
              :span="1"
              class="right_icon"
            >
              <ifa-balloon-icon
                v-if="checkFxTradeHistoryComment"
                :key="iconKey"
                icon-type="info"
                :message="form.fxTradeHistoryComment"
              ></ifa-balloon-icon>
            </el-col>
          </el-row>

          <el-row>
            <el-form-item
              class="form_label period_error_width right_margin"
            >
              <ifa-date-range-picker
                v-model="form.periodDate"
                unlink-panels
                label="期間指定"
                prop="periodDate"
                required
                :picker-options="pickerOptions"
              ></ifa-date-range-picker>
            </el-form-item>
            <el-form-item
              class="form_label"
            >
              <ifa-input-select
                v-model="form.currency"
                code-list-id="original"
                :original-list="currencyCodeListOption"
                label="通貨"
                style="width: 180px;"
              ></ifa-input-select>
            </el-form-item>
          </el-row>
          <el-row class="form_row">
            <div style="padding-top: 13px; padding-left: 151px;">※期間は6ヶ月以内を指定してください。（過去1年間の履歴を照会いただけます。）</div>
          </el-row>

          <el-row style="padding-left: 46px;">
            <el-col>
              <ifa-button
                id="btnDisplay"
                icon="Search"
                text="表示"
                width="90"
                small
                :request-model="IfaFxTradeHistoryA002RequestModel"
                :form="formRef"
                action-id="SUB020302_0202-01#A002"
                action-type="requestAction"
                @response-handler="displayA002($event)"
              ></ifa-button>
              <ifa-button
                text="クリア"
                width="90"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="clearA003"
              ></ifa-button>
            </el-col>
          </el-row>

        </el-form>
      </div>
    </el-card>

    <!-- 一覧表示 -->
    <el-card
      class="content-card"
      shadow="always"
      style="width: auto !important;"
    >
      <el-row class="grid-button-area">
        <ifa-button
          id="btnCsvDownload"
          text="CSV出力"
          width="90"
          small
          :disabled="disabledCsvBtn"
          :form="formRef"
          :request-model="IfaFxTradeHistoryA004RequestModel"
          action-id="SUB020302_0202-01#A004"
          action-type="outputCsvAction"
          :csv-file-name="form.title.name"
        ></ifa-button>
      </el-row>
      <el-row>
        <grid-table
          ref="pqGrid"
          :auto-refresh="false"
          :options="pqGridOption"
        ></grid-table>
      </el-row>
    </el-card>
  </div>
  <ifa-requester
    id="IfaFxTradeHistoryA001"
    action-id="SUB020302_0202-01#A001"
    action-type="requestAction"
    @response-handler="initializeA001($event)"
    @response-error-handler="initializeErrorA001($event)"
  ></ifa-requester>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaFxTradeHistoryFormModel } from './js/IfaFxTradeHistoryFormModel'
import { IfaFxTradeHistoryA002RequestModel } from './js/IfaFxTradeHistoryA002RequestModel'
import { IfaFxTradeHistoryA004RequestModel } from './js/IfaFxTradeHistoryA004RequestModel'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
export default {
  components: { GridTable, IfaCommonSearch, screenTitle },
  emits: ['initializeError'],
  data() {
    return {
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      form: new IfaFxTradeHistoryFormModel(),
      rules: {
        periodDate: [{ type: 'array', validator: this.periodDateValidator }]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '前営業日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          }, {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const businessDate = new Date(this.previousBusinessDay)
              const endDate =
               businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
              return [startDate, endDate]
            }
          }, {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date()
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }
        ]
      },
      currencyCodeListOption: [],
      disabledCsvBtn: true,
      iconKey: 0
    }
  },
  computed: {
    IfaFxTradeHistoryA002RequestModel() {
      return new IfaFxTradeHistoryA002RequestModel(this.form)
    },
    IfaFxTradeHistoryA004RequestModel() {
      return new IfaFxTradeHistoryA004RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    checkFxTradeHistoryComment() {
      if (this.form.fxTradeHistoryComment) {
        return this.form.fxTradeHistoryComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume) {
      if (!resume) {
        // 初期表示時のみA001初期化処理を実行
        this.$nextTick(() => {
          document.getElementById('IfaFxTradeHistoryA001').click()
        })
        // カレンダのデフォルトとして前営業日をセットする
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      }

      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView(true)
      this.disabledCsvBtn = true
      this.iconKey++
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      // 通貨コードリストに「全通貨」を追加
      this.form.currencyCodeList.unshift({ key: '', name: '全通貨' })
      this.currencyCodeListOption = this.form.currencyCodeList.map(item => ({
        key: item.key,
        value: item.name
      }))
    },
    initializeErrorA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    displayA002(response) {
      if (response.dataList[0]?.fxTradeHistoryList) {
        this.pqGridOption.dataModel.data = response.dataList[0].fxTradeHistoryList
        if (response.dataList[0].fxTradeHistoryList.length > 0) {
          this.disabledCsvBtn = false
        } else {
          this.disabledCsvBtn = true
        }
      } else {
        this.pqGridOption.dataModel.data = []
        this.disabledCsvBtn = true
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    // クリアボタン押下
    clearA003() {
      this.$refs['form']?.clearValidate()
      this.$refs.commonSearch.formClear()
      this.form.currency = ''
      // カレンダのデフォルトとして前営業日をセットする
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
    },
    // 日付(yyyy/MM/dd形式)を Date に変換する
    parseDate(dateStr) {
      const date = new Date()
      const params = dateStr.split('/')
      date.setFullYear(params[0], params[1] - 1, params[2])
      date.setHours(0, 0, 0, 0)
      return date
    },
    // 期間指定のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
      if (this.form.periodDate.length > 0) {
        // 6ヶ月前の日付を算出
        const startDate = this.parseDate(this.form.periodDate[0])
        const endDate = this.parseDate(this.form.periodDate[1])
        const y = endDate.getFullYear()
        const m = endDate.getMonth()
        let d = endDate.getDate()
        const temp = new Date(y, m - 5, 0)
        const lastD = temp.getDate()
        if (d > lastD) {
          d = lastD
        }
        const beforeSixMonth = new Date()
        beforeSixMonth.setFullYear(y, m - 6, d)
        beforeSixMonth.setHours(0, 0, 0, 0)

        // 入力チェック
        if (startDate < beforeSixMonth) {
          callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
          return
        }
      }

      // OK
      callback()
    },
    handleMediateUserPrivacy(data) {
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('brokerageBranchCode', false)
        this.setHidden('brokerBranchName', false)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('brokerageBranchCode', true)
        this.setHidden('brokerBranchName', true)
      }
      this.$refs['pqGrid'].refreshView(true)
      this.disabledCsvBtn = true
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '為替取引履歴',
  flexHeight: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  { title: '仲介業者コード', minWidth: 120, dataType: 'string', dataIndx: 'brokerCode', editable: false, halign: 'center', align: 'center' },
  { title: '仲介業者名', minWidth: 170, dataType: 'string', dataIndx: 'brokerName', editable: false, halign: 'center', align: 'left' },
  { title: '営業員コード', minWidth: 110, dataType: 'string', dataIndx: 'empCode', editable: false, halign: 'center', align: 'center' },
  { title: '営業員名', minWidth: 140, dataType: 'string', dataIndx: 'brokerChargeName', editable: false, halign: 'center', align: 'left' },
  { title: '部店', minWidth: 60, dataType: 'string', dataIndx: 'buten', editable: false, halign: 'center', align: 'center' },
  { title: '口座番号', minWidth: 100, dataType: 'string', dataIndx: 'accountNumber', editable: false, halign: 'center', align: 'left',
    render: function(ui) {
      if (ui.rowData.accountNumber) {
        return ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7)
      } else {
        return '-'
      }
    }
  },
  { title: '扱者コード', minWidth: 100, dataType: 'string', dataIndx: 'dealerNumber', editable: false, halign: 'center', align: 'center' },
  { title: '取引コース', minWidth: 160, dataType: 'string', dataIndx: 'courseName', editable: false, halign: 'center', align: 'left' },
  { title: '顧客名(漢字)', minWidth: 110, dataType: 'string', dataIndx: 'customerNameKanji', editable: false, halign: 'center', align: 'left' },
  { title: '顧客名(カナ)', minWidth: 110, dataType: 'string', dataIndx: 'customerNameKana', editable: false, halign: 'center', align: 'left' },
  { title: '約定日', minWidth: 90, dataType: 'string', dataIndx: 'tradeDate', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      if (ui.rowData.tradeDate) {
        return getFormattedDateValue(ui.rowData.tradeDate)
      } else {
        return '-'
      }
    }
  },
  { title: '受渡日', minWidth: 90, dataType: 'string', dataIndx: 'settlementDate', editable: false, halign: 'center', align: 'center',
    render: function(ui) {
      if (ui.rowData.settlementDate) {
        return getFormattedDateValue(ui.rowData.settlementDate)
      } else {
        return '-'
      }
    }
  },
  { title: '取引種別', minWidth: 110, dataType: 'string', dataIndx: 'tradeTypeName', editable: false, halign: 'center', align: 'center' },
  { title: '通貨', minWidth: 90, dataType: 'string', align: 'center', dataIndx: 'currency', editable: false, halign: 'center' },
  { title: '為替レート', minWidth: 130, dataType: 'string', align: 'right', dataIndx: 'fxRate', editable: false, halign: 'center',
    render: function(ui) {
      if (ui.rowData.fxRate) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate)
      } else {
        return '-'
      }
    }
  },
  { title: '為替スプレッド', minWidth: 130, dataType: 'string', align: 'right', dataIndx: 'exchangeSpread', editable: false, halign: 'center',
    render: function(ui) {
      if (ui.rowData.exchangeSpread) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.exchangeSpread)
      } else {
        return '-'
      }
    }
  },
  { title: '円額', minWidth: 110, dataType: 'integer', align: 'right', dataIndx: 'yenAmount', editable: false, halign: 'center',
    render: function(ui) {
      if (ui.rowData.yenAmount) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenAmount)
      } else {
        return '-'
      }
    }
  },
  { title: '外貨額', minWidth: 110, dataType: 'integer', align: 'right', dataIndx: 'foreignAmount', editable: false, halign: 'center',
    render: function(ui) {
      if (ui.rowData.foreignAmount) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignAmount)
      } else {
        return '-'
      }
    }
  },
  { title: '為替手数料', minWidth: 110, dataType: 'integer', align: 'right', dataIndx: 'exchangeFee', editable: false, halign: 'center',
    render: function(ui) {
      if (ui.rowData.exchangeFee) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.exchangeFee)
      } else {
        return '-'
      }
    }
  },
  { title: '支店コード', minWidth: 100, dataType: 'string', dataIndx: 'brokerageBranchCode', editable: false, halign: 'center', align: 'center' },
  { title: '支店名', minWidth: 170, dataType: 'string', dataIndx: 'brokerBranchName', editable: false, halign: 'center', align: 'left' }
]

obj.pageModel = { type: 'local', rPP: 30, rPPOptions: [] }
</script>

<style scoped>
:deep(.period_error_width) .el-form-item__error {
  width: 370px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem 1rem 1rem;
}
.form-area__section {
  height: auto;
  padding: 0.5rem 0;
}
.form_row {
  margin-bottom: 2rem;
}
.search-form__item {
  margin: 0 0.2rem;
  width: 200px;
}
.middle_input {
  width: 120px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
}
.right_icon {
  text-align: right;
  margin-left: auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}
.grid-button-area {
  margin-bottom: 10px;
}
.ifa-date-picker.small.el-range-editor.el-input__inner {
  position: relative;
}
.right_margin :deep(.el-form-item) {
  margin-right: 149px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
