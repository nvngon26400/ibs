<template>
  <div>
    <!-- SUB0202_0212-06_1_信用返済注文訂正入力 -->
    <el-dialog
      v-model="showInputDialog"
      :title="form.title.name"
      :show-close="false"
      :center="true"
      :before-close="handleCloseModalA011"
      :close-on-click-modal="false"
      :class="classObject.classObjBgColor"
      width="1200"
    >
      <!-- ヘッダ -->
      <div class="header-button">
        <ifa-button
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="handleCloseModalA011"
        ></ifa-button>
      </div>
      <!-- 顧客情報 -->
      <el-row style="font-weight: bold;">
        <!-- 部店 - 口座番号 -->
        <el-col>
          <span>{{ $_out(accountNumber) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.3rem;"
        class="_bold_black_l"
      >
        <!-- 個人・法人アイコン -->
        <el-col
          style="font-size: 20px;"
          class="_bold_black_l"
        >
          <el-icon
            :size="23"
            style="position: relative; top: 5px;"
          >
            <component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component>
          </el-icon>
          <!-- 顧客名 -->
          <span
            style="font-size: 20px;"
            class="_bold_black_l"
          >
            {{ $_out(customerName) }}
          </span>
          <!-- 注意情報リンク -->
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 3.25px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>
      <!-- 銘柄エリア + 銘柄時価情報のカード -->
      <el-card
        style="background-color: #eee; margin-bottom: 0.5rem; margin-top: 0.3rem"
      >
        <!-- 銘柄検索 -->
        <el-row style="display: flex; justify-content: space-between; flex-direction: row; align-items: center;">
          <div style="flex: 0 0 4.16%;"></div>
          <div
            class="ifa-brand-search__adjust-content"
            style="flex: 1; display: flex; align-items: flex-end;"
          >
            <!-- CC014_銘柄検索 -->
            <ifa-brand-search
              ref="ifaBrandSearch"
              :is-code-lock="true"
              trading-type="1"
              :market-list="[{ key: form.latestMarketId }]"
              @change="handleChangeBrandSearch"
              @display-stock-board="true"
            ></ifa-brand-search>
          </div>
          <div
            v-if="brandInfo.brandCode !== ''"
            style="flex: 0 0 200px; margin-left: 0; margin-right: 0; display: flex; align-items: flex-end;"
          >
            <ifa-button
              id="IfaMarginRepayOrderCorrectInputA003"
              text="詳細"
              icon="Document"
              small
              action-type="requestAction"
              action-id="SUB0202_0208-02#A001"
              :request-model="IfaStockDetailInfoA001RequestModel"
              style="margin-right: 4px;"
              @response-handler="responseHandlerIfaMarginRepayOrderCorrectInputA003"
            ></ifa-button>
            <ifa-button
              id="IfaMarginRepayOrderCorrectInputA004"
              text="更新"
              icon="RefreshRight"
              small
              action-type="requestAction"
              action-id="SUB0202_0212-06_1#A004"
              :request-model="IfaMarginRepayOrderCorrectInputA004RequestModel"
              style="margin-right: -3px;"
              @response-handler="responseHandlerIfaMarginRepayOrderCorrectInputA004($event)"
            ></ifa-button>
          </div>
        </el-row>
        <!-- 銘柄時価情報（国内株） -->
        <el-row>
          <ifa-brand-price-info
            ref="ifaBrandPriceInfo"
            :brand-code="form.cc014.brandCode"
            :market="form.cc014.selectedMarket"
            @change="handleChangeBrandPrice"
          ></ifa-brand-price-info>
        </el-row>
      </el-card>
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="180px"
        label-position="left"
      >
        <!-- 注文訂正入力エリア -->
        <!-- 取引種別 -->
        <div class="form-area__section">
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="取引種別"
          >
            <ifa-text
              v-if="form.tradeCd"
              :class="classObject.classObjFontColor"
              code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
              disp-pattern="1"
              :code-key="form.tradeCd"
            ></ifa-text>
            <span
              v-else
              class="font-color__plus bold"
            >現物買</span>
          </el-form-item>
        </div>
        <div class="form-area__section">
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="数量(未約定)"
          >
            <span>
              {{ $_out($_withCommaInteger(form.quantity)) }}
              ({{ $_out($_withCommaInteger(form.unTradeQuantity)) }})株
            </span>
          </el-form-item>
        </div>
        <!-- 価格～訂正方法/執行方法(通常 / 逆指値) -->
        <template
          v-if="form.orderKind === '1'"
        >
          <ifa-margin-repay-order-correct-input-price-normal
            v-model:price-param="priceParam"
            class="el-form-item__error_custom-margin"
            prop-prefix=""
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price-param="beforeCorrectPriceParam"
            :working-status="form.workingStatus"
          ></ifa-margin-repay-order-correct-input-price-normal>

          <!-- 訂正後市場 -->
          <div
            v-if="isMarketVisible(form.sasinariHouhou, form.paymentDeadline, form.latestMarketId, form.sorServiceKbn)"
            class="form-area__section el-form-item__error_custom-margin"
          >
            <ifa-input-select
              v-model="form.afterCorrecMarket"
              code-list-id="SELECT_MARKET"
              label="市場"
              prop="afterCorrecMarket"
              :disp-pattern="1"
              :select-pattern="3"
              required
            ></ifa-input-select>
          </div>
        </template>

        <!-- 価格～訂正方法/執行方法(OCO1) -->
        <template
          v-if="form.orderKind === '2'"
        >
          <!-- OCO1(発火状態=falseの場合のみ表示) -->
          <template
            v-if="form.workingStatus === 'false'"
          >
            <div class="ifd-oco-label separator">OCO1</div>
            <ifa-margin-repay-order-correct-input-price-normal
              v-model:price-param="oco1PriceParam"
              class="el-form-item__error_custom-margin"
              prop-prefix="oco1"
              :trade-cd="form.tradeCd"
              :order-kind="form.orderKind"
              :brand-info="brandInfo"
              :before-correct-price-param="beforeCorrectOco1PriceParam"
              :working-status="form.workingStatus"
            ></ifa-margin-repay-order-correct-input-price-normal>
          </template>
          <!-- OCO2 -->
          <div
            class="ifd-oco-label separator"
            style="margin-top: 8px;"
          >OCO2</div>
          <ifa-margin-repay-order-correct-input-price-oco2
            v-model:price-param="oco2PriceParam"
            class="el-form-item__error_custom-margin"
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price="form.beforeCorrectPrice"
            :working-status="form.workingStatus"
          ></ifa-margin-repay-order-correct-input-price-oco2>
        </template>
        <!-- 注文期間 -->
        <div
          v-if="isPeriodVisible(form.latestMarketId, form.afterCorrecMarket, form.tradeStatus, form.orderKind, form.sasinariHouhou)"
          class="form-area__section"
        >
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="注文期間"
          >
            <span>
              {{ $_out($_getFormattedDateValue(form.period)) }}
            </span>
          </el-form-item>
        </div>

        <!-- 注文期間（当日） -->
        <div
          v-if="isPeriodTodayVisible(form.afterCorrecMarket, form.tradeStatus)"
          class="form-area__section"
        >
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="注文期間"
          >
            <span>
              当日中
            </span>
          </el-form-item>
        </div>

        <!-- 注文訂正その他エリア -->
        <div class="form-area__section">
          <!-- 信用取引区分 -->
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="信用取引区分"
          >
            <span>{{ $_out(form.marginTradeTypeText) }}</span>
          </el-form-item>
        </div>
        <div class="form-area__section">
          <!-- 手数料区分 -->
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="手数料区分"
          >
            <ifa-text
              code-list-id="COMM_TYPE"
              :disp-pattern="2"
              :code-key="form.tesuuryouKbn"
            ></ifa-text>
          </el-form-item>
        </div>
        <div class="form-area__section">
          <!-- 新規単価 -->
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="建単価"
          >
            <span>{{ form.newPrice && form.newPrice !== "0" ? $_withCommaNoneZeroPadding(form.newPrice) + '円' : '-' }}</span>
          </el-form-item>
        </div>
        <div class="form-area__section">
          <!-- 新規建日 -->
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="新規建日"
          >
            <span>{{ isNotAllHalfWidthSpace(form.constructionDate) ? $_getFormattedDateValue(form.constructionDate) : '----/--/--' }}</span>
          </el-form-item>
        </div>
        <div class="form-area__section">
          <!-- 建玉No -->
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="建玉No"
          >
            <span>{{ isNotAllHalfWidthSpace(form.positionNo) ? $_out($_zeroPadding(form.positionNo, 5)) : '-' }}</span>
          </el-form-item>
        </div>
        <!-- IFA固有エリア -->
        <!-- 勧誘区分 -->
        <div class="form-area__section el-form-item__error_custom-margin">
          <ifa-input-select
            v-model="form.kanyuKbn"
            prop="kanyuKbn"
            label="勧誘区分"
            code-list-id="INVITATION_TYPE"
            :disp-pattern="1"
            :select-pattern="1"
            :rules="rules"
            required
          ></ifa-input-select>
        </div>
        <!-- 受注方法 -->
        <div class="form-area__section el-form-item__error_custom-margin">
          <ifa-input-select
            v-model="form.orderMethod"
            prop="orderMethod"
            label="受注方法"
            code-list-id="ORDER_METHOD_TYPE"
            :disp-pattern="1"
            :select-pattern="1"
            size="small"
            required
          ></ifa-input-select>
        </div>
        <!-- フォーム: 重要事項確認 -->
        <el-col class="label-left">
          <div
            class="el-form-item__error_custom-margin"
            style="margin-top: 1rem; margin-bottom: 1.5rem;"
          >
            <ifa-input-check
              ref="checkInsiderRef"
              v-model="form.checkInsider"
              prop="checkInsider"
              label="確認項目"
              code-list-id="INSIDER_CONFIRM"
              :select-pattern="2"
              :disp-pattern="3"
              true-value="1"
              false-value="0"
            ></ifa-input-check>
          </div>
          <div
            v-if="form.afterCorrecMarket === 'A'"
            style="margin-left:229px;"
            class="el-form-item__error_custom-margin"
          >
            <ifa-input-check
              ref="checkSorRef"
              v-model="form.checkSor"
              label=""
              prop="checkSor"
              :code-list-id="'SOR_CONFIRM'"
              :disp-pattern="2"
              :select-pattern="2"
            ></ifa-input-check>
          </div>
        </el-col>

        <!-- 訂正確認ボタン -->
        <ifa-button
          text="訂正確認"
          action-type="requestAction"
          action-id="SUB0202_0212-06_1#A010"
          :form="formRef"
          :request-model="IfaMarginRepayOrderCorrectInputA010RequestModel"
          @response-handler="responseHandlerA010($event)"
        ></ifa-button>
      </el-form>
    </el-dialog>
    <!-- ダイアログ -->
    <!-- 確認画面-->
    <ifa-margin-repay-order-correct-confirm
      ref="ifaMarginRepayOrderCorrectConfirm"
      :is-visible="showConfirmDialog"
      :form-data="confirmData"
      :customer-info="customerInfo"
      @close-modal="handleBackModal"
      @order-finish="handleOrderFinish"
    ></ifa-margin-repay-order-correct-confirm>
    <!-- 完了画面 -->
    <ifa-margin-repay-order-correct-complete
      :is-visible="showCompleteDialog"
      :form-data="completeData"
      :customer-info="customerInfo"
      :yuko-kigen-data="yukoKigenData"
      @close-modal="showCompleteDialog = false"
      @move-to-order-list="handleMoveToOrderList"
    ></ifa-margin-repay-order-correct-complete>
    <ifa-stock-detail-info
      :is-visible="showStockDetailInfo"
      :form-data="stockDetailInfo"
      @close-modal="showStockDetailInfo = false"
    ></ifa-stock-detail-info>
  </div>
</template>
<script>
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import IfaMarginRepayOrderCorrectInputPriceNormal from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/components/IfaMarginRepayOrderCorrectInputPriceNormal.vue'
import IfaMarginRepayOrderCorrectInputPriceOco2 from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/components/IfaMarginRepayOrderCorrectInputPriceOco2.vue'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import IfaCurrency152DigitsBDomainModel from '@/resource/domain/IfaCurrency152DigitsBDomainModel.json'
import { IfaMarginRepayOrderCorrectInputA004RequestModel } from './js/IfaMarginRepayOrderCorrectInputA004RequestModel'
import { IfaMarginRepayOrderCorrectInputA010RequestModel } from './js/IfaMarginRepayOrderCorrectInputA010RequestModel'
import { IfaStockDetailInfoA001RequestModel } from '../spotTrade/js/IfaStockDetailInfoA001RequestModel'
import { IfaMarginRepayOrderCorrectInputFormModel } from './js/IfaMarginRepayOrderCorrectInputFormModel'
import IfaMarginRepayOrderCorrectConfirm from './IfaMarginRepayOrderCorrectConfirm.vue'
import IfaMarginRepayOrderCorrectComplete from './IfaMarginRepayOrderCorrectComplete.vue'
import { getMessage } from '@/utils/errorHandler'
export default {
  components: {
    IfaBrandPriceInfo,
    IfaBrandSearch,
    IfaNoticeInfo,
    IfaStockDetailInfo,
    IfaMarginRepayOrderCorrectInputPriceNormal,
    IfaMarginRepayOrderCorrectInputPriceOco2,
    IfaMarginRepayOrderCorrectConfirm,
    IfaMarginRepayOrderCorrectComplete
  },
  props: {
    isVisible: { type: Boolean, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'open-modal', 'initialize-order-status-list'],
  data() {
    return {
      ifaCurrency152DigitsBDomainModel: IfaCurrency152DigitsBDomainModel,
      form: new IfaMarginRepayOrderCorrectInputFormModel(),
      confirmData: {},
      formRef: this.$refs.formRef,
      rules: {
        checkInsider: {
          validator: (rule, value, callback) => {
            if (value !== '1') {
              callback(new Error(getMessage('errors.selected', ['確認項目'])))
            } else {
              callback()
            }
          }
        },
        checkSor: {
          validator: (rule, value, callback) => {
            if (value !== '1') {
              callback(new Error(getMessage('errors.selected', ['確認項目'])))
            } else {
              callback()
            }
          }
        }
      },
      brandInfo: {
        brandCode: '', // 銘柄コード
        market: '', // 市場
        currentPrice: '', // 現在値
        tick: '', // 現在ティック
        currentFlag: '', // 現在値フラグ
        start: '', // 始値
        startTime: '', // 終値
        high: '', // 高値
        highTime: '', // 高値更新時刻
        low: '', // 安値
        lowTime: '', // 安値更新時刻
        diff: '', // 前日比
        diffRaw: '', // 前日比
        ratio: '', // 前日比率
        updateDate: '', // 現在値日付
        updateTime4: '', // 現在値更新時刻/前日比更新時刻
        last: '', // 前日終値
        lastDate: '', // 前日終値日付
        buySellPrice: '', // 売買代金
        volume: '', // 出来高
        volumeTime: '', // 出来高更新時刻
        unit: '', // 売買単位
        sellStopHigh: '', // ストップ高(売)
        sellStopLow: '', // ストップ安(売り)
        buyStopHigh: '', // ストップ高(買)
        buyStopLow: '', // ストップ安(買)
        baseDate: '', // 呼び値制限(年月日)
        orderPriceUnit: [], // 呼び値リスト
        basePrice: ''
      },
      stockDetailInfo: {},
      ifaBrandPriceInfoRequestModel: {},
      showConfirmDialog: false,
      showCompleteDialog: false,
      showStockDetailInfo: false,
      // 完了画面に渡すデータ
      completeData: {},
      yukoKigenData: {
        yukoKigenChange: '',
        yukoKigen: ''
      },
      classObject: {
        classObjBgColor: '',
        classObjFontColor: ''
      }
    }
  },
  computed: {
    // 自画面の表示制御
    showInputDialog: {
      get() {
        return this.isVisible && !this.showConfirmDialog && !this.showCompleteDialog
      },
      set(newValue) {
        if (newValue === false) {
          this.$emit('close-modal')
        }
      }
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    // A004リクエストモデル
    IfaMarginRepayOrderCorrectInputA004RequestModel() {
      return new IfaMarginRepayOrderCorrectInputA004RequestModel(this.form)
    },
    // A010リクエストモデル
    IfaMarginRepayOrderCorrectInputA010RequestModel() {
      return new IfaMarginRepayOrderCorrectInputA010RequestModel(this.form)
    },
    // 株式詳細情報 A001リクエストモデル
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(
        {
          brandCode: this.form.cc014.brandCode,
          market: this.form.cc014.selectedMarket
        }
      )
    },
    // 顧客共通情報
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    priceParam: {
      get() {
        return {
          sasinariHouhou: this.form.sasinariHouhou,
          sasinariJyouken: this.form.sasinariJyouken,
          triggerPrice: this.form.triggerPrice,
          triggerPriceText: this.form.triggerPriceText,
          gyakusasiHouhou: this.form.gyakusasiHouhou,
          price: this.form.price
        }
      },
      set(newValue) {
        this.form.sasinariHouhou = newValue.sasinariHouhou
        this.form.sasinariJyouken = newValue.sasinariJyouken
        this.form.triggerPrice = newValue.triggerPrice
        this.form.triggerPriceText = newValue.triggerPriceText
        this.form.gyakusasiHouhou = newValue.gyakusasiHouhou
        this.form.price = newValue.price
      }
    },
    oco1PriceParam: {
      get() {
        return {
          sasinariHouhou: this.form.oco1SasinariHouhou,
          sasinariJyouken: this.form.oco1SasinariJyouken,
          triggerPrice: this.form.triggerPrice,
          triggerPriceText: this.form.triggerPriceText,
          gyakusasiHouhou: this.form.gyakusasiHouhou,
          price: this.form.oco1Price
        }
      },
      set(newValue) {
        this.form.oco1SasinariHouhou = newValue.sasinariHouhou
        this.form.oco1SasinariJyouken = newValue.sasinariJyouken
        this.form.oco1Price = newValue.price
      }
    },
    oco2PriceParam: {
      get() {
        return {
          sasinariHouhou: '3',
          sasinariJyouken: this.form.oco2GyakusasiJyouken,
          triggerPrice: this.form.oco2TriggerPrice,
          triggerPriceText: this.form.oco2TriggerPriceText,
          gyakusasiHouhou: this.form.oco2GyakusasiHouhou,
          price: this.form.oco2Price
        }
      },
      set(newValue) {
        this.form.oco2GyakusasiJyouken = newValue.sasinariJyouken
        this.form.oco2TriggerPrice = newValue.triggerPrice
        this.form.oco2TriggerPriceText = newValue.triggerPriceText
        this.form.oco2GyakusasiHouhou = newValue.gyakusasiHouhou
        this.form.oco2Price = newValue.price
      }
    },
    beforeCorrectPriceParam() {
      return {
        sasinariHouhou: this.form.beforeCorrectPrice.sasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.sasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.triggerPrice,
        triggerPriceText: this.form.beforeCorrectPrice.triggerPriceText,
        gyakusasiHouhou: this.form.beforeCorrectPrice.gyakusasiHouhou,
        price: this.form.beforeCorrectPrice.price
      }
    },
    beforeCorrectOco1PriceParam() {
      return {
        sasinariHouhou: this.form.beforeCorrectPrice.oco1SasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.oco1SasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.triggerPrice,
        triggerPriceText: this.form.beforeCorrectPrice.triggerPriceText,
        gyakusasiHouhou: this.form.beforeCorrectPrice.gyakusasiHouhou,
        price: this.form.beforeCorrectPrice.oco1Price
      }
    }
  },
  methods: {
    onShow(event) {
      // データの初期化
      const data = event.dataList[0]
      // this.unstripData(data)
      this.form = new IfaMarginRepayOrderCorrectInputFormModel()
      this.form = { ...this.form, ...data }
      this.form.ecOrderNo = this.ecOrderNo
      if (
        this.form.sasinariHouhou === '2' ||
        this.form.gyakusasiHouhou === '2'
      ) {
        this.form.price = ''
      }
      if (this.form.oco2GyakusasiHouhou === '2') {
        this.form.oco2Price = ''
      }
      if (
        this.form.ifd1SasinariHouhou === '2' ||
        this.form.ifd1GyakusasiHouhou === '2'
      ) {
        this.form.ifd1Price = ''
      }
      if (this.form.ifd2GyakusasiHouhou === '2') {
        this.form.ifd2Price = ''
      }
      this.form.beforeCorrectPrice = { ...data }
      this.setClassObj()
      // フォームの初期化
      this.formRef = this.$refs.formRef
      this.$nextTick(() => {
        // チェックボックスのリセット
        this.$refs['checkInsiderRef'].$refs.checkInsider.resetField()
        if (this.$refs['checkSorRef']) {
          this.$refs['checkSorRef'].$refs.checkSor.resetField()
        }
        this.formRef.clearValidate()
      })

      // 銘柄検索の初期化
      this.$refs.ifaBrandSearch.resetAll()
      this.$nextTick(() => {
        this.$refs.ifaBrandSearch.setStockInfo({ brandCode: this.form.brandCode })
      })
      // 訂正後市場のデフォルト値セット
      // 表示条件を満たす場合のみセットする
      if (this.form.orderKind === '1' && this.isMarketVisible(this.form.sasinariHouhou, this.form.paymentDeadline, this.form.latestMarketId, this.form.sorServiceKbn)) {
        // SOR注文区分＝"△"：SOR対象外注文　の場合 東証　をデフォルト選択
        if (this.form.sorOrderClassification === ' ') {
          this.form.afterCorrecMarket = '0'
        // 上記以外 当社優先市場／SOR　をデフォルト選択
        } else {
          this.form.afterCorrecMarket = 'A'
        }
      }
    },
    responseHandlerIfaMarginRepayOrderCorrectInputA003(event) {
      this.stockDetailInfo = event.dataList[0]
      this.showStockDetailInfo = true
    },
    responseHandlerIfaMarginRepayOrderCorrectInputA004(event) {
      const data = event.dataList[0]
      this.form = { ...this.form, ...data }
      this.form.beforeCorrectPrice = { ...data }
      // 銘柄時価情報（国内株）の更新
      this.$refs.ifaBrandPriceInfo.updateRequest()
    },
    responseHandlerA010(event) {
      this.confirmData = Object.assign(this.confirmData, event.dataList[0])
      this.yukoKigenData.yukoKigenChange = event.dataList[0].yukoKigenChange
      this.yukoKigenData.yukoKigen = event.dataList[0].yukoKigen
      this.$nextTick(() => {
        this.$refs.ifaMarginRepayOrderCorrectConfirm.onShow()
        this.showConfirmDialog = true
      })
    },
    handleBackModal() {
      // 確認画面から入力画面へ遷移
      // 確認画面クローズ
      this.showConfirmDialog = false
      // 入力画面オープン
      this.$emit('open-modal')
    },
    handleCloseModalA011() {
      // ダイアログのクローズ
      this.showInputDialog = false
      this.showConfirmDialog = false
      this.showCompleteDialog = false
      this.showStockDetailInfo = false
    },
    handleChangeBrandSearch(newValue) {
      this.form.cc014.brandCode = newValue.data.brandCode
      this.form.cc014.selectedMarket = this.$refs.ifaBrandSearch.selectedMarket !== 'A'
        ? this.$refs.ifaBrandSearch.selectedMarket // SOR以外の場合、選択市場
        : newValue.data.upperRankMkt // SORの場合、最上位上位市場
      // 銘柄時価情報（国内株）の初期化
      this.$refs.ifaBrandPriceInfo.updateRequest()
    },
    handleChangeBrandPrice(newValue) {
      this.brandInfo = { ...this.brandInfo, ...newValue }
    },
    // 完了画面に遷移
    handleOrderFinish(response) {
      Object.assign(this.completeData, response)
      this.showConfirmDialog = false
      this.showCompleteDialog = true
    },
    // 注文一覧画面に遷移
    handleMoveToOrderList() {
      this.showCompleteDialog = false
      this.$emit('initialize-order-status-list')
    },
    checkPrice(priceStr, buySellType) {
      const price = Number(priceStr)
      let stopLow = 9999999999
      let stopHigh = 0
      if (buySellType === '5' || buySellType === '') {
        stopLow = Number(this.brandInfo.buyStopLow)
        stopHigh = Number(this.brandInfo.buyStopHigh)
      } else {
        stopLow = Number(this.brandInfo.sellStopLow)
        stopHigh = Number(this.brandInfo.sellStopHigh)
      }
      if (price < stopLow || stopHigh < price) {
        return false
      }
      return true
    },
    // 背景の色 文字色
    setClassObj() {
      if (this.form.tradeCd === '5') {
        // 返済買
        this.classObject.classObjBgColor = 'buy-background-color'
        this.classObject.classObjFontColor = 'font-color__plus bold'
      } else {
        // 返済売
        this.classObject.classObjBgColor = 'sell-background-color'
        this.classObject.classObjFontColor = 'font-color__minus bold'
      }
    },
    isNotAllHalfWidthSpace(str) {
      if (str) {
        // ALL半角スペースかチェックする
        const regex = new RegExp(/^\s+$/)
        return !regex.test(str)
      }
      return false
    },
    isMarketVisible(sasinariHouhou, paymentDeadline, latestMarketId, sorServiceKbn) {
      // 執行方法（通常/逆指値）= 逆指値 の場合、項目非表示
      if (sasinariHouhou === '3') {
        return false
      }
      // SOR取扱区分 ≠ SOR対象銘柄 の場合、項目非表示
      if (sorServiceKbn !== '1') {
        return false
      }
      // 直近市場 = 東証 の場合、項目表示
      if (latestMarketId === '0') {
        return true
      }
      // 上記以外 項目非表示
      return false
    },
    isPeriodVisible(latestMarketId, afterCorrecMarket, tradeStatus, orderKind, sasinariHouhou) {
      // 注文種別 != 通常/逆指値　の場合、項目表示
      if (orderKind !== '1') {
        return true
      // 注文種別 = 通常/逆指値　かつ　執行方法（通常/逆指値）= 逆指値 の場合、項目表示
      } else if (orderKind === '1' && sasinariHouhou === '3') {
        return true
      // 上記以外
      } else {
        // 直近市場 != 東証 の場合、項目表示
        if (latestMarketId !== '0') {
          return true
        } else {
          // 訂正後市場 = 東証 または 約定ステータス = 未出来 の場合、項目表示
          if (afterCorrecMarket === '0' || tradeStatus === '0') {
            return true
          }
        }
        // 上記以外 項目非表示
        return false
      }
    },
    isPeriodTodayVisible(afterCorrecMarket, tradeStatus) {
      // 訂正後市場 = 当社優先市場／SOR かつ 約定ステータス = 一部出来 の場合、項目表示
      if (afterCorrecMarket === 'A' && tradeStatus === '1') {
        return true
      }
      // 上記以外 項目非表示
      return false
    }
  }
}
</script>
<style lang="scss">
@import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
:deep(.el-tabs_full_content) .el-tabs__content {
  padding: 0;
}
.header-button {
  display: flex;
  justify-content: flex-end;
  padding: 0 0 0.2rem 1rem;
}
.form-area__section {
  border-bottom: 1px solid #eee;
  margin: 0.5rem 0 0 0;
  padding: 0.5rem 0 1rem 0px;
}
.form-button__wrapper {
  display: flex;
  padding: 1.5rem 2rem 0.3rem 2rem;
}
:deep(.charge-type) .el-form-item__label {
  color: #f00;
}
:deep(.el-form-item__label) {
  margin-left: calc((100% - 7.5px) / 24 - 3.75px);
  margin-right: 0.5rem;
  padding-right: 3rem;
  font-weight: 700;
}
.error-message {
  margin: 0.5rem;
  color: #f00;
  font-size: 14px;
  font-weight: 600;
}
.warning-message {
  margin: 0.5rem;
  color: #f00;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem;
  color: #000;
  font-size: 14px;
}
:deep(.el-form-item__error) {
  margin-left: 2.5rem;
}
:deep(input) {
  height: 40px;
}
:deep(.correction-dialog) .el-dialog {
  width: 1200px;
}
.buying-power-amount {
  border-bottom: 1px solid #bfcbD9;
  text-align: right;
}
:deep(.separator) {
  height: 20px;
  padding-left: 16px;
  background: #ffd985;
  font-weight: bold;
  margin-bottom: 10px;
}
:deep(.el-form-item__error_custom-margin .el-form-item__error) {
  margin: 0.5rem 0 0 0;
}
.ifa-brand-search__adjust-content {
  :deep(.ifa-brand-search__wrapper .brand-info) {
    --brand-info-code-position-adjust: -3rem;
    --brand-info-name-position-adjust: 1.5rem;
  }
  :deep(.ifa-brand-search__wrapper .market-info) {
    --market-info-position-adjust: -1.5rem;
  }
}
</style>
