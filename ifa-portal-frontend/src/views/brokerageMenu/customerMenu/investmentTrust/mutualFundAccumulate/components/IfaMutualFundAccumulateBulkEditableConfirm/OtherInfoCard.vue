<template>
  <el-card
    class="box-card"
    style="font-size: 16px;"
  >
    <el-row
      class="_bold_black_m"
      style="padding-top: 0.5rem; padding-left: 1rem;"
    >
      その他設定内容
    </el-row>

    <el-divider style="border-width: 2px; margin: 8px 0;"></el-divider>

    <div style="padding: 0 0.5rem">
      <el-row class="bordered-bottom-solid">
        <el-col
          :span="8"
          class="_bold_black_m"
        >
          勧誘区分
        </el-col>
        <el-col
          :span="16"
          style="padding-left: 10px;"
        >
          {{ $_getCodeValue('INVITATION_TYPE', 1, info?.kanyuKbn) }}
        </el-col>
      </el-row>

      <el-row class="bordered-bottom-solid">
        <el-col
          :span="8"
          class="_bold_black_m"
        >
          受付方法
        </el-col>
        <el-col
          :span="16"
          style="padding-left: 10px;"
        >
          {{ $_getCodeValue('ORDER_METHOD_TYPE', 1, info?.receiveMethod) }}
        </el-col>
      </el-row>

      <el-row class="bordered-bottom-solid">
        <el-col
          :span="8"
          class="_bold_black_m"
          style="align-self: flex-start;"
        >
          ご注意事項
        </el-col>
        <el-col :span="16">
          <div style="padding-left: 10px;">
            <ifa-mutual-fund-accumulate-confirm-text></ifa-mutual-fund-accumulate-confirm-text>
          </div>
        </el-col>
      </el-row>

    </div>
  </el-card>
</template>

<script>
import IfaMutualFundAccumulateConfirmText from '../IfaMutualFundAccumulateConfirmText.vue'

export default {
  components: {
    IfaMutualFundAccumulateConfirmText
  },
  props: {
    info: { type: Object, required: true }
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
</style>
