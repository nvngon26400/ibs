<template>
  <el-dialog
    v-model="vmIsVisible"
    title="米株信用代用振替確認"
    class="foreign_margin_coll"
    width="1170px"
    :center="true"
    :show-close="false"
    :before-close="backA002"
    :close-on-click-modal="false"
    @open="onShow"
  >
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          name="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col style="margin-left: 6.7px;">
        <ifa-message-area
          :main-messages="['振替指示はまだ完了していません。画面下の振替指示ボタンを押下してください。']"
        ></ifa-message-area>
      </el-col>
    </el-row>

    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>{{ accountNumber }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="font-size: 20px; padding-top: 0.2rem;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span style="position: relative; top: 3px;">
          <el-icon v-if="customerInfo().corporationType === '0'"><Avatar></Avatar></el-icon>
          <el-icon v-if="customerInfo().corporationType === '1'"><OfficeBuilding></OfficeBuilding></el-icon>
        </span>
        <span>{{ customerName }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo().noticeInfoLevel"
          :customer-code="customerInfo().customerCode"
          :buten-code="customerInfo().butenCode"
          :account-number="customerInfo().accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        style="margin-top: 2rem"
        :span="22"
        :offset="1"
      >
        <span style="font-weight: bold;">振替結果（予定）</span>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 1rem"
      >
        <table
          class="_table__body"
          style="width:600px"
        >
          <tbody>
            <tr>
              <th
                class="_table__header __center"
                style="width:200px"
              ></th>
              <th
                class="_table__header __center"
                style="width:200px"
              >振替指示前</th>
              <th
                class="_table__header __center"
                style=""
              >振替指示後</th>
            </tr>
            <tr>
              <th
                class="_table__header __left"
              >信用建余力
              </th>
              <td class="_table__data __right">{{ $_out($_withCommaZeroPadding(form.marginPositionPowerBefore, 2)) }} <span class="fix_width">USD</span></td>
              <td
                class="_table__data __right"
                style="background: #FDF3ED;"
              >{{ $_out($_withCommaZeroPadding(form.marginPositionPowerAfter, 2)) }} <span class="fix_width">USD</span></td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
              >委託保証金率
              </th>
              <td class="_table__data __right">{{ marginDepositRateBefore }} <span class="fix_width">%</span></td>
              <td
                class="_table__data __right"
                style="background: #FDF3ED;"
              >{{ marginDepositRateAfter }} <span class="fix_width">%</span></td>
            </tr>
            <tr>
              <th
                class="_table__header __left"
              >代用評価額
              </th>
              <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.collateralValuationBefore, 2)) }} <span class="fix_width">USD</span></td>
              <td
                class="_table__data __right"
                style="background: #FDF3ED;"
              >{{ $_withCommaZeroPadding($_out(form.collateralValuationAfter, 2)) }} <span class="fix_width">USD</span></td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>
    <el-row v-if="formData.transferClassification === 'SECURITIES_DEPOSIT'">
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 1rem"
      >
        <span style="font-weight: bold;">振替指示　保護　→　代用</span>
      </el-col>
    </el-row>
    <el-row v-if="formData.transferClassification === 'SECURITIES_DEPOSIT'">
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 0.5rem"
      >
        <table
          class="_table__body"
          style="width:100%"
        >
          <thead>
            <tr>
              <th
                class="_table__header __center"
                style="width:400px"
              >銘柄名</th>
              <th
                class="_table__header __center"
                style="width:100px"
              >代用掛目</th>
              <th
                class="_table__header __center"
                style="width:100px"
              >預り区分</th>
              <th
                class="_table__header __center"
                style="width:200px"
              >振替数量<br>（買付受渡未到来）</th>
              <th
                class="_table__header __center"
                style=""
              >評価単価<br>代用評価額</th>
            </tr>
          </thead>
          <tbody
            v-for="(item, idx) in form.protectionList"
            :key="idx"
          >
            <tr>
              <td class="_table__data _left">{{ $_out(item.collateralDepositListBrandName) }}<br>{{ $_out(item.collateralDepositListBrandCode) }}</td>
              <td class="_table__data __right">{{ collateralAssessment(idx) }}%</td>
              <td class="_table__data __center">{{ $_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.specificAccountCode) }}</td>
              <td class="_table__data __right">{{ $_withCommaInteger($_out(item.transferQuantity)) }} 株<br>({{ $_withCommaInteger($_out(item.notReceivedQuantity)) }} 株)</td>
              <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(item.valuatePrice), 4) }} USD<br>{{ $_withCommaZeroPadding($_out(item.collateralValuation), 2) }} USD</td>
            </tr>
          </tbody>
          <tbody>
            <tr>
              <td
                class="__center __total"
              >合計</td>
              <td
                class="__left __total"
                colspan="3"
              ></td>
              <td class="__right __total">
                <span> {{ totalAmount }} USD</span></td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>

    <el-row v-if="formData.transferClassification === 'SECURITIES_WITHDRAWAL'">
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 1rem"
      >
        <span style="font-weight: bold;">振替指示　代用　→　保護</span>
      </el-col>
    </el-row>
    <el-row v-if="formData.transferClassification === 'SECURITIES_WITHDRAWAL'">
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 0.5rem"
      >
        <table
          class="_table__body"
          style="width:100%"
        >
          <thead>
            <tr>
              <th
                class="_table__header __center"
                style="width:400px"
              >銘柄名</th>
              <th
                class="_table__header __center"
                style="width:100px"
              >代用掛目</th>
              <th
                class="_table__header __center"
                style="width:100px"
              >預り区分</th>
              <th
                class="_table__header __center"
                style="width:200px"
              >振替数量<br>（買付受渡未到来）</th>
              <th
                class="_table__header __center"
                style=""
              >評価単価<br>代用評価額</th>
            </tr>
          </thead>
          <tbody
            v-for="(item, idx) in form.substitutionList"
            :key="idx"
          >
            <tr>
              <td class="_table__data _left">{{ $_out(item.collateralDepositListBrandName) }}<br>{{ $_out(item.collateralDepositListBrandCode) }}</td>
              <td class="_table__data __right">{{ collateralAssessment(idx) }}%</td>
              <td class="_table__data __center">{{ $_getCodeValue('FOREIGN_DEPOSIT_TYPE', 1, item.specificAccountCode) }}</td>
              <td class="_table__data __right">{{ $_withCommaInteger($_out(item.transferQuantity)) }} 株<br>({{ $_withCommaInteger($_out(item.notReceivedQuantity)) }} 株)</td>
              <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(item.valuatePrice), 4) }} USD<br>{{ $_withCommaZeroPadding($_out(item.collateralValuation), 2) }} USD</td>
            </tr>
          </tbody>
          <tbody>
            <tr>
              <td
                class="__center __total"
              >合計</td>
              <td
                class="__left __total"
                colspan="3"
              ></td>
              <td class="__right __total">
                <span> {{ totalAmount }} USD</span></td>
            </tr>
          </tbody>
        </table>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="margin-top: 2rem; margin-bottom: 1rem"
      >
        <ifa-button
          id="btnOrderRegister"
          name="btnOrderRegister"
          text="振替指示"
          color="primary"
          style="padding-left: 0;"
          :request-model="A001RequestModel"
          action-id="SUB0202_0305-01_2#A001"
          action-type="requestAction"
          :disabled="btnTransferInstructionDisabled"
          @response-handler="responseHandlerTransferInstructionA001($event)"
        >
        </ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>
<script>
import { BigNumber } from 'bignumber.js'
import { useVModel } from 'vue-composable'
import { IfaForeignMarginCollateralTransferConfirmA001RequestModel } from './js/IfaForeignMarginCollateralTransferConfirmA001RequestModel.js'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaForeignMarginCollateralTransferConfirmFormModel } from './js/IfaForeignMarginCollateralTransferConfirmFormModel.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true }
  },
  emits: ['close-modal', 'guarantee-transfer-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignMarginCollateralTransferConfirmFormModel(),
      btnTransferInstructionDisabled: false // 振替指示ボタンの制御。ボタン非活性の場合true
    }
  },
  computed: {
    customerName() {
      return this.customerInfo().customerNameKanji + '（' + this.customerInfo().customerNameKana + '）'
    },
    accountNumber() {
      return this.customerInfo().butenCode + '-' + this.customerInfo().accountNumber
    },
    A001RequestModel() {
      return new IfaForeignMarginCollateralTransferConfirmA001RequestModel(this.formData)
    },
    totalAmount() {
      let total = 0
      if (this.formData.collateralSecurityTransferInfo) {
        for (let i = 0; i < this.formData.collateralSecurityTransferInfo.length; i++) {
          total = (new BigNumber(total)).plus(new BigNumber(this.formData.collateralSecurityTransferInfo[i].collateralValuation))
          if (!this.formData.collateralSecurityTransferInfo[i].collateralValuation) {
            total = '-'
            break
          }
        }
      }
      return this.$_withCommaZeroPadding(this.$_out(total), 2)
    },
    marginDepositRateBefore() {
      if (this.form.marginDepositRateBefore === null) {
        // NULL値が返ってきた場合
        return '--'
      }
      return this.$_withCommaZeroPadding(this.$_out(this.form.marginDepositRateBefore), 2)
    },
    marginDepositRateAfter() {
      if (this.form.marginDepositRateAfter === null) {
        // NULL値が返ってきた場合
        return '--'
      }
      return this.$_withCommaZeroPadding(this.$_out(this.form.marginDepositRateAfter), 2)
    }
  },
  methods: {
    onShow() {
      // 入力画面から受け取ったデータをformにセット
      this.form.marginPositionPowerBefore = this.formData.currentPower.marginBuyingPower
      this.form.marginDepositRateBefore = this.formData.currentPower.depositRate
      this.form.collateralValuationBefore = this.formData.currentPower.totalCollateralValue
      this.form.marginPositionPowerAfter = this.formData.afterPower.marginBuyingPower
      this.form.marginDepositRateAfter = this.formData.afterPower.depositRate
      this.form.collateralValuationAfter = this.formData.afterPower.totalCollateralValue
      this.form.protectionList = this.formData.collateralSecurityTransferInfo
      this.form.substitutionList = this.formData.collateralSecurityTransferInfo
    },
    // 戻るボタン
    backA002() {
      this.$emit('close-modal')
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    responseHandlerTransferInstructionA001(data) {
      if (data.dataList.length !== 0) {
        // A001レスポンスを入力画面経由で完了画面へ渡す
        this.$emit('guarantee-transfer-finish', data.dataList[0])
      }
    },
    collateralAssessment(idx) {
      const collateralAssessment = this.formData.collateralSecurityTransferInfo?.[idx]?.collateralAssessment
      if (collateralAssessment === null || collateralAssessment === undefined) {
        // NULL値が返ってきた場合
        return '--'
      }
      return this.$_withCommaInteger(this.$_out(collateralAssessment))
    }
  }
}

</script>
<style lang="scss">
.foreign_margin_coll {
  .el-dialog__header {
      font-weight: bold;
      padding-top: 40px;
  }
}
</style>

<style lang="scss" scoped>
@import "@/styles/table.scss";
@import "@/styles/orderStatusList.scss";
@import "@/styles/variables.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
._black_s {
  font-size: 14px;
  color: #606266;
  padding-bottom: 0.5rem;
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
:deep(.el-table) .__right {
  text-align: right;
}
:deep(.el-form-item__label) {
  font-weight: normal;
  text-align: right;
  width: 135px;
}
.caption_card {
  padding: 5px 15px 20px 15px;
}
:deep(.el-form-item__content) {
  text-align: right;
  width: 135px;
}
.error-message {
  margin: 0.5rem 0;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.__total{
  padding: 2px 14px 2px 14px;
}
.fix_width {
  width: 27.9px;
  display: inline-block;
  text-align: left;
}
</style>
