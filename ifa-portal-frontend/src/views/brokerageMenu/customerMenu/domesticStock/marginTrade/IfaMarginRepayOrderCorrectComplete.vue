<template>
  <el-dialog
    v-model="vmIsVisible"
    class="margin_new_order_comp"
    :title="form.title.name"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
    width="1200px"
    :style="dialogStyle"
    @open="onShow"
  >
    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['下記の内容で訂正を受け付けました。']"
    ></ifa-message-area>
    <!-- 口座エリア -->
    <el-row
      style="font-weight: bold; color: black;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row
      style="padding-top: 0.3rem;"
      class="_bold_black_l"
    >
      <el-col
        :offset="1"
        :span="22"
        style="font-size: 20px;"
        class="_bold_black_l"
      >
        <el-icon><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        <span style="margin-right: 1rem;">{{ $_out(customerName) }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <!-- 注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding-top: 0.7rem;"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>ご注文内容</span>
          </el-row>
          <el-row class="_bold_black_m">
            <el-col
              :span="5"
              :offset="7"
            >
              <span>訂正前</span>
            </el-col>
            <el-col :span="3">
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="7"
              :offset="1"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
            </el-col>
            <el-col
              :span="12"
              :offset="1"
            >
              <span style="overflow-wrap: break-word;">[{{ $_out(form.request.brandCode) }}]&nbsp;{{ $_out(form.request.brandName) }}</span>
            </el-col>
            <el-col
              v-if="form.request.tradeNoticeInfoBrandMsg"
              :span="5"
              style="text-align: center;"
            >
              <el-icon
                style="color: red; vertical-align: middle;"
              ><WarningFilled></WarningFilled></el-icon>
              <ifa-link
                :url-id="16"
                :url-object="{ brandCode: getUrlObject }"
                :disp-name="'取引注意情報'"
              ></ifa-link>
            </el-col>
          </el-row>

          
          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">注文種別</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <span v-if="form.request.correctBeforePriceSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.request.orderKind"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <span v-if="form.request.correctBeforePriceSasinariHouhou === '3'">逆指値注文</span>
              <ifa-text
                v-else
                code-list-id="ORDER_CLASS"
                :disp-pattern="2"
                :code-key="form.request.orderKind"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">取引種別</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <ifa-text
                :style="fontColor"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="form.request.tradeCd"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
              style="font-weight: bold;"
            >
              <ifa-text
                :style="fontColor"
                :code-list-id="'DOMESTIC_STOCK_TRADE_CLASS'"
                :disp-pattern="1"
                :code-key="form.request.tradeCd"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">市場</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'SELECT_MARKET'"
                :disp-pattern="1"
                :code-key="form.request.orderMarket"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'SELECT_MARKET'"
                :disp-pattern="1"
                :code-key="form.request.afterCorrecMarket"
              ></ifa-text>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">未約定数量</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.unTradeQuantity ? `${$_withCommaInteger(form.request.unTradeQuantity)}株` : '-' }} </span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.unTradeQuantity ? `${$_withCommaInteger(form.request.unTradeQuantity)}株` : '-' }} </span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受注数量</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.quantity ? `${$_withCommaInteger(form.request.quantity)}株` : '-' }} </span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>-</span>
            </el-col>
          </el-row>

          <!-- 通常/逆指値 -->
          <el-row
            v-if="form.request.orderKind === '1'"
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">価格</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="form.request.correctBeforePriceSasinariHouhou"
              ></ifa-text><br v-if="getBeforePrice() !== ''">
              <span>{{ getBeforePrice() }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'EXECUTE_METHOD'"
                :disp-pattern="1"
                :code-key="form.request.sasinariHouhou"
              ></ifa-text><br v-if="getPrice() !== ''">
              <span>{{ getPrice() }}</span>
            </el-col>
          </el-row>
          <!-- OCO -->
          <div v-if="form.request.orderKind === '2'">
            <el-row
              v-if="form.request.workingStatus === 'false'"
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO1</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  :code-list-id="'EXECUTE_METHOD'"
                  :disp-pattern="1"
                  :code-key="form.request.correctBeforePriceOco1SasinariHouhou"
                ></ifa-text><br>
                <span>{{ getOCO1BeforePrice() }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <ifa-text
                  :code-list-id="'EXECUTE_METHOD'"
                  :disp-pattern="1"
                  :code-key="form.request.oco1SasinariHouhou"
                ></ifa-text><br>
                <span>{{ getOCO1Price() }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="6">
                <span class="_bold_black_m">価格／OCO2</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ getOCO2BeforePrice() }}</span>
              </el-col>
              <el-col
                :span="8"
                :offset="1"
              >
                <span>{{ getOCO2Price() }}</span>
              </el-col>
            </el-row>
          </div>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">注文期間</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.period ? $_out($_getFormattedDateValue(form.request.period)) : "----/--/--" }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ yukoKigenData.yukoKigenChange == '1' ? $_getFormattedDateValue(yukoKigenData.yukoKigen) : $_out($_getFormattedDateValue(form.request.period)) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">信用取引区分</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.marginTradeTypeText }}</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <span>{{ form.request.marginTradeTypeText }}</span>
            </el-col>
          </el-row>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">手数料区分</span>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'COMM_TYPE'"
                :disp-pattern="2"
                :code-key="form.request.tesuuryouKbn"
              ></ifa-text>
            </el-col>
            <el-col
              :span="8"
              :offset="1"
            >
              <ifa-text
                :code-list-id="'COMM_TYPE'"
                :disp-pattern="2"
                :code-key="form.request.tesuuryouKbn"
              ></ifa-text>
            </el-col>
          </el-row>
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
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>その他注文内容</span>
          </el-row>
          <hr>

          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">訂正後建玉余力</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ form.positionPower && form.positionPower > 0 ? `${$_withCommaInteger(form.positionPower)}円` : '-' }} </span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">約定予定日</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateValue(form.contractDate)) }}</span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受渡予定日</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateValue(form.deliveryDate)) }}</span>
            </el-col>
          </el-row>

          <el-row class="dotted_border">
            <el-col :span="6">
              <span class="_bold_black_m">受注日時</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime, 'datetime12')) }}</span>
            </el-col>
          </el-row>

          <!-- 勧誘区分 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">勧誘区分</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="INVITATION_TYPE"
                :disp-pattern="1"
                :code-key="form.request.kanyuKbn"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 受注方法 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">受注方法</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="ORDER_METHOD_TYPE"
                :disp-pattern="1"
                :code-key="form.request.orderMethod"
              ></ifa-text>
            </el-col>
          </el-row>

          <!-- 確認項目 -->
          <el-row
            class="dotted_border"
          >
            <el-col :span="6">
              <span class="_bold_black_m">確認項目</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <ifa-text
                code-list-id="INSIDER_CONFIRM"
                :disp-pattern="3"
                code-key="1"
              ></ifa-text>
              <br v-if="form.request.afterCorrecMarket === 'A'">
              <ifa-text
                v-if="form.request.afterCorrecMarket === 'A'"
                code-list-id="SOR_CONFIRM"
                :disp-pattern="2"
                :code-key="form.request.checkSor"
              ></ifa-text>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>
    <!-- アラート内容確認 -->
    <el-row
      v-if="form.request.tradeNoticeInfoBrandMsg ||
        form.request.noticeInfoAlert ||
        form.request.noticeAlert"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card class="box-card"
                 style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.2rem; padding-left: 1rem"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <!-- 取引注意情報（銘柄）確認 -->
          <el-row
            v-if="form.request.tradeNoticeInfoBrandMsg"
            class="dotted_border"
            style="padding-left: 0.5rem;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="padding-bottom: 0;"
            >
              <span>取引注意情報の説明</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_getCodeValue('TRADE_NOTICE_INFO_EXPLAIN', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="form.request.noticeInfoAlert"
            class="dotted_border"
            style="padding-left: 0.5rem;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="padding-bottom: 0;"
            >
              <span>注意情報の確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_getCodeValue('NOTICE_INFO_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
          <el-row
            v-if="form.request.noticeAlert"
            class="dotted_border"
            style="padding-left: 0.5rem;"
          >
            <el-col
              :span="6"
              class="_bold_black_m"
              style="padding-bottom: 0;"
            >
              <span>重要なお知らせの確認</span>
            </el-col>
            <el-col
              :span="16"
              :offset="1"
            >
              <span>{{ $_getCodeValue('IMPORTANT_NOTIFICATION_CONFIRM', 1, '1') }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文状況一覧へボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="margin-top: 1rem"
      >
        <ifa-button
          id="btnToOrderStatusList"
          text="注文状況一覧へ"
          color="primary"
          action-type="originalAction"
          style="padding-left: 4px;"
          @app-action-handler="handleMoveToUnexecutedOrderList"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaMarginRepayOrderCorrectCompleteFormModel } from './js/IfaMarginRepayOrderCorrectCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true },
    yukoKigenData: { type: Object, required: true }
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
      form: new IfaMarginRepayOrderCorrectCompleteFormModel(),
      dialogStyle: '',
      fontColor: '',
      yukoKigenInfo: {
        yukoKigenChange: '',
        yukoKigen: ''
      }
    }
  },
  computed: {
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    getUrlObject() {
      if (!this.form.request.brandCode) {
        return null
      }
      return this.form.request.brandCode.substring(0, 4)
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
      Object.assign(this.yukoKigenInfo, this.yukoKigenData)
      if (this.form.request.tradeCd === '5') {
        // 信用返済買
        this.dialogStyle = 'backgroundColor: #fef0f0;'
        this.fontColor = 'color: #E5004C'
      } else {
        // 信用返済売
        this.dialogStyle = 'backgroundColor: #ecf5ff;'
        this.fontColor = 'color: #00847F'
      }
    },
    getPrice() {
      if (this.form.request.sasinariHouhou === '1') {
        // 指値
        const str = this.form.request.price ? this.$_withCommaNoneZeroPadding(this.form.request.price) + '円' : '-'
        if (this.form.request.sasinariJyouken !== ' ') {
          // 通常/逆指値.執行条件≠条件なし　の場合
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken)) +
               '/' + str
        } else {
          return str
        }
      } else if (this.form.request.sasinariHouhou === '2') {
        // 成行
        if (this.form.request.sasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.request.sasinariJyouken))
        }
      } else if (this.form.request.sasinariHouhou === '3') {
        // 逆指値
        const triggerPrice = this.form.request.triggerPrice ? this.$_withCommaNoneZeroPadding(this.form.request.triggerPrice) + '円' : '-'
        let strTriggerPrice = '現在値が' + triggerPrice
        switch (this.form.request.tradeCd) {
          // 取引種別=信用返済買　の場合
          case '5':
            strTriggerPrice += '以上になった時点で'
            break
          // 取引種別=信用返済売　の場合
          case '6':
            strTriggerPrice += '以下になった時点で'
            break
        }
        if (this.form.request.gyakusasiHouhou === '1') {
          // 執行方法（逆指値）＝指値　の場合
          const strPrice = this.form.request.price ? this.$_withCommaNoneZeroPadding(this.form.request.price) + '円' : '-'
          if (this.form.request.sasinariJyouken !== ' ') {
            // 通常/逆指値.執行条件≠条件なし　の場合
            return strTriggerPrice + this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.sasinariJyouken) + strPrice + 'で執行'
          } else {
            return strTriggerPrice + strPrice + 'で執行'
          }
        } else if (this.form.request.gyakusasiHouhou === '2') {
          // 執行方法（逆指値）が成行の時
          return strTriggerPrice + this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.request.sasinariJyouken) + 'で執行'
        }
        return strTriggerPrice + 'で執行'
      }
      return ''
    },
    getBeforePrice() {
      // 訂正前価格.通常/逆指値.執行方法 : 指値
      if (this.form.request.correctBeforePriceSasinariHouhou === '1') {
        if (this.form.request.correctBeforePriceSasinariJyouken !== ' ') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceSasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円'
        }
        return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円'
      }
      // 訂正前価格.通常/逆指値.執行方法 : 成行
      if (this.form.request.correctBeforePriceSasinariHouhou === '2') {
        if (this.form.request.correctBeforePriceSasinariJyouken !== 'N') {
          return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.form.request.correctBeforePriceSasinariJyouken))
        }
      }
      // 訂正前価格.通常/逆指値.執行方法 : 逆指値
      if (this.form.request.correctBeforePriceSasinariHouhou === '3') {
        let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceTriggerPrice)) + '円'
        if (this.form.request.correctBeforePriceStopOrderPriceText2 === ' ') {
          str += (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点で'
        } else {
          str += this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.form.request.correctBeforePriceStopOrderPriceText2) + 'になった時点で'
        }
        // 訂正前価格.通常/逆指値.執行方法（逆指値）: 指値
        if (this.form.request.correctBeforePriceGyakusasiHouhou === '1') {
          if (this.form.request.correctBeforePriceSasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceSasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePricePrice)) + '円で執行'

        // 訂正前価格.通常/逆指値.執行方法（逆指値）: 成行
        } else if (this.form.request.correctBeforePriceGyakusasiHouhou === '2') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.form.request.sasinariJyouken)) + 'で執行'
        }
        return str
      }
      return ''
    },
    getOCO1Price() {
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco1Price)) + '円'
    },
    getOCO1BeforePrice() {
      if (this.form.request.correctBeforePriceOco1SasinariJyouken !== ' ') {
        return this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco1SasinariJyouken)) +
               '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco1Price)) + '円'
      }
      return this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco1Price)) + '円'
    },
    getOCO2Price() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2TriggerPrice)) + '円' +
                  (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でOCO1'
      if (this.form.request.oco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // OCO2.執行方法（逆指値）: 指値
      if (this.form.request.oco2GyakusasiHouhou === '1') {
        // OCO2.執行条件（逆指値）：≠条件なし
        if (this.form.request.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.oco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.oco2Price)) + '円に訂正'
        }
        // OCO2.執行方法（逆指値）: 成行
      } else if (this.form.request.oco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    getOCO2BeforePrice() {
      let str = '現在値が' + this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2TriggerPrice)) + '円'
      if (this.form.request.correctBeforePriceOco2StopOrderPriceText2 === ' ') {
        str += (this.form.request.tradeCd === '5' ? '以上' : '以下') + 'になった時点でになった時点でOCO1'
      } else {
        str += this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.form.request.correctBeforePriceOco2StopOrderPriceText2) + 'になった時点でになった時点でOCO1'
      }
      if (this.form.request.correctBeforePriceOco1SasinariJyouken !== ' ') {
        str += '（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco1SasinariJyouken)) + '）を'
      } else {
        str += '（指値）を'
      }
      // 訂正前価格.OCO2.執行方法（逆指値）: 指値
      if (this.form.request.correctBeforePriceOco2GyakusasiHouhou === '1') {
        // 訂正前価格.OCO2.執行条件（逆指値）：≠条件なし
        if (this.form.request.correctBeforePriceOco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.request.correctBeforePriceOco2GyakusasiJyouken)) + '/' +
          this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2Price)) + '円に訂正'
        } else {
          str += this.$_out(this.$_withCommaNoneZeroPadding(this.form.request.correctBeforePriceOco2Price)) + '円に訂正'
        }
        // 訂正前価格.OCO2.執行方法（逆指値）: 成行
      } else if (this.form.request.correctBeforePriceOco2GyakusasiHouhou === '2') {
        str += '成行に訂正'
      }
      return str
    },
    // 閉じるボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    handleMoveToUnexecutedOrderList() {
      this.$emit('move-to-order-list')
    }
  }
}
</script>

<style lang="scss">
@import '@/styles/orderStatusList.scss';
.domestic_stock_order_correct {
  .el-card__body {
    padding: 5px !important;
  }
}
.margin_new_order_comp {
	.el-dialog__title{
	font-weight: bold;
	}
}
  .arrow {
      font-weight: 700;
      font-size: 22px;
      margin-top: -2px;
      -webkit-transform: scaleX(1.4);
      transform: scaleX(1.4);
      color: #000;
  }
  .el-icon-right {
  font-family: element-icons!important;
  font-style: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  vertical-align: baseline;
  display: inline-block;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  }
</style>
<style lang="scss" scoped>
:deep(.el-text){
  font-size: 16px;
}
</style>
