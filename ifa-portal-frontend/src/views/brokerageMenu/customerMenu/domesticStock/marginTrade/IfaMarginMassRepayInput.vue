<template>
  <div>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-row style="margin-top:0.5rem">
        <el-col :span="22">
          <el-row>
            <el-col :span="2">
              <span style="font-weight:bold">コード：</span>
            </el-col>
            <el-col :span="22">
              <span>{{ $_out(form.brandCode) + ' ' + $_out(form.brandName) }}</span>
            </el-col>
          </el-row>
          <el-row style="margin-top:0.5rem">
            <el-col :span="2">
              <span style="font-weight:bold">建玉区分：</span>
            </el-col>
            <el-col :span="3">
              <span
                :class="form.newCreditOrderType === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                style="font-weight:bold"
              ><span>{{ $_out($_getCodeValue('NEW_CREDIT_SELL_BUY_TYPE', 1, form.newCreditOrderType)) }}</span>&nbsp;({{ $_out($_getCodeValue('PAYMENT_DEADLINE', 1, form.paymentDeadline)) }})</span>
            </el-col>
          </el-row>
          <el-row style="margin-top:0.5rem">
            <el-col :span="2">
              <span style="font-weight:bold">建玉件数：</span>
            </el-col>
            <el-col :span="2">
              <span>{{ $_out( $_withCommaInteger(form.positionCount)) }}件</span>
            </el-col>
          </el-row>
        </el-col>
        <el-col
          :span="2"
          class="__right"
        >
          <ifa-button
            id="btnUpdate"
            text="更新"
            icon="RefreshRight"
            small
            action-id="SUB0202_0212-05_1#A010"
            action-type="requestAction"
            :request-model="a011RequestModel"
            @response-handler="responseHandlerInitializeReturnA010($event)"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- 建玉情報エリア -->
      <el-row style="margin-top: 1rem;">
        <el-table
          :data="editPositionTotal"
          :cell-class-name="tableCellClassNameSummary"
          style="width: 100%;font-size:12px;box-shadow: 1px 2px 2px #ddd;"
          :class="!isShowTokyoSecurity && !isShowPts ? 'empty_table' : ''"
        >
          <el-table-column
            :data="PositionTotal"
            label="市場"
            :min-width="200"
          >
            <template
              #default="scope"
            >
              <span>
                {{ scope.row.market }}
              </span>
            </template>
          </el-table-column>
          <el-table-column
            label="建代金"
            :min-width="200"
          >
            <template
              #default="scope"
            >
              <span>
                {{ $_out($_withCommaInteger(scope.$index === 0 && isShowTokyoSecurity ? scope.row.tokyoSecurityDetailTotalPrice : (isShowPts ? scope.row.ptsDetailTotalPrice : ''))) }}
              </span>
            </template>

          </el-table-column>
          <el-table-column :min-width="200">
            <template #header>
              <div>評価額合計（前日）</div>
              <div>評価額合計（リアル）</div>
            </template>
            <template
              #default="scope"
            >
              <div>
                {{ $_out($_withCommaInteger(scope.$index===0 && isShowTokyoSecurity ? scope.row.tokyoSecurityDetailValuationTotalPreviousDay : (isShowPts ? scope.row.ptsDetailValuationTotalPreviousDay : ''))) }}
              </div>
              <div>
                {{ $_out($_withCommaInteger(scope.$index===0 && isShowTokyoSecurity ? scope.row.tokyoSecurityDetailValuationTotalReal : (isShowPts ? scope.row.ptsDetailValuationTotalReal : ''))) }}
              </div>
            </template>
          </el-table-column>
          <el-table-column
            label="諸経費合計"
            :min-width="200"
          >
            <template
              #default="scope"
            >
              <span>
                {{ $_out($_withCommaInteger(scope.$index===0 && isShowTokyoSecurity ? scope.row.tokyoSecurityDetailCostTotalYen : (isShowPts ? scope.row.ptsDetailCostTotalYen : ''))) }}
              </span>
            </template>
          </el-table-column>
          <el-table-column :min-width="200">
            <template #header>
              <div>評価損益合計（前日）</div>
              <div>評価損益合計（リアル）</div>
            </template>
            <template #default="scope">
              <div
                :class="Number(domesticPositionValuationTotalPreviousDay(scope)) < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
              >{{ $_out($_signedWithCommaNoneZeroPadding(domesticPositionValuationTotalPreviousDay(scope)))
              }}
              </div>
              <div
                :class="Number(domesticPositionValuationTotalReal(scope)) < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
              >{{ $_out($_signedWithCommaNoneZeroPadding(domesticPositionValuationTotalReal(scope))) }}
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="220px"
        label-position="left"
        f
      >
        <el-row class="_radio_button_area">
          <!-- フォーム: 返済建玉指定方法 -->
          <el-col
            class="form_label"
          >
            <ifa-input-radio
              v-model="form.repayMethod"
              label="返済建玉指定方法："
              prop="repayMethod"
              :code-list-id="'REPAY_METHOD'"
              :disp-pattern="3"
              :select-pattern="1"
              @change="repayMethodA002"
            ></ifa-input-radio>
          </el-col>
        </el-row>

        <el-row class="_radio_button_area">
          <el-col
            class="form_label"
          >
            <ifa-input-radio
              v-model="form.repaymentOrder"
              :label="individualOrderMode ? '並替順序：' : '返済順序：'"
              prop="repaymentOrder"
              :code-list-id="'REPAY_ORDER'"
              :disp-pattern="1"
              :select-pattern="1"
              @change="repaymentOrderA003"
            ></ifa-input-radio>
          </el-col>

          <el-col
            v-if="!individualOrderMode"
            style="margin-top:2rem;"
            class="form_label"
          >
            <div
              style="display: flex; align-items: center;"
            >
              <ifa-input-quantity
                ref="inputTotalQuantity"
                v-model="form.totalQuantity"
                prop="totalQuantity"
                label="株数："
                :min="form.unit"
                :max="form.orderAbleStockQuantity ? form.orderAbleStockQuantity : 99999999"
                :support="true"
                :step="form.unit"
                :placeholder="' '"
                style="margin-top: 2px;"
                :initial-step="form.unit ? form.unit : 0"
                :domain="IfaStocks8DomainModel"
                unit="株"
              ></ifa-input-quantity>

              <div
                style="flex: 0 0 180px; padding-bottom:6px;"
              >
                <ifa-button
                  id="btnDesignationAllStockA004"
                  text="全株指定"
                  color="primary"
                  icon="CaretLeft"
                  small
                  style="margin-left:10px; font-size:12px;"
                  action-type="originalAction"
                  @app-action-handler="designationAllStockA004"
                ></ifa-button>
              </div>
              <div
                style="flex: 0 1 300px; margin-bottom:4px;"
              >
                注文可能株数/{{ form.orderAbleStockQuantity ? `${$_withCommaInteger(form.orderAbleStockQuantity)}株` : '-' }}

              </div>
            </div>
          </el-col>
        </el-row>

        <div v-if="individualOrderMode">
          <el-row>
            <div class="font-color__plus shorter-link">
              <span>●</span>
              <ifa-link
                :link-icon-image="null"
                :url-id="35"
                disp-name="返済期限設定銘柄"
              ></ifa-link>
            </div>
          </el-row>
          <el-row>
            <el-table
              :data="form.positionDetailList"
              :highlight-current-row="true"
              :cell-class-name="tableCellClassNameDetail"
              :cell-style="{height: '60px', width: '1000px'}"
              :stripe="true"
              :scrollbar-always-on="true"
              style="width: 100%; margin-top: 1rem;font-size:12px;"
              class="positionDetail_table"
            >
              <el-table-column
                label="市場"
                :min-width="60"
              >
                <template #default="scope">
                  <span>{{ $_out($_getCodeValue('NEW_MARKET', 1, scope.row.market)) }}</span>
                </template>
              </el-table-column>
              <el-table-column :min-width="100">
                <template #header>
                  <div>新規建日</div>
                  <div>返済期限</div>
                </template>
                <template #default="scope">
                  <div v-if="scope.row.dueDateShortenClassification === '1'">
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
                :min-width="100"
              >
                <template #header>
                  <div>親株新規</div>
                  <div>約定日</div>
                </template>
                <template #default="scope">
                  <div>
                    <div>{{ scope.row.parentStockTradeDate ? $_getFormattedDateValue(scope.row.parentStockTradeDate) : '----/--/--' }}</div>
                  </div>
                </template>
              </el-table-column>
              <el-table-column :min-width="80">
                <template #header>
                  <div>預り区分</div>
                  <div>(担保)</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_getCodeValue('SPECIFIC_POSITION_TYPE', 1, scope.row.accountType)) }}</div>
                  <div v-if="scope.row.security"> {{ scope.row.security === '0' ? '（--%）':'（' + scope.row.security + '%）' }}</div>
                  <div v-else>（--）</div>
                  <div v-if="scope.row.securityClassification !== ' '">{{ $_out($_getCodeValue('COLLATERAL_REGULATIONS', 1, scope.row.securityClassification)) }}</div>
                </template>
              </el-table-column>
              <el-table-column :min-width="170">
                <template #header>
                  <div>建株数</div>
                  <div>(注文中)</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_withCommaInteger(scope.row.contPositionTotal)) }}</div>
                  <div>({{ $_out($_withCommaInteger(scope.row.unactualQuantity)) }})</div>
                </template>
              </el-table-column>
              <el-table-column :min-width="320">
                <template #header>
                  <div>注文株数</div>
                  <div>売買単位{{ form.unit }} 株</div>
                </template>
                <template #default="scope">
                  <div
                    style="display: inlie-block; margin-top: 24px;"
                  >
                    <ifa-input-quantity
                      ref="quantityParent"
                      v-model="scope.row.quantity"
                      :prop="'positionDetailList.' + scope.$index + '.quantity'"
                      :min="form.unit"
                      :max="scope.row.contPositionTotal && scope.row.unactualQuantity ? Number(scope.row.contPositionTotal) - Number(scope.row.unactualQuantity) : 99999999"
                      :support="true"
                      :step="form.unit"
                      :placeholder="' '"
                      :initial-step="form.unit ? form.unit : 0"
                      :domain="IfaStocks8DomainModel"
                      :allow-zero="true"
                      unit="株"
                      @change="getOrderingTotalQuantityA009"
                    ></ifa-input-quantity>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                label="全株指定"
                :min-width="140"
              >
                <template #default="scope">
                  <ifa-button
                    id="btnDesignationAllStockA005"
                    text="全株指定"
                    color="primary"
                    icon="CaretLeft"
                    small
                    style="font-size:12px"
                    action-type="originalAction"
                    @app-action-handler="designationAllStockA005(scope.row)"
                  ></ifa-button>
                </template>
              </el-table-column>
              <el-table-column
                label="建単価"
                :min-width="150"
              >
                <template #default="scope">
                  <span>{{ $_out($_withCommaNoneZeroPadding(scope.row.newPrice)) }}</span>
                </template>
              </el-table-column>
              <el-table-column :min-width="140">
                <template #header>
                  <div>評価単価（前日）</div>
                  <div>現在値（リアル）</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_withCommaInteger(scope.row.dayBeforeValuationPrice)) }}</div>
                  <div>{{ $_out($_withCommaInteger(scope.row.currentPriceReal)) }}</div>
                </template>
              </el-table-column>
              <el-table-column
                label="建代金"
                :min-width="160"
              >
                <template #default="scope">
                  <span>{{ $_out($_withCommaInteger(scope.row.openInterestAmount)) }}</span>
                </template>
              </el-table-column>
              <el-table-column :min-width="160">
                <template #header>
                  <div>評価額（前日）</div>
                  <div>評価額（リアル）</div>
                </template>
                <template #default="scope">
                  <div>{{ $_out($_withCommaInteger(scope.row.valuationPreviousDay)) }}</div>
                  <div>{{ $_out($_withCommaInteger(scope.row.valuationReal)) }}</div>
                </template>
              </el-table-column>
              <el-table-column
                label="諸経費"
                :min-width="160"
              >
                <template #default="scope">
                  <el-link
                    disable-transitions
                    class="cost-link"
                    @click="costA006(scope.row)"
                  >{{ $_out($_withCommaInteger(scope.row.cost)) }}
                  </el-link>
                </template>
              </el-table-column>
              <el-table-column :min-width="170">
                <template #header>
                  <div>評価損益（前日）</div>
                  <div>評価損益（リアル）</div>
                </template>
                <template #default="scope">
                  <div
                    :class="Number(scope.row.profitAndLossPrevDay) < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                    disable-transitions
                  >{{ $_out($_signedWithCommaNoneZeroPadding(scope.row.profitAndLossPrevDay)) }}
                  </div>
                  <div
                    :class="Number(scope.row.profitAndLossReal) < 0 ? 'font-color__minus bold' : 'font-color__plus bold'"
                    disable-transitions
                  >{{ $_out($_signedWithCommaNoneZeroPadding(scope.row.profitAndLossReal)) }}
                  </div>
                </template>
              </el-table-column>
              <el-table-column :min-width="160">
                <template #header>
                  <div>現金</div>
                  <div>拘束金</div>
                </template>
                <template #default="scope">
                  <span>{{ $_out($_withCommaInteger(scope.row.cashBond)) }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-row>

          <el-row class="_summary_table_area">
            <el-col
              :span="3"
              class="_summary_table__header"
            >
              <span>返済順序</span>
            </el-col>
            <el-col
              :span="3"
              class="_summary_table__body __center"
            >
              <span>{{ $_out($_getCodeValue('REPAY_ORDER', 1, form.repaymentOrder)) }}</span>
            </el-col>
            <el-col
              :span="4"
              class="_summary_table__header"
            >
              <span class="__bold">注文可能株数</span>
            </el-col>
            <el-col
              :span="5"
              class="_summary_table__body __right __padding_right"
            >
              <span>{{ form.orderAbleStockQuantity ? `${$_withCommaInteger(form.orderAbleStockQuantity)}株` : '-' }}</span>
            </el-col>
            <el-col
              :span="4"
              class="_summary_table__header"
            >
              <span class="__bold">注文株数合計</span>
            </el-col>
            <el-col
              :span="5"
              class="_summary_table__body __right __padding_right"
            >
              <span>{{ orderingTotalQuantity ? `${$_withCommaInteger(orderingTotalQuantity)}株` : '-' }}</span>
            </el-col>
          </el-row>
        </div>

        <el-row
          v-show="individualOrderMode"
        >
          <el-col :span="22">
            <div style="display: table; text-align: left;">
              <div>預り区分（担保）欄表記</div>
              <div>{{ $_out($_getCodeValue('COLLATERAL_REGULATIONS', 2, '1')) }}</div>
              <div>{{ $_out($_getCodeValue('COLLATERAL_REGULATIONS', 2, '2')) }}</div>
              <div>{{ $_out($_getCodeValue('COLLATERAL_REGULATIONS', 2, '3')) }}</div>
            </div>
          </el-col>
        </el-row>
        <el-row
          class="button__wrapper"
        >
          <el-col
            :span="2"
            style="text-align: right;"
          >
            <ifa-button
              v-if="!individualOrderMode"
              id="btnInput"
              :disabled="editDisable"
              text="注文入力"
              color="primary"
              action-type="originalAction"
              @app-action-handler="orderInputA007"
            ></ifa-button>
            <ifa-button
              v-if="individualOrderMode"
              id="btnInputIndividualOrderMode"
              :disabled="isDisable"
              text="注文入力"
              color="primary"
              action-type="originalAction"
              @app-action-handler="orderInputA007"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </div>

    <!-- ダイアログ -->
    <ifa-domestic-position-detail
      :is-visible="dialogVisible"
      :form-data="domesticPositionDetailModel"
      @close-modal="handleCloseModal"
    ></ifa-domestic-position-detail>
    <!--初期化-->
    <ifa-requester
      id="IfaMarginMassRepayInputInitializeA001"
      action-id="SUB0202_0212-05_1#A001"
      action-type="requestAction"
      :request-model="a001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>
    <!-- A003 -->
    <ifa-requester
      id="IfaMarginMassRepayInputRepaymentOrderA003"
      action-id="SUB0202_0212-05_1#A003"
      action-type="requestAction"
      :request-model="a003RequestModel"
      @response-handler="responseHandlerRepaymentOrderA003($event)"
    ></ifa-requester>
    <!-- A006 -->
    <ifa-requester
      id="IfaMarginMassRepayInputA006"
      action-id="SUB07-05#X001"
      action-type="requestAction"
      :request-model="X001RequestModel"
      @response-handler="responseHandlerA006"
    ></ifa-requester>
    <!--A010-->
    <ifa-requester
      id="IfaMarginMassRepayInputInitializeReturnA010"
      action-id="SUB0202_0212-05_1#A010"
      action-type="requestAction"
      :request-model="a010RequestModel"
      @response-handler="responseHandlerInitializeReturnA010($event)"
      @response-error-handler="responseErrorHandlerInitializeReturnA010($event)"
    ></ifa-requester>
    <!-- 信用返済注文入力 A001(初期化) -->
    <ifa-requester
      id="IfaMassRepayOrderIfaMarginRepayOrderInputInitializeA001"
      action-id="SUB0202_0212-04_1#A001"
      action-type="requestAction"
      :request-model="marginRepayOrderA001RequestModel"
      @response-handler="responseHandlerMarginRepayOrderInputA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaDomesticPositionDetail from '@/views/common/IfaDomesticPositionDetail'
import { IfaDomesticPositionDetailX001RequestModel } from '@/views/brokerageMenu/customerMenu/accountManage/balanceInfo/js/IfaDomesticPositionDetailX001RequestModel'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaStocks8DomainModel from '@/resource/domain/IfaStocks8DomainModel.json'
import { IfaMarginMassRepayInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginMassRepayInputA001RequestModel.js'
import { IfaMarginMassRepayInputA003RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginMassRepayInputA003RequestModel.js'
import { IfaMarginMassRepayInputA010RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginMassRepayInputA010RequestModel.js'
import { IfaMarginMassRepayInputA011RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginMassRepayInputA011RequestModel.js'
import { IfaMarginRepayOrderInputA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginRepayOrderInputA001RequestModel.js'
import { IfaMarginMassRepayInputFormModel } from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/js/IfaMarginMassRepayInputFormModel.js'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaDomesticPositionDetail,
    screenTitle
  },
  props: {
    params: { type: Object, required: true }
  },
  emits: ['transition-margin-repay-order-input', 'initializeError'],
  data() {
    return {
      isDisable: true,
      X001RequestModel: {},
      domesticPositionDetailModel: {},
      orderingTotalQuantity: null,
      domesticPositionValuationTotalPreviousDay: this.getDomesticPositionValuationTotalPreviousDay,
      domesticPositionValuationTotalReal: this.getDomesticPositionValuationTotalReal,
      formRef: {},
      form: new IfaMarginMassRepayInputFormModel(),
      IfaStocks8DomainModel: IfaStocks8DomainModel,
      dialogVisible: false,
      individualOrderMode: false,
      isQuantityValid: false,
      rules: {
        totalQuantity: [
          { required: true, message: getMessage('errors.required', ['株数']), trigger: 'blur' }
        ],
        repayMethod: [
          { required: true, message: getMessage('errors.selected', ['返済建玉指定方法']), trigger: 'change' }
        ],
        repaymentOrder: [
          { required: true, message: getMessage('errors.selected', this.individualOrderMode ? ['並替順序'] : ['返済順序']), trigger: 'change' }
        ]
      },
      // 建玉情報エリア
      PositionTotal: [
        {
          market: '東証',
          tokyoSecurityDetailTotalPrice: '',
          tokyoSecurityDetailValuationTotalPreviousDay: '',
          tokyoSecurityDetailValuationTotalReal: '',
          tokyoSecurityDetailCostTotalYen: '',
          tokyoSecurityDetailDomesticPositionValuationTotalPreviousDay: '',
          tokyoSecurityDetailDomesticPositionValuationTotalReal: ''
        },
        {
          market: 'PTS',
          ptsDetailTotalPrice: '',
          ptsDetailValuationTotalPreviousDay: '',
          ptsDetailValuationTotalReal: '',
          ptsDetailCostTotalYen: '',
          ptsDetailDomesticPositionValuationTotalPreviousDay: '',
          ptsDetailDomesticPositionValuationTotalReal: ''
        }
      ]
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    editDisable() {
      return Number(this.form.totalQuantity) <= 0
    },
    editPositionTotal() {
      let PositionTotal = this.PositionTotal
      if (!this.isShowTokyoSecurity) PositionTotal = PositionTotal.filter(obj => obj.market !== '東証')
      if (!this.isShowPts) PositionTotal = PositionTotal.filter(obj => obj.market !== 'PTS')
      return PositionTotal
    },

    isShowTokyoSecurity() {
      return Number(this.form.positionCountTokyoSecurity) > 0
    },
    isShowPts() {
      return Number(this.form.positionCountPts) > 0
    },

    a001RequestModel() {
      return new IfaMarginMassRepayInputA001RequestModel(this.params)
    },
    a003RequestModel() {
      return new IfaMarginMassRepayInputA003RequestModel(this.form)
    },
    a010RequestModel() {
      return new IfaMarginMassRepayInputA010RequestModel(this.params)
    },
    a011RequestModel() {
      return new IfaMarginMassRepayInputA011RequestModel(this.form)
    },
    marginRepayOrderA001RequestModel() {
      return new IfaMarginRepayOrderInputA001RequestModel(this.form)
    }
  },
  watch: {
    'form.positionDetailList': {
      handler(newValue) {
        newValue.forEach((obj, index) => {
          this.getOrderingTotalQuantityA009()
        })
      },
      deep: true
    },
    'orderingTotalQuantity': {
      handler(newValue) {
        if (newValue && newValue > 0 && !this.isQuantityValid) {
          this.isDisable = false
        } else {
          this.isDisable = true
        }
      },
      deep: true
    }
  },
  methods: {
    initializeReturnA010() {
      // 初期化（戻り）
      this.$nextTick(() => {
        document.getElementById('IfaMarginMassRepayInputInitializeReturnA010').click()
        this.formRef = this.$refs.form
      }
      )
    },
    responseHandlerInitializeReturnA010(data) {
      // ⑧画面の初期表示を行う
      Object.assign(this.form, data.dataList[0])

      // レスポンスデータと構造が異なるため建玉一覧エリア用にレスポンス値をコピーする
      for (let i = 0; i < this.PositionTotal.length; i++) {
        Object.assign(this.PositionTotal[i], this.form)
      }
      this.repayMethodA002(this.form.repayMethod)
    },
    responseErrorHandlerInitializeReturnA010(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    onShow() {
      // 初期化
      this.form = new IfaMarginMassRepayInputFormModel()
      this.individualOrderMode = false
      this.isDisable = true
      this.$nextTick(() => {
        document.getElementById('IfaMarginMassRepayInputInitializeA001').click()
        this.formRef = this.$refs.form
      }
      )
    },
    responseHandlerInitializeA001(data) {
      // ⑧画面の初期表示を行う
      Object.assign(this.form, data.dataList[0])

      // レスポンスデータと構造が異なるため建玉一覧エリア用にレスポンス値をコピーする
      for (let i = 0; i < this.PositionTotal.length; i++) {
        Object.assign(this.PositionTotal[i], this.form)
      }
    },
    responseErrorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    costA006(row) {
      this.X001RequestModel = new IfaDomesticPositionDetailX001RequestModel(row)
      this.X001RequestModel.brandCode = this.form.brandCode
      this.X001RequestModel.brandName = this.form.brandName
      this.X001RequestModel.openTradeKbn = this.form.newCreditOrderType
      this.X001RequestModel.paymentDeadline = this.form.paymentDeadline
      this.X001RequestModel.butenCode = this.customerInfo.butenCode
      this.X001RequestModel.accountNumber = this.customerInfo.accountNumber
      this.X001RequestModel.newTradeDate = row.constructionDate
      this.X001RequestModel.batchIndividualDisplayFlag = '1'
      this.$nextTick(() => {
        document.getElementById('IfaMarginMassRepayInputA006').click()
      })
    },
    responseHandlerA006(response) {
      this.domesticPositionDetailModel = Object.assign(this.domesticPositionDetailModel, response.dataList[0])
      this.dialogVisible = true
    },
    repayMethodA002(repayMethod) {
      if (repayMethod === '0') {
        this.individualOrderMode = false
      } else {
        this.individualOrderMode = true
      }
    },
    repaymentOrderA003() {
      // 返済方法=個別指定の場合、以下の処理を行う。
      if (this.form.repayMethod === '1') {
        // サーバ処理を呼ぶ
        this.$nextTick(() => {
          document.getElementById('IfaMarginMassRepayInputRepaymentOrderA003').click()
        }
        )
        // 建玉合計エリア、建玉個別指定エリアを更新し、初期値を表示する
        for (let i = 0; i < this.form.positionDetailList.length; i++) {
          this.form.positionDetailList[i].quantity = ''
        }
        this.orderingTotalQuantity = null
        this.form.totalQuantity = null

        return
      }
    },
    responseHandlerRepaymentOrderA003(data) {
      // ⑧画面の初期表示を行う
      Object.assign(this.form, data.dataList[0])

      // レスポンスデータと構造が異なるため建玉一覧エリア用にレスポンス値をコピーする
      for (let i = 0; i < this.PositionTotal.length; i++) {
        Object.assign(this.PositionTotal[i], this.form)
      }
    },
    getDomesticPositionValuationTotalPreviousDay(scope) {
      if (scope.$index === 0 && this.isShowTokyoSecurity) {
        return scope.row.tokyoSecurityDetailDomesticPositionValuationTotalPreviousDay
      } else if (this.isShowPts) {
        return scope.row.ptsDetailDomesticPositionValuationTotalPreviousDay
      }
    },
    getDomesticPositionValuationTotalReal(scope) {
      if (scope.$index === 0 && this.isShowTokyoSecurity) {
        return scope.row.tokyoSecurityDetailDomesticPositionValuationTotalReal
      } else if (this.isShowPts) {
        return scope.row.ptsDetailDomesticPositionValuationTotalReal
      }
    },
    getOrderingTotalQuantityA009() {
      this.orderingTotalQuantity = 0
      this.isQuantityValid = false
      // それぞれの注文株数を合計する
      for (let i = 0; i < this.form.positionDetailList.length; i++) {
        const quantity = this.form.positionDetailList[i].quantity
        if (quantity && Number(quantity) && Number(quantity) <= 99999999 && Number.isInteger(Number(quantity) / Number(this.form.unit))) {
          // 入力チェックエラーがない注文株数を合計
          this.orderingTotalQuantity += quantity ? Number(quantity) : 0
        } else if (quantity && Number(quantity) !== 0) {
          // 入力済みかつ一項目でも入力チェックエラーがある場合、注文入力ボタンを非活性とする
          this.isQuantityValid = true
        }
      }
    },
    // 戻るボタン
    handleCloseModal() {
      this.dialogVisible = false
    },
    // 注文入力ボタン押下時処理
    async orderInputA007() {
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 遷移先のリクエスト項目名に合わせ新規売買区分をセットする
          this.form.openTradeKbn = this.form.newCreditOrderType

          // 信用返済注文入力A001を実行
          this.$nextTick(() => {
            document.getElementById('IfaMassRepayOrderIfaMarginRepayOrderInputInitializeA001').click()
          })
        }
      })
    },
    responseHandlerMarginRepayOrderInputA001(response) {
      // 信用返済注文入力画面へ遷移
      this.$emit('transition-margin-repay-order-input', response)
    },
    responseHandlerInitializeA011(data) {
      Object.assign(this.form, data.dataList[0])
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
        return '__right __bold'
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
      // 注文株数 売買単位/ 100株
      } else if (columnIndex === 5) {
        return '__right'
      // 全株指定
      } else if (columnIndex === 6) {
        return '__right'
      // 新規単価
      } else if (columnIndex === 7) {
        return '__right'
      // 評価単価（前日）現在値（リアル）
      } else if (columnIndex === 8) {
        return '__right'
      // 建単価
      } else if (columnIndex === 9) {
        return '__right'
      // 評価額（前日）評価額（リアル）
      } else if (columnIndex === 10) {
        return '__right'
      // 諸経費
      } else if (columnIndex === 11) {
        return '__right'
      // 評価損益（前日）評価損益（リアル）
      } else if (columnIndex === 12) {
        return '__right'
      // 現金拘束金
      } else if (columnIndex === 13) {
        return '__right'
      }
    },
    designationAllStockA005(positionDetailList) {
      positionDetailList.quantity = Number(positionDetailList.contPositionTotal) - Number(positionDetailList.unactualQuantity)
      positionDetailList.quantity = String(positionDetailList.quantity)
      this.getOrderingTotalQuantityA009(Number(positionDetailList.quantity))
    },
    designationAllStockA004() {
      this.form.totalQuantity = this.form.orderAbleStockQuantity
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/styles/orderStatusList.scss';
@import '@/styles/table.scss';
.button__wrapper {
   margin-top: 0.5rem;
   display: flex;
   justify-content: flex-end;
   padding:0 2rem 0 0.2rem 0;
}
._radio_button_area {
  margin-top: 1rem;
  border: 3px solid #eee;
  padding: 0.2rem 0 0.2rem 1rem
}
._summary_table_area {
  margin-top: 1rem;
  border: 3px solid #eee;
}
._summary_table__header {
  background-color:#004485;
  color:#ffffff;
  text-align:center;
  font-weight:bold;
  padding: 0.4rem 0 0.4rem 0
}
._summary_table__body {
  margin-top:6px;
}
.__padding_right {
  padding-right: 0.5rem;
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
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.empty_table) .el-table__body-wrapper{
  display: none!important;
}
.form_label :deep(.el-form-item__label)  {
  width: 140px;
  margin-left: 0px;
  justify-content: flex-start;
}
.shorter-link {
  margin-top: 1rem;
  margin-left: 5%;
}
:deep(.positionDetail_table){
  .el-table__row {
    .cell {
      word-break: normal;
    }
    .no-label{
      height: 60px;
    .el-form-item__content{
      height: 50px;
    }
  }
  }
  .el-form-item__error{
  padding-left: 20px;
  width: calc(100% - 145px);
  white-space: nowrap;
}
}
:deep(.el-form-item__error){
  width: calc(100% - 145px);
  white-space: nowrap;
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
</style>
