<template>
  <div>
    <ifa-requester
      id="IfaForeignPositionListA003"
      action-id="SUB07-06#X001"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseHandlerA003"
    ></ifa-requester>
    <ifa-requester
      id="IfaForeignPositionListPositionRepaymentA002"
      action-id="SUB0202_010203-01#A002"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="responsePositionRepaymentA002($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaForeignMarginTradeRepayOrderInputInitializeA001"
      action-id="SUB0202_0303-04_1#A001"
      action-type="requestAction"
      :request-model="A002RequestModel"
      @response-handler="responseRepayOrderInputInitializeA001($event)"
    ></ifa-requester>
    <screen-title :text="form.screenTitle"></screen-title>
    <el-row>
      <el-col
        class="__right"
        style="padding-right: 12px;"
      >
        <ifa-button
          id="btnUpdateIfaForeignPositionList"
          text="更新"
          icon="RefreshRight"
          small
          :parent-ref="$refs"
          :request-model="A001RequestModel"
          action-id="SUB0202_010203-01#A001"
          action-type="requestAction"
          @response-handler="responseHandlerA001($event)"
          @response-error-handler="errorHandlerInitializeA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
    <div
      v-if="!form.positionListList.length > 0"
      class="caption_card"
      style="text-align: center;"
    >
      <div style="margin: 0 0 2rem 0">建玉明細はありません。</div>
    </div>
    <div
      v-else
      class="caption_card"
    >
      <el-row style="margin-top: 0.5rem;">
        <el-table
          :data="form.positionListList"
          :span-method="objectSpanMethod"
          :cell-class-name="tableCellClassName"
          style="width: 100%; margin-top: 1rem;font-size:12px;"
        >
          <el-table-column :width="100">
            <template #header>
              <div>ティッカー</div>
            </template>
            <template #default="scope">
              {{ $_out(scope.row.brandCode) }}
            </template>
          </el-table-column>
          <el-table-column :min-width="120">
            <template #header>
              <div>銘柄名</div>
            </template>
            <template #default="scope">
              {{ $_out(scope.row.brandName) }}
            </template>
          </el-table-column>
          <el-table-column :min-width="80">
            <template #header>
              <div>市場</div>
            </template>
            <template #default="scope">
              {{ $_out(scope.row.marketAbbreviatedName) }}
            </template>
          </el-table-column>
          <el-table-column
            :width="80"
          >
            <template #header>
              <div>建区分</div>
              <div>期限</div>
            </template>
            <template #default="scope">
              <div
                disable-transitions
                style="font-weight: bold;"
              >
                <ifa-text
                  :style="scope.row.tradeKbn === '0' ? 'color: #E5004C; font-size: 12px;' : 'color: #00847F; font-size: 12px;'"
                  code-list-id="POSITION_SELL_BUY_TYPE"
                  disp-pattern="1"
                  :code-key="scope.row.tradeKbn"
                ></ifa-text>
                <span v-if="!scope.row.tradeKbn">-</span>
                <div
                  disable-transitions
                >
                  <ifa-text
                    :style="'color: black; font-weight: normal; font-size: 12px;'"
                    code-list-id="MARGIN_DUE_DATE"
                    disp-pattern="1"
                    :code-key="scope.row.marginDueDate"
                  ></ifa-text>
                  <span v-if="!scope.row.marginDueDate">-</span>
                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column :width="110">
            <template #header>
              <div>新規建日</div>
              <div>(返済期限)</div>
            </template>
            <template #default="scope">
              <div>
                <div>{{ $_out(scope.row.openTradeDate) }}</div>
                <div>{{ scope.row.lastTradeDate ? scope.row.lastTradeDate : '(----/--/--)' }}</div>
              </div>
            </template>
          </el-table-column>
          <el-table-column :min-width="85">
            <template #header>
              <div>預り区分</div>
              <div>担保</div>
            </template>
            <template #default="scope">
              <ifa-text
                style="font-size: 12px;"
                code-list-id="FOREIGN_DEPOSIT_TYPE"
                disp-pattern="1"
                :code-key="scope.row.depositType"
              ></ifa-text>
              <span v-if="!scope.row.depositType">-</span>
              <div>{{ scope.row.positionNecessaryDepositRate ? `${ifaFormatUtils.withCommaNoneZeroPadding(scope.row.positionNecessaryDepositRate)}%` : '-' }}
                <span v-if="scope.row.additionalSecurityRegulationPositionFlag === '1'">(
                  <ifa-text
                    style="font-size: 12px;"
                    code-list-id="ADDITIONAL_COLLATERAL"
                    disp-pattern="1"
                    :code-key="scope.row.additionalSecurityRegulationPositionFlag"
                  ></ifa-text>
                  )</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column :min-width="75">
            <template #header>
              <div>建株数</div>
              <div>(注文中)</div>
            </template>
            <template #default="scope">
              <div>{{ scope.row.positionRemainingQuantity ? `${ifaFormatUtils.withCommaInteger(scope.row.positionRemainingQuantity)} 株` : '-' }}</div>
              <div>({{ scope.row.unactualQuantity ? `${ifaFormatUtils.withCommaInteger(scope.row.unactualQuantity)} 株` :'-' }})</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="100">
            <template #header>
              <div>建単価</div>
              <div>現在値</div>
            </template>
            <template #default="scope">
              <div>{{ scope.row.newPositionPriceForeign ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.newPositionPriceForeign, 2)} USD` : '-' }}</div>
              <div>{{ scope.row.currentPriceOrPreviousDayEndPrice ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.currentPriceOrPreviousDayEndPrice, 2)} USD` : '-' }}</div>
            </template>
          </el-table-column>
          <el-table-column :min-width="110">
            <template #header>
              <div>建代金</div>
              <div>諸経費等</div>
            </template>
            <template #default="scope">
              <div>{{ scope.row.foreignNewPositionAmount ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.foreignNewPositionAmount, 2)} USD` : '-' }}</div>
              <div>
                <el-link
                  :underline="false"
                  class="cost-link"
                  disable-transitions
                  style="text-decoration:underline; text-underline-offset:0.1em; font-size:12px;"
                  @click="positionDetailA003(scope.row)"
                >{{ scope.row.expensesTotalAmountForeign ? ifaFormatUtils.withCommaZeroPadding(scope.row.expensesTotalAmountForeign, 2) + ' USD' : '-' }}</el-link>
              </div>
            </template>

          </el-table-column>
          <el-table-column :min-width="100">
            <template #header>
              <div>評価損益</div>
              <div>評価割合</div>
            </template>
            <template #default="scope">
              <div
                :class="scope.row.customerListProfitAndLossForeign === '' ? 'black-normal' : scope.row.customerListProfitAndLossForeign < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                disable-transitions
              >{{ scope.row.customerListProfitAndLossForeign ? (scope.row.customerListProfitAndLossForeign > 0 ? '+': '') + ifaFormatUtils.withCommaZeroPadding(scope.row.customerListProfitAndLossForeign, 2) + 'USD' : '-' }}</div>
              <div
                :class="scope.row.valuationRate < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                disable-transitions
              >({{ scope.row.valuationRate ? (scope.row.valuationRate > 0 ? '+': '') + ifaFormatUtils.withCommaZeroPadding(scope.row.valuationRate, 2) + '%' : '-' }})</div>
            </template>
          </el-table-column>
          <el-table-column
            label="取引"
            :width="110"
          >
            <template #default="scope">
              <ifa-button
                v-if="scope.row.repaySellButton === '1' && getIntermediaryValue('5') !== '0' && form.tradeSuspendFlag !== '1'"
                id="btnSell"
                :color="'sell'"
                small
                :text="'返済売'"
                :disabled="scope.row.tradeButtonDeactivation === '1' ? true : false"
                action-type="originalAction"
                @app-action-handler="positionRepaymentA002(scope.row)"
              ></ifa-button>
              <ifa-button
                v-if="scope.row.repayBuyButton === '1' && getIntermediaryValue('4') !== '0' && form.tradeSuspendFlag !== '1'"
                id="btnBuy"
                :color="'buy'"
                small
                :text="'返済買'"
                :disabled="scope.row.tradeButtonDeactivation === '1' ? true : false"
                action-type="originalAction"
                @app-action-handler="positionRepaymentA002(scope.row)"
              ></ifa-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>

      <!-- 信用建玉詳細 -->
      <ifa-foreign-position-detail
        :is-visible="dialogVisible"
        :form-data="foreignPositionDetailModel"
        @close-modal="handleCloseModal"
      ></ifa-foreign-position-detail>

    </div>
  </div>
</template>

<script>
const offset = []
let openInterestGroup = []
let dealOffset = 0
const colored = []
const coloredRowIndex = []
import IfaForeignPositionDetail from '@/views/common/IfaForeignPositionDetail'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaForeignPositionListA001RequestModel } from './js/IfaForeignPositionListA001RequestModel'
import { IfaForeignPositionListA002RequestModel } from './js/IfaForeignPositionListA002RequestModel'
import { IfaForeignPositionListA003RequestModel } from './js/IfaForeignPositionListA003RequestModel'
import { IfaForeignPositionListFormModel } from './js/IfaForeignPositionListFormModel'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'

export default {
  components: {
    IfaForeignPositionDetail,
    screenTitle
  },
  props: {
    entryOfCreditRepaymentOrder: { type: Boolean, required: false, default: false },
    entryOfCurrentDeliveryOrder: { type: Boolean, required: false, default: false }
  },
  emits: ['deal-link-clicked-process', 'show-foreign-stock-credit-repay-new', 'initializeError'],
  data() {
    return {
      form: new IfaForeignPositionListFormModel(),
      ifaFormatUtils: ifaFormatUtils,
      dialogVisible: false,
      dialogBrandDetailVisible: false,
      dialogTitle: '建玉詳細（個別表示）',
      dialogBrandDetailTitle: '銘柄別建玉一覧',
      A002RequestModel: {},
      A003RequestModel: {},
      foreignPositionDetailModel: {}
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A001RequestModel() {
      return new IfaForeignPositionListA001RequestModel(this.customerInfo)
    }
  },
  methods: {
    onShow() {
      document.getElementById('btnUpdateIfaForeignPositionList').click()
    },
    positionRepaymentA002(row) {
      this.A002RequestModel = new IfaForeignPositionListA002RequestModel(row)
      this.$nextTick(() => {
        document.getElementById('IfaForeignPositionListPositionRepaymentA002').click()
      })
    },
    responsePositionRepaymentA002() {
      document.getElementById('IfaForeignMarginTradeRepayOrderInputInitializeA001').click()
    },
    responseRepayOrderInputInitializeA001(response) {
      this.$_startShowMenu('SUB0202_0303', response.dataList[0])
    },
    // 戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
    },
    // テーブル整形処理
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // コード
      if (columnIndex === 0 && rowIndex < this.form.positionListList.length) {
        return this.objectSpanMethodCodeName(row, column, rowIndex, columnIndex)
      // 銘柄
      } else if (columnIndex === 1 && rowIndex < this.form.positionListList.length) {
        return this.objectSpanMethodCodeName(row, column, rowIndex, columnIndex)
      // 市場
      } else if (columnIndex === 2 && rowIndex < this.form.positionListList.length) {
        return this.objectSpanMethodCodeName(row, column, rowIndex, columnIndex)
      // 取引
      } else if (columnIndex === 10 && rowIndex < this.form.positionListList.length) {
        return this.objectSpanMethodDeal(row, column, rowIndex, columnIndex)
      }
    },
    // テーブル整形処理(コード 銘柄)
    objectSpanMethodCodeName(row, column, rowIndex, columnIndex) {
      let count = 1

      if (rowIndex === 0) {
        offset[columnIndex] = 0
        colored[columnIndex] = false
        coloredRowIndex[columnIndex] = []
      }
      // 同名要素数の確認
      if (rowIndex >= offset[columnIndex]) {
        for (let i = rowIndex; i < this.form.positionListList.length - 1; i++) {
          // 次の行と同じ値なら結合対象と判断。異なる値が出てくるまでループ
          // 範囲外の index を参照しないようにするため、最後から2番目の要素までチェック
          if (this.form.positionListList[i].brandCode === this.form.positionListList[i + 1].brandCode) {
            count++
            if (colored[columnIndex]) {
              if (!coloredRowIndex[columnIndex].includes(i)) coloredRowIndex[columnIndex].push(i)
              if (!coloredRowIndex[columnIndex].includes(i + 1)) coloredRowIndex[columnIndex].push(i + 1)
            }
          } else {
            // 現在行と異なる値が確認できたときの処理
            // セルの結合と結合幅を変数(offset)に格納
            // offset 未満の index を持つ行処理を次回以降SKIP
            offset[columnIndex] += count
            // ボーダー色セットフラグを反転してセット
            colored[columnIndex] = !colored[columnIndex]
            return {
              rowspan: count,
              colspan: 1
            }
          }
        }

        // 最終行の処理
        offset[columnIndex] += count
        return {
          rowspan: count++,
          colspan: 1
        }
      } else {
        // すでに結合対象となっているセル
        return {
          rowspan: 0,
          colspan: 0
        }
      }
    },
    // テーブル整形処理（取引）
    objectSpanMethodDeal(row, column, rowIndex, columnIndex) {
      let count = 1

      if (rowIndex === 0) {
        dealOffset = 0
        openInterestGroup = []
      }
      // 同名要素数の確認
      if (rowIndex >= dealOffset) {
        openInterestGroup[rowIndex] = [rowIndex]

        // 次の行と同じ値なら結合対象と判断。異なる値が出てくるまでループ
        // 範囲外の index を参照しないようにするため、最後から2番目の要素までチェック
        for (let i = rowIndex; i < this.form.positionListList.length - 1; i++) {
          if (!openInterestGroup[rowIndex].includes(i)) {
            openInterestGroup[rowIndex].push(i)
          }

          if ((this.form.positionListList[i].brandName === this.form.positionListList[i + 1].brandName) &&
            (this.form.positionListList[i].brandCode === this.form.positionListList[i + 1].brandCode) &&
            (this.form.positionListList[i].marketAbbreviatedName === this.form.positionListList[i + 1].marketAbbreviatedName) &&
            (this.form.positionListList[i].tradeKbn === this.form.positionListList[i + 1].tradeKbn) &&
            (this.form.positionListList[i].marginDueDate === this.form.positionListList[i + 1].marginDueDate) &&
            (this.form.positionListList[i].repaySellButton === this.form.positionListList[i + 1].repaySellButton)) {
            if (!openInterestGroup[rowIndex].includes(i + 1)) {
              openInterestGroup[rowIndex].push(i + 1)
            }
            count++
          } else {
            // 現在行と異なる値が確認できたときの処理
            // セルの結合と結合幅を変数(offset)に格納
            // offset 未満の index を持つ行処理を次回以降SKIP
            dealOffset += count
            return {
              rowspan: count,
              colspan: 1
            }
          }
        }

        // 最終行の処理
        dealOffset += count
        return {
          rowspan: count++,
          colspan: 1
        }
      } else {
        // すでに結合対象となっているセル
        return {
          rowspan: 0,
          colspan: 0
        }
      }
    },
    tableCellClassName({ row, column, rowIndex, columnIndex }) {
      let rowStyle = ''
      if (coloredRowIndex.includes(rowIndex)) {
        rowStyle = '__colored '
      }
      // ティッカー/銘柄コード
      if (columnIndex === 0) {
        return rowStyle + '__left'
      // 銘柄名
      } else if (columnIndex === 1) {
        return rowStyle + '__left'
      // 市場
      } else if (columnIndex === 2) {
        return rowStyle + '__left'
      // 建区分,期限
      } else if (columnIndex === 3) {
        return rowStyle + '__center'
      // 建日（返済期限）
      } else if (columnIndex === 4) {
        return rowStyle + '__center'
      // 預り区分(担保)
      } else if (columnIndex === 5) {
        return rowStyle + '__center'
      // 建株数(注文中)
      } else if (columnIndex === 6) {
        return rowStyle + '__right'
      // 建単価 現在値
      } else if (columnIndex === 7) {
        return rowStyle + '__right __bold'
      // 建代金 諸経費等合計
      } else if (columnIndex === 8) {
        return rowStyle + '__right'
      // 評価損益 評価割合
      } else if (columnIndex === 9) {
        return rowStyle + '__right'
      // 取引
      } else if (columnIndex === 10) {
        return rowStyle + '__right'
      }
    },
    // 更新ボタン
    responseHandlerA001: function(data) {
      Object.assign(this.form, data.dataList[0])
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    positionDetailA003(row) {
      this.$_logDebug(row)
      Object.assign(this.A003RequestModel, new IfaForeignPositionListA003RequestModel({
        ...row,
        butenCode: this.$store.getters.customerInfo.butenCode,
        accountNumber: this.$store.getters.customerInfo.accountNumber
      }))
      this.$nextTick(() => {
        document.getElementById('IfaForeignPositionListA003').click()
      })
    },
    responseHandlerA003(response) {
      this.foreignPositionDetailModel = Object.assign(this.foreignPositionDetailModel, response.dataList[0])
      this.dialogVisible = true
    },
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    getIntermediaryValue(tradeClass) {
      return this.form.intermediaryValueList.find(element => element.tradeClass === tradeClass)?.intermediaryValue
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.form-button__wrapper {
   display: flex;
   justify-content: flex-end;
   padding:0 2rem 0.2rem 0;
}
._bold_black_m {
  font-size: 16px;
  font-weight: 700;
  color: #606266;
}
.clickable:hover {
  cursor: pointer
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
:deep(.el-table) .__blue {
  color: blue;
}
:deep(.el-table) .__colored {
  background-color: #fafafa
}
:deep(.el-table) td .__border_bottom_none {
  border-bottom: none;
}
:deep(.el-table) td .__border_normal {
  border-collapse: collapse;
  border: 3px solid #eee;
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
.caption_card {
  padding: 5px 15px 20px 15px;
}
.cost-link {
  color: #092987;
  text-decoration:underline;
  text-underline-offset:0.1em;
  font-size:12px;
}
.black-normal{
  color: black;
  font-weight: normal;
}
</style>
