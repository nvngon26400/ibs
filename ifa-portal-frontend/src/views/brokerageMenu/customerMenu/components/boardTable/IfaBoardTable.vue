<template>
  <el-table
    v-loading="isLoading"
    :data="inputTableData"
    border
    size="small"
    style="font-size:12px;margin-top:1rem;overflow:hidden;"
    :stripe="false"
    :fit="true"
    :row-class-name="tableRowClassName"
    @row-click="handleClick"
  >
    <el-table-column
      label="売気配株数"
      width="200%"
      :resizable="false"
      align="center"
    >
      <template #default="scope">
        <div style="text-align:center">
          <span style="margin-left: 10px;">{{ scope.row.sellSignVolume }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      label=""
      width="40%"
      :resizable="false"
    >
      <template #default="scope">
        <div style="text-align:center;color: #606266;">
          <span style="margin-left: 10px">{{ $_getCodeValue('SEVERAL_SELL_QUOTE_FLAG', 1, scope.row.sellSignPriceFlg) }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      label="気配値"
      width="170%"
      :resizable="false"
      align="center"
    >
      <template #default="scope">
        <div style="text-align:center">
          <span style="margin-left: 10px">
            {{ scope.row.signPrice }}
          </span>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      label=""
      width="40%"
      :resizable="false"
    >
      <template #default="scope">
        <div style="text-align:center;color: #606266;">
          <span style="margin-left: 10px">{{ $_getCodeValue('SEVERAL_BUY_QUOTE_FLAG', 1, scope.row.buySignPriceFlg) }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column
      label="買気配株数"
      width="200%"
      :resizable="false"
      align="center"
    >
      <template #default="scope">
        <div style="text-align:center">
          <span style="margin-left: 10px;">{{ scope.row.buySignVolume }}</span>
        </div>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  props: {
    inputTableData: {
      type: Array,
      required: true,
      default: undefined
    },
    isLoading: {
      type: Boolean,
      required: false,
      default: false
    },
    market: {
      type: String,
      required: true,
      default: ''
    }
  },
  emits: ['select'],
  data() {
    return {
      tableData: this.inputTableData
    }
  },
  methods: {
    handleClick(val) {
      this.$emit('select', val.price)
    },
    tableRowClassName({ row, rowIndex }) {
      if (this.market === '7') {
        if (rowIndex === 4) {
          return '.clickable lowest-buy-row'
        } else if (rowIndex === 5) {
          return '.clickable highest-sell-row'
        }
      } else {
        if (rowIndex === 11) {
          return '.clickable lowest-buy-row'
        } else if (rowIndex === 12) {
          return '.clickable highest-sell-row'
        }
      }
      return 'clickable'
    }
  }
}
</script>

<style>
.el-table .highest-sell-row {
  color: #E5004C;
}
.el-table .lowest-buy-row {
  color: #00847F;
}
.clickable:hover {
  cursor: pointer
}
.header-row .th {
  text-align: center;
}
</style>

<style scoped>
:deep(.el-table__body),
:deep(.el-table__header) {
  width: auto !important;
}
</style>
