<template>
  <!-- その他商品注文一覧 その他余力拘束 -->
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
          <th
            colspan="2"
            rowspan="2"
          >注文番号</th>
          <th
            colspan="2"
            rowspan="2"
          >EC受注番号</th>
          <th
            colspan="2"
            rowspan="2"
          >口座区分</th>
          <th
            colspan="2"
            rowspan="1"
          >金額（買付余力）</th>
          <th
            colspan="2"
            rowspan="1"
          >入力日</th>
          <th
            colspan="2"
            rowspan="2"
          >拘束理由</th>
          <th
            colspan="2"
            rowspan="1"
          >発注日</th>
          <th
            colspan="2"
            rowspan="2"
          >取消</th>
        </tr>
        <tr>
          <th
            colspan="2"
            rowspan="1"
          >金額（NISA投資可能額）</th>
          <th
            colspan="2"
            rowspan="1"
          >拘束期限</th>
          <th
            colspan="2"
            rowspan="1"
          >受注日時</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
        :class="{'even-row-color': idx % 2 != 0}"
      >
        <tr>
          <td
            colspan="2"
            rowspan="2"
            class="text-center"
          >{{ item.orderNo }}</td>
          <td
            colspan="2"
            rowspan="2"
            class="text-center"
          >{{ item.ecOrderNo }}</td>
          <td
            colspan="2"
            rowspan="2"
            class="text-center"
          >{{ item.accountType }}</td>
          <td
            colspan="2"
            rowspan="1"
            class="text-right"
          >{{ item.buyingPower.toLocaleString() }}</td>
          <td
            colspan="2"
            rowspan="1"
            class="text-center"
          >{{ item.inputDate }}</td>
          <td
            colspan="2"
            rowspan="2"
            class="text-left"
          >{{ item.bindReason }}</td>
          <td
            colspan="2"
            rowspan="1"
            class="text-center"
          >{{ item.placingOrderDate }}</td>
          <td
            colspan="2"
            rowspan="2"
            class="text-center"
          >
            <div>
              <ifa-button
                text="取消"
                width="80"
                color="secondary"
                small
                :disabled="!item.cancelFlag"
                action-type="originalAction"
                @app-action-handler="cancelDialogVisible = true"
              ></ifa-button>
            </div>
          </td>
        </tr>
        <tr>
          <td
            colspan="2"
            rowspan="1"
            class="text-right"
          >{{ item.nisaBuyingPower.toLocaleString() }}</td>
          <td
            colspan="2"
            rowspan="1"
            class="text-center"
          >{{ item.bindPriod }}</td>
          <td
            colspan="2"
            rowspan="1"
            class="text-center"
          >{{ item.receivingOrderDatetime }}</td>
        </tr>
      </tbody>
    </table>
    <div class="message">※「投信積立銀行引落」は拘束期限日前営業日24:30頃、「その他」は拘束期限日27:00頃に余力拘束が解除されます。</div>

    <!-- ダイアログ -->
    <ifa-demo-dialog
      :is-visible="cancelDialogVisible"
      @close-modal="cancelDialogVisible = false"
    ></ifa-demo-dialog>
  </div>
</template>

<script>
import IfaDemoDialog from '../IfaDemoDialog'

export default {
  components: {
    IfaDemoDialog
  },
  props: {
    tableData: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      cancelDialogVisible: false
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>

.message {
  margin-top: 10px;
  color: #18181A;
}
</style>
