<template>
  <!-- 外国注文状況一覧：国別 -->
  <div
    v-if="tableData.length != 0 && tableData != null"
    class="order-status-list-table"
  >
    <table
      cellspacing="0"
      cellpadding="0"
      border="0"
      class="el-table"
      width="100%"
    >
      <colgroup>
        <col style="min-width: 10rem;">
        <col style="min-width: 10rem; width: 100rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 11rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 10rem;">
        <col style="min-width: 8rem;">
      </colgroup>
      <thead>
        <!-- １列目 -->
        <tr>
          <th rowspan="2">注文番号</th>
          <th rowspan="2">ティッカー/銘柄コード<br>銘柄名<br>市場</th>
          <th rowspan="2">注文状況<br>約定状況</th>
          <th rowspan="2">取引種別<br>決済方法</th>
          <th rowspan="1">注文種別<br>預り区分</th>
          <th rowspan="1">国内注文日時<br>期間</th>
          <th rowspan="1">数量<br>(約定数量)</th>
          <th rowspan="1">注文単価<br>平均約定単価</th>
          <th rowspan="1">注文チャネル</th>
          <th rowspan="2">取消/訂正</th>
        </tr>
        <!-- ２列目 -->
        <tr>
          <th colspan="5">条件</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <!-- １列目 -->
        <tr :style="{ 'background-color': item.orderStatus === '6' ? '#C3C3C3' : '' }">
          <td
            class="text-center"
            rowspan="2"
          >
            <!--注文番号-->
            {{ $_out(item.orderNumber) }}
          </td>
          <td
            class="text-left"
            rowspan="2"
          >
            <!-- 銘柄コード -->
            {{ $_out(item.brandCode) }}<br>
            <!-- 銘柄名 -->
            {{ $_out(item.brandName) }}<br>
            <!--市場-->
            {{ $_out(item.market) }}
          </td>
          <td
            class="text-center"
            rowspan="2"
          >
            <!--注文状況-->
            {{ $_out($_getCodeValue('FOREIGN_STOCK_ENTRUST_ORDER_STATUS', 1, item.orderStatus)) }}<br>
            <!--約定状況-->
            {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_STATUS', 1, item.tradeStatus)) }}
          </td>
          <td
            class="text-center"
            rowspan="2"
          >
            <!--取引種別-->
            <span :class="fontColor(item.buySellTypeName)">
              {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, item.buySellTypeName)) }}
            </span><br>
            <!--決済方法-->
            {{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, item.kessaiHoho)) }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--注文種別-->
            {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 5, item.orderStatusListOrderClass)) }}<br>
            <!--預り区分-->
            {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.depositType)) }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--国内注文日時-->
            {{ $_out($_getFormattedDateTimeValue(item.orderDate, 'datetime12')) }}<br>
            <!--期間-->
            {{ item.orderPeriod !== '当日注文' ? $_out($_getFormattedDateValue(item.orderPeriod)) : $_out(item.orderPeriod) }}
          </td>
          <td
            class="text-right"
            rowspan="1"
          >
            <!--数量-->
            {{ $_out(ifaFormatUtils.withCommaInteger(item.domesticQuantityInput)) }}<br>
            <!--約定数量-->
            <span v-if="item.tradeQuantity === '0' || item.tradeQuantity === ''">
              ({{ $_out(ifaFormatUtils.withCommaInteger(item.tradeQuantity)) }})</span>
            <span v-else>
              (<span
                class="clickable inner-link"
                :underline="false"
                style="text-decoration:underline;text-underline-offset:0.1em;"
                disable-transitions
                @click="displayA003Action(item)"
              >{{ $_out(ifaFormatUtils.withCommaInteger(item.tradeQuantity)) }}</span>)
            </span>
          </td>
          <td
            class="text-right"
            rowspan="1"
          >
            <!--執行方法-->
            {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 4, item.orderStatusListOrderClass)) }}&nbsp;
            <!--注文単価-->
            <span v-if="item.orderStatusListOrderClass === '1' || item.orderStatusListOrderClass === '3'">
              {{ $_out(ifaFormatUtils.withCommaZeroPadding(item.price, 2)) }}
            </span>
            <!--通貨単位-->
            <span v-if="item.orderStatusListOrderClass === '1' || item.orderStatusListOrderClass === '3'">
              {{ $_out($_getCodeValue('NATIONAL_CURRENCY_UNIT', 1, countryCd)) }}
            </span><br>
            <!--平均約定単価-->
            {{ item.tradeQuantity === '0' ? '-' : $_out(ifaFormatUtils.withCommaZeroPadding(item.averageTradePrice, 4)) }}
            <!--通貨単位-->
            {{ item.tradeQuantity !== '0' ? $_getCodeValue('NATIONAL_CURRENCY_UNIT', 1, countryCd) : '' }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--注文チャネル-->
            {{ $_out($_getCodeValue('COMMISSION_APPLICATION_TYPE', 1, item.commissionApplicationType)) }}
          </td>
          <td
            class="text-left"
            rowspan="2"
          >
            <!-- 取消/訂正 -->
            <div style="width: 90px; margin-bottom: 0.5rem; text-align: center;">
              <div
                v-if="tradeSuspendFlag === '0' &&
                  intermediaryValueList.filter(list => list.productCd === '15').length !== 0 &&
                  intermediaryValueList.filter(list => list.tradeCd === item.buySellTypeName).length !== 0 &&
                  intermediaryValueList.filter(list => list.countryCd === countryCd).length !== 0 &&
                  intermediaryValueList.filter(list => list.mediatePropriety === '1').length !== 0 &&
                  item.mutualFundCancelButtonDisplayJudgment === '1'"
              >
                <!-- 国別リスト.外国株式委託注文リスト.株取引区分 が "現物"の場合  A013 -->
                <ifa-button
                  v-if="item.stockTradeType === 'STOCK' || item.stockTradeType === 'STOCK'"
                  text="取消"
                  width="80"
                  color="secondary"
                  small
                  action-id="SUB0202_0301-03_1#A001"
                  action-type="requestAction"
                  :request-model="{
                    'orderSubNumber': item.orderSubNumber,
                    'countryCd': countryCd
                  }"
                  @response-handler="responseHandlerSpotTradeCancelConfirminitializeA001"
                  @response-error-handler="responseErrorHandlerSpotTradeCancelConfirmInitializeA001($event)"
                ></ifa-button>
                <!-- 国別リスト.外国株式委託注文リスト.株取引区分 が ""信用新規"または"信用返済"の場合  A015 -->
                <ifa-button
                  v-if="item.stockTradeType === 'MARGIN_OPEN' || item.stockTradeType === 'MARGIN_CLOSE'"
                  text="取消"
                  width="80"
                  color="secondary"
                  small
                  action-id="SUB0202_0303-03_1#A001"
                  action-type="requestAction"
                  :request-model="{
                    'orderSubNumber': item.orderSubNumber,
                    'countryCd': countryCd
                  }"
                  @response-handler="responseHandlerinitializeA001"
                ></ifa-button>
              </div>
            </div>
          </td>
        </tr>
        <!-- ２列目 -->
        <tr v-if="item.conditions"
            :style="{ 'background-color': item.orderStatus === '6' ? '#C3C3C3' : '' }"
        >
          <td
            class="text-left"
            colspan="5"
          ><!--条件-->{{ $_out(item.conditions) }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 外国現物取引注文取消確認 -->
    <ifa-foreign-spot-trade-order-cancel-confirm
      :is-visible="spotTradeCancelConfirmDialogVisible"
      :form-data="spotTradeCancelConfirmModel"
      :customer-info="customerInfo"
      @close-modal="spotTradeCancelConfirmDialogVisible = false"
      @cancel-finish="handleCancelFinishSpotTrade"
    ></ifa-foreign-spot-trade-order-cancel-confirm>

    <!-- 外国現物取引注文取消完了 -->
    <ifa-foreign-spot-trade-order-cancel-complete
      :is-visible="spotTradeCancelCompleteDialogVisible"
      :form-data="spotTradeCancelCompleteModel"
      :customer-info="customerInfo"
      @move-to-order-list="handleMoveToOrderListSpotTrade"
    ></ifa-foreign-spot-trade-order-cancel-complete>

    <!-- 米株信用取引注文取消 -->
    <ifa-foreign-margin-trade-order-cancel-confirm
      :is-visible="cancelConfirmDialogVisible"
      :form-data="cancelConfirmModel"
      :customer-info="customerInfo"
      @close-modal="cancelConfirmDialogVisible = false"
      @cancel-finish="handleCancelFinish"
    ></ifa-foreign-margin-trade-order-cancel-confirm>

    <!-- 米株信用取引注文完了 -->
    <ifa-foreign-margin-trade-order-cancel-complete
      :is-visible="cancelCompleteDialogVisible"
      :form-model="cancelCompleteModel"
      :customer-info="customerInfo"
      @close-modal="cancelCompleteDialogVisible = false"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-foreign-margin-trade-order-cancel-complete>
  </div>
</template>

<script>
import IfaForeignSpotTradeOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/IfaForeignSpotTradeOrderCancelConfirm.vue'
import IfaForeignSpotTradeOrderCancelComplete from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/IfaForeignSpotTradeOrderCancelComplete.vue'

import { IfaForeignSpotTradeOrderCancelConfirmFormModel } from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/js/IfaForeignSpotTradeOrderCancelConfirmFormModel'
import { IfaForeignSpotTradeOrderCancelCompleteFormModel } from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/js/IfaForeignSpotTradeOrderCancelCompleteFormModel'

import IfaForeignMarginTradeOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/IfaForeignMarginTradeOrderCancelConfirm.vue'
import IfaForeignMarginTradeOrderCancelComplete from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/IfaForeignMarginTradeOrderCancelComplete.vue'
import { IfaForeignMarginTradeOrderCancelConfirmFormModel } from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/js/IfaForeignMarginTradeOrderCancelConfirmFormModel'
import { IfaForeignMarginTradeOrderCancelCompleteFormModel } from '@/views/brokerageMenu/customerMenu/foreignStock/spotTrade/js/IfaForeignMarginTradeOrderCancelCompleteFormModel'

import { IfaOrderStatusListA003RequestModel } from './IfaOrderStatusListA003RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'

export default {
  components: {
    IfaForeignSpotTradeOrderCancelConfirm,
    IfaForeignSpotTradeOrderCancelComplete,
    IfaForeignMarginTradeOrderCancelConfirm,
    IfaForeignMarginTradeOrderCancelComplete
  },

  props: {
    tableData: {
      type: Array,
      required: true
    },
    customerInfo: {
      type: Object,
      required: true
    },
    tradeSuspendFlag: {
      type: String,
      required: true
    },
    intermediaryValueList: {
      type: Array,
      required: true
    },
    countryCd: {
      type: String,
      required: true
    }
  },
  emits: ['initialize-order-status-list', 'entrust-order-trade-info'],
  data() {
    return {
      cancelDialogVisible1: false,
      spotTradeCancelConfirmDialogVisible: false,
      spotTradeCancelCompleteDialogVisible: false,
      cancelConfirmDialogVisible: false,
      cancelCompleteDialogVisible: false,
      orderData: {},
      spotTradeCancelConfirmModel: new IfaForeignSpotTradeOrderCancelConfirmFormModel(),
      spotTradeCancelCompleteModel: new IfaForeignSpotTradeOrderCancelCompleteFormModel(),
      cancelConfirmModel: new IfaForeignMarginTradeOrderCancelConfirmFormModel(),
      cancelCompleteModel: new IfaForeignMarginTradeOrderCancelCompleteFormModel(),
      ifaFormatUtils: ifaFormatUtils
    }
  },
  computed: {
    fontColor: function() {
      return function(buySellTypeName) {
        // 買
        if (buySellTypeName === '0' ||
            buySellTypeName === '2' ||
            buySellTypeName === '4' ||
            buySellTypeName === '11') {
          return 'font-color__plus bold'
        // 売
        } else if (buySellTypeName === '1' ||
            buySellTypeName === '3' ||
            buySellTypeName === '5' ||
            buySellTypeName === '12') {
          return 'font-color__minus bold'
        } else {
          return ''
        }
      }
    }
  },
  methods: {
    // 外国現物取引注文取消確認に遷移する。
    responseHandlerSpotTradeCancelConfirminitializeA001(response) {
      this.spotTradeCancelConfirmModel = Object.assign(this.spotTradeCancelConfirmModel, response.dataList[0])
      this.spotTradeCancelConfirmDialogVisible = true
    },
    responseHandlerinitializeA001(response) {
      this.cancelConfirmModel = Object.assign(this.cancelConfirmModel, response.dataList[0])
      this.cancelConfirmDialogVisible = true
    },
    IfaOrderStatusListA003RequestModel(item) {
      return new IfaOrderStatusListA003RequestModel(item)
    },
    handleCancelFinish(response) {
      this.cancelConfirmDialogVisible = false
      this.cancelCompleteModel = Object.assign(this.cancelCompleteModel, response.dataList[0])
      this.cancelCompleteDialogVisible = true
    },
    // 外国現物取引注文取消完了画面に遷移する。
    handleCancelFinishSpotTrade(response) {
      this.spotTradeCancelConfirmDialogVisible = false
      this.spotTradeCancelCompleteModel = Object.assign(this.spotTradeCancelCompleteModel, response.dataList[0])
      this.spotTradeCancelCompleteDialogVisible = true
    },
    handleMoveToOrderListSpotTrade() {
      this.spotTradeCancelCompleteDialogVisible = false
      this.$emit('initialize-order-status-list')
    },
    handleMoveToOrderList() {
      this.cancelCompleteDialogVisible = false
      this.$emit('initialize-order-status-list')
    },
    displayA003Action(item) {
      this.$emit('entrust-order-trade-info', item)
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
.el-table {
  width: 100%;
  overflow-x: auto;
}
.inner-link {
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
}
</style>
