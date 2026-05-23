<template>
  <el-card style="background-color: #eeeeee;">
    <div v-show="status === 'overview'">
      <div style="display: flex; align-items: center;column-gap: 30px; padding:15px;">
        <div>
          <span class="text-header">次回発注予定日：</span>
          <span>{{ `${subInfo?.orderPlanDate ?? '--年--月--日'}` }}</span>
        </div>
        <div>
          <span class="text-header">発注金額：</span>
          <span>{{ formatYen(subInfo?.orderPlanAmount) }}</span>
        </div>
      </div>
    </div>

    <div v-show="status === 'create' || status === 'update'">
      <div style="display: flex; flex-direction: column; row-gap: 10px; padding:15px;">
        <div style="display: flex; align-items: center; column-gap: 20px;">
          <span>銘柄：</span>
          <span>{{ `[${subInfo?.mfkaisu}.${' '}${subInfo?.mfgo}]` }}</span>
          <span>{{ subInfo?.fundName }}</span>
        </div>
        <div style="display: flex; align-items: center; column-gap: 30px;">
          <div style="display: flex; column-gap: 10px;">
            <span style="padding-right: 30px">基準価格：</span>
            <span>
              {{ subInfo?.standardPriceStr !== '-'
                ? formatYen(subInfo?.standardPriceStr)
                : '-' }}
            </span>
            <ifa-text
              :style="tickColor"
              code-list-id="CURRENT_TICK"
              :disp-pattern="1"
              :select-pattern="1"
              :code-key="subInfo?.tick"
            ></ifa-text>
            <span>{{ `/${formatYen(subInfo?.standardPriceUnitStr, '')}` }}</span>
          </div>
          <div>
            <span style="padding-right: 30px">前日比：</span>
            <span :style="previousChangeStyle">
              {{ subInfo?.standardPriceStr !== '-'
                ? `${previousChangeSign}${subInfo?.previousChangeStr}`
                : '-' }}
            </span>
            <span>
              <span>（</span>
              <span :style="previousChangeStyle">
                {{ `${previousChangeSign}${subInfo?.previousRatio}%` }}
              </span>
              <span>）</span>
            </span>
          </div>
          <div>
            <span style="padding-right: 30px">純資産：</span>
            <span>{{ subInfo?.netAsset !== '-'
              ? formatYen(subInfo?.netAsset,'百万円')
              : '-'
            }}</span>
            <span>{{ subInfo?.priceDate }}</span>
          </div>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
import IfaUtils from '@/utils/ifaUtils'
import IfaText from '@/components/Input/IfaText.vue'

export default {
  components: {
    IfaText
  },
  props: {
    status: { type: String, required: true },
    subInfo: { type: Object, required: false, default: () => {} }
  },
  data() {
    return {}
  },
  computed: {
    tickColor() {
      switch (this.subInfo?.tick) {
        // 赤：#E5004C
        case '1':
          return 'color: #E5004C; font-weight: bold'
        // 青緑：#00847F
        case '2':
          return 'color: #00847F; font-weight: bold'
        default:
          return 'color: #606266; font-weight: bold;'
      }
    },
    previousChangeSign() {
      if (this.subInfo?.previousChangeSign === '0') return ''
      return this.subInfo?.previousChangeSign
    },
    previousChangeStyle() {
      switch (this.subInfo?.previousChangeSign) {
        case '+':
          // 赤：#E5004C
          return 'color: #E5004C;'
        case '-':
          // 青緑：#00847F
          return 'color: #00847F;'
        default:
          return 'color: #606266'
      }
    }
  },
  methods: {
    formatYen(val, unit) {
      if (!val) return `${0}${unit ?? '円'}`
      return `${IfaUtils.addComma(val)}${unit ?? '円'}`
    }
  }
}
</script>

<style lang="scss" scoped>
.text-header {
  padding-right: 30px;
  font-weight: 700;
  color: #606266;
}
</style>
