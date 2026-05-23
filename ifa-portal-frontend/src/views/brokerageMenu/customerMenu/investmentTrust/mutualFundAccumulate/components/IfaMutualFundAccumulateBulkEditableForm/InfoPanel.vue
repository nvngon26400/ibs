<template>
  <div class="form-area__section">
    <el-row>
      <el-col :span="21">
        <el-form-item
          label="ボーナス月設定"
          style="font-weight: 700;"
          class="label-item-class"
        >
          <div style="font-weight: normal;">
            <template v-if="item?.settingBonusFlag === '1'">
              <span>積立金額（1回あたり）：</span>
              <span>{{ formatYen(item?.settingBonusAmount) }}</span>
            </template>
            <template v-else>
              <span>設定なし</span>
            </template>
          </div>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row v-if="item?.settingBonusFlag === '1'">
      <el-col :span="21">
        <el-form-item
          label=" "
          style="font-weight: 700;"
        >
          <div style="font-weight: normal;">
            <span>申込設定日：</span>
            <span>{{ formatSettingBonusDate(item) }}</span>
          </div>
        </el-form-item>
      </el-col>
    </el-row>
  </div>

  <div
    v-if="(item?.accountType === 'H' || item?.accountType === 'I') &&
      item?.nisaBarelyBuyingType &&
      item?.nisaBarelyBuyingType !== '0'"
    class="form-area__section"
  >
    <el-row>
      <el-col :span="21">
        <el-form-item
          label="NISA枠ぎりぎり注文"
          style="font-weight: 700;"
        >
          <span style="font-weight: normal;">
            {{ $_getCodeValue('FUND_RESERVE_NISA_BARELY_BUYING_KBN', 2, item?.nisaBarelyBuyingType) }}
          </span>
        </el-form-item>
      </el-col>
    </el-row>
  </div>

  <div
    v-if="item?.accountType === 'H' &&
      item?.taxShiftType &&
      item?.taxShiftType !== '0'"
    class="form-area__section"
  >
    <el-row>
      <el-col :span="21">
        <el-form-item
          label="課税枠シフト注文"
          style="font-weight: 700;"
        >
          <span style="font-weight: normal;">
            {{ $_getCodeValue('FUND_RESERVE_TAX_SHIFT_KBN', 2, item?.taxShiftType) }}
          </span>
        </el-form-item>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import IfaUtils from '@/utils/ifaUtils'

export default {
  props: {
    item: { type: Object, required: true }
  },
  methods: {
    formatYen(val) {
      if (!val) return `${0}${' '}円`
      return `${IfaUtils.addComma(val)}${' '}円`
    },
    formatSettingBonusDate(item) {
      const DEFAULT_VAL = '-'
      const END_OF_MONTH = '月末'

      if (!item?.settingBonus1Month || !item?.settingBonus1Day) return DEFAULT_VAL

      const formatBonusDay = day => day === '31' ? END_OF_MONTH : day

      const date1 = `${item.settingBonus1Month}/${formatBonusDay(item.settingBonus1Day)}`

      if (item?.settingBonus2Month && item?.settingBonus2Day) {
        const date2 = `${item.settingBonus2Month}/${formatBonusDay(item.settingBonus2Day)}`
        return `${date1},${date2}`
      }

      return date1
    }
  }
}
</script>

<style scoped lang="scss">
.form-area__section {
  height: auto;
  white-space: pre-wrap;
}

:deep(.label-item-class) .el-form-item__label {
  align-self: baseline !important;
}
</style>
