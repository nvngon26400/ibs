<template>
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
      >
        <el-col style="margin-top: 1rem">
          <span style="font-weight: bold; padding-left: 1rem;">保証金振替指示（預り金⇔保証金）</span>
        </el-col>
        <el-col style="margin-top: 1rem">
          <table
            class="_table__body"
            style="width:700px"
          >
            <tbody>
              <tr>
                <th
                  class="_table__header __left"
                >
                  <el-form-item
                    label="口座選択"
                    required
                  ></el-form-item></th>
                <td
                  class="__left"
                >
                  <ifa-input-radio
                    v-model="form.accountSelect"
                    prop="accountSelect"
                    code-list-id="DEPOSIT_TRANSFER_TYPE"
                    :disp-pattern="2"
                    :select-pattern="1"
                    style="padding: 0.2rem 6rem"
                    @change="accountSelectionA002($event)"
                  ></ifa-input-radio>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>

        <el-col style="margin-top: 1rem">
          <table
            class="_table__body"
            style="width:700px"
          >
            <tbody>
              <tr>
                <th
                  class="_table__header __left"
                >指示可能金額</th>
                <td
                  class="_table__data __right"
                  style="padding-right: 150px;"
                >
                  {{ $_withCommaZeroPadding($_out(form.destinationAbleAmount), 2) }}USD</td>
              </tr>
              <tr>
                <th
                  class="_table__header __left"
                ><el-form-item
                  label="指示金額"
                  required
                ></el-form-item></th>
                <td class="_table__data __right">
                  <ifa-input-amount
                    v-model="form.destinationAmount"
                    prop="destinationAmount"
                    :domain="IfaUsdCurrency142DomainModel"
                    :min="0.01"
                    :max="form.destinationAbleAmount"
                    :initial-step="0.01"
                    digit="2"
                    unit="USD"
                    size="small"
                    support
                    @change="displayBtnDisable = false"
                    @keydown.enter.prevent
                  ></ifa-input-amount>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>
        <div style="display: flex; width: 700px;">
          <el-col
            style="margin-top: 2rem"
            :span="20"
          >
            <span style="font-weight: bold; padding-left: 1rem;">振替結果（予定）</span>
          </el-col>
          <el-col
            style="margin-top: 1rem"
            :span="4"
          >
            <ifa-button
              id="btnDisplayResult"
              text="結果を表示"
              color="primary"
              :form="formRef"
              :disabled="displayBtnDisable"
              action-id="SUB0202_0304-01_1#A003"
              action-type="requestAction"
              :request-model="a003RequestModel"
              @response-handler="displayResultA003($event)"
            >
            </ifa-button>
          </el-col>
        </div>
        <el-col style="margin-top: 0.5rem">
          <table
            class="_table__body"
            style="width:700px"
          >
            <tbody>
              <tr>
                <th
                  class="_table__header __center"
                ></th>
                <th class="_table__header __center">振替指示前</th>
                <th class="_table__header __center">振替指示後</th>
              </tr>
              <tr>
                <th
                  class="_table__header __left"
                ><el-form-item
                  label="信用建余力	"
                ></el-form-item></th>
                <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginPositionPowerBefore), 2) }}USD</td>
                <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginPositionPowerAfter), 2) }}USD</td>
              </tr>
              <tr>
                <th
                  class="_table__header __left"
                ><el-form-item
                  label="委託保証金率	"
                ></el-form-item></th>
                <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginDepositRateBefore), 2) }}%</td>
                <td class="_table__data __right">{{ $_withCommaZeroPadding($_out(form.marginDepositRateAfter), 2) }}%</td>
              </tr>
            </tbody>
          </table>
        </el-col>
        <el-col style="margin-top: 2rem; margin-bottom: 1rem">
          <ifa-button
            id="btnTransferConfirm"
            text="振替確認"
            color="primary"
            :form="formRef"
            :disabled="confirmBtnDisable"
            action-id="SUB0202_0304-01_1#A004"
            action-type="requestAction"
            :request-model="a004RequestModel"
            @response-handler="transferConfirmA004($event)"
          >
          </ifa-button>
        </el-col>
      </el-form>
    </div>
    <!--保証金振替確認-->
    <ifa-foreign-margin-deposit-transfer-confirm
      ref="IfaForeignMarginDepositTransferConfirm"
      :is-visible="confirmIsVisible"
      :param="form"
      @close-modal="handleCloseModal"
      @guarantee-transfer-finish="handleGuaranteeTransferFinish"
    >
    </ifa-foreign-margin-deposit-transfer-confirm>

    <!--保証金振替完了-->
    <ifa-foreign-margin-deposit-transfer-complete
      :is-visible="dialogFinish"
      :param="confirmResponseData"
      @back-order="handlebackOrder"
    >
    </ifa-foreign-margin-deposit-transfer-complete>

    <ifa-requester
      id="IfaForeignMarginDepositTransferInputInitializeA001"
      action-id="SUB0202_0304-01_1#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
      @response-error-handler="errorHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaForeignMarginDepositTransferInputAccountSelectionA002"
      action-id="SUB0202_0304-01_1#A002"
      action-type="requestAction"
      :request-model="a002RequestModel"
      @response-handler="accountSelectionA002Res($event)"
    ></ifa-requester>
  </div>
</template>
<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import { getMessage } from '@/utils/errorHandler'
import IfaUsdCurrency142DomainModel from '@/resource/domain/IfaUsdCurrency142DomainModel.json'
import IfaForeignMarginDepositTransferConfirm from './IfaForeignMarginDepositTransferConfirm.vue'
import IfaForeignMarginDepositTransferComplete from './IfaForeignMarginDepositTransferComplete.vue'
import { IfaForeignMarginDepositTransferInputFormModel } from './js/IfaForeignMarginDepositTransferInputFormModel'
import { IfaForeignMarginDepositTransferInputA002RequestModel } from './js/IfaForeignMarginDepositTransferInputA002RequestModel'
import { IfaForeignMarginDepositTransferInputA003RequestModel } from './js/IfaForeignMarginDepositTransferInputA003RequestModel'
import { IfaForeignMarginDepositTransferInputA004RequestModel } from './js/IfaForeignMarginDepositTransferInputA004RequestModel'

export default {
  components: {
    screenTitle,
    IfaForeignMarginDepositTransferConfirm,
    IfaForeignMarginDepositTransferComplete
  },
  emits: ['initializeError', 'update-customer-portal'],
  data() {
    return {
      IfaUsdCurrency142DomainModel,
      confirmIsVisible: false,
      dialogFinish: false,
      displayBtnDisable: true,
      form: new IfaForeignMarginDepositTransferInputFormModel(),
      formRef: {},
      rules: {
        destinationAmount: [{ required: true, trigger: 'blur', validator: this.destinationAmountValidator }],
        accountSelect: [{ required: true, trigger: 'blur', validator: this.accountSelectValidator }]
      },
      confirmResponseData: {}
    }
  },
  computed: {
    a002RequestModel() {
      return new IfaForeignMarginDepositTransferInputA002RequestModel(this.form)
    },
    a003RequestModel() {
      return new IfaForeignMarginDepositTransferInputA003RequestModel(this.form)
    },
    a004RequestModel() {
      return new IfaForeignMarginDepositTransferInputA004RequestModel(this.form)
    },
    confirmBtnDisable() {
      return this.form.destinationAmount === ''
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    onShow() {
      this.formRef.clearValidate()
      this.displayBtnDisable = true
      this.form = new IfaForeignMarginDepositTransferInputFormModel()
      this.$nextTick(() => {
        document.getElementById('IfaForeignMarginDepositTransferInputInitializeA001').click()
      })
    },
    initializeA001(response) {
      Object.assign(this.form, response.dataList[0])
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    accountSelectionA002() {
      document.getElementById('IfaForeignMarginDepositTransferInputAccountSelectionA002').click()
      if (this.form.destinationAmount !== '') {
        this.displayBtnDisable = false
      }
    },
    accountSelectionA002Res(response) {
      Object.assign(this.form, response.dataList[0])
    },
    displayResultA003(response) {
      Object.assign(this.form, response.dataList[0])
      if (response.dataList[0].marginPositionPowerBefore === '') {
        this.form.marginPositionPowerBefore = '--'
      }
      if (response.dataList[0].marginPositionPowerAfter === '') {
        this.form.marginPositionPowerAfter = '--'
      }
      if (response.dataList[0].marginDepositRateBefore === null || response.dataList[0].marginDepositRateBefore === '') {
        this.form.marginDepositRateBefore = '--'
      }
      if (response.dataList[0].marginDepositRateAfter === null || response.dataList[0].marginDepositRateAfter === '') {
        this.form.marginDepositRateAfter = '--'
      }
      this.displayBtnDisable = true
    },
    transferConfirmA004(response) {
      Object.assign(this.form, response.dataList[0])
      this.confirmIsVisible = true
      this.$refs['IfaForeignMarginDepositTransferConfirm'].onShow()
    },
    // 保証金振替入力画面に遷移
    handleCloseModal() {
      this.confirmIsVisible = false
    },
    // 保証金振替完了画面に遷移
    handleGuaranteeTransferFinish(response) {
      this.confirmResponseData = response
      this.confirmIsVisible = false
      this.dialogFinish = true
    },
    // 注文画面へ遷移
    handlebackOrder() {
      this.dialogFinish = false
      this.onShow()
      this.$nextTick(() => {
        this.$emit('update-customer-portal')
      })
    },
    destinationAmountValidator(rule, value, callback) {
      if (parseFloat(this.form.destinationAbleAmount) <= 0) {
        callback(getMessage('errors.frs.instructableAmount.nothing'))
      }
      if (parseFloat(value) > parseFloat(this.form.destinationAbleAmount)) {
        callback(getMessage('errors.frs.maxValue.exceeded', ['指示金額', '指示可能金額']))
      }
      if (this.form.destinationAmount === '0' || this.form.destinationAmount === '') {
        callback(getMessage('errors.required', ['指示金額']))
      }
      // OK
      callback()
    },
    accountSelectValidator(rule, value, callback) {
      if (this.form.accountSelect === '') {
        return
      }
      // OK
      callback()
    }
  }
}
</script>
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
:deep(.el-form-item__label) {
  margin: 0.3rem 0 0;
  justify-content: flex-start;
  align-items: center;
  font-weight: normal;
  text-align: right;
  width: 135px;
}
:deep(.el-form-item__error) {
  position: relative !important;
  left: 50px;
}

._table__header {
  width: 250px;
  padding: 5px;
}
.caption_card {
  padding: 5px 15px 20px;
}
:deep(.el-form-item__content) {
  text-align: right;
  width: 135px;
}
:deep(.ifa-input_base__wrapper) {
  width: 350px;
}
:deep(.price_input) {
  width: 220px !important;
}
</style>
