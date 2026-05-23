<template>
  <div>
    <screen-title :text="form.screenTitle"></screen-title>
    <div class="caption_card">
      <el-row>
        <ul
          type="disc"
          class="error-message"
        >
          <span>{{ form.priorityTransferMsg }}
          </span>
        </ul>
      </el-row>
      <el-form
        ref="form"
        :model="form"
      >
        <el-col style="margin-top: 0.5rem">
          <table
            class="_table__body"
            style="width:1150px"
          >
            <tbody>
              <tr :style="changeColor1 ? 'background-color: #DDEBF7' : ''">
                <th
                  class="_table__header __left"
                  style="width:770px"
                >
                  <div>
                    <span class="explain_content">信用新規建(新規買・新規売)の注文時の自動振替設定</span>
                    <span class="red_content">  ※複数選択可</span>
                  </div>
                  <div>新規建注文時に信用建余力が不足している場合、自動で振替を行う対象を選択してください。</div>
                </th>
                <td
                  class="_table__data"
                  style="width:380px"
                >
                  <ifa-input-check
                    v-model="form.marginBuyingPowerShortfallSecurities"
                    :code-list-id="'MARGIN_BUYING_POWER_SHORTFALL_SECURITIES'"
                    :select-pattern="1"
                    :disp-pattern="1"
                    :label-class="labelClass"
                    @change="changeSelectCheck1"
                  >
                  </ifa-input-check>
                  <ifa-input-check
                    v-model="form.marginBuyingPowerShortfallCash"
                    :code-list-id="'MARGIN_BUYING_POWER_SHORTFALL_CASH'"
                    :select-pattern="1"
                    :disp-pattern="1"
                    :label-class="labelClass2"
                    @change="changeSelectCheck1"
                  >
                  </ifa-input-check>
                </td>
              </tr>
              <tr :style="changeColor2 ? 'background-color: #DDEBF7' : ''">
                <th
                  class="_table__header __left"
                  style="width:770px"
                >
                  <div>
                    <span class="explain_content">追加証拠金（追証）・新規建不足発生時の自動振替設定</span>
                    <span class="red_content">  ※複数選択可</span>
                  </div>
                  <div>追加証拠金（追証）が発生した場合および信用建注文発注時に拘束していた余力を超えて約定したことにより保証金不足（新規建不足）が発生した場合、自動で振替を行う対象を選択してください。</div>
                </th>
                <td
                  class="_table__data"
                  style="width:380px; margin-left: 5px;"
                >
                  <ifa-input-check
                    v-model="form.marginShortfallSecurities"
                    :code-list-id="'MARGIN_SHORTFALL_SECURITIES'"
                    :select-pattern="1"
                    :disp-pattern="1"
                    :label-class="labelClass"
                    @change="changeSelectCheck2"
                  >
                  </ifa-input-check>
                  <ifa-input-check
                    v-model="form.marginShortfallCash"
                    :code-list-id="'MARGIN_SHORTFALL_CASH'"
                    :select-pattern="1"
                    :disp-pattern="1"
                    class="custom-margin"
                    :label-class="labelClass2"
                    @change="changeSelectCheck2"
                  >
                  </ifa-input-check>
                </td>
              </tr>
              <tr :style="changeColor3 ? 'background-color: #DDEBF7' : ''">
                <th
                  class="_table__header __left"
                  style="width:770px; padding-top: 0.8rem; padding-bottom: 0.8rem;"
                >
                  <div>
                    <span class="explain_content">米国現物株買付時の代用自動振替設定</span>
                  </div>
                  <div>米国株式を特定預り・一般預りで現物買付を行った場合に、自動的に代用預りに振り替える設定ができます（代用適格有価証券に限ります）。</div>
                  <div>なお、現物買付を行う銘柄を、当該買付注文の約定時点で同一の預り区分（特定預りまたは一般預り）で保有されており、かつ、保護預りとなっている場合には、代用預りへの自動振替は行われず全て（既存保有分および追加買付分）保護預りとなりますのでご注意ください。</div>
                </th>
                <td
                  class="_table__data"
                  style="width:380px"
                >
                  <ifa-input-radio
                    v-model="form.depositType"
                    :code-list-id="'DEPOSIT_TYPE'"
                    :select-pattern="1"
                    :disp-pattern="2"
                    :label-class="labelClass3"
                    @change="changeSelectCheck3"
                  >
                  </ifa-input-radio>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>

        <div>
          <el-col style="margin-top: 2rem; margin-bottom: 1rem; margin-left: -4px;">
            <ifa-button
              id="btnSettingConfirm"
              name="btnSettingConfirm"
              text="設定確認"
              color="primary"
              :disabled="btnDisabled"
              :request-model="IfaForeignMarginAutoTransferSettingInputA002RequestModel"
              action-id="SUB0202_0306-01_1#A002"
              action-type="requestAction"
              @response-handler="responseHandlersettingConfirmA002($event)"
            >
            </ifa-button>
          </el-col>
        </div>
      </el-form>
    </div>
    <!--確認-->
    <ifa-foreign-margin-auto-transfer-setting-confirm
      :is-visible="confirmIsVisible"
      :form="confirmForm"
      :customer-info="customerInfo"
      :auto-transfer-setting-info="autoTransferSettingInfo"
      @close-modal="handleCloseModal"
      @transfer-finish="handleTransferFinish"
    >
    </ifa-foreign-margin-auto-transfer-setting-confirm>

    <ifa-foreign-margin-auto-transfer-setting-complete
      :is-visible="dialogFinish"
      :form="completeForm"
      :customer-info="customerInfo"
      @back-order="handlebackOrder"
    >
    </ifa-foreign-margin-auto-transfer-setting-complete>
  </div>
  <ifa-requester
    id="IfaForeignMarginAutoTransferSettingInputA001"
    action-id="SUB0202_0306-01_1#A001"
    action-type="requestAction"
    @response-handler="responseHandlerInitializeA001($event)"
    @response-error-handler="errorHandlerInitializeA001($event)"
  ></ifa-requester>
</template>
<script>
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
import IfaForeignMarginAutoTransferSettingConfirm from '@/views/brokerageMenu/customerMenu/foreignStock/autoTransferSetting/IfaForeignMarginAutoTransferSettingConfirm'
import IfaForeignMarginAutoTransferSettingComplete from '@/views/brokerageMenu/customerMenu/foreignStock/autoTransferSetting/IfaForeignMarginAutoTransferSettingComplete'
import { IfaForeignMarginAutoTransferSettingInputFormModel } from './js/IfaForeignMarginAutoTransferSettingInputFormModel'
import { IfaForeignMarginAutoTransferSettingInputA002RequestModel } from './js/IfaForeignMarginAutoTransferSettingInputA002RequestModel'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    screenTitle,
    IfaForeignMarginAutoTransferSettingConfirm,
    IfaForeignMarginAutoTransferSettingComplete
  },
  emits: ['initializeError', 'update-customer-portal'],
  data() {
    return {
      labelClass: 'labelClass',
      labelClass2: 'labelClass2',
      labelClass3: 'labelClass3',
      changeColor1: false,
      changeColor2: false,
      changeColor3: false,
      confirmIsVisible: false,
      dialogFinish: false,
      // 自動振替設定情報
      autoTransferSettingInfo: [],
      form: new IfaForeignMarginAutoTransferSettingInputFormModel(),
      confirmForm: {},
      completeForm: {}
    }
  },
  computed: {
    btnDisabled() {
      return !this.changeColor1 && !this.changeColor2 && !this.changeColor3
    },
    IfaForeignMarginAutoTransferSettingInputA002RequestModel() {
      return new IfaForeignMarginAutoTransferSettingInputA002RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('IfaForeignMarginAutoTransferSettingInputA001').click()
      })
    },
    changeSelectCheck1() {
      this.changeColor1 =
      (this.form.marginBuyingPowerShortfallSecurities !== this.form.hiddenMarginBuyingPowerShortfallSecurities) ||
      (this.form.marginBuyingPowerShortfallCash !== this.form.hiddenMarginBuyingPowerShortfallCash)
    },
    changeSelectCheck2() {
      this.changeColor2 =
      (this.form.marginShortfallSecurities !== this.form.hiddenMarginShortfallSecurities) ||
      (this.form.marginShortfallCash !== this.form.hiddenMarginShortfallCash)
    },
    changeSelectCheck3() {
      this.changeColor3 = (this.form.depositType !== this.form.hiddenAutoTransferSettingInfoDepositType)
    },
    responseHandlerInitializeA001(data) {
      if (data.dataList.length === 0) {
        notifyMessage(-1, data.message, this.$store.getters.pageInfo.label)
        const errorInfo = {
          title: this.form.screenTitle,
          message: data.message
        }
        this.$emit('initializeError', errorInfo)
      } else {
        this.autoTransferSettingInfo = data.dataList[0]
        this.form.hiddenMarginBuyingPowerShortfallCash = data.dataList[0].marginBuyingPowerShortfallCash
        this.form.hiddenMarginBuyingPowerShortfallSecurities = data.dataList[0].marginBuyingPowerShortfallSecurities
        this.form.hiddenMarginShortfallCash = data.dataList[0].marginShortfallCash
        this.form.hiddenMarginShortfallSecurities = data.dataList[0].marginShortfallSecurities
        this.form.hiddenAutoTransferSettingInfoDepositType = data.dataList[0].depositType

        this.form.marginBuyingPowerShortfallSecurities = this.form.hiddenMarginBuyingPowerShortfallSecurities
        this.form.marginBuyingPowerShortfallCash = this.form.hiddenMarginBuyingPowerShortfallCash
        this.form.marginShortfallSecurities = this.form.hiddenMarginShortfallSecurities
        this.form.marginShortfallCash = this.form.hiddenMarginShortfallCash
        this.form.depositType = this.form.hiddenAutoTransferSettingInfoDepositType
      }
    },
    errorHandlerInitializeA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    responseHandlersettingConfirmA002(data) {
      this.confirmForm = Object.assign(this.confirmForm, this.form)
      this.confirmForm = Object.assign(this.confirmForm, data.dataList[0])
      this.confirmIsVisible = true
    },
    // 設定指示ボタン
    handleTransferFinish(data) {
      this.completeForm = Object.assign(this.completeForm, data)
      this.confirmIsVisible = false
      this.dialogFinish = true
    },
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // 戻るボタン
    handleCloseModal() {
      this.confirmIsVisible = false
    },
    // 自動振替設定入力へボタン
    handlebackOrder() {
      this.dialogFinish = false
      this.form = new IfaForeignMarginAutoTransferSettingInputFormModel()
      document.getElementById('IfaForeignMarginAutoTransferSettingInputA001').click()
      this.changeSelectCheck1()
      this.changeSelectCheck2()
      this.changeSelectCheck3()
      this.$nextTick(() => {
        this.$emit('update-customer-portal')
      })
    }
  }
}

</script>
<style lang="scss" scoped>
@import "@/styles/table.scss";
:deep(.el-table) .__left {
  text-align: left;
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
  text-align: left;
  width: 135px;
}
.explain_content {
  font-weight: bold;
}
.red_content {
  color: red;
}
.error-message {
  margin: 0.5rem 0;
  color: red;
  font-weight: bold;
  white-space: pre-wrap;
}
.labelClass {
  margin-top: 0.5rem;
  margin-bottom: 0;
}
:deep(.el-checkbox) {
  height: 25px;
  margin-left: 11.5px;
}
.labelClass2 {
  margin-bottom: 0.5rem;
}
.labelClass3 {
  width: 100px;
}
:deep(.el-radio) {
  height: 25px;
  margin-left: 6px;
}
</style>
