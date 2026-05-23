<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1100px"
    style="overflow-x: auto;"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="onShow"
  >

    <!-- 閉じるボタン -->
    <el-row>
      <el-col
        :span="24"
        style="text-align: right;"
      >
        <ifa-button
          id="btnClose"
          text="閉じる"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- 顧客情報/口座情報 -->
    <el-row style="font-weight: bold;">
      <el-col :span="24">
        <span>{{ $_out(form.accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="font-size: 20px;"
    >
      <el-col :span="24">
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo.corporationType == '1'"><OfficeBuilding></OfficeBuilding></el-icon>
          <el-icon v-else><Avatar></Avatar></el-icon>
        </span>
        <span>
          {{ $_out(form.customerName) }}
        </span>
      </el-col>
    </el-row>

    <!-- 分類 -->
    <el-row
      v-if="form.searchResultCount > 0"
      style="margin: 1rem 0 0.5rem 0;"
    >
      <el-col :span="8">
        <span class="_bold_black_m"> {{ $_out(form.classificationDisplay) }}</span>
      </el-col>
    </el-row>

    <!-- 更新日時 -->
    <el-row>
      <el-col
        v-if="form.searchResultCount > 0"
        :span="8"
        :offset="16"
        style="text-align: right;"
      >
        更新日時：{{ $_out($_getFormattedDateTimeValue($store.getters.requestedTime)) }}
      </el-col>
    </el-row>

    <!-- 精算金額合計 -->
    <el-row v-if="form.searchResultCount > 0">
      <el-col
        :span="8"
        :offset="16"
      >
        <table class="_table__horizontal _table__body">
          <tbody>
            <tr>
              <th
                class="_table__header __center"
                style="width: 50%;"
              >精算金額合計</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.sumSettlementAmount)) }}</td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <!-- 一覧表 -->
    <el-row v-if="form.searchResultCount > 0">
      <table class="_table__horizontal _table__body">
        <thead>
          <tr>
            <th
              class="_table__header __center"
              style="min-width: 4rem;"
            >項番</th>
            <th
              class="_table__header __center"
              style="min-width: 7rem;"
            >受渡日</th>
            <th
              class="_table__header __center"
              style="min-width: 7rem;"
            >約定日</th>
            <th
              class="_table__header __center"
              style="min-width: 4rem;"
            >取引</th>
            <th
              class="_table__header __center"
              style="min-width: 7rem;"
            >摘要</th>
            <th
              class="_table__header __center"
              style="min-width: 4rem;"
            >数量</th>
            <th
              class="_table__header __center"
              style="min-width: 7rem;"
            >単価</th>
            <th
              class="_table__header __center"
              style="min-width: 7rem;"
            >精算金額</th>
          </tr>
        </thead>
        <tbody class="__colored">
          <tr
            v-for="(data, index) in form.unsettleDetail"
            :key="index"
          >
            <td class="_table__data __center">{{ $_out(data.kouban) }}</td>
            <td class="_table__data __center">{{ $_out($_getFormattedDateValue(data.settlementDate)) }}</td>
            <td class="_table__data __center">{{ data.tradeDate === ' ' ? ' ' : $_out($_getFormattedDateValue(data.tradeDate)) }}</td>
            <td
              style="min-width: 75px;"
              class="_table__data __center"
            >
              {{ $_out($_getCodeValue('TRAD', 1, data.openTradeKbn)) }}
            </td>
            <td
              class="_table__data __left"
              style="word-break: break-word;"
            >
              {{ $_out(data.dispAbstract) }}
            </td>
            <td class="_table__data __right">{{ $_out(data.quantity) }}</td>
            <td class="_table__data __right">{{ $_out(data.price) }}</td>
            <td class="_table__data __right">
              <span :class="data.settlementAmount >= 0 ? '' : 'font-color__minus bold'">
                {{ $_out($_withCommaInteger(data.settlementAmount)) }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </el-row>
    <el-row
      v-else
      style="height: 200px; text-align: center; padding: 80px;"
    >
      <el-col>
        <span style="text-align: center;">{{ form.noDetailMsg }}</span>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaUnsettleDetailFormModel } from './js/IfaUnsettleDetailFormModel'

export default {
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true, default: Object }
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
      form: new IfaUnsettleDetailFormModel()
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  },
  methods: {
    onShow() {
      this.form = Object.assign(this.form, this.formData)
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>
._bold_black_m {
  font-size: 16px;
  font-weight: 700;
  color: #606266;
}
.close-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -60px 2rem 0 auto;
}
</style>
