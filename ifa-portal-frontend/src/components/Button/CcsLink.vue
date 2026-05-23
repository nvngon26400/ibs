<template>
  <div class="ccs-link-container">
    <ifa-button
      id="btnCustProperty"
      name="btnCustProperty"
      text="顧客属性"
      width="120"
      action-type="originalAction"
      @app-action-handler="onBtnCustPropertyClickHandler"
    ></ifa-button>
    <ifa-button
      id="btnAccountSum"
      name="btnAccountSum"
      text="口座サマリー"
      width="120"
      action-type="originalAction"
      @app-action-handler="onBtnAccountSumClickHandler"
    ></ifa-button>
    <ifa-button
      id="btnStockOrder"
      name="btnStockOrder"
      text="取引メニュー"
      width="120"
      action-type="originalAction"
      @app-action-handler="onBtnStockOrderClickHandler"
    ></ifa-button>
  </div>
</template>

<script>
export default {
  name: 'CcsLink',
  methods: {
    onBtnCustPropertyClickHandler() {
      // this.$_logDebug('顧客属性ボタンクリック')
    },
    onBtnAccountSumClickHandler() {
      // this.$_logDebug('口座サマリークリック')
    },
    onBtnStockOrderClickHandler() {
      // this.$_logDebug('取引メニューボタンクリック')
    }
  }
}
</script>

<style>
.ccs-link-container {
  display: inline;
}
</style>
