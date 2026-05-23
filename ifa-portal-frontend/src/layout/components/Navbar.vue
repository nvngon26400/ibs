<template>
  <div class="navbar">
    <hamburger
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggle-click="handleToggleClick"
    ></hamburger>

    <breadcrumb class="breadcrumb-container"></breadcrumb>

    <div class="right-menu">
      <div class="menu-icon__container">
        <span
          v-if="isSugBoxVisible"
          class="nav-item"
        > <span
            class="clickable-header"
            style="position: relative; bottom: 10px;"
            @click="showSugBoxClick"
          >目安箱</span>
          <span
            v-show="sugBoxUnreadItems > 0"
            class="sug-box-badge"
            @click="showSugBoxClick"
          >{{ $_withCommaNoneZeroPadding(sugBoxUnreadItems) }}</span>
        </span>
        <span
          v-if="isReleaseNoteShow"
          class="nav-item clickable-header"
          style="position: relative; bottom: 10px;"
          @click="showReleaseNoteClick"
        >リリースノート</span>
        <span
          class="nav-item clickable-header"
          style="position: relative; bottom: 10px;"
          @click="showFaqClick"
        >よくある質問</span>
        <span class="nav-password ">
          <span
            class="__center clickable-header"
            @click="isPasswordChangeVisible = true"
          >パスワード変更</span>
          <span
            class="__center clickable-header"
            @click="isCcsResetVisible = true"
          >CCS情報リセット</span>
        </span>
        <img
          src="@/assets/icons/logout.svg"
          title="ログアウト"
          class="menu-icon__item"
          @click="logout"
        >
      </div>
    </div>
    <!-- リリースノートダイアログ -->
    <ifa-release-note
      :is-visible="isReleaseNoteVisible"
      @open-modal="showReleaseNoteClick"
      @close-modal="isReleaseNoteClose"
    ></ifa-release-note>
    <!-- パスワード変更ダイアログ -->
    <ifa-password-change
      :is-visible="isPasswordChangeVisible"
      :login-form-ref="loginFormRef"
      :message="''"
      :period-flag="false"
      @close-modal="isPasswordChangeVisible = false"
    ></ifa-password-change>
    <!-- CCSリセットダイアログ -->
    <el-dialog
      v-model="isCcsResetVisible"
      title="CCSリセット"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="400px"
    >
      <div
        class="reset-password"
        style="margin: 1rem 0"
      >CCS情報をリセットします｡よろしいですか？</div>
      <div>
        <ifa-button
          text="リセット"
          width="100"
          color="primary"
          small
          action-type="requestAction"
          action-id="Navbar#A004"
          :request-model="{}"
          @response-handler="handleRespopnseA004"
          @response-error-handler="handleErrorResponseA004"
        ></ifa-button>
        <ifa-button
          text="キャンセル"
          width="100"
          color="secondary"
          small
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import store from '@/store'
import IfaPasswordDomainModel from '@/resource/domain/IfaPasswordDomainModel.json'
import { getMessage, notifyMessage, backToLoginWithoutDialog } from '@/utils/errorHandler'
import IfaPasswordChange from '@/views/login/IfaPasswordChange'
import IfaReleaseNote from '@/views/releaseNote/IfaReleaseNote'
import { isAccessible } from '@/utils/controlAuth'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    IfaPasswordChange,
    IfaReleaseNote
  },
  data() {
    return {
      isPasswordChangeVisible: false,
      isPasswordChangeFinishVisible: false,
      isCcsResetVisible: false,
      isReleaseNoteVisible: false,
      passwordChangeForm: {
        oldPassword: '',
        newPassword: '',
        newPasswordCheck: ''
      },
      passwordRules: {
        oldPassword: [{ required: false, trigger: 'blur', validator: oldPasswordValidator }],
        newPassword: [{ required: false, trigger: 'blur', validator: newPasswordValidator }],
        newPasswordCheck: [{ required: false, trigger: 'blur', validator: newPasswordCheckValidator }]
      },
      IfaPasswordDomainModel: IfaPasswordDomainModel,
      loginFormRef: {} // 初期値として空のオブジェクト
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ]),
    isSugBoxVisible() {
      return ['3', '4', '5', '6', '7', '8', '9'].includes(this.$store.getters.userAccount.medUsers?.privId)
    },
    sugBoxUnreadItems() {
      return this.$store.getters.sugBoxUnreadItems
    },
    isReleaseNoteShow() {
      return ['1', '2', '3', '4', '5', '6', '7', '8', '9'].includes(this.$store.getters.userAccount.medUsers?.privId)
    }
  },
  mounted() {
    // リリースノート表示フラグがTrueの場合はログイン後に自動で遷移する。
    if (this.$store.getters.releaseNoteDispFlg) {
      this.$nextTick(() => {
        this.isReleaseNoteVisible = true
      })
    }
  },
  methods: {
    showSugBoxClick() {
      const menuId = 'SUB00_01'
      if (isAccessible(menuId)) {
        this.$_startShowMenu(menuId, null)
      } else {
        notifyMessage(
          -1,
          getMessage('errors.cmn.loginUsers.insufficientPrivilege'),
          '目安箱',
          Date.now()
        )
      }
    },
    /**
     * リリースノートダイアログを開く
     */
    showReleaseNoteClick() {
      this.isReleaseNoteVisible = true
    },
    isReleaseNoteClose() {
      this.isReleaseNoteVisible = false
    },
    showFaqClick() {
      const resolvedRoute = this.$router.resolve({
        name: 'Ifa-Faq'
      })
      window.open(resolvedRoute.href, '_blank')
    },
    handleToggleClick() {
      this.$store.dispatch('app/toggleSideBar')
    },
    logout() {
      backToLoginWithoutDialog()
    },
    resetPasswordChangeForm() {
      this.$refs['passwordChangeForm'].clearValidate()
      this.passwordChangeForm.oldPassword = ''
      this.passwordChangeForm.newPassword = ''
      this.passwordChangeForm.newPasswordCheck = ''
    },
    changePassword() {
      this.$refs['passwordChangeForm'].validate(valid => {
        if (valid) {
          this.resetPasswordChangeForm()
          this.isPasswordChangeVisible = false
          this.isPasswordChangeFinishVisible = true
        } else {
          return false
        }
      })
    },
    changePasswordCancel() {
      this.resetPasswordChangeForm()
      this.onDialogClose()
    },
    handleRespopnseA004() {
      // CCSユーザパスワードをクリアする
      this.$store.commit('user/setCcsUserPw', null)
      this.onDialogClose()
    },
    handleErrorResponseA004() {
      // エラーをポップアップ表示する
      notifyMessage(2 /* warning */, 'CCS情報リセット', 'エラー')
      this.onDialogClose()
    },
    onDialogClose() {
      this.isPasswordChangeVisible = false
      this.isPasswordChangeFinishVisible = false
      this.isCcsResetVisible = false
      this.isReleaseNoteVisible = false
    }
  }
}
const oldPasswordValidator = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('旧パスワードを入力してください。'))
  } else {
    callback()
  }
}
const newPasswordValidator = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('新パスワードを入力してください。'))
  } else {
    callback()
  }
}
const newPasswordCheckValidator = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('新パスワード（確認入力）を入力してください。'))
  } else {
    callback()
  }
}
</script>

<style lang="scss" scoped>
.clickable-header {
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
.user-dropdown {
  margin-top:-6px;
}
.nav-item {
  color: #fff;
  font-size: 14px;
  line-height: 50px;
  margin-right: 20px;
  span.right {
    text-align: right;
  }
}
.sug-box-badge {
  position: relative;
  top: -20px;
  right: -1px;
  background-color: red;
  border-radius: 6px;
  padding: 0px 4px;
  font-size: 13px;
  text-align: center;
  cursor: pointer;
}
.nav-user-info {
  color: #fff;
  font-size: 14px;
  line-height: 1rem;
  display: inline-grid;
  position: relative;
  top: -1rem;
  margin-right: 20px;
}
.nav-password {
  color: #fff;
  font-size: 14px;
  line-height: 1rem;
  display: inline-grid;
  position: relative;
  top: -1rem;
}
.navbar {
  height: 45px;
  overflow: hidden;
  position: relative;
  background: #005FCC;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 14px;
        }
      }
    }
  }
}
.ifa-user-icon {
   font-size:2rem;
}

.menu-icon__container {
  margin-right: 30px;
  margin-top: 0.5rem;

  .menu-icon__item {
    margin-left: 1rem;
    &:hover{
      cursor: pointer;
    }
  }
}
.password-message {
  color: red;
}
:deep(.change-password) .el-form-item {
  margin: 0.6rem 0;
  padding-left: 6rem;
}
:deep(.change-password) .el-form-item__label {
  width: 180px;
  white-space: nowrap;
  line-height: 2;
}
:deep(.change-password) .el-form-item__error {
  white-space: nowrap
}
.reset-password {
  display: flex;
  justify-content: center;
  height: 3rem;
}
</style>
