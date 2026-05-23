<template>
  <!-- 画面名：共同店舗　取引検索 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <el-card
      class="content-card"
      shadow="always"
    >
      <el-form
        ref="form"
        :model="form"
        :inline="true"
        :rules="rules"
      >
        <div class="filter-container">
          <el-row>
            <el-col :span="23">
              <!-- 共通検索項目 -->
              <ifa-common-search
                ref="commonSearchItem"
                :form="form"
                display-pattern="pt1"
                list-pattern="pt2"
                :course-validate="true"
                original-screen-id="SUB0208_01-01"
                @mediate-user-privacy="mediateUserPrivacy"
              ></ifa-common-search>
            </el-col>
            <!-- バルーンアイコン -->
            <el-col
              :span="1"
              class="right_icon"
              style="padding-top: 12px;"
            >
              <ifa-balloon-icon
                v-if="checkjointMarketTradeSearchComment"
                icon-type="info"
                :message="form.comment"
              ></ifa-balloon-icon>
            </el-col>
          </el-row>
          <el-row>
            <!-- 期間指定 -->
            <el-form-item class="form_label right_margin">
              <ifa-date-range-picker
                ref="datePicker"
                v-model="form.period"
                label="期間指定"
                size="small"
                prop="period"
                unlink-panels
                :required="true"
                :picker-options="pickerOptions"
              ></ifa-date-range-picker>
              <div style="margin-left: 9.4rem; white-space: pre; width: 310px;">※期間は6ヶ月以内を指定してください。（過去2年間の履歴を照会いただけます。）</div>
            </el-form-item>
            <!-- 証券種別 -->
            <el-form-item
              class="form_label"
              style="width: 414px;"
            >
              <product-code-select
                ref="productCodeSelect"
                list-kind="pt9"
                id-string="securityClass"
                prop="securityClass"
                label="証券種別"
                @change-selected="form.securityClass = $event"
              ></product-code-select>
            </el-form-item>
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
          <!-- 検索用ボタン -->
          <el-row class="form_button">
            <ifa-button
              id="btnDisplay"
              icon="Search"
              text="表示"
              width="90"
              small
              :pre-request-handler="preRequestHandlerA002"
              :request-model="IfaJointMarketTradeSearchA002RequestModel"
              :form="formRef"
              action-id="SUB0208_01-01#A002"
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
          :disabled="csvBtnDisabled"
          :form="formRef"
          :pre-request-handler="preRequestHandlerA004"
          :request-model="IfaJointMarketTradeSearchA004RequestModel"
          action-id="SUB0208_01-01#A004"
          action-type="outputCsvAction"
          csv-file-name="共同店舗取引検索"
          @response-handler="responseHandlerA004($event)"
          @response-error-handler="responseErrorHandlerA004($event)"
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
    id="IfaJointMarketTradeSearchInitializaA001"
    action-id="SUB0208_01-01#A001"
    action-type="requestAction"
    @response-handler="initializeA001($event)"
  ></ifa-requester>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import IfaBrandCode12PeriodDomainModel from '@/resource/domain/IfaBrandCode12PeriodDomainModel.json'
import { IfaJointMarketTradeSearchFormModel } from './js/IfaJointMarketTradeSearchFormModel'
import { IfaJointMarketTradeSearchA002RequestModel } from './js/IfaJointMarketTradeSearchA002RequestModel'
import { IfaJointMarketTradeSearchA004RequestModel } from './js/IfaJointMarketTradeSearchA004RequestModel'
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import ProductCodeSelect from '@/components/MultiSelect/ProductCodeSelect'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import { getMessage } from '@/utils/errorHandler'
export default {
  components: {
    GridTable,
    ProductCodeSelect,
    screenTitle,
    IfaCommonSearch
  },
  data() {
    return {
      IfaBrandCode12PeriodDomainModel: IfaBrandCode12PeriodDomainModel,
      formRef: {},
      form: new IfaJointMarketTradeSearchFormModel(),
      pqGridOption: getConvertedOption(obj),
      rules: {
        period: [{ validator: this.dateValidator, trigger: 'change' }]
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '前営業日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          },
          {
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
                businessDate.getMonth() !== startDate.getMonth()
                  ? startDate
                  : this.previousBusinessDay
              return [startDate, endDate]
            }
          },
          {
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
    IfaJointMarketTradeSearchA002RequestModel() {
      return new IfaJointMarketTradeSearchA002RequestModel(this.form)
    },
    IfaJointMarketTradeSearchA004RequestModel() {
      return new IfaJointMarketTradeSearchA004RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(
        this.$store.getters.previousBusinessDay
      )
    },
    csvBtnDisabled() {
      if (this.pqGridOption.dataModel.data.length) {
        return false
      }
      return true
    },
    checkjointMarketTradeSearchComment() {
      if (this.form.comment) {
        return (
          this.form.comment.replace(/\s/g, '').replace(/　/g, '') !== ''
        )
      } else {
        return false
      }
    }
  },
  watch: {
    'form.period': {
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
        this.form.period = [this.previousBusinessDay, this.previousBusinessDay]
      }
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
        document.getElementById('IfaJointMarketTradeSearchInitializaA001').click()
      })
    },
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    preRequestHandlerA002() {
      this.IfaJointMarketTradeSearchA002RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaJointMarketTradeSearchA002RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
      this.pqGridOption.dataModel.data = []
      this.$refs['pqGrid'].refreshView(true)
    },
    displayA002(response) {
      if (response.dataList[0]?.jointMarketTradeSearchList) {
        this.pqGridOption.dataModel.data =
          response.dataList[0].jointMarketTradeSearchList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['pqGrid'].refreshView(true)
    },
    // クリアボタンによる初期化
    clearA003() {
      this.$refs.commonSearchItem.formClear()
      this.$refs['form']?.clearValidate()
      this.form.period = [
        this.previousBusinessDay,
        this.previousBusinessDay
      ]
      this.form.brandCode12 = ''
      this.$refs['productCodeSelect'].handleCheckAll(true) // 【初期値】全選択
    },
    preRequestHandlerA004() {
      this.IfaJointMarketTradeSearchA004RequestModel.periodDateFrom = this.form.periodDateFrom.replaceAll('/', '')
      this.IfaJointMarketTradeSearchA004RequestModel.periodDateTo = this.form.periodDateTo.replaceAll('/', '')
    },
    responseHandlerA004(data) {
      this.$_logDebug(data)
    },
    responseErrorHandlerA004(error) {
      this.$_logError(error)
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
      this.$refs['pqGrid'].refreshView(true)
    },
    dateValidator(rule, value, callback) {
      // 期間指定の指定が6ヶ月以内 チェック
      if (this.form.period[0] && this.form.period[1]) {
        const start = new Date(this.form.period[0])
        const end = new Date(this.form.period[1])

        const beforeTwelveMonth = new Date(end)
        beforeTwelveMonth.setMonth(beforeTwelveMonth.getMonth() - 6)

        if (start < beforeTwelveMonth) {
          callback(getMessage('errors.accurately', ['期間指定は6ヶ月以内']))
        }
      }
      callback()
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '共同店舗　取引検索',
  flexHeight: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  wrap: false
}

obj.colModel = [
  { title: '仲介業者コード', dataType: 'string', dataIndx: 'brokerCode', width: '120', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '仲介業者名', dataType: 'string', dataIndx: 'brokerName', width: '120', halign: 'center', editable: false, align: 'left', hidden: false },
  { title: '部店', dataType: 'string', dataIndx: 'buten', width: '100', halign: 'center', editable: false, align: 'center' },
  { title: '口座番号', dataType: 'string', dataIndx: 'accountNumber', width: '180', halign: 'center', editable: false, align: 'left' },
  { title: '取引コース', dataType: 'string', dataIndx: 'courseName', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(漢字)', dataType: 'string', dataIndx: 'customerNameKanji', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '顧客名(カナ)', dataType: 'string', dataIndx: 'customerNameKana', width: '100', halign: 'center', editable: false, align: 'left' },
  { title: '支店コード', dataType: 'string', dataIndx: 'branchCode', width: '200', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '支店名', dataType: 'string', dataIndx: 'branchName', width: '200', halign: 'center', editable: false, align: 'left', hidden: false },
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
  { title: '為替レート', dataType: 'string', dataIndx: 'fxRate', width: '200', halign: 'center', editable: false, align: 'right',
    render: function(ui) {
      return ui.rowData.fxRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.fxRate) : '-'
    }
  },
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
  {
    title: '共募報酬率', dataType: 'integer', dataIndx: 'jointRate', width: '150', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.jointRate ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.jointRate) + '%' : '-'
    }
  },
  {
    title: '支払額', dataType: 'integer', dataIndx: 'jointRewardPrice', width: '150', editable: false, halign: 'center', align: 'right',
    render: function(ui) {
      return ui.rowData.jointRewardPrice ? ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.jointRewardPrice) : '-'
    }
  },
  { title: '営業員コード', dataType: 'string', dataIndx: 'empCode', width: '100', halign: 'center', editable: false, align: 'center', hidden: false },
  { title: '営業員名', dataType: 'string', dataIndx: 'brokerChargeName', width: '100', halign: 'center', editable: false, align: 'left', hiddden: false },
  { title: '債券　媒介/非媒介', dataType: 'string', dataIndx: 'bondIntermediary', width: '200', halign: 'center', editable: false, align: 'left' },
  { title: '米国株　店頭/委託', dataType: 'string', dataIndx: 'usStockStoreEntrust', width: '200', halign: 'center', editable: false, align: 'left' }
]

obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style lang="scss" scoped>

:deep(.middle_input) .el-select__tags {
  max-width: none !important;
}

.grid_button_area {
  margin-bottom: 10px;
}

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
</style>
