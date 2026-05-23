<template>
  <el-dialog
    id="bulk-editable-confirm"
    v-model="isVisible"
    :title="confirmTitle"
    destroy-on-close
    center
    :width="1200"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    style="background-color: #fef0f0;"
  >
    <div class="wrapper">
      <el-row
        v-if="status === 'confirm'"
        style="display: flex; align-items: center; justify-content: flex-end;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="handleCancel"
        ></ifa-button>
      </el-row>

      <!-- msg panel -->
      <ifa-message-area
        :key="messageKey"
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warns"
      ></ifa-message-area>

      <!-- account info -->
      <account-info-panel :info="info"></account-info-panel>

      <el-form
        ref="formRef"
        :model="form"
      >
        <confirm-info-card
          :status="status"
          :info="info"
          :form="form"
        ></confirm-info-card>
        <el-divider style="margin: 16px 0; border-width: 2px;"></el-divider>

        <!-- その他設定内容 -->
        <other-info-card :info="info"></other-info-card>
        <el-divider style="margin: 16px 0; border-width: 2px;"></el-divider>

        <!-- アラート内容確認 -->
        <alert-check-card
          v-if="showWarning"
          :status="status"
          :info="info"
          :form="form"
        ></alert-check-card>
      </el-form>

      <div style="padding-top: 25px;">
        <ifa-button
          v-if="status === 'confirm'"
          :disabled="confirmBtnDisabled"
          text="設定変更登録"
          color="primary"
          action-type="originalAction"
          @app-action-handler="handleSubmit"
        ></ifa-button>
        <ifa-button
          v-if="status === 'complete'"
          text="投信積立設定済銘柄一覧へ"
          color="primary"
          action-type="originalAction"
          @app-action-handler="handleRedirect"
        ></ifa-button>
      </div>

      <ifa-requester
        id="ifaMutualFundAccumulateSettingBulkChangeRegisterA001"
        action-id="SUB0202_0403-05_3#A001"
        action-type="requestAction"
        :request-model="settingBulkChangeConfirmRequestModel"
        @response-handler="settingBulkChangeConfirmHandler($event)"
        @response-error-handler="settingBulkChangeConfirmErrorHandler"
      ></ifa-requester>
    </div>
  </el-dialog>
</template>

<script>
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea.vue'

import AccountInfoPanel from './AccountInfoPanel.vue'
import ConfirmInfoCard from './ConfirmInfoCard.vue'
import OtherInfoCard from './OtherInfoCard.vue'
import AlertCheckCard from './AlertCheckCard.vue'

import { IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestModel } from '../../js/IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestModel'
import { IfaMutualFundAccumulateBulkEditableConfirmFormModel } from '../../js/IfaMutualFundAccumulateBulkEditableConfirmFormModel'

export default {
  components: {
    IfaButton,
    IfaRequester,
    IfaMessageArea,
    AccountInfoPanel,
    ConfirmInfoCard,
    OtherInfoCard,
    AlertCheckCard
  },
  props: {
    visible: { type: Boolean, required: true },
    confirmInfo: { type: Object, required: false, default: () => {} }
  },
  emits: ['on-cancel', 'on-close'],
  data() {
    return {
      // status -> confirm | complete
      status: 'confirm',
      info: this.confirmInfo ?? {},
      form: {},
      messages: {
        mains: [],
        errors: [],
        warns: []
      },
      messageKey: 0
    }
  },
  computed: {
    isVisible() {
      return this.visible
    },
    confirmTitle() {
      return this.status === 'confirm'
        ? '投信積立設定一括変更確認'
        : '投信積立設定一括変更完了'
    },
    confirmBtnDisabled() {
      const { privId } = this.$store.getters.userAccount.medUsers

      if (privId === '1' || privId === '2') return true

      const bulkCheckIsInvalid = this.info?.bulkChangeList?.some((item, idx) => {
        const formItem = this.form.dynamicList[idx]

        if (item.complianceRankCheck?.message) {
          if (!formItem.complianceRankCheckConfirm) return true
        }
        if (item.complianceRankCheck?.startCriteriaConfirmMsg) {
          if (formItem.complianceRankCheckStartBaseConfirm !== '1') return true
        }
        return false
      })

      const alertsInvalid = [
        this.info?.noticeInfoAlert && this.form.noticeInfoAlertConfirm !== '1',
        this.info?.noticeAlert && this.form.noticeAlertConfirm !== '1',
        this.info?.confirmDocumentAlert && this.form.confirmDocumentAlertConfirm !== '1'
      ].some(Boolean)

      return bulkCheckIsInvalid || alertsInvalid
    },
    showWarning() {
      const {
        noticeInfoAlert,
        noticeAlert,
        confirmDocumentAlert
      } = this.info

      return noticeInfoAlert || noticeAlert || confirmDocumentAlert
    },
    settingBulkChangeConfirmRequestModel() {
      return new IfaMutualFundAccumulateSettingBulkChangeConfirmA001RequestModel(this.form, this.info)
    }
  },
  watch: {
    status(_curr, _prev) {
      this.setMsgs()
    }
  },
  mounted() {
    this.setMsgs()
    this.info = this.confirmInfo
    this.form = new IfaMutualFundAccumulateBulkEditableConfirmFormModel(this.info)
  },
  methods: {
    handleCancel() {
      this.$emit('on-cancel')
    },
    handleSubmit() {
      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingBulkChangeRegisterA001').click()
      })
    },
    handleRedirect() {
      this.$emit('on-close')
    },
    setMsgs() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warns = []

      if (this.status === 'confirm') {
        this.messages.mains.push('設定変更はまだ完了していません。画面下の設定変更登録ボタンを押下してください。')

        if (this.info?.noticeInfoAlert) {
          this.messages.errors.push(this.info?.noticeInfoAlert)
        }

        if (this.info?.noticeAlert) {
          this.messages.errors.push(this.info?.noticeAlert)
        }

        if (this.info?.confirmDocumentAlert) {
          this.messages.errors.push(this.info?.confirmDocumentAlert)
        }
      }

      if (this.status === 'complete') {
        this.messages.mains.push('下記の内容で積立設定変更を受け付けました。')
      }

      this.messageKey++
    },
    settingBulkChangeConfirmHandler(res) {
      this.$_logDebug(res)

      this.info = res?.dataList?.[0] ?? {}

      this.$refs.formRef.resetFields()

      this.status = 'complete'

      this.$nextTick(() => {
        const dialog = document.getElementById('bulk-editable-confirm')

        dialog.scrollTo({
          top: 0,
          behavior: 'smooth'
        })
      })
    },
    settingBulkChangeConfirmErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
.wrapper {
  padding: 0 30px;
  display: flex;
  flex-direction: column;

  .padding {
    padding-left: 0;
    padding-right: 0;
  }
}

:deep(.main-message) {
  padding: 0;
}

:deep(.error-message) {
  padding: 0 1rem;
  display: flex;
  flex-direction: column;
  row-gap: 5px;
}

:deep(.warning-message) {
  padding: 0 1rem;
  display: flex;
  flex-direction: column;
  row-gap: 5px;
}

:deep(.el-divider--horizontal) {
  margin: 16px 0;
  border-width: 2px;
}
</style>
