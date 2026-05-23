<template>
  <div
    class="flex flex-col"
    style="row-gap: 10px;"
  >
    <el-card style="background-color: #eeeeee;">
      <div class="ctr">
        <div class="w-full flex flex-col row-gap-10">
          <div class="flex items-center border-bottom column-gap-30">
            <span
              class="flex items-center title-text"
              style="font-size: 15px; column-gap: 6px;"
            >
              <span>1ヵ月あたりの積立金額の概算</span>
              <span
                v-if="status === 'overview' && !!oneMonthSumAmount?.oneMonthComment"
                class="icon-wrapper"
              >
                <ifa-balloon-icon
                  icon-type="info"
                  :message="oneMonthSumAmount?.oneMonthComment"
                  :show-msg-on-mount="!isRedirect"
                >
                </ifa-balloon-icon>
              </span>
            </span>
            <span class="text-sm word-wrap">
              {{ formatYen(oneMonthSumAmount?.settingAmount) }}
            </span>
          </div>
          <div class="w-full flex column-gap-30 pl-30">
            <div class="w-full">
              <div class="border-bottom title-text">決済方法</div>
              <div class="pl-30">
                <div class="flex items-center justify-between">
                  <div class="title-text">現金</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneMonthSumAmount?.cashSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">クレジットカード</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneMonthSumAmount?.creditCardSettingAmount) }}
                  </div>
                </div>
              </div>
            </div>
            <div class="w-full">
              <div class="border-bottom title-text">預り区分</div>
              <div class="pl-30">
                <div class="flex items-center justify-between">
                  <div class="title-text">特定/一般</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneMonthSumAmount?.normalSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（成長投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneMonthSumAmount?.nisaGrowthSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（つみたて投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneMonthSumAmount?.nisaReserveSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-end">
                  <div class="text-sm word-wrap">
                    {{ `/${formatYen(oneMonthSumAmount?.oneMonthLimitNisaReserveAmount)}` }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="w-full flex flex-col row-gap-10">
          <div class="flex items-center border-bottom column-gap-30">
            <span
              class="flex items-center title-text"
              style="font-size: 15px; column-gap: 6px;"
            >
              <span>1年あたりの積立金額の概算</span>
              <span
                v-if="status === 'overview' && !!oneYearSumAmount?.oneYearComment"
                class="icon-wrapper"
              >
                <ifa-balloon-icon
                  icon-type="info"
                  :message="oneYearSumAmount?.oneYearComment"
                  :show-msg-on-mount="!isRedirect"
                >
                </ifa-balloon-icon>
              </span>
            </span>
            <span class="text-sm word-wrap">
              {{ formatYen(oneYearSumAmount?.settingAmount) }}
            </span>
          </div>
          <div class="w-full flex column-gap-30 pl-30">
            <div class="w-full">
              <div class="border-bottom title-text">決済方法</div>
              <div class="pl-30">
                <div class="flex items-center justify-between">
                  <div class="title-text">現金</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneYearSumAmount?.cashSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">クレジットカード</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneYearSumAmount?.creditCardSettingAmount) }}
                  </div>
                </div>
              </div>
            </div>
            <div class="w-full">
              <div class="border-bottom title-text">預り区分</div>
              <div class="pl-30">
                <div class="flex items-center justify-between">
                  <div class="title-text">特定/一般</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneYearSumAmount?.normalSettingAmount) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（成長投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneYearSumAmount?.nisaGrowthSettingAmount) }}

                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（つみたて投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(oneYearSumAmount?.nisaReserveSettingAmount) }}

                  </div>
                </div>
                <div class="flex items-center justify-end">
                  <div class="text-sm word-wrap">
                    {{ `/${formatYen(oneYearSumAmount?.oneYearLimitNisaReserveAmount)}` }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="w-full flex flex-col row-gap-10">
          <div class="flex items-center border-bottom column-gap-30">
            <span
              class="flex items-center title-text"
              style="font-size: 15px; column-gap: 30px;"
            >
              ボーナス月設定の年間合計額
            </span>
            <span class="text-sm word-wrap">
              {{ formatYen(bonusMonthSettingInfo?.bonusSumAmount) }}
            </span>
          </div>
          <div class="w-full flex column-gap-30 pl-30">
            <div class="w-half"></div>
            <div class="w-half">
              <div class="border-bottom title-text">預り区分</div>
              <div class="pl-30">
                <div class="flex items-center justify-between">
                  <div class="title-text">特定/一般</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(bonusMonthSettingInfo?.bonusSumAmountNormal) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（成長投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(bonusMonthSettingInfo?.bonusSumAmountNisaGrowth) }}
                  </div>
                </div>
                <div class="flex items-center justify-between">
                  <div class="title-text">NISA（つみたて投資枠）</div>
                  <div class="text-sm word-wrap">
                    {{ formatYen(bonusMonthSettingInfo?.bonusSumAmountNisaReserve) }}
                  </div>
                </div>
                <div class="flex items-center justify-end">
                  <div class="text-sm word-wrap">
                    {{ `/${formatYen(bonusMonthSettingInfo?.limitNisaReserveAmount)}` }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="w-full flex flex-col row-gap-10">
          <div class="flex items-center border-bottom column-gap-30">
            <span
              class="title-text"
              style="font-size: 15px;"
            >
              積立注文ポイント利用の設定
            </span>
            <span class="text-sm">{{ pointSettingText }}</span>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import IfaUtils from '@/utils/ifaUtils'
import IfaBalloonIcon from '@/components/icon/IfaBalloonIcon.vue'

export default {
  components: {
    IfaBalloonIcon
  },
  props: {
    baseInfo: {
      type: Object,
      required: false,
      default: () => {}
    },
    status: {
      type: String,
      required: true
    },
    isRedirect: {
      type: Boolean,
      required: false,
      default: false
    }
  },
  computed: {
    oneMonthSumAmount() {
      return this.baseInfo?.oneMonthSumAmount
    },
    oneYearSumAmount() {
      return this.baseInfo?.oneYearSumAmount
    },
    bonusMonthSettingInfo() {
      return this.baseInfo?.bonusMonthSettingInfo
    },
    pointSettingInfo() {
      return this.baseInfo?.pointSettingInfo
    },
    pointSettingText() {
      const limit = this.pointSettingInfo?.pointUseUpperLimit
      const setting = this.pointSettingInfo?.pointSetting

      if (limit) {
        return `${setting.replaceAll('&{pointLimit}', this.$_addComma(limit))}`
      }

      return this.pointSettingInfo?.pointSetting
    }
  },
  methods: {
    formatYen(val) {
      if (!val) return `${0}${' '}円`
      return `${IfaUtils.addComma(val)}${' '}円`
    }
  }
}
</script>

<style lang="scss" scoped>
  .ctr {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    padding: 15px;
    gap: 20px;
  }

  .w-full {
    width: 100%;
  }

  .w-half {
    width: 50%;
  }

  .flex {
    display: flex;
  }

  .flex-col {
    flex-direction: column;
  }

  .items-center {
    align-items: center;
  }

  .justify-between {
    justify-content: space-between;
  }

  .justify-end {
    justify-content: flex-end;
  }

  .border-bottom {
    border-bottom: 1px solid #bfcbd9;
  }

  .column-gap-30 {
    column-gap: 30px;
  }

  .row-gap-10 {
    row-gap: 10px;
  }

  .pl-30 {
    padding-left: 30px;
  }

  .p-15 {
    padding: 15px;
  }

  .title-text {
    color: #606266;
    font-weight: 700;
    font-size: 14px;
    line-height: 1.5;
  }

  .text-sm {
    font-size: 12px
  }

  .word-wrap {
    word-break: break-all;
  }

  .icon-wrapper {
    display: flex;
    align-items: center;
  }

  :deep(.el-icon-info) {
    font-size: 20px;
    padding-bottom: 1.5px;
  }
</style>
