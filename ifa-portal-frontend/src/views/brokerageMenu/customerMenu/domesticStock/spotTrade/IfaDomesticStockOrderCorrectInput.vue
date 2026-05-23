<template>
  <div>
    <!-- 訂正入力ダイアログ -->
    <el-dialog
      v-model="showInputDialog"
      :title="form.title.name"
      :show-close="false"
      :center="true"
      :before-close="handleCloseModalA011"
      :close-on-click-modal="false"
      :class="{
        'buy-background-color': (form.orderKind === '1' || form.orderKind === '2') && form.tradeCd === '1',
        'sell-background-color': (form.orderKind === '1' || form.orderKind === '2') && form.tradeCd === '2',
        'ifd-background-color': form.orderKind === '3' || form.orderKind === '4',
      }"
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
          <span>{{ $_out(customerInfo.butenCode) }}-{{ customerInfo.accountNumber ? $_zeroPadding(customerInfo.accountNumber, 7) : '-' }}</span>
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
            <!-- 法人アイコン -->
            <officeBuilding
              v-if="customerInfo.corporationType == '1'"
            ></officeBuilding>

            <!-- 個人アイコン -->
            <avatar v-else></avatar>
          </el-icon>

          <!-- 顧客名 -->
          <span
            style="font-size: 20px;"
            class="_bold_black_l"
          >
            {{ $_out(customerInfo.customerNameKanji) }}（{{ $_out(customerInfo.customerNameKana) }}）
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
        style="background-color: #eee; margin-bottom: 0.5rem; margin-top: 0.3rem;"
      >
        <!-- 銘柄検索 -->
        <el-row
          :style="{
            display: 'flex',
            justifyContent: 'space-between',
            flexDirection: 'row'
          }"
        >
          <div style="flex: 0 0 4.16%;"></div>
          <div
            style="flex: 1;"
          >
            <ifa-brand-search
              ref="ifaBrandSearch"
              :is-code-lock="true"
              trading-type="0"
              :market-list="[{ key: form.latestMarketId }]"
              @change="handleChangeBrandSearch"
              @display-stock-board="true"
            ></ifa-brand-search>
          </div>
          <div
            v-if="brandInfo.brandCode !== ''"
            style="flex: 0 0 200px; margin-left: 32px;"
          >
            <ifa-button
              id="IfaDomesticStockOrderCorrectInputA003"
              text="詳細"
              icon="Document"
              action-type="requestAction"
              small
              action-id="SUB0202_0208-02#A001"
              :request-model="IfaStockDetailInfoA001RequestModel"
              @response-handler="responseHandlerDomesticStockOrderCorrectInputA003"
            ></ifa-button>

            <ifa-button
              id="IfaDomesticStockOrderCorrectInputA004 "
              text="更新"
              icon="RefreshRight"
              action-type="requestAction"
              small
              action-id="SUB0202_0208-03_1#A004"
              :request-model="IfaDomesticStockOrderCorrectInputA004RequestModel"
              @response-handler="responseHandlerDomesticStockOrderCorrectInputA004($event)"
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

      <!-- 買付余力エリア(取引種別が現物買場合のみ表示) -->
      <el-card
        v-if="form.tradeCd === '1' || form.tradeCd === ''"
        style="background-color: #eee; margin-bottom: 0.5rem;"
      >
        <el-row
          :gutter="20"
        >
          <el-col
            :span="7"
            :offset="1"
          >
            <span style="font-weight: bold;">買付余力</span>
          </el-col>
          <el-col
            :span="4"
          >
            {{ $_out($_getFormattedDateValue(form.settlementDateAfterBusinessDayT2, 'date6')) }}(2営業日後)
          </el-col>
          <el-col
            :span="4"
            :offset="1"
          >
            {{ $_out($_getFormattedDateValue(form.settlementDateAfterBusinessDayT3, 'date6')) }}(3営業日後)
          </el-col>
        </el-row>
        <el-row
          :gutter="20"
          style="margin-top: 12px;"
        >
          <el-col
            :span="4"
            :offset="4"
          >
            <span v-if="customerInfo.jrIsaContractType === '1'">(総合口座)</span>
          </el-col>
          <el-col
            :span="4"
          >
            <div class="buying-power-amount">
              {{ $_out($_withCommaInteger(form.yenBuyingPowerGeneralAccountT2)) }} 円
            </div>
          </el-col>
          <el-col
            :span="4"
            :offset="1"
          >
            <div class="buying-power-amount">
              {{ $_out($_withCommaInteger(form.yenBuyingPowerGeneralAccountT3)) }} 円
            </div>
          </el-col>
        </el-row>

        <el-row
          v-if="customerInfo.jrIsaContractType === '1'"
          :gutter="20"
          style="margin-top: 12px;"
        >
          <el-col
            :span="4"
            :offset="4"
          >
            <span>(ジュニアNISA口座)</span>
          </el-col>
          <el-col
            :span="4"
          >
            <div class="buying-power-amount">
              {{ $_out($_withCommaInteger(form.yenBuyingPowerJrNisaT2)) }} 円
            </div>
          </el-col>
          <el-col
            :span="4"
            :offset="1"
          >
            <div class="buying-power-amount">
              {{ $_out($_withCommaInteger(form.yenBuyingPowerJrNisaT3)) }} 円
            </div>
          </el-col>
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

        <!-- IFD1見出し(IFD/IFDOCOの場合のみ表示) -->
        <div
          v-if="form.orderKind === '3' || form.orderKind === '4'"
        >
          <div class="ifd-oco-label separator">IFD1</div>
        </div>

        <!-- 取引種別 -->
        <div class="form-area__section">
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="取引種別"
          >
            <ifa-text
              v-if="form.tradeCd"
              :class="form.tradeCd === '1' ? 'font-color__plus bold' : form.tradeCd === '2' ? 'font-color__minus bold' : ''"
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
              {{ $_out($_withCommaInteger(form.orderQuantity)) }}
              ({{ $_out($_withCommaInteger(form.unTradeQuantity)) }})株
            </span>
          </el-form-item>
        </div>

        <!-- 価格～訂正方法/執行方法(通常 / 逆指値) -->
        <template
          v-if="form.orderKind === '1'"
        >
          <ifa-domestic-stock-order-correct-input-price-normal
            v-model:price-param="priceParam"
            class="el-form-item__error_custom-margin"
            prop-prefix=""
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price-param="beforeCorrectPriceParam"
            :working-status="form.workingStatus"
          ></ifa-domestic-stock-order-correct-input-price-normal>

          <!-- 訂正後市場 -->
          <div
            v-if="isMarketVisible"
            class="form-area__section el-form-item__error_custom-margin"
          >
            <ifa-input-select
              v-model="form.afterCorrectMarket"
              code-list-id="SELECT_MARKET"
              label="市場"
              prop="afterCorrectMarket"
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
            <ifa-domestic-stock-order-correct-input-price-normal
              v-model:price-param="oco1PriceParam"
              class="el-form-item__error_custom-margin"
              prop-prefix="oco1"
              :trade-cd="form.tradeCd"
              :order-kind="form.orderKind"
              :brand-info="brandInfo"
              :before-correct-price-param="beforeCorrectOco1PriceParam"
              :working-status="form.workingStatus"
            ></ifa-domestic-stock-order-correct-input-price-normal>
          </template>

          <!-- OCO2 -->
          <div
            class="ifd-oco-label separator"
            style="margin-top: 8px;"
          >OCO2</div>
          <ifa-domestic-stock-order-correct-input-price-oco2
            v-model:price-param="oco2PriceParam"
            class="el-form-item__error_custom-margin"
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price="form.beforeCorrectPrice"
            :working-status="form.workingStatus"
          ></ifa-domestic-stock-order-correct-input-price-oco2>
        </template>

        <!-- 価格～注文期間 - IFD1(IFD / IFDOCO)-->
        <template
          v-if="form.orderKind === '3' || form.orderKind === '4'"
        >
          <ifa-domestic-stock-order-correct-input-price-normal
            v-model:price-param="ifd1PriceParam"
            class="el-form-item__error_custom-margin"
            prop-prefix="ifd1"
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price-param="beforeCorrectIfd1PriceParam"
            :working-status="form.workingStatus"
          ></ifa-domestic-stock-order-correct-input-price-normal>

          <!-- 注文期間 -->
          <div class="form-area__section">
            <el-form-item
              class="el-form-item__error_custom-margin"
              label="注文期間"
            >
              <span>
                {{ $_out($_getFormattedDateValue(form.period)) }}
              </span>
            </el-form-item>
          </div>
        </template>

        <!-- 取引種別～訂正方法/執行方法 - IFD2(IFD) -->
        <template
          v-if="form.orderKind === '3'"
        >
          <!-- 取引種別 -->
          <div class="ifd-oco-label separator">IFD2</div>
          <div class="form-area__section">
            <el-form-item
              class="el-form-item__error_custom-margin"
              label="取引種別"
            >
              <span class="font-color__minus bold">現物売</span>
            </el-form-item>
          </div>

          <ifa-domestic-stock-order-correct-input-price-normal
            v-model:price-param="ifd2PriceParam"
            class="el-form-item__error_custom-margin"
            prop-prefix="ifd2"
            trade-cd="2"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price-param="beforeCorrectIfd2PriceParam"
          ></ifa-domestic-stock-order-correct-input-price-normal>
        </template>

        <!-- 取引種別～訂正方法/執行方法 - IFD2(IFDOCO) -->
        <template
          v-if="form.orderKind === '4'"
        >
          <div class="ifd-oco-label separator">IFD2</div>

          <div class="form-area__section">
            <el-form-item
              class="el-form-item__error_custom-margin"
              label="取引種別"
            >
              <span class="font-color__minus bold">現物売</span>
            </el-form-item>
          </div>

          <!-- OCO1-->
          <div class="ifd-oco-label separator">OCO1</div>
          <ifa-domestic-stock-order-correct-input-price-normal
            v-model:price-param="oco1PriceParam"
            class="el-form-item__error_custom-margin"
            prop-prefix="oco1"
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price-param="beforeCorrectOco1PriceParam"
          ></ifa-domestic-stock-order-correct-input-price-normal>

          <!-- OCO2 -->
          <div class="ifd-oco-label separator">OCO2</div>
          <ifa-domestic-stock-order-correct-input-price-oco2
            v-model:price-param="oco2PriceParam"
            class="el-form-item__error_custom-margin"
            :trade-cd="form.tradeCd"
            :order-kind="form.orderKind"
            :brand-info="brandInfo"
            :before-correct-price="form.beforeCorrectPrice"
            :working-status="form.workingStatus"
          ></ifa-domestic-stock-order-correct-input-price-oco2>
        </template>

        <!-- 注文期間 -->
        <div
          v-if="isPeriodVisible"
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
          v-if="isPeriodTodayVisible" 
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

        <!-- 預り区分 -->
        <div class="form-area__section">
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="預り区分"
          >
            <span v-if="!form.depositType">-</span>
            <ifa-text
              code-list-id="DOMESTIC_DEPOSIT_TYPE"
              :disp-pattern="setTextDepositTypeDispPattern"
              :code-key="form.depositType"
            ></ifa-text>

            <!-- NISA枠 -->
            <span
              v-if="(form.tradeCd === '1' || form.tradeCd === '') && form.depositType === 'H'"
              style="margin-left: 50px; color: #FF0000;"
            >
              NISA投資可能枠は{{ $_out($_withCommaInteger(form.nisaInvestableQuotaMessage)) }}円です。
            </span>
          </el-form-item>
        </div>

        <!-- IFA固有エリア -->
        <!-- 手数料区分 -->
        <div class="form-area__section">
          <el-form-item
            class="el-form-item__error_custom-margin"
            label="手数料区分"
          >
            <ifa-text
              code-list-id="COMM_TYPE"
              :disp-pattern="2"
              :code-key="form.tesuuryouKbn"
            ></ifa-text>
            <span>{{ form.tesuuryouKbn ? '（電話手数料）' : '-' }}</span>
          </el-form-item>
        </div>

        <!-- 勧誘区分 -->
        <div class="form-area__section el-form-item__error_custom-margin">
          <ifa-input-select
            v-model="form.kanyuKbn"
            code-list-id="INVITATION_TYPE"
            label="勧誘区分"
            prop="kanyuKbn"
            :disp-pattern="1"
            :select-pattern="1"
            required
          ></ifa-input-select>
        </div>

        <!-- 受注方法 -->
        <div class="form-area__section el-form-item__error_custom-margin">
          <ifa-input-select
            v-model="form.receiveOrderType"
            code-list-id="ORDER_METHOD_TYPE"
            label="受注方法"
            prop="receiveOrderType"
            :disp-pattern="1"
            :select-pattern="1"
            required
          ></ifa-input-select>
        </div>

        <!-- 重要事項確認 -->

        <!-- フォーム: 重要事項確認 -->
        <el-col class="label-left">
          <div
            class="el-form-item__error_custom-margin"
            style="margin-top: 1rem; margin-bottom: 1.5rem;"
          >
            <ifa-input-check
              ref="checkInsiderRef"
              v-model="form.checkInsider"
              code-list-id="INSIDER_CONFIRM"
              label="確認項目"
              prop="checkInsider"
              :select-pattern="2"
              :disp-pattern="3"
              true-value="1"
              false-value="0"
              required
            ></ifa-input-check>
          </div>
          <div
            v-if="this.form.afterCorrectMarket === 'A'"
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
          action-id="SUB0202_0208-03_1#A010"
          :form="formRef"
          :request-model="IfaDomesticStockOrderCorrectInputA010RequestModel"
          @response-handler="responseHandlerDomesticStockOrderCorrectInputA010"
        ></ifa-button>
      </el-form>
    </el-dialog>

    <!-- ダイアログ -->
    <ifa-domestic-stock-order-correct-confirm
      :is-visible="showConfirmDialog"
      :confirm-data="confirmData"
      @back-modal="handleBackModal"
      @order-finish="handleOrderFinish"
    ></ifa-domestic-stock-order-correct-confirm>

    <ifa-domestic-stock-order-correct-complete
      ref="ifaDomesticStockOrderCorrectComplete"
      :is-visible="showCompleteDialog"
      :complete-data="completeData"
      @move-to-order-list="domesticStockOrderCorrectCompleteFinish"
    ></ifa-domestic-stock-order-correct-complete>

    <ifa-stock-detail-info
      :is-visible="showStockDetailInfo"
      :form-data="stockDetailInfo"
      @close-modal="showStockDetailInfo = false"
    ></ifa-stock-detail-info>
  </div>
</template>

<script>
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaBrandPriceInfo from '@/components/Info/IfaBrandPriceInfo'
import IfaBrandSearch from '@/components/SearchCondition/IfaBrandSearch'
import IfaCurrency152DigitsBDomainModel from '@/resource/domain/IfaCurrency152DigitsBDomainModel.json'
import IfaUnitPriceYen102DigitsBDomainModel from '@/resource/domain/IfaUnitPriceYen102DigitsBDomainModel.json'
import IfaDomesticStockOrderCorrectInputPriceNormal from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/components/IfaDomesticStockOrderCorrectInputPriceNormal'
import IfaDomesticStockOrderCorrectInputPriceOco2 from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/components/IfaDomesticStockOrderCorrectInputPriceOco2'
import IfaDomesticStockOrderCorrectComplete from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaDomesticStockOrderCorrectComplete'
import IfaDomesticStockOrderCorrectConfirm from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaDomesticStockOrderCorrectConfirm'
import IfaStockDetailInfo from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/IfaStockDetailInfo'
import { IfaDomesticStockOrderCorrectInputA004RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaDomesticStockOrderCorrectInputA004RequestModel.js'
import { IfaDomesticStockOrderCorrectInputA010RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaDomesticStockOrderCorrectInputA010RequestModel.js'
import { IfaDomesticStockOrderCorrectInputFormModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaDomesticStockOrderCorrectInputFormModel.js'
import { IfaStockDetailInfoA001RequestModel } from '@/views/brokerageMenu/customerMenu/domesticStock/spotTrade/js/IfaStockDetailInfoA001RequestModel'
import { getMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaBrandPriceInfo,
    IfaBrandSearch,
    IfaNoticeInfo,
    IfaStockDetailInfo,
    IfaDomesticStockOrderCorrectConfirm,
    IfaDomesticStockOrderCorrectComplete,
    IfaDomesticStockOrderCorrectInputPriceNormal,
    IfaDomesticStockOrderCorrectInputPriceOco2
  },
  props: {
    isVisible: { type: Boolean, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'open-modal', 'initialize-order-status-list'],
  data() {
    return {
      ifaUnitPriceYen102DigitsBDomainModel: IfaUnitPriceYen102DigitsBDomainModel,
      ifaCurrency152DigitsBDomainModel: IfaCurrency152DigitsBDomainModel,
      form: new IfaDomesticStockOrderCorrectInputFormModel(),
      confirmData: {},
      formRef: this.$refs.formRef,
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
        basePrice: '' // 基準価格
      },
      stockDetailInfo: {},
      ifaBrandPriceInfoRequestModel: {},
      showConfirmDialog: false,
      showCompleteDialog: false,
      showStockDetailInfo: false,
      // 完了画面に渡すデータ
      completeData: {},
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

    // A004リクエストモデル
    IfaDomesticStockOrderCorrectInputA004RequestModel() {
      return new IfaDomesticStockOrderCorrectInputA004RequestModel(this.form)
    },

    // A010リクエストモデル
    IfaDomesticStockOrderCorrectInputA010RequestModel() {
      return new IfaDomesticStockOrderCorrectInputA010RequestModel(this.form)
    },

    // 株式詳細情報 A001リクエストモデル
    IfaStockDetailInfoA001RequestModel() {
      return new IfaStockDetailInfoA001RequestModel(this.form)
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
          stopOrderPriceText2: this.form.stopOrderPriceText2,
          gyakusasiHouhou: this.form.gyakusasiHouhou,
          price: this.form.price
        }
      },

      set(newValue) {
        this.form.sasinariHouhou = newValue.sasinariHouhou
        this.form.sasinariJyouken = newValue.sasinariJyouken
        this.form.triggerPrice = newValue.triggerPrice
        this.form.stopOrderPriceText2 = newValue.stopOrderPriceText2
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
          stopOrderPriceText2: this.form.stopOrderPriceText2,
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
          sasinariHouhou: this.form.sasinariHouhou,
          sasinariJyouken: this.form.oco2GyakusasiJyouken,
          triggerPrice: this.form.oco2TriggerPrice,
          stopOrderPriceText2: this.form.oco2StopOrderPriceText2,
          gyakusasiHouhou: this.form.oco2GyakusasiHouhou,
          price: this.form.oco2Price
        }
      },

      set(newValue) {
        this.form.oco2GyakusasiJyouken = newValue.sasinariJyouken
        this.form.oco2TriggerPrice = newValue.triggerPrice
        this.form.oco2StopOrderPriceText2 = newValue.stopOrderPriceText2
        this.form.oco2GyakusasiHouhou = newValue.gyakusasiHouhou
        this.form.oco2Price = newValue.price
      }
    },

    ifd1PriceParam: {
      get() {
        return {
          sasinariHouhou: this.form.ifd1SasinariHouhou,
          sasinariJyouken: this.form.ifd1SasinariJyouken,
          triggerPrice: this.form.ifd1TriggerPrice,
          stopOrderPriceText2: this.form.ifd1StopOrderPriceText2,
          gyakusasiHouhou: this.form.ifd1GyakusasiHouhou,
          price: this.form.ifd1Price
        }
      },

      set(newValue) {
        this.form.ifd1SasinariHouhou = newValue.sasinariHouhou
        this.form.ifd1SasinariJyouken = newValue.sasinariJyouken
        this.form.ifd1TriggerPrice = newValue.triggerPrice
        this.form.ifd1StopOrderPriceText2 = newValue.stopOrderPriceText2
        this.form.ifd1GyakusasiHouhou = newValue.gyakusasiHouhou
        this.form.ifd1Price = newValue.price
      }
    },

    ifd2PriceParam: {
      get() {
        return {
          limit: this.form.ifd2Limit,
          sasinariHouhou: this.form.ifd2SasinariHouhou,
          sasinariJyouken: this.form.ifd2SasinariJyouken,
          triggerPrice: this.form.ifd2TriggerPrice,
          stopOrderPriceText2: this.form.ifd2StopOrderPriceText2,
          gyakusasiHouhou: this.form.ifd2GyakusasiHouhou,
          price: this.form.ifd2Price
        }
      },

      set(newValue) {
        this.form.ifd2Limit = newValue.limit
        this.form.ifd2SasinariHouhou = newValue.sasinariHouhou
        this.form.ifd2SasinariJyouken = newValue.sasinariJyouken
        this.form.ifd2TriggerPrice = newValue.triggerPrice
        this.form.ifd2StopOrderPriceText2 = newValue.stopOrderPriceText2
        this.form.ifd2GyakusasiHouhou = newValue.gyakusasiHouhou
        this.form.ifd2Price = newValue.price
      }
    },

    beforeCorrectPriceParam() {
      return {
        sasinariHouhou: this.form.beforeCorrectPrice.sasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.sasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.triggerPrice,
        stopOrderPriceText2: this.form.beforeCorrectPrice.stopOrderPriceText2,
        gyakusasiHouhou: this.form.beforeCorrectPrice.gyakusasiHouhou,
        price: this.form.beforeCorrectPrice.price
      }
    },

    beforeCorrectOco1PriceParam() {
      return {
        sasinariHouhou: this.form.beforeCorrectPrice.oco1SasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.oco1SasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.triggerPrice,
        stopOrderPriceText2: this.form.beforeCorrectPrice.stopOrderPriceText2,
        gyakusasiHouhou: this.form.beforeCorrectPrice.gyakusasiHouhou,
        price: this.form.beforeCorrectPrice.oco1Price
      }
    },

    beforeCorrectOco2PriceParam() {
      return {
        sasinariJyouken: this.form.beforeCorrectPrice.oco1SasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.triggerPrice,
        stopOrderPriceText2: this.form.beforeCorrectPrice.stopOrderPriceText2,
        gyakusasiHouhou: this.form.beforeCorrectPrice.gyakusasiHouhou,
        price: this.form.beforeCorrectPrice.oco2Price
      }
    },

    beforeCorrectIfd1PriceParam() {
      return {
        sasinariHouhou: this.form.beforeCorrectPrice.ifd1SasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.ifd1SasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.ifd1TriggerPrice,
        stopOrderPriceText2: this.form.beforeCorrectPrice.ifd1StopOrderPriceText2,
        gyakusasiHouhou: this.form.beforeCorrectPrice.ifd1GyakusasiHouhou,
        price: this.form.beforeCorrectPrice.ifd1Price
      }
    },

    beforeCorrectIfd2PriceParam() {
      return {
        limit: this.form.beforeCorrectPrice.ifd2Limit,
        sasinariHouhou: this.form.beforeCorrectPrice.ifd2SasinariHouhou,
        sasinariJyouken: this.form.beforeCorrectPrice.ifd2SasinariJyouken,
        triggerPrice: this.form.beforeCorrectPrice.ifd2TriggerPrice,
        stopOrderPriceText2: this.form.beforeCorrectPrice.ifd2StopOrderPriceText2,
        gyakusasiHouhou: this.form.beforeCorrectPrice.ifd2GyakusasiHouhou,
        price: this.form.beforeCorrectPrice.ifd2Price
      }
    },
    setTextDepositTypeDispPattern() {
      // 顧客共通情報.ジュニアISA契約区分=1：契約
      if (this.customerInfo.jrIsaContractType === '1') {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 1
        } else {
          return 8
        }
      } else {
        // 顧客共通情報.特定口座区分=1:特定口座(代行納付)　または　2:特定口座(確定申告)
        if (this.customerInfo.specificAccountType === '1' || this.customerInfo.specificAccountType === '2') {
          return 2
        } else {
          return 9
        }
      }
    },
    isMarketVisible() {
      // 注文種別=通常/逆指値 かつ 執行方法（通常/逆指値）≠ 逆指値 かつ SOR取扱区分＝"1" かつ 直近市場＝"0"：東証 の場合、表示
      if (this.form.orderKind === '1'&& this.form.sasinariHouhou !== '3' && this.form.sorServiceKbn === '1' && this.form.latestMarketId === '0'
        ) {
        return true
      }
      // 上記以外 項目非表示
      return false
    },
    isPeriodVisible() {
      // 注文種別 != 通常/逆指値　の場合　項目表示
      if (this.form.orderKind !== '1'){
        return true
      // 注文種別 = 通常/逆指値　かつ　執行方法（通常/逆指値）= 逆指値 の場合　項目表示
      } else if (this.form.orderKind === '1' && this.form.sasinariHouhou === '3'){
        return true
      // 上記以外
      } else {
        // 直近市場≠"0"：東証の場合　項目表示
        if (this.form.latestMarketId !== '0') {
          return true
        // 直近市場＝"0"：東証
        } else {
          // 訂正後市場＝東証　または　約定ステータス＝"0"：未出来　の場合　項目表示
          if (this.form.afterCorrectMarket === '0' || this.form.tradeStatus === '0') {
            return true
          }
        }
      }
      // 上記以外 項目非表示
      return false
    },
    isPeriodTodayVisible() {
      // 訂正後市場＝当社優先市場／SOR　かつ　約定ステータス＝"1"：一部出来　の場合　項目表示
      if (this.form.afterCorrectMarket === 'A' && this.form.tradeStatus === '1') {
        return true
      }
      // 上記以外 項目非表示
      return false
    },
  },
  methods: {
    onShow(event) {
      // データの初期化
      const data = event.dataList[0]
      this.unstripData(data)
      this.form = new IfaDomesticStockOrderCorrectInputFormModel()
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
      if (this.isMarketVisible) {
        // SOR注文区分＝"△"：SOR対象外注文　の場合 東証　をデフォルト選択
        if (this.form.sorOrderClassification === ' ') {
          this.form.afterCorrectMarket = '0'
        // 上記以外 当社優先市場／SOR　をデフォルト選択
        } else {
          this.form.afterCorrectMarket = 'A'
        }
      }
    },

    responseHandlerDomesticStockOrderCorrectInputA003(event) {
      this.stockDetailInfo = event.dataList[0]
      this.showStockDetailInfo = true
    },

    responseHandlerDomesticStockOrderCorrectInputA004(event) {
      const data = event.dataList[0]
      this.unstripData(data)
      this.form = { ...this.form, ...data }
      this.form.beforeCorrectPrice = { ...data }

      // 銘柄時価情報（国内株）の更新
      this.$refs.ifaBrandPriceInfo.updateRequest()
    },

    responseHandlerDomesticStockOrderCorrectInputA010(event) {
      this.confirmData = event.dataList[0]
      this.$nextTick(() => {
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
    domesticStockOrderCorrectCompleteFinish() {
      // ダイアログのクローズ
      // 注文状況一覧へ遷移
      this.showInputDialog = false
      this.showConfirmDialog = false
      this.showCompleteDialog = false
      this.showStockDetailInfo = false
      this.$emit('initialize-order-status-list')
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
      this.showConfirmDialog = false
      this.completeData = response.dataList[0]
      this.$nextTick(() => {
        this.$refs['ifaDomesticStockOrderCorrectComplete'].setData()
        this.showCompleteDialog = true
      })
    },

    normalStopLossOrderValidator(rule, value, callback) {
      this.normalValidator(this.priceParam, this.form.tradeCd, callback)
    },

    oco1Validator(rule, value, callback) {
      this.normalValidator(this.oco1PriceParam, this.form.tradeCd, callback)
    },

    oco2Validator(rule, value, callback) {
      this.ocoValidator(this.oco2PriceParam, this.form.tradeCd, callback)
    },

    ifd1Validator(rule, value, callback) {
      this.normalValidator(this.ifd1PriceParam, this.form.tradeCd, callback)
    },

    ifd2Validator(rule, value, callback) {
      this.normalValidator(this.ifd2PriceParam, '2', callback)
    },
    checkPrice(priceStr, buySellType) {
      const price = Number(priceStr)
      let stopLow = 9999999999
      let stopHigh = 0
      if (buySellType === '1' || buySellType === '') {
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

    normalValidator(priceParam, tradeCd, callback) {
      // 執行方法
      if (!priceParam.sasinariHouhou) {
        callback('執行方法を選択してください。')
        return
      }

      // 執行条件
      if (!priceParam.sasinariJyouken) {
        callback('執行条件を選択してください。')
        return
      }

      // 発火条件価格（逆指値）
      if (priceParam.sasinariHouhou === '3') {
        if (!priceParam.triggerPrice) {
          callback('発火条件価格（逆指値）を入力してください。')
          return
        }
      }

      // 執行方法（逆指値）
      if (priceParam.sasinariHouhou === '3' && !priceParam.gyakusasiHouhou) {
        callback('執行方法（逆指値）を入力してください。')
        return
      }

      // 注文単価
      if (
        priceParam.sasinariHouhou === '1' || // 指値
        priceParam.sasinariHouhou === '3' && priceParam.gyakusasiHouhou === '1' // 逆指値 かつ 執行方法（逆指値）=指値
      ) {
        if (!priceParam.price) {
          callback('注文単価を入力してください。')
          return
        }
      }

      // チェックOK
      callback()
    },

    ocoValidator(priceParam, tradeCd, callback) {
      // 発火条件価格（逆指値）
      if (!priceParam.triggerPrice) {
        callback('発火条件価格（逆指値）を入力してください。')
        return
      }

      // 執行方法（逆指値）
      if (!priceParam.gyakusasiHouhou) {
        callback('執行方法（逆指値）を入力してください。')
        return
      }

      // 執行条件
      if (!priceParam.sasinariJyouken) {
        callback('執行条件を入力してください。')
        return
      }

      if (priceParam.gyakusasiHouhou === '1') { // 執行方法（逆指値）＝指値
        // 注文単価
        if (!priceParam.price) {
          callback('注文単価を入力してください。')
        }
      }

      // チェックOK
      callback()
    },

    unstripData(data) {
      const unstrip = (val) => {
        if (val) {
          return val.trim()
        }

        return val
      }

      // 半角スペース 前埋め
      data.settlementDateAfterBusinessDayT2 = unstrip(data.settlementDateAfterBusinessDayT2) // 買付余力.受渡日(T+2)
      data.settlementDateAfterBusinessDayT3 = unstrip(data.settlementDateAfterBusinessDayT3) // 買付余力.受渡日(T+3)
      data.yenBuyingPowerGeneralAccountT2 = unstrip(data.yenBuyingPowerGeneralAccountT2) // 買付余力.総合口座（T+2）
      data.yenBuyingPowerGeneralAccountT3 = unstrip(data.yenBuyingPowerGeneralAccountT3) // 買付余力.総合口座（T+3）
      data.yenBuyingPowerJrNisaT2 = unstrip(data.yenBuyingPowerJrNisaT2) // 買付余力.JrNISA口座（T+2）
      data.yenBuyingPowerJrNisaT3 = unstrip(data.yenBuyingPowerJrNisaT3) // 買付余力.JrNISA口座（T+3）
      data.nisaInvestableQuotaMessage = unstrip(data.nisaInvestableQuotaMessage) // 買付余力.NISA買付可能枠
      data.orderQuantity = unstrip(data.orderQuantity) // 注文数量
      data.unTradeQuantity = unstrip(data.unTradeQuantity) // 未約定数量
      data.triggerPrice = unstrip(data.triggerPrice) // 通常/逆指値.発火条件価格（逆指値）
      data.price = unstrip(data.price) // 通常/逆指値.注文単価
      data.oco1Price = unstrip(data.oco1Price) // OCO1.注文単価
      data.oco2StopOrderPriceText2 = unstrip(data.oco2StopOrderPriceText2) // OCO2.発火条件価格（逆指値）ゾーン
      data.oco2Price = unstrip(data.oco2Price) // OCO2.注文単価
      data.ifd1TriggerPrice = unstrip(data.ifd1TriggerPrice) // IFD1.発火条件価格（逆指値）
      data.ifd1Price = unstrip(data.ifd1Price) // IFD1.注文単価
      data.ifd2TriggerPrice = unstrip(data.ifd2TriggerPrice) // IFD2.発火条件価格（逆指値）
      data.ifd2Price = unstrip(data.ifd2Price) // IFD2.注文単価

      // 全角スペース 後ろ埋め
      data.brandName = unstrip(data.brandName) // 銘柄名
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
</style>
