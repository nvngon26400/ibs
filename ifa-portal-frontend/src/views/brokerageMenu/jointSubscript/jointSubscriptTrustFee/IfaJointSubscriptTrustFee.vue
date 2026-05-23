<template>
  <!-- 画面名：共同募集　信託報酬 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div id="contentAreaInput">
      <el-card class="content-card">
        <el-form
          id="inpJointSubscriptTrustRewardForm"
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
                <ifa-joint-subscript-search
                  ref="commonSearchPt2"
                  key="commonSearchPt2"
                  :form="form"
                  :display-pattern="changePtn"
                  list-pattern="pt2"
                  :broker-code-validate="false"
                  :emp-code-validate="false"
                  :course-validate="true"
                  :is-display-info="false"
                  original-screen-id="SUB0206_03-01"
                  @mediate-user-privacy="handleMediateUserPrivacy"
                ></ifa-joint-subscript-search>
              </el-col>
              <el-col
                :span="1"
              >
                <ifa-balloon-icon
                  v-if="checkCommComment"
                  icon-type="info"
                  :message="form.commComment"
                >
                </ifa-balloon-icon>
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
                    list-kind="pt10"
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
                <div>2ヶ月：国内投信残高、外貨建MMF残高、国内投信つみたてNISA、ST信報</div>
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
                :request-model="IfaJointSubscriptTrustFeeA002RequestModel"
                :form="formRef"
                action-id="SUB0206_03-01#A002"
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
            :request-model="IfaJointSubscriptTrustFeeA004RequestModel"
            action-id="SUB0206_03-01#A004"
            action-type="outputCsvAction"
            :csv-file-name="csvFileName"
            :pre-request-handler="setCsvFileName"
            :is-check-csv-download-privacy-confirmation="isOkCancelDialogVisible"
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
      id="IfaJointSubscriptTrustFeeA001"
      action-id="SUB0206_03-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="initializeErrorA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaJointSubscriptSearch from '@/components/SearchCondition/IfaJointSubscriptSearch.vue'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode8PeriodDomainModel from '@/resource/domain/IfaBrandCode8PeriodDomainModel.json'
import { IfaJointSubscriptTrustFeeFormModel } from './js/IfaJointSubscriptTrustFeeFormModel'
import { IfaJointSubscriptTrustFeeA002RequestModel } from './js/IfaJointSubscriptTrustFeeA002RequestModel'
import { IfaJointSubscriptTrustFeeA004RequestModel } from './js/IfaJointSubscriptTrustFeeA004RequestModel'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    ProductCodeSelect,
    IfaJointSubscriptSearch,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      isOkCancelDialogVisible: true,
      changePtn: 'pt2',
      formRef: {},
      form: new IfaJointSubscriptTrustFeeFormModel(),
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
              const date = new Date()
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
      empCodeAutoDispFlag: '',
      csvFileName: ''
    }
  },
  computed: {
    IfaJointSubscriptTrustFeeA002RequestModel() {
      return new IfaJointSubscriptTrustFeeA002RequestModel(this.form)
    },
    IfaJointSubscriptTrustFeeA004RequestModel() {
      return new IfaJointSubscriptTrustFeeA004RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    checkCommComment() {
      if (this.form.commComment) {
        return this.form.commComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    }
  },
  watch: {
    'form.detailCustomerCurrencyCountingUnit': function(newVal, oldVal) {
      this.isOkCancelDialogVisible = true
      this.changePtn = 'pt2'
      if (newVal === '2') {
        this.changePtn = 'pt3'
        this.isOkCancelDialogVisible = false
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume) {
      if (!resume) {
        // カレンダのデフォルトとして前営業日をセットする
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
        // カレンダのデフォルトとして当月をセットする
        this.yearMonthInitialize()
      }
      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      this.disabledCsvBtn = true
      // 初期表示時のみA001初期化処理を実行
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
        document.getElementById('IfaJointSubscriptTrustFeeA001').click()
      })
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
        gridCols[key].hidden = info[0].hiddenColumn.includes(gridCols[key].dataIndx)
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
      this.$refs.commonSearchPt2.formClear()
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
          if (startDate < beforeSixMonth) {
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
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差6ヶ月より大きい
        if (this.form.periodMonth.length > 0) {
          // 期間指定（From）の6ヶ月前を算出
          const startMonth = this.parseMonth(this.form.periodMonth[0])
          const endMonth = this.parseMonth(this.form.periodMonth[1])
          const beforeSixMonth = new Date()
          beforeSixMonth.setHours(0, 0, 0, 0)
          beforeSixMonth.setDate(1)
          beforeSixMonth.setFullYear(endMonth.getFullYear(), endMonth.getMonth() - 6)

          // 入力チェック
          if (startMonth < beforeSixMonth) {
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
  title: '共同募集　信託報酬',
  flexHeight: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}

obj.colModel = [
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '部店', dataIndx: 'butenCode', width: 70, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '口座番号', dataIndx: 'accountNumber', width: 100, dataType: 'integer', editable: false, halign: 'center', hidden: false },
  { title: '取引コース', dataIndx: 'course', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名（漢字）', dataIndx: 'customerNameKanji', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名（カナ）', dataIndx: 'customerNameKana', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '共募支店コード', dataIndx: 'jointBranchCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '共募支店名', dataIndx: 'jointBranchName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '証券種別', dataIndx: 'securityClass', width: 180, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '保有日', dataIndx: 'baseDate', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) { return ui.rowData.baseDate ? getFormattedDateValue(ui.rowData.baseDate) : '-' }, hidden: false },
  { title: '保有月', dataIndx: 'baseMonth', width: 80, dataType: 'string', editable: false, halign: 'center', align: 'center',
    render: function(ui) { return ui.rowData.baseMonth ? getFormattedMonthValue(ui.rowData.baseMonth) : '-' }, hidden: false },
  { title: '銘柄コード', dataIndx: 'brandCode', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
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
  { title: '残手数料', dataIndx: 'feeBalance', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.feeBalance ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.feeBalance) : '-' } },
  { title: '支払額', dataIndx: 'jointRewardPrice', width: 150, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.jointRewardPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.jointRewardPrice) : '-' } },
  { title: '営業員コード', dataIndx: 'empCode', width: 110, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false }
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
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '0', csvFileName: '共募信託報酬_日次_明細', hiddenColumn: ['baseMonth'] },
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '1', csvFileName: '共募信託報酬_日次_顧客・商品分類・通貨毎', hiddenColumn: ['brandCode', 'brandName', 'baseMonth', 'quantity', 'referencePrice', 'price', 'trustFeeRate'] },
  { dailyMonthlyCountingUnit: '0', detailCustomerCurrencyCountingUnit: '2', csvFileName: '共募信託報酬_日次_通貨毎', hiddenColumn: ['butenCode', 'accountNumber', 'course', 'customerNameKanji', 'customerNameKana', 'brandCode', 'brandName', 'baseMonth', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'empCode', 'brokerChargeName'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '0', csvFileName: '共募信託報酬_月次累計_明細', hiddenColumn: ['baseDate', 'quantity', 'referencePrice', 'price', 'trustFeeRate'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '1', csvFileName: '共募信託報酬_月次累計_顧客・商品分類・通貨毎', hiddenColumn: ['brandCode', 'brandName', 'baseDate', 'quantity', 'referencePrice', 'price', 'trustFeeRate'] },
  { dailyMonthlyCountingUnit: '1', detailCustomerCurrencyCountingUnit: '2', csvFileName: '共募信託報酬_月次累計_通貨毎', hiddenColumn: ['butenCode', 'accountNumber', 'course', 'customerNameKanji', 'customerNameKana', 'brandCode', 'brandName', 'baseDate', 'quantity', 'referencePrice', 'price', 'trustFeeRate', 'empCode', 'brokerChargeName'] }
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

.filter-container {
  margin-top:1rem;
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
