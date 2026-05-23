<template>
  <div>
    <el-row
      style="padding: 0.5rem 0"
    >
      <div style="width: 100%;">
        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="7"
            :offset="1"
          >
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">現在値:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__current-price">
                  <span class="brand-price-info__current-price--price __bold">{{ brandInfo.currentPrice }} </span>
                  <span
                    :class="[tickColor()]"
                    class="brand-price-info__current-price--tick __bold"
                  >{{ tickLabel() }} </span>
                  <span class="brand-price-info__current-price--flag __bold">{{ flagLabel() }}</span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">始値:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.start }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    {{ brandInfo.startTime }}
                  </span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">高値:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.high }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    {{ brandInfo.highTime }}
                  </span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">安値:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.low }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    {{ brandInfo.lowTime }}
                  </span>
                </div>
              </span>
            </div>
          </el-col>
        </el-row>
        <el-row
          :gutter="20"
          style="padding-top: 0.5rem;"
        >
          <el-col
            :span="7"
            :offset="1"
          >
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">{{ market === '7' ? '基準値比:' : '前日比:' }}</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__day-before-ratio">
                  <span
                    class="brand-price-info__day-before-ratio--diff"
                    :class="[ratioColor()]"
                  >{{ brandInfo.diff }}</span>
                  <span class="brand-price-info__day-before-ratio--ratio">
                    <span>(</span><span :class="[ratioColor()]">{{ brandInfo.ratio }}</span><span>)</span>
                  </span>
                  <span class="brand-price-info__day-before-ratio--date-time">
                    ({{ brandInfo.updateDate }} {{ brandInfo.updateTime4 }})
                  </span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">{{ market === '7' ? '基準値:' : '前日終値:' }}</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.last }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    {{ brandInfo.lastDate }}
                  </span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">売買代金:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.buySellPrice }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    (千円)
                  </span>
                </div>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="brand-price-info__container">
              <span class="brand-price-info-item__header">出来高:</span>
              <span class="brand-price-info-item__value">
                <div class="brand-price-info__other-index">
                  <span class="brand-price-info__other-index--value">
                    {{ brandInfo.volume }}
                  </span>
                  <span class="brand-price-info__other-index--appendix">
                    {{ brandInfo.volumeTime }}
                  </span>
                </div>
              </span>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-row>

    <ifa-requester
      id="ifaBrandPriceInfo"
      action-id="CC013#A002"
      action-type="requestAction"
      :request-model="ifaBrandPriceInfoRequestModel"
      @response-handler="responseHandlerA002($event)"
      @response-error-handler="responseErrorHandlerA002($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { IfaBrandPriceInfoRequestModel } from './IfaBrandPriceInfoRequestModel'
export default {
  props: {
    brandCode: { type: String, required: true },
    market: { type: String, required: true }
  },
  emits: ['change'],
  data() {
    return {
      brandInfo: {
        brandCode: '',
        market: '',
        currentPrice: '-',
        tick: ' ',
        currentFlag: ' ',
        start: '-',
        startTime: '(--:--)',
        high: '-',
        highTime: '(--:--)',
        low: '-',
        lowTime: '(--:--)',
        diff: '-',
        diffRaw: '',
        ratio: '-%',
        updateDate: '--/--/-- ',
        updateTime4: '--:--',
        last: '-',
        lastDate: '(--/--/--)',
        buySellPrice: '-',
        volume: '-',
        volumeTime: '(--:--)',
        unit: '',
        sellStopHigh: '',
        sellStopLow: '',
        buyStopHigh: '',
        buyStopLow: '',
        baseDate: '',
        orderPriceUnit: [],
        basePrice: ''
      },
      ifaBrandPriceInfoRequestModel: {},
      processing: false
    }
  },
  methods: {
    // 前日比の色を算出する
    ratioColor() {
      const n = Number(this.brandInfo.diffRaw)
      return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black __bold'
    },
    // 上下矢印(現在値ティック)に表示するラベルを返す
    tickLabel() {
      if (this.brandInfo.currentPrice === ' ' || this.brandInfo.currentPrice === '-' || this.brandInfo.currentPrice === '0') {
        // 現在値＝｢" "、-、0｣の場合、表示しない
        return ' '
      }
      switch (this.brandInfo.tick) {
        case '1':
          // 1=↑
          return '↑'
        case '2':
          // 2=↓
          return '↓'
        case ' ':
        case '-':
        case '0':
          // [" ", -, 0]=''
          return ' '
        default:
          // 未指定時=スペース
          return ' '
      }
    },
    // 上下矢印(現在値ティック)に表示する色を返す
    tickColor() {
      if (this.brandInfo.currentPrice === ' ' || this.brandInfo.currentPrice === '-' || this.brandInfo.currentPrice === '0') {
        return '__black'
      }
      switch (this.brandInfo.tick) {
        case '1':
          return 'font-color__plus __bold'
        case '2':
          return 'font-color__minus __bold'
        default:
          return '__black __bold'
      }
    },
    flagLabel() {
      if (this.brandInfo.currentPrice === ' ' || this.brandInfo.currentPrice === '-' || this.brandInfo.currentPrice === '0') {
        return ' '
      }
      switch (this.brandInfo.currentFlag) {
        case ' ':
        case '-':
          return ' '
        case '#':
        case 'C':
        case '*':
        case '!':
        case 'X':
        case 'H':
        case 'L':
        case 'A':
        case 'B':
        case 'E':
        default:
          return this.brandInfo.currentFlag
      }
    },
    initData() {
      this.brandInfo.brandCode = ''
      this.brandInfo.market = ''
      this.brandInfo.currentPrice = '-'
      this.brandInfo.last = '-'
      this.brandInfo.lastDate = '(--/--/--)'
      this.brandInfo.diff = '-'
      this.brandInfo.diffRaw = ''
      this.brandInfo.ratio = '-%'
      this.brandInfo.updateDate = '--/--/-- '
      this.brandInfo.updateTime4 = '--:--'
      this.brandInfo.start = '-'
      this.brandInfo.startTime = '(--:--)'
      this.brandInfo.low = '-'
      this.brandInfo.lowTime = '(--:--)'
      this.brandInfo.high = '-'
      this.brandInfo.highTime = '(--:--)'
      this.brandInfo.buySellPrice = '-'
      this.brandInfo.volume = '-'
      this.brandInfo.volumeTime = '(--:--)'
      this.brandInfo.tick = ' '
      this.brandInfo.currentFlag = ' '
      this.brandInfo.sellStopHigh = ''
      this.brandInfo.sellStopLow = ''
      this.brandInfo.buyStopHigh = ''
      this.brandInfo.buyStopLow = ''
      this.brandInfo.baseDate = ''
      this.brandInfo.unit = ''
      this.brandInfo.orderPriceUnit = []
      this.brandInfo.basePrice = ''
      // this.$emit('change', this.brandinfo)
    },
    setData(brandData) {
      // エラー、データなしの場合は値をセットせず初期値のままとする
      if (brandData.brandCode != null) this.brandInfo.brandCode = brandData.brandCode
      if (brandData.market != null) this.brandInfo.market = brandData.market
      if (brandData.currentPrice != null && Number(brandData.currentPrice) !== 0) {
        this.brandInfo.currentPrice = this.$_addComma(brandData.currentPrice)
      }
      if (brandData.last != null && Number(brandData.last) !== 0) {
        this.brandInfo.last = this.$_addComma(brandData.last)
      }
      if (brandData.lastDate) this.brandInfo.lastDate = '(' + this.$_getFormattedDateValue(brandData.lastDate, 'date6') + ')'
      if (brandData.diff != null && brandData.diff !== '+0') {
        this.brandInfo.diff = this.$_addComma(brandData.diff)
      }
      if (brandData.diff != null) this.brandInfo.diffRaw = brandData.diff
      if (brandData.ratio != null && brandData.ratio !== '+0') {
        this.brandInfo.ratio = brandData.ratio + '%'
      }
      if (brandData.updateDate) this.brandInfo.updateDate = this.$_getFormattedDateValue(brandData.updateDate, 'date6') + ' '
      if (brandData.updateTime4) this.brandInfo.updateTime4 = this.$_getFormattedTimeValue(brandData.updateTime4, 'time4')
      if (brandData.start != null && Number(brandData.start) !== 0) {
        this.brandInfo.start = this.$_addComma(brandData.start)
      }
      if (brandData.startTime) this.brandInfo.startTime = '(' + this.$_getFormattedTimeValue(brandData.startTime, 'time4') + ')'
      if (brandData.low != null && Number(brandData.low) !== 0) {
        this.brandInfo.low = this.$_addComma(brandData.low)
      }
      if (brandData.lowTime) this.brandInfo.lowTime = '(' + this.$_getFormattedTimeValue(brandData.lowTime, 'time4') + ')'
      if (brandData.high != null && Number(brandData.high) !== 0) {
        this.brandInfo.high = this.$_addComma(brandData.high)
      }
      if (brandData.highTime) this.brandInfo.highTime = '(' + this.$_getFormattedTimeValue(brandData.highTime, 'time4') + ')'
      if (brandData.buySellPrice != null && Number(brandData.buySellPrice) !== 0) {
        this.brandInfo.buySellPrice = this.$_addComma(brandData.buySellPrice)
      }
      if (brandData.volume != null && Number(brandData.volume) !== 0) {
        this.brandInfo.volume = this.$_addComma(brandData.volume)
      }
      if (brandData.updateTime41) this.brandInfo.volumeTime = '(' + this.$_getFormattedTimeValue(brandData.updateTime41, 'time4') + ')'
      if (brandData.tick != null) this.brandInfo.tick = brandData.tick
      if (brandData.currentFlag != null) this.brandInfo.currentFlag = brandData.currentFlag
      if (brandData.sellStopHigh != null) this.brandInfo.sellStopHigh = brandData.sellStopHigh
      if (brandData.sellStopLow != null) this.brandInfo.sellStopLow = brandData.sellStopLow
      if (brandData.buyStopHigh != null) this.brandInfo.buyStopHigh = brandData.buyStopHigh
      if (brandData.buyStopLow != null) this.brandInfo.buyStopLow = brandData.buyStopLow
      if (brandData.baseDate != null) this.brandInfo.baseDate = this.$_getFormattedDateValue(brandData.baseDate, 'date6')
      if (brandData.unit != null) this.brandInfo.unit = brandData.unit
      if (brandData.orderPriceUnit != null) this.brandInfo.orderPriceUnit = brandData.orderPriceUnit
      if (brandData.basePrice != null) this.brandInfo.basePrice = brandData.basePrice
      this.emitChange(brandData)
    },
    // 互換性維持のため､`change` イベントで親に渡すデータは現状のままにする
    emitChange(brandData) {
      const brandInfo = {}
      brandInfo.brandCode = brandData.brandCode
      brandInfo.market = brandData.market
      brandInfo.currentPrice = brandData.currentPrice
      brandInfo.last = brandData.last
      brandInfo.lastDate = ' (' + this.$_getFormattedDateValue(brandData.lastDate, 'date6') + ')'
      brandInfo.diff = brandData.diff
      brandInfo.ratio = brandData.ratio + '%'
      brandInfo.updateDate = this.$_getFormattedDateValue(brandData.updateDate, 'date6') + ' '
      brandInfo.updateTime4 = this.$_getFormattedTimeValue(brandData.updateTime4, 'time4')
      brandInfo.start = this.$_addComma(brandData.start) + ' (' + this.$_getFormattedTimeValue(brandData.startTime, 'time4') + ')'
      brandInfo.low = this.$_addComma(brandData.low) + ' (' + this.$_getFormattedTimeValue(brandData.lowTime, 'time4') + ')'
      brandInfo.high = this.$_addComma(brandData.high) + ' (' + this.$_getFormattedTimeValue(brandData.highTime, 'time4') + ')'
      brandInfo.buySellPrice = this.$_addComma(brandData.buySellPrice) + ' (千円)'
      brandInfo.volume = this.$_addComma(brandData.volume) + ' (' + this.$_getFormattedTimeValue(brandData.updateTime41, 'time4') + ')'
      brandInfo.tick = brandData.tick
      brandInfo.currentFlag = brandData.currentFlag
      brandInfo.sellStopHigh = brandData.sellStopHigh
      brandInfo.sellStopLow = brandData.sellStopLow
      brandInfo.buyStopHigh = brandData.buyStopHigh
      brandInfo.buyStopLow = brandData.buyStopLow
      brandInfo.baseDate = this.$_getFormattedDateValue(brandData.baseDate, 'date6')
      brandInfo.unit = brandData.unit
      brandInfo.orderPriceUnit = brandData.orderPriceUnit
      brandInfo.basePrice = brandData.basePrice
      this.$emit('change', brandInfo)
    },
    updateRequest() {
      if (this.processing) return
      this.processing = true

      this.$nextTick(() => {
        const requestData = {
          brandCode: this.brandCode,
          market: this.market
        }
        this.ifaBrandPriceInfoRequestModel = new IfaBrandPriceInfoRequestModel(requestData)
        this.$nextTick(() => {
          this.$el.querySelector('#ifaBrandPriceInfo').click()
        })
      })
    },
    responseHandlerA002(data) {
      if (data.errorLevel >= 0) {
        this.initData()
        this.setData(data.dataList[0])
      }
      this.processing = false
    },
    responseErrorHandlerA002(data) {
      this.$_logError('IfaBrandPriceInfo: responseErrorHandlerA002', data)
      this.processing = false
    }
  }
}

</script>
<style lang="scss" scoped>
.brand-price-info__container {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
  border-bottom: 1px solid #bfcbd9;
}
.brand-price-info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  line-height: 25px;
  white-space: nowrap;
}
.brand-price-info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  line-height: 25px;
  white-space: nowrap;
}
.__bold {
  font-weight: bold;
}
.__black {
  color: #18181A;
}
.brand-price-info__current-price {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
  align-items: flex-start;
  &--price {
    flex: 0 0 30%;
    text-align: right;
  }
  &--tick {
    flex: 0 0 12.5%;
    text-align: center;
  }
  &--flag {
    flex: 0 0 12.5%;
    text-align: center;
  }
}
.brand-price-info__day-before-ratio {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
  text-align: right;
  &--diff {
    flex: 0 0 30%;
  }
  &--ratio {
    flex: 0 0 auto;
    font-size: 12px;
    margin-left: 0.5rem;
  }
  &--date-time {
    flex: 1 0 auto;
    font-size: 12px;
    margin-right: 0.5rem;
  }
}
.brand-price-info__other-index {
  display: flex;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
  text-align: right;
  &--value {
    flex: 0 0 50%;
  }
  &--appendix {
    flex: 1 0 auto;
    font-size: 12px;
  }
}
</style>
