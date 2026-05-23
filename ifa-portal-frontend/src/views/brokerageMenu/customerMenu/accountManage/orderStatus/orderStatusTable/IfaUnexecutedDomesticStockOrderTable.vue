<template>
  <!-- 未約定一覧 現物・信用・現引・現渡 -->
  <div
    v-if="tableData.length != 0 && tableData != null"
    class="order-status-list-table"
  >
    <table
      cellspacing="0"
      cellpadding="0"
      border="0"
      class="el-table"
    >
      <colgroup>
        <col style="min-width: 7rem;">
        <col style="min-width: 10rem; width: 100rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 7rem;">
        <col style="min-width: 11rem;">
        <col style="min-width: 8rem;">
        <col style="min-width: 8rem;">
        <col style="min-width: 7rem;">
        <col style="min-width: 9rem;">
        <col style="min-width: 8rem;">
      </colgroup>
      <thead>
        <tr>
          <th rowspan="2">EC受注番号</th>
          <th rowspan="2">銘柄コード<br>銘柄名<br>市場</th>
          <th rowspan="2">注文状況</th>
          <th rowspan="2">取引種別<br>信用取引区分<br>強制決済</th>
          <th rowspan="2">受注日時<br>発注日<br>期間</th>
          <th rowspan="1">注文種別<br>預り区分</th>
          <th rowspan="1">数量<br>未出来数量</th>
          <th rowspan="1">価格</th>
          <th rowspan="1">手数料区分<br>利用ポイント</th>
          <th rowspan="2">取消/訂正</th>
        </tr>
        <tr>
          <th colspan="4">条件</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <tr :style="{ 'background-color': item.orderStatus === '完了' ? '#C3C3C3' : '' }">
          <td
            rowspan="2"
            class="text-center"
          >
            <!--EC受注番号-->
            {{ $_out(item.ecOrderNo) }}
          </td>
          <td
            rowspan="2"
            class="text-left"
          >
            <!-- 銘柄コード -->
            {{ $_out(item.brandCode) }}<br>
            <!-- 銘柄名 -->
            {{ $_out(item.brandName) }}<br>
            <!--市場-->
            {{ $_out($_getCodeValue('SELECT_MARKET', 2, item.market)) }}
          </td>
          <td
            rowspan="2"
            class="text-center"
          >
            <!--注文状況-->
            {{ $_out(item.orderStatus) }}<br>
            <!--注文状況補足-->
            {{ !item.orderStatusSupplement || item.orderStatusSupplement === '-' ? '' : item.orderStatusSupplement }}
          </td>
          <td
            rowspan="2"
            class="text-center"
          >
            <!--取引種別-->
            <span :class="fontColor(item.buySellTypeName)">
              {{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, item.buySellTypeName)) }}
            </span><br>
            <!--信用取引区分-->
            {{ $_out(item.marginTradeTypeText) }}<br>
            <!--強制決済-->
            {{ $_out($_getCodeValue('COERCION_TYPE', 1, item.coercionType)) }}
          </td>
          <td
            rowspan="2"
            class="text-center"
          >
            <!--受注日時-->
            {{ $_out($_getFormattedDateTimeValue(item.orderDayTime, 'datetime12')) }}<br>
            <!--発注日-->
            {{ $_out($_getFormattedDateValue(item.marketOrderDate)) }}<br>
            <!--期間-->
            {{ $_out($_getFormattedDateValue(item.orderPeriod)) }}
          </td>
          <td
            :rowspan="1"
            class="text-center"
          >
            <!--注文種別-->
            {{ $_out($_getCodeValue('LIST_ORDER_CLASS', 1, item.orderStatusListOrderClass)) }}<br>
            <!--預り区分-->
            {{ $_out($_getCodeValue('DOMESTIC_DEPOSIT_TYPE', 3, item.notSpecificDepositTradeType)) }}
          </td>
          <td
            :rowspan="1"
            class="text-right"
          >
            <!--数量-->
            {{ $_out(ifaFormatUtils.withCommaInteger(item.orderQuantity)) }}<br>
            <!--未出来数量-->
            {{ $_out(ifaFormatUtils.withCommaInteger(item.unMatchedTradeQuantity)) }}
          </td>
          <td
            :rowspan="1"
            class="text-right"
          >
            <!--執行方法-->
            {{ $_out($_getCodeValue('LIMIT_MARKET_TYPE', 1, item.orderExecuteMethodList)) }}
            <!--価格-->
            <span v-if="item.orderExecuteMethodList === ' ' ||
              item.orderExecuteMethodList === 'Z' ||
              item.orderExecuteMethodList === 'I' ||
              item.orderExecuteMethodList === 'F' ||
              item.orderExecuteMethodList === 'P'"
            >
              {{ $_out($_addComma(item.price)) }}
            </span>
          </td>
          <td
            :rowspan="1"
            class="text-center"
          >
            <!--手数料区分-->
            {{ item.buySellTypeName === '7' || item.buySellTypeName === '8' ?
              '-' : $_out($_getCodeValue('COMM_TYPE', 1, item.tradingCoursText)) }}<br>
            <!--利用ポイント-->
            {{ item.point === ' ' ? '0' : $_out($_addComma(item.point)) }}
          </td>
          <td
            rowspan="2"
            class="text-center"
          >
            <!--取消-->
            <div style="margin-bottom: 0.5rem">
              <ifa-button
                v-if="tradeSuspendFlag === '0' &&
                  intermediaryValueList.filter(list => list.productCd === '01').length !== 0 &&
                  intermediaryValueList.filter(list => list.tradeCd === item.buySellTypeName).length !== 0 &&
                  intermediaryValueList.filter(list => list.mediatePropriety === '1').length !== 0 &&
                  item.domesticStockCancelButtonDisplayJudgment === '1'"
                id="btnCancel"
                text="取消"
                width="80"
                color="secondary"
                small
                :disabled="item.sorLinkKbn === '1'"
                action-type="originalAction"
                @app-action-handler="cancelDialogOpen(item)"
              ></ifa-button>
            </div>
            <!--訂正-->
            <div>
              <ifa-button
                v-if="tradeSuspendFlag === '0' &&
                  intermediaryValueList.filter(list => list.productCd === '01').length !== 0 &&
                  intermediaryValueList.filter(list => list.tradeCd === item.buySellTypeName).length !== 0 &&
                  intermediaryValueList.filter(list => list.mediatePropriety === '1').length !== 0 &&
                  item.domesticStockCorrectButtonDisplayJudgment === '1' &&
                  customerInfo.butenCode.substring(0, 1) !== 'N'"
                id="btnEdit"
                text="訂正"
                width="80"
                color="primary"
                small
                :disabled="item.sorLinkKbn === '1' || (item.sorLinkKbn === '2' && item.latestMarketId === '7')"
                action-type="originalAction"
                @app-action-handler="correctDialogOpen(item)"
              ></ifa-button>
            </div>
          </td>
        </tr>
        <tr :style="{ 'background-color': item.orderStatus === '完了' ? '#C3C3C3' : '' }">
          <td
            v-if="item.conditions"
            colspan="4"
            class="text-center"
            style="text-align: left;"
          >
            <!--条件-->
            <span v-html="item.conditions"></span>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 国内株式注文取消確認 -->
    <ifa-domestic-stock-order-cancel-confirm
      :is-visible="cancelDialogVisible1"
      :cancel-data="orderData"
      :ec-order-no="ecOrderNo"
      @close-modal="cancelDialogVisible1 = false"
      @initialize-order-status-list="domesticStockOrderCancelCompleteFinish"
    ></ifa-domestic-stock-order-cancel-confirm>

    <!--  信用新規注文取消確認 -->
    <ifa-margin-new-order-cancel-confirm
      :is-visible="marginNewOrderCancelConfirmDialogVisible"
      :cancel-data="orderData"
      :ec-order-no="ecOrderNo"
      @close-modal="marginNewOrderCancelConfirmDialogVisible = false"
      @cancel-finish="marginNewOrderCancelConfirmFinish"
    ></ifa-margin-new-order-cancel-confirm>

    <!--  信用新規注文取消完了 -->
    <ifa-margin-new-order-cancel-complete
      :is-visible="marginNewOrderCancelCompleteDialogVisible"
      :cancel-data="orderData"
      @close-modal="marginNewOrderCancelCompleteFinish"
    ></ifa-margin-new-order-cancel-complete>

    <!-- 信用返済注文取消確認 -->
    <ifa-margin-repay-order-cancel-confirm
      :is-visible="marginRepayOrderCancelConfirmDialogVisible"
      :cancel-data="orderData"
      :ec-order-no="ecOrderNo"
      @close-modal="marginRepayOrderCancelConfirmDialogVisible = false"
      @cancel-finish="marginRepayOrderCancelConfirmFinish"
    ></ifa-margin-repay-order-cancel-confirm>

    <!-- 信用返済注文取消完了 -->
    <ifa-margin-repay-order-cancel-complete
      :is-visible="marginRepayOrderCancelCompleteDialogVisible"
      :cancel-data="orderData"
      @close-modal="marginRepayOrderCancelCompleteFinish"
    ></ifa-margin-repay-order-cancel-complete>

    <!-- 現引現渡注文取消確認 -->
    <ifa-receipt-delivery-order-cancel-confirm
      v-if="cancelDialogVisible4"
      :is-visible="cancelDialogVisible4"
      :ec-order-no="ecOrderNo"
      :cancel-data="orderData"
      @close-modal="cancelDialogVisible4 = false"
      @initialize-order-status-list="receiptDeliceryOrderCancelCompleteFinish"
    ></ifa-receipt-delivery-order-cancel-confirm>

    <ifa-requester
      id="IfaReceiptDeliveryOrderCancelConfirmInitializeA001"
      action-id="SUB0202_0212-09_1#A001"
      action-type="requestAction"
      :request-model="IfaReceiptDeliveryOrderCancelConfirmA001RequestModel"
      @response-handler="a011ActionHandler"
    ></ifa-requester>

    <!-- 国内株式注文訂正入力 -->
    <ifa-domestic-stock-order-correct-input
      ref="ifaDomesticStockOrderCorrectInput"
      :is-visible="correctDialogVisible1"
      :ec-order-no="ecOrderNo"
      @close-modal="correctDialogVisible1 = false"
      @open-modal="correctDialogVisible1 = true"
      @initialize-order-status-list="domesticStockOrderCorrectCompleteFinish"
    ></ifa-domestic-stock-order-correct-input>

    <!-- 信用新規注文訂正入力 -->
    <ifa-margin-new-order-correct-input
      v-if="correctDialogVisible2"
      :is-visible="correctDialogVisible2"
      :form="beforeData"
      :before-data="beforeData"
      :ec-order-no="orderData.ecOrderNo"
      @close-modal="correctDialogVisible2 = false"
      @initialize-order-status-list="marginNewOrderCorrectCompleteFinish"
    ></ifa-margin-new-order-correct-input>

    <ifa-requester
      id="IfaMarginNewOrderCorrectInputInitializeA001"
      action-id="SUB0202_0212-02_1#A001"
      action-type="requestAction"
      :request-model="IfaOrderStatusListA007RequestModel"
      @response-handler="a007ActionHandler"
    ></ifa-requester>

    <!-- 信用返済注文訂正入力 -->
    <ifa-margin-repay-order-correct-input
      ref="ifaMarginRepayOrderCorrectInput"
      :is-visible="correctDialogVisible3"
      :ec-order-no="ecOrderNo"
      @close-modal="correctDialogVisible3 = false"
      @open-modal="correctDialogVisible3 = true"
      @initialize-order-status-list="marginRepayOrderCorrectCompleteFinish"
    ></ifa-margin-repay-order-correct-input>

    <ifa-requester
      id="IfaDomesticStockOrderCancelConfirmInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_0208-04_1#A001"
      :request-model="IfaOrderStatusListA006RequestModel"
      @response-handler="domesticStockOrderCancelConfirmInitializeA001($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaDomesticStockOrderCorrectInputA001"
      action-type="requestAction"
      action-id="SUB0202_0208-03_1#A001"
      :request-model="IfaDomesticStockOrderCorrectInputA001RequestModel"
      @response-handler="responseHandlerDomesticStockOrderCorrectInputA001($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaMarginNewOrderCancelConfirmInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_0212-03_1#A001"
      :request-model="IfaMarginNewOrderCancelConfirmA001RequestModel"
      @response-handler="marginNewOrderCancelConfirmInitializeA001($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaMarginRepayOrderCorrectInputInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_0212-06_1#A001"
      :request-model="IfaMarginRepayOrderCorrectInputA001RequestModel"
      @response-handler="responseHandlerMarginRepayOrderCorrectInputInitializeA001($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaMarginRepayOrderCancelConfirmInitializeA001"
      action-type="requestAction"
      action-id="SUB0202_0212-07_1#A001"
      :request-model="IfaMarginRepayOrderCancelConfirmA001RequestModel"
      @response-handler="marginRepayOrderCancelConfirmInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaDomesticStockOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaDomesticStockOrderCancelConfirm'
import IfaDomesticStockOrderCorrectInput from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaDomesticStockOrderCorrectInput'
import IfaMarginNewOrderCorrectInput from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderCorrectInput'
import { IfaOrderStatusListA006RequestModel } from './IfaOrderStatusListA006RequestModel'
import { IfaOrderStatusListA007RequestModel } from '../js/IfaOrderStatusListA007RequestModel'
import { IfaDomesticStockOrderCorrectInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaDomesticStockOrderCorrectInputA001RequestModel.js'
import IfaReceiptDeliveryOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaReceiptDeliveryOrderCancelConfirm'
import { IfaReceiptDeliveryOrderCancelConfirmA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaReceiptDeliveryOrderCancelConfirmA001RequestModel.js'
import IfaMarginNewOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderCancelConfirm.vue'
import { IfaMarginNewOrderCancelConfirmA001RequestModel } from '@/views//brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginNewOrderCancelConfirmA001RequestModel'
import IfaMarginNewOrderCancelComplete from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderCancelComplete.vue'
import IfaMarginRepayOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginRepayOrderCancelConfirm.vue'
import { IfaMarginRepayOrderCancelConfirmA001RequestModel } from '@/views//brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderCancelConfirmA001RequestModel'
import IfaMarginRepayOrderCancelComplete from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginRepayOrderCancelComplete.vue'
import IfaMarginRepayOrderCorrectInput from '../../../domesticStock/marginTrade/IfaMarginRepayOrderCorrectInput.vue'
import { IfaMarginRepayOrderCorrectInputA001RequestModel } from '../../../domesticStock/marginTrade/js/IfaMarginRepayOrderCorrectInputA001RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'

export default {
  components: {
    IfaDomesticStockOrderCancelConfirm,
    IfaDomesticStockOrderCorrectInput,
    IfaMarginNewOrderCorrectInput,
    IfaReceiptDeliveryOrderCancelConfirm,
    IfaMarginNewOrderCancelConfirm,
    IfaMarginNewOrderCancelComplete,
    IfaMarginRepayOrderCancelConfirm,
    IfaMarginRepayOrderCancelComplete,
    IfaMarginRepayOrderCorrectInput
  },
  props: {
    tableData: {
      type: Array,
      required: true
    },
    tradeSuspendFlag: {
      type: String,
      required: true
    },
    intermediaryValueList: {
      type: Array,
      required: true
    }
  },
  emits: ['initialize-order-status-list'],
  data() {
    return {
      IfaOrderStatusListA006RequestModel: {},
      IfaDomesticStockOrderCorrectInputA001RequestModel: {},
      IfaMarginNewOrderCancelConfirmA001RequestModel: {},
      IfaReceiptDeliveryOrderCancelConfirmA001RequestModel: {},
      IfaMarginRepayOrderCancelConfirmA001RequestModel: {},
      IfaMarginRepayOrderCorrectInputA001RequestModel: {},
      cancelDialogVisible1: false,
      marginNewOrderCancelConfirmDialogVisible: false,
      marginNewOrderCancelCompleteDialogVisible: false,
      marginRepayOrderCancelConfirmDialogVisible: false,
      marginRepayOrderCancelCompleteDialogVisible: false,
      cancelDialogVisible3: false,
      cancelDialogVisible4: false,
      correctDialogVisible1: false,
      correctDialogVisible2: false,
      correctDialogVisible3: false,
      orderData: {},
      beforeData: {},
      form: {},
      ifaFormatUtils: ifaFormatUtils,
      ecOrderNo: ''
    }
  },
  computed: {
    IfaOrderStatusListA007RequestModel() {
      return new IfaOrderStatusListA007RequestModel(this.orderData)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    fontColor: function() {
      return function(buySellTypeName) {
        // 買
        if (buySellTypeName === '1' ||
            buySellTypeName === '3' ||
            buySellTypeName === '5' ||
            buySellTypeName === '8' ||
            buySellTypeName === 'B' ||
            buySellTypeName === 'C' ||
            buySellTypeName === 'D') {
          return 'font-color__plus bold'
        // 売
        } else if (buySellTypeName === '2' ||
            buySellTypeName === '4' ||
            buySellTypeName === '6' ||
            buySellTypeName === '7' ||
            buySellTypeName === 'E') {
          return 'font-color__minus bold'
        } else {
          return ''
        }
      }
    }
  },
  methods: {
    a007ActionHandler(response) {
      this.beforeData = response.dataList[0]
      this.correctDialogVisible2 = true
    },
    a011ActionHandler(response) {
      this.orderData = response.dataList[0]
      this.cancelDialogVisible4 = true
    },
    correctDialogOpen(item) {
      if (item.buySellTypeName === '1' || item.buySellTypeName === '2') {
        // 　現物買 または 現物売 の場合、A005（国内株式注文訂正確認）
        this.IfaDomesticStockOrderCorrectInputA001RequestModel = new IfaDomesticStockOrderCorrectInputA001RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaDomesticStockOrderCorrectInputA001').click()
        })
      } else if (item.buySellTypeName === '3' || item.buySellTypeName === '4') {
        this.$_logDebug('★A007')
        // 　新規買 または 新規売 の場合、A007（信用新規注文訂正入力）
        this.orderData = item
        this.$nextTick(() => {
          document.getElementById('IfaMarginNewOrderCorrectInputInitializeA001').click()
        })
      } else if (item.buySellTypeName === '5' || item.buySellTypeName === '6') {
        this.$_logDebug('★A009')
        // 　返済買 または 返済売 の場合、A009（信用返済注文取消確認）
        this.IfaMarginRepayOrderCorrectInputA001RequestModel = new IfaMarginRepayOrderCorrectInputA001RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaMarginRepayOrderCorrectInputInitializeA001').click()
        })
      }
    },
    cancelDialogOpen(item) {
      if (item.buySellTypeName === '1' || item.buySellTypeName === '2') {
        // 取消確認画面の初期化処理
        this.IfaOrderStatusListA006RequestModel = new IfaOrderStatusListA006RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaDomesticStockOrderCancelConfirmInitializeA001').click()
        })
      } else if (item.buySellTypeName === '3' || item.buySellTypeName === '4') {
        this.$_logDebug('★A008')
        // 　新規買 または 新規売 の場合、A008（信用新規注文取消確認）
        this.IfaMarginNewOrderCancelConfirmA001RequestModel = new IfaMarginNewOrderCancelConfirmA001RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaMarginNewOrderCancelConfirmInitializeA001').click()
        })
      } else if (item.buySellTypeName === '5' || item.buySellTypeName === '6') {
        this.$_logDebug('★A010')
        // 　返済買 または 返済売 の場合、A010（信用返済注文取消確認）
        this.IfaMarginRepayOrderCancelConfirmA001RequestModel = new IfaMarginRepayOrderCancelConfirmA001RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaMarginRepayOrderCancelConfirmInitializeA001').click()
        })
      } else if (item.buySellTypeName === '7' || item.buySellTypeName === '8') {
        this.$_logDebug('★A011')
        // 　現引買 または 現渡売 の場合、A011（現引現渡注文取消確認）
        this.IfaReceiptDeliveryOrderCancelConfirmA001RequestModel = new IfaReceiptDeliveryOrderCancelConfirmA001RequestModel(item)
        this.ecOrderNo = item.ecOrderNo
        this.$nextTick(() => {
          document.getElementById('IfaReceiptDeliveryOrderCancelConfirmInitializeA001').click()
        })
      }
    },
    domesticStockOrderCancelConfirmInitializeA001(response) {
      this.orderData = response.dataList[0]
      this.cancelDialogVisible1 = true
    },
    // 国内株式注文取消完了
    domesticStockOrderCancelCompleteFinish() {
      this.cancelDialogVisible1 = false
      this.$emit('initialize-order-status-list')
    },
    responseHandlerDomesticStockOrderCorrectInputA001(response) {
      this.correctDialogVisible1 = true
      this.$nextTick(() => {
        this.$refs.ifaDomesticStockOrderCorrectInput.onShow(response)
      })
    },
    // 国内株式注文訂正完了
    domesticStockOrderCorrectCompleteFinish() {
      // 注文状況一覧を初期化
      this.correctDialogVisible1 = false
      this.$emit('initialize-order-status-list')
    },
    marginNewOrderCancelConfirmInitializeA001(response) {
      this.orderData = response.dataList[0]
      this.marginNewOrderCancelConfirmDialogVisible = true
    },
    marginNewOrderCancelConfirmFinish(response) {
      // 信用新規注文取消完了へ遷移
      this.orderData = response.dataList[0]
      this.marginNewOrderCancelConfirmDialogVisible = false
      this.marginNewOrderCancelCompleteDialogVisible = true
    },
    // 信用新規注文取消完了
    marginNewOrderCancelCompleteFinish() {
      this.marginNewOrderCancelCompleteDialogVisible = false
      this.$emit('initialize-order-status-list')
    },
    marginRepayOrderCancelConfirmInitializeA001(response) {
      this.orderData = response.dataList[0]
      this.marginRepayOrderCancelConfirmDialogVisible = true
    },
    marginRepayOrderCancelConfirmFinish(response) {
      // 信用返済注文取消完了へ遷移
      this.orderData = response.dataList[0]
      this.marginRepayOrderCancelConfirmDialogVisible = false
      this.marginRepayOrderCancelCompleteDialogVisible = true
    },
    marginRepayOrderCancelCompleteFinish() {
      this.marginRepayOrderCancelCompleteDialogVisible = false
      this.$emit('initialize-order-status-list')
    },
    responseHandlerMarginRepayOrderCorrectInputInitializeA001(response) {
      // 信用返済注文訂正入力へ遷移
      this.correctDialogVisible3 = true
      this.$nextTick(() => {
        this.$refs.ifaMarginRepayOrderCorrectInput.onShow(response)
      })
    },
    // 現引現渡注文取消完了
    receiptDeliceryOrderCancelCompleteFinish() {
      this.cancelDialogVisible4 = false
      this.$emit('initialize-order-status-list')
    },
    // 信用新規注文訂正完了
    marginNewOrderCorrectCompleteFinish() {
      this.correctDialogVisible2 = false
      this.$emit('initialize-order-status-list')
    },
    // 信用返済注文訂正完了
    marginRepayOrderCorrectCompleteFinish() {
      this.correctDialogVisible3 = false
      this.$emit('initialize-order-status-list')
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
table.scroll {
  overflow-y: scroll;
}
</style>
