<template>
  <!-- 外国注文状況一覧：店頭 -->
  <div
    v-if="tableData.length != 0 && tableData != null"
    class="order-status-list-table"
  >
    <ifa-requester
      id="IfaOrderStatusListForeignStockCounterDetailDisplayA004"
      action-id="SUB0202_0104-01#A004"
      action-type="requestAction"
      :request-model="IfaOrderStatusListA004RequestModel"
      @response-handler="responseHandlerForeignStockCounterDetailDisplayA004"
    ></ifa-requester>
    <ifa-requester
      id="IfaForeignStockCounterDetailDisplayinitializeA001"
      action-id="SUB0202_0104-03#A001"
      action-type="requestAction"
      :request-model="IfaForeignStockCounterDetailDisplayA001RequestModel"
      @response-handler="responseHandlerIfaForeignStockCounterDetailDisplayInitializeA001"
    ></ifa-requester>
    <table
      cellspacing="0"
      cellpadding="0"
      :border="0"
      class="el-table"
      style="width: 100%"
    >
      <colgroup>
        <col style="min-width: 8rem;">
        <col style="min-width: 10rem; width: 100rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 8rem;">
        <col style="min-width: 6rem;">
        <col style="min-width: 8rem;">
        <col style="min-width: 8rem;">
        <col style="min-width: 8rem;">
      </colgroup>
      <thead>
        <!-- ヘッダ１行目 -->
        <tr>
          <th rowspan="2">管理番号</th>
          <th rowspan="2">ティッカー<br>銘柄名</th>
          <th rowspan="1">ステータス</th>
          <th rowspan="1">取引種別</th>
          <th rowspan="1">預り区分</th>
          <th rowspan="1">注文日時<br>約定日時</th>
          <th rowspan="1">数量</th>
          <th rowspan="1">価格</th>
          <th rowspan="1">ワーニング取引</th>
          <th rowspan="2">詳細</th>
        </tr>
        <!-- ヘッダ２行目 -->
        <tr>
          <th colspan="7">取消理由</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <!-- データ１行目 -->
        <tr :style="{ 'background-color': item.status === '30' ? '#C3C3C3' : '' }">
          <td
            class="text-center"
            rowspan="2"
          >
            <!--管理番号-->
            {{ $_out(item.manageNumber) }}
          </td>
          <td
            class="text-left"
            rowspan="2"
          >
            <!--ティッカー-->
            {{ $_out(item.brandCode) }}<br>
            <!--銘柄名-->
            {{ $_out(item.brandName) }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--ステータス-->
            {{ $_out($_getCodeValue('FOREIGN_STOCK_COUNTER_ORDER_STATUS', 1, item.status)) }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--取引種別-->
            <span :class="isFontColor(item.buySellTypeName)">
              {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, item.buySellTypeName)) }}
            </span>
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--預り区分-->
            {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.depositType)) }}
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--注文日時-->
            {{ $_out($_getFormattedDateTimeValue(item.orderDayTime, 'datetime12')) }}<br>
            <!--約定日時-->
            {{ $_out($_getFormattedDateTimeValue(item.tradeDateTime, 'datetime12')) }}
          </td>
          <td
            class="text-right"
            rowspan="1"
          >
            <!--数量-->
            {{ $_out($_addComma(item.domesticQuantityInput)) }}
          </td>
          <td
            rowspan="1"
            class="text-right"
          >
            <!--価格-->
            {{ $_out($_addComma(item.tradePrice)) }}&nbsp;USD
          </td>
          <td
            class="text-center"
            rowspan="1"
          >
            <!--ワーニング取引-->
            {{ $_out($_getCodeValue('WARNING_APPLICATION_TRAD', 1, item.warningApplyTrade)) }}
          </td>
          <td
            class="text-center"
            rowspan="2"
          >
            <!--詳細-->
            <ifa-button
              id="btnDetail"
              text="詳細"
              color="secondary"
              small
              width="80"
              action-type="originalAction"
              @app-action-handler="foreignStockCounterDetailDisplayA004(item)"
            ></ifa-button>
          </td>
        </tr>
        <!-- データ２行目 -->
        <tr :style="{ 'background-color': item.status === '30' ? '#C3C3C3' : '' }">
          <td
            v-if="item.cancelReason"
            class="text-left"
            colspan="7"
          ><!--取消理由-->
            {{ $_out(item.cancelReason) }}
          </td>
        </tr>
      </tbody>
    </table>
    <ifa-foreign-stock-counter-detail-display
      v-if="isVisible"
      :is-visible="isVisible"
      :row-data="orderData"
      @close-modal="isVisible = false"
    ></ifa-foreign-stock-counter-detail-display>
  </div>
</template>

<script>
import IfaForeignStockCounterDetailDisplay from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/IfaForeignStockCounterDetailDisplay.vue'
import { IfaForeignStockCounterDetailDisplayA001RequestModel } from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/js/IfaForeignStockCounterDetailDisplayA001RequestModel'
import { IfaOrderStatusListA004RequestModel } from './IfaOrderStatusListA004RequestModel'
import { IfaForeignStockCounterDetailDisplayFormModel } from '@/views/brokerageMenu/customerMenu/accountManage/orderStatus/js/IfaForeignStockCounterDetailDisplayFormModel'

export default {
  components: {
    IfaForeignStockCounterDetailDisplay
  },
  props: {
    tableData: {
      type: Array,
      required: true
    },
    customerInfo: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      form: new IfaForeignStockCounterDetailDisplayFormModel(),
      isVisible: false,
      orderData: {},
      requestModel: {}
    }
  },
  computed: {
    IfaOrderStatusListA004RequestModel() {
      return new IfaOrderStatusListA004RequestModel(this.requestModel)
    },
    IfaForeignStockCounterDetailDisplayA001RequestModel() {
      return new IfaForeignStockCounterDetailDisplayA001RequestModel(this.requestModel)
    },
    isFontColor: function() {
      return function(buySellTypeName) {
        if (buySellTypeName === '12') {
          return 'font-color__minus bold'
        } else if (buySellTypeName === '11') {
          return 'font-color__plus bold'
        } else {
          return ''
        }
      }
    }
  },
  methods: {
    foreignStockCounterDetailDisplayA004(item) {
      this.requestModel = {
        manageNumber: item.manageNumber,
        tradeKbn: item.tradeKbn
      }
      this.$nextTick(() => {
        document.getElementById('IfaOrderStatusListForeignStockCounterDetailDisplayA004').click()
      })
    },
    responseHandlerForeignStockCounterDetailDisplayA004() {
      this.$nextTick(() => {
        document.getElementById('IfaForeignStockCounterDetailDisplayinitializeA001').click()
      })
    },
    responseHandlerIfaForeignStockCounterDetailDisplayInitializeA001(response) {
      this.orderData = Object.assign(this.form, response.dataList[0])
      this.isVisible = true
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/orderStatusList.scss';
</style>

