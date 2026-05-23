<template>
  <!-- 為替取引注文完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': bgColor }"
    :title="formModel.title"
    width="1200px"
    class="fx-order-class"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
  >

    <!-- 説明 -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました。']"
    ></ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col :offset="1">
        <span>{{ customerInfo.butenCode }}-{{ customerInfo.accountNumber }}</span>
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
          <el-icon v-if="customerInfo.corporationType === '0'"><Avatar></Avatar></el-icon>
          <el-icon v-if="customerInfo.corporationType === '1'"><OfficeBuilding></OfficeBuilding></el-icon>
        </span>
        <span>{{ customerInfo.customerNameKanji + ' (' + customerInfo.customerNameKana + ')' }}</span>
      </el-col>
    </el-row>
    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="background-color: #eee; margin: 0.5rem 0;"
        >
          <el-row>
            <span
              v-if="formModel.tradeKbn === '3'"
              class="info-item__header"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__plus"
                style="padding: 0 1rem;"
              >
                {{ $_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn) }}
              </span>
              <span style="padding: 0 0.5rem;">円</span>
              <img :src="imgs('jp')">
              <span style="padding: 0 0.5rem;">→</span>
              <span style="padding: 0 0.5rem;">{{ formModel.currency }}</span>
              <img :src="imgs(formModel.currencyCode.substring(0,2).toLowerCase())">
            </span>
            <span
              v-else
              class="info-item__header"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__minus"
                style="padding: 0 1rem;"
              >
                {{ $_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn) }}
              </span>
              <span style="padding: 0 0.5rem;">{{ formModel.currency }}</span>
              <img :src="imgs(formModel.currencyCode.substring(0,2).toLowerCase())">
              <span style="padding: 0 0.5rem;">→</span>
              <span style="padding: 0 0.5rem;">円</span>
              <img :src="imgs('jp')">
            </span>
          </el-row>
        </el-card>
      </el-col>
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
            <span>ご注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注文日時</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateTimeValue(formModel.orderDate, 'datetime12')) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注文番号</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formModel.orderNumber) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="16">
              <span :class="formModel.tradeKbn === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">
                {{ $_out($_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn)) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>通貨</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(formModel.currency) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ formModel.tradeKbn === '3' ? '買付数量' : '売却数量' }}</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_withCommaZeroPadding(formModel.quantity, setDigit(formModel.decimalLength)) }}
                {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ formModel.fxTrade === '1' ? '約定レート' : '概算レート' }}</span>
            </el-col>
            <el-col :span="16">
              <div>
                <span>
                  {{ Number(formModel.decimalLength) > 2 ? $_withCommaZeroPadding(formModel.fxRate, formModel.decimalLength) : formModel.fxRate }}円 /
                  {{ formModel.denominator === '1' ? '' : $_addComma(formModel.denominator) }}
                  {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
                </span>
                <span style="font-size: 12px;">
                  ({{ $_getFormattedDateTimeValue(formModel.approximateRateExchangeRateDateTime, 'datetime12') }})
                </span><br>
                <span v-if="formModel.tradeKbn === '3' && formModel.fxTrade === '2'">
                  ※概算用レートは、参考レートに上乗せレートを加味したレートです。
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ formModel.fxTrade === '1' ? '受渡金額' : '概算受渡金額' }}</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_addComma(formModel.deliveryAmount) }} 円
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>約定タイミング</span>
            </el-col>
            <el-col :span="16">
              <span v-if="formModel.fxTrade === '1'">リアルタイム</span>
              <span v-if="formModel.fxTrade === '2'">{{ $_out($_getFormattedDateTimeValue(formModel.tradeDateTime, 'datetime12')) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受渡日</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out($_getFormattedDateValue(formModel.settlementDate)) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>{{ formModel.tradeKbn === '3' ? '買付口座' : '売却口座' }}</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_getCodeValue('FX_ACCOUNT_CLASS', 1, formModel.accountType) }}
              </span>
            </el-col>
          </el-row>
        </el-card>

        <!-- アラート内容確認 -->
        <el-card
          v-if="formModel.orderWarningexceedingThreshold"
          class="box-card"
          style="margin-top: 5px; font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              注文金額{{ $_addComma(formModel.warningThreshold) }}
              {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}超過
            </el-col>
            <el-col :span="16">
              <span>{{ $_getCodeValue('EXCEEDING_ORDER_AMOUNT_LIMIT', 1, '1') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row
      class="_black_ss"
      style="padding-top: 0.5rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>
          ※ご注文の際には､
          <ifa-link
            :disp-name="'こちら'"
            :url-id="25"
          ></ifa-link>
          のご注意事項をご確認ください｡
        </span>
      </el-col>
    </el-row>

    <!-- 注文情報ボタン -->
    <br>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          text="注文照会へ"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="moveToOrderList"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
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
    }
  },
  computed: {
    bgColor() {
      if (this.formModel.tradeKbn === '3') {
        return '#fef0f0'
      } else if (this.formModel.tradeKbn === '1') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    }
  },
  methods: {
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文情報ボタン
    moveToOrderList() {
      this.$emit('move-to-order-list')
    },
    // 国旗の画像を読み込む
    imgs(icon) {
      return require('@/assets/png/flags/32/' + icon + '.png')
    },
    setDigit(value) {
      return Number(value) > 2 ? value : 2
    }
  }
}
</script>

<style lang="scss">
.fx-order-class {
    .el-dialog__header{
      margin-top: 10px;
      padding-bottom: 0px;
      padding-top: 30px;
      .el-dialog__title{
        font-weight: bold;
      }
    }
}
</style>
<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
</style>
