<template>
  <el-dialog
    v-model="vmIsVisible"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    :before-close="onDialogClose"
    :title="form.batchIndividualDisplayFlag === '1' ? form.title+'（個別表示）' : form.title+'（一括表示）'"
    width="455px"
    height="820px"
    @open="onShow"
  >
    <div style="text-align: right;">
      <ifa-button
        id="btnClose"
        text="閉じる"
        color="secondary"
        action-type="originalAction"
        @app-action-handler="onDialogClose"
      ></ifa-button>
    </div>
    <hr>
    <div style="margin-top:1rem;">
      <table style="margin-bottom: 0.5rem;width:400px">
        <tbody>
          <tr>
            <th class="_table__header __left">ティッカー</th>
            <td class="_table__data __right">{{ $_out(form.brandCode) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">銘柄</th>
            <td class="_table__data __right">{{ $_out(form.brandName) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">市場</th>
            <td class="_table__data __right">{{ $_out(form.foreignMarket) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">建区分</th>
            <td
              class="_table__data __right"
              :class="form.tradeKbn === '1' ? 'font-color__minus bold' : 'font-color__plus bold'"
            >{{ $_out($_getCodeValue('POSITION_SELL_BUY_TYPE', 1, form.tradeKbn)) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">期限</th>
            <td class="_table__data __right">{{ $_out($_getCodeValue('MARGIN_DUE_DATE', 1, form.marginDueDate)) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">新規建日</th>
            <td class="_table__data __right">{{ form.domesticTradeDate === '----/--/--' || !form.domesticTradeDate ? '----/--/--' : $_getFormattedDateValue(form.domesticTradeDate) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">返済期限</th>
            <td class="_table__data __right">{{ form.lastTradeDate === '----/--/--' || !form.lastTradeDate ? '----/--/--' : $_getFormattedDateValue(form.lastTradeDate) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">預り区分</th>
            <td class="_table__data __right">{{ $_out($_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, form.depositType)) }}</td>
          </tr>
          <tr>
            <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '0' ? '建株数合計' : '建株数' }}</th>
            <td class="_table__data __right">{{ $_out($_addComma(form.positionRestQuantity)) }}&nbsp;株</td>
          </tr>
          <tr>
            <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '0' ? '平均建単価' : '建単価' }}</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.previousDayValue, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '0' ? '建代金合計' : '建代金' }}</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.foreignNewPositionAmount, 2)) }}&nbsp;USD</td>
          </tr>
        </tbody>
      </table>
      <table style="margin-bottom: 0.5rem;width:400px">
        <span class="__bold">{{ form.batchIndividualDisplayFlag === '0' ? '諸経費等合計' : '諸経費等' }}</span>
        <tbody>
          <tr>
            <th class="_table__header __left">新規建手数料（税込）</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.foreignNewComm, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left">金利</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.interestForeign, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left">貸株料</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.stockLendingPriceForeign, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left">管理料（税込）</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.managePriceForeign, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left">名義書換料（税込）</th>
            <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.transferPriceForeign, 2)) }}&nbsp;USD</td>
          </tr>
          <tr>
            <th class="_table__header __left __bold">合計</th>
            <td class="_table__data __right __bold">{{ $_out($_withCommaZeroPadding(form.costForeignLink, 2)) }}&nbsp;USD</td>
          </tr>
        </tbody>
      </table>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaForeignPositionDetailFormModel } from './IfaForeignPositionDetailFormModel'

export default {
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
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignPositionDetailFormModel()
    }
  },
  methods: {
    onShow() {
      this.form = Object.assign(this.form, this.formData)
    },
    onDialogClose() {
      this.$emit('close-modal')
    }
  }
}
</script>

<style lang="scss" scoped>
.__bold {
  font-weight: bold;
}
.clickable:hover {
  cursor: pointer
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
:deep(.el-dialog__header) {
  font-weight: bold;
}
:deep(.el-table) th {
  text-align: center;
  font-size: 14px;
}
:deep(._table__header) {
  width: 50%
}
:deep(th) {
  font-weight: bold;
}
</style>
