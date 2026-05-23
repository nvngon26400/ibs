<template>
  <!-- 為替取引 -->
  <div>
    <div v-if="!orderInputVisible">
      <screen-title :text="form.screenTitle"></screen-title>
      <div class="caption_card">
        <!-- 取扱通貨 -->
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
                <el-col :span="7"><span>リアルタイム約定通貨の場合</span></el-col>
                <el-col :span="17"><span>夏時間：月 7:05〜翌5:30、火〜金 6:05〜翌5:30 のほぼ24時間</span></el-col>
              </el-row>
              <el-row>
                <el-col
                  :span="17"
                  :offset="7"
                ><span>冬時間：月～金 7:05～翌6:30のほぼ24時間</span>
                  <br>
                  <span style="font-size: 12px; color: #999;">※月～金19:00～19:30の定期メンテナンスを除く<br></span>
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
                width="60"
                :resizable="false"
              >
                <template #default="scope">
                  <div class="custom-cell">
                    <img v-if="scope.row.currencyCode"
                         :src="imgs(scope.row.currencyCode.substring(0, 2).toLowerCase())"
                    >
                    <span v-if="!scope.row.currencyCode">-</span>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                prop="currencyName"
                label="通貨"
                width="170"
                :resizable="false"
                header-align="center"
                :formatter="$_format"
              >
                <template #default="scope">
                  <span>{{ $_out(scope.row.currencyName) }}</span>
                </template>
              </el-table-column>
              <el-table-column
                label="約定タイミング"
                width="150"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default>
                  <span>リアルタイム</span>
                </template>
              </el-table-column>
              <el-table-column
                prop="purchaseReferenceRate"
                label="買付参考レート"
                width="330"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default="scope">
                  <span v-if="scope.row.decimalLength > 2">{{ scope.row.purchaseReferenceRate ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.purchaseReferenceRate, scope.row.decimalLength)} 円` : '-' }}</span>
                  <span v-else>{{ scope.row.purchaseReferenceRate ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.purchaseReferenceRate, 2)} 円` : '-' }}</span>
                  <ifa-button
                    id="btnBuy"
                    class="order-button"
                    text="買付"
                    color="buy"
                    small
                    action-type="originalAction"
                    @app-action-handler="setParam('3', scope.row.currencyCode)"
                  ></ifa-button>
                </template>
              </el-table-column>
              <el-table-column
                prop="referenceRateForSale"
                label="売却参考レート"
                width="330"
                :resizable="false"
                header-align="center"
                align="right"
              >
                <template #default="scope">
                  <span v-if="scope.row.decimalLength > 2">{{ scope.row.referenceRateForSale ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.referenceRateForSale, scope.row.decimalLength)} 円` : '-' }}</span>
                  <span v-else>{{ scope.row.referenceRateForSale ? `${ifaFormatUtils.withCommaZeroPadding(scope.row.referenceRateForSale, 2)} 円` : '-' }}</span>
                  <ifa-button
                    id="btnSell"
                    class="order-button"
                    text="売却"
                    color="sell"
                    small
                    action-type="originalAction"
                    @app-action-handler="setParam('1', scope.row.currencyCode)"
                  ></ifa-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
        </div>
      </div>
    </div>
    <ifa-ifa-fx-order-input
      :order-input-visible="orderInputVisible"
      :request-data="ifaIfaFxOrderInputA001ResponseForm"
      @handle-close-modal="initializeBackA003"
    ></ifa-ifa-fx-order-input>
  </div>
  <ifa-requester
    id="ifaIfaCurrencyDealtListA001"
    action-id="SUB0202_0503-01#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>

  <ifa-requester
    id="ifaIfaFxOrderInputInitializeA001"
    action-type="requestAction"
    action-id="SUB0202_0503-02_1#A001"
    :request-model="IfaIfaFxOrderInputA001RequestModel"
    @response-handler="responseHandlerInitializeIfaIfaFxOrderInputA001($event)"
    @response-error-handler="errorHandlerInitializeIfaIfaFxOrderInputA001($event)"
  ></ifa-requester>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaIfaFxOrderInput from '@/views/brokerageMenu/customerMenu/fxTrade/ifaFxTrade/IfaIfaFxOrderInput.vue'
import { IfaIfaCurrencyDealtListA002RequestModel } from './js/IfaIfaCurrencyDealtListA002RequestModel'
import { IfaIfaCurrencyDealtListFormModel } from './js/IfaIfaCurrencyDealtListFormModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils'
import { IfaIfaFxOrderInputA001RequestModel } from './js/IfaIfaFxOrderInputA001RequestModel'

export default {
  components: {
    IfaIfaFxOrderInput,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaIfaCurrencyDealtListFormModel(),
      ifaFormatUtils: ifaFormatUtils,
      orderInputVisible: false,
      ifaIfaFxOrderInputA001RequestForm: {
        currencyCode: '',
        tradeKbn: ''
      },
      ifaIfaFxOrderInputA001ResponseForm: {}
    }
  },
  computed: {
    IfaIfaCurrencyDealtListA002RequestModel() {
      return new IfaIfaCurrencyDealtListA002RequestModel(this.form)
    },
    IfaIfaFxOrderInputA001RequestModel() {
      return new IfaIfaFxOrderInputA001RequestModel(this.ifaIfaFxOrderInputA001RequestForm)
    }
  },
  methods: {
    onShow() {
      document.getElementById('ifaIfaCurrencyDealtListA001').click()
      this.initializeBackA003()
    },
    responseHandlerInitializeA001(data) {
      this.form = Object.assign(this.form, data.dataList[0])
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    setParam(tradeKbn, currencyCode) {
      this.ifaIfaFxOrderInputA001RequestForm.tradeKbn = tradeKbn
      this.ifaIfaFxOrderInputA001RequestForm.currencyCode = currencyCode

      this.$nextTick(() => {
        document.getElementById('ifaIfaFxOrderInputInitializeA001').click()
      })
    },
    initializeBackA003() {
      this.orderInputVisible = false
    },
    imgs(icon) {
      return require('@/assets/png/flags/32/' + icon + '.png')
    },
    responseHandlerInitializeIfaIfaFxOrderInputA001(response) {
      // 通貨コード（全角半角）
      this.ifaIfaFxOrderInputA001ResponseForm.currencyCode = response.dataList[0].currencyCode
      // 売買区分（全角半角）
      this.ifaIfaFxOrderInputA001ResponseForm.tradeKbn = response.dataList[0].tradeKbn
      // 通貨名（全角）
      this.ifaIfaFxOrderInputA001ResponseForm.currency = response.dataList[0].currency
      // 小数部有効桁数（数値(整数)）
      this.ifaIfaFxOrderInputA001ResponseForm.decimalLength = response.dataList[0].decimalLength
      //　為替グループ（全角半角）
      this.ifaIfaFxOrderInputA001ResponseForm.exchangeGroup = response.dataList[0].exchangeGroup
      // 注文ワーニングしきい値（IFAリアルタイム為替）
      this.ifaIfaFxOrderInputA001ResponseForm.warningThreshold = response.dataList[0].ifaWarningThreshold
      // 取引下限（数値(小数)）
      this.ifaIfaFxOrderInputA001ResponseForm.closableQuantity = response.dataList[0].closableQuantity
      // 取引上限（数値(小数)）
      this.ifaIfaFxOrderInputA001ResponseForm.tradeLimitMax = response.dataList[0].tradeLimitMax
      // 取引単位
      this.ifaIfaFxOrderInputA001ResponseForm.tradeUnit = response.dataList[0].tradeUnit
      // 外貨スピンボタン増減幅
      this.ifaIfaFxOrderInputA001ResponseForm.foreignButtonRange = response.dataList[0].foreignButtonRange
      // 参考レート（数値(小数)）
      this.ifaIfaFxOrderInputA001ResponseForm.referenceRate = response.dataList[0].referenceRate
      // 概算用レート
      this.ifaIfaFxOrderInputA001ResponseForm.computeExchangeRate = response.dataList[0].computeExchangeRate
      // デノミ（数値(整数)）
      this.ifaIfaFxOrderInputA001ResponseForm.denominator = response.dataList[0].denominator
      // スプレッド
      this.ifaIfaFxOrderInputA001ResponseForm.adjustPrice = response.dataList[0].adjustPrice
      // 注文可能数量（数値(整数)）
      this.ifaIfaFxOrderInputA001ResponseForm.maxOrderableQuantity = response.dataList[0].maxOrderableQuantity
      // 概算買付可能金額（数値(整数)）
      this.ifaIfaFxOrderInputA001ResponseForm.approximatePurchaseAmount = response.dataList[0].approximatePurchaseAmount
      // 外貨指定最大値
      this.ifaIfaFxOrderInputA001ResponseForm.foreignDesignationMaxValue = response.dataList[0].foreignDesignationMaxValue
      // 売却可能数量
      this.ifaIfaFxOrderInputA001ResponseForm.sellAbleQuantity = response.dataList[0].foreignDesignationMaxValue
      // 円指定最小値
      this.ifaIfaFxOrderInputA001ResponseForm.yenDesignationMinValue = response.dataList[0].yenDesignationMinValue
      // 円指定最大値
      this.ifaIfaFxOrderInputA001ResponseForm.yenDesignationMaxValue = response.dataList[0].yenDesignationMaxValue

      this.ifaIfaFxOrderInputA001ResponseForm.updateTime = response.requestedTime

      if (this.ifaIfaFxOrderInputA001ResponseForm.tradeKbn === '1') {
        // 売却方法 初期値 "指定した数量を売却"
        this.ifaIfaFxOrderInputA001ResponseForm.saleMethod = '1'
      }
      this.orderInputVisible = true
    },
    errorHandlerInitializeIfaIfaFxOrderInputA001(response) {
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

.custom-cell {
  overflow: visible;
}
</style>
