<template>
  <el-dialog
    v-model="vmIsVisible"
    class="margin_new_order_comp"
    :style="dialogStyle"
    :title="form.title.name"
    width="1200px"
    top="6%"
    :show-close="false"
    :center="true"
    :before-close="backA002"
    :close-on-click-modal="false"
  >
    <!-- ヘッダ -->
    <el-row style="padding-top: 1.5rem;">
      <el-col
        :span="22"
        :offset="1"
        style="text-align: right;"
      >
        <ifa-button
          text="戻る"
          color="secondary"
          class="form-button__wrapper"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- 戻るボタン -->
    <el-row>
    </el-row>

    <!-- メッセージエリア -->
    <ifa-message-area
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warnings"
      :info-messages="messages.infos"
    ></ifa-message-area>

    <!-- 口座エリア -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="font-size: 20px;"
        class="_bold_black_l"
      >
        <span style="position: relative; top: 3px;">
          <el-icon><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        </span>
        <span style="margin-right: 1rem;">{{ $_out(customerName) }}</span>
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
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <el-row class="_bold_black_m">
            <el-col
              :span="5"
              :offset="7"
            >
              <span>訂正前</span>
            </el-col>
            <el-col :span="3">
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="12"
              :offset="1"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(form.request.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
            </el-col>
            <el-col
              v-if="form.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                ref="tradeLimitRef"
                :disp-name="'取引注意情報'"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
              ></ifa-link>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">注文種別</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <span v-if="form.request.correctBeforePriceSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.request.orderKind"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <span v-if="form.request.correctBeforePriceSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.request.orderKind"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">取引種別</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <ifa-text
                :style="fontStyle"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="form.request.tradeCd"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <ifa-text
                :style="fontStyle"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="form.request.tradeCd"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row
            class="dotted_border"
            :class="{
              'correction-bg-color':
                form.request.orderMarket !== form.request.afterCorrecMarket
            }"
          >
            <el-col :span="6">
              <span class="_bold_black_m">市場</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'SELECT_MARKET'"
                :disp-pattern="1"
                :code-key="form.request.orderMarket"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              :class="{
                _bold_black_origin:
                  form.request.orderMarket !== form.request.afterCorrecMarket
              }"
            >
              <ifa-text
                :code-list-id="'SELECT_MARKET'"
                :disp-pattern="1"
                :code-key="form.request.afterCorrecMarket"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">未約定数量</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.unTradeQuantity ? `${$_withCommaInteger(form.request.unTradeQuantity)}株` : '-' }} </span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.unTradeQuantity ? `${$_withCommaInteger(form.request.unTradeQuantity)}株` : '-' }} </span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受注数量</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.quantity ? `${$_withCommaInteger(form.request.quantity)}株` : '-' }} </span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>-</span>
            </el-col>
          </el-row>

          <!-- 通常/逆指値 -->
          <el-row
            v-if="form.request.orderKind === '1'"
            class="dotted_border"
            :class="{
              'correction-bg-color':
                getBeforePrice() != getPrice()
            }"
          >
            <el-col :span="6">
              <span class="_bold_black_m">価格</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="form.request.correctBeforePriceSasinariHouhou"
              ></ifa-text><br v-if="getBeforePrice() !== ''">
              <span>{{ getBeforePrice() }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="form.request.sasinariHouhou"
                :class="{
                  _bold_black_origin: getBeforePrice() !== getPrice()
                }"
              ></ifa-text><br v-if="getPrice() !== ''">
              <span
                :class="{
                  _bold_black_origin: getBeforePrice() !== getPrice()
                }"
              >{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <!-- OCO -->
          <div v-if="form.request.orderKind === '2'">
            <el-row
              v-if="form.request.workingStatus === 'false'"
              class="dotted_border"
              :class="{
                'correction-bg-color':
                  getOCO1BeforePrice() != getOCO1Price() ||
                  getOCO2BeforePrice() != getOCO2Price()
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格/OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  :code-list-id="'EXECUTE_METHOD'"
                  :disp-pattern="1"
                  :code-key="form.request.correctBeforePriceOco1SasinariHouhou"
                ></ifa-text>／
                <span>{{ getOCO1BeforePrice() }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  :code-list-id="'EXECUTE_METHOD'"
                  :disp-pattern="1"
                  :code-key="form.request.oco1SasinariHouhou"
                  :class="{
                    _bold_black_origin: getOCO1BeforePrice() !== getOCO1Price()
                  }"
                ></ifa-text>／
                <span
                  :class="{
                    _bold_black_origin: getOCO1BeforePrice() !== getOCO1Price()
                  }"
                >{{ getOCO1Price() }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
              :class="{
                'correction-bg-color': getOCO2BeforePrice() != getOCO2Price()
              }"
            >
              <el-col :span="6">
                <span class="_bold_black_m">条件/OCO2</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ getOCO2BeforePrice() }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span
                  :class="{
                    _bold_black_origin: getOCO2BeforePrice() !== getOCO2Price()
                  }"
                >{{ getOCO2Price() }}</span>
              </el-col>
            </el-row>
          </div>

          <el-row
            class="dotted_border"
            :class="{
              'correction-bg-color':
                form.yukoKigenChange == '1'
            }"
          >
            <el-col :span="6">
              <span class="_bold_black_m">注文期間</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.period ? $_out($_getFormattedDateValue(form.request.period)) : "----/--/--" }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span
                :class="{
                  _bold_black_origin: form.yukoKigenChange == '1'
                }"
              >{{ form.yukoKigenChange == '1' ? $_getFormattedDateValue(form.yukoKigen) : $_out($_getFormattedDateValue(form.request.period)) }}</span>
            </el-col>
          </el-row>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">信用取引区分</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.marginTradeTypeText }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.marginTradeTypeText }}</span>
            </el-col>
          </el-row>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">手数料区分</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'COMM_TYPE'"
                :disp-pattern="2"
                :code-key="form.request.tesuuryouKbn"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'COMM_TYPE'"
                :disp-pattern="2"
                :code-key="form.request.tesuuryouKbn"
              ></ifa-text>
            </el-col>
          </el-row>

        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- その他注文内容 -->
    <el-row>
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">訂正後建玉余力</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ form.positionPower && form.positionPower > 0 ? `${$_withCommaInteger(form.positionPower)}円` : '-' }} </span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">約定予定日</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateValue(form.contractDate)) }}</span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受渡予定日</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateValue(form.deliveryDate)) }}</span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受注日時</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime, 'datetime12')) }}</span>
            </el-col>
          </el-row>

          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">勧誘区分</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="form.request.kanyuKbn"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">受注方法</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.request.orderMethod"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">確認項目</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="3"
                code-key="1"
              ></ifa-text>
              <br v-if="form.request.afterCorrecMarket === 'A'">
              <ifa-text
                v-if="form.request.afterCorrecMarket === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                :code-key="form.request.checkSor"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row
      v-if="form.tradeNoticeInfoBrandMsg ||
        form.noticeInfoAlert ||
        form.noticeAlert"
      style="margin-top:0.5rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card alert_content"
          :style="[dialogStyle, { fontSize: '16px' }]"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; color: #f00; padding-left: 1rem"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <el-row
            v-if="form.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="display: flex; align-items: center; padding-left: 0;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-input-check
                v-model="form.tradeNoticeInfoBrandConfirm"
                label-class="checkClass"
                code-list-id="TRADE_NOTICE_INFO_EXPLAIN"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeInfoAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="display: flex; align-items: center; padding-left: 0;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">注意情報の確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-input-check
                v-model="form.noticeInfoAlertConfirm"
                label-class="checkClass"
                code-list-id="NOTICE_INFO_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.noticeAlert"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="display: flex; align-items: center; padding-left: 0;"
            >
              <div class="required-mark">*</div><span style="color: #f00;">重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-input-check
                v-model="form.noticeAlertConfirm"
                label-class="checkClass"
                code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem"
      >
        <ifa-button
          id="btnOrderRegister"
          text="注文訂正"
          :disabled="buttonDisabled"
          action-type="requestAction"
          style="padding-left:0;"
          :request-model="A001RequestModel"
          action-id="SUB0202_0212-06_2#A001"
          @response-handler="correctionOrderA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaMarginRepayOrderCorrectConfirmFormModel } from './js/IfaMarginRepayOrderCorrectConfirmFormModel'
import { IfaMarginRepayOrderCorrectConfirmA001RequestModel } from './js/IfaMarginRepayOrderCorrectConfirmA001RequestModel'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaMarginRepayOrderCorrectConfirmFormModel(),
      dialogStyle: '',
      fontStyle: '',
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      }
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaMarginRepayOrderCorrectConfirmA001RequestModel(this.form)
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    isPrivId() {
      if (this.$store.getters.userAccount) {
        return (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2')
      }
      return false
    },
    isErrMsgs() {
      if ((this.form.tradeNoticeInfoBrandMsg && this.form.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.form.noticeInfoAlert && this.form.noticeInfoAlert.length > 0) ||
        (this.form.noticeAlert && this.form.noticeAlert.length > 0)) {
        return true
      }
      return false
    },
    buttonDisabled() {
      if (this.isPrivId) {
        return true
      }
      if (!this.isErrMsgs) {
        return false
      }
      return (this.isNotEmptyMsg(this.form.tradeNoticeInfoBrandMsg) && this.form.tradeNoticeInfoBrandConfirm !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeInfoAlert) && this.form.noticeInfoAlertConfirm !== '1') ||
          (this.isNotEmptyMsg(this.form.noticeAlert) && this.form.noticeAlertConfirm !== '1')
    },
    getUrlObject() {
      if (!this.form.request.brandCode) {
        return null
      }
      return this.form.request.brandCode.substring(0, 4)
    }
  },
  watch: {
    'form.request.tradeCd': {
      deep: true,
      handler(newValue) {
        // 信用返済買
        if (newValue === '5') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
          this.fontStyle = 'color: #E5004C'
        } else {
        // 信用返済売
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
          this.fontStyle = 'color: #00847F'
        }
      }
    }
  },
  beforeUpdate() {
    this.messages.mains.length = 0
    this.messages.mains.push('訂正はまだ完了してません。画面下の注文訂正ボタンを押下ください。')

    this.messages.errors.length = 0
    if (this.form.tradeNoticeInfoBrandMsg) {
      this.messages.errors.push(this.form.tradeNoticeInfoBrandMsg)
    }
    if (this.form.noticeInfoAlert) {
      this.messages.errors.push(this.form.noticeInfoAlert)
    }
    if (this.form.noticeAlert) {
      this.messages.errors.push(this.form.noticeAlert)
    }
    this.messages.warnings.length = 0
    this.messages.infos.length = 0
    if (this.form.code ? this.form.code.startsWith('W') : false && this.form.errMessage) {
      const EditErrMessage = this.form.errMessage + '（' + this.form.code + '）'
      this.messages.infos.push(EditErrMessage)
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)

      // 訂正SOR注文結果区分="1"：SOR訂正注文　以外の場合、市場（訂正後）を市場（訂正前）と同じにする
      if (this.form.correctSorOrderResultClassification !== '1') {
        this.form.request.afterCorrecMarket = this.form.request.orderMarket
      }

      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
      })
    },
    // 戻るボタン
    backA002() {
      this.$emit('close-modal')
      this.form.tradeNoticeInfoBrandConfirm = '0'
      this.form.noticeInfoAlertConfirm = '0'
      this.form.noticeAlertConfirm = '0'
      this.form.insiderErrorConfirm = '0'
    },
    // 注文訂正ボタン
    correctionOrderA001: function(data) {
      this.$emit('order-finish', data.dataList[0])
      this.form.tradeNoticeInfoBrandConfirm = '0'
      this.form.noticeInfoAlertConfirm = '0'
      this.form.noticeAlertConfirm = '0'
      this.form.insiderErrorConfirm = '0'
    },
    getPrice() {
      // 通常/逆指値.執行方法 : 指値
      if (this.form.request.sasinariHouhou === '1') {
        if (this.form.request.sasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円'
        }
        return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円'
      }
      // 通常/逆指値.執行方法 : 成行
      if (this.form.request.sasinariHouhou === '2') {
        if (this.form.request.sasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.request.sasinariJyouken))
        }
      }
      // 通常/逆指値.執行方法 : 逆指値
      if (this.form.request.sasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.triggerPrice)) + '円' +
                  (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点で'
        // 通常/逆指値.執行方法（逆指値）: 指値
        if (this.form.request.gyakusasiHouhou === '1') {
          if (this.form.request.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.price)) + '円で執行'
        // 通常/逆指値.執行方法（逆指値）: 成行
        } else if (this.form.request.gyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.request.sasinariJyouken)) + 'で執行'
        }
        return str
      }
      return ''
    },
    getBeforePrice() {
      // 訂正前価格.通常/逆指値.執行方法 : 指値
      if (this.form.request.correctBeforePriceSasinariHouhou === '1') {
        if (this.form.request.correctBeforePriceSasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceSasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円'
        }
        return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円'
      }
      // 訂正前価格.通常/逆指値.執行方法 : 成行
      if (this.form.request.correctBeforePriceSasinariHouhou === '2') {
        if (this.form.request.correctBeforePriceSasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.request.correctBeforePriceSasinariJyouken))
        }
      }
      // 訂正前価格.通常/逆指値.執行方法 : 逆指値
      if (this.form.request.correctBeforePriceSasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceTriggerPrice)) + '円'
        if (this.form.request.correctBeforePriceStopOrderPriceText2 === ' ') {
          str += (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点で'
        } else {
          str += this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.form.request.correctBeforePriceStopOrderPriceText2) + 'になった時点で'
        }
        // 訂正前価格.通常/逆指値.執行方法（逆指値）: 指値
        if (this.form.request.correctBeforePriceGyakusasiHouhou === '1') {
          if (this.form.request.correctBeforePriceSasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceSasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円で執行'
        // 訂正前価格.通常/逆指値.執行方法（逆指値）: 成行
        } else if (this.form.request.correctBeforePriceGyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.request.correctBeforePriceSasinariJyouken)) + 'で執行'
        }
        return str
      }
      return ''
    },
    getOCO1Price() {
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
    },
    getOCO1BeforePrice() {
      if (this.form.request.correctBeforePriceOco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco1Price)) + '円'
    },
    getOCO2Price() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2TriggerPrice)) + '円' +
                  (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // OCO2.執行方法（逆指値）: 指値
      if (this.form.request.oco2GyakusasiHouhou === '1') {
        // OCO2.執行条件（逆指値）：≠条件なし
        if (this.form.request.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        }
        // OCO2.執行方法（逆指値）: 成行
      } else if (this.form.request.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    getOCO2BeforePrice() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2TriggerPrice)) + '円'
      if (this.form.request.correctBeforePriceOco2StopOrderPriceText2 === ' ') {
        str += (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でOCO1'
      } else {
        str += this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.form.request.correctBeforePriceOco2StopOrderPriceText2) + 'になった時点でOCO1'
      }
      if (this.form.request.correctBeforePriceOco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // 訂正前価格.OCO2.執行方法（逆指値）: 指値
      if (this.form.request.correctBeforePriceOco2GyakusasiHouhou === '1') {
        // 訂正前価格.OCO2.執行条件（逆指値）：≠条件なし
        if (this.form.request.correctBeforePriceOco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2Price)) + '円に訂正'
        }
        // 訂正前価格.OCO2.執行方法（逆指値）: 成行
      } else if (this.form.request.correctBeforePriceOco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    isNotEmptyMsg(msg) {
      if (!msg) {
        return false
      }
      return true
    }
  }
}
</script>
<style lang="scss">
@import '@/styles/orderStatusList.scss';
.domestic_stock_order_correct {
  .el-card__body {
    padding: 5px !important;
  }
}
.margin_new_order_comp {
	.el-dialog__title{
   font-weight: bold;
	}
}
  .arrow {
      font-weight: 700;
      font-size: 22px;
      margin-top: -2px;
      -webkit-transform: scaleX(1.4);
      transform: scaleX(1.4);
      color: #000;
  }
  .el-icon-right {
  font-family: element-icons!important;
  font-style: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  vertical-align: baseline;
  display: inline-block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  }
</style>
<style lang="scss" scoped>
.required-mark {
  color: #ff2b2b;
  margin-right: 2px;
}
:deep(.checkClass label>.el-checkbox__label){
  margin-left: 5px !important;
  color: #f00 !important;
  font-weight: bold !important;
  white-space: pre-wrap;
}
:deep(.el-text){
  font-size: 16px;
}
._bold_black_origin {
  font-size: 16px;
  font-weight: 700;
  color: #18181A;
  padding-bottom: 0.5rem;
}
:deep(.el-checkbox__label) {
    font-size: 16px;
  }
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
</style>
