<template>
  <el-form
    ref="formRef"
    :model="form"
    label-width="160px"
    label-position="left"
  >
    <div style="display: flex; flex-direction: column; row-gap: 10px;">
      <div style="display: flex; align-items: center; justify-content: flex-end;">
        <ifa-button
          id="btnReset"
          text="リセット"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="handleReset"
        ></ifa-button>
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          @app-action-handler="handleClose"
        ></ifa-button>
      </div>
      <el-card style="background-color: #eeeeee;">
        <bulk-editable-item
          :info="info"
          :form="form"
          @on-course-type-change="handleCourseTypeChange"
        ></bulk-editable-item>
        <confirm-item :form="form"></confirm-item>
      </el-card>
      <ifa-button
        text="設定変更確認"
        color="primary"
        action-type="originalAction"
        style="padding: 0"
        @app-action-handler="handleValidate"
      ></ifa-button>
    </div>
  </el-form>

  <ifa-mutual-fund-accumulate-bulk-editable-confirm
    v-if="confirmVisible"
    :visible="confirmVisible"
    :confirm-info="confirmInfo ?? {}"
    @on-cancel="handleCloseFormPanel"
    @on-close="handleClose"
  ></ifa-mutual-fund-accumulate-bulk-editable-confirm>

  <ifa-requester
    id="ifaMutualFundAccumulateSettingBulkChangeInputConfirmA005"
    action-id="SUB0202_0403-05_2#A005"
    action-type="requestAction"
    :request-model="confirmRequestModel"
    @response-handler="confirmHandler($event)"
    @response-error-handler="confirmErrorHandler"
  ></ifa-requester>
</template>

<script>
import IfaButton from '@/components/Button/IfaButton.vue'
import IfaRequester from '@/components/Button/IfaRequester.vue'

import BulkEditableItem from './BulkEditableItem.vue'
import ConfirmItem from './ConfirmItem.vue'
import IfaMutualFundAccumulateBulkEditableConfirm from '../IfaMutualFundAccumulateBulkEditableConfirm/index.vue'

import { IfaMutualFundAccumulateSettingBulkChangeInputFormModel } from '../../js/IfaMutualFundAccumulateSettingBulkChangeInputFormModel'
import { IfaMutualFundAccumulateSettingBulkChangeInputConfirmA005RequestModel } from '../../js/IfaMutualFundAccumulateSettingBulkChangeInputConfirmA005RequestModel'

export default {
  components: {
    IfaButton,
    IfaRequester,
    IfaMutualFundAccumulateBulkEditableConfirm,
    BulkEditableItem,
    ConfirmItem
  },
  props: {
    info: { type: Object, required: true }
  },
  emits: ['on-close'],
  data() {
    return {
      form: new IfaMutualFundAccumulateSettingBulkChangeInputFormModel(this.info),
      confirmVisible: false,
      confirmInfo: null
    }
  },
  computed: {
    confirmRequestModel() {
      return new IfaMutualFundAccumulateSettingBulkChangeInputConfirmA005RequestModel(this.form, this.info)
    }
  },
  methods: {
    handleReset() {
      this.$refs.formRef.resetFields()
      this.form = new IfaMutualFundAccumulateSettingBulkChangeInputFormModel(this.info)
    },
    handleCloseFormPanel() {
      this.confirmVisible = false
      this.confirmInfo = null
    },
    handleClose() {
      this.confirmVisible = false
      this.$emit('on-close')
    },
    handleCourseTypeChange(idx) {
      this.form.dynamicList[idx].settingReserveWeek = '0'
      this.form.dynamicList[idx].settingReserveMonth = '00'
      this.form.dynamicList[idx].settingReserveOddDay = '00'
      this.form.dynamicList[idx].settingReserveEvenDay = '00'
      this.form.dynamicList[idx].settingReserveMultiDay = []
    },
    handleValidate() {
      const formEl = this.$refs.formRef
      formEl.validate((valid) => {
        if (valid) {
          this.$nextTick(() => {
            document.getElementById('ifaMutualFundAccumulateSettingBulkChangeInputConfirmA005').click()
          })
        }
      })
    },
    confirmHandler(res) {
      this.$_logDebug(res)

      this.confirmInfo = res?.dataList?.[0] ?? {}
      this.confirmVisible = true
    },
    confirmErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
:deep(.el-form.el-form--label-left .el-form-item.asterisk-left .el-form-item__label) {
  align-self: flex-start;
}
</style>
