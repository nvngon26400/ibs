<template>
  <!-- 画面名：証券・金銭・残高照会 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
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
          <!-- ここに検索条件用のコンテンツを記述する -->
          <el-row>
            <el-col
              :span="23"
            >
              <ifa-common-search
                ref="commonSearchItem"
                display-pattern="pt1"
                list-pattern="pt2"
                :form="form"
                :course-validate="true"
                original-screen-id="SUB020302_0301-01"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
            <!-- iアイコン -->
            <el-col
              v-if="messageContent.length > 0"
              :span="1"
              class="right_icon"
            >
              <ifa-balloon-icon
                :show-message="isInfoMessage"
                icon-type="info"
                :message="messageContent"
              ></ifa-balloon-icon>
            </el-col>
          <!-- /iアイコン -->
          </el-row>
          <el-row>
            <el-col
              :span="5"
            >
              <el-tooltip
                :disabled="form.butenCodeArray.length < 1"
                :content="form.butenCodeArray"
                placement="bottom"
                effect="light"
              >
                <ifa-input-text
                  id="butenCodeArray_SUB020302_0301-01"
                  v-model="form.butenCodeArray"
                  label="閲覧可能部店"
                  size="small"
                  prop="butenCodeArray"
                  style="width: 180px;"
                  :domain="IfaButenCodeArrayDomainModel"
                  @change="addCommaVisibleButenCode"
                >
                </ifa-input-text>
              </el-tooltip>
            </el-col>
          </el-row>
          <!-- ここにボタンより下段の検索条件用のコンテンツを記述する -->
          <el-row>
            <el-form-item
              class="form_label bottom_margin"
            >
              <ifa-month-picker
                v-model="form.period"
                label="期間指定"
                size="small"
                prop="period"
                :required="true"
                :domain="IfaDate6YearMonthDomainModel"
                :picker-options="pickerOptions"
              ></ifa-month-picker>
            </el-form-item>
            <el-form-item
              class="form_label_securitiesMoney"
            >
              <product-code-select
                ref="productCodeSecurityTypeSecuritiesMoney"
                label="証券 金銭"
                list-kind="pt1"
                id-string="SecurityTypeSecuritiesMoney"
                prop="securityTypeSecuritiesMoney"
                @change-selected="form.securityTypeSecuritiesMoney = $event"
              ></product-code-select>
            </el-form-item>
            <el-form-item
              class="form_label_creditFuturesOp"
            >
              <product-code-select
                ref="productCodeSecurityTypeCreditFuturesOp"
                label="信用・先OP"
                list-kind="pt2"
                id-string="SecurityTypeCreditFuturesOp"
                prop="securityTypeCreditFuturesOp"
                @change-selected="form.securityTypeCreditFuturesOp = $event"
              ></product-code-select>
            </el-form-item>
            <el-form-item
              class="form_label_brandCode"
            >
              <ifa-input-text
                id="brandCode12"
                v-model="form.brandCode"
                size="small"
                maxlength="12"
                label="銘柄コード"
                prop="brandCode"
                :domain="IfaBrandCode12PeriodDomainModel"
              >
              </ifa-input-text>
            </el-form-item>
          </el-row>
          <el-row class="form_row">
            <div style="position: relative; top: -22px; padding-left: 151px;">{{ form.periodAlert }}</div>
          </el-row>
          <el-row class="form_button">
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="表示"
              width="90"
              search
              small
              :form="formRef"
              :request-model="requestModelIfaSecurityCashBalanceLookupA002"
              action-id="SUB020302_0301-01#A002"
              action-type="requestAction"
              :pre-request-handler="preRequestHandlerA002"
              @response-handler="responseHandlerA002($event)"
              @response-error-handler="handleErrorResponseA002($event)"
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
    <el-card
      class="content-card"
      style="width: auto !important;"
    >
      <el-row>
        <div class="gridButtonArea">
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            :disabled="csvBtnDisabled"
            text="CSV出力"
            color="primary"
            small
            width="90"
            action-id="SUB020302_0301-01#A006"
            :form="formRef"
            :request-model="requestModelIfaSecurityCashBalanceLookupA006"
            csv-file-name="証券・金銭 残高照会"
            action-type="outputCsvAction"
            :pre-request-handler="preRequestHandlerA006"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="true"
          ></ifa-button>
        </div>
      </el-row>
      <grid-table
        ref="gridTable"
        :options="pqGridOption"
        :auto-refresh="false"
        @click="handleClick"
      ></grid-table>
    </el-card>

    <!--仕組債情報-->
    <ifa-structured-bond-brand-info
      ref="ifaStructuredBondBrandInfo"
      :is-visible="isVisible"
      @close-modal="handleCloseModal"
    ></ifa-structured-bond-brand-info>
    <!-- Ifa-requester -->
    <ifa-requester
      id="IfaSecurityCashBalanceLookupInitializeA001"
      action-type="requestAction"
      action-id="SUB020302_0301-01#A001"
      @response-handler="responseHandlerIfaSecurityCashBalanceLookupInitializeA001($event)"
      @response-error-handler="errorHandlerIfaSecurityCashBalanceLookupInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaStructuredBondBrandInfoInitializeA001"
      action-type="requestAction"
      :request-model="requestModelIfaSecurityCashBalanceLookupA007"
      action-id="SUB020302_0104-02#A001"
      @response-handler="responseHandlerIfaStructuredBondBrandInfoInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaStructuredBondBrandInfo from '@/views/brokerageMenu/wholeCustomer/tradeStatusBalanceLookupSearch/tradeStatus/withdrawScheduleList/IfaStructuredBondBrandInfo.vue'
import IfaButenCodeArrayDomainModel from '@/resource/domain/IfaButenCodeArrayDomainModel.json'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import IfaDate6YearMonthDomainModel from '@/resource/domain/IfaDate6YearMonthDomainModel.json'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaSecurityCashBalanceLookupA002RequestModel } from './js/IfaSecurityCashBalanceLookupA002RequestModel'
import { IfaSecurityCashBalanceLookupA006RequestModel } from './js/IfaSecurityCashBalanceLookupA006RequestModel'
import { IfaSecurityCashBalanceLookupA007RequestModel } from './js/IfaSecurityCashBalanceLookupA007RequestModel'
import { IfaSecurityCashBalanceLookupFormModel } from './js/IfaSecurityCashBalanceLookupFormModel'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { getCodeValue } from '@/components/Input/js/IfaCodeListFunction'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    ProductCodeSelect,
    IfaCommonSearch,
    screenTitle,
    IfaStructuredBondBrandInfo
  },
  emits: [
    'initializeError'
  ],
  data() {
    return {
      IfaButenCodeArrayDomainModel: IfaButenCodeArrayDomainModel,
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      IfaDate6YearMonthDomainModel: IfaDate6YearMonthDomainModel,
      form: new IfaSecurityCashBalanceLookupFormModel(),
      formRef: {},
      rules: {
        period: { required: true, validator: this.periodValidator, trigger: 'blur' },
        butenCodeArray: { required: false, validator: this.butenCodeArrayValidator, trigger: 'blur' },
        securityTypeSecuritiesMoney: { required: false, validator: this.securityTypeSecuritiesMoneyValidator, trigger: 'blur' },
        securityTypeCreditFuturesOp: { required: false, validator: this.securityTypeCreditFuturesOpValidator, trigger: 'blur' }
      },
      pickerOptions: {
        shortcuts: [{
          text: '当月',
          value: () => {
            const date = new Date(this.$store.getters.requestedTime)
            return [`${date.getFullYear()}/${date.getMonth() + 1}`]
          }
        },
        {
          text: '前月',
          value: () => {
            const date = new Date(this.$store.getters.requestedTime)
            let prevMonth = date.getMonth()
            let prevYear = date.getFullYear()

            if (prevMonth === 0) {
              prevMonth = 12
              prevYear--
            }

            return [`${prevYear}/${prevMonth}`]
          }
        }]
      },
      isInfoMessage: false,
      messageContent: '',
      pqGridOption: getConvertedOption(obj),
      csvBtnDisabled: true,
      isVisible: false,
      requestModelA007: {}
    }
  },
  computed: {
    requestModelIfaSecurityCashBalanceLookupA002() {
      return new IfaSecurityCashBalanceLookupA002RequestModel(
        this.form
      )
    },
    requestModelIfaSecurityCashBalanceLookupA006() {
      return new IfaSecurityCashBalanceLookupA006RequestModel(
        this.form
      )
    },
    requestModelIfaSecurityCashBalanceLookupA007() {
      return new IfaSecurityCashBalanceLookupA007RequestModel(
        this.form
      )
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.csvBtnDisabled = true
      this.isInfoMessage = true
      const monthTemp = new Date().getMonth() + 1 < 10 ? `0${new Date().getMonth() + 1}` : new Date().getMonth() + 1
      this.form.period = `${new Date().getFullYear()}/${monthTemp}`
      this.$refs['productCodeSecurityTypeSecuritiesMoney'].handleCheckAll(true) // 【初期値】全選択
      this.$refs['productCodeSecurityTypeCreditFuturesOp'].handleCheckAll(false) // 【初期値】未選択
      this.$refs['form'].clearValidate()
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
        document.getElementById('IfaSecurityCashBalanceLookupInitializeA001').click()
      })
      if (this.messageContent.length > 0) {
        setTimeout(() => {
          this.isInfoMessage = false
        }
        , 3000
        )
      }
    },
    responseHandlerIfaSecurityCashBalanceLookupInitializeA001(response) {
      this.messageContent = response.dataList[0].comment
    },
    errorHandlerIfaSecurityCashBalanceLookupInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    preRequestHandlerA002() {
      // 期間指定: YYYYMM形式に変換
      this.requestModelIfaSecurityCashBalanceLookupA002.periodYm = this.form.period.replaceAll('/', '')
      if (this.requestModelIfaSecurityCashBalanceLookupA002.brandCode !== '') {
        // 桁数に満たない場合、不足桁分だけ先頭に0を補完
        this.requestModelIfaSecurityCashBalanceLookupA002.brandCode = this.$_zeroPadding(this.form.brandCode, 12)
      }
    },
    preRequestHandlerA006() {
      // 期間指定: YYYYMM形式に変換
      this.requestModelIfaSecurityCashBalanceLookupA006.periodYm = this.form.period.replaceAll('/', '')
      if (this.requestModelIfaSecurityCashBalanceLookupA006.brandCode !== '') {
        // 桁数に満たない場合、不足桁分だけ先頭に0を補完
        this.requestModelIfaSecurityCashBalanceLookupA006.brandCode = this.$_zeroPadding(this.form.brandCode, 12)
      }
    },
    responseHandlerA002(response) {
      // 一覧へのデータの反映
      if (response.dataList.length > 0) {
        this.csvBtnDisabled = false
        Object.assign(this.form.securityCashBalanceLookupList, response.dataList)
        this.pqGridOption.dataModel.data = response.dataList
      } else {
        this.csvBtnDisabled = true
        this.form.securityCashBalanceLookupList = []
        this.pqGridOption.dataModel.data = []
      }
      this.changeGridColumnVisible()
    },
    handleErrorResponseA002() {
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
      })
    },
    // 検索項目を初期状態にする。
    handleFormClearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['productCodeSecurityTypeSecuritiesMoney'].handleCheckAll(true) // 【初期値】全選択
      this.$refs['productCodeSecurityTypeCreditFuturesOp'].handleCheckAll(false) // 【初期値】未選択
      this.form.butenCodeArray = '' // 【初期値】""
      const monthTemp = new Date().getMonth() + 1 < 10 ? `0${new Date().getMonth() + 1}` : new Date().getMonth() + 1
      this.form.period = `${new Date().getFullYear()}/${monthTemp}` // 【初期値】当月
      this.form.brandCode = '' // 【初期値】""
      this.$refs['form'].clearValidate()
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
    periodValidator(rule, value, callback) {
      if (this.form.period.length === 0 || value.length === 0) {
        callback('期間指定を入力してください。')
      } else {
        // OK
        callback()
      }
    },
    butenCodeArrayValidator(rule, value, callback) {
      const butenCodeArray = value.split(',')
      const duplicateValue = this.getDuplicateValue(butenCodeArray, butenCodeArray.length)
      // 閲覧可能部店が重複していないかチェック
      if (duplicateValue) {
        callback(new Error(getMessage('errors.codeDuplicate', ['閲覧可能部店', '閲覧可能部店', duplicateValue])))
        return
      }
      // OK
      callback()
    },
    securityTypeSecuritiesMoneyValidator(rule, value, callback) {
      const notSecurityTypeSecuritiesMoneySelected = value.every(item => item.isSelected === false)
      const notSecurityTypeCreditFuturesOpSelected = this.form.securityTypeCreditFuturesOp.every(item => item.isSelected === false)
      if (notSecurityTypeSecuritiesMoneySelected && notSecurityTypeCreditFuturesOpSelected) {
        callback('証券 金銭をを選択してください。')
      } else {
        // OK
        callback()
      }
    },
    securityTypeCreditFuturesOpValidator(rule, value, callback) {
      const notSecurityTypeSecuritiesMoneySelected = this.form.securityTypeSecuritiesMoney.every(item => item.isSelected === false)
      const notSecurityTypeCreditFuturesOpSelected = value.every(item => item.isSelected === false)
      if (notSecurityTypeSecuritiesMoneySelected && notSecurityTypeCreditFuturesOpSelected) {
        callback('信用・先OPをを選択してください。')
      } else {
        // OK
        callback()
      }
    },
    handleClick(ui) {
      if (ui.dataIndx === 'structuredBondClassification' && getCodeValue('STRUCTURED_BOND_TYPE', 1, ui.rowData.structuredBondClassification) === '仕組債') {
        // Todo: SUB020302_0104-02_仕組債銘柄情報の実装待ち
        this.form.brandCodeCurrency = ui.rowData.brandCodeCurrency
        this.$nextTick(() => {
          document.getElementById('IfaStructuredBondBrandInfoInitializeA001').click()
        })
      }
    },
    // Todo: SUB020302_0104-02_仕組債銘柄情報の実装待ち
    responseHandlerIfaStructuredBondBrandInfoInitializeA001(response) {
      this.$refs.ifaStructuredBondBrandInfo.onShow(response.dataList[0] || [])
      this.isVisible = true
    },
    handleCloseModal() {
      this.isVisible = false
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
    changeGridColumnVisible() {
      this.$nextTick(() => {
        // 「証券 金銭」の証券種別に全商品、もしくは「外国債券（外貨建）」を選択した場合のみ表示。それ以外の場合項目非表示。
        const isSecurityTypeSecuritiesMoneyAllSelected = this.form.securityTypeSecuritiesMoney.every(item => item.isSelected === true)
        const isSecurityTypeSecuritiesMoneyCode14Selected = this.form.securityTypeSecuritiesMoney.some(item => item.id === '14' && item.isSelected === true)
        if (isSecurityTypeSecuritiesMoneyAllSelected || isSecurityTypeSecuritiesMoneyCode14Selected) {
          this.setHidden('foreignStandardDate', false) // 外債評価基準日
          this.setHidden('structuredBondClassification', false) // 債券種別
        } else {
          this.setHidden('foreignStandardDate', true) // 外債評価基準日
          this.setHidden('structuredBondClassification', true) // 債券種別
        }
        // 「信用・先OP」の証券種別が未選択の場合項目非表示。
        const isSecurityTypeCreditFuturesOpNotSelected = this.form.securityTypeCreditFuturesOp.every(item => item.isSelected === false)
        if (isSecurityTypeCreditFuturesOpNotSelected) {
          this.setHidden('lastTradeDate', true) // 返済期限
        } else {
          this.setHidden('lastTradeDate', false) // 返済期限
        }
        this.$refs['gridTable'].refreshView(true)
      })
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    getDuplicateValue(array, arrayLength) {
      let result
      for (let i = 0; i < arrayLength - 1; ++i) {
        for (let j = i + 1; j < arrayLength; ++j) {
          if (array[i] === array[j]) {
            result = array[i]
            break
          }
        }
      }
      return result
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '証券・金銭・残高照会',
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
// 列の定義(まとめて定義すべき)
obj.colModel = [
  {
    title: '年月日',
    dataType: 'string',
    dataIndx: 'dateYmd',
    width: '100',
    editable: false,
    halign: 'center',
    render: function(ui) {
      return ui.rowData.dateYmd ? getFormattedDateValue(ui.rowData.dateYmd) : '-'
    }
  },
  {
    title: '仲介業者コード',
    dataType: 'string',
    dataIndx: 'brokerCode',
    width: '120',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '仲介業者名',
    dataType: 'string',
    dataIndx: 'brokerName',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '営業員コード',
    dataType: 'string',
    dataIndx: 'empCode',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '営業員名',
    dataType: 'string',
    dataIndx: 'brokerChargeName',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '部店',
    dataType: 'string',
    dataIndx: 'buten',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '口座番号',
    dataType: 'string',
    dataIndx: 'accountNumber',
    width: '120',
    halign: 'center',
    render: function(ui) {
      return ui.rowData.accountNumber ? ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 6) : '-'
    }
  },
  {
    title: '扱者コード',
    dataType: 'string',
    dataIndx: 'dealerNumber',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '取引コース',
    dataType: 'string',
    dataIndx: 'courceName',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '顧客名(漢字)',
    dataType: 'string',
    dataIndx: 'customerNameKanji',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '顧客名(カナ)',
    dataType: 'string',
    dataIndx: 'customerNameKana',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '証券種別名',
    dataType: 'string',
    dataIndx: 'securiytClass',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '取引種別名',
    dataType: 'string',
    dataIndx: 'tradeTypeName',
    width: '150',
    halign: 'center',
    hidden: false
  },
  {
    title: '返済期限',
    dataType: 'string',
    dataIndx: 'lastTradeDate',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false,
    render: function(ui) {
      return ui.rowData.lastTradeDate ? getFormattedDateValue(ui.rowData.lastTradeDate) : '-'
    }
  },
  {
    title: '銘柄コード/通貨',
    dataType: 'string',
    dataIndx: 'brandCodeCurrency',
    width: '120',
    halign: 'center',
    hidden: false
  },
  {
    title: '銘柄名',
    dataType: 'string',
    dataIndx: 'brandName',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '預り区分',
    dataType: 'string',
    dataIndx: 'depositType',
    width: '100',
    halign: 'center',
    hidden: false
  },
  {
    title: '約定基準残高',
    dataType: 'string',
    dataIndx: 'contractStandardDeposit',
    width: '165',
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.contractStandardDeposit ? ifaFormatUtils.withCommaInteger(ui.rowData.contractStandardDeposit) : '-'
    }
  },
  {
    title: '現在値',
    dataType: 'string',
    dataIndx: 'currentPrice',
    width: '195',
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.currentPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.currentPrice) : '-'
    }
  },
  {
    title: '評価額(円貨)',
    dataType: 'string',
    dataIndx: 'valuation',
    width: '170',
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.valuation ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.valuation) : '-'
    }
  },
  {
    title: '通貨',
    dataType: 'string',
    dataIndx: 'currency',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '評価額(外貨)',
    dataType: 'string',
    dataIndx: 'foreignValuation',
    width: '170',
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.foreignValuation ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignValuation) : '-'
    }
  },
  {
    title: '為替レート',
    dataType: 'string',
    dataIndx: 'fxRate',
    width: '195',
    halign: 'center',
    align: 'right',
    hidden: false,
    render: function(ui) {
      return ui.rowData.fxRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate) : '-'
    }
  },
  {
    title: '支店コード',
    dataType: 'string',
    dataIndx: 'branchCode',
    width: '100',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '支店名',
    dataType: 'string',
    dataIndx: 'branchName',
    width: '200',
    halign: 'center',
    hidden: false
  },
  {
    title: '閲覧可能部店',
    dataType: 'string',
    dataIndx: 'butenCodeViewAble',
    width: '120',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '外債評価基準日',
    dataType: 'string',
    dataIndx: 'foreignStandardDate',
    width: '120',
    halign: 'center',
    hidden: false,
    render: function(ui) {
      return ui.rowData.foreignStandardDate ? getFormattedDateValue(ui.rowData.foreignStandardDate) : '-'
    }
  },
  {
    title: '債券種別',
    dataType: 'string',
    dataIndx: 'structuredBondClassification',
    width: '120',
    halign: 'center',
    hidden: false,
    render: function(ui) {
      const value = ui.rowData.structuredBondClassification
      return value ? `<a><span style="color: #092987;">` + getCodeValue('STRUCTURED_BOND_TYPE', 1, value) + `</span></a> 
        <style>
        a {
          color: #092987;
          text-decoration: underline;
        }
        a:focus, a:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
        }
        </style>` : '-'
    }
  }
]

</script>
<style lang="scss"  scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
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
  width: 120px;
}

:deep(.checkmark) .el-checkbox＿label {
  font-weight:bold;
  text-align : right;
}

.form_button {
  margin: 0 2rem 0.8rem 46px;
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
.form_label_securitiesMoney :deep(.el-form-item__label) {
  width: 95px;
  line-height: 2
}
.form_label_creditFuturesOp :deep(.el-form-item__label) {
  width: 115px;
  line-height: 2
}
.form_label_brandCode :deep(.el-form-item__label) {
  width: 185px;
  line-height: 2
}

:deep(.label_butenCodeArr) .el-form-item {
  margin: 0
}

.right_icon {
  text-align:right;
  margin-left:auto;
  display: inline-block;
  position: absolute;
  right: 20px;
  top: 10px;
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
:deep(.el-form-item__error) {
  width: max-content;
}
.form_row {
  margin-bottom: 27px;
}
:deep(.el-form-item) {
  margin-right: 5px;
}
.filter-container {
  margin-bottom:1rem;
  margin-top:1rem;
}
:deep(.el-date-editor.el-input) {
  height: 32px;
}
.bottom_margin :deep(.el-form-item) {
  margin-bottom: 10px;
}
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
</style>
