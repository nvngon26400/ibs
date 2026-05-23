<template>
  <!-- お知らせ確認ダイアログ -->
  <el-dialog
    v-model="isIfaNotificationConfirmdialogVisible"
    width="750px"
    title="お知らせ確認画面"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
  >
    <div style="padding-right: 100px">下記の項目が終了しておりません。御対応お願いいたします。</div><br>
    <div style="margin: 0.3rem 0">
      <el-link
        style="color: #092987; text-decoration: underline;"
        :underline="false"
        @click="toInfo"
      >コンプライアンス通信</el-link>
    </div><br>
    <div>コンプライアンス通信は過去に未読がありますとポップアップが出続けます。</div>
    <div>
      <ifa-button
        text="閉じる"
        width="100"
        color="secondary"
        small
        class="right_btn"
        action-type="originalAction"
        @app-action-handler="infoDialogClose"
      ></ifa-button>
    </div>
  </el-dialog>

</template>

<script>

import { useVModel } from 'vue-composable'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    loginForm: {
      type: Object,
      required: true
    }
  },
  emits: ['close-modal'],
  setup(props) {
    const isIfaNotificationConfirmdialogVisible = useVModel(props, 'isVisible')
    return {
      isIfaNotificationConfirmdialogVisible
    }
  },
  methods: {
    infoDialogClose() {
      this.$emit('close-modal')
    },
    toInfo() {
      this.$router.push({ path: '/complianceReport' })
    }
  }
}
</script>

<style lang="scss" scoped>
:deep(.el-dialog) {
  background-color: #fff;
}
:deep(.el-dialog__body) {
  color: #303133;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  font-weight: bold;
}
.password-message {
  color: red;
}

.right_btn {
  margin-left: auto;
  display: inline-block;
  position: absolute;
  top: 60px;
  right: 10px;
  text-align: center;
}
</style>
