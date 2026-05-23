<template>
  <div>
    <!-- 表示制御：信用建余力対象 -->
    <el-row>
      <el-col :span="24">
        <div
          class="clickable"
          @click="creditReserveDetailVisible = !creditReserveDetailVisible"
        >
          <el-icon
            v-if="creditReserveDetailVisible"
            class="header-icon"
          ><Remove></Remove></el-icon>
          <el-icon
            v-else
            class="header-icon"
          ><CirclePlus></CirclePlus></el-icon>
          <span class="list-font">信用建余力詳細情報</span>
        </div>
      </el-col>
    </el-row>

    <div v-if="creditReserveDetailVisible === true">
      <el-row style="margin-top: 1rem;">
        <caption-card
          v-if="form.deliveryDateToFiveDaysLaterList && form.deliveryDateToFiveDaysLaterList.length"
          caption="信用余力情報"
        >
          <table class="_table__body">
            <tbody>
              <tr>
                <th class="_table__header __center __bold">受渡日</th>
                <th class="_table__header __center __bold">委託保証金率</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '0')"
                    class="_table__header __center __bold"
                >31%超分</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >31%超分<br>(当社のみ)</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >31%超分<br>(当社+SSNB)</th>
                <th class="_table__header __center __bold">信用建余力</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '0')"
                    class="_table__header __center __bold"
                >30%超分</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >30%超分<br>(当社のみ)</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >30%超分<br>(当社+SSNB)</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >30.2%超分<br>(当社のみ)</th>
                <th v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__header __center __bold"
                >30.2%超分<br>(当社+SSNB)</th>
                <th class="_table__header __center __bold">現物買付余力</th>
                <th class="_table__header __center __bold">出金振替可能額</th>
              </tr>
              <tr v-for="(item, idx) in form.deliveryDateToFiveDaysLaterList"
                  :key="idx"
                  :class="[idx % 2 === 1 ? '_border_colored_row' : '']"
              >
                <td v-if="idx != form.deliveryDateToFiveDaysLaterList.length - 1"
                    class="_table__data __center"
                >{{ $_out($_getFormattedDateValue(item.settlementDateT0toT5, 'date8')) }}
                </td>
                <td v-else-if="idx === form.deliveryDateToFiveDaysLaterList.length - 1"
                    class="_table__data __center"
                >{{ $_out(item.settlementDateT0toT5) === '-' ? '-' : $_getFormattedDateValue(item.settlementDateT0toT5, 'date8') + '以降' }}
                </td>
                <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(item.reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9,2)) === '-' ? $_out($_withCommaZeroPadding(item.reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9,2)): $_withCommaZeroPadding(item.reservePowerDetailDeliveryDateToFiveDaysLaterMarginDepositRate9,2) + '%' }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '0')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over31per)) }}
                </td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over31perOurCompany)) }}
                </td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over31perOurCompanySsnb)) }}</td>
                <td v-if="item.creditCapacity < 0"
                    class="_table__data __right"
                >{{ "0" }}</td>
                <td v-else
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.creditCapacity)) }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '0')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over30per)) }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over30perOurCompany)) }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over30perOurCompanySsnb)) }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over302perOurCompany)) }}</td>
                <td v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.over302perOurCompanySsnb)) }}</td>
                <td class="_table__data __right">{{ $_out($_withCommaInteger(item.buyingRemainingPower)) }}</td>
                <td class="_table__data __right">{{ $_out($_withCommaInteger(item.withdrawalCapacity)) }}</td>
              </tr>
            </tbody>
          </table>
        </caption-card>
      </el-row>

      <el-row>
        <caption-card
          v-if="form.deliveryDateToFiveDaysLaterList && form.deliveryDateToFiveDaysLaterList.length"
          caption="委託保証金"
          style="overflow-x: scroll;"
        >
          <table class="_table__body">
            <tbody>
              <tr>
                <th
                  rowspan="2"
                  class="_table__header __center __bold"
                >受渡日</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '0')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >保証金現金<br>(余力WK)</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >SSNB精算前<br>保証金現金<br>(余力WK)</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >SSNB精算額</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >SSNB精算後<br>保証金</th>
                <th class="_table__header __center __bold">代用入庫額</th>
                <th
                  rowspan="2"
                  class="_table__header __center _separator_right __bold"
                >保証金代用</th>
                <th
                  rowspan="2"
                  class="_table__header __center __bold"
                >保証金合計</th>
                <th
                  rowspan="14"
                  class="_separator_row"
                ></th>
                <th
                  rowspan="2"
                  class="_table__header_light __center __bold"
                >評価損<br>(評価損益)</th>
                <th
                  rowspan="2"
                  class="_table__header_light __center __bold"
                >決済損益</th>
                <th
                  rowspan="2"
                  class="_table__header_light __center __bold"
                >支払諸経費</th>
                <th
                  rowspan="2"
                  class="_table__header_light __center _separator_right __bold"
                >実質保証金</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                > スィープ専用<br>銀行口座<br>預金残高</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >実保+<br> スィープ専用<br>銀行口座<br>預金</th>
                <th
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >当社保証金+<br> スィープ専用<br>銀行口座<br>(現金部分のみ)</th>
              </tr>
              <tr>
                <td class="_table__header __center __bold">代用出庫額</td>
              </tr>
              <template v-for="(item, idx) in form.deliveryDateToFiveDaysLaterList"
                        :key="idx"
              >
                <tr>
                  <td v-if="idx !== form.deliveryDateToFiveDaysLaterList.length - 1"
                      rowspan="2"
                      class="_table__data __center"
                  >{{ $_out($_getFormattedDateValue(item.consignmentSecurityDepositDeliveryDate, 'date8')) }}
                  </td>

                  <td v-else-if="idx === form.deliveryDateToFiveDaysLaterList.length - 1"
                      rowspan="2"
                      class="_table__data __center"
                  >{{ $_out(item.consignmentSecurityDepositDeliveryDate) === '-' ? '-' : $_getFormattedDateValue(item.consignmentSecurityDepositDeliveryDate, 'date8') + '以降' }}
                  </td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '0')"
                    class="_table__data __right"
                    rowspan="2"
                  >{{ $_out($_withCommaInteger(item.remainingCapacityWkTop)) }}
                    <br>{{ $_out(item.remainingCapacityWkUnder) === '-' ? '-' : '(' +$_out($_withCommaInteger(item.remainingCapacityWkUnder)) + ')' }}
                  </td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    class="_table__data __right"
                    rowspan="2"
                  >{{ $_out($_withCommaInteger(item.marginDepositBeforeSsnbCalculateTop)) }}<br>{{ $_out(item.marginDepositBeforeSsnbCalculateUnder) === '-' ? '-' : '(' + $_withCommaInteger(item.marginDepositBeforeSsnbCalculateUnder) + ')' }}
                  </td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.ssnbCalculateAmount)) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.marginDepositAfterSsnbCalculate)) }}</td>
                  <td class="_table__data __right">{{ $_out($_withCommaInteger(item.alternativeReceipt)) }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right _separator_right"
                  >{{ $_out($_withCommaInteger(item.alternativeDeposit)) }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.totalDeposit)) }}</td>
                  <td
                    rowspan="2"
                    :class="item.paymentExpence < 0 ? '__right font-color__minus _table__data_frame' : '_table__data __right'"
                    :style="item.paymentExpence < 0 ? 'font-weight:bold' : ''"
                  >{{ $_out(item.valuationLoss) === '-' ? '-' : $_signedWithCommaInteger(item.valuationLoss).replace('+', '') }}<br>{{ $_out(item.domesticPositionValuation) === '-' ? '-' : '(' + $_signedWithCommaInteger(item.domesticPositionValuation).replace('+', '') + ')' }}</td>
                  <td
                    rowspan="2"
                    :class="item.settlement == 0 ? '_table__data __right' : changeFontColor(item.settlement)"
                    :style="item.settlement == 0 || item.settlement == null || item.settlement =='' ? '' : 'font-weight:bold'"
                  >{{ $_out($_signedWithCommaInteger(item.settlement)) }}</td>
                  <td
                    rowspan="2"
                    :class="item.paymentExpence < 0 ? '__right font-color__minus _table__data_frame' : '_table__data __right'"
                    :style="item.paymentExpence < 0 ? 'font-weight:bold' : ''"
                  >{{ $_out(item.paymentExpence) === '-' ? '-' : $_signedWithCommaInteger(item.paymentExpence).replace('+', '') }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                    :class="item.settlement == 0 ? '_table__data __right' : changeFontColorYen(item.yenActualDeposit)"
                    :style="item.yenActualDeposit == 0 || item.yenActualDeposit == null || item.yenActualDeposit =='' ? '' : 'font-weight:bold'"
                  >{{ $_out(item.yenActualDeposit) === '-' ? '-' : $_signedWithCommaInteger(item.yenActualDeposit) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.hybridDepositBalance)) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.realInsuranceAndHybridDepositBalance)) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.depositAndHybridDepositBalance)) }}</td>
                </tr>
                <tr>
                  <td class="_table__data __right"> {{ $_out($_withCommaInteger(item.substituteDeliveryAmount)) }}</td>
                </tr>
              </template>
            </tbody>
          </table>
        </caption-card>
      </el-row>

      <!-- 必要保証金 -->
      <el-row style="margin-top: 1rem;">
        <caption-card
          v-if="form.deliveryDateToFiveDaysLaterList && form.deliveryDateToFiveDaysLaterList.length"
          caption="必要保証金"
          style="overflow-x: scroll;"
        >
          <table class="_table__body__margin_deposit">
            <tbody>
              <tr>
                <th></th>
                <th class="_table__header __center __left__margin_deposit __right__margin_deposit __top__margin_deposit __left__margin_deposit __bold">前日基準<br>未決済建玉金額</th>
                <th colspan="12"></th>
              </tr>
              <tr>
                <td class="__bottom__margin_deposit"></td>
                <td class="_table__data __right">{{ $_out($_withCommaInteger(form.previousDayBaseOpenInterestAmount)) }}</td>
                <td
                  colspan="12"
                  class="__bottom__margin_deposit"
                ></td>
              </tr>
              <tr>
                <td
                  rowspan="2"
                  class="_table__header __center __left__margin_deposit __left__margin_deposit __bold"
                >受渡日</td>
                <td
                  rowspan="2"
                  class="_table__header __center __bold"
                >注文中建玉金額<br>未決済建玉金額</td>
                <td
                  rowspan="2"
                  class="_table__header __center __bold"
                >決済済建玉金額<br>現引現渡建玉金額</td>
                <td
                  rowspan="2"
                  class="_table__header __center __bold"
                >建玉必要保証金<br>(うち現金)</td>
                <td
                  colspan="3"
                  class="_table__header __center __bold"
                >買付必要保証金<br>(30%)</td>
                <td
                  colspan="3"
                  class="_table__header __center __bold"
                >買付必要保証金<br>(30.2%)</td>
                <td
                  colspan="3"
                  class="_table__header __center __bold"
                >返却必要保証金</td>
                <td
                  rowspan="2"
                  class="_table__header __center __bold"
                >現引・現渡<br>必要保証金</td>
                <td
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __bold"
                >信用取引<br>必要保証金<br>（※）</td>
                <td
                  v-if="Boolean(form.autoSweepTargetFlag === '1')"
                  rowspan="2"
                  class="_table__header __center __right__margin_deposit __bold"
                >当社<br>必要保証金<br>（※）</td>
              </tr>
              <tr>
                <td
                  colspan="3"
                  class="_table__header __center __bold"
                >(うち現金)</td>
                <td
                  colspan="1.5"
                  class="_table__header __center __bold"
                >[増担保]</td>
                <td
                  colspan="2"
                  class="_table__header __center __bold"
                >[配当金]</td>
                <td
                  colspan="1"
                  class="_table__header __center __bold"
                >(うち現金)</td>
                <td
                  colspan="1"
                  class="_table__header __center __bold"
                >[増担保]</td>
                <td
                  colspan="1"
                  class="_table__header __center __bold"
                >[配当金]</td>
              </tr>
              <template v-for="(item, idx) in form.deliveryDateToFiveDaysLaterList"
                        :key="idx"
              >
                <tr>
                  <td v-if="idx != form.deliveryDateToFiveDaysLaterList.length - 1"
                      rowspan="2"
                      class="_table__data __center __left__margin_deposit"
                  >{{ $_out($_getFormattedDateValue(item.settlementDateT0toT5, 'date8')) }}
                  </td>
                  <td v-else-if="idx === form.deliveryDateToFiveDaysLaterList.length - 1"
                      rowspan="2"
                      class="_table__data __center __left__margin_deposit"
                  >{{ $_out(item.settlementDateT0toT5) === '-' ? '-' : $_getFormattedDateValue(item.settlementDateT0toT5, 'date8') + '以降' }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.orderOpenInterest)) }}<br>{{ $_out($_withCommaInteger(item.unsetteledOpenInterest)) }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.setteledOpenInterest)) }}<br>{{ $_out($_withCommaInteger(item.currentDeliverlyOpenInterest)) }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.openInterstDeposit)) }}<br>{{ $_out(item.openInterstDepositCash) === '-' ? '-' : '(' + $_withCommaInteger(item.openInterstDepositCash) + ')' }}</td>
                  <td
                    colspan="3"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.purchaseDeposit30)) }}</td>
                  <td
                    colspan="3"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.purchaseDeposit32)) }}</td>
                  <td
                    colspan="3"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.returnDeposit)) }}</td>
                  <td
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.currentDeliverlyDeposit)) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right"
                  >{{ $_out($_withCommaInteger(item.creditOrderDeposit)) }}</td>
                  <td
                    v-if="Boolean(form.autoSweepTargetFlag === '1')"
                    rowspan="2"
                    class="_table__data __right __right__margin_deposit"
                  >{{ $_out($_withCommaInteger(item.innerDeposit)) }}</td>
                </tr>
                <tr>
                  <td
                    colspan="2"
                    class="_table__data __right"
                  >{{ $_out(item.purchaseDeposit30Cash) === '-' ? '-' : '(' + $_withCommaInteger(item.purchaseDeposit30Cash) + ')' }}</td>
                  <td
                    colspan="2"
                    class="_table__data __right"
                  >{{ $_out(item.purchaseDeposit32AdditionalCollateral) === '-' ? '-' : '[' + $_withCommaInteger(item.purchaseDeposit32AdditionalCollateral) + ']' }}</td>
                  <td
                    colspan="2"
                    class="_table__data __right"
                  >{{ $_out(item.purchaseDeposit32Divined) === '-' ? '-' : '[' + $_withCommaInteger(item.purchaseDeposit32Divined) + ']' }}</td>
                  <td
                    colspan="1"
                    class="_table__data __right"
                  >{{ $_out(item.returnDepositCash) === '-' ? '-' : '(' + $_withCommaInteger(item.returnDepositCash) + ')' }}</td>
                  <td
                    colspan="1"
                    class="_table__data __right"
                  >{{ $_out(item.returnDepositAdditionalCollateral) === '-' ? '-' : '[' + $_withCommaInteger(item.returnDepositAdditionalCollateral) + ']' }}</td>
                  <td
                    colspan="1"
                    class="_table__data __right"
                  >{{ $_out(item.returnDepositDivined) === '-' ? '-' : '[' + $_withCommaInteger(item.returnDepositDivined) + ']' }}</td>
                </tr>
              </template>
            </tbody>
          </table>
          <!-- 注意事項 -->
          <div v-if="Boolean(form.autoSweepTargetFlag === '1')"
               style="color:red"
          >
            (※)信用取引必要保証金と当社必要保証金はSSNBとの間の翌日清算金を算出するために利用します。信用取引必要保証金は建玉必要保証金、買付必要保証金、追証必要保証金、返却必要保証金、最低必要保証金のうち最大金額とします。ただし、「信用取引保証金」の左欄に表示している建玉必要保証金、買付必要保証金、返却必要保証金とは算出が異なり、未約定の新規建注文中の建玉金額は含みません。</div>
        </caption-card>
      </el-row>
    </div>
  </div>
</template>

<script>
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'

export default {
  components: {
    captionCard
  },
  props: {
    form: { type: Object, required: true }
  },
  data() {
    return {
      creditReserveDetailVisible: false
    }
  },
  methods: {
    changeFontColor(settlement) {
      if (settlement > 0) {
        return 'font-color__plus _table__data_frame'
      } else {
        return '__right font-color__minus _table__data_frame'
      }
    },
    changeFontColorYen(yenActualDeposit) {
      if (yenActualDeposit > 0) {
        return 'font-color__plus _table__data_frame'
      } else {
        return '__right font-color__minus _table__data_frame'
      }
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
.__bold {
    font-weight: bold;
}
.__disable {
  background-color: #eeeeee;
}
._table__body__margin_deposit {
  width: 100%;
  border-collapse: collapse;
}
.__left__margin_deposit {
  border-left: 3px solid #eee;
}
.__bottom__margin_deposit {
  border-bottom: 3px solid #eee;
}
.__right__margin_deposit {
  border-right: 3px solid #eee;
}
.__top__margin_deposit {
  border-top: 3px solid #eee;
}
._separator_right {
  border-right: 3px solid #eee;
}
._table__header_light {
  background-color: #fff8d8;
  border: 1px solid #a7b1c3;
}
._separator_row {
  width:0.5rem
}
._border_colored_row {
  background-color: #f5f5f5;
}
.clickable:hover {
  cursor: pointer
}
._table__data_frame {
  border: 1px solid #a7b1c3;
  padding: 2px 14px 2px 14px;
}
</style>
