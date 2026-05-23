<template>
  <!-- パスワード変更ダイアログ -->
  <el-dialog
    v-model="isPasswordChangeVisible"
    width="750px"
    :title="form.screenTitle"
    :show-close="false"
    :center="true"
    :before-close="closeA005"
    :close-on-click-modal="false"
    :close-on-press-escape="message === ''"
    @open="onShow"
  >
    <div
      v-if="message"
      class="password-message"
    >{{ message }}
    </div>

    <div
      class="change-password password_label"
      style="margin: 1rem 0"
    >
      <el-form
        ref="form"
        :model="form"
        :inline="true"
        :rules="rules"
      >
        <el-row>
          <ifa-input-text
            id="oldPassword"
            v-model="form.oldPassword"
            label="旧パスワード"
            prop="oldPassword"
            required
            size="small"
            type="password"
            :domain="IfaPasswordNoneValidateDomainModel"
            style="width: 280px;"
          ></ifa-input-text>
        </el-row>
        <el-row>
          <ifa-input-text
            id="newPassword"
            v-model="form.newPassword"
            label="新パスワード"
            prop="newPassword"
            required
            size="small"
            type="password"
            :domain="IfaPasswordNoneValidateDomainModel"
            style="width: 280px;"
          ></ifa-input-text>
        </el-row>
        <el-row>
          <ifa-input-text
            id="newPasswordCheck"
            v-model="form.newPasswordCheck"
            label="新パスワード（確認入力）"
            prop="newPasswordCheck"
            required
            size="small"
            type="password"
            :domain="IfaPasswordNoneValidateDomainModel"
            style="width: 280px;"
          ></ifa-input-text>
        </el-row>
      </el-form>
    </div>
    <div>
      <ifa-button
        id="btnChange"
        text="変更"
        width="100"
        color="primary"
        small
        :form="formRef"
        :request-model="A003RequestModel"
        action-id="SUB00-03_1#A003"
        action-type="requestAction"
        :pre-request-handler="validateA003"
        @response-handler="changePasswordA003($event)"
        @response-error-handler="responseErrorHandlerA003($event)"
      ></ifa-button>
      <ifa-button
        v-if="message === ''"
        id="btnCancel"
        text="キャンセル"
        width="100"
        color="secondary"
        small
        action-type="originalAction"
        @app-action-handler="changePasswordCancelA004"
      ></ifa-button>
    </div>
  </el-dialog>

  <!-- パスワード変更完了ダイアログ -->
  <el-dialog
    v-model="isPasswordChangeFinishVisible"
    title="パスワード変更完了"
    :show-close="false"
    :center="true"
    :close-on-click-modal="false"
    width="400px"
  >
    <div class="password-message">パスワードは変更されました。</div>
    <div style="margin-top: 1.5rem">
      <ifa-button
        id="btnClose"
        text="閉じる"
        width="100"
        color="secondary"
        small
        action-type="originalAction"
        @app-action-handler="closeA005"
      ></ifa-button>
    </div>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaPasswordNoneValidateDomainModel } from '@/resource/domain/IfaPasswordNoneValidateDomainModel.json'
import { IfaPasswordChangeFormModel } from './js/IfaPasswordChangeFormModel.js'
import { IfaPasswordChangeA003RequestModel } from './js/IfaPasswordChangeA003RequestModel.js'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    loginFormRef: {
      type: Object,
      required: true
    },
    message: {
      type: String,
      required: true
    },
    periodFlag: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal', 'change-finish', 'update:isVisible', 'password-changed'],
  setup(props) {
    const isPasswordChangeVisible = useVModel(props, 'isVisible')
    return {
      isPasswordChangeVisible
    }
  },
  data() {
    return {
      IfaPasswordNoneValidateDomainModel: IfaPasswordNoneValidateDomainModel,
      form: new IfaPasswordChangeFormModel(),
      rules: {
        oldPassword: [{ required: false, trigger: 'blur', validator: this.oldPasswordValidator }],
        newPassword: [{ required: false, trigger: 'blur', validator: this.newPasswordValidator }],
        newPasswordCheck: [{ required: false, trigger: 'blur', validator: this.newPasswordCheckValidator }]
      },
      isPasswordChangeFinishVisible: false,
      formRef: {}
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    pageInfo() {
      return this.$store.getters.pageInfo.params
    },
    A003RequestModel() {
      return new IfaPasswordChangeA003RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.form = new IfaPasswordChangeFormModel()
      Object.assign(this.form, this.loginFormRef)
      this.$nextTick(() => {
        this.formRef = this.$refs.form
      })
      this.form.passwordValidityPeriodFlag = this.periodFlag
    },
    changePasswordA003(response) {
      // エラーメッセージが標準からはずれているため、独自にエラーチェックを実装
      if (response.errorLevel === '-1' || response.errorLevel === '-2') {
        this.responseErrorHandlerA003(response)
        return
      }

      this.$emit('close-modal')
      this.isPasswordChangeFinishVisible = true
    },
    responseErrorHandlerA003(response) {
      const message = response.dataList.join('<br>')
      this.$nextTick(() => {
        notifyMessage(response.errorLevel, message, response.title, new Date().toLocaleString('ja-JP'))
      })
    },
    changePasswordCancelA004() {
      this.$refs['form'].clearValidate()
      this.isPasswordChangeVisible = false
      this.$emit('close-modal')
    },
    closeA005() {
      if (this.form.passwordValidityPeriodFlag) {
        // ユーザ共通情報.コンプライアンス通信フラグがtrueの場合
        if (this.userInfo.userNeedsToReadComplianceLetters) {
          // パスワード完了画面を閉じる
          this.isPasswordChangeFinishVisible = false
          // お知らせ確認画面に遷移
          this.$emit('change-finish')
        } else {
          // パスワード完了画面を閉じる
          this.isPasswordChangeFinishVisible = false
          this.$emit('password-changed')
        }
      } else {
        // パスワード完了画面を閉じる
        this.isPasswordChangeFinishVisible = false
        this.$emit('close-modal')
      }
    },
    oldPasswordValidator(rule, value, callback) {
      const dateRegex = '^[\\w\\d\\+\\-\\_\\.\\/\\@\\*\\#\\%\\!\\"\\$\\&\\(\\)\\=\\~\\^\\\\\\?\\>\\,\\|\\`\\[\\]\\{\\}\\:\\;\\<\\' + '\'' + ']+$'
      if (value === '' || value.length < 6 || value.length > 32 || String(value).match(new RegExp(dateRegex)) === null) {
        callback('旧パスワード: パスワードを正しく入力して下さい。')
        return false
      } else {
        callback()
      }
    },
    newPasswordValidator(rule, value, callback) {
      const dateRegex = '^[\\w\\d\\+\\-\\_\\.\\/\\@\\*\\#\\%\\!\\"\\$\\&\\(\\)\\=\\~\\^\\\\\\?\\>\\,\\|\\`\\[\\]\\{\\}\\:\\;\\<\\' + '\'' + ']+$'
      if (value === '' || value.length < 6 || value.length > 32 || String(value).match(new RegExp(dateRegex)) === null) {
        callback('新パスワード: パスワードを正しく入力して下さい。')
        return false
      } else {
        callback()
      }
    },
    newPasswordCheckValidator(rule, value, callback) {
      const dateRegex = '^[\\w\\d\\+\\-\\_\\.\\/\\@\\*\\#\\%\\!\\"\\$\\&\\(\\)\\=\\~\\^\\\\\\?\\>\\,\\|\\`\\[\\]\\{\\}\\:\\;\\<\\' + '\'' + ']+$'
      if (value === '' || value.length < 6 || value.length > 32 || String(value).match(new RegExp(dateRegex)) === null) {
        callback('新パスワード（確認入力）: パスワードを正しく入力して下さい。')
        return false
      } else {
        callback()
      }
    },
    validateA003() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          return true
        } else {
          return false
        }
      })
    },
    resetPasswordChangeForm() {
      this.$refs['form'].clearValidate()
      this.form.password = ''
      this.form.oldPassword = ''
      this.form.newPassword = ''
      this.form.newPasswordCheck = ''
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$cursor: #000;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
   .el-input input {
    color: $cursor;
  }
}
.el-dialog__title {
  font-size: 18px;
  font-weight: bold;
}
</style>

<style lang="scss" scoped="">
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
  padding-left: 10px;
}
.password_label :deep(.el-form-item) {
  margin: 0.6rem 0;
  padding-left: 6rem;
}
.password_label :deep(.el-form-item__label) {
  width: 135px !important;
  white-space: nowrap;
  line-height: 2;
}
.password_label :deep(.el-form-item__error) {
  white-space: nowrap
}
</style>
