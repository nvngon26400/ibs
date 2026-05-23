<template>
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div v-if="isUpdate"
         class="caption_card"
    >
      <el-row>
        <el-col
          class="right-btn"
          style="right: 200px;"
        >
          <ifa-button
            id="btnShortPositionDividendAdjustDetail"
            text="売建配当調整金明細"
            class="right-btn"
            color="secondary"
            action-id="SUB0202_010302-02#A001"
            action-type="requestAction"
            @response-handler="ifaShortPositionDividendAdjustDetailInitializeA001"
          ></ifa-button>
        </el-col>
        <el-col
          class="right-btn"
        >
          <ifa-button
            id="btnUpdate"
            text="更新"
            icon="RefreshRight"
            action-id="SUB0202_010302-01#A001"
            action-type="requestAction"
            :request-model="getA001RequestModel"
            @response-handler="initializeA001($event)"
          ></ifa-button>
        </el-col>

      </el-row>
      <!-- 信用余力 -->
      <caption-card
        v-if="form.marginCallinfoList && form.marginCallinfoList.length"
        :auto-refrese="false"
        caption="追証情報"
        style="margin-top: 3rem;"
      >
        <el-row>
          <table
            class="_table__horizontal _table__body _table__body__margin_irregular_format"
            style="margin-bottom: 0.5rem;"
          >
            <tbody>
              <tr>
                <th class="_table__header __center __add_top__border __add_left__border __bold">解消期限/<br>入金・入庫計上日</th>
                <th class="_table__header __center __add_top__border __bold">当初追証額</th>
                <th class="_table__header __center __add_top__border __bold">追証発生日</th>
                <th class="_table__header __center __add_top__border __bold">預り金不足額</th>
                <th class="_table__header __center __add_top__border __bold">入金額</th>
                <th class="_table__header __center __add_top__border __add_right__border __bold">入庫額 及び 決済建玉充当額<br>（決済建玉金額×20%）</th>
              </tr>
              <tr v-for="(item, idx) in form.marginCallinfoList"
                  :key="idx"
                  :class="[idx % 2 === 1 ? '_border_colored_row' : '']"
              >
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __center __add_left__border"
                >{{ $_out(item.cancellationDeadlineRecordingDate) === '-' ? '-' : $_getFormattedDateValue(item.cancellationDeadlineRecordingDate, 'date8') + '(' + item.cancellationDeadlineRecordingDateDefiniteApproximate+ ')' }}</td>
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __right"
                >
                  {{ $_out($_withCommaInteger(item.initialMarginAmount)) }}
                </td>
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __center"
                >{{ $_out($_getFormattedDateValue(item.marginCallDate, 'date8')) }}</td>
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.depositShortage)) }}</td>
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __right"
                >{{ $_out($_withCommaInteger(item.depositAmount)) }}</td>
                <td v-if="idx <= form.marginCallinfoList.length - 1"
                    class="_table__data __right __add_right__border"
                >{{ $_out($_withCommaInteger(item.settlementOpenInterestYen)) }}</td>
              </tr>
              <tr v-for="(item, idx) in form.marginCallinfoList"
                  :key="idx"
              >
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="_table__data __center  __add_bottom__border __add_left__border _border_colored_row"
                >{{ $_out($_getFormattedDateValue(item.cancellationDeadlineRecordingDateSettlementDate, 'date8')) }}</td>
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="__add_top__border __add_left__border"
                ></td>
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="__add_top__border"
                ></td>
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="__add_top__border __add_right__border"
                ></td>
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="_table__data __right __add_bottom__border _border_colored_row"
                >{{ $_out($_withCommaInteger(form.deliveryDateDepositScheduleTotalAmount)) }}</td>
                <td v-if="idx === form.marginCallinfoList.length - 1"
                    class="__add_top__border __add_left__border"
                ></td>
              </tr>
            </tbody>
          </table>
        </el-row>

        <el-row style="margin-top: 1rem;">
          <!-- 信用建余力対象商品残高 -->
          <el-col :span="16">
            <table class="_table__body _table__bottom__space">
              <tbody>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追証ステータス</th>
                  <td class="_table__data __right">{{ $_out(form.marginStatus) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追加保証金未解消に伴う建玉強制返済予定日時</th>
                  <td class="_table__data __right">{{ $_getFormattedDateValue(form.plannedDateForcedRedemptionOpenInterest, 'date8') ? $_getFormattedDateValue(form.plannedDateForcedRedemptionOpenInterest, 'date8') + " 12:30以降" : '-' }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追加保証金を全て解消するための必要額{{ $_out(form.marginDepositScheduleDate) }}</th>
                  <td class="_table__data __right">{{ $_out($_withCommaInteger(form.amountrequiredtocancelallAdditionalsecuritydeposits)) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追証基準維持率</th>
                  <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.marginstandardmaintenanceRate,2)) === '-' ? '-': $_withCommaZeroPadding(form.marginstandardmaintenanceRate,2) + '%' }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追証金額</th>
                  <td class="_table__data __right">{{ $_out($_withCommaInteger(form.marginAmount)) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追証発生日</th>
                  <td class="_table__data __right">{{ $_out($_getFormattedDateValue(form.marginCallDate, 'date8')) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >追証解消期限</th>
                  <td class="_table__data __right">{{ $_out($_getFormattedDateValue(form.marginCancellationDeadline, 'date8')) }}</td>
                </tr>
                <tr>
                  <th
                    class="_table__header __left __bold"
                    style="width: 70%;"
                  >建玉強制返済執行期限</th>
                  <td class="_table__data __right">{{ $_getFormattedDateValue(form.deadlineforforcedrepaymentOfopeninterest, 'date8') ? $_getFormattedDateValue(form.deadlineforforcedrepaymentOfopeninterest, 'date8') + " 12:00" : '-' }}</td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>
      </caption-card>

      <!-- 表示制御：信用建余力対象 -->
      <el-row>
        <el-col :span="24">
          <div
            class="clickable"
            @click="creditReserveVisible = !creditReserveVisible"
          >
            <el-icon
              v-if="creditReserveVisible"
              class="header-icon"
            ><Remove></Remove></el-icon>
            <el-icon
              v-else
              class="header-icon"
            ><CirclePlus></CirclePlus></el-icon>
            <span class="list-font">信用余力サマリー情報</span>
          </div>
        </el-col>
      </el-row>

      <div v-if="creditReserveVisible === true">
        <el-row style="margin-top: 1rem;">
          <!-- 信用建余力対象商品残高 -->
          <el-col :span="10">
            <caption-card caption="信用建余力">
              <table class="_table__body">
                <tbody>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >信用建余力</th>
                    <td class="_table__data __right">{{ $_out($_withCommaInteger(0 > form.creditCapacity ? "0" : form.creditCapacity )) }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >現引可能額</th>
                    <td class="_table__data __right">{{ $_out($_withCommaInteger(form.cashOnDelivery)) }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >現物買付余力</th>
                    <td class="_table__data __right">{{ $_out($_withCommaInteger(form.buyingRemainingPower)) }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >出金・振替可能額</th>
                    <td class="_table__data __right">{{ $_out($_withCommaInteger(form.withdrawalCapacity)) }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >建玉総限度額</th>
                    <td class="_table__data __right">{{ $_out($_withCommaInteger(form.openInterestLimit)) ==='-' ? $_out($_withCommaInteger(form.openInterestLimit)): $_withCommaInteger(form.openInterestLimit) + '万円' }}</td>
                  </tr>
                </tbody>
              </table>
            </caption-card>
          </el-col>
        </el-row>

        <!-- 委託保証金率の推移 -->
        <el-row style="margin-top: 1rem;">
          <caption-card caption="委託保証金率の推移">
            <table
              class="_table__body__margin_irregular_format"
              style="margin-bottom: 0.5rem;"
            >
              <tbody>
                <tr>
                  <th></th>
                  <th class="_table__header __center __add_left__border __add_right__border __add_top__border __bold">前日委託保証金率</th>
                  <th colspan="3"></th>
                </tr>
                <tr>
                  <td class="__add_bottom__border"></td>
                  <td class="_table__data __right __add_left__border __add_right__border">{{ $_out(form.consignmentDeposit) === '-' ? '-' : $_withCommaZeroPadding(form.consignmentDeposit,2) + '%' }}</td>
                  <td class="__add_bottom__border"></td>
                  <td class="__add_bottom__border"></td>
                  <td class="__add_bottom__border"></td>
                </tr>
                <tr>
                  <td class="_table__header __center __add_left__border __bold">日付</td>
                  <td class="_table__header __center __bold">委託保証金率<br>（参考委託保証金率）</td>
                  <td class="_table__header __center __bold">建玉必要額</td>
                  <td class="_table__header __center __bold">追加保証金予定額</td>
                  <td class="_table__header __center __add_right__border __bold">預り金不足予定額</td>
                </tr>
                <tr v-for="(item, idx) in form.deliveryDateToFiveDaysLaterList"
                    :key="idx"
                    :class="[idx % 2 === 1 ? '_border_colored_row' : '']"
                >
                  <td v-if="idx != form.deliveryDateToFiveDaysLaterList.length - 1"
                      class="_table__data __center __add_left__border"
                  >{{ $_out($_getFormattedDateValue(item.settlementDateT0toT5, 'date8')) }}
                  </td>
                  <td v-else-if="idx === form.deliveryDateToFiveDaysLaterList.length - 1"
                      class="_table__data __center __add_bottom__border  __add_left__border"
                  >{{ $_out(item.settlementDateT0toT5) === '-' ? '-' : $_getFormattedDateValue(item.settlementDateT0toT5, 'date8') + '以降' }}</td>
                  <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(item.marginDepositRate9,2)) === '-' ? '-' : $_withCommaZeroPadding(item.marginDepositRate9,2) + '%' }}<br>{{ $_out($_withCommaZeroPadding(item.domesticReferenceMarginDeposit,2)) === '-' ? '-' : '(' + $_withCommaZeroPadding(item.domesticReferenceMarginDeposit,2) + '%)' }}</td>
                  <td class="_table__data __right">{{ $_out($_withCommaInteger(item.openInterestCostYen)) }}</td>
                  <td class="_table__data __right">{{ $_out($_withCommaInteger(item.additionalMarginPlannedAmount)) }}</td>
                  <td class="_table__data __right __add_right__border">{{ $_out($_withCommaInteger(item.shortageDepositDue)) }}</td>
                </tr>
              </tbody>
            </table>
            <!-- 注意事項 -->
            <div>※先日付における委託保証金率、追加保証金予定額、預り金不足予定額は、現在の委託保証金現金、代用有価証券評価額、評価損、決済損益、諸経費等、建玉の状況を元に算出した値です。株式等の時価変動及び諸経費等の追加により毎営業日変動いたしますのでご注意ください。 </div>
          </caption-card>
        </el-row>

        <el-row style="margin-top: 1rem;">
          <!-- 各種保証金率 -->
          <el-col :span="20">
            <caption-card caption="各種保証金率">
              <table class="_table__body _table__bottom__space">
                <tbody>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >新規建保証金率<br>（信用建余力の計算に適用）</th>
                    <td class="_table__data __right">{{ $_out(form.newOpenInterest) === '-' ? '-' : $_withCommaZeroPadding(form.newOpenInterest,2) + '%' }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >現金保証金率<br>（新規建に必要な保証金現金の計算に適用）</th>
                    <td class="_table__data __right">{{ $_out(form.chache) === '-' ? '-' : $_withCommaZeroPadding(form.chache,2) + '%' }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >現物買付保証金率<br>（現物の信用建余力の計算に適用）</th>
                    <td class="_table__data __right">{{ $_out(form.inKindPurchase) === '-' ? '-' : $_withCommaZeroPadding(form.inKindPurchase,2) + '%' }}</td>
                  </tr>
                  <tr>
                    <th
                      class="_table__header __left __bold"
                      style="width: 50%;"
                    >出金・振替保証金率<br>（出金・振替可能額の計算に適用）</th>
                    <td class="_table__data __right">{{ $_out(form.withdrawTransfer) === '-' ? '-' : $_withCommaZeroPadding(form.withdrawTransfer,2) + '%' }}</td>
                  </tr>
                </tbody>
              </table>
            </caption-card>
          </el-col>
        </el-row>
      </div>
      <ifa-margin-power-domestic-sub-credit-reserve
        style="margin-top:1rem"
        :form="form"
      ></ifa-margin-power-domestic-sub-credit-reserve>
      <!-- ダイアログ -->
      <ifa-short-position-dividend-adjust-detail
        ref="ifaShortPositionDividendAdjustDetail"
        :form-data="ifaShortPositionDividendAdjustDetailFormModel"
        :is-visible="isDialogVisible"
        @close-modal="handleClose"
      >
      </ifa-short-position-dividend-adjust-detail>
    </div>
    <ifa-requester
      id="IfaMarginPowerDomesticInitializeA001"
      action-id="SUB0202_010302-01#A001"
      :request-model="getA001RequestModel"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
      @response-error-handler="responseErrorHandlerInitializeA001($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaMarginPowerDomesticSubCreditReserve from './components/IfaMarginPowerDomesticSubCreditReserve'
import IfaShortPositionDividendAdjustDetail from './components/IfaShortPositionDividendAdjustDetail'
import captionCard from '@/views/brokerageMenu/customerMenu/components/captionCard'
import { IfaMarginPowerDomesticA001RequestModel } from './js/IfaMarginPowerDomesticA001RequestModel.js'
import { IfaMarginPowerDomesticFormModel } from './js/IfaMarginPowerDomesticFormModel.js'

export default {
  components: {
    IfaMarginPowerDomesticSubCreditReserve,
    IfaShortPositionDividendAdjustDetail,
    captionCard
  },
  emits: ['initializeError'],
  data() {
    return {
      ifaShortPositionDividendAdjustDetailFormModel: {},
      creditReserveVisible: false,
      isDialogVisible: false,
      isUpdate: true,
      form: new IfaMarginPowerDomesticFormModel()
    }
  },
  computed: {
    getA001RequestModel() {
      return new IfaMarginPowerDomesticA001RequestModel(
        {
          butenCode: this.$store.getters.customerInfo.butenCode,
          accountNumber: this.$store.getters.customerInfo.accountNumber
        }
      )
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaMarginPowerDomesticInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(data) {
      if (data.errorLevel === 0) {
        const defaultValue = this.form.deliveryDateToFiveDaysLaterList[0].shortageDepositDue
        Object.assign(this.form, data.dataList[0])

        for (let i = 0; i < this.form.deliveryDateToFiveDaysLaterList.length; i++) {
          // オブジェクトの配列の中から値がnullのshortageDepositDueを取得
          if (this.form.deliveryDateToFiveDaysLaterList[i].shortageDepositDue === null || this.form.deliveryDateToFiveDaysLaterList[i].shortageDepositDue === '') {
            // 初期値を代入
            this.form.deliveryDateToFiveDaysLaterList[i].shortageDepositDue = defaultValue
          }
        }
      }
    },
    responseErrorHandlerInitializeA001(data) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: data.message
      }
      this.$emit('initializeError', errorInfo)
    },
    // 更新ボタン
    initializeA001(data) {
      // APIレスポンス正常時業務処理（必須）
      Object.assign(this.form, data.dataList[0])
      this.update()
    },
    update() {
      this.isUpdate = false
      this.$nextTick(() => (this.isUpdate = true))
    },
    // 売建配当調整金明細ボタン
    ifaShortPositionDividendAdjustDetailInitializeA001(response) {
      this.ifaShortPositionDividendAdjustDetailFormModel = Object.assign(this.ifaShortPositionDividendAdjustDetailFormModel, response.dataList[0])
      this.$refs.ifaShortPositionDividendAdjustDetail.onShow()
      this.isDialogVisible = true
    },
    handleClose() {
      this.isDialogVisible = false
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
  @import "@/styles/table.scss";
.__bold {
    font-weight: bold;
}
._table__bottom__space {
  margin-bottom:1rem
}
._table__body__margin_irregular_format {
  width: 100%;
  border-collapse: collapse;
}
.__add_left__border {
  border-left: 3px solid #eee;
}
.__add_bottom__border {
  border-bottom: 3px solid #eee;
}
.__add_right__border {
  border-right: 3px solid #eee;
}
.__add_top__border {
  border-top: 3px solid #eee;
}
._border_colored_row {
  background-color: #f5f5f5;
}
.clickable:hover {
  cursor: pointer
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
</style>
