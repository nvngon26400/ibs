<template>
  <!-- 画面名：SUB020505-01_残高連動手数料・報酬 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div id="contentAreaInput">
        <el-card class="content-card">
          <el-form
            id="inpLevelFeeTotalForm"
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
            label-position="right"
            label-width="135px"
          >
            <div class="filter-container">
              <el-row>
                <!-- 共通検索条件 -->
                <el-col
                  :span="23"
                >
                  <!-- CC005 表示パターン3（集計単位(仲介業者/営業員/顧客) = 仲介業者毎） -->
                  <ifa-common-search
                    v-show="displayPattern === 'pt3'"
                    ref="commonSearchPt3"
                    key="commonSearchPt3"
                    :form="form"
                    display-pattern="pt3"
                    @mediate-user-privacy="mediateUserPrivacy"
                  ></ifa-common-search>
                  <!-- CC005 表示パターン14（集計単位(仲介業者/営業員/顧客) = 営業員毎）-->
                  <ifa-common-search
                    v-show="displayPattern === 'pt14'"
                    ref="commonSearchPt14"
                    key="commonSearchPt14"
                    :form="form"
                    display-pattern="pt14"
                  ></ifa-common-search>
                  <!-- CC005 表示パターン13（集計単位(仲介業者/営業員/顧客) = 顧客毎）-->
                  <ifa-common-search
                    v-show="displayPattern === 'pt13'"
                    ref="commonSearchPt13"
                    key="commonSearchPt13"
                    :form="form"
                    display-pattern="pt13"
                  ></ifa-common-search>
                </el-col>
                <!-- infoメッセージ -->
                <el-col
                  :span="1"
                  class="right_icon"
                >
                  <ifa-balloon-icon
                    v-if="visibleLevelFeeComment"
                    icon-type="info"
                    :message="form.levelFeeComment"
                  ></ifa-balloon-icon>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <div style="display: inline-flex; width: 737px;">
                    <!-- 集計単位(日次/月次) -->
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
                      @change="changeDailyMonthlyCountingUnit"
                    ></ifa-input-radio>
                    <!-- 集計単位(仲介業者/営業員/顧客) -->
                    <ifa-input-radio
                      id="brokerChargeBranchCountingUnit"
                      v-model="form.brokerChargeBranchCountingUnit"
                      code-list-id="BROKER_CHARGE_CUSTOMER_COUNTING_UNIT"
                      is-button
                      :select-pattern="1"
                      :disp-pattern="1"
                      required
                      prop="brokerChargeBranchCountingUnit"
                      @change="chengeBrokerChargeBranchCountingUnit"
                    ></ifa-input-radio>
                  </div>
                </el-col>
              </el-row>

              <el-row>
                <el-col>
                  <!-- 期間指定（日次指定）（集計単位(日次/月次) = 日次） -->
                  <ifa-date-range-picker
                    v-show="periodDateIsVisible"
                    v-model="form.periodDate"
                    prop="periodDate"
                    size="small"
                    unlink-panels
                    label="手数料徴収日"
                    required
                    class="period_error_width"
                    :picker-options="datePickerOptions"
                  ></ifa-date-range-picker>
                  <!-- 期間指定（月次指定）（集計単位(日次/月次) = 月次） -->
                  <ifa-month-range-picker
                    v-show="!periodDateIsVisible"
                    v-model="form.yearMonth"
                    prop="yearMonth"
                    size="small"
                    unlink-panels
                    label="手数料徴収日"
                    required
                    class="period_error_width"
                    :picker-options="monthPickerOptions"
                  ></ifa-month-range-picker>
                  <!-- 期間指定_メッセージ -->
                  <div
                    class="date-info"
                  >※期間は6ヶ月以内を指定してください。（過去5年の履歴を照会いただけます。）
                  </div>
                </el-col>
              </el-row>

              <!-- 表示ボタン -->
              <el-row class="form_button">
                <ifa-button
                  id="btnDisplay"
                  icon="Search"
                  text="表示"
                  width="90"
                  small
                  :request-model="IfaLevelFeeA002RequestModel"
                  :form="formRef"
                  action-id="SUB020505-01#A002"
                  action-type="requestAction"
                  @response-handler="displayA002($event)"
                ></ifa-button>
                <!-- クリアボタン -->
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
        <!-- CSV出力ボタン -->
        <el-row class="grid_button_area">
          <ifa-button
            id="btnCsvDownload"
            text="CSV出力"
            width="90"
            small
            :disabled="disabledCsvBtn"
            :form="formRef"
            :request-model="IfaLevelFeeA004RequestModel"
            action-id="SUB020505-01#A004"
            action-type="outputCsvAction"
            csv-file-name="レベルフィー"
            :is-check-csv-download-privacy-confirmation="isCheckCsvDownloadPrivacyConfirmation"
          ></ifa-button>
        </el-row>
        <!-- 残高連動手数料一覧エリア -->
        <el-row>
          <div
            class="pq-grid-title"
            style="width: 100%;"
          >レベルフィー</div>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
          ></grid-table>
        </el-row>
      </el-card>
    </el-row>
    <!-- A001 初期化 -->
    <ifa-requester
      id="IfaLevelFeeInitializeA001"
      action-id="SUB020505-01#A001"
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
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaLevelFeeFormModel } from './js/IfaLevelFeeFormModel'
import { IfaLevelFeeA002RequestModel } from './js/IfaLevelFeeA002RequestModel'
import { IfaLevelFeeA004RequestModel } from './js/IfaLevelFeeA004RequestModel'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue, monthsBefore, validateDateRangeFromTo, validateDateRangeBeforeMonths } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getFormattedMonthValue } from '@/components/Date/js/IfaMonthPickerFunction.js'
import {  } from '@/components/Date/js/IfaDatePickerFunction.js'
export default {
  components: {
    GridTable,
    IfaCommonSearch,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      formRef: {},
      pqGridOption: getDefaultOption(obj),
      form: new IfaLevelFeeFormModel(),
      disabledCsvBtn: true, // CSV出力ボタン 活性/非活性フラグ
      rules: {
        periodDate: [{ type: 'array', validator: this.periodDateValidator }],
        yearMonth: [{ type: 'array', validator: this.periodDateValidator }]
      },
      datePickerOptions: { // 期間指定（日次指定）ショートカット
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
              const startDate = new Date(this.$store.getters.requestedTime)
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
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }
        ]
      },
      monthPickerOptions: { // 期間指定（月次指定）ショートカット
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
      displayPattern: 'pt3', // CC005 表示パターン
      periodDateIsVisible: true, // 期間指定（日次指定）表示/非表示フラグ
      displayColumnInfoList: [ // 検索条件に応じた非表示カラムリスト 「別紙_検索条件別明細表示」シート 参照
        { no: '01', // No.1：集計単位(日次/月次) = 日次, 集計単位(仲介業者/営業員/顧客) = 顧客毎
          dailyMonthlyCountingUnit: '0',
          brokerChargeBranchCountingUnit: '2',
          hiddenColumn: [
            'dateYm'
          ] 
        },
        { no: '02', // No.2：集計単位(日次/月次) = 日次, 集計単位(仲介業者/営業員/顧客) = 仲介業者毎
          dailyMonthlyCountingUnit: '0',
          brokerChargeBranchCountingUnit: '0',
          hiddenColumn: [
            'dateYm',
            'empCode',
            'brokerChargeName',
            'butenCode',
            'accountNumber',
            'dealerNumber',
            'customerNameKanji',
            'customerNameKana',
            'branchCode',
            'branchName'
          ] 
        },
        { no: '03', // No.3：集計単位(日次/月次) = 日次, 集計単位(仲介業者/営業員/顧客) = 営業員毎
          dailyMonthlyCountingUnit: '0',
          brokerChargeBranchCountingUnit: '1',
          hiddenColumn: [
            'dateYm',
            'butenCode',
            'accountNumber',
            'dealerNumber',
            'customerNameKanji',
            'customerNameKana'
          ] 
        },
        { no: '04', // No.4：集計単位(日次/月次) = 月次, 集計単位(仲介業者/営業員/顧客) = 顧客毎
          dailyMonthlyCountingUnit: '1',
          brokerChargeBranchCountingUnit: '2',
          hiddenColumn: [
            'dateYmd'
          ] 
        },
        { no: '05', // No.5：集計単位(日次/月次) = 月次, 集計単位(仲介業者/営業員/顧客) = 仲介業者毎
          dailyMonthlyCountingUnit: '1',
          brokerChargeBranchCountingUnit: '0',
          hiddenColumn: [
            'dateYmd',
            'empCode',
            'brokerChargeName',
            'butenCode',
            'accountNumber',
            'dealerNumber',
            'customerNameKanji',
            'customerNameKana',
            'branchCode',
            'branchName'
          ] 
        },
        { no: '06', // No.5：集計単位(日次/月次) = 月次, 集計単位(仲介業者/営業員/顧客) = 営業員毎
          dailyMonthlyCountingUnit: '1',
          brokerChargeBranchCountingUnit: '1',
          hiddenColumn: [
            'dateYmd',
            'butenCode',
            'accountNumber',
            'dealerNumber',
            'customerNameKanji',
            'customerNameKana',
          ]
        },
      ],
      autoDispHiddenColumn: ['brokerCode', 'brokerName', 'empCode', 'brokerChargeName', 'branchCode', 'branchName'] // 共通検索条件.営業員コード自動設定判定 != '0':自動設定なし の場合に非表示となるカラムリスト
    }
  },
  computed: {
    visibleLevelFeeComment() {
      // 残高連動手数料コメントに値があるかを判定
      if (this.form.levelFeeComment) {
        return this.form.levelFeeComment.replace(/\s/g, '').replace(/　/g, '') !== '' // 空白文字・スペースだけの場合はfalse
      } else {
        return false
      }
    },
    IfaLevelFeeA002RequestModel() {
      return new IfaLevelFeeA002RequestModel(this.form)
    },
    IfaLevelFeeA004RequestModel() {
      return new IfaLevelFeeA004RequestModel(this.form)
    },
    previousBusinessDay() {
      // 前営業日
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    getHiddenColumnList() {
      // 検索条件に応じた非表示カラムを取得する
      const mathedItem = this.displayColumnInfoList.find(data => {
        return this.form.dailyMonthlyCountingUnit === data.dailyMonthlyCountingUnit &&
        this.form.brokerChargeBranchCountingUnit === data.brokerChargeBranchCountingUnit
      })

      const result = {...mathedItem}

      // 共通検索条件.営業員コード自動設定判定 != '0':自動設定なし の場合の非表示カラムを取得する
      if (this.$refs.commonSearchPt3.empCodeAutoDispFlag) {
        // 重複カラムを除いてセットする
        result.hiddenColumn = [...new Set([...result.hiddenColumn, ...this.autoDispHiddenColumn])]
      }
      return result
    },
    isCheckCsvDownloadPrivacyConfirmation() {
      // 集計単位(仲介業者/営業員/顧客) = 顧客 の場合、個人情報管理台帳ダウンロード要否チェックを行う
      return this.form.brokerChargeBranchCountingUnit === '2'
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    /* 初期表示 */
    onShow() {
      // A001 初期化
      this.$nextTick(() => {
        document.getElementById('IfaLevelFeeInitializeA001').click()
      })
      // 期間指定（日次指定） デフォルト値に前営業日をセットする
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      // 期間指定（月次指定）　デフォルト値に当月をセットする
      this.yearMonthInitialize()
      
      // CSV出力ボタンを非活性化
      this.disabledCsvBtn = true
    },
    /* CC005 A001レスポンスハンドラ */
    mediateUserPrivacy() {
      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []

      this.updateGridConfigualtion();
    },
    /* A001 初期化 レスポンスハンドラ */
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    /* A001 初期化 レスポンスエラーハンドラ */
    initializeErrorA001(response) {
      // 共通エラー画面を出力する
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    /* A002 表示 レスポンスハンドラ */
    displayA002(response) {
      // 検索結果1件以上
      if (response.dataList.length > 0 && response.dataList[0].levelFeeList.length > 0) {
        this.disabledCsvBtn = false // CSV出力ボタンを活性化
        this.pqGridOption.dataModel.data = response.dataList[0].levelFeeList
      } else { // 検索結果0件
        this.disabledCsvBtn = true // CSV出力ボタンを非活性化
        this.pqGridOption.dataModel.data = []
      }

      this.updateGridConfigualtion()
    },
    /* A003 クリア */
    clearA003() {
      // 共通検索条件のクリア
      this.$refs['form']?.clearValidate()
      if (this.displayPattern === 'pt3') { this.$refs.commonSearchPt3.formClear() }
      if (this.displayPattern === 'pt13') { this.$refs.commonSearchPt13.formClear() }
      if (this.displayPattern === 'pt14') { this.$refs.commonSearchPt14.formClear() }

      // 集計単位のクリア
      this.form.dailyMonthlyCountingUnit = '0'
      this.form.brokerChargeBranchCountingUnit = '0'

      // 期間指定のクリア
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      this.yearMonthInitialize()

      this.changeDailyMonthlyCountingUnit()
      this.chengeBrokerChargeBranchCountingUnit()
    },
    /* テーブルのカラム編集を行う */
    updateGridConfigualtion() {
      // カラムの表示/非表示反映
      for (const key in this.$refs.pqGrid.grid.columns) {
        this.setHidden(key, this.getHiddenColumnList?.hiddenColumn.includes(key))
      }
      
      // 空白列の幅調整
      this.setSpaceColumnWidth()

      // テーブル再描画
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    /* 空白列の幅を動的にセットする */
    setSpaceColumnWidth() {
      // 表示されているカラムの幅の合計値を取得する
      const visibleColumn = this.pqGridOption.colModel.filter(col =>!col.hidden && col.dataIndx !== 'space')

      let totalColumnWidth = 0;
      visibleColumn.forEach(col => {
        if (col.width) {
          totalColumnWidth += parseInt(col.width);
        }
      });

      // テーブルの幅を取得する
      const gridContainer = this.$refs.pqGrid.$el;
      const tableWidth = gridContainer.clientWidth

      // 空白列の幅に(テーブルの幅 - 表示されているカラムの幅の合計)をセットする。結果がマイナスの場合は空白列を非表示にする。
      const spaceColumnWidth = tableWidth - totalColumnWidth
      
      if (spaceColumnWidth <= 0) {
        this.setHidden('space', true)
      } else {
        const spaceColumnModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === 'space')
        if (typeof spaceColumnModel === 'object') {
          spaceColumnModel.width = spaceColumnWidth
        }
      }
    },
    /* 指定されたカラムを非表示にする */
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    /* 集計単位(日次/月次)に応じて期間指定を制御する */
    changeDailyMonthlyCountingUnit() {
      if (this.form.dailyMonthlyCountingUnit === '0') { // 集計単位(日次/月次) = 日次
        this.periodDateIsVisible = true
        this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      } else if (this.form.dailyMonthlyCountingUnit === '1') { // 集計単位(日次/月次) = 月次
        this.periodDateIsVisible = false
        this.yearMonthInitialize()
      }
    },
    /* 集計単位(仲介業者/営業員/顧客)に応じて共通検索条件を制御する */
    chengeBrokerChargeBranchCountingUnit() {
      if (this.form.brokerChargeBranchCountingUnit === '0') { // 集計単位(仲介業者/営業員/顧客) = 仲介業者毎
        this.displayPattern = 'pt3'
        // 入力項目のバリデーションチェックを再実施
        this.$refs.form.validateField('brokerCode').catch(() => {})
      } else if (this.form.brokerChargeBranchCountingUnit === '1') { // 集計単位(仲介業者/営業員/顧客) = 営業員毎
        this.displayPattern = 'pt14'
        // 入力項目のバリデーションチェックを再実施
        this.$refs.form.validateField('brokerCode').catch(() => {})
        this.$refs.form.validateField('branchCode').catch(() => {})
        this.$refs.form.validateField('empCode').catch(() => {})
      } else if (this.form.brokerChargeBranchCountingUnit === '2') { // 集計単位(仲介業者/営業員/顧客) = 顧客毎
        this.displayPattern = 'pt13'
        // 入力項目のバリデーションチェックを再実施
        this.$refs.form.validateField('brokerCode').catch(() => {})
        this.$refs.form.validateField('branchCode').catch(() => {})
        this.$refs.form.validateField('empCode').catch(() => {})
        this.$refs.form.validateField('butenCode').catch(() => {})
        this.$refs.form.validateField('accountNumber').catch(() => {})
        this.$refs.form.validateField('customerNameKanjiKana').catch(() => {})
      }
    },
    /* 期間指定（月次指定）のデフォルト値をセットする */
    yearMonthInitialize() {
      const date = new Date(this.$store.getters.requestedTime)
      const thisMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
      this.form.yearMonth = [thisMonth, thisMonth]
    },
    /* 期間指定のバリデーションエラー判定 */
    periodDateValidator(rule, value, callback) {
      let date = []
      if (this.form.dailyMonthlyCountingUnit === '0' && this.form.periodDate.length > 0) {　// 集計単位(日次/月次) = 日次
        date = this.form.periodDate
      }　else if (this.form.dailyMonthlyCountingUnit === '1' && this.form.yearMonth.length > 0) {　// 集計単位(日次/月次) = 月次
        // YYYYMM　→ YYYY/MM/DD　に変換
        date = [this.form.yearMonth[0] + '/01', this.form.yearMonth[1] + '/01']
      }

      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい 場合エラー
      if (date.length > 0 && validateDateRangeFromTo(date, 6, true)) {
        callback(getMessage('errors.dateRange', ['手数料徴収日', '6ヶ月']))
        return
      }

      callback()
    }
  }
}

const obj = [
  { title: '年月日', dataIndx: 'dateYmd', width: 100, dataType: 'string', editable: false, halign: 'center',
    render: function(ui) { return ui.rowData.dateYmd ? getFormattedDateValue(ui.rowData.dateYmd) : '-' }, hidden: false
  },
  { title: '年月', dataIndx: 'dateYm', width: 100, dataType: 'string', editable: false, halign: 'center',
    render: function(ui) { return ui.rowData.dateYm ? getFormattedMonthValue(ui.rowData.dateYm) : '-' }, hidden: false
  },
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員コード', dataIndx: 'empCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '部店', dataIndx: 'butenCode', width: 80, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '口座番号', dataIndx: 'accountNumber', width: 100, dataType: 'string', editable: false, halign: 'center', hidden: false,
    render: function(ui) {
      if (ui.rowData.accountNumber) {
        return ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7)
      } else {
        return '-'
      }
    }  
  },
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '顧客名（漢字）', dataIndx: 'customerNameKanji', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名（カナ）', dataIndx: 'customerNameKana', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '証券種別', dataIndx: 'securiytClass', width: 70, dataType: 'string', editable: false, halign: 'center', hidden: false,
    render: function() { return '国内投信' },
  },
  { title: '計算対象残高', dataIndx: 'valuationTotalJpyAmount', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.valuationTotalJpyAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.valuationTotalJpyAmount) : '-' },
  },
  { title: '徴収額(税抜)', dataIndx: 'fee', width: 180, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.fee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fee) : '-' },
  },
  { title: '支店コード', dataIndx: 'branchCode', width: 100, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '支店名', dataIndx: 'branchName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  {
    title: '',
    dataType: 'string',
    dataIndx: 'space',
    width: 1000,
    halign: 'center',
    hidden: false,
    render: function(ui) {
      return ''
    }
  }
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
