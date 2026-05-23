<template>
  <el-row>
    <div class="el-form-item__label fake-label">設定日</div>
    <div>
      <div
        v-if="form.dynamicList[idx].courseType === '1'"
        style="height: 100%; display: flex; align-items: center; font-weight: normal; white-space: normal;"
      >
        毎営業日に積立発注
      </div>
      <div
        v-if="form.dynamicList[idx].courseType === '2'"
        style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
      >
        <span style="padding-right: 5px;">毎週</span>
        <ifa-input-select
          v-model="form.dynamicList[idx].settingReserveWeek"
          :prop="`dynamicList.${idx}.settingReserveWeek`"
          style="width: 80px;"
          code-list-id="FUND_RESERVE_WEEKLY_SETTING"
          :select-pattern="1"
          :disp-pattern="1"
          :rules="settingReserveWeekRules"
        >
          <template #suffix>
            <span style="padding-left: 5px">曜日に積立発注</span>
          </template>
        </ifa-input-select>
      </div>
      <div
        v-if="form.dynamicList[idx].courseType === '3'"
        style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
      >
        <span style="padding-right: 5px;">毎月</span>
        <ifa-input-select
          v-model="form.dynamicList[idx].settingReserveMonth"
          :prop="`dynamicList.${idx}.settingReserveMonth`"
          style="width: 80px;"
          required
          code-list-id="FUND_RESERVE_DAY_SETTING"
          :select-pattern="1"
          :disp-pattern="2"
          :rules="settingReserveDayRules"
        >
          <template #suffix>
            <span style="padding-left: 5px;">日に積立発注</span>
          </template>
        </ifa-input-select>
      </div>
      <div
        v-if="form.dynamicList[idx].courseType === '4'"
        style="height: 100%; display: flex; align-items: flex-end; column-gap: 20px; font-weight: normal"
      >
        <div
          style="display: flex; width: 510px; align-items: baseline; flex-wrap: wrap; font-weight: normal"
        >
          <ifa-input-check
            v-model="form.dynamicList[idx].settingReserveMultiDay"
            :prop="`dynamicList.${idx}.settingReserveMultiDay`"
            label="設定日"
            label-class="hidden-label"
            required
            code-list-id="FUND_RESERVE_DAY_SETTING"
            :select-pattern="4"
            :disp-pattern="4"
          ></ifa-input-check>
        </div>
      </div>
      <div
        v-if="form.dynamicList[idx].courseType === '5'"
        style="height: 100%; display: flex; align-items: baseline; font-weight: normal"
      >
        <span style="padding-right: 5px;">奇数月の</span>
        <ifa-input-select
          v-model="form.dynamicList[idx].settingReserveOddDay"
          :prop="`dynamicList.${idx}.settingReserveOddDay`"
          style="width: 80px;"
          required
          code-list-id="FUND_RESERVE_DAY_SETTING"
          :select-pattern="1"
          :disp-pattern="2"
          :rules="settingReserveDayRules"
        >
          <template #suffix>
            <span style="padding-left: 5px;">日に積立発注</span>
          </template>
        </ifa-input-select>
      </div>
      <div
        v-if="form.dynamicList[idx].courseType === '6'"
        style="height: 100%; display: flex; align-items: baseline; font-weight: normal;"
      >
        <span style="padding-right: 5px;">偶数月の</span>
        <ifa-input-select
          v-model="form.dynamicList[idx].settingReserveEvenDay"
          :prop="`dynamicList.${idx}.settingReserveEvenDay`"
          style="width: 80px;"
          code-list-id="FUND_RESERVE_DAY_SETTING"
          :select-pattern="1"
          :disp-pattern="2"
          :rules="settingReserveDayRules"
        >
          <template #suffix>
            <span style="padding-left: 5px;">日に積立発注</span>
          </template>
        </ifa-input-select>
      </div>
    </div>
  </el-row>
</template>

<script>
import IfaInputSelect from '@/components/Input/IfaInputSelect.vue'
import IfaInputCheck from '@/components/Input/IfaInputCheck.vue'

export default {
  components: {
    IfaInputSelect,
    IfaInputCheck
  },
  props: {
    form: { type: Object, required: true },
    idx: { type: Number, required: true }
  },
  computed: {
    settingReserveDayRules() {
      return [
        {
          required: true,
          message: '設定日を選択してください。',
          trigger: 'change'
        },
        {
          validator: (_rule, value, callback) => {
            if (value === '00') {
              callback('設定日を選択してください。')
            } else {
              callback()
            }
          },
          trigger: 'change'
        }
      ]
    },
    settingReserveWeekRules() {
      return [
        {
          required: true,
          message: '設定日を選択してください。',
          trigger: 'change'
        },
        {
          validator: (_rule, value, callback) => {
            if (value === '0') {
              callback('設定日を選択してください。')
            } else {
              callback()
            }
          },
          trigger: 'change'
        }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
:deep(.el-form.el-form--label-left .el-form-item.asterisk-left .el-form-item__label) {
  align-self: flex-start;
}

:deep(.fake-label) {
  width: 160px;
  align-self: flex-start !important;
  font-weight: 700;
  &::before {
    content: '*';
    color: #f56c6c;
    margin-right: 4px;
  }
}

:deep(.hidden-label > .el-form-item__label) {
  display: none !important;
}

:deep(.el-checkbox) {
  margin-right: 5px;
}

:deep(.el-checkbox__label) {
  padding-left: 2px;
}
</style>
