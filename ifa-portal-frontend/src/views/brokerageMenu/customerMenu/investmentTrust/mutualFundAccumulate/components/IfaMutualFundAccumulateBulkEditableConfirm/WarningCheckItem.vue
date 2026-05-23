<template>
  <el-card
    v-if="item?.complianceRankCheck?.message || item?.complianceRankCheck?.startCriteriaConfirmMsg"
    style="background-color: #fef0f0;"
  >
    <el-row
      class="_bold_black_m"
      style="padding-top: 0.5rem; padding-left: 1rem; color: #f00;"
    >
      アラート内容確認
    </el-row>

    <el-divider style="border-width: 2px; margin: 8px 0;"></el-divider>

    <!-- コンプラランクチェック -->
    <el-row
      v-if="item?.complianceRankCheck?.message"
      class="dotted_border"
      style="align-items: center; min-height: 45px;"
    >
      <el-col
        :span="8"
        class="_bold_red_alert"
      >
        <div
          v-if="status === 'confirm'"
          class="required-mark"
        >*</div>
        <ifa-text
          style="color: #f00; font-size: 16px;"
          code-list-id="COMPLA_CHECK_BOX_WORDING"
          :disp-pattern="3"
          :select-pattern="1"
          :code-key="item?.complianceRankCheck?.invitationCheck"
        ></ifa-text>
      </el-col>
      <el-col :span="12">
        <el-form-item
          v-if="status === 'confirm'"
          :prop="`dynamicList.${idx}.complianceRankCheckConfirm`"
          :required="true"
          :rules="[
            {
              validator: (_rule, value, callback) => {
                if (value) {
                  callback()
                } else {
                  callback('項目を選択してください。')
                }
              },
              trigger: 'change'
            }
          ]"
        >
          <el-checkbox
            v-model="form.dynamicList[idx].complianceRankCheckConfirm"
            style="color: #f00; font-size: 16px; font-weight: 700;"
            class="checkbox-style"
            :label="item?.complianceRankCheck?.invitationCheck === '1'
              ? '△・◇ワーニング申請は「申請・承認済」であることを確認済'
              :'勧誘なし'"
          ></el-checkbox>
        </el-form-item>
        <ifa-text
          v-else
          code-list-id="COMPLA_CHECK_BOX_WORDING"
          :disp-pattern="1"
          :select-pattern="1"
          :code-key="item?.complianceRankCheck?.invitationCheck"
          style="font-size:16px; color: #f00; font-weight: 700;"
        ></ifa-text>
      </el-col>
    </el-row>

    <!-- コンプラランクチェック開始基準確認 -->
    <el-row
      v-if="item?.complianceRankCheck?.startCriteriaConfirmMsg"
      class="dotted_border"
      style="align-items: center; min-height: 45px;"
    >
      <el-col
        :span="8"
        class="_bold_red_alert"
      >
        <div
          v-if="status === 'confirm'"
          class="required-mark"
        >*</div>
        <span style="color: #f00;">開始基準の確認</span>
      </el-col>
      <el-col :span="16">
        <ifa-input-check
          v-if="status === 'confirm'"
          v-model="form.dynamicList[idx].complianceRankCheckStartBaseConfirm"
          :prop="`dynamicList.${idx}.complianceRankCheckStartBaseConfirm`"
          label-class="text-red"
          code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
          :disp-pattern="1"
          :select-pattern="2"
          required
        ></ifa-input-check>
        <ifa-text
          v-else
          code-list-id="COMPLIANCE_START_CRITERIA_CONFIRM_CHECK_BOX_WORDING"
          :disp-pattern="1"
          :select-pattern="2"
          code-key="1"
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
    form: { type: Object, required: true },
    item: { type: Object, required: true },
    idx: { type: Number, required: true },
    status: { type: String, required: true }
  }
}
</script>

<style scoped lang="scss">
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

:deep(.el-checkbox_label) {
  font-size: 16px;
  font-weight: 700;
}

:deep(.checkbox-style) .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #f00;
  font-weight: 700;
}

.required-mark {
  display: inline-block;
  color: #ff2b2b;
  width: 8px;
}
</style>
