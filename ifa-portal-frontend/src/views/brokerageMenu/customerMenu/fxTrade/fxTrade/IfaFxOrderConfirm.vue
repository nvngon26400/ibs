<template>
  <!-- 為替取引注文確認ダイアログ -->
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
    @open="onShow"
  >

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnReturn"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- 説明 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="['注文はまだ完了していません。画面下の注文発注ボタンを押下してください。', '約定価格､受渡金額は注文の約定タイミングでの為替レートをもとに確定いたします｡予めご留意ください｡']"
      :error-messages="formModel.orderWarningexceedingThreshold ? [ formModel.orderWarningexceedingThreshold ] : []"
    ></ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col
        :offset="1"
      >
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
        <span>{{ customerInfo.customerNameKanji + '（' + customerInfo.customerNameKana + '）' }}</span>
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
            <span>ご注文内容（復唱項目）</span>
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
              <span :class="formModel.tradeKbn === '3' ? 'font-color__plus bold' : 'font-color__minus bold'">
                {{ $_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn) }}
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
                {{ $_withCommaZeroPadding(formModel.exchangeOrderAmount, setDigit(formModel.decimalLength)) }}
                {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>概算用レート</span>
            </el-col>
            <el-col :span="16">
              <div>
                <span>
                  {{ Number(formModel.decimalLength) > 2 ? $_withCommaZeroPadding(formModel.fxRate, formModel.decimalLength) : formModel.fxRate }}円 /
                  {{ formModel.denominator === '1' ? '' : $_addComma(formModel.denominator) }}
                  {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
                </span>
                <span style="font-size: 12px;">
                  ({{ $_getFormattedDateTimeValue(formModel.approximateRateExchangeRateDateTime) }})
                </span><br>
                <span v-if="formModel.tradeKbn === '3'">
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
              <span>概算受渡金額</span>
            </el-col>
            <el-col :span="16">
              <span>
                {{ $_addComma(formModel.yenDeliveryAmount) }}円
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
                {{ $_out($_getCodeValue('FX_ACCOUNT_CLASS', 1, formModel.accountType)) }}
              </span>
            </el-col>
          </el-row>
        </el-card>

        <!-- アラート内容確認 -->
        <el-card
          v-if="formModel.warningThreshold && formModel.orderWarningexceedingThreshold"
          class="box-card"
          :class="formModel.tradeKbn === '3' ? 'buy-background-color_card' : 'sell-background-color_card'"
          style="margin-top: 5px"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span style="color: red;">アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
              style="padding-left: 0;"
            >
              <div class="required-mark">*</div>
              <span
                style="color: red;"
              >注文金額{{ $_addComma(formModel.warningThreshold) }}
                {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}超過
              </span>
            </el-col>
            <el-col :span="16">
              <el-checkbox
                v-model="formModel.warningMessage"
                style="color: red;"
                label="確認済"
              ></el-checkbox>
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

    <!-- 注文発注ボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem; margin-bottom: 2rem; text-align: left;"
      >
        <ifa-button
          id="btnOrder"
          :disabled="($store.getters.userAccount.medUsers.privId === '1' || $store.getters.userAccount.medUsers.privId === '2') || (formModel.warningThreshold && formModel.orderWarningexceedingThreshold && !formModel.warningMessage)"
          text="注文発注"
          action-type="requestAction"
          :action-id="'SUB0202_0502-02_2#A001'"
          style="padding-left: 0;"
          :request-model="IfaFxOrderConfirmA001RequestModel"
          @response-handler="a001ActionHandler"
          @response-error-handler="a001ActionErrorHandler"
        ></ifa-button>
      </el-col>
    </el-row>
    <div>
      <el-row style="margin-top: 0.5rem; margin-bottom: 1rem;">
        <el-col
          :span="22"
          :offset="1"
        >
          <el-card
            class="box-card"
            style="padding: 0.5rem 1.5rem;"
          >
            <el-row style="display: block;">
              <span><strong>以下の事項にご注意ください</strong></span>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  外国の休日等の理由により、為替取引の約定日または受渡日は変更となる場合がございます。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  表示している「参考レート」は、買い・売りの実勢レートにそれぞれに対して当社為替スプレッドを加味しております。
                  「為替注文入力」・「為替注文確認」画面等の段階で表示されるレートは当該時点の参考レートであり、最終的な約定レート（確定値）とは異なります。
                  約定時に実際に適用されるレートは「為替取引注文照会」画面にてご確認ください。
                  なお、為替相場の状況によっては参考レートと約定レートの間に大きな乖離が発生する場合がありますのでご注意ください。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  前受金制のため、日本円売り外貨買いの場合は、ご注文をいただいてから約定となるまでの間、
                  日本円の出金可能額（出金可能額は、買付余力とは異なる場合があります。）から概算受渡金額を拘束させていただきます。
                  概算受渡金額の算出は実勢レートに対し、注文時上乗せレート、為替スプレッドを加味しております。
                  実際の受渡金額は、約定時のレートより計算されます。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  外貨売り日本円買いの場合は、外貨の出金可能額をご注文の外貨金額分拘束させていただきます。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  為替相場が大きく動いた場合、実際の約定金額が概算受渡金額を上回り、かつ、
                  日本円の出金可能額が不足した場合、為替取引のご注文は約定となりませんのでご注意ください。
                  なお、実際の約定金額が概算受渡金額を上回った場合でも、実際の約定金額分の日本円の出金可能額がある場合には、為替取引のご注文は約定となります。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  リアルタイム為替では、為替市場の流動性が急激に低下した場合や、経済指標発表時などの相場急変時において、
                  当社所定のサービス提供時間内であっても当社の判断で取引を停止する可能性がありますのであらかじめご了承ください。
                </span>
              </td>
            </el-row>
            <el-row style="display: block;">
              <td style="width: 1.5rem; vertical-align: top;">
                <el-icon><Warning></Warning></el-icon>
              </td>
              <td>
                <span>
                  リアルタイム約定通貨の注文結果について、システム障害や通信トラブル等により「為替取引注文照会」画面の注文状況において「注文中」と表示される場合がございます。
                  このような場合は注文結果が更新されるまで時間がかかる可能性があります。
                  時間をおいてから改めてご確認くださいますよう、お願いいたします。
                </span>
              </td>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
    </div>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaFxOrderConfirmA001RequestModel } from './js/IfaFxOrderConfirmA001RequestModel'
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
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      isAgree: false,
      messageKey: 0
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
    },
    IfaFxOrderConfirmA001RequestModel() { return new IfaFxOrderConfirmA001RequestModel(this.formModel) }
  },
  methods: {
    onShow() {
      this.messageKey++
    },
    a001ActionHandler(data) {
      this.$emit('order-finish', data.dataList[0])
    },
    a001ActionErrorHandler() {
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文発注ボタン
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
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
:deep(.el-checkbox__label) {
  color: #f00;
  font-size: 16px;
  font-weight: bold;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.required-mark {
  display: inline-block;
  color: #ff2b2b;
  margin-right: 2px;
}
</style>
