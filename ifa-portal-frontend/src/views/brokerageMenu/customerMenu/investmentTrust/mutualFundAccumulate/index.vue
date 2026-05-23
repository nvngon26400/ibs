<template>
  <div class="wrapper">
    <screen-title :text="screenTitle"></screen-title>

    <!-- 積立金額合計表示エリア -->
    <ifa-mutual-fund-accumulate-base-info-card
      v-if="baseInfo"
      :key="rerenderKey"
      :base-info="baseInfo"
      :status="status"
      :is-redirect="isRedirect"
    ></ifa-mutual-fund-accumulate-base-info-card>

    <!-- 次回発注予定表示エリア -->
    <ifa-mutual-fund-accumulate-sub-info-card
      v-if="status !== 'bulk-update'"
      :key="rerenderKey"
      :status="status"
      :sub-info="subInfo"
    ></ifa-mutual-fund-accumulate-sub-info-card>

    <!-- 投信積立設定済一覧明細エリア -->
    <el-card v-show="status === 'overview'">
      <div style="display: flex; flex-direction: column; row-gap: 10px; padding: 5px 0;">
        <el-row style="display: flex; align-items: center;">
          <ifa-button
            id="bulk-update"
            text="選択した積立設定を変更"
            small
            :disabled="selectedItems.length === 0"
            color="primary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="handleBulkUpdate"
          ></ifa-button>
          <ifa-button
            id="cancel"
            text="選択した積立設定を解除"
            small
            :disabled="selectedItems.length === 0"
            color="secondary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="handleSelectedBrands"
          ></ifa-button>
        </el-row>
        <el-row>
          <grid-table
            ref="pqGrid"
            :options="pqGridOption"
            :auto-refresh="false"
          ></grid-table>
          <button
            id="create-btn"
            type="button"
            value=""
            hidden
            @click="handleCreate"
          ></button>
          <button
            id="update-btn"
            type="button"
            hidden
            value=""
            @click="handleUpdate"
          ></button>
        </el-row>
      </div>
    </el-card>

    <!-- 投信積立設定入力 entry -->
    <ifa-mutual-fund-accumulate-create-form
      v-if="status === 'create'"
      :key="rerenderKey"
      :info="createFormInfo"
      @on-redirect="handleBack($event)"
      @on-close="handleCloseForm"
    ></ifa-mutual-fund-accumulate-create-form>

    <!-- 投信積立設定変更入力 entry -->
    <ifa-mutual-fund-accumulate-editable-form
      v-if="status === 'update'"
      :info="updateFormInfo"
      @on-close="handleCloseForm"
    ></ifa-mutual-fund-accumulate-editable-form>

    <!-- 投信積立設定一括変更入力 entry -->
    <ifa-mutual-fund-accumulate-bulk-editable-form
      v-if="status === 'bulk-update'"
      :info="bulkUpdateFormInfo"
      @on-close="handleCloseForm"
    ></ifa-mutual-fund-accumulate-bulk-editable-form>

    <!-- 投信積立設定解除 entry -->
    <ifa-mutual-fund-accumulate-cancel-selected-brands
      v-if="cancelSelectedBrandsVisible"
      :visible="cancelSelectedBrandsVisible"
      :data="cancelConfirmBrandsInfo"
      @on-close="handleCloseCancelBrands"
    ></ifa-mutual-fund-accumulate-cancel-selected-brands>

    <!-- back to the top -->
    <el-backtop
      target="#app-main"
      :right="100"
      :bottom="120"
    ></el-backtop>

    <!-- list init query -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBrandListInitializeA001"
      action-id="SUB0202_0403-01#A001"
      action-type="requestAction"
      :request-model="{}"
      :pre-request-handler="handleReset"
      @response-handler="brandListInitializeA001Handler($event)"
      @response-error-handler="brandListInitializeA001ErrorHandler"
    ></ifa-requester>

    <!-- other page redirect to the current page's init query -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBrandListInitializeA002"
      action-id="SUB0202_0403-01#A002"
      action-type="requestAction"
      :request-model="settingBrandListInitA002RequestModel"
      :pre-request-handler="handleReset"
      @response-handler="brandListInitializeA002Handler($event)"
      @response-error-handler="brandListInitializeA002ErrorHandler"
    ></ifa-requester>

    <!-- 投信積立設定入力 check -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBrandListAddA003"
      action-id="SUB0202_0403-01#A003"
      action-type="requestAction"
      :request-model="checkBrandListAddA003RequestModel"
      @response-handler="checkBrandListAddA003Handler($event)"
      @response-error-handler="checkBrandListAddA003ErrorHandler"
    ></ifa-requester>

    <!-- 投信積立設定変更入力 check -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBrandListChangeA004"
      action-id="SUB0202_0403-01#A004"
      action-type="requestAction"
      :request-model="checkBrandListChangeA004RequestModel"
      @response-handler="checkBrandListChangeA004Handler($event)"
      @response-error-handler="checkBrandListChangeA004ErrorHandler"
    ></ifa-requester>

    <!-- 設定入力 init -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingInputInitializeA001"
      action-id="SUB0202_0403-02_1#A001"
      action-type="requestAction"
      :request-model="settingInputInitA001RequestModel"
      @response-handler="settingInputInitializeA001Handler($event)"
      @response-error-handler="settingInputInitializeA001ErrorHandler"
    ></ifa-requester>

    <!-- 設定変更入力 init -->
    <ifa-requester
      id="IfaMutualFundAccumulateSettingChangeInputInitializeA001"
      action-id="SUB0202_0403-03_1#A001"
      action-type="requestAction"
      :request-model="settingChangeInputInitA001RequestModel"
      @response-handler="settingChangeInputInitializeA001Handler($event)"
      @response-error-handler="settingChangeInputInitializeA001ErrorHandler"
    ></ifa-requester>

    <!-- 投信積立設定一括変更入力 check -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBrandListBulkChangeA007"
      action-id="SUB0202_0403-01#A007"
      action-type="requestAction"
      :request-model="settingBrandListBulkChangeA007RequestModel"
      @response-handler="settingBrandListBulkChangeA007Handler($event)"
      @response-error-handler="settingBrandListBulkChangeA007ErrorHandler"
    ></ifa-requester>

    <!-- 投信積立設定一括変更入力 init -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingBulkChangeInputInitializeA001"
      action-id="SUB0202_0403-05_1#A001"
      action-type="requestAction"
      :request-model="settingBulkChangeInputInitA001RequestModel"
      @response-handler="settingBulkChangeInputInitA001Handler($event)"
      @response-error-handler="settingBulkChangeInputInitA001ErrorHandler"
    ></ifa-requester>

    <!-- 投信積立設定解除 init -->
    <ifa-requester
      id="ifaMutualFundAccumulateSettingCancelConfirmInitializeA001"
      action-id="SUB0202_0403-04_1#A001"
      action-type="requestAction"
      :request-model="cancelConfirmInitializeA001RequestModel"
      @response-handler="cancelConfirmInitializeA001Handler($event)"
      @response-error-handler="cancelConfirmInitializeA001ErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import ScreenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import GridTable from '@/components/GridTable/index.vue'
import IfaButton from '@/components/Button/IfaButton.vue'
import { notifyMessage, getMessage } from '@/utils/errorHandler'

import IfaMutualFundAccumulateBaseInfoCard from './components/IfaMutualFundAccumulateBaseInfoCard.vue'
import IfaMutualFundAccumulateSubInfoCard from './components/IfaMutualFundAccumulateSubInfoCard.vue'
import IfaMutualFundAccumulateCreateForm from './components/IfaMutualFundAccumulateCreateForm.vue'
import IfaMutualFundAccumulateEditableForm from './components/IfaMutualFundAccumulateEditableForm.vue'
import IfaMutualFundAccumulateBulkEditableForm from './components/IfaMutualFundAccumulateBulkEditableForm/index.vue'
import IfaMutualFundAccumulateCancelSelectedBrands from './components/IfaMutualFundAccumulateCancelSelectedBrands/index.vue'

import { COL_MODEL, getTableOptions } from './index.helper'
import { IfaMutualFundAccumulateSettingBrandListInitA002RequestModel } from './js/IfaMutualFundAccumulateSettingBrandListInitA002RequestModel'
import { IfaMutualFundAccumulateSettingInputInitA001RequestModel } from './js/IfaMutualFundAccumulateSettingInputInitA001RequestModel'
import { IfaMutualFundAccumulateCancelConfirmInitA001RequestModel } from './js/IfaMutualFundAccumulateCancelConfirmInitA001RequestModel'
import { IfaMutualFundAccumulateSettingChangeInputInitA001RequestModel } from './js/IfaMutualFundAccumulateSettingChangeInputInitA001RequestModel'
import { IfaMutualFundAccumulateSettingBrandListAddA003RequestModel } from './js/IfaMutualFundAccumulateSettingBrandListAddA003RequestModel'
import { IfaMutualFundAccumulateSettingBrandListChangeA004RequestModel } from './js/IfaMutualFundAccumulateSettingBrandListChangeA004RequestModel'
import { IfaMutualFundAccumulateSettingBrandListBulkChangeA007RequestModel } from './js/IfaMutualFundAccumulateSettingBrandListBulkChangeA007RequestModel'
import { IfaMutualFundAccumulateSettingBulkChangeInitA001RequestModel } from './js/IfaMutualFundAccumulateSettingBulkChangeInitA001RequestModel'

const MAX_SELECTED_BRANDS = 20
const PAGE_NAME = '定期積立'

export default {
  components: {
    IfaButton,
    ScreenTitle,
    GridTable,
    IfaRequester,
    IfaMutualFundAccumulateBaseInfoCard,
    IfaMutualFundAccumulateSubInfoCard,
    IfaMutualFundAccumulateCreateForm,
    IfaMutualFundAccumulateEditableForm,
    IfaMutualFundAccumulateBulkEditableForm,
    IfaMutualFundAccumulateCancelSelectedBrands
  },
  data() {
    return {
      // enum: overview | create | update | bulk-update
      status: 'overview',
      canCancel: false,
      baseInfo: {},
      subInfo: {},
      selectedItems: [],
      cancelConfirmBrandsInfo: {},
      cancelSelectedBrandsVisible: false,
      pqGridOption: null,
      tableList: [],
      cancelConfirmInitA001List: null,
      bulkUpdatePayload: null,
      settingInputInitA001Info: null,
      settingInputInitA002Info: null,
      settingChangeInputInitA001Info: null,
      createFormInfo: null,
      updateFormInfo: null,
      bulkUpdateFormInfo: null,
      isRedirect: false,
      rerenderKey: 0
    }
  },
  computed: {
    screenTitle() {
      switch (this.status) {
        case 'overview':
          return '投信積立設定済銘柄一覧'
        case 'create':
          return '投信積立設定入力'
        case 'update':
          return '投信積立設定変更入力'
        case 'bulk-update':
          return '投信積立設定一括変更入力'
        default:
          return '投信積立設定済銘柄一覧'
      }
    },
    cancelConfirmInitializeA001RequestModel() {
      return new IfaMutualFundAccumulateCancelConfirmInitA001RequestModel(this.cancelConfirmInitA001List)
    },
    settingBrandListBulkChangeA007RequestModel() {
      return new IfaMutualFundAccumulateSettingBrandListBulkChangeA007RequestModel(this.bulkUpdatePayload)
    },
    settingBulkChangeInputInitA001RequestModel() {
      return new IfaMutualFundAccumulateSettingBulkChangeInitA001RequestModel(this.bulkUpdatePayload)
    },
    settingBrandListInitA002RequestModel() {
      return new IfaMutualFundAccumulateSettingBrandListInitA002RequestModel(this.settingInputInitA002Info)
    },
    checkBrandListAddA003RequestModel() {
      return new IfaMutualFundAccumulateSettingBrandListAddA003RequestModel(this.settingInputInitA001Info)
    },
    checkBrandListChangeA004RequestModel() {
      return new IfaMutualFundAccumulateSettingBrandListChangeA004RequestModel(this.settingChangeInputInitA001Info)
    },
    settingInputInitA001RequestModel() {
      return new IfaMutualFundAccumulateSettingInputInitA001RequestModel(this.settingInputInitA001Info)
    },
    settingChangeInputInitA001RequestModel() {
      return new IfaMutualFundAccumulateSettingChangeInputInitA001RequestModel(this.settingChangeInputInitA001Info)
    }
  },
  created() {
    this.pqGridOption = getTableOptions(COL_MODEL)
  },
  methods: {
    onShow() {
      this.$refs.pqGrid.$el.addEventListener('click', this.handleCheckboxClick)
      this.pqGridOption.dataModel.data = []
      this.status = 'overview'

      this.$nextTick(() => {
        this.$refs.pqGrid.refreshView(true)
      })

      const pageParams = this.$store.getters.pageInfo.params

      /*
      * page code: SUB0202_0401 -> 国内投信購入・積立可能一覧
      * page code: SUB0202_010201 -> 保有商品一覧
      */
      if (pageParams && (pageParams.source === 'SUB0202_0401' || pageParams.source === 'SUB0202_010201')) {
        const clonePageParams = { ...pageParams }
        const { data: { data, fundCodeTimes, fundCodeIssues, kyoukaiCd, listFlag, errorLevel, message, requestedTime }} = clonePageParams
        // reset store -> pageInfo
        this.$store.dispatch('page/setPageInfo', {
          menuId: 'SUB0202_0403',
          label: PAGE_NAME,
          componentName: 'ifa-mutual-fund-accumulate',
          target: 'tab-investment-trust:ifa-mutual-fund-accumulate'
        })

        if (!kyoukaiCd || !fundCodeIssues || !fundCodeTimes) return

        if (data?.goToPageActionId && data?.goToPageActionId === 'SUB0202_0403') {
          this.settingInputInitA002Info = {
            fundCode: data?.fundCode,
            mfkaisu: data?.mfkaisu,
            mfgo: data?.mfgo,
            source: pageParams.source,
            step: data?.step,
            listFlag: listFlag ?? false
          }

          this.$nextTick(() => {
            document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA002').click()
          })

          return
        }

        this.createFormInfo = data
        this.baseInfo = this.transformBaseInfo(data)
        this.subInfo = this.transformSubInfo(data)

        // when redirect the create form page, the comp wasn't render
        this.rerenderKey += 1

        this.status = 'create'

        // when an external page jumping into this page with a warning, show it
        if (errorLevel === 2) {
          notifyMessage(errorLevel, message, PAGE_NAME, requestedTime)
        }

        return
      }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA001').click()
      })
    },

    onHide() {
      this.status = 'overview'
      this.pqGridOption.dataModel.data = []
      this.tableList = []
      this.selectedItems = []
      this.cancelConfirmBrandsInfo = null
      this.$refs.pqGrid.$el.removeEventListener('click', this.handleCheckboxClick)

      this.$nextTick(() => {
        this.$refs.pqGrid.refreshView(true)
      })
    },

    handleReset() {
      this.pqGridOption.dataModel.data = []
      this.tableList = []
      this.selectedItems = []
      this.cancelConfirmBrandsInfo = null
      this.baseInfo = {}
      this.subInfo = {}

      this.$nextTick(() => {
        this.$refs.pqGrid.refreshView(true)
      })
    },

    handleScrollToTop() {
      const ctn = document.getElementById('app-main')
      ctn.scrollTo({ top: 0, behavior: 'smooth' })
    },

    handleCloseForm() {
      this.status = 'overview'

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA001').click()
      })
    },

    handleCreate() {
      const rowData = JSON.parse(decodeURIComponent(document.getElementById('create-btn').value))
      const { rowData: { mfgo, fundCode, mfkaisu }} = rowData
      const source = 'SUB0202_0403'

      this.settingInputInitA001Info = { fundCode, mfkaisu, mfgo, source }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListAddA003').click()
      })
    },

    handleBack(data) {
      this.isRedirect = true
      this.$_startShowMenu('SUB0202_0401', data)
    },

    handleUpdate() {
      const rowData = JSON.parse(decodeURIComponent(document.getElementById('update-btn').value))
      const { rowData: { mfgo, fundCode, mfkaisu, accountType, paymentMethod }} = rowData

      this.settingChangeInputInitA001Info = { fundCode, mfkaisu, mfgo, accountType, paymentMethod }

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListChangeA004').click()
      })
    },

    handleBulkUpdate() {
      if (this.selectedItems.length > MAX_SELECTED_BRANDS) {
        const message = getMessage('errors.fndReserveChange.overSelect')

        return notifyMessage(-1, message, PAGE_NAME)
      }

      const isEachItemCanBeChanged = this.selectedItems.every(v => v.accumulateChangeAvailability === '1')

      if (!isEachItemCanBeChanged) {
        const brandCodeList = this.selectedItems
          .filter(x => x.accumulateChangeAvailability === '2')
          .map(v => `${v.mfkaisu}.${v.mfgo}`)
          .sort((a, b) => a.localeCompare(b))

        const brandCodeStr = Array.from(new Set(brandCodeList)).join('、')
        const message = `${getMessage('errors.fndReserveChecks.notChange')}<br>銘柄コード: ${brandCodeStr}`

        return notifyMessage(-1, message, PAGE_NAME)
      }

      this.bulkUpdatePayload = this.selectedItems.map(v => ({
        mfgo: v.mfgo,
        mfkaisu: v.mfkaisu,
        fundCode: v.fundCode,
        paymentMethod: v.paymentMethod,
        accountType: v.accountType
      }))

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListBulkChangeA007').click()
      })
    },

    handleSelectedBrands() {
      if (this.selectedItems.length > MAX_SELECTED_BRANDS) {
        const message = getMessage('errors.fndReserveCancel.overSelect')

        return notifyMessage(-1, message, PAGE_NAME)
      }

      this.cancelConfirmInitA001List = this.selectedItems.map(v => ({
        mfgo: v.mfgo,
        mfkaisu: v.mfkaisu,
        fundCode: v.fundCode,
        paymentMethod: v.paymentMethod,
        accountType: v.accountType
      }))

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingCancelConfirmInitializeA001').click()
      })
    },

    handleCloseCancelBrands() {
      this.cancelSelectedBrandsVisible = false

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA001').click()
      })
    },

    handleCheckboxClick(e) {
      const target = e.target
      if (target.tagName !== 'INPUT' && target.type !== 'checkbox') return

      const id = target.getAttribute('data-id')
      const isChecked = target.checked

      const listItem = this.tableList.find(v => v.id === id)

      if (listItem) {
        listItem.selected = isChecked
      }

      if (isChecked) {
        this.selectedItems.push(listItem)
      } else {
        this.selectedItems = this.selectedItems.filter(item => item.id !== id)
      }
    },

    transformBaseInfo(data) {
      return {
        oneMonthSumAmount: {
          ...data?.oneMonthSumAmount,
          oneMonthLimitNisaReserveAmount: data?.oneMonthLimitNisaReserveAmount,
          oneMonthComment: data?.oneMonthComment ?? ''
        },
        oneYearSumAmount: {
          ...data?.oneYearSumAmount,
          oneYearLimitNisaReserveAmount: data?.oneYearLimitNisaReserveAmount,
          oneYearComment: data?.oneYearComment ?? ''
        },
        bonusMonthSettingInfo: {
          ...data?.bonusMonthSettingInfo,
          limitNisaReserveAmount: data?.oneYearLimitNisaReserveAmount
        },
        pointSettingInfo: {
          pointSetting: data?.pointSetting,
          pointUseUpperLimit: data?.pointUseUpperLimit
        }
      }
    },

    transformSubInfo(data) {
      return {
        ...data?.nextOrderPlan,
        mfkaisu: data?.mfkaisu,
        mfgo: data?.mfgo,
        fundName: data?.fundName,
        tick: data?.tick,
        standardPriceStr: data?.standardPriceStr,
        standardPriceUnitStr: data?.standardPriceUnitStr,
        previousChangeStr: data?.previousChangeStr,
        previousChangeSign: data?.previousChangeSign,
        previousRatio: data?.previousRatio,
        netAsset: data?.netAssetStr,
        priceDate: data?.priceDate
      }
    },

    brandListInitializeA001Handler(res) {
      this.$_logDebug(res)

      const { detailList, nextOrderPlan, ...rest } = res?.dataList[0]

      this.tableList = (detailList && detailList?.length > 0)
        ? detailList?.map(v => ({ ...v, selected: false }))
        : []

      this.baseInfo = this.transformBaseInfo(rest)
      this.subInfo = nextOrderPlan

      this.pqGridOption.dataModel.data = this.tableList
      this.$nextTick(() => {
        this.$refs.pqGrid.refreshView(true)

        setTimeout(() => {
          this.handleScrollToTop()
        }, 100)
      })
    },

    brandListInitializeA001ErrorHandler(error) {
      this.$_logError(error)

      this.status = 'overview'
    },

    brandListInitializeA002Handler(res) {
      this.$_logDebug(res)

      this.status = 'overview'

      const { detailList, nextOrderPlan, ...rest } = res?.dataList[0]

      this.tableList = (detailList && detailList?.length > 0)
        ? detailList?.map(v => ({ ...v, selected: false }))
        : []

      this.baseInfo = this.transformBaseInfo(rest)
      this.subInfo = nextOrderPlan

      this.pqGridOption.dataModel.data = this.tableList
      this.$nextTick(() => {
        this.$refs.pqGrid.refreshView(true)
      })
    },

    brandListInitializeA002ErrorHandler(error) {
      this.$_logError(error)

      const data = error?.dataList?.[0] ?? {}

      if (data?.step) {
        this.$_startShowMenu(data?.source, {
          listFlag: data?.listFlag ?? false,
          fundCodeTimes: data?.mfkaisu,
          fundCodeIssues: data?.mfgo,
          source: data?.step,
          errorMsg: error?.message,
          errorLevel: error?.errorLevel
        })
      }
    },

    cancelConfirmInitializeA001Handler(res) {
      this.$_logDebug(res)

      this.cancelConfirmBrandsInfo = res?.dataList[0]
      this.cancelSelectedBrandsVisible = true
    },

    cancelConfirmInitializeA001ErrorHandler(error) {
      this.$_logError(error)

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA001').click()
      })
    },

    settingInputInitializeA001Handler(res) {
      this.$_logDebug(res)

      const data = res?.dataList?.[0] ?? {}

      this.createFormInfo = data
      this.baseInfo = this.transformBaseInfo(data)
      this.subInfo = this.transformSubInfo(data)
      this.status = 'create'

      this.handleScrollToTop()
    },

    settingInputInitializeA001ErrorHandler(error) {
      this.$_logError(error)

      this.status = 'overview'

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBrandListInitializeA001').click()
      })
    },

    checkBrandListAddA003Handler(_res) {
      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingInputInitializeA001').click()
      })
    },

    checkBrandListAddA003ErrorHandler(error) {
      this.$_logError(error)

      this.status = 'overview'
    },

    checkBrandListChangeA004Handler(_res) {
      this.$nextTick(() => {
        document.getElementById('IfaMutualFundAccumulateSettingChangeInputInitializeA001').click()
      })
    },

    checkBrandListChangeA004ErrorHandler(error) {
      this.$_logError(error)

      this.status = 'overview'
    },

    settingChangeInputInitializeA001Handler(res) {
      this.$_logDebug(res)

      const data = res?.dataList?.[0] ?? {}

      // '01;11;21;' -> ['01','11','21']
      const settingReserveMultiDay = data?.settingReserveMultiDay
        ? data?.settingReserveMultiDay.split(';').filter(item => Boolean(item))
        : []

      const nisaOptions = []
      if (data.nisaBarelyBuyingType === '1') {
        nisaOptions.push('1')
      }
      if (data.taxShiftType === '1') {
        nisaOptions.push('2')
      }

      this.updateFormInfo = {
        ...data,
        nisaOptions,
        settingReserveMultiDay,
        kanyuKbn: null,
        receiveMethod: null,
        checkMadoAki: []
      }

      this.baseInfo = this.transformBaseInfo(data)
      this.subInfo = this.transformSubInfo(data)
      this.status = 'update'
      this.handleScrollToTop()
    },

    settingChangeInputInitializeA001ErrorHandler(error) {
      this.$_logError(error)

      this.status = 'overview'
    },

    settingBrandListBulkChangeA007Handler(_res) {
      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBulkChangeInputInitializeA001').click()
      })
    },

    settingBrandListBulkChangeA007ErrorHandler(error) {
      this.$_logDebug(error)
      this.status = 'overview'
    },

    settingBulkChangeInputInitA001Handler(res) {
      this.$_logDebug(res)

      const data = res?.dataList?.[0] ?? {}

      if (!data?.bulkChangeList || data?.bulkChangeList?.length === 0) {
        const message = getMessage('errors.cmn.notSettingFund')

        return notifyMessage(-1, message, PAGE_NAME)
      }

      // '01;11;21' -> ['01','11','21']
      const transformBulkChangeList = data.bulkChangeList.map(v => {
        const settingReserveMultiDay = v?.settingReserveMultiDay
          ? v?.settingReserveMultiDay.split(';').filter(item => Boolean(item))
          : []

        return {
          ...v,
          settingReserveMultiDay
        }
      })

      this.bulkUpdateFormInfo = {
        ...data,
        bulkChangeList: transformBulkChangeList,
        kanyuKbn: null,
        receiveMethod: null,
        checkMadoAki: false
      }

      this.baseInfo = this.transformBaseInfo(data)
      this.subInfo = {}
      this.status = 'bulk-update'

      this.handleScrollToTop()
    },

    settingBulkChangeInputInitA001ErrorHandler(error) {
      this.$_logDebug(error)
      this.status = 'overview'
    }
  }
}
</script>

<style scoped lang="scss">
.wrapper {
  display: flex;
  flex-direction: column;
  row-gap: 10px;
  padding: 0 10px 30px 10px;

  .caption-container {
    margin-left: 0;
  }
}

:deep(.pg-grid-cell) {
  height: 100px;
  padding-left: 0;
  padding-right: 0;
}
</style>
