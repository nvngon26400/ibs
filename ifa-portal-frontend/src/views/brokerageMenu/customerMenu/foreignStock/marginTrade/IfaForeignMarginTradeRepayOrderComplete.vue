<template>
  <!-- 信用返済注文受付確認ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': bgColor, 'font-weight': 'bold' }"
    :title="formModel.title"
    width="1200px"
    top="6%"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    class="foreign-margin_dialog-class"
  >
    <!-- エラー・警告・通知 -->
    <ifa-message-area
      :main-messages="[formModel.finishMassage]"
    ></ifa-message-area>

    <!-- /エラー・警告・通知 -->

    <!-- 顧客情報 -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
        <span>{{ customerInfo.butenCode + '-' +　customerInfo.accountNumber }}</span>
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
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo.corporationType == '1'"><OfficeBuilding></OfficeBuilding></el-icon>
          <el-icon v-else><Avatar></Avatar></el-icon>
        </span>
        <span>{{ customerInfo.customerNameKanji }}（{{ customerInfo.customerNameKana }}）</span>
        <!-- 注意情報 -->
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
                >［{{ $_out(formModel.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(formModel.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; min-width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(formModel.marketAbbreviatedName) }}</div>
              <div
                style="min-width: 140px; margin-left: auto;"
              >
                <el-col
                  v-if="formModel.tradeLimit === 'true'"
                  style="padding-right: 0.5rem;"
                >
                  <el-icon
                    style="color: red; position: relative; top: 3px;"
                  ><WarningFilled></WarningFilled></el-icon>
                  <ifa-link
                    :param-url="formModel.tradeLimitUrl"
                    :disp-name="'取引注意情報'"
                    link-icon-image="externalLink"
                  ></ifa-link>
                </el-col>
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
          <!-- /ご注文内容（ラベル） -->
          <hr>
          <!-- 取引種別 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span
                v-if="formModel.tradeCd === '4'"
                class="font-color__plus bold"
              >
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}
                ({{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.lastTradeDate)) }})
              </span>
              <span
                v-else
                class="font-color__minus bold"
              >
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}
                ({{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.lastTradeDate)) }})
              </span>
            </el-col>
          </el-row>
          <!-- /取引種別 -->

          <!-- 数量 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_addComma(formModel.orderQuantity)) }}&nbsp;株</span>
            </el-col>
          </el-row>
          <!-- /数量 -->

          <!-- 返済建玉指定方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>返済建玉指定方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('FOREIGN_REPAY_METHOD', 1, formModel.repayPositionDesignateMethod)) }}&nbsp;
                ({{ $_out($_getCodeValue('FOREIGN_REPAY_ORDER', 1, formModel.repaySelectOrder)) }})
              </span>
            </el-col>
          </el-row>
          <!-- /返済建玉指定方法 -->

          <!-- 価格 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <el-col
                v-if="formModel.orderPriceKindList == '1' || formModel.orderPriceKindList == '2'"
                :span="16"
              >
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, formModel.orderPriceKindList)) }}<br>
                <span v-if="formModel.orderPriceKindList == '1'">{{ $_out($_withCommaZeroPadding(formModel.hiddenOrderPrice, 2)) }}{{ $_out(formModel.currencyCode) }}</span>
              </el-col>
              <el-col
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="16"
              >
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, formModel.orderPriceKindList)) }}
              </el-col>
              <el-row
                v-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
                :span="16"
              >
                {{ getPrice() }}
              </el-row>
            </el-col>
          </el-row>
          <!-- /価格 -->

          <!-- 期間 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>期間</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formModel.periodRadio === '0'">{{ $_getCodeValue('PERIOD_CONDITIONS', 2, formModel.periodRadio) }}</span>
              <span v-if="formModel.periodRadio === '1'">{{ $_out($_getFormattedDateValue(formModel.period)) + "まで" }}</span>
            </el-col>
          </el-row>
          <!-- /期間 -->

          <!-- 預り区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, formModel.depositType)) }}
            </el-col>
          </el-row>
          <!-- /預り区分 -->

          <!-- 決済方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              {{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, formModel.kessaiHoho)) }}
            </el-col>
          </el-row>
          <!-- /決済方法 -->

          <!-- 国内注文日時 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内注文日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateTimeValue(formModel.orderDate, 'datetime12' )) }}</span>
            </el-col>
          </el-row>
          <!-- /国内注文日時 -->

          <!-- 現地約定予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>現地約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.localTradeDate)) }}</span>
            </el-col>
          </el-row>
          <!-- 現地約定予定日 -->

          <!-- 国内約定予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内約定予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.businessDaysAfterOrder)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内約定予定日 -->

          <!-- 国内受渡予定日 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内受渡予定日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.domesticSettlementDate)) }}</span>
            </el-col>
          </el-row>
          <!-- /国内受渡予定日 -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /注文内容(復唱項目) -->

    <el-row>
      <div
        style="margin-bottom: 0.5rem;"
      ></div>
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
          <!-- /その他注文内容（ラベル） -->
          <hr>
          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FOREIGN_STOCK_INVITATION_TYPE', 1, formModel.kanyuKbn)) }}</span>
            </el-col>
          </el-row>
          <!-- /勧誘区分 -->

          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('FOREIGN_STOCK_ORDER_METHOD_TYPE', 1, formModel.receiveOrderTypeName)) }}</span>
            </el-col>
          </el-row>
          <!-- /受注方法 -->

          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('INSIDER_CONFIRM', 2, formModel.checkInsider)) }}</span>
            </el-col>
          </el-row>
          <!-- /確認項目 -->
        </el-card>
      </el-col>
    </el-row>
    <!-- /その他注文内容 -->

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="messagesIsVisible">
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <el-row
            v-if="formModel.tradeNoticeInfoBrandMsg.length > 0"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, formModel.tradingCautionInformation)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.noticeInfoAlert.length > 0"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('NOTICE_INFO_CONFIRM', 1, formModel.noteInfoCheckbox)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.noticeAlert.length > 0"
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, formModel.noteLimitCheck)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.stopOrderInstantMessage.length > 0 "
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>逆指値注文即時発火</span>
            </el-col>

            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('IMMEDIATE_STOP_ORDER', 1, formModel.methodCheck)) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="formModel.nextBusinessDayMessage.length > 0 "
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>翌営業日向け注文</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getCodeValue('ORDER_FOR_NEXT_BUSINESS_DAY', 1, formModel.nextDayCheck)) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="text-align: left; margin-top: 1rem;"
      >
        <ifa-button
          id="btnBack"
          text="注文状況一覧へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleClick"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formModel: { type: Object, required: true },
    customerInfo: { type: [Object, Function], required: true },
    stockInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'move-to-order-list', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
    }
  },
  computed: {
    // 顧客情報関連
    infoIcon() {
      return this.customerInfo.noticeInfoLevel === '3' ? 'alert-icon' : 'notice-icon'
    },
    // アラーム確認
    messagesIsVisible() {
      if ((this.formModel.noticeInfoAlert && this.formModel.noticeInfoAlert.length > 0) ||
        (this.formModel.noticeAlert && this.formModel.noticeAlert.length > 0) ||
        (this.formModel.tradeNoticeInfoBrandMsg && this.formModel.tradeNoticeInfoBrandMsg.length > 0) ||
        (this.formModel.stopOrderInstantMessage && this.formModel.stopOrderInstantMessage.length > 0) ||
        (this.formModel.nextBusinessDayMessage && this.formModel.nextBusinessDayMessage.length > 0)) {
        return true
      } else {
        return false
      }
    },
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 背景色
    bgColor() {
      if (this.formModel.tradeCd === '4') {
        return '#fef0f0'
      } else if (this.formModel.tradeCd === '5') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    }
  },
  // エラー/警告/情報の更新
  methods: {
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // Infoアイコンイベント
    stopPropagation(event) {
      event.stopPropagation()
    },
    // 注文状況一覧へボタン
    handleClick() {
      this.$emit('move-to-order-list')
    },
    // 価格に表示する内容を生成する
    getPrice() {
      let str = ''
      if (this.formModel.orderPriceKindList === '3' || this.formModel.orderPriceKindList === '4') {
        str += '(現在値が' + this.$_out(this.$_withCommaZeroPadding(this.formModel.stopOrderPrice, 2))
        str += this.$_out(this.formModel.currencyCode) + (this.formModel.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で'
      }
      if (this.formModel.orderPriceKindList === '3') {
        str += this.$_out(this.$_withCommaZeroPadding(this.formModel.hiddenOrderPrice, 2)) + this.$_out(this.formModel.currencyCode) + 'で発注)'
      } else if (this.formModel.orderPriceKindList === '4') {
        str += '成行で発注)'
      }
      return str
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.foreign-margin_dialog-class {
  .el-dialog__body {
    font-weight: normal !important;
  }
}
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
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: red;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.input-table {
  width:95%;
  margin: 10px;
  border-collapse: collapse;
  background-color:  #fff;
  color: rgb(72,116,173);
  font: 11px/1.3 sans-serif;
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.search_header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  padding-right: 0.5rem;
  height: 25px;
  line-height: 25px;
  white-space: nowrap;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0.5rem;
  top: 0;
}
.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
}
.brand-name {
  width: 100%;
  color: #606266;
  font-weight: bold;
  font-size: 14px;
  padding-left: 0.5rem;
}
.__bold {
  font-weight: bold;
}
.__black {
  color: #606266;
}
.__color_empty {
  color: #bfcbd9;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
}
.info_s {
  display: grid;
  width: 100%;
  grid-template-columns: 5rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__current {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__empty {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 10px;
  // height: 25px;
  // line-height: 25px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
</style>
