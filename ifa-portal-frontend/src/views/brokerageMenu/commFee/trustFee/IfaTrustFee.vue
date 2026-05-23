<template>
  <!-- 画面名：信託報酬 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div id="contentAreaInput">
        <el-card class="content-card">
          <el-form
            id="inpTrustRewardForm"
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <div class="filter-container">
              <el-row>
                <el-col
                  :span="23"
                >
                  <ifa-common-search
                    v-show="form.detailCustomerCurrencyCountingUnit !== '2'"
                    ref="commonSearchPt1"
                    key="commonSearchPt1"
                    :form="form"
                    display-pattern="pt1"
                    list-pattern="pt2"
                    :broker-code-validate="false"
                    :emp-code-validate="false"
                    :course-validate="true"
                    original-screen-id="SUB020503-01"
                    @mediate-user-privacy="handleMediateUserPrivacy"
                  ></ifa-common-search>
                  <ifa-common-search
                    v-show="form.detailCustomerCurrencyCountingUnit === '2'"
                    ref="commonSearchPt6"
                    key="commonSearchPt6"
                    :form="form"
                    display-pattern="pt6"
                    list-pattern="pt2"
                    :broker-code-validate="false"
                    :emp-code-validate="false"
                    :course-validate="true"
                    original-screen-id="SUB020503-01"
                    @mediate-user-privacy="handleMediateUserPrivacy"
                  ></ifa-common-search>

                </el-col>
                <el-col
                  :span="1"
                  class="right_icon"
                >
                  <ifa-balloon-icon
                    v-if="checkTrustFeeComment"
                    :key="iconKey"
                    icon-type="info"
                    :message="form.trustFeeComment"
                  ></ifa-balloon-icon>
                </el-col>
              </el-row>

              <!-- 集計単位 -->
              <el-row>
                <el-col>
                  <div style="display: inline-flex;">
                    <ifa-input-radio
                      id="dailyMonthlyCountingUnit"
                      v-model="form.dailyMonthlyCountingUnit"
                      code-list-id="DAILY_MONTHLY_COUNTING_UNIT"
                      is-button
                      :select-pattern="1"
                      :disp-pattern="2"
                      label="集計単位"
                      required
                      prop="dailyMonthlyCountingUnit"
                    ></ifa-input-radio>
                  </div>
                  <ifa-input-radio
                    id="detailCustomerCurrencyCountingUnit"
                    v-model="form.detailCustomerCurrencyCountingUnit"
                    code-list-id="DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT"
                    is-button
                    :select-pattern="1"
                    :disp-pattern="1"
                    required
                    prop="detailCustomerCurrencyCountingUnit"
                  ></ifa-input-radio>
                </el-col>
              </el-row>

              <!-- 期間指定 -->
              <el-row>
                <el-col>
                  <div style="display: inline-block; width: 682px;">
                    <ifa-date-range-picker
                      v-show="form.dailyMonthlyCountingUnit !== '1'"
                      v-model="form.periodDate"
                      prop="periodDate"
                      size="small"
                      unlink-panels
                      label="期間指定"
                      label-class="label_period"
                      required
                      class="period_error_width"
                      :picker-options="datePickerOptions"
                    ></ifa-date-range-picker>
                    <ifa-month-range-picker
                      v-show="form.dailyMonthlyCountingUnit === '1'"
                      v-model="form.periodMonth"
                      prop="periodMonth"
                      size="small"
                      unlink-panels
                      label="期間指定"
                      label-class="label_period"
                      required
                      class="period_error_width"
                      :picker-options="monthPickerOptions"
                    ></ifa-month-range-picker>
                  </div>
                  <div style="display: inline-block; width: 446px;">
                    <product-code-select
                      ref="productCodeSelect"
                      list-kind="pt6"
                      id-string="securityClassList"
                      prop="securityClassList"
                      label="証券種別"
                      required
                      @change-selected="form.securityClassList = $event"
                    ></product-code-select>
                  </div>
                  <ifa-input-text
                    id="brandCode"
                    v-model="form.brandCode"
                    type="text"
                    size="small"
                    label="銘柄コード"
                    style="width: 180px;"
                    label-class="label_brand"
                    prop="brandCode"
                    :domain="IfaBrandCode8PeriodDomainModel"
                  ></ifa-input-text>
                </el-col>
              </el-row>

              <el-row>
                <div class="date-info">
                  <div>※期間は6ヶ月以内を指定してください。（照会できる過去履歴は下記の通りです。）</div>
                  <div>2ヶ月：国内投信残高、外貨建MMF残高、投信マイレージ、国内投信つみたてNISA、SBIラップマイレージ、ST信報</div>
                  <div>3ヶ月：外国投信残高</div>
                  <div>2年：外国投信残高(他)</div>
                </div>
              </el-row>

              <!-- 検索用ボタン -->
              <el-row class="form_button">
                <ifa-button
                  id="btnDisplay"
                  icon="Search"
                  text="表示"
                  width="90"
                  small
                  :request-model="IfaTrustFeeA002RequestModel"
                  :form="formRef"
                  action-id="SUB020503-01#A002"
                  action-type="requestAction"
                  @response-handler="displayA002($event)"
                ></ifa-button>

                <ifa-button
                  id="btnTopInputClear"
                  name="btnTopInputClear"
                  text="クリア"
                  width="90"
                  color="secondary"
                  small
                  action-type="originalAction"
                  @app-action-handler="clearA003"
                ></ifa-button>
              </el-row>
              <!-- /検索用ボタン -->
            </div>
          </el-form>
        </el-card>
      </div>
    </div>
    <el-row>
      <el-card class="content-card">
        <el-row class="grid_button_area">
          <ifa-button
            id="btnCsvDownload"
            text="CSV出力"
            width="90"
            small
            :disabled="disabledCsvBtn"
            :form="formRef"
            :request-model="IfaTrustFeeA004RequestModel"
            action-id="SUB020503-01#A004"
            action-type="outputCsvAction"
            :csv-file-name="csvFileName"
            :pre-request-handler="setCsvFileName"
            :is-check-csv-download-privacy-confirmation="requirePiiDialogOnCsvDownload"
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
    </el-row>
    <ifa-requester
      id="IfaTrustFeeA001"
      action-id="SUB020503-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="initializeErrorA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode8PeriodDomainModel from '@/resource/domain/IfaBrandCode8PeriodDomainModel.json'
import { IfaTrustFeeFormModel } from './js/IfaTrustFeeFormModel'
import { IfaTrustFeeA002RequestModel } from './js/IfaTrustFeeA002RequestModel'
import { IfaTrustFeeA004RequestModel } from './js/IfaTrustFeeA004RequestModel'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    ProductCodeSelect,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      formRef: {},
      form: new IfaTrustFeeFormModel(),
      IfaBrandCode8PeriodDomainModel: IfaBrandCode8PeriodDomainModel,
      pqGridOption: getConvertedOption(obj),
      disabledCsvBtn: true,
      rules: {
        periodDate: [{ type: 'array', validator: this.periodDateValidator }],
        periodMonth: [{ type: 'array', validator: this.periodMonthValidator }]
      },
      datePickerOptions: {
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
      monthPickerOptions: {
        shortcuts: [
          {
            text: '当月',
            value: () => {
              const date = new Date(this.$store.getters.requestedTime)
              const thisMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              return [thisMonth, thisMonth]
            }
          },
          {
            text: '前月',
            value: () => {
              const date = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const lastMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              return [lastMonth, lastMonth]
            }
          }
        ]
      },
      iconKey: 0,
      empCodeAutoDispFlag: '',
      csvFileName: ''
    }
  },
  computed: {
    IfaTrustFeeA002RequestModel() {
      return new IfaTrustFeeA002RequestModel(this.form)
    },
    IfaTrustFeeA004RequestModel() {
      return new IfaTrustFeeA004RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    checkTrustFeeComment() {
      if (this.form.trustFeeComment) {
        return this.form.trustFeeComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    },
    requirePiiDialogOnCsvDownload() {
      // 区分.集計単位（明細/顧客/通貨毎）.通貨毎
      const DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_PER_CURRENCY = '2'

      return this.form.detailCustomerCurrencyCountingUnit !== DETAIL_CUSTOMER_CURRENCY_COUNTING_UNIT_PER_CURRENCY
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
          document.getElementById('IfaTrustFeeA001').click()
        })
        // カレンダのデフォルトとして前営業日をセットする
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
        // カレンダのデフォルトとして当月をセットする
        this.yearMonthInitialize()
      }

      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView(true)
      this.disabledCsvBtn = true
      this.iconKey++
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    initializeErrorA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    displayA002(response) {
      if (response.dataList[0]?.trustFeeList) {
        this.pqGridOption.dataModel.data = response.dataList[0].trustFeeList
        if (response.dataList[0].trustFeeList.length > 0) {
          this.disabledCsvBtn = false
        } else {
          this.disabledCsvBtn = true
        }
      } else {
        this.pqGridOption.dataModel.data = []
        this.disabledCsvBtn = true
      }
      // グリッドの表示項目制御
      const info = getViewControllInfo(this.form.dailyMonthlyCountingUnit, this.form.detailCustomerCurrencyCountingUnit)
      const gridCols = this.pqGridOption.colModel
      for (const key in gridCols) {
        gridCols[key].hidden = info[0].hiddenColumn.includes(key)
      }

      // 自動設定判定による表示制御
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (this.empCodeAutoDispFlag !== '0') {
        // 非表示
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('branchCode', true)
        this.setHidden('branchName', true)
      } else {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('branchCode', false)
        this.setHidden('branchName', false)
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    yearMonthInitialize() {
      // 当月を設定する
      const date = new Date()
      const thisMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
      this.form.periodMonth = [thisMonth, thisMonth]
    },
    handleMediateUserPrivacy(data) {
      this.empCodeAutoDispFlag = data.empCodeAutoDispFlag
    },
    // クリアボタンによる初期化
    clearA003() {
      this.$refs['form']?.clearValidate()
      this.$refs.commonSearchPt1.formClear()
      this.$refs.commonSearchPt6.formClear()
      this.form.dailyMonthlyCountingUnit = '0'
      this.form.detailCustomerCurrencyCountingUnit = '0'
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      this.yearMonthInitialize()
      this.$refs['productCodeSelect'].handleCheckAll(true)
      this.form.brandCode = ''
    },
    setCsvFileName() {
      // csvファイル名の設定
      const info = getViewControllInfo(this.form.dailyMonthlyCountingUnit, this.form.detailCustomerCurrencyCountingUnit)
      this.csvFileName = info[0].csvFileName
    },
    periodDateValidator(rule, value, callback) {
      if (this.form.dailyMonthlyCountingUnit === '0') {
        // 日次
        // 以下の条件の時エラー
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
        if (this.form.periodDate.length > 0) {
        // 期間指定（From）の6ヶ月前の日付を算出
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
          if (startDate <= beforeSixMonth) {
            callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
            return
          }
        }
      }
      // OK
      callback()
    },
    periodMonthValidator(rule, value, callback) {
      if (this.form.dailyMonthlyCountingUnit === '1') {
        // 月次
        // 以下の条件の時エラー
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
        if (this.form.periodMonth.length > 0) {
          // 期間指定（From）の6ヶ月前を算出
          const startMonth = this.parseMonth(this.form.periodMonth[0])
          const endMonth = this.parseMonth(this.form.periodMonth[1])
          const beforeSixMonth = new Date()
          beforeSixMonth.setHours(0, 0, 0, 0)
          beforeSixMonth.setDate(1)
          beforeSixMonth.setFullYear(endMonth.getFullYear(), endMonth.getMonth() - 6)

          // 入力チェック
          if (startMonth <= beforeSixMonth) {
            callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
            return
          }
        }
      }
      // OK
      callback()
    },
    // 日付(yyyy/MM/dd形式)を Date に変換する
    parseDate(dateStr) {
      const date = new Date()
      const params = dateStr.split('/')
      date.setFullYear(params[0], params[1] - 1, params[2])
      date.setHours(0, 0, 0, 0)
      return date
    },
    parseMonth(dateStr) {
      const date = new Date()
      const params = dateStr.split('/')
      date.setFullYear(params[0], params[1] - 1, 1)
      date.setHours(0, 0, 0, 0)
      return date
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
  title: '信託報酬',
  flexHeight: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}

obj.colModel = [
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 120, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員コード', dataIndx: 'empCode', width: 110, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '部店', dataIndx: 'buten', width: 70, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '口座番号', dataIndx: 'accountNumber', width: 100, dataType: 'integer', editable: false, halign: 'center', hidden: false },
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 120, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '取引コース', dataIndx: 'course', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名（漢字）', dataIndx: 'customerNameKanji', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名（カナ）', dataIndx: 'customerNameKana', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '証券種別', dataIndx: 'securityClass', width: 180, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '保有日', dataIndx: 'holdingcDate', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) { return ui.rowData.holdingcDate ? getFormattedDateValue(ui.rowData.holdingcDate) : '-' }, hidden: false },
  { title: '保有月', dataIndx: 'baseMonth', width: 80, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) { return ui.rowData.baseMonth ? getFormattedMonthValue(ui.rowData.baseMonth) : '-' }, hidden: false },
  { title: '銘柄コード', dataIndx: 'brandCode', width: 100, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '銘柄名', dataIndx: 'brandName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '数量', dataIndx: 'quantity', width: 100, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.quantity ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.quantity) : '-' } },
  { title: '参考価額', dataIndx: 'referencePrice', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.referencePrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.referencePrice) : '-' } },
  { title: '基準価額', dataIndx: 'price', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.price ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.price) : '-' } },
  { title: '評価額', dataIndx: 'valuation', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.valuation ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.valuation) : '-' } },
  { title: '信託報酬率', dataIndx: 'trustFeeRate', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.trustFeeRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.trustFeeRate) + '%' : '-' } },
  { title: '通貨', dataIndx: 'currency', width: 70, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '為替レート', dataIndx: 'fxRate', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.fxRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate) : '-' } },
  { title: '信託報酬額', dataIndx: 'trustFeeAmount', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.trustFeeAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.trustFeeAmount) : '-' } },
  { title: '支店コード', dataIndx: 'branchCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '支店名', dataIndx: 'branchName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '', dataIndx: 'space', width: 700, dataType: 'string', editable: false, halign: 'center', hidden: false,
    render: function(ui) { return '' } }
]

obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

const getViewControllInfo = (dailyMonthlyCountingUnit, detailCustomerCurrencyCountingUnit) => {
  return viewControllInfoList.filter(function(data) {
    return dailyMonthlyCountingUnit === data.dailyMonthlyCountingUnit && detailCustomerCurrencyCountingUnit === data.detailCustomerCurrencyCountingUnit
  })
}

// 画面制御情報リスト
const viewControllInfoList = [
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '0', csvFileName: '信託報酬_日次_明細', hiddenColumn: ['baseMonth', 'space'] },
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '1', csvFileName: '信託報酬_日次_顧客・商品分類・通貨毎', hiddenColumn: ['baseMonth', 'brandCode', 'brandName', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'space'] },
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '2', csvFileName: '信託報酬_日次_通貨毎', hiddenColumn: ['empCode', 'brokerChargeName', 'buten', 'accountNumber', 'dealerNumber', 'course', 'customerNameKanji', 'customerNameKana', 'baseMonth', 'brandCode', 'brandName', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'branchCode', 'branchName'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '0', csvFileName: '信託報酬_月次累計_明細', hiddenColumn: ['holdingcDate', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'space'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '1', csvFileName: '信託報酬_月次累計_顧客・商品分類・通貨毎', hiddenColumn: ['holdingcDate', 'brandCode', 'brandName', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'space'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '2', csvFileName: '信託報酬_月次累計_通貨毎', hiddenColumn: ['empCode', 'brokerChargeName', 'buten', 'accountNumber', 'dealerNumber', 'course', 'customerNameKanji', 'customerNameKana', 'holdingcDate', 'brandCode', 'brandName', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'branchCode', 'branchName'] }
]

</script>
<style scoped>
:deep(.period_error_width) .el-form-item__error {
  width: 370px;
}
.date-info {
  display: flex;
  flex-direction: column;
  padding-left: 151px;
  padding-bottom: 2rem;
}

.hideBranchCode :deep([name="branchCode"]) {
  display: none;
}

.filter-container {
  margin-top:1rem;
}

.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}

.middle_input {
  width: 120px;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
  vertical-align: top;
}

.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 40px;
}

.el-icon-info {
  font-size: 30px;
}

.form_button {
  padding-left: 46px;
  padding-bottom: 13px;
}

.grid_button_area {
  margin-bottom: 10px;
}

:deep(.label_period) .el-form-item__label {
  width: 135px;
}

:deep(.label_brand) .el-form-item__label {
  width: 100px;
}

.content-card {
  margin: 0.5rem 1rem;
}

:deep(.h_dropdown) {
  margin-left: 0px;
}
:deep(.el-radio-group) {
  vertical-align: top;
  margin-left: 0px;
}

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
