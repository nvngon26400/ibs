<template>
  <el-dialog
    v-model="vmIsVisible"
    width="920px"
    :center="true"
    :title="formModel.title.name"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
  >
    <el-row>
      <el-col
        class="_right"
        style="padding-right: 5px;"
      >
        <ifa-button
          id="btnUpdate"
          text="更新"
          color="primary"
          icon="RefreshRight"
          :parent-ref="$refs"
          action-type="requestAction"
          action-id="SUB0202_010304-02#A002"
          @response-handler="responseHandlerA002"
        ></ifa-button>
        <ifa-button
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
    <div>
      <el-card class="box-card">
        <el-row>
          <el-col>
            <table
              class="_table__body"
              style="width:100%"
            >
              <tbody>
                <tr>
                  <th class="_table__header __center"></th>
                  <th class="_table__header __center">当日基準<br>更新：リアルタイム</th>
                  <th class="_table__header __center">値洗い基準<br>更新：9時頃、19時頃</th>
                </tr>
                <tr>
                  <th class="_table__header __left">リアル委託保証金率</th>
                  <th class="_table__header __left"></th>
                  <th class="_table__header __left"></th>
                </tr>
                <tr>
                  <td class="_table__data __left">委託保証金率A/B×100</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseMarginDepositRate8), 2) }} {{ $_out(formModel.todayBaseMarginDepositRate8) != '-' ? ' %' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseMarginDepositRate8), 2) }} {{ $_out(formModel.markToBaseMarginDepositRate8) != '-' ? ' %' : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">(A)実質保証金</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseForeignCurrencyactualDeposit), 2) }} {{ $_out(formModel.todayBaseForeignCurrencyactualDeposit) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseForeignCurrencyactualDeposit), 2) }} {{ $_out(formModel.markToBaseForeignCurrencyactualDeposit) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >委託保証金現金</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseMarginDepositCache), 2) }} {{ $_out(formModel.todayBaseMarginDepositCache) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseMarginDepositCache), 2) }} {{ $_out(formModel.markToBaseMarginDepositCache) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >代用有価証券評価額合計</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseAmericaAlternativeSecuritiesTotal), 2) }} {{ $_out(formModel.todayBaseAmericaAlternativeSecuritiesTotal) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseAmericaAlternativeSecuritiesTotal), 2) }} {{ $_out(formModel.markToBaseAmericaAlternativeSecuritiesTotal) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >建玉評価損合計</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseTotalOpenInterestValuationLoss), 2) }} {{ $_out(formModel.todayBaseTotalOpenInterestValuationLoss) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseTotalOpenInterestValuationLoss), 2) }} {{ $_out(formModel.markToBaseTotalOpenInterestValuationLoss) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >決済損益合計</td>
                  <td
                    :class="changeFontColor(formModel.todayBaseTotalSettlementProfitLoss)"
                    class="_table__data __right"
                  >{{ ifaFormatUtils.signedWithCommaZeroPadding($_out(formModel.todayBaseTotalSettlementProfitLoss), 2) }} {{ $_out(formModel.todayBaseTotalSettlementProfitLoss) != '-' ? ' USD' : '' }}</td>
                  <td
                    :class="changeFontColor(formModel.markToBaseTotalSettlementProfitLoss)"
                    class="_table__data __right"
                  >{{ ifaFormatUtils.signedWithCommaZeroPadding($_out(formModel.markToBaseTotalSettlementProfitLoss), 2) }} {{ $_out(formModel.markToBaseTotalSettlementProfitLoss) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >支払諸経費等合計</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseTotalExpensesPaid), 2) }} {{ $_out(formModel.todayBaseTotalExpensesPaid) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseTotalExpensesPaid), 2) }} {{ $_out(formModel.markToBaseTotalExpensesPaid) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <th class="_table__header __left">振替可能分含む</th>
                  <th class="_table__header __left"></th>
                  <th class="_table__header __left"></th>
                </tr>
                <tr>
                  <td class="_table__data __left">参考委託保証金率(A+C+D)/Bx100</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseForeignReferenceMarginDeposit), 2) }} {{ $_out(formModel.todayBaseForeignReferenceMarginDeposit) != '-' ? '%' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseForeignReferenceMarginDeposit), 2) }} {{ $_out(formModel.markToBaseForeignReferenceMarginDeposit) != '-' ? '%' : '' }}</td>
                </tr>
                <tr>
                  <td class="_table__data __left">参考保証金</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseReferenceSecurityDeposit), 2) }} {{ $_out(formModel.todayBaseReferenceSecurityDeposit) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseReferenceSecurityDeposit), 2) }} {{ $_out(formModel.markToBaseReferenceSecurityDeposit) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >(A)実質保証金</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseForeignCurrencyactualDeposit), 2) }} {{ $_out(formModel.todayBaseForeignCurrencyactualDeposit) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseForeignCurrencyactualDeposit), 2) }} {{ $_out(formModel.markToBaseForeignCurrencyactualDeposit) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >(C)預り金</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseForeignCurrencydeposit), 2) }} {{ $_out(formModel.todayBaseForeignCurrencydeposit) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseForeignCurrencydeposit), 2) }} {{ $_out(formModel.markToBaseForeignCurrencydeposit) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <td
                    class="_table__data __left"
                    style="padding-left: 30px"
                  >(D)保護預り有価証券評価額合計</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseTotalAppraisalValueOfSecuritiesInCustody), 2) }} {{ $_out(formModel.todayBaseTotalAppraisalValueOfSecuritiesInCustody) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseTotalAppraisalValueOfSecuritiesInCustody), 2) }} {{ $_out(formModel.markToBaseTotalAppraisalValueOfSecuritiesInCustody) != '-' ? 'USD' : '' }}</td>
                </tr>
                <tr>
                  <th class="_table__header __left">建代金合計</th>
                  <th class="_table__header __left"></th>
                  <th class="_table__header __left"></th>
                </tr>
                <tr>
                  <td class="_table__data __left">(B)建代金合計</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.todayBaseConstructionPriceTotal), 2) }} {{ $_out(formModel.todayBaseConstructionPriceTotal) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.markToBaseConstructionPriceTotal), 2) }} {{ $_out(formModel.markToBaseConstructionPriceTotal) != '-' ? 'USD' : '' }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import ifaFormatUtils from '@/utils/ifaFormatUtils'

export default {
  components: {
  },
  props: {
    isVisible: { type: Boolean, required: true },
    realEntrustDepositRateFormModel: { type: Object, required: true }
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
      ifaFormatUtils: ifaFormatUtils,
      formModel: this.realEntrustDepositRateFormModel
    }
  },
  methods: {
    // 更新ボタン
    responseHandlerA002(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    changeFontColor(data) {
      if (!data) {
        return
      } else if (data >= 0) {
        return 'font-color__plus bold'
      } else {
        return 'font-color__minus bold'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
._right {
  text-align: right;
}
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
.box-card {
  margin: 10px 10px 15px 10px;
}
</style>
