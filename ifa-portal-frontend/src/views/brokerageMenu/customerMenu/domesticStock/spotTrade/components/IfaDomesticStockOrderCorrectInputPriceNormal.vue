<!-- 価格～訂正前価格/執行方法はOCO かつ 発火済みの場合表示しない -->
<template>
  <!-- 訂正前価格 -->
  <div class="form-area__section">
    <el-form-item
      class="el-form-item__error_custom-margin"
      label="価格"
    >
      <span style="white-space: nowrap;">{{ beforeCorrectPriceStr }}</span>
    </el-form-item>
  </div>

  <!-- 価格/執行方法 -->
  <div
    class="form-area__section"
    v-bind="$attrs"
    :class="$attrs.class"
  >
    <el-form-item
      class="el-form-item__error_custom-margin"
      label="訂正価格/執行方法"
      required
    >
      <el-row
        :gutter="15"
      >
        <!-- 執行方法 -->
        <el-col
          v-if="inputParamForSasinariHouhou.showElement"
          :span="3"
        >
          <ifa-input-select
            v-model="innerSasinariHouhou"
            code-list-id="EXECUTE_METHOD"
            :prop="getPropWithPrefix('sasinariHouhou')"
            :disabled="inputParamForSasinariHouhou.disabled"
            :disp-pattern="inputParamForSasinariHouhou.displayPattern"
            :select-pattern="inputParamForSasinariHouhou.selectPattern"
            required
            placeholder=" "
          ></ifa-input-select>
        </el-col>

        <!-- 執行条件 -->
        <el-col
          v-if="inputParamForSasinariJyouken.showElement"
          :span="4"
        >
          <ifa-input-select
            v-model="innerSasinariJyouken"
            code-list-id="LIMIT_MARKET_TYPE"
            :prop="getPropWithPrefix('sasinariJyouken')"
            :disabled="inputParamForSasinariJyouken.disabled"
            :disp-pattern="inputParamForSasinariJyouken.displayPattern"
            :select-pattern="inputParamForSasinariJyouken.selectPattern"
            required
            placeholder=" "
          ></ifa-input-select>
        </el-col>

        <!-- 注文単価(変更後の執行方法 = 指値の場合) -->
        <el-col
          v-if="priceParam.sasinariHouhou === '1'"
          :span="10"
        >
          <ifa-input-price
            id="domesticCorrectLimitOrderPrice"
            v-model="innerPrice"
            :prop="getPropWithPrefix('price')"
            :min="minPrice"
            :max="maxPrice"
            :step-table="brandInfo.orderPriceUnit"
            :initial-step="initialStepPrice"
            :domain="ifaUnitPriceYen102DigitsBDomainModel"
            :support="true"
            unit="円"
            required
          ></ifa-input-price>
        </el-col>

        <!-- 逆指値の場合の項目No 59～68 -->
        <template
          v-if="priceParam.sasinariHouhou === '3'"
        >
          <el-col
            :span="2"
            style="display: flex; align-items: center;"
          >
            <span>現在値が</span>
          </el-col>

          <!-- 発火条件価格(逆指値) -->
          <el-col
            :span="10"
          >
            <ifa-input-price
              id="domesticCorrectLimitOrderPrice"
              v-model="innerTriggerPrice"
              :prop="getPropWithPrefix('triggerPrice')"
              :disabled="workingStatus === 'true'"
              :min="minPrice"
              :max="maxPrice"
              :step-table="brandInfo.orderPriceUnit"
              :initial-step="initialStepTrigger"
              :domain="ifaCurrency152DigitsBDomainModel"
              :support="true"
              unit="円"
            ></ifa-input-price>
          </el-col>

          <!-- 発火条件価格（逆指値）後文言-->
          <el-col
            :span="5"
            :pull="1"
            style="display: flex; align-items: center;"
          >
            <span>{{ triggerPriceSuffix }}</span>
          </el-col>
        </template>
      </el-row>

      <el-row
        v-if="priceParam.sasinariHouhou === '3'"
        :gutter="15"
        style="margin-top: 12px;"
      >
        <el-col
          :span="3"
          :offset="3"
        >
          <!-- 執行方法(逆指値)(変更前の執行方法(逆指値)が指値の場合) -->
          <ifa-input-select
            v-if="beforeCorrectPriceParam.gyakusasiHouhou === '1'"
            v-model="innerGyakusasiHouhou"
            code-list-id="EXECUTE_METHOD"
            :prop="getPropWithPrefix('gyakusasiHouhou')"
            :disabled="innerSasinariJyouken === 'F'"
            :disp-pattern="1"
            :select-pattern="2"
            required
            placeholder=" "
          ></ifa-input-select>

          <!-- 執行方法(逆指値)(変更前の執行方法(逆指値)が成行の場合) -->
          <ifa-input-select
            v-if="beforeCorrectPriceParam.gyakusasiHouhou === '2'"
            v-model="innerGyakusasiHouhou"
            code-list-id="EXECUTE_METHOD"
            :prop="getPropWithPrefix('gyakusasiHouhou')"
            :disp-pattern="1"
            :select-pattern="2"
            required
            placeholder=" "
          ></ifa-input-select>
        </el-col>

        <el-col :span="4">
          <!-- 執行条件(変更後の執行方法 = 指値の場合) -->
          <ifa-input-select
            v-if="innerGyakusasiHouhou === '1'"
            v-model="innerSasinariJyouken"
            code-list-id="LIMIT_MARKET_TYPE"
            :prop="getPropWithPrefix('sasinariJyouken')"
            :disabled="true"
            :disp-pattern="2"
            :select-pattern="4"
            required
            placeholder=" "
          ></ifa-input-select>

          <!-- 執行条件(変更後の執行方法 = 成行の場合) -->
          <ifa-input-select
            v-if="innerGyakusasiHouhou === '2'"
            v-model="innerSasinariJyouken"
            code-list-id="LIMIT_MARKET_TYPE"
            :prop="getPropWithPrefix('sasinariJyouken')"
            :disabled="true"
            :disp-pattern="3"
            :select-pattern="5"
            required
            placeholder=" "
          ></ifa-input-select>
        </el-col>

        <el-col
          v-if="innerGyakusasiHouhou === '1'"
          :span="10"
        >
          <!-- 注文単価(変更後の執行方法 = 指値の場合) -->
          <ifa-input-price
            id="domesticCorrectLimitOrderPrice"
            v-model="innerPrice"
            :prop="getPropWithPrefix('price')"
            :min="minPrice"
            :max="maxPrice"
            :step-table="brandInfo.orderPriceUnit"
            :initial-step="initialStepPrice"
            :domain="ifaUnitPriceYen102DigitsBDomainModel"
            :support="true"
            unit="円"
            required
          ></ifa-input-price>
        </el-col>

        <!-- 注文単価（逆指値）文言(変更後の執行方法 = 指値の場合)  -->
        <el-col
          v-if="innerGyakusasiHouhou === '1'"
          :span="2"
          :pull="1"
          style="display: flex; align-items: center;"
        >
          <span>で執行</span>
        </el-col>

        <!-- 注文単価（逆指値）文言(変更後の執行方法 = 成行の場合)  -->
        <el-col
          v-if="innerGyakusasiHouhou === '2'"
          :span="2"
          style="display: flex; align-items: center;"
        >
          <span>で執行</span>
        </el-col>
      </el-row>

      <!-- 制限値幅 -->
      <el-row
        :gutter="15"
        style="margin-top: 8px;"
      >
        <el-col
          :offset="priceParam.sasinariHouhou === '3' ? 10 : 7"
          :span="15"
        >
          <!-- 制限値幅 -->
          <span
            v-if="stopLow !== '1.0'
              || stopHigh !== '9999999999999.9'"
          >
            値幅制限：{{ $_out($_withCommaNoneZeroPadding(stopLow)) }}円
            ～{{ $_out($_withCommaNoneZeroPadding(stopHigh)) }}円
            （{{ $_out($_getFormattedDateValue(brandInfo.baseDate, 'date6')) }}）
          </span>
          <span v-else>値幅制限：なし</span>
        </el-col>
      </el-row>
    </el-form-item>
  </div>
</template>
<script>
import { useVModel } from 'vue-composable'
import IfaUnitPriceYen102DigitsBDomainModel from '@/resource/domain/IfaUnitPriceYen102DigitsBDomainModel.json'
import IfaCurrency152DigitsBDomainModel from '@/resource/domain/IfaCurrency152DigitsBDomainModel.json'

export default {
  props: {
    propPrefix: { type: String, required: true },
    tradeCd: { type: String, required: true },
    orderKind: { type: String, required: true },
    beforeCorrectPriceParam: { type: Object, required: true },
    priceParam: { type: Object, required: true },
    brandInfo: { type: Object, required: true },
    workingStatus: { type: String, required: false, default: 'false' }
  },
  emits: ['update:priceParam'],
  data() {
    return {
      ifaUnitPriceYen102DigitsBDomainModel: IfaUnitPriceYen102DigitsBDomainModel,
      ifaCurrency152DigitsBDomainModel: IfaCurrency152DigitsBDomainModel,
      vmPriceParam: useVModel(this.props, 'priceParam')
    }
  },
  computed: {
    // 内部用priceParam(v-modelで更新するため)
    innerPriceParam: {
      get() {
        return this.priceParam
      },

      set(newValue) {
        this.$emit('update:priceParam', newValue)
      }
    },

    // 内部用執行方法(v-modelで更新するため)
    innerSasinariHouhou: {
      get() {
        return this.priceParam.sasinariHouhou
      },

      set(newValue) {
        // A005 執行条件の変更
        let newSasinariJyouken = null
        if (this.innerSasinariHouhou === '1' && newValue === '2') { // 指値 → 成行
          switch (this.innerSasinariJyouken) {
            case ' ': // 指値/条件なし → 成行/条件なし
              newSasinariJyouken = 'N'
              break

            case 'Z': // 指値/寄指 → 成行/寄成
              newSasinariJyouken = 'Y'
              break

            case 'I': // 指値/引指 → 成行/引成
              newSasinariJyouken = 'H'
              break

            case 'P': // 指値/IOC指 → 成行/IOC成
              newSasinariJyouken = 'O'
              break

            default:
              break
          }
        }

        if (this.innerSasinariHouhou === '2' && newValue === '1') { // 成行 → 指値
          switch (this.innerSasinariJyouken) {
            case 'N': // 成行/条件なし → 指値/条件なし
              newSasinariJyouken = ' '
              break

            case 'Y': // 成行/寄成 → 指値/寄指
              newSasinariJyouken = 'Z'
              break

            case 'H': // 成行/引成 → 指値/引指
              newSasinariJyouken = 'I'
              break

            case 'O': // 成行/IOC成 → 指値/IOC指
              newSasinariJyouken = 'P'
              break

            default:
              break
          }
        }

        if (newSasinariJyouken === null) {
          this.innerPriceParam = { ...this.innerPriceParam, sasinariHouhou: newValue }
        } else {
          this.innerPriceParam = { ...this.innerPriceParam, sasinariHouhou: newValue, sasinariJyouken: newSasinariJyouken }
        }
      }
    },

    // 内部用執行条件(v-modelで更新するため)
    innerSasinariJyouken: {
      get() {
        return this.priceParam.sasinariJyouken
      },

      set(newValue) {
        this.innerPriceParam = { ...this.innerPriceParam, sasinariJyouken: newValue }
      }
    },

    // 内部用トリガー価格(v-modelで更新するため)
    innerTriggerPrice: {
      get() {
        return this.priceParam.triggerPrice
      },

      set(newValue) {
        this.innerPriceParam = { ...this.innerPriceParam, triggerPrice: newValue }
      }
    },

    // 内部用トリガーゾーン(v-modelで更新するため)
    innerStopOrderPriceText2: {
      get() {
        return this.priceParam.stopOrderPriceText2
      },

      set(newValue) {
        this.innerPriceParam = { ...this.innerPriceParam, stopOrderPriceText2: newValue }
      }
    },

    // 内部用執行方法(逆指値)(v-modelで更新するため)
    innerGyakusasiHouhou: {
      get() {
        return this.priceParam.gyakusasiHouhou
      },

      set(newValue) {
        // A008 執行条件（逆指値）の変更
        let newSasinariJyouken = null
        if (this.innerGyakusasiHouhou === '1' && newValue === '2') { // 指値 → 成行
          switch (this.innerSasinariJyouken) {
            case ' ': // 指値/条件なし → 成行/条件なし
              newSasinariJyouken = 'N'
              break

            case 'I': // 指値/引指 → 成行/引成
              newSasinariJyouken = 'H'
              break

            default:
              break
          }
        }

        if (this.innerGyakusasiHouhou === '2' && newValue === '1') { // 成行 → 指値
          switch (this.innerSasinariJyouken) {
            case 'N': // 成行/条件なし → 指値/条件なし
              newSasinariJyouken = ' '
              break

            case 'H': // 成行/引成 → 指値/引指
              newSasinariJyouken = 'I'
              break

            default:
              break
          }
        }

        if (newSasinariJyouken === null) {
          this.innerPriceParam = { ...this.innerPriceParam, gyakusasiHouhou: newValue }
        } else {
          this.innerPriceParam = { ...this.innerPriceParam, gyakusasiHouhou: newValue, sasinariJyouken: newSasinariJyouken }
        }
      }
    },

    // 内部用注文単価(v-modelで更新するため)
    innerPrice: {
      get() {
        return this.priceParam.price
      },

      set(newValue) {
        this.innerPriceParam = { ...this.innerPriceParam, price: newValue }
      }
    },

    // ストップ安
    stopLow() {
      if (this.tradeCd === '1' || this.tradeCd === '') {
        return this.brandInfo.buyStopLow
      } else {
        return this.brandInfo.sellStopLow
      }
    },

    // ストップ高
    stopHigh() {
      if (this.tradeCd === '1' || this.tradeCd === '') {
        return this.brandInfo.buyStopHigh
      } else {
        return this.brandInfo.sellStopHigh
      }
    },
    // 価格最小値
    minPrice() {
      return 1
    },
    // 価格最大値
    maxPrice() {
      const stepTable = this.brandInfo?.orderPriceUnit
      if (stepTable) {
        const maxStep = stepTable.reduce((current, candidate) => {
          return current < Number(candidate.orderPriceUnit) ? Number(candidate.orderPriceUnit) : current
        }, 0)

        const splitedValue = String(maxStep).split('.')
        if (splitedValue[1] && splitedValue[1].length > 0) {
          return 99999999.9
        } else {
          return 9999999999
        }
      }

      return 9999999999
    },

    // 訂正前価格を文字列として出力
    beforeCorrectPriceStr() {
      // 執行方法
      const sasinariHouhouStr = this.$_out(this.$_getCodeValue('EXECUTE_METHOD', 1, this.beforeCorrectPriceParam.sasinariHouhou))

      // 指値
      if (this.beforeCorrectPriceParam.sasinariHouhou === '1') {
        // 執行条件
        let sasinariJyoukenStr = ''
        if (this.beforeCorrectPriceParam.sasinariJyouken !== ' ') { // 執行条件ありの場合
          sasinariJyoukenStr = this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.beforeCorrectPriceParam.sasinariJyouken)) + '/'
        }

        // 注文単価
        const priceStr = this.$_out(this.$_withCommaNoneZeroPadding(this.beforeCorrectPriceParam.price)) + '円'

        return sasinariHouhouStr + sasinariJyoukenStr + '/' + priceStr
      }

      // 成行
      if (this.beforeCorrectPriceParam.sasinariHouhou === '2') {
        let sasinariJyoukenStr = ''
        if (this.beforeCorrectPriceParam.sasinariJyouken !== 'N') {
          sasinariJyoukenStr = '\n' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 3, this.beforeCorrectPriceParam.sasinariJyouken))
        }

        return sasinariHouhouStr + sasinariJyoukenStr
      }

      // 逆指値
      if (this.beforeCorrectPriceParam.sasinariHouhou === '3') {
        // トリガー価格
        const triggerPriceStr = this.$_out(this.$_withCommaNoneZeroPadding(this.beforeCorrectPriceParam.triggerPrice)) + '円'

        // 以上 or 以下
        let stopOrderPriceText2Str = '-'
        if (this.beforeCorrectPriceParam.stopOrderPriceText2 === ' ') { // 指定なしの場合、取引種別から以上/以下を取得
          switch (this.tradeCd) {
            case '1':
              stopOrderPriceText2Str = '以上になった時点で'
              break
            case '2':
              stopOrderPriceText2Str = '以下になった時点で'
              break
            default:
              break
          }
        } else if (this.beforeCorrectPriceParam.stopOrderPriceText2 === '0' || this.beforeCorrectPriceParam.stopOrderPriceText2 === '1') { // 指定ありの場合
          stopOrderPriceText2Str = this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.beforeCorrectPriceParam.stopOrderPriceText2)) + 'になった時点で'
        }

        // 執行条件
        let sasinariJyoukenStr = ''
        if (this.beforeCorrectPriceParam.gyakusasiHouhou === '1') { // 指値
          if (this.beforeCorrectPriceParam.sasinariJyouken !== ' ') { // 執行条件が指定されている場合
            sasinariJyoukenStr = '\n' + this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 2, this.beforeCorrectPriceParam.sasinariJyouken))
          }
        } else if (this.beforeCorrectPriceParam.gyakusasiHouhou === '2') { // 成行
          sasinariJyoukenStr = this.$_out(this.$_getCodeValue('LIMIT_MARKET_TYPE', 1, this.beforeCorrectPriceParam.sasinariJyouken))
        }

        // 注文単価
        let priceStr = ''
        if (this.beforeCorrectPriceParam.gyakusasiHouhou === '1') { // 指値
          priceStr = '/' + this.$_out(this.$_withCommaNoneZeroPadding(this.beforeCorrectPriceParam.price)) + '円'
        }

        return '現在値が' + triggerPriceStr + stopOrderPriceText2Str + sasinariJyoukenStr + priceStr + 'で執行'
      }

      return '-'
    },

    // 価格/執行方法 - 執行方法の表示に必要な情報
    inputParamForSasinariHouhou() {
      let showElement = false
      let selectPattern = ''
      let displayPattern = ''
      let disabled = false

      // OCO注文の場合のOCO1
      if (this.orderKind === '2' || (this.orderKind === '4' && this.propPrefix === 'oco1')) {
        showElement = true
        selectPattern = 3
        displayPattern = 1
        disabled = true
      } else {
        // OCO注文以外で、変更前の執行方法が指値の場合
        if (this.beforeCorrectPriceParam.sasinariHouhou === '1') {
          showElement = true
          selectPattern = 2
          displayPattern = 1
          disabled = this.innerSasinariJyouken === 'F'
        }

        // OCO注文以外で、変更前の執行方法が成行の場合
        if (this.beforeCorrectPriceParam.sasinariHouhou === '2') {
          showElement = true
          selectPattern = 2
          displayPattern = 1
        }

        // OCO注文以外で、変更前の執行方法が逆指値の場合
        if (this.beforeCorrectPriceParam.sasinariHouhou === '3') {
          showElement = true
          selectPattern = 5
          displayPattern = 1
          disabled = true
        }
      }

      return {
        showElement: showElement,
        selectPattern: selectPattern,
        displayPattern: displayPattern,
        disabled: disabled
      }
    },

    inputParamForSasinariJyouken() {
      let showElement = false
      let selectPattern = ''
      let displayPattern = ''
      let disabled = false

      // OCO注文の場合のOCO1
      if (this.orderKind === '2') {
        showElement = true
        selectPattern = 6
        displayPattern = 2
        disabled = true
      } else {
        // OCO注文以外で、変更前の執行方法が指値の場合
        if (this.innerSasinariHouhou === '1') {
          showElement = true
          selectPattern = 2
          displayPattern = 2
          disabled = true
        }

        // OCO注文以外で、変更前の執行方法が成行の場合
        if (this.innerSasinariHouhou === '2') {
          showElement = true
          selectPattern = 3
          displayPattern = 3
          disabled = true
        }
      }

      return {
        showElement: showElement,
        selectPattern: selectPattern,
        displayPattern: displayPattern,
        disabled: disabled
      }
    },

    // 価格を未入力時のステップ
    initialStepPrice() {
      // 注文単価
      const price = Number(this.beforeCorrectPriceParam?.price)
      if (price !== 0 && !isNaN(price)) {
        return price
      }

      // CC013.現在値
      const currentPrice = Number(this.brandInfo?.currentPrice)
      if (currentPrice !== 0 && !isNaN(currentPrice)) {
        return currentPrice
      }

      // CC013.基準価格
      const basePrice = Number(this.brandInfo?.basePrice)
      if (basePrice !== 0 && !isNaN(basePrice)) {
        return basePrice
      }

      return 0
    },

    // 発火条件価格を未入力時のステップ
    initialStepTrigger() {
      // 発火条件価格
      const triggerPrice = Number(this.beforeCorrectPriceParam?.triggerPrice)
      if (triggerPrice !== 0 && !isNaN(triggerPrice)) {
        return triggerPrice
      }

      // CC013.現在値
      const currentPrice = Number(this.brandInfo?.currentPrice)
      if (currentPrice !== 0 && !isNaN(currentPrice)) {
        return currentPrice
      }

      // CC013.基準価格
      const basePrice = Number(this.brandInfo?.basePrice)
      if (basePrice !== 0 && !isNaN(basePrice)) {
        return basePrice
      }

      return 0
    },

    // 発火条件価格（逆指値）後文言(入力欄)
    triggerPriceSuffix() {
      let stopOrderPriceText2Str = '-'
      if (this.priceParam.stopOrderPriceText2 === ' ') { // 指定なしの場合、取引種別から以上/以下を取得
        switch (this.tradeCd) {
          case '1':
            stopOrderPriceText2Str = '以上になった時点で'
            break
          case '2':
            stopOrderPriceText2Str = '以下になった時点で'
            break
          default:
            break
        }
      } else if (this.priceParam.stopOrderPriceText2 === '0' || this.priceParam.stopOrderPriceText2 === '1') { // 指定ありの場合
        stopOrderPriceText2Str = this.$_out(this.$_getCodeValue('LATEST_TRIGGER_ZONE', 1, this.priceParam.stopOrderPriceText2)) + 'になった時点で'
      }

      return stopOrderPriceText2Str
    }
  },
  methods: {
    getPropWithPrefix(name) {
      if (this.propPrefix) {
        const camelName = name.charAt(0).toUpperCase() + name.slice(1)
        return this.propPrefix + camelName
      } else {
        return name
      }
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
.order-input {
  width: 80%;
}
.header-button {
  display: flex;
  justify-content: flex-end;
  padding: 0 0.5rem 0.2rem 1rem;
}
.form-radio {
  width: 4rem;
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
.confirm-dialog {
  background-color: rgb(254, 240, 240);
  width: 200px;
}
.amount-text {
  margin-left: 5.8rem;
}
.item {
  margin-left: 10px;
}
:deep(.charge-type) .el-form-item__label {
  color: #f00;
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
.custom-loading-text {
  color: #fff;
  font-size: 20px;
}
:deep(.el-form-item__error) {
  margin-left: 2.5rem;
}
.input-execution-form {
  width: 105px;
}
.input-method-form {
  margin-left: 1rem;
  width: 115px;
}
.input-price-form {
  margin-left: 1rem;
  width: 270px;
}
:deep(input) {
  height: 40px;
}
:deep(.correction-dialog) .el-dialog {
  width: 1100px;
}
</style>
