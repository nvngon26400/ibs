<template>
  <div>
    <div class="message">別ウィンドウで画面が起動しております｡</div>
    <ifa-requester
      id="ifaComplianceRelatedReport"
      action-id="ifaLinkUrlUrlAcquire"
      action-type="requestAction"
      :request-model="ifaComplianceRelatedReportRequestModel"
      @response-handler="responseHandler($event)"
    ></ifa-requester>
  </div>
</template>
<script>

export default {
  computed: {
    ifaComplianceRelatedReportRequestModel() {
      return {
        urlId: '174',
        patternId: 1,
        httpMethod: 'GET'
      }
    }
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaComplianceRelatedReport').click()
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
