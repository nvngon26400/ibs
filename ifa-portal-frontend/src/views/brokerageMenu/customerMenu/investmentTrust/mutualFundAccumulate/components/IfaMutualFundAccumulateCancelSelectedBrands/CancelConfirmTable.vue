<template>
  <el-table
    row-key="id"
    border
    stripe
    style="font-size: 12px;"
    :data="confirmInfo.settingCancelConfirmList"
  >
    <el-table-column
      header-align="center"
      align="center"
      class-name="cell-item"
    >
      <template #header>
        <div style="display: flex; flex-direction: column; row-gap: 5px;">
          <div>協会コード</div>
          <div>銘柄コード</div>
        </div>
      </template>
      <template #default="scope">
        <div class="double-cell-wrapper">
          <div class="double-cell-item">
            {{ scope.row.fundCode }}
          </div>
          <div class="cell-divider"></div>
          <div class="double-cell-item">
            {{ `${scope.row.mfkaisu}.${scope.row.mfgo}` }}
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      prop="fundName"
      label="銘柄名"
      header-align="center"
      :width="200"
    ></el-table-column>
    <el-table-column
      prop="accountTypeName"
      label="預り区分"
      header-align="center"
      align="center"
    ></el-table-column>
    <el-table-column
      prop="paymentMethodName"
      label="決済方法"
      header-align="center"
      align="center"
    ></el-table-column>
    <el-table-column
      header-align="center"
      align="center"
      class-name="cell-item"
    >
      <template #header>
        <div style="display: flex; flex-direction: column; row-gap: 5px;">
          <div>積立コース</div>
          <div>設定金額</div>
        </div>
      </template>
      <template #default="scope">
        <div class="double-cell-wrapper">
          <div class="double-cell-item">
            {{ scope.row.courseType }}
          </div>
          <div class="cell-divider"></div>
          <div class="double-cell-item">
            {{ formatYen(scope.row.settingAmount) }}
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      header-align="center"
      align="center"
    >
      <template #header>
        <div>ボーナス月の設定</div>
      </template>
      <template #default="scope">
        <div v-if="scope.row.settingBonusMonthDay === '-'">
          <span>-</span>
        </div>
        <div
          v-else
          style="display: flex; flex-direction: column; row-gap: 5px;"
        >
          <div>{{ formatYen(scope.row.settingBonusAmount) }}</div>
          <div>{{ scope.row.settingBonusMonthDay }}</div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      header-align="center"
      align="center"
      class-name="cell-item"
    >
      <template #header>
        <div style="display: flex; flex-direction: column; row-gap: 5px;">
          <div>NISA枠ぎりぎり注文</div>
          <div>課税枠シフト注文</div>
        </div>
      </template>
      <template #default="scope">
        <div class="double-cell-wrapper">
          <div class="double-cell-item">
            {{ scope.row.nisaBarelyBuyingType ?? '-' }}
          </div>
          <div class="cell-divider"></div>
          <div class="double-cell-item">
            {{ scope.row.taxShiftType ?? '-' }}
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      prop="oneMonthSumAmount"
      label="1ヵ月あたりの積立概算"
      header-align="center"
      align="center"
    >
      <template #default="scope">
        <div>{{ formatYen(scope.row.oneMonthSumAmount) }}</div>
      </template>
    </el-table-column>
    <el-table-column
      prop="nextReserveDate"
      label="次回発注予定日"
      header-align="center"
      align="center"
    >
      <template #default="scope">
        <div>{{ scope.row.nextReserveDate ?? '-' }}</div>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import IfaUtils from '@/utils/ifaUtils'

export default {
  props: {
    confirmInfo: { type: Object, required: true }
  },
  methods: {
    formatYen(val) {
      if (!val) return '-'
      return `${IfaUtils.addComma(val)}${' '}円`
    }
  }
}
</script>

<style scoped lang="scss">
  :deep(.cell-item .cell) {
    padding: 0;
    height: 100%;
  }

  .double-cell-wrapper {
    display: flex;
    flex-direction: column;
    row-gap: 5px;
    height: 100%;
    justify-content: center;
  }

  .double-cell-item {
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: center;
  }

  .cell-divider {
    border-top:1px solid #ccc;
    margin: 2px 0;
  }
</style>
