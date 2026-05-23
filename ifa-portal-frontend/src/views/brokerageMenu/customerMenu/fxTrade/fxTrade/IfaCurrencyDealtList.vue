<template>
  <ifa-requester
    id="IfaFxOrderInputA001"
    action-id="SUB0202_0502-02_1#A001"
    action-type="requestAction"
    :request-model="a002RequestModel"
    @response-handler="ifaFxOrderInputA001ActionHandler"
  ></ifa-requester>
  <!-- 為替取引 -->
  <div v-if="!orderInputVisible">
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <!-- 取扱通貨一覧 -->
      <div>
        <div>
          <el-row style="margin-top: 0.5rem; margin-bottom: 1rem; width: 850px;">
            <el-card
              class="box-card"
              style="background-color: #eee; padding: 0.5rem 1.5rem;"
            >
              <el-row>
                <span><strong>注文受付時間</strong></span>
              </el-row>
              <el-row>
                <el-col :span="8"><span>リアルタイム約定通貨の場合</span></el-col>
                <el-col :span="16"><span>夏時間：月 7:05〜翌5:30、火〜金 6:05〜翌5:30 のほぼ24時間</span></el-col>
              </el-row>
              <el-row>
                <el-col
                  :span="16"
                  :offset="8"
                ><span>冬時間：月～金　7:05～翌6:30のほぼ24時間</span></el-col>
              </el-row>
              <el-row>
                <el-col :span="8"><span>定時約定通貨の場合</span></el-col>
                <el-col :span="16">
                  <span>当社適用為替レート確定する締切時刻から30分程度を除き可能</span>
                  <br>
                  <span style="font-size: 12px; color: #999;">※毎日19:00～19:30の定期メンテナンスを除く<br></span>
                </el-col>
              </el-row>
            </el-card>
          </el-row>
        </div>

        <div>
          <el-row style="width: 1040px;">
            <el-table
              :data="form.currencyList"
              border
            >
              <el-table-column
                label=""
                width="60"
                :resizable="false"
              >
                <template #default="scope">
                  <img v-if="scope.row.currencyCode"
                       :src="CurrencyIcon($_out(scope.row.currencyCode))"
                  >
                  <span v-else>
                    {{ $_out(scope.row.currencyCode) }}
                  </span>
                </template>
              </el-table-column>
              <el-table-column
                label="通貨"
                :resizable="false"
                width="170"
                header-align="center"
              >
                <template #default="scope">
                  {{ $_out(scope.row.currencyName) }}
                </template>
              </el-table-column>
              <el-table-column
                label="約定タイミング"
                width="150"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default="scope">
                  <span v-if="scope.row.fxTrade === 'REAL'">リアルタイム</span>
                  <span v-else-if="scope.row.fxTrade === 'REGULAR'">{{ $_out(scope.row.deadlines2 ? scope.row.deadlines1 + '/' + scope.row.deadlines2 : scope.row.deadlines1) }}</span>
                  <span v-else>{{ $_out(null) }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="買付参考レート"
                width="330"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default="scope">
                  <span v-if="scope.row.decimalLength > 2">{{ $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, scope.row.decimalLength)) === '-' ? $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, scope.row.decimalLength)) : $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, scope.row.decimalLength)) + '円' }} </span>
                  <span v-else>{{ $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, 2)) === '-' ? $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, 2)) : $_out($_withCommaZeroPadding(scope.row.purchaseReferenceRate, 2)) + '円' }} </span>
                  <ifa-button
                    id="btnBuy"
                    class="order-button"
                    text="買付"
                    color="buy"
                    small
                    action-type="originalAction"
                    @app-action-handler="purchaseSaleA002('3', scope.row.currencyCode)"
                  ></ifa-button>
                </template>
              </el-table-column>
              <el-table-column
                label="売却参考レート"
                width="330"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default="scope">
                  <span v-if="scope.row.decimalLength > 2">{{ $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, scope.row.decimalLength)) === '-' ? $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, scope.row.decimalLength)) : $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, scope.row.decimalLength)) + '円' }} </span>
                  <span v-else>{{ $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, 2)) === '-' ? $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, 2)) : $_out($_withCommaZeroPadding(scope.row.referenceRateForSale, 2)) + '円' }} </span>
                  <ifa-button
                    id="btnSell"
                    class="order-button"
                    text="売却"
                    color="sell"
                    small
                    action-type="originalAction"
                    @app-action-handler="purchaseSaleA002('1', scope.row.currencyCode)"
                  ></ifa-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
          <el-row style="margin-left: 60px;">
            <el-row style="padding: 0.5rem 0 1rem 0;">
              ※ベトナムドンについては､10,000倍のレート(10,000ベトナムドンに対する円レート)の表示となります｡
              <br>※韓国ウォン､インドネシアルピアについては､100倍のレート(100韓国ウォン､100インドネシアルピアに対する円レート)の表示となります｡
            </el-row>
          </el-row>
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <!--為替注文入力画面 -->
    <ifa-fx-order-input
      ref="ifaFxOrderInput"
      :form-data="IfaFxOrderInputA001Data"
      @handle-close-modal="orderNewMarginBackA003"
    ></ifa-fx-order-input>
  </div>
  <ifa-requester
    id="IfaCurrencyDealtListOrderNewMarginA001"
    action-id="SUB0202_0502-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerOrderNewMarginA001($event)"
    @response-error-handler="a001ActionErrorHandle($event)"
  ></ifa-requester>
</template>

<script>
import ifaFxOrderInput from '@/views/brokerageMenu/customerMenu/fxTrade/fxTrade/IfaFxOrderInput'
import { IfaCurrencyDealtListA002RequestModel } from './js/IfaCurrencyDealtListA002RequestModel'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { IfaCurrencyDealtListFormModel } from './js/IfaCurrencyDealtListFormModel.js'

export default {
  components: {
    ifaFxOrderInput,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaFxOrderInputA001Data: {},
      a002RequestModel: {
        // 選択行通貨コード
        currencyCode: '',
        // 売買区分
        tradeKbn: ''
      },
      selectedCurrencyInfo: {
        currencyCode: '',
        tradeKbn: ''
      },
      orderInputVisible: false,
      currencyList: [],
      form: new IfaCurrencyDealtListFormModel()
    }
  },
  computed: {
    IfaCurrencyDealtListA002RequestModel() {
      return new IfaCurrencyDealtListA002RequestModel(this.a002RequestModel)
    }
  },
  methods: {
    // タブが表示された
    onShow() {
      // タブを初期化する
      this.$nextTick(() => {
        document.getElementById('IfaCurrencyDealtListOrderNewMarginA001').click()
        this.orderInputVisible = false
      })
    },
    responseHandlerOrderNewMarginA001(data) {
      Object.assign(this.form, data.dataList[0])
    },
    a001ActionErrorHandle(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    ifaFxOrderInputA001ActionHandler(response) {
      this.IfaFxOrderInputA001Data = response.dataList[0]
      this.orderInputVisible = true
    },
    // 注文入力画面に遷移する
    purchaseSaleA002(tradeType, currencyCode) {
      this.$_logDebug(tradeType, currencyCode)
      if (currencyCode && currencyCode !== '') {
        this.a002RequestModel.tradeKbn = tradeType
        this.a002RequestModel.currencyCode = currencyCode
        this.$nextTick(() => {
          document.getElementById('IfaFxOrderInputA001').click()
        })
      }
    },
    orderNewMarginBackA003() {
      this.$nextTick(() => {
        document.getElementById('IfaCurrencyDealtListOrderNewMarginA001').click()
        this.orderInputVisible = false
      })
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    CurrencyIcon(codeKey) {
      let icon = ''
      if (codeKey === 'USD') {
        icon = 'us'
      }
      if (codeKey === 'AUD') {
        icon = 'au'
      }
      if (codeKey === 'HKD') {
        icon = 'hk'
      }
      if (codeKey === 'EUR') {
        icon = 'eu'
      }
      if (codeKey === 'NZD') {
        icon = 'nz'
      }
      if (codeKey === 'CAD') {
        icon = 'ca'
      }
      if (codeKey === 'ZAR') {
        icon = 'za'
      }
      if (codeKey === 'MXN') {
        icon = 'mx'
      }
      if (codeKey === 'TRY') {
        icon = 'tr'
      }
      if (codeKey === 'SGD') {
        icon = 'sg'
      }
      if (codeKey === 'KRW') {
        icon = 'kr'
      }
      if (codeKey === 'RUB') {
        icon = 'ru'
      }
      if (codeKey === 'VND') {
        icon = 'vn'
      }
      if (codeKey === 'IDR') {
        icon = 'id'
      }
      if (codeKey === 'THB') {
        icon = 'th'
      }
      if (codeKey === 'MYR') {
        icon = 'my'
      }
      if (codeKey === 'CNY') {
        icon = 'cn'
      }
      return require('@/assets/png/flags/32/' + icon + '.png')
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
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
  width: 85%;
  margin-left: 3%;
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
</style>
