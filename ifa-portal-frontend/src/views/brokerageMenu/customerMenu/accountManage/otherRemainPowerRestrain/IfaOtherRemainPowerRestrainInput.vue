<template>
  <!-- SUB0202_0110-01_1その他余力拘束注文入力 -->
  <div>
    <screen-title :text="form.inputTitle"></screen-title>
    <div
      ref="focusCard"
      class="caption_card"
      tabindex="-1"
    >
      <el-row>
        <el-card
          class="box-card input_area"
        >
          <div>
            <el-form
              ref="form"
              :model="form"
              label-width="260px"
              label-position="left"
            >
              <!-- フォーム: 口座区分 ジュニアNISA口座開設フラグは'1'(開設済)の場合、表示する-->
              <div
                v-if="form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col class="label-left"
                          :span="12"
                  >
                    <ifa-input-radio
                      v-model="form.accountType"
                      label="口座"
                      prop="accountType"
                      required
                      :disabled="setDisable"
                      :code-list-id="'OTHER_REMAINPOWER_RESTRAIN_ACCOUNT_TYPE'"
                      :disp-pattern="1"
                      :select-pattern="1"
                      class="label"
                      @change="selectAccountType"
                    ></ifa-input-radio>
                  </el-col>
                  <el-col
                    class="label-left"
                  >
                    <!-- リセットボタン -->
                    <span class="form-reset-button__wrapper">
                      <ifa-button
                        :disabled="setDisable"
                        text="リセット"
                        color="secondary"
                        tabindex="-1"
                        action-type="originalAction"
                        @app-action-handler="execHandlerActionA003()"
                      ></ifa-button>
                    </span>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束種別 -->
              <div class="form-area__section">
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col class="label-left"
                          :span="12"
                  >
                    <ifa-input-select
                      v-model="form.restrainType"
                      label="拘束種別"
                      prop="restrainType"
                      required
                      :code-list-id="'RESTRAIN_TYPE'"
                      :disp-pattern="1"
                      :select-pattern="(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA) ? 2 : 1"
                      size="small"
                      style="width: 380px;"
                      placeholder=""
                      :disabled="setDisable"
                      :clearable="false"
                      @change="selectRestrainType"
                    ></ifa-input-select>
                  </el-col>
                  <el-col
                    class="label-left"
                  >
                    <!-- リセットボタン ジュニアNISA口座開設フラグは'0'(未開設)の場合、表示する-->
                    <span
                      v-if="form.jrNisageneralAccountFlag === IS_UNOPEN_JRNISA_ACCOUNT"
                      class="form-reset-button__wrapper"
                    >
                      <ifa-button
                        :disabled="setDisable"
                        text="リセット"
                        color="secondary"
                        tabindex="-1"
                        action-type="originalAction"
                        @app-action-handler="execHandlerActionA003()"
                      ></ifa-button>
                    </span>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束金額(買付余力) -->
              <div
                v-if="(form.restrainType === '1' || form.restrainType === '6' || form.restrainType === '7')"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <ifa-input-amount
                      v-model="form.netAmount"
                      prop="netAmount"
                      label="拘束金額(買付余力)"
                      :disabled="setDisable"
                      :domain="IfaYen150DomainModel"
                      :min="1"
                      unit="円"
                      required
                    ></ifa-input-amount>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 買付余力 -->
              <div
                v-if="
                  (form.restrainType === '1' || form.restrainType === '6' || form.restrainType === '7')
                    && ((isValidString(form.buyingPowerTotal)
                      && (form.jrNisageneralAccountFlag === IS_UNOPEN_JRNISA_ACCOUNT || (form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType !== ACCOUNT_TYPE_JRNISA)))
                      || (isValidString(form.buyingPowerTotalJrnisa)
                        && (form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)))"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <el-form-item label="買付余力">
                      <!-- 買付余力 -->
                      <!-- ジュニアNISA口座開設フラグが'0'(未開設) または -->
                      <!-- (ジュニアNISA口座開設フラグが'1'(開設済)、かつ 口座が'1'(ジュニアNISA口座)以外の場合 -->
                      <!-- ブラック以外の場合 表示する -->
                      <span
                        v-if="(form.jrNisageneralAccountFlag === IS_UNOPEN_JRNISA_ACCOUNT || (form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType !== ACCOUNT_TYPE_JRNISA))
                          && isValidString(form.buyingPowerTotal)"
                      >
                        {{ $_withCommaInteger(form.buyingPowerTotal) + ' 円' }}
                      </span>
                      <!-- 買付余力(JrNISA) -->
                      <!-- ジュニアNISA口座開設フラグが'1'(開設済)、かつ 口座が'1'(ジュニアNISA口座)の場合 -->
                      <!-- ブラック以外の場合 表示する -->
                      <span
                        v-if="(form.jrNisageneralAccountFlag === IS_OPENED_JRNISA_ACCOUNT && form.accountType === ACCOUNT_TYPE_JRNISA)
                          && isValidString(form.buyingPowerTotalJrnisa)"
                      >
                        {{ $_withCommaInteger(form.buyingPowerTotalJrnisa) + ' 円' }}
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束金額(NISA成長投資枠) -->
              <div
                v-if="form.restrainType === '4' || form.restrainType=== '6' "
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <ifa-input-amount
                      v-model="form.isaSeityoLimitAmount"
                      prop="isaSeityoLimitAmount"
                      label="拘束金額(NISA成長投資枠)"
                      :disabled="setDisable"
                      :domain="IfaYen100DomainModel"
                      :min="1"
                      unit="円"
                      required
                    ></ifa-input-amount>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 投資可能枠－NISA(成長投資枠) -->
              <div
                v-if="(form.restrainType=== '4' || form.restrainType === '6') && isValidString(form.isaSeityoBuyLimit)"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <el-form-item label="投資可能枠－NISA(成長投資枠)">
                      <span>
                        {{ $_withCommaInteger(form.isaSeityoBuyLimit) + ' 円' }}
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束金額(NISAつみたて投資枠) -->
              <div
                v-if="form.restrainType === '5' || form.restrainType === '7'"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <ifa-input-amount
                      v-model="form.isaTsumitateLimitAmount"
                      prop="isaTsumitateLimitAmount"
                      label="拘束金額(NISAつみたて投資枠)"
                      :disabled="setDisable"
                      :domain="IfaYen100DomainModel"
                      :min="1"
                      unit="円"
                      required
                    ></ifa-input-amount>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 投資可能枠－NISA(つみたて投資枠) -->
              <div
                v-if="(form.restrainType === '5' || form.restrainType === '7') && isValidString(form.isaTsumitateBuyLimit)"
                class="form-area__section"
              >
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <el-form-item label="投資可能枠－NISA(つみたて投資枠)">
                      <span>
                        {{ $_withCommaInteger(form.isaTsumitateBuyLimit) + ' 円' }}
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束期限 -->
              <div class="form-area__section">
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    :span="10"
                    class="label-left"
                    style="flex-shrink: 0 !important;"
                  >
                    <ifa-date-picker
                      v-model="form.restrainDateTo"
                      size="small"
                      prop="restrainDateTo"
                      label="拘束期限"
                      :disabled="setDisable"
                      required
                    ></ifa-date-picker>
                  </el-col>
                  <el-col
                    :span="8"
                    style="margin-top: 0.6rem;"
                  >
                    <span>
                      ※拘束期限日27:00頃に余力拘束が解除されます。
                    </span>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 拘束理由 -->
              <div class="form-area__section">
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <ifa-input-text
                      id="restrainReason"
                      v-model="form.restrainReason"
                      prop="restrainReason"
                      label="拘束理由"
                      original-screen-id="SUB0202_0110-01_1"
                      :domain="IfaText100DomainModel"
                      :disabled="setDisable"
                      style="width:800px;"
                    ></ifa-input-text>
                  </el-col>
                </el-row>
              </div>
              <!-- フォーム: 確認項目 -->
              <div class="form-area__section">
                <el-row
                  class="el-col-offset-1"
                >
                  <el-col
                    class="label-left"
                  >
                    <ifa-input-check
                      ref="confirmItemParent"
                      v-model="form.confirmItem"
                      prop="confirmItem"
                      label="確認項目"
                      :disabled="setDisable"
                      required
                      :code-list-id="'INSIDER_CONFIRM'"
                      :disp-pattern="3"
                      :select-pattern="2"
                    ></ifa-input-check>
                  </el-col>
                </el-row>
              </div>
              <div class="form-area__section">
                <!-- フォーム: 注文確認 -->
                <ifa-button
                  id="btnOrderConfirm"
                  class="label-btn"
                  :disabled="setDisable"
                  text="注文確認"
                  color="primary"
                  action-type="requestAction"
                  action-id="SUB0202_0110-01_1#A002"
                  :form="formRef"
                  :request-model="ifaOtherRemainPowerRestrainInputA002RequestModel"
                  @response-handler="responseHandlerActionA002"
                ></ifa-button>
              </div>
            </el-form>
          </div>
        </el-card>
      </el-row>
    </div>
    <screen-title :text="form.listTitle"></screen-title>
    <div class="caption_card">
      <el-row>  <!-- 一覧表示部エリア -->
        <grid-table
          ref="gridTable"
          :options="pqGridOption"
          :auto-refresh="false"
        ></grid-table>
      </el-row>
    </div>
  </div>
  <!-- 以下：アクション処理に対応する画面コンポーネントを実装する -->
  <!-- A001アクションのリクエスト処理:画面初期化 -->
  <ifa-requester
    id="ifaOtherRemainPowerRestrainInputA001"
    action-id="SUB0202_0110-01_1#A001"
    action-type="requestAction"
    @response-handler="responseHandlerActionA001($event)"
    @response-error-handler="errorHandlerActionA001($event)"
  ></ifa-requester>
  <!-- A004アクションのリクエスト処理:注文取消 -->
  <ifa-requester
    id="ifaOtherRemainPowerRestrainInputA004"
    action-id="SUB0202_0110-02_1#A001"
    action-type="requestAction"
    :request-model="ifaOtherRemainPowerRestrainInputA004RequestModel"
    @response-handler="responseHandlerActionA004($event)"
  ></ifa-requester>
  <!-- ダイアログ：注文入力確認画面へ -->
  <ifa-other-remain-power-restrain-input-confirm
    ref="ifaOtherRemainPowerRestrainInputConfirm"
    :is-visible="isShowInputConfirm"
    :form-data="inputConfirmForm"
    :customer-info="customerInfo"
    @close-modal="handleCloseModal('inputConfirm')"
    @input-complete="handleInputComplete"
  ></ifa-other-remain-power-restrain-input-confirm>
  <!-- ダイアログ：注文入力完了画面へ -->
  <ifa-other-remain-power-restrain-input-complete
    :is-visible="isShowInputComplete"
    :form-data="inputCompleteForm"
    :customer-info="customerInfo"
    @close-modal="handleCloseModal('all');execHandlerActionA001();"
  ></ifa-other-remain-power-restrain-input-complete>
  <!-- ダイアログ：注文取消確認画面へ -->
  <ifa-other-remain-power-restrain-cancel-confirm
    ref="ifaOtherRemainPowerRestrainCancelConfirm"
    :is-visible="isShowCancelConfirm"
    :form-data="cancelConfirmForm"
    :customer-info="customerInfo"
    @close-modal="handleCloseModal('cancelConfirm')"
    @cancel-complete="handleCancelComplete"
  ></ifa-other-remain-power-restrain-cancel-confirm>
  <!-- ダイアログ：注文取消完了画面へ -->
  <ifa-other-remain-power-restrain-cancel-complete
    :is-visible="isShowCancelComplete"
    :form-data="cancelCompleteForm"
    :customer-info="customerInfo"
    @close-modal="handleCloseModal('all');execHandlerActionA001();"
  ></ifa-other-remain-power-restrain-cancel-complete>
  <button
    id="order-cancel-button"
    type="button"
    value=""
    hidden="true"
    @click="handleOrderCancel"
  ></button>
</template>

<script>
/* 以下は各々コンポーネントをエクスポートする */
// ↓↓共通部品↓↓
// タイトル表示コンポーネント
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle'
// 日付文字列フォーマット
import { getFormattedDateValue, getFormattedDateTimeValue } from '@/components/Date/js/IfaDatePickerFunction.js'
// 数値フォーマット
import ifaFormatUtils from '@/utils/ifaFormatUtils.js'
// ↓↓一覧用gird↓↓
// pqGridコンポーネント
import gridTable from '@/components/GridTable'
// pqGridコンポーネントの設定オプション
import { getConvertedOption } from '@/utils/pqgridHelper'
// ↓↓ドメイン↓↓
// 円貨15_0のドメイン
import IfaYen150DomainModel from '@/resource/domain/IfaYen150DomainModel.json'
// 円貨10_0のドメイン
import IfaYen100DomainModel from '@/resource/domain/IfaYen100DomainModel.json'
// 西暦年月日8のドメイン
import IfaDate8DomainModel from '@/resource/domain/IfaDate8DomainModel.json'
// テキスト100のドメイン
import IfaText100DomainModel from '@/resource/domain/IfaText100DomainModel.json'
// ↓↓モデル↓↓
// SUB0202_0110-01_1その他余力拘束注文入力のフォームモデル
import { IfaOtherRemainPowerRestrainInputFormModel } from './js/IfaOtherRemainPowerRestrainInputFormModel'
// SUB0202_0110-01_1 その他余力拘束注文入力のA002注文確認リクエストモデル
import { IfaOtherRemainPowerRestrainInputA002RequestModel } from './js/IfaOtherRemainPowerRestrainInputA002RequestModel'
// SUB0202_0110-01_1 その他余力拘束注文入力のA004注文取消リクエストモデル
import { IfaOtherRemainPowerRestrainInputA004RequestModel } from './js/IfaOtherRemainPowerRestrainInputA004RequestModel'
// 画面ダイアログ↓↓
// 画面:SUB0202_0110-01_2その他余力拘束注文入力確認
import IfaOtherRemainPowerRestrainInputConfirm from './IfaOtherRemainPowerRestrainInputConfirm'
// 画面:SUB0202_0110-01_3その他余力拘束注文入力完了
import IfaOtherRemainPowerRestrainInputComplete from './IfaOtherRemainPowerRestrainInputComplete'
// 画面:SUB0202_0110-02_1その他余力拘束注文取消確認
import IfaOtherRemainPowerRestrainCancelConfirm from './IfaOtherRemainPowerRestrainCancelConfirm'
// 画面:SUB0202_0110-02_2その他余力拘束注文取消完了
import IfaOtherRemainPowerRestrainCancelComplete from './IfaOtherRemainPowerRestrainCancelComplete'

/* 以下はVUEコンポーネントのプロパティ：エクスポートされたオブジェクトを指定する */
export default {
  /* 使用可能な子コンポーネントを定義する */
  components: {
    gridTable,
    screenTitle,
    IfaOtherRemainPowerRestrainInputConfirm,
    IfaOtherRemainPowerRestrainInputComplete,
    IfaOtherRemainPowerRestrainCancelConfirm,
    IfaOtherRemainPowerRestrainCancelComplete
  },
  /* 以下はVUEコンポーネントのプロパティ：イベントを宣言する */
  emits: ['show-tab-by-name', 'initializeError'],
  /* 以下はVUEコンポーネントのプロパティ：画面変数を初期化する */
  data() {
    return {
      // 定数：ジュニアNISA口座開設済み
      IS_OPENED_JRNISA_ACCOUNT: '1',
      // 定数：ジュニアNISA口座開設待ち
      IS_UNOPEN_JRNISA_ACCOUNT: '0',
      // 定数：口座区分: 1.ジュニアNISA口座
      ACCOUNT_TYPE_JRNISA: '1',
      // 数値フォーマットを初期化
      ifaFormatUtils: ifaFormatUtils,
      // フォーム参照オブジェクト
      formRef: {},
      // その他余力拘束注文入力画面のフォームモデルを初期化
      form: new IfaOtherRemainPowerRestrainInputFormModel(),
      // その他余力拘束注文入力確認画面のフォームモデルを初期化
      inputConfirmForm: {},
      // その他余力拘束注文入力完了画面のフォームモデルを初期化
      inputCompleteForm: {},
      // その他余力拘束注文取消確認画面のフォームモデルを初期化
      cancelConfirmForm: {},
      // その他余力拘束注文取消完了画面のフォームモデルを初期化
      cancelCompleteForm: {},
      // pqGridコンポーネントの設定オプション
      pqGridOption: getConvertedOption(gridTableObj),
      // pqGridコンポーネントの単一選択されたレコードの格納オブジェクト
      pqGridSelectedInfo: {},
      // ドメインを初期化:円貨15_0
      IfaYen150DomainModel: IfaYen150DomainModel,
      // ドメインを初期化:円貨10_0
      IfaYen100DomainModel: IfaYen100DomainModel,
      // ドメインを初期化:西暦年月日8
      IfaDate8DomainModel: IfaDate8DomainModel,
      // ドメインを初期化:テキスト100
      IfaText100DomainModel: IfaText100DomainModel,
      // 注文入力確認画面ポップアップするかどうかフラグ
      isShowInputConfirm: false,
      // 注文入力完了画面ポップアップするかどうかフラグ
      isShowInputComplete: false,
      // 注文取消確認画面ポップアップするかどうかフラグ
      isShowCancelConfirm: false,
      // 注文取消完了画面ポップアップするかどうかフラグ
      isShowCancelComplete: false
    }
  },
  computed: {
    // A002アクション 注文確認のリクエストモデル
    ifaOtherRemainPowerRestrainInputA002RequestModel() {
      return new IfaOtherRemainPowerRestrainInputA002RequestModel(this.form)
    },
    // A004アクション 注文取消のリクエストモデル
    ifaOtherRemainPowerRestrainInputA004RequestModel() {
      return new IfaOtherRemainPowerRestrainInputA004RequestModel(this.pqGridSelectedInfo)
    },
    // 顧客情報を取得する
    customerInfo() {
      return this.$store.getters.customerInfo
    },
    // ボタンの非活性化設定
    setDisable() {
      return false
    }
  },
  mounted() {
    this.formRef = this.$refs['form']
  },
  methods: {
    /**
     * 関数：onshow
     */
    onShow() {
      this.execHandlerActionA001()
    },
    /**
     * 関数：A001アクションの処理:画面初期化
     */
    execHandlerActionA001() {
      this.form = new IfaOtherRemainPowerRestrainInputFormModel()
      this.form.confirmItem = '0' // 0:未確認, 1:確認済み
      this.pqGridOption.dataModel.data = []
      this.$nextTick(() => {
        this.$refs['form'].clearValidate()
        this.$refs['confirmItemParent'].$refs.confirmItem.resetField()
        this.$refs['gridTable'].refreshView(true)
        document.getElementById('ifaOtherRemainPowerRestrainInputA001').click()
      })
    },
    /**
     * 関数：A001アクションのレスポンス処理:画面初期化
     * @param response レスポンス
     */
    responseHandlerActionA001(response) {
      this.form = Object.assign(this.form, response.dataList[0])
      this.requestedTime = response.requestedTime.split(' ')[0]

      if (response.dataList[0].orderData.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].orderData.map(item => ({ ...item }))
      } else {
        this.pqGridOption.dataModel.data = []
      }
      if (this.form.jrNisageneralAccountFlag === this.IS_OPENED_JRNISA_ACCOUNT) {
        this.form.accountType = this.ACCOUNT_TYPE_JRNISA
      }
      this.setGridTableColumnsOption()
    },
    errorHandlerActionA001(response) {
      const errorInfo = {
        title: this.form.screenTitle,
        message: response.message
      }
      this.$emit('initializeError', errorInfo)
    },
    /**
     * 関数：A002アクションのレスポンス処理:注文確認
     * @param response レスポンス
     */
    async responseHandlerActionA002(response) {
      this.inputConfirmForm = response.dataList[0]
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.ifaOtherRemainPowerRestrainInputConfirm.onShow()
      this.isShowInputConfirm = true
    },
    /**
     * 関数：A003アクションの処理:リセット
     * @param response レスポンス
     */
    execHandlerActionA003() {
      if (this.form.jrNisageneralAccountFlag === this.IS_OPENED_JRNISA_ACCOUNT) {
        this.form.accountType = this.ACCOUNT_TYPE_JRNISA
      }  else {
        this.form.accountType = ' '
      }
      this.form.restrainType = ''
      this.form.netAmount = ''
      this.form.isaSeityoLimitAmount = ''
      this.form.isaTsumitateLimitAmount = ''
      this.form.restrainDateTo = ''
      this.form.restrainReason = ''
      this.form.confirmItem = '0' // 0:未確認, 1:確認済み
      this.$nextTick(() => {
        this.$refs['form'].clearValidate()
        this.$refs['confirmItemParent'].$refs.confirmItem.resetField()
      })
    },
    /**
     * 関数：A004アクションのレスポンス処理:注文確認
     * @param response レスポンス
     */
    async responseHandlerActionA004(response) {
      this.cancelConfirmForm = response.dataList[0]
      await this.$nextTick() // 現在の更新を待つためのVueのメソッド
      await this.$refs.ifaOtherRemainPowerRestrainCancelConfirm.onShow()
      this.isShowCancelConfirm = true
    },
    /**
     * 関数：画面一覧部の項目を再設定する
     */
    setGridTableColumnsOption() {
      this.$nextTick(() => {
        // ジュニアNISA口座開設フラグは'1'(開設済)の場合
        if (this.form.jrNisageneralAccountFlag === this.IS_OPENED_JRNISA_ACCOUNT) {
          this.setGridTableColumnsHidden('accountType', false)
        } 
        else {
          this.setGridTableColumnsHidden('accountType', true)
        }
        this.$refs['gridTable'].refreshView(true)
      })
    },
    /**
     * 関数：画面一覧部の項目に対してhiddenの設定を行う
     * @param dataIndx 項目名
     * @param value trueの場合、hidden
     */
    setGridTableColumnsHidden(dataIndx, value) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.hidden = value
      }
    },
    /**
     * 関数：画面一覧部の項目に対してwidthの設定を行う
     * @param dataIndx 項目名
     * @param value width値、'〇〇px'
     */
    setGridTableColumnsWidth(dataIndx, width) {
      const colModel = this.pqGridOption.colModel.find(cm => cm.dataIndx === dataIndx)
      if (typeof colModel === 'object') {
        colModel.width = width
      }
    },
    /**
     * 関数：注文〇〇処理のダイアログの『戻る』処理
     * @param emitBack
     */
    handleCloseModal(emitBack) {
      if (emitBack === 'inputConfirm') {
        this.isShowInputConfirm = false
      } else if (emitBack === 'cancelConfirm') {
        this.isShowCancelConfirm = false
      } else {
        this.isShowInputConfirm = false
        this.isShowInputComplete = false
        this.isShowCancelConfirm = false
        this.isShowCancelComplete = false
      }
      this.$refs.focusCard.focus()
    },
    /**
     * 関数：注文入力完了画面に遷移
     * @param response
     */
    async handleInputComplete(response) {
      this.inputCompleteForm = response
      this.isShowInputConfirm = false
      this.isShowInputComplete = true
    },
    /**
     * 関数：注文取消完了画面に遷移
     * @param response
     */
    async handleCancelComplete(response) {
      this.cancelCompleteForm = response
      this.isShowCancelConfirm = false
      this.isShowCancelComplete = true
    },
    // 注文状況が注文中の注文を取消する
    handleOrderCancel() {
      const cancelData = JSON.parse(unescape(document.getElementById('order-cancel-button').value))
      Object.assign(this.pqGridSelectedInfo, cancelData)
      this.pqGridSelectedInfo.jrNisageneralAccountFlag = this.form.jrNisageneralAccountFlag
      this.$nextTick(() => {
        document.getElementById('ifaOtherRemainPowerRestrainInputA004').click()
      })
    },
    selectAccountType() {
      this.form.restrainType = ''
      this.selectRestrainType()
    },
    selectRestrainType() {
      this.form.netAmount = ''
      this.form.isaSeityoLimitAmount = ''
      this.form.isaTsumitateLimitAmount = ''
      this.$nextTick(() => { this.$refs['form'].clearValidate() })
    },
    /**
     * 文字列が有効かどうかを判断する関数
     * @param {string|null} value - 判定する文字列またはnull
     * @returns {boolean} - 有効な場合はtrue、無効な場合はfalse
     */
    isValidString(value) {
      return value != null && value !== ''
    }
  }
}

// 一覧用GridTableの設定
const gridTableObj = {
  width: 0,
  height: 0,
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: false,
  showTop: true,
  numberCell: { show: false },
  topVisible: false,
  warp: false
}
gridTableObj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
gridTableObj.colModel = [
  {
    title: '口座区分',
    dataType: 'string',
    dataIndx: 'accountType',
    width: '140px',
    halign: 'center',
    align: 'center',
    editable: false,
    hidden: false,
    codeValue: {
      codeListId: 'OTHER_REMAINPOWER_RESTRAIN_ACCOUNT_TYPE',
      dispPattern: 1
    }
  },
  {
    title: '金額<br>(買付余力)',
    dataType: 'string',
    dataIndx: 'netAmount',
    width: '177px',
    halign: 'center',
    align: 'right',
    editable: false,
    hidden: false,
    render: function(ui) {
      if (ui.rowData.netAmount === '0' || ui.rowData.netAmount === '') {
        return '-'
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.netAmount)
      }
    }
  },
  {
    title: '金額<br>(NISA成長投資枠)',
    dataType: 'string',
    dataIndx: 'isaSeityoLimitAmount',
    width: '177px',
    halign: 'center',
    align: 'right',
    editable: false,
    hidden: false,
    render: function(ui) {
      if (ui.rowData.isaSeityoLimitAmount === '0' || ui.rowData.isaSeityoLimitAmount === '') {
        return '-'
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.isaSeityoLimitAmount)
      }
    }
  },
  {
    title: '金額<br>(NISAつみたて投資枠)',
    dataType: 'string',
    dataIndx: 'isaTsumitateLimitAmount',
    width: '177px',
    halign: 'center',
    align: 'right',
    editable: false,
    hidden: false,
    render: function(ui) {
      if (ui.rowData.isaTsumitateLimitAmount === '0' || ui.rowData.isaTsumitateLimitAmount === '') {
        return '-'
      } else {
        return ifaFormatUtils.withCommaInteger(ui.rowData.isaTsumitateLimitAmount)
      }
    }
  },
  {
    title: '拘束期間',
    dataType: 'string',
    dataIndx: 'restrainDateFromTo',
    width: '229px',
    halign: 'center',
    align: 'center',
    editable: false,
    hidden: false,
    sortType: function(rowData1, rowData2) {
      const val1Fr = rowData1.restrainDateFrom || '10000000';
      const val1To = rowData1.restrainDateTo || '10000000';
      const val2Fr = rowData2.restrainDateFrom || '10000000';
      const val2To = rowData2.restrainDateTo || '10000000';
      const val1 = `${val1Fr}${val1To}`;
      const val2 = `${val2Fr}${val2To}`;
      return val1 > val2 ? 1 : val1 < val2 ? -1 : 0;
    },
    render: function(ui) {
      let val = ''
      if (!ui.rowData.restrainDateFrom && !ui.rowData.restrainDateTo) {
        return '-'
      }
      if (ui.rowData.restrainDateFrom !== null && ui.rowData.restrainDateFrom !== '') {
        val += getFormattedDateValue(ui.rowData.restrainDateFrom, 'date8')
      }
      val += '～'
      if (ui.rowData.restrainDateTo !== null && ui.rowData.restrainDateTo !== '') {
        val += getFormattedDateValue(ui.rowData.restrainDateTo, 'date8')
      }
      return val
    }
  },
  {
    title: '拘束理由',
    dataType: 'string',
    dataIndx: 'restrainReason',
    width: '360px',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: false
  },
  {
    title: '受注日時',
    dataType: 'string',
    dataIndx: 'acceptDateTime',
    width: '156px',
    halign: 'center',
    align: 'center',
    editable: false,
    hidden: false,
    render: function(ui) {
      if (ui.rowData.acceptDateTime) {
        const value = getFormattedDateTimeValue(ui.rowData.acceptDateTime, 'datetime14')
        return value !== null && typeof value === 'string' && value.includes(' ') ? value.replace(' ', '<br>') : '-'
      } else {
        return '-'
      }
    }
  },
  {
    title: '発注日',
    dataType: 'string',
    dataIndx: 'orderDate',
    width: '156px',
    halign: 'center',
    align: 'center',
    editable: false,
    hidden: false,
    render: function(ui) {
      if (ui.rowData.orderDate) {
        return getFormattedDateValue(ui.rowData.orderDate, 'date8')
      } else {
        return '-'
      }
    }
  },
  {
    title: '取消',
    dataType: 'string',
    dataIndx: 'isCancel',
    width: '97px',
    halign: 'center',
    align: 'center',
    editable: false,
    hidden: false,
    render: function(ui) {
      // 商品区分が'Z9'(その他) かつ 受付経路区分が'3'(仲介業者)の場合
      if (ui.rowData.secId === 'Z9' && ui.rowData.callcenterId === '1') {
        // 有効区分が'1'(取消)以外 かつ 取消区分が'1'(取消)以外の場合
        if (ui.rowData.validId !== '1' && ui.rowData.orderId !== '1') {
          // 取消区分(DB)が'△'(ノーマル)の場合、”取消”ボタンを表示
          if (ui.rowData.torikeshiKbn === ' ') {
            const v = JSON.stringify(ui.rowData)
            return "<button type='button' class='el-button ifa-button el-button--default el-button--mini secondary-class' onclick='const btn = document.getElementById(\"order-cancel-button\"); btn.value = \"" + escape(v) + "\"; btn.click()'><span class='__adjust_button_text'>取消</span></button>"
          // 取消区分(DB)が'1'(取消)の場合、”取消済”を表示
          } else if (ui.rowData.torikeshiKbn === '1') {
            return "<span style='color: red;'>取消済</span>"
          } 
        // 有効区分が'1'(取消) かつ 取消区分が'1'(取消)以外の場合、”取消済”を表示
        } else if (ui.rowData.validId === '1' && ui.rowData.orderId !== '1') {
          return "<span style='color: red;'>取消済</span>"
        }
      }
    }
  }
]
</script>

<!-- CSS部 -->
<style lang="scss" scoped>
.caption_card {
  padding: 5px 15px 20px 15px;
}
.input_area {
  background-color: #fef0f0;
}
.form-area__section {
  height: auto;
  margin: 0.2rem 0;
  padding-bottom: 0.2rem;
  border-bottom: 1px solid #eee;
}
.label-left {
  margin-left: -10px;
}
.form-reset-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -2.5rem 0 0.5rem 0;
  padding-right: 4rem;
}
:deep(.el-form-item__label) {
  font-weight: bold;
  line-height: 2;
  justify-content: flex-start;
}
:deep(.el-date-editor.el-input, .el-date-editor.el-input__wrapper) {
  height: auto;
}
:deep(.el-form-item__error) {
  white-space: nowrap;
}
.el-form-item__error_custom-margin :deep(.el-form-item__error) {
  margin-top: -0.7rem;
  margin-bottom: 5px;
}
:deep(.el-form-item__content) {
  width: 70%;
}
.__adjust_button_text {
  height: 24px;
  line-height: 16px !important;
  white-space: nowrap;
}
</style>
