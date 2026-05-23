<template>
  <!-- 信用新規注文完了ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    :style="{ 'background-color': refFormModel.tradeCd === '3' ? '#fef0f0' : '#ecf5ff' }"
    :title="refFormModel.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="onOpen"
  >
    <!-- メッセージエリア -->
    <el-row style="margin-top: 40px;"></el-row>
    <ifa-message-area
      :main-messages="[refFormModel.finishMassage]"
    ></ifa-message-area>
    <!-- 口座エリア -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(customerInfo.butenCode) + '-' + $_out($_zeroPadding(customerInfo.accountNumber,7)) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo.corporationType == '1'"><OfficeBuilding></OfficeBuilding></el-icon>
          <el-icon v-else><Avatar></Avatar></el-icon>
        </span>
        <span>{{ customerInfo.customerNameKanji }}（{{ customerInfo.customerNameKana }}）</span>
        <ifa-notice-info
          :notice-info-level="customerInfoA().noticeInfoLevel"
          :customer-code="customerInfoA().customerCode"
          :buten-code="customerInfoA().butenCode"
          :account-number="customerInfoA().accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <!-- 注文受付内容 基本系（注文種別=通常/逆指値）-->
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
            <span>{{ $_out(refFormModel.areaHeadings) }}</span>
          </el-row>
          <hr>
          
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              class="_bold_black_m"
              :span="9"
            >
              <span>[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="10"
              style="font-size: 16px;"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(refFormModel.brandCode) }}]&nbsp;{{ $_out(refFormModel.brandName) }}</span>
            </el-col>
            <el-col
              v-if="refFormModel.tradeNoticeInfoBrandMsg && refFormModel.tradeNoticeInfoBrandMsg.length > 0"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red;  vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                ref="tradeLimitRef"
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
                :disp-name="'取引注意情報'"
              ></ifa-link>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文種別</span>
            </el-col>
            <el-col
              style="font-weight: bold"
              :span="15"
            >
              <span v-if="refFormModel.orderKind =='1' && refFormModel.sasinariHouhou == '3'">逆指値注文</span>
              <span v-else>{{ $_out($_getCodeValue('ORDER_CLASS', 2, refFormModel.orderKind )) }}</span>
            </el-col>
          </el-row>
          <!-- 注文種別＝IFD -->
          <el-row
            v-if="refFormModel.orderKind =='3' || refFormModel.orderKind =='4'"
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 20%;"
          >
            <el-col :span="12"><span>IFD1</span></el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引種別</span>
            </el-col>
            <el-col :span="15">
              <span
                v-if="refFormModel.tradeCd == '3'"
                class="font-color__plus bold"
              >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, refFormModel.tradeCd )) }}
              </span>
              <span
                v-else
                class="font-color__minus bold"
              >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, refFormModel.tradeCd )) }}
              </span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>市場</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, refFormModel.market )) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注数量</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_addComma(refFormModel.orderQuantity)) }}株</span>
            </el-col>
          </el-row>
          <!-- 通常/逆指値 -->
          <el-row
            v-if="refFormModel.orderKind == '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, refFormModel.sasinariHouhou )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getJyoukenPrice(refFormModel.sasinariHouhou, refFormModel.sasinariJyouken, refFormModel.price, refFormModel.triggerPrice) }}
                </span>
              </el-col>
            </el-col>
          </el-row>
          <!-- OCO -->
          <el-row
            v-if="refFormModel.orderKind == '2'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格／OCO1</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, refFormModel.oco1OrderExecuteMethodText )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getOco1JyoukenPrice(refFormModel.oco1SasinariJyouken, refFormModel.oco1Price) }}
                </span>
              </el-col>
            </el-col>
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>条件／OCO2</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ getOco2JyoukenPrice(refFormModel.oco2GyakusasiHouhou, refFormModel.oco2GyakusasiJyouken, refFormModel.oco2Price, refFormModel.oco2TriggerPrice) }}
              </span>
            </el-col>
          </el-row>
          <!-- IFD-IFD1 -->
          <el-row
            v-if="refFormModel.orderKind =='3' || refFormModel.orderKind =='4'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>価格</span>
            </el-col>
            <el-col :span="15">
              <span>
                {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, refFormModel.ifd1SasinariHouhou )) }}
              </span>
              <el-col :span="16">
                <span>
                  {{ getIfd1JyoukenPrice(refFormModel.ifd1SasinariHouhou, refFormModel.ifd1SasinariJyouken, refFormModel.ifd1Price, refFormModel.ifd1TriggerPrice) }}
                </span>
              </el-col>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注文期間</span>
            </el-col>
            <el-col :span="15">
              <span>{{ refFormModel.periodRadio === '0' ? "当日中" : $_getFormattedDateValue(refFormModel.limit) + "まで" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>信用取引区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_getCodeValue('PAYMENT_DEADLINE', 1, refFormModel.marginTradeTypeText ) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('PRE_CONTRACT_DOC_CODE', 1, customerInfo.customerAttribute)) }}（電話手数料）</span>
            </el-col>
          </el-row>
          <!-- 注文種別=IFDの場合 -->
          <el-row
            v-if="refFormModel.orderKind =='3' || refFormModel.orderKind =='4'"
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 20%;"
          >
            <el-col :span="12"><span>IFD2</span></el-col>
          </el-row>
          <div v-if="refFormModel.orderKind =='3' || refFormModel.orderKind =='4'">
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>取引種別</span>
              </el-col>
              <el-col :span="15">
                <span
                  v-if="refFormModel.tradeCd == '3'"
                >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, '6' )) }}
                </span>
                <span
                  v-else
                >{{ $_out($_getCodeValue('DOMESTIC_STOCK_TRADE_CLASS', 1, '5' )) }}
                </span>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span v-if="refFormModel.orderKind == '4'">価格／OCO1</span>
                <span v-else-if="refFormModel.orderKind == '3'">価格</span>
              </el-col>
              <!-- IFDOCO -->
              <el-col
                v-if="refFormModel.orderKind == '4'"
                :span="15"
              >
                <span>
                  {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, refFormModel.oco1OrderExecuteMethodText )) }}
                </span>
                <el-col :span="16">
                  <span>
                    {{ getOco1JyoukenPrice(refFormModel.oco1SasinariJyouken, refFormModel.oco1Price) }}
                  </span>
                </el-col>
              </el-col>
              <el-col
                v-if="refFormModel.orderKind == '4'"
                :span="9"
                class="_bold_black_m"
              >
                <span>条件／OCO2</span>
              </el-col>
              <el-col
                v-if="refFormModel.orderKind == '4'"
                :span="15"
              >
                <span>
                  {{ getOco2JyoukenPrice(refFormModel.oco2GyakusasiHouhou, refFormModel.oco2GyakusasiJyouken, refFormModel.oco2Price, refFormModel.oco2TriggerPrice) }}
                </span>
              </el-col>
              <!-- IFD-IFD2 -->
              <el-col
                v-if="refFormModel.orderKind == '3'"
                :span="15"
              >
                <span>
                  {{ $_out($_getCodeValue('EXECUTE_METHOD', 1, refFormModel.ifd2SasinariHouhou )) }}
                </span>
                <el-col :span="16">
                  <span>
                    {{ getIfd2JyoukenPrice(refFormModel.ifd2SasinariHouhou, refFormModel.ifd2SasinariJyouken, refFormModel.ifd2Price, refFormModel.ifd2TriggerPrice) }}
                  </span>
                </el-col>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col
                :span="9"
                class="_bold_black_m"
              >
                <span>注文期間</span>
              </el-col>
              <el-col :span="15">
                <span>{{ refFormModel.ifd2PeriodDate === '0' ? '当日中' : $_out($_getFormattedDateValue(refFormModel.ifd2Limit)) + "まで" }}</span>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
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
            <span>その他注文内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>見積単価</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_withCommaZeroPadding(refFormModel.quoteUnitPrice, 1)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_addComma(refFormModel.contractAmount)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>手数料/諸費用</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_addComma(refFormModel.charge)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>消費税</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_addComma(refFormModel.consumptionTax)) }}円</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.tradeCd === '3'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>適用金利</span>
            </el-col>
            <el-col :span="15">
              <span>{{ "年利" + $_out($_withCommaZeroPadding(refFormModel.applicableInterestRate)) + "％" }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.tradeCd === '4'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>適用貸株料</span>
            </el-col>
            <el-col :span="15">
              <span>{{ "年利" + $_out($_withCommaZeroPadding(refFormModel.applicableStockLendingFees, 2)) + "％" }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>精算金額</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_addComma(refFormModel.settlementAmount)) }}円</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>約定予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateValue(refFormModel.contractDate)) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受渡予定日</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateValue(refFormModel.deliveryDate)) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注日時</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getFormattedDateTimeValue(refFormModel.orderDayTime, 'datetime12')) }}</span>
            </el-col>
          </el-row>
          <!-- 勧誘区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('INVITATION_TYPE', 1, refFormModel.kanyuKbn )) }}</span>
            </el-col>
          </el-row>
          <!-- 受注方法 -->
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('ORDER_METHOD_TYPE', 1, refFormModel.receiveOrderTypeName )) }}</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>確認項目</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('INSIDER_CONFIRM', 2, refFormModel.checkInsider )) }}</span>
              <el-col
                v-if="refFormModel.market == 'A'"
                :span="16"
              >
                <span>{{ $_out($_getCodeValue('SOR_CONFIRM', 2, refFormModel.checkSor )) }}</span>
              </el-col>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>EC受注番号</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out(refFormModel.ecOrderNo) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- アラート内容確認 -->
    <el-row v-if="messagesIsVisible">
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          :class="formModel.tradeCd === '3' ? 'buy-background-color_card' : 'sell-background-color_card'"
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>

          <el-row
            v-if="refFormModel.tradingCautionInformation === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, refFormModel.tradingCautionInformation )) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.noteInfoCheckbox === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('NOTICE_INFO_CONFIRM', 1, refFormModel.noteInfoCheckbox )) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.noteLimitCheck === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, refFormModel.noteLimitCheck )) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.insiderErrorConfirmationCheckbox === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>内部者取引確認（知る前契約/計画の確認含む）</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('INSIDER_TRADE_CONFIRM', 1, refFormModel.insiderErrorConfirmationCheckbox )) }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="refFormModel.shortSellingRegulationsCheckbox === '1'"
            class="dotted_border"
          >
            <el-col
              :span="9"
              class="_bold_black_m"
            >
              <span>空売り規制取引</span>
            </el-col>
            <el-col :span="15">
              <span>{{ $_out($_getCodeValue('SHORT_SELLING_REGULATION_CONFIRM', 2, refFormModel.shortSellingRegulationsCheckbox )) }}</span>
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
          text="注文状況一覧へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="handleClick"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaMarginNewOrderCompleteFormModel } from './js/IfaMarginNewOrderCompleteFormModel'
import IfaLink from '@/components/Link/IfaLink'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaLink,
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    refFormModel: { type: Object, required: true },
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
      formModel: new IfaMarginNewOrderCompleteFormModel()
    }
  },
  computed: {
    getUrlObject() {
      if (!this.refFormModel.brandCode) {
        return null
      }
      return this.refFormModel.brandCode.substring(0, 4)
    },
    infoIcon() {
      return this.customerInfo.noticeInfoLevel === '3' ? 'alert-icon' : 'notice-icon'
    },
    messagesIsVisible() {
      if (this.refFormModel.noteInfoCheckbox !== '' ||
        this.refFormModel.noteLimitCheck !== '' ||
        this.refFormModel.insiderErrorConfirmationCheckbox　!== '' ||
        this.refFormModel.tradingCautionInformation !== '' ||
        this.refFormModel.shortSellingRegulationsCheckbox !== '') {
        return true
      } else {
        return false
      }
    }
  },
  methods: {
    onOpen() {
      // 取引注意情報リンクの初期化
      this.$nextTick(() => {
        this.$refs['tradeLimitRef']?.trigger()
      })
    },
    // 価格に表示する内容を生成する
    getPrice(jyouken, price) {
      if (jyouken === ' ') {
        return '/' + this.$_out(this.$_withCommaZeroPadding(price, 1)) + '円'
      } else if (jyouken !== ' ') {
        return ''
      }
    },
    getJyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_addComma(price) + '円'
        } else {
          return this.$_addComma(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_addComma(trigger) + '円' + (this.refFormModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.refFormModel.gyakusasiHouhou === '1'
          ? (jyouken === ' ' ? this.$_addComma(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_addComma(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    getOco1JyoukenPrice(jyouken, price) {
      if (jyouken !== ' ') {
        return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_addComma(price) + '円'
      } else {
        return this.$_addComma(price) + '円'
      }
    },
    getOco2JyoukenPrice(houhou, jyouken, price, trigger) {
      return '現在値が' + this.$_addComma(trigger) + '円' +
      (this.refFormModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
      (this.refFormModel.oco1SasinariJyouken === ' ' ? 'OCO1(指値)を' : 'OCO1(' + this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.refFormModel.oco1SasinariJyouken) + ')を') +
      (houhou === '1' ? (jyouken === ' ' ? this.$_addComma(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_addComma(price) + '円') : '') +
      (houhou === '2' ? '成行' : '') + 'に訂正'
    },
    getIfd1JyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_addComma(price) + '円'
        } else {
          return this.$_addComma(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_addComma(trigger) + '円' + (this.refFormModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.refFormModel.ifd1GyakusasiHouhou === '1'
          ? (jyouken === ' ' ? this.$_addComma(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_addComma(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    getIfd2JyoukenPrice(houhou, jyouken, price, trigger) {
      if (houhou === '1') { // 通常/逆指値.執行方法=指値
        if (jyouken !== ' ') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + '/' + this.$_addComma(price) + '円'
        } else {
          return this.$_addComma(price) + '円'
        }
      } else if (houhou === '2') { // 通常/逆指値.執行方法=成行
        if (jyouken !== 'N') {
          return this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, jyouken)
        }
      } else if (houhou === '3') { // 通常/逆指値.執行方法=逆指値
        return '現在値が' + this.$_addComma(trigger) + '円' + (this.refFormModel.tradeCd === '3' ? '以上' : '以下') + 'になった時点で' +
        (this.refFormModel.ifd2OrderExecuteMethodText === '1'
          ? (jyouken === ' ' ? this.$_addComma(price) + '円' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, jyouken) + this.$_addComma(price) + '円')
          : (jyouken === ' ' ? '' : this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, jyouken))) + 'で執行'
      }
    },
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    handleClick() {
      this.$emit('move-to-order-list')
    },
    customerInfoA() {
      return this.$store.getters.customerInfo
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.error-message {
  margin: 0.5rem 0;
  padding: 0 8rem;
  color: red;
  font-size: 18px;
  font-weight: bold;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
}
.finish-message {
  margin: 0.5rem 0;
  padding-left: 2rem;
  color: red;
  font-size: 14px;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
.center-label {
  text-align: center;
}
</style>
