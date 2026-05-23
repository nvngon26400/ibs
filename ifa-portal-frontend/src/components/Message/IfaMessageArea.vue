<template>
  <el-row>
    <div
      class="main-message"
    >
      <div
        v-for="message in mainMessageList"
        :key="message"
        v-text="message"
      ></div>
    </div>
    <ul
      type="disc"
      class="error-message"
    >
      <li
        v-for="message in errorMessageList"
        :key="message"
        v-text="message"
      ></li>
    </ul>
    <ul
      type="disc"
      class="warning-message"
    >
      <li
        v-for="message in warningMessageList"
        :key="message"
        v-text="message"
      ></li>
    </ul>
    <ul
      type="disc"
      class="info-message"
    >
      <li
        v-for="message in infoMessageList"
        :key="message"
        v-text="message"
      ></li>
    </ul>
  </el-row>
</template>

<script>
export default {
  name: 'IfaMessageArea',
  props: {
    mainMessages: { type: [Array], required: false, default: () => [] }, // メインメッセージリスト
    errorMessages: { type: [Array], required: false, default: () => [] }, // エラーメッセージリスト
    warningMessages: { type: [Array], required: false, default: () => [] }, // ワーニングメッセージリスト
    infoMessages: { type: [Array], required: false, default: () => [] } // インフォメーションメッセージリスト
  },
  data() {
    return {
      mainMessageList: this.mainMessages,
      errorMessageList: this.errorMessages,
      warningMessageList: this.warningMessages,
      infoMessageList: this.infoMessages
    }
  }
}
</script>

<style lang="scss" scoped>
.main-message {
  margin: 0.5rem 0;
  padding: 0 45px;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
  width: 100%;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
  width: 100%;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 14px;
  white-space: pre-wrap;
  width: 100%;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
  width: 100%;
}
</style>

