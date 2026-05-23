<template>
  <!-- SUB0202_0110-01_2その他余力拘束注文確認 -->
  <el-dialog
    v-model="vmIsVisible"
    :title="form.screenTitle"
    :center="true"
    :show-close="false"
    :before-close="actionA003Handler"
    :close-on-click-modal="false"
    :class="'buy-background-color'"
    width="1200px"
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
          @app-action-handler="actionA003Handler"
        ></ifa-button>
      </el-col>
    </el-row>
    <!-- エラー/警告表示 -->
    <ifa-message-area
      :main-messages="['注文はまだ完了していません。画面下の注文発注ボタンを押下してください。']"
    ></ifa-message-area>
    <!-- 顧客情報 -->
    <el-row style="font-weight: bold;">
      <el-col :offset="1">
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
        <el-icon style="position: relative; top: 3px;"><component :is="customerInfo.corporationType === '1' ? 'OfficeBuilding' : 'Avatar'"></component></el-icon>
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
    <!-- /顧客情報 -->
    <!-- 注文内容(復唱項目) -->
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
            style="padding: 0.5rem 0.5rem; padding-left: 1rem;"
          >
            <span>ご注文内容（復唱項目）</span>
          </el-row>
          <hr>
          <!-- 選択口座  ジュニアNISA口座開設フラグが'0'(未開設)の場合、非表示-->
          <el-row
            v-if="form.jrNisageneralAccountFlag != IS_UNOPEN_JRNISA_ACCOUNT"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>選択口座</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'OTHER_REMAINPOWER_RESTRAIN_ACCOUNT_TYPE'"
                  :disp-pattern="1"
                  :code-key="form.accountType"
                  style="font-size: 16px;"
                ></ifa-text>
              </span>
            </el-col>
          </el-row>
          <!-- 拘束種別 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束種別</span>
            </el-col>
            <el-col :span="16">
              <span>
                <ifa-text
                  :code-list-id="'RESTRAIN_TYPE'"
                  :disp-pattern="1"
                  :code-key="form.restrainType"
                  style="font-size: 16px;"
                ></ifa-text>
              </span>
            </el-col>
          </el-row>
          <!-- 拘束金額(買付余力) -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束金額(買付余力)</span>
            </el-col>
            <el-col :span="16">
              <!-- 0 または ブランクの場合、"-"(ハイフン)を表示する -->
              <span>{{ isInvalidAmount(form.netAmount) ? '-' : $_withCommaInteger(form.netAmount) + ' 円' }}</span>
            </el-col>
          </el-row>
          <!-- 拘束金額(NISA成長投資枠)  ジュニアNISA口座開設フラグが'1'(開設済)、かつ選択口座が'1'(ジュニアNISA口座)の場合、非表示 -->
          <el-row
            v-if="!(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束金額(NISA成長投資枠)</span>
            </el-col>
            <el-col :span="16">
              <!-- 0 または ブランクの場合、"-"(ハイフン)を表示する -->
              <span>{{ isInvalidAmount(form.isaSeityoLimitAmount) ? '-' : $_withCommaInteger(form.isaSeityoLimitAmount) + ' 円' }}</span>
            </el-col>
          </el-row>
          <!-- 拘束金額(NISAつみたて投資枠)  ジュニアNISA口座開設フラグが'1'(開設済)、かつ選択口座が'1'(ジュニアNISA口座)の場合、非表示 -->
          <el-row
            v-if="!(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束金額(NISAつみたて投資枠)</span>
            </el-col>
            <el-col :span="16">
              <!-- 0 または ブランクの場合、"-"(ハイフン)を表示する -->
              <span>{{ isInvalidAmount(form.isaTsumitateLimitAmount) ? '-' : $_withCommaInteger(form.isaTsumitateLimitAmount) + ' 円' }}</span>
            </el-col>
          </el-row>
          <!-- 拘束期限 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束期限</span>
            </el-col>
            <el-col :span="16">
              <span>{{ form.restrainDateTo ? $_getFormattedDateValue(form.restrainDateTo) : '' }}</span>
            </el-col>
          </el-row>
          <!-- 拘束理由 -->
          <el-row class="dotted_border">
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>拘束理由</span>
            </el-col>
            <el-col :span="16">
              <span style="overflow-wrap: anywhere; white-space: break-spaces;">{{ form.restrainReason }}</span>
            </el-col>
          </el-row>
          <!-- 注文後買付余力  拘束種別が'4'(NISA(成長投資枠)投資可能枠)、'5'(NISA(つみたて投資枠)投資可能枠)のいずれかの場合、非表示 -->
          <el-row
            v-if="!(form.restrainType === '4' || form.restrainType === '5')"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注文後買付余力</span>
            </el-col>
            <el-col :span="16">
              <!-- 0の場合、"-"(ハイフン)を表示する -->
              <span>{{ isInvalidAmount(form.buyingPowerTotalAfter) ? '-' : $_withCommaInteger(form.buyingPowerTotalAfter) + ' 円' }}</span>
            </el-col>
          </el-row>
          <!-- 注文後NISA成長投資枠 -->
          <!-- ジュニアNISA口座開設フラグが'1'(開設済)、かつ選択口座が'1'(ジュニアNISA口座)の場合、非表示 -->
          <!-- 注文後NISA成長投資枠が 0 または ブランクの場合、非表示 -->
          <!-- 拘束種別が'1'(買付余力)の場合、非表示 -->
          <el-row
            v-if="!(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)
              && isValidLimit(form.isaSeityoLimitAmountAfter) && form.restrainType !== '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注文後NISA成長投資枠</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaInteger(form.isaSeityoLimitAmountAfter) + ' 円' }}</span>
            </el-col>
          </el-row>
          <!-- 注文後NISAつみたて投資枠 -->
          <!-- ジュニアNISA口座開設フラグが'1'(開設済)、かつ選択口座が'1'(ジュニアNISA口座)の場合、非表示 -->
          <!-- 注文後NISAつみたて投資枠が 0 または ブランクの場合、非表示 -->
          <!-- 拘束種別が'1'(買付余力)の場合、非表示-->
          <el-row
            v-if="!(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)
              && isValidLimit(form.isaTsumitateLimitAmountAfter) && form.restrainType !== '1'"
            class="dotted_border"
          >
            <el-col
              :span="8"
              class="_bold_black_m"
            >
              <span>注文後NISAつみたて投資枠</span>
            </el-col>
            <el-col :span="16">
              <span>{{ $_withCommaInteger(form.isaTsumitateLimitAmountAfter) + ' 円' }}</span>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- 注文発注ボタン -->
    <el-row style="margin-top: 20px;">
      <el-col
        :offset="1"
        :span="22"
        style="text-align: left;"
      >
        <ifa-button
          id="btnOtherRemainConfirm"
          text="注文発注"
          style="padding-left: 0;"
          action-type="requestAction"
          :control-auth-enabled="false"
          :request-model="ifaOtherRemainPowerRestrainInputConfirmA002RequestModel"
          action-id="SUB0202_0110-01_2#A002"
          @response-handler="responseHandlerActionA002($event)"
        ></ifa-button>
      </el-col>
    </el-row>
  </el-dialog>
  <!-- 以下：アクション処理に対応する画面コンポーネントを実装する -->
  <!-- A001アクションのリクエスト処理:画面初期化 -->
  <ifa-requester
    id="ifaOtherRemainPowerRestrainInputConfirmA001"
    action-id="SUB0202_0110-01_2#A001"
    action-type="requestAction"
    :request-model="formData"
    @response-handler="responseHandlerActionA001($event)"
  ></ifa-requester>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaNoticeInfo from '@/components/icon/IfaNoticeInfo.vue'
import IfaMessageArea from '@/components/Message/IfaMessageArea'
import { IfaOtherRemainPowerRestrainInputConfirmFormModel } from './js/IfaOtherRemainPowerRestrainInputConfirmFormModel'
import { IfaOtherRemainPowerRestrainInputConfirmA002RequestModel } from './js/IfaOtherRemainPowerRestrainInputConfirmA002RequestModel'

export default {
  components: {
    IfaNoticeInfo,
    IfaMessageArea
  },
  // 親コンポーネントから受け取る値
  props: {
    // 本コンポーネントの表示・表示
    isVisible: { type: Boolean, required: true },
    formData: { type: Object, required: true },
    customerInfo: { type: Object, required: true }
  },
  emits: ['close-modal', 'input-complete', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      // 定数：ジュニアNISA口座開設済み
      IS_OPENED_JRNISA_ACCOUNT: '1',
      // 定数：ジュニアNISA口座開設待ち
      IS_UNOPEN_JRNISA_ACCOUNT: '0',
      // 定数：口座区分: 1.ジュニアNISA口座
      ACCOUNT_TYPE_JRNISA: '1',
      // 画面のフォームモデルを初期化
      form: new IfaOtherRemainPowerRestrainInputConfirmFormModel(),
      messages: {
        mains: [],
        errors: [],
        warnings: [],
        infos: []
      }
    }
  },
  computed: {
    ifaOtherRemainPowerRestrainInputConfirmA002RequestModel() {
      return new IfaOtherRemainPowerRestrainInputConfirmA002RequestModel(this.form)
    },
    customerName() {
      return this.customerInfo.customerNameKanji + '（' + this.customerInfo.customerNameKana + '）'
    },
    accountNumber() {
      return `${this.customerInfo.butenCode}-${this.$_zeroPadding(this.customerInfo.accountNumber, 7)}`
    },
    userInfo() {
      return this.$store.getters.userAccount
    }
  },
  methods: {
    onShow() {
      this.form = new IfaOtherRemainPowerRestrainInputConfirmFormModel()
      this.$nextTick(() => {
        document.getElementById('ifaOtherRemainPowerRestrainInputConfirmA001').click()
      })
    },
    /**
     * 関数：A001アクションのレスポンス処理:画面初期化
     * @param response レスポンス
     */
    responseHandlerActionA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.requestedTime = response.requestedTime.split(' ')[0]
    },
    // 戻るボタン
    actionA003Handler() {
      this.$emit('close-modal')
    },
    // A002アクション:注文発注
    responseHandlerActionA002: function(data) {
      this.$emit('input-complete', data.dataList[0])
    },
    /**
     * 指定された値が有効かどうかを判断する関数
     * @param {string|null} value - 判定する文字列またはnull
     * @returns {boolean} - 有効な場合はtrue、無効な場合はfalse
     */
    isValidLimit(value) {
      return value != null && value !== '' && value !== '0'
    },
    /**
     * 指定された値が無効かどうかを判断する関数
     * @param {string|null} value - 判定する文字列またはnull
     * @returns {boolean} - 無効な場合はtrue、有効な場合はfalse
     */
    isInvalidAmount(value) {
      return value === null || value === '' || value === '0'
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/orderStatusList.scss";
</style>
