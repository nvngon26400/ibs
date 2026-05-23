<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.batchIndividualDisplayFlag === '1' ? form.title+'（個別表示）' : form.title+'（一括表示）'"
      :center="true"
      top="3%"
      :close-on-click-modal="false"
      :show-close="false"
      :before-close="onDialogClose"
      width="455px"
      height="820px"
      :append-to-body="true"
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
              <th class="_table__header __left">銘柄コード</th>
              <td class="_table__data __right">{{ $_out(form.brandCode) }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">銘柄</th>
              <td class="_table__data __right">{{ $_out(form.brand) }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">市場</th>
              <td class="_table__data __right">
                {{ $_out($_getCodeValue('NEW_MARKET', 1, form.market)) }}
              </td>
            </tr>
            <tr>
              <th class="_table__header __left">売/買建</th>
              <td class="_table__data __right">
                <span :class="form.newMarket === '0' ? 'font-color__plus bold' : 'font-color__minus bold'">
                  {{ $_out($_getCodeValue('NEW_CREDIT_SELL_BUY_TYPE', 1, form.newMarket)) }}
                </span>
              </td>
            </tr>
            <tr>
              <th class="_table__header __left">期限</th>
              <td class="_table__data __right">
                {{ $_out($_getCodeValue('PAYMENT_DEADLINE', 1, form.limit)) }}
              </td>
            </tr>
            <tr>
              <th class="_table__header __left">親株新規約定日</th>
              <td class="_table__data __right">{{ form.positionDetailDeadLine === '----/--/--' ? form.positionDetailDeadLine : $_getFormattedDateValue(form.positionDetailDeadLine) }}</td>
            </tr>
            <tr>
              <th class="_table__header __left">新規建日<br>返済期限</th>
              <td class="_table__data __right">
                {{ form.openTradeDate === '----/--/--' ? form.openTradeDate : $_getFormattedDateValue(form.openTradeDate) }}<br>
                <span :style="form.repayPeriodShorterFlag === '1' ? 'color:red;' : ''">
                  {{ form.lastTradeDate === '----/--/--' || form.lastTradeDate === '無期限' ? form.lastTradeDate : $_getFormattedDateValue(form.lastTradeDate) }}
                </span>
              </td>
            </tr>
            <tr>
              <th class="_table__header __left">預り区分</th>
              <td class="_table__data __right">
                {{ $_out($_getCodeValue('SPECIFIC_POSITION_TYPE', 1, form.depositType)) }}
              </td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '1' ? '建株数' : '建株数合計' }}</th>
              <td class="_table__data __right">{{ $_out($_addComma(form.stockNumTotal)) }}株</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '1' ? '建単価' : '平均建単価' }}</th>
              <td class="_table__data __right">{{ $_out($_withCommaNoneZeroPadding(form.positionPriceLabel)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left">{{ form.batchIndividualDisplayFlag === '1' ? '建代金' : '建代金合計' }}</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.constructionPriceTotal)) }}円</td>
            </tr>
          </tbody>
        </table>
        <table style="margin-bottom: 0.5rem;width:400px">
          <span class="__bold">{{ form.batchIndividualDisplayFlag === '1' ? '諸経費等' : '諸経費等合計' }}</span>
          <tbody>
            <tr>
              <th class="_table__header __left">新規建手数料(税込)</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.domesticNewComm)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left">管理料</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.managePrice)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left">権利処理等手数料</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.rightProcessingCharge)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left">日歩</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.dailyInterest)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left">逆日歩及び貸株料</th>
              <td class="_table__data __right">{{ $_out($_withCommaInteger(form.reverseDailyInterest)) }}円</td>
            </tr>
            <tr>
              <th class="_table__header __left __bold">合計</th>
              <td class="_table__data __right __bold">{{ $_out($_withCommaInteger(form.total)) }}円</td>
            </tr>
          </tbody>
        </table>
        <table style="margin-bottom: 0.5rem;width:400px">
          <span class="__bold">各種保証金率</span>
          <tbody>
            <tr>
              <th class="_table__header __left">新規建保証金率</th>
              <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.newDepositRate)) }}%</td>
            </tr>
            <tr>
              <th class="_table__header __left">現金保証金率</th>
              <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.cashDepositRate)) }}%</td>
            </tr>
            <tr>
              <th class="_table__header __left">現物買付保証金率</th>
              <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.cashBuyDepositRate)) }}%</td>
            </tr>
            <tr>
              <th class="_table__header __left">出金・振替保証金率</th>
              <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.withdrawTransferDepositRate)) }}%</td>
            </tr>
            <tr>
              <th class="_table__header __left">増担保規制等</th>
              <td class="_table__data __right">{{ $_out(form.additionalCollateralRegulations) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        <ul>
          <li>HYPER料は、「逆日歩及び貸株料」に含まれます。</li>
          <li>
            増担保規制等欄表記<br>
            {{ $_getCodeValue('COLLATERAL_REGULATIONS', 2, '1') }}<br>
            {{ $_getCodeValue('COLLATERAL_REGULATIONS', 2, '2') }}<br>
            {{ $_getCodeValue('COLLATERAL_REGULATIONS', 2, '3') }}
          </li>
        </ul>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaDomesticPositionDetailFormModel } from './js/IfaDomesticPositionDetailFormModel'

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
      form: new IfaDomesticPositionDetailFormModel()
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
:deep(.el-dialog__title) {
   font-weight: bold;
}
:deep(.el-table) th {
   text-align: center;
   font-size: 14px;
}
:deep(._table__header) {
   width: 50%
}
</style>
