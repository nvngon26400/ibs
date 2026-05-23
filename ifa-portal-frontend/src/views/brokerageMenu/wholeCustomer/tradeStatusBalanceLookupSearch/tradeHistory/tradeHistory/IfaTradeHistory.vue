<template>
  <!-- 画面名：取引履歴 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>

    <!-- 検索条件エリア -->
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
            <el-col :span="23">
              <!-- 共通検索項目 -->
              <ifa-common-search
                ref="commonSearchItem"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :course-validate="true"
                original-screen-id="SUB020302_0201-01"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
              <!-- 閲覧可能部店 -->
              <ifa-input-text
                id="butenCodeArray_SUB020302_0201-01"
                v-model="form.butenCodeArray"
                label="閲覧可能部店"
                size="small"
                prop="butenCodeArray"
                :tooltip-enabled="!form.butenCodeArray < 1"
                :tooltip-content="form.butenCodeArray"
                tooltip-placement="bottom"
                tooltip-effect="light"
                style="width: 180px;"
                :domain="IfaButenCodeArrayDomainModel"
                @change="addCommaVisibleButenCode"
              ></ifa-input-text>
            </el-col>
            <!-- バルーンアイコン -->
            <el-col
              v-if="messageContent"
              :span="1"
              class="right_icon"
            >
              <ifa-balloon-icon
                :show-message="isInfoMessage"
                icon-type="info"
              >
                <div v-html="messageContent"></div>
              </ifa-balloon-icon>
            </el-col>
          </el-row>
          <el-row>
            <!-- 期間指定 -->
            <el-form-item class="form_label right_margin">
              <ifa-date-range-picker
                ref="datePicker"
                v-model="form.periodDate"
                label="期間指定"
                size="small"
                prop="periodDate"
                unlink-panels
                :required="true"
                :picker-options="checkBusinessDay"
              ></ifa-date-range-picker>
              <div style="margin-left: 9.4rem; white-space: pre; width: 310px;">※期間は6ヶ月以内を指定してください。（過去2年間の履歴を照会いただけます。）</div>
            </el-form-item>
            <!-- 証券種別 -->
            <el-form-item
              class="form_label"
              style="width: 414px;"
            >
              <product-code-select
                ref="securityClass"
                :list-kind="(isPrivId || form.count > 0) ? 'pt3' : 'pt7'"
                id-string="TradeHistoryProductCodeSelect"
                label="証券種別"
                @change-selected="form.securityClass = $event"
              ></product-code-select>
            </el-form-item>
            <!-- 銘柄コード -->
            <el-form-item class="form_label">
              <ifa-input-text
                id="brandCode12"
                v-model="form.brandCode12"
                label="銘柄コード"
                label-class="brand_width"
                class="middle_input"
                size="small"
                prop="brandCode12"
                :domain="IfaBrandCode12PeriodDomainModel"
              ></ifa-input-text>
            </el-form-item>
          </el-row>
          <el-row
            style="padding-top: 1.3rem;"
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
              :request-model="IfaTradeHistoryA002RequestModel"
              action-id="SUB020302_0201-01#A002"
              action-type="requestAction"
              @response-handler="responseHandlerA002($event)"
            ></ifa-button>
            <ifa-button
              id="btnTopInputClear"
              name="btnTopInputClear"
              text="クリア"
              width="90"
              color="secondary"
              small
              action-type="originalAction"
              @app-action-handler="handleFormClearA003"
            ></ifa-button>
          </el-row>
        </el-form>
      </div>
    </el-card>

    <!-- 取引履歴一覧 -->
    <el-card
      class="content-card"
      shadow="always"
      style="width: auto !important;"
    >
      <el-row>
        <div class="gridButtonArea">
          <ifa-button
            id="btmCustomerPortal"
            name="btmCustomerPortal"
            :disabled="activeBtn"
            text="顧客別メニュー"
            color="primary"
            small
            action-type="originalAction"
            @app-action-handler="handleMoveCustomerMenu"
          ></ifa-button>
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            text="CSV出力"
            :disabled="csvBtnDisabled"
            color="primary"
            small
            width="90"
            csv-file-name="取引履歴"
            :form="formRef"
            :pre-request-handler="preRequestHandlerA005"
            :request-model="IfaTradeHistoryA005RequestModel"
            action-id="SUB020302_0201-01#A005"
            action-type="outputCsvAction"
            @response-handler="responseHandlerA005($event)"
            @response-error-handler="responseErrorHandlerA005($event)"
          ></ifa-button>
        </div>
      </el-row>
      <el-row>
        <grid-table
          ref="gridTable"
          :options="pqGridOption"
          :auto-refresh="false"
          @click="handleClick($event)"
        ></grid-table>
      </el-row>
    </el-card>
    <!--仕組債情報-->
    <ifa-structured-bond-brand-info
      ref="ifaStructuredBondBrandInfo"
      :is-visible="isVisible"
      @close-modal="handleCloseModal"
    ></ifa-structured-bond-brand-info>
    <ifa-requester
      id="IfaTradeHistoryInitializeA001"
      action-type="requestAction"
      action-id="SUB020302_0201-01#A001"
      @response-handler="responseHandlerA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaStructuredBondBrandInfoInitializeA001"
      action-type="requestAction"
      :request-model="IfaTradeHistoryA006RequestModel"
      action-id="SUB020302_0104-02#A001"
      @response-handler="responseHandlerA006($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaTradeHistoryFormModel } from './js/IfaTradeHistoryFormModel.js'
import { IfaTradeHistoryA002RequestModel } from './js/IfaTradeHistoryA002RequestModel'
import { IfaTradeHistoryA005RequestModel } from './js/IfaTradeHistoryA005RequestModel'
import IfaStructuredBondBrandInfo from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeStatus/withdrawScheduleList/IfaStructuredBondBrandInfo.vue'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaButenCodeArrayDomainModel from '@/resource/domain/IfaButenCodeArrayDomainModel.json'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    GridTable,
    ProductCodeSelect,
    IfaCommonSearch,
    screenTitle,
    IfaStructuredBondBrandInfo
  },
  data() {
    return {
      IfaButenCodeArrayDomainModel: IfaButenCodeArrayDomainModel,
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      form: new IfaTradeHistoryFormModel(),
      formRef: {},
      isInfoMessage: false,
      messageContent: '',
      pqGridOption: getConvertedOption(obj),
      pqGridSelectedInfo: {},
      activeBtn: true,
      rules: {
        periodDate: { validator: this.periodDateValidator, trigger: 'change' },
        butenCodeArray: { validator: this.butenCodeArrayValidator, trigger: 'blur' }
      },
      checkBusinessDay: {
        shortcuts: [
          {
            text: '前営業日',
            value: () => {
              return [getFormattedDateValue(this.previousBusinessDay), getFormattedDateValue(this.previousBusinessDay)]
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
              const endDate = businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
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
      isVisible: false,
      IfaTradeHistoryA006RequestModel: {}
    }
  },
  computed: {
    IfaTradeHistoryA002RequestModel() {
      return new IfaTradeHistoryA002RequestModel(this.form)
    },
    IfaTradeHistoryA005RequestModel() {
      return new IfaTradeHistoryA005RequestModel(this.form)
    },
    csvBtnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
    },
    isPrivId() {
      if (this.$store.getters.userAccount) {
        return (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2')
      }
      return false
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    }
  },
  watch: {
    'form.periodDate': {
      handler(newValue) {
        this.form.periodDateFrom = newValue[0]
        this.form.periodDateTo = newValue[1]
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaTradeHistoryInitializeA001').click()
      })
      this.form.periodDate = [getFormattedDateValue(this.previousBusinessDay), getFormattedDateValue(this.previousBusinessDay)]
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
      this.activeBtn = true
      this.isInfoMessage = true
      if (this.messageContent) {
        setTimeout(() => {
          this.isInfoMessage = false
        }
        , 3000
        )
      }
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    preRequestHandlerA002() {
      this.IfaTradeHistoryA002RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaTradeHistoryA002RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
      this.IfaTradeHistoryA002RequestModel.customerNameSearchType = this.form.customerNameKanjiKanaTerms - 1
      this.pqGridOption.dataModel.data = []
      this.activeBtn = true
      this.$refs['gridTable'].refreshView(true)
    },
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.messageContent = this.form.comment
    },
    responseHandlerA002(response) {
      // 一覧へのデータの反映
      this.activeBtn = true
      Object.assign(this.form.tradeHistoryList, response.dataList)
      this.changeGridColumnVisible()
      this.pqGridOption.dataModel.data = response.dataList
      this.$refs['gridTable'].refreshView(true)
    },
    handleFormClearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['securityClass'].handleCheckAll(true) // 【初期値】全選択
      this.form.butenCodeArray = '' // 【初期値】""
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      this.form.brandCode12 = '' // 【初期値】""
      this.$refs['form'].clearValidate()
    },
    handleClick(ui) {
      this.pqGridSelectedInfo = ui.rowData
      this.activeBtn = false
      this.$_logDebug(ui.rowData)
      if (ui.dataIndx === 'structuredBondClassification' && getCodeValue('STRUCTURED_BOND_TYPE', 1, ui.rowData.structuredBondClassification) === '仕組債') {
        this.IfaTradeHistoryA006RequestModel = {
          brandCode: ui.rowData.brandCode
        }
        this.$nextTick(() => {
          document.getElementById('IfaStructuredBondBrandInfoInitializeA001').click()
        })
      }
    },
    handleMoveCustomerMenu() {
      const rowData = this.pqGridSelectedInfo
      const param = {
        butenCode: '',
        accountNumber: ''
      }
      param.butenCode = rowData.buten
      param.accountNumber = rowData.accountNumber

      if (isAccessible(this.$customerMenuAccessCheckScreenId)) {
        this.$_startShowMenu(this.$customerMenuInitialScreenId, param)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          this.form.title.name
        )
      }
    },
    addCommaVisibleButenCode() {
      const array = this.form.butenCodeArray.replace(/,/g, '').split('')
      for (let i = 0; i < array.length; i++) {
      // リストの4th中に","を追加する
        if ((i + 1) % 4 === 0) {
          array.splice(i, 0, ',')
        }
      }
      this.form.butenCodeArray = array.join('')
    },
    changeGridColumnVisible() {
      const isSecurityClass14Selected = this.form.securityClass.some(item => item.id === '14' && item.isSelected === true)
      if (isSecurityClass14Selected) {
        this.setHidden('structuredBondClassification', false) // 債券種別
      } else {
        this.setHidden('structuredBondClassification', true) // 債券種別
      }
      this.$refs['gridTable'].refreshView(true)
    },
    mediateUserPrivacy(data) {
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('branchCode', false)
        this.setHidden('branchName', false)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('branchCode', true)
        this.setHidden('branchName', true)
      }
      this.$refs['gridTable'].refreshView(true)
    },
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
      if (validateDateRangeFromTo(this.form.periodDate, 6, false)) {
        callback(getMessage('errors.accurately', ['期間指定は6ヶ月以内']))
        return
      }
      // OK
      callback()
    },
    butenCodeArrayValidator(rule, value, callback) {
      const butenCodeArray = value.split(',')
      const duplicateValue = this.getDuplicateValue(butenCodeArray, butenCodeArray.length)
      // 閲覧可能部店が重複していないかチェック
      if (duplicateValue.length > 0) {
        callback(new Error(getMessage('errors.codeDuplicate', ['閲覧可能部店', '閲覧可能部店', duplicateValue])))
        return
      }
      // OK
      callback()
    },
    preRequestHandlerA005() {
      this.IfaTradeHistoryA005RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaTradeHistoryA005RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
      this.IfaTradeHistoryA005RequestModel.customerNameSearchType = this.form.customerNameKanjiKanaTerms - 1
    },
    responseHandlerA005(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA005(error) {
      this.$_logError(error)
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
    responseHandlerA006(response) {
      this.$refs.ifaStructuredBondBrandInfo.onShow(response.dataList[0] || [])
      this.isVisible = true
    },
    handleCloseModal() {
      this.isVisible = false
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '取引履歴',
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
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '120', halign: 'center', editable: false, align: 'left', hidden: false },
  { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '100', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '100', halign: 'center', editable: false, align: 'left', hiddden: false },
  { title: '部店', dataType: 'string', dataIndx: 'buten', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '180', halign: 'center', editable: false, align: 'left' },
  { title: 'Cランク', dataType: 'string', dataIndx: 'tcCompRank', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '扱者コード', dataType: 'string', dataIndx: 'dealerNumber', width: '120', halign: 'center', editable: false, align: 'center' },
  { title: '取引コース', dataType: 'string', dataIndx: 'courseName', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'customerNameKanji', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(カナ)', dataType: 'string', dataIndx: 'customerNameKana', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '証券種別', dataType: 'string', dataIndx: 'securityClass', width: '120', halign: 'center', editable: false, align: 'left' },
  { title: '銘柄コード', dataType: 'string', dataIndx: 'brandCode', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '銘柄名', dataType: 'string', dataIndx: 'brandName', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '商品ランク', dataType: 'string', dataIndx: 'productRank', width: '200', halign: 'center', editable: false, align: 'center' },
  { title: '取引種別', dataType: 'string', dataIndx: 'tradeTypeName', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '預り区分', dataType: 'string', dataIndx: 'depositName', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '約定日', dataType: 'string', dataIndx: 'tradeDate', width: '200', halign: 'center', editable: false, align: 'left',
    render: function(ui) {
      return ui.rowData.tradeDate ? getFormattedDateValue(ui.rowData.tradeDate) : '-'
    }
  },
  { title: '受渡日', dataType: 'string', dataIndx: 'settlementDate', width: '200', halign: 'center', editable: false, align: 'left',
    render: function(ui) {
      return ui.rowData.settlementDate ? getFormattedDateValue(ui.rowData.settlementDate) : '-'
    }
  },
  { title: '単価', dataType: 'string', dataIndx: 'price', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.price ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.price) : '-'
    }
  },
  { title: '数量', dataType: 'string', dataIndx: 'quantity', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.quantity ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.quantity) : '-'
    }
  },
  { title: '約定金額（円貨）', dataType: 'string', dataIndx: 'contractAmountJpyAmount', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.contractAmountJpyAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.contractAmountJpyAmount) : '-'
    }
  },
  { title: '手数料（円貨）', dataType: 'string', dataIndx: 'yenFee', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.yenFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenFee) : '-'
    }
  },
  { title: '受渡金額（円貨）', dataType: 'string', dataIndx: 'yenDeliveryAmount', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.yenDeliveryAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenDeliveryAmount) : '-'
    }
  },
  { title: '通貨', dataType: 'string', dataIndx: 'currency', width: '200', halign: 'center', editable: false, align: 'center' },
  { title: '約定金額（外貨）', dataType: 'string', dataIndx: 'contractAmountForeign', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.contractAmountForeign ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.contractAmountForeign) : '-'
    }
  },
  { title: '受渡金額（外貨）', dataType: 'string', dataIndx: 'foreignDeliveryAmount', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.foreignDeliveryAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignDeliveryAmount) : '-'
    }
  },
  { title: '手数料（外貨）', dataType: 'string', dataIndx: 'foreignFee', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.foreignFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignFee) : '-'
    }
  },
  { title: '為替レート', dataType: 'string', dataIndx: 'fxRate', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.fxRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate) : '-'
    }
  },
  { title: '支店コード', dataType: 'string', dataIndx: 'branchCode', width: '200', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '支店名', dataType: 'string', dataIndx: 'branchName', width: '200', halign: 'center', editable: false, align: 'left', hidden: false },
  { title: '債券　媒介/非媒介', dataType: 'string', dataIndx: 'bondIntermediary', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '米国株　店頭/委託', dataType: 'string', dataIndx: 'usStockStoreEntrust', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '閲覧可能部店', dataType: 'string', dataIndx: 'visibleButenCode', width: '200', halign: 'center', editable: false, align: 'center' },
  { title: '債券種別', dataType: 'string', dataIndx: 'structuredBondClassification', width: '180', halign: 'center', editable: false, align: 'left',
    render: function(ui) {
      const value = ui.rowData.structuredBondClassification
      return value ? `<a class="el-link"><span style="color: #092987;">` + getCodeValue('STRUCTURED_BOND_TYPE', 1, value) + `</span></a> 
        <style>
        a {
          text-decoration: none;
          color: #092987;
        }
        a:focus, a:hover {
          cursor: pointer;
          text-decoration: underline #409EFF;
          text-underline-offset: 0.2rem;
          color: #092987;
          opacity: 0.7;
        }
        </style>` : '-'
    }
  }
]

</script>
<style scoped>
:deep(.el-link) {
  text-decoration: underline !important;
  text-underline-offset: 0.2rem;
}
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}

.checkmark {
  margin-top: 10px;
  margin-right: 5px;
}

:deep(.el-checkbox__label) {
  font-weight: bold;
}

.middle_input {
  width: 200px;
}

:deep(.checkmark) .el-checkbox＿label {
  font-weight:bold;
  text-align : right;
}

.form_button {
  padding-left: 46px;
}

:deep(.el-textarea__inner) {
  width:82%;
  margin-left:85px;
  resize: none;
}

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
}

.form_lower {
  padding-top: 25px;
}

.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}

.date_input {
  width: 135px;
}

:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
.right_margin :deep(.el-form-item) {
  margin-right: 149px;
  margin-bottom: 10px;
}
.brand_width :deep(.el-form-item__label) {
  width: 100px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
