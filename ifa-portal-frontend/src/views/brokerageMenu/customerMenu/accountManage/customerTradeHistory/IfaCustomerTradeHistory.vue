<template>
  <!-- 画面名：SUB0202_0109-01_取引履歴（顧客別） -->
  <div>
    <!-- タイトルエリア -->
    <screen-title :text="form.screenTitle.name"></screen-title>
    <!-- 検索条件エリア -->
    <div class="card-area">
      <el-card class="search-conditions-wrapper">
        <el-form
          ref="form"
          :inline="true"
          :model="form"
          label-position="left"
        >
          <div class="search-conditions-container">
            <el-row  class="type-row">
              <el-col :span="10"
                      :offset="0"
              >
                <!-- 商品区分 -->
                <el-form-item
                  label="商品区分"
                  prop="securityType"
                >
                  <el-select
                    v-model="form.securityType"
                    size="small"
                    style="width: 310px;"
                  >
                    <el-option
                      v-for="item in getOptions('TRADE_HISTORY_SECURITY_TYPE')"
                      :key="item.key"
                      :label="item.value"
                      :value="item.key"
                      :class="`option-level-${item.level}`"
                    >
                      <span>{{ item.value }}</span>  
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12"
                      :offset="0"
              >
                <!-- 並び順指定【項目】 -->
                <ifa-input-radio
                  id="sortOrderItem"
                  v-model="form.sortOrderItem"
                  prop="sortOrderItem"
                  label="並び順指定【項目】"
                  is-button
                  required
                  code-list-id="SORT_ORDER_ITEM"
                  :select-pattern="1"
                  :disp-pattern="1"
                ></ifa-input-radio>
              </el-col>
            </el-row>
            <el-row  class="type-row">
              <el-col :span="10"
                      :offset="0"
              >
                <!-- 取引区分 -->
                <el-form-item
                  label="取引区分"
                  prop="tradeType"
                >
                  <el-select
                    v-model="form.tradeType"
                    size="small"
                    style="width: 310px;"
                  >
                    <el-option
                      v-for="item in getOptions('TRADE_TYPE')"
                      :key="item.key"
                      :label="item.value"
                      :value="item.key"
                      :class="`option-level-${item.level}`"
                    >
                      <span>{{ item.value }}</span>  
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12"
                      :offset="0"
              >
                <!-- 並び順指定【属性】 -->
                <ifa-input-radio
                  id="sortOrderProfile"
                  v-model="form.sortOrderProfile"
                  prop="sortOrderProfile"
                  label="並び順指定【属性】"
                  is-button
                  required
                  code-list-id="SORT_ORDER_PROFILE"
                  :select-pattern="1"
                  :disp-pattern="1"
                ></ifa-input-radio>
              </el-col>
            </el-row>
            <el-row>
              <div style="display: flex; align-items: baseline; column-gap: 10px; padding-left: 18px;">
                <label
                  class="right-label"
                >銘柄コード</label>
                <!-- 会社コード -->
                <div class="input-text-class">
                  <ifa-input-text
                    id="companyCode"
                    v-model="form.companyCode"
                    prop="companyCode"
                    size="small"
                    style="width:100px;"
                    placeholder="1234(会社)"
                    :domain="IfaText5DomainModel"
                  ></ifa-input-text>
                </div>
                <!-- 回数 -->
                <span>－</span>
                <div class="input-text-class">
                  <ifa-input-text
                    id="times"
                    v-model="form.times"
                    prop="times"
                    size="small"
                    style="width:100px;"
                    placeholder="4(回数)"
                    :domain="IfaText4HalfAlphanumericDomainModel"
                  ></ifa-input-text>
                </div>
                <!-- 号 -->
                <span>－</span>
                <div class="input-text-class">
                  <ifa-input-text
                    id="issue"
                    v-model="form.issue"
                    prop="issue"
                    size="small"
                    style="width:100px;"
                    placeholder="12(号)"
                    :domain="IfaText3HalfAlphanumericDomainModel"
                  ></ifa-input-text>
                </div>
              </div>
            </el-row>
            <el-row class="period-row">
              <!-- 約定日 -->
              <ifa-date-range-picker
                v-model="form.period"
                prop="period"
                unlink-panels
                label="約定日"
                required
                :picker-options="pickerOptions"
              ></ifa-date-range-picker>
            </el-row>
            <el-row>
              <!-- 表示 -->
              <ifa-button
                id="btnDisplay"
                text="表示"
                width="90"
                small
                :request-model="IfaCustomerTradeHistoryA002RequestModel"
                :form="formRef"
                action-id="SUB0202_0109-01#A002"
                action-type="requestAction"
                @response-handler="responseHandlerA002($event)"
              ></ifa-button>
              <!-- クリア -->
              <ifa-button
                id="btnTopInputClear"
                name="btnTopInputClear"
                text="クリア"
                width="90"
                color="secondary"
                small
                action-type="originalAction"
                @app-action-handler="clear"
              ></ifa-button>
            </el-row>
          </div>
        </el-form>
      </el-card>
    </div>
    <div class="card-area">
      <el-card class="grid-wrapper">
        <!-- 操作エリア -->
        <el-row style="padding: 10px;">
          <div style="display: flex; align-items: baseline; column-gap: 15px;">
            <!-- CSV出力 -->
            <ifa-button
              id="btnCsvDownload"
              text="CSV出力"
              width="90"
              small
              :disabled="disabledCsvBtn"
              :form="formRef"
              :request-model="IfaCustomerTradeHistoryA004RequestModel"
              action-id="SUB0202_0109-01#A004"
              action-type="outputCsvAction"
              :csv-file-name="csvFileName"
              :is-check-csv-download-privacy-confirmation="false"
              :is-check-csv-download-allowed="false"
              :control-auth-enabled="false"
            ></ifa-button>
            <!--
              一覧表示モード
              ※ 常時非表示で 「最大」固定（要望があれば再表示させる）
            -->
            <ifa-input-radio
              :visible="false"
              id="listDisplayMode"
              v-model="form.listDisplayMode"
              prop="listDisplayMode"
              label="一覧表示モード"
              code-list-id="original"
              :original-list="listDisplayModeItem"
              is-button
              @change="changeDisplayMode"
            ></ifa-input-radio>
            <!--
              振替表示モード
              ※ 常時非表示で「除外」固定（要望があれば再表示させる）
            -->
            <ifa-input-radio
              :visible="false"
              id="transferDisplayMode"
              v-model="form.transferDisplayMode"
              prop="transferDisplayMode"
              label="振替表示モード"
              code-list-id="original"
              :original-list="transferDisplayModeItem"
              is-button
              @change="changeDisplayMode"
            ></ifa-input-radio>
          </div>
        </el-row>

        <!-- 取引履歴明細エリア -->
        <grid-table
          ref="pqGrid"
          :options="pqGridOption"
          :auto-refresh="false"
        ></grid-table>
      </el-card>
    </div>

    <!-- A001 初期化 リクエスター -->
    <ifa-requester
      id="IfaCustomerTradeHistoryInitializeA001"
      action-id="SUB0202_0109-01#A001"
      action-type="requestAction"
       @response-error-handler="responseErrorHandlerA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable'
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaButton from '@/components/Button/IfaButton.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaCustomerTradeHistoryA002RequestModel } from './js/IfaCustomerTradeHistoryA002RequestModel'
import { IfaCustomerTradeHistoryA004RequestModel } from './js/IfaCustomerTradeHistoryA004RequestModel'
import { IfaCustomerTradeHistoryFormModel } from './js/IfaCustomerTradeHistoryFormModel'
import IfaText5DomainModel from '@/resource/domain/IfaText5DomainModel.json'
import IfaText4HalfAlphanumericDomainModel from '@/resource/domain/IfaText4HalfAlphanumericDomainModel.json'
import IfaText3HalfAlphanumericDomainModel from '@/resource/domain/IfaText3HalfAlphanumericDomainModel.json'

export default {
  components: {
    GridTable,
    IfaButton,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      lengthLevelConfig: { // 商品区分, 取引区分 選択エリア表示時のインデント指定
        TRADE_HISTORY_SECURITY_TYPE: { // 商品区分
          levels: [
            { length: 1, level: 1 },
            { length: 3, level: 2 },
            { length: 5, level: 3 }
          ]
        },
        TRADE_TYPE: { // 取引区分
          levels: [
            { length: 1, level: 1 },
            { length: 2, level: 2 },
            { length: 3, level: 3 }
          ]
        }
      },
      listDisplayModeItem: [ // 一覧表示モード 選択項目
        { key: '1', value: '標準' },
        { key: '2', value: '最大' }
      ],
      transferDisplayModeItem: [ // 振替表示モード 選択項目
        { key: '1', value: '除外' },
        { key: '2', value: '表示' }
      ],
      defaultDisplayColumn: [ // 一覧表示モード = 標準 の際に表示されるカラム
        'tradeDate',
        'securityType',
        'brandName',
        'detailsTrade',
        'detailsType',
        'price',
        'quantity',
        'fee',
        'accurateAmount',
        'space'
      ],
      pickerOptions: {
        shortcuts: [
          {
            text: '前日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          }, {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに当日を設定
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setHours(0, 0, 0, 0)
              return [startDate, endDate]
            }
          },
          {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date(this.$store.getters.requestedTime)
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date(this.$store.getters.requestedTime)
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }
        ]
      },
      formRef: {},
      disabledCsvBtn: true,
      pqGridOption: getConvertedOption(obj),
      form: new IfaCustomerTradeHistoryFormModel(),
      IfaCustomerTradeHistoryA002RequestModel: {},
      IfaCustomerTradeHistoryA004RequestModel: {},
      IfaText5DomainModel: IfaText5DomainModel,
      IfaText4HalfAlphanumericDomainModel: IfaText4HalfAlphanumericDomainModel,
      IfaText3HalfAlphanumericDomainModel: IfaText3HalfAlphanumericDomainModel
    }
  },
  computed: {
    previousBusinessDay() {
      // 前営業日
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    },
    customerInfo() {
      // 顧客共通情報
      return this.$store.getters.customerInfo
    },
    csvFileName() {
      // ファイル名："取引履歴（顧客別）_部店コード3桁+口座番号0埋め7桁_YYYYMMDD.csv"
      return `取引履歴（顧客別）_${this.customerInfo.butenCode}${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    IfaCustomerTradeHistoryA002RequestModel() {
      // A002リクエストモデル
      return new IfaCustomerTradeHistoryA002RequestModel(this.form)
    },
    IfaCustomerTradeHistoryA004RequestModel() {
      // A004リクエストモデル
      return new IfaCustomerTradeHistoryA004RequestModel(this.form)
    }
  },
  methods: {
    /* 初期表示 */
    onShow() {
      // A001 初期化
      this.$nextTick(() => {
        document.getElementById('IfaCustomerTradeHistoryInitializeA001').click()
      })
      
      this.formRef = this.$refs.form

      // 操作エリアの初期化
      this.form.listDisplayMode = '2' // 一覧表示モード
      this.form.transferDisplayMode = '1' // 振替表示モード
      
      // 検索条件エリア, 取引履歴明細エリアの初期化 
      this.clear()

      this.form.customerCode = this.customerInfo.customerCode // 顧客ID
    },
    /* A001 初期化 レスポンスエラーハンドラ */
    responseErrorHandlerA001(error) {
      // 共通エラー画面を出力する
      if (error.errorLevel === -1) {
        const errorInfo = {
          title: this.form.screenTitle.name,
          message: error.message
        }
        this.$emit('initializeError', errorInfo)
      }
    },
    /* A002 表示 レスポンスハンドラ */
    responseHandlerA002(response) {
      if (response.dataList.length > 0 && response.dataList[0].tradeHistoryList.length > 0) {
        this.disabledCsvBtn = false
        Object.assign(this.form, response.dataList[0])
      } else {
        this.disabledCsvBtn = true
        this.form.tradeHistoryList = []
      }
      this.updateGridConfigualtion()
    },
    /* クリア */
    clear() {
      // 検索条件エリアの初期化
      this.form.companyCode = '' // 会社コード
      this.form.times = '' // 回数
      this.form.issue = '' // 号
      this.form.securityType = '$NULL' // 商品区分
      this.form.tradeType = '$NULL' // 取引区分
      this.form.sortOrderItem = '0' // 並び順指定【項目】
      this.form.sortOrderProfile = '0' // 並び順指定【属性】

      // 期間指定
      const startDate = new Date(this.$store.getters.requestedTime)
      const endDate = new Date(this.$store.getters.requestedTime)
      startDate.setMonth(startDate.getMonth() - 1)
      this.form.period = [this.formatDate(startDate), this.formatDate(endDate)]

      // 取引履歴明細エリアの初期化
      this.form.tradeHistoryList = []
      this.disabledCsvBtn = true

      this.$refs['form']?.clearValidate()

      this.updateGridConfigualtion()
    },
    /* 表示モードの変更 */
    changeDisplayMode() {
      this.updateGridConfigualtion()
    },
    /* Date オブジェクトを文字列('YYYY/MM/DD')に変換 */
    formatDate(date) {
      if (date && typeof date === 'object' && 'toLocaleDateString' in date) {
        return date.toLocaleDateString('ja-JP', { year: 'numeric', month: '2-digit', day: '2-digit' })
      } else {
        return date
      }
    },
    /* 商品区分, 取引区分の選択項目を取得 */
    getOptions(codeListId) {
      const codeList = this.$_getCodeList(codeListId, 1, 1)
      const codeListOptions = codeList.map(item => {
        const keyLength = item.key.length
        // 区分値の文字列の長さに応じて選択項目のインデントを指定する
        const levelConfig = this.lengthLevelConfig[codeListId].levels.find(l => l.length === keyLength)
        return {
          ...item,
          level: levelConfig ? levelConfig.level : '1'
        }
      })

      return codeListOptions
    },
    /* テーブルの編集 */
    updateGridConfigualtion() {
      // ■ 振替表示モード
      if (this.form.transferDisplayMode === '1') { // 振替表示モード = 除外
        // 内訳区分＝振替の明細を非表示
        this.pqGridOption.dataModel.data = this.form.tradeHistoryList.filter((row, index) => row.detailsType !== '振替')
      } else {  // 振替表示モード = 表示
        // 全明細を表示
        this.pqGridOption.dataModel.data = this.form.tradeHistoryList
      }

      // ■ 一覧表示モード
      for (const key in this.$refs.pqGrid.grid.columns) { // 一覧表示モード = 標準
        if (this.form.listDisplayMode === '1') {
          // 標準以外の項目を非表示
          this.setHidden(key, !this.defaultDisplayColumn.includes(key))
        } else {  // 一覧表示モード = 最大
          // 全項目を表示
          this.setHidden(key, false)
        }
      }

      // 取消区分は必ず非表示
      this.setHidden('cancelFlg', true)
      
      // 空白列の幅調整
      this.setSpaceColumnWidth()

      // テーブル再描画
      this.$nextTick(() => {
        this.$refs['pqGrid'].refreshView(true)
      })
    },
    /* 指定されたテーブル項目を非表示にする */
    setHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    /* 空白列の幅を動的にセットする */
    setSpaceColumnWidth() {
      // 表示されているカラムの幅の合計値を取得する
      const visibleColumn = this.pqGridOption.colModel.filter(col =>!col.hidden && col.dataIndx !== 'space')

      let totalColumnWidth = 0;
      visibleColumn.forEach(col => {
        if (col.width) {
          totalColumnWidth += parseInt(col.width);
        }
      });

      // テーブルの幅を取得する
      const gridContainer = this.$refs.pqGrid.$el;
      const tableWidth = gridContainer.clientWidth

      // 空白列の幅に(テーブルの幅 - 表示されているカラムの幅の合計)をセットする。結果がマイナスの場合は空白列を非表示にする。
      const spaceColumnWidth = tableWidth - totalColumnWidth
      
      if (spaceColumnWidth <= 0) {
        this.setHidden('space', true)
      } else {
        const spaceColumnModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === 'space')
        if (typeof spaceColumnModel === 'object') {
          spaceColumnModel.width = spaceColumnWidth
        }
      }
    }
  }
}

const obj = {
  width: 0,
  height: 0,
  title: '取引履歴',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false,
  pageModel: {
    type: 'local',
    curPage: 1,
    rPP: 30,
    rPPOptions: []
  }
}

obj.colModel = [
  {
    title: '約定日',
    width: 90,
    align: 'left',
    halign: 'center',
    dataIndx: 'tradeDate',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {  // 取消区分＝'1'　の場合、該当レコードの文字を赤で表示（全項目共通処理）
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '受渡日',
    width: 90,
    align: 'left',
    halign: 'center',
    dataIndx: 'deliveryDate',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '商品区分',
    width: 90,
    align: 'center',
    halign: 'center',
    dataIndx: 'securityType',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '銘柄コード',
    width: 120,
    align: 'center',
    halign: 'center',
    dataIndx: 'brandCode',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '銘柄名',
    width: 270,
    align: 'left',
    halign: 'center',
    dataIndx: 'brandName',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '内訳取引',
    width: 90,
    align: 'center',
    halign: 'center',
    dataIndx: 'detailsTrade',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '預り/税',
    width: 90,
    align: 'center',
    halign: 'center',
    dataIndx: 'depositTax',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '単価',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'price',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '取得単価',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'openPrice',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '数量',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'quantity',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '手数料',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'fee',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '消費税',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'consumptionTax',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '精算金額',
    width: 150,
    align: 'right',
    halign: 'center',
    dataIndx: 'accurateAmount',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '内訳区分',
    width: 90,
    align: 'center',
    halign: 'center',
    dataIndx: 'detailsType',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '取引市場/理由',
    width: 120,
    align: 'center',
    halign: 'center',
    dataIndx: 'tradeMarketReason',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '取得日',
    width: 90,
    align: 'left',
    halign: 'center',
    dataIndx: 'acquireDate',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '通貨',
    width: 100,
    align: 'center',
    halign: 'center',
    dataIndx: 'currency',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '為替',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'fx',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '経過利子',
    width: 100,
    align: 'right',
    halign: 'center',
    dataIndx: 'accruedInterest',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '実現損益',
    width: 150,
    align: 'right',
    halign: 'center',
    dataIndx: 'realProfitAndLoss',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '譲渡益税/源泉税',
    width: 120,
    align: 'right',
    halign: 'center',
    dataIndx: 'capitalGainTax',
    render: function(ui) {
      const cancelFlg = ui.rowData.cancelFlg
      const value = ui.cellData ?? '-'
      
      if (cancelFlg === '1') {
        return '<sapn style="color: red;">' + value + '</sapn>'
      } else {
        return value
      }
    }
  },
  {
    title: '取消区分',
    dataIndx: 'cancelFlg',
    hidden: true
  },
  {
    title: '',
    dataType: 'string',
    dataIndx: 'space',
    width: 1000,
    halign: 'center',
    hidden: false,
    render: function(ui) {
      return ''
    }
  }
]

</script>

<style lang="scss" scoped>
.card-area {
  margin: 0.5rem 0.8rem;
}
.search-conditions-wrapper {
  background-color: #EEEEEE;
  padding-left: 20px;
}
.search-conditions-container {
  margin-top:1rem;
  margin-bottom: 1rem;
}
.search-conditions-container :deep(.el-row):not(:first-child) {
 padding-top: 13px;
}

:deep(.type-row) .el-col:first-child .el-form-item__label {
  padding-right: 15px;
}

:deep(.period-row) .el-form-item__label {
  padding-right: 30px;
}
:deep(.el-row) .el-form-item__label {
  width: auto;
  font-weight: bold;
  margin: 0px 25x 0px 5px;
  align-self: auto !important;
}

.grid-wrapper :deep(.el-row) .el-form-item__label {
  padding: 0 !important;
  margin: 0 !important;
}
:deep(.el-row) .el-form-item__error {
  padding-top: 3px;
}
:deep(.input-text-class .el-form-item) {
  margin-right: 0 !important;
}
.option-level-2 span {
  padding-left: 20px;
}
.option-level-3 span {
  padding-left: 40px;
}
</style>
