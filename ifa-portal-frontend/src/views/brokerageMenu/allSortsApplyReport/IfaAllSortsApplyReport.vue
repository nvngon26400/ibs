<template>
  <div>
    <div class="message">別ウィンドウで画面が起動しております｡</div>
    <ifa-requester
      id="ifaAllSortsApplyReport"
      action-id="ifaLinkUrlUrlAcquire"
      action-type="requestAction"
      :request-model="ifaAllSortsApplyReportRequestModel"
      @response-handler="responseHandler($event)"
    ></ifa-requester>
  </div>
</template>
<script>

export default {
  computed: {
    ifaAllSortsApplyReportRequestModel() {
      return {
        urlId: '173',
        patternId: 1,
        httpMethod: 'GET'
      }
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaAllSortsApplyReport').click()
      })
    },
    responseHandler(response) {
      if (response && response.linkUrl) {
        this.$nextTick(() => {
          window.open(response.linkUrl, '_blank')
        })
      }
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
