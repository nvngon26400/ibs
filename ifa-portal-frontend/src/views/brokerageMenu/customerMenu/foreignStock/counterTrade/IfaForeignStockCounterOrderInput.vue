<template>
  <div v-if="isVisible">
    <ifa-requester
      id="ifaForeignStockCounterOrderInputGetNewMainSiteA019"
      action-id="SUB0202_0302-02_1#A019"
      action-type="requestAction"
      :request-model="ifaLinkRequestModel"
      @response-handler="responseHandlerGetNewMainSiteA019($event)"
    ></ifa-requester>
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <div style="margin-bottom:10px;">
        <el-card class="devGrey">
          <el-row>
            <el-col
              :span="21"
              class="boldLabel"
              style="margin-left: 0.5rem; font-size: 18px;"
            >
              <div>
                <span>銘柄：[{{ $_out(form.brandCode) }}] </span>
                <span>{{ $_out(form.brandName) }}</span>
              </div>
            </el-col>
            <el-col
              :span="2"
              style="margin-left: 2.5rem;"
            >
              <ifa-button
                id="btnUpdate"
                style="float: right;"
                color="primary"
                text="更新"
                small
                icon="RefreshRight"
                action-type="requestAction"
                action-id="SUB0202_0302-02_1#A002"
                :request-model="IfaForeignStockCounterOrderInputA002RequestModel"
                @response-handler="updateA002($event)"
                @response-error-handler="updateErrorA002()"
              ></ifa-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="5"
              class="info-item"
            >
              <span>上限数量：</span>
              <span><span>{{ $_out($_withCommaInteger(form.upperLimitQuantity)) }}</span><span v-if="form.upperLimitQuantity"> 株</span></span>
            </el-col>
            <el-col
              :span="5"
              class="info-item"
            >
              <span>取引価格：</span>
              <span><span>{{ $_out($_withCommaNoneZeroPadding(form.tradePrice)) }}</span><span v-if="form.tradePrice"> USD</span></span>
            </el-col>
            <el-col
              :span="6"
              style="margin: 0.5rem 1rem 0 auto; text-align: right;"
            >
              <div
                class="external-link-wrapper"
                :class="{
                  'external-link-disabled': isDisabled,
                  'external-link-enabled': !isDisabled
                }"
              >
                <el-link
                  :underline="false"
                  type="primary"
                  class="external-link"
                  @click="ifaForeignStockCounterOrderInputGetNewMainSiteA019(47,'1','POST',form.brandCode)"
                > {{ "チャート" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignStockCounterOrderInputGetNewMainSiteA019(47,'1','POST',form.brandCode)"
                >
                  <img
                    src="@/assets/icons/external-link.svg"
                  >
                </el-icon>
              </div>
              <div
                class="external-link-wrapper"
                :class="{
                  'external-link-disabled': isDisabled,
                  'external-link-enabled': !isDisabled
                }"
              >
                <el-link
                  :underline="false"
                  type="primary"
                  class="external-link"
                  @click="ifaForeignStockCounterOrderInputGetNewMainSiteA019(48,'1','POST',form.brandCode)"
                > {{ "株価参照" }}
                </el-link>
                <el-icon
                  class="external-link-icon"
                  @click="ifaForeignStockCounterOrderInputGetNewMainSiteA019(48,'1','POST',form.brandCode)"
                >
                  <img
                    src="@/assets/icons/external-link.svg"
                  >
                </el-icon>
              </div>

            </el-col>
          </el-row>
        </el-card>
      </div>
      <el-row v-if="form.tradeClassification === '3'">
        <el-card
          class="devGrey"
          style="width: 100%;"
        >
          <el-row
            :gutter="20"
            style="font-weight: bold; color: #606266;"
          >
            <el-col
              :span="5"
              class="info-item_usd"
              style="padding-right: 0 !important;"
            >
              <span class="info-item__header">買付余力（外貨）</span>
              <span>{{ storeBuyingPower }}</span>
              <span style="margin-left: auto;"> USD</span>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
      <div>
        <div>
          <el-row style="margin-top: 0.5rem;">
            <el-col id="usageMethod">
              <div
                class="how-to-use"
              >
                <ifa-link
                  v-if="form.orderInputUsageUrl"
                  id="orderInputUsageUrl"
                  :param-url="form.orderInputUsageUrl"
                  link-icon-image="externalLink"
                  disp-name="注文入力の使い方"
                >
                </ifa-link>
              </div>
            </el-col>
          </el-row>

          <div id="confirmCheck">
            <el-card
              class="box-card"
              :style="{'background-color': bgColor}"
            >
              <el-form
                ref="form"
                :model="form"
                :inline="true"
                :rules="rules"
                label-position="left"
                label-width="180px"
              >
                <div id="normalPart">
                  <el-row id="commonTable">
                    <el-row class="form-area__section">
                      <el-col :span="12">
                        <el-form-item
                          label="取引種別"
                          style="margin-top: 1rem;"
                        >
                          <ifa-text
                            v-if="form.tradeCd === '11' || form.tradeCd === '12'"
                            :class="form.tradeClassification === '3' ? 'font-color__plus bold' : 'font-color__minus bold'"
                            code-list-id="FOREIGN_STOCK_TRADE_CLASS"
                            :disp-pattern="1"
                            :code-key="form.tradeCd"
                          ></ifa-text>
                          <span v-else>-</span>
                        </el-form-item>
                      </el-col>
                      <!-- リセットボタン -->
                      <el-col
                        :span="5"
                        class="right-btn"
                        style="margin-right: 0.9rem;"
                      >
                        <ifa-button
                          id="btnReset"
                          text="リセット"
                          color="secondary"
                          action-type="originalAction"
                          @app-action-handler="resetA009"
                        ></ifa-button>
                        <ifa-button
                          id="btnBack"
                          text="戻る"
                          color="secondary"
                          action-type="originalAction"
                          @app-action-handler="backA010"
                        ></ifa-button>
                      </el-col>
                    </el-row>
                    <el-row
                      v-if="form.tradeClassification === '1'"
                      class="form-area__section"
                    >
                      <el-form-item label="注文可能数量">
                        <span style="width: 150px; text-align: right;">
                          {{ maxOrderableQuantity }}</span><label style="padding-left: 4px;">株</label>
                      </el-form-item>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-quantity
                        id="quantity"
                        v-model="form.quantity"
                        label="数量"
                        prop="quantity"
                        unit="株"
                        :step="1"
                        :initial-step="1"
                        :min="1"
                        :max="Math.max(form.forCheckUpperLimitOrderAmount, maxOrderableQuantity)"
                        support
                        required
                        :domain="IfaStocks6DomainModel"
                        style="width: 200px;"
                        @change="quantityFocusOffA007"
                      ></ifa-input-quantity>
                    </el-row>
                    <el-row class="form-area__section">
                      <el-form-item label="約定金額">
                        <span style="width: 150px; text-align: right;">{{ $_withCommaNoneZeroPadding(form.contractAmount) }}</span><label style="padding-left: 4px;">USD</label>
                      </el-form-item>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-radio
                        id="depositType"
                        v-model="form.depositType"
                        prop="depositType"
                        code-list-id="FOREIGN_DEPOSIT_TYPE"
                        label="預り区分"
                        required
                        :select-pattern="setRadioDepositTypePattern"
                        :disp-pattern="1"
                        style="margin-left: -0.5px;"
                        :disabled-items="['4']"
                      ></ifa-input-radio>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-radio
                        id="settlementType"
                        v-model="form.settlementType"
                        prop="settlementType"
                        code-list-id="SETTLEMENT_TYPE"
                        label="決済方法"
                        required
                        :select-pattern="1"
                        :disp-pattern="1"
                        style="margin-left: -0.5px;"
                        :disabled-items="['1']"
                      ></ifa-input-radio>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-select
                        id="kanyuKbn"
                        v-model="form.kanyuKbn"
                        prop="kanyuKbn"
                        class="form-area__select"
                        code-list-id="FOREIGN_STOCK_INVITATION_TYPE"
                        label="勧誘区分"
                        required
                        style="width: 200px;"
                        :select-pattern="1"
                        :disp-pattern="1"
                      ></ifa-input-select>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-select
                        id="orderMethod"
                        v-model="form.orderMethod"
                        prop="orderMethod"
                        class="form-area__select"
                        code-list-id="FOREIGN_STOCK_ORDER_METHOD_TYPE"
                        label="受注方法"
                        required
                        style="width: 200px;"
                        :select-pattern="1"
                        :disp-pattern="1"
                      ></ifa-input-select>
                    </el-row>
                    <el-row class="form-area__section">
                      <ifa-input-check
                        id="powerConfirm"
                        ref="powerConfirm"
                        v-model="form.powerConfirm"
                        prop="powerConfirm"
                        class="checkbox-bkcolor"
                        code-list-id="POWER_CONFIRM"
                        label="余力"
                        required
                        :select-pattern="1"
                        :disp-pattern="1"
                      ></ifa-input-check>
                    </el-row>
                  </el-row>
                </div>
                <div id="warningPart">
                  <el-row
                    id="buyTable"
                  >
                    <el-row
                      v-if="form.tradeClassification === '3'"
                      class="form-area__section"
                    >
                      <ifa-input-radio
                        id="importantMatter"
                        v-model="form.importantMatter"
                        prop="importantMatter"
                        code-list-id="IMPORTANT_MATTERS_EXPLAIN"
                        label="重要事項の説明"
                        required
                        style="margin-left: -0.5px;"
                        :select-pattern="1"
                        :disp-pattern="1"
                      ></ifa-input-radio>
                    </el-row>
                    <el-row
                      v-if="form.tradeClassification === '3' && form.foreignSecurityInfoDate"
                      class="form-area__section"
                    >
                      <el-row
                        class="form-area__section_none-border"
                        style="display: flex;"
                      >
                        <div>
                          <el-form-item
                            label="外国証券情報"
                            style="width:220px;"
                          >
                          </el-form-item>
                        </div>
                        <div
                          class="label-left-area_hanban el-form-item__error_custom"
                          style="margin-left: -203px;"
                        >
                          <ifa-date-picker
                            id="foreignStockYmd"
                            v-model="form.foreignStockYmd"
                            prop="foreignStockYmd"
                            label="版番"
                            class="date_class"
                            label-class="date_class_hanban"
                            size="small"
                            required
                          ></ifa-date-picker>
                        </div>
                        <div
                          class="label-left-area el-form-item__error_custom"
                          style="margin-left: -150px;"
                        >
                          <ifa-date-picker
                            id="documentDeliveryDate"
                            v-model="form.documentDeliveryDate"
                            prop="documentDeliveryDate"
                            label="交付日"
                            class="date_class"
                            size="small"
                            required
                          ></ifa-date-picker>
                        </div>
                      </el-row>
                      <el-row class="label-left-area">
                        <el-col :span="1">
                          <el-form-item
                            label=""
                            style="width:220px;"
                          >
                          </el-form-item>
                        </el-col>
                        <el-col
                          :span="23"
                          class="label-left-area"
                          style=" margin-left: 76px;"
                        >
                          <ifa-input-select
                            id="issuedMethod"
                            v-model="form.issuedMethod"
                            prop="issuedMethod"
                            class="form-area__select"
                            code-list-id="FOREIGN_SECURITY_INFO_ISSUE_METHOD"
                            label="交付方法"
                            required
                            style="width: 200px;"
                            :select-pattern="1"
                            :disp-pattern="1"
                          ></ifa-input-select>
                        </el-col>
                      </el-row>
                    </el-row>
                    <el-row
                      v-if="form.tradeClassification === '3' && form.etfFlag === '1'"
                      id="hideETF"
                      class="form-area__section"
                    >
                      <ifa-input-radio
                        id="switchingSolicitationEtf"
                        v-model="form.switchingSolicitationEtf"
                        prop="switchingSolicitationEtf"
                        code-list-id="ETF_SOLICITING_TRANSFERS"
                        label="乗換え勧誘(ETF)"
                        required
                        style="margin-left: -0.5px;"
                        :select-pattern="1"
                        :disp-pattern="1"
                      ></ifa-input-radio>
                    </el-row>
                    <el-row
                      v-if="form.tradeClassification === '3' && engPubBrandAreaIsVisible"
                      id="hideEngPubBrand"
                      class="form-area__section"
                      style="display: flex;"
                    >
                      <div>
                        <ifa-input-check
                          id="engPubBrand"
                          ref="engPubBrand"
                          v-model="form.engPubBrand"
                          prop="engPubBrand"
                          code-list-id="ISSUES_DISCLOSED_IN_ENGLISH_BRAND"
                          label="英文開示銘柄"
                          label-class="label_class_engPubBrand"
                          required
                          :select-pattern="1"
                          :disp-pattern="1"
                        ></ifa-input-check>
                      </div>
                      <div
                        style="line-height: 32px;"
                      >
                        <span
                          class="faq-link"
                          @click="englishDisclosureBrandAboutLinkA011"
                        >英文開示銘柄について</span>
                      </div>
                      <div
                        class="label-left-area"
                        style="margin-left: -93px;"
                      >
                        <ifa-date-picker
                          id="engPubYmd"
                          v-model="form.engPubYmd"
                          prop="engPubYmd"
                          size="small"
                          label="説明日"
                          class="date_class"
                          required
                          :disabled="form.engPubBrand !== '0'"
                        ></ifa-date-picker>
                      </div>
                    </el-row>
                  </el-row>
                </div>
                <div id="summaryAnyPart">
                  <el-row
                    id="hideSummaryAny"
                    class="form-area__section"
                  >
                    <ifa-input-text
                      id="summaryAny"
                      v-model="form.summaryAny"
                      prop="summaryAny"
                      size="small"
                      class="ifa-input__text-long"
                      style="font-size: 14px;"
                      label="摘要(任意)"
                      type="textarea"
                      original-screen-id="SUB0202_0302-02_1"
                      :domain="IfaTextFullHalf100DomainModel"
                    ></ifa-input-text>
                  </el-row>
                </div>
              </el-form>
              <div
                style="margin-top:2rem;"
              >
                <ifa-button
                  id="btnConfirm"
                  name="btnConfirm"
                  text="注文確認"
                  action-type="requestAction"
                  action-id="SUB0202_0302-02_1#A008"
                  :disabled="updateErrorFlag"
                  :form="formRef"
                  :request-model="IfaForeignStockCounterOrderInputA008RequestModel"
                  @response-handler="orderConfirmA008($event)"
                ></ifa-button>
              </div>
            </el-card>
          </div>
        </div>
      </div>

      <ifa-foreign-stock-counter-order-confirm
        ref="IfaForeignStockCounterOrderConfirm"
        :is-visible="dialogConfirmVisible"
        :form-data="confirmData"
        @close-modal="dialogConfirmVisible = false"
        @order-finish="handleOrderFinish"
      ></ifa-foreign-stock-counter-order-confirm>

      <!--完了画面のプロパティ修正-->
      <ifa-foreign-stock-counter-order-complete
        ref="IfaForeignStockCounterOrderComplete"
        :is-visible="dialogCompleteVisible"
        :form-data="completeData"
        @move-to-order-list="handleMoveToOrderList"
      ></ifa-foreign-stock-counter-order-complete>
    </div>
  </div>
</template>
<script>
import IfaForeignStockCounterOrderConfirm from './IfaForeignStockCounterOrderConfirm'
import IfaForeignStockCounterOrderComplete from './IfaForeignStockCounterOrderComplete'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaStocks6DomainModel from '@/resource/domain/IfaStocks6DomainModel.json'
import IfaTextFullHalf100DomainModel from '@/resource/domain/IfaTextFullHalf100DomainModel.json'
import { IfaForeignStockCounterOrderInputFormModel } from './js/IfaForeignStockCounterOrderInputFormModel.js'
import { IfaForeignStockCounterOrderInputA002RequestModel } from './js/IfaForeignStockCounterOrderInputA002RequestModel.js'
import { IfaForeignStockCounterOrderInputA008RequestModel } from './js/IfaForeignStockCounterOrderInputA008RequestModel.js'
import { BigNumber } from 'bignumber.js'
import { getMessage } from '@/utils/errorHandler'
import { IfaLinkRequestModel } from './js/IfaLinkRequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaForeignStockCounterOrderConfirm,
    IfaForeignStockCounterOrderComplete,
    screenTitle
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
  },
  emits: ['close-modal', 'back-click', 'show-tab-by-name'],
  data() {
    return {
      IfaStocks6DomainModel: IfaStocks6DomainModel,
      IfaTextFullHalf100DomainModel: IfaTextFullHalf100DomainModel,
      dialogConfirmVisible: false,
      dialogCompleteVisible: false,
      formRef: {},
      form: new IfaForeignStockCounterOrderInputFormModel(),
      // 確認画面へ渡すデータ
      confirmData: {},
      // 完了画面へ渡すデータ
      completeData: {},
      // 更新エラー時のフラグ
      updateErrorFlag: false,
      bgColor: '',
      rules: {
        documentDeliveryDate: [
          {
            validator: this.documentDeliveryDateValidator,
            trigger: 'blur'
          }
        ],
        engPubYmd: [
          {
            validator: this.engPubYmdValidator,
            trigger: 'blur'
          }
        ]
      },
      request: '',
      requestProps: {},
      ifaLinkRequestModel: {}
    }
  },
  watch: {
    'form.foreignStockYmd': function() {
      // 交付日のバリデーションを実行
      if (this.form.documentDeliveryDate) {
        this.$nextTick(() => {
          this.$refs['form'].validateField('documentDeliveryDate');
        });
      }
    }
  },
  computed: {
    IfaForeignStockCounterOrderInputA002RequestModel() { return new IfaForeignStockCounterOrderInputA002RequestModel(this.form) },
    IfaForeignStockCounterOrderInputA008RequestModel() { return new IfaForeignStockCounterOrderInputA008RequestModel(this.form) },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    storeBuyingPower() {
      // 買付余力（外貨）
      if (this.form.depositType === '2') {
        // 特定
        return this.$_out(this.$_withCommaInteger(this.form.specificBuyingPowerForeign))
      } else if (this.form.depositType === '1') {
        // 一般
        return this.$_out(this.$_withCommaInteger(this.form.generalBuyingPowerForeign))
      } else {
        return ''
      }
    },
    maxOrderableQuantity() {
      // 注文可能数量
      if (this.form.depositType === '2') {
        // 特定
        if (!this.form.specificOrderAbleQuantity) {
          // 初期値:ブランク
          return ''
        }
        return this.$_withCommaInteger(this.form.specificOrderAbleQuantity)
      } else if (this.form.depositType === '1') {
        // 一般
        if (!this.form.generalOrderAbleQuantity) {
          // 初期値:ブランク
          return ''
        }
        return this.$_withCommaInteger(this.form.generalOrderAbleQuantity)
      } else {
        return ''
      }
    },
    engPubBrandAreaIsVisible() {
      // 英文開示銘柄適用日<=システム日時の算出
      // Note: 年月日にセパレータ('\-')が含まれる場合は削除
      // Note: 日時の形式の場合は日付だけ(' ')分割してとりだして比較する
      const systemDate = this.$store.getters.requestedTime?.replace(/[\/-]/g, '')?.split(' ')?.[0]
      // 英文開示銘柄適用日がNULLまたは空の場合はfalse
      if (this.form.englishDisclosureBrandEffectiveDate === null || this.form.englishDisclosureBrandEffectiveDate === '') {
        return false
      }
      return this.form.englishDisclosureBrandEffectiveDate <= systemDate
    },
    setRadioDepositTypePattern() {
      // 顧客共通情報.特定口座区分 ＝ "1"（特定口座(代行納付)） または
      // 顧客共通情報.特定口座区分 ＝ "2"（特定口座(確定申告)）の場合
      if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
        // @取得パターン:4
        return '4'
      } else {
        // @取得パターン:5
        return '5'
      }
    },
    target() {
      return 'link' + (this.requestProps.urlId === 0 ? '' : this.requestProps.urlId.toString())
    }
  },
  methods: {
    onShow() {
      this.resetA009()
      Object.assign(this.form, this.formData)
      // 上限数量、取引価格
      if (this.formData.tradeClassification === '3') {
        // 買付
        this.form.upperLimitQuantity = this.formData.maxBuyLimitQuantity
        if (this.formData.buyPrice === '' || this.formData.buyPrice === null) {
          this.form.tradePrice = ''
        } else {
          this.form.tradePrice = Math.floor(this.formData.buyPrice * 1000) / 1000 // 4桁未満切り捨て
        }
        this.form.tradeCd = '11'
        this.bgColor = '#fef0f0'
      } else if (this.formData.tradeClassification === '1') {
        // 売却
        this.form.upperLimitQuantity = this.formData.maxSellLimitQuantity
        if (this.formData.sellPrice === '' || this.formData.sellPrice === null) {
          this.form.tradePrice = ''
        } else {
          this.form.tradePrice = Math.ceil(this.formData.sellPrice * 1000) / 1000 // 4桁未満切り上げ
        }
        this.form.tradeCd = '12'
        this.bgColor = '#ecf5ff'
      }
      this.$nextTick(() => {
        this.formRef = this.$refs.form
      })
    },
    updateA002(response) {
      this.updateErrorFlag = false
      Object.assign(this.form, response.dataList[0])
      // 上限数量
      if (this.form.tradeClassification === '3') {
        // 買付
        this.form.upperLimitQuantity = response.dataList[0].maxBuyLimitQuantity
      } else if (this.form.tradeClassification === '1') {
        // 売却
        this.form.upperLimitQuantity = response.dataList[0].maxSellLimitQuantity
      }
    },
    updateErrorA002() {
      // 上限数量が空もしくは0以下の場合、注文確認ボタンを非活性にする。
      this.updateErrorFlag = true
    },
    quantityFocusOffA007() {
      const contractAmount = BigNumber(this.form.quantity).times(BigNumber(this.form.tradePrice))
      if (
        this.form.tradePrice &&
        contractAmount &&
        Number(this.form.quantity) <= Number(this.form.forCheckUpperLimitOrderAmount)
      ) {
        this.form.contractAmount = contractAmount
      } else {
        // 初期値:ブランク
        this.form.contractAmount = ''
      }
    },
    orderConfirmA008(response) {
      this.confirmData = response.dataList[0]
      this.$nextTick(() => {
        // 確認画面の初期化処理
        this.$refs['IfaForeignStockCounterOrderConfirm'].setData()
        this.dialogConfirmVisible = true
      })
    },
    resetA009() {
      this.form.quantity = ''
      this.form.contractAmount = ''
      this.form.depositType = ''
      this.form.settlementType = '2'
      this.form.kanyuKbn = ''
      this.form.orderMethod = ''
      this.form.powerConfirm = '0'
      this.form.importantMatter = ''
      this.form.foreignStockYmd = ''
      this.form.documentDeliveryDate = ''
      this.form.issuedMethod = ''
      this.form.switchingSolicitationEtf = ''
      this.form.engPubBrand = this.formData.tradeClassification === '3' && this.engPubBrandAreaIsVisible ? '1' : ''
      this.form.engPubYmd = ''
      this.form.summaryAny = ''
      this.$nextTick(() => {
        this.$refs['powerConfirm'].$refs.powerConfirm.resetField()
        if (this.$refs['engPubBrand']) {
          // 英文開示銘柄チェックボックスが表示されている時
          this.$refs['engPubBrand'].$refs.engPubBrand.resetField()
        }
        this.$refs.form.clearValidate()
      })
      this.updateErrorFlag = false
      // 預り区分
      if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
        // @取得パターン:4、初期値は「特定」
        this.form.depositType = '2'
      } else {
        // @取得パターン:5、初期値は未選択
        this.form.depositType = ''
      }
    },
    backA010() {
      this.$emit('back-click', {
        tickerSelectFlag: this.form.returnTickerSelectFlag,
        brandCodeTicker: this.form.forReturnBrandCode,
        brandName: this.form.forReturnBrandName
      })
    },
    englishDisclosureBrandAboutLinkA011() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10004')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    handleOrderFinish(response) {
      // 完了画面にデータを渡す
      this.completeData = response
      this.$nextTick(() => {
        // 完了画面に遷移
        this.$refs['IfaForeignStockCounterOrderComplete'].setData()
        this.dialogConfirmVisible = false
        this.dialogCompleteVisible = true
      })
    },
    // 注文状況一覧へ遷移
    handleMoveToOrderList() {
      this.dialogCompleteVisible = false
      this.$_startShowMenu('SUB0202_0104')
    },
    documentDeliveryDateValidator(rule, value, callback) {
      // 現在日付 < 外国証券情報交付日
      if (this.form.documentDeliveryDate && new Date(this.$store.getters.requestedTime?.split(' ')?.[0]) < new Date(this.form.documentDeliveryDate)) {
        callback(getMessage('errors.date.specifyToToday', ['外国証券情報交付日']))
        return
      }
      // 外国証券情報交付日 < 外国証券情報版番
      if (this.form.documentDeliveryDate && this.form.foreignStockYmd && new Date(this.form.documentDeliveryDate) < new Date(this.form.foreignStockYmd)) {
        callback(getMessage('errors.date.specifyAfterAppointedDay'))
        return
      }
      // OK
      callback()
    },
    engPubYmdValidator(rule, value, callback) {
      // 現在日付 < 英文開示銘柄説明日
      if (this.form.engPubYmd && new Date(this.$store.getters.requestedTime?.split(' ')?.[0]) < new Date(this.form.engPubYmd)) {
        callback(getMessage('errors.date.specifyToToday', ['英文開示銘柄説明日']))
        return
      }
      // OK
      callback()
    },
    ifaForeignStockCounterOrderInputGetNewMainSiteA019(urlId, patternId, httpMethod, brandCode) {
      const newData = {
        urlId: urlId,
        patternId: patternId,
        httpMethod: httpMethod,
        brandCode: brandCode
      }
      this.requestProps = newData
      this.ifaLinkRequestModel = new IfaLinkRequestModel(this.requestProps)
      document.getElementById('ifaForeignStockCounterOrderInputGetNewMainSiteA019').click()
    },
    responseHandlerGetNewMainSiteA019(response) {
      Object.assign(this.form, response?.dataList?.[0])
      if (this.form.postRequest) {
        this.request = this.form.postRequest
      }
      this.openWindow()
    },
    openWindow() {
      // cursor: not-allowed; と pointer-events: none; が同時に使えなかったため
      this.linkUrl = this.form.linkUrl
      this.paramObject = this.form.newMainSiteParamList[0]
      const newWindow = window.open('', this.target)
      if (newWindow) {
        const linkForm = document.createElement('form')
        linkForm.target = this.target
        linkForm.method = 'POST'
        linkForm.action = this.linkUrl
        // request と paramObject をマージする｡
        // key が同じ場合は､ paramObject を優先する
        const objs = Object.assign({}, this.request, this.paramObject)
        const params = Object.entries(objs)
          .map(e => ({ name: e[0], value: e[1] }))
        if (params) {
          for (const param of params) {
            const linkInput = document.createElement('input')
            linkInput.type = 'hidden'
            linkInput.name = param.name
            linkInput.value = param.value
            linkForm.appendChild(linkInput)
          }
        }
        document.body.appendChild(linkForm)
        linkForm.submit()
        document.body.removeChild(linkForm)
      } else {
        const label = this.$store.getters.pageInfo.label
        notifyMessage(2, 'ポップアップを許可してください｡', label)
      }
    }
  }
}
</script>
<style lang="scss">
@import '@/styles/foreignStockOrder.scss';
</style>
<style lang="scss" scoped>
.form-area__section {
  height: auto;
  padding: 0.5rem 0;
  border-bottom: 1px solid #eee;
  border-bottom: 1px solid #eee;
}
.form-area__period.form-area__section {
  padding-bottom: 0;
}
.form-area__section_none-border {
  height: auto;
  margin: 0.5rem 0;
  padding-bottom: 0.2rem;
}
:deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-start;
  margin-right: 0.5rem;
  margin-left: 5rem;
  padding-bottom: 0.5px;
  width: 180px;
}
.label-left-area :deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-end;
}
.label-left-area_hanban :deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-end;
  margin-right: 34px;
}
:deep(.el-form-item__content) {
  line-height: 32px;
}
:deep(.el-form-item__error) {
  white-space: nowrap;
}
.el-form-item__error_custom :deep(.el-form-item__error) {
  white-space: normal;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.form-area__select {
  width: calc(100% - 1.5rem);
  min-width: 140px;
}
.info-item {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
  display: flex;
  justify-content: space-between;
  margin: 0 1.5rem;
  padding: 0 0.25rem;
}
.info-item_usd {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  display: flex;
  justify-content: space-between;
  margin-left: 1.9rem;
  padding: 0 0.25rem;
}
.faq-link {
  padding: 3px 7px;
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
}
.date_class :deep(.el-input) {
  width: 200px;
  margin-bottom: 10px;
}
.date_class_hanban :deep(.el-form-item__content) {
  max-width: 200px;
}
.label_class_engPubBrand :deep(.el-form-item__content) {
  max-width: 64px;
}
.external-link {
  padding: 3px 7px;
  text-decoration: underline;
  &:hover, &:focus {
    text-decoration: underline;
      }
}
.external-link-icon {
  --background-image: url(~@/assets/icons/external-link.svg);
  content: "";

  height: 14px;
  width: 14px;
  display: inline-block;
  vertical-align: middle;
}
.external-link-wrapper {
  display: inline-block
}
.external-link-enabled {
  cursor: pointer;
  .el-link {
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  &:hover {
    opacity: 0.7;
  }
}
.external-link-disabled {
  .el-link {
    // el-link が pointer アイコンにしてしまうので強制的にアイコンを変更
    cursor: not-allowed;
    &:hover {
      // el-link の元々の opacity と混ざるのでリセット
      opacity: unset;
    }
  }
  cursor: not-allowed;
  &:hover {
    opacity: 0.7;
  }
}
</style>
