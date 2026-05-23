<template>
  <!-- BB申込確認ダイアログ -->
  <el-dialog
    :key="refreshDialog"
    v-model="vmIsVisible"
    class="buy-background-color"
    title="BB申込確認"
    width="1200px"
    :center="true"
    :close-on-click-modal="false"
    :show-close="false"
    destroy-on-close
    @open="onDialogOpen"
  >

    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- ワーニング表示 -->
    <ifa-message-area
      class="el-col-offset-1"
      :main-messages="message.main"
      :warning-messages="message.warningMessage.map(e => e.label)"
    >
    </ifa-message-area>
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="padding-top: 1rem; font-weight: bold;"
    >
      <el-col
        :span="22"
        :offset="1"
      >
        <span>{{ $_out(`${form.butenCode}-${form.accountNumber}`) }}</span>
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
        <el-icon>
          <span style="position: relative; top: 3px;">
            <OfficeBuilding v-if="form.corporationType === '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </span>
        </el-icon>
        <span>{{ $_out(`${form.customerNameKanji}（${form.customerNameKana}）`) }}</span>
        <ifa-notice-info
          :notice-info-level="form.noticeInfoLevel"
          :buten-code="form.butenCode"
          :account-number="form.accountNumber"
          :customer-code="form.customerCode"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="background-color: #eee; margin: 0.5rem 0;"
        >
          <el-row>
            <el-col
              :span="24"
              style="display: flex; align-items: flex-start;"
            >
              <div
                style="font-size: 20px; display: inline-block; width: auto; white-space: nowrap;"
                class="_bold_black_l"
              >
                <span>［{{ $_out(form.brandCode) }}］</span>
              </div>
              <div
                style="font-size: 20px; display: inline-block;"
                class="_bold_black_l"
              >
                <span>{{ $_out(form.brandName) }}</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>お申込内容（復唱項目）</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.quantity ? `${$_withCommaInteger(form.quantity)}${form.sellBuyUnitType}`: $_out(form.quantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格/ディスカウント率</span>
            </el-col>
            <el-col :span="16">
              <span v-if="form.marketOrder==='1'">成行</span>
              <span v-else-if="form.issuePriceType === '1'">{{ form.price ? `${$_withCommaNoneZeroPadding(form.price)}円` : $_out(form.price) }}</span>
              <span v-else-if="form.issuePriceType === '2'">{{ form.discountRate? `${$_withCommaNoneZeroPadding(form.discountRate)}%` : $_out(form.discountRate) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- その他注文内容 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>その他申込内容</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量希望数量</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.discretionQuantity ? `${$_withCommaInteger(form.discretionQuantity)}${form.sellBuyUnitType}`: $_out(form.discretionQuantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量選定理由</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.selectReason ? $_getCodeValue('DISCRETION_SELECT_REASON', 1, form.selectReason) : $_out(form.selectReason) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>投資家属性</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_out(form.investorAttributeName) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.kanyuKbn ? $_getCodeValue('INVITATION_TYPE', 1, form.kanyuKbn) : $_out(form.kanyuKbn) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受注方法</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.receiveMethod ? $_getCodeValue('ORDER_METHOD_TYPE', 1, form.receiveMethod) : $_out(form.receiveMethod) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom:0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>備考</span>
            </el-col>
            <el-col :span="16">
              <span style="overflow-wrap: anywhere; white-space: break-spaces;">{{ $_out(form.bbRemark) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <el-row
      v-if="message.warningMessage.length"
    >
      <el-col
        :span="22"
        :offset="1"
        style="color: #f00;"
      >
        <el-card
          class="box-card buy-background-color_card alert_content"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; color: #f00; padding-left: 1rem;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="form.complianceRankCheckMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, form.chkBoxLabel)) }}</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                id="confirmed"
                v-model="form.invitationCheck"
                code-list-id="original"
                :original-list="complianceRankList"
                :is-button="false"
                class="warning-message"
                name="confirmed"
                style="margin-left: 5px; color: #f00;"
              >
              </ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.discretionAllocateTimesOverFiveWarningMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">裁量配分割当回数5回以上</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                id="confirmed"
                v-model="form.discretionAllocateTimesOverFive"
                name="confirmed"
                code-list-id="DISCRETION_ALLOCATE_TIMES_OVER_FIVE"
                :disp-pattern="1"
                :select-pattern="2"
                style="margin-left: 5px; color: #f00;"
              >
              </ifa-input-check>
            </el-col>
          </el-row>
          <el-row
            v-if="form.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg"
            class="dotted_border"
            style="align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <div class="required-mark">*</div><span style="color: #f00;">金融資産3,000万円未満の裁量申込</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                v-model="form.financialAssetLessThanThirtyMillionYenDiscretionApply"
                :code-list-id="'FINANCIAL_ASSET_LESS_THAN_THIRTY_MILLION_YEN_DISCRETION_APPLY'"
                :disp-pattern="1"
                :select-pattern="2"
                style="margin-left: 5px; color: #f00;"
              >
              </ifa-input-check>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- 注文発注ボタン -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="text-align: left; margin-top: 1rem;"
      >
        <ifa-button
          text="申込登録"
          :disabled="buttonDisabled"
          action-type="requestAction"
          action-id="SUB0204_01-02_2#A001"
          :request-model="ifaBbApplyConfirmA001RequestModel"
          :pre-request-handler="preConfirm"
          style="padding-left: 0"
          @response-handler="a001ResponseHandler($event)"
          @response-error-handler="responseErrorHandlerA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaBbApplyInputA001RequestModel } from './js/IfaBbApplyInputA001RequestModel.js'
import { IfaBbApplyInputA002RequestModel } from './js/IfaBbApplyInputA002RequestModel.js'
import { IfaBbApplyInputA003RequestModel } from './js/IfaBbApplyInputA003RequestModel.js'
import { IfaBbApplyInputA004RequestModel } from './js/IfaBbApplyInputA004RequestModel.js'
import { IfaBbApplyConfirmA001RequestModel } from './js/IfaBbApplyConfirmA001RequestModel.js'
import { IfaBbApplyConfirmFormModel } from './js/IfaBbApplyConfirmFormModel.js'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'

export default {
  components: {
    IfaMessageArea,
    ifaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    applyInputForm: { type: Object, required: true }
  },
  emits: ['order-finish', 'close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      ifaBbApplyInputA001RequestModel: '',
      ifaBbApplyInputA002RequestModel: '',
      ifaBbApplyInputA003RequestModel: '',
      ifaBbApplyInputA004RequestModel: '',
      form: new IfaBbApplyConfirmFormModel(),
      refreshDialog: Date.now()
    }
  },
  computed: {
    ifaBbApplyConfirmA001RequestModel() {
      return new IfaBbApplyConfirmA001RequestModel(this.form)
    },
    complianceRankList() {
      return [{ key: '0', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.chkBoxLabel) }, { key: '1', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.chkBoxLabel) }]
    },
    // ユーザ共通情報.権限コード
    privId() {
      return this.$store.getters.userAccount.medUsers.privId
    },
    // メッセージエリア
    message() {
      const main = []
      main.push(this.form.warningMessage)
      const warningMessage = []
      if (this.form.complianceRankCheckMsg) {
        warningMessage.push({ label: this.form.complianceRankCheckMsg, checked: this.form.invitationCheck })
      }
      if (this.form.discretionAllocateTimesOverFiveWarningMsg) {
        warningMessage.push({ label: this.form.discretionAllocateTimesOverFiveWarningMsg, checked: this.form.discretionAllocateTimesOverFive })
      }
      if (this.form.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg) {
        warningMessage.push({ label: this.form.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg, checked: this.form.financialAssetLessThanThirtyMillionYenDiscretionApply })
      }
      return {
        main,
        warningMessage
      }
    },
    buttonDisabled() {
      if (this.privId === '1' || this.privId === '2') {
        return true
      } else if (this.message.warningMessage.length !== 0 && this.message.warningMessage.find(e => e.checked === '0')) {
        return true
      } else {
        return false
      }
    }
  },
  watch: {
    isVisible(newValue) {
      if (newValue) {
        this.ifaBbApplyInputA001RequestModel = new IfaBbApplyInputA001RequestModel(this.applyInputForm)
        this.ifaBbApplyInputA002RequestModel = new IfaBbApplyInputA002RequestModel(this.applyInputForm)
        this.ifaBbApplyInputA003RequestModel = new IfaBbApplyInputA003RequestModel(this.applyInputForm)
        this.ifaBbApplyInputA004RequestModel = new IfaBbApplyInputA004RequestModel(this.applyInputForm)
      }
    }
  },
  methods: {
    onDialogOpen() {
      this.form = new IfaBbApplyConfirmFormModel()
      Object.keys(this.applyInputForm).forEach(key => {
        if (typeof (this.form[key]) !== 'undefined' && !this.form[key]) {
          this.form[key] = this.applyInputForm[key]
        }
      })
      this.form.chkBoxLabel = this.applyInputForm.complianceRankCheckChkBoxLabel
      this.form.invitationCheck = '0'
      this.form.discretionAllocateTimesOverFive = '0'
      this.form.financialAssetLessThanThirtyMillionYenDiscretionApply = '0'
      this.form.warningMessage = '申込はまだ完了していません。画面下の申込登録ボタンを押下してください。'
      this.refreshDialog = Date.now()
    },

    a001ResponseHandler(data) {
      if (data.dataList) {
        Object.assign(this.form, data.dataList[0])
      }
      this.onOrderFinish()
    },
    responseErrorHandlerA001(error) {
      this.$_logDebug('responseErrorHandlerA001-Error', error)
    },
    onOrderFinish() {
      this.form.invitationCheck = '0'
      this.form.discretionAllocateTimesOverFive = '0'
      this.form.financialAssetLessThanThirtyMillionYenDiscretionApply = '0'
      this.$emit('order-finish', this.form)
    },
    onDialogClose() {
      // IPO/POの申込入力へ戻る
      if (this.form.transitionSourceScreen === 'SUB0204_01-01') {
        this.$emit('close-modal', 'SUB0204_01-01', this.ifaBbApplyInputA001RequestModel, this.ifaBbApplyInputA004RequestModel)
      // 顧客一覧の申込入力へ戻る
      } else if (this.form.transitionSourceScreen === 'SUB0201_01-01') {
        this.$emit('close-modal', 'SUB0201_01-01', this.ifaBbApplyInputA002RequestModel, this.ifaBbApplyInputA003RequestModel)
      }
    },
    preConfirm() {
      this.ifaBbApplyConfirmA001RequestModel.selectReason = this.$_getCodeValue('DISCRETION_SELECT_REASON', 1, this.ifaBbApplyConfirmA001RequestModel.selectReason)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.buy-background-color{
  background-color: #fef0f0 !important;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
  padding-left: 0.5rem;
}

.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
:deep(.el-checkbox .el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-weight: bold !important;
  font-size: 16px;
}
:deep(.el-checkbox__input.is-checked + .el-checkbox__label) {
    color: #f00;
}
.sell-background-color_card {
  background-color: #ecf5ff;
}
:deep(.el-card__body) {
  margin-left: 0px !important;
}

:deep(.el-form-item.asterisk-left.form_label .el-form-item__label) {
  color: red;
  font-weight: bold;
}
:deep(._black_s_bold.warning) {
  white-space: nowrap;
  overflow: hidden;
  line-height: 42px
}
:deep(.main-message) {
  padding-left: 0px !important;
}
:deep(.warning-message) {
  padding-left: 20px !important;
}
:deep(.error-message,.info-message) {
  display: none;
}
._bold_red_alart {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}
.alert_content {
  :deep(.el-form-item) {
    margin-bottom: 0 !important;
  }
  :deep(.el-form-item__content) {
    line-height: normal;
  }
}
</style>
