<template>
  <div>
    <el-row>
      <el-table
        v-if="tableData.length > 0"
        :data="tableData"
        :cell-class-name="tableCellClassName"
        style="width: 780px;font-size:12px;box-shadow: 1px 2px 2px #ddd;"
        :class="tableData && tableData.length === 1 ? 'only-row' : ''"
      >
        <el-table-column
          label="建市場"
          :min-width="60"
        >
          <template #default="scope">
            <span>{{ $_out($_getCodeValue('NEW_MARKET', 1, scope.row.builtMarket)) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="新規建日"
          :min-width="70"
        >
          <template #default="scope">
            <span>{{ scope.row.constructionDate ? $_getFormattedDateValue(scope.row.constructionDate) : '----/--/--' }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="親株新規約定日"
          :min-width="70"
        >
          <template #default="scope">
            <span>{{ scope.row.parentStockTradeDate ? $_getFormattedDateValue(scope.row.parentStockTradeDate) : '----/--/--' }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="建単価"
          :min-width="90"
        >
          <template #default="scope">
            <span>{{ $_out($_withCommaNoneZeroPadding(scope.row.newPrice)) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="注文株数"
          :min-width="90"
        >
          <template #default="scope">
            <span>{{ $_out($_withCommaInteger(scope.row.orderQuantity)) }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="評価損益（リアル）"
          :min-width="100"
        >
          <template #default="scope">
            <div
              v-if="scope.row.profitAndLossReal"
              :class="scope.row.profitAndLossReal < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
              disable-transitions
            >{{ $_signedWithCommaInteger(scope.row.profitAndLossReal) }}
            </div>
            <div v-else>
              -
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

  </div>
</template>

<script>
export default {
  props: {
    tableData: { type: Array, required: true }
  },
  methods: {
    tableCellClassName({ row, column, rowIndex, columnIndex }) {
      // 建市場
      if (columnIndex === 0) {
        return '__center'
      // 新規建日
      } else if (columnIndex === 1) {
        return '__center'
      // 親株新規約定日
      } else if (columnIndex === 2) {
        return '__center'
      // 新規単価
      } else if (columnIndex === 3) {
        return '__right'
      // 注文株数
      } else if (columnIndex === 4) {
        return '__right'
      // 評価損益（リアル）
      } else if (columnIndex === 5) {
        return '__right'
      // 建玉NO
      } else if (columnIndex === 6) {
        return '__center'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.__center {
  text-align: center;
}
.__left {
  text-align: left;
}
.__right {
  text-align: right;
}
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-table) th {
  text-align: center;
  font-size: 12px;
  border: 1px solid #eee;
}
:deep(.el-table) td {
  padding: 6px 0 6px 0;
  border-collapse: collapse;
  border: 1px solid #eee;
}
.only-row {
  :deep(.el-scrollbar__wrap){
    height: 16px;
  }
  :deep(.el-scrollbar__view) {
    display: block !important;
    vertical-align: top !important;
  }
}
</style>
