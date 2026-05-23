<template>
  <div>
    <el-row style="margin-top: 1rem;">
      <el-col
        :span="1"
        style="padding-left: 0.5rem"
      >
        <img :src="flagImg">
      </el-col>
      <el-col
        :span="20"
        style="margin-top: 0.5rem;"
      >
        <span
          class="_bold_black_m"
          style="padding-left: 0rem;"
        >{{ $_out(currencyName) }}</span>
      </el-col>
    </el-row>

    <!-- 総合口座 -->
    <template
      v-if="showWholeAccount && hasWholeAccountYenDeposit"
    >
      <!-- ヘッダー -->
      <el-row>
        <el-col>
          <span
            v-if="hasJrNisaAccount"
            class="_bold_black_m"
          >現物取引（総合口座）</span>
          <span
            v-else
            class="_bold_black_m"
          >現物取引</span>
        </el-col>
      </el-row>

      <table
        class="_table__body"
        style="width:100%; margin-bottom: 1rem;"
      >
        <tbody>
          <!-- 受渡日 -->
          <tr>
            <th class="_table__header __left left_header">受渡日</th>
            <th
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__header __center table_data"
            >
              {{ $_out($_getFormattedDateValue(buyingPower.domesticSettlementDate)) }}
              <br>
              ({{ buyingPower.settlementDateAfterBusinessDay }})
            </th>
          </tr>

          <!-- 預り金 -->
          <tr>
            <th class="_table__header __left left_header">預り金</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.yenDeposit, currencyUnit) }}
            </td>
          </tr>

          <!-- 入金額 -->
          <tr>
            <th class="_table__header __left left_header">入金額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.depositAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 支払額 -->
          <tr>
            <th class="_table__header __left left_header">支払額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.payment, currencyUnit) }}
            </td>
          </tr>

          <!-- 未約定買注文額 -->
          <tr>
            <th class="_table__header __left left_header">未約定買注文額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.uncontractedPurchaseOrderAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 出金額 -->
          <tr>
            <th class="_table__header __left left_header">出金額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.withdrawAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 受取額 -->
          <tr>
            <th class="_table__header __left left_header">受取額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.amountReceived, currencyUnit) }}
            </td>
          </tr>

          <!-- 受取額(日計り分) -->
          <tr>
            <th class="_table__header __left left_header">受取額(日計り分)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.dayTrading, currencyUnit) }}
            </td>
          </tr>

          <!-- 振替予定額(信用口座→現物口座) -->
          <tr v-if="hasMarginAccount">
            <th class="_table__header __left left_header">振替予定額<br>(信用口座→現物口座)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.transferPlanAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 残高(預り金) -->
          <tr>
            <th class="_table__header __left left_header">残高(預り金)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.depositTotalBalance, currencyUnit) }}
            </td>
          </tr>

          <!-- 必要精算額 -->
          <tr>
            <th class="_table__header __left left_header">必要精算額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.needSettleAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 買付余力 -->
          <tr>
            <th class="_table__header __left left_header">買付余力</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.buyingPower, currencyUnit) }}
            </td>
          </tr>

          <!-- 当日米株店頭買付約定金額 -->
          <tr v-if="isUsdData">
            <th class="_table__header __left left_header">当日米株店頭買付約定金額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.todayAmericaCounterBuyTradeAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 当日外債買付代金 -->
          <tr v-if="isUsdData">
            <th class="_table__header __left left_header">当日外債買付代金</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.todayForeignBondBuyAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 買付余力2 -->
          <tr v-if="isUsdData">
            <th class="_table__header __left left_header">買付余力</th>
            <td
              v-for="(buyingPower, index) in buyingPowersWholeAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.buyingPower2, currencyUnit) }}
            </td>
          </tr>
        </tbody>
      </table>
    </template>

    <!-- ジュニアNISA口座 -->
    <template
      v-if="showJrNisaAccount && hasJrNisaAccountYenDeposit"
    >
      <!-- ヘッダー -->
      <el-row>
        <el-col>
          <span
            class="_bold_black_m"
          >現物取引（ジュニアNISA口座）</span>
        </el-col>
      </el-row>

      <table
        class="_table__body"
        style="width:100%; margin-bottom: 1rem;"
      >
        <tbody>
          <!-- 受渡日 -->
          <tr>
            <th class="_table__header __left left_header">受渡日</th>
            <th
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__header __center"
            >
              {{ $_out($_getFormattedDateValue(buyingPower.domesticSettlementDate)) }}
              <br>
              ({{ buyingPower.settlementDateAfterBusinessDay }})
            </th>
          </tr>

          <!-- 預り金 -->
          <tr>
            <th class="_table__header __left left_header">預り金</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.yenDeposit, currencyUnit) }}
            </td>
          </tr>

          <!-- 入金額 -->
          <tr>
            <th class="_table__header __left left_header">入金額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.depositAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 支払額 -->
          <tr>
            <th class="_table__header __left left_header">支払額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.payment, currencyUnit) }}
            </td>
          </tr>

          <!-- 未約定買注文額 -->
          <tr>
            <th class="_table__header __left left_header">未約定買注文額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.uncontractedPurchaseOrderAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 出金額 -->
          <tr>
            <th class="_table__header __left left_header">出金額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.withdrawAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 受取額 -->
          <tr>
            <th class="_table__header __left left_header">受取額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.amountReceived, currencyUnit) }}
            </td>
          </tr>

          <!-- 受取額(日計り分) -->
          <tr>
            <th class="_table__header __left left_header">受取額(日計り分)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.dayTrading, currencyUnit) }}
            </td>
          </tr>

          <!-- 振替予定額(総合口座→ジュニアNISA口座) -->
          <tr v-if="hasMarginAccount">
            <th class="_table__header __left left_header">振替予定額<br>(総合口座→ジュニアNISA口座)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.transferPlanAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 残高(預り金) -->
          <tr>
            <th class="_table__header __left left_header">残高(預り金)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.depositTotalBalance, currencyUnit) }}
            </td>
          </tr>

          <!-- 振替可能額(総合口座→ジュニアNISA口座) -->
          <tr>
            <th class="_table__header __left left_header">振替可能額<br>(総合口座→ジュニアNISA口座)</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.transferAbleAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 必要精算額 -->
          <tr>
            <th class="_table__header __left left_header">必要精算額</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.needSettleAmount, currencyUnit) }}
            </td>
          </tr>

          <!-- 買付余力 -->
          <tr>
            <th class="_table__header __left left_header">買付余力</th>
            <td
              v-for="(buyingPower, index) in buyingPowersJrNisaAccount"
              :key="index"
              class="_table__data __right table_data"
            >
              {{ formatForeignCurrency(buyingPower.buyingPower, currencyUnit) }}
            </td>
          </tr>
        </tbody>
      </table>
    </template>
  </div>
</template>

<script>
export default {
  props: {
    buyingPowersWholeAccount: {
      type: Array,
      default: () => []
    },
    buyingPowersJrNisaAccount: {
      type: Array,
      default: () => []
    },
    currencyName: {
      type: String,
      default: ''
    },
    currencyUnit: {
      type: String,
      default: ''
    },
    flagImg: {
      type: String,
      default: ''
    },
    showWholeAccount: {
      type: Boolean,
      default: false
    },
    showJrNisaAccount: {
      type: Boolean,
      default: false
    },
    hasJrNisaAccount: {
      type: Boolean,
      default: false
    },
    hasMarginAccount: {
      type: Boolean,
      default: false
    }
  },

  computed: {
    wholeAccountLabel() {
      return this.showJrNisaAccount
        ? '現物取引（総合口座）'
        : '現物取引'
    },

    hasWholeAccountYenDeposit() {
      return (
        this.buyingPowersWholeAccount &&
        this.buyingPowersWholeAccount.some(val => {
          return (
            val.yenDeposit &&
            !Number.isNaN(parseFloat(val.yenDeposit)) &&
            parseFloat(val.yenDeposit) !== 0.0
          )
        })
      )
    },

    hasJrNisaAccountYenDeposit() {
      return (
        this.buyingPowersJrNisaAccount &&
        this.buyingPowersJrNisaAccount.some(val => {
          return (
            val.yenDeposit &&
            !Number.isNaN(parseFloat(val.yenDeposit)) &&
            parseFloat(val.yenDeposit) !== 0.0
          )
        })
      )
    },

    isUsdData() {
      return this.currencyUnit === 'USD'
    }
  },

  methods: {
    formatForeignCurrency(amount, unit) {
      const formattedAmount = this.$_withCommaZeroPadding(amount, 2) || '0.00'
      return formattedAmount + ' ' + unit
    }
  }
}
</script>

<style lang="scss" scoped>
  @import '@/styles/orderStatusList.scss';
  :deep(.el-table) .__center {
    text-align: center;
  }
  :deep(.el-table) .__left {
    text-align: left;
  }
  :deep(.el-table) .__right {
    text-align: right;
  }
  .left_header {
    width: 225px;
    min-width: 225px;
  }

  .table_data {
    width: calc((100% - 225px) / 6);
    min-width: 160px;
  }
</style>
