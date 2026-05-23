<template>
  <!-- 書類請求取消確認ダイアログ SUB0202_0704-03_1 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.title"
    width="1200px"
    :center="true"
    :show-close="false"
    :before-close="onDialogClose"
    :close-on-click-modal="false"
    style="background-color: #fef0f0;"
  >

    <!-- エラー/警告表示 -->
    <ifa-message-area
      :key="messageKey"
      :main-messages="messages.mains"
      :error-messages="messages.errors"
      :warning-messages="messages.warns"
    ></ifa-message-area>

    <!-- ヘッダ -->
    <!-- 顧客情報/口座情報 -->
    <el-row
      style="font-weight: bold;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <span>{{ $_out(accountNumber) }}</span>
      </el-col>
    </el-row>
    <el-row
      class="_bold_black_l"
      style="padding-top: 0.5rem; font-size: 20px;"
    >
      <el-col
        :offset="1"
        :span="22"
      >
        <span style="position: relative; top: 3px;">
          <el-icon><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
        </span>
        <span>{{ $_out(customerName) }}</span>
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
      <div style="margin-bottom: 0.5rem;"></div>
    </el-row>

    <!-- 書類請求取消内容（復唱項目） -->
    <el-row>
      <el-col
        :span="22"
        :offset="1"
      >
        <el-card
          class="box-card"
          style="font-size: 16px;"
        >
          <el-row
            class="_bold_black_m"
            style="padding-top: 0.5rem; padding-left: 1rem;"
          >
            <span>書類請求取消内容（復唱項目）</span>
          </el-row>
          <hr>
          <!-- 分類 -->
          <el-row class="dotted_border"
                  style="padding-bottom: 0.5rem;"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>分類</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.shoruiBunruiMei) }}</span>
            </el-col>
          </el-row>
          <!-- 書類名 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>書類名</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.shoruimei) }}</span>
            </el-col>
          </el-row>
          <div v-if="form.bmKoufuShubetsu === '0'">
            <!-- 部数 -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>部数</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.busuu) }}</span>
              </el-col>
            </el-row>
          </div>
          <!-- 交付区分 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>交付区分</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.hassouStsName) }}</span>
            </el-col>
          </el-row>
          <div v-if="form.bmKoufuShubetsu === '0'">
            <!-- 内容 -->
            <el-row v-if="form.naiyouCaption"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.naiyouCaption) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.naiyou) }}</span>
              </el-col>
            </el-row>
            <!-- 備考 -->
            <el-row v-if="form.bikouCaption"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.bikouCaption) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.bikou) }}</span>
              </el-col>
            </el-row>
            <!-- オプション1 -->
            <el-row v-if="form.option1"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option1) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei1) }}</span>
              </el-col>
            </el-row>
            <!-- オプション2 -->
            <el-row v-if="form.option2"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option2) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei2) }}</span>
              </el-col>
            </el-row>
            <!-- オプション3 -->
            <el-row v-if="form.option3"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.option3) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.sentakuMei3) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト1 -->
            <el-row v-if="form.txt1"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt1) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou1) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト2 -->
            <el-row v-if="form.txt2"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt2) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou2) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト3 -->
            <el-row v-if="form.txt3"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt3) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou3) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト4 -->
            <el-row v-if="form.txt4"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt4) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou4) }}</span>
              </el-col>
            </el-row>
            <!-- テキスト5 -->
            <el-row v-if="form.txt5"
                    class="dotted_border"
            >
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>{{ $_out(form.txt5) }}</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.txtNaiyou5) }}</span>
              </el-col>
            </el-row>
          </div>
          <div v-if="form.bmKoufuShubetsu === '1'">
            <!-- 投信銘柄コード -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>投信銘柄コード</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.meigaraCd) }}</span>
              </el-col>
            </el-row>
            <!-- 投信銘柄名 -->
            <el-row class="dotted_border">
              <el-col
                :span="8"
                class="_bold_black_m"
              >
                <span>投信銘柄名</span>
              </el-col>
              <el-col
                :span="16"
                class="content-wrapper"
              >
                <span>{{ $_out(form.meigaraMei) }}</span>
              </el-col>
            </el-row>
          </div>
          <!-- 取扱者 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>取扱者</span>
            </el-col>
            <el-col
              :span="16"
              class="content-wrapper"
            >
              <span>{{ $_out(form.userNm) }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- 書類請求一覧へボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnDocCancel"
          text="書類請求一覧へ"
          action-type="originalAction"
          style="padding-left: 0;"
          @app-action-handler="handleMoveToInput"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaDocRequestCancelCompleteFormModel } from './js/IfaDocRequestCancelCompleteFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  props: {
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'move-to-input-by-cancel', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDocRequestCancelCompleteFormModel(),
      cancelComfirmVisible: false,
      confirmData: {},
      messages: {
        mains: [],
        errors: [],
        warns: []

      },
      messageKey: 0
    }
  },
  computed: {
    customerName() {
      return this.$_out(this.customerInfo.customerNameKanji) + '（' + this.$_out(this.customerInfo.customerNameKana) + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    }
  },
  methods: {
    onShow() {
      Object.assign(this.form, this.formData)
      this.setMsg()
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    setMsg() {
      this.messages.mains = []
      this.messages.errors = []
      this.messages.warns = []
      this.messages.mains.push('下記の内容で書類請求取消を受け付けました。')
      this.messageKey++
    },
    handleMoveToInput() {
      this.$emit('move-to-input-by-cancel')
    }
  }
}
</script>

<style lang="scss">
@import "@/styles/orderStatusList.scss";
.content-wrapper {
  font-size: 16px;
  overflow-wrap: anywhere;
  white-space: break-spaces;
}
</style>
