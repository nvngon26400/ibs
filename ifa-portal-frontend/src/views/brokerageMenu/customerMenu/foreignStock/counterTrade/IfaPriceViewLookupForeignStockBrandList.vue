<template>
  <div>
    <screen-title
      v-if="priceViewLookupIsVisible"
      :text="form.title.name"
    ></screen-title>
    <div
      v-if="priceViewLookupIsVisible"
      class="caption_card"
    >
      <div
        id="contentAreaInput"
        style="margin-bottom: 15px;"
      >
        <el-row class="form_body">
          <el-row valign="top">
            <el-col style="margin-top:20px;">
              <el-form
                id="counterSalesInfoListForm"
                ref="form"
                :model="form"
                :inline="true"
              >
                <div id="indicator-display">
                  <el-row style="display: flex; align-items: start;">
                    <ifa-input-select
                      id="tickerSelectFlag"
                      v-model="form.tickerSelectFlag"
                      code-list-id="FOREIGN_STOCK_COUNTER_SEARCH_CONDITION_SELECT"
                      size="small"
                      :disp-pattern="1"
                      :select-pattern="1"
                      :clearable="false"
                      class="brand_select"
                    ></ifa-input-select>
                    <ifa-input-text
                      v-if="form.tickerSelectFlag === '0'"
                      id="brandCodeTicker"
                      v-model="form.brandCodeTicker"
                      prop="brandCodeTicker"
                      size="small"
                      class="ifa-input__text-default code_input_text"
                      :domain="IfaTickerBrandCodeDomainModel"
                    ></ifa-input-text>
                    <ifa-input-text
                      v-else-if="form.tickerSelectFlag === '1'"
                      id="brandNameKey"
                      v-model="form.brandName"
                      prop="brandName"
                      size="small"
                      class="ifa-input__text-default"
                      style="width: 20em;"
                      original-screen-id="SUB0202_0302-01"
                      :domain="IfaBrandName100DomainModel"
                    ></ifa-input-text>
                  </el-row>
                  <el-row style="margin-top:30px;">
                    <el-col :span="5">
                      <ifa-button
                        id="btnDisplay"
                        name="btnDisplay"
                        text="表示"
                        width="90"
                        small
                        action-type="requestAction"
                        action-id="SUB0202_0302-01#A002"
                        :form="$refs.form"
                        :request-model="IfaPriceViewLookupForeignStockBrandListA002RequestModel"
                        @response-handler="displayA002($event)"
                      ></ifa-button>
                    </el-col>
                    <el-col
                      :span="19"
                      class="info-btn-area"
                    >
                      <div
                        class="faq-link"
                        @click="englishDisclosureBrandEtcListA004"
                      >英文開示銘柄等一覧</div>
                      <ifa-link
                        v-if="form.latestForeignSecuritiesInfoListUrl"
                        ref="latestForeignSecuritiesInfoListUrl"
                        link-icon-image="Document"
                        :param-url="form.latestForeignSecuritiesInfoListUrl"
                        disp-name="最新の外国証券情報一覧"
                        manua-init
                      ></ifa-link>
                      <ifa-link
                        v-if="form.domesticOverTheCounterTradingManualUrl"
                        ref="domesticOverTheCounterTradingManualUrl"
                        style="margin-left:0.5rem; margin-right:0.5rem"
                        link-icon-image="Document"
                        :param-url="form.domesticOverTheCounterTradingManualUrl"
                        disp-name="国内店頭取引マニュアル"
                        manua-init
                      ></ifa-link>
                      <ifa-link
                        v-if="form.foreignSecuritiesInfoUpdateHistoryUrl"
                        ref="foreignSecuritiesInfoUpdateHistoryUrl"
                        link-icon-image="Document"
                        :param-url="form.foreignSecuritiesInfoUpdateHistoryUrl"
                        disp-name="外国証券情報 更新履歴"
                        manua-init
                      ></ifa-link>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </el-col>
          </el-row>
          <el-row style="margin-top: 25px;">
            <label class="label">お知らせ</label>
          </el-row>
          <el-row>
            <el-card
              shadow="never"
              style="width: 100%; height: 115px; overflow-y: auto;"
            >
              <div
                v-html="$_out(form.notification)"
              ></div>
            </el-card>
          </el-row>
        </el-row>
      </div>
      <div id="paneBottom">
        <div>
          <div
            id="outputPaneMargins"
            style="margin-bottom: 30px;"
          >
            <el-row
              class="form_body"
              style="padding-top: 0px;"
            >
              <el-card
                id="outputTable"
              >
                <el-row>
                  <el-col
                    :span="18"
                    class="updatetime"
                  >
                    <span>{{ updateTime }}</span>
                  </el-col>
                  <el-col
                    :span="6"
                    class="caution_area"
                  >
                    <label class="caution">顧客配布厳禁　社内・仲介業者様限</label>
                  </el-col>
                </el-row>
                <grid-table
                  ref="gridTable"
                  :options="pqGridOption"
                  :auto-refresh="false"
                  @click="handleClick"
                ></grid-table>
              </el-card>
            </el-row>
            <el-row>
              <el-row
                id="resultArea"
                class="form_body"
              >
                <el-row>
                  <label class="label">注意事項 </label>
                </el-row>
                <el-row>
                  <el-card
                    shadow="never"
                    style="width: 100%; height: 115px; overflow-y: auto;"
                  >
                    <div
                      v-html="$_out(form.noticeNote)"
                    ></div>
                  </el-card>
                </el-row>
              </el-row>
            </el-row>
          </div>
        </div>
      </div>
    </div>
    <ifa-foreign-stock-counter-order-input
      ref="ifaForeignStockCounterOrderInput"
      :is-visible="counterOrderIsVisible"
      :form-data="orderInputData"
      @back-click="handleBackClick"
    ></ifa-foreign-stock-counter-order-input>

    <ifa-requester
      id="IfaPriceViewLookupForeignStockBrandListInitializeA001"
      action-id="SUB0202_0302-01#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaPriceViewLookupForeignStockBrandListBackButtonRedisplayA008"
      action-id="SUB0202_0302-01#A008"
      action-type="requestAction"
      :request-model="IfaPriceViewLookupForeignStockBrandListA008RequestModel"
      @response-handler="backButtonRedisplayA008($event)"
      @response-error-handler="errorHandlerA008($event)"
    ></ifa-requester>

    <ifa-requester
      id="IfaForeignStockCounterOrderInputInitializeA001"
      action-id="SUB0202_0302-02_1#A001"
      action-type="requestAction"
      :request-model="IfaForeignStockCounterOrderInputA001RequestModel"
      @response-handler="foreignStockCounterOrderInputInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import GridTable from '@/components/GridTable'
import { getDefaultOption } from '@/utils/pqgridHelper'
import IfaForeignStockCounterOrderInput from './IfaForeignStockCounterOrderInput'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaTickerBrandCodeDomainModel from '@/resource/domain/IfaTickerBrandCodeDomainModel.json'
import IfaBrandName100DomainModel from '@/resource/domain/IfaBrandName100DomainModel.json'
import IfaText10000DomainModel from '@/resource/domain/IfaText10000DomainModel.json'
import { IfaPriceViewLookupForeignStockBrandListA002RequestModel } from './js/IfaPriceViewLookupForeignStockBrandListA002RequestModel.js'
import { IfaPriceViewLookupForeignStockBrandListA008RequestModel } from './js/IfaPriceViewLookupForeignStockBrandListA008RequestModel.js'
import { IfaPriceViewLookupForeignStockBrandListFormModel } from './js/IfaPriceViewLookupForeignStockBrandListFormModel.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { IfaForeignStockCounterOrderInputA001RequestModel } from './js/IfaForeignStockCounterOrderInputA001RequestModel.js'

export default {
  components: {
    GridTable,
    IfaForeignStockCounterOrderInput,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      IfaTickerBrandCodeDomainModel: IfaTickerBrandCodeDomainModel,
      IfaBrandName100DomainModel: IfaBrandName100DomainModel,
      IfaText10000DomainModel: IfaText10000DomainModel,
      priceViewLookupIsVisible: true,
      counterOrderIsVisible: false,
      pqGridOption: getDefaultOption(obj),
      form: new IfaPriceViewLookupForeignStockBrandListFormModel(),
      backData: {
        tickerSelectFlag: '',
        brandCodeTicker: '',
        brandName: ''
      },
      orderInputData: {},
      orderInputA001RequestData: {
        selectTicker: '',
        tradeClassification: '',
        tickerSelectFlag: '',
        brandCodeTicker: '',
        brandName: ''
      }
    }
  },
  computed: {
    IfaPriceViewLookupForeignStockBrandListA002RequestModel() {
      const brandCodeTickerTemp = this.form.tickerSelectFlag === '0' ? this.form.brandCodeTicker : ''
      const brandNameTemp = this.form.tickerSelectFlag === '1' ? this.form.brandName : ''
      return new IfaPriceViewLookupForeignStockBrandListA002RequestModel(
        {
          ...this.form,
          brandCodeTicker: brandCodeTickerTemp,
          brandName: brandNameTemp
        }
      )
    },
    IfaPriceViewLookupForeignStockBrandListA008RequestModel() { return new IfaPriceViewLookupForeignStockBrandListA008RequestModel(this.backData) },
    IfaForeignStockCounterOrderInputA001RequestModel() { return new IfaForeignStockCounterOrderInputA001RequestModel(this.orderInputA001RequestData) },
    updateTime() {
      if (this.pqGridOption.dataModel.data.length >= 1) {
        return '【' + this.$_out(this.$_getFormattedTimeValue(this.form.updateTime, 'time4')) + '更新】'
      } else {
        return '【--:--更新】'
      }
    }
  },
  created() {
    this.pqGridOption.wrap = true
  },
  methods: {
    onShow() {
      this.form = new IfaPriceViewLookupForeignStockBrandListFormModel()
      this.counterOrderIsVisible = false
      this.priceViewLookupIsVisible = true
      this.$nextTick(() => {
        document.getElementById('IfaPriceViewLookupForeignStockBrandListInitializeA001').click()
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      })
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.$nextTick(() => {
        this.$refs['latestForeignSecuritiesInfoListUrl'].trigger()
        this.$refs['domesticOverTheCounterTradingManualUrl'].trigger()
        this.$refs['foreignSecuritiesInfoUpdateHistoryUrl'].trigger()
      })
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    displayA002(response) {
      this.form.updateTime = response.dataList[0]?.updateTime || ''
      this.pqGridOption.dataModel.data = response.dataList[0]?.brandList || []
      this.$refs['gridTable'].refreshView()
    },
    handleClick(ui) {
      const sellPrice = ui.rowData.sellPrice
      const buyPrice = ui.rowData.buyPrice
      if ((ui.dataIndx === 'sellPrice' && sellPrice !== '取引停止') ||
       (ui.dataIndx === 'buyPrice' && buyPrice !== '取引停止')) {
        // 入力画面のA001リクエストパラメータをセット
        this.orderInputA001RequestData.selectTicker = ui.rowData.ticker
        this.orderInputA001RequestData.tickerSelectFlag = this.form.tickerSelectFlag
        this.orderInputA001RequestData.brandCodeTicker = this.form.brandCodeTicker
        this.orderInputA001RequestData.brandName = this.form.brandName
        if (ui.dataIndx === 'sellPrice') {
          this.orderInputA001RequestData.tradeClassification = '1'
        } else if (ui.dataIndx === 'buyPrice') {
          this.orderInputA001RequestData.tradeClassification = '3'
        }
        this.$nextTick(() => {
          document.getElementById('IfaForeignStockCounterOrderInputInitializeA001').click()
        })
      }
    },
    englishDisclosureBrandEtcListA004() {
      const resolvedRoute = this.$router.resolve({ name: 'Ifa-Faq' })
      this.$store.commit('page/setFaqParam', '10003')
      window.open(resolvedRoute.href, '_blank')
      this.$store.commit('page/setFaqParam', '')
    },
    handleBackClick(param) {
      this.counterOrderIsVisible = false
      this.priceViewLookupIsVisible = true
      this.backData = param
      // (検索条件) 外国株式店頭注文入力画面から戻るボタンで戻った場合ティッカー選択フラグの値によって選択を変える
      this.form.tickerSelectFlag = param.tickerSelectFlag
      // (ティッカーキー) 外国株式店頭注文入力画面から戻るボタンで戻った場合 銘柄コード（ティッカー）をセット
      this.form.brandCodeTicker = param.brandCodeTicker
      // (名称キー) 外国株式店頭注文入力画面から戻るボタンで戻った場合 銘柄名をセット
      this.form.brandName = param.brandName
      document.getElementById('IfaPriceViewLookupForeignStockBrandListBackButtonRedisplayA008').click()
    },
    backButtonRedisplayA008(response) {
      this.form.updateTime = response.dataList[0].updateTime
      this.pqGridOption.dataModel.data = response.dataList[0].brandList
      this.$refs['gridTable'].refreshView()
    },
    errorHandlerA008(response) {
      if (response.errorLevel === -1) {
        this.pqGridOption.dataModel.data = []
        this.$refs['gridTable'].refreshView()
      }
    },
    foreignStockCounterOrderInputInitializeA001(response) {
      this.orderInputData = response.dataList[0]
      this.$nextTick(() => {
        this.$refs['ifaForeignStockCounterOrderInput'].onShow()
        // 入力画面へ遷移
        this.priceViewLookupIsVisible = false
        this.counterOrderIsVisible = true
      })
    }
  }
}

// Grid表示用データ
const obj = [
  { title: 'ティッカー', dataType: 'string', align: 'center', dataIndx: 'ticker', width: '150', editable: false, halign: 'center' },
  { title: '名称', dataType: 'string', align: 'left', dataIndx: 'name', width: '450', halign: 'center' },
  { title: '売却（USD)', dataType: 'string', align: 'right', dataIndx: 'sellPrice', width: '150', halign: 'center',
    render: function(ui) {
      const sellPrice = ui.rowData.sellPrice ? ui.rowData.sellPrice : '-'
      if (sellPrice === '取引停止') {
        return sellPrice
      } else {
        const formatSellPrice = ifaFormatUtils.withCommaNoneZeroPadding(sellPrice)
        return `<span class='grid-link'><a>` + formatSellPrice + `</a></span> 
        <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
         }
        </style>`
      }
    }
  },
  { title: '基準価格', dataType: 'string', align: 'right', dataIndx: 'basePrice8', width: '180', halign: 'center',
    render: function(ui) {
      if (ui.rowData.basePrice8) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.basePrice8)
      } else {
        return '-'
      }
    }
  },
  { title: '買付（USD)', dataType: 'string', align: 'right', dataIndx: 'buyPrice', width: '150', halign: 'center',
    render: function(ui) {
      const buyPrice = ui.rowData.buyPrice ? ui.rowData.buyPrice : '-'
      if (buyPrice === '取引停止') {
        return buyPrice
      } else {
        const formatBuyPrice = ifaFormatUtils.withCommaNoneZeroPadding(buyPrice)
        return `<span class='grid-link'><a>` + formatBuyPrice + `</a></span> 
        <style>
        .grid-link a {
          color: #092987;
          text-decoration: underline;
         &:focus, &:hover {
          color: #092987;
          text-decoration: underline;
          cursor: pointer;
          opacity: 0.7;
         }
        </style>`
      }
    }
  },
  { title: '前日終値', dataType: 'string', align: 'right', dataIndx: 'last', width: '180', halign: 'center',
    render: function(ui) {
      if (ui.rowData.last) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.last)
      } else {
        return '-'
      }
    }
  },
  { title: '前日比（%）', dataType: 'string', align: 'right', dataIndx: 'diff', width: '150', halign: 'center',
    render: function(ui) {
      if (ui.rowData.diff) {
        return ifaFormatUtils.withCommaNoneZeroPadding(ui.rowData.diff)
      } else {
        return '-'
      }
    }
  },
  { title: '取引停止/価格変更理由', dataType: 'string', align: 'left', dataIndx: 'tradeSuspendPriceChangeReason', width: '450', halign: 'center' }
]
</script>
<style lang="scss" scoped>
@import '@/styles/counterSalesInfo.scss';
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.el-radio__label) {
  font-weight: normal;
}
.faq-link {
  vertical-align: middle;
  display: inline-block;
  padding: 3px 7px;
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
}
.pq-header-outer {
  width: 100%;
}
</style>
