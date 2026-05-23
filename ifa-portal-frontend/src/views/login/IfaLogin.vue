<template>
  <div>
    <div class="login-container">
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        auto-complete="on"
        label-position="left"
      >

        <div class="title-container">
          <div class="logo__inner">
            <img
              v-if="logo"
              :src="logo"
              class="logo"
            >
            <span class="logo-title">IFAポータル</span>
          </div>
          <div class="title">{{ $_out(form.screenTitle) }}</div>
        </div>

        <ifa-input-text
          v-model="loginForm.userId"
          prefix-icon="User"
          :domain="IfaLoginIdNoneValidateDomainModel"
          prop="userId"
          placeholder="ログインID"
          name="userId"
          type="text"
          tabindex="1"
          auto-complete="on"
          size="large"
          style="color:#000;width:400"
          maxlength="16"
        ></ifa-input-text>

        <ifa-input-text
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          prefix-icon="Lock"
          :domain="IfaPasswordNoneValidateDomainModel"
          prop="password"
          type="password"
          placeholder="パスワード"
          name="password"
          tabindex="2"
          auto-complete="on"
          maxlength="32"
        ></ifa-input-text>

        <div style="margin-bottom:1rem;">
          <ifa-button
            id="btnLogin"
            :form="loginFormRef"
            text="ログイン"
            width="215"
            color="primary"
            :request-model="getIfaLoginA002RequestModel"
            action-id="SUB00-00#A002"
            action-type="requestAction"
            @response-handler="loginA002($event)"
          ></ifa-button>
          <ifa-button
            id="btnReset"
            text="リセット"
            action-type="originalAction"
            width="215"
            color="secondary"
            @app-action-handler="resetA003"
          ></ifa-button>
        </div>

      </el-form>

      <el-card
        header="SBI証券からのご連絡"
        class="box-card"
      >
        <div v-for="(item, idx) in form.notification"
             :key="idx"
             :class="[idx % 2 === 1 ? '_border_colored_row' : '']"
        >
          <div style="word-wrap:break-word;"
               v-html="$_out(item.infoDetail)"
          ></div>
        </div>
      </el-card>
    </div>

    <!-- パスワード変更ダイアログ -->
    <ifa-password-change
      :is-visible="isPasswordChangeVisible"
      :login-form-ref="loginFormRef"
      :message="message"
      :period-flag="true"
      @close-modal="isPasswordChangeVisible = false"
      @password-changed="passwordChanged"
      @change-finish="changeFinish"
    ></ifa-password-change>
    <!-- お知らせ確認ダイアログ -->
    <ifa-notification-confirm
      :is-visible="isIfaNotificationConfirmdialogVisible"
      :login-form="loginForm"
      @close-modal="ifaNotificationConfirmClose"
    ></ifa-notification-confirm>
    <!-- 初回認証用メールアドレス登録ダイアログ -->
    <el-dialog
      v-if="mailAddressForm.isConfirm"
      v-model="isIfaMailAddressRegister"
      width="750px"
      title="初回認証用メールアドレス登録"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      @open="setMailAddressFormRef"
    >
      <div
        class="change-password"
        style="margin: 0 0 1.3rem "
      >
        <el-form
          ref="mailAddressForm"
          :model="mailAddressForm"
          :inline="true"
          :rules="mailAddressRules"
        >
          <ifa-input-text
            v-model="mailAddressForm.mailAddress"
            label="メールアドレス"
            prop="mailAddress"
            :domain="IfaMailAddressDomainModel"
            size="small"
            type="text"
            :disabled="!mailAddressForm.isConfirm"
            style="width: 380px"
          ></ifa-input-text>
        </el-form>
      </div>
      <div>
        <ifa-button
          id="btnConfirm"
          :form="mailAddressFormRef"
          text="確認"
          width="100"
          color="primary"
          small
          action-id="SUB00-00#A004"
          action-type="originalAction"
          @app-action-handler="confirmA004"
        ></ifa-button>
        <ifa-button
          id="btnCancel"
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="cancelA006"
        ></ifa-button>
      </div>
    </el-dialog>
    <el-dialog
      v-else
      v-model="isIfaMailAddressRegister"
      width="750px"
      title="初回認証用メールアドレス登録"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      class="custom-dialog"
      @open="setMailAddressFormRef"
    >
      <ifa-button
        id="btnBack"
        class="custom-button"
        text="戻る"
        height="100"
        width="100"
        color="secondary"
        small
        action-type="originalAction"
        @app-action-handler="backA007"
      ></ifa-button>
      <div
        class="change-password"
        style="margin: 0 0 1.3rem "
      >
        <el-form
          ref="mailAddressForm"
          :model="mailAddressForm"
          :inline="true"
          :rules="mailAddressRules"
        >
          <ifa-input-text
            v-model="mailAddressForm.mailAddress"
            label="メールアドレス"
            prop="mailAddress"
            :domain="IfaMailAddressDomainModel"
            size="small"
            type="text"
            :disabled="!mailAddressForm.isConfirm"
          ></ifa-input-text>
        </el-form>
      </div>
      <div>
        <ifa-button
          id="btnRegister"
          :form="mailAddressFormRef"
          text="登録"
          width="100"
          color="primary"
          small
          :request-model="getIfaLoginA005RequestModel"
          action-id="SUB00-00#A005"
          action-type="requestAction"
          @response-handler="registerRegisterA005($event)"
        ></ifa-button>
      </div>
    </el-dialog>
    <!-- 認証ダイアログ -->
    <el-dialog
      v-model="isIfaAuthCodeInputVisible"
      width="750px"
      title="認証"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      @open="setAuthCodeRef"
    >
      <div>メールに記載された認証コードを入力してください。</div>
      <div
        class="change-password"
        style="margin: 1rem 0 1.3rem "
      >
        <el-form
          ref="authCode"
          :model="authCode"
          :rules="authCodeRules"
          :inline="true"
        >
          <ifa-input-text
            v-model="authCode.verifyCode"
            :domain="IfaFixedLengthInput4DomainModel"
            label="認証コード"
            prop="verifyCode"
            size="small"
            type="text"
            maxlength="4"
            style="width: 380px"
          ></ifa-input-text>

        </el-form>
      </div>
      <div>
        <ifa-button
          id="btnOk"
          :form="authCodeRef"
          text="OK"
          width="100"
          color="primary"
          small
          :request-model="getIfaLoginA008RequestModel"
          action-id="SUB00-00#A008"
          action-type="requestAction"
          @response-handler="inputAuthA008($event)"
          @response-error-handler="inputAuthA008Error($event)"
        ></ifa-button>
        <ifa-button
          id="btnCancel"
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="loginAuthCancelA009"
        ></ifa-button>
      </div>
    </el-dialog>
    <ifa-requester
      id="IfaLoginInitializeA001"
      action-id="SUB00-00#A001"
      action-type="requestAction"
      @response-handler="responseHandlerInitializeA001($event)"
    ></ifa-requester>
    <ifa-requester
      id="IfaLoginLoginA008"
      :request-model="getIfaLoginA002RequestModel"
      action-id="SUB00-00#A008"
      action-type="requestAction"
      @response-handler="inputAuthA008($event)"
      @response-error-handler="inputAuthA008Error($event)"
    ></ifa-requester>

    <!-- 通知ポップアップ -->
    <ifa-notifications ref="ifaNotifications"></ifa-notifications>
  </div>
</template>

<script>
import { IfaLoginFormModel } from './js/IfaLoginFormModel.js'
import { IfaLoginA002RequestModel } from './js/IfaLoginA002RequestModel.js'
import { IfaLoginA005RequestModel } from './js/IfaLoginA005RequestModel.js'
import { IfaLoginA008RequestModel } from './js/IfaLoginA008RequestModel.js'
import IfaMailAddressDomainModel from '@/resource/domain/IfaMailAddressDomainModel.json'
import IfaFixedLengthInput4DomainModel from '@/resource/domain/IfaFixedLengthInput4DomainModel.json'
import IfaLoginIdNoneValidateDomainModel from '@/resource/domain/IfaLoginIdNoneValidateDomainModel.json'
import IfaPasswordNoneValidateDomainModel from '@/resource/domain/IfaPasswordNoneValidateDomainModel.json'
import { getMessage } from '@/utils/errorHandler.js'
import IfaNotificationConfirm from './IfaNotificationConfirm'
import IfaPasswordChange from './IfaPasswordChange'
import { storeLoginResponse } from '@/utils/storeHelper'
import IfaNotifications from '@/components/Notifications/IfaNotifications'
import { resetAllStates } from '@/api/user'

export default {
  name: 'Login',
  components: {
    IfaNotificationConfirm,
    IfaPasswordChange,
    IfaNotifications
  },
  data() {
    return {
      ErrorReturnCodeList: ['badLogin', 'verifyCodeError', 'codeExpired', 'notMatch', 'verifyError', 'authError', 'systemError', 'mailError', 'updateFail'],
      message: '',
      IfaMailAddressDomainModel: IfaMailAddressDomainModel,
      IfaFixedLengthInput4DomainModel: IfaFixedLengthInput4DomainModel,
      IfaLoginIdNoneValidateDomainModel: IfaLoginIdNoneValidateDomainModel,
      IfaPasswordNoneValidateDomainModel: IfaPasswordNoneValidateDomainModel,
      loginFormRef: {},
      mailAddressFormRef: {},
      authCodeRef: {},
      form: new IfaLoginFormModel(),
      logo: require('@/assets/logo.png'),
      loginForm: {
        userId: '',
        password: ''
      },
      mailAddressForm: {
        mailAddress: '',
        isConfirm: true
      },
      authCode: {
        verifyCode: ''
      },
      loginRules: {
        userId: [{ required: true, message: getMessage('errors.login.badLogin'), trigger: 'blur' }],
        password: [{ required: true, message: getMessage('errors.login.badLogin'), trigger: 'blur' }]
      },
      mailAddressRules: {
        mailAddress: [{ required: true, message: getMessage('errors.required', ['認証用メールアドレス']), trigger: 'blur' }]
      },
      authCodeRules: {
        verifyCode: [{ required: true, message: getMessage('errors.login.verifyCodeError'), trigger: 'blur' }]
      },
      isPasswordChangeVisible: false,
      isPasswordChangeFinishVisible: false,
      isIfaNotificationConfirmdialogVisible: false,
      isIfaMailAddressRegister: false,
      isIfaAuthCodeInputVisible: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  computed: {
    getIfaLoginA002RequestModel() {
      return new IfaLoginA002RequestModel({
        userId: this.loginForm.userId, // ログインID
        password: this.loginForm.password, // パスワード
        verifyCode: this.authCode.verifyCode // 認証コード
      })
    },
    getIfaLoginA005RequestModel() {
      return new IfaLoginA005RequestModel({
        mailAddress: this.mailAddressForm.mailAddress, // メールアドレス
        userId: this.loginForm.userId, // ログインID
        password: this.loginForm.password // パスワード
      })
    },
    getIfaLoginA008RequestModel() {
      return new IfaLoginA008RequestModel({
        userId: this.loginForm.userId, // ログインID
        password: this.loginForm.password, // パスワード
        verifyCode: this.authCode.verifyCode // 認証コード
      })
    }

  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
        this.$nextTick(() => {
          document.getElementById('IfaLoginInitializeA001').click()
        })
      },
      immediate: true
    }
  },
  mounted() {
    // ログイン画面初期化時にリセット
    resetAllStates()
  },
  methods: {
    responseHandlerInitializeA001(data) {
      if (data.errorLevel === 0) {
        Object.assign(this.form.notification, data.dataList)
        this.loginFormRef = this.$refs.loginForm
      }
    },

    setMailAddressFormRef() {
      this.mailAddressFormRef = this.$refs.mailAddressForm
    },
    setAuthCodeRef() {
      this.authCodeRef = this.$refs.authCode
    },
    loginA002(data) {
      if (data.errorLevel === 0) {
        if (data.returnCode === 'firstverify') {
          // 初回認証用メールアドレス登録画面へ遷移
          this.isIfaMailAddressRegister = true
        } else if (data.returnCode === 'verify') {
          // 認証画面へ遷移
          this.isIfaAuthCodeInputVisible = true
        } else if (data.returnCode === 'notvu') {
          // 次の処理へ(A008をコール)
          document.getElementById('IfaLoginLoginA008').click()
        }
      } else {
        if (this.ErrorReturnCodeList.includes(data.returnCode)) {
          return
        }
      }
    },
    moveHome(response) {
      storeLoginResponse(response)
      if (this.$store.getters.userAccount.userNeedsToReadComplianceLetters === true) {
        this.isIfaNotificationConfirmdialogVisible = true
      } else {
        this.$router.push({ path: '/' })
      }
    },
    resetA003() {
      this.$refs['loginForm'].clearValidate()
      this.loginForm.userId = ''
      this.loginForm.password = ''
    },
    async confirmA004() {
      let validateResult = true
      await this.mailAddressFormRef.validate().catch(() => { validateResult = false })

      // 入力内容のバリデートOKなら、次画面に遷移
      if (validateResult === true) {
        this.mailAddressForm.isConfirm = false
      }
    },
    registerRegisterA005(data) {
      if (data.errorLevel === 0) {
        this.isIfaMailAddressRegister = false
        this.isIfaAuthCodeInputVisible = true
        this.mailAddressForm.isConfirm = ''
        this.mailAddressForm.isConfirm = true
      } else if (this.ErrorReturnCodeList.includes(data.returnCode)) {
        this.isIfaMailAddressRegister = false
        this.mailAddressForm.isConfirm = ''
        this.mailAddressForm.isConfirm = true
        return
      }
    },
    cancelA006() {
      this.isIfaMailAddressRegister = false
      this.$refs['mailAddressForm'].clearValidate()
      this.mailAddressForm.isConfirm = ''
      this.loginForm.password = ''
      this.mailAddressForm.isConfirm = true
    },
    backA007() {
      this.mailAddressForm.isConfirm = true
      this.$nextTick(() => {
        // this.$refsはリアクティブでは無いため､次の描画タイミングでフォームをクリアさせる
        this.$refs['mailAddressForm'].clearValidate()
        this.setMailAddressFormRef()
      })
    },
    inputAuthA008Error(response) {
      storeLoginResponse(response)
      if (this.isIfaAuthCodeInputVisible) {
        this.isIfaAuthCodeInputVisible = false
      }
      if (response.returnCode === 'expired') {
        // 以下の遷移先リクエスト項目にパラメータをセットしてパスワード変更画面へ遷移し処理を終了する。
        // 遷移先リクエスト.メッセージ：FCT014.メッセージ
        this.message = 'パスワードの有効期限が切れています。新しいパスワードに変更してください。'
        this.isPasswordChangeVisible = true
      } else if (this.ErrorReturnCodeList.includes(response.returnCode)) {
        this.isIfaAuthCodeInputVisible = false
        return
      }
    },
    passwordChanged() {
      this.isPasswordChangeVisible = false
      if (this.$store.getters.userAccount.userNeedsToReadComplianceLetters === true) {
        this.isIfaNotificationConfirmdialogVisible = true
      } else {
        this.$router.push({ path: '/' })
      }
    },
    changeFinish() {
      this.isIfaNotificationConfirmdialogVisible = true
    },
    ifaNotificationConfirmClose() {
      this.isIfaNotificationConfirmdialogVisible = false
      this.$router.push({ path: '/' })
    },
    inputAuthA008(data) {
      if (data.errorLevel === 0) {
        this.moveHome(data)
      }
    },
    loginAuthCancelA009() {
      this.isIfaAuthCodeInputVisible = false
      this.$refs['loginForm'].clearValidate()
      this.$refs['authCode'].clearValidate()
      this.loginForm.password = ''
      this.authCode.verifyCode = ''
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#eee;
$light_gray:#fff;
$cursor: #000;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  min-width: 530px;
  .el-form-item__content div {
      width: 100%;
      .el-input {
        display: inline-block;
        height: 47px;
        width: 100%;

        .el-input__wrapper {
          width: 100%;
          background: transparent;
          border: 0px;
          box-shadow: none;
          border-radius: 0px;
          padding: 12px 5px 12px 15px;
          color: #000;
          height: 47px;
          caret-color: #000;
          .el-input__inner{
            height: 38px;
          }

          &:-webkit-autofill {
            box-shadow: 0 0 0px 1000px $bg inset !important;
            -webkit-text-fill-color: $cursor !important;
          }
        }
      }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
    margin-bottom: 1.2rem;
  }
}
</style>

<style lang="scss" scoped>
$bg:#fff;
$dark_gray:#889aa4;
$font_color:#000;

:deep(.custom-dialog) {
  .el-dialog__header {
      height: 50px;
      width: 540px;
      padding-right: 0px;
      padding-left: 110px;
      margin-right: 0px;
      padding-bottom: 40px;
      float: left;
  }
}
.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 50px 35px 20px 35px;
    margin: 0 auto;
    overflow: hidden;
  }

  .title-container {
    position: relative;
    line-height: 50px;
    text-align: center;
    .logo {
      height: 26px;
      vertical-align: middle;
    }
    .logo-title {
      color: #000;
      margin-left: 0.8rem;
      font-weight: 600;
      line-height: 3rem;
      font-size: 26px;
      vertical-align: bottom;
    }
    .title {
      font-size: 18px;
      color: $font_color;
      margin: 0px auto 10px auto;
      font-weight: 600;
    }
  }
  .box-card {
    margin: 10px auto 15px auto;
    width: 70%;
  }
  :deep(.box-card) > .el-card__header {
    background-color: rgba(190, 190, 190, 0.5);
    padding: 0.7rem 1rem;
    border-left: 6px solid #024d94;
    font-weight: bold;
  }
  :deep(.box-card) > .el-card__body {
    height: 480px;
    overflow: auto;
  }
  .info-card {
    margin: 0.5rem 0.5rem 0.8rem 0.5rem;
  }
  :deep(.info-card) > .el-card__header {
    background-color: rgba(235, 235, 235, 0.5);
    padding: 0.5rem;
  }
  :deep(.info-card) > .el-card__body {
    padding: 0.5rem;
  }
  .info-card div {
    margin: 0.3rem 0;
  }
}
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
:deep(.change-password) .el-form-item {
  margin: 0.6rem 0;
}
:deep(.change-password) .el-form-item__label {
  width: 180px;
  line-height: 2;
}
:deep(.change-password) .el-form-item__error {
  white-space: nowrap
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
