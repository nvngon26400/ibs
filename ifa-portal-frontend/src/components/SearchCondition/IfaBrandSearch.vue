<template>
  <el-row
    align="middle"
    class="ifa-brand-search__wrapper"
  >
    <el-col
      :span="10"
      class="brand-info"
    >
      <template v-if="!isCodeLock">
        <span class="search_header">銘柄検索:</span>
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
            width="650px"
          >
            <!-- 検索結果が100件以上ならエラーをポップアップ -->
            <div
              v-if="searchResultNumber > 100"
              @mouseenter="setMouseOver(true)"
              @mouseleave="setMouseOver(false)"
            >
              <div>
                <span>検索結果が100件を超過しています（</span>
                {{ searchResultNumber }}
                <span>件ヒット）。</span>
              </div>
              <div>
                <span>条件を詳細に設定して下さい。</span>
              </div>
            </div>
            <!-- 検索結果が0件ならデータなし､2件以上ならリストをポップアップ -->
            <div v-else>
              <el-table
                ref="searchBox"
                :data="stockInfo"
                stripe
                size="small"
                class="table-min-height"
                @row-click="handleRowClick"
                @mouseenter="setMouseOver(true)"
                @mouseleave="setMouseOver(false)"
              >
                <el-table-column
                  class="_center"
                  label="銘柄コード"
                  prop="brandCode"
                  width="100"
                ></el-table-column>
                <el-table-column
                  label="銘柄名"
                  prop="brandName"
                  width="300"
                ></el-table-column>
                <el-table-column
                  label="取扱市場"
                  width="220"
                >
                  <template #default="props">
                    <el-badge
                      v-if="props.row.mktTky"
                      value="東"
                      class="badge-item"
                      :type="badgeColor(props, 0)"
                    ></el-badge>
                    <el-badge
                      v-if="props.row.mktNgy"
                      value="名"
                      class="badge-item"
                      :type="badgeColor(props, 2)"
                    ></el-badge>
                    <el-badge
                      v-if="props.row.mktFko"
                      value="福"
                      class="badge-item"
                      :type="badgeColor(props, 6)"
                    ></el-badge>
                    <el-badge
                      v-if="props.row.mktSpr"
                      value="札"
                      class="badge-item"
                      :type="badgeColor(props, 8)"
                    ></el-badge>
                    <el-badge
                      v-if="props.row.mktPts"
                      value="Ｐ"
                      class="badge-item"
                      :type="badgeColor(props, 7)"
                    ></el-badge>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagination"
                layout="prev, pager, next"
                :page-size="pageSize"
                :current-page="currentPage"
                :total="filteredPage"
                @current-change="setPage"
                @mouseenter="setMouseOver(true)"
                @mouseleave="setMouseOver(false)"
              ></el-pagination>
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
                @focus="confirmSelection(true)"
                @change="confirmSelection(false)"
                @clear="resetAll(true)"
                @input="handleSearchWordInput"
              ></el-input>
            </template>
          </el-popover>
        </span>
      </template>
      <template v-else>
        <div class="search_header">銘柄:</div>
        <div class="brand-code">[ {{ search }} ]</div>
      </template>
      <div
        v-if="brandCode !== ''"
        class="brand-name"
      >{{ brandName }}</div>
    </el-col>

    <el-col
      v-if="brandCode !== ''"
      :span="8"
      class="market-info"
    >
      <template v-if="internalMarketList.length === 0">
        <!-- 条件にあてはまるリストが1件もない場合は、項目非表示 -->
      </template>
      <template v-else-if="!isMarketOnly">
        <!-- 市場ドロップダウンリストが2件以上の場合に表示 -->
        <div class="market-name">市場:</div>
        <ifa-input-select
          v-model="selectedMarket"
          size="small"
          code-list-id="original"
          :original-list="internalMarketList"
          style="width: 200px;"
          @change="selectedMarketChanged(true)"
        ></ifa-input-select>
      </template>
      <template v-else>
        <!-- 市場ドロップダウンリストが1件の場合に表示 -->
        <span class="market-name">市場: {{ marketName }}</span>
      </template>
    </el-col>

    <el-col :span="3">
      <div v-if="topixFlg === '1'">
        <span class="topix-100-label">TOPIX100</span>
      </div>
    </el-col>
    <el-col :span="3">
      <div
        v-if="regKbn === '1'"
        class="trade-caution-info"
      >
        <el-icon class="trade-caution-info__icon"><WarningFilled></WarningFilled></el-icon>
        <ifa-link
          :url-id="16"
          :url-object="{ brandCode: getUrlObject }"
          disp-name="取引注意情報"
        ></ifa-link>
      </div>
    </el-col>

    <ifa-requester
      id="IfaBrandSearchSearchBrandA002"
      action-id="CC014#A002"
      action-type="requestAction"
      :request-model="searchBrandA002RequestModel"
      @response-handler="responseHandlerA002($event)"
      @response-error-handler="responseErrorHandlerA002($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaBrandSearchInitializeA001"
      action-id="CC014#A001"
      action-type="requestAction"
      :request-model="initializeA001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>
  </el-row>
</template>

<script>
import { nextTick } from 'vue'

export default {
  props: {
    isCodeLock: { type: Boolean, default: false }, // 銘柄コード変更不可
    tradingType: { type: String, required: true, default: '0' }, // 信用区分(0:現物取引/1:信用取引)
    marketList: { type: Array, required: false, default: () => [] } // 市場リスト
  },
  /**
   * change イベントの id と data について
   * change イベントを親コンポーネントに emit する時に引数で渡される id と data は以下の通り
   * ID=1: 銘柄が選択された, data: data.dataList[0] 銘柄の詳細(コード､名称､市場など)
   * ID=2: 市場が変更された, data: this.selectedMarket 選択された市場コード (例: 東証が選択された場合'0')
   * ID=3: クリアボタン押下時, data: {} 空きオブジェクト
   */
  emits: ['change'],
  data() {
    return {
      search: '',
      oldSearch: '',
      brandList: [],
      brandCode: '',
      brandName: '',
      selectedMarket: '',
      openInterestBuiltMarket: '',
      // テキスト入力が focus() が当たっているか状態を保持する
      // 候補銘柄一覧の popover の表示/非表示の制御に使用する
      inputFocused: false,
      creditRepaymentProcess: false,
      maxLength: 100,
      page: 1,
      currentPage: 1,
      pageSize: 20,
      filteredPage: 0,
      // 候補銘柄の数｡1の場合はその銘柄に確定する｡2以上の場合は候補銘柄一覧を表示する
      searchResultNumber: 0,
      // 選択可能な市場が1の場合 true になる｡true の場合､市場はリスト表示せずにテキストで表示する
      internalMarketList: [],
      searchBrandA002RequestModel: {},
      initializeA001RequestModel: {},
      // ネットワーク処理中は非同期で処理されるため連打をガードするフラグ
      processing: false,
      topixFlg: '',
      regKbn: '',
      // 候補銘柄一覧を表示しているとき､候補銘柄一覧をクリックするとテキスト入力の blur が発生してしまう
      // 候補銘柄一覧上にマウスカーソルがあるとき､このフラグを true にする
      // blur が発生してもこのフラグが true のときは無視することで候補銘柄一覧を閉じないように制御する
      // またこのフラグが true → false に変化したタイミングでテキスト入力に focus() する
      mouseOver: false
    }
  },
  computed: {
    searchCandidatesVisible() {
      return this.inputFocused && this.searchResultNumber !== 1
    },
    searchBoxWidth() {
      return this.brandCode === '' ? 'width: 280px;' : 'width: 90px;'
    },
    stockInfo() {
      return this.brandList.slice(this.pageSize * this.currentPage - this.pageSize, this.pageSize * this.currentPage)
    },
    badgeColor() {
      return function(props, index) {
        if (index !== 7 && this.upperRankMkt(props) === index) {
          // PTS以外且つ、最上位市場と合致した場合は赤バッチで表示
          return 'danger'
        }
        return 'success'
      }
    },
    marketName() {
      return this.$_getCodeValue('SELECT_MARKET', 1, this.selectedMarket) ?? ''
    },
    getUrlObject() {
      if (!this.search) {
        return null
      }
      return this.search.substring(0, 4)
    },
    isMarketOnly() {
      return this.internalMarketList.length <= 1
    }
  },
  methods: {
    confirmSelection(focused) {
      // ネットワーク処理中またはコードロック中または focus イベントで検索文字列が0文字のときまたは銘柄確定状態では処理しない
      if (this.processing || this.isCodeLock || (focused && this.search.length === 0) || this.brandCode.length > 0) return

      // focus イベントで mouseOver が true のとき､一覧を再描画させて終了する
      if (focused && this.mouseOver) {
        this.forceUpdate()
        return
      }

      if (this.search.length > 0) {
        this.processing = true
        this.inputFocused = true
        this.$nextTick(() => {
          this.searchBrandA002RequestModel = {
            search: this.search
          }
          this.$el.querySelector('#IfaBrandSearchSearchBrandA002').click()
        })
      }
    },
    responseHandlerA002(data) {
      this.processing = false
      if (data.errorLevel >= 0 &&
          // 銘柄検索の結果が0件の場合､searchResultDetail に null がセットされる (不具合管理 #1538)
          data.dataList[0] && data.dataList[0].searchResultDetail) {
        // 銘柄コードの昇順にソートする
        this.brandList = data.dataList[0].searchResultDetail.sort((a, b) => Number(a.brandCode) - Number(b.brandCode))
        this.searchResultNumber = this.brandList.length

        // 銘柄検索で1個まで銘柄が絞られていたらその銘柄を確定させる
        if (this.searchResultNumber === 1) {
          this.setStockInfo(this.brandList[0], true)
        } else {
          this.filteredPage = this.searchResultNumber
          // 検索結果は最大100件表示
          if (this.filteredPage > 100) {
            this.filteredPage = 100
          }
          this.currentPage = 1
          this.$nextTick(() => {
            this.forceUpdate()
          })
        }
      } else {
        this.searchResultNumber = 0
        this.brandList = []
        this.filteredPage = 0
        this.currentPage = 1
        this.$nextTick(() => {
          this.forceUpdate()
        })
      }
    },
    responseErrorHandlerA002(data) {
      this.processing = false
    },
    async forceUpdate() {
      // popover のコンテンツ部分をレンダリングさせて､popover を visible にしても
      // popover が正常に機能しない問題が発生した
      // popover を一度 invisible にして､再度 visible にすることで再レンダリングさせると正常に戻る
      // 参考サイト: https://michaelnthiessen.com/force-re-render
      this.inputFocused = false
      await nextTick()
      this.inputFocused = true
    },
    setPage(val) {
      this.currentPage = val
    },
    handleRowClick(event) {
      this.setStockInfo(event, true)
    },
    setStockInfo(event, notify = false) {
      if (this.processing) return
      this.processing = true

      this.$nextTick(() => {
        this.initializeA001RequestModel = {
          brandCode: event.brandCode,
          tradeType: this.tradingType
        }
        this.$el.querySelector('#IfaBrandSearchInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      this.processing = false
      if (data.errorLevel >= 0) {
        this.search = data.dataList[0].brandCode
        this.oldSearch = data.dataList[0].brandCode
        this.brandCode = data.dataList[0].brandCode
        this.brandName = data.dataList[0].brandName
        this.inputFocused = false

        if (Array.isArray(data.dataList[0].selectMarketList) && data.dataList[0].selectMarketList.length > 0) {
          // 銘柄市場リストあり
          if (Array.isArray(this.marketList) && this.marketList.length > 0) {
            // 対象市場リストあり
            // 銘柄市場リストにあり　かつ　対象市場リストにあり　の市場のみプルダウンに表示
            this.internalMarketList = data.dataList[0].selectMarketList
              .filter(m1 => this.marketList.find(m2 => m1.key === m2.key))
          } else {
            // 対象市場リストなし
            // 銘柄市場リストをそのままプルダウンに表示
            this.internalMarketList = [...data.dataList[0].selectMarketList]
          }

          if (this.internalMarketList.length > 1) {
            // 'mktSor' または upperRankMkt に一致する市場を上位にする
            this.internalMarketList = this.internalMarketList.sort((a, b) => {
              // SOR を一番上位にする
              if (a.key === 'A') return -1
              if (b.key === 'A') return 1
              // upperRankMkt を上位にする
              if (a.key === data.dataList[0].upperRankMkt) return -1
              if (b.key === data.dataList[0].upperRankMkt) return 1
              // その他はソートなし
              return 0
            })
          }

          this.selectedMarket = this.internalMarketList.length > 0 ? this.internalMarketList[0].key : ''
        } else {
          this.internalMarketList = []
          this.selectedMarket = ''
        }

        this.topixFlg = data.dataList[0].topixFlg
        this.regKbn = data.dataList[0].regKbn

        // 親コンポーネントに change イベントを発行するとき､
        // SorMkt, upperRankMkt が上位にくるようにソートした selectMarketList を渡す
        const brandInfo = { ...data.dataList[0] }
        brandInfo.selectMarketList = [...this.internalMarketList]
        const param = {
          id: '1',
          data: brandInfo
        }
        this.$emit('change', param)
      }
    },
    responseErrorHandlerInitializeA001(data) {
      this.processing = false
    },
    selectedMarketChanged(notify = false) {
      if (notify) {
        const param = {
          id: '2',
          data: this.selectedMarket
        }
        this.$emit('change', param)
      }
    },
    resetAll(notify = false) {
      this.search = ''
      this.searchResultNumber = 0
      this.oldSearch = ''
      this.brandCode = ''
      this.brandName = ''
      this.selectedMarket = ''
      this.inputFocused = false
      this.topixFlg = ''
      this.regKbn = ''
      if (notify) {
        const param = {
          id: '3',
          data: {}
        }
        this.$emit('change', param)
      }
    },
    handleSearchWordInput(value) {
      // 銘柄確定済みならテキストの変更を無効にする
      if (this.brandCode !== '') {
        this.search = this.brandCode
      }
      this.inputFocused = false
    },
    async setMouseOver(status) {
      if (!status) {
        this.$el.querySelector('#brandSearchKey').focus()
      }
      await nextTick()
      this.mouseOver = status
    },
    // upperRankMarket を区分値に変換する
    upperRankMkt(props) {
      if (props && props.row && typeof props.row.upperRankMkt === 'string') {
        const marketCode = { TKY: 0, NGY: 2, FKO: 6, SPR: 8, PTS: 7 }
        return marketCode[props.row.upperRankMkt.trim()] ?? -1
      }
      return -1
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/table.scss";
.ifa-brand-search__wrapper {
  --brand-info-code-position-adjust: 0;
  --brand-info-name-position-adjust: 0;
  --market-info-position-adjust: 0;
  .brand-info {
    display: inline-flex;
    align-items: center;
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
    .brand-code {
      display: flex;
      white-space: nowrap;
      margin-left: calc(3rem + var(--brand-info-code-position-adjust));
    }
    .brand-name {
      color: #606266;
      font-weight: bold;
      font-size: 14px;
      padding-right: var(--brand-info-name-position-adjust);
      padding-left: 0.5rem;
    }
    .badge-item {
      padding-top: 0.4rem;
      padding-left: 0.4rem;
    }
    .pagination {
      display: flex;
      justify-content: center;
    }
  }
  .market-info {
    display: flex;
    align-items: center;
    margin-left: var(--market-info-position-adjust);
    .market-name {
      color: #606266;
      font-weight: bold;
      font-size: 15px;
      padding-right: 0.5rem;
    }
  }
  .topix-100-label {
    font-size: 15px;
  }
  .trade-caution-info {
    display: flex;
    white-space: nowrap;
    align-items: center;
    line-height: 15px;
    &__icon {
      font-size: 15px;
      color: red;
    }
  }
}

// バッジが1つも表示されないとき､行の高さが狭くなってポップアップの高さが変わってしまう｡
// そのためページネーションの操作性が悪くなる｡
// 最小高を設定することでバッジの表示なしのときも高さをありと揃えるようにする
.table-min-height {
  :deep(.cell) {
    min-height: 27.5px;
    align-content: center;
  }
}
</style>
