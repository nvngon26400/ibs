<template>
  <!-- 当日約定一覧 現引・現渡 -->
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
      <thead>
        <tr>
          <th>EC受注番号</th>
          <th>銘柄コード<br>銘柄名</th>
          <th>取引種別<br>信用取引区分<br>強制決済</th>
          <th>預り区分</th>
          <th>受注日時<br>約定日</th>
          <th>数量</th>
          <th>諸経費</th>
          <th>精算金額</th>
          <th>新規建日</th>
          <th>新規単価</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
        :class="{'even-row-color': idx % 2 != 0}"
      >
        <tr>
          <td
            class="text-center"
          >
            {{ $_out(item.ecOrderNo) }}
          </td>
          <td
            class="text-left"
          >
            {{ $_out(item.brandCode) }}<br>
            {{ $_out(item.brandName) }}
          </td>
          <td
            class="text-center"
          >
            <span :class="isFontColor(item.tradeCd)">
              {{ $_out($_getCodeValue('RECEIPT_DELIVERY_TRADE_CLASS', 1, item.tradeCd)) }}
            </span><br>
            {{ $_out(item.marginTradeClassification) }}<br>
            {{ $_out($_getCodeValue('COERCION_TYPE', 2, item.coercionType)) }}
          </td>
          <td
            class="text-center"
          >
            {{ $_out($_getCodeValue('DOMESTIC_DEPOSIT_TYPE', 4, item.depositType)) }}
          </td>
          <td
            class="text-center"
          >
            {{ $_out($_getFormattedDateTimeValue(item.orderDayTime, 'datetime12')) }}<br>
            {{ $_out($_getFormattedDateValue(item.tradeDate)) }}
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaInteger(item.quantity)) }}
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaInteger(item.cost)) }}
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaInteger(item.settlementAmount)) }}
          </td>
          <td
            class="text-center"
          >
            {{ $_out($_getFormattedDateValue(item.constructionDate)) }}
          </td>
          <td
            class="text-right"
          >
            {{ $_out($_withCommaNoneZeroPadding(Number(item.newPrice) / 100)) }}
          </td>
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script>

export default {
  props: {
    tableData: {
      type: Array,
      required: true
    }
  },
  computed: {
    // TODO:不具合管理 #2184、取引種別の「-」の場合、色が指定しないこと。
    isFontColor: function() {
      return function(tradeCd) {
        if (tradeCd === '1') {
          return 'font-color__plus bold'
        } else if (tradeCd === '0') {
          return 'font-color__minus bold'
        } else {
          return ''
        }
      }
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
