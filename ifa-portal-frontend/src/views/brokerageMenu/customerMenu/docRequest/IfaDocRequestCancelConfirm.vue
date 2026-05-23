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
    <!-- 戻るボタン -->
    <el-row>
      <el-col
        :offset="1"
        :span="22"
        style="text-align: right;"
      >
        <ifa-button
          id="btnBack"
          text="戻る"
          color="secondary"
          action-type="originalAction"
          style="padding-right: 0;"
          @app-action-handler="onDialogClose"
        ></ifa-button>
      </el-col>
    </el-row>

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

    <!-- 取消実行ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          v-if="form.bmKoufuShubetsu === '0'"
          id="btnDocCancel"
          text="取消実行"
          style="padding-left: 0;"
          action-id="SUB0202_0704-03_1#A002"
          action-type="requestAction"
          :request-model="A002RequestModel"
          @response-handler="handlerExecuteA002($event)"
        ></ifa-button>
        <ifa-button
          v-if="form.bmKoufuShubetsu !== '0'"
          id="btnDocBmCancel"
          text="取消実行"
          style="padding-left: 0;"
          action-id="SUB0202_0704-03_1#A005"
          action-type="requestAction"
          :request-model="A005RequestModel"
          @response-handler="handlerExecuteA005($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import { IfaDocRequestCancelConfirmFormModel } from './js/IfaDocRequestCancelConfirmFormModel'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaDocRequestCancelConfirmA002RequestModel } from './js/IfaDocRequestCancelConfirmA002RequestModel'
import { IfaDocRequestCancelConfirmA005RequestModel } from './js/IfaDocRequestCancelConfirmA005RequestModel'
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
  emits: ['close-modal', 'cancel-finish', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: new IfaDocRequestCancelConfirmFormModel(),
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
    },
    A002RequestModel() {
      return new IfaDocRequestCancelConfirmA002RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaDocRequestCancelConfirmA005RequestModel(this.form)
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
      this.messages.mains.push('取消はまだ完了していません。')
      this.messages.mains.push('画面下の取消実行ボタンを押下してください。')
      this.messageKey++
    },
    handlerExecuteA002(res) {
      this.$emit('cancel-finish')
    },
    handlerExecuteA005(res) {
      this.$emit('cancel-finish')
    }
  }
}
</script>

<style lang="scss">
  @import '@/styles/orderStatusList.scss';
</style>
<style lang="scss" scoped>
.content-wrapper {
  font-size: 16px;
  overflow-wrap: anywhere;
  white-space: break-spaces;
}
.error-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: red;
  font-size: 16px;
  font-weight: bold;
  white-space: pre-wrap;
}
.warning-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  font-size: 14px;
  white-space: pre-wrap;
}
.info-message {
  margin: 0.5rem 0;
  padding: 0 4rem;
  color: #000;
  font-size: 14px;
  white-space: pre-wrap;
}
</style>
