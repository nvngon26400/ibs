<template>
  <!-- 募集注文 -->
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
        <col style="min-width: 10rem;">
        <col style="min-width: 10rem; width: 100rem;">
        <col style="min-width: 10rem;">
        <col style="min-width: 10rem;">
        <col style="min-width: 10rem;">
        <col style="min-width: 12rem;">
      </colgroup>
      <thead>
        <tr>
          <th>銘柄コード</th>
          <th>銘柄名</th>
          <th>注文状況</th>
          <th>預り区分</th>
          <th>数量</th>
          <th>受注日時</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <tr :style="{ 'background-color': item.orderStatus === '3' ? '#C3C3C3' : '' }">
          <td
            class="text-left"
          >
            <!--銘柄コード-->
            {{ $_out(item.brandCode) }}
          </td>
          <td
            class="text-left"
          >
            <!--銘柄名-->
            {{ $_out(item.brandName) }}
          </td>
          <td
            class="text-center"
          >
            <!--注文状況-->
            {{ $_out($_getCodeValue('SUBSCRIPT_ORDER_STATUS', 1, item.orderStatus)) }}
          </td>
          <td
            class="text-center"
          >
            <!--預り区分-->
            {{ $_out($_getCodeValue('FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE', 1, item.depositType)) }}
          </td>
          <td
            class="text-right"
          >
            <!--数量-->
            {{ $_out($_addComma(item.quantity)) }}
          </td>
          <td
            class="text-center"
          >
            <!--受注日時-->
            {{ $_out($_getFormattedDateTimeValue(item.recruitmentOrderDate, 'datetime12')) }}
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
  }
}
</script>

<style lang="scss">
@import '@/styles/orderStatusList.scss';
</style>
