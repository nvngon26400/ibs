<template>
  <el-dialog
    id="cancel-confirm"
    v-model="isVisible"
    :title="title"
    destroy-on-close
    center
    :width="1200"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <div style="display: flex; flex-direction: column; row-gap: 20px;">
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
          @app-action-handler="handleClose"
        ></ifa-button>
      </el-row>

      <ifa-message-area
        :key="messageKey"
        :main-messages="messages.mains"
        :error-messages="messages.errors"
        :warning-messages="messages.warns"
      ></ifa-message-area>

      <div style="display: flex; flex-direction: column; row-gap: 0.5rem;">
        <!-- account info -->
        <div>
          <el-row style="font-weight: bold;">
            <span>{{ confirmInfo.accountNumber }}</span>
          </el-row>

          <el-row
            class="_bold_black_l"
            style="padding-top: 0.5rem; font-size: 20px;"
          >
            <el-col>
              <span style="position: relative; top: 3px;">
                <el-icon>
                  <component
                    :is="confirmInfo.corporationKbn === '1'
                      ? 'OfficeBuilding'
                      : 'Avatar'"
                  ></component>
                </el-icon>
              </span>
              <span>{{ confirmInfo.customerName }}</span>
            </el-col>
          </el-row>
        </div>
        <cancel-confirm-table
          :confirm-info="confirmInfo"
        ></cancel-confirm-table>
      </div>

      <div>
        <ifa-button
          v-if="status === 'confirm'"
          text="設定解除"
          color="primary"
          :disabled="buttonDisabled"
          action-type="originalAction"
          class="confirmBtn"
          @app-action-handler="handleCancelBrands"
        ></ifa-button>
        <ifa-button
          v-if="status === 'complete'"
          text="投信積立設定済銘柄一覧へ"
          color="primary"
          action-type="originalAction"
          class="confirmBtn"
          @app-action-handler="handleClose"
        ></ifa-button>
      </div>
    </div>

    <ifa-requester
      id="ifaMutualFundAccumulateSettingCancelConfirmA003"
      action-id="SUB0202_0403-04_1#A003"
      action-type="requestAction"
      :request-model="A003RequestModel"
      @response-handler="cancelConfirmA003Handler($event)"
      @response-error-handler="cancelConfirmA003ErrorHandler"
    ></ifa-requester>
  </el-dialog>
</template>

<script>
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'

import CancelConfirmTable from './CancelConfirmTable.vue'
import { IfaMutualFundAccumulateCancelConfirmA003RequestModel } from '../../js/IfaMutualFundAccumulateCancelConfirmA003RequestModel'

export default {
  components: {
    IfaButton,
    IfaMessageArea,
    IfaRequester,
    CancelConfirmTable
  },
  props: {
    data: { type: Object, required: true },
    visible: { type: Boolean, required: true }
  },
  emits: ['on-close'],
  data() {
    return {
      // status -> confirm | complete
      status: 'confirm',
      confirmInfo: {},
      cancelConfirmA003RequestModel: null,
      messages: {
        mains: [],
        errors: [],
        warns: []
      },
      messageKey: 0
    }
  },
  computed: {
    title() {
      return this.status === 'confirm'
        ? '投信積立設定解除確認'
        : '投信積立設定解除完了'
    },
    isVisible() {
      return this.visible
    },
    buttonDisabled() {
      const { medUsers: { privId }} = this.$store.getters.userAccount
      return privId === '1' || privId === '2'
    },
    A003RequestModel() {
      return new IfaMutualFundAccumulateCancelConfirmA003RequestModel(this.cancelConfirmA003RequestModel)
    }
  },
  watch: {
    status(_curr, _prev) {
      this.setMsgs()
    }
  },
  mounted() {
    this.setMsgs()
    this.confirmInfo = this?.data ?? {}
  },
  methods: {
    handleCancelBrands() {
      const { settingCancelConfirmList } = this.confirmInfo

      this.cancelConfirmA003RequestModel = settingCancelConfirmList.map(v => ({
        mfgo: v.mfgo,
        mfkaisu: v.mfkaisu,
        fundCode: v.fundCode,
        paymentMethod: v.paymentMethod,
        accountType: v.accountType
      }))

      this.$nextTick(() => {
        document.getElementById('ifaMutualFundAccumulateSettingCancelConfirmA003').click()
      })
    },
    handleClose() {
      this.$emit('on-close')
      this.status = 'confirm'
    },
    setMsgs() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warns = []

      if (this.status === 'confirm') {
        this.messages.mains.push('設定解除はまだ完了していません。画面下の設定解除ボタンを押下してください。')
      }
      if (this.status === 'complete') {
        this.messages.mains.push('下記の内容で積立設定解除を受け付けました。')
      }

      this.messageKey++
    },
    cancelConfirmA003Handler(res) {
      this.$_logDebug(res)

      this.confirmInfo = res?.dataList?.[0] ?? {}
      this.status = 'complete'

      this.$nextTick(() => {
        const dialog = document.getElementById('cancel-confirm')

        dialog.scrollTo({
          top: 0,
          behavior: 'smooth'
        })
      })
    },
    cancelConfirmA003ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style scoped lang="scss">
  :deep(.main-message) {
    padding: 0;
  }
</style>
