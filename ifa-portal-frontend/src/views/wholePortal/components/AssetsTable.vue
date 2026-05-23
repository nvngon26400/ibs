<template>
  <table :class="{ _table__body: true, narrow_padding_print: isPrint }">
    <thead>
      <tr>
        <th
          :class="{ _table__header: true, asset_header_print: isPrint }"
        >資産</th>
        <th class="_table__header">評価額</th>
        <th class="_table__header">評価損益</th>
      </tr>
    </thead>
    <tbody
      v-for="(item, idx) in assetsTableData.portfolioSummaryList"
      :key="idx"
    >
      <tr>
        <th
          class="_table__header _left"
          v-html="addLineBreakHints($_out(item.securityClass))"
        ></th>
        <td class="_table__data _right">{{ $_out($_withCommaInteger(item.valuation)) }}</td>
        <td class="_table__data _right">
          <span :class="[Number(item.profitAndLoss) < 0 ? 'font-color__minus' : $_out($_signedWithCommaNoneZeroPadding(item.profitAndLoss)) !== '-' && Number(item.profitAndLoss) >= 0 ? 'font-color__plus' : '', '__right bold']">
            {{ $_out($_signedWithCommaNoneZeroPadding(item.profitAndLoss)) }}
          </span>
        </td>
      </tr>
    </tbody>
    <tbody>
      <tr>
        <th class="_table__header _left">合計</th>
        <td class="_table__data _right">{{ $_out($_withCommaInteger(assetsTableData.portfolioSummaryValuationTotal)) }}</td>
        <td class="_table__data _right">
          <span :class="[Number(assetsTableData.portfolioSummaryProfitAndLossTotal) < 0 ? 'font-color__minus' : $_out($_signedWithCommaNoneZeroPadding(assetsTableData.portfolioSummaryProfitAndLossTotal)) !== '-' && Number(assetsTableData.portfolioSummaryProfitAndLossTotal) >= 0 ? 'font-color__plus' : '', '__right bold']">
            {{ $_out($_signedWithCommaNoneZeroPadding(assetsTableData.portfolioSummaryProfitAndLossTotal)) }}
          </span>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  props: {
    assetsTableData: { type: Object, require: true, default: () => ({}) },
    isPrint: { type: Boolean, require: false, default: false }
  },
  methods: {
    addLineBreakHints: (val) => {
      if (!val) {
        return val
      }
      const base = '<span style="display: inline-block">' + val + '</span>'
      const pattern = /(.*)([\(（].*?[\)）])(.*)/
      const result = base.replace(pattern, '$1</span><span style="display: inline-block">$2</span><span style="display: inline-block">$3')
      return result
    }
  }
}
</script>
<style lang="scss"  scoped>
@import "@/styles/variables.scss";
._left {
  text-align: left;
}
._right {
  text-align: right;
}
.asset_header_print {
  width: 30mm;
}
.narrow_padding_print th,
.narrow_padding_print td {
  padding: 2px;
}
</style>
