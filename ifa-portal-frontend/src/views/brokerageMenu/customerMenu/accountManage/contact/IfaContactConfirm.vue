<template>
  <el-dialog
    v-model="vmIsVisible"
    width="1200px"
    :title="confirmForm.dialogTitle.name"
    :show-close="false"
    :center="true"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    class="buy-background-color"
    @open="onShow"
  >
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          text="戻る"
          color="secondary"
          style="padding-right: 0;"
          action-type="originalAction"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>
    <ifa-message-area
      :main-messages="messages.mains"
    ></ifa-message-area>
    <el-form
      ref="formRef"
      :model="confirmForm"
      :inline="true"
    >
      <el-row
        style="font-weight: bold; color: black;"
      >
        <el-col
          :offset="1"
          :span="22"
        >
          <span>{{ $_out(customerInfo.butenCode) }}-{{ $_zeroPadding($_out(customerInfo.accountNumber), 7) }}</span>
        </el-col>
      </el-row>
      <el-row
        style="padding-top: 0.3rem;"
        class="_bold_black_l"
      >
        <el-col
          :offset="1"
          :span="22"
          style="font-size: 20px;"
          class="_bold_black_l"
        >
          <el-icon style="position: relative; top: 3px;">
            <OfficeBuilding v-if="customerInfo.corporationType == '1'"></OfficeBuilding>
            <Avatar v-else></Avatar>
          </el-icon>
          <span>{{ $_out(customerInfo.customerNameKanji) }}（{{ $_out(customerInfo.customerNameKana) }}）</span>
          <ifa-notice-info
            :notice-info-level="customerInfo.noticeInfoLevel"
            :customer-code="customerInfo.customerCode"
            :buten-code="customerInfo.butenCode"
            :account-number="customerInfo.accountNumber"
            style="position: relative; top: 4px;"
          ></ifa-notice-info>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="padding-top: 0.7rem;"
        >
          <el-card
            class="box-card"
            style="font-size: 16px;"
          >
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">カテゴリ</span>
              </el-col>
              <el-col
                :span="operationType === '1' ? 14 : 7"
                :offset="1"
              >
                {{ formatToiawaseMei(displayToiawaseMei(confirmForm.afterData.toiawaseDList, confirmForm.beforeData.toiawaseDCd), displayToiawaseMei(confirmForm.beforeData.toiawaseList, confirmForm.beforeData.toiawaseCd), displayToiawaseMei(confirmForm.beforeData.toiawaseSList, confirmForm.beforeData.toiawaseSCd)) }}
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.toiawaseDCd + confirmForm.beforeData.toiawaseCd + confirmForm.beforeData.toiawaseSCd !== confirmForm.afterData.toiawaseDCd + confirmForm.afterData.toiawaseCd + confirmForm.afterData.toiawaseSCd">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ formatToiawaseMei(displayToiawaseMei(confirmForm.afterData.toiawaseDList, confirmForm.afterData.toiawaseDCd), displayToiawaseMei(confirmForm.afterData.toiawaseList, confirmForm.afterData.toiawaseCd), displayToiawaseMei(confirmForm.afterData.toiawaseSList, confirmForm.afterData.toiawaseSCd)) }}
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">接触経路</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="SESSYOKU_KEIRO"
                  :disp-pattern="1"
                  :code-key="confirmForm.beforeData.sessyokuKeiro"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.sessyokuKeiro !== confirmForm.afterData.sessyokuKeiro">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="SESSYOKU_KEIRO"
                  :disp-pattern="1"
                  :code-key="confirmForm.afterData.sessyokuKeiro"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">重要度</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="JUUYOUDO"
                  :disp-pattern="1"
                  :code-key="confirmForm.beforeData.juuyoudo"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.juuyoudo !== confirmForm.afterData.juuyoudo">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="JUUYOUDO"
                  :disp-pattern="1"
                  :code-key="confirmForm.afterData.juuyoudo"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">クレーム</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                {{ confirmForm.beforeData.cream === '1' ? '※': '' }}
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.cream !== confirmForm.afterData.cream">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ confirmForm.afterData.cream === '1' ? '※': '' }}
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">リクエスト</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                {{ confirmForm.beforeData.request === '1' ? '※': '' }}
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.request !== confirmForm.afterData.request">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ confirmForm.afterData.request === '1' ? '※': '' }}
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">対応ステータス</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="TAIOU_STS"
                  :disp-pattern="1"
                  :code-key="confirmForm.beforeData.taiouSts"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.taiouSts !== confirmForm.afterData.taiouSts">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="TAIOU_STS"
                  :disp-pattern="1"
                  :code-key="confirmForm.afterData.taiouSts"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">入電方向</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="NYUUDEN_HOUKOU"
                  :disp-pattern="1"
                  :code-key="confirmForm.beforeData.houkou"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.houkou !== confirmForm.afterData.houkou">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="NYUUDEN_HOUKOU"
                  :disp-pattern="1"
                  :code-key="confirmForm.afterData.houkou"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">訪問日</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(confirmForm.beforeData.houmonbi) }}
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.houmonbi !== confirmForm.afterData.houmonbi">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(confirmForm.afterData.houmonbi) }}
              </el-col>
            </el-row>
            <el-row class="dotted_border">
              <el-col :span="5">
                <span class="_bold_black_m">次回訪問予定日</span>
              </el-col>
              <el-col
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(confirmForm.beforeData.nextHoumonbi) }}
              </el-col>
              <el-col :span="3">
                <span v-show="confirmForm.beforeData.nextHoumonbi !== confirmForm.afterData.nextHoumonbi">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(confirmForm.afterData.nextHoumonbi) }}
              </el-col>
            </el-row>
            <el-row
              v-if="operationType === '1' || operationType === '3'"
              class="inner-box-line"
            >
              <el-col>
                <span class="_bold_black_m">内容</span>
              </el-col>
            </el-row>
            <el-row
              v-if="operationType === '1' || operationType === '3'"
              class="dotted_border"
            >
              <el-col>
                <ifa-input-text
                  id="toiawaseNaiyouConfirm"
                  v-model="confirmForm.toiawaseNaiyou"
                  type="textarea"
                  :rows="6"
                  resize="vertical"
                  class="form_label ifa-input__text-default"
                  label-class="validation-error-width"
                  :disabled="true"
                  style="width: 100%;"
                ></ifa-input-text>
              </el-col>
            </el-row>
            <el-row
              v-if="operationType === '3'"
              class="inner-box-line"
            >
              <el-col>
                <span class="_bold_black_m">追加入力</span>
              </el-col>
            </el-row>
            <el-row
              v-if="operationType === '3'"
              class="dotted_border"
            >
              <el-col>
                <ifa-input-text
                  id="kaitouNaiyouConfirm"
                  v-model="confirmForm.kaitouNaiyou"
                  type="textarea"
                  :rows="6"
                  resize="vertical"
                  label-class="validation-error-width"
                  :disabled="true"
                  style="width: 100%;"
                ></ifa-input-text>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :offset="1"
          style="margin-top: 1rem"
        >
          <span class="dialog-footer">
            <ifa-button
              text="登録"
              width="90"
              color="primary"
              style="padding-left: 0"
              :form="formRef"
              action-type="requestAction"
              action-id="SUB0202_0106-04#A002"
              :request-model="A002RequestModel"
              @response-handler="comfirmHandlerA002"
            ></ifa-button>
          </span>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea.vue'
import { initTextareaHeight, formatToiawaseMei, displayToiawaseMei } from './js/IfaContactUtils'
import { IfaContactConfirmFormModel } from './js/IfaContactConfirmFormModel'
import { IfaContactConfirmUpdateA002RequestModel } from './js/IfaContactConfirmUpdateA002RequestModel'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    operationType: { type: String, required: true },
    confirmData: { type: Object, required: true }
  },
  emits: ['update:isVisible', 'back-modal', 'contact-complete'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      formRef: {},
      confirmForm: new IfaContactConfirmFormModel(),
      messages: {
        mains: []
      }
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    A002RequestModel() {
      const ifaContactConfirmUpdateA002RequestModel = new IfaContactConfirmUpdateA002RequestModel(
        {
          ...this.confirmForm.afterData,
          toiawaseDCd: this.confirmForm.afterData.toiawaseDCd,
          toiawaseDMei: displayToiawaseMei(this.confirmForm.afterData.toiawaseDList, this.confirmForm.afterData.toiawaseDCd),
          toiawaseCd: this.confirmForm.afterData.toiawaseCd,
          toiawaseMei: displayToiawaseMei(this.confirmForm.afterData.toiawaseList, this.confirmForm.afterData.toiawaseCd),
          toiawaseSCd: this.confirmForm.afterData.toiawaseSCd,
          toiawaseSMei: displayToiawaseMei(this.confirmForm.afterData.toiawaseSList, this.confirmForm.afterData.toiawaseSCd),
          toiawaseNaiyou: this.confirmForm.toiawaseNaiyou,
          kaitouNaiyou: this.confirmForm.kaitouNaiyou,
          operationType: this.operationType,
          ifaNyuuryokuFlg: this.confirmForm.ifaNyuuryokuFlg
        }
      )
      if (this.operationType !== '1') {
        ifaContactConfirmUpdateA002RequestModel.ifaToiawaseNo = this.confirmForm.ifaToiawaseNo
      }
      return ifaContactConfirmUpdateA002RequestModel
    },
    setDisable() {
      if (this.operationType === '1' || this.operationType === '2') {
        return false
      } else {
        return true
      }
    }
  },
  mounted() {
    this.formRef = this.$refs.formRef
  },
  methods: {
    onShow() {
      Object.assign(this.confirmForm, this.confirmData)
      let messageTitle
      if (this.operationType === '1') {
        messageTitle = '接触履歴入力'
        this.confirmForm.dialogTitle.name = '接触履歴入力確認'
      } else if (this.operationType === '2') {
        messageTitle = '管理項目修正'
        this.confirmForm.dialogTitle.name = '管理項目修正確認'
      } else {
        messageTitle = '追加入力'
        this.confirmForm.dialogTitle.name = '追加入力確認'
      }
      this.messages.mains.length = 0
      this.messages.mains.push(messageTitle + 'はまだ完了していません。画面下の登録ボタンを押下してください。')
      // 初期化時に「内容」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#toiawaseNaiyouConfirm'))
      // 初期化時に「追加入力」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#kaitouNaiyouConfirm'))
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$emit('back-modal')
    },
    // 登録用リクエストのレスポンスハンドラー
    comfirmHandlerA002() {
      this.$emit('contact-complete', this.confirmData)
    },
    displayToiawaseMei,
    formatToiawaseMei
  }
}
</script>
<style lang="scss" scoped>
.inner-box-line {
  padding: 0.5rem 0.2rem 0.2rem 0.5rem;
}
:deep(.el-textarea__inner) {
  min-height: 136px !important;
}
</style>
