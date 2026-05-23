<template>
  <!-- SUB0202_0212-03_1_信用新規注文取消確認 -->
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.title.name"
      :show-close="false"
      :center="true"
      :before-close="backA003"
      :close-on-click-modal="false"
      :class="classObject.classObjBgColor"
      width="1200px"
      @open="onShow"
    >
      <el-row>
        <el-col
          :offset="1"
          :span="22"
          style="text-align: right;"
        >
          <ifa-button
            id="btnBack"
            text="戻る"
            style="padding-right: 0;"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="backA003"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- ヘッダ -->
      <!-- エラー/警告表示 -->
      <ifa-message-area
        :main-messages="['取消はまだ完了していません。画面下の注文取消ボタンを押下してください。']"
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
          <el-icon style="position: relative; top: 3px;">
            <component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
          <span>{{ $_out(customerName) }}</span>
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
            <!--見出し、注文種別、取引種別、市場、数量は共通して表示-->
            <el-row>
              <el-row
                class="_bold_black_m"
                style="padding-top: 0.2rem; padding-left: 1rem;"
              >
                <span>ご注文内容（復唱項目）</span>
                <!--IFD、IFDOCOの時はラベル名変更-->
                <span v-if="form.orderKind === '3' || form.orderKind === '4'">：IFD1</span>
              </el-row>
              <hr style="width: 100%">

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
                </el-col>
                <el-co :span="16">
                  <span style="overflow-wrap: break-word;">[{{ $_out(form.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
                </el-co>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文種別</span>
                </el-col>
                <el-col
                  :span="16"
                  style="font-weight: bold"
                >
                  <span v-if="form.sasinariHouhou === '3'">逆指値注文</span>
                  <span v-else>{{ $_out($_getCodeValue('ORDER_CLASS', 2, form.orderKind)) }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">取引種別</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    :class="classObject.classObjFontColor"
                    code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                    :disp-pattern="1"
                    :code-key="form.tradeCd"
                  ></ifa-text>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">市場</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out($_getCodeValue('SELECT_MARKET', 1, form.market)) }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">信用取引区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out(form.marginTradeTypeTextCalculation) }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">数量</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ form.unTradeQuantity ? `${$_out($_withCommaInteger(form.unTradeQuantity))}株` : '-' }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--通常/逆指値、IFD、IFDOCOの時価格欄は同じ構成-->
            <el-row
              v-if="form.orderKind === '1' || form.orderKind === '3' || form.orderKind === '4'"
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span
                  v-if="form.orderKind === '1'"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="form.sasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(priceParam) }}</span>
                <span
                  v-if="(form.orderKind === '3' || form.orderKind === '4')"
                  style="white-space: pre-wrap;"
                >
                  <ifa-text
                    code-list-id="EXECUTE_METHOD"
                    :disp-pattern="1"
                    :code-key="form.ifd1SasinariHouhou"
                  ></ifa-text>
                  {{ getPrice(ifd1PriceParam) }}</span>
              </el-col>
            </el-row>

            <!-- OCOの時価格欄-->
            <el-row v-if="form.orderKind === '2'">
              <el-row
                v-if="form.workingStatus === 'false'"
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;">{{ getOco1Price() }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price(form.tradeCd, form.oco1SasinariJyouken) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分、受注日時は共通して表示-->
            <el-row>
              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文期間</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out($_getFormattedDateValue(form.period)) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">手数料区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    code-list-id="PRE_CONTRACT_DOC_CODE"
                    :disp-pattern="1"
                    :code-key="customerInfo.customerAttribute"
                  ></ifa-text>
                  （電話手数料）
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">受注日時</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime, 'datetime12')) }}</span>
                </el-col>
              </el-row>
            </el-row>

          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <div style="margin-bottom: 0.5rem;"></div>
      </el-row>

      <!-- 注文内容 IFD2（注文種別が IFD また IFDOCO の時のみ表示）-->
      <el-row v-if="form.orderKind == '3' || form.orderKind == '4'">
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
              style="padding-top: 0.2rem; padding-left: 1rem;"
            >
              <span>ご注文内容（復唱項目）：IFD2</span>
            </el-row>
            <hr>

            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">[銘柄コード]&nbsp;銘柄名</span>
              </el-col>
              <el-col :span="16">
                <span style="overflow-wrap: break-word;">[{{ $_out(form.brandCode) }}]&nbsp;{{ $_out(form.brandName) }}</span>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">注文種別</span>
              </el-col>
              <el-col
                :span="16"
                style="font-weight: bold"
              >
                <span v-if="form.sasinariHouhou === '3'">逆指値注文</span>
                <ifa-text
                  v-else
                  code-list-id="ORDER_CLASS"
                  :disp-pattern="2"
                  :code-key="form.orderKind"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">取引種別</span>
              </el-col>
              <el-col
                :span="16"
              >
                <!-- IFD1の取引種別=信用新規買:返済売, IFD1の取引種別=信用新規売:返済買 -->
                <ifa-text
                  :class="form.tradeCd === '3' ? 'font-color__minus bold' : 'font-color__plus bold'"
                  code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
                  :disp-pattern="1"
                  :code-key="form.tradeCd === '3' ? '6' : '5'"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">市場</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="SELECT_MARKET"
                  :disp-pattern="1"
                  :code-key="form.market"
                ></ifa-text>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">信用取引区分</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ $_out(form.marginTradeTypeTextCalculation) }}</span>
              </el-col>
            </el-row>

            <el-row class="dotted_border">
              <el-col :span="8">
                <span class="_bold_black_m">数量</span>
              </el-col>
              <el-col
                :span="16"
              >
                <span>{{ form.unTradeQuantity ? `${$_out($_withCommaInteger(form.unTradeQuantity))}株` : '-' }}</span>
              </el-col>
            </el-row>

            <!--IFDの時価格欄-->
            <el-row
              v-if="form.orderKind == '3'"
              class="dotted_border"
            >
              <el-col :span="8">
                <span class="_bold_black_m">価格</span>
              </el-col>
              <el-col
                :span="16"
              >
                <ifa-text
                  code-list-id="EXECUTE_METHOD"
                  :disp-pattern="1"
                  :code-key="form.ifd2SasinariHouhou"
                ></ifa-text>
                <span style="white-space: pre-wrap;">{{ getPrice(ifd2PriceParam) }}</span>
              </el-col>
            </el-row>

            <!-- IFDOCOの時価格欄-->
            <el-row v-if="form.orderKind === '4'">
              <el-row
                v-if="form.workingStatus === 'false'"
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">価格／OCO1</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;">{{ getOco1Price() }}</span>
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">条件／OCO2</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span style="white-space: pre-wrap;">{{ getOco2Price(form.tradeCd, form.oco1SasinariJyouken) }}</span>
                </el-col>
              </el-row>
            </el-row>

            <!--期間、預り区分、手数料区分、受注日時は共通して表示-->
            <el-row>
              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">注文期間</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out($_getFormattedDateValue(form.ifd2Limit)) }}</span>
                </el-col>
              </el-row>

              <el-row
                class="dotted_border"
              >
                <el-col :span="8">
                  <span class="_bold_black_m">手数料区分</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <ifa-text
                    code-list-id="PRE_CONTRACT_DOC_CODE"
                    :disp-pattern="1"
                    :code-key="customerInfo.customerAttribute"
                  ></ifa-text>
                  （電話手数料）
                </el-col>
              </el-row>

              <el-row class="dotted_border">
                <el-col :span="8">
                  <span class="_bold_black_m">受注日時</span>
                </el-col>
                <el-col
                  :span="16"
                >
                  <span>{{ $_out($_getFormattedDateTimeValue(form.orderDayTime,'datetime12')) }}</span>
                </el-col>
              </el-row>
            </el-row>
          </el-card>
        </el-col>
      </el-row>

      <el-row style="margin-top: 20px;">
        <el-col
          :offset="1"
          :span="22"
          style="text-align: left;"
        >
          <ifa-button
            id="btnOrderRegister"
            text="注文取消"
            style="padding-left: 0;"
            :disabled="userInfo.medUsers.privId === '1' || userInfo.medUsers.privId === '2'"
            action-type="requestAction"
            action-id="SUB0202_0212-03_1#A002"
            :request-model="IfaMarginNewOrderCancelConfirmA002RequestModel"
            @response-handler="orderCancellationA002"
          ></ifa-button>
        </el-col>
      </el-row>

    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaMarginNewOrderCancelConfirmFormModel } from './js/IfaMarginNewOrderCancelConfirmFormModel.js'
import { IfaMarginNewOrderCancelConfirmA002RequestModel } from './js/IfaMarginNewOrderCancelConfirmA002RequestModel.js'

import IfaMessageArea from '@/components/Message/IfaMessageArea'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaMessageArea,
    IfaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    cancelData: { type: Object, required: true },
    ecOrderNo: { type: String, required: true }
  },
  emits: ['close-modal', 'update:isVisible', 'cancel-finish'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaMarginNewOrderCancelConfirmFormModel(),
      priceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: ''
      },
      ifd1PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: ''
      },
      ifd2PriceParam: {
        sasinariHouhou: '',
        sasinariJyouken: '',
        triggerPrice: '',
        gyakusasiHouhou: '',
        price: ''
      },
      cancelCompleteIsVisible: false,
      classObject: {
        classObjBgColor: '',
        classObjFontColor: ''
      },
      // 完了画面に渡すデータ
      completeData: {}
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    userInfo() {
      return this.$store.getters.userAccount
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    IfaMarginNewOrderCancelConfirmA002RequestModel() {
      return new IfaMarginNewOrderCancelConfirmA002RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.cancelData)
      this.form.ecOrderNo = this.ecOrderNo
      this.form.ifd2Limit = this.$_getFormattedDateValue(this.form.ifd2Limit)
      this.form.orderDayTime = this.$_getFormattedDateTimeValue(this.form.orderDayTime, 'datetime12')
      // gePriceに渡すパラメータへ値をセット
      this.priceParam = {
        sasinariHouhou: this.form.sasinariHouhou,
        sasinariJyouken: this.form.sasinariJyouken,
        triggerPrice: this.form.triggerPrice,
        gyakusasiHouhou: this.form.gyakusasiHouhou,
        price: this.form.price
      }
      this.ifd1PriceParam = {
        sasinariHouhou: this.form.ifd1SasinariHouhou,
        sasinariJyouken: this.form.ifd1SasinariJyouken,
        triggerPrice: this.form.ifd1TriggerPrice,
        gyakusasiHouhou: this.form.ifd1GyakusasiHouhou,
        price: this.form.ifd1Price
      }
      this.ifd2PriceParam = {
        sasinariHouhou: this.form.ifd2SasinariHouhou,
        sasinariJyouken: this.form.ifd2SasinariJyouken,
        triggerPrice: this.form.ifd2TriggerPrice,
        gyakusasiHouhou: this.form.ifd2GyakusasiHouhou,
        price: this.form.ifd2Price
      }
      // 背景、取引種別の色
      this.setClassObjBgColor()
      this.setClassObjFontColor()
    },
    // 背景の色
    setClassObjBgColor() {
      if (this.form.orderKind === '1' || this.form.orderKind === '2') {
        // 買付
        if (this.form.tradeCd === '3') {
          this.classObject.classObjBgColor = 'buy-background-color'
        // 売却
        } else {
          this.classObject.classObjBgColor = 'sell-background-color'
        }
      } else {
        this.classObject.classObjBgColor = 'ifd-background-color'
      }
    },
    // 取引種別の色
    setClassObjFontColor() {
      if (this.form.tradeCd === '3') {
        this.classObject.classObjFontColor = 'font-color__plus bold'
        // 売却
      } else {
        this.classObject.classObjFontColor = 'font-color__minus bold'
      }
    },
    // 取消発注ボタン
    orderCancellationA002(response) {
      // 完了画面に遷移
      this.$emit('cancel-finish', response)
    },
    // 戻るボタン
    backA003() {
      this.$emit('close-modal')
    },
    // 注文状況一覧へボタン
    handleToOrderStatusList() {
      this.cancelCompleteIsVisible = false
      this.$emit('close-modal')
    },
    // 価格欄に表示する内容を生成する
    getPrice(param) {
      if (param.sasinariHouhou === '1') {
      // 指値
        const str = param.price ? this.$_withCommaNoneZeroPadding(param.price) + '円' : '-'
        if (param.sasinariJyouken !== ' ') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken)) +
               '/' + str
        } else {
          return `\n` + str
        }
      } else if (param.sasinariHouhou === '2') {
      // 成行
        if (param.sasinariJyouken !== 'N') {
          return `\n` + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, param.sasinariJyouken))
        }
      } else if (param.sasinariHouhou === '3') {
      // 逆指値
        const triggerPrice = param.triggerPrice ? this.$_withCommaNoneZeroPadding(param.triggerPrice) + '円' : '-'
        let str = '現在値が' + triggerPrice

        switch (this.form.tradeCd) {
          case '3':
            str += '以上になった時点で'
            break
          case '4':
            str += '以下になった時点で'
            break
        }
        if (param.gyakusasiHouhou === '1') {
          // 執行方法（逆指値）が指値の時
          if (param.sasinariJyouken !== ' ') {
            str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, param.sasinariJyouken))
          }
          str += this.$_out(this.$_withCommaNoneZeroPadding(param.price)) + '円'
        } else if (param.gyakusasiHouhou === '2') {
        // 執行方法（逆指値）が成行の時
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, param.sasinariJyouken))
        }
        return `\n` + str + 'で執行'
      }
      return ''
    },
    getOco1Price() {
      let str = this.$_out(this.$_getCodeValue('EXECUTE_METHOD', 1, this.form.oco1SasinariHouhou))
      str += `\n`
      if (this.form.oco1SasinariJyouken !== ' ') {
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.oco1SasinariJyouken)) + '/'
      }
      const oco1Price = this.form.oco1Price ? this.$_withCommaNoneZeroPadding(this.form.oco1Price) + '円' : '-'
      return str + oco1Price
    },
    getOco2Price(tradeCd, sasinariJyouken) {
      const oco2TriggerPrice = this.form.oco2TriggerPrice ? this.$_withCommaNoneZeroPadding(this.form.oco2TriggerPrice) + '円' : '-'
      let str = '現在値が' + oco2TriggerPrice
      switch (tradeCd) {
        case '3':
          str += `以下になった時点で`
          break
        case '4':
          str += '以上になった時点で'
          break
      }
      if (sasinariJyouken === ' ') {
      // 条件なし
        str += 'OCO1（指値）を'
      } else {
        str += 'OCO1（' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, sasinariJyouken)) + '）を'
      }
      if (this.form.oco2GyakusasiHouhou === '1') {
      // 執行方法（逆指値）が指値の時
        const oco2Price = this.form.oco2Price ? this.$_withCommaNoneZeroPadding(this.form.oco2Price) + '円' : '-'
        if (this.form.oco2GyakusasiJyouken !== ' ') {
          str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.form.oco2GyakusasiJyouken)) + '/' + oco2Price
        } else {
          str += oco2Price
        }
      } else if (this.form.oco2GyakusasiHouhou === '2') {
      // 執行方法（逆指値）が成行の時
        str += this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, 'N'))
      }
      return str + 'に訂正'
    }
  }
}
</script>

<style lang="scss">
@import "@/styles/orderStatusList.scss";
</style>
<style lang="scss" scoped>
:deep(.el-text){
  font-size: 16px;
}
</style>
