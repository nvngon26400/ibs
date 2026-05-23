<template>
  <!-- 画面名：余力情報 -->
  <div>
    <ifa-requester
      id="IfaBuyingPowerDomesticA001"
      action-id="SUB0202_010301-01#A001"
      action-type="requestAction"
      :request-model="IfaBuyingPowerDomesticA001RequestModel"
      @response-handler="a001ActionHandler"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaBuyingPowerDomesticA002"
      action-id="SUB0202_010301-02#A001"
      action-type="requestAction"
      :request-model="IfaBuyingPowerDomesticA002RequestModel"
      @response-handler="a002ActionHandler"
    ></ifa-requester>
    <ifa-requester
      id="IfaBuyingPowerDomesticA003"
      action-id="SUB0202_010301-01#A003"
      action-type="requestAction"
      :request-model="IfaBuyingPowerDomesticA003RequestModel"
      @response-handler="a003ActionHandler"
    ></ifa-requester>
    <screen-title :text="form.title"></screen-title>
    <div class="caption_card">
      <el-row>
        <el-col :span="22">
          <!-- 口座種別選択 -->
          <ifa-input-radio
            v-if="customerInfo.jrIsaContractType === '1' && customerInfo.withdrawalRestrictionCancelFlag === '1'"
            v-model="form.accountTypeRadio"
            :code-list-id="'ACCOUNT_TYPE'"
            :disp-pattern="'1'"
            :select-pattern="'1'"
            @change="changeAccountType"
          ></ifa-input-radio>
        </el-col>
        <el-col
          :span="2"
          class="__right"
        >
          <ifa-button
            text="更新"
            icon="RefreshRight"
            action-type="originalAction"
            @app-action-handler="updateLoading"
          ></ifa-button>
        </el-col>
      </el-row>
      <!-- 買付余力 -->
      <el-row>
        <caption-card caption="買付余力">
          <table
            class="_table__horizontal _table__body"
            style="margin-bottom: 0.5rem;"
          >
            <thead>
              <tr>
                <th class="_table__header __center">受渡日</th>
                <th
                  v-if="form.generalAccountFlag === '1' && form.jrNisageneralAccountFlag !== '1'"
                  class="_table__header __center"
                >買付余力</th>
                <th
                  v-if="form.generalAccountFlag === '0' && form.jrGeneralAccountFlag === '1' && form.jrNisageneralAccountFlag === '1'"
                  class="_table__header __center"
                >買付余力<br>（総合口座）</th>
                <th
                  v-if="form.jrNisageneralAccountFlag === '1'"
                  class="_table__header __center"
                >買付余力<br>（ジュニアNISA口座）</th>
                <th class="_table__header __center">対象取引・商品</th>
                <th class="_table__header __center">受渡日</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="_table__data __center">{{ $_getFormattedDateValue(form.settlementDateT2) }}({{ getDateOfWeek(form.settlementDateT2) }})</td>
                <td
                  v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerGeneralAccountT2) }}円</td>
                <td
                  v-if="form.jrNisageneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerJrNisaT2) }}円</td>
                <td class="_table__data __left">国内株式</td>
                <td class="_table__data __center">2営業日後</td>
              </tr>
              <tr>
                <td class="_table__data __center">{{ $_getFormattedDateValue(form.settlementDateT3) }}({{ getDateOfWeek(form.settlementDateT3) }})</td>
                <td
                  v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerGeneralAccountT3) }}円</td>
                <td
                  v-if="form.jrNisageneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerJrNisaT3) }}円</td>
                <td class="_table__data __left">投資信託</td>
                <td class="_table__data __center">3営業日後</td>
              </tr>
              <tr>
                <td class="_table__data __center">{{ $_getFormattedDateValue(form.settlementDateT4) }}({{ getDateOfWeek(form.settlementDateT4) }})</td>
                <td
                  v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerGeneralAccountT4) }}円</td>
                <td
                  v-if="form.jrNisageneralAccountFlag === '1'"
                  class="_table__data __right"
                >{{ $_withCommaInteger(form.yenBuyingPowerJrNisaT4) }}円</td>
                <td class="_table__data __left">投資信託</td>
                <td class="_table__data __center">4営業日後</td>
              </tr>
            </tbody>
          </table>
          <span>※買付余力は取引内容、商品受渡日別に算出されます。（投資信託の受渡日は各ファンド毎に異なります。） </span>
        </caption-card>
      </el-row>
      <el-row>
        <!-- 買付余力対象商品残高 -->
        <el-col :span="10">
          <caption-card caption="買付余力対象商品残高">
            <table class="_table__body">
              <tbody>
                <tr v-if="form.generalAccountFlag === '0' && form.jrGeneralAccountFlag === '1' && form.jrNisageneralAccountFlag === '1'">
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  ></th>
                  <th
                    class="_table__header __left"
                  >総合口座</th>
                  <th
                    class="_table__header __left"
                  >ジュニアNISA口座</th>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >預り金(MRF含む)・信用保証金</th>
                  <td
                    v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.totalOfMrfDpositMargin) }}</td>
                  <td
                    v-if="form.jrNisageneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.nisatotalOfMrfDpositMargin) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >入金額</th>
                  <td
                    v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.depositAmount) }}</td>
                  <td
                    v-if="form.jrNisageneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.nisadepositAmount) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >合計額</th>
                  <td
                    v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.totalAmount) }}</td>
                  <td
                    v-if="form.jrNisageneralAccountFlag === '1'"
                    class="_table__data __right"
                  >{{ $_withCommaInteger(form.nisatotalAmount) }}</td>
                </tr>
              </tbody>
            </table>
          </caption-card>
        </el-col>
        <!-- ポイント残高 -->
        <el-col
          v-if="form.pointDisplayAreaAvailability && form.pointDisplayAreaAvailability !== '0'"
          :span="9"
        >
          <caption-card caption="ポイント残高">
            <table class="_table__body">
              <tbody>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >{{ form.pointNameDisplayAvailability === '1' ? "保有" + $_getCodeValue("POINT_TYPE", 1, form.pointShubetsu) : "保有ポイント" }}</th>
                  <td class="_table__data __right">
                    {{ form.pointNumberDisplayAvailability === '1' ? $_withCommaInteger(form.pointNumber) + $_getCodeValue("POINT_TYPE", 2, form.pointShubetsu) :
                      form.pointNumberDisplayAvailability === '2' ? '-' + $_getCodeValue("POINT_TYPE", 2, form.pointShubetsu) : '' }}
                  </td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >{{ form.fixedTermPointDisplayAvailability === '1' || '2' ? form.pointNameDisplayAvailability === '1' ? "うち期間固定" + $_getCodeValue("POINT_TYPE", 1, form.pointShubetsu) : "うち期間固定ポイント" : "うち期間固定ポイント" }}</th>
                  <td class="_table__data __right">
                    {{ form.fixedTermPointDisplayAvailability === '1' ? form.fixedTermPoint ? $_withCommaInteger(form.fixedTermPoint) : '0' : '' }}
                    {{ form.fixedTermPointDisplayAvailability === '1' ? $_getCodeValue("POINT_TYPE", 2, form.pointShubetsu) :
                      form.fixedTermPointDisplayAvailability === '2' ? '-' + $_getCodeValue("POINT_TYPE", 2, form.pointShubetsu) : '' }}
                  </td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left"
                    style="width: 50%;"
                  >最短有効期限</th>
                  <td class="_table__data __right">
                    {{ form.pointShortLimitDisplayAvailability === '1' ? $_getFormattedDateValue(form.pointShortLimit) : form.pointShortLimitDisplayAvailability === '2' ? "----/--/--" : "" }}
                  </td>
                </tr>
              </tbody>
            </table>
          </caption-card>
        </el-col>
      </el-row>
      <!-- 精算予定一覧表 -->
      <el-row v-if="form.generalAccountFlag === '1' || form.jrGeneralAccountFlag === '1'">
        <caption-card :caption="form.generalAccountFlag === '1' ? '精算予定一覧表' : form.jrGeneralAccountFlag === '1' ? '精算予定一覧表（総合口座）' : '精算予定一覧表'">
          <table class="_table__horizontal _table__body">
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                  style="min-width: 245px;"
                >受渡日</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.todaySettlementDate) }}<br>（当日）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterOnedaySettlementDate) }}<br>（1営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterTwodaysSettlementDate) }}<br>（2営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterThreedaysSettlementDate) }}<br>（3営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterFourdaysSettlementDate) }}<br>（4営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterFivedaysSettlementDate) }}～<br>（5営業日後～）</th>
              </tr>
            </thead>
            <tbody>
              <tr v-if="customerInfo.domesticMarginAccountType === ' '">
                <td class="_table__data __left">預り金(MRF含む)</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.todayDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterOnedayDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterTwodaysDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterThreedaysDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFourdaysDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFivedaysDepositIncludingMrf) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === '1'">
                <td class="_table__data __left">保証金現金</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.todayMargin) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterOnedayMargin) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterTwodaysMargin) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterThreedaysMargin) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFourdaysMargin) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFivedaysMargin) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === '1'">
                <td class="_table__data __left">預り金</td>
                <td :class="cellColor(form.todayYenDeposit)">{{ $_withCommaInteger(form.todayYenDeposit) }}</td>
                <td :class="cellColor(form.afterOnedayYenDeposit)">{{ $_withCommaInteger(form.afterOnedayYenDeposit) }}</td>
                <td :class="cellColor(form.afterTwodaysYenDeposit)">{{ $_withCommaInteger(form.afterTwodaysYenDeposit) }}</td>
                <td :class="cellColor(form.afterThreedaysYenDeposit)">{{ $_withCommaInteger(form.afterThreedaysYenDeposit) }}</td>
                <td :class="cellColor(form.afterFourdaysYenDeposit)">{{ $_withCommaInteger(form.afterFourdaysYenDeposit) }}</td>
                <td :class="cellColor(form.afterFivedaysYenDeposit)">{{ $_withCommaInteger(form.afterFivedaysYenDeposit) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">入金額</td>
                <td
                  :class="cellColor(form.todayDepositAmount, true)"
                  @click="a002Action('1', form.todayDepositAmount, '0', form.todaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.todayDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayDepositAmount, true)"
                  @click="a002Action('1', form.afterOnedayDepositAmount, ' ', form.afterOnedaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterOnedayDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysDepositAmount, true)"
                  @click="a002Action('1', form.afterTwodaysDepositAmount, ' ', form.afterTwodaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterTwodaysDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysDepositAmount, true)"
                  @click="a002Action('1', form.afterThreedaysDepositAmount, ' ', form.afterThreedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterThreedaysDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysDepositAmount, true)"
                  @click="a002Action('1', form.afterFourdaysDepositAmount, ' ', form.afterFourdaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFourdaysDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysDepositAmount, true)"
                  @click="a002Action('1', form.afterFivedaysDepositAmount, '1', form.afterFivedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFivedaysDepositAmount) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">支払額</td>
                <td
                  :class="cellColor(form.todayPayment, true)"
                  @click="a002Action('2', form.todayPayment, '0', form.todaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.todayPayment) }}</td>
                <td
                  :class="cellColor(form.afterOnedayPayment, true)"
                  @click="a002Action('2', form.afterOnedayPayment, ' ', form.afterOnedaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterOnedayPayment) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysPayment, true)"
                  @click="a002Action('2', form.afterTwodaysPayment, ' ', form.afterTwodaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterTwodaysPayment) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysPayment, true)"
                  @click="a002Action('2', form.afterThreedaysPayment, ' ', form.afterThreedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterThreedaysPayment) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysPayment, true)"
                  @click="a002Action('2', form.afterFourdaysPayment, ' ', form.afterFourdaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFourdaysPayment) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysPayment, true)"
                  @click="a002Action('2', form.afterFivedaysPayment, '1', form.afterFivedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFivedaysPayment) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === '1'">
                <td class="_table__data __left">未約定信用決済損</td>
                <td
                  :class="cellColor(form.todayUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.todayUncontractedCreditSettlementLoss, '0', form.todaySettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.todayUncontractedCreditSettlementLoss) }}</td>
                <td
                  :class="cellColor(form.afterOnedayUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.afterOnedayUncontractedCreditSettlementLoss, ' ', form.afterOnedaySettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.afterOnedayUncontractedCreditSettlementLoss) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.afterTwodaysUncontractedCreditSettlementLoss, ' ', form.afterTwodaysSettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.afterTwodaysUncontractedCreditSettlementLoss) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.afterThreedaysUncontractedCreditSettlementLoss, ' ', form.afterThreedaysSettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.afterThreedaysUncontractedCreditSettlementLoss) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.afterFourdaysUncontractedCreditSettlementLoss, ' ', form.afterFourdaysSettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.afterFourdaysUncontractedCreditSettlementLoss) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysUncontractedCreditSettlementLoss, true)"
                  @click="a002Action('6', form.afterFivedaysUncontractedCreditSettlementLoss, '1', form.afterFivedaysSettlementDate, getAccountType(false, true))"
                >{{ $_withCommaInteger(form.afterFivedaysUncontractedCreditSettlementLoss) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">未約定買注文額</td>
                <td
                  :class="cellColor(form.todayUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.todayUncontractedPurchaseOrderAmount, '0', form.todaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.todayUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterOnedayUncontractedPurchaseOrderAmount, ' ', form.afterOnedaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterOnedayUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterTwodaysUncontractedPurchaseOrderAmount, ' ', form.afterTwodaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterTwodaysUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterThreedaysUncontractedPurchaseOrderAmount, ' ', form.afterThreedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterThreedaysUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterFourdaysUncontractedPurchaseOrderAmount, ' ', form.afterFourdaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFourdaysUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterFivedaysUncontractedPurchaseOrderAmount, '1', form.afterFivedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFivedaysUncontractedPurchaseOrderAmount) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">出金・振替指示額</td>
                <td
                  :class="cellColor(form.todayWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.todayWithdrawalTransferInstructions, '0', form.todaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.todayWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterOnedayWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterOnedayWithdrawalTransferInstructions, ' ', form.afterOnedaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterOnedayWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterTwodaysWithdrawalTransferInstructions, ' ', form.afterTwodaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterTwodaysWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterThreedaysWithdrawalTransferInstructions, ' ', form.afterThreedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterThreedaysWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterFourdaysWithdrawalTransferInstructions, ' ', form.afterFourdaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFourdaysWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterFivedaysWithdrawalTransferInstructions, '1', form.afterFivedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFivedaysWithdrawalTransferInstructions) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">受取額</td>
                <td
                  :class="cellColor(form.todayAmountReceived, true)"
                  @click="a002Action('5', form.todayAmountReceived, '0', form.todaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.todayAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterOnedayAmountReceived, true)"
                  @click="a002Action('5', form.afterOnedayAmountReceived, ' ', form.afterOnedaySettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterOnedayAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysAmountReceived, true)"
                  @click="a002Action('5', form.afterTwodaysAmountReceived, ' ', form.afterTwodaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterTwodaysAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysAmountReceived, true)"
                  @click="a002Action('5', form.afterThreedaysAmountReceived, ' ', form.afterThreedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterThreedaysAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysAmountReceived, true)"
                  @click="a002Action('5', form.afterFourdaysAmountReceived, ' ', form.afterFourdaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFourdaysAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysAmountReceived, true)"
                  @click="a002Action('5', form.afterFivedaysAmountReceived, '1', form.afterFivedaysSettlementDate, getAccountType(false, false))"
                >{{ $_withCommaInteger(form.afterFivedaysAmountReceived) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">受取額（日計り分）</td>
                <td
                  :class="cellColor(form.todayDayTrading)"
                >{{ $_withCommaInteger(form.todayDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterOnedayDayTrading)"
                >{{ $_withCommaInteger(form.afterOnedayDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysDayTrading)"
                >{{ $_withCommaInteger(form.afterTwodaysDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysDayTrading)"
                >{{ $_withCommaInteger(form.afterThreedaysDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysDayTrading)"
                >{{ $_withCommaInteger(form.afterFourdaysDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysDayTrading)"
                >{{ $_withCommaInteger(form.afterFivedaysDayTrading) }}</td>
              </tr>
              <tr v-if="form.sbiHybridFlag === '1'">
                <td class="_table__data __left">スィープ専用銀行口座精算額</td>
                <td
                  :class="cellColor(form.todaySbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.todaySbiHybridDepositSettlementAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedaySbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.afterOnedaySbiHybridDepositSettlementAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysSbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.afterTwodaysSbiHybridDepositSettlementAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysSbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.afterThreedaysSbiHybridDepositSettlementAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysSbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.afterFourdaysSbiHybridDepositSettlementAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysSbiHybridDepositSettlementAmount)"
                >{{ $_withCommaInteger(form.afterFivedaysSbiHybridDepositSettlementAmount) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === ' ' && form.sbiHybridFlag === '0'">
                <td class="_table__data __left">残高合計額</td>
                <td
                  :class="cellColor(form.todayTotalBalance)"
                >{{ $_withCommaInteger(form.todayTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedayTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedayTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysTotalBalance) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === ' ' && form.sbiHybridFlag === '1'">
                <th class="_table__header __vertical __left">残高（当社）</th>
                <td
                  :class="cellColor(form.todayOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.todayOurCompanyTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedayOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedayOurCompanyTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysOurCompanyTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysOurCompanyTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysOurCompanyTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysOurCompanyTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysOurCompanyTotalBalance) }}</td>
              </tr>
              <tr v-if="form.sbiHybridFlag === '1'">
                <th class="_table__header __vertical __left">残高(スィープ専用銀行口座)</th>
                <td
                  :class="cellColor(form.todaySbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.todaySbiHybridDepositSettlementTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedaySbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedaySbiHybridDepositSettlementTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysSbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysSbiHybridDepositSettlementTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysSbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysSbiHybridDepositSettlementTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysSbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysSbiHybridDepositSettlementTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysSbiHybridDepositSettlementTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysSbiHybridDepositSettlementTotalBalance) }}</td>
              </tr>
              <tr v-if="form.sbiHybridFlag === '1'">
                <td class="_table__data __left">(スィープ専用銀行口座拘束額)</td>
                <td
                  :class="cellColor(form.todaySbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.todaySbiHybridDepositSettlementRestrictedAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedaySbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.afterOnedaySbiHybridDepositSettlementRestrictedAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysSbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.afterTwodaysSbiHybridDepositSettlementRestrictedAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysSbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.afterThreedaysSbiHybridDepositSettlementRestrictedAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysSbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.afterFourdaysSbiHybridDepositSettlementRestrictedAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysSbiHybridDepositSettlementRestrictedAmount)"
                >{{ $_withCommaInteger(form.afterFivedaysSbiHybridDepositSettlementRestrictedAmount) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === '1'">
                <th class="_table__header __vertical __left">残高(保証金現金)</th>
                <td
                  :class="cellColor(form.todayMarginTotalBalance)"
                >{{ $_withCommaInteger(form.todayMarginTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedayMarginTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedayMarginTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysMarginTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysMarginTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysMarginTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysMarginTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysMarginTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysMarginTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysMarginTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysMarginTotalBalance) }}</td>
              </tr>
              <tr v-if="customerInfo.domesticMarginAccountType === '1'">
                <th class="_table__header __vertical __left">残高(預り金)</th>
                <td
                  :class="cellColor(form.todayDpositTotalBalance)"
                >{{ $_withCommaInteger(form.todayDpositTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedayDpositTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedayDpositTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysDpositTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysDpositTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysDpositTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysDpositTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysDpositTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysDpositTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysDpositTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysDpositTotalBalance) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">必要精算額
                  <span v-if="customerInfo.domesticMarginAccountType === '1'">
                    <br>
                    必要保証金額
                  </span>
                </td>
                <td
                  :class="cellColor(form.todayRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.todayRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterOnedayRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterOnedayRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterTwodaysRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterThreedaysRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterFourdaysRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterFivedaysRequiredSettlementAmountYen) }}</td>
              </tr>
              <tr>
                <th class="_table__header __vertical __center">買付余力</th>
                <td
                  :class="cellColor(form.todayYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.todayYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterOnedayYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterTwodaysYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterThreedaysYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterFourdaysYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterFivedaysYenBuyingPowerGeneralAccount) }}</td>
              </tr>
            </tbody>
          </table>
        </caption-card>
      </el-row>
      <el-row v-if="form.jrNisageneralAccountFlag === '1'">
        <caption-card caption="精算予定一覧表（ジュニアNISA口座）">
          <table class="_table__horizontal _table__body">
            <thead>
              <tr>
                <th
                  class="_table__header __center"
                  style="min-width: 245px;"
                >受渡日</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.jrNisaSettlementDate) }}<br>（当日）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterOnedayJrNisaSettlementDate) }}<br>（1営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterTwodaysJrNisaSettlementDate) }}<br>（2営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterThreedaysJrNisaSettlementDate) }}<br>（3営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterFourdaysJrNisaSettlementDate) }}<br>（4営業日後）</th>
                <th class="_table__header __center">{{ $_getFormattedDateValue(form.afterFivedaysJrNisaSettlementDate) }}～<br>（5営業日後～）</th>
              </tr>
            </thead>
            <tbody>
              <tr v-if="customerInfo.domesticMarginAccountType === ' '">
                <td class="_table__data __left">預り金(MRF含む)</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.jrNisaDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterOnedayJrNisaDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterTwodaysJrNisaDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterThreedaysJrNisaDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFourdaysJrNisaDepositIncludingMrf) }}</td>
                <td class="_table__data __right">{{ $_withCommaInteger(form.afterFivedaysJrNisaDepositIncludingMrf) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">入金額</td>
                <td
                  :class="cellColor(form.jrNisaDepositAmount, true)"
                  @click="a002Action('1', form.jrNisaDepositAmount, '0', form.jrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.jrNisaDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaDepositAmount, true)"
                  @click="a002Action('1', form.afterOnedayJrNisaDepositAmount, ' ', form.afterOnedayJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaDepositAmount, true)"
                  @click="a002Action('1', form.afterTwodaysJrNisaDepositAmount, ' ', form.afterTwodaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaDepositAmount, true)"
                  @click="a002Action('1', form.afterThreedaysJrNisaDepositAmount, ' ', form.afterThreedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaDepositAmount, true)"
                  @click="a002Action('1', form.afterFourdaysJrNisaDepositAmount, ' ', form.afterFourdaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaDepositAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaDepositAmount, true)"
                  @click="a002Action('1', form.afterFivedaysJrNisaDepositAmount, '1', form.afterFivedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaDepositAmount) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">支払額</td>
                <td
                  :class="cellColor(form.jrNisaPayment, true)"
                  @click="a002Action('2', form.jrNisaPayment, '0', form.jrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.jrNisaPayment) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaPayment, true)"
                  @click="a002Action('2', form.afterOnedayJrNisaPayment, ' ', form.afterOnedayJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaPayment) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaPayment, true)"
                  @click="a002Action('2', form.afterTwodaysJrNisaPayment, ' ', form.afterTwodaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaPayment) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaPayment, true)"
                  @click="a002Action('2', form.afterThreedaysJrNisaPayment, ' ', form.afterThreedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaPayment) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaPayment, true)"
                  @click="a002Action('2', form.afterFourdaysJrNisaPayment, ' ', form.afterFourdaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaPayment) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaPayment, true)"
                  @click="a002Action('2', form.afterFivedaysJrNisaPayment, '1', form.afterFivedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaPayment) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">未約定買注文額</td>
                <td
                  :class="cellColor(form.jrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.jrNisaUncontractedPurchaseOrderAmount, '0', form.jrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.jrNisaUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterOnedayJrNisaUncontractedPurchaseOrderAmount, ' ', form.afterOnedayJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterTwodaysJrNisaUncontractedPurchaseOrderAmount, ' ', form.afterTwodaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterThreedaysJrNisaUncontractedPurchaseOrderAmount, ' ', form.afterThreedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterFourdaysJrNisaUncontractedPurchaseOrderAmount, ' ', form.afterFourdaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaUncontractedPurchaseOrderAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaUncontractedPurchaseOrderAmount, true)"
                  @click="a002Action('3', form.afterFivedaysJrNisaUncontractedPurchaseOrderAmount, '1', form.afterFivedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaUncontractedPurchaseOrderAmount) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">出金・振替指示額</td>
                <td
                  :class="cellColor(form.jrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.jrNisaWithdrawalTransferInstructions, '0', form.jrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.jrNisaWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterOnedayJrNisaWithdrawalTransferInstructions, ' ', form.afterOnedayJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterTwodaysJrNisaWithdrawalTransferInstructions, ' ', form.afterTwodaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterThreedaysJrNisaWithdrawalTransferInstructions, ' ', form.afterThreedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterFourdaysJrNisaWithdrawalTransferInstructions, ' ', form.afterFourdaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaWithdrawalTransferInstructions) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaWithdrawalTransferInstructions, true)"
                  @click="a002Action('4', form.afterFivedaysJrNisaWithdrawalTransferInstructions, '1', form.afterFivedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaWithdrawalTransferInstructions) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">受取額</td>
                <td
                  :class="cellColor(form.jrNisaAmountReceived, true)"
                  @click="a002Action('5', form.jrNisaAmountReceived, '0', form.jrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.jrNisaAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaAmountReceived, true)"
                  @click="a002Action('5', form.afterOnedayJrNisaAmountReceived, ' ', form.afterOnedayJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaAmountReceived, true)"
                  @click="a002Action('5', form.afterTwodaysJrNisaAmountReceived, ' ', form.afterTwodaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaAmountReceived, true)"
                  @click="a002Action('5', form.afterThreedaysJrNisaAmountReceived, ' ', form.afterThreedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaAmountReceived, true)"
                  @click="a002Action('5', form.afterFourdaysJrNisaAmountReceived, ' ', form.afterFourdaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaAmountReceived) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaAmountReceived, true)"
                  @click="a002Action('5', form.afterFivedaysJrNisaAmountReceived, '1', form.afterFivedaysJrNisaSettlementDate, getAccountType(true, false))"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaAmountReceived) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">受取額（日計り分）</td>
                <td
                  :class="cellColor(form.jrNisaDayTrading)"
                >{{ $_withCommaInteger(form.jrNisaDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaDayTrading)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaDayTrading)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaDayTrading)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaDayTrading)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaDayTrading) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaDayTrading)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaDayTrading) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">振替予定額<br>（総合口座→ジュニアNISA口座）</td>
                <td
                  :class="cellColor(form.jrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.jrNisaPlannedTransferAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaPlannedTransferAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaPlannedTransferAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaPlannedTransferAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaPlannedTransferAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaPlannedTransferAmount)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaPlannedTransferAmount) }}</td>
              </tr>
              <tr>
                <th class="_table__header __vertical __left">残高合計額</th>
                <td
                  :class="cellColor(form.jrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.jrNisaTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaTotalBalance) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaTotalBalance)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaTotalBalance) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">振替可能額<br>（総合口座→ジュニアNISA口座）</td>
                <td
                  :class="cellColor(form.jrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.jrNisaTransferableAmount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaTransferableAmount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaTransferableAmount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaTransferableAmount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaTransferableAmount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaTransferableAmount)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaTransferableAmount) }}</td>
              </tr>
              <tr>
                <td class="_table__data __left">必要精算額
                  <span v-if="customerInfo.domesticMarginAccountType === '1'">
                    <br>必要保証金額
                  </span>
                </td>
                <td
                  :class="cellColor(form.jrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.jrNisaRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaRequiredSettlementAmountYen) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaRequiredSettlementAmountYen)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaRequiredSettlementAmountYen) }}</td>
              </tr>
              <tr>
                <th class="_table__header __vertical __center">買付余力</th>
                <td
                  :class="cellColor(form.jrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.jrNisaYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterOnedayJrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterOnedayJrNisaYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterTwodaysJrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterTwodaysJrNisaYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterThreedaysJrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterThreedaysJrNisaYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterFourdaysJrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterFourdaysJrNisaYenBuyingPowerGeneralAccount) }}</td>
                <td
                  :class="cellColor(form.afterFivedaysJrNisaYenBuyingPowerGeneralAccount)"
                >{{ $_withCommaInteger(form.afterFivedaysJrNisaYenBuyingPowerGeneralAccount) }}</td>
              </tr>
            </tbody>
          </table>
        </caption-card>
      </el-row>
      <!-- 注意事項 -->
      <el-row style="font-size: 12px;">
        ＜ポイントをご利用した注文についてのご注意事項＞<br>
        精算予定一覧表ではポイントを利用した買い注文分は未約定買注文額には表示されません。<br>
        約定後、ポイントが現金に交換されることで支払額に表示されます。ただし、ポイントを利用したご注文の場合、成行注文の見積単価や指値価格と実際の約定価格が異なった場合、当初ポイント拘束額と約定金額の差額のポイント分は夕刻16時半頃にお戻しさせていただきます。
      </el-row>
      <!-- ダイアログ -->
      <ifa-unsettle-detail
        :is-visible="unpaidDetailsVisible"
        :form-data="unsettleDetailModel"
        @close-modal="handleCloseModal"
      ></ifa-unsettle-detail>

    </div>
  </div>
</template>

<script>
import IfaUnsettleDetail from './IfaUnsettleDetail'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaBuyingPowerDomesticA001RequestModel } from './js/IfaBuyingPowerDomesticA001RequestModel'
import { IfaBuyingPowerDomesticA002RequestModel } from './js/IfaBuyingPowerDomesticA002RequestModel'
import { IfaBuyingPowerDomesticA003RequestModel } from './js/IfaBuyingPowerDomesticA003RequestModel'
import { IfaBuyingPowerDomesticFormModel } from './js/IfaBuyingPowerDomesticFormModel'

export default {
  components: {
    IfaUnsettleDetail,
    captionCard,
    screenTitle
  },
  emits: ['initializeError'],
  data() {
    return {
      form: new IfaBuyingPowerDomesticFormModel(),
      a002RequestParam: {},
      a003RequestParam: {},
      unsettleDetailModel: {},
      unpaidDetailsVisible: false
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    IfaBuyingPowerDomesticA001RequestModel() { return new IfaBuyingPowerDomesticA001RequestModel(this.customerInfo) },
    IfaBuyingPowerDomesticA002RequestModel() { return new IfaBuyingPowerDomesticA002RequestModel(this.a002RequestParam) },
    IfaBuyingPowerDomesticA003RequestModel() { return new IfaBuyingPowerDomesticA003RequestModel(this.a003RequestParam) }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaBuyingPowerDomesticA001').click()
      })
    },
    a001ActionHandler(response) {
      this.form = Object.assign(this.form, response.dataList[0])
    },
    responseErrorHandlerInitializeA001(data) {
      const errorInfo = {
        title: this.form.title,
        message: data.message
      }
      this.$emit('initializeError', errorInfo)
    },
    a002Action(reqType, value, dateType, setDate, accountType) {
      const param = {
        butenCode: this.$store.getters.customerInfo.butenCode,
        accountNumber: this.$store.getters.customerInfo.accountNumber,
        requestType: reqType,
        requestDateType: dateType,
        settlementDate: setDate,
        acquireAccountType: accountType
      }
      if (Number(value) !== 0) {
        this.a002RequestParam = param
        this.$nextTick(() => {
          document.getElementById('IfaBuyingPowerDomesticA002').click()
        })
      }
    },
    a003Action() {
      const param = {
        butenCode: this.$store.getters.customerInfo.butenCode,
        accountNumber: this.$store.getters.customerInfo.accountNumber,
        accountTypeRadio: this.form.accountTypeRadio.toString()
      }
      this.a003RequestParam = param
      this.$nextTick(() => {
        document.getElementById('IfaBuyingPowerDomesticA003').click()
      })
    },
    a002ActionHandler(response) {
      this.unsettleDetailModel = Object.assign(this.unsettleDetailModel, response.dataList[0])
      this.unpaidDetailsVisible = true
    },
    a003ActionHandler(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.$nextTick(() => {
        const radioVal = this.customerInfo.jrIsaContractType === '1' && this.customerInfo.withdrawalRestrictionCancelFlag === '1' ? this.form.accountTypeRadio : ' '
        this.changeAccountType(radioVal)
      })
    },
    // 更新ボタン
    updateLoading() {
      this.a003Action()
    },
    // 戻るボタン
    handleCloseModal() {
      this.unpaidDetailsVisible = false
    },
    changeAccountType(value) {
      this.form.generalAccountFlag = value === ' ' ? '1' : '0'
      this.form.jrGeneralAccountFlag = (value === '2' || value === ' ') ? '1' : '0'
      this.form.jrNisageneralAccountFlag = (value === '2' || value === '1') ? '1' : '0'
    },
    getDateOfWeek(inputDate) {
      if (inputDate) {
        const date = new Date(this.$_getFormattedDateValue(inputDate))
        const dayOfWeek = date.getDay()
        const dayOfWeekStr = ['日', '月', '火', '水', '木', '金', '土'][dayOfWeek]
        return dayOfWeekStr
      }
      return '-'
    },
    // 数値に応じた色を返す
    cellColor(value, clickable = false) {
      return '_table__data __right ' + (clickable && Number(value) !== 0 ? ' clickable' : '')
    },
    // A002.リクエスト.取得口座区分を取得する
    getAccountType(isJrNisaLink, isUncontractedCreditSettlementLoss) {
      // 精算予定一覧表（ジュニアNISA口座）内のリンク：1
      if (isJrNisaLink) {
        return '1'
      // 精算予定一覧内のリンク
      } else {
        // 顧客共通情報.ジュニアISA契約区分≠1：△
        if (this.customerInfo.jrIsaContractType !== '1') {
          return ' '
        // 顧客共通情報.ジュニアISA契約区分＝1
        } else {
          // 顧客共通情報.払出制限解除フラグ＝1：△
          if (this.customerInfo.withdrawalRestrictionCancelFlag === '1') {
            return ' '
          // 顧客共通情報.払出制限解除フラグ≠1
          } else {
            // 呼出元画面のラベルが「未約定信用決済損」：△
            if (isUncontractedCreditSettlementLoss) {
              return ' '
            }
          }
        }
      }
      // 上記を満たさない場合：2
      return '2'
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
@import "@/styles/table.scss";
.__right {
  text-align: right;
  padding-right: 1rem;
  &._buy {
    color: $buyOrPlusColor;
  }
  &._sell {
    color: $sellOrMinusColor;
  }
  &.clickable {
    text-decoration-skip-ink: none;
    color: #092987;
    text-decoration: underline;
      &:hover {
      opacity: 0.7;
    }
  }
  &.bold {
    font-weight: bold;
  }
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
</style>
