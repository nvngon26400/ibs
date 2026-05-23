<template>
  <div>
    <el-row :gutter="20">
      <el-card style="background-color: #eee; margin: 1rem 10px; padding: 10px 20px">
        <el-form>
          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col :span="13">
              <div class="info_xs">
                <span class="info-item__header __left">銘柄:</span>
                <span class="info-item__value __left">{{ getBrand() }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">市場:</span>
                <span class="info-item__value __left">{{ getMarket() }}</span>
              </div>
            </el-col>
            <el-col
              :span="5"
              class="update-button"
            >
              <ifa-button
                class="TrendCharts"
                small
                text=" 詳細"
                color="primary"
                action-type="originalAction"
                @app-action-handler="displayStockBoard"
              ></ifa-button>
              <ifa-button
                class="RefreshRight"
                small
                text=" 更新"
                color="primary"
                action-type="originalAction"
                @app-action-handler="updateLoading"
              ></ifa-button>
            </el-col>
          </el-row>
          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col :span="7">
              <div class="info_xs">
                <span class="info-item__header __left">現在値:</span>
                <span class="info-item__value __right">
                  <el-row v-if="brandinfo.brandCode !== ''">
                    <span>{{ brandinfo.desc.current }} </span>
                    <span
                      :class="[tickColor()]"
                      class="__bold"
                    >{{ tickLabel() }} </span>
                    <span class="__bold">{{ flagLabel() }}</span>
                  </el-row>
                  <el-row
                    v-else
                    class="__color_empty __center"
                  >
                    <span>-</span>
                  </el-row>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">前日終値:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.last }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">始値:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.start }}</span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">安値:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.low }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row
            :gutter="20"
            style="padding-top: 0.5rem;"
          >
            <el-col :span="7">
              <div class="info_xs">
                <span class="info-item__header __left">前日比:</span>
                <span class="info-item__value __right">
                  <el-row>
                    <span :class="[ratioColor()]">{{ brandinfo.desc.diff }}</span>
                    <span> (</span>
                    <span :class="[ratioColor()]">{{ brandinfo.desc.ratio }}</span>
                    <span>) </span>
                    <span>{{ brandinfo.desc.diffDateTime }}</span>
                  </el-row>
                </span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">売買代金:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.value }}</span>
              </div>
            </el-col>
            <el-col :span="5">
              <div class="info_xs">
                <span class="info-item__header __left">高値:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.high }}</span>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="info_xs">
                <span class="info-item__header __left">出来高:</span>
                <span class="info-item__value __right">{{ brandinfo.desc.volume }}</span>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
    </el-row>

  </div>
</template>

<script>
export default {
  props: {
    propBrandCode: { type: String, required: true },
    propMarket: { type: String, required: true },
    brandInfoGetFlag: { type: Boolean, required: true }
  },
  emits: ['change', 'display-stock-board'],
  data() {
    return {
      isVisible: false,
      market: '',
      brandinfo: {
        brandCode: '',
        brandName: '',
        desc: {
          current: '',
          last: '',
          diff: '',
          ratio: '',
          diffDateTime: '',
          start: '',
          low: '',
          high: '',
          value: '',
          volume: ''
        },
        tick: '',
        flag: ''
      },
      brandData: [{
        code: '2002',
        name: 'XX食品',
        value: '2002 XX食品',
        desc: {
          unit: 100,
          current: 1501,
          last: 1488,
          lastDate: '21/09/13',
          diff: '+13',
          ratio: '+0.87',
          diffDateTime: '21/09/14 15:00',
          limitDate: '21/09/14',
          limitHigh: 1488 + 300,
          limitLow: 1488 - 300,
          start: 1492,
          startTime: '09:00',
          low: 1464,
          lowTime: '09:01',
          high: 1511,
          highTime: '12:30',
          value: 279840,
          volume: 186500,
          volumeTime: '15:00',
          validMarkets: [true, true, true, true, true, false, false]
        },
        tick: '1',
        flag: 'C'
      }, {
        code: '2811',
        name: 'カゴメ',
        value: '2811 カゴメ',
        desc: {
          unit: 100,
          current: 2951,
          last: 3080,
          lastDate: '21/09/13',
          diff: '-129',
          ratio: '-4.18',
          diffDateTime: '21/09/14 15:00',
          limitDate: '21/09/14',
          limitHigh: 3080 + 700,
          limitLow: 3080 - 700,
          start: 3085,
          startTime: '09:00',
          low: 3080,
          lowTime: '14:46',
          high: 3115,
          highTime: '11:11',
          value: 5488642,
          volume: 1829500,
          volumeTime: '15:00',
          validMarkets: [true, true, true, true, true, true, true]
        },
        tick: '1',
        flag: 'C'
      }, {
        code: '4099',
        name: 'yy薬品',
        value: '4099 yy薬品',
        desc: {
          unit: 1000,
          current: 473,
          last: 426,
          lastDate: '21/09/13',
          diff: '+47',
          ratio: '+11.03',
          diffDateTime: '21/09/14 15:00',
          limitDate: '21/09/14',
          limitHigh: 426 + 80,
          limitLow: 426 - 80,
          start: 431,
          startTime: '09:00',
          low: 430,
          lowTime: '09:01',
          high: 476,
          highTime: '12:34',
          value: 46132489,
          volume: 97531000,
          volumeTime: '15:00',
          validMarkets: [true, true, false, false, false, false, true]
        },
        tick: '1',
        flag: 'C'
      }, {
        code: '6001',
        name: '〇〇電子',
        value: '6001 〇〇電子',
        desc: {
          unit: 100,
          current: 7952,
          last: 7897,
          lastDate: '21/09/13',
          diff: '+55',
          ratio: '+0.69',
          diffDateTime: '21/09/14 15:00',
          limitDate: '21/09/14',
          limitHigh: 7897 + 1500,
          limitLow: 7897 - 1500,
          start: 7900,
          startTime: '09:00',
          low: 7864,
          lowTime: '11:22',
          high: 8011,
          highTime: '13:31',
          value: 5813540351,
          volume: 73126200,
          volumeTime: '15:00',
          validMarkets: [false, true, true, true, true, false, true]
        },
        tick: '1',
        flag: 'C'
      }, {
        code: '3069',
        name: 'ＪＦＬＡホールディングス',
        value: '3069 ＪＦＬＡホールディングス',
        desc: {
          unit: 100,
          current: 7952,
          last: 7897,
          lastDate: '21/09/13',
          diff: '+55',
          ratio: '+0.69',
          diffDateTime: '21/09/14 15:00',
          limitDate: '21/09/14',
          limitHigh: 2000 + 1500,
          limitLow: 2000 - 1500,
          start: 7900,
          startTime: '09:00',
          low: 7864,
          lowTime: '11:22',
          high: 8011,
          highTime: '13:31',
          value: 5813540351,
          volume: 73126200,
          volumeTime: '15:00',
          validMarkets: [false, true, true, true, true, false, true]
        },
        tick: '1',
        flag: 'C'
      }]
    }
  },
  mounted() {
    const tmp = this.brandData.filter(e => e.code === this.propBrandCode)
    this.brandinfo.brandCode = tmp[0].code
    this.brandinfo.brandName = tmp[0].name
    this.brandinfo.desc.current = this.addComma(tmp[0].desc.current)
    this.brandinfo.desc.last = this.addComma(tmp[0].desc.last) + ' (' + tmp[0].desc.lastDate + ')'
    this.brandinfo.desc.diff = tmp[0].desc.diff
    this.brandinfo.desc.ratio = tmp[0].desc.ratio
    this.brandinfo.desc.diffDateTime = tmp[0].desc.diffDateTime
    this.brandinfo.desc.start = this.addComma(tmp[0].desc.start) + ' (' + tmp[0].desc.startTime + ')'
    this.brandinfo.desc.low = this.addComma(tmp[0].desc.low) + ' (' + tmp[0].desc.lowTime + ')'
    this.brandinfo.desc.high = this.addComma(tmp[0].desc.high) + ' (' + tmp[0].desc.highTime + ')'
    this.brandinfo.desc.value = this.addComma(tmp[0].desc.value) + ' (千円)'
    this.brandinfo.desc.volume = this.addComma(tmp[0].desc.volume) + ' (' + tmp[0].desc.volumeTime + ')'
    this.brandinfo.tick = tmp[0].tick
    this.brandinfo.flag = tmp[0].flag
    this.market = this.propMarket
    const param = tmp[0]
    this.$emit('change', param)
  },
  methods: {
    getBrand() {
      return this.brandinfo.brandCode + '  ' + this.brandinfo.brandName
    },
    getMarket: function() {
      if (this.market === '0') {
        return 'SOR'
      } else if (this.market === '1') {
        return '東証'
      } else if (this.market === '2') {
        return '名証'
      } else if (this.market === '3') {
        return '福証'
      } else if (this.market === '4') {
        return '札証'
      } else if (this.market === '5') {
        return '単元未満'
      } else if (this.market === '6') {
        return 'PTS'
      } else return '-'
    },
    addComma(num) {
      return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,')
    },
    updateLoading() {
      return
    },
    // 前日比の色を算出する
    ratioColor() {
      const n = Number(this.brandinfo.desc.diff)
      return n > 0 ? '__red' : n < 0 ? '__blue' : '__black'
    },
    // 上下矢印(現在値ティック)に表示するラベルを返す
    tickLabel() {
      if (this.brandinfo.desc.current === '△' || this.brandinfo.desc.current === '-' || this.brandinfo.desc.current === '０') {
        // 現在値＝｢△、-、０｣の場合、表示しない
        return ''
      }
      switch (this.brandinfo.tick) {
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
          return ''
        default:
          // 未指定時=スペース
          return '　'
      }
    },
    // 上下矢印(現在値ティック)に表示する色を返す
    tickColor() {
      if (this.brandinfo.desc.current === '△' || this.brandinfo.desc.current === '-' || this.brandinfo.desc.current === '０') {
        return '__black'
      }
      switch (this.brandinfo.tick) {
        case '1':
          return '__red __bold'
        case '2':
          return '__blue __bold'
        default:
          return '__black __bold'
      }
    },
    flagLabel() {
      if (this.brandinfo.desc.current === '△' || this.brandinfo.desc.current === '-' || this.brandinfo.desc.current === '０') {
        return ''
      }
      switch (this.brandinfo.flag) {
        case ' ':
        case '-':
          return ''
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
          return this.brandinfo.flag
      }
    },
    displayStockBoard() {
      this.$emit('display-stock-board')
    }
  }
}
</script>
<style lang="scss" scoped>
.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
  line-height: 25px;
}
.__right {
  text-align: right;
  padding-right: 0.5rem;
}
.info_xs {
  display: grid;
  width: 100%;
  grid-template-columns: 4rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #18181A;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #18181A;
  font-size: 14px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.brand-name {
  width: 100%;
  color: #18181A;
  font-weight: bold;
  font-size: 14px;
  padding-left: 0.5rem;
}
.__bold {
  font-weight: bold;
}
.__red {
  color: red;
}
.__blue {
  color: blue;
}
.__black {
  color: #18181A;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0.5rem;
  top: 0;
}
</style>
