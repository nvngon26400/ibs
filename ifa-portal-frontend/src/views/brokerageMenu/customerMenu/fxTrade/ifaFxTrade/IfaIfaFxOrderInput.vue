<template>
  <!-- 為替取引 -->
  <div>
    <!-- 注文入力 -->
    <div v-if="orderInputVisible">
      <screen-title :text="form.title.name"></screen-title>
      <div class="caption_card">
        <el-row>
          <!-- 参考レート -->
          <el-row style="margin-bottom: 0.5rem;">
            <el-col
              :span="24"
            >
              <el-card
                class="box-card"
                style="background-color: #eee; padding: 0.5rem 1.5rem;"
              >
                <el-row>
                  <el-col
                    :span="24"
                    class="rate-button"
                  >
                    <span class="info-item__value __right">(更新日時：{{ $_out($_getFormattedDateTimeValue(form.updateTime, 'datetime12')) }})</span>
                    <ifa-button
                      id="btnUpdate"
                      name="btnUpdate"
                      text="更新"
                      color="primary"
                      icon="RefreshRight"
                      small=""
                      action-id="SUB0202_0503-02_1#A002"
                      action-type="requestAction"
                      :request-model="a002RequestModel"
                      @response-handler="a002Action($event)"
                      @response-error-handler="responseHandlerA002($event)"
                    ></ifa-button>
                  </el-col>
                </el-row>
                <div style="max-width: 1000px;">
                  <el-row>
                    <el-col
                      :span="3"
                      class="info-item__header __left __border"
                    >
                      <span>参考レート</span>
                    </el-col>
                    <el-col
                      :span="8"
                      class="info-item__header __right __border"
                    >
                      <span>
                        <span v-if="form.decimalLength > '2' && form.decimalLength <= '4'">{{ ifaFormatUtils.withCommaZeroPadding(form.referenceRate, form.decimalLength) }} </span>
                        <span v-if="form.decimalLength > '4'">{{ ifaFormatUtils.withCommaZeroPadding(form.referenceRate, 4) }} </span>
                        <span v-if="form.decimalLength <= '2'">{{ ifaFormatUtils.withCommaZeroPadding(form.referenceRate, 2) }} </span>
                      </span>
                      <span>&nbsp;円/{{ form.denominator === '1' ? '': $_out(ifaFormatUtils.withCommaNoneZeroPadding(form.denominator)) }}
                        {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                      </span>
                    </el-col>
                    <div
                      style="display: inline-block;"
                      class="info-item__value __left __border"
                    >
                      <span style="padding-left: 0.5rem;">※適用スプレッド：{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.adjustPrice, 2)) }} 円/{{ form.denominator === '1' ? '': $_out(ifaFormatUtils.withCommaNoneZeroPadding(form.denominator)) }}
                        {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                      </span>
                    </div>
                  </el-row>

                  <div>
                    <el-row>
                      <el-col
                        :span="3"
                        class="info-item__header __left __border"
                      >
                        <span>
                          {{ form.tradeKbn === '3' ? '買付可能数量' : '売却可能数量' }}
                        </span>
                      </el-col>
                      <el-col
                        :span="3"
                        class="info-item__header __right __border"
                      >
                        <span size="small">総合口座</span>
                      </el-col>
                      <el-col
                        :span="5"
                        class="info-item__header __right __border"
                      >
                        <span>
                          {{ $_out(ifaFormatUtils.withCommaZeroPadding(form.maxOrderableQuantity, 2)) }}
                        </span>
                        <span>&nbsp;
                          {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                        </span>
                      </el-col>
                      <div
                        v-if="form.tradeKbn === '3'"
                        style="display: inline-block;"
                        class="info-item__value __left __border"
                      >
                        <span>({{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(form.approximatePurchaseAmount)) }} 円分)</span>
                      </div>
                    </el-row>
                    <el-row>
                      <el-col
                        :span="24"
                        class="info-item__value __left"
                        style="height: auto; font-size: 12px;"
                      >
                      </el-col>
                    </el-row>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-card
            :style="form.tradeKbn === '3' ? {'background-color':'#fef0f0'} : {'background-color':'#ecf5ff'}"
            class="box-card"
          >
            <!-- 注文入力フォーム -->
            <div class="form-container">
              <el-form
                ref="form"
                :model="form"
                label-width="160px"
                class="form-main"
              >
                <div class="form-area__section">
                  <!-- 注文方法 -->
                  <el-form-item
                    label="取引種別"
                    style="font-weight: bold;"
                    class="trade-type"
                  >
                    <div style="display: flex; align-items: flex-start;">
                      <div
                        style="display: inline-block; margin-right: 20px; min-width: 60px;"
                      >
                        <span
                          :style="form.tradeKbn === '3' ? {'color':'#E5004C'} : {'color':'#00847F'}"
                        >
                          <span :style="form.tradeKbn === '3' ? {'color':'#E5004C'} : {'color':'#00847F'}">
                            {{ $_out($_getCodeValue('SELL_BUY_TYPE', 1, form.tradeKbn)) }}
                          </span>注文
                        </span>
                      </div>
                      <div style="display: inline-block; width: 100%;">
                        <span
                          v-if="form.tradeKbn === '3'"
                          style="display: flex; line-height: 32px;"
                        >
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >円</span>
                          <img :src="imgs('jp')">
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >→</span>
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >{{ form.currency }}</span>
                          <img :src="imgs(form.currencyCode.substring(0,2).toLowerCase())">
                        </span>
                        <span
                          v-else
                          style="display: flex; line-height: 32px;"
                        >
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >{{ form.currency }}</span>
                          <img :src="imgs(form.currencyCode.substring(0,2).toLowerCase())">
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >&nbsp;→&nbsp;</span>
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >円</span>
                          <img :src="imgs('jp')">
                        </span>
                      </div>
                      <div
                        class="__right"
                        style="min-width:200px;"
                      >
                        <!-- リセットボタン -->
                        <ifa-button
                          id="btnReset"
                          name="btnReset"
                          text="リセット"
                          color="secondary"
                          action-type="originalAction"
                          @app-action-handler="a006Action()"
                        ></ifa-button>

                        <!-- 戻るボタン -->
                        <ifa-button
                          id="btnBack"
                          name="btnBack"
                          text="戻る"
                          color="secondary"
                          action-type="originalAction"
                          @app-action-handler="handleCloseModal"
                        ></ifa-button>
                      </div>
                    </div>
                  </el-form-item>
                </div>
                <!-- フォームアイテム: 売却方法(売却のみ) -->
                <div
                  v-if="form.tradeKbn === '1'"
                  class="form-area__section"
                >
                  <el-form-item
                    label="売却方法"
                    prop="saleMethod"
                    inline-message="true"
                    style="font-weight: bold;"
                  >
                    <el-col :span="12">
                      <ifa-input-radio
                        id="saleMethod"
                        v-model="form.saleMethod"
                        code-list-id="SELL_METHOD"
                        :disp-pattern="1"
                        :select-pattern="1"
                        required
                        @change="a003Action()"
                      >
                      </ifa-input-radio>
                    </el-col>
                  </el-form-item>
                </div>

                <!-- フォームアイテム: 数量の指定方法 -->
                <div class="form-area__section">
                  <el-form-item
                    label="数量の指定方法"
                    prop="quantityDesignationMethod"
                    inline-message="true"
                    style="font-weight: bold;"
                    class="radio_margin"
                  >
                    <el-col :span="12">
                      <ifa-input-radio
                        id="quantityDesignationMethod"
                        v-model="form.quantityDesignationMethod"
                        code-list-id="FX_QUANTITY_DESIGNATION_METHOD"
                        :disp-pattern="1"
                        :select-pattern="1"
                        :disabled="quantityDesignationMethodInputDisabled"
                        required
                        @change="a004Action"
                      >
                      </ifa-input-radio>
                    </el-col>
                  </el-form-item>
                </div>

                <!-- フォームアイテム: 数量 -->
                <div class="form-area__section">
                  <el-row>
                    <el-col style="display: flex; align-items: flex-start;">
                      <div style="display: inline-block; min-width: 550px;">
                        <ifa-input-amount
                          v-if="form.quantityDesignationMethod === '1'"
                          id="foreignVolume"
                          ref="foreignVolume"
                          v-model="form.foreignVolume"
                          prop="foreignVolume"
                          :label="form.tradeKbn === '3' ? '買付数量' : '売却数量'"
                          :min="(form.tradeKbn === '1' && form.saleMethod === '2') ? 0 : form.closableQuantity"
                          :max="form.foreignDesignationMaxValue"
                          :step="form.foreignButtonRange"
                          :step-for-validation="form.tradeUnit"
                          :initial-step="Math.max(form.closableQuantity, form.foreignButtonRange)"
                          :disabled="volumeInputDisabled"
                          :domain="IfaForeign224DomainModel"
                          :unit="form.currencyCode"
                          :digit="2"
                          support
                          required
                          @change="a005Action()"
                          @keydown.enter.prevent
                        >
                        </ifa-input-amount>
                        <ifa-input-amount
                          v-if="form.quantityDesignationMethod === '2'"
                          id="domesticVolume"
                          v-model="form.domesticVolume"
                          prop="domesticVolume"
                          :label="form.tradeKbn === '3' ? '買付数量' : '売却数量'"
                          :min="form.yenDesignationMinValue"
                          :max="form.yenDesignationMaxValue"
                          :step="100"
                          :step-for-validation="1"
                          :initial-step="Math.max(form.yenDesignationMinValue, 100)"
                          :disabled="volumeInputDisabled"
                          support
                          :domain="IfaYen220DomainModel"
                          :unit="'円'"
                          :digit="0"
                          required
                          @change="a005Action()"
                          @keydown.enter.prevent
                        >
                        </ifa-input-amount>
                      </div>
                      <div
                        style="font-size: 12px; height: 45px; display: flex; align-items: center;"
                      >
                        <span
                          v-if="form.saleMethod === '1' || form.saleMethod === ''"
                        >
                          取引単位：{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.closableQuantity, 2)) }}
                          {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                          以上､
                          <span v-if="form.tradeLimitMax > '0'">{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.tradeLimitMax, 2)) }}
                            {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                            以下</span>
                          ､{{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(form.tradeUnit)) }}
                          {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                          単位
                        </span>
                        <span
                          v-if="form.saleMethod === '2'"
                        >1回の取引における売却数量上限は{{ $_out(ifaFormatUtils.withCommaZeroPadding(form.tradeLimitMax, 2)) }}
                          {{ $_out($_getCodeValue('CURRENCY_CODE', 3, form.currencyCode)) }}
                          になります。</span>
                      </div>
                    </el-col>
                  </el-row>
                </div>

                <!-- 概算受渡金額 -->
                <div>
                  <el-row style="padding-top: 1rem; padding-bottom: 1rem;">
                    <el-col
                      :span="7"
                      class="info-item__header __left"
                      style="padding-left: 178px;"
                    >
                      {{ form.quantityDesignationMethod === '1' || form.saleMethod === '2' ? '概算受渡金額' : '数量' }}
                    </el-col>
                    <el-col
                      :span="5"
                      class="info-item__value __left"
                    >
                      <el-text>{{ form.quantityDesignationMethod === '1' || form.saleMethod === '2' ? $_out(ifaFormatUtils.withCommaInteger(a005Action())) : $_out(ifaFormatUtils.withCommaZeroPadding(a005Action(), 2)) }}</el-text>
                      {{ form.quantityDesignationMethod === '1' || form.saleMethod === '2' ? '円' : form.currencyCode }}

                    </el-col>
                  </el-row>
                </div>

                <!-- 注意事項 -->
                <div>
                  <el-row>
                    <div
                      class="info-item__value __left"
                      style="height: auto; font-size: 12px; margin-left: 178px;"
                    >
                      <li v-if="form.tradeKbn === '3' && form.quantityDesignationMethod === '1'">
                        <span>※概算受渡金額は、概算用レートで計算（円未満の金額は切り上げ）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り捨て）いたします。</span>
                      </li>
                      <li v-if="form.tradeKbn === '3' && form.quantityDesignationMethod === '2'">
                        <span>
                          ※円で指定いただいた金額以下で買付可能な外貨を「数量」として表示しています。<br>
                          「数量」は概算用レートで計算（小数点以下切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り捨て）いたします。為替相場の変動により円で指定した金額を上回る場合がございます。<br>
                          ※当該数量に基づき算出した概算受渡金額（円貨）を次の確認画面で表示いたします。
                        </span>
                      </li>
                      <li v-if="form.tradeKbn === '1' && (form.quantityDesignationMethod === '1' || form.saleMethod === '2')">
                        <span>※概算受渡金額は、概算用レートで計算（円未満の金額は切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り上げ）いたします。</span>
                      </li>
                      <li v-if="form.tradeKbn === '1' && form.quantityDesignationMethod === '2'">
                        <span>
                          ※円で指定いただいた金額以下で売却可能な外貨を「数量」として表示しています。<br>
                          「数量」は概算用レートで計算（小数点以下切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り上げ）いたします。為替相場の変動により円で指定した金額を下回る場合がございます。<br>
                          ※当該数量に基づき算出した概算受渡金額（円貨）を次の確認画面で表示いたします。
                        </span>
                      </li>
                    </div>
                  </el-row>
                </div>

                <!-- 注文確認ボタン -->
                <!-- Note: タブ移動での順番の関係でリセットボタンよりも前に配置する必要あり -->
                <el-row>
                  <ifa-button
                    id="btnOrderConfirm"
                    name="btnOrderConfirm"
                    class="orderinput-button"
                    text="注文確認"
                    :form="formRef"
                    action-id="SUB0202_0503-02_1#A008"
                    action-type="requestAction"
                    :request-model="a008RequestModel"
                    @response-handler="a008Action($event)"
                    @response-error-handler="responseHandlerA008($event)"
                  ></ifa-button>
                </el-row>
              </el-form>
            </div>
            <div class="form-container">
              <el-form
                class="form-main"
              >
                <el-row style="padding-right: 13px; padding-left: 17.6px;">
                  <el-card
                    class="box-card"
                    style="padding: 0.5rem 1.5rem;"
                  >
                    <el-row>
                      <span><strong>以下の事項にご注意ください</strong></span>
                    </el-row>
                    <table>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>外国の休日等の理由により、為替取引の約定日または受渡日は変更となる場合がございます。</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>表示している「参考レート」は、買い・売りの実勢レートにそれぞれに対して当社為替スプレッドを加味しております。「為替注文入力」・「為替注文確認」画面等の段階で表示されるレートは当該時点の参考レートであり、最終的な約定レート（確定値）とは異なります。約定時に実際に適用されるレートは「為替取引注文照会」画面にてご確認ください。なお、為替相場の状況によっては参考レートと約定レートの間に大きな乖離が発生する場合がありますのでご注意ください。</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>前受金制のため、日本円売り外貨買いの場合は、ご注文をいただいてから約定となるまでの間、日本円の出金可能額（出金可能額は、買付余力とは異なる場合があります。）から概算受渡金額を拘束させていただきます。
                            <br>概算受渡金額の算出は実勢レートに対し、注文時上乗せレート、為替スプレッドを加味しております。実際の受渡金額は、約定時のレートより計算されます。</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>外貨売り日本円買いの場合は、外貨の出金可能額をご注文の外貨金額分拘束させていただきます。</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>為替相場が大きく動いた場合、実際の約定金額が概算受渡金額を上回り、かつ、日本円の出金可能額が不足した場合、為替取引のご注文は約定となりませんのでご注意ください。
                            <br>なお、実際の約定金額が概算受渡金額を上回った場合でも、実際の約定金額分の日本円の出金可能額がある場合には、為替取引のご注文は約定となります。
                          </span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>リアルタイム為替では、為替市場の流動性が急激に低下した場合や、経済指標発表時などの相場急変時において、当社所定のサービス提供時間内であっても当社の判断で取引を停止する可能性がありますのであらかじめご了承ください。</span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>リアルタイム約定通貨の注文結果について、システム障害や通信トラブル等により「為替取引注文照会」画面の注文状況において「注文中」と表示される場合がございます。
                            <br>このような場合は注文結果が更新されるまで時間がかかる可能性があります。時間をおいてから改めてご確認くださいますよう、お願いいたします。
                          </span>
                        </td>
                      </tr>
                    </table>
                  </el-card>
                </el-row>
              </el-form>
            </div>
          </el-card>
        </el-row>
      </div>
    </div>

    <!-- ダイアログ -->
    <ifa-ifa-fx-order-confirm
      ref="IfaIfaFxOrderConfirm"
      :is-visible="confirmationDialogVisible"
      :form-model="form"
      :customer-info="customerInfo"
      @close-modal="handleCloseModal"
      @order-finish="handleOrderFinish"
    ></ifa-ifa-fx-order-confirm>

    <ifa-ifa-fx-order-complete
      :is-visible="finishDialogVisible"
      :form-model="form"
      :customer-info="customerInfo"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-ifa-fx-order-complete>
  </div>

</template>

<script>

import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaIfaFxOrderInputFormModel } from './js/IfaIfaFxOrderInputFormModel'
import { IfaIfaFxOrderInputA002RequestModel } from './js/IfaIfaFxOrderInputA002RequestModel'
import { IfaIfaFxOrderInputA008RequestModel } from './js/IfaIfaFxOrderInputA008RequestModel'
import IfaForeign224DomainModel from '@/resource/domain/IfaForeign224DomainModel.json'
import IfaYen220DomainModel from '@/resource/domain/IfaYen220DomainModel.json'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import IfaIfaFxOrderConfirm from './IfaIfaFxOrderConfirm'
import IfaIfaFxOrderComplete from './IfaIfaFxOrderComplete'
import { BigNumber } from 'bignumber.js'

export default {
  components: {
    IfaIfaFxOrderConfirm,
    IfaIfaFxOrderComplete,
    screenTitle
  },
  props: {
    requestData: {
      type: Object,
      default: () => ({})
    },
    orderInputVisible: {
      type: Boolean,
      default: false
    }
  },
  emits: ['show-tab-by-name', 'handle-close-modal'],
  data() {
    return {
      messages: {
        errors: [],
        warnings: [],
        infos: []
      },
      quantityDesignationMethodInputDisabled: false,
      volumeInputDisabled: false,
      form: new IfaIfaFxOrderInputFormModel(),
      IfaForeign224DomainModel: IfaForeign224DomainModel,
      IfaYen220DomainModel: IfaYen220DomainModel,
      ifaFormatUtils: ifaFormatUtils,
      formRef: {},
      confirmationDialogVisible: false,
      finishDialogVisible: false
    }
  },
  computed: {
    a002RequestModel() {
      return new IfaIfaFxOrderInputA002RequestModel(this.form)
    },
    a008RequestModel() {
      return new IfaIfaFxOrderInputA008RequestModel(this.form)
    }
  },
  watch: {
    orderInputVisible: {
      handler(n, o) {
        if (n) {
          this.a006Action()
          Object.assign(this.form, this.requestData)
        }
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    a002Action(response) {
      this.$_logDebug('responseHandlerA002-OK')
      if (response.dataList.length !== 0) {
        // 参考レート（数値(小数)）
        this.form.referenceRate = response.dataList[0].referenceRate
        // 概算用レート
        this.form.computeExchangeRate = response.dataList[0].computeExchangeRate
        // デノミ（数値(整数)）
        this.form.denominator = response.dataList[0].denominator
        // スプレッド
        this.form.adjustPrice = response.dataList[0].adjustPrice
        // 注文可能数量（数値(整数)）
        this.form.maxOrderableQuantity = response.dataList[0].maxOrderableQuantity
        // 概算買付可能金額（数値(整数)）
        this.form.approximatePurchaseAmount = response.dataList[0].approximatePurchaseAmount
        // 外貨指定最大値
        this.form.foreignDesignationMaxValue = response.dataList[0].foreignDesignationMaxValue
        // 売却可能数量
        this.form.sellAbleQuantity = response.dataList[0].foreignDesignationMaxValue
        // 円指定最小値
        this.form.yenDesignationMinValue = response.dataList[0].yenDesignationMinValue
        // 円指定最大値
        this.form.yenDesignationMaxValue = response.dataList[0].yenDesignationMaxValue
        // 概算受渡金額（数値(整数)）
        this.form.approximateDeliveryAmount = response.dataList[0].approximateDeliveryAmount
        // 概算外貨数量
        this.form.approximateForeignCount = response.dataList[0].approximateForeignCount
        // 更新日時
        this.form.updateTime = response.dataList[0].updateTime
      }
    },
    responseHandlerA002(error) {
      this.$_logWarn('responseErrorHandlerA002-Error', error)
    },
    a008Action(response) {
      this.$_logDebug('responseHandlerA008-OK')
      if (response.dataList.length !== 0) {
        // 通貨コード（全角半角）
        this.form.currencyCode = response.dataList[0].currencyCode
        // 通貨名（全角）
        this.form.currency = response.dataList[0].currency
        // 売却方法
        this.form.saleMethod = response.dataList[0].saleMethod
        // 売買区分（全角半角）
        this.form.tradeKbn = response.dataList[0].tradeKbn
        // 為替注文金額
        this.form.orderAmount = response.dataList[0].orderAmount
        // 約定日時
        this.form.tradeDateTime = response.dataList[0].tradeDateTime
        // 受渡日
        this.form.settlementDate = response.dataList[0].settlementDate
        // 為替レート（数値(小数)）
        this.form.fxRate = response.dataList[0].fxRate
        // 為替レート日時
        this.form.exchangeRateDateTime = response.dataList[0].exchangeRateDateTime
        // 受渡金額（円貨）（数値(小数)）
        this.form.yenDeliveryAmount = response.dataList[0].yenDeliveryAmount
        // 注文ワーニングしきい値（数値(整数)）
        this.form.warningThreshold = response.dataList[0].warningThreshold
        // 注文ワーニングしきい値超過メッセージ（全角半角）
        this.form.orderWarningexceedingThreshold = response.dataList[0].orderWarningexceedingThreshold
        // 適用スプレッド（数値(小数)）
        this.form.selectedCurrencyInfo = response.dataList[0].selectedCurrencyInfo
        // 上乗せ区分
        this.form.fxRateAdditionalType = response.dataList[0].fxRateAdditionalType
        // 上乗せ金額(金額)
        this.form.adjustAmount = response.dataList[0].adjustAmount
        // 上乗せ金額(パーセント)
        this.form.adjustPercent = response.dataList[0].adjustPercent
        // 数量の指定方法
        this.form.quantityDesignationMethod = response.dataList[0].quantityDesignationMethod
        // 小数部有効桁数
        this.form.decimalLength = response.dataList[0].decimalLength

        this.confirmationDialogVisible = true
      }
    },
    responseHandlerA008(error) {
      this.$_logWarn('responseErrorHandlerA008-Error', error)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // フォームをリセットする
    a006Action() {
      this.form.domesticVolume = ''
      this.form.foreignVolume = ''
      this.quantityDesignationMethodInputDisabled = false
      this.volumeInputDisabled = false
      this.form.saleMethod = '1'
      this.form.quantityDesignationMethod = '1'
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs['form'].clearValidate()
        })
      })
    },
    // 戻るボタン処理
    handleCloseModal() {
      if (this.confirmationDialogVisible || this.finishDialogVisible) {
        this.confirmationDialogVisible = false
        this.finishDialogVisible = false
      } else {
        this.$parent.orderInputVisible = false
      }
    },
    // 注文発注処理
    handleOrderFinish(response) {
      this.form.tradeKbn = response.dataList[0].tradeKbn
      this.form.orderDate = response.dataList[0].orderDate
      this.form.orderNumber = response.dataList[0].orderNumber
      this.form.quantity = response.dataList[0].quantity
      this.form.currencyCode = response.dataList[0].currencyCode
      this.form.currency = response.dataList[0].currency
      this.form.contractExchangeRate = response.dataList[0].contractExchangeRate
      this.form.contractExchangeRateDateTime = response.dataList[0].contractExchangeRateDateTime
      this.form.selectedCurrencyInfo = response.dataList[0].selectedCurrencyInfo
      this.form.fxRateAdditionalType = response.dataList[0].fxRateAdditionalType
      this.form.additionalPrice = response.dataList[0].additionalPrice
      this.form.deliveryAmount = response.dataList[0].deliveryAmount
      this.form.tradeTiming = response.dataList[0].tradeTiming
      this.form.settlementDate = response.dataList[0].settlementDate
      this.form.orderWarningexceedingThreshold = response.dataList[0].orderWarningexceedingThreshold
      this.form.warningThreshold = response.dataList[0].warningThreshold
      this.form.addLinkAttention = response.dataList[0].addLinkAttention
      this.form.decimalLength = response.dataList[0].decimalLength
      this.confirmationDialogVisible = false
    },
    // 国旗の画像を読み込む
    imgs(icon) {
      return require('@/assets/png/flags/32/' + icon + '.png')
    },
    // 概算受渡金額/概算外貨数量を算出する
    a005Action() {
      this.formRef = this.$refs.form
      let num = 0
      let amountOrquantityNumer = 0
      if (typeof this.form.computeExchangeRate !== 'undefined' && typeof this.form.denominator !== 'undefined') {
        if (this.form.quantityDesignationMethod === '1') {
          // 外貨
          num = BigNumber(this.form.foreignVolume).times(BigNumber(this.form.computeExchangeRate)).div(BigNumber(this.form.denominator))
          if (this.form.tradeKbn === '3') {
            // 概算受渡金額は、1円未満の金額を 注文時：切り上げ
            num = Math.ceil(Number(num))
          } else if (this.form.tradeKbn === '1') {
            // 概算受渡金額は、1円未満の金額を 注文時：切り捨て
            num = Math.floor(Number(num))
          }
          // 概算受渡金額
          this.form.approximateDeliveryAmount = num
        } else {
          // 円
          if (!isNaN(this.form.domesticVolume)) {
            num = BigNumber(this.form.domesticVolume).times(BigNumber(this.form.denominator)).div(BigNumber(this.form.computeExchangeRate))
          }
          // 概算外貨数量
          this.form.approximateForeignCount = Math.floor(Number(num))
          num = Math.floor(Number(num))
        }
      }
      amountOrquantityNumer = num
      this.form.amountOrquantity = amountOrquantityNumer
      return amountOrquantityNumer
    },
    // 数量の指定方法が変更されたときの処理
    a004Action() {
      if (this.form.quantityDesignationMethod === '2') {
        // ■外貨で指定→円で指定に切り替え時
        // 数量入力（外貨）の値を数量にセット、概算受渡金額を数量入力（円貨）にセット
        this.form.domesticVolume = this.form.approximateDeliveryAmount
      } else {
        // ■円で指定→外貨で指定に切り替え時
        // 数量入力（円）の値を概算受渡金額にセット、数量を数量入力（外貨）にセット
        this.form.foreignVolume = this.form.approximateForeignCount
      }
    },
    // 売却方法が変更されたときの処理
    a003Action() {
      if (this.form.saleMethod === '1') {
        this.form.foreignVolume = ''
        this.volumeInputDisabled = false
        this.quantityDesignationMethodInputDisabled = false
        this.form.foreignVolume = 0
      } else if (this.form.saleMethod === '2') {
        this.form.quantityDesignationMethod = '1'
        this.volumeInputDisabled = true
        this.quantityDesignationMethodInputDisabled = true
        this.form.foreignVolume = parseFloat(this.form.sellAbleQuantity).toFixed(2)
      }
      this.a005Action()
    },
    handleMoveToOrderList() {
      this.a006Action()
      this.finishDialogVisible = false
      this.$_startShowMenu('SUB0202_0501')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.error-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 14px;
  font-weight: 600;
}
.warning-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: black;
  font-size: 14px;
}
.title-container {
  display: flex;
  align-items: center;
  resize: none;
  border-left: 4px solid blue;
  height: 33px;
  line-height: 24px;
  padding-left: 1rem;
}
.title-content {
  color: #606266;
  font-size: 16px;
  font-weight: bold;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  height: 25px;
  line-height: 25px;
}
.form-container {
  display: flex;
  justify-content: space-between;
}
.form-main {
  width: 100%;
  margin-left: 2%;
}
.form-area__section {
  height: auto;
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
}
.form-radio__auto {
  width: auto;
}
.orderinput-button {
  margin-top: 1rem;
  margin-bottom: 1rem;
}
.order-button {
  margin-right: 0.5rem;
  margin-left: 1rem;
}
.rate-button {
  display: flex;
  justify-content: flex-end;
}
.back-button {
  display: flex;
  justify-content: flex-end;
  margin-right: 2rem;
}
.reset-button {
  margin-right: 2rem;
}
.custom-loading-text {
  color: white;
  font-size: 20px;
}
.__left {
  text-align: left;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.__border {
  border-bottom: 1px solid #bfcbd9;
}
:deep(.el-form-item__label) {
  padding-right: 3rem;
  justify-content: flex-start;
}
:deep(.el-form-item__error) {
  white-space: nowrap
}
.el-form-item__error_custom-margin :deep(.el-form-item__error) {
  margin-left: 1rem;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.ifa-input_base.with-control) {
  width: 200px;
}
.info-item__value li {
  list-style: none;
  display: flex;
}
.trade-type{
  margin-left: 0.6rem;
}
.radio_margin :deep(.el-radio) {
  margin-right: 88px;
}
</style>
