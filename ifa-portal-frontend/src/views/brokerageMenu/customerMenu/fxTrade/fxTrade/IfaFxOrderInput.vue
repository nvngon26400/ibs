<template>
  <ifa-requester
    id="IfaFxOrderInputUpdateA002"
    action-id="SUB0202_0502-02_1#A002"
    action-type="requestAction"
    :request-model="IfaFxOrderInputA002RequestModel"
    @response-handler="a002ActionHandler"
    @response-error-handler="a002ActionErrorHandler"
  ></ifa-requester>
  <ifa-requester
    id="IfaFxOrderInputA008"
    action-id="SUB0202_0502-02_1#A008"
    action-type="requestAction"
    :request-model="IfaFxOrderInputA008RequestModel"
    @response-handler="a008ActionHandler"
    @response-error-handler="a008ActionErrorHandler"
  ></ifa-requester>
  <!-- 為替取引 -->
  <div>
    <screen-title :text="formModel.title"></screen-title>
    <div class="caption_card">
      <!-- 注文入力 -->
      <div>
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
                    <span class="info-item__value __right">(更新日時：{{ $_getFormattedDateTimeValue($store.getters.requestedTime, 'datetime12') }})</span>
                    <ifa-button
                      id="btnUpdate"
                      text="更新"
                      color="primary"
                      icon="RefreshRight"
                      small
                      action-type="originalAction"
                      @app-action-handler="updateA002"
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
                        {{ $_addComma(parseFloat(formModel.referenceRate).toFixed(setDigit(formModel.decimalLength))) }}円 /
                        {{ formModel.denominator === '1' ? '' : $_addComma(formModel.denominator) }}
                        {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
                      </span>
                    </el-col>
                  </el-row>
                  <div>
                    <el-row>
                      <el-col
                        :span="3"
                        class="info-item__header __left __border"
                      >
                        <span>
                          {{ formModel.tradeKbn === '3' ? '買付可能数量' : formModel.tradeKbn === '1' ? '売却可能数量' : '' }}
                        </span>
                      </el-col>
                      <el-col
                        :span="3"
                        class="info-item__header __right __border"
                      >
                        <span v-if="formModel.accountType === '2'">ジュニアNISA口座</span>
                        <span v-else>総合口座</span>
                      </el-col>
                      <el-col
                        :span="5"
                        class="info-item__header __right __border"
                      >
                        <span>
                          {{ $_out(ifaFormatUtils.withCommaZeroPadding(formModel.maxOrderableQuantity, 2)) }}
                        </span>
                        <span>&nbsp;{{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}</span>
                      </el-col>
                      <div
                        v-if="formModel.tradeKbn === '3'"
                        style="display: inline-block;"
                        class="info-item__value __left __border"
                      >
                        <span>({{ $_addComma(formModel.approximatePurchaseAmount) }} 円分)</span>
                      </div>
                      <el-col
                        :span="2"
                        style="margin-top: -0.2rem; margin-left: 0.5rem;"
                      >
                        <el-button
                          v-if="formModel.accountType === '2'"
                          icon="Refresh"
                          type="text"
                          style="border: none; text-decoration: underline;"
                          @click="handleAccountChanged"
                        >総合口座に切り替え</el-button>
                        <el-button
                          v-if="formModel.accountType === '1' && formModel.jrNisaAccountStatus === 'RESTRICTED_RELEASE_BEFORE'"
                          icon="Refresh"
                          type="text"
                          style="border: none; text-decoration: underline;"
                          @click="handleAccountChanged"
                        >ジュニアNISA口座に切り替え</el-button>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col
                        :span="24"
                        class="info-item__value __left"
                        style="height: auto;"
                      >
                        <li style="list-style: none;">
                          ※参考レートは、為替スプレッドを加味したレートです。詳しくは
                          <ifa-link
                            style="margin-top: -0.2rem;"
                            :disp-name="'こちら'"
                            :url-id="23"
                          ></ifa-link>
                        </li>
                        <li
                          v-if="formModel.accountType === '2'"
                          style="list-style: none;"
                        >
                          ※ジュニアNISAの取引は､払出制限の対象となります｡詳細は
                          <ifa-link
                            :disp-name="'こちら'"
                            :url-id="24"
                          ></ifa-link>
                        </li>
                      </el-col>
                    </el-row>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>

          <el-card
            v-if="formModel.tradeKbn"
            class="box-card"
            :style="{ 'background-color': bgColor }"
          >
            <!-- 注文入力フォーム -->
            <div class="form-container">
              <el-form
                ref="form"
                :model="formModel"
                label-width="160px"
                class="form-main"
                :style="{ 'background-color': bgColor }"
              >
                <!-- 注文方法 -->
                <div class="form-area__section">
                  <el-form-item
                    label="取引種別"
                    style="font-weight: bold;"
                    class="trade-type"
                  >
                    <div style="display: flex; align-items: flex-start;">
                      <div
                        style="display: inline-block; margin-right: 20px; min-width: 60px;"
                      >
                        <span>
                          <span :class="isFontColor(formModel.tradeKbn)">
                            {{ $_getCodeValue('SELL_BUY_TYPE', 1, formModel.tradeKbn) }}注文
                          </span>
                        </span>
                      </div>
                      <div style="display: inline-block; width: 100%;">
                        <span
                          v-if="formModel.tradeKbn === '3'"
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
                          >{{ formModel.currency }}</span>
                          <img :src="imgs(formModel.currencyCode.substring(0,2).toLowerCase())">
                        </span>
                        <span
                          v-else
                          style="display: flex; line-height: 32px;"
                        >
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >{{ formModel.currency }}</span>
                          <img :src="imgs(formModel.currencyCode.substring(0,2).toLowerCase())">
                          <span
                            class="title-content"
                            style="padding: 0 0.5rem;"
                          >→</span>
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
                          text="リセット"
                          color="secondary"
                          action-type="originalAction"
                          @app-action-handler="resetForm()"
                        ></ifa-button>
                        <!-- 戻るボタン -->
                        <ifa-button
                          id="btnBack"
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
                  v-if="formModel.tradeKbn === '1'"
                  class="form-area__section"
                >
                  <el-form-item
                    label="売却方法"
                    prop="saleMethod"
                    inline-message="true"
                    style="font-weight: bold;"
                  >
                    <ifa-input-radio
                      v-model="formModel.saleMethod"
                      :code-list-id="'SELL_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :required="true"
                      @change="a003Action($event)"
                    ></ifa-input-radio>
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
                    <ifa-input-radio
                      v-model="formModel.quantityDesignationMethod"
                      :code-list-id="'FX_QUANTITY_DESIGNATION_METHOD'"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :disabled="formModel.saleMethod === '2'"
                      :required="true"
                      @change="a004Action()"
                    ></ifa-input-radio>
                  </el-form-item>
                </div>

                <!-- フォームアイテム: 数量 -->
                <div class="form-area__section">
                  <el-row>
                    <el-col style="display: flex; align-items: flex-start;">
                      <div style="display: inline-block; min-width: 550px;">
                        <ifa-input-amount
                          v-if="formModel.quantityDesignationMethod === '1'"
                          v-model="formModel.foreignVolume"
                          prop="foreignVolume"
                          :label="formModel.tradeKbn === '3' ? '買付数量' : '売却数量'"
                          :unit="formModel.currencyCode"
                          :support="true"
                          :step="formModel.foreignSpinButtonRange"
                          :step-for-validation="formModel.tradeLimitMin"
                          :initial-step="Math.max(formModel.closableQuantity, formModel.foreignSpinButtonRange)"
                          :min="(formModel.tradeKbn === '1' && formModel.saleMethod === '2') ? 0 : formModel.closableQuantity"
                          :max="formModel.foreignCurrencyMax"
                          :disabled="formModel.saleMethod === '2'"
                          :required="true"
                          :domain="IfaForeign224DomainModel"
                          :digit="2"
                          @change="a005Action()"
                          @keydown.enter.prevent
                        ></ifa-input-amount>

                        <ifa-input-amount
                          v-if="formModel.quantityDesignationMethod === '2'"
                          v-model="formModel.domesticVolume"
                          prop="domesticVolume"
                          :label="formModel.tradeKbn === '3' ? '買付数量' : '売却数量'"
                          :unit="'円'"
                          :support="true"
                          :step="100"
                          :step-for-validation="1"
                          :initial-step="Math.max(formModel.yenCurrencyMin, 100)"
                          :min="formModel.yenCurrencyMin"
                          :max="formModel.yenCurrencyMax"
                          :disabled="formModel.saleMethod === '2'"
                          :required="true"
                          :domain="IfaYen220DomainModel"
                          :digit="0"
                          @change="a005Action()"
                          @keydown.enter.prevent
                        ></ifa-input-amount>
                      </div>
                      <div
                        style="font-size: 12px; height: 45px; display: flex; align-items: center;"
                      >
                        <span v-if="formModel.saleMethod === '2'">
                          <span v-if="parseFloat(formModel.tradeLimitMax) > 0">
                            1回の取引における売却数量上限は
                            {{ $_out(ifaFormatUtils.withCommaZeroPadding(formModel.tradeLimitMax, 2)) }}
                            {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
                            になります。
                          </span>
                        </span>
                        <span v-else>取引単位：
                          {{ $_out(ifaFormatUtils.withCommaZeroPadding(formModel.closableQuantity, 2)) }}
                          {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}以上､
                          <span v-if="parseFloat(formModel.tradeLimitMax) > 0">
                            {{ $_out(ifaFormatUtils.withCommaZeroPadding(formModel.tradeLimitMax, 2)) }}
                            {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}以下、
                          </span>
                          {{ $_addComma(parseFloat(formModel.tradeLimitMin)) }}
                          {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}単位
                        </span>
                      </div>
                    </el-col>
                  </el-row>
                </div>

                <!-- 概算表示エリア -->
                <div>
                  <el-row style="padding-top: 1rem; padding-bottom: 1rem;">
                    <el-col
                      :span="7"
                      class="info-item__header __left"
                      style="padding-left: 178px;"
                    >
                      {{ formModel.saleMethod === '2' || formModel.quantityDesignationMethod === '1' ? '概算受渡金額' : '数量' }}
                    </el-col>
                    <!-- 概算受渡金額 -->
                    <el-col
                      v-if="formModel.saleMethod === '2' || formModel.quantityDesignationMethod === '1'"
                      :span="5"
                      class="info-item__value __left"
                    >
                      {{ $_withCommaNoneZeroPadding(formModel.approximateDeliveryAmount) }}円
                    </el-col>
                    <!-- 概算外貨数量 -->
                    <el-col
                      v-else
                      :span="5"
                      class="info-item__value __left"
                    >
                      {{ $_out(ifaFormatUtils.withCommaZeroPadding(formModel.foreignApproximateDeliveryAmount, 2)) }}
                      {{ $_getCodeValue('CURRENCY_CODE', 3, formModel.currencyCode) }}
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
                      <li
                        v-if="formModel.tradeKbn === '3' && formModel.quantityDesignationMethod === '1'"
                      >
                        <span>※概算受渡金額は、概算用レートで計算（円未満の金額は切り上げ）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り捨て）いたします。</span>
                      </li>
                      <li
                        v-if="formModel.tradeKbn === '3' && formModel.quantityDesignationMethod === '2'"
                      >
                        ※円で指定いただいた金額以下で買付可能な外貨を「数量」として表示しています。<br>
                        「数量」は概算用レートで計算（小数点以下切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り捨て）いたします。為替相場の変動により円で指定した金額を上回る場合がございます。<br>
                        ※当該数量に基づき算出した概算受渡金額（円貨）を次の確認画面で表示いたします。
                      </li>
                      <li
                        v-if="formModel.tradeKbn === '1' && (formModel.quantityDesignationMethod === '1' || formModel.saleMethod === '2')"
                      >
                        <span>※概算受渡金額は、概算用レートで計算（円未満の金額は切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り上げ）いたします。</span>
                      </li>
                      <li
                        v-if="formModel.tradeKbn === '1' && formModel.quantityDesignationMethod === '2'"
                      >
                        ※円で指定いただいた金額以下で売却可能な外貨を「数量」として表示しています。<br>
                        「数量」は概算用レートで計算（小数点以下切り捨て）しておりますが、実際の受渡金額は約定時のレートで計算（円未満の金額は切り上げ）いたします。為替相場の変動により円で指定した金額を下回る場合がございます。<br>
                        ※当該数量に基づき算出した概算受渡金額（円貨）を次の確認画面で表示いたします。
                      </li>
                    </div>
                  </el-row>
                </div>

                <!-- 注文確認ボタン -->
                <!-- Note: タブ移動での順番の関係でリセットボタンよりも前に配置する必要あり -->
                <el-row>
                  <ifa-button
                    id="btnConfirm"
                    class="orderinput-button"
                    text="注文確認"
                    action-type="originalAction"
                    @app-action-handler="a008Action"
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
                      <span><strong>以下の事項にご注意ください。</strong></span>
                    </el-row>
                    <table>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>
                            外国の休日等の理由により、為替取引の約定日または受渡日は変更となる場合がございます。
                          </span>
                        </td>
                      </tr>
                      <tr>
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
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>
                            前受金制のため、日本円売り外貨買いの場合は、ご注文をいただいてから約定となるまでの間、日本円の出金可能額（出金可能額は、買付余力とは異なる場合があります。）から概算受渡金額を拘束させていただきます。
                            概算受渡金額の算出は実勢レートに対し、注文時上乗せレート、為替スプレッドを加味しております。
                            実際の受渡金額は、約定時のレートより計算されます。
                          </span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>
                            外貨売り日本円買いの場合は、外貨の出金可能額をご注文の外貨金額分拘束させていただきます。
                          </span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>
                            為替相場が大きく動いた場合、実際の約定金額が概算受渡金額を上回り、かつ、日本円の出金可能額が不足した場合、為替取引のご注文は約定となりませんのでご注意ください。
                            なお、実際の約定金額が概算受渡金額を上回った場合でも、実際の約定金額分の日本円の出金可能額がある場合には、為替取引のご注文は約定となります。
                          </span>
                        </td>
                      </tr>
                      <tr>
                        <td style="width: 1.5rem; vertical-align: top;">
                          <el-icon><Warning></Warning></el-icon>
                        </td>
                        <td>
                          <span>
                            リアルタイム為替では、為替市場の流動性が急激に低下した場合や、経済指標発表時などの相場急変時において、当社所定のサービス提供時間内であっても当社の判断で取引を停止する可能性がありますのであらかじめご了承ください。
                          </span>
                        </td>
                      </tr>
                      <tr>
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
                      </tr>
                    </table>
                  </el-card>
                </el-row>
              </el-form>
            </div>
          </el-card>
        </el-row>

      </div>

      <!-- 為替注文確認 -->
      <ifa-fx-order-confirm
        :is-visible="confirmationDialogVisible"
        :form-model="confirmFormModel"
        :customer-info="customerInfo()"
        @close-modal="handleCloseConfirmModal"
        @order-finish="handleOrderFinish"
      ></ifa-fx-order-confirm>

      <!-- 為替注文完了 -->
      <ifa-fx-order-complete
        :is-visible="finishDialogVisible"
        :form-model="completeFormModel"
        :customer-info="customerInfo()"
        @close-modal="
          handleCloseModal();
          resetForm();
        "
        @move-to-order-list="handleMoveToOrderList"
      ></ifa-fx-order-complete>
    </div>
  </div>
</template>

<script>
import IfaFxOrderConfirm from './IfaFxOrderConfirm'
import IfaFxOrderComplete from './IfaFxOrderComplete'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaForeign224DomainModel from '@/resource/domain/IfaForeign224DomainModel.json'
import IfaYen220DomainModel from '@/resource/domain/IfaYen220DomainModel.json'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { IfaFxOrderInputFormModel } from './js/IfaFxOrderInputFormModel'
import { IfaFxOrderConfirmFormModel } from './js/IfaFxOrderConfirmFormModel'
import { IfaFxOrderCompleteFormModel } from './js/IfaFxOrderCompleteFormModel'
import { IfaFxOrderInputA002RequestModel } from './js/IfaFxOrderInputA002RequestModel'
import { IfaFxOrderInputA008RequestModel } from './js/IfaFxOrderInputA008RequestModel'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    IfaFxOrderConfirm,
    IfaFxOrderComplete,
    screenTitle
  },
  props: {
    formData: {
      type: Object,
      required: true
    }
  },
  emits: ['handle-close-modal'],
  data() {
    return {
      IfaForeign224DomainModel: IfaForeign224DomainModel,
      IfaYen220DomainModel: IfaYen220DomainModel,
      ifaFormatUtils: ifaFormatUtils,
      confirmationDialogVisible: false,
      finishDialogVisible: false,
      formModel: new IfaFxOrderInputFormModel(),
      confirmFormModel: new IfaFxOrderConfirmFormModel(),
      completeFormModel: new IfaFxOrderCompleteFormModel()
    }
  },
  computed: {
    // 買付と売却の背景色を返す処理
    bgColor() {
      if (this.formModel.tradeKbn === '3') {
        return '#fef0f0'
      } else if (this.formModel.tradeKbn === '1') {
        return '#ecf5ff'
      }
      return '#fdf6ec'
    },
    isFontColor: function() {
      return function(tradeKbn) {
        if (tradeKbn === '1') {
          return 'font-color__minus bold'
        } else if (tradeKbn === '3') {
          return 'font-color__plus bold'
        } else {
          return ''
        }
      }
    },
    IfaFxOrderInputA002RequestModel() { return new IfaFxOrderInputA002RequestModel(this.formModel) },
    IfaFxOrderInputA008RequestModel() { return new IfaFxOrderInputA008RequestModel(this.formModel) }
  },
  mounted() {
    this.formModel = Object.assign(this.formModel, this.formData)
  },
  methods: {
    updateA002() {
      this.$nextTick(() => {
        document.getElementById('IfaFxOrderInputUpdateA002').click()
      })
    },
    a002ActionHandler(data) {
      this.formModel = Object.assign(this.formModel, data.dataList[0])
      this.a005Action()
    },
    a002ActionErrorHandler(error) {
      if (error.errorLevel !== 0) {
        notifyWrapper({
          title: this.formModel.title,
          message: error.message,
          type: 'warning'
        })
      }
    },
    a003Action(value) {
      if (value === '2') {
        // 売却方法＝全て売却が選択された場合
        this.formModel.quantityDesignationMethod = '1'
        this.formModel.foreignVolume = this.formModel.maxOrderableQuantity
      } else {
        // 売却方法＝指定した数量を売却が選択された場合
        this.formModel.foreignVolume = this.formModel.closableQuantity
      }
      this.a005Action()
    },
    a004Action() {
      // 数量指定エリアと概算表示エリアの値を引き継ぐ
      if (this.formModel.quantityDesignationMethod === '1') {
        // 円で指定→外貨で指定に切り替え時
        this.formModel.foreignVolume = this.formModel.foreignApproximateDeliveryAmount
      } else {
        // 外貨で指定→円で指定に切り替え時
        this.formModel.domesticVolume = this.formModel.approximateDeliveryAmount
      }
      this.a005Action()
    },
    a005Action() {
      if (this.formModel.saleMethod === '2' || this.formModel.quantityDesignationMethod === '1') {
        // 画面.数量入力（外貨） × 画面.概算用レート ÷ 画面.デノミ
        // （A001.売買区＝買の場合、分計算結果を小数点以下切り上げ）
        // （A001.売買区＝売の場合、分計算結果を小数点以下切り捨て）
        if (this.formModel.tradeKbn === '3') {
          this.formModel.approximateDeliveryAmount = parseFloat(this.formModel.foreignVolume * this.formModel.approximationRate / this.formModel.denominator).toFixed(0)
        } else {
          this.formModel.approximateDeliveryAmount = Math.floor(parseFloat(this.formModel.foreignVolume * this.formModel.approximationRate / this.formModel.denominator))
        }
      } else {
        // 画面.数量入力（円） × 画面.デノミ ÷ 画面.概算用レート（計算結果を小数点以下切り捨て）
        this.formModel.foreignApproximateDeliveryAmount = Math.floor(parseFloat(this.formModel.domesticVolume * this.formModel.denominator / this.formModel.approximationRate))
      }
    },
    a008Action() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$nextTick(() => {
            document.getElementById('IfaFxOrderInputA008').click()
          })
        } else {
          return false
        }
      })
    },
    a008ActionHandler(data) {
      this.confirmFormModel = Object.assign(this.confirmFormModel, data.dataList[0])
      this.confirmFormModel.decimalLength = this.formModel.decimalLength
      this.confirmationDialogVisible = true
    },
    a008ActionErrorHandler(error) {
      this.$_logWarn('a008ActionErrorHandler-Error', error)
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // タブが表示された
    resetForm() {
      this.formModel.saleMethod = '1'
      this.formModel.quantityDesignationMethod = '1'
      this.formModel.foreignVolume = ''
      this.formModel.domesticVolume = ''
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs['form'].clearValidate()
        })
      })
      this.a005Action()
    },
    // 戻るボタン処理
    handleCloseConfirmModal() {
      this.confirmationDialogVisible = false
    },
    handleCloseModal() {
      this.confirmationDialogVisible = false
      this.finishDialogVisible = false
      this.$emit('handle-close-modal')
    },
    // 注文発注処理
    handleOrderFinish(param) {
      this.completeFormModel = Object.assign(this.completeFormModel, param)
      this.confirmationDialogVisible = false
      this.finishDialogVisible = true
    },
    // 注文照会画面に遷移する
    handleMoveToOrderList() {
      this.resetForm('form')
      this.finishDialogVisible = false
      this.$_startShowMenu('SUB0202_0501') // 為替取引注文照会
    },
    // 国旗の画像を読み込む
    imgs(icon) {
      return require('@/assets/png/flags/32/' + icon + '.png')
    },
    // 口座が変更されたときの処理
    handleAccountChanged() {
      this.formModel.accountType = this.formModel.accountType === '2' ? '1' : '2'
      this.$nextTick(() => {
        document.getElementById('IfaFxOrderInputUpdateA002').click()
      })
    },
    setDigit(value) {
      return Number(value) > 2 ? value : 2
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
.trade-type{
  margin-left: 0.6rem;
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
.radio_margin :deep(.el-radio) {
  margin-right: 88px;
}
</style>
