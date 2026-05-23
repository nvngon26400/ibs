<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="formModel.title"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
    width="1200px"
    :style="{ 'background-color': bgColor }"
  >
    <!-- ヘッダ -->
    <ifa-message-area
      :main-messages="['下記の内容で取消を受け付けました｡']"
    ></ifa-message-area>
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber)) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span style="position: relative; top: 3px;">
          <span v-if="customerInfo.corporationType === '法人'">
            <el-icon><OfficeBuilding></OfficeBuilding></el-icon>
          </span>
          <span v-else>
            <el-icon><UserFilled></UserFilled></el-icon>
          </span>
        </span>
        <span>{{ $_out(customerInfo.customerNameKanji) + '（' + $_out(customerInfo.customerNameKana) + '）' }}</span>
        <template v-if="customerInfo.noticeInfoLevel > 1">
          <!-- 注意情報 -->
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </template>
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
          style="background-color: #eee; padding-right: 10px;"
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
                >［{{ $_out(formModel.brandInfo.brandCode) }}］</span>
                <span
                  style="display: inline-block; word-break: break-all;"
                >{{ $_out(formModel.brandInfo.brandName) }}</span>
              </div>
              <div
                class="_bold_black_l"
                style="font-size: 20px; white-space: nowrap; width:253px;"
              >&nbsp;&nbsp;上場市場：{{ $_out(formModel.marketInfo.marketName) }}</div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容 -->
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
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>国内注文日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateTimeValue(formModel.orderDate)) }}</span>
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
                    :class="formModel.tradeKbn === '3' ? 'font-color__plus bold' : 'font-color__minus bold'"
            >
              <span>
                {{ $_out($_getCodeValue('FOREIGN_STOCK_TRADE_CLASS', 1, formModel.tradeCd)) }}
              </span>
              <span>（
                {{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, formModel.marginDueDate)) }}
                ）</span>
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
              <span>{{ $_out($_addComma(formModel.foreignQuantity)) }} 株</span>
            </el-col>
          </el-row>
          <!-- 返済建玉指定方法 -->
          <div v-if="formModel.tradeCd === '4' || formModel.tradeCd === '5'">
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>返済建玉指定方法</span>
              </el-col>
              <el-col :span="16">
                <!-- 返済選択順序 -->
                <span>
                  {{ $_out($_getCodeValue('FOREIGN_REPAY_METHOD', 1, formModel.repaymentMethodRadio)) }}
                  (
                  {{ $_out($_getCodeValue('FOREIGN_REPAY_ORDER', 1, formModel.sortOrderRadio)) }}                    )
                </span>
              </el-col>
            </el-row>
          </div>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('SELECT_ABLE_PRICE_CONDITIONS', 1, formModel.orderPriceKindList)) }}
              </span>
              <el-col v-if="formModel.orderPriceKindList == '1'">
                {{ $_out($_withCommaZeroPadding(formModel.hiddenOrderPrice,2)) }} {{ $_out(formModel.currencyCode) }}
              </el-col>
              <el-col v-else-if="formModel.orderPriceKindList == '3' || formModel.orderPriceKindList == '4'"
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
              <span v-if="formModel.periodRadio == '0'">
                {{ $_out($_getCodeValue('PERIOD_CONDITIONS', 2, formModel.periodRadio)) }}
              </span>
              <span v-else-if="formModel.periodRadio == '1'">
                {{ $_out($_getFormattedDateValue(formModel.period)) + "まで" }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>預り区分</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 2, formModel.depositType)) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>決済方法</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out($_getCodeValue('SETTLEMENT_TYPE', 1, formModel.currencyTypeRadio)) }}
              </span>
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
          text=" 注文状況一覧へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="toOrderStatusListA002()"
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
      infoView: false
    }
  },
  computed: {
    bgColor() {
      if (this.formModel.tradeCd === '2') {
        return '#fef0f0'
      } else if (this.formModel.tradeCd === '3') {
        return '#ecf5ff'
      } else if (this.formModel.tradeCd === '4') {
        return '#fef0f0'
      } else if (this.formModel.tradeCd === '5') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    }
  },
  methods: {
    stopPropagation(event) {
      event.stopPropagation()
    },
    getStopPrice() {
      if (this.formModel.orderPriceKindList === '3') {
        const str = '(現在値が' + this.$_withCommaZeroPadding(this.formModel.stopOrderPrice2, 2) + this.formModel.currencyCode + (this.formModel.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で' +
        this.$_withCommaZeroPadding(this.formModel.hiddenOrderPrice, 2) + this.formModel.currencyCode + 'で発注)'
        return str
      } else if (this.formModel.orderPriceKindList === '4') {
        const str = '(現在値が' + this.$_withCommaZeroPadding(this.formModel.stopOrderPrice2, 2) + this.formModel.currencyCode + (this.formModel.tradeKbn === '3' ? '以上' : '以下') + 'になった時点で成行で発注)'
        return str
      }
    },
    // 注文状況一覧へボタン
    toOrderStatusListA002() {
      this.$emit('move-to-order-list')
    },
    infoOpen() {
      this.infoView = !this.infoView
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
@import "@/styles/orderStatusList.scss";
@import "@/styles/foreignStockOrder.scss";
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
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
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
.brand_area_flex {
  display: flex;
  align-items: flex-start;
}
:deep(.el-text){
  font-size: 16px;
}
</style>
