<template>
  <div id="ifaShortPositionDividendAdjustDetailDialogWrapper">
    <el-dialog
      v-model="$props.isVisible"
      width="1250px"
      :title="form.title.name"
      :center="true"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
    >

      <div style="margin-bottom: -0.5rem">口座番号：{{ $_out(accountNumber) }}</div>
      <el-row
        v-if="Number(form.searchResultCount) <= 0"
        class="no-data-content"
      >
        売建配当調整金明細はありません。
      </el-row>
      <el-row v-else>
        <table
          class="_table__horizontal _table__body _table__body__margin_irregular_format"
          style="margin: 0.5rem 0;"
        >
          <tbody>
            <tr>
              <th
                class="_table__header __center __add_top__border __add_left__border"
                style="width: 15%;"
              >銘柄コード</th>
              <th
                class="_table__header __center __add_top__border"
                style="width: 25%;"
              >銘柄名</th>
              <th
                class="_table__header __center __add_top__border"
                style="width: 15%;"
              >予想配当</th>
              <th
                class="_table__header __center __add_top__border"
                style="width: 15%;"
              >株数</th>
              <th
                class="_table__header __center __add_top__border"
                style="width: 15%;"
              >拘束金</th>
              <th
                class="_table__header __center __add_top__border __add_right__border"
                style="width: 15%;"
              >権利付最終日</th>
            </tr>
            <tr
              v-for="item in form.detailList"
              :key="item.index"
            >
              <td class="_table__data __right __add_left__border">{{ item.brandCode }}</td>
              <td class="_table__data __left">{{ item.brandName }}</td>
              <td class="_table__data __right">{{ item.expectedDividend }}</td>
              <td class="_table__data __right">{{ $_withCommaInteger(item.stockQuantity) }}</td>
              <td class="_table__data __right">{{ $_withCommaInteger(item.restraintAmount) }}</td>
              <td class="_table__data __right __add_right__border">{{ $_getFormattedDateValue(item.rightLastDate) }}</td>
            </tr>
          </tbody>
        </table>
      </el-row>
      <!-- 戻るボタン -->
      <el-row
        style="height: 75px;"
      >
        <ifa-button
          id="btnClose"
          class="close-button__wrapper"
          text="閉じる"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { IfaShortPositionDividendAdjustDetailFormModel } from '@/views/brokerageMenu/customerMenu/accountManage/buyingPowerInfo/js/IfaShortPositionDividendAdjustDetailFormModel.js'

export default {
  props: {
    formData: { type: Object, default: null },
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaShortPositionDividendAdjustDetailFormModel()
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "@/styles/table.scss";
._table__header {
  padding: 5px;
}
._table__body__margin_irregular_format {
  width: 100%;
  border-collapse: collapse;
}
.__add_left__border {
  border-left: 3px solid #eee;
}
.__add_right__border {
  border-right: 3px solid #eee;
}
.__add_top__border {
  border-top: 3px solid #eee;
}
.close-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: 30px 2rem 30px auto;
}
.no-data-content {
  width: 100%;
  height: 100px;
  text-align: center;
  line-height: 100px;
  display: block;
}
#ifaShortPositionDividendAdjustDetailDialogWrapper :deep(.el-dialog__header) {
  margin: 2rem 0 -1rem 0;

  .el-dialog__title {
    font-size: 18px;
    font-weight: bold;
  }
}
</style>

