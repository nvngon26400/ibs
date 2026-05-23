<template>
  <el-dialog
    v-model="vmIsVisible"
    title="米株信用自動振替設定確認"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="backA002"
    :close-on-click-modal="false"
    @open="defaultSet"
  >
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="23"
        style="text-align: right;"
      >
        <ifa-button
          class="form-button__wrapper"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="backA002"
        ></ifa-button>
      </el-col>
    </el-row>

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="[
        '設定はまだ完了していません。画面下の設定指示ボタンを押下してください。',
        '※同一項目内で米国株式（保護預り）および米ドル預り金を設定した場合、米国株式（保護預り）から優先して振り替えします。'
      ]"
    ></ifa-message-area>

    <!-- 顧客情報/口座情報 -->
    <el-row
      class="_bold_black_l"
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
      >
        <span>{{ accountNumber }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >

      <el-col
        :offset="1"
        :span="22"
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
      <div style="margin-bottom: 1rem;"></div>
    </el-row>
    <!-- 注文内容(復唱項目) -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
        style="padding: 0.3rem 0 0.6rem 0"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>設定内容（復唱項目）</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            :style="{
              'background-color': changeSelectCheck ? '#DDEBF7' : '',
              'padding-bottom': '0.5rem',
              'align-items': 'center'
            }"
          >
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>信用新規建(新規買・新規売)の注文時の自動振替設定</span>
            </el-col>
            <el-col :span="13">
              <div
                v-if="form.marginBuyingPowerShortfallSecurities === '1'"
                :class="{'bold': changeSelectCheck}"
              >
                {{ $_getCodeValue('MARGIN_BUYING_POWER_SHORTFALL_SECURITIES', 1, form.marginBuyingPowerShortfallSecurities) }}
              </div>
              <div
                v-if="form.marginBuyingPowerShortfallCash === '1'"
                :class="{'bold': changeSelectCheck}"
              >
                {{ $_getCodeValue('MARGIN_BUYING_POWER_SHORTFALL_CASH', 1, form.marginBuyingPowerShortfallCash) }}
              </div>
              <div v-if="form.marginBuyingPowerShortfallSecurities !== '1' && form.marginBuyingPowerShortfallCash !== '1'">-</div>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            :style="{
              'background-color': changeSelectCheck2 ? '#DDEBF7' : '',
              'padding-bottom': '0.5rem',
              'align-items': 'center'
            }"
          >
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>追加証拠金（追証）・新規建不足発生時の自動振替設定</span>
            </el-col>
            <el-col :span="13">
              <div
                v-if="form.marginShortfallSecurities === '1'"
                :class="{'bold': changeSelectCheck2}"
              >
                {{ $_getCodeValue('MARGIN_SHORTFALL_SECURITIES', 1, form.marginShortfallSecurities) }}
              </div>
              <div
                v-if="form.marginShortfallCash === '1'"
                :class="{'bold': changeSelectCheck2}"
              >
                {{ $_getCodeValue('MARGIN_SHORTFALL_CASH', 1, form.marginShortfallCash) }}
              </div>
              <div v-if="form.marginShortfallSecurities !== '1' && form.marginShortfallCash !== '1'">-</div>
            </el-col>
          </el-row>
          <el-row
            class="dotted_border"
            :style="{
              'background-color': changeSelectCheck3 ? '#DDEBF7' : '',
              'padding-bottom': '0.5rem',
              'align-items': 'center'
            }"
          >
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>米国現物株買付時の代用自動振替設定</span>
            </el-col>
            <el-col :span="3">
              <span :class="{'bold': changeSelectCheck3}">
                {{ $_getCodeValue('DEPOSIT_TYPE', 2, form.depositType) }}
              </span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOrderRegister"
          text="設定指示"
          color="primary"
          :request-model="A001RequestModel"
          style="padding-left: 0;"
          action-id="SUB0202_0306-01_2#A001"
          action-type="requestAction"
          :disabled="btnSettingInstructionDisabled"
          @response-handler="responseHandlerSettingInstructionA001($event)"
        >
        </ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>
<script>
import { useVModel } from 'vue-composable'
import { IfaForeignMarginAutoTransferSettingConfirmA001RequestModel } from './js/IfaForeignMarginAutoTransferSettingConfirmA001RequestModel.js'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true },
    autoTransferSettingInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'transfer-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      btnSettingInstructionDisabled: false // 設定指示ボタンの制御。ボタン非活性の場合true
    }
  },
  computed: {
    customerName() {
      return this.customerInfo().customerNameKanji + ' (' + this.customerInfo().customerNameKana + ')'
    },
    accountNumber() {
      return this.customerInfo().butenCode + '-' + this.customerInfo().accountNumber
    },
    A001RequestModel() {
      return new IfaForeignMarginAutoTransferSettingConfirmA001RequestModel(this.form)
    },
    changeSelectCheck() {
      let flag = this.form.marginBuyingPowerShortfallSecurities
      let flag2 = this.form.marginBuyingPowerShortfallCash
      if (flag === undefined) {
        flag = '0'
      }
      if (flag2 === undefined) {
        flag2 = '0'
      }
      return flag !== this.form.hiddenMarginBuyingPowerShortfallSecurities ||
       flag2 !== this.form.hiddenMarginBuyingPowerShortfallCash
    },
    changeSelectCheck2() {
      let flag3 = this.form.marginShortfallSecurities
      let flag4 = this.form.marginShortfallCash
      if (flag3 === undefined) {
        flag3 = '0'
      }
      if (flag4 === undefined) {
        flag4 = '0'
      }
      return flag3 !== this.form.hiddenMarginShortfallSecurities ||
       flag4 !== this.form.hiddenMarginShortfallCash
    },
    changeSelectCheck3() {
      return this.form.depositType !== this.form.hiddenAutoTransferSettingInfoDepositType
    }
  },
  methods: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    responseHandlerSettingInstructionA001(data) {
      if (data.dataList.length !== 0) {
        this.$emit('transfer-finish', data.dataList[0].apiRes)
      }
    },
    defaultSet() {
      if (this.$store.getters.userAccount.medUsers.privId === '1' || this.$store.getters.userAccount.medUsers.privId === '2') {
        this.btnSettingInstructionDisabled = true
      }
    },
    backA002() {
      // ①米株信用自動振替設定確認画面（ポップアップ）を閉じる。
      this.$emit('close-modal')
    }
  }
}

</script>
<style lang="scss" scoped>
@import "@/styles/table.scss";
@import "@/styles/orderStatusList.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
:deep(.el-form-item__label) {
  font-weight: normal;
  text-align: right;
  width: 135px;
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
._black_s_bold {
  font-size: 15px;
  font-weight: 700;
  color: #18181A;
  padding-bottom: 0.5rem;
}
.bold {
  font-weight: bold;
}
</style>
