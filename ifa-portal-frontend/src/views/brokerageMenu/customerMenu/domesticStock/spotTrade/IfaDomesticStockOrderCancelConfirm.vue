<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.title.name"
      :show-close="false"
      :center="true"
      :before-close="backA003"
      :close-on-click-modal="false"
      :class="classObject.classObjBgColor"
      width="1200px"
      @open="onShow"
    >
      <!-- ヘッダ -->
      <el-row style="padding-top: 1.5rem;">
        <el-col
          :offset="1"
          :span="22"
          style="text-align: right;"
        >
          <ifa-button
            id="btnBack"
            text="戻る"
            color="secondary"
            action-type="originalAction"
            style="padding-right: 0;"
            @app-action-handler="backA003"
          ></ifa-button>
        </el-col>
        <!-- エラー/警告表示 -->
        <ifa-message-area
          :main-messages="['取消はまだ完了していません。画面下の注文取消ボタンを押下してください。']"
        ></ifa-message-area>
      </el-row>
      <!-- 顧客情報 -->
      <el-row
        style="font-weight: bold; color: black;"
      >
        <el-col
          :offset="1"
        >
          <span>{{ $_out(customerInfo.butenCode) }}-{{ $_out(customerInfo.accountNumber) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.3rem;"
        class="_bold_black_l"
      >
        <el-col
          :offset="1"
          :span="22"
          style="font-size: 20px;"
          class="_bold_black_l"
        >

          <el-icon style="position: relative; top: 3px;">
            <OfficeBuilding v-if="customerInfo.corporationType == '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
          <span>{{ $_out(customerInfo.customerNameKanji) }}（{{ $_out(customerInfo.customerNameKana) }}）</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>
      <!-- 注文内容 -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding-top: 0.7rem;"
        >
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <!--見出し、注文種別、取引種別、市場、数量は共通して表示-->
            <el-row>
              <el-row
                class="_bold_black_m"
                style="padding-top: 0.2rem; padding-left: 1rem;"
              >
                <span>ご注文内容（復唱項目）</span>
                <!--IFD、IFDOCOの時はラベル名変更-->
                <span v-if="cancelData.orderKind === '3' || cancelData.orderKind === '4'">：IFD1</span>
              </el-row>
              <hr style="width: 100%">

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="overflow-wrap: break-word;">[{{ $_out(cancelData.brandCode) }}]&nbsp;{{ $_out(cancelData.brandName) }}</span>
                </el-col>
              </el-row>
              
              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文種別</span>
                </el-col>
                <el-col
                  :span="16"
                  style="font-size:16px;font-weight: bold"
                >
                  <span v-if="cancelData.sasinariHouhou === '3'">逆指値注文</span>
                  <ifa-text
                    v-else
                    code-list-id="ORDER_CLASS"
                    :disp-pattern="2"
                    :code-key="cancelData.orderKind"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span v-if="!cancelData.orderKind">-</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">取引種別</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    :class="classObject.classObjFontColor"
                    code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                    :disp-pattern="1"
                    :code-key="cancelData.tradeKbn"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span v-if="!cancelData.tradeKbn">-</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">市場</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    code-list-id="SELECT_MARKET"
                    :disp-pattern="1"
                    :code-key="cancelData.market"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span v-if="!cancelData.market">-</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">数量</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span
                    style="font-size: 16px;"
                  >{{ $_out($_withCommaInteger(cancelData.unTradeQuantity)) }}株</span>
                </el-col>
              </el-row>
            </el-row>

            <!--通常/逆指値、IFD、IFDOCOの時価格欄は同じ構成-->
            <el-row
              v-if="cancelData.orderKind === '1' || cancelData.orderKind === '3' || cancelData.orderKind === '4'"
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span
                  v-if="cancelData.orderKind === '1'"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="cancelData.sasinariHouhou"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span style="font-size: 16px;">
                    {{ getPrice(priceParam, cancelData.tradeKbn) }}</span>
                </span>
                <span
                  v-if="(cancelData.orderKind === '3' || cancelData.orderKind === '4')"
                  style="white-space: pre-wrap; font-size: 16px;"
                >
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="cancelData.ifd1SasinariHouhou"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span style="font-size: 16px;">
                    {{ getPrice(ifd1PriceParam, cancelData.tradeKbn) }}</span>
                </span>
              </el-col>
            </el-row>

            <!--空のレスポンスが返ってきたときに表示-->
            <el-row v-if="!cancelData.orderKind">
              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">価格</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>-</span>
                </el-col>
              </el-row>
            </el-row>

            <!-- OCOの時価格欄-->
            <el-row v-if="cancelData.orderKind === '2'">
              <el-row
                v-if="cancelData.workingStatus === 'false'"
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap; font-size: 16px;">{{ getOco1Price() }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;  font-size: 16px;">{{ getOco2Price(cancelData.tradeKbn, cancelData.oco1SasinariJyouken) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分、受注日時は共通して表示-->
            <el-row>
              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文期間</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span
                    style="font-size: 16px;"
                  >{{ $_out($_getFormattedDateValue(cancelData.yukoKigen)) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">預り区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    v-if="customerInfo.jrIsaContractType === '1'"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="1"
                    :code-key="cancelData.notSpecificDepositTradeType"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <ifa-text
                    v-else
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="2"
                    :code-key="cancelData.notSpecificDepositTradeType"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span v-if="!cancelData.notSpecificDepositTradeType">-</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">手数料区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    code-list-id="PRE_CONTRACT_DOC_CODE"
                    :disp-pattern="1"
                    :code-key="customerInfo.customerAttribute"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span class="fee-label">（電話手数料）</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">受注日時</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span
                    style="font-size: 16px;"
                  >{{ $_out($_getFormattedDateTimeValue(cancelData.orderDayTime, 'datetime12')) }}</span>
                </el-col>
              </el-row>
            </el-row>

          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- 注文内容 IFD2（注文種別が IFD また IFDOCO の時のみ表示）-->
      <el-row v-if="cancelData.orderKind == '3' || cancelData.orderKind == '4'">
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <el-row
              class="_bold_black_m"
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）：IFD2</span>
            </el-row>
            <hr>

            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span style="overflow-wrap: break-word;">[{{ $_out(cancelData.brandCode) }}]&nbsp;{{ $_out(cancelData.brandName) }}</span>
              </el-col>
            </el-row>
            
            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">注文種別</span>
              </el-col>
              <el-col
                :span="16"
                style="font-size:16px;font-weight: bold"
              >
                <span v-if="cancelData.sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="cancelData.orderKind"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  class="font-color__minus bold"
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  code-key="2"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">市場</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="cancelData.market"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">数量</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span
                  style="font-size: 16px;"
                >
                  {{ $_out($_withCommaInteger(cancelData.unTradeQuantity)) }}株</span>
              </el-col>
            </el-row>

            <!--IFDの時価格欄-->
            <el-row
              v-if="cancelData.orderKind == '3'"
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="cancelData.ifd2SasinariHouhou"
                  style="font-size: 16px;"
                ></ifa-text>
                <span style="white-space: pre-wrap;font-size: 16px;">{{ getPrice(ifd2PriceParam, '2') }}</span>
              </el-col>
            </el-row>

            <!-- IFDOCOの時価格欄-->
            <el-row v-if="cancelData.orderKind === '4'">
              <el-row
                v-if="cancelData.workingStatus === 'false'"
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap; font-size: 16px;">{{ getOco1Price() }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;  font-size: 16px;">{{ getOco2Price('2', cancelData.oco1SasinariJyouken) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分、受注日時は共通して表示-->
            <el-row>
              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文期間</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span
                    style="font-size: 16px;"
                  >{{ $_out($_getFormattedDateValue(cancelData.ifd2Limit)) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">預り区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    v-if="customerInfo.jrIsaContractType === '1'"
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="1"
                    :code-key="cancelData.notSpecificDepositTradeType"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <ifa-text
                    v-else
                    code-list-id="DOMESTIC_DEPOSIT_TYPE"
                    :disp-pattern="2"
                    :code-key="cancelData.notSpecificDepositTradeType"
                    style="font-size: 16px;"
                  ></ifa-text>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">手数料区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    code-list-id="PRE_CONTRACT_DOC_CODE"
                    :disp-pattern="1"
                    :code-key="customerInfo.customerAttribute"
                    style="font-size: 16px;"
                  ></ifa-text>
                  <span class="fee-label">（電話手数料）</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">受注日時</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span
                    style="font-size: 16px;"
                  >{{ $_out($_getFormattedDateTimeValue(cancelData.orderDayTime, 'datetime12')) }}</span>
                </el-col>
              </el-row>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <el-col
          :offset="1"
          style="margin-top: 1rem"
        >
          <span class="dialog-footer">
            <ifa-button
              id="btnOrderRegister"
              text="注文取消"
              :disabled="userInfo.medUsers.privId === '1' || userInfo.medUsers.privId === '2'"
              action-type="requestAction"
              action-id="SUB0202_0208-04_1#A002"
              style="padding-left: 0;"
              :request-model="IfaDomesticStockOrderCancelConfirmA002RequestModel"
              @response-handler="orderCancellationA002($event)"
            ></ifa-button>
          </span>
        </el-col>
      </el-row>

    </el-dialog>
    <ifa-domestic-stock-order-cancel-complete
      :is-visible="cancelCompleteIsVisible"
      :cancel-data="completeData"
      @to-order-status-list="handleToOrderStatusList"
    ></ifa-domestic-stock-order-cancel-complete>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaDomesticStockOrderCancelComplete from './IfaDomesticStockOrderCancelComplete'
import { IfaDomesticStockOrderCancelConfirmA002RequestModel } from './js/IfaDomesticStockOrderCancelConfirmA002RequestModel.js'
import { IfaDomesticStockOrderCancelConfirmFormModel } from './js/IfaDomesticStockOrderCancelConfirmFormModel.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaDomesticStockOrderCancelComplete,
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    cancelData: { type: Object, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'initialize-order-status-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDomesticStockOrderCancelConfirmFormModel(),
      priceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: ''
      },
      ifd1PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: ''
      },
      ifd2PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        stopOrderPriceText2: '',
        gyakusasiHouhou: '',
        price: ''
      },
      cancelCompleteIsVisible: false,
      classObject: {
        classObjBgColor: '',
        classObjFontColor: ''
      },
      // 完了画面に渡すデータ
      completeData: {}
    }
  },
  computed: {
    IfaDomesticStockOrderCancelConfirmA002RequestModel() { return new IfaDomesticStockOrderCancelConfirmA002RequestModel(this.form) },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.cancelData)
      this.form.ecOrderNo = this.ecOrderNo
      // gePriceに渡すパラメータへ値をセット
      Object.assign(this.priceParam, this.cancelData)
      this.ifd1PriceParam = {
        sasinariHouhou: this.cancelData.ifd1SasinariHouhou,
        sasinariJyouken: this.cancelData.ifd1SasinariJyouken,
        triggerPrice: this.cancelData.ifd1TriggerPrice,
        stopOrderPriceText2: this.cancelData.ifd1StopOrderPriceText2,
        gyakusasiHouhou: this.cancelData.ifd1GyakusasiHouhou,
        price: this.cancelData.ifd1Price
      }
      this.ifd2PriceParam = {
        sasinariHouhou: this.cancelData.ifd2SasinariHouhou,
        sasinariJyouken: this.cancelData.ifd2SasinariJyouken,
        triggerPrice: this.cancelData.ifd2TriggerPrice,
        stopOrderPriceText2: this.cancelData.ifd2StopOrderPriceText2,
        gyakusasiHouhou: this.cancelData.ifd2GyakusasiHouhou,
        price: this.cancelData.ifd2Price
      }
      // 背景、取引種別の色
      if (this.cancelData.tradeKbn === '1') {
        // 買付
        this.classObject.classObjFontColor = 'font-color__plus bold'
        if (this.cancelData.orderKind === '1' || this.cancelData.orderKind === '2') {
          this.classObject.classObjBgColor = 'buy-background-color'
        } else if (this.cancelData.orderKind === '3' || this.cancelData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
        }
      } else if (this.cancelData.tradeKbn === '2') {
        // 売却
        this.classObject.classObjFontColor = 'font-color__minus bold'
        if (this.cancelData.orderKind === '1' || this.cancelData.orderKind === '2') {
          this.classObject.classObjBgColor = 'sell-background-color'
        } else if (this.cancelData.orderKind === '3' || this.cancelData.orderKind === '4') {
          this.classObject.classObjBgColor = 'ifd-background-color'
        }
      }
    },
    // 取消発注ボタン
    orderCancellationA002(response) {
      this.completeData = response.dataList[0]
      // 完了画面に遷移
      this.$emit('close-modal')
      this.cancelCompleteIsVisible = true
    },
    // 戻るボタン
    backA003() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    handleToOrderStatusList() {
      this.cancelCompleteIsVisible = false
      this.$emit('initialize-order-status-list')
    },
    // 価格欄に表示する内容を生成する
    getPrice(param, tradeKbn) {
      if (param.sasinariHouhou === '1') {
      // 指値
        const str = this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        if (param.sasinariJyouken !== ' ') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken)) +
               '/' + str
        } else {
          return `\n` + str
        }
      } else if (param.sasinariHouhou === '2') {
      // 成行
        if (param.sasinariJyouken !== 'N') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, param.sasinariJyouken))
        }
      } else if (param.sasinariHouhou === '3') {
      // 逆指値
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(param.triggerPrice)) + '円'
        if (param.stopOrderPriceText2 === ' ') {
        // 指定なし
          switch (tradeKbn) {
            case '1':
              str += '以上になった時点で'
              break
            case '2':
              str += '以下になった時点で'
              break
          }
        } else if (param.stopOrderPriceText2 === '0' || param.stopOrderPriceText2 === '1') {
        // 以上、以下の場合
          str += this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, param.stopOrderPriceText2)) +
                 'になった時点で'
        }
        if (param.gyakusasiHouhou === '1') {
        // 執行方法（逆指値）が指値の時
          if (param.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        } else if (param.gyakusasiHouhou === '2') {
        // 執行方法（逆指値）が成行の時
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, param.sasinariJyouken))
        }
        return `\n` + str + 'で執行'
      }
      return ''
    },
    getOco1Price() {
      let str = this.$_out(this.$_getCodeValue('EXECUTE_METHOD', 1, this.cancelData.oco1SasinariHouhou))
      str += `\n`
      if (this.cancelData.oco1SasinariJyouken !== ' ') {
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.cancelData.oco1SasinariJyouken)) + '/'
      }
      return str + this.$_out(this.$_withCommaNoneZeroPadding(this.cancelData.oco1Price)) + '円'
    },
    getOco2Price(tradeKbn, sasinariJyouken) {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.cancelData.oco2TriggerPrice)) + '円'
      if (this.cancelData.oco2StopOrderPriceText2 === ' ') {
      // 指定なし
        switch (tradeKbn) {
          case '1':
            str += '以上になった時点でOCO1注文を以下の執行条件に訂正'
            break
          case '2':
            str += `以下になった時点でOCO1注文を以下の執行条件に訂正`
            break
        }
      } else if (this.cancelData.oco2StopOrderPriceText2 === '0' || this.cancelData.oco2StopOrderPriceText2 === '1') {
      // 以上、以下の場合
        str += this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.cancelData.oco2StopOrderPriceText2)) +
               `になった時点でOCO1注文を以下の執行条件に訂正`
      }
      str += `\n` // 改行
      if (sasinariJyouken === ' ') {
      // 条件なし
        str += 'OCO1（指値）を'
      } else {
        str += 'OCO1（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, sasinariJyouken)) + '）を'
      }
      if (this.cancelData.oco2GyakusasiHouhou === '1') {
      // 執行方法（逆指値）が指値の時
        if (this.cancelData.oco2GyakusasiJyouken === ' ') {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.cancelData.oco2Price)) + '円'
        } else {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.cancelData.oco2GyakusasiJyouken)) + '/' +
                 this.$_out(this.$_withCommaNoneZeroPadding(this.cancelData.oco2Price)) + '円'
        }
      } else if (this.cancelData.oco2GyakusasiHouhou === '2') {
      // 執行方法（逆指値）が成行の時
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, 'N'))
      }
      return str + 'に訂正'
    }
  }
}
</script>

<style lang="scss">
@import "@/styles/orderStatusList.scss";
.domestic_stock_order_cancel {
  .el-card__body {
    padding: 5px !important;
  }
}
.fee-label {
    font-size: 16px;
}

</style>
