<template>
  <!-- BB訂正・取消ダイアログ -->
  <el-dialog
    :key="refreshDialog"
    v-model="vmIsVisible"
    class="buy-background-color"
    title="BB申込訂正確認"
    width="1200px"
    :center="true"
    :style="{ 'background-color': '#fef0f0' }"
    :close-on-click-modal="false"
    :show-close="false"
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

    <!-- エラー/警告表示 -->
    <ifa-message-area
      class="el-col-offset-1 message"
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
        <span>{{ $_out(`${confirmForm.butenCode}-${$_zeroPadding(confirmForm.accountNumber, 7)}`) }}</span>
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
          <el-icon>
            <OfficeBuilding v-if="confirmForm.corporationType === '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
        </span>
        <span>{{ $_out(`${confirmForm.customerNameKanji}（${confirmForm.customerNameKana}）`) }}</span>
        <ifa-notice-info
          :notice-info-level="confirmForm.noticeInfoLevel"
          :buten-code="confirmForm.butenCode"
          :account-number="confirmForm.accountNumber"
          :customer-code="confirmForm.customerCode"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>
    <!-- 銘柄情報 -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="background-color: #eee;"
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
                <span>［{{ $_out(confirmForm.brandCode) }}］</span>
              </div>
              <div
                style="font-size: 20px; display: inline-block;"
                class="_bold_black_l"
              >
                <span>{{ $_out(confirmForm.brandName) }}</span>
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
            <el-col
              :span="8"
            >
              <span>BB申込内容（復唱項目）</span>
            </el-col>
            <el-col
              :span="2"
              style="margin-right: -25px;"
            >
              <span>訂正前</span>
            </el-col>
            <el-col
              :span="6"
              style="display: flex; justify-content: center; align-items: center; margin-right: 25px;"
            >
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': confirmForm.quantity!==confirmForm.quantityBeforeCorrect}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>数量</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ confirmForm.quantityBeforeCorrect ? `${$_withCommaInteger(confirmForm.quantityBeforeCorrect)}${confirmForm.sellBuyUnitType}`: $_out(confirmForm.quantityBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ confirmForm.quantity ? `${$_withCommaInteger(confirmForm.quantity)}${confirmForm.sellBuyUnitType}`: $_out(confirmForm.quantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': (confirmForm.marketOrder!==confirmForm.marketOrderBeforeCorrect)||
              (confirmForm.price!==confirmForm.priceBeforeCorrect)||
              (confirmForm.discountRate!==confirmForm.discountRateBeforeCorrect)}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>価格/ディスカウント率</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span v-if="confirmForm.marketOrderBeforeCorrect==='1'">成行</span>
              <span v-else-if="confirmForm.issuePriceType === '1'">{{ confirmForm.priceBeforeCorrect ? `${$_withCommaNoneZeroPadding(confirmForm.priceBeforeCorrect)}円` : $_out(confirmForm.price) }}</span>
              <span v-else-if="confirmForm.issuePriceType === '2'">{{ confirmForm.discountRateBeforeCorrect? `${$_withCommaNoneZeroPadding(confirmForm.discountRateBeforeCorrect)}%` : $_out(confirmForm.discountRate) }}</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span v-if="confirmForm.marketOrder==='1'">成行</span>
              <span v-else-if="confirmForm.issuePriceType === '1'">{{ confirmForm.price ? `${$_withCommaNoneZeroPadding(confirmForm.price)}円` : $_out(confirmForm.price) }}</span>
              <span v-else-if="confirmForm.issuePriceType === '2'">{{ confirmForm.discountRate? `${$_withCommaNoneZeroPadding(confirmForm.discountRate)}%` : $_out(confirmForm.discountRate) }}</span>
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
        <el-card class="box-card">
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <el-col
              :span="8"
            >
              <span>その他申込内容</span>
            </el-col>
            <el-col
              :span="2"
              style="margin-right: -25px;"
            >
              <span>訂正前</span>
            </el-col>
            <el-col
              :span="6"
              style="display: flex; justify-content: center; align-items: center; margin-right: 25px;"
            >
              <span class="el-icon-right arrow">→</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>訂正後</span>
            </el-col>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': confirmForm.discretionQuantity!==confirmForm.discretionQuantityBeforeCorrect}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量希望数量</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ confirmForm.discretionQuantityBeforeCorrect ? `${$_withCommaInteger(confirmForm.discretionQuantityBeforeCorrect)}${confirmForm.sellBuyUnitType}`: $_out(confirmForm.discretionQuantityBeforeCorrect) }}</span>
              <!--<span>100株</span>-->
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ confirmForm.discretionQuantity ? `${$_withCommaInteger(confirmForm.discretionQuantity)}${confirmForm.sellBuyUnitType}`: $_out(confirmForm.discretionQuantity) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': confirmForm.selectReasonBeforeCorrect!==confirmForm.selectReason}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>裁量選定理由</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>{{ $_out(confirmForm.selectReasonBeforeCorrect) }}</span>
              <!-- <span>取引拡大のため</span> -->
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ $_out(confirmForm.selectReason) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': confirmForm.investorAttributeNameBeforeCorrect!==confirmForm.investorAttributeName}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>投資家属性</span>
            </el-col>
            <el-col
              :span="8"
            >
              <span>{{ $_out(confirmForm.investorAttributeNameBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ $_out(confirmForm.investorAttributeName) }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>勧誘区分</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>-</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ confirmForm.kanyuKbn ? $_getCodeValue('INVITATION_TYPE', 1, confirmForm.kanyuKbn) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>受付方法</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span>-</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span>{{ confirmForm.receiveMethod ? $_getCodeValue('ORDER_METHOD_TYPE', 1, confirmForm.receiveMethod) : '-' }}</span>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
            :class="{'correction-bg-color': confirmForm.bbRemarkBeforeCorrect!==confirmForm.bbRemark}"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>備考</span>
            </el-col>
            <el-col
              :span="8"
              style="min-height: 1px;"
            >
              <span style="overflow-wrap: anywhere; white-space: break-spaces;">{{ $_out(confirmForm.bbRemarkBeforeCorrect) }}</span>
            </el-col>
            <el-col
              :span="8"
              style="font-weight: bold;"
            >
              <span style="overflow-wrap: anywhere; white-space: break-spaces;">{{ $_out(confirmForm.bbRemark) }}</span>
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
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
          >
            <span>アラート内容確認</span>
          </el-row>
          <hr>
          <el-row
            v-if="confirmForm.complianceRankCheckMsg"
            class="dotted_border"
            style="padding-bottom: 0.5rem; align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">{{ $_out($_getCodeValue('COMPLA_CHECK_BOX_WORDING', 3, confirmForm.complianceRankCheckChkBoxLabel)) }}</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                id="confirmed"
                v-model="confirmForm.invitationCheck"
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
            v-if="confirmForm.discretionAllocateTimesOverFiveWarningMsg"
            class="dotted_border"
            style="padding-bottom: 0.5rem; align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">裁量配分割当回数5回以上</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                id="confirmed"
                v-model="confirmForm.discretionAllocateTimesOverFive"
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
            v-if="confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg"
            class="dotted_border"
            style="padding-bottom: 0.5rem; align-items: center;"
          >
            <el-col
              :span="8"
              class="_bold_red_alart"
            >
              <span class="required-mark">*</span><span style="color: #f00;">金融資産3,000万円未満の裁量申込</span>
            </el-col>
            <el-col
              :span="16"
            >
              <ifa-input-check
                v-model="confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApply"
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
          text="申込訂正"
          :disabled="buttonDisabled"
          action-type="requestAction"
          action-id="SUB0204_02-02_2#A001"
          style="padding-left: 0;"
          :request-model="ifaBbApplyCorrectConfirmA001RequestModel"
          @response-handler="applicationCorrectionA001ResponseHandler($event)"
          @response-error-handler="responseErrorHandlerA001($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaBbApplyCorrectConfirmFormModel } from './js/IfaBbApplyCorrectConfirmFormModel.js'
import { IfaBbApplyCorrectConfirmA001RequestModel } from './js/IfaBbApplyCorrectConfirmA001RequestModel.js'
import ifaNoticeInfo from '@/components/icon/IfaNoticeInfo'
export default {
  components: {
    IfaMessageArea,
    ifaNoticeInfo
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true }
  },
  emits: ['close-modal', 'order-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      confirmForm: new IfaBbApplyCorrectConfirmFormModel(),
      refreshDialog: Date.now()
    }
  },
  computed: {
    ifaBbApplyCorrectConfirmA001RequestModel() {
      return new IfaBbApplyCorrectConfirmA001RequestModel(this.confirmForm)
    },
    complianceRankList() {
      return [{ key: '0', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceRankCheckChkBoxLabel) }, { key: '1', value: this.$_getCodeValue('COMPLA_CHECK_BOX_WORDING', 1, this.form.complianceRankCheckChkBoxLabel) }]
    },
    // ユーザ共通情報.権限コード
    privId() {
      return this.$store.getters.userAccount.medUsers.privId
    },
    // メッセージエリア
    message() {
      const main = []
      main.push(this.confirmForm.warningMessage)
      const warningMessage = []
      if (this.confirmForm.complianceRankCheckMsg) {
        warningMessage.push({ label: this.confirmForm.complianceRankCheckMsg, checked: this.confirmForm.invitationCheck })
      }
      if (this.confirmForm.discretionAllocateTimesOverFiveWarningMsg) {
        warningMessage.push({ label: this.confirmForm.discretionAllocateTimesOverFiveWarningMsg, checked: this.confirmForm.discretionAllocateTimesOverFive })
      }
      if (this.confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg) {
        warningMessage.push({ label: this.confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg, checked: this.confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApply })
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
  methods: {
    onDialogOpen() {
      Object.keys(this.confirmForm).forEach(key => {
        this.confirmForm[key] = this.form[key]
      })
      this.confirmForm.invitationCheck = '0'
      this.confirmForm.discretionAllocateTimesOverFive = '0'
      this.confirmForm.financialAssetLessThanThirtyMillionYenDiscretionApply = '0'
      this.confirmForm.warningMessage = '訂正はまだ完了していません。画面下の申込訂正ボタンを押下してください。'
      this.refreshDialog = Date.now()
    },
    onOrderFinish() {
      this.$emit('order-finish', this.confirmForm)
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    applicationCorrectionA001ResponseHandler(data) {
      this.$_logDebug(data)
      Object.assign(this.confirmForm, data.dataList[0])
      this.onOrderFinish()
    },
    responseErrorHandlerA001(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.buy-background-color_card {
  background-color: #fef0f0;
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
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
:deep(.el-dialog) {
  padding: 40px 20px 20px 20px !important;
  padding-top: 40px;
}
._bold_red_m {
  font-size: 16px;
  font-weight: bold;
  color: #f00;
}
.required-mark {
  color: #ff2b2b;
  display: inline-block;
  width: 8px;
}
:deep(.el-checkbox__label) {
  padding-bottom: 0px;
  color: #f00;
  font-weight: bold;
  font-size: 16px;
}
:deep(.el-checkbox__input.is-checked) + .el-checkbox__label {
    color: #f00;
}
.buy-background-color_card {
  background-color: #fef0f0;
}
.sell-background-color_card {
  background-color: #ecf5ff;
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
:deep(.el-form-item.asterisk-left.form_label .el-form-item__label) {
  color: red;
  font-weight: bold;
}
:deep(._bold_red_alart) {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}
.message {
  flex-direction: column !important;
}
:deep(.el-card__body) {
  margin-left: 0px !important;
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
