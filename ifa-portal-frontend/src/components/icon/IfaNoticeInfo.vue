<template>
  <div style="display: inline;">
    <el-tooltip
      v-if="isVisible"
      content="注意情報"
      placement="bottom"
      effect="light"
    >
      <el-icon
        :class="infoIcon"
        @click="openDialog"
      ><InfoFilled></InfoFilled></el-icon>
    </el-tooltip>

    <el-dialog
      v-model="isDialogVisible"
      :append-to-body="true"
      :show-close="false"
      :close-on-click-modal="false"
      width="1350px"
    >

      <!-- 戻るボタン -->
      <el-row>
        <ifa-button
          class="form-button__wrapper"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="closeDialog"
        ></ifa-button>
      </el-row>

      <!-- 注意情報 -->
      <el-row>
        <ifa-notice-info-contents
          :notice-info-level="noticeInfoLevel"
          :customer-code="customerCode"
          :buten-code="butenCode"
          :account-number="accountNumber"
          :notice-info="noticeInfo"
        ></ifa-notice-info-contents>
      </el-row>

    </el-dialog>

    <!-- 注意情報をサーバから受信します -->
    <ifa-requester
      :id="requestId"
      action-id="CC016#A002"
      action-type="requestAction"
      :request-model="requestModelA002"
      @response-handler="handleResponseA002($event)"
      @response-error-handler="handleErrorResponse($event)"
    ></ifa-requester>

  </div>
</template>

<script>
import IfaNoticeInfoContents from './IfaNoticeInfoContents.vue'
export default {
  components: {
    IfaNoticeInfoContents
  },
  props: {
    noticeInfoLevel: { type: [String, Number], required: false, default: 0 },
    customerCode: { type: String, required: false, default: '' },
    butenCode: { type: String, required: false, default: '' },
    accountNumber: { type: String, required: false, default: '' }
  },
  data() {
    return {
      isDialogVisible: false,
      processing: false,
      requestModelA002: {},
      noticeInfo: {
        noticeInfoList: [],
        tradeRestrictionList: []
      },
      requestId: 'IfaNoticeInfoDisplayA002'
    }
  },
  computed: {
    isVisible() {
      return Number(this.noticeInfoLevel) > 1
    },
    infoIcon() {
      return Number(this.noticeInfoLevel) === 3 ? 'alert-icon' : 'notice-icon'
    }
  },
  methods: {
    openDialog() {
      if (!this.processing) {
        this.processing = true

        // ここでサーバに通知情報をリクエストします
        this.$nextTick(() => {
          this.requestModelA002 = {
            customerCode: this.customerCode,
            butenCode: this.butenCode,
            accountNumber: this.accountNumber
          }
          this.$el.querySelector('#' + this.requestId).click()
        })
      }
    },
    closeDialog() {
      this.isDialogVisible = false
    },
    handleResponseA002(response) {
      this.noticeInfo = response.dataList[0]
      this.isDialogVisible = true
      this.processing = false
    },
    handleErrorResponse(data) {
      this.processing = false
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/variables.scss";
.form-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 0rem 0 auto;
}
.notice-icon {
  font-size: 22px;
  color: $subBlueColor;
  animation: poyopoyo 2.0s 1 normal;
}
.alert-icon {
  font-size: 22px;
  color: $errorColor;
  animation: poyopoyo 2.0s 1 normal;
}
@keyframes poyopoyo {
  0%, 40%, 60%, 80% {
    transform: scale(1.0);
  }
  50%, 70% {
    transform: scale(0.80);
  }
}
</style>
