<template>
  <!-- SUB0202_0301-03_2_外国現物取引注文取消完了 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
    width="1200px"
    :style="{ 'background-color': bgColor }"
    @open="onShow"
  >
    <!-- ヘッダ -->
    <el-row style="margin-top: 40px;"></el-row>
    <ifa-message-area
      :main-messages="['下記の内容で取消を受け付けました。']"
    ></ifa-message-area>
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold; color: black;"
    >
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
        style="font-size: 20px;"
        class="_bold_black_l"
      >
        <el-icon style="position: relative; top: 3px;">
          <component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
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
                >［{{ $_out(formData.brandInformationList.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(formData.brandInformationList.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width:253px;"
              ><span>&nbsp;&nbsp;上場市場：{{ $_out(form.marketInformationList.marketName) }}</span></div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width: auto; padding-right: 10px;"
              > <span style="white-space: pre;">&nbsp;&nbsp;国籍：{{ $_out($_getCodeValue('NATIONALITY_CODE', 2, form.marketInformationList.countryCode)) }}</span></div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>
    <!-- 注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding: 0.5rem 0.5rem;"
          >
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <!-- 国内注文日時 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内注文日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateTimeValue(form.orderDate)) }}</span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16"
                    :class="form.request.tradeCd === '0' ? 'font-color__plus bold' : 'font-color__minus bold'"
            >
              <span>
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, form.request.tradeCd)) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.foreignQuantity ? `${$_withCommaInteger(form.foreignQuantity)}株` : '0' }} </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, form.priceConditionsType)) }}
              </span>
              <el-col v-if="form.priceConditionsType == '1'">
                {{ form.hiddenOrderPrice && form.limitPriceText ? `${($_withCommaZeroPadding(form.hiddenOrderPrice,2))} ${(form.limitPriceText)}` : '0' }}
              </el-col>
              <el-col v-else-if="form.priceConditionsType == '3' || form.priceConditionsType == '4'"
                      :span="16"
              >
                <span>{{ getStopPrice() }}</span>
              </el-col>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>期間</span>
            </el-col>
            <el-col :span="16">
              <span v-if="form.periodRadio == '0'">
                {{ $_out($_getCodeValue('PERIOD_CONDITIONS', 2, form.periodRadio)) }}
              </span>
              <span v-else-if="form.periodRadio == '1'">
                {{ $_out($_getFormattedDateValue(form.period)) + "まで" }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <!-- 区分値取得 -->
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, form.depositType)) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <!-- 区分値取得 -->
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, form.kessaiHoho)) }}
              </span>
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
        style="margin-top: 1rem"
      >
        <!--  取消完了/戻る -->
        <span class="dialog-footer">
          <ifa-button
            id="btnBack"
            text="注文状況一覧へ"
            color="primary"
            action-type="originalAction"
            style="padding-left: 0;"
            @app-action-handler="moveToOrderListA002"
          ></ifa-button>

        </span>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignSpotTradeOrderCancelCompleteFormModel } from './js/IfaForeignSpotTradeOrderCancelCompleteFormModel'
import { IfaForeignSpotTradeOrderCancelConfirmA010RequestModel } from './js/IfaForeignSpotTradeOrderCancelConfirmA010RequestModel'

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
  emits: ['close-modal', 'cancel-finish', 'move-to-order-list'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignSpotTradeOrderCancelCompleteFormModel()
    }
  },
  computed: {
    IfaForeignSpotTradeOrderCancelConfirmA010RequestModel() {
      return new IfaForeignSpotTradeOrderCancelConfirmA010RequestModel(this.form)
    },
    bgColor() {
      if (this.form.request.tradeCd === '0') {
        return '#fef0f0'
      } else {
        return '#ecf5ff'
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
    },
    getStopPrice() {
      const stopOrderPrice = this.form.stopOrderPrice2 ? this.$_withCommaZeroPadding(this.form.stopOrderPrice2, 2) : '0'
      const hiddenOrderPrice = this.form.hiddenOrderPrice ? this.$_withCommaZeroPadding(this.form.hiddenOrderPrice, 2) : '0'
      const stopLimitPriceText = stopOrderPrice === '0' ? '' : this.form.limitPriceText
      const hiddenLimitPriceText = hiddenOrderPrice === '0' ? '' : this.form.limitPriceText

      if (this.form.priceConditionsType === '3') {
        const str = '(現在値が' + stopOrderPrice + stopLimitPriceText + (this.form.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で' +
        hiddenOrderPrice + hiddenLimitPriceText + 'で発注)'
        return str
      } else if (this.form.priceConditionsType === '4') {
        const str = '(現在値が' + stopOrderPrice + stopLimitPriceText + (this.form.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で成行で発注)'
        return str
      }
    },
    // 注文状況一覧へボタン
    moveToOrderListA002() {
      this.$emit('move-to-order-list')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
@import "@/styles/orderStatusList.scss";
@import "@/styles/foreignStockOrder.scss";
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
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
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
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.input-table {
  border-collapse: collapse;
  color: rgb(72,116,173);
  font: 11px/1.3 sans-serif;
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.notice-icon {
  font-size: 22px;
  color: $subBlueColor;
  animation: poyopoyo 2.0s 1 normal;
}
.alert-icon {
  font-size: 22px;
  color: $errorColor;
  animation: poyopoyo 2.0s 1 normal;
}
:deep(.el-text){
  font-size: 16px;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
</style>
