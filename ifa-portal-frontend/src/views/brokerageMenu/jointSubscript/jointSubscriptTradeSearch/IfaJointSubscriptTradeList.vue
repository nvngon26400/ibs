<template>
  <!-- 画面名：共同募集取引検索 -->
  <div>
    <screen-title :text="form.pageTitle.name"></screen-title>

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
          <!-- 共通検索項目 -->
          <el-row>
            <el-col :span="24">
              <ifa-joint-subscript-search
                ref="jointSubscriptSearchItem"
                :form="form"
                display-pattern="pt2"
                list-pattern="pt2"
                :is-display-info="checkCommComment"
              ></ifa-joint-subscript-search>
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
                list-kind="pt9"
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
              :request-model="IfaJointSubscriptTradeListA002RequestModel"
              action-id="SUB0206_02-01#A002"
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
    <el-card
      class="content-card"
      shadow="always"
      style="width: auto !important;"
    >
      <el-row>
        <el-row class="gridButtonArea">
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            text="CSV出力"
            :disabled="csvBtnDisabled"
            color="primary"
            small
            width="90"
            csv-file-name="共募取引検索"
            :form="formRef"
            :pre-request-handler="preRequestHandlerA003"
            :request-model="IfaJointSubscriptTradeListA003RequestModel"
            action-id="SUB0206_02-01#A003"
            action-type="outputCsvAction"
            @response-handler="responseHandlerA003($event)"
            @response-error-handler="responseErrorHandlerA003($event)"
          ></ifa-button>
        </el-row>
      </el-row>
      <el-row>
        <grid-table
          ref="gridTable"
          :options="pqGridOption"
          :auto-refresh="false"
        ></grid-table>
      </el-row>
    </el-card>
    <ifa-requester
      id="ifaJointSubscriptTradeListInitializeA001"
      action-type="requestAction"
      action-id="SUB0206_02-01#A001"
      @response-handler="responseHandlerA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { IfaJointSubscriptTradeListFormModel } from './js/IfaJointSubscriptTradeListFormModel.js'
import { IfaJointSubscriptTradeListA002RequestModel } from './js/IfaJointSubscriptTradeListA002RequestModel'
import { IfaJointSubscriptTradeListA003RequestModel } from './js/IfaJointSubscriptTradeListA003RequestModel'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import IfaJointSubscriptSearch from '@/components/SearchCondition/IfaJointSubscriptSearch.vue'
import { getConvertedOption } from '@/utils/pqgridHelper'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    screenTitle,
    GridTable,
    ProductCodeSelect,
    IfaJointSubscriptSearch
  },
  data() {
    return {
      form: new IfaJointSubscriptTradeListFormModel(),
      formRef: {},
      isInfoMessage: false,
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      delFlag: false,
      currentText: '',
      rules: {
        periodDate: { validator: this.periodDateValidator, trigger: 'change' }
      },
      pqGridOption: getConvertedOption(obj),
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
      }
    }
  },
  computed: {
    IfaJointSubscriptTradeListA002RequestModel() {
      return new IfaJointSubscriptTradeListA002RequestModel(this.form)
    },
    IfaJointSubscriptTradeListA003RequestModel() {
      return new IfaJointSubscriptTradeListA003RequestModel(this.form)
    },
    csvBtnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
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
    onShow(resume) {
      if (!resume) {
        this.form.periodDate = [getFormattedDateValue(this.previousBusinessDay), getFormattedDateValue(this.previousBusinessDay)]
      }
      // 検索結果を初期化
      this.pqGridOption.dataModel.data = []
      // 初期表示時のみA001初期化処理を実行
      this.$nextTick(() => {
        this.$refs['gridTable'].refreshView(true)
        document.getElementById('ifaJointSubscriptTradeListInitializeA001').click()
      })
    },
    preRequestHandlerA002() {
      this.IfaJointSubscriptTradeListA002RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaJointSubscriptTradeListA002RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView(true)
    },
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    responseHandlerA002(response) {
      // 一覧へのデータの反映
      this.pqGridOption.dataModel.data = response.dataList
      this.$refs['gridTable'].refreshView(true)
    },
    handleFormClearA003() {
      this.$refs.jointSubscriptSearchItem.formClear()
      this.$refs['form'].clearValidate()
      this.form.periodDate = [this.previousBusinessDay, this.previousBusinessDay]
      this.$refs['securityClass'].handleCheckAll(true)
      this.form.brandCode12 = ''
    },
    periodDateValidator(rule, value, callback) {
      // 期間指定は6ヶ月以内 チェック
      if (this.form.periodDate[0] && this.form.periodDate[1]) {
        const start = new Date(this.form.periodDate[0])
        const end = new Date(this.form.periodDate[1])

        const beforeSixMonth = new Date(end)
        beforeSixMonth.setMonth(beforeSixMonth.getMonth() - 6)

        if (start < beforeSixMonth) {
          callback(getMessage('errors.accurately', ['期間指定は6ヶ月以内']))
        }
      }
      callback()
    },
    preRequestHandlerA003() {
      this.IfaJointSubscriptTradeListA003RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaJointSubscriptTradeListA003RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
    },
    responseHandlerA003(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA003(error) {
      this.$_logError(error)
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '共同募集　取引検索',
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
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '180', halign: 'center', editable: false, align: 'left', hidden: false },
  { title: '部店', dataType: 'string', dataIndx: 'butenCode', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '取引コース', dataType: 'string', dataIndx: 'customerAttributeName', width: '180', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'customerNameKanji', width: '120', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(カナ)', dataType: 'string', dataIndx: 'customerNameKana', width: '120', halign: 'center', editable: false, align: 'left' },
  { title: '共募支店コード', dataType: 'string', dataIndx: 'jointBranchCode', width: '120', halign: 'center', editable: false, align: 'center' },
  { title: '共募支店名', dataType: 'string', dataIndx: 'jointBranchName', width: '150', halign: 'center', editable: false, align: 'left' },
  { title: '証券種別', dataType: 'string', dataIndx: 'securityClass', width: '140', halign: 'center', editable: false, align: 'left' },
  { title: '銘柄コード', dataType: 'string', dataIndx: 'brandCode', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '銘柄名', dataType: 'string', dataIndx: 'brandName', width: '150', halign: 'center', editable: false, align: 'left' },
  { title: '商品ランク', dataType: 'string', dataIndx: 'productRank', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '取引種別', dataType: 'string', dataIndx: 'tradeTypeName', width: '120', halign: 'center', editable: false, align: 'left' },
  { title: '預り区分', dataType: 'string', dataIndx: 'depositName', width: '120', halign: 'center', editable: false, align: 'left' },
  { title: '約定日', dataType: 'string', dataIndx: 'tradeDate', width: '120', halign: 'center', editable: false, align: 'left',
    render: function(ui) {
      return ui.rowData.tradeDate ? getFormattedDateValue(ui.rowData.tradeDate) : '-'
    }
  },
  { title: '受渡日', dataType: 'string', dataIndx: 'settlementDate', width: '120', halign: 'center', editable: false, align: 'left',
    render: function(ui) {
      return ui.rowData.settlementDate ? getFormattedDateValue(ui.rowData.settlementDate) : '-'
    }
  },
  { title: '単価', dataType: 'string', dataIndx: 'price', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.price ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.price) : '-'
    }
  },
  { title: '数量', dataType: 'string', dataIndx: 'quantity', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.quantity ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.quantity) : '-'
    }
  },
  { title: '約定金額（円貨）', dataType: 'string', dataIndx: 'contractAmountJpyAmount', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.contractAmountJpyAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.contractAmountJpyAmount) : '-'
    }
  },
  { title: '手数料（円貨）', dataType: 'string', dataIndx: 'yenFee', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.yenFee ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenFee) : '-'
    }
  },
  { title: '受渡金額（円貨）', dataType: 'string', dataIndx: 'yenDeliveryAmount', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.yenDeliveryAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.yenDeliveryAmount) : '-'
    }
  },
  { title: '通貨', dataType: 'string', dataIndx: 'currency', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '為替レート', dataType: 'string', dataIndx: 'fxRate', width: '100', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.fxRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate) : '-'
    }
  },
  { title: '約定金額（外貨）', dataType: 'string', dataIndx: 'contractAmountForeign', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.contractAmountForeign ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.contractAmountForeign) : '-'
    }
  },
  { title: '受渡金額（外貨）', dataType: 'string', dataIndx: 'foreignDeliveryAmount', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.foreignDeliveryAmount ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.foreignDeliveryAmount) : '-'
    }
  },
  { title: '残手数料', dataType: 'string', dataIndx: 'feeBalance', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.feeBalance ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.feeBalance) : '-'
    }
  },
  { title: '支払額', dataType: 'string', dataIndx: 'jointRewardPrice', width: '150', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.jointRewardPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.jointRewardPrice) : '-'
    }
  },
  { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '100', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '120', halign: 'center', editable: false, align: 'left', hiddden: false },
  { title: '債券　媒介/非媒介', dataType: 'string', dataIndx: 'bondIntermediary', width: '150', halign: 'center', editable: false, align: 'left' },
  { title: '米国株　店頭/委託', dataType: 'string', dataIndx: 'usStockStoreEntrust', width: '150', halign: 'center', editable: false, align: 'left' }
]
</script>
<style lang="scss" scoped>
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

.gridButtonArea {
  margin-bottom: 10px;
}

:deep(.form_label) .el-form-item__label {
  width: 135px;
  line-height: 2
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
