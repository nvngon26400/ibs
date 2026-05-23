<template>
  <div
    v-if="isVisible"
    style="height: 600px; overflow: auto;"
  >
    <table
      cellspacing="0"
      cellpadding="0"
      border="0"
      class="el-table"
    >
      <tbody>
        <tr>
          <th class="investment-trust-table_name">コード 銘柄名</th>
          <th class="investment-trust-table__top">区分</th>
          <th class="investment-trust-table__top">目論見書</th>
          <th class="investment-trust-table__top">基準価額<br>前日比</th>
          <th class="investment-trust-table_margin">買付手数料（税込）<br>優遇適用率</th>
          <th class="investment-trust-table__top">目論見書<br>発送日</th>
          <th class="investment-trust-table__top">通貨選択<br>確認書</th>
          <th class="investment-trust-table__top">複雑投信<br>確認書</th>
          <th class="investment-trust-table__top">購入可能日</th>
          <th class="investment-trust-table__top">マーケット<br>発注日</th>
          <th class="investment-trust-table__top">取引<br>（締切時刻）</th>
        </tr>
        <template
          v-for="item in tableData"
          :key="item.index"
        >
          <tr>
            <td>{{ $_out(item.brandCodeBrandName) }}</td>
            <td>
              <ifa-text
                code-list-id="FUND_TYPE"
                :disp-pattern="1"
                :code-key="item.fundType"
              ></ifa-text>
              <span v-if="item.fundType === null || !item.fundType.length">-</span>
            </td>
            <td>
              <ifa-text
                code-list-id="PROSPECTUS_STATUS"
                :disp-pattern="1"
                :code-key="item.prospectus"
              ></ifa-text>
              <span v-if="item.prospectus === null || !item.prospectus.length">-</span>
            </td>
            <td class="align-right">
              {{ $_withCommaNoneZeroPadding($_out($_withCommaInteger(item.price))) }}<br>
              <span :class="changeColor($_signedWithCommaInteger(item.diff))">
                {{ $_out($_signedWithCommaInteger(item.diff)) }}
              </span>
            </td>
            <td class="align-right">
              {{ $_withCommaNoneZeroPadding($_out(item.buyComm)) }}
              <br>
              {{ $_withCommaZeroPadding($_out(item.preferentialApplicationRate), 2) }}
              {{ item.preferentialApplicationRate === null || isNaN(item.preferentialApplicationRate) || !item.preferentialApplicationRate.length ? '' : '%' }}
            </td>
            <td>{{ $_out($_getFormattedDateValue(item.prospectusShippingDate)) }}</td>
            <td>
              <ifa-text
                code-list-id="CURRENCY_TYPE_COMPLICATED_CONFIRM_DOC_ACCEPTANCE_STATUS"
                :disp-pattern="2"
                :code-key="item.currencySelectConfirmDocument"
              ></ifa-text>
              <span v-if="item.currencySelectConfirmDocument === null || !item.currencySelectConfirmDocument.length">-</span>
            </td>
            <td>
              <ifa-text
                code-list-id="CURRENCY_TYPE_COMPLICATED_CONFIRM_DOC_ACCEPTANCE_STATUS"
                :disp-pattern="1"
                :code-key="item.complexMutualFundConfirmDocument"
              ></ifa-text>
              <span v-if="item.complexMutualFundConfirmDocument === null || !item.complexMutualFundConfirmDocument.length">-</span>
            </td>
            <td>{{ $_out($_getFormattedDateValue(item.buyAbledate)) }}</td>
            <td>{{ $_out($_getFormattedDateValue(item.marketOrderDate)) }}</td>
            <td style="min-width: 7rem;">
              <span
                v-if="item.coercionOrderTarget == 3"
                class="force-order"
              >強制注文対象</span>
              <span style="display: flex; align-items: center; justify-content: center;">
                <ifa-button
                  v-if="item.coercionOrderTarget == 1 || item.coercionOrderTarget == 2"
                  id="btnBuy"
                  text="購入"
                  color="buy"
                  :disabled="item.coercionOrderTarget == 2"
                  small
                  width="40"
                  action-type="originalAction"
                  @app-action-handler="handleClick(item.brandCodeBrandName)"
                ></ifa-button>
                <ifa-button
                  v-if="item.accumulateType === '1' || item.accumulateType === '4'"
                  name="btnAccumulate"
                  text="積立"
                  class="accumulation-class"
                  :disabled="item.accumulateType === '4'"
                  small
                  width="40"
                  action-type="originalAction"
                  @app-action-handler="handleRedirectAccumulate(item)"
                ></ifa-button>
              </span>
              <span>({{ item.deadlines }})</span>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
    <div
      v-if="parseInt(hitNumber) == 0"
      style="margin: 10px auto; text-align: center;"
    >投信購入・積立可能明細はありません。</div>
  </div>
</template>

<script>
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaText from '@/components/Input/IfaText.vue'

export default {
  components: {
    IfaButton,
    IfaText
  },
  props: {
    dataList: { type: Array, required: true },
    isVisible: { type: Boolean, required: true },
    hitNumber: { type: String, required: true }
  },
  emits: ['order', 'on-redirect-accumulate'],
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    setTableData() {
      this.tableData = this.dataList
      this.tableData.forEach(item => {
        if (item.deadlines === null || !item.deadlines.length) {
          item.deadlines = '--:--'
        } else {
          item.deadlines = item.deadlines.slice(0, 2) + ':' + item.deadlines.slice(2, 4)
        }
      })
    },
    handleClick(val) {
      const param = {
        fundCodeTimes: val.slice(0, 4),
        fundCodeIssues: val.slice(6, 8),
        dispatchId: ' ',
        listFlag: true
      }
      this.$emit('order', param)
    },
    handleRedirectAccumulate(item) {
      const { fundCodeTimes, fundCodeIssues, kyoukaiCd } = item

      this.$emit('on-redirect-accumulate', {
        fundCodeTimes,
        fundCodeIssues,
        kyoukaiCd,
        listFlag: true
      })
    },
    changeColor(value) {
      if (parseInt(value) < 0) {
        return 'font-color__minus bold'
      } else if (parseInt(value) > 0) {
        return 'font-color__plus bold'
      } else {
        return 'bold'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";

.el-table th, .el-table td {
    border-right: 1px solid #EBEEF5;
    border-bottom: 1px solid #EBEEF5;
    text-align: center;
    vertical-align: top;
}
.el-table th {
  padding: 5px;
  font-weight: 600;
}
.investment-trust-table__top {
  border-top: 1px solid #EBEEF5;
  width:8%
}
.investment-trust-table_margin {
  border-top: 1px solid #EBEEF5;
  width:10%
}
.investment-trust-table_name {
  border-top: 1px solid #EBEEF5;
  width:18%
}
:deep(.el-table) .align-right {
  text-align: right;
}
:deep(.el-table) .align-left {
  text-align: left;
}
.force-order {
  color: red;
  font-weight: 700;
  margin-top: 10px;
}
::v-deep .accumulation-class .el-button {
  background-color: #70ad47 !important;
  border-color: #70ad47 !important;
  &.is-disabled {
    color: #676b74 !important;
    background-color: #f5f6f7 !important;
    border-color: $mainRuledLineColor !important;
  }
}
</style>
