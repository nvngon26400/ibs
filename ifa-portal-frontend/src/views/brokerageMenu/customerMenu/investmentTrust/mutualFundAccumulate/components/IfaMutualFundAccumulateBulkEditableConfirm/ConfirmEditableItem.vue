<template>
  <el-card
    class="box-card"
    style="font-size: 16px;"
  >
    <el-row
      class="_bold_black_m"
      style="padding-top: 0.5rem; padding-left: 1rem;"
    >
      <el-col :span="8">
        {{ `ご設定内容${status === 'confirm' ? '（復唱項目）' : ''}` }}
      </el-col>
      <el-col
        :span="7"
        style="padding-left: 6px;"
      >
        変更前
      </el-col>
      <el-col :span="2"></el-col>
      <el-col :span="7">変更後</el-col>
    </el-row>

    <el-divider style="border-width: 2px; margin: 8px 0;"></el-divider>

    <div style="display: flex; flex-direction: column; row-gap: 10px;">
      <div style="padding: 0 0.5rem;">
        <el-row class="bordered-bottom-solid">
          <el-col
            :span="8"
            class="_bold_black_m"
          >
            銘柄名
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 10px;"
          >
            {{ item?.reserveSettingChangeAfter?.fundName ?? '-' }}
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="7"></el-col>
        </el-row>

        <el-row class="bordered-bottom-solid">
          <el-col
            :span="8"
            class="_bold_black_m"
          >
            協会コード
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 10px;"
          >
            {{ item?.reserveSettingChangeAfter?.fundCode ?? '-' }}
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="7"></el-col>
        </el-row>

        <el-row class="bordered-bottom-solid">
          <el-col
            :span="8"
            class="_bold_black_m"
          >
            銘柄コード
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 10px;"
          >
            {{ item?.reserveSettingChangeAfter?.brandCode ?? '-' }}
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="7"></el-col>
        </el-row>

        <el-row class="bordered-bottom-solid">
          <el-col
            :span="8"
            class="_bold_black_m"
          >
            預り区分
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 10px;"
          >
            {{
              $_getCodeValue(
                'RESERVE_TRADE_DEPOSIT_TYPE',
                info?.openedJnisa === '1' ? 2 : 1,
                item?.reserveSettingChangeAfter?.accountType
              )
            }}
          </el-col>
          <el-col :span="2"></el-col>
          <el-col :span="7"></el-col>
        </el-row>
      </div>

      <div style="padding: 0 0.5rem;">
        <el-row class="bordered-bottom-solid">
          <el-col
            :span="8"
            class="_bold_black_m"
          >
            コース
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 10px;"
          >
            {{ courseTypeText(item?.reserveSettingChangeBefore?.courseType) }}
          </el-col>
          <el-col
            :span="2"
            style="padding-left: 6px;"
          >
            {{ item?.reserveSettingChangeBefore?.courseType !== item?.reserveSettingChangeAfter?.courseType
              ? changeArrow
              : '' }}
          </el-col>
          <el-col
            :span="7"
            style="padding-left: 6px;"
          >
            {{ courseTypeText(item?.reserveSettingChangeAfter?.courseType) }}
          </el-col>
        </el-row>

        <el-row class="bordered-bottom-solid-sub">
          <el-col
            class="_bold_black_m"
            :span="8"
          >
            金額
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeBefore?.settingAmount)} 円` }}
          </el-col>
          <el-col :span="2">
            {{ item?.reserveSettingChangeBefore?.settingAmount !== item?.reserveSettingChangeAfter?.settingAmount
              ? changeArrow
              : '' }}
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeAfter?.settingAmount)} 円` }}
          </el-col>
        </el-row>
        <el-row class="bordered-bottom-solid-sub">
          <el-col
            class="_bold_black_m"
            :span="8"
          >
            概算手数料（税込み）
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeBefore?.estimateFundOrder)} 円` }}
          </el-col>
          <el-col :span="2">
            {{ item?.reserveSettingChangeBefore?.estimateFundOrder !== item?.reserveSettingChangeAfter?.estimateFundOrder
              ? changeArrow
              : '' }}
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeAfter?.estimateFundOrder)} 円` }}
          </el-col>
        </el-row>
        <el-row class="bordered-bottom-solid-sub">
          <el-col
            class="_bold_black_m"
            :span="8"
          >
            設定日
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ settingDay(item?.reserveSettingChangeBefore) }}
          </el-col>
          <el-col :span="2">
            {{ settingDay(item?.reserveSettingChangeBefore) !== settingDay(item?.reserveSettingChangeAfter)
              ? changeArrow
              : '' }}
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ settingDay(item?.reserveSettingChangeAfter) }}
          </el-col>
        </el-row>
        <el-row class="bordered-bottom-solid-sub">
          <el-col
            class="_bold_black_m"
            :span="8"
          >
            1ヶ月あたりの設定金額
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeBefore?.oneMonthSumAmount)} 円` }}
          </el-col>
          <el-col :span="2">
            {{ item?.reserveSettingChangeBefore?.oneMonthSumAmount !== item?.reserveSettingChangeAfter?.oneMonthSumAmount
              ? changeArrow
              : '' }}
          </el-col>
          <el-col
            :span="7"
            style="word-break: break-all;"
          >
            {{ `${$_addComma(item?.reserveSettingChangeAfter?.oneMonthSumAmount)} 円` }}
          </el-col>
        </el-row>

        <el-row class="bordered-bottom-solid-sub">
          <el-col
            class="_bold_black_m"
            :span="8"
          >
            次の発注予定日
          </el-col>
          <el-col :span="7">
            {{ item?.reserveSettingChangeBefore?.planDate ?? '-' }}
          </el-col>
          <el-col :span="2">
            {{ item?.reserveSettingChangeBefore?.planDate !== item?.reserveSettingChangeAfter?.planDate
              ? changeArrow
              : '' }}
          </el-col>
          <el-col :span="7">
            {{ item?.reserveSettingChangeAfter?.planDate ?? '-' }}
          </el-col>
        </el-row>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  props: {
    item: { type: Object, required: true },
    info: { type: Object, required: true },
    status: { type: String, required: true }
  },
  computed: {
    changeArrow() {
      return '→'
    }
  },
  methods: {
    courseTypeText(courseType) {
      if (courseType === '1') return '毎日'
      if (courseType === '2') return '毎週'
      if (courseType === '3') return '毎月'
      if (courseType === '4') return '複数日'
      if (courseType === '5') return '奇数月'
      if (courseType === '6') return '偶数月'

      return '-'
    },
    settingDay(info) {
      switch (info?.courseType) {
        case '1':
          return '毎日'
        case '2':
          return `毎週${this.$_getCodeValue('FUND_RESERVE_WEEKLY_SETTING', 2, info?.settingReserveWeek)}`
        case '3':
          return `毎月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        case '4':
          return `毎月${info?.settingReserveMultiDay
            ?.replace(/\b0(\d)\b/g, '$1')
            .replace(/;$/, '')
            .split(';')
            .join(', ')}日`
        case '5':
          return `奇数月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        case '6':
          return `偶数月${this.$_getCodeValue('FUND_RESERVE_DAY_SETTING', 3, info?.settingReserveDay)}`
        default:
          return '-'
      }
    }
  }
}
</script>

<style scoped lang="scss">
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

:deep(.el-divider--horizontal) {
  margin: 16px 0;
  border-width: 2px;
}
</style>
