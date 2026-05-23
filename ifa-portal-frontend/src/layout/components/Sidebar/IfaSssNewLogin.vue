<template>
  <div :class="{ 'collapse': collapse }">
    <transition>
      <div
        v-if="collapse"
        class="el-menu-item submenu-title-noDropdown"
        @click="isVisible = true"
      >
        <el-tooltip
          content="SSS(新債券メニュー)"
          placement="right"
        >
          <i
            class="seeds-external svg-icon"
            style="margin-left: 20px;"
          ></i>
        </el-tooltip>
      </div>
      <div
        v-else
        class="el-menu-item submenu-title-noDropdown"
        @click="isVisible = true"
      >
        <i class="seeds-external svg-icon"></i>
        <span class="sub-el-title">
          <el-image
            class="sss-icon"
            :src="require('@/assets/icons/sss/SSS_logo.svg')"
          >
          </el-image>
          (新債券メニュー)
        </span>
      </div>
    </transition>

    <!-- ダイアログ -->
    <el-dialog
      v-model="isVisible"
      :append-to-body="true"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="60%"
      style="margin-top: 10%"
      @open="openDialog"
    >

      <!-- SSS(新債券メニュー) -->
      <page-caption v-if="errorFlg"
                    :text="sssTitle"
                    background-color="rgba(190, 190, 190, 0.35)"
      ></page-caption>
      <!-- 閉じるボタン -->
      <ifa-button v-if="errorFlg"
                  class="form-button__wrapper"
                  color="secondary"
                  text="閉じる"
                  action-type="originalAction"
                  @app-action-handler="onDialogClose"
      ></ifa-button>
      <div class="sss-content">
        <div v-if="errorFlg === 'error'">
          <el-row>
            <el-col class="errorMessage">
              <span>SSS(新債券メニュー) 接続の処理でエラーが発生したため、画面を表示できません。</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col class="errorLabel">
              <span class="errorLabelFont">エラーコード：</span>
              <span class="errorMessageFont">{{ sssCode }}</span>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <span class="errorLabelFont">エラーメッセージ：</span>
              <span class="errorMessageFont">{{ sssMessage }}</span>
            </el-col>
          </el-row>
        </div>
        <div v-if="errorFlg === 'warning'">
          <el-row>
            <el-col class="warningMessage">
              {{ sssMessage }}
            </el-col>
          </el-row>
        </div>
        <div v-if="errorFlg === '403'">
          <el-row>
            <el-col class="warningMessage">
              {{ sssMessage }}
            </el-col>
          </el-row>
        </div>
      </div>
      <form
        ref="sssForm"
        target="_blank"
        method="GET"
        :action="sssLoginUrl"
        @submit.prevent="onSubmit"
      >
        <input
          type="hidden"
          name="code"
          :value="sssLoginParam"
        >
      </form>
      <ifa-requester
        id="ifaSssNewLoginInitializeA001"
        action-id="SUB08-01#A001"
        action-type="requestAction"
        :request-model="A001RequestModel"
        @response-handler="responseHandlerInitializeA001($event)"
        @response-error-handler="responseErrorHandlerInitializeA001($event)"
      ></ifa-requester>
    </el-dialog>
  </div>
</template>

<script>
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
export default {
  components: {
    pageCaption
  },
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      isVisible: false,
      errorFlg: '',
      sssTitle: '',
      sssLoginUrl: '',
      sssLoginParam: '',
      sssMessage: '',
      sssCode: '',
      A001RequestModel: { sssType: '1' }
    }
  },
  methods: {
    openDialog() {
      this.errorFlg = ''
      this.isVisible = true
      this.ifaSssLoginInitializeA001()
    },
    onDialogClose() {
      this.isVisible = false
    },
    changeTitle() {
      const title = 'SSS(新債券メニュー) '
      if (this.errorFlg === 'error') {
        this.sssTitle = title + '接続エラー'
      } else if (this.errorFlg === 'warning') {
        this.sssTitle = title + '警告'
      } else if (this.errorFlg === '403') {
        this.sssTitle = title + 'アクセス権限エラー'
      } else {
        this.sssTitle = ''
      }
    },
    ifaSssLoginInitializeA001() {
      this.$nextTick(() => {
        document.getElementById('ifaSssNewLoginInitializeA001').click()
      })
    },
    responseHandlerInitializeA001(response) {
      this.errorFlg = response.errorFlg
      if (this.errorFlg === undefined) {
        this.errorFlg = 'warning'
      }
      this.sssCode = response.errorCode
      this.sssMessage = response.errorMessage
      this.sssLoginParam = response.sssLoginParam
      this.sssLoginUrl = response.sssLoginUrl
      this.changeTitle()
      if (!this.errorFlg) {
        this.onSubmit()
      }
    },
    responseErrorHandlerInitializeA001(response) {
      this.onDialogClose()
    },
    onSubmit() {
      this.$nextTick(() => {
        this.$refs.sssForm.submit()
        setTimeout(() => {
          this.onDialogClose()
        }, 3000)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.el-dialog {
  margin-top: 10% !important;
}
.sss-content {
  height: 300px;
}
.separator {
  border: 0;
  height: 1px;
  background: #333;
}
.warningMessage {
  font-size: 18px;
  font-weight: bold;
  margin-left: 30px;
  margin-top: 20px;
}
.errorMessage {
  font-size: 18px;
  font-weight: bold;
  margin-left: 30px;
  margin-top: 20px;
  color: red;
}
.errorLabel {
  margin-top: 30px;
}
.errorLabelFont {
  font-size: 18px;
  margin-left: 30px;
  margin-top: 20px;
  color: red;
}
.errorMessageFont {
  font-size: 18px;
  margin-top: 20px;
  color: red;
  word-break: break-all;
  overflow-wrap: break-word;
}
.form-button__wrapper {
  position: absolute;
  right: 2%;
  margin-top: 15px;
  z-index: 999;
}
.sub-el-title {
  display:inline-block;
  line-height:20px;
  white-space: pre
}
.sss-icon {
  height: 20px;
  padding-right: 8px;
}
</style>
