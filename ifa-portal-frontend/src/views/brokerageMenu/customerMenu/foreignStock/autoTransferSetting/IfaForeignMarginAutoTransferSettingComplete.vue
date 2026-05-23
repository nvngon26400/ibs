<template>
  <el-dialog
    v-model="vmIsVisible"
    title="米株信用自動振替設定完了"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="backA001"
    :close-on-click-modal="false"
  >

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="[
        '下記の内容で設定を変更いたしました。',
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
        <span style="margin-right: 1rem;">{{ customerName }}</span>
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
            <span>設定内容</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>信用新規建(新規買・新規売)の注文時の自動振替設定</span>
            </el-col>
            <el-col :span="13">
              <div v-if="form.marginBuyingPowerShortfallSecurities === '1'">
                {{ $_getCodeValue('MARGIN_BUYING_POWER_SHORTFALL_SECURITIES', 1, form.marginBuyingPowerShortfallSecurities) }}
              </div>
              <div v-if="form.marginBuyingPowerShortfallCash === '1'">
                {{ $_getCodeValue('MARGIN_BUYING_POWER_SHORTFALL_CASH', 1, form.marginBuyingPowerShortfallCash) }}
              </div>
              <div v-if="form.marginBuyingPowerShortfallSecurities !== '1' & form.marginBuyingPowerShortfallCash !== '1'">-</div>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>追加証拠金（追証）・新規建不足発生時の自動振替設定</span>
            </el-col>
            <el-col :span="13">
              <div v-if="form.marginShortfallSecurities === '1'">
                {{ $_getCodeValue('MARGIN_SHORTFALL_SECURITIES', 1, form.marginShortfallSecurities) }}
              </div>
              <div v-if="form.marginShortfallCash === '1'">
                {{ $_getCodeValue('MARGIN_SHORTFALL_CASH', 1, form.marginShortfallCash) }}
              </div>
              <div v-if="form.marginShortfallSecurities !== '1' & form.marginShortfallCash !== '1'">-</div>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col
              :span="11"
              class="_bold_black_m"
            >
              <span>米国現物株買付時の代用自動振替設定</span>
            </el-col>
            <el-col :span="13">
              <span>
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
          text="自動振替設定入力へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="backA001"
        >
        </ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>
<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    form: { type: Object, required: true }
  },
  emits: ['back-order', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
    }
  },
  computed: {
    customerName() {
      return this.customerInfo().customerNameKanji + ' (' + this.customerInfo().customerNameKana + ')'
    },
    accountNumber() {
      return this.customerInfo().butenCode + '-' + this.customerInfo().accountNumber
    }
  },
  methods: {
    backA001() {
      // ①米株信用自動振替設定完了画面（ポップアップ）を閉じる。
      this.$emit('back-order')
    },
    customerInfo() {
      return this.$store.getters.customerInfo
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
</style>
