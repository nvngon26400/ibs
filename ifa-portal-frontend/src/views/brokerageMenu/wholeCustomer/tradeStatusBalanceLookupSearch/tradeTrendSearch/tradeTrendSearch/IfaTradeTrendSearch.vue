<template>
  <!-- 画面名：取引動向検索 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div id="contentAreaInput">
      <el-card class="content-card">
        <el-form
          id="inpTradeTrendSearchRewardForm"
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
                  ref="commonSearchPt12"
                  list-pattern="pt2"
                  :form="form"
                  :display-pattern="changePtn"
                  :broker-code-validate="false"
                  :emp-code-validate="false"
                  :course-validate="true"
                  original-screen-id="SUB020302_0401"
                  @mediate-user-privacy="handleMediateUserPrivacy"
                ></ifa-common-search>
              </el-col>
              <el-col
                :span="1"
                class="right_icon"
              >
                <ifa-balloon-icon
                  v-if="checkCommComment"
                  :key="iconKey"
                  icon-type="info"
                >
                  <div v-html="form.commComment">
                  </div>
                </ifa-balloon-icon>
              </el-col>
            </el-row>

            <!-- 集計単位 -->
            <el-row>
              <div style="display: inline-flex;">
                <ifa-input-radio
                  id="countingUnit"
                  v-model="form.countingUnit"
                  label="集計単位"
                  :code-list-id="'original'"
                  :original-list="brokerChargeCustomerCountingUnitOptions"
                  is-button
                  required
                  :disabled-items="brokerChargeCustomerCountingUnitOptionsDisabled"
                ></ifa-input-radio>
              </div>
              <div style="display: inline-block; margin-left: -62px;">
                <ifa-input-text
                  v-show="form.countingUnit === '3'"
                  id="visibleButenCode"
                  v-model="form.visibleButenCode"
                  label="閲覧可能部店"
                  size="small"
                  prop="visibleButenCode"
                  :tooltip-enabled="!form.visibleButenCode < 1"
                  :tooltip-content="form.visibleButenCode"
                  tooltip-placement="bottom"
                  tooltip-effect="light"
                  style="width: 180px;"
                  :domain="IfaButenCodeArrayDomainModel"
                  @change="addCommaVisibleButenCode"
                >
                </ifa-input-text>
              </div>
            </el-row>

            <!-- 期間指定 -->
            <el-row>
              <el-col>
                <div style="display: inline-block;">
                  <product-code-select
                    ref="productCodeSelect"
                    list-kind="pt9"
                    id-string="securityClassList"
                    prop="securityClassList"
                    label="証券種別"
                    required
                    @change-selected="form.securityClassList = $event"
                  ></product-code-select>
                </div>
                <div style="display: inline-block; margin-left: -42px;">
                  <ifa-month-range-picker
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
                <div
                  class="date-info"
                >※期間は1年以内を指定してください。（1年前から当月までを照会いただけます｡）<br>
                </div>
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
                :request-model="IfaTradeTrendSearchA002RequestModel"
                :form="formRef"
                action-id="SUB020302_0401-01#A002"
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
            id="btmCustomerPortal"
            name="btmCustomerPortal"
            :disabled="activeBtn"
            text="顧客別メニュー"
            color="primary"
            small
            action-type="originalAction"
            @app-action-handler="handleMovePortfolio($event)"
          ></ifa-button>
          <ifa-button
            id="btnCsvDownload"
            text="CSV出力"
            width="90"
            small
            :disabled="disabledCsvBtn"
            :form="formRef"
            :request-model="IfaTradeTrendSearchA004RequestModel"
            action-id="SUB020302_0401-01#A004"
            action-type="outputCsvAction"
            :csv-file-name="csvFileName"
            :pre-request-handler="setCsvFileName"
            :is-check-csv-download-privacy-confirmation="true"
          ></ifa-button>
        </el-row>
        <el-row>
          <grid-table
            ref="pqGrid"
            :auto-refresh="false"
            :options="pqGridOption"
            @click="handleClick($event)"
          ></grid-table>
        </el-row>
      </el-card>
    </el-row>
    <ifa-requester
      id="IfaTradeTrendSearchA001"
      action-id="SUB020302_0401-01#A001"
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
import IfaButenCodeArrayDomainModel from '@/resource/domain/IfaButenCodeArrayDomainModel.json'
import { IfaTradeTrendSearchFormModel } from './js/IfaTradeTrendSearchFormModel'
import { IfaTradeTrendSearchA002RequestModel } from './js/IfaTradeTrendSearchA002RequestModel'
import { IfaTradeTrendSearchA004RequestModel } from './js/IfaTradeTrendSearchA004RequestModel'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import calculateTableHeight from '@/utils/calculateTableHeight.js'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    GridTable,
    ProductCodeSelect,
    IfaCommonSearch,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      changePtn: 'pt12',
      formRef: {},
      pqGridSelectedInfo: {},
      form: new IfaTradeTrendSearchFormModel(),
      IfaBrandCode8PeriodDomainModel: IfaBrandCode8PeriodDomainModel,
      IfaButenCodeArrayDomainModel: IfaButenCodeArrayDomainModel,
      pqGridOption: getConvertedOption(obj),
      disabledCsvBtn: true,
      activeBtn: true,
      rules: {
        visibleButenCode: { validator: this.butenCodeArrayValidator, trigger: 'blur' }
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
          }, {
            text: '前月',
            value: () => {
              const date = new Date()
              const endDate = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              const date01 = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              const startDate = date01.getFullYear() + '/' + String(date01.getMonth() + 1).padStart(2, '0')
              return [startDate, endDate]
            }
          }, {
            text: '3ヶ月前',
            value: () => {
              const date = new Date()
              const endDate = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              const date01 = monthsBefore(new Date(this.$store.getters.requestedTime), 3)
              const startDate = date01.getFullYear() + '/' + String(date01.getMonth() + 1).padStart(2, '0')
              return [startDate, endDate]
            }
          }, {
            text: '6ヶ月前',
            value: () => {
              const date = new Date()
              const endDate = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              const date01 = monthsBefore(new Date(this.$store.getters.requestedTime), 6)
              const startDate = date01.getFullYear() + '/' + String(date01.getMonth() + 1).padStart(2, '0')
              return [startDate, endDate]
            }
          }, {
            text: '1年前',
            value: () => {
              const date = new Date()
              const endDate = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
              const date01 = monthsBefore(new Date(this.$store.getters.requestedTime), 12)
              const startDate = date01.getFullYear() + '/' + String(date01.getMonth() + 1).padStart(2, '0')
              return [startDate, endDate]
            }
          }
        ],

        disabledDateFrom(date) {
          // 最小値: From、Toともに1年前の年月
          // 最大値: From、Toともに当月
          const oneYearsAgo = new Date()
          const y = oneYearsAgo.getFullYear()
          const m = oneYearsAgo.getMonth()
          oneYearsAgo.setFullYear(y, m - 12, '01')
          oneYearsAgo.setHours(0, 0, 0, 0)
          const lastMonth = monthsBefore(new Date(), 0)
          return date < oneYearsAgo || date > lastMonth
        },
        disabledDateTo(date) {
          // 最小値: From、Toともに1年前の年月
          // 最大値: From、Toともに当月
          const oneYearsAgo = new Date()
          const y = oneYearsAgo.getFullYear()
          const m = oneYearsAgo.getMonth()
          oneYearsAgo.setFullYear(y, m - 12, '01')
          oneYearsAgo.setHours(0, 0, 0, 0)
          const lastMonth = monthsBefore(new Date(), 0)
          return date < oneYearsAgo || date > lastMonth
        }
      },
      iconKey: 0,
      empCodeAutoDispFlag: '',
      csvFileName: ''
    }
  },
  computed: {
    IfaTradeTrendSearchA002RequestModel() {
      return new IfaTradeTrendSearchA002RequestModel(this.form)
    },
    IfaTradeTrendSearchA004RequestModel() {
      return new IfaTradeTrendSearchA004RequestModel(this.form)
    },
    checkCommComment() {
      if (this.form.commComment) {
        return this.form.commComment.replace(/\s/g, '').replace(/　/g, '') !== ''
      } else {
        return false
      }
    },
    brokerChargeCustomerCountingUnitOptions() {
      const codeList = this.$_getCodeList('AGGREGATION_UNIT_TRADE_TREND', 1, 1)
      return codeList || []
    },
    userAccount() {
      return this.$store.getters.userAccount
    },
    brokerChargeCustomerCountingUnitOptionsDisabled() {
      if (!this.userAccount) return []
      const privId = this.userAccount?.medUsers?.privId
      if (privId !== '9') return []
      return ['1']
    }
  },
  watch: {
    'form.countingUnit': function(newVal, oldVal) {
      this.changePtn = 'pt12'
      if (newVal === '1') {
        this.changePtn = 'pt10'
      }
      if (newVal === '2') {
        this.changePtn = 'pt11'
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume) {
      const pt12 = this.$refs.commonSearchPt12.$el.querySelector('.ifa-common-search__course-select')
      if (pt12) {
        pt12.classList.remove('ifa-common-search__course-select')
      }
      if (!resume) {
        // カレンダのデフォルトとして当月をセットする
        this.yearMonthInitialize()
      }
      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      this.disabledCsvBtn = true
      this.iconKey++
      this.activeBtn = true

      // 初期表示時のみA001初期化処理を実行
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
        document.getElementById('IfaTradeTrendSearchA001').click()
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
      if (response.dataList.length > 0) {
        this.disabledCsvBtn = false
        this.pqGridOption.dataModel.data = response.dataList
        this.pqGridOption.maxHeight = calculateTableHeight.getDynamicTableHeight()
      } else {
        this.pqGridOption.dataModel.data = []
        this.disabledCsvBtn = true
      }
      this.activeBtn = true
      // グリッドの表示項目制御
      const info = getViewControllInfo(this.form.countingUnit)
      const gridCols = this.pqGridOption.colModel
      for (const key in gridCols) {
        gridCols[key].hidden = info[0].hiddenColumn.includes(gridCols[key].dataIndx)
      }

      // 期間指定（From）= 期間指定（To）
      if (this.form.periodMonth[0] === this.form.periodMonth[1]) {
        this.setHidden('monthlyRealProfitAndLoss', false) // 当月実現損益
        this.setHidden('periodRealProfitAndLoss', true) // 期間指定実現損益
      } else {
        this.setHidden('monthlyRealProfitAndLoss', true) // 当月実現損益
        this.setHidden('periodRealProfitAndLoss', false) // 期間指定実現損益
      }

      this.$refs['pqGrid'].refreshView(true)
    },

    yearMonthInitialize() {
      // 当月を設定する
      const date = new Date()
      const thisMonth = date.getFullYear() + '/' + String(date.getMonth() + 1).padStart(2, '0')
      this.form.periodMonth = [thisMonth, thisMonth]
    },
    butenCodeArrayValidator(rule, value, callback) {
      const visibleButenCode = value.split(',')
      const duplicateValue = this.getDuplicateValue(visibleButenCode, visibleButenCode.length)
      // 閲覧可能部店が重複していないかチェック
      if (duplicateValue.length > 0) {
        callback(new Error(getMessage('errors.codeDuplicate', ['閲覧可能部店', '閲覧可能部店', duplicateValue])))
        return
      }
      // OK
      callback()
    },
    getDuplicateValue(array, arrayLength) {
      const elementCount = new Map()
      array.forEach(item => {
        elementCount.set(item, (elementCount.get(item) || 0) + 1)
      })
      const result = []
      elementCount.forEach((count, item) => {
        if (count > 1) {
          result.push(item)
        }
      })
      return result
    },
    handleMediateUserPrivacy(data) {
      this.empCodeAutoDispFlag = data.empCodeAutoDispFlag
    },
    // クリアボタンによる初期化
    clearA003() {
      this.$refs['form']?.clearValidate()
      this.$refs.commonSearchPt12.formClear()
      this.form.countingUnit = '3'
      this.yearMonthInitialize()
      this.$refs['productCodeSelect'].handleCheckAll(true)
      this.form.visibleButenCode = ''
    },
    addCommaVisibleButenCode() {
      const array = this.form.visibleButenCode.replace(/,/g, '').split('')
      for (let i = 0; i < array.length; i++) {
      // リストの4th中に","を追加する
        if ((i + 1) % 4 === 0) {
          array.splice(i, 0, ',')
        }
      }
      this.form.visibleButenCode = array.join('')
    },
    handleClick(ui) {
      this.pqGridSelectedInfo = ui.rowData
      this.activeBtn = this.form.countingUnit !== '3'
      this.$_logDebug(ui.rowData)
    },
    // 顧客別メニューへ
    handleMovePortfolio() {
      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        const { butenCode, accountNumber } = this.pqGridSelectedInfo
        this.$_startShowMenu(this.$customerMenuInitialScreenId, {
          butenCode,
          accountNumber
        })
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.title.name
        )
      }
    },
    setCsvFileName() {
      // csvファイル名の設定
      const info = getViewControllInfo(this.form.countingUnit)
      this.csvFileName = info[0].csvFileName
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
  title: '取引動向検索',
  flexHeight: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}

obj.colModel = [
  { title: '仲介業者コード', dataIndx: 'brokerCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '仲介業者名', dataIndx: 'brokerName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員名', dataIndx: 'brokerChargeName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '部店コード', dataIndx: 'butenCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '口座番号', dataIndx: 'accountNumber', width: 120, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '顧客名', dataIndx: 'nameKanji', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '年齢', dataIndx: 'age', width: 120, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: 'Cランク', dataIndx: 'tcCompRank', width: 120, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '取引コース', dataIndx: 'customerAttribute', width: 300, editable: false, halign: 'center', align: 'left', hidden: false,
    render: function(ui) {
      const value = ui.rowData.customerAttribute
      return value ? getCodeValue('PRE_CONTRACT_DOC_CODE', 1, value) : '-'
    }
  },
  { title: '職業区分', dataIndx: 'occupation', width: 270, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '投資方針', dataIndx: 'investmentPlan', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '資金性格', dataIndx: 'fundCharacter', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '運用期間', dataIndx: 'emloymentPeriod', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '収入区分', dataIndx: 'incomeForm', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '年収', dataIndx: 'annualIncome', width: 150, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '金融資産', dataIndx: 'financialAssets', width: 170, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '投資経験年数（株式現物）', dataIndx: 'uaiExpStock', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '投資経験年数（信用）', dataIndx: 'uaiExpMargin', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '投資経験年数（その他投信）', dataIndx: 'uaiExpOtherfund', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '投資経験年数（外国証券）', dataIndx: 'uaiExpForeign', width: 200, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '営業員コード', dataIndx: 'empCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '扱者コード', dataIndx: 'dealerNumber', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '支店コード', dataIndx: 'brokerBranchCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '支店名', dataIndx: 'branchName', width: 300, dataType: 'string', editable: false, halign: 'center', hidden: false },
  { title: '閲覧可能部店', dataIndx: 'visibleButenCode', width: 120, dataType: 'string', editable: false, halign: 'center', align: 'center', hidden: false },
  { title: '取引回数', dataIndx: 'tradeCount', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false },
  { title: '調整後回数', dataIndx: 'adjustmentTradeCount', width: 200, dataType: 'string', editable: false, halign: 'center', align: 'right', hidden: false },
  { title: '買付約定総金額', dataIndx: 'grossAmountYen', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.grossAmountYen ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.grossAmountYen) : '-' }
  },
  { title: '預り資産', dataIndx: 'assets', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.assets ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.assets) : '-' }
  },
  { title: '資金回転率（％）', dataIndx: 'grossAmountYenAssets', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.grossAmountYenAssets ? ifaFormatUtils.noneWithCommaZeroPadding(ui.rowData.grossAmountYenAssets, 2) : '-' }
  },
  { title: '手数料', dataIndx: 'fee', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.fee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fee) : '-' }
  },
  { title: '営業員手数料合計', dataIndx: 'rewardPrice', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.rewardPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.rewardPrice) : '-' }
  },
  { title: '手数料依存率（％）', dataIndx: 'feeRewardPrice', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.feeRewardPrice ? ifaFormatUtils.noneWithCommaZeroPadding(ui.rowData.feeRewardPrice, 2) : '-' }
  },
  { title: '評価額', dataIndx: 'currenctPrice', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) { return ui.rowData.currenctPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.currenctPrice) : '-' }
  },
  { title: '評価損益', dataIndx: 'profitAndLoss', width: 200, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ui.rowData.profitAndLoss
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '評価損益率（％）', dataIndx: 'profitAndLossCurrenctPrice', width: 170, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ifaFormatUtils.noneWithCommaZeroPadding(ui.rowData.profitAndLossCurrenctPrice, 2)
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '前月比評価損益', dataIndx: 'currenctPriceMonth', width: 170, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ui.rowData.currenctPriceMonth
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '年間実現損益', dataIndx: 'yearRealProfitAndLoss', width: 170, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ui.rowData.yearRealProfitAndLoss
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '当月実現損益', dataIndx: 'monthlyRealProfitAndLoss', width: 170, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ui.rowData.monthlyRealProfitAndLoss
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  },
  { title: '期間指定実現損益', dataIndx: 'periodRealProfitAndLoss', width: 170, dataType: 'integer', editable: false, halign: 'center', align: 'right', hidden: false,
    render: function(ui) {
      const grid = this
      const data = ui.rowData.periodRealProfitAndLoss
      if (data) {
        if (Number(data) >= 0) {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__plus bold' })
        } else {
          grid.addClass({ rowIndx: ui.rowIndx, colIndx: ui.colIndx, cls: 'font-color__minus bold' })
        }
        return ifaFormatUtils.signedWithCommaInteger(data)
      } else {
        return '-'
      }
    }
  }
]

obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}

const getViewControllInfo = (countingUnit) => {
  return viewControllInfoList.filter(function(data) {
    return countingUnit === data.countingUnit
  })
}

// 画面制御情報リスト
const viewControllInfoList = [
  { countingUnit: '1', csvFileName: '取引動向照会', hiddenColumn: ['brokerChargeName', 'butenCode', 'accountNumber', 'nameKanji', 'age', 'tcCompRank', 'customerAttribute', 'occupation', 'investmentPlan', 'fundCharacter', 'emloymentPeriod', 'incomeForm', 'annualIncome', 'financialAssets', 'uaiExpStock', 'uaiExpMargin', 'uaiExpOtherfund', 'uaiExpForeign', 'empCode', 'brokerBranchCode', 'branchName', 'dealerNumber', 'visibleButenCode', 'rewardPrice', 'feeRewardPrice'] },
  { countingUnit: '2', csvFileName: '取引動向照会', hiddenColumn: ['butenCode', 'accountNumber', 'nameKanji', 'age', 'tcCompRank', 'customerAttribute', 'occupation', 'investmentPlan', 'fundCharacter', 'emloymentPeriod', 'incomeForm', 'annualIncome', 'financialAssets', 'uaiExpStock', 'uaiExpMargin', 'uaiExpOtherfund', 'uaiExpForeign', 'dealerNumber', 'visibleButenCode', 'rewardPrice', 'feeRewardPrice'] },
  { countingUnit: '3', csvFileName: '取引動向照会', hiddenColumn: [''] }
]

</script>
<style scoped>
:deep(.period_error_width) .el-form-item__error {
  width: 370px;
}
.date-info {
  padding-left: 490px;
  margin-bottom: 2rem;
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
