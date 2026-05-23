<template>
  <el-row>
    <div
      class="el-form-item__label fake-label"
      style="font-weight: 700;"
    >
      積立金額
    </div>
    <div style="display: flex; align-items: center; font-weight: normal;">
      <ifa-input-amount
        v-model="form.dynamicList[idx].settingAmount"
        label="金額"
        :prop="`dynamicList.${idx}.settingAmount`"
        label-class="setting-amount-label-class"
        unit="円"
        required
        :min="0"
        :domain="IfaYen80DomainModel"
      ></ifa-input-amount>
      <div style="height: 33px; margin-bottom: 5px; display: flex; align-items: center; align-self: baseline; white-space: normal;">
        （手数料・消費税込）
      </div>
      <div style="height: 33px; margin-bottom: 5px; display: flex; align-items: center; align-self: baseline; padding-left: 10px;">
        <span>上限：</span>
        <span>
          {{ upperLimitAmount(
            info?.oneYearLimitNisaReserveAmount,
            item?.accountType,
            form.dynamicList[idx].courseType
          ) }}
        </span>
      </div>
    </div>
  </el-row>
</template>

<script>
import BigNumber from 'bignumber.js'

import IfaInputAmount from '@/components/Input/IfaInputAmount.vue'

import IfaYen80DomainModel from '@/resource/domain/IfaYen80DomainModel.json'
import IfaUtils from '@/utils/ifaUtils'

export default {
  components: {
    IfaInputAmount
  },
  props: {
    info: { type: Object, required: true },
    form: { type: Object, required: true },
    item: { type: Object, required: true },
    idx: { type: Number, required: true }
  },
  data() {
    return {
      IfaYen80DomainModel
    }
  },
  methods: {
    upperLimitAmount(oneYearLimitNisaReserveAmount, accountType, courseType) {
      const DEFAULT_YEN_VAL = '-円'

      // 1年あたりのNISA（つみたて投資枠）設定金額上限
      const oneYearLimitNisaReserveAmountVal = new BigNumber(oneYearLimitNisaReserveAmount)

      if (accountType !== 'I') return DEFAULT_YEN_VAL

      const courseDivisors = {
        // 247日
        '1': new BigNumber(247),
        // 52週
        '2': new BigNumber(52),
        // 12ヶ月
        '3': new BigNumber(12)
      }

      const divisor = courseDivisors[courseType]
      if (!divisor) return DEFAULT_YEN_VAL

      // ※ 算出結果に端数が発生する場合は、小数点以下切り捨てとする。"
      // e.g: 123.111 -> 123
      const result = oneYearLimitNisaReserveAmountVal
        .dividedBy(divisor)
        .integerValue(BigNumber.ROUND_DOWN)

      return `${IfaUtils.addComma(result.toString())}円`
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
  &::before {
    content: '*';
    color: #f56c6c;
  }
}

:deep(.setting-amount-label-class) .el-form-item__label {
  width: auto !important;
  line-height: normal;
  font-weight: normal;
  padding: 0;
  margin-left: -5px;
  margin-right: 6px;
  align-self: baseline !important;

  &::before {
    content: '' !important;
  }
}

:deep(.setting-amount-label-class) .el-form-item__content .ifa-input_base__wrapper {
  min-width: auto;
  .ifa-input_base {
    margin-right: 0;
  }
}
</style>
