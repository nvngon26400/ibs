<template>
  <div>
    <div class="message">別ウィンドウで画面が起動しております｡</div>
    <ifa-requester
      id="IfaOnLineAccountOpen"
      action-id="SUB0207_0201#A001"
      action-type="requestAction"
      @response-handler="initializeA001($event)"
    ></ifa-requester>
  </div>
</template>
<script>

export default {
  methods: {
    onShow() {
      // A001
      this.$nextTick(() => {
        document.getElementById('IfaOnLineAccountOpen').click()
      })
    },
    initializeA001(response) {
      this.$nextTick(() => {
        window.open(response.dataList[0]?.url, '_blank')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.message {
  padding: 2rem;
  font-size: 18px;
}
</style>
