<template>
  <!-- 未約定一覧 投資信託 -->
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
        <col style="min-width: 8rem;">
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
        <tr>
          <th>EC受注番号</th>
          <th>銘柄コード<br>ファンド名</th>
          <th>注文状況</th>
          <th>取引種別</th>
          <th>預り区分</th>
          <th>受注日時<br>発注日</th>
          <th>口数/金額</th>
          <th>ポイント種別<br>利用ポイント<br></th>
          <th style="white-space: nowrap;">分配金受取方法指定<br>強制</th>
          <th>取消</th>
        </tr>
      </thead>

      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <tr :style="{ 'background-color': item.orderStatus === '約定' ? '#C3C3C3' : '' }">
          <td
            class="text-center"
          ><!--EC受注番号-->
            {{ $_out(item.ecOrderNo) }}
          </td>
          <td
            class="text-center"
            style="text-align: left;"
          >
            <!--銘柄コード-->
            <span>
              {{ item.fundCodeTimes }}{{ item.fundCodeIssues ? '.' + item.fundCodeIssues : '' }}<br>
            </span>
            <!--ファンド名-->
            <span>{{ $_out(item.brandName) }}</span>
          </td>
          <td
            class="text-center"
          >
            <!--注文状況-->
            {{ $_out(item.orderStatus) }}</td>
          <td
            class="text-center"
          >
            <!--取引種別-->
            <span :class="fontColor(item.buySellTypeName)">
              {{ $_out($_getCodeValue('DOMESTIC_MUTUAL_FUND_TRADE_CLASS', 1, item.buySellTypeName)) }}
            </span>
          </td>
          <td
            class="text-center"
          >
            <!--預り区分-->
            {{ $_out($_getCodeValue('MUTUAL_FUND_DEPOSIT_TYPE', setDepositDisplay(item.tradeKbn, item.notSpecificDepositTradeType), item.notSpecificDepositTradeType)) }}
          </td>
          <td
            class="text-center"
          >
            <!--発注日時-->
            {{ $_out($_getFormattedDateTimeValue(item.orderDayTime, 'datetime12')) }}<br>
            <!--発注日-->
            {{ $_out($_getFormattedDateValue(item.orderDate)) }}
          </td>
          <td
            class="text-center"
            style="text-align: right;"
          ><!--口数/金額-->
            {{ $_out($_addComma(item.unitAmount)) }} {{ item.unit }}</td>
          <td
            class="text-center"
            style="text-align: right;"
          >
            <!--ポイント種別-->
            {{ $_out($_getCodeValue('POINT_TYPE', 1, item.pointType)) }}<br>
            <!--利用ポイント-->
            {{ item.callcenterKbn === '9' ? '未確定' : item.point === '        ' ? '0' : $_out($_addComma(item.point)) }}<br>
          </td>
          <td
            class="text-center"
          >
            <!--分配金受取方法指定-->
            {{ $_out($_getCodeValue('DISTRIBUTION_RECEIVE_METHOD', 3, item.distributionReceiveMethod)) }}<br>
            <!--強制-->
            {{ item.yoryokuCheckKbn === ' ' ? '-' : '強制' }}
          </td>
          <td class="text-center">
            <!--取消-->
            <ifa-button
              v-if="tradeSuspendFlag === '0' &&
                intermediaryValueList.filter(list => list.productCd === '06').length !== 0 &&
                intermediaryValueList.filter(list => list.tradeCd === item.buySellTypeName).length !== 0 &&
                intermediaryValueList.filter(list => list.mediatePropriety === '1').length !== 0 &&
                item.mutualFundCancelButtonDisplayJudgment === '1'"
              id="btnCancel"
              text="取消"
              width="80"
              color="secondary"
              small
              action-type="originalAction"
              @app-action-handler="cancelDialogOpenA016(item)"
            ></ifa-button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- ダイアログ -->
    <ifa-domestic-mutual-fund-order-cancel-confirm
      v-if="cancelDialogVisible"
      ref="IfaDomesticMutualFundOrderCancelConfirm"
      :customer-info="customerInfo"
      :is-visible="cancelDialogVisible"
      @close-modal="cancelDialogVisible = false"
      @initialize-order-status-list="domesticMutualFundOrderCancelCompleteFinish"
    ></ifa-domestic-mutual-fund-order-cancel-confirm>

    <ifa-requester
      id="IfaDomesticMutualFundOrderCancelConfirmA001"
      action-type="requestAction"
      action-id="SUB0202_0401-04_1#A001"
      :request-model="a016RequestModel"
      @response-handler="responseHandlerA016($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaDomesticMutualFundOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/investmentTrust/domesticMutualFund/IfaDomesticMutualFundOrderCancelConfirm.vue'
import { IfaOrderStatusListA016RequestModel } from './IfaOrderStatusListA016RequestModel'

export default {
  components: {
    IfaDomesticMutualFundOrderCancelConfirm
  },
  props: {
    customerInfo: {
      type: Object,
      required: true
    },
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
      cancelDialogVisible: false,
      a016RequestModel: {}
    }
  },
  computed: {
    fontColor: function() {
      return function(buySellTypeName) {
        // 買
        if (buySellTypeName === '0' ||
        buySellTypeName === '1' ||
        buySellTypeName === '2' ||
        buySellTypeName === '11') {
          return 'font-color__plus bold'
        // 売
        } else {
          return 'font-color__minus bold'
        }
      }
    }
  },
  methods: {
    cancelDialogOpenA016(item) {
      this.a016RequestModel = new IfaOrderStatusListA016RequestModel(item)
      document.getElementById('IfaDomesticMutualFundOrderCancelConfirmA001').click()
    },
    responseHandlerA016(response) {
      this.cancelDialogVisible = true
      this.$nextTick(() => {
        this.$refs['IfaDomesticMutualFundOrderCancelConfirm'].onShow(response.dataList[0])
      })
    },
    domesticMutualFundOrderCancelCompleteFinish() {
      this.cancelDialogVisible = false
      this.$emit('initialize-order-status-list')
    },
    setDepositDisplay(tradeKbn, notSpecificDepositTradeType) {
      // 国内投信注文リスト.売買区分 = "K"：買(購入) の場合
      if (tradeKbn === 'K') {
        // 国内投信注文リスト.非特定預り売買区分=5:買付時：Jr特定/Jr一般　かつ (顧客共通情報.ジュニア特定口座区分=(1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
        if (notSpecificDepositTradeType === '5' && !(this.customerInfo.jrTokuteiKouzaKbn === '1' || this.customerInfo.jrTokuteiKouzaKbn === '2')) {
          return 7
        // 国内投信注文リスト.非特定預り売買区分=△:特定/一般　かつ (顧客共通情報.特定口座区分= (1:特定口座(代行納付) または 2:特定口座(確定申告)) でない)場合
        } else if (notSpecificDepositTradeType === ' ' && !(this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2')) {
          return 7
        } else {
          return 2
        }
      } else {
        // 国内投信注文リスト.売買区分 ≠ "K"：買(購入) の場合
        return 1
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
</style>
