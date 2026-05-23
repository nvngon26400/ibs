<template>
  <!-- 外国MMF一覧 -->
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
          <th>注文番号</th>
          <th>銘柄コード<br>ファンド名</th>
          <th>注文状況</th>
          <th>取引種別<br>決済方法</th>
          <th>預り区分</th>
          <th>受注日時<br>締切日時</th>
          <th>価格</th>
          <th>取消</th>
        </tr>
      </thead>
      <tbody
        v-for="(item, idx) in tableData"
        :key="idx"
      >
        <tr>
          <td
            class="text-center"
          ><!--注文番号-->{{ '000008' }}</td>
          <td
            class="text-left"
          ><!--銘柄コード-->{{ '0Z41-0405469' }}<br><!--ファンド名-->{{ '南アフリカランドMMF' }}</td>
          <td
            class="text-center"
          ><!--注文状況-->{{ '注文中' }}</td>
          <td
            class="text-center"
          ><!--取引種別--><span :class="true ? 'font-color__plus bold' : 'font-color__minus bold'">{{ '買付' }}</span><br><!--決済方法-->外貨決算</td>
          <td
            class="text-center"
          ><!--預り区分-->{{ '一般' }}</td>
          <td
            class="text-center"
          ><!--受注日時-->{{ '2021/09/06 15:31' }}<br><!--締切日時-->{{ '2021/09/06 18:31' }}</td>
          <td
            class="text-right"
          ><!--価格-->{{ '10,000' }}&nbsp;円</td>
          <td
            class="text-center"
          >
            <!-- 取消 -->
            <div
              style="margin-bottom: 0.5rem; text-align: center;"
            >
              <ifa-button
                text="取消"
                width="80"
                color="secondary"
                small
                :disabled="!item.cancelFlag"
                action-type="originalAction"
                @app-action-handler="cancelDialogOpen(idx)"
              ></ifa-button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 取消ダイアログ（ダミー） -->
    <!-- <el-dialog
      v-model="cancelDialogVisible"
      :title="'取消'"
      :show-close="true"
      :center="true"
      :close-on-click-modal="false"
      class="correction-dialog"
    >
    <span class="_bold_red_l">作成中（ダミー）</span>
    </el-dialog> -->
    <!-- /取消ダイアログ（ダミー） -->
    <!-- <ifa-foreign-currency-mmf-order-cancel-confirm
      :is-visible="cancelDialogVisible"
      :cancel-data="orderData"
      :customer-info="customerInfo"
      @close-modal="cancelDialogVisible = false"
    ></ifa-foreign-currency-mmf-order-cancel-confirm> -->
  </div>
</template>

<script>
// import ifaForeignCurrencyMmfOrderCancelConfirm from '@/views/brokerageMenu/customerMenu/transaction/orderStatus/IfaForeignCurrencyMmfOrderCancelConfirm.vue'
// import IfaForeignOrderCorrectionInput from '../IfaForeignOrderCorrectionInput.vue'

export default {
  components: {
    // ifaForeignCurrencyMmfOrderCancelConfirm
    // IfaForeignOrderCorrectionInput,
  },

  props: {
    tableData: {
      type: Array,
      required: true
    },
    customerInfo: { type: Object, required: true }
  },
  data() {
    return {
      cancelDialogVisible: false,
      correctDialogVisible: false,
      form: {
      },
      orderData: {
      }
    }
  },
  computed: {
    // TODO:デモ用の使いまわしのロジックのため本番実装では修正必須
    isFontColor: function() {
      return function(i) {
        if (this.tableData[i].tradeKubun === '売') {
          return false
        } else {
          return true
        }
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return this.form.accountType === '0' && this.hasJrNisaAccount
        ? this.customerInfo.jrNisaAccountNumber
        : this.customerInfo.accountNumber
    },
    hasJrNisaAccount() {
      return this.customerInfo.jrNisaAccountNumber !== ''
    }
  },
  methods: {
    cancelDialogOpen(i) {
      this.orderData = JSON.parse(JSON.stringify(this.tableData[i]))
      this.cancelDialogVisible = true
    },
    correctDialogOpen(i) {
      this.orderData = JSON.parse(JSON.stringify(this.tableData[i]))
      this.form = JSON.parse(JSON.stringify(this.tableData[i]))
      this.correctDialogVisible = true
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/orderStatusList.scss';
</style>
