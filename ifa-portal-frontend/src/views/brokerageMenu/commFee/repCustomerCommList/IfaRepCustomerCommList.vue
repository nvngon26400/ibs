<template>
  <!-- 画面名：担当顧客別手数料一覧 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="ifa-search-view__main-default">
      <div id="contentAreaInput">
        <el-card class="content-card">
          <el-form
            id="inpFeeTotalForm"
            ref="form"
            class="filter-container"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <el-row>
              <el-col
                :span="23"
              >
                <ifa-common-search
                  ref="IfaRepCustomerCommListCommonSearch"
                  display-pattern="pt1"
                  list-pattern="pt2"
                  :form="form"
                  :course-validate="true"
                  original-screen-id="SUB020501-01"
                  @mediate-user-privacy="mediateUserPrivacy"
                ></ifa-common-search>
              </el-col>

              <!-- iアイコン -->
              <el-col
                :span="1"
                class="right_icon"
                style="padding-top: 12px;"
              >
                <ifa-balloon-icon
                  v-if="form.repCustomerCommComment"
                  :show-message="isInfoMessage"
                  icon-type="info"
                  :message="form.repCustomerCommComment"
                ></ifa-balloon-icon>
              </el-col>
              <!-- /iアイコン -->
            </el-row>
            <el-row>

              <!-- 集計単位 /-->
              <ifa-input-radio
                id="chargeCustomerCountingUnit"
                v-model="form.chargeCustomerCountingUnit"
                label="集計単位"
                :code-list-id="'original'"
                style="margin-right: 20px;"
                :original-list="chargeCustomerCountingUnitOptions"
                is-button
                required
              >
              </ifa-input-radio>

              <!-- 銘柄コード /-->
              <ifa-input-text
                id="brandCode"
                v-model="form.brandCode"
                label="銘柄コード"
                type="text"
                size="small"
                maxlength="12"
                style="width: 180px;"
                class="ifa-input__text-default"
                prop="brandCode"
                :domain="IfaBrandCode12PeriodDomainModel"
              >
              </ifa-input-text>
              <div>
                <!-- 証券種別 /-->
                <el-form-item
                  label="証券種別"
                  class="form_label"
                ><product-code-select
                  ref="IfaRepCustomerCommListsecurityClass"
                  :list-kind="isPt4"
                  id-string="IfaRepCustomerCommListProductCodeSelect"
                  required
                  style="max-width:none;"
                  @change-selected="form.securityClass = $event"
                ></product-code-select>
                </el-form-item>
              </div>
            </el-row>

            <el-row>
              <el-col>
                <!-- 期間指定 /-->
                <ifa-date-range-picker
                  ref="datePicker"
                  v-model="form.period"
                  default-value="pickerOptions.defaultSetting"
                  label="期間指定"
                  :picker-options="pickerOptions"
                  size="small"
                  prop="period"
                  class="form_label"
                  :required="true"
                ></ifa-date-range-picker>
                <div style="padding-top: 0.5rem;margin: 0px 0px 25px 150px;">※期間は過去6ヶ月以内を指定してください。（照会できる過去履歴は下記の通りです。）<br>
                  2年 : 国内株式現物、国内株式信用、国内株式IPO・PO、国内CB、国内投信、外国投信、先物、オプション、国内債券、外国債券(円建)、外国債券(外貨建)、外国株式、外貨建MMF、カバードワラント、<br>国内株式(単元未満)、国内債券(国債)、現株ポイント、米株信用、ST<br>
                  12ヶ月 : 為替取引</div>
              </el-col>
            </el-row>
            <!-- 検索用ボタン -->
            <el-row class="form_button">
              <ifa-button
                id="btnDisplay"
                name="btnDisplay"
                text="表示"
                width="90"
                search
                small
                :form="formRef"
                :request-model="requestModelIfaRepCustomerCommListA002"
                action-id="SUB020501-01#A002"
                action-type="requestAction"
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
                @app-action-handler="clearSearchCondition"
              ></ifa-button>
              <!-- /検索用ボタン -->
            </el-row>
          </el-form>
        </el-card>
      </div>
    </div>
    <el-row>
      <el-card class="content-card">
        <el-row class="grid_button_area">
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            text="CSV出力"
            :disabled="csvBtnDisabled"
            small
            action-id="SUB020501-01#A004"
            :form="formRef"
            :request-model="requestModelIfaRepCustomerCommListA004"
            :csv-file-name="determineFileName"
            action-type="outputCsvAction"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="requirePiiDialogOnCsvDownload"
          ></ifa-button>
        </el-row>
        <el-row>
          <grid-table
            ref="gridTable"
            :options="pqGridOption"
            :auto-refresh="false"
          ></grid-table>
        </el-row>
      </el-card>
    </el-row>
    <!-- 初期表示時のリクエスト -->
    <ifa-requester
      id="ifaRepCustomerCommListInitializeA001"
      action-id="SUB020501-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001"
    >
    </ifa-requester></div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import IfaDate8DomainModel from '@/resource/domain/IfaDate8KanjiDomainModel.json'
import { IfaRepCustomerCommListFormModel } from '@/views/brokerageMenu/commFee/repCustomerCommList/js/IfaRepCustomerCommListFormModel.js'
import { IfaRepCustomerCommListA002RequestModel } from '@/views/brokerageMenu/commFee/repCustomerCommList/js/IfaRepCustomerCommListA002RequestModel.js'
import { IfaRepCustomerCommListA004RequestModel } from '@/views/brokerageMenu/commFee/repCustomerCommList/js/IfaRepCustomerCommListA004RequestModel.js'
import { getMessage } from '@/utils/errorHandler'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { validateDateRangeFromTo } from '@/components/Date/js/IfaDatePickerFunction.js'

export default {
  components: {
    GridTable,
    IfaCommonSearch,
    ProductCodeSelect,
    screenTitle
  },
  data() {
    return {
      formRef: {},
      IfaDate8DomainModel: IfaDate8DomainModel,
      form: new IfaRepCustomerCommListFormModel(),
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      pqGridOption: getConvertedOption(obj),
      csvBtnDisabled: true,
      isInfoMessage: false,
      isInfoIcon: false,
      rules: {
        period: [
          {
            required: true,
            type: 'array',
            message: getMessage('errors.required', ['期間指定']),
            trigger: 'blur'
          },
          {
            validator: this.periodDateValidator,
            trigger: 'blur'
          }
        ]
      },
      pickerOptions: {
        shortcuts: [{
          text: '前営業日',
          value: () => {
            return [this.previousBusinessDay, this.previousBusinessDay]
          }

        },
        {
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
        },
        {
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

        }]
      },
      empCodeAutoDispFlag: '',
      fitScrollModel: {
        autoFit: true,
        horizontal: true
      },
      defaultScrollModel: {
        pace: 'fast',
        smooth: false,
        horizontal: true,
        lastColumn: 'auto',
        autoFit: false,
        theme: true
      }
    }
  },
  computed: {
    requestModelIfaRepCustomerCommListA002() {
      return new IfaRepCustomerCommListA002RequestModel(this.form)
    },
    requestModelIfaRepCustomerCommListA004() {
      return new IfaRepCustomerCommListA004RequestModel(this.form)
    },
    determineFileName() {
      if (this.requestModelIfaRepCustomerCommListA004.chargeCustomerCountingUnit === '0') {
        return '担当顧客別手数料一覧・営業員毎'
      } else {
        return '担当顧客別手数料一覧・顧客毎'
      }
    },
    isPt4() {
      if (this.form.spotStockPointReferenceAbleList.some(val => val === this.userInfo.medUsers.brokerId) || this.userInfo.medUsers.privId === '1' || this.userInfo.medUsers.privId === '2') {
        return 'pt4'
      } else {
        return 'pt5'
      }
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    chargeCustomerCountingUnitOptions() {
      const codeList = this.$_getCodeList('CHARGE_CUSTOMER_COUNTING_UNIT', 1, 1)
      return codeList || []
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    requirePiiDialogOnCsvDownload() {
      // 区分.集計単位（営業員/顧客）.営業員
      const CHARGE_CUSTOMER_COUNTING_UNIT_PER_CHARGE = '0'

      return this.form.chargeCustomerCountingUnit !== CHARGE_CUSTOMER_COUNTING_UNIT_PER_CHARGE
    }
  },
  watch: {
    'form.period': {
      handler(newValue) {
        this.form.periodYmFrom = newValue[0]
        this.form.periodYmTo = newValue[1]
      }
    }
  },
  created() {
    this.pqGridOption.scrollModel = this.defaultScrollModel
    const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === 'space')
    if (typeof colModel === 'object') {
      colModel.width = '1000'
    }
  },
  methods: {
    onShow(resume) {
      if (!resume) {
        this.$refs['IfaRepCustomerCommListsecurityClass'].handleCheckAll(true) // 【初期値】全選択
        this.form.period = [this.previousBusinessDay, this.previousBusinessDay]
        this.setHidden('butenCode', false)
        this.setHidden('accountNumber', false)
        this.setHidden('customerNameKanji', false)
        this.setHidden('customerNameKana', false)
        this.setHidden('dealerNumber', false)
        this.setHidden('courseName', false)
        this.$nextTick(() => {
          this.$refs['gridTable'].refreshView(true)
          document.getElementById('ifaRepCustomerCommListInitializeA001').click()
          this.formRef = this.$refs.form
        })
      }
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      // 担当顧客別手数料コメントに値がある場合のみ、インフォメーションアイコンを表示する。
      if (this.form.repCustomerCommComment.length > 0) {
        this.isInfoMessage = true
        this.isInfoIcon = true
      }
    },
    mediateUserPrivacy(data) {
      this.empCodeAutoDispFlag = data.empCodeAutoDispFlag
      // 共通検索条件.営業員コード自動設定判定 = '0':自動設定なし の場合のみカラムを表示する。
      if (data.empCodeAutoDispFlag === '0') {
        this.setHidden('brokerCode', false)
        this.setHidden('brokerName', false)
        this.setHidden('empCode', false)
        this.setHidden('brokerChargeName', false)
        this.setHidden('brokerageBranchCode', false)
        this.setHidden('brokerBranchName', false)
        this.setHidden('space', false)

        this.$refs['gridTable'].refreshView(true)
      } else {
        this.setHidden('brokerCode', true)
        this.setHidden('brokerName', true)
        this.setHidden('empCode', true)
        this.setHidden('brokerChargeName', true)
        this.setHidden('brokerageBranchCode', true)
        this.setHidden('brokerBranchName', true)
        this.setHidden('space', false)

        this.$refs['gridTable'].refreshView(true)
      }
    },
    // 日付を文字列に変換する。
    formatDate(date) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2)
    },
    responseHandlerA002(response) {
      // 一覧へのデータの反映
      if (response.dataList.length > 0) {
        this.csvBtnDisabled = false
        Object.assign(this.form.repCustomerCommList, response.dataList[0].repCustomerCommList)
        this.pqGridOption.dataModel.data = response.dataList[0].repCustomerCommList
        // 担当顧客別手数料リスト.集計単位 = '1':顧客毎 の場合のみカラムを表示する。
        if (response.dataList[0].repCustomerCommList.every((obj) => obj.chargeCustomerCountingUnit === '1')) {
          this.setHidden('butenCode', false)
          this.setHidden('accountNumber', false)
          this.setHidden('customerNameKanji', false)
          this.setHidden('customerNameKana', false)
          this.setHidden('dealerNumber', false)
          this.setHidden('courseName', false)
          if (this.empCodeAutoDispFlag !== '0') {
            this.setHidden('space', false)
            this.pqGridOption.scrollModel = this.fitScrollModel
          } else {
            this.setHidden('space', true)
            this.pqGridOption.scrollModel = this.defaultScrollModel
          }
        } else {
          this.setHidden('butenCode', true)
          this.setHidden('accountNumber', true)
          this.setHidden('customerNameKanji', true)
          this.setHidden('customerNameKana', true)
          this.setHidden('dealerNumber', true)
          this.setHidden('courseName', true)
          this.setHidden('space', false)
          this.pqGridOption.scrollModel = this.fitScrollModel
        }
      } else {
        this.csvBtnDisabled = true
        this.pqGridOption.dataModel.data = []
      }
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
        window.dispatchEvent(new Event('resize'))
      })
    },
    handleErrorResponseA002() {
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
      })
    },
    clearSearchCondition() {
      this.$refs['form'].clearValidate()
      this.$refs.IfaRepCustomerCommListCommonSearch.formClear()
      this.$refs['IfaRepCustomerCommListsecurityClass'].handleCheckAll(true) // 【初期値】全選択
      this.form.brandCode = ''
      this.form.chargeCustomerCountingUnit = '1'
      this.form.period = [this.previousBusinessDay, this.previousBusinessDay] // 【初期値】前営業日
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    // 期間指定のバリデーションチェック処理
    periodDateValidator(rule, value, callback) {
      // 以下の条件の時エラー
      // リクエスト.期間指定（From）とリクエスト.期間指定（To）の差が6ヶ月より大きい
      if (validateDateRangeFromTo(this.form.period, 6, true)) {
        callback(getMessage('errors.dateRange', ['期間指定', '6ヶ月']))
        return
      }
      // OK
      callback()
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '担当顧客別手数料一覧',
  flexHeight: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  {
    title: '仲介業者コード',
    dataType: 'string',
    dataIndx: 'brokerCode',
    resizable: true,
    minWidth: '110',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '仲介業者名',
    dataType: 'string',
    dataIndx: 'brokerName',
    resizable: true,
    minWidth: '300',
    halign: 'center',
    hidden: false
  },
  {
    title: '営業員コード',
    dataType: 'string',
    dataIndx: 'empCode',
    resizable: true,
    minWidth: '110',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '営業員名',
    dataType: 'string',
    dataIndx: 'brokerChargeName',
    resizable: true,
    minWidth: '300',
    halign: 'center',
    hidden: false
  },
  {
    title: '部店',
    dataType: 'string',
    dataIndx: 'butenCode',
    minWidth: '85',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '口座番号',
    dataType: 'string',
    dataIndx: 'accountNumber',
    width: '100',
    minWidth: '85',
    halign: 'center',
    render: function(ui) {
      return ui.rowData.accountNumber ? ifaFormatUtils.zeroPadding(ui.rowData.accountNumber, 7) : '-'
    }
  },
  {
    title: '扱者コード',
    dataType: 'string',
    dataIndx: 'dealerNumber',
    width: '100',
    minWidth: '110',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '取引コース',
    dataType: 'string',
    dataIndx: 'courseName',
    minWidth: '230',
    halign: 'center',
    hidden: false
  },
  {
    title: '顧客名(漢字)',
    dataType: 'string',
    dataIndx: 'customerNameKanji',
    minWidth: '300',
    halign: 'center',
    hidden: false
  },
  {
    title: '顧客名(カナ)',
    dataType: 'string',
    dataIndx: 'customerNameKana',
    minWidth: '300',
    halign: 'center',
    hidden: false
  },
  {
    title: '手数料/合計（税抜き）',
    dataIndx: 'commTotal',
    minWidth: '160',
    maxWidth: '200',
    dataType: 'string',
    editable: false,
    halign: 'center',
    align: 'right',
    render: function(ui) {
      return ui.rowData.commTotal ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.commTotal) : '-'
    }
  },
  {
    title: '支店コード',
    dataType: 'string',
    dataIndx: 'brokerageBranchCode',
    minWidth: '110',
    halign: 'center',
    align: 'center',
    hidden: false
  },
  {
    title: '支店名',
    dataType: 'string',
    dataIndx: 'brokerBranchName',
    minWidth: '300',
    halign: 'center',
    hidden: false
  },
  {
    title: '',
    dataType: 'string',
    dataIndx: 'space',
    width: '1000',
    halign: 'center',
    hidden: false,
    render: function(ui) {
      return ''
    }
  }
]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>
<style scoped>
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
:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}
:deep(.el-form-item) {
  margin-bottom: 0.6rem;
  margin-right: 10px;
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
:deep(.form_label) .el-form-item__label {
  width: 135px;
}

:deep(.label_period) .el-form-item__label {
  width: 135px;
}
.content-card {
  margin: 0.5rem 1rem;
}
:deep(.h_dropdown) {
  margin-left: 0px;
}
:deep(.el-radio-group) {
  vertical-align: top;
}
</style>
