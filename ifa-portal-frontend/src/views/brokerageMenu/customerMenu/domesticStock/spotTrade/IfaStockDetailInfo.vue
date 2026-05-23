<template>
  <el-dialog
    v-model="vmIsVisible"
    :center="true"
    :title="form.title"
    :close-on-click-modal="true"
    width="700px"
    class="order-status-list__el-dialog--disabled stock-detail_dialog-class"
    :show-close="false"
    :before-close="onDialogClose"
    @open="onShow"
  >

    <!-- 閉じるボタン -->
    <el-row>
      <el-col style="text-align: right;">
        <ifa-button
          text="閉じる"
          color="secondary"
          style="padding-right: 0;"
          width="86"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

    <div>
      <!-- 銘柄情報 -->
      <el-row style="display: flex; align-items: center;">
        <el-col :span="24">
          <el-card
            class="box-card"
            style="background-color: #eee; margin: 0.5rem 0;"
          >
            <el-row
              class="_bold_black_l"
              style="font-size: 20px; display: flex; align-items: flex-start;"
            >
              <div style="display: inline-block; width: 90px;">
                <!-- 銘柄コード -->
                <span>［{{ $_out(form.brandCode) }}］</span>
              </div>
              <div style="display: inline-block;">
                <!-- 銘柄名 -->
                <span>{{ $_out(form.brandName) }}</span>
              </div>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
      <hr>
      <el-row class="form-button__wrapper">
        <el-col
          :span="8"
          style="padding-top: 0.8rem; text-align: right;"
        >
          更新日時: {{ $_getFormattedDateTimeValue(form.updateTime, 'datetime12') }}
        </el-col>
        <el-col
          :span="4"
          style="text-align: right;"
        >
          <ifa-button
            if="btnUpdate"
            text="更新"
            color="primary"
            icon="RefreshRight"
            style="padding-right: 0;"
            action-type="requestAction"
            action-id="SUB0202_0208-02#A002"
            :request-model="IfaStockDetailInfoA002RequestModel"
            @response-handler="responseHandlerA002($event)"
          ></ifa-button>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-row
        class="_bold_black_l"
        style="padding-left: 1rem;"
      >
        <el-col :span="4">
          <!-- 市場 -->
          {{ $_getCodeValue('SELECT_MARKET', 1, form.market) }}
        </el-col>
        <el-col :span="4">
          <!-- 一日信用買建区分 -->
          <el-row v-if="form.onedayCreditBuyCategory === '1'">
            <span>
              {{ $_getCodeValue('MARGIN_LONG_POSITION_TYPE', 1, form.onedayCreditBuyCategory) }}
              <span class="font-color__plus">
                {{ $_getCodeValue('MARGIN_LONG_POSITION_TYPE', 2, form.onedayCreditBuyCategory) }}
              </span>
            </span>
          </el-row>
          <!-- 一日信用売建区分 -->
          <el-row v-if="form.onedayCreditSellCategory === '1' && form.premiumShortSaleCcategory !== '1'">
            <span>
              {{ $_getCodeValue('MARGIN_SHORT_POSITION_TYPE', 1, form.onedayCreditSellCategory) }}
              <span class="font-color__minus">
                {{ $_getCodeValue('MARGIN_SHORT_POSITION_TYPE', 2, form.onedayCreditSellCategory) }}
              </span>
            </span>
          </el-row>
          <!-- プレミアム空売り区分 -->
          <el-row v-if="form.onedayCreditSellCategory === '1' && form.premiumShortSaleCcategory === '1'">
            <span>
              {{ $_getCodeValue('PREMIUM_SHORT_SELLING_TYPE', 1, form.premiumShortSaleCcategory) }}
              <span class="font-color__minus">
                {{ $_getCodeValue('PREMIUM_SHORT_SELLING_TYPE', 2, form.premiumShortSaleCcategory) }}
              </span>
            </span>
          </el-row>
        </el-col>
      </el-row>
      <table style="width: 100%; padding-top: 0.5rem;">
        <tbody>
          <tr>
            <th
              class="_table__header __left"
              style="width:30%;"
            >
              現在値
            </th>
            <td class="_table__data __left">
              <!-- 現在値 -->
              <span style="font-weight: bold">
                {{ formatCurrency172DigitsB(form.currentPrice) }}
              </span>
              <span style="margin-left: 1rem">
                <!-- 現在値ティック -->
                <span :class="form.tick === '1' ? 'font-color__plus' : 'font-color__minus'">
                  {{ currentPriceIsNan ? ' ' : $_getCodeValue('CURRENT_TICK', 1, form.tick) }}
                </span>
                <!-- 現在値フラグ -->
                {{ currentPriceIsNan ? ' ' : $_getCodeValue('CURRENT_PRICE_FLAG', 2, form.currentFlag) }}
              </span>
            </td>
          </tr>
          <tr>
            <th
              class="_table__header __left"
              style="width: 30%;"
            >{{ form.market === '7' ? '基準値比' : '前日比' }}
            </th>
            <td class="_table__data __left">
              <!-- 前日比 -->
              <span v-if="currentPriceIsNan || isInvalidNumber((form.diff))">-</span>
              <span v-else-if="form.diff >= 0"
                    style="color: red; margin: 0 0.2rem 0 0.2rem; font-weight: bold"
              >{{ formatSignedYen172DigitsB(form.diff) }}</span>
              <span v-else
                    style="color: blue; margin:0 0.2rem 0 0.2rem; font-weight: bold"
              >{{ formatSignedYen172DigitsB(form.diff) }}</span>
              <!-- 前日比率（％） -->
              <span>(</span>
              <span v-if="currentPriceIsNan || isInvalidNumber(form.ratio)">-%</span>
              <span v-else-if="form.ratio >= 0"
                    style="color: red; margin:0 0.2rem 0 0.2rem; font-weight: bold"
              >{{ formatSignedRate62(form.ratio) }}%</span>
              <span v-else
                    style="color: blue; margin:0 0.2rem 0 0.2rem; font-weight: bold"
              >{{ formatSignedRate62(form.ratio) }}%</span>
              <span>)</span>
              <span style="margin:0 0.2rem 0 0.2rem">
                <!-- 更新日付 -->
                {{ currentPriceIsNan || isInvalidNumber(form.updateDate) || !isDate(form.updateDate) ? '(--/--/--' : '('+$_getFormattedDateValue(form.updateDate) }}
                <!-- 更新時刻 -->
                {{ currentPriceIsNan || isTime(form.updateTime4) ? '--:--)' : formatTime(form.updateTime4)+')' }}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <table style="margin-top:1rem;width:100%;">
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >始値</th>
          <td class="_table__data __left">
            <!-- 始値 -->
            {{ formatCurrency172DigitsB(form.start) }}
            <!-- 始値時刻 -->
            {{ isTime(form.startTime) ? '(--:--)' : '('+formatTime(form.startTime)+')' }}</td>
        </tr>
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >高値</th>
          <td class="_table__data __left">
            <!-- 高値 -->
            {{ formatCurrency172DigitsB(form.high) }}
            <!-- 高値時刻 -->
            {{ isTime(form.highTime) ? '(--:--)' : '('+formatTime(form.highTime)+')' }}</td>
        </tr>
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >安値</th>
          <td class="_table__data __left">
            <!-- 安値 -->
            {{ formatCurrency172DigitsB(form.low) }}
            <!-- 安値時刻 -->
            {{ isTime(form.lowTime) ? '(--:--)' : '('+formatTime(form.lowTime)+')' }}</td>
        </tr>
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >{{ form.market === '7' ? '基準値' : '前日終値' }}</th>
          <td class="_table__data __left">
            <!-- 前日終値 -->
            {{ formatCurrency172DigitsB(form.last) }}
            <!-- 前日終値日付 -->
            {{ isInvalidNumber(form.lastDate) || !isDate(form.lastDate) ? '(--/--/--)' : '('+$_getFormattedDateValue(form.lastDate, 'date6')+')' }}</td>
        </tr>
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >出来高</th>
          <td class="_table__data __left">{{ isInvalidNumber(form.volume) ? '-' : $_addComma(form.volume) }}</td>
        </tr>
        <tr>
          <th
            class="_table__header __left"
            style="width:30%;"
          >売買代金</th>
          <td class="_table__data __left">{{ formatYen170(form.buySellPrice) }}(千円)</td>
        </tr>
      </table>
    </div>
    <ifa-board-table
      :is-loading="loading"
      :input-table-data="generateBoardTableData()"
      :market="form.market"
    ></ifa-board-table>
    <div style="margin-top:1rem;">
      <table style="margin-bottom: 0.5rem;width:100%">
        <tbody>
          <tr>
            <th class="_table__header __left">年初来高値</th>
            <td class="_table__data __right">-</td>
            <th class="_table__header __left">年初来安値</th>
            <td class="_table__data __right">-</td>
          </tr>
          <tr>
            <th class="_table__header __left">信用売残</th>
            <td class="_table__data __right">-</td>
            <th class="_table__header __left">信用売残前週比</th>
            <td class="_table__data __right">-</td>
          </tr>
          <tr>
            <th class="_table__header __left">信用買残</th>
            <td class="_table__data __right">-</td>
            <th class="_table__header __left">信用買残前週比</th>
            <td class="_table__data __right">-</td>
          </tr>
          <tr>
            <th class="_table__header __left">貸借倍率</th>
            <td class="_table__data __right">-倍</td>
            <th class="_table__header __left">信用/貸借</th>
            <td class="_table__data __right">{{ $_getCodeValue('MARGIN_LOAN', 1, form.creditLoan) || '-' }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaBoardTable from '@/views/brokerageMenu/customerMenu/components/boardTable/IfaBoardTable'
import { IfaStockDetailInfoFormModel } from './js/IfaStockDetailInfoFormModel'
import { IfaStockDetailInfoA002RequestModel } from './js/IfaStockDetailInfoA002RequestModel'
import ifaFormatUtils from '@/utils/ifaFormatUtils'

export default {
  components: {
    IfaBoardTable
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
      required: true,
      default: () => ({})
    }
  },
  emits: ['close-modal', 'order-finish', 'price-select', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaStockDetailInfoFormModel(),
      loading: false,
      ifaFormatUtils: ifaFormatUtils
    }
  },
  computed: {
    IfaStockDetailInfoA002RequestModel() { return new IfaStockDetailInfoA002RequestModel(this.form) },
    // 現在値数値以外
    currentPriceIsNan() {
      return !+this.form.currentPrice
    }
  },
  methods: {
    onShow() {
      this.form = Object.assign(this.form, this.formData)
    },
    isTime(str) {
      if (!str) {
        return true
      }
      const h = parseInt(str.substring(0, 2))
      const m = parseInt(str.substring(2, 4))
      return !((h >= 0 && h <= 23) && (m >= 0 && m <= 59))
    },
    formatTime(time) {
      if (!time) {
        return '--:--'
      }
      const h = time.substring(0, 2)
      const m = time.substring(2, 4)
      return h + ':' + m
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    onOrderFinish() {
      this.$emit('order-finish')
    },
    notifySelectedValue(value) {
      this.$emit('price-select', value)
    },
    responseHandlerA002(response) {
      Object.assign(this.form, response.dataList[0])
    },
    a002ActionHandler(data) {
      this.form = Object.assign(this.form, data.dataList[0])
      this.generateBoardTableData()
    },
    generateBoardTableData() {
      const tableData = []
      if (this.form.market === '7') {
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume5, '', this.form.sellSignPrice5))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume4, '', this.form.sellSignPrice4))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume3, '', this.form.sellSignPrice3))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume2, '', this.form.sellSignPrice2))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume1, this.form.sellSignPriceFlg, this.form.sellSignPrice1))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume1, this.form.buySignPriceFlg, this.form.buySignPrice1))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume2, '', this.form.buySignPrice2))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume3, '', this.form.buySignPrice3))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume4, '', this.form.buySignPrice4))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume5, '', this.form.buySignPrice5))
      } else {
        tableData.push(this.generateMarketBoardTableObject(this.form.executionSellOrderQuantity, this.form.executio))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolumeOver, '', 'OVER'))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume10, '', this.form.sellSignPrice10))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume9, '', this.form.sellSignPrice9))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume8, '', this.form.sellSignPrice8))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume7, '', this.form.sellSignPrice7))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume6, '', this.form.sellSignPrice6))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume5, '', this.form.sellSignPrice5))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume4, '', this.form.sellSignPrice4))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume3, '', this.form.sellSignPrice3))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume2, '', this.form.sellSignPrice2))
        tableData.push(this.generateSellBoardTableObject(this.form.sellSignVolume1, this.form.sellSignPriceFlg, this.form.sellSignPrice1))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume1, this.form.buySignPriceFlg, this.form.buySignPrice1))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume2, '', this.form.buySignPrice2))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume3, '', this.form.buySignPrice3))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume4, '', this.form.buySignPrice4))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume5, '', this.form.buySignPrice5))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume6, '', this.form.buySignPrice6))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume7, '', this.form.buySignPrice7))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume8, '', this.form.buySignPrice8))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume9, '', this.form.buySignPrice9))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolume10, '', this.form.buySignPrice10))
        tableData.push(this.generateBuyTableObject(this.form.buySignVolumeUnder, '', 'UNDER'))
      }
      return tableData
    },
    generateMarketBoardTableObject(sellSignVolume, buySignVolume) {
      const formattedSellSignVolume = this.formatStocks(sellSignVolume)
      const formattedBuySignVolume = this.formatStocks(buySignVolume)
      return { sellSignVolume: formattedSellSignVolume, sellSignPriceFlg: '', signPrice: '成行', buySignPriceFlg: '', buySignVolume: formattedBuySignVolume }
    },
    generateSellBoardTableObject(sellSignVolume, sellSignPriceFlg, signPrice) {
      const formattedSellSignVolume = this.formatStocks(sellSignVolume, signPrice === 'OVER' ? '-' : '')
      const formattedSignPrice = signPrice === 'OVER'
        ? signPrice
        : this.formatCurrency172DigitsB(signPrice)
      return {
        sellSignVolume: formattedSellSignVolume,
        sellSignPriceFlg: sellSignPriceFlg,
        signPrice: formattedSignPrice,
        buySignPriceFlg: '',
        buySignVolume: ''
      }
    },
    generateBuyTableObject(buySignVolume, buySignPriceFlg, signPrice) {
      const formattedBuySignVolume = this.formatStocks(buySignVolume, signPrice === 'UNDER' ? '-' : '')
      const formattedSignPrice = signPrice === 'UNDER'
        ? signPrice
        : this.formatCurrency172DigitsB(signPrice)
      return {
        sellSignVolume: '',
        sellSignPriceFlg: '',
        signPrice: formattedSignPrice,
        buySignPriceFlg: buySignPriceFlg,
        buySignVolume: formattedBuySignVolume
      }
    },
    isDate(str) {
      if (!str) {
        return false
      }
      const arr = (str.substr(0, 4) + '/' + str.substr(4, 2) + '/' + str.substr(6, 2)).split('/')
      if (arr.length !== 3) return false
      const date = new Date(arr[0], arr[1] - 1, arr[2])
      if (arr[0] !== String(date.getFullYear()) || arr[1] !== ('0' + (date.getMonth() + 1)).slice(-2) || arr[2] !== ('0' + date.getDate()).slice(-2)) {
        return false
      } else {
        return true
      }
    },
    getDecimalPlaces(numberStr, maxPlaces) {
      if (!numberStr) {
        return 0
      }

      const splitedNumberStr = numberStr.split('.')
      if (splitedNumberStr.length < 2) {
        return 0
      } else {
        const decimalPlaces = splitedNumberStr[1].length
        return decimalPlaces <= maxPlaces ? decimalPlaces : maxPlaces
      }
    },
    isInvalidNumber(number) {
      return !+number
    },
    formatCurrency172DigitsB(number) {
      // 数値か否かの判定
      if (!+number) {
        return '-'
      }

      // #,##0.#の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.withCommaNoneZeroPadding(number))
    },
    formatFxRate172(number) {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return '-'
      }

      // #,##0.0の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.withCommaZeroPadding(number, 2))
    },
    formatSignedRate62(number) {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return '-'
      }

      // 先頭に'+'がついていると、フォーマッターで不具合が起きるため除去
      const strippedNumber = number.replace(/^\+/, '')

      // ±#,##0.0の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.signedWithCommaZeroPadding(strippedNumber, 2))
    },
    formatSignedStocks(number) {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return '-'
      }

      // 先頭に'+'がついていると、フォーマッターで不具合が起きるため除去
      const strippedNumber = number.replace(/^\+/, '')

      // ±#,##0の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.signedWithCommaInteger(strippedNumber))
    },
    formatSignedYen172DigitsB(number) {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return '-'
      }

      // 先頭に'+'がついていると、フォーマッターで不具合が起きるため除去
      const strippedNumber = number.replace(/^\+/, '')

      // ±#,##0.#の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.signedWithCommaNoneZeroPadding(strippedNumber))
    },
    formatStocks(number, defaultValue = '-') {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return defaultValue
      }

      // #,##0の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.withCommaInteger(number))
    },
    formatYen170(number) {
      // 数値か否かの判定
      if (this.isInvalidNumber(number)) {
        return '-'
      }

      // #,##0.#の形式でフォーマット
      return this.$_out(this.ifaFormatUtils.withCommaInteger(number))
    }
  }
}
</script>

<style lang="scss">
@import "@/styles/orderStatusList.scss";
.stock-detail_dialog-class {
  .el-dialog__body {
    padding-top: 0 !important;
  }
  .el-dialog__title{
    font-weight: bold;
  }
}
</style>
<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: 0 0 0 0px;
}
:deep(.el-table) th {
   text-align: center;
   font-size: 14px;
}
</style>
