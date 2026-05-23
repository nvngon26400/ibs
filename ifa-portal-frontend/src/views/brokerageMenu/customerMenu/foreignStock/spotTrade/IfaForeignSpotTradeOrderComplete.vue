<template>
  <!-- 外国現物取引注文受付完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :close-on-press-escape="false"
    :title="form.screenTitle"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :destroy-on-close="true"
    :style="dialogStyle"
    width="1200px"
  >

    <!-- ヘッダ -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました｡']"
    ></ifa-message-area>

    <!-- 顧客情報 -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <el-icon style="position: relative; top: 3px;"><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        <span>{{ $_out(customerName) }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <!-- /顧客情報 -->

    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="background-color: #eee;"
        >
          <el-row>
            <el-col
              :span="24"
              class="brand_area_flex"
            >
              <div
                class="brand_area_flex _bold_black_l"
                style="font-size: 20px; min-width: 343px;"
              >
                <span
                  style="display: inline-block; white-space: nowrap;"
                >［{{ $_out(formData.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(formData.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(formData.marketAbbreviation) }}</div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width: 190px;"
              >&nbsp;&nbsp;国籍：{{ $_getCodeValue('NATIONALITY_CODE', 2, formData.countryCode) }}</div>
              <div
                v-if="toBoolean(formData.tradeLimit)"
                style="text-align: right; min-width: 140px; margin-left: auto;"
              >
                <el-icon
                  style="color: red; position: relative; top: 3px;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
                  disp-name="取引注意情報"
                  :url-id="18"
                  :url-object="{ countryCode: formData.countryCode }"
                ></ifa-link>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- /銘柄情報 -->

    <!-- 注文内容(復唱項目) -->
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
            style="padding: 0.5rem 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span
                v-if="formData.tradeCd"
              >
                <ifa-text
                  :class="formData.tradeCd === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
                  :code-list-id="'FOREIGN_STOCK_TRADE_CLASS'"
                  :disp-pattern="1"
                  :code-key="formData.tradeCd"
                ></ifa-text>
              </span>
              <span
                v-else
              >-</span>
            </el-col>
          </el-row>
          <!-- /取引 -->

          <!-- 受注数量 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_withCommaInteger(formData.orderList[0].orderQuantity)) }}株</span>
            </el-col>
          </el-row>
          <!-- /受注数量 -->

          <!-- 価格 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'SELECT_ABLE_PRICE_CONDITIONS'"
                :disp-pattern="1"
                :code-key="formData.orderList[0].priceCondition"
              ></ifa-text>
              <span v-if="!formData.orderList[0].priceCondition">-</span><br v-if="formData.orderList[0].priceCondition !== '2'">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <!-- /価格 -->

          <!-- 注文期間 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>期間</span>
            </el-col>
            <el-col :span="16">
              <span>{{ !formData.orderList[0].periodRadio ? '-' : formData.orderList[0].periodRadio === '1' ? `${$_out(formatDate(formData.orderList[0].period))}まで` : "当日注文" }}</span>
            </el-col>
          </el-row>
          <!-- /注文期間 -->

          <!-- /預り区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_DEPOSIT_TYPE'"
                :disp-pattern="3"
                :code-key="formData.orderList[0].depositType"
              ></ifa-text>
              <span v-if="!formData.orderList[0].depositType">-</span>
            </el-col>
          </el-row>
          <!-- 預り区分 -->

          <!-- 決済方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'SETTLEMENT_TYPE'"
                :disp-pattern="1"
                :code-key="formData.orderList[0].settlementType"
              ></ifa-text>
              <span v-if="!formData.orderList[0].settlementType">-</span>
            </el-col>
          </el-row>
          <!-- /決済方法 -->

          <!-- 国内注文日時 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内注文日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formatOrderDate(formatDateTime(formData.orderList[0].orderDate))) }}</span>
            </el-col>
          </el-row>
          <!-- /国内注文日時 -->

          <!-- 現地約定予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現地約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formatDate(formData.orderList[0].localContractDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /現地約定予定日 -->

          <!-- 国内約定予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formatDate(formData.orderList[0].domesticTradeDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内約定予定日 -->

          <!-- 国内受渡予定日 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内受渡予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formatDate(formData.orderList[0].domesticSettlementDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内受渡予定日 -->

        </el-card>
      </el-col>
    </el-row>
    <!-- /注文内容(復唱項目) -->

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
            style="padding: 0.5rem 0.5rem; padding-left: 1rem;"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>

          <!-- 勧誘区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_STOCK_INVITATION_TYPE'"
                :disp-pattern="1"
                :code-key="formData.kanyuKbn"
              ></ifa-text>
              <span v-if="!formData.kanyuKbn">-</span>
            </el-col>
          </el-row>
          <!-- /勧誘区分 -->

          <!-- 受注方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'FOREIGN_STOCK_ORDER_METHOD_TYPE'"
                :disp-pattern="1"
                :code-key="formData.receiveOrderType"
              ></ifa-text>
              <span v-if="!formData.receiveOrderType">-</span>
            </el-col>
          </el-row>
          <!-- /受注方法 -->

          <!-- 重要事項の説明 -->
          <el-row
            v-if="formData.importantMatterType"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要事項の説明</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'IMPORTANT_MATTERS_EXPLAIN'"
                :disp-pattern="1"
                :code-key="formData.importantMatterType"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /重要事項の説明 -->

          <!-- 乗換え勧誘(ETF) -->
          <el-row
            v-if="formData.solicitationEtf"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換え勧誘(ETF)</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'ETF_SOLICITING_TRANSFERS'"
                :disp-pattern="1"
                :code-key="formData.solicitationEtf"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- 乗換え勧誘(ETF) -->

          <!-- 英文開示銘柄 -->
          <el-row
            v-if="formData.engPubCheckbox"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>英文開示銘柄</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'ISSUES_DISCLOSED_IN_ENGLISH_BRAND'"
                :disp-pattern="1"
                :code-key="formData.engPubCheckbox"
              ></ifa-text>
            </el-col>
          </el-row>
          <!-- /英文開示銘柄 -->

          <!-- インサイダー -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="16">
              <ifa-text
                :code-list-id="'INSIDER_CONFIRM'"
                :disp-pattern="2"
                :code-key="formData.checkList[0].checkInsider"
              ></ifa-text>
              <span v-if="!formData.checkList[0].checkInsider">-</span>
            </el-col>
          </el-row>
          <!-- /インサイダー -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /その他注文内容 -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="formData.tradeNoticeInfoBrandMsg ||
      (Array.isArray(formData.complianceCheckList) ? Boolean(formData.complianceCheckList.length) : formData.complianceCheckList) ||
      formData.noticeInfoAlert ||
      formData.noticeAlert ||
      formData.contractAmountOverMessage ||
      formData.stopOrderInstantMessage ||
      formData.nextBusinessDayMessage ||
      formData.overseasEtfAlertMessage"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="formData.tradeNoticeInfoBrandMsg"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- コンプライアンスチェック -->
          <el-row
            v-if="complianceCheckListDispFlg"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ $_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, formData.complianceCheckList[0].chkBoxLabel) }}</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, formData.complianceCheckList[0].chkBoxLabel) }}</span>
            </el-col>
          </el-row>
          <!-- 注意情報アラート確認 -->
          <el-row
            v-if="formData.noticeInfoAlert"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('NOTICE_INFO_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- お知らせアラート確認 -->
          <el-row
            v-if="formData.noticeAlert"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- 約定代金の上限超過 -->
          <el-row
            v-if="formData.contractAmountOverMessage"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>誤発注防止制限金額超過</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('EXCEEDING_TRADE_AMOUNT_LIMIT', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- 逆指値注文即時発火 -->
          <el-row
            v-if="formData.stopOrderInstantMessage"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>逆指値注文即時発火</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('IMMEDIATE_STOP_ORDER', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- 翌営業日向け注文 -->
          <el-row
            v-if="formData.nextBusinessDayMessage"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>翌営業日向け注文</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('ORDER_FOR_NEXT_BUSINESS_DAY', 1, '1') }}</span>
            </el-col>
          </el-row>
          <!-- 海外ETFアラート確認 -->
          <el-row
            v-if="formData.overseasEtfAlertMessage"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>乗換え勧誘(ETF)なし</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('OVERSEAS_ETF_ALERT_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnToOrderStatusList"
          text="注文状況一覧へ"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="toOrderStatusListA003"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaForeignSpotTradeOrderCompleteFormModel } from './js/IfaForeignSpotTradeOrderCompleteFormModel'
import { getFormattedDateTimeValue, getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  // 親コンポーネントから受け取る値
  props: {
    // 本コンポーネントの表示・表示
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'move-to-order-list', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      dialogStyle: '',
      form: new IfaForeignSpotTradeOrderCompleteFormModel()
    }
  },

  computed: {
    complianceCheckListDispFlg() {
      if (Array.isArray(this.formData.complianceCheckList) ? (Boolean(this.formData.complianceCheckList.length) && 'complianceCheckMsg' in this.formData.complianceCheckList[0] ? this.formData.complianceCheckList[0].complianceCheckMsg : false) : this.formData.complianceCheckList) {
        return true
      } else {
        return false
      }
    },
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },

  watch: {
    'formData.tradeCd': {
      deep: true,
      handler(newValue) {
        if (newValue === '0') {
          this.dialogStyle = 'backgroundColor: #fef0f0;'
        } else {
          this.dialogStyle = 'backgroundColor: #ecf5ff;'
        }
      }
    }

  },

  methods: {
    toBoolean(booleanStr) {
      return typeof booleanStr === 'boolean' ? booleanStr : booleanStr.toLowerCase() === 'true'
    },
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.formData.orderList[0].priceCondition === '1') {
        return this.formData.orderList[0].hiddenOrderPrice ? this.$_withCommaZeroPadding(this.formData.orderList[0].hiddenOrderPrice, 2) + this.formData.orderList[0].limitPriceText : '0' + this.formData.orderList[0].limitPriceText
      } else if (this.formData.orderList[0].priceCondition === '3' || this.formData.orderList[0].priceCondition === '4') {
        let str = '（現在値が' + (this.formData.orderList[0].triggerPrice ? this.$_withCommaZeroPadding(this.formData.orderList[0].triggerPrice, 2) : '0') + this.formData.orderList[0].limitPriceText +
                  (this.formData.orderList[0].tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
        if (this.formData.orderList[0].priceCondition === '3') {
          str += (this.formData.orderList[0].hiddenOrderPrice ? this.$_withCommaZeroPadding(this.formData.orderList[0].hiddenOrderPrice, 2) : '0') + this.formData.orderList[0].limitPriceText + 'で発注）'
        } else if (this.formData.orderList[0].priceCondition === '4') {
          str += '成行で発注）'
        }
        return str
      }
      return ''
    },

    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },

    // 注文状況一覧へボタン
    toOrderStatusListA003() {
      this.$emit('move-to-order-list')
    },
    formatDateTime(val) {
      return getFormattedDateTimeValue(val)
    },
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    formatOrderDate(orderDate) {
      if (!orderDate) {
        return orderDate
      }
      const dateTimeParts = orderDate.split(' ')
      const datePart = dateTimeParts[0]
      const timePart = dateTimeParts[1].slice(0, 5)
      return `${datePart} ${timePart}`
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
:deep(.el-text){
  font-size: 16px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
</style>
