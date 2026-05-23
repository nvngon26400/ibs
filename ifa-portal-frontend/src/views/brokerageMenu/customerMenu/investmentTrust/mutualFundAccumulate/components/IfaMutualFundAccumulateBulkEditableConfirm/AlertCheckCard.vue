<template>
  <el-card style="background-color: #fef0f0;">
    <el-row
      class="_bold_black_m"
      style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
    >
      アラート内容確認
    </el-row>

    <el-divider style="border-width: 2px; margin: 8px 0;"></el-divider>

    <!-- 注意情報アラート確認 -->
    <el-row
      v-if="info?.noticeInfoAlert"
      class="dotted_border"
      style="align-items: center; min-height: 45px;"
    >
      <el-col
        :span="8"
        class="_bold_red_alert"
      >
        <span
          v-if="status === 'confirm'"
          class="required-mark"
        >*</span>
        <span style="color: #f00;">注意情報の確認</span>
      </el-col>
      <el-col :span="16">
        <ifa-input-check
          v-if="status === 'confirm'"
          v-model="form.noticeInfoAlertConfirm"
          prop="noticeInfoAlertConfirm"
          label-class="text-red"
          code-list-id="NOTICE_INFO_CONFIRM"
          required
          :disp-pattern="1"
          :select-pattern="2"
        ></ifa-input-check>
        <ifa-text
          v-else
          code-list-id="NOTICE_INFO_CONFIRM"
          :disp-pattern="1"
          :select-pattern="2"
          :code-key="info?.noticeInfoAlertConfirm"
          style="font-size:16px; color: #f00; font-weight: 700;"
        ></ifa-text>
      </el-col>
    </el-row>

    <!-- お知らせ情報アラート確認 -->
    <el-row
      v-if="info?.noticeAlert"
      class="dotted_border"
      style="align-items: center; min-height: 45px;"
    >
      <el-col
        :span="8"
        class="_bold_red_alert"
      >
        <span
          v-if="status === 'confirm'"
          class="required-mark"
        >*</span>
        <span style="color: #f00;">重要なお知らせの確認</span>
      </el-col>
      <el-col :span="16">
        <ifa-input-check
          v-if="status === 'confirm'"
          v-model="form.noticeAlertConfirm"
          prop="noticeAlertConfirm"
          label-class="text-red"
          code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
          required
          :disp-pattern="1"
          :select-pattern="2"
        ></ifa-input-check>
        <ifa-text
          v-else
          code-list-id="IMPORTANT_NOTIFICATION_CONFIRM"
          :disp-pattern="1"
          :code-key="info?.noticeAlertConfirm"
          style="font-size:16px; color: #f00; font-weight: 700;"
        ></ifa-text>
      </el-col>
    </el-row>

    <!--確認書受け入れの確認-->
    <el-row
      v-if="info?.confirmDocumentAlert"
      class="dotted_border"
      style="align-items: center; min-height: 45px;"
    >
      <el-col
        :span="8"
        class="_bold_red_alert"
      >
        <span
          v-if="status === 'confirm'"
          class="required-mark"
        >*</span>
        <span style="color: #f00;">確認書受け入れの確認</span>
      </el-col>
      <el-col :span="16">
        <ifa-input-check
          v-if="status === 'confirm'"
          v-model="form.confirmDocumentAlertConfirm"
          prop="confirmDocumentAlertConfirm"
          label-class="text-red"
          style="margin-left: 5px; color: #f00;"
          code-list-id="CONFIRMATION_LETTER_ACCEPT_CONFIRM"
          required
          :disp-pattern="1"
          :select-pattern="2"
        ></ifa-input-check>
        <ifa-text
          v-else
          code-list-id="CONFIRMATION_LETTER_ACCEPT_CONFIRM"
          :disp-pattern="1"
          :code-key="info?.confirmDocumentAlertConfirm"
          style="font-size:16px; color: #f00; font-weight: 700;"
        ></ifa-text>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import IfaText from '@/components/Input/IfaText.vue'
import IfaInputCheck from '@/components/Input/IfaInputCheck.vue'

export default {
  components: {
    IfaText,
    IfaInputCheck
  },
  props: {
    status: { type: String, required: true },
    info: { type: Object, required: true },
    form: { type: Object, required: true }
  }
}
</script>

<style lang="scss" scoped>
.bordered-bottom-solid {
  border-bottom: 1px solid #dddddd;
  display: flex;
  align-items: center;
  padding: 0.25rem 0;
  line-height: normal;
}

.bordered-bottom-solid-sub {
  border-bottom: 1px solid #dddddd;
  display: flex;
  align-items: center;
  padding: 0.25rem 0 0.25rem 1rem;
  line-height: normal;
}

.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}

.error-message-item {
  margin: 0;
  padding: 0 1rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  row-gap: 5px;
}

._bold_red_alert {
  font-size: 16px;
  font-weight: bold;
  padding-right: 0.5rem;
  color: red;
}

:deep(.text-red) .el-checkbox__label {
  color: #f00;
  font-weight: 700;
}

:deep(.el-checkbox__label) {
  font-size: 16px;
  font-weight: 700;
}

:deep(.checkbox-style) .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #f00;
  font-weight: 700;
}

:deep(.el-divider--horizontal) {
  margin: 16px 0;
  border-width: 2px;
}
</style>
