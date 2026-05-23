<template>
  <el-dialog
    v-model="vmIsVisible"
    title="米株信用代用振替完了"
    class="foreign_margin_coll"
    width="1170px"
    :center="true"
    :show-close="false"
    :close-on-click-modal="false"
    @open="onShow"
    @closed="handleClosed"
  >
    <el-row>
      <el-col style="margin-left: 6.7px;">
        <ifa-message-area
          :main-messages="['下記の内容で振替指示を受け付けました。']"
        ></ifa-message-area>
      </el-col>
    </el-row>
    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
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

    <!-- 振替結果 -->
    <el-row style="margin-top: 1rem">
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
            style="padding-top: 0.5rem;"
          >
            <span>振替結果</span>
          </el-row>
          <hr>
          <el-row class="dotted_border">
            <el-col :span="8"
                    class="_bold_black_m"
            >
              <span>信用建余力</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaZeroPadding($_out(form.foreignMarginPositionPower), 2) }} USD</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col :span="8"
                    class="_bold_black_m"
            >
              <span>委託保証金率</span>
            </el-col>
            <el-col :span="16">
              <span>{{ marginDepositRateAfter }} %</span>
            </el-col>
          </el-row>
          <el-row class="dotted_border">
            <el-col :span="8"
                    class="_bold_black_m"
            >
              <span>代用評価額</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaZeroPadding($_out(form.collateralValuation), 2) }} USD</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left; margin-top: 2rem; margin-bottom: 1rem"
      >
        <ifa-button
          id="btnForeignMarginCollateralTransferInput"
          name="btnForeignMarginCollateralTransferInput"
          text="代用振替入力へ"
          color="primary"
          style="padding-left: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        >
        </ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>
<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaForeignMarginCollateralTransferCompleteFormModel } from './js/IfaForeignMarginCollateralTransferCompleteFormModel.js'
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
  emits: ['close-modal', 'closed', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaForeignMarginCollateralTransferCompleteFormModel()
    }
  },
  computed: {
    customerName() {
      return this.customerInfo().customerNameKanji + '（' + this.customerInfo().customerNameKana + '）'
    },
    accountNumber() {
      return this.customerInfo().butenCode + '-' + this.customerInfo().accountNumber
    },
    marginDepositRateAfter() {
      if (this.form.collateralTransferMarginDepositRateAfter === null) {
        return '--'
      }
      return this.$_withCommaZeroPadding(this.$_out(this.form.collateralTransferMarginDepositRateAfter), 2)
    }
  },
  methods: {
    onShow() {
      this.form.foreignMarginPositionPower = this.formData.afterPower.marginBuyingPower
      this.form.collateralTransferMarginDepositRateAfter = this.formData.afterPower.depositRate // 委託保証金率 【初期値】"--"
      this.form.collateralValuation = this.formData.afterPower.totalCollateralValue // 代用評価額 【初期値】"--"
    },
    // 戻るボタン
    onDialogClose() {
      this.$emit('close-modal')
    },
    handleClosed() {
      this.$emit('closed')
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
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.__total{
  padding: 2px 14px 2px 14px;
}
</style>
