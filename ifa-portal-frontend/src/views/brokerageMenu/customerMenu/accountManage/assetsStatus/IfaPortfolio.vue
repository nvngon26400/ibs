<template>
  <div :class="isPrint === false ? '' : 'portfolio_print'">
    <screen-title :text="form.title.name"></screen-title>
    <div class="caption_card">
      <el-row v-show="isPrint === false">
        <el-col
          :span="23"
          style="text-align: right;"
        >
          <ifa-button
            text="印刷"
            color="primary"
            action-id="SUB0202_0101-01#A001"
            action-type="requestAction"
            @response-handler="handlePrintClickX001($event)"
            @finish-action-handler="finishHandlePrintClickX001()"
          ></ifa-button>
        </el-col>
      </el-row>

      <el-row style="padding-top: 1rem;">
        <el-col
          :span="23"
          style="text-align: right;"
        >
          <span><strong>作成日 {{ $_getFormattedDateTimeValue(form.createTime, 'datetime14') }}</strong></span>
        </el-col>
      </el-row>

      <el-row
        v-if="!hasSummaryList() && !hasHoldingSecurityList()"
        style="margin: 1rem 0;"
      >
        <el-col
          :span="24"
          style="text-align: center;"
        >
          <span v-if="form.jobStatus !== '1'">{{ form.noDetailMsg }}</span>
          <span v-else>{{ form.jobRunningMsg }}</span>
        </el-col>
      </el-row>

      <el-row
        v-if="hasSummaryList()"
        style="margin-bottom: 1.5rem;"
      >
        <el-col
          :span="isPrint ? 24 : 22"
          :offset="isPrint ? 0 : 1"
          :style="{ paddingTop: '0.5rem', display: 'flex', justifyContent: 'space-between', gap: isPrint ? '5mm' : '60px' }"
        >
          <div
            :style="{ flex: '2 1 auto' }"
          >
            <div style="padding-bottom: 0.5rem;"><span>●<strong>資産状況</strong></span></div>
            <assets-table
              id="assetsTable"
              :assets-table-data="portfolioSummary"
              :is-print="isPrint"
            ></assets-table>
          </div>
          <div
            :style="{ flex: 'none', width: isPrint ? '75mm' : '540px' }"
          >
            <div style="padding-bottom: 0.5rem;"><span>●<strong>商品別資産比率</strong></span></div>
            <doughnat-chart
              :key="chartKey"
              :height="isPrint ? 175 : 370"
              :chart-data-form="portfolioSummary"
              :is-print="isPrint"
            ></doughnat-chart>
          </div>
        </el-col>
      </el-row>

      <el-row v-if="hasHoldingSecurityList()">
        <el-col
          :span="isPrint ? 24 : 22"
          :offset="isPrint ? 0 : 1"
        >
          <span>●<strong>保有商品一覧</strong></span>
        </el-col>
      </el-row>

      <el-row>
        <el-col
          :span="isPrint ? 24 : 22"
          :offset="isPrint ? 0 : 1"
          style="padding-top: 0.5rem;"
        >
          <portfolio-table
            :portfolio-data="form"
            :is-print="isPrint"
          ></portfolio-table>
        </el-col>
      </el-row>
    </div>
    <ifa-requester
      id="IfaPortfolioInitializeA001"
      action-id="SUB0202_0101-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001($event)"
      @response-error-handler="responseErrorHandlerA001($event)"
      @finish-action-handler="finishActionHandlerA001()"
    ></ifa-requester>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import DoughnatChart from '@/views/wholePortal/components/DoughnatChart'
import AssetsTable from '@/views/wholePortal/components/AssetsTable'
import portfolioTable from '@/views/wholePortal/components/portfolioTable'
import { IfaPortfolioFormModel } from './js/IfaPortfolioFormModel'

export default {
  components: {
    screenTitle,
    DoughnatChart,
    AssetsTable,
    portfolioTable
  },
  emits: [
    'initializeError'
  ],
  data() {
    return {
      chartKey: 0,
      form: new IfaPortfolioFormModel(),
      isPrint: true,
      formData: {
        totalProfitOrLossValuation: [],
        totalAppraisedValue: []
      },
      chartHeight: 600,
      chartWidth: 1080,
      isPrintLoading: false,
      loading: null,
      iframe: null
    }
  },
  computed: {
    portfolioSummary() {
      // 表示順テーブル: 資産状況をテーブルに登録された順にソートして表示させる
      // テーブルに登録する商品種別(Security Class) はコードテーブルの'種別:63(証券種別（資産状況）)'に登録される値と一致していること
      const dispalyOrder = [
        '国内株式', '国内債券', '投資信託', '外国債券(円建)', '外国株式', '外国建MMF', '外国債券(外貨建)',
        'ST', '現金(円貨)', '現金(外貨)', '先OP(保証金等)'
      ]
      function findSummary(self, securityClassName) {
        return self.form.portfolioSummaryList.find(summary => summary.securityClass === securityClassName)
      }

      const summaryList = []
      dispalyOrder.forEach(securityClassName => {
        const summary = findSummary(this, securityClassName)
        if (summary) summaryList.push(summary)
      })
      const summary = {
        portfolioSummaryList: summaryList,
        portfolioSummaryValuationTotal: this.form.portfolioSummaryValuationTotal,
        portfolioSummaryProfitAndLossTotal: this.form.portfolioSummaryProfitAndLossTotal
      }
      return summary
    }
  },
  watch: {
    'form.portfolioSummaryList': {
      handler: function() {
        this.$nextTick(() => {
          const assetsTable = document.getElementById('assetsTable')

          if (assetsTable) {
            const offsetHeight = document.getElementById('assetsTable').offsetHeight
            const offsetWidth = document.getElementById('assetsTable').offsetWidth
            this.chartHeight = offsetHeight < 270 ? 270 : offsetHeight > 370 ? 370 : offsetHeight
            this.chartWidth = offsetWidth < 400 ? 400 : offsetWidth > 500 ? 500 : offsetWidth
          }
        })
      },
      deep: true
    }
  },
  mounted() {
    if (this.isPrint === true) {
      this.onShow()
    }
  },
  unmounted() {
    if (this.loading) {
      if (this.isPrintLoading) {
        // 何らかのエラーが発生してisPringLoadingがtrueのままの場合、ローディングをクローズさせる
        this.loading.close()
      }
      this.isPrintLoading = false
      this.iframe = null
    }
  },
  created() {
    // iframeを作成
    this.iframe = document.createElement('iframe')
    this.iframe.style.visibility = 'hidden'

    // iframeの読み込み完了後、ロード画面を表示する
    this.iframe.onload = () => {
      if (this.isPrintLoading) {
        // iframeから完了メッセージを受け取ったら印刷プレビューを表示する
        window.addEventListener('message', (event) => {
          if (event.data === 'iframeContentLoaded' && this.iframe && this.iframe.contentWindow) {
            this.loading.close()
            this.iframe.contentWindow.print()
            document.body.removeChild(this.iframe)
            this.isPrintLoading = false
          }
        })
      }
    }
  },
  methods: {
    onShow() {
      this.form = new IfaPortfolioFormModel()
      this.$nextTick(() => {
        document.getElementById('IfaPortfolioInitializeA001').click()
      })
    },
    responseHandlerA001(response) {
      Object.assign(this.form, response.dataList[0])
      this.form.createTime = response.requestedTime
      this.chartKey++
    },
    finishActionHandlerA001() {
      if (this.isPrint) {
        // iframeの表示待ちのため、5秒間時間を置く
        setTimeout(() => {
          window.parent.postMessage('iframeContentLoaded', '*')
        }, 5000)
      }
    },
    responseErrorHandlerA001(response) {
      const errorInfo = {
        title: this.form.title.name,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    setIsPrint(value) {
      this.isPrint = value
    },
    handlePrintClickX001() {
      if (!this.isPrintLoading) {
        this.isPrintLoading = true
        // iframeに印刷用の資産状況の画面を埋め込む
        const resolvedRoute = this.$router.resolve({
          name: 'Ifa-Portfolio'
        })
        this.iframe.src = resolvedRoute.href
        document.body.appendChild(this.iframe)
      }
    },
    finishHandlePrintClickX001() {
      // ローディング画面を表示
      // ifaButton側でレスポンスが返却された際にloading.close()の処理が行われているため、finishActionHandlerで実装
      if (this.isPrintLoading) {
        this.loading = this.$loading({
          lock: true,
          text: '処理中',
          background: 'rgba(0, 0, 0, 0.4)'
        })
      }
    },
    hasSummaryList() {
      return this.form.portfolioSummaryList && this.form.portfolioSummaryList.length > 0
    },
    hasHoldingSecurityList() {
      if (this.form.holdingSecurityDomesticStock && this.form.holdingSecurityDomesticStock.length > 0 ||
          this.form.holdingSecurityListDomesticClaim && this.form.holdingSecurityListDomesticClaim.length > 0 ||
          this.form.holdingSecurityListMutualFund && this.form.holdingSecurityListMutualFund.length > 0 ||
          this.form.holdingSecurityListForeignClaimYenBase && this.form.holdingSecurityListForeignClaimYenBase.length > 0 ||
          this.form.holdingSecurityListForeignStock && this.form.holdingSecurityListForeignStock.length > 0 ||
          this.form.holdingSecurityListForeignMmf && this.form.holdingSecurityListForeignMmf.length > 0 ||
          this.form.holdingSecurityListForeignClaimForeign && this.form.holdingSecurityListForeignClaimForeign.length > 0 ||
          this.form.holdingSecurityListForeignClaimForeignStructuredBond && this.form.holdingSecurityListForeignClaimForeignStructuredBond.length > 0 ||
          this.form.holdingSecurityListCash && this.form.holdingSecurityListCash.length > 0 ||
          this.form.holdingSecurityListSbiRapAccountCash && this.form.holdingSecurityListSbiRapAccountCash.length > 0 ||
          this.form.holdingSecurityListForeignDeposit && this.form.holdingSecurityListForeignDeposit.length > 0 ||
          this.form.holdingSecurityListSecurityToken && this.form.holdingSecurityListSecurityToken.length > 0 ||
          this.form.holdingSecurityListMarginPosition && this.form.holdingSecurityListMarginPosition.length > 0 ||
          this.form.holdingSecurityListUsStockMarginPositionList && this.form.holdingSecurityListUsStockMarginPositionList.length > 0 ||
          this.form.holdingSecurityListMutualFundTotalReturnList && this.form.holdingSecurityListMutualFundTotalReturnList.length > 0) {
        return true
      } else {
        return false
      }
    }
  }
}

</script>
<style lang="scss" scoped>
@import "@/styles/table.scss";
.caption_card {
  padding: 5px 15px 20px 15px;
}
.portfolio_print {
  width: 100%;
  font-size: 8px;
  height: 100%;
  overflow: scroll;
}
iframe {
  overflow: hidden;
}
@page {
  margin: 10mm;
}
@media print {
  * {
    -ms-overflow-style: none;
    ::-webkit-scrollbar, body *::-webkit-scrollbar {
      display: none;
    }
  }

  th._table_light_header {
    padding-left: 0 !important;
    padding-right: 0 !important;
  }
}
</style>
