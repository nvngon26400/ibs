<template>
  <template
    v-for="(item, idx) in info?.bulkChangeList"
    :key="idx"
  >
    <el-row>
      <el-col :span="24">
        <div class="form-area__section">
          <el-form-item
            label="銘柄"
            style="font-weight: 700;"
          >
            <div style="display: flex; align-items: center; column-gap: 10px; font-weight: normal;">
              <span style="min-width: 65px;">
                {{ `[${item?.brandCode ?? '-'}]` }}
              </span>
              <span>{{ item?.fundName ?? '-' }}</span>
            </div>
          </el-form-item>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="14">
        <div class="form-area__section">
          <el-form-item
            style="font-weight: 700;"
            label="預り区分"
          >
            <ifa-text
              style="font-weight: normal;"
              code-list-id="RESERVE_TRADE_DEPOSIT_TYPE"
              :disp-pattern="info?.openedJnisa === '1' ? 2 : 1"
              :select-pattern="info?.openedJnisa === '1' ? 2 : 1"
              :code-key="item?.accountType"
            ></ifa-text>
          </el-form-item>
        </div>

        <div class="form-area__section">
          <ifa-input-radio
            v-model="form.dynamicList[idx].courseType"
            :prop="`dynamicList.${idx}.courseType`"
            label="積立コース"
            required
            code-list-id="FUND_RESERVE_COURSE_KBN"
            :disp-pattern="item?.accountType === 'I' ? 2 : 1"
            :select-pattern="item?.accountType === 'I' ? 2 : 1"
            @change="handleCourseTypeChange(idx)"
          ></ifa-input-radio>
        </div>

        <div class="form-area__section">
          <setting-reserve-date-panel
            :form="form"
            :idx="idx"
          ></setting-reserve-date-panel>
        </div>

        <div class="form-area__section">
          <setting-amount-panel
            :form="form"
            :info="info"
            :item="item"
            :idx="idx"
          ></setting-amount-panel>
        </div>
      </el-col>
      <el-col :span="10">
        <info-panel :item="item"></info-panel>
      </el-col>
    </el-row>
    <el-divider></el-divider>
  </template>
</template>

<script>
import IfaInputRadio from '@/components/Input/IfaInputRadio.vue'
import IfaText from '@/components/Input/IfaText.vue'

import SettingReserveDatePanel from './SettingReserveDatePanel.vue'
import SettingAmountPanel from './SettingAmountPanel.vue'
import InfoPanel from './InfoPanel.vue'

export default {
  components: {
    IfaInputRadio,
    IfaText,
    SettingReserveDatePanel,
    SettingAmountPanel,
    InfoPanel
  },
  props: {
    info: { type: Object, required: true },
    form: { type: Object, required: true }
  },
  emits: ['on-course-type-change'],
  methods: {
    handleCourseTypeChange(idx) {
      this.$emit('on-course-type-change', idx)
    }
  }
}
</script>

<style lang="scss" scoped>
.form-area__section {
  height: auto;
  white-space: pre-wrap;
}

:deep(.el-radio-group) {
  margin-left: 0 !important;
}
</style>
