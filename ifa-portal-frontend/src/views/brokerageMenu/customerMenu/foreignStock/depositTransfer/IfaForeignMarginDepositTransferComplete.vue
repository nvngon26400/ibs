<template>
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title.name"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    @open="defaultSet"
  >

    <!-- メッセージエリア -->
    <ifa-message-area
      :main-messages="['下記の内容で振替指示を受け付けました']"
    ></ifa-message-area>

    <!-- ヘッダ -->
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
          <el-icon v-if="customerInfo.corporationType === '0'"><Avatar></Avatar></el-icon>
          <el-icon v-if="customerInfo.corporationType === '1'"><OfficeBuilding></OfficeBuilding></el-icon>
        </span>
        <span>{{ customerName }}</span>
        <ifa-notice-info
          :notice-info-level="customerInfo.noticeInfoLevel"
          :customer-code="customerInfo.customerCode"
          :buten-code="customerInfo.butenCode"
          :account-number="customerInfo.accountNumber"
          style="position: relative; top: 4px;"
        ></ifa-notice-info>
      </el-col>
    </el-row>

    <!-- 振替結果 -->
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
            <span>振替結果</span>
          </el-row>
          <hr>
          <el-row
            class="dotted_border"
            style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>信用建余力</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaZeroPadding($_out(form.marginPositionPowerAfter), 2) }}{{ $_out(form.marginPositionPowerAfter) != '-' && $_out(form.marginPositionPowerAfter) != '--'? ' USD' : '' }}</span>
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
              <span>委託保証金率</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaZeroPadding($_out(form.marginDepositRateAfter), 2) }}{{ $_out(form.marginDepositRateAfter) != '-' && $_out(form.marginDepositRateAfter) != '--' ? ' %' : '' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 注文発注ボタン -->

    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnToForeignMarginDepositTransferInput"
          text="保証金振替入力へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaForeignMarginDepositTransferCompleteFormModel } from './js/IfaForeignMarginDepositTransferCompleteFormModel.js'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    param: { type: Object, required: true }
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
      form: new IfaForeignMarginDepositTransferCompleteFormModel()
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    customerName() {
      return this.customerInfo.customerNameKanji + ' (' + this.customerInfo.customerNameKana + ')'
    },
    accountNumber() {
      return this.customerInfo.butenCode + '-' + this.customerInfo.accountNumber
    }
  },
  methods: {
    // 閉じるボタン
    onDialogClose() {
      this.$emit('back-order')
    },
    defaultSet() {
      Object.assign(this.form, this.param)
      if (this.form.marginPositionPowerAfter == null) {
        this.form.marginPositionPowerAfter = '--'
      }
      if (this.form.marginDepositRateAfter == null) {
        this.form.marginDepositRateAfter = '--'
      }
    }
  }
}
</script>
<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 2rem 0 auto;
}
._black_s {
  font-size: 14px;
  color: #606266;
  padding-bottom: 0.5rem;
}
</style>
