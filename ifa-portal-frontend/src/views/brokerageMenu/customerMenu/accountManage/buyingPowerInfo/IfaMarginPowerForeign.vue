<template>
  <!-- 信用余力(米株) -->
  <div>
    <ifa-requester
      id="IfaMarginPowerForeignInitializeA001"
      action-id="SUB0202_010304-01#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA001"
      @response-error-handler="responseErrorHandlerA001"
    ></ifa-requester>
    <!--リアル委託保証金率遷移のリクエスト-->
    <ifa-requester
      id="IfaMarginPowerForeignRealEntrustDepositRateA002"
      action-id="SUB0202_010304-02#A001"
      action-type="requestAction"
      @response-handler="responseHandlerA002"
    ></ifa-requester>
    <screen-title :text="formModel.title.name"></screen-title>

    <div class="caption_card">
      <el-row>
        <el-col :span="22">
        </el-col>
        <el-col
          :span="2"
          class="__right"
        >
          <ifa-button
            id="btnUpdate"
            text="更新"
            color="primary"
            icon="RefreshRight"
            :parent-ref="$refs"
            :request-model="A001RequestModel"
            action-type="requestAction"
            action-id="SUB0202_010304-01#A001"
            @response-handler="responseHandlerA001"
            @response-error-handler="responseErrorHandlerA001"
          ></ifa-button>
        </el-col>
      </el-row>
      <template v-if="formModel.marginCallFlag === '1'">
        <el-row style="margin-top: 1.2rem;">
          <span style="font-weight: bold;">追加保証金</span>
        </el-row>
        <el-row>
          <el-col>
            <table
              class="_table__body"
              style="width:56%"
            >
              <thead>
                <tr>
                  <th class="_table__header __center"></th>
                  <th class="_table__header __center">解消期限</th>
                  <th class="_table__header __center">当初追証額<br>（追証発生日)</th>
                  <th class="_table__header __center">入金充当額</th>
                  <th class="_table__header __center">代用充当額</th>
                  <th class="_table__header __center">決済建玉充当額</th>
                  <th class="_table__header __center">必要額</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(asset, index) in formModel.addDeposit"
                  :key="index"
                >
                  <td class="_table__data __center">{{ $_out(asset.markToMarketType) }}</td>
                  <td class="_table__data __center">{{ $_out(asset.cancellationDeadline) != '-' ? $_out($_getFormattedDateValue(asset.cancellationDeadline, 'date8')) + ' 17:30': '----/--/-- --:--' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.initialMarginAmount), 2) }}{{ $_out(asset.initialMarginAmount) != '-' ? ' USD' : '' }}<br>({{ $_out($_getFormattedDateValue(asset.marginCallDate, 'date8')) }})</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.addDepositPaymentInterest), 2) }}{{ $_out(asset.addDepositPaymentInterest) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.substituteInterest), 2) }}{{ $_out(asset.substituteInterest) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.settlementOpenInterestForeignCurrency), 2) }}{{ $_out(asset.settlementOpenInterestForeignCurrency) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.openInterestCostForeignCurrency), 2) }}{{ $_out(asset.openInterestCostForeignCurrency) != '-' ? ' USD' : '' }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </template>
      <template v-if="formModel.depositDeficientFlag === '1'">
        <el-row style="margin-top: 1.2rem;">
          <span style="font-weight: bold; ">預り金不足</span>
        </el-row>
        <el-row>
          <el-col>
            <table
              class="_table__body"
              style="width:32%"
            >
              <tbody>
                <tr>
                  <th class="_table__header __center">解消期限</th>
                  <th class="_table__header __center">状況</th>
                  <th class="_table__header __center">預り金不足額</th>
                  <th class="_table__header __center">入金額</th>
                </tr>
                <tr
                  v-for="(asset, index) in formModel.depositDeficient"
                  :key="index"
                >
                  <td class="_table__data __center">{{ $_out(asset.cancellationDeadline) != '-' ? $_out($_getFormattedDateValue(asset.cancellationDeadline, 'date8')) + ' 17:30' : '----/--/-- --:--' }}</td>
                  <td class="_table__data __center">{{ $_out(asset.status) }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.depositShortage), 2) }}{{ $_out(asset.depositShortage) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.depositAmount), 2) }}{{ $_out(asset.depositAmount) != '-' ? ' USD' : '' }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </template>
      <template v-if="formModel.newPositionDeficientFlag === '1'">
        <el-row style="margin-top: 1.2rem">
          <span style="font-weight: bold; ">新規建不足</span>
        </el-row>
        <el-row>
          <el-col>
            <table
              class="_table__body"
              style="width:50%"
            >
              <tbody>
                <tr>
                  <th class="_table__header __center">解消期限</th>
                  <th class="_table__header __center">新規建不足金額<br>（発生日）</th>
                  <th class="_table__header __center">入金充当額</th>
                  <th class="_table__header __center">代用充当額</th>
                  <th class="_table__header __center">決済建玉充当額</th>
                  <th class="_table__header __center">必要額</th>
                </tr>
                <tr
                  v-for="(asset, index) in formModel.newPositionDeficient"
                  :key="index"
                >
                  <td class="_table__data __center">{{ $_out(asset.cancellationDeadline) != '-' ? $_out($_getFormattedDateValue(asset.cancellationDeadline, 'date8')) + ' 17:30' : '----/--/-- --:--' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.constructionShortage), 2) }}{{ $_out(asset.constructionShortage) != '-' ? ' USD' : '' }}<br>({{ $_out($_getFormattedDateValue(asset.accuralDate, 'date8')) }})</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.paymentInterest), 2) }}{{ $_out(asset.paymentInterest) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.substituteInterest), 2) }}{{ $_out(asset.substituteInterest) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.settlementOpenInterestForeignCurrency), 2) }}{{ $_out(asset.settlementOpenInterestForeignCurrency) != '-' ? ' USD' : '' }}</td>
                  <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.openInterestCostForeignCurrency), 2) }}{{ $_out(asset.openInterestCostForeignCurrency) != '-' ? ' USD' : '' }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </template>
      <el-row>
        <el-col
          :span="21"
          style="margin-top: 1.2rem;"
        >
          <span style="font-weight: bold">信用取引</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <table
            class="_table__body"
            style="width:80%"
          >
            <tbody>
              <tr>
                <th class="_table__header __center">国内受渡日</th>
                <th class="_table__header __center">保証金現金</th>
                <th class="_table__header __center">支払額</th>
                <th class="_table__header __center">未約定信用決済損</th>
                <th class="_table__header __center">受取額</th>
                <th class="_table__header __center">振替予定額<br>(現物口座→信用口座)</th>
                <th class="_table__header __center">残高(保証金)</th>
                <th class="_table__header __center">必要保証金額</th>
              </tr>
              <tr
                v-for="(asset, index) in formModel.marginTrade"
                :key="index"
              >
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(asset.domesticSettlementDate, 'date8')) }}<br>{{ $_out(asset.settlementDateAfterBusinessDay) }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.margin), 2) }}{{ $_out(asset.margin) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.payment), 2) }}{{ $_out(asset.payment) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.uncontractedCreditSettlementLoss), 2) }}{{ $_out(asset.uncontractedCreditSettlementLoss) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.amountReceived), 2) }}{{ $_out(asset.amountReceived) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.plannedTransferAmount), 2) }}{{ $_out(asset.plannedTransferAmount) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.balanceDeposit), 2) }}{{ $_out(asset.balanceDeposit) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.requiredSettlementAmountForeignCurrency), 2) }}{{ $_out(asset.requiredSettlementAmountForeignCurrency) != '-' ? ' USD' : '' }}</td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>
      <el-row style="margin: 1.2rem 0 1rem;">
        <span style="font-weight: bold">余力情報</span>
      </el-row>
      <el-row style="margin-bottom: 1rem;">
        <el-col
          :span="22"
        >
          <el-card
            class="box-card"
            style="background-color: #eee; padding: 0.5rem 1.5rem; width: 55%;"
          >
            <el-row>
              <el-col :span="4">
                <span style="font-weight: bold">信用建余力</span>
              </el-col>
              <el-col
                :span="20"
                class="_right"
              ><span>{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.foreignNewBuildingCapacity), 2) }}{{ $_out(formModel.foreignNewBuildingCapacity) != '-' ? ' USD' : '' }}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="8"
                :offset="11"
              ><span>委託保証金率</span>
              </el-col>
              <el-col
                :span="5"
                class="_right"
              ><span>{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.marginDepositRate8), 2) }}{{ $_out(formModel.marginDepositRate8) != '-' ? ' %' : '' }}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="8"
                :offset="11"
              ><el-link
                :underline="false"
                class="inner-link"
                disable-transitions
                @click="a002Action()"
              >リアル委託保証金率</el-link>
              </el-col>
              <el-col
                :span="5"
                class="_right"
              ><span>{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.realTimeMarginDepositRate), 2) }}{{ $_out(formModel.realTimeMarginDepositRate) != '-' ? ' %' : '' }}</span>
              </el-col>
            </el-row>
            <hr>
            <el-row>
              <el-col :span="5">
                <span>引出可能額</span>
              </el-col>
              <el-col
                :span="7"
                :offset="6"
              >
                <span>米ドル保証金</span>
              </el-col>
              <el-col
                :span="6"
                class="_right"
              >
                <span>{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.usdSecurityDeposit), 2) }}{{ $_out(formModel.usdSecurityDeposit) != '-' ? ' USD' : '' }}</span>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="8"
                :offset="11"
              ><span>代用有価証券</span>
              </el-col>
              <el-col
                :span="5"
                class="_right"
              ><span>{{ ifaFormatUtils.withCommaZeroPadding($_out(formModel.substituteSecurities), 2) }}{{ $_out(formModel.substituteSecurities) != '-' ? ' USD' : '' }}</span>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
      <el-row style="margin-top: 1.2rem;">
        <span style="font-weight: bold">委託保証金率の推移</span>
      </el-row>
      <el-row>
        <el-col>
          <table
            class="_table__body"
            style="width:100%"
          >
            <tbody>
              <tr>
                <th class="_table__header __center">国内受渡日</th>
                <th class="_table__header __center">委託保証金現金合計</th>
                <th class="_table__header __center">代用有価証券評価額合計</th>
                <th class="_table__header __center">評価額・決済損益・諸経費合計</th>
                <th class="_table__header __center">(A)実質保証金</th>
                <th class="_table__header __center">(B)建代金合計</th>
                <th class="_table__header __center">委託保証金率<br>A/B×100</th>
                <th class="_table__header __center">(C)預り金</th>
                <th class="_table__header __center">(D)保護預り<br>有価証券評価額合計</th>
                <th class="_table__header __center">参考委託保証金率<br>(A+C+D)/Bx100</th>
              </tr>
              <tr
                v-for="(asset, index) in formModel.entrustDepositTransition"
                :key="index"
              >
                <td class="_table__data __center">{{ $_out($_getFormattedDateValue(asset.domesticSettlementDate, 'date8')) }}<br>{{ $_out(asset.settlementDateAfterBusinessDay) }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.marginDepositTotalCache), 2) }}{{ $_out(asset.marginDepositTotalCache) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.alternativeSecuritiesTotal), 2) }}{{ $_out(asset.alternativeSecuritiesTotal) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">
                  <span :class="[asset.marginDepositRateSettlementProfitLossCost < 0 ? 'font-color__minus' : asset.marginDepositRateSettlementProfitLossCost > 0 ? 'font-color__plus' : '', '__right bold']">
                    {{ ifaFormatUtils.withCommaZeroPadding($_out(asset.marginDepositRateSettlementProfitLossCost), 2) }}{{ $_out(asset.marginDepositRateSettlementProfitLossCost) != '-' ? ' USD' : '' }}
                  </span>
                </td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.foreignCurrencyactualDeposit), 2) }}{{ $_out(asset.foreignCurrencyactualDeposit) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.constructionPriceTotal), 2) }}{{ $_out(asset.constructionPriceTotal) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.marginDepositRate8), 2) }}{{ $_out(asset.marginDepositRate8) != '-' ? ' %' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.foreignCurrencydeposit), 2) }}{{ $_out(asset.foreignCurrencydeposit) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.totalAppraisalValueOfSecuritiesInCustody), 2) }}{{ $_out(asset.totalAppraisalValueOfSecuritiesInCustody) != '-' ? ' USD' : '' }}</td>
                <td class="_table__data __right">{{ ifaFormatUtils.withCommaZeroPadding($_out(asset.foreignReferenceMarginDeposit), 2) }}{{ $_out(asset.foreignReferenceMarginDeposit) != '-' ? ' %' : '' }}</td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>
    </div>
    <!--リアル委託保証金率-->
    <ifa-real-entrust-deposit-rate
      :is-visible="realMarginDepositRateisVisible"
      :real-entrust-deposit-rate-form-model="realEntrustDepositRateFormModel"
      @close-modal="handleCloseModal"
    >
    </ifa-real-entrust-deposit-rate>
  </div>
</template>

<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaRealEntrustDepositRate from './IfaRealEntrustDepositRate.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import { IfaRealEntrustDepositRateFormModel } from './js/IfaRealEntrustDepositRateFormModel.js'
import { IfaMarginPowerForeignFormModel } from './js/IfaMarginPowerForeignFormModel.js'
import { IfaMarginPowerForeignA001RequestModel } from './js/IfaMarginPowerForeignA001RequestModel.js'
import ifaFormatUtils from '@/utils/ifaFormatUtils'

export default {
  components: {
    screenTitle,
    IfaRealEntrustDepositRate,
    IfaRequester
  },
  emits: ['initializeError'],
  data() {
    return {
      realEntrustDepositRateFormModel: new IfaRealEntrustDepositRateFormModel(),
      formModel: new IfaMarginPowerForeignFormModel(),
      ifaFormatUtils: ifaFormatUtils,
      realMarginDepositRateisVisible: false
    }
  },
  computed: {
    A001RequestModel() {
      return new IfaMarginPowerForeignA001RequestModel()
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaMarginPowerForeignInitializeA001').click()
      })
    },

    responseHandlerA001(response) {
      this.formModel = Object.assign(this.formModel, response.dataList[0])
    },
    responseErrorHandlerA001(error) {
      if (error.errorLevel === -1) {
        const errorInfo = {
          title: this.formModel.title.name,
          message: error.message
        }
        this.$emit('initializeError', errorInfo)
      }
    },
    // リアル委託保証金率遷移のためのResponseHandler
    responseHandlerA002(response) {
      this.realEntrustDepositRateFormModel = Object.assign(this.realEntrustDepositRateFormModel, response.dataList[0])
      this.realMarginDepositRateisVisible = true
    },
    // リアル委託保証金率遷移のA002アクション
    a002Action() {
      this.$nextTick(() => {
        document.getElementById('IfaMarginPowerForeignRealEntrustDepositRateA002').click()
      })
    },
    // リアル委託保証金率のダイアログを閉じる
    handleCloseModal() {
      this.realMarginDepositRateisVisible = false
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
  @import "@/styles/table.scss";
:deep(.el-table) .__center {
  text-align: center;
}
:deep(.el-table) .__left {
  text-align: left;
}
:deep(.el-table) .__right {
  text-align: right;
}
._right {
  text-align: right;
}
._left {
  text-align: left;
}
.table__data {
  border: 1px solid #a7b1c3;
  padding: 2px 14px 2px 14px;
}
._table__header-alart {
  color: red;
  background-color: yellow;
  border: 1px solid #a7b1c3;
  padding: 2px 14px 2px 14px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
.inner-link {
  cursor: pointer;
  color: #092987;
  text-decoration: underline;
  &:hover {
    opacity: 0.7;
      }
}
</style>
