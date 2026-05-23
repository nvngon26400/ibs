<template>
  <div>
    <!-- 信用新規 -->
    <ifa-foreign-margin-trade-new-order-input
      v-if="currentViewName === 'IfaForeignMarginTradeNewOrderInput'"
      ref="IfaForeignMarginTradeNewOrderInput"
      @initialize-error="handleInitializeError($event)"
    ></ifa-foreign-margin-trade-new-order-input>
    <!-- 信用返済 -->
    <ifa-foreign-margin-trade-repay-order-input
      v-else-if="currentViewName === 'IfaForeignMarginTradeRepayOrderInput'"
      ref="IfaForeignMarginTradeRepayOrderInput"
      :params="params"
    ></ifa-foreign-margin-trade-repay-order-input>
  </div>
</template>

<script>
import IfaForeignMarginTradeNewOrderInput from '@/views/brokerageMenu/customerMenu/foreignStock/marginTrade/IfaForeignMarginTradeNewOrderInput'
import IfaForeignMarginTradeRepayOrderInput from '@/views/brokerageMenu/customerMenu/foreignStock/marginTrade/IfaForeignMarginTradeRepayOrderInput'

export default {
  components: {
    IfaForeignMarginTradeNewOrderInput,
    IfaForeignMarginTradeRepayOrderInput
  },
  emits: ['initializeError'],
  data() {
    return {
      params: [],
      currentViewName: ''
    }
  },
  methods: {
    onShow(resume, isRouting) {
      if (isRouting === true) {
        // 画面から遷移した場合
        this.params = this.$store.getters.pageInfo.params
        this.currentViewName = 'IfaForeignMarginTradeRepayOrderInput'
      } else {
        // タブ押下で遷移した場合
        this.currentViewName = 'IfaForeignMarginTradeNewOrderInput'
      }
      // 画面の初期化処理
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs[this.currentViewName]?.onShow(this.params)
        })
      })
    },
    handleInitializeError(errorInfo) {
      this.$emit('initializeError', errorInfo)
    }
  }
}
</script>
