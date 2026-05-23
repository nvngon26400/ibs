<template>
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <el-row class="reflesh-button">
        <ifa-button
          id="btnUpdate"
          action-type="originalAction"
          text="更新"
          icon="RefreshRight"
          style="width: auto;"
          @app-action-handler="onShow"
        ></ifa-button>
      </el-row>
      <div v-if="parseInt(form.searchResultCount) === 0"
           style="text-align: center; margin: 0 0 2rem 0"
      >
        {{ form.noDetailMsg }}
      </div>
      <template
        v-else
      >
        <el-row
          style="margin-top: 0.5rem;"
        >
          <!-- 建玉一覧サマリー -->
          <table class="_table__body">
            <tbody>
              <tr>
                <th
                  rowspan="2"
                  class="_table__header __left"
                  style="width: 12%;"
                >建玉金額合計</th>
                <td
                  rowspan="2"
                  class="_table__data __right"
                  style="width: 11%;"
                >{{ $_withCommaNoneZeroPadding($_out(form.totalPrice)) }}</td>
                <th
                  class="_table__header __left"
                  style="width: 18%;"
                >評価額合計（前日）</th>
                <td
                  class="_table__data __right"
                  style="width: 11%;"
                >{{ $_withCommaNoneZeroPadding($_out(form.valuationTotalPreviousDay)) }}</td>
                <th
                  rowspan="2"
                  class="_table__header __left"
                  style="width: 11%;"
                >諸経費合計</th>
                <td
                  rowspan="2"
                  class="_table__data __right"
                  style="width: 7%;"
                >{{ $_withCommaNoneZeroPadding($_out(form.costTotalYen15)) }}</td>
                <th
                  class="_table__header __left"
                  style="width: 20%;"
                >評価損益合計（前日）</th>
                <td
                  :class="['_table__data __right bold', form.domesticPositionValuationTotalPreviousDay > 0 ? 'font-color__plus' : 'font-color__minus']"
                >{{ $_signedWithCommaNoneZeroPadding($_out(form.domesticPositionValuationTotalPreviousDay)) }}</td>
              </tr>
              <tr>
                <th class="_table__header __left">評価額合計（リアル）</th>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(form.valuationTotalReal)) }}</td>
                <th class="_table__header __left">評価損益合計（リアル）</th>
                <td :class="['_table__data __right bold', form.domesticPositionValuationTotalReal > 0 ? 'font-color__plus' : form.domesticPositionValuationTotalReal < 0 ? 'font-color__minus' : '']">
                  {{ $_signedWithCommaNoneZeroPadding($_out(form.domesticPositionValuationTotalReal)) }}</td>
              </tr>
            </tbody>
          </table>
        </el-row>

        <el-row v-if="form.searchResultCount !== '0'">
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
            :data="form.positionList"
            :span-method="objectSpanMethod"
            :cell-class-name="tableCellClassName"
            max-height="690px"
            style="width: 100%; font-size:12px;"
            :cell-style="{height: '84px'}"
            :scrollbar-always-on="true"
          >
            <el-table-column :width="62">
              <template #header>
                <div>銘柄</div>
                <div>コード</div>
              </template>
              <template #default="scope">
                {{ $_out(scope.row.brandCode) }}
              </template>
            </el-table-column>
            <el-table-column :min-width="55">
              <template #header>
                <div>銘柄名</div>
              </template>
              <template #default="scope">
                {{ $_out(scope.row.brandName) }}
              </template>
            </el-table-column>
            <el-table-column :width="53">
              <template #header>
                <div>建区分</div>
                <div>期限</div>
              </template>
              <template #default="scope">
                <div
                  disable-transitions
                >
                  <ifa-text
                    :class="scope.row.tradeKbn === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                    code-list-id="NEW_CREDIT_SELL_BUY_TYPE"
                    :disp-pattern="1"
                    :code-key="scope.row.tradeKbn"
                  ></ifa-text>
                  <span v-if="!scope.row.tradeKbn">-</span>
                </div>
                <div
                  disable-transitions
                >
                  <ifa-text
                    :class="scope.row.tradeKbn === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                    code-list-id="PAYMENT_DEADLINE"
                    :disp-pattern="1"
                    :code-key="scope.row.paymentDeadline"
                  ></ifa-text>
                  <span v-if="!scope.row.paymentDeadline">-</span>
                </div>
              </template>
            </el-table-column>
            <el-table-column :width="38">
              <template #header>
                <div>市</div>
                <div>場</div>
              </template>
              <template #default="scope">
                <ifa-text
                  code-list-id="NEW_MARKET"
                  :disp-pattern="1"
                  :code-key="scope.row.market"
                ></ifa-text>
                <span v-if="!scope.row.market">-</span>
              </template>
            </el-table-column>
            <el-table-column :width="82">
              <template #header>
                <div>新規建日</div>
                <div>返済期限</div>
              </template>
              <template #default="scope">
                <div v-if="scope.row.repayPeriodShorter === '●'">
                  <div>{{ scope.row.constructionDate ? $_getFormattedDateValue(scope.row.constructionDate) : '----/--/--' }}</div>
                  <div class="font-color__plus">●{{ scope.row.lastTradeDate ? $_getFormattedDateValue(scope.row.lastTradeDate) : '----/--/--' }}</div>
                </div>
                <div v-else>
                  <div>{{ scope.row.constructionDate ? $_getFormattedDateValue(scope.row.constructionDate) : '----/--/--' }}</div>
                  <div>{{ scope.row.lastTradeDate ? $_getFormattedDateValue(scope.row.lastTradeDate) : '----/--/--' }}</div>
                </div>
              </template>
            </el-table-column>
            <el-table-column
              :width="82"
            >
              <template #header>
                <div>親株新規</div>
                <div>約定日</div>
              </template>
              <template #default="scope">
                <div>{{ scope.row.parentStockTradeDate ? $_getFormattedDateValue(scope.row.parentStockTradeDate) : '----/--/--' }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="45">
              <template #header>
                <div>預り区分</div>
                <div>(担保)</div>
              </template>
              <template #default="scope">
                <div>{{ $_out(scope.row.accountType) }}</div>
                <div>
                  {{ $_out(scope.row.domesticCollateral) }}
                </div>
                <div>
                  <ifa-text
                    v-if="scope.row.collateralRegulations !== ' '"
                    code-list-id="COLLATERAL_REGULATIONS"
                    :disp-pattern="1"
                    :code-key="scope.row.collateralRegulations"
                  ></ifa-text>
                </div>
              </template>
            </el-table-column>
            <el-table-column
              :min-width="36"
            >
              <template #header>
                <div>建玉</div>
                <div>件数</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.positionCount)) }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="50">
              <template #header>
                <div>建株数</div>
                <div>(注文中)</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.contPositionTotal)) }}</div>
                <div v-if="scope.row.unactualQuantity > 0">({{ $_withCommaNoneZeroPadding($_out(scope.row.unactualQuantity)) }})</div>
              </template>
            </el-table-column>
            <el-table-column
              prop="newPrice"
              label="建単価"
              :min-width="55"
            >
              <template #default="scope">
                <span>{{ $_withCommaNoneZeroPadding($_out(scope.row.newPrice)) }}</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="67">
              <template #header>
                <div>評価単価</div>
                <div>(前日)</div>
                <div>現在値</div>
                <div>(リアル)</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaZeroPadding($_out(scope.row.dayBeforeValuationPrice), 2) }}</div>
                <div>{{ $_withCommaZeroPadding($_out(scope.row.currentPrice), 2) }}</div>
              </template>
            </el-table-column>
            <el-table-column
              label="建代金"
              :min-width="85"
            >
              <template #default="scope">
                <span>{{ $_withCommaNoneZeroPadding($_out(scope.row.totalSmallPrice)) }}</span>
              </template>
            </el-table-column>
            <el-table-column :min-width="85">
              <template #header>
                <div>評価額計</div>
                <div>(前日)</div>
                <div>評価額計</div>
                <div>(リアル)</div>
              </template>
              <template #default="scope">
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.previousDayValueTotal)) }}</div>
                <div>{{ $_withCommaNoneZeroPadding($_out(scope.row.realtimeValueTotal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column
              label="諸経費等"
              :min-width="63"
            >
              <template #default="scope">
                <el-link
                  :underline="false"
                  disable-transitions
                  class="cost-link"
                  @click="costSmallTotalYenA002(scope.row)"
                >{{ $_withCommaNoneZeroPadding($_out(scope.row.costSmallTotalYen)) }}</el-link>
              </template>
            </el-table-column>
            <el-table-column :min-width="90">
              <template #header>
                <div>評価損益</div>
                <div>(前日)</div>
                <div>評価損益</div>
                <div>(リアル)</div>
              </template>
              <template #default="scope">
                <div
                  :class="scope.row.domesticPositionValuationTotalPreviousDay < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalPreviousDay > 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalPreviousDay)) }}</div>
                <div
                  :class="scope.row.domesticPositionValuationTotalReal < 0 ? 'font-color__minus bold' : scope.row.domesticPositionValuationTotalReal > 0 ? 'font-color__plus bold' : 'bold'"
                  disable-transitions
                >{{ $_signedWithCommaNoneZeroPadding($_out(scope.row.domesticPositionValuationTotalReal)) }}</div>
              </template>
            </el-table-column>
            <el-table-column :min-width="72">
              <template #header>
                <div>現金</div>
                <div>拘束金</div>
              </template>
              <template #default="scope">
                <span>{{ $_withCommaNoneZeroPadding($_out(scope.row.cashBond)) }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="取引"
              :width="124"
            >
              <template #default="scope">
                <div v-if="form.tradeSuspendFlag !== '1'">
                  <ifa-button
                    v-if="scope.row.repayBuyButtonDisplaylassification === '1' && getIntermediaryValue('5') !== '0'"
                    id="btnBuy"
                    color="buy"
                    small
                    text="返済買"
                    :disabled="scope.row.activationDeactivationFlag === '0'"
                    action-type="originalAction"
                    @app-action-handler="repaymentBuySellA005(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="scope.row.repaySellButtonDisplaylassification === '1' && getIntermediaryValue('6') !== '0'"
                    id="btnSell"
                    color="sell"
                    small
                    text="返済売"
                    :disabled="scope.row.activationDeactivationFlag === '0'"
                    action-type="originalAction"
                    @app-action-handler="repaymentBuySellA005(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="scope.row.positionDetailButtonDisplaylassification === '1'"
                    id="btnDetail"
                    color="secondary"
                    small
                    text="建玉詳細"
                    action-type="originalAction"
                    @app-action-handler="positionDetailA003(scope.row)"
                  ></ifa-button>
                </div>
                <div
                  v-if="form.tradeSuspendFlag !== '1'"
                  style="margin-top:10px"
                >
                  <ifa-button
                    v-if="scope.row.deliveryButtonDisplaylassification === '1' && getIntermediaryValue('7') !== '0'"
                    id="btnDelivery"
                    color="sell"
                    small
                    text="現渡"
                    :disabled="scope.row.activationDeactivationFlag === '0'"
                    action-type="originalAction"
                    @app-action-handler="cashPullDeliveryA006(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="scope.row.receiptButtonDisplaylassification === '1' && getIntermediaryValue('8') !== '0'"
                    id="btnReceipt"
                    color="buy"
                    small
                    text="現引"
                    :disabled="scope.row.activationDeactivationFlag === '0'"
                    action-type="originalAction"
                    @app-action-handler="cashPullDeliveryA006(scope.row)"
                  ></ifa-button>
                  <ifa-button
                    v-if="scope.row.massRepayButtonDisplaylassification === '1' && getIntermediaryValue(scope.row.tradeKbn === '0' ? '6' : '5') !== '0'"
                    id="btnMassRepay"
                    color="primary"
                    small
                    text="一括返済"
                    :disabled="scope.row.activationDeactivationFlag === '0'"
                    action-type="originalAction"
                    @app-action-handler="massRepayA004(scope.row)"
                  ></ifa-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        <div style="margin-top: 2rem;">
          <span>預り区分（担保）欄表記</span>
          <br><br>
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
      </template>

      <!-- ダイアログ -->
      <ifa-domestic-position-detail
        :is-visible="dialogVisible"
        :form-data="domesticPositionDetailModel"
        @close-modal="handleCloseModal"
      ></ifa-domestic-position-detail>
      <ifa-brand-position-list
        ref="IfaBrandPositionList"
        :is-visible="dialogBrandDetailVisible"
        :customer-info="customerInfo"
        @close-modal="handleCloseModal"
      ></ifa-brand-position-list>
    </div>
    <ifa-requester
      id="IfaDomesticPositionListA001"
      action-id="SUB0202_010202-01#A001"
      action-type="requestAction"
      :request-model="A001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaDomesticPositionListA002"
      action-id="SUB07-05#X001"
      action-type="requestAction"
      :request-model="X001RequestModel"
      @response-handler="responseHandlerA002"
    ></ifa-requester>
    <ifa-requester
      id="IfaDomesticPositionListA003"
      action-id="SUB0202_010202-03#A001"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="responseHandlerPositionDetailA003($event)"
    ></ifa-requester>
    <!-- 信用返済注文入力 A001(初期化) -->
    <ifa-requester
      id="IfaDomesticPositionListIfaMarginRepayOrderInputInitializeA001"
      action-id="SUB0202_0212-04_1#A001"
      action-type="requestAction"
      :request-model="marginRepayOrderA001RequestModel"
      @response-handler="responseHandlerMarginRepayOrderInputA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
const offset = []
let dealOffset = 0
const colored = []
const coloredRowIndex = []
import IfaDomesticPositionDetail from '@/views/common/IfaDomesticPositionDetail'
import IfaBrandPositionList from './IfaBrandPositionList'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { getCreditRepaymentWrapperViewNames } from '@/views/brokerageMenu/customerMenu/commonProcess/creditRepayment.js'
import { IfaDomesticPositionListFormModel } from './js/IfaDomesticPositionListFormModel'
import { IfaDomesticPositionListA001RequestModel } from './js/IfaDomesticPositionListA001RequestModel'
import { IfaDomesticPositionDetailX001RequestModel } from './js/IfaDomesticPositionDetailX001RequestModel'
import { IfaDomesticPositionListA003RequestModel } from './js/IfaDomesticPositionListA003RequestModel'
import { IfaDomesticPositionListA004RequestModel } from './js/IfaDomesticPositionListA004RequestModel'
import { IfaDomesticPositionListA005RequestModel } from './js/IfaDomesticPositionListA005RequestModel'
import { IfaDomesticPositionListA006RequestModel } from './js/IfaDomesticPositionListA006RequestModel'
import { IfaMarginRepayOrderInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA001RequestModel.js'

export default {
  components: {
    IfaDomesticPositionDetail,
    IfaBrandPositionList,
    screenTitle
  },
  props: {
    entryOfCreditRepaymentOrder: { type: Boolean, required: false, default: false },
    entryOfCurrentDeliveryOrder: { type: Boolean, required: false, default: false }
  },
  emits: ['initializeError'],
  data() {
    return {
      requestType: 0,
      dialogVisible: false,
      dialogBrandDetailVisible: false,
      dialogIsIndividual: false,
      tagColor1: 'info',
      tagColor2: 'info',
      form: new IfaDomesticPositionListFormModel(),
      X001RequestModel: {},
      A003RequestModel: {},
      A004RequestModel: {},
      A005RequestModel: {},
      A006RequestModel: {},
      domesticPositionDetailModel: {},
      marginRepayOrderA001RequestModel: {}
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A001RequestModel() {
      return new IfaDomesticPositionListA001RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.dialogBrandDetailVisible = false
      this.form.butenCode = this.customerInfo.butenCode
      this.form.accountNumber = this.customerInfo.accountNumber
      this.$nextTick(() => {
        document.getElementById('IfaDomesticPositionListA001').click()
      })
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.$nextTick(() => {
        if (this.$refs['repaymentDeadlineSecurity']) {
          this.$refs['repaymentDeadlineSecurity'].trigger()
        }
      })
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    costSmallTotalYenA002(row) {
      this.X001RequestModel = new IfaDomesticPositionDetailX001RequestModel(row)
      this.X001RequestModel.butenCode = this.customerInfo.butenCode
      this.X001RequestModel.accountNumber = this.customerInfo.accountNumber
      if (Number(row.positionCount) !== 1) {
        this.X001RequestModel.newOpenInterestNumber = '00000'
      }
      if (Number(row.positionCount) !== 1) {
        this.X001RequestModel.parentStockTradeDate = ''
      }
      if (Number(row.positionCount) !== 1) {
        this.X001RequestModel.newTradeDate = ''
      }
      if (Number(row.positionCount) !== 1) {
        this.X001RequestModel.openPrice = ''
      }
      this.$nextTick(() => {
        document.getElementById('IfaDomesticPositionListA002').click()
      })
    },
    responseHandlerA002(response) {
      this.domesticPositionDetailModel = Object.assign(this.domesticPositionDetailModel, response.dataList[0])
      this.dialogVisible = true
    },
    positionDetailA003(row) {
      this.A003RequestModel = new IfaDomesticPositionListA003RequestModel(row)
      this.$nextTick(() => {
        document.getElementById('IfaDomesticPositionListA003').click()
      })
    },
    responseHandlerPositionDetailA003(response) {
      this.dialogBrandDetailVisible = true
      this.$refs['IfaBrandPositionList'].onShow(response.dataList[0])
    },
    massRepayA004(row) {
      this.A004RequestModel = new IfaDomesticPositionListA004RequestModel(row)
      if (row.join) {
        this.A004RequestModel.market = 'ALL'
      }
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: getCreditRepaymentWrapperViewNames().IfaMarginMassRepayInput })
      this.$_startShowMenu('SUB0202_0212', this.A004RequestModel)
    },
    repaymentBuySellA005(row) {
      this.A005RequestModel = new IfaDomesticPositionListA005RequestModel(row)
      this.marginRepayOrderA001RequestModel = new IfaMarginRepayOrderInputA001RequestModel(this.A005RequestModel)

      this.$nextTick(() => {
        document.getElementById('IfaDomesticPositionListIfaMarginRepayOrderInputInitializeA001').click()
      })
    },
    responseHandlerMarginRepayOrderInputA001(response) {
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: getCreditRepaymentWrapperViewNames().IfaMarginRepayOrderInput })
      this.$_startShowMenu('SUB0202_0212', response)
    },
    cashPullDeliveryA006(row) {
      this.A006RequestModel = new IfaDomesticPositionListA006RequestModel(row)
      this.A006RequestModel.openTradeKbn = row.tradeKbn
      this.A006RequestModel.newOpenMarket = row.market
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: getCreditRepaymentWrapperViewNames().IfaReceiptDeliveryOrderInput })
      this.$_startShowMenu('SUB0202_0212', this.A006RequestModel)
    },
    getIntermediaryValue(tradeClass) {
      return this.form.intermediaryValueList.find(element => element.tradeClass === tradeClass)?.intermediaryValue
    },
    // 戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
      this.dialogBrandDetailVisible = false
    },
    // テーブル整形処理
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // 銘柄コード
      if (columnIndex === 0 && rowIndex < this.form.positionList.length) {
        return this.objectSpanMethodCodeName(row, column, rowIndex, columnIndex)
      // 銘柄名
      } else if (columnIndex === 1 && rowIndex < this.form.positionList.length) {
        return this.objectSpanMethodCodeName(row, column, rowIndex, columnIndex)
      // 取引
      } else if (columnIndex === 16 && rowIndex < this.form.positionList.length) {
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
        for (let i = rowIndex; i < this.form.positionList.length - 1; i++) {
          // 次の行と同じ値なら結合対象と判断。異なる値が出てくるまでループ
          // 範囲外の index を参照しないようにするため、最後から2番目の要素までチェック
          if (this.form.positionList[i].brandCode === this.form.positionList[i + 1].brandCode) {
            count++
            if (colored[columnIndex]) {
              if (!coloredRowIndex.includes(i)) coloredRowIndex.push(i)
              if (!coloredRowIndex.includes(i + 1)) coloredRowIndex.push(i + 1)
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
      }
      // 同名要素数の確認
      if (rowIndex >= dealOffset) {
        for (let i = rowIndex; i < this.form.positionList.length - 1; i++) {
          // 次の行と同じ値なら結合対象と判断。異なる値が出てくるまでループ
          // 範囲外の index を参照しないようにするため、最後から2番目の要素までチェック
          if ((this.form.positionList[i].brandCode === this.form.positionList[i + 1].brandCode) &&
             (this.form.positionList[i].tradeKbn === this.form.positionList[i + 1].tradeKbn) &&
             (this.form.positionList[i].paymentDeadline === this.form.positionList[i + 1].paymentDeadline)) {
            count++
            this.form.positionList[i].join = true
            this.form.positionList[i + 1].join = true
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
      // 銘柄コード
      if (columnIndex === 0) {
        return rowStyle + '__left'
      // 銘柄名
      } else if (columnIndex === 1) {
        return rowStyle + '__left'
      // 取引
      } else if (columnIndex === 2) {
        return rowStyle + '__center __bold'
      // 市場
      } else if (columnIndex === 3) {
        return rowStyle + '__center'
      // 新規建日返済期限
      } else if (columnIndex === 4) {
        return rowStyle + '__center'
      // 親株新規約定日
      } else if (columnIndex === 5) {
        return rowStyle + '__center'
      // 特定・一般(担保)
      } else if (columnIndex === 6) {
        return rowStyle + '__center'
      // 建玉件数
      } else if (columnIndex === 7) {
        return rowStyle + '__center'
      // 建株数(注文中)
      } else if (columnIndex === 8) {
        return rowStyle + '__right'
      // 新規単価
      } else if (columnIndex === 9) {
        return rowStyle + '__right'
      // 評価単価（前日）現在値（リアル）
      } else if (columnIndex === 10) {
        return rowStyle + '__right'
      // 建玉金額計
      } else if (columnIndex === 11) {
        return rowStyle + '__right'
      // 評価額計（前日）評価額計（リアル）
      } else if (columnIndex === 12) {
        return rowStyle + '__right'
      // 諸費用計
      } else if (columnIndex === 13) {
        return rowStyle + '__right'
      // 評価損益（前日）評価損益（リアル）
      } else if (columnIndex === 14) {
        return rowStyle + '__right __bold'
      // 現金拘束金
      } else if (columnIndex === 15) {
        return rowStyle + '__right'
      // 取引
      } else if (columnIndex === 16) {
        return rowStyle + '__center'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.reflesh-button {
  justify-content: flex-end;
  padding: 10px 30px;
}
.shorter-link {
  margin-top: 2rem;
  margin-left: 17%;
}
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
:deep(.el-table) .cell {
  line-height: 16px;
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
:deep(.el-text) {
  font-size: 12px;
}
:deep(.ifa-button) {
  width: 88px;
}
:deep(.external-link) {
  padding: 0;
  color: blue;
  text-decoration: underline;
}
.cost-link {
  color: #092987;
  text-decoration:underline;
  text-underline-offset:0.1em;
  font-size:12px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}

:deep(.el-table .cell ) {
  padding: 0 8px !important;
}
</style>
