<template>
  <!-- 当日約定一覧 現物・信用 -->
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
        <col span="16">
      </colgroup>
      <thead>
        <tr>
          <th>銘柄コード<br>銘柄名</th>
          <th>取引種別<br>信用取引区分</th>
          <th>預り区分</th>
          <th>約定日<br>受渡日</th>
          <th>数量<br>平均単価</th>
          <th>課税額/譲渡益税<br>手数料/諸経費</th>
          <th>精算金額</th>
          <th>日計り</th>
          <th>代用適格</th>
          <th>出来明細</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
        :class="{'even-row-color': idx % 2 != 0}"
      >
        <tr>
          <td>
            {{ $_out(item.brandCode) }}<br>
            {{ $_out(item.brandName) }}
          </td>
          <td
            class="text-center"
          >
            <span :class="isFontColor(item.tradeClassification)">
              {{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 2, item.tradeClassification)) }}
            </span><br>
            {{ $_out(item.marginTradeClassification) }}
          </td>
          <td
            class="text-center"
          >
            {{ $_out($_getCodeValue('DOMESTIC_DEPOSIT_TYPE', 3, item.depositType)) }}
          </td>
          <td
            class="text-center"
          >
            {{ $_out($_getFormattedDateValue(item.tradeDate)) }}<br>
            {{ $_out($_getFormattedDateValue(item.settlementDate)) }}
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaInteger(item.quantity)) }}<br>
            {{ $_out($_withCommaNoneZeroPadding(item.averagePrice)) }}
          </td>
          <td
            class="text-right"
          >
            <!-- TODO:不具合管理 #2184対応  -->
            {{ $_out($_withCommaInteger(item.taxAmountCapitalGain)) }}<br>
            {{ $_out($_withCommaInteger(item.commCost)) }}<br>
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaInteger(item.settlementAmount)) }}
          </td>
          <td
            class="text-right"
          >{{ $_out($_withCommaInteger(item.dayTrade)) }}</td>
          <td
            class="text-center"
          >
            <span v-if="item.tradeClassification === '1'">
              {{ $_out($_getCodeValue('COLLATERAL_ELIGIBLE_TYPE', 1, item.collateralEligibleType)) }}
            </span>
            <span v-else>-</span>
          </td>
          <td
            class="text-center"
          >
            <ifa-button
              id="btnDetail"
              text="明細"
              width="70"
              color="primary"
              small
              action-type="originalAction"
              @app-action-handler="a002Action(item)"
            ></ifa-button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- ダイアログ -->
    <ifa-matched-trade-detail
      ref="ifaMatchedTradeDetail"
      :is-visible="dialogVisible"
      :detail-data="detailData"
      @close-modal="dialogVisible = false"
      @response-handle="responseHandler"
    ></ifa-matched-trade-detail>

  </div>
  <!-- <ifa-requester
    id="IfaMatchedTradeDetailA001"
    action-id="SUB0202_0105-02#A001"
    action-type="requestAction"
    :request-model="IfaDomesticTradeStatusListA002RequestModel"
    @response-handler="responseHandlerInitializeA001($event)"
  ></ifa-requester> -->
</template>

<script>
import IfaMatchedTradeDetail from '../IfaMatchedTradeDetail'
import { IfaDomesticTradeStatusListA002RequestModel } from '../js/IfaDomesticTradeStatusListA002RequestModel'

export default {
  components: {
    IfaMatchedTradeDetail
  },
  props: {
    tableData: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      dialogVisible: false,
      detailData: {},
      IfaDomesticTradeStatusListA002RequestModel: {}
    }
  },
  computed: {
    // TODO:不具合管理 #2184の横展開、取引種別の「-」の場合、色が指定しないこと。
    isFontColor: function() {
      return function(tradeClassification) {
        if (tradeClassification === '1' ||
            tradeClassification === '3' ||
            tradeClassification === '5' ||
            tradeClassification === '8') {
          return 'font-color__plus bold'
        } else if (tradeClassification === '2' ||
            tradeClassification === '4' ||
            tradeClassification === '6' ||
            tradeClassification === '7') {
          return 'font-color__minus bold'
        } else {
          return ''
        }
      }
    }
  },
  methods: {
    a002Action(rowData) {
      this.IfaDomesticTradeStatusListA002RequestModel = new IfaDomesticTradeStatusListA002RequestModel(rowData)
      this.$refs['ifaMatchedTradeDetail'].setA001Req(this.IfaDomesticTradeStatusListA002RequestModel)
      this.$refs['ifaMatchedTradeDetail'].initializeA001()
      // document.getElementById('IfaMatchedTradeDetailA001').click()
    },
    responseHandler() {
      this.dialogVisible = true
      this.$refs.ifaMatchedTradeDetail.onShow()
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
