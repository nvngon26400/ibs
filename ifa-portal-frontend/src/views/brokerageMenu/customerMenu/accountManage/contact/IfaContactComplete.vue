<template>
  <el-dialog
    v-model="vmIsVisible"
    width="1200px"
    :title="completeForm.dialogTitle.name"
    :show-close="false"
    :center="true"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    class="buy-background-color"
    @open="onShow"
  >
    <ifa-message-area
      :main-messages="messages.mains"
    ></ifa-message-area>
    <el-form
      :model="completeForm"
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
                {{ formatToiawaseMei(displayToiawaseMei(completeForm.afterData.toiawaseDList, completeForm.beforeData.toiawaseDCd), displayToiawaseMei(completeForm.beforeData.toiawaseList, completeForm.beforeData.toiawaseCd), displayToiawaseMei(completeForm.beforeData.toiawaseSList, completeForm.beforeData.toiawaseSCd)) }}
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.toiawaseDCd + completeForm.beforeData.toiawaseCd + completeForm.beforeData.toiawaseSCd !== completeForm.afterData.toiawaseDCd + completeForm.afterData.toiawaseCd + completeForm.afterData.toiawaseSCd">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ formatToiawaseMei(displayToiawaseMei(completeForm.afterData.toiawaseDList, completeForm.afterData.toiawaseDCd), displayToiawaseMei(completeForm.afterData.toiawaseList, completeForm.afterData.toiawaseCd), displayToiawaseMei(completeForm.afterData.toiawaseSList, completeForm.afterData.toiawaseSCd)) }}
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
                  :code-key="completeForm.beforeData.sessyokuKeiro"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.sessyokuKeiro !== completeForm.afterData.sessyokuKeiro">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="SESSYOKU_KEIRO"
                  :disp-pattern="1"
                  :code-key="completeForm.afterData.sessyokuKeiro"
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
                  :code-key="completeForm.beforeData.juuyoudo"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.juuyoudo !== completeForm.afterData.juuyoudo">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="JUUYOUDO"
                  :disp-pattern="1"
                  :code-key="completeForm.afterData.juuyoudo"
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
                {{ completeForm.beforeData.cream === '1' ? '※': '' }}
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.cream !== completeForm.afterData.cream">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ completeForm.afterData.cream === '1' ? '※': '' }}
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
                {{ completeForm.beforeData.request === '1' ? '※': '' }}
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.request !== completeForm.afterData.request">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ completeForm.afterData.request === '1' ? '※': '' }}
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
                  :code-key="completeForm.beforeData.taiouSts"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.taiouSts !== completeForm.afterData.taiouSts">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="TAIOU_STS"
                  :disp-pattern="1"
                  :code-key="completeForm.afterData.taiouSts"
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
                  :code-key="completeForm.beforeData.houkou"
                  style="font-size: 16px;"
                ></ifa-text>
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.houkou !== completeForm.afterData.houkou">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                <ifa-text
                  code-list-id="NYUUDEN_HOUKOU"
                  :disp-pattern="1"
                  :code-key="completeForm.afterData.houkou"
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
                {{ $_getFormattedDateValue(completeForm.beforeData.houmonbi) }}
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.houmonbi !== completeForm.afterData.houmonbi">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(completeForm.afterData.houmonbi) }}
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
                {{ $_getFormattedDateValue(completeForm.beforeData.nextHoumonbi) }}
              </el-col>
              <el-col :span="3">
                <span v-show="completeForm.beforeData.nextHoumonbi !== completeForm.afterData.nextHoumonbi">→</span>
              </el-col>
              <el-col
                v-if="operationType !== '1'"
                :span="7"
                :offset="1"
              >
                {{ $_getFormattedDateValue(completeForm.afterData.nextHoumonbi) }}
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
                  id="toiawaseNaiyouComplete"
                  v-model="completeForm.toiawaseNaiyou"
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
                  id="kaitouNaiyouComplete"
                  v-model="completeForm.kaitouNaiyou"
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
              :text="setButtonText"
              action-type="originalAction"
              style="padding-left: 0;"
              @app-action-handler="moveToOriginPage"
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
import { IfaContactCompleteFormModel } from './js/IfaContactCompleteFormModel'

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
    completeData: { type: Object, required: true }
  },
  emits: ['update:isVisible', 'move-to-origin', 'close-modal'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      completeForm: new IfaContactCompleteFormModel(),
      messages: {
        mains: []
      }
    }
  },
  computed: {
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    setDisable() {
      if (this.operationType === '1' || this.operationType === '2') {
        return false
      } else {
        return true
      }
    },
    setButtonText() {
      if (this.operationType === '1') {
        return '接触履歴へ'
      } else {
        return '接触履歴詳細へ'
      }
    }
  },
  methods: {
    onShow() {
      this.messages.mains.length = 0
      Object.assign(this.completeForm, this.completeData)
      if (this.operationType === '1') {
        this.completeForm.dialogTitle.name = '接触履歴入力完了'
        this.messages.mains.push('下記の内容で接触履歴入力を受け付けました。')
      } else if (this.operationType === '2') {
        this.completeForm.dialogTitle.name = '管理項目修正完了'
        this.messages.mains.push('下記の内容で管理項目修正を受け付けました。')
      } else {
        this.completeForm.dialogTitle.name = '追加入力完了'
        this.messages.mains.push('下記の内容で追加入力を受け付けました。')
      }
      // 初期化時に「内容」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#toiawaseNaiyouComplete'))
      // 初期化時に「追加入力」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#kaitouNaiyouComplete'))
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 処理区分が'1'(接触履歴入力)の場合、"接触履歴へ"
    // 上記を満たさない場合、”接触履歴詳細へ”
    moveToOriginPage() {
      this.$emit('move-to-origin')
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
:deep(.main-message) {
  margin: 1rem 0 0.5rem 0;
}
:deep(.el-textarea__inner) {
  min-height: 136px !important;
}
</style>
