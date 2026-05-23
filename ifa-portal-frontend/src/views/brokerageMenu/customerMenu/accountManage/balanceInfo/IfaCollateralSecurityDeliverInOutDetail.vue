<template>
  <el-dialog
    width="1200px"
    class="__bold"
    :title="form.screenTitle"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    :before-close="onDialogClose"
  >
    <el-row style="margin-top: 1rem">
      <el-row>
        <el-col :span="2"
                style="margin-right: -28px;"
        >
          <span>口座番号：</span>
        </el-col>
        <el-col :span="22">
          <span style="margin-left:0.3rem">{{ customerInfo.butenCode }}-{{ $_zeroPadding(customerInfo.accountNumber) }}&nbsp;&nbsp;&nbsp;&nbsp;{{ customerInfo.customerNameKanji }}&nbsp;({{ customerInfo.customerNameKana }})</span>
        </el-col>
      </el-row>
    </el-row>

    <el-row style="margin-top: 2rem;">
      <el-row>
        <el-col>
          <span>{{ $_out($_getFormattedDateValue(form.settlementDate)) }}</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="5"
          class="__evaluation_price_total_title"
        >
          <span>{{ $_out(form.collateralSecurityValuationPriceName) }}</span>
        </el-col>
        <el-col
          :span="4"
          class="__evaluation_price_total_data"
        >
          <span>{{ $_out($_withCommaInteger(form.collateralValuationTotal)) }}</span>
        </el-col>
      </el-row>
    </el-row>

    <div v-if="form.detailList.length > 0">
      <el-row>
        <el-table
          :data="tableData"
          :max-height="500"
          :highlight-current-row="true"
          :cell-class-name="tableCellClassNameDetail"
          :scrollbar-always-on="true"
          style="width: 100%; margin-top: 1rem;font-size:12px"
        >
          <el-table-column
            prop="securityClass"
            label="商品分類"
            :min-width="100"
          ></el-table-column>
          <el-table-column
            prop="brandCode"
            label="銘柄コード"
            :min-width="80"
          ></el-table-column>
          <el-table-column
            prop="brandName"
            label="銘柄名"
            :min-width="230"
          ></el-table-column>
          <el-table-column
            prop="depositType"
            label="預り区分"
            :min-width="100"
          ></el-table-column>
          <el-table-column
            label="株数/口数"
            :min-width="160"
          >
            <template #default="scope">
              <span>{{ $_withCommaInteger(scope.row.contPosition) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="評価単価"
            :min-width="140"
          >
            <template #default="scope">
              <span>{{ $_withCommaNoneZeroPadding(scope.row.valuationPrice) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="collateralAssessment"
            label="掛目(%)"
            :min-width="70"
          ></el-table-column>
          <el-table-column
            label="代用評価額"
            :min-width="150"
          >
            <template #default="scope">
              <span>{{ $_withCommaInteger(scope.row.collateralValuation) }}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </div>
    <div
      v-else
      style="margin-top:1rem; display: flex; justify-content: center;"
    >
      <span>{{ form.noDetailMsg }}</span>
    </div>
    <ifa-button
      class="form-button__wrapper"
      text="閉じる"
      color="secondary"
      action-type="originalAction"
      @app-action-handler="onDialogClose"
    ></ifa-button>
  </el-dialog>
</template>

<script>
import { IfaCollateralSecurityDeliverInOutDetailFormModel } from './js/IfaCollateralSecurityDeliverInOutDetailFormModel'

export default {
  props: {
    customerInfo: { type: Object, required: true },
    detailParam: { type: Object, required: false, default: () => ({}) }
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaCollateralSecurityDeliverInOutDetailFormModel(),
      tableData: []
    }
  },
  methods: {
    onShow(response) {
      this.initializeA001(response)
    },
    initializeA001(params) {
      Object.assign(this.form, params)
      if (this.form.deliverInOutClassification === '1') {
        this.form.screenTitle = '代用有価証券出庫-個別詳細'
        this.form.collateralSecurityValuationPriceName = '代用有価証券評価額合計(出庫)'
        this.form.noDetailMsg = this.form.displayBaseDate + '基準において、お客様の代用有価証券出庫情報はございません。'
      } else if (this.form.deliverInOutClassification === '2') {
        this.form.screenTitle = '代用有価証券入庫-個別詳細'
        this.form.collateralSecurityValuationPriceName = '代用有価証券評価額合計(入庫)'
        this.form.noDetailMsg = this.form.displayBaseDate + '基準において、お客様の代用有価証券入庫情報はございません。'
      }
      this.setTableData()
    },
    setTableData() {
      this.tableData = []
      this.tableData = this.form.detailList
      this.tableData.forEach(item => {
        item.securityClass = this.$_out(item.securityClass)
        item.brandCode = this.$_out(item.brandCode)
        item.brandName = this.$_out(item.brandName)
        item.depositType = this.$_out(item.depositType)
        item.contPosition = this.$_out(item.contPosition)
        item.valuationPrice = this.$_out(item.valuationPrice)
        item.collateralAssessment = this.$_out(item.collateralAssessment)
        item.collateralValuation = this.$_out(item.collateralValuation)
      })
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    // テーブルスタイル調整
    tableCellClassNameDetail({ row, column, rowIndex, columnIndex }) {
      // 商品分類
      if (columnIndex === 0) {
        return '__center'
      // 銘柄コード
      } else if (columnIndex === 1) {
        return '__left'
      // 銘柄名
      } else if (columnIndex === 2) {
        return '__left'
      // 預り区分
      } else if (columnIndex === 3) {
        return '__center'
      // 株数/口数
      } else if (columnIndex === 4) {
        return '__right'
      // 評価単価
      } else if (columnIndex === 5) {
        return '__right'
      // 掛目(%)
      } else if (columnIndex === 6) {
        return '__right'
      // 代用評価額
      } else if (columnIndex === 7) {
        return '__right'
      }
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
.__evaluation_price_total_title {
  color: #fff;
  background-color: #004485;
  padding: 0.2rem 0 0.2rem 1rem;
  font-weight:bold;
  box-shadow: 1px 2px 2px #ddd;
  border: 1px solid #eee;
  font-size: 12px;
}
.__evaluation_price_total_data {
  text-align: right;
  padding: 0.2rem 1rem 0.2rem 0;
  box-shadow: 1px 2px 2px #ddd;
  border: 1px solid #eee;
  font-size: 12px;
}
.__bold {
  font-weight: bold;
}
:deep(.el-dialog){
  width:1000px;
  height:510px;
}
.form-button__wrapper {
  position: absolute;
  top: 100px;
  right: 30px;
  padding: 0.5rem 0 0.2rem 0;
}
:deep(.el-dialog__title) {
   font-weight: bold;
}
:deep(.el-table) th {
   text-align: center;
   font-size: 14px;
}
</style>
