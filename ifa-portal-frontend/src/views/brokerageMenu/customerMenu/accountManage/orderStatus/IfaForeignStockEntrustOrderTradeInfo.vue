<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      style="width: 640px; max-width: 100%; overflow: auto;"
      :center="true"
      :show-close="false"
      :title="form.title.name"
      :before-close="handleMoveToUnexecutedOrderList"
      :close-on-click-modal="false"
      width="640px"
      class="foreign_stock_entrust_order_trade_info_class"
      @open="onShow"
    >
      <div>
        <!-- 顧客情報 -->
        <el-row style="font-weight: bold;">
          <el-col :offset="1">
            <span>{{ accountNumber }}</span>
          </el-col>
        </el-row>
        <el-row
          class="_bold_black_l"
          style="padding-top: 0.5rem; font-size: 20px;"
        >
          <el-col
            :offset="1"
            style="padding-right: 25px;"
          >
            <span style="position: relative; top: 3px;">
              <el-icon v-if="customerInfo.corporationType == '1'"><OfficeBuilding></OfficeBuilding></el-icon>
              <el-icon v-else><Avatar></Avatar></el-icon>
            </span>
            <span>{{ customerName }}</span>
          </el-col>
        </el-row>
        <!-- 委託注文約定情報一覧 -->
        <el-row
          v-if="form.entrustOrderTradeinfoList.length > 0"
          style="margin-top: 0.5rem;"
        >
          <el-col
            :offset="1"
            :span="22"
          >
            <el-table
              :data="form.entrustOrderTradeinfoList"
              :class="form.entrustOrderTradeinfoList.length > 10 ? 'table_height--fix' : 'table_height--auto'"
              :scrollbar-always-on="true"
            >
              <el-table-column
                label="約定日時"
                width="180"
              >
                <template #default="scope">
                  <span>{{ $_out($_getFormattedDateTimeValue(scope.row.tradeDateTime, 'datetime14')) }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="約定数量"
                align="right"
                width="140"
              >
                <template #default="scope">
                  <span>{{ $_out($_withCommaInteger(scope.row.tradeQuantity)) }}{{ scope.row.tradeQuantity ? '株' : '' }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="約定単価"
                align="right"
                width="220"
              >
                <template #default="scope">
                  <span>{{ $_out($_withCommaZeroPadding(scope.row.tradePrice, 4)) }} {{ scope.row.tradePriceCurrencyCode }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <el-row
          v-else
          class="caption_card __center"
          style="justify-content: center; margin: 2rem 0;"
        >
          <el-col :offset="1">
            {{ form.noDetailMsg }}
          </el-col>
        </el-row>
        <el-row style="margin-top: 2rem;">
          <el-col
            :offset="1"
            :span="22"
            style="text-align: left;"
          >
            <ifa-button
              text="注文状況一覧へ"
              color="primary"
              style="padding-left: 0;"
              action-type="originalAction"
              @app-action-handler="handleMoveToUnexecutedOrderList"
            ></ifa-button>
          </el-col>
        </el-row>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaForeignStockEntrustOrderTradeInfoFormModel } from './js/IfaForeignStockEntrustOrderTradeInfoFormModel'
export default {
  components: {
  },
  props: {
    isVisible: { type: Boolean, required: true },
    infoData: { type: Object, required: true }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignStockEntrustOrderTradeInfoFormModel()
    }
  },
  computed: {
    customerName() {
      return this.$_out(this.form.customerNameKanji) + '（' + this.$_out(this.form.customerNameKana) + '）'
    },
    accountNumber() {
      const fomarttedAccountNumber = this.form.accountNumber ? this.$_zeroPadding(this.form.accountNumber, 7) : '-'
      return this.$_out(this.form.butenCode) + '-' + fomarttedAccountNumber
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.infoData)
    },
    // 注文状況一覧へボタン
    handleMoveToUnexecutedOrderList() {
      this.$emit('close-modal')
    }
  }
}
</script>
<style lang="scss">
.foreign_stock_entrust_order_trade_info_class {
  &.el-dialog {
    width: 640px !important;
    min-width: 640px !important;
  }
}
</style>

<style lang="scss"  scoped>
@import "@/styles/variables.scss";
.table_height--fix {
  height: 220px;
}
.table_height--auto {
  height: auto;
}
:deep {
  .el-table {
   --el-table-border-color: #a7b1c3;
   --el-table-border: 1px solid;
   box-shadow: 1px 2px 2px #ddd;
 }
 .el-table .cell{
  padding: 0;
 }
  .el-table th, .el-table th.el-table__cell {
  color: #18181a;
  background-color: #E6E8F0;
  padding: 2px 14px 2px 14px;
  text-align: center;
 }
 .el-table td, .el-table td.el-table__cell {
  color: #18181a;
  padding: 2px 14px 2px 14px;
 }
 .el-table:not(.el-table--border) .el-table__cell{
  border: 1px solid #a7b1c3;
 }
 th.el-table__cell:not(:last-child),
 td.el-table__cell:not(:last-child){
  border-right: none !important;
  border-bottom: none !important;
 }
 th.el-table__cell:last-child,
 td.el-table__cell:last-child{
  border-bottom: none !important;
 }
}
</style>
