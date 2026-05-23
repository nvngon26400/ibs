<template>
  <div>
    <!-- 出来明細 -->
    <el-dialog
      v-model="vmIsVisible"
      :title="form.title"
      width="850px"
      :center="true"
      :close-on-click-modal="false"
      :before-close="closeA002"
      :show-close="false"
    >
      <div class="close-button">
        <ifa-button
          id="btnUpdate"
          text="更新"
          width="90"
          color="primary"
          icon="RefreshRight"
          action-type="requestAction"
          :request-model="A003RequestModel"
          action-id="SUB0202_0105-02#A003"
          @response-handler="initializeA003($event)"
        ></ifa-button>
        <ifa-button
          id="btnClose"
          text="閉じる"
          width="90"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="closeA002"
        ></ifa-button>
      </div>
      <div class="tradingInfo">
        <el-row>
          <el-col
            :span="3"
            class="fontBoid"
          >口座番号：</el-col><el-col :span="12">
            {{ `${customerInfo().butenCode}-${customerInfo().accountNumber} ${customerInfo().customerNameKanji}（${customerInfo().customerNameKana}）` }}</el-col>
          <el-col
            :span="9"
            class="text-right"
          ><span class="fontBoid blank">更新日時:</span>{{ $_out(formatDateTime(form.updateTime)) }}</el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="fontBoid"
          >銘柄：</el-col><el-col :span="21">{{ !form.brandCode && !form.brandName ? '-' : `${form.brandCode} ${form.brandName}` }}</el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="fontBoid"
          >取引種別：</el-col>
          <el-col
            :span="21"
            :class="isFontColor(form.tradeClassification) ? 'font-color__plus bold' : 'font-color__minus bold'"
          >
            <ifa-text
              :style="isFontColor(form.tradeClassification) ? 'color: #E5004C;' : 'color: #00847F;'"
              code-list-id="DOMESTIC_STOCK_TRADE_CLASS"
              disp-pattern="2"
              :code-key="form.tradeClassification"
            ></ifa-text>
            <span
              v-if="form.tradeClassification === '3' ||
                form.tradeClassification === '4' ||
                form.tradeClassification === '5' ||
                form.tradeClassification === '6' "
            >({{ $_out(form.marginTradeTypeText) }})</span>
            <span
              v-if="!form.tradeClassification && !form.marginTradeTypeText"
              style="color: #18181a;"
            >-</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="fontBoid"
          >約定日：</el-col><el-col :span="21">{{ $_out(formatDate(form.tradeDate)) }}</el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="fontBoid"
          >受渡日：</el-col><el-col :span="21">{{ $_out(formatDate(form.deliveryDate)) }}</el-col>
        </el-row>
      </div>
      <div class="detail-table">
        <table
          cellspacing="0"
          cellpadding="0"
          border="0"
          class="el-table"
        >
          <thead>
            <tr>
              <th
                colspan="1"
                rowspan="1"
              >約定時刻</th>
              <th
                colspan="1"
                rowspan="1"
              >約定数量</th>
              <th
                colspan="1"
                rowspan="1"
              >約定単価</th>
              <th
                colspan="1"
                rowspan="1"
              >約定金額</th>
              <th
                colspan="1"
                rowspan="1"
              >市場</th>
              <th
                colspan="1"
                rowspan="1"
              >預り区分</th>
              <th
                colspan="1"
                rowspan="1"
              >EC受注番号</th>
              <th
                colspan="1"
                rowspan="1"
              >ステータス</th>
            </tr>
          </thead>
          <tbody
            v-for="(item, idx) in form.tradeList"
            :key="idx"
          >
            <tr>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              ><!--約定時刻-->{{ $_out(formatTime(item.tradeTime)) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              ><!--約定株数-->{{ $_out(ifaFormatUtils.withCommaInteger(item.tradeQuantity)) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              ><!--約定単価-->{{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(item.tradePrice)) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              ><!--約定金額-->{{ $_out(ifaFormatUtils.withCommaInteger(item.contractAmount)) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              ><!--市場-->{{ $_out(item.market) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              ><!--預り区分-->
                <ifa-text
                  code-list-id="DOMESTIC_DEPOSIT_TYPE"
                  disp-pattern="3"
                  :code-key="item.depositType"
                ></ifa-text>
                <span v-if="!item.depositType">-</span>
              </td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              ><!--EC受注番号-->{{ $_out(item.ecOrderNo) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              ><!--ステータス-->
                <ifa-text
                  code-list-id="CANCEL_STATUS"
                  disp-pattern="1"
                  :code-key="item.cancelStatus"
                ></ifa-text>
                <span v-if="!item.cancelStatus">-</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div
        v-if="form.tradeClassification == 5 || form.tradeClassification == 6"
        class="detail-table"
      >
        <h3>決済建玉</h3>
        <table
          cellspacing="0"
          cellpadding="0"
          border="0"
          class="el-table"
        >
          <thead>
            <tr>
              <th
                colspan="1"
                rowspan="1"
              >新規建日<br>(返済期限)</th>
              <th
                colspan="1"
                rowspan="1"
              >建市場</th>
              <th
                colspan="1"
                rowspan="1"
              >親株新規約定日</th>
              <th
                colspan="1"
                rowspan="1"
              >建単価</th>
              <th
                colspan="1"
                rowspan="1"
              >注文数量</th>
              <th
                colspan="1"
                rowspan="1"
              >約定数量</th>
              <th
                colspan="1"
                rowspan="1"
              >平均約定単価</th>
              <th
                colspan="1"
                rowspan="1"
              >手数料/諸経費</th>
              <th
                colspan="1"
                rowspan="1"
              >決済損益</th>
            </tr>
          </thead>
          <tbody
            v-for="(item, idx) in form.settlementPositionList"
            :key="idx"
          >
            <tr>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              >{{ $_out(formatDate(item.openTradeDate)) }}<br>({{ item.lastTradeDate ? formatDate(item.lastTradeDate) : '----/--/--' }})</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              >
                <ifa-text
                  code-list-id="NEW_MARKET"
                  disp-pattern="1"
                  :code-key="item.bargainMarket"
                ></ifa-text>
                <span v-if="!item.bargainMarket">-</span>
              </td>
              <td
                colspan="1"
                rowspan="1"
                class="text-center"
              >{{ $_out(formatDate(item.orgNewTradeDate)) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              >{{ $_out((ifaFormatUtils.withCommaNoneZeroPadding(item.builtPrice))) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              >{{ $_out((ifaFormatUtils.withCommaInteger(item.orderQuantity))) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              >{{ $_out(item.actualQuantity === '0' ? '-' : (ifaFormatUtils.withCommaInteger(item.actualQuantity))) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              >{{ $_out(ifaFormatUtils.withCommaNoneZeroPadding(formatPrice(item.averageTradePrice, item.actualQuantity ))) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
              >{{ $_out(ifaFormatUtils.withCommaInteger(formatPrice(item.costTotal, item.actualQuantity))) }}</td>
              <td
                colspan="1"
                rowspan="1"
                class="text-right"
                :class="{ 'font-color__plus bold': item.settlementLossProfit > 0 && item.actualQuantity !== '0', 'font-color__minus bold': item.settlementLossProfit < 0 && item.actualQuantity !== '0'}"
              >
                <span v-if="item.settlementLossProfit > 0 && item.actualQuantity !== '0'">+</span>{{ $_out(ifaFormatUtils.withCommaInteger(formatPrice(item.settlementLossProfit, item.actualQuantity))) }}
              </td>
            </tr>
          </tbody>
        </table>

      </div>
    </el-dialog>
    <ifa-requester
      id="IfaMatchedTradeDetailA001"
      action-id="SUB0202_0105-02#A001"
      action-type="requestAction"
      :request-model="IfaMatchedTradeDetailA001RequestModel"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaMatchedTradeDetailFormModel } from './js/IfaMatchedTradeDetailFormModel'
import { IfaMatchedTradeDetailA003RequestModel } from './js/IfaMatchedTradeDetailA003RequestModel'
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
import { IfaDomesticTradeStatusListA002RequestModel } from './js/IfaDomesticTradeStatusListA002RequestModel'
import { IfaMatchedTradeDetailA001RequestModel } from './js/IfaMatchedTradeDetailA001RequestModel'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    detailData: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible', 'responseHandle'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaDomesticTradeStatusListA002RequestModel: {},
      form: new IfaMatchedTradeDetailFormModel(),
      ifaFormatUtils: ifaFormatUtils
    }
  },
  computed: {
    IfaMatchedTradeDetailA001RequestModel() {
      return new IfaMatchedTradeDetailA001RequestModel(this.IfaDomesticTradeStatusListA002RequestModel)
    },
    ifaDomesticTradeStatusListA002RequestModel() {
      return new IfaDomesticTradeStatusListA002RequestModel(this.IfaDomesticTradeStatusListA002RequestModel)
    },
    A003RequestModel() {
      return new IfaMatchedTradeDetailA003RequestModel(this.form)
    },
    isFontColor() {
      return function(data) {
        if (data === '1' || data === '3' || data === '5' || data === '7') {
          return true
        } else {
          return false
        }
      }
    }
  },
  methods: {
    initializeA001() {
      this.IfaMatchedTradeDetailA001RequestModel.marginTradeTypeText = this.IfaDomesticTradeStatusListA002RequestModel.marginTradeClassification
      this.IfaMatchedTradeDetailA001RequestModel.deliveryDate = this.IfaDomesticTradeStatusListA002RequestModel.settlementDate
      this.IfaMatchedTradeDetailA001RequestModel.notSpecificDepositTradeType = this.IfaDomesticTradeStatusListA002RequestModel.depositType
      this.$nextTick(() => {
        document.getElementById('IfaMatchedTradeDetailA001').click()
      })
    },
    onShow() {
      Object.assign(this.form, this.detailData)
    },
    setA001Req(A001ReqModel) {
      this.IfaDomesticTradeStatusListA002RequestModel = new IfaDomesticTradeStatusListA002RequestModel(A001ReqModel)
    },
    responseHandlerInitializeA001(response) {
      Object.assign(this.detailData, response.dataList[0])
      this.$emit('responseHandle')
    },
    initializeA003: function(data) {
      // APIレスポンス正常時業務処理（必須）
      this.form = Object.assign(this.form, data.dataList[0])
    },
    closeA002() {
      this.$emit('close-modal')
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    formatDate(val) {
      return getFormattedDateValue(val)
    },
    formatDateTime(val) {
      return getFormattedDateTimeValue(val)
    },
    formatPrice(price, quantity) {
      if (price === '0' || quantity === '0') {
        return '-'
      } else {
        return price
      }
    },
    formatTime(hhmmss) {
      if (!hhmmss) {
        return
      } else {
        const hours = hhmmss.substring(0, 2)
        const minutes = hhmmss.substring(2, 4)
        const seconds = hhmmss.substring(4, 6)
        const formattedTime = `${hours}:${minutes}:${seconds}`
        return formattedTime
      }
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
:deep(.el-dialog__body) {
  color: #18181A;
  margin: 0 1.5rem 2rem 1.5rem
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
.tradingInfo div {
  padding: 3px 2px;
}
.tradingInfo .fontBoid{
  font-weight: bold;
}
.detail-table{
  margin: 1rem 0;
  overflow-y: auto;
}
.blank {
  margin-right: 30px;
}
</style>
