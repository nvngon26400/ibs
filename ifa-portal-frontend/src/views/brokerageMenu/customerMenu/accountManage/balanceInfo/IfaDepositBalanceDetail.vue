<template>
  <!-- 預り残高明細ダイアログ -->
  <el-dialog
    v-model="vmIsVisible"
    class="detail-dialog"
    :title="form.screenTitle"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="closeA003"
    :close-on-click-modal="false"
  >
    <!-- 戻るボタン -->
    <el-row style="margin: 0 auto; width: 90%;">
      <el-col style="text-align: right;">
        <ifa-button
          id="btnUpdate"
          action-type="requestAction"
          action-id="SUB0202_010201-04#A004"
          :request-model="a004RequestModel"
          style="padding-right: 0;"
          text="更新"
          color="primary"
          icon="RefreshRight"
          @response-handler="updateA004($event)"
        ></ifa-button>
        <ifa-button
          id="btnClose"
          action-type="originalAction"
          style="padding-right: 0;"
          text="閉じる"
          width="86"
          color="secondary"
          @app-action-handler="closeA003"
        ></ifa-button>
      </el-col>
    </el-row>
    <el-row
      class="info-label"
      style="margin-top: 20px;"
    >
      <el-col :span="24">
        <span>口座番号： {{ form.butenCode }}-{{ form.accountNumber }} {{ form.customerNameKanji }} ({{ form.customerNameKana }})</span>
        <br><span>{{ form.brandCode }} {{ form.brandName }}</span>
      </el-col>
    </el-row>

    <el-row
      v-if="existDetail"
      style="padding-top: 1rem;"
    >
      <div style="margin: 0 auto; width: 90%;">
        <span class="table-label">{{ form.commodityAccountDeposit }}</span>
        <div class="table__wrapper">
          <table
            v-if="form.productName === '国内株式' && form.domesticStockDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有株数</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}単価<br>現在値</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}金額<br>評価額</th>
                <th
                  class="_table__header __center"
                >評価損益</th>
                <th
                  class="_table__header __center"
                >預り年月日</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
                <th
                  class="_table__header __center"
                >入庫理由</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.domesticStockDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.holdingStock)) }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.getPriceReferencePrice)) }}<br>{{ $_withCommaNoneZeroPadding($_out(row.currentPrice)) }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.acquireAmountReferenceAmount)) }}<br>{{ $_withCommaNoneZeroPadding($_out(row.valuation)) }}</td>
                <td :class="['_table__data', '__right', getProfitColor(row.valuationProfitAndLoss) ]">{{ $_signedWithCommaNoneZeroPadding($_out(row.valuationProfitAndLoss)) }}</td>
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(row.depositDate)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
                <td class="_table__data __center">{{ $_out(row.storageReason) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '投資信託口数' && form.mutualFundLotDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有口数</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}単価<br>現在値</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}金額<br>評価額</th>
                <th
                  class="_table__header __center"
                >評価損益</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
                <th
                  class="_table__header __center"
                >入庫理由</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.mutualFundLotDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.unitVolume)) }} 口</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.getPriceReferencePrice)) }}<br>{{ row.currentPrice != '0' ? $_withCommaNoneZeroPadding($_out(row.currentPrice)) : '-' }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.acquireAmountReferenceAmount)) }}<br>{{ $_withCommaInteger($_out(row.valuation)) }}</td>
                <td :class="['_table__data', '__right', getProfitColor(row.valuationProfitAndLoss) ]">{{ $_signedWithCommaInteger($_out(row.valuationProfitAndLoss)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
                <td class="_table__data __center">{{ $_out(row.storageReason) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '投資信託金額' && form.mutualFundAmountDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有口数</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}単価<br>現在値（基準価格単位）</th>
                <th
                  class="_table__header __center"
                >{{ isGeneral ? '参考' : '取得' }}金額<br>評価額</th>
                <th
                  class="_table__header __center"
                >評価損益</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.mutualFundAmountDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.unitVolume)) }} 口</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.getPriceReferencePrice)) }}<br>{{ row.currentPrice != '0' ? $_withCommaNoneZeroPadding($_out(row.currentPrice)) : '-' }}{{ $_withCommaInteger($_out(row.depositDetailPriceUnit)) }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.acquireAmountReferenceAmount)) }}<br>{{ $_withCommaInteger($_out(row.valuation)) }}</td>
                <td :class="['_table__data', '__right', getProfitColor(row.valuationProfitAndLoss) ]">{{ $_signedWithCommaInteger($_out(row.valuationProfitAndLoss)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '国内債券' && form.domesticClaimDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有額面</th>
                <th
                  class="_table__header __center"
                >利率（％）</th>
                <th
                  class="_table__header __center"
                >償還日</th>
                <th
                  class="_table__header __center"
                >利払日</th>
                <th
                  class="_table__header __center"
                >参考為替<br>{{ isGeneral ? '参考' : '取得' }}単価</th>
                <th
                  class="_table__header __center"
                >約定為替<br>約定金額</th>
                <th
                  class="_table__header __center"
                >預り年月日</th>
                <th
                  class="_table__header __center"
                >評価額</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
                <th
                  class="_table__header __center"
                >入庫理由</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.domesticClaimDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.holdingFaceValue)) }}</td>
                <td class="_table__data __center">{{ row.compoundInterest == 0 || row.compoundInterest === ' ' ? '-' : $_withCommaZeroPadding($_out(row.compoundInterest), 3) }}</td>
                <td class="_table__data __center">{{ row.redemptionDate === ' ' ? '-' : $_out($_getFormattedDateValue(row.redemptionDate)) }}</td>
                <td class="_table__data __center">{{ $_out(row.interestPaymentDate) }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.exchangeRate)) }}<br>{{ $_withCommaNoneZeroPadding($_out(row.getPriceReferencePrice)) }}</td>
                <td class="_table__data __right">{{ $_withCommaNoneZeroPadding($_out(row.contractExchange)) }}<br>{{ $_withCommaInteger($_out(row.contractAmount)) }}</td>
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(row.depositDate)) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.valuation)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
                <td class="_table__data __center">{{ $_out(row.storageReason) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '外国債券' && form.foreignClaimDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有額面</th>
                <th
                  class="_table__header __center"
                >利率（％）</th>
                <th
                  class="_table__header __center"
                >償還日</th>
                <th
                  class="_table__header __center"
                >利払日</th>
                <th
                  class="_table__header __center"
                >参考為替<br>買付単価</th>
                <th
                  class="_table__header __center"
                >約定為替<br>約定金額</th>
                <th
                  class="_table__header __center"
                >預り年月日</th>
                <th
                  class="_table__header __center"
                >評価額（円）</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
                <th
                  class="_table__header __center"
                >入庫理由</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.foreignClaimDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.holdingFaceValue, '$_withCommaInteger')) }}</td>
                <td class="_table__data __center">{{ row.compoundInterest == 0 || row.compoundInterest === ' ' ? '-' : $_withCommaZeroPadding($_out(row.compoundInterest), 3) }}</td>
                <td class="_table__data __center">{{ row.redemptionDate === ' ' ? '-' : $_out($_getFormattedDateValue(row.redemptionDate)) }}</td>
                <td class="_table__data __center">{{ $_out(row.interestPaymentDate) }}</td>
                <td class="_table__data __right">{{ row.currencyCode === 'INR' ? $_out(splitPriceUnit(row.exchangeRate, '$_withCommaZeroPadding', 4)) : row.currencyCode === 'IDR' ? $_out(splitPriceUnit(row.exchangeRate, '$_withCommaZeroPadding', 6)) : $_out(splitPriceUnit(row.exchangeRate, '$_withCommaZeroPadding', 2)) }}
                  <br>
                  {{ $_out(splitPriceUnit(row.unitPrice, '$_withCommaZeroPadding', 2)) }}
                </td>
                <td class="_table__data __right">{{ row.currencyCode === 'INR' ? $_out(splitPriceUnit(row.contractExchange, '$_withCommaZeroPadding', 4)) : row.currencyCode === 'IDR' ? $_out(splitPriceUnit(row.contractExchange, '$_withCommaZeroPadding', 6)) : $_out(splitPriceUnit(row.contractExchange, '$_withCommaZeroPadding', 2)) }}
                  <br>
                  {{ $_out(splitPriceUnit(row.contractAmount, '$_withCommaInteger')) }}
                </td>
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(row.depositDate)) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.valuationJpAmount)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
                <td class="_table__data __center">{{ $_out(row.storageReason) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '外国債券（外貨建）' && form.foreignClaimForeignCurrencyDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有額面</th>
                <th
                  class="_table__header __center"
                >利率（％）</th>
                <th
                  class="_table__header __center"
                >償還日</th>
                <th
                  class="_table__header __center"
                >利払日</th>
                <th
                  class="_table__header __center"
                >参考為替<br>買付単価</th>
                <th
                  class="_table__header __center"
                >約定為替<br>約定金額</th>
                <th
                  class="_table__header __center"
                >評価額（円）</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.foreignClaimForeignCurrencyDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.holdingFaceValue, '$_withCommaInteger')) }}</td>
                <td class="_table__data __center">{{ $_out($_withCommaNoneZeroPadding(row.compoundInterest)) }}</td>
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(row.redemptionDate)) }}</td>
                <td
                  class="_table__data __center"
                  style="white-space: pre-wrap;"
                >{{ $_out(row.interestPaymentDate) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.exchangeRate, '$_withCommaZeroPadding')) }}
                  <br>
                  {{ $_out(splitPriceUnit(row.unitPrice, '$_withCommaZeroPadding', 2)) }}
                </td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.contractExchange, '$_withCommaZeroPadding')) }}
                  <br>
                  {{ $_out(splitPriceUnit(row.contractAmount, '$_withCommaInteger')) }}
                </td>
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.valuationJpAmount)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '外国株式' && form.foreignStockDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有株数</th>
                <th
                  class="_table__header __center"
                >取得単価<br>現在値</th>
                <th
                  class="_table__header __center"
                >取得金額<br>評価額</th>
                <th
                  class="_table__header __center"
                >外貨建評価損益</th>
                <th
                  class="_table__header __center"
                >評価為替レート</th>
                <th
                  class="_table__header __center"
                >評価額<br>（円貨）</th>
                <th
                  class="_table__header __center"
                >評価損益<br>（円貨）</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.foreignStockDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.holdingStock)) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.openPrice, '$_withCommaNoneZeroPadding')) }}<br>
                  {{ $_out(splitPriceUnit(row.currentPrice, '$_withCommaNoneZeroPadding')) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.getAmount, '$_withCommaNoneZeroPadding')) }}<br>
                  {{ $_out(splitPriceUnit(row.valuation, '$_withCommaNoneZeroPadding')) }}</td>
                <td :class="['_table__data', '__right', getForeignProfitColor(row.foreignProfitAndLoss) ]">{{ $_out(splitPriceUnit(row.foreignProfitAndLoss, '$_signedWithCommaNoneZeroPadding')) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.fxValuationRate, '$_withCommaZeroPadding', 2)) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.valuationJpAmount)) }}</td>
                <td :class="['_table__data', '__right', getProfitColor(row.yenProfitLoss) ]">{{ $_signedWithCommaInteger($_out(row.yenProfitLoss)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
              </tr>
            </tbody>
          </table>
          <table
            v-else-if="form.productName === '外貨建MMF' && form.foreignMmfDepositList.length > 0"
            class="_table__horizontal _table__body"
          >
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                >保有口数</th>
                <th
                  class="_table__header __center"
                >評価額<br>（外貨）</th>
                <th
                  class="_table__header __center"
                >評価為替レート</th>
                <th
                  class="_table__header __center"
                >評価額<br>（円貨）</th>
                <th
                  class="_table__header __center"
                >評価損益<br>（円貨）</th>
                <th
                  class="_table__header __center"
                >保護／代用区分</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(row, index) in form.foreignMmfDepositList"
                :key="index"
              >
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.unitVolume)) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.foreignValuation, '$_withCommaNoneZeroPadding')) }}</td>
                <td class="_table__data __right">{{ $_out(splitPriceUnit(row.fxValuationRate, '$_withCommaZeroPadding', 2)) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger($_out(row.valuation)) }}</td>
                <td :class="['_table__data', '__right', getProfitColor(row.yenProfitLoss) ]">{{ $_signedWithCommaInteger($_out(row.yenProfitLoss)) }}</td>
                <td class="_table__data __center">{{ $_out(row.protectionSubstitutionClass) }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </el-row>

    <div
      v-if="!existDetail"
      class="no-detail-msg"
    >{{ form.noDetailMsg }}</div>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaDepositBalanceDetailFormModel } from './js/IfaDepositBalanceDetailFormModel'
import { IfaDepositBalanceDetailA004RequestModel } from './js/IfaDepositBalanceDetailA004RequestModel'

export default {
  props: {
    isVisible: { type: Boolean, required: true },
    param: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDepositBalanceDetailFormModel()
    }
  },
  computed: {
    a004RequestModel() {
      return new IfaDepositBalanceDetailA004RequestModel(this.form)
    },
    // 一般預り判別
    isGeneral() {
      return this.form.commodityAccountDeposit.includes('一般預り')
    },
    existDetail() {
      return (this.form.domesticStockDepositList.length > 0 ||
      this.form.mutualFundLotDepositList.length > 0 ||
      this.form.mutualFundAmountDepositList.length > 0 ||
      this.form.domesticClaimDepositList.length > 0 ||
      this.form.foreignClaimDepositList.length > 0 ||
      this.form.foreignClaimForeignCurrencyDepositList.length > 0 ||
      this.form.foreignStockDepositList.length > 0 ||
      this.form.foreignMmfDepositList.length > 0)
    }
  },
  methods: {
    onShow(data) {
      Object.assign(this.form, this.param)
      Object.assign(this.form, data)
    },
    closeA003() {
      this.$emit('close')
    },
    updateA004(response) {
      Object.assign(this.form, response.dataList[0])
    },
    getProfitColor(value) {
      return value < 0 ? 'font-color__minus bold' : value > 0 ? 'font-color__plus bold' : ''
    },
    getForeignProfitColor(value) {
      if (!value) {
        return
      } else {
        let str = []
        str = value.split(' ')
        return str[0] < 0 ? 'font-color__minus bold' : str[0] > 0 ? 'font-color__plus bold' : ''
      }
    },
    splitPriceUnit(value, format, digits) {
      let str = []
      let formatVale = ''
      // 単位と数字を分割
      if (!value || value === '-') {
        return value
      } else if (value.includes('円/')) {
        str = value.split('円/')
        const val = str[1]
        str[1] = val ? '円/' + val : '円/-'
      } else {
        str = value.split(' ')
        const val = str[1]
        str[1] = val ? ' ' + val : ' -'
      }
      // 数字の部分にフォーマットを適用
      if (this[format] && str[0]) {
        formatVale = this[format](str[0], digits)
        return formatVale + str[1]
      } else {
        return '-' + str[1]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.detail-dialog :deep() {
  .el-dialog__header {
    font-weight: 700;
    padding: 2rem 5rem;
  }
  .el-dialog__body {
    padding-top: 0;
  }
  .el-dialog__footer {
    display: flex;
    justify-content: flex-start;
    padding: 0 0 2rem 4rem;
  }
}
.info-label {
  font-size: 16px;
  line-height: 25px;
  margin: 0 auto;
  width: 90%;
}
.table-label {
  font-size: 16px;
  font-weight: 700;
  color: #303133;
}
.table__wrapper {
  overflow-x: scroll;
  white-space: nowrap;
}
.close-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: 20px 3rem 0 auto;
}
.no-detail-msg {
  text-align: center;
  margin-top: 3rem;
}
:deep(._table__data) {
  font-size: 14px;
  padding: 7px;
}
</style>
