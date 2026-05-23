<template>
  <div>
    <!-- 信用新規 -->
    <ifa-margin-new-order-input
      v-if="currentViewName === viewName.IfaMarginNewOrderInput"
      :ref="viewName.IfaMarginNewOrderInput"
      @initialize-error="responseErrorHandlerInitializeA001($event)"
    ></ifa-margin-new-order-input>
    <!-- 信用返済 -->
    <ifa-margin-repay-order-input
      v-else-if="currentViewName === viewName.IfaMarginRepayOrderInput"
      :ref="viewName.IfaMarginRepayOrderInput"
      :params="params"
      @transition-margin-mass-repay-input="handleTransitionMarginMassRepayInput"
      @initialize-error="responseErrorHandlerInitializeA001($event)"
    ></ifa-margin-repay-order-input>
    <!-- 信用一括返済 -->
    <ifa-margin-mass-repay-input
      v-else-if="currentViewName === viewName.IfaMarginMassRepayInput"
      :ref="viewName.IfaMarginMassRepayInput"
      :params="params"
      @transition-margin-repay-order-input="handleTransitionMarginRepayOrderInput"
      @initialize-error="responseErrorHandlerInitializeA001($event)"
    ></ifa-margin-mass-repay-input>
    <!-- 現引現渡注文入力 -->
    <ifa-receipt-delivery-order-input
      v-else-if="currentViewName === viewName.IfaReceiptDeliveryOrderInput"
      :ref="viewName.IfaReceiptDeliveryOrderInput"
      :params="params"
      @initialize-error="responseErrorHandlerInitializeA001($event)"
    ></ifa-receipt-delivery-order-input>
  </div>
</template>

<script>
import IfaMarginNewOrderInput from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginNewOrderInput.vue'
import IfaMarginRepayOrderInput from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginRepayOrderInput'
import IfaMarginMassRepayInput from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaMarginMassRepayInput'
import IfaReceiptDeliveryOrderInput from '@/views/brokerageMenu/customerMenu/domesticStock/marginTrade/IfaReceiptDeliveryOrderInput'
import { getCreditRepaymentWrapperViewNames } from '@/views/brokerageMenu/customerMenu/commonProcess/creditRepayment.js'

export default {
  components: {
    IfaMarginNewOrderInput,
    IfaMarginRepayOrderInput,
    IfaMarginMassRepayInput,
    IfaReceiptDeliveryOrderInput
  },
  emits: ['initializeError'],
  data() {
    return {
      viewName: getCreditRepaymentWrapperViewNames(), // 画面名のリスト
      params: [], // 遷移先画面へ渡すパラメータ
      currentViewName: '' // 遷移先画面
    }
  },
  methods: {
    onShow(resume, isRouting) {
      if (isRouting === true) {
        // 画面から遷移した場合
        this.params = this.$store.getters.pageInfo.params
        this.currentViewName = this.$store.getters.creditRepaymentWrapperCurrentViewName
      } else {
        // タブ押下で遷移した場合
        this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: this.viewName.IfaMarginNewOrderInput })
        this.currentViewName = this.viewName.IfaMarginNewOrderInput
      }
      // 画面の初期化処理
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs[this.currentViewName]?.onShow()
        })
      })
    },
    responseErrorHandlerInitializeA001(errorInfo) {
      this.$emit('initializeError', errorInfo)
    },
    // 信用取引タブ内の遷移
    // 信用一括返済入力へ遷移
    handleTransitionMarginMassRepayInput(params) {
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: this.viewName.IfaMarginMassRepayInput })
      this.params = params
      this.currentViewName = this.viewName.IfaMarginMassRepayInput
      // 画面の初期化処理
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs[this.currentViewName]?.initializeReturnA010()
        })
      })
    },
    // 信用取引タブ内の遷移
    // 信用返済入力へ遷移
    handleTransitionMarginRepayOrderInput(params) {
      this.$store.dispatch('customerPortalMenuList/setCreditRepaymentWrapperCurrentViewName', { viewName: this.viewName.IfaMarginRepayOrderInput })
      this.params = params
      this.currentViewName = this.viewName.IfaMarginRepayOrderInput
      // 画面の初期化処理
      this.$nextTick(() => {
        this.$nextTick(() => {
          this.$refs[this.currentViewName]?.onShow()
        })
      })
    }
  }
}

</script>
