<template>
  <div>
    <el-row>
      <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
        <el-row>
          <!-- 国籍 -->
          <el-col
            :span="6"
            style="padding-left: 1rem;"
          >
            <span
              class="search_header"
            >国籍：</span>
            <el-select
              v-if="!isCodeLock"
              v-model="countryCode"
              :disabled="isMargin || (isMargin === false && code !== '')"
              :readonly="isCodeLock"
              placeholder="国籍"
              size="small"
            >
              <el-option
                v-for="(val, key) in countryList"
                :key="key"
                :label="val"
                :value="key"
              ></el-option>
            </el-select>
            <span
              v-else
              class="search_header"
            >{{ getCountryName }}</span>
          </el-col>

          <!-- 銘柄 -->
          <el-col
            :span="3"
          >
            <span
              class="search_header"
            >銘柄：</span>
            <el-input
              v-if="!isCodeLock"
              v-model="searchWord"
              :disabled="tradeType===''"
              :readonly="isCodeLock"
              clearable
              style="width: 120px;"
              size="small"
              @clear="resetAll(true)"
              @input="handleSearchWordInput"
            ></el-input>
            <span
              v-else
              class="search_header"
            >[{{ searchWord }}]</span>
          </el-col>

          <!-- 株価表示 -->
          <el-col
            :span="3"
          >
            <span
              v-if="code === ''"
              style="padding-left: 0.5rem;"
            >
              <ifa-button
                text="株価表示"
                :small="true"
                action-type="originalAction"
                @app-action-handler="stockShow()"
              ></ifa-button>
            </span>
          </el-col>

          <!-- 銘柄検索 -->
          <el-col
            v-if="code === ''"
            :span="3"
          >
            <div style="padding-top: 0.4rem;">
              <el-link
                :underline="false"
                disable-transitions
                :disabled="tradeType===''"
                @click="openDialog"
              >銘柄検索</el-link>
            </div>
          </el-col>
          <!-- 銘柄名 -->
          <el-col
            v-if="code !== ''"
            :span="4"
          >
            <span
              class="search_header"
            >{{ name }}</span>
          </el-col>
          <!-- 上場市場 -->
          <el-col
            v-if="code !== ''"
            :span="4"
          >
            <div>
              <span class="search_header __left">上場市場:</span>
              <span class="search_header">{{ market }}</span>
            </div>
          </el-col>

          <!-- 取引注意情報・株価チャート -->
          <el-col
            v-if="code !== ''"
            style="text-align:right;"
            :span="5"
          >
            <el-icon><Warning></Warning></el-icon>
            <ifa-external-text-link
              :url="tradeLimit"
              :label="'取引注意情報'"
            ></ifa-external-text-link>
            <span style="padding-left: 8px;">
              <ifa-external-text-link
                :url="''"
                :label="'株価チャート'"
              ></ifa-external-text-link>
            </span>
          </el-col>

          <!-- 更新 -->
          <el-col
            v-if="code !== ''"
            :span="2"
            class="update-button"
          >
            <ifa-button
              text="更新"
              :small="true"
              icon="RefreshRight"
              action-type="originalAction"
              @app-action-handler="updateLoading"
            ></ifa-button>
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
            <div class="info_xs">
              <span class="info-item__header __left">現在値:</span>
              <span class="info-item__current __right">
                <el-row v-if="code !== ''">
                  <el-col :span="12">
                    <span>{{ desc.current }}</span>
                  </el-col>
                  <el-col :span="3">
                    <span
                      :class="[tickColor]"
                      class="__bold"
                    >{{ tickLabel }}</span>
                  </el-col>
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
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">始値:</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >{{ desc.start }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--:--)</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">高値:</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >{{ desc.high }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--:--)</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">安値:</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >{{ desc.low }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--:--)</span>
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
            <div class="info_xs">
              <span class="info-item__header __left">前日比</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >
                <el-row>
                  <el-col :span="12">
                    <span :class="[ratioColor(desc.diff)]">{{ desc.diff }}</span>
                    <span> (</span>
                    <span :class="[ratioColor(desc.diff)]">{{ desc.ratio }}</span>
                    <span>)</span>
                  </el-col>
                  <el-col :span="12">
                    <span>{{ desc.diffDateTime }}</span>
                  </el-col>
                </el-row>
              </span>
              <span
                v-else
                class="info-item__empty __right"
              >
                <el-row>
                  <el-col :span="12">
                    <span>( -%)</span>
                  </el-col>
                  <el-col :span="12">
                    <span>(--/--/-- --:--)</span>
                  </el-col>
                </el-row>
              </span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">前日終値:</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >{{ desc.last }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--/--/--)</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">出来高:</span>
              <span
                v-if="code !== ''"
                class="info-item__value __right"
              >{{ desc.volume }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--:--)</span>
            </div>
          </el-col>
          <el-col
            :span="6"
            style="text-align: right;"
          >
            <span class="data-provider">© REFINITIV</span>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
    <!--銘柄検索ダイアログ-->
    <ifa-foreign-stock-brand-search-popup
      ref="ifaForeignStockBrandSearchPopup"
      :is-margin="isMargin"
      :is-visible="brandSerachIsVisible"
      :order-type="orderType"
      @close-modal="handleCloseModal"
      @result="handleResult"
    ></ifa-foreign-stock-brand-search-popup>
  </div>
</template>

<script>
import IfaForeignStockBrandSearchPopup from '@/views/common/IfaForeignStockBrandSearchPopup'
import { IfaForeignStockBrandSearchPopupA003RequestModel } from '@/views/common/js/IfaForeignStockBrandSearchPopupA003RequestModel.js'
export default {
  components: {
    IfaForeignStockBrandSearchPopup
  },
  props: {
    // 銘柄コード変更不可 true、それ以外：false
    isCodeLock: { type: Boolean, default: false },
    // 信用取引：true, それ以外：false
    isMargin: { type: Boolean, default: false },
    // 米株信用新規売:0, それ以外:1
    orderType: { type: String, default: '1' },
    // 銘柄入力可能:1 入力不可：1以外
    enableInput: { type: String, default: '1' },
    // 取引区分
    tradeType: { type: String, default: '-1' }
  },
  emits: ['change'],
  data() {
    return {
      country: '',
      name: '',
      code: '',
      countryCode: 'US',
      market: '',
      desc: {
        current: '',
        last: '',
        diff: '-',
        ratio: '-％',
        diffDateTime: '',
        start: '',
        low: '',
        high: '',
        volume: '',
        tick: ''
      },
      searchWord: '',
      oldSearchWord: '',
      brandSerachIsVisible: false,
      countryList: {
        'US': '米国',
        'CN': '中国',
        'KR': '韓国',
        'RU': 'ロシア',
        'VN': 'ベトナム',
        'ID': 'インドネシア',
        'SG': 'シンガポール',
        'TH': 'タイ',
        'MY': 'マレーシア'
      }
    }
  },
  computed: {
    // v-modelをcomputed->変数のように間接的に扱う場合はgetterとsetterの両方が必要。
    // 参考: https://qiita.com/shizen-shin/items/a48cfbe7c88cc9f42e30
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 上下矢印(現在値ティック)に表示するラベルを返す
    tickLabel() {
      if (this.desc.current === '△' || this.desc.current === '-' || this.desc.current === '０') {
        // 現在値＝｢△、-、０｣の場合、表示しない
        return ''
      }

      switch (this.desc.tick) {
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
      if (this.desc.current === '△' || this.desc.current === '-' || this.desc.current === '０') {
        return '__black'
      }

      switch (this.desc.tick) {
        case '1':
          return 'font-color__plus __bold'
        case '2':
          return 'font-color__minus __bold'
        default:
          return '__black __bold'
      }
    },
    // 国ごとの取引制限ページを表示する
    tradeLimit() {
      const samepart = 'https://search.sbisec.co.jp/v2/popwin/attention/stock/'
      switch (this.countryCode) {
        case 'US':
          return samepart + 'usstock.html'
        case 'CN':
          return samepart + 'hk_cash.html'
        case 'KR':
          return samepart + 'kor_cash.html'
        case 'RU':
          return samepart + 'ru_cash.html'
        case 'VN':
          return samepart + 'vn_cash.html'
        case 'ID':
          return samepart + 'id_cash.html'
        case 'SG':
          return samepart + 'sg_cash.html'
        case 'TH':
          return samepart + 'th_cash.html'
        case 'MY':
          return samepart + 'my_cash.html'
        default:
          // 未指定時=スペース
          return samepart + ' '
      }
    },
    getCountryName() {
      switch (this.countryCode) {
        case 'US':
          return '米国'
        case 'CN':
          return '中国'
        case 'KR':
          return '韓国'
        case 'RU':
          return 'ロシア'
        case 'VN':
          return 'ベトナム'
        case 'ID':
          return 'インドネシア'
        case 'SG':
          return 'シンガポール'
        case 'TH':
          return 'タイ'
        case 'MY':
          return 'マレーシア'
        default:
          // 未指定時=スペース
          return '　'
      }
    }
  },
  mounted() {
    if (this.isMargin === true) {
      this.countryCode = 'US'
    }
  },

  methods: {
    handleRowClick(params) {
      this.searchWord = params.searchWord
      this.stockShow()
    },
    addComma(num) {
      return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,')
    },
    resetAll(notify = false) {
      this.countryCode = 'US'
      this.searchWord = ''
      this.code = ''
      this.name = ''
      this.desc.current = ''
      this.desc.last = ''
      this.desc.diff = ''
      this.desc.ratio = '-％'
      this.desc.diffDateTime = ''
      this.desc.start = ''
      this.desc.low = ''
      this.desc.high = ''
      this.desc.volume = ''
      this.desc.tick = ''
      if (notify) {
        const param = {
          id: '3',
          data: {}
        }
        this.$emit('change', param)
      }
    },
    updateLoading() {
      return
    },
    handleCloseModal() {
      this.brandSerachIsVisible = false
    },
    openDialog() {
      this.$refs['ifaForeignStockBrandSearchPopup'].clear()
      // A011 リクエスト: 国コード
      this.$refs['ifaForeignStockBrandSearchPopup'].setCuntoryByString(this.countryCode)
      this.brandSerachIsVisible = true
    },
    handleResult(parameter) {
      const ifaForeignStockBrandSearchPopupA003RequestModel = new IfaForeignStockBrandSearchPopupA003RequestModel(parameter)
      this.$_logDebug(ifaForeignStockBrandSearchPopupA003RequestModel)
      // TODO 遷移先.A014
      // this.searchWord = parameter.code
      // this.oldSearchWord = parameter.code
      // this.code = parameter.code
      // this.name = parameter.name
      // this.market = parameter.market
      // this.countryCode = parameter.countryCode
      // this.desc.current = parameter.desc.current
      // this.desc.last =
      //   parameter.desc.last + ' (' + parameter.desc.lastDate + ')'
      // this.desc.diff = this.addComma(parameter.desc.diff)
      // this.desc.ratio = parameter.desc.ratio + '%'
      // this.desc.diffDateTime = ' (' + parameter.desc.diffDateTime + ')'
      // this.desc.start = parameter.desc.start
      // this.desc.low = parameter.desc.low
      // this.desc.high = parameter.desc.high
      // this.desc.volume = this.addComma(parameter.desc.volume)
      // this.desc.tick = parameter.desc.tick

      // const param = {
      //   id: '1',
      //   data: parameter
      // }
      // this.$emit('change', param)
    },
    handleSearchWordInput(value) {
      if (this.code !== '') {
        this.searchWord = this.oldSearchWord
        return
      }

      this.oldSearchWord = this.searchWord
    }
  }
}

</script>
<style lang="scss" scoped>
.search_header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  padding-right: 0.5rem;
  height: 25px;
  line-height: 25px;
  white-space: nowrap;
}
.update-button {
  text-align: right;
  position: absolute;
  right: 0.5rem;
  top: 0;
}
.market-label {
  color: #f00;
  font-weight: bold;
  padding-left: 0.1rem;
}
.brand-name {
  width: 100%;
  color: #606266;
  font-weight: bold;
  font-size: 14px;
  padding-left: 0.5rem;
}
.__bold {
  font-weight: bold;
}
.__black {
  color: #606266;
}
.__color_empty {
  color: #bfcbd9;
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
.info_s {
  display: grid;
  width: 100%;
  grid-template-columns: 5rem 1fr;
}
.info-item__header {
  resize: none;
  border: none;
  color: #606266;
  font-size: 14px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__value {
  resize: none;
  border: none;
  color: #606266;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__current {
  resize: none;
  border: none;
  color: #606266;
  font-size: 15px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.info-item__empty {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  border-bottom: 1px solid #bfcbd9;
}
.data-provider {
  resize: none;
  border: none;
  color: #bfcbd9;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
}
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
}
</style>
