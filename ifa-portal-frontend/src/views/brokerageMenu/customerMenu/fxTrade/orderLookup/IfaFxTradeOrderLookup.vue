<template>
  <!-- 為替取引注文照会 -->
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-row
        class="_bold_black_l"
        style="margin: 1rem 0 1rem 45px;"
      >
        <span>過去5年間のお取引をご覧いただけます｡</span>
      </el-row>

      <!-- エラー/警告表示 -->
      <el-row>
        <div>
          <ul
            type="disc"
            class="error-message"
          >
            <li
              v-for="message in messages.errors"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
          <ul
            type="disc"
            class="warning-message"
          >
            <li
              v-for="message in messages.warnings"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
          <ul
            type="disc"
            class="info-message"
          >
            <li
              v-for="message in messages.infos"
              :key="message"
            >
              {{ message }}
            </li>
          </ul>
        </div>
      </el-row>

      <!-- 検索条件フォーム -->
      <el-row style="width: 1020px;">
        <el-card
          class="box-card"
          style="background-color: #eee;"
        >
          <div class="form-container">
            <el-form
              ref="form"
              :inline="true"
              :model="form"
              label-width="100px"
              :rules="rules"
            >

              <el-row class="form_label"
                      style="padding: 0.5rem 0; font-weight: bold;"
              >
                <!-- フォーム: 絞込期間 -->
                <ifa-date-range-picker
                  ref="datePicker"
                  v-model="form.refinementPeriod"
                  size="small"
                  unlink-panels
                  label="絞込期間"
                  :required="true"
                  prop="refinementPeriod"
                  :picker-options="pickerOptions"
                ></ifa-date-range-picker>
              </el-row>
              <el-row style="flex-wrap: nowrap;">
                <!-- フォーム: 注文状況 -->
                <ifa-input-select
                  id="orderStatus"
                  v-model="form.orderStatus"
                  :clearable="false"
                  label="注文状況"
                  prop="orderStatus"
                  size="small"
                  :required="true"
                  code-list-id="FX_TRADE_SEARCH_ORDER_STATUS"
                  :disp-pattern="1"
                  :select-pattern="1"
                ></ifa-input-select>
                <ifa-input-select
                  id="currencyCode"
                  v-model="form.currencyCode"
                  :clearable="false"
                  label="通貨"
                  prop="currencyCode"
                  size="small"
                  :required="true"
                  code-list-id="original"
                  :original-list="codeOptions"
                ></ifa-input-select>
                <!-- フォーム: 取引種別 -->
                <ifa-input-select
                  id="tradeKbn"
                  v-model="form.tradeKbn"
                  :clearable="false"
                  prop="tradeKbn"
                  label="取引種別"
                  size="small"
                  :required="true"
                  code-list-id="FX_TRADE_SEARCH_TRADE_CLASS"
                  :disp-pattern="1"
                  :select-pattern="1"
                ></ifa-input-select>
              </el-row>
              <el-row class="form_button">
                <ifa-button
                  id="btnDisplay"
                  name="btnDisplay"
                  text="表示"
                  color="primary"
                  :form="formRef"
                  action-id="SUB0202_0501-01#A003"
                  action-type="requestAction"
                  :pre-request-handler="preRequestHandlerA003"
                  :request-model="IfaFxTradeOrderLookupA003RequestModel"
                  width="90"
                  :aria-disabled="false"
                  @response-handler="a003Action($event)"
                  @response-error-handler="a003ActionErrorHandle($event)"
                ></ifa-button>
                <ifa-button
                  text="クリア"
                  width="90"
                  color="secondary"
                  action-type="originalAction"
                  @app-action-handler="handleClear"
                ></ifa-button>
              </el-row>
            </el-form>
          </div>
        </el-card>
      </el-row>

      <!-- 検索結果 -->
      <ifa-foreign-exchange-transaction-result
        ref="ifaForeignExchangeTransactionResult"
        :foreign-currency-balance-list="form.fxOrderInfoList"
        :trade-suspend-flag="form.tradeSuspendFlag"
        :fx-trade-mediate-propriety-list="form.fxTradeMediateProprietyList"
        :foreign-currency-list="form.currencyList"
        :show-order-cancel-button="true"
        :show-csv-output-button="isNotIfaBroker"
        @order-cancel="handleOrderCancel"
      ></ifa-foreign-exchange-transaction-result>

      <!-- ダイアログ -->
      <ifa-fx-trade-order-cancel-confirm
        ref="cancelConfirm"
        :is-visible="cancelDialogVisible"
        :order="order"
        :customer-info="customerInfo"
        @close-modal="handleCloseModal"
        @order-finish="handleOrderFinish"
      ></ifa-fx-trade-order-cancel-confirm>

      <ifa-fx-trade-order-cancel-complete
        :is-visible="finishDialogVisible"
        :order="order"
        :customer-info="customerInfo"
        @close-modal="handleCloseCompleteModal"
      ></ifa-fx-trade-order-cancel-complete>

    </div>
    <ifa-requester
      id="ifaFxTradeOrderLookupInitializeA001"
      action-id="SUB0202_0501-01#A001"
      action-type="requestAction"
      @response-handler="a001Action($event)"
      @response-error-handler="a001ActionErrorHandle($event)"
    ></ifa-requester>
    <ifa-requester
      id="ifaFxTradeOrderCancelConfirmInitializeA001"
      action-id="SUB0202_0501-02_1#A001"
      action-type="requestAction"
      :request-model="IfaFxTradeOrderLookupA004RequestModel"
      @response-handler="cancelConfirmInitializeA001($event)"
      @response-error-handler="cancelConfirmA001ErrorHandle($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { getMessage } from '@/utils/errorHandler'
import IfaForeignExchangeTransactionResult from './components/IfaForeignExchangeTransactionResult.vue'
import IfaFxTradeOrderCancelConfirm from './IfaFxTradeOrderCancelConfirm'
import IfaFxTradeOrderCancelComplete from './IfaFxTradeOrderCancelComplete'
import { IfaFxTradeOrderLookupA003RequestModel } from './js/IfaFxTradeOrderLookupA003RequestModel'
import { IfaFxTradeOrderLookupFormModel } from './js/IfaFxTradeOrderLookupFormModel'
import { IfaFxTradeOrderLookupA004RequestModel } from './js/IfaFxTradeOrderLookupA004RequestModel'
import { getFormattedDateValue, monthsBefore } from '@/components/Date/js/IfaDatePickerFunction.js'
export default {
  components: {
    IfaForeignExchangeTransactionResult,
    IfaFxTradeOrderCancelConfirm,
    IfaFxTradeOrderCancelComplete,
    screenTitle
  },
  emits: ['initializeError', 'update-customer-portal'],
  data: function() {
    return {
      messages: {
        errors: [],
        warnings: [],
        infos: []
      },
      form: new IfaFxTradeOrderLookupFormModel(),
      cancelDialogVisible: false,
      finishDialogVisible: false,
      order: {},
      codeOptions: [],
      pickerOptions: {
        shortcuts: [
          {
            text: '本日',
            value: () => {
              return [new Date(this.$store.getters.requestedTime), new Date(this.$store.getters.requestedTime)]
            }
          },
          {
            text: '1週間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setDate(start.getDate() - 7)
              return [start, today]
            }
          },
          {
            text: '1ヶ月',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = monthsBefore(new Date(this.$store.getters.requestedTime), 1)
              return [start, today]
            }
          },
          {
            text: '1年間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setFullYear(start.getFullYear() - 1)
              return [start, today]
            }
          },
          {
            text: '2年間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setFullYear(start.getFullYear() - 2)
              return [start, today]
            }
          },
          {
            text: '3年間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setFullYear(start.getFullYear() - 3)
              return [start, today]
            }
          },
          {
            text: '4年間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setFullYear(start.getFullYear() - 4)
              return [start, today]
            }
          },
          {
            text: '5年間',
            value: () => {
              const today = new Date(this.$store.getters.requestedTime)
              const start = new Date(this.$store.getters.requestedTime)
              start.setFullYear(start.getFullYear() - 5)
              return [start, today]
            }
          }
        ]
      },
      formRef: {},
      rules: {
        currencyCode: { required: true, trigger: 'change', validator: this.currencyCodeRuleValidator }
      },
      isNotIfaBroker: false
    }
  },
  computed: {
    IfaFxTradeOrderLookupA003RequestModel() {
      return new IfaFxTradeOrderLookupA003RequestModel(this.form)
    },
    IfaFxTradeOrderLookupA004RequestModel() {
      return this.order
    },
    previousBusinessDay() {
      return this.$store.getters.previousBusinessDay
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow(resume) {
      this.form.orderStatus = '0'
      this.form.currencyCode = '0'
      this.form.tradeKbn = '0'
      this.order = {}
      this.cancelDialogVisible = false
      this.finishDialogVisible = false
      this.form.refinementPeriod = [getFormattedDateValue(this.previousBusinessDay), this.formatDate(new Date())]
      this.$refs['ifaForeignExchangeTransactionResult'].onShow()

      this.$nextTick(() => {
        document.getElementById('ifaFxTradeOrderLookupInitializeA001').click()
      })
    },
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2)
    },
    preRequestHandlerA001() {
      this.form.fxOrderInfoList = []
      this.form.currencyList = []
      this.form.fxTradeMediateProprietyList = []
      this.form.tradeSuspendFlag = []
      this.$refs['ifaForeignExchangeTransactionResult'].update()
    },
    a001Action(response) {
      this.codeOptions = []
      this.codeOptions = response.dataList[0].currencyList
      // すべての選択を追加
      this.codeOptions.splice(0, 0, { currencyCode: '0', currency: 'すべて', decimalLength: '' })
      const renamedList = this.codeOptions.map(item => {
        const { currencyCode, currency, ...rest } = item
        return { key: currencyCode, value: currency, ...rest }
      })
      this.codeOptions = renamedList
      console.log(this.codeOptions)
      // A001返却値の設定
      this.form.fxOrderInfoList = response.dataList[0].fxOrderInfoList
      this.form.currencyList = response.dataList[0].currencyList
      this.form.fxTradeMediateProprietyList = response.dataList[0].fxTradeMediateProprietyList
      this.form.tradeSuspendFlag = response.dataList[0].tradeSuspendFlag
      this.$refs['ifaForeignExchangeTransactionResult'].update()
    },
    a001ActionErrorHandle(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: getMessage(response.returnCode)
      }
      this.$emit('initializeError', errorInfo)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    resetAll() {
      this.results = []
      this.form.refinementPeriod = []
      this.form.orderStatus = '0'
      this.form.currencyCode = '0'
      this.form.tradeKbn = '0'
      this.$refs['ifaForeignExchangeTransactionResult'].update()
    },
    handleClear() {
      this.form.orderStatus = '0'
      this.form.currencyCode = '0'
      this.form.tradeKbn = '0'
      this.form.refinementPeriod = [getFormattedDateValue(this.previousBusinessDay), this.formatDate(new Date())]
      this.$refs['form'].clearValidate()
    },
    preRequestHandlerA003() {
      this.form.fxOrderInfoList = []
      this.form.fxTradeMediateProprietyList = []
      this.form.tradeSuspendFlag = []
      this.$refs['ifaForeignExchangeTransactionResult'].update()
    },
    // 検索条件に従い一覧を作成する
    a003Action(response) {
      this.form.fxOrderInfoList = response.dataList[0].fxOrderInfoList
      this.form.fxTradeMediateProprietyList = response.dataList[0].fxTradeMediateProprietyList
      this.form.tradeSuspendFlag = response.dataList[0].tradeSuspendFlag
      this.$refs['ifaForeignExchangeTransactionResult'].update()
    },
    a003ActionErrorHandle(error) {
      this.$_logWarn('☆errorlog☆')
      this.$_logWarn('a003ActionErrorHandle-Error', error)
    },
    // 注文状況が注文中の注文を取消する
    handleOrderCancel(order) {
      this.order = new IfaFxTradeOrderLookupA004RequestModel(order)
      this.$nextTick(() => {
        document.getElementById('ifaFxTradeOrderCancelConfirmInitializeA001').click()
      })
      this.$_logDebug(this.order)
    },
    cancelConfirmInitializeA001(response) {
      this.$_logDebug(111)
      // this.formModel = Object.assign(this.formModel, response.dataList[0])
      // this.formModel.orderEventId = response.dataList[0].selectedRowOrderEventId
      this.$_logDebug(response.dataList[0])
      this.$refs.cancelConfirm.responseHandlerInitializeA001(response)

      this.cancelDialogVisible = true
    },
    cancelConfirmA001ErrorHandle(error) {
      this.$_logWarn('cancelConfirmA001-Error', error)
      this.cancelDialogVisible = false
    },
    handleOrderFinish(response) {
      this.cancelDialogVisible = false
      this.finishDialogVisible = true
      this.order = response.dataList[0]
      this.$_logDebug(this.order)
    },
    // ダイアログをクローズする
    handleCloseModal() {
      this.cancelDialogVisible = false
      this.finishDialogVisible = false
      this.order = {}
    },
    handleCloseCompleteModal() {
      this.cancelDialogVisible = false
      this.finishDialogVisible = false
      this.order = {}
      this.$nextTick(() => {
        document.getElementById('btnDisplay').click()
        this.$emit('update-customer-portal')
      })
    },
    currencyCodeRuleValidator(rule, value, callback) {
      // OK
      callback()
    }
  }
}
</script>

<style lang="scss" scoped="">
.error-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
}
.warning-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 16px;
}
.info-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: black;
  font-size: 14px;
}
.form-container {
  display: block;
}
.form-area__select_s {
  width: 8rem;
}
.form-area__select_l {
  width: 15rem;
}
._bold_black_l {
  font-size: 16px;
  font-weight: bold;
  color: #606266;
}
.inquiry-button__wrapper {
  margin: 0.5rem 2rem;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.form_button {
  padding: 10px 0 11px 8px;
}
</style>
