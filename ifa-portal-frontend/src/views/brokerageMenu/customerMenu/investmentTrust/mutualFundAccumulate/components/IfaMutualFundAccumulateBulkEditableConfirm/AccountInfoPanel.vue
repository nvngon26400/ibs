<template>
  <div>
    <el-row style="font-weight: bold;">
      <span>{{ info?.accountNumber ?? '-' }}</span>
    </el-row>

    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <div style="display: flex; column-gap: 2px">
        <el-icon>
          <component
            :is="info?.corporationKbn === '1'
              ? 'OfficeBuilding'
              : 'Avatar'"
          ></component>
        </el-icon>
        <div>{{ info?.customerName ?? '-' }}</div>
        <div style="padding-left: 10px;">
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
          ></ifa-notice-info>
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'

export default {
  components: {
    IfaNoticeInfo
  },
  props: {
    info: { type: Object, required: true }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    }
  }
}
</script>
