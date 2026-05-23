<template>
  <!-- 為替取引注文完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': bgColor }"
    class="status_change"
    :title="form.title.name"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >

    <!-- 説明 -->
    <ifa-message-area
      :main-messages="['下記の内容で注文を受け付けました。']"
    ></ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(customerInfo().butenCode) + '-' + $_out(customerInfo().accountNumber) }}</span>
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
          <el-icon v-if="customerInfo().corporationType === '0'"><Avatar></Avatar></el-icon>
          <el-icon v-if="customerInfo().corporationType === '1'"><OfficeBuilding></OfficeBuilding></el-icon>
        </span>
        <span>{{ customerInfo().customerNameKanji + ' (' + customerInfo().customerNameKana + ')' }}</span>
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
              :span="19"
              class="info-item__header"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__plus"
                style="padding: 0 1rem;"
              >
                {{ $_out($_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn)) }}
              </span>
              <span style="padding: 0 0.5rem;">円</span>
              <img :src="imgs('jp')">
              <span style="padding: 0 0.5rem;">→</span>
              <span style="padding: 0 0.5rem;">{{ $_out(formModel.currency) }}</span>
              <img :src="imgs(formModel.currencyCode.substring(0,2).toLowerCase())">
            </span>
            <span
              v-else
              :span="19"
              class="info-item__header"
              style="display: flex; font-size: 20px;"
            >
              <span
                class="font-color__minus"
                style="padding: 0 1rem;"
              >
                {{ $_out($_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn)) }}
              </span>
              <span style="padding: 0 0.5rem;">{{ $_out(formModel.currency) }}</span>
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
            <el-col
              :span="16"
              style="font-weight: bold;"
            >
              <span :style="formModel.tradeKbn === '3' ? {'color':'#E5004C'} : {'color':'#00847F'}">
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
                {{ ifaFormatUtils.withCommaZeroPadding(formModel.quantity, 2) }} {{ $_out($_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode)) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>約定レート</span>
            </el-col>
            <el-col :span="16">
              <div>
                <span>
                  <span v-if="formModel.decimalLength > '2' && formModel.decimalLength <= '4'">{{ ifaFormatUtils.withCommaZeroPadding(formModel.contractExchangeRate, formModel.decimalLength) }} </span>
                  <span v-if="formModel.decimalLength > '4'">{{ ifaFormatUtils.withCommaZeroPadding(formModel.contractExchangeRate, 4) }} </span>
                  <span v-if="formModel.decimalLength <= '2'">{{ ifaFormatUtils.withCommaZeroPadding(formModel.contractExchangeRate, 2) }} </span>
                  円/{{ $_out($_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode)) }}
                </span>
                <span style="font-size: 12px;">
                  ({{ $_out($_getFormattedDateTimeValue(formModel.contractExchangeRateDateTime, 'datetime12')) }})
                </span>&nbsp;&nbsp;
                <span>
                  適用スプレッド：{{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(formModel.selectedCurrencyInfo)) }}円/{{ $_out($_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode)) }}
                </span>
              </div>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受渡金額</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(formModel.deliveryAmount)) }} 円
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
              <span>{{ $_out($_getCodeValue('FX_TRADE', 2, formModel.tradeTiming)) }}</span>
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
              <span>{{ $_out($_getFormattedDateValue(formModel.settlementDate, 'date8')) }}</span>
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
                総合口座
              </span>
            </el-col>
          </el-row>
        </el-card>

        <!-- アラート内容確認 -->
        <el-card
          v-if="formModel.orderWarningexceedingThreshold !== ''"
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
              注文金額{{ ifaFormatUtils.withCommaNoneZeroPadding(formModel.warningThreshold) }}
              {{ $_out($_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode)) }}超過
            </el-col>
            <el-col :span="6">
              <span>
                {{ $_getCodeValue('EXCEEDING_ORDER_AMOUNT_LIMIT', 1, '1') }}
              </span>
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
            :url-id="formModel.addLinkAttention"
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
          id="btnMove"
          name="btnMove"
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
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { IfaIfaFxOrderCompleteFormModel } from './js/IfaIfaFxOrderCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'

export default {
  components: {
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formModel: { type: Object, required: true },
    customerInfo: { type: [Object, Function], required: true }
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
      ifaFormatUtils: ifaFormatUtils,
      form: new IfaIfaFxOrderCompleteFormModel()
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
    }
  }
}
</script>

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

<style lang="scss">
  .status_change{
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
