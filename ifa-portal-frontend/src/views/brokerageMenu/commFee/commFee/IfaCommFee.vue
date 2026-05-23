<template>
  <!-- 画面名：手数料・報酬 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div id="contentAreaInput">
        <el-card class="content-card">
          <el-form
            id="inpFeeTotalForm"
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
            label-position="right"
            label-width="135px"
          >
            <div class="filter-container">
              <el-row>
                <el-col
                  :span="23"
                >
                  <ifa-common-search
                    v-show="displayPattern === 'pt5'"
                    ref="commonSearchPt5"
                    key="commonSearchPt5"
                    :form="form"
                    display-pattern="pt5"
                  ></ifa-common-search>
                  <ifa-common-search
                    v-show="displayPattern === 'pt3'"
                    ref="commonSearchPt3"
                    key="commonSearchPt3"
                    :form="form"
                    display-pattern="pt3"
                  ></ifa-common-search>
                  <ifa-common-search
                    v-show="displayPattern === 'pt2'"
                    ref="commonSearchPt2"
                    key="commonSearchPt2"
                    :form="form"
                    display-pattern="pt2"
                  ></ifa-common-search>
                </el-col>
                <el-col
                  :span="1"
                  class="right_icon"
                >
                  <ifa-balloon-icon
                    v-if="checkCommFeeComment"
                    :key="iconKey"
                    icon-type="info"
                    :message="form.commFeeComment"
                  ></ifa-balloon-icon>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <!-- 表示内容-->
                  <div style="display: inline-flex; width: 341px;">
                    <ifa-input-radio
                      id="displayContent"
                      v-model="form.displayContent"
                      prop="displayContent"
                      label="表示内容"
                      required
                      is-button
                      :disabled-items="displayContentDisabled"
                      code-list-id="FEE_COMM_DISPLAY_CONTENT"
                      :select-pattern="1"
                      :disp-pattern="1"
                      @change="changeDisplayContent"
                    ></ifa-input-radio>
                  </div>

                  <div style="display: inline-flex; width: 737px;">
                    <!-- 集計単位 -->
                    <ifa-input-radio
                      id="dailyMonthlyCountingUnit"
                      v-model="form.dailyMonthlyCountingUnit"
                      code-list-id="DAILY_MONTHLY_COUNTING_UNIT"
                      is-button
                      :disabled-items="dailyMonthlyCountingUnitDisabled"
                      :select-pattern="1"
                      :disp-pattern="1"
                      label="集計単位"
                      required
                      prop="dailyMonthlyCountingUnit"
                      @change="changeDailyMonthlyCountingUnit"
                    ></ifa-input-radio>
                    <ifa-input-radio
                      id="brokerChargeBranchCountingUnit"
                      v-model="form.brokerChargeBranchCountingUnit"
                      code-list-id="BROKER_CHARGE_BRANCH_COUNTING_UNIT"
                      is-button
                      :select-pattern="1"
                      :disp-pattern="1"
                      required
                      prop="brokerChargeBranchCountingUnit"
                      @change="chengeBrokerChargeBranchCountingUnit"
                    ></ifa-input-radio>
                    <ifa-input-radio
                      id="aggregatorHandlerCountingUnit"
                      v-model="form.aggregatorHandlerCountingUnit"
                      code-list-id="AGGREGATOR_HANDLER_COUNTING_UNIT"
                      is-button
                      :disabled-items="aggregatorHandlerCountingUnitDisabled"
                      :select-pattern="1"
                      :disp-pattern="1"
                      required
                      prop="aggregatorHandlerCountingUnit"
                    ></ifa-input-radio>
                  </div>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <!-- 期間指定 -->
                  <ifa-date-range-picker
                    v-show="periodDateIsVisible"
                    v-model="form.periodDate"
                    prop="periodDate"
                    size="small"
                    unlink-panels
                    label="期間指定"
                    required
                    class="period_error_width"
                    :picker-options="datePickerOptions"
                  ></ifa-date-range-picker>
                  <ifa-month-range-picker
                    v-show="!periodDateIsVisible"
                    v-model="form.yearMonth"
                    prop="yearMonth"
                    size="small"
                    unlink-panels
                    label="期間指定"
                    required
                    class="period_error_width"
                    :picker-options="monthPickerOptions"
                  ></ifa-month-range-picker>
                  <div
                    v-if="periodDateIsVisible"
                    class="date-info"
                  >※期間は3ヶ月以内を指定してください。（過去6ヶ月の履歴を照会いただけます。）</div>
                  <div
                    v-else-if="!periodDateIsVisible"
                    class="date-info"
                  >※期間は1年以内を指定してください。（過去全ての履歴を照会いただけます。）</div>
                </el-col>
              </el-row>

              <!-- 検索用ボタン -->
              <el-row class="form_button">
                <ifa-button
                  id="btnDisplay"
                  icon="Search"
                  text="表示"
                  width="90"
                  small
                  :request-model="IfaCommFeeA002RequestModel"
                  :form="formRef"
                  action-id="SUB020502-01#A002"
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
            :request-model="IfaCommFeeA004RequestModel"
            action-id="SUB020502-01#A004"
            action-type="outputCsvAction"
            :csv-file-name="csvFileName"
            :pre-request-handler="setCsvFileName"
            :is-check-csv-download-privacy-confirmation="false"
          ></ifa-button>
        </el-row>
        <el-row>
          <div
            class="pq-grid-title"
            style="width: 100%;"
          >{{ gridTitle }}</div>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="handleClick"
          ></grid-table>
        </el-row>
      </el-card>
    </el-row>
    <!-- その他報酬詳細画面 -->
    <ifa-other-fee-detail
      ref="IfaOtherFeeDetail"
      :is-visible="otherFeeDetailIsVisible"
      :param="otherFeeDetaiParam"
      @close-modal="handleCloseModal"
    ></ifa-other-fee-detail>
    <ifa-requester
      id="IfaCommFeeA001"
      action-id="SUB020502-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="initializeErrorA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import IfaOtherFeeDetail from './IfaOtherFeeDetail'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaCommFeeFormModel } from './js/IfaCommFeeFormModel'
import { IfaCommFeeA002RequestModel } from './js/IfaCommFeeA002RequestModel'
import { IfaCommFeeA004RequestModel } from './js/IfaCommFeeA004RequestModel'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import ifaUtils from '@/utils/ifaUtils.js'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    IfaOtherFeeDetail,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      formRef: {},
      pqGridOption: getDefaultOption(obj),
      form: new IfaCommFeeFormModel(),
      disabledCsvBtn: true,
      rules: {
        periodDate: [{ type: 'array', validator: this.periodDateValidator }],
        yearMonth: [{ type: 'array', validator: this.yearMonthValidator }]
      },
      gridTitle: '手数料一覧',
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
      displayPattern: 'pt3',
      dailyMonthlyCountingUnitDisabled: [],
      periodDateIsVisible: true,
      aggregatorHandlerCountingUnitDisabled: [],
      otherFeeDetailIsVisible: false,
      otherFeeDetaiParam: {
        brokerCode: '',
        targetDateYm: ''
      },
      csvFileName: ''
    }
  },
  computed: {
    checkCommFeeComment() {
      if (this.form.commFeeComment) {
        return this.form.commFeeComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    },
    IfaCommFeeA002RequestModel() {
      return new IfaCommFeeA002RequestModel(this.form)
    },
    IfaCommFeeA004RequestModel() {
      return new IfaCommFeeA004RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    userAccount() {
      return this.$store.getters.userAccount
    },
    displayContentDisabled() {
      if (this.userAccount) {
        return ['4', '5', '6', '7', '8', '9'].includes(this.userAccount.medUsers.privId) ? ['1'] : []
      } else {
        return []
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
          document.getElementById('IfaCommFeeA001').click()
        })
        // カレンダのデフォルトとして前営業日をセットする
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
        // カレンダのデフォルトとして当月をセットする
        this.yearMonthInitialize()
      }

      // 検索結果を初期化
      obj = []
      obj = obj.concat(obj1).concat(obj3)
      this.pqGridOption.colModel = obj
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
      if (response.dataList.length > 0) {
        if (response.dataList[0]?.commFeeList) {
          this.pqGridOption.dataModel.data = response.dataList[0].commFeeList
          if (response.dataList[0].commFeeList.length > 0) {
            this.disabledCsvBtn = false
          } else {
            this.disabledCsvBtn = true
          }
        } else {
          this.pqGridOption.dataModel.data = []
          this.disabledCsvBtn = true
        }
        // グリッドの表示項目制御
        const info = getViewControllInfo(this.form.displayContent, this.form.dailyMonthlyCountingUnit, this.form.brokerChargeBranchCountingUnit, this.form.aggregatorHandlerCountingUnit)
        const gridCols = this.$refs.pqGrid.grid.columns
        for (const key in gridCols) {
          this.setHidden(key, info[0].hiddenColumn.includes(key))
        }
        // 現株ポイントの表示制御　※1
        if (response.dataList[0]?.spotStockPointReferenceAbleList) {
          if (!gridCols['spotStockPoint'].hidden && (this.userAccount.medUsers.privId !== '1' && this.userAccount.medUsers.privId !== '2') &&
          !response.dataList[0].spotStockPointReferenceAbleList.some(item => item.brokerCode.trim() === this.userAccount.medUsers.brokerId)) {
            this.setHidden('spotStockPoint', true)
          }
        } else {
          this.setHidden('spotStockPoint', true)
        }

        obj = []
        const obj2 = []

        for (const i in response.dataList[0].sbiRapManageFeeDisplayControlList) {
          if (response.dataList[0].sbiRapManageFeeDisplayControlList[i]) {
            const list = response.dataList[0].sbiRapManageFeeDisplayControlList[i]
            const sbiRapCourseLabel = list.serviceName
            const sbiRapCourseContent = list.itemName
            let sbiRapDispFlag = true
            if (list.displayControlFlag === '0') {
              sbiRapDispFlag = false
            } else {
              sbiRapDispFlag = true
            }
            const colSbiRap = { title: sbiRapCourseLabel, dataIndx: sbiRapCourseContent, width: 120, dataType: function(val1, val2) { return ifaUtils.nullSorting(val1 - 0, val2 - 0) }, editable: false, halign: 'center', align: 'right',
              render: function(ui) { return ui.rowData[sbiRapCourseContent] ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData[sbiRapCourseContent]) : '-' }, hidden: sbiRapDispFlag }
            obj2.push(colSbiRap)
          }
        }

        if (obj2.length > 0) {
          obj = obj.concat(obj1).concat(obj2).concat(obj3)
        } else {
          obj = obj.concat(obj1).concat(obj3)
        }

        if (response.dataList[0].displayContent === '1') {
          this.gridTitle = '報酬一覧'
        } else if (response.dataList[0].displayContent === '0') {
          this.gridTitle = '手数料一覧'
        } else {
          this.gridTitle = '-'
        }
      } else {
        this.pqGridOption.dataModel.data = []
        this.disabledCsvBtn = true

        if (this.form.displayContent === '1') {
          this.gridTitle = '報酬一覧'
        } else if (this.form.displayContent === '0') {
          this.gridTitle = '手数料一覧'
        } else {
          this.gridTitle = '-'
        }
      }

      this.$nextTick(() => {
        this.pqGridOption.colModel = obj
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    // クリアボタンによる初期化
    clearA003() {
      this.$refs['form']?.clearValidate()
      if (this.displayPattern === 'pt2') { this.$refs.commonSearchPt2.formClear() }
      if (this.displayPattern === 'pt3') { this.$refs.commonSearchPt3.formClear() }
      if (this.displayPattern === 'pt5') { this.$refs.commonSearchPt5.formClear() }
      this.form.displayContent = '0'
      this.form.dailyMonthlyCountingUnit = '0'
      this.form.brokerChargeBranchCountingUnit = '0'
      this.form.aggregatorHandlerCountingUnit = '0'
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      this.yearMonthInitialize()

      this.changeDisplayContent()
      this.changeDailyMonthlyCountingUnit()
      this.chengeBrokerChargeBranchCountingUnit()
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    setCsvFileName() {
      // csvファイル名の設定
      if (this.form.displayContent === '0') {
        // 手数料
        this.csvFileName = '手数料'
      } else {
        this.csvFileName = '報酬'
      }
    },
    // 一覧選択
    handleClick(ui) {
      // A005
      if ((ui.dataIndx === 'other1' && ui.rowData.other1) || (ui.dataIndx === 'other2' && ui.rowData.other2) ||
      (ui.dataIndx === 'other3' && ui.rowData.other3) || (ui.dataIndx === 'other4' && ui.rowData.other4) ||
      (ui.dataIndx === 'other5' && ui.rowData.other5) || (ui.dataIndx === 'other6' && ui.rowData.other6)) {
        this.otherFeeDetailIsVisible = true
        this.otherFeeDetaiParam = { brokerCode: ui.rowData.brokerCode, targetDateYm: ui.rowData.dateYm }
      }
    },
    async handleCloseModal() {
      this.otherFeeDetailIsVisible = false
    },
    changeDisplayContent() {
      if (this.form.displayContent === '0') {
        // 手数料
        this.form.dailyMonthlyCountingUnit = '0'
        this.dailyMonthlyCountingUnitDisabled = []
        this.periodDateIsVisible = true
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      } else if (this.form.displayContent === '1') {
        // 報酬
        this.form.dailyMonthlyCountingUnit = '1'
        this.dailyMonthlyCountingUnitDisabled = ['0']
        this.periodDateIsVisible = false
        this.yearMonthInitialize()
      }
    },
    changeDailyMonthlyCountingUnit() {
      if (this.form.dailyMonthlyCountingUnit === '0') {
        // 日次
        this.periodDateIsVisible = true
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      } else if (this.form.dailyMonthlyCountingUnit === '1') {
        // 月次
        this.periodDateIsVisible = false
        this.yearMonthInitialize()
      }
    },
    chengeBrokerChargeBranchCountingUnit() {
      if (this.form.brokerChargeBranchCountingUnit === '0') {
        // 仲介業者毎
        this.form.aggregatorHandlerCountingUnit = '0'
        this.aggregatorHandlerCountingUnitDisabled = []
        this.displayPattern = 'pt3'
        this.$refs.form.validateField('brokerCode').catch(() => {})
      } else if (this.form.brokerChargeBranchCountingUnit === '1') {
        // 営業員毎
        this.form.aggregatorHandlerCountingUnit = '0'
        this.aggregatorHandlerCountingUnitDisabled = ['1']
        this.displayPattern = 'pt2'
        this.$refs.form.validateField('brokerCode').catch(() => {})
        this.$refs.form.validateField('branchCode').catch(() => {})
        this.$refs.form.validateField('empCode').catch(() => {})
      } else if (this.form.brokerChargeBranchCountingUnit === '2') {
        // 支店毎
        this.form.aggregatorHandlerCountingUnit = '0'
        this.aggregatorHandlerCountingUnitDisabled = ['1']
        this.displayPattern = 'pt5'
        this.$refs.form.validateField('brokerCode').catch(() => {})
        this.$refs.form.validateField('branchCode').catch(() => {})
      }
    },
    yearMonthInitialize() {
      // 当月を設定する
      const date = new Date()
      const thisMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
      this.form.yearMonth = [thisMonth, thisMonth]
    },
    periodDateValidator(rule, value, callback) {
      if (this.form.dailyMonthlyCountingUnit === '0') {
        // 日次
        // 以下の条件の時エラー
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が3ヶ月より大きい
        // リクエスト.期間指定（From）がシステム日付-6ヶ月より小さい
        // リクエスト.期間指定（To）がシステム日付-6ヶ月より小さい
        if (this.form.periodDate.length > 0) {
        // 期間指定（From）の3ヶ月前の日付を算出
          const startDate = this.parseDate(this.form.periodDate[0])
          const endDate = this.parseDate(this.form.periodDate[1])
          const y = endDate.getFullYear()
          const m = endDate.getMonth()
          let d = endDate.getDate()
          const temp = new Date(y, m - 2, 0)
          const lastD = temp.getDate()
          if (d > lastD) {
            d = lastD
          }
          const beforeThreeMonth = new Date()
          beforeThreeMonth.setFullYear(y, m - 3, d)
          beforeThreeMonth.setHours(0, 0, 0, 0)

          // システム日付-6ヶ月の算出
          const systemDate = this.parseDate(this.$store.getters.requestedTime?.split(' ')?.[0])
          const beforeSixMonth = new Date(systemDate)
          beforeSixMonth.setMonth(beforeSixMonth.getMonth() - 6)
          // 入力チェック
          if (startDate <= beforeThreeMonth || startDate < beforeSixMonth || endDate < beforeSixMonth) {
            callback(getMessage('errors.dateRange', ['期間指定', '過去6ヶ月の範囲で3ヶ月']))
            return
          }
        }
      }
      // OK
      callback()
    },
    yearMonthValidator(rule, value, callback) {
      if (this.form.dailyMonthlyCountingUnit === '1') {
        // 月次
        // 以下の条件の時エラー
        // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が1年より大きい
        if (this.form.yearMonth.length > 0) {
          // 期間指定（From）の1年前を算出
          const startMonth = this.parseMonth(this.form.yearMonth[0])
          const endMonth = this.parseMonth(this.form.yearMonth[1])
          const beforeOneYear = new Date()
          beforeOneYear.setHours(0, 0, 0, 0)
          beforeOneYear.setDate(1)
          beforeOneYear.setMonth(endMonth.getMonth())
          beforeOneYear.setFullYear(endMonth.getFullYear() - 1)

          // 入力チェック
          if (startMonth <= beforeOneYear) {
            callback(getMessage('errors.dateRange', ['期間指定', '1年']))
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
    }
  }
}

const obj1 = [
  { title: '年月日', dataIndx: 'dateYmd', width: 100, dataType: 'string', editable: false, halign: 'center',
    render: function(ui) { return ui.rowData.dateYmd ? getFormattedDateValue(ui.rowData.dateYmd) : '-' }, hidden: false },
  { title: '年月', dataIndx: 'dateYm', width: 100, dataType: 'string', editable: false, halign: 'center',
    render: function(ui) { return ui.rowData.dateYm ? getFormattedMonthValue(ui.rowData.dateYm) : '-' }, hidden: false },
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員コード', dataIndx: 'empCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '取引コース', dataIndx: 'course', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '国内株式 現物', dataIndx: 'domesticStockSpot', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticStockSpot ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticStockSpot) : '-' }, hidden: false },
  { title: '国内株式 信用', dataIndx: 'domesticStockMargin', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticStockMargin ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticStockMargin) : '-' }, hidden: false },
  { title: '国内株式 IPO・PO', dataIndx: 'domesticStockIpoPo', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticStockIpoPo ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticStockIpoPo) : '-' }, hidden: false },
  { title: '国内CB', dataIndx: 'domesticCb', width: 130, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticCb ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticCb) : '-' }, hidden: false },
  { title: '国内投信 販売', dataIndx: 'domesticMutualFundSales', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticMutualFundSales ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticMutualFundSales) : '-' }, hidden: false },
  { title: '国内投信 信報', dataIndx: 'domesticMutualFundTrustFee', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticMutualFundTrustFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticMutualFundTrustFee) : '-' }, hidden: false },
  { title: '外国投信 販売', dataIndx: 'foreignMutualFundSales', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignMutualFundSales ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignMutualFundSales) : '-' }, hidden: false },
  { title: '外国投信 信報', dataIndx: 'foreignMutualFundTrustFee', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignMutualFundTrustFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignMutualFundTrustFee) : '-' }, hidden: false },
  { title: '外国投信 信報（その他）', dataIndx: 'foreignMutualFundOtherTrustFee', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignMutualFundOtherTrustFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignMutualFundOtherTrustFee) : '-' }, hidden: false },
  { title: '先物・OP', dataIndx: 'futuresOptions', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.futuresOptions ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.futuresOptions) : '-' }, hidden: false },
  { title: '国内債券', dataIndx: 'domesticBond', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.domesticBond ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.domesticBond) : '-' }, hidden: false },
  { title: '外国債券（円建）', dataIndx: 'jpyForeignBond', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.jpyForeignBond ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.jpyForeignBond) : '-' }, hidden: false },
  { title: '外国債券（外貨建）', dataIndx: 'foreignBond', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignBond ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignBond) : '-' }, hidden: false },
  { title: '外国株式', dataIndx: 'foreignStock', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignStock ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignStock) : '-' }, hidden: false },
  { title: '米株信用', dataIndx: 'americaStockMargin', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.americaStockMargin ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.americaStockMargin) : '-' }, hidden: false },
  { title: 'ST', dataIndx: 'st', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.st ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.st) : '-' }, hidden: false },
  { title: 'ST信報', dataIndx: 'stSintaku', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.stSintaku ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.stSintaku) : '-' }, hidden: false },
  { title: '外貨建MMF 信報', dataIndx: 'foreignMmfTrustFee', width: 150, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.foreignMmfTrustFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignMmfTrustFee) : '-' }, hidden: false },
  { title: '為替取引', dataIndx: 'fxTrade', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.fxTrade ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxTrade) : '-' }, hidden: false },
  { title: '投信（SBIラップ）マイレージ', dataIndx: 'mutualFundSbiWrapMileage', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.mutualFundSbiWrapMileage ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.mutualFundSbiWrapMileage) : '-' }, hidden: false },
  { title: '投信マイレージ', dataIndx: 'mutualFundMileage', width: 130, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.mutualFundMileage ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.mutualFundMileage) : '-' }, hidden: false },
  { title: '現株ポイント', dataIndx: 'spotStockPoint', width: 130, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.spotStockPoint ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.spotStockPoint) : '-' }, hidden: false }
]

const obj3 = [
  { title: '小計', dataIndx: 'rewardSubtotal', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.rewardSubtotal ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.rewardSubtotal) : '-' }, hidden: false },
  { title: 'その他1', dataIndx: 'other1', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other1 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other1)) : '-'
    }
  },
  { title: 'その他2', dataIndx: 'other2', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other2 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other2)) : '-'
    }
  },
  { title: 'その他3', dataIndx: 'other3', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other3 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other3)) : '-'
    }
  },
  { title: 'その他4', dataIndx: 'other4', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other4 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other4)) : '-'
    }
  },
  { title: 'その他5', dataIndx: 'other5', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other5 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other5)) : '-'
    }
  },
  { title: 'その他6', dataIndx: 'other6', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      return ui.rowData.other6 ? changeColorBorderBottom(ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.other6)) : '-'
    }
  },
  { title: '手数料合計', dataIndx: 'commTotal', width: 150, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.commTotal ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.commTotal) : '-' }, hidden: false },
  { title: '合計', dataIndx: 'total', width: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.total ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.total) : '-' }, hidden: false },
  { title: '消費税', dataIndx: 'consumptionTax', width: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.consumptionTax ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.consumptionTax) : '-' }, hidden: false },
  { title: '支払報酬額', dataIndx: 'paymentFeeAmount', width: 110, dataType: 'string', editable: false, halign: 'center', align: 'right',
    render: function(ui) { return ui.rowData.paymentFeeAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.paymentFeeAmount) : '-' }, hidden: false },
  { title: '支店コード', dataIndx: 'branchCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '支店名', dataIndx: 'branchName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false }
]

let obj = []
obj = obj.concat(obj1).concat(obj3)

const changeColorBorderBottom = (item) => {
  return `<span class='grid-link'><a>` + item + `</a></span>
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
}

const getViewControllInfo = (displayContent, dailyMonthlyCountingUnit, brokerChargeBranchCountingUnit, aggregatorHandlerCountingUnit) => {
  return viewControllInfoList.filter(function(data) {
    return displayContent === data.displayContent && dailyMonthlyCountingUnit === data.dailyMonthlyCountingUnit &&
    brokerChargeBranchCountingUnit === data.brokerChargeBranchCountingUnit && aggregatorHandlerCountingUnit === data.aggregatorHandlerCountingUnit
  })
}

// 部品制御情報リスト
const viewControllInfoList = [
  { no: '01', displayContent: '0', dailyMonthlyCountingUnit: '0', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYm', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount', 'branchCode', 'branchName'] },
  { no: '02', displayContent: '0', dailyMonthlyCountingUnit: '0', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '1', hiddenColumn: ['dateYm', 'empCode', 'brokerChargeName', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount', 'branchCode', 'branchName'] },
  { no: '03', displayContent: '0', dailyMonthlyCountingUnit: '0', brokerChargeBranchCountingUnit: '1', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYm', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount'] },
  { no: '04', displayContent: '0', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount', 'branchCode', 'branchName'] },
  { no: '05', displayContent: '0', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '1', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount', 'branchCode', 'branchName'] },
  { no: '06', displayContent: '0', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '1', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount'] },
  { no: '07', displayContent: '1', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'commTotal', 'branchCode', 'branchName'] },
  { no: '08', displayContent: '1', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '0', aggregatorHandlerCountingUnit: '1', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'commTotal', 'consumptionTax', 'paymentFeeAmount', 'branchCode', 'branchName'] },
  { no: '09', displayContent: '1', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '1', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'dealerNumber', 'course', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'commTotal', 'consumptionTax', 'paymentFeeAmount'] },
  { no: '10', displayContent: '0', dailyMonthlyCountingUnit: '0', brokerChargeBranchCountingUnit: '2', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYm', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount'] },
  { no: '11', displayContent: '0', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '2', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'mutualFundSbiWrapMileage', 'mutualFundMileage', 'spotStockPoint', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'total', 'consumptionTax', 'paymentFeeAmount'] },
  { no: '12', displayContent: '1', dailyMonthlyCountingUnit: '1', brokerChargeBranchCountingUnit: '2', aggregatorHandlerCountingUnit: '0', hiddenColumn: ['dateYmd', 'empCode', 'brokerChargeName', 'dealerNumber', 'course', 'rewardSubtotal', 'other1', 'other2', 'other3', 'other4', 'other5', 'other6', 'commTotal', 'consumptionTax', 'paymentFeeAmount'] }
]

</script>

<style scoped>
:deep(.period_error_width) .el-form-item__error {
  width: 370px;
}
.date-info {
  padding-left: 151px;
  margin-bottom: 2rem;
}

.filter-container {
  margin-top:1rem;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
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

.content-card {
  margin: 0.5rem 1rem;
}

:deep(.el-card) .clickable:hover {
  cursor: pointer
}
:deep(.el-radio-group) {
  vertical-align: top;
}
</style>
