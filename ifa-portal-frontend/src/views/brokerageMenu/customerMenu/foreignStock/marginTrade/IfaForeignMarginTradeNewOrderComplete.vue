<template>
  <!-- 外国株信用取引新規注文完了 -->
  <el-dialog
    v-model="vmIsVisible"
    :close-on-press-escape="false"
    :title="form.screenTitle"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :style="dialogStyle"
    style="width: 1200px;"
    class="foreign-margin_dialog-class"
  >

    <!-- ヘッダ -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました｡']"
    ></ifa-message-area>

    <!-- 顧客情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
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
                style="font-size: 20px; min-width: 343px; max-width: 665px;"
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
              >&nbsp;&nbsp;上場市場：{{ $_out(formData.listedMarket) }}</div>
              <div
                v-if="formData.tradeLimitTitle"
                style="text-align: right; min-width: 140px; margin-left: auto;"
              >
                <el-icon
                  style="color: red; position: relative; top: 3px;"
                ><WarningFilled></WarningFilled></el-icon>
                <ifa-link
                  :param-url="formData.tradeLimit"
                  :disp-name="'取引注意情報'"
                  link-icon-image="externalLink"
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
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <!-- 取引 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span
                :style="formData.orderInfo.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
              >
                <ifa-text
                  :style="formData.orderInfo.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
                  :code-list-id="'FOREIGN_STOCK_TRADE_CLASS'"
                  :disp-pattern="1"
                  :code-key="formData.tradeCd"
                ></ifa-text>
                <span v-if="!formData.tradeCd">-</span>
              </span>
              <span
                :style="formData.orderInfo.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
              >（
                <ifa-text
                  :style="formData.orderInfo.tradeKbn === '3' ? 'color: #E5004C; font-weight: bold;' : 'color: #00847F; font-weight: bold;'"
                  :code-list-id="'MARGIN_DUE_DATE'"
                  :disp-pattern="1"
                  :code-key="formData.marginDueDate"
                ></ifa-text>
                <span v-if="!formData.marginDueDate">-</span>
                ）</span>
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
              <span>{{ $_out(ifaFormatUtils.withCommaInteger(formData.orderInfo.foreignQuantity)) }} 株</span>
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
                :code-key="formData.orderInfo.orderPriceKindList"
              ></ifa-text>
              <span v-if="!formData.orderInfo.orderPriceKindList">-</span><br v-if="formData.orderInfo.orderPriceKindList !== '2'">
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
              <span>{{ formData.orderInfo.periodDate ? formData.orderInfo.periodDate === '1' ? `${$_out(formatDate(formData.orderInfo.period))}まで` : "当日注文" : '-' }}</span>
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
                :disp-pattern="2"
                :code-key="formData.orderInfo.depositType"
              ></ifa-text>
              <span v-if="!formData.orderInfo.depositType">-</span>
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
                :code-key="formData.orderInfo.kessaiHoho"
              ></ifa-text>
              <span v-if="!formData.orderInfo.kessaiHoho">-</span>
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
              <span>{{ $_out(formatOrderDate(formatDateTime(formData.orderInfo.orderDate))) }}</span>
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
              <span>{{ $_out(formatDate(formData.orderInfo.contractDate)) }}</span>
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
              <span>{{ $_out(formatDate(formData.orderInfo.tradeDate)) }}</span>
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
              <span>{{ $_out(formatDate(formData.orderInfo.domesticSettlementDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内受渡予定日 -->

          <!-- 保証金不足額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>保証金不足額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ formData.depositDeficientAmount ? `${ifaFormatUtils.withCommaZeroPadding(formData.depositDeficientAmount, 2)} ${formData.orderInfo.stopOrderPriceText2}` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 米ドル振替額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>米ドル振替額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ formData.currency ? `${ifaFormatUtils.withCommaZeroPadding(formData.currency, 2)} ${formData.orderInfo.stopOrderPriceText2}` : '-' }}</span>
            </el-col>
          </el-row>

          <!-- 米国株式振替額 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>米国株式振替額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ formData.americaStockTransferAmount ? `${ifaFormatUtils.withCommaZeroPadding(formData.americaStockTransferAmount, 2)} ${formData.orderInfo.stopOrderPriceText2}` : '-' }}</span>
            </el-col>
          </el-row>

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
            style="padding-top: 0.5rem; padding-left: 1rem;"
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
                :code-key="formData.orderMethod"
              ></ifa-text>
              <span v-if="!formData.orderMethod">-</span>
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

          <!-- 英文開示銘柄 -->
          <el-row
            v-if="formData.engPubText"
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
                :code-key="formData.engPubText"
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
                :code-key="formData.confirmItem.insiderConfirmCheckBox"
              ></ifa-text>
              <span v-if="!formData.confirmItem.insiderConfirmCheckBox">-</span>
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
    <el-row v-if="formData.tradeNoticeInfoBrandMsg.length > 0 ||
      formData.noticeInfoAlert.length > 0 ||
      formData.noticeAlert.length > 0 ||
      formData.additionalCollateralRegulationConfirmMsg.length > 0 ||
      formData.stopOrderInstantMessage.length > 0 ||
      formData.nextBusinessDayMessage.length > 0"
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
          <el-row
            v-if="formData.tradeNoticeInfoBrandMsg.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col :span="16">
              <span>説明済</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.noticeInfoAlert.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <span>確認済</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.noticeAlert.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <span>確認済</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.additionalCollateralRegulationConfirmMsg.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>増し担保規制の確認</span>
            </el-col>
            <el-col :span="16">
              <span>確認済</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.stopOrderInstantMessage.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>逆指値注文即時発火</span>
            </el-col>
            <el-col :span="16">
              <span>確認済</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formData.nextBusinessDayMessage.length > 0"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>翌営業日向け注文</span>
            </el-col>
            <el-col :span="16">
              <span>確認済</span>
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
          @app-action-handler="toOrderStatusListA002"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- /注文発注ボタン -->
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaForeignMarginTradeNewOrderCompleteFormModel } from './js/IfaForeignMarginTradeNewOrderCompleteFormModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
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
    customerInfo: { type: Object, required: true },
    stockInfo: { type: Object, required: true },
    userInfo: { type: Object, required: true },
    priorityMarket: { type: String, required: true }
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
      form: new IfaForeignMarginTradeNewOrderCompleteFormModel(),
      ifaFormatUtils: ifaFormatUtils
    }
  },

  computed: {
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.customerInfo.accountNumber}`
    }
  },

  watch: {
    'formData.orderInfo.tradeKbn'(newValue) {
      if (newValue === '3') {
        this.dialogStyle = 'backgroundColor: #fef0f0;'
      } else {
        this.dialogStyle = 'backgroundColor: #ecf5ff;'
      }
    }
  },

  methods: {
    // 価格に表示する内容を生成する
    getPrice() {
      if (this.formData.orderInfo.orderPriceKindList === '1') {
        return this.$_out(ifaFormatUtils.withCommaZeroPadding(this.formData.orderInfo.hiddenOrderPrice, 2)) + this.formData.orderInfo.stopOrderPriceText2
      } else if (this.formData.orderInfo.orderPriceKindList === '3' || this.formData.orderInfo.orderPriceKindList === '4') {
        let str = '（現在値が' + this.$_out(ifaFormatUtils.withCommaZeroPadding(this.formData.orderInfo.stopOrderPrice2, 2)) + this.formData.orderInfo.stopOrderPriceText2 +
                  (this.formData.orderInfo.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
        if (this.formData.orderInfo.orderPriceKindList === '3') {
          str += this.$_out(ifaFormatUtils.withCommaZeroPadding(this.formData.orderInfo.hiddenOrderPrice, 2)) + this.formData.orderInfo.stopOrderPriceText2 + 'で発注）'
        } else if (this.formData.orderInfo.orderPriceKindList === '4') {
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
    toOrderStatusListA002() {
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
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
._black_s {
  font-size: 14px;
  color: #606266;
  padding-bottom: 0.5rem;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.footer_button {
  text-align: left;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
:deep(.el-text){
  font-size: 16px;
}
</style>
