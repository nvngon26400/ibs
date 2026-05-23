<template>
  <div>
    <el-row>
      <el-card style="background-color: #eee; margin-bottom: 0.5rem;">
        <el-row>
          <el-col
            :span="9"
            style="padding-left: 1rem;"
          >
            <div>
              <!-- <span
                v-if="creditRepaymentProcess"
                class="search_header"
              >{{ brandCode }}</span> -->
              <template v-if="!isCodeLock">
                <span class="search_header __left">銘柄検索:</span>
                <span>
                  <el-badge
                    v-if="searchCandidatesVisible"
                    :value="filteredPage"
                    class="badge-item"
                    type="primary"
                  ></el-badge>
                  <el-popover
                    :visible="searchCandidatesVisible"
                    placement="right"
                  >
                    <div v-if="searchResultNumber > 100">
                      <div>
                        <span>検索結果が100件を超過しています（</span>
                        {{ searchResultNumber }}
                        <span>件ヒット）。</span>
                      </div>
                      <div>
                        <span>条件を詳細に設定して下さい。</span>
                      </div>
                    </div>
                    <div v-if="searchResultNumber <= 100">
                      <el-table
                        ref="searchBox"
                        :data="stockInfo"
                        stripe
                        size="small"
                        @row-click="handleRowClick"
                      >
                        <el-table-column
                          class="__center"
                          label="銘柄コード"
                          prop="code"
                          width="100"
                        ></el-table-column>
                        <el-table-column
                          label="銘柄名"
                          prop="name"
                          width="300"
                        ></el-table-column>
                        <el-table-column
                          label="取扱市場"
                          width="220"
                        >
                          <template #default="props">
                            <el-badge
                              v-if="props.row.desc.validMarkets[1]"
                              value="東"
                              class="badge-item"
                              :type="badgeColor(props, 1)"
                            ></el-badge>
                            <el-badge
                              v-if="props.row.desc.validMarkets[2]"
                              value="名"
                              class="badge-item"
                              :type="badgeColor(props, 2)"
                            ></el-badge>
                            <el-badge
                              v-if="props.row.desc.validMarkets[3]"
                              value="福"
                              class="badge-item"
                              :type="badgeColor(props, 3)"
                            ></el-badge>
                            <el-badge
                              v-if="props.row.desc.validMarkets[4]"
                              value="札"
                              class="badge-item"
                              :type="badgeColor(props, 4)"
                            ></el-badge>
                            <el-badge
                              v-if="props.row.desc.validMarkets[6]"
                              value="Ｐ"
                              class="badge-item"
                              :type="badgeColor(props, 6)"
                            ></el-badge>
                            <el-badge
                              v-if="props.row.desc.validMarkets[5]"
                              value="単"
                              class="badge-item"
                              :type="badgeColor(props, 5)"
                            ></el-badge>
                          </template>
                        </el-table-column>
                      </el-table>
                      <div
                        @mousedown="mouseDown()"
                        @click="handleClick()"
                      >
                        <el-pagination
                          class="pagination"
                          layout="prev,  pager, next"
                          :page-size="pageSize"
                          :current-page="currentPage"
                          :total="filteredPage"
                          @current-change="setPage"
                        ></el-pagination>
                      </div>
                    </div>
                    <template #reference>
                      <el-input
                        id="brandSearchKey"
                        v-model="search"
                        :readonly="isCodeLock"
                        clearable
                        placeholder="キーワード"
                        :style="searchBoxWidth"
                        size="small"
                        @change="confirmSelection(true)"
                        @focus="inputFocused = true"
                        @blur="confirmSelection(false)"
                        @clear="resetAll(true)"
                        @input="handleSearchWordInput"
                      ></el-input>
                    </template>
                  </el-popover>
                </span>
              </template>
              <template v-if="isCodeLock">
                <span style="margin-left:3rem;">[ {{ search }} ]</span>
              </template>

              <span
                v-if="brandCode !== ''"
                class="brand-name"
              >{{ brandName }}</span>
            </div>
          </el-col>

          <el-col
            v-if="brandCode !== '' && fractionalSharesFlag === false"
            :span="12"
          >
            <div>
              <span class="search_header __left">市場:</span>
              <span
                v-if="creditRepaymentProcess"
                class="search_header"
              >{{ desc.openInterestBuiltMarket }}</span>
              <span v-else>
                <el-select
                  v-model="desc.priorityMarket"
                  size="small"
                  style="width: 170px;"
                  @change="priorityMarketChanged(true)"
                >
                  <el-option
                    v-if="desc.validMarkets[0]"
                    key="0"
                    label="当社優先市場/SOR"
                    value="0"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[1]"
                    key="1"
                    label="東証"
                    value="1"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[2]"
                    key="2"
                    label="名証"
                    value="2"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[3]"
                    key="3"
                    label="福証"
                    value="3"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[4]"
                    key="4"
                    label="札証"
                    value="4"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[6]"
                    key="6"
                    label="PTS"
                    value="6"
                  ></el-option>
                  <el-option
                    v-if="desc.validMarkets[5]"
                    key="5"
                    label="単元未満"
                    value="5"
                  ></el-option>
                </el-select>
              </span>
            </div>
          </el-col>

          <el-col
            v-if="brandCode !== ''"
            :span="5"
            class="update-button"
          >
            <ifa-button
              v-if="showDisplayDetailButton"
              text="詳細"
              icon="Document"
              small
              action-type="originalAction"
              @app-action-handler="displayStockBoard"
            ></ifa-button>
            <ifa-button
              text="更新"
              icon="RefreshRight"
              small
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
                <el-row v-if="brandCode !== ''">
                  <el-col :span="12">
                    <span>{{ desc.current }}</span>
                  </el-col>
                  <el-col :span="3">
                    <span
                      :class="[tickColor]"
                      class="__bold"
                    >{{ tickLabel }}</span>
                  </el-col>
                  <el-col :span="3">
                    <span class="__bold">{{ flagLabel }}</span>
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
                v-if="brandCode !== ''"
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
                v-if="brandCode !== ''"
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
                v-if="brandCode !== ''"
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
              <span class="info-item__header __left">{{ ratioLabel }}</span>
              <span
                v-if="brandCode !== ''"
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
                v-if="brandCode !== ''"
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
              <span class="info-item__header __left">売買代金:</span>
              <span
                v-if="brandCode !== ''"
                class="info-item__value __right"
              >{{ desc.value }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (千円)</span>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="info_xs">
              <span class="info-item__header __left">出来高:</span>
              <span
                v-if="brandCode !== ''"
                class="info-item__value __right"
              >{{ desc.volume }}</span>
              <span
                v-else
                class="info-item__empty __right"
              >- (--:--)</span>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    // 銘柄コード変更不可 true、それ以外：false
    isCodeLock: { type: Boolean, default: false },
    fractionalSharesFlag: { type: Boolean, required: false, default: false }
  },
  emits: ['change', 'display-stock-board'],
  data() {
    return {
      search: '',
      oldSearch: '',
      brandCode: '',
      brandName: '',
      desc: {
        current: '',
        last: '',
        diff: '-',
        ratio: '-％',
        diffDateTime: '',
        start: '',
        low: '',
        high: '',
        value: '',
        volume: '',
        priorityMarket: '0',
        validMarkets: [
          true, // SOR
          true, // 東証
          true, // 名証
          true, // 福証
          true, // 札証
          true, // 単元未満
          true // PTS
        ],
        openInterestBuiltMarket: '',
        tick: '',
        flag: ''
      },
      inputFocused: false,
      showDisplayDetailButton: false,
      creditRepaymentProcess: false,
      page: 1,
      currentPage: 1,
      pageSize: 20,
      // バッジに表示する件数
      filteredPage: 0,
      // 検索件数
      searchResultNumber: 0,
      clickPage: false,
      changePage: false,
      // 検索条件保持用
      beforeSearch: ''
    }
  },
  computed: {
    // v-modelをcomputed->変数のように間接的に扱う場合はgetterとsetterの両方が必要。
    // 参考: https://qiita.com/shizen-shin/items/a48cfbe7c88cc9f42e30
    searchCandidatesVisible: {
      get() {
        return this.inputFocused && this.search.length > 0
      },
      set(value) {
        // this.$_logDebug('searchCandidatesVisible set to ' + value)
      }
    },
    searchBoxWidth() {
      return this.brandCode === '' ? 'width: 280px;' : 'width: 90px;'
    },
    // 前日比の色を算出する
    ratioColor() {
      return function(value) {
        const n = Number(value)
        return n > 0 ? 'font-color__plus __bold' : n < 0 ? 'font-color__minus __bold' : '__black'
      }
    },
    // 前日比のラベルを返す
    ratioLabel() {
      return this.desc.priorityMarket === '6' ? '基準値比:' : '前日比:'
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
    flagLabel() {
      if (this.desc.current === '△' || this.desc.current === '-' || this.desc.current === '０') {
        return ''
      }

      switch (this.desc.flag) {
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
          return this.desc.flag
      }
    }
  },
  methods: {
    // ページングエリア押下
    mouseDown() {
      this.clickPage = true
    },
    // 銘柄検索のフォーカスが外れた時､すでに1個まで銘柄が絞られていたらその銘柄を確定させる
    confirmSelection(visibility) {
      if (this.clickPage === true) {
        return
      } else {
        if (this.stockInfo.length === 1) {
          this.setStockInfo(this.stockInfo[0], true)
        }
        this.inputFocused = visibility
      }
    },
    // ページ移動時
    setPage(val) {
      this.changePage = true
      this.page = val
      document.getElementById('brandSearchKey').focus()
      this.clickPage = false
    },
    handleClick() {
      // ページエリア以外の場所がクリックされた時
      if (this.changePage === false) {
        document.getElementById('brandSearchKey').focus()
        this.clickPage = false
      }
      this.changePage = false
    },
    handleRowClick(event) {
      this.setStockInfo(event, true)
    },
    setStockInfo(event, notify = false) {
      this.search = event.code
      this.oldSearch = event.code
      this.brandCode = event.code
      this.brandName = event.name

      this.desc.current = this.addComma(event.desc.current)
      this.desc.last =
        this.addComma(event.desc.last) + ' (' + event.desc.lastDate + ')'
      this.desc.diff = this.addComma(event.desc.diff)
      this.desc.ratio = event.desc.ratio + '%'
      this.desc.diffDateTime = ' (' + event.desc.diffDateTime + ')'
      this.desc.start =
        this.addComma(event.desc.start) + ' (' + event.desc.startTime + ')'
      this.desc.low =
        this.addComma(event.desc.low) + ' (' + event.desc.lowTime + ')'
      this.desc.high =
        this.addComma(event.desc.high) + ' (' + event.desc.highTime + ')'
      this.desc.value = this.addComma(event.desc.value) + ' (千円)'
      this.desc.volume =
        this.addComma(event.desc.volume) + ' (' + event.desc.volumeTime + ')'
      this.desc.validMarkets = event.desc.validMarkets
      this.desc.tick = event.desc.tick
      this.desc.flag = event.desc.flag
      this.inputFocused = false
      // this.clickPage = false
      if (event.creditRepaymentProcess != null && event.creditRepaymentProcess) {
        this.creditRepaymentProcess = true
        this.desc.openInterestBuiltMarket = event.builtMarket
      }
      if (notify) {
      // forward an event to parent.
        const param = {
          id: '1',
          data: event
        }
        this.$emit('change', param)
      }

      if (!this.desc.validMarkets[0] && this.desc.priorityMarket === '0') {
        this.desc.priorityMarket = '1'
        this.priorityMarketChanged(notify)
      }

      this.showDisplayDetailButton = true
    },
    priorityMarketChanged(notify = false) {
      if (notify) {
        const param = {
          id: '2',
          data: this.desc.priorityMarket
        }
        this.$emit('change', param)
      }
    },
    addComma(num) {
      return String(num).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, '$1,')
    },
    resetAll(notify = false) {
      this.search = ''
      this.oldSearch = ''
      this.brandCode = ''
      this.brandName = ''
      this.desc.current = ''
      this.desc.last = ''
      this.desc.diff = ''
      this.desc.ratio = '-％'
      this.desc.diffDateTime = ''
      this.desc.start = ''
      this.desc.low = ''
      this.desc.high = ''
      this.desc.value = ''
      this.desc.volume = ''
      this.desc.priorityMarket = '0'
      this.desc.validMarkets = [true, true, true, true, true, true, true]
      this.desc.tick = ''
      this.desc.flag = ''
      this.inputFocused = false
      // this.clickPage = false
      if (notify) {
        const param = {
          id: '3',
          data: {}
        }
        this.$emit('change', param)
      }
    },
    setValidMarkets(validMarkets) {
      this.desc.validMarkets = validMarkets.slice()
      if (!this.desc.validMarkets[this.desc.priorityMarket]) {
        for (let i = 0; i <= 7; i++) {
          if (this.desc.validMarkets[i]) {
            this.desc.priorityMarket = String(i)
            break
          }
        }
      }
    },
    setValidMarket(index, value) {
      this.desc.validMarkets[index] = value
      if (!this.desc.validMarkets[this.desc.priorityMarket]) {
        for (let i = 0; i <= 7; i++) {
          if (this.desc.validMarkets[i]) {
            this.desc.priorityMarket = String(i)
            break
          }
        }
      }
    },
    displayStockBoard: function() {
      this.$emit('display-stock-board')
    },
    updateLoading() {
      return
    },
    handleSearchWordInput(value) {
      if (this.brandCode !== '') {
        this.search = this.oldSearch
        return
      }

      this.oldSearch = this.search
    }

  }
}

</script>
<style lang="scss" scoped>
@import "@/styles/table.scss";
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
.badge-item {
  padding-top: 0.4rem;
  padding-left: 0.4rem;
}
.__adjust_button_text {
  font-size: 12px!important;
  height: 18px !important;
  line-height: 18px !important;
  text-align: center!important;
}
.pagination {
  display: flex;
  justify-content: center;
}
</style>
