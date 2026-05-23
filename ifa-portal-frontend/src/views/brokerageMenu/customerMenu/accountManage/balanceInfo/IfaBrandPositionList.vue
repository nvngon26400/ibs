<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.screenTitle"
      :center="true"
      top="1%"
      :close-on-click-modal="false"
      :show-close="false"
      :before-close="onDialogClose"
    >

      <div
        class="reflesh-button"
        style="position: relative; margin-bottom: 1rem; margin-top: 0; text-align: center;"
      >
        <ifa-button
          id="btnUpdate"
          text="更新"
          icon="RefreshRight"
          style="width: auto;"
          action-type="originalAction"
          @app-action-handler="initializeA001"
        ></ifa-button>
        <ifa-button
          id="btnClose"
          text="閉じる"
          color="secondary"
          action-type="originalAction"
          style="margin-left: 10px;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </div>

      <div style="position: relative;">
        <div v-if="form.searchResultCount <= 0"
             style="text-align: center; margin: 3rem 0; font-size: 14px;"
        >
          {{ form.noDetailMsg }}
        </div>
        <el-row v-else
                style="margin-top: 3rem;"
        >
          <el-row>
            <el-col :span="2">
              <span style="font-weight:bold">コード：</span>
            </el-col>
            <el-col :span="1">
              <span>{{ $_out(form.brandCode) }}</span>
            </el-col>
            <el-col :span="8">
              <span>{{ $_out(form.brandName) }}</span>
            </el-col>
          </el-row>
          <el-row style="margin-top:0.5rem">
            <el-col :span="2">
              <span style="font-weight:bold">建玉区分：</span>
            </el-col>
            <el-col :span="3">
              <div
                :class="{'font-color__plus bold': form.newCreditOrderType === '0', 'font-color__minus bold': form.newCreditOrderType !== '0'}"
              >
                <ifa-text
                  :class="form.newCreditOrderType === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                  code-list-id="NEW_CREDIT_SELL_BUY_TYPE"
                  :disp-pattern="1"
                  :code-key="form.newCreditOrderType"
                ></ifa-text>
                <span v-if="!form.newCreditOrderType">-</span>
                <ifa-text
                  :class="form.newCreditOrderType === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                  code-list-id="PAYMENT_DEADLINE"
                  :disp-pattern="2"
                  :code-key="form.specificPositionTypePaymentDeadline"
                ></ifa-text>
                <span v-if="!form.specificPositionTypePaymentDeadline">-</span>
              </div>
            </el-col>
          </el-row>
          <el-row style="margin-top:0.5rem">
            <el-col :span="2">
              <span style="font-weight:bold">建玉件数：</span>
            </el-col>
            <el-col :span="2">
              <span>{{ $_withCommaInteger($_out(form.searchResultCount)) }}件</span>
            </el-col>
          </el-row>
        </el-row>

        <el-row
          v-if="form.searchResultCount > 0"
          style="margin-top: 2rem;"
        >
          <el-table
            :data="summary"
            :cell-class-name="tableCellClassNameSummary"
            style="width: 100%;font-size:12px;box-shadow: 1px 2px 2px #ddd;"
          >
            <el-table-column
              label="市場"
              :min-width="200"
            >
              <template #default="scope">
                <span>{{ scope.row.market }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="建玉金合計"
              :min-width="200"
            >
              <template #default="scope">
                <span>{{ $_withCommaInteger($_out(scope.row.totalPrice)) }}</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="200">
              <template #header>
                <div>評価額合計（前日）</div>
                <div>評価額合計（リアル）</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.valuationTotalPreviousDay)) }}</div>
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.valuationTotalReal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column
              label="諸経費合計"
              :min-width="200"
            >
              <template #default="scope">
                <div>{{ $_withCommaInteger($_out(scope.row.expensesTotal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="200">
              <template #header>
                <div>評価損益合計（前日）</div>
                <div>評価損益合計（リアル）</div>
              </template>
              <template #default="scope">
                <div
                  :class="scope.row.domesticPositionValuationTotalPreviousDay === '' ? 'black-normal' : scope.row.domesticPositionValuationTotalPreviousDay < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalPreviousDay >= 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalPreviousDay)) }}</div>
                <div
                  :class="scope.row.domesticPositionValuationTotalReal === '' ? 'black-normal' : scope.row.domesticPositionValuationTotalReal < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalReal >= 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalReal)) }}</div>
              </template>
            </el-table-column>
          </el-table>
        </el-row>

        <el-row
          v-if="form.searchResultCount > 0"
          style="margin-top: 1rem;border: 3px solid #eee;padding: 0.2rem 0 0.2rem 1rem"
        >
          <el-col
            :span="3"
            style="padding-top: 10px;"
          >
            <span class="__bold">並替順序 ：</span>
          </el-col>
          <ifa-input-radio
            v-model="form.sortOrder"
            code-list-id="SORT_ORDER"
            :disp-pattern="1"
            :select-pattern="2"
            @change="requestTypeA002"
          ></ifa-input-radio>
        </el-row>

        <el-row
          v-if="form.searchResultCount > 0"
          style="margin-top: 1rem;"
        >
          <div class="font-color__plus shorter-link">
            <span>●</span>
            <ifa-link
              ref="repaymentDeadlineSecurity"
              :link-icon-image="null"
              :url-id="35"
              disp-name="返済期限設定銘柄"
              manual-init
            ></ifa-link>
          </div>
          <el-table
            :data="form.brandPositionDetailList"
            :highlight-current-row="true"
            :cell-class-name="tableCellClassNameDetail"
            style="width: 100%; font-size:12px;"
            :scrollbar-always-on="true"
          >
            <el-table-column
              label="市場"
              :min-width="50"
            >
              <template #default="scope">
                <ifa-text
                  code-list-id="NEW_MARKET"
                  :disp-pattern="1"
                  :select-pattern="2"
                  :code-key="scope.row.market"
                ></ifa-text>
                <span v-if="!scope.row.market">-</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="110">
              <template #header>
                <div>新規建日</div>
                <div>返済期限</div>
              </template>
              <template #default="scope">
                <div v-if="scope.row.dueDateShortenClassification !== '1'">
                  <div>{{ $_out($_getFormattedDateValue(scope.row.constructionDate)) }}</div>
                  <div>{{ $_getFormattedDateValue(scope.row.lastTradeDate) || $_out(scope.row.lastTradeDate) }}</div>
                </div>
                <div v-else>
                  <div>{{ $_out($_getFormattedDateValue(scope.row.constructionDate)) }}</div>
                  <div class="font-color__plus">●{{ $_getFormattedDateValue(scope.row.lastTradeDate) || $_out(scope.row.lastTradeDate) }}</div>
                </div>
              </template>
            </el-table-column>
            <el-table-column
              prop="parentStockTradeDate"
              :min-width="100"
            >
              <template #header>
                <div>親株新規</div>
                <div>約定日</div>
              </template>
              <template #default="scope">
                <div>{{ $_getFormattedDateValue(scope.row.parentStockTradeDate) || $_out(scope.row.parentStockTradeDate) }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="100">
              <template #header>
                <div>預り区分</div>
                <div>(担保)</div>
              </template>
              <template #default="scope">
                <div>
                  <ifa-text
                    code-list-id="SPECIFIC_POSITION_TYPE"
                    :disp-pattern="1"
                    :code-key="scope.row.accountType"
                  ></ifa-text>
                  <span v-if="!scope.row.accountType">-</span>
                </div>
                <div v-if="scope.row.security">
                  {{ scope.row.security }}
                  <ifa-text
                    v-if="scope.row.collateralRegulations !== ' '"
                    code-list-id="COLLATERAL_REGULATIONS"
                    :disp-pattern="1"
                    :code-key="scope.row.collateralRegulations"
                  ></ifa-text>
                </div>
                <div v-else>( - )</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="170">
              <template #header>
                <div>建株数</div>
                <div>(注文中)</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaInteger($_out(scope.row.contPositionTotal)) }}</div>
                <div>({{ $_withCommaInteger($_out(scope.row.unactualQuantity)) }})</div>
              </template>
            </el-table-column>
            <el-table-column
              prop="unit"
              label="建単価"
              :min-width="150"
            >
              <template #default="scope">
                <span>{{ $_withCommaNoneZeroPadding($_out(scope.row.newPrice)) }}</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="200">
              <template #header>
                <div>評価単価</div>
                <div>（前日）</div>
                <div>現在値</div>
                <div>（リアル）</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaZeroPadding($_out(scope.row.dayBeforeValuationPrice), 2) }}</div>
                <div>{{ $_out($_withCommaZeroPadding(scope.row.latestPrice), 2) }}</div>
              </template>
            </el-table-column>
            <el-table-column
              label="建代金"
              :min-width="160"
            >
              <template #default="scope">
                <span>{{ $_withCommaInteger($_out(scope.row.openInterestAmount)) }}</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="160">
              <template #header>
                <div>評価額</div>
                <div>（前日）</div>
                <div>評価額</div>
                <div>（リアル）</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.valuationPreviousDay)) }}</div>
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.valuationReal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column
              prop="expences"
              label="諸経費"
              :min-width="160"
            >
              <template #default="scope">
                <el-link
                  :underline="false"
                  disable-transitions
                  class="cost-link"
                  @click="domesticPositionDetailA003(scope.row)"
                >{{ $_withCommaInteger($_out(scope.row.charge)) }}</el-link>
              </template>
            </el-table-column>
            <el-table-column :min-width="180">
              <template #header>
                <div>評価損益</div>
                <div>（前日）</div>
                <div>評価損益</div>
                <div>（リアル）</div>
              </template>
              <template #default="scope">
                <div
                  :class="scope.row.domesticPositionValuationTotalPreviousDay === '' ? 'black_normal' : scope.row.domesticPositionValuationTotalPreviousDay < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalPreviousDay >= 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalPreviousDay)) }}</div>
                <div
                  :class="scope.row.domesticPositionValuationTotalReal === '' ? 'black_normal' : scope.row.domesticPositionValuationTotalReal < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalReal >= 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalReal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="160">
              <template #header>
                <div>現金</div>
                <div>拘束金</div>
              </template>
              <template #default="scope">
                <span>{{ $_withCommaInteger($_out(scope.row.cashBond)) }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="取引"
              :min-width="120"
            >
              <template
                #default="scope"
              >
                <div
                  v-if="form.tradeSuspendFlag !== '1'"
                >
                  <ifa-button
                    v-if="form.newCreditOrderType === '0' && getIntermediaryValue('6') !== '0'"
                    id="btnSell"
                    color="sell"
                    small
                    text="返済売"
                    :disabled="scope.row.maxOrderableQuantity <= 0"
                    action-type="originalAction"
                    @app-action-handler="marginRepayOrderInputA004(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="form.newCreditOrderType === '1' && getIntermediaryValue('5') !== '0'"
                    id="btnBuy"
                    color="buy"
                    small
                    text="返済買"
                    :disabled="scope.row.maxOrderableQuantity <= 0"
                    action-type="originalAction"
                    @app-action-handler="marginRepayOrderInputA004(scope.row)"
                  ></ifa-button>
                </div>
                <div
                  v-if="form.tradeSuspendFlag !== '1'"
                  style="margin-top: 5px;"
                >
                  <ifa-button
                    v-if="form.newCreditOrderType === '0' && getIntermediaryValue('8') !== '0'"
                    id="btnReceipt"
                    color="buy"
                    small
                    text="現引"
                    :disabled="scope.row.maxOrderableQuantity <= 0"
                    action-type="originalAction"
                    @app-action-handler="receiptDeliveryOrderInputA005(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="form.newCreditOrderType === '1' && getIntermediaryValue('7') !== '0'"
                    id="btnDelivery"
                    color="sell"
                    small
                    text="現渡"
                    :disabled="scope.row.maxOrderableQuantity <= 0"
                    action-type="originalAction"
                    @app-action-handler="receiptDeliveryOrderInputA005(scope.row)"
                  ></ifa-button>
                </div>
              </template>
            </el-table-column>
          </el-table>

        </el-row>
        <div style="padding-top: 1rem;">
          <span style="font-weight: bold;">預り区分（担保）欄表記</span>
          <br>
          <ifa-text
            code-list-id="COLLATERAL_REGULATIONS"
            :disp-pattern="2"
            code-key="1"
          ></ifa-text>
          <br>
          <ifa-text
            code-list-id="COLLATERAL_REGULATIONS"
            :disp-pattern="2"
            code-key="2"
          ></ifa-text>
          <br>
          <ifa-text
            code-list-id="COLLATERAL_REGULATIONS"
            :disp-pattern="2"
            code-key="3"
          ></ifa-text>
        </div>
      </div>
    </el-dialog>

    <!-- ダイアログ -->
    <ifa-domestic-position-detail
      :is-visible="dialogVisible"
      :form-data="X001Response"
      @close-modal="handleCloseModal"
    ></ifa-domestic-position-detail>
    <ifa-requester
      id="IfaBrandPositionListA001"
      action-id="SUB0202_010202-03#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaBrandPositionListA003"
      action-id="SUB07-05#X001"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseHandlerA003"
      @response-error-handler="responseErrorHandlerA003"
    ></ifa-requester>
    <!-- 信用返済注文入力 A001(初期化) -->
    <ifa-requester
      id="IfaBrandPositionListIfaMarginRepayOrderInputInitializeA001"
      action-id="SUB0202_0212-04_1#A001"
      action-type="requestAction"
      :request-model="marginRepayOrderA001RequestModel"
      @response-handler="responseHandlerMarginRepayOrderInputA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaDomesticPositionDetail from '@/views/common/IfaDomesticPositionDetail'
import { getCreditRepaymentWrapperViewNames, setTagColor } from '@/views/brokerageMenu/customerMenu/commonProcess/creditRepayment.js'
import { IfaBrandPositionListFormModel } from './js/IfaBrandPositionListFormModel'
import { IfaBrandPositionListA001RequestModel } from './js/IfaBrandPositionListA001RequestModel'
import { IfaBrandPositionListA003RequestModel } from './js/IfaBrandPositionListA003RequestModel'
import { IfaBrandPositionListA004RequestModel } from './js/IfaBrandPositionListA004RequestModel'
import { IfaBrandPositionListA005RequestModel } from './js/IfaBrandPositionListA005RequestModel'
import { IfaMarginRepayOrderInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA001RequestModel.js'

export default {
  components: {
    IfaDomesticPositionDetail
  },
  props: {
    isVisible: { type: Boolean, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      dialogVisible: false,
      dialogIsIndividual: true,
      form: new IfaBrandPositionListFormModel(),
      A003RequestModel: {},
      A004RequestModel: {},
      A005RequestModel: {},
      X001Response: {}
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaBrandPositionListA001RequestModel(this.form)
    },
    marginRepayOrderA001RequestModel() {
      return new IfaMarginRepayOrderInputA001RequestModel(this.A004RequestModel)
    },
    // サマリー情報
    summary() {
      const summary = []
      const tokyoSecurity = this.form.tokyoSecurityList.find(e => e.valuationTotalReal)
      const pts = this.form.ptsList.find(e => e.valuationTotalReal)
      if (tokyoSecurity) summary.push(tokyoSecurity)
      if (pts) summary.push(pts)
      return summary
    }

  },
  methods: {
    onShow(param) {
      this.form = new IfaBrandPositionListFormModel()
      Object.assign(this.form, param)
      this.form.butenCode = this.customerInfo.butenCode
      this.form.accountNumber = this.customerInfo.accountNumber
      this.form.sortOrder = '61' // 初期値「評価益順」
      this.form.searchResultCount = +this.form.searchResultCount
      this.$nextTick(() => {
        if (this.$refs['repaymentDeadlineSecurity']) {
          this.$refs['repaymentDeadlineSecurity'].trigger()
        }
      })
    },
    initializeA001() {
      this.form.sortOrder = '61'
      document.getElementById('IfaBrandPositionListA001').click()
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.form.searchResultCount = +this.form.searchResultCount
      this.$nextTick(() => {
        if (this.$refs['repaymentDeadlineSecurity']) {
          this.$refs['repaymentDeadlineSecurity'].trigger()
        }
      })
    },
    requestTypeA002() {
      // A001と同処理
      document.getElementById('IfaBrandPositionListA001').click()
    },
    responseHandlerA003(data) {
      this.X001Response = data.dataList[0]
      this.dialogVisible = true
    },
    responseErrorHandlerA003(error) {
      this.$_logError(error)
    },
    domesticPositionDetailA003(row) {
      this.A003RequestModel = new IfaBrandPositionListA003RequestModel(this.form)
      this.A003RequestModel.newOpenMarket = row.market
      this.A003RequestModel.newOpenInterestNumber = row.newOpenInterestNumber
      this.A003RequestModel.parentStockTradeDate = row.hiddenItemParentStockTradeDate
      this.A003RequestModel.newTradeDate = row.constructionDate
      this.A003RequestModel.openPrice = row.newPrice
      document.querySelector('#IfaBrandPositionListA003').click()
    },
    marginRepayOrderInputA004(row) {
      this.A004RequestModel = new IfaBrandPositionListA004RequestModel(this.form)
      this.A004RequestModel.contPositionTotal = row.maxOrderableQuantity
      this.A004RequestModel.positionDetailList[0].parentStockTradeDate = row.hiddenItemParentStockTradeDate
      this.A004RequestModel.positionDetailList[0].constructionDate = row.constructionDate
      this.A004RequestModel.positionDetailList[0].newPrice = row.newPrice
      this.A004RequestModel.positionDetailList[0].market = row.market

      this.$nextTick(() => {
        document.getElementById('IfaBrandPositionListIfaMarginRepayOrderInputInitializeA001').click()
      })
    },
    responseHandlerMarginRepayOrderInputA001(response) {
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: getCreditRepaymentWrapperViewNames().IfaMarginRepayOrderInput })
      this.$_startShowMenu('SUB0202_0212', response)
    },
    receiptDeliveryOrderInputA005(row) {
      this.A005RequestModel = new IfaBrandPositionListA005RequestModel(this.form)
      this.A005RequestModel.newOpenMarket = row.market
      this.A005RequestModel.parentStockTradeDate = row.hiddenItemParentStockTradeDate
      this.A005RequestModel.newTradeDate = row.constructionDate
      this.A005RequestModel.newPrice = row.newPrice
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: getCreditRepaymentWrapperViewNames().IfaReceiptDeliveryOrderInput })
      this.$_startShowMenu('SUB0202_0212', this.A005RequestModel)
    },
    getIntermediaryValue(tradeClass) {
      return this.form.intermediaryValueList.find(element => element.tradeClass === tradeClass)?.intermediaryValue
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
    },
    // 取引セルのタグ色設定処理
    setTagColor(dealType, dealEnable) {
      return setTagColor(dealType, null, dealEnable)
    },
    tableCellClassNameSummary({ row, column, rowIndex, columnIndex }) {
      // 市場
      if (columnIndex === 0) {
        return '__center'
      // 建玉金額合計
      } else if (columnIndex === 1) {
        return '__right'
      // 評価額合計（前日）評価額合計（リアル）
      } else if (columnIndex === 2) {
        return '__right'
      // 諸費用合計
      } else if (columnIndex === 3) {
        return '__right'
      // 評価損益合計（前日）評価損益合計（リアル）
      } else if (columnIndex === 4) {
        return '__right'
      }
    },
    tableCellClassNameDetail({ row, column, rowIndex, columnIndex }) {
      // 市場
      if (columnIndex === 0) {
        return '__center'
      // 新規建日返済期限
      } else if (columnIndex === 1) {
        return '__center'
      // 親株新規約定日
      } else if (columnIndex === 2) {
        return '__center'
      // 特定・一般(担保)
      } else if (columnIndex === 3) {
        return '__center'
      // 建株数(注文中)
      } else if (columnIndex === 4) {
        return '__right'
      // 新規単価
      } else if (columnIndex === 5) {
        return '__right'
      // 評価単価（前日）現在値（リアル）
      } else if (columnIndex === 6) {
        return '__right'
      // 建玉金額
      } else if (columnIndex === 7) {
        return '__right'
      // 評価額（前日）評価額（リアル）
      } else if (columnIndex === 8) {
        return '__right'
      // 諸費用
      } else if (columnIndex === 9) {
        return '__right'
      // 評価損益（前日）評価損益（リアル）
      } else if (columnIndex === 10) {
        return '__right'
      // 現金拘束金
      } else if (columnIndex === 11) {
        return '__right'
      // 決済期日
      } else if (columnIndex === 12) {
        return '__center'
      // 建玉No
      } else if (columnIndex === 13) {
        return '__center'
      // 取引
      } else if (columnIndex === 14) {
        return '__center'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.reflesh-button {
  position: absolute;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.shorter-link {
  margin-top: 2rem;
  margin-left: 5%;
}
._bold_black_m {
  font-size: 16px;
  font-weight: 700;
  color: #606266;
}
.__center {
  text-align: center;
}
.__left {
  text-align: left;
}
.__right {
  text-align: right;
}
.__red {
  color: red;
}
.black-normal{
  color: black;
  font-weight: normal;
}
.clickable:hover {
  cursor: pointer
}
.cost-link {
  color: #092987;
  text-decoration:underline;
  text-underline-offset:0.1em;
  font-size:12px;
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
:deep(.el-dialog){
  width:1400px;
  max-height:1000px;
}
</style>
