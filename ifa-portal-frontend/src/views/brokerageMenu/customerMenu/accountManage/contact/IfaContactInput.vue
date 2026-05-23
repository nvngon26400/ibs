<template>
  <div id="cardArea">
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :title="inputForm.dialogTitle.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      @open="onShow"
    >
      <el-row>
        <div style="width:97%; text-align: right; margin: 0 10px 0px 10px;">
          <ifa-button
            text="リセット"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="formReset"
          ></ifa-button>
          <ifa-button
            text="戻る"
            color="secondary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </div>
      </el-row>
      <el-form
        ref="formRef"
        :model="inputForm"
        :rules="rules"
        :inline="true"
      >
        <table class="input-table">
          <tr>
            <td class="table-label">
              <el-form-item>
                <span class="required-mark">*</span>カテゴリ
              </el-form-item>
            </td>
            <td class="table-body">
              <el-row>
                <ifa-input-select
                  v-model="inputForm.toiawaseDCd"
                  prop="toiawaseDCd"
                  size="small"
                  class="ifa-input__text-default"
                  code-list-id="original"
                  :original-list="inputForm.toiawaseDList"
                  :disabled="setDisable"
                  style="width: 16.2rem;"
                  @change="toiawaseDCdChangeEvent"
                >
                </ifa-input-select>
                <ifa-input-select
                  v-model="inputForm.toiawaseCd"
                  prop="toiawaseCd"
                  size="small"
                  class="ifa-input__text-default"
                  code-list-id="original"
                  :original-list="inputForm.toiawaseList"
                  :disabled="setDisable"
                  style="width: 16.2rem;"
                  :visible="setToiawaseListVisible"
                  @change="toiawaseCdChangeEvent"
                >
                </ifa-input-select>
                <ifa-input-select
                  v-model="inputForm.toiawaseSCd"
                  prop="toiawaseSCd"
                  size="small"
                  class="ifa-input__text-default"
                  code-list-id="original"
                  :original-list="inputForm.toiawaseSList"
                  :disabled="setDisable"
                  style="width: 16.2rem;"
                  :visible="setToiawaseSListVisible"
                >
                </ifa-input-select>
              </el-row>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                接触経路
              </el-form-item>
            </td>
            <td class="table-body">
              <ifa-input-select
                v-model="inputForm.sessyokuKeiro"
                size="small"
                class="ifa-input__text-default"
                code-list-id="original"
                :original-list="inputForm.sessyokuKeiroList"
                :disabled="setSessyokuKeiroDisable"
                style="width: 16.2rem;"
              >
              </ifa-input-select>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                <span class="required-mark">*</span>重要度
              </el-form-item>
            </td>
            <td class="table-body">
              <ifa-input-radio
                v-model="inputForm.juuyoudo"
                prop="juuyoudo"
                code-list-id="JUUYOUDO"
                :disp-pattern="1"
                :select-pattern="1"
                :disabled="setDisable"
              ></ifa-input-radio>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                クレーム/リクエスト
              </el-form-item>
            </td>
            <td class="table-body">
              <el-row>
                <ifa-input-check
                  v-model="inputForm.cream"
                  code-list-id="original"
                  :original-list="inputForm.creamCheckbox"
                  :disabled="setDisable"
                ></ifa-input-check>
                <ifa-input-check
                  v-model="inputForm.request"
                  code-list-id="original"
                  :original-list="inputForm.requestCheckbox"
                  :disabled="setDisable"
                ></ifa-input-check>
              </el-row>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                <span class="required-mark">*</span>対応ステータス
              </el-form-item>
            </td>
            <td class="table-body">
              <ifa-input-radio
                v-model="inputForm.taiouSts"
                prop="taiouSts"
                code-list-id="TAIOU_STS"
                :disp-pattern="1"
                :select-pattern="1"
              ></ifa-input-radio>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                <span class="required-mark">*</span>入電方向
              </el-form-item>
            </td>
            <td class="table-body margin-houkou">
              <ifa-input-radio
                v-model="inputForm.houkou"
                prop="houkou"
                code-list-id="NYUUDEN_HOUKOU"
                :disp-pattern="1"
                :select-pattern="1"
                :disabled="setDisable"
              ></ifa-input-radio>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                訪問日
              </el-form-item>
            </td>
            <td class="table-body">
              <ifa-date-picker
                v-model="inputForm.houmonbi"
                size="small"
                placeholder="blank"
                :disabled="setDisable"
              ></ifa-date-picker>
            </td>
          </tr>
          <tr>
            <td class="table-label">
              <el-form-item>
                次回訪問予定日
              </el-form-item>
            </td>
            <td class="table-body">
              <ifa-date-picker
                v-model="inputForm.nextHoumonbi"
                size="small"
                placeholder="blank"
                :disabled="setDisable"
              ></ifa-date-picker>
            </td>
          </tr>
          <tr v-if="setToiawaseNaiyouVisible">
            <td class="table-label">
              <el-form-item>
                <span
                  v-if="operationType === '1'"
                  class="required-mark"
                >*
                </span>内容
              </el-form-item>
            </td>
          </tr>
          <tr v-if="setToiawaseNaiyouVisible">
            <td
              class="table-label"
              colspan="2"
            >
              <ifa-input-text
                id="toiawaseNaiyou"
                v-model="inputForm.toiawaseNaiyou"
                prop="toiawaseNaiyou"
                type="textarea"
                label="内容"
                :rows="6"
                resize="vertical"
                original-screen-id="SUB0202_0106-03"
                :domain="IfaText3910DomainModel"
                class="form_label ifa-input__text-default"
                :disabled="setToiawaseNaiyouDisable"
                :visible="setToiawaseNaiyouVisible"
                style="width: 100%;"
              ></ifa-input-text>
            </td>
          </tr>
          <tr v-if="setKaitouNaiyouVisible">
            <td class="table-label">
              <el-form-item>
                <span class="required-mark">*</span>追加入力
              </el-form-item>
            </td>
          </tr>
          <tr v-if="setKaitouNaiyouVisible">
            <td
              class="table-label"
              colspan="2"
            >
              <ifa-input-text
                id="kaitouNaiyou"
                v-model="inputForm.kaitouNaiyou"
                prop="kaitouNaiyou"
                type="textarea"
                label="追加入力"
                :rows="6"
                resize="vertical"
                original-screen-id="SUB0202_0106-03"
                :domain="IfaText3910DomainModel"
                class="form_label ifa-input__text-default"
                :visible="setKaitouNaiyouVisible"
                style="width: 100%;"
              ></ifa-input-text>
            </td>
          </tr>
        </table>
      </el-form>
      <el-row>
        <ifa-button
          text="確認"
          color="primary"
          small
          width="110"
          action-type="originalAction"
          @app-action-handler="preRequestHandlerA002"
        ></ifa-button>
      </el-row>
    </el-dialog>
  </div>
  <!-- 問合せ登録 (接触履歴入力)初期化イベント -->
  <ifa-requester
    id="ifaContactInputInitializeA001"
    action-id="SUB0202_0106-03#A001"
    action-type="requestAction"
    @response-handler="initializeResponse($event)"
  ></ifa-requester>
  <!-- 問合せ登録 (追加入力/管理項目修正)初期化イベント -->
  <ifa-requester
    id="ifaContactInputInitializeX001"
    action-id="SUB0202_0106-03#X001"
    action-type="requestAction"
    :request-model="{...queryData, operationType: operationType}"
    @response-handler="initializeResponse($event)"
  ></ifa-requester>
  <!-- カテゴリ大	選択イベント -->
  <ifa-requester
    id="ifaContactInputToiawaseDCdChangeA007"
    action-id="SUB0202_0106-03#A007"
    action-type="requestAction"
    :request-model="ifaContactInputA007RequestModel"
    @response-handler="handleToiawaseDCdChange($event)"
  ></ifa-requester>
  <!-- カテゴリ中	選択イベント -->
  <ifa-requester
    id="ifaContactInputToiawaseCdChangeA008"
    action-id="SUB0202_0106-03#A008"
    action-type="requestAction"
    :request-model="ifaContactInputA008RequestModel"
    @response-handler="handleToiawaseCdChange($event)"
  ></ifa-requester>
  <!-- 問合せ入力 確認イベント -->
  <ifa-requester
    id="ifaContactInputConfirmA002"
    action-id="SUB0202_0106-03#A002"
    action-type="requestAction"
    :request-model="ifaContactInputA002RequestModel"
    @response-handler="comfirmHandlerA002"
  ></ifa-requester>
  <!-- 接触履歴入力確認ポップアップ画面 -->
  <ifa-contact-confirm
    :is-visible="showConfirmDialog"
    :operation-type="operationType"
    :confirm-data="confirmData"
    @back-modal="handleBackModal"
    @contact-complete="handleContactComplete"
  ></ifa-contact-confirm>
  <!-- 接触履歴完了ポップアップ画面 -->
  <ifa-contact-complete
    :is-visible="showCompleteDialog"
    :operation-type="operationType"
    :complete-data="completeData"
    @move-to-origin="handleMoveEvent();handleResearch();"
  ></ifa-contact-complete>
</template>

<script>
import { useVModel } from 'vue-composable'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { displayToiawaseMei, initTextareaHeight, calculateTextLength } from './js/IfaContactUtils'
import IfaText3910DomainModel from '@/resource/domain/IfaText3910DomainModel.json'
import { IfaContactInputFormModel } from './js/IfaContactInputFormModel'
import { IfaContactInputA002RequestModel } from './js/IfaContactInputA002RequestModel'
import { IfaContactInputA007RequestModel } from './js/IfaContactInputA007RequestModel'
import { IfaContactInputA008RequestModel } from './js/IfaContactInputA008RequestModel'
import { IfaContactConfirmFormModel } from './js/IfaContactConfirmFormModel'
import { IfaContactCompleteFormModel } from './js/IfaContactCompleteFormModel'
import IfaContactConfirm from './IfaContactConfirm'
import IfaContactComplete from './IfaContactComplete'

export default {
  components: {
    IfaContactConfirm,
    IfaContactComplete
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    operationType: { type: String, required: true },
    queryData: { type: Object, required: false, default: null }
  },
  emits: ['update:isVisible', 'open-modal', 'close-modal', 'research'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      rules: {
        toiawaseDCd: [{ required: true, message: getMessage('errors.selected', ['カテゴリ大']) }],
        toiawaseCd: [{ required: true, message: getMessage('errors.selected', ['カテゴリ中']) }, { trigger: 'change', validator: this.toiawaseCdScopeValidator }],
        sessyokuKeiro: [{ required: true, message: getMessage('errors.selected', ['接触経路']) }],
        juuyoudo: [{ required: true, message: getMessage('errors.selected', ['重要度']) }],
        taiouSts: [{ required: true, message: getMessage('errors.selected', ['対応ステータス']) }],
        houkou: [{ required: true, message: getMessage('errors.selected', ['入電方向']) }],
        toiawaseNaiyou: [{ trigger: 'blur', validator: this.lengthValidator('内容') }],
        kaitouNaiyou: [{ trigger: 'blur', required: true, message: getMessage('errors.required', ['追加入力']) }, { trigger: 'blur', validator: this.lengthValidator('追加入力') }]
      },
      formRef: {},
      IfaText3910DomainModel: IfaText3910DomainModel,
      inputForm: new IfaContactInputFormModel(),
      ifaContactInputA002RequestModel: {},
      ifaContactInputA007RequestModel: {},
      ifaContactInputA008RequestModel: {},
      dialogComfirmVisible: false,
      showConfirmDialog: false,
      showCompleteDialog: false,
      // 初期値(クリア用)
      resetForm: {
        toiawaseDCd: '', // 分類（大）
        toiawaseCd: '', // 分類（中）
        toiawaseSCd: '', // 分類（小）
        sessyokuKeiro: '', // 接触経路
        juuyoudo: '2', // 重要度
        cream: '', // クレーム
        request: '', // リクエスト
        taiouSts: '0', // 対応ステータス
        houkou: '0', // 入電方向
        houmonbi: '', // 訪問日
        nextHoumonbi: '', // 次回訪問予定日
        toiawaseNaiyou: '', // 内容
        kaitouNaiyou: '' // 追加入力
      },
      confirmData: new IfaContactConfirmFormModel(),
      completeData: new IfaContactCompleteFormModel()
    }
  },
  computed: {
    setDisable() {
      if (this.operationType === '1' || this.operationType === '2') {
        return false
      } else {
        return true
      }
    },
    setSessyokuKeiroDisable() {
      if (this.operationType === '1' || (this.operationType === '2' && this.inputForm.ifaNyuuryokuFlg === '1')) {
        return false
      } else {
        return true
      }
    },
    setToiawaseNaiyouDisable() {
      if (this.operationType === '1') {
        return false
      } else {
        return true
      }
    },
    setToiawaseNaiyouVisible() {
      if (this.operationType === '1' || this.operationType === '3') {
        return true
      } else {
        return false
      }
    },
    setKaitouNaiyouVisible() {
      if (this.operationType === '3') {
        return true
      } else {
        return false
      }
    },
    setToiawaseListVisible() {
      if (this.inputForm.toiawaseDCd !== '' && this.inputForm.toiawaseList?.length > 0) {
        return true
      } else {
        return false
      }
    },
    setToiawaseSListVisible() {
      if (this.inputForm.toiawaseCd !== '' && this.inputForm.toiawaseSList?.length > 0) {
        return true
      } else {
        return false
      }
    }
  },
  watch: {
    isVisible: function() {
      if (this.isVisible && this.operationType === '1') {
        this.formReset()
      } else if (this.isVisible && this.operationType === '3') {
        this.inputForm.kaitouNaiyou = ''
        // バリデーションクリア
        this.$refs['formRef']?.clearValidate()
      }
    }
  },
  created() {
    this.inputForm.sessyokuKeiroList = this.$_getCodeList('SESSYOKU_KEIRO', '1', '1')
    this.inputForm.sessyokuKeiroList[0] = { key: '', value: '' }
  },
  mounted() {
    this.formRef = this.$refs.formRef
    if (this.operationType === '1') {
      this.rules.toiawaseNaiyou.push({ trigger: 'blur', required: true, message: getMessage('errors.required', ['内容']) })
    }
  },
  methods: {
    onShow() {
      // 初期化時に「内容」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#toiawaseNaiyou'))
      // 初期化時に「追加入力」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#kaitouNaiyou'))
      this.$nextTick(() => {
        // 処理区分が'1'(接触履歴入力)の場合
        if (this.operationType === '1') {
          this.inputForm.dialogTitle.name = '接触履歴入力'
          document.getElementById('ifaContactInputInitializeA001').click()
        } else {
          if (this.operationType === '2') {
            this.inputForm.dialogTitle.name = '管理項目修正'
          } else {
            this.inputForm.dialogTitle.name = '追加入力'
          }
          // 処理区分が'2'、'3'(問合せ修正/回答入力)の場合
          document.getElementById('ifaContactInputInitializeX001').click()
          // バリデーションクリア
          this.$refs['formRef']?.clearValidate()
        }
      })
    },
    initializeResponse(response) {
      if (this.operationType !== '1') {
        // 処理区分が'2'、'3'(問合せ修正/回答入力)の場合
        Object.assign(this.inputForm, Object.fromEntries(
          Object.entries(response.dataList[0]).filter(([_, value]) => value !== null)
        ))
        // クレームディフォルト値設定
        if (response.dataList[0].cream === null) this.inputForm.cream = '0'
        // リクエストディフォルト値設定
        if (response.dataList[0].request === null) this.inputForm.request = '0'
        // 訪問日フォーマット(yyyyMMdd → yyyy/MM/dd)
        this.inputForm.houmonbi = this.$_getFormattedDateValue(response.dataList?.[0]?.houmonbi ?? '')
        // 次回訪問予定日フォーマット(yyyyMMdd → yyyy/MM/dd)
        this.inputForm.nextHoumonbi = this.$_getFormattedDateValue(response.dataList?.[0]?.nextHoumonbi ?? '')
        // 処理区分が'2'(管理項目修正)以外の場合、回答情報を取得する
        // 内容は内容、回答情報リスト.回答内容[0]～[3]を結合して表示する
        if (response.dataList[0].answerList?.length > 0) {
          response.dataList[0].answerList.forEach(item => {
            if (item.kaitouNaiyou) {
              this.inputForm.toiawaseNaiyou += item.kaitouNaiyou
            }
          })
        }
        // カテゴリリスト(中)取得
        this.inputForm.toiawaseList = response.dataList[0].toiawaseList?.length > 0 ? response.dataList[0].toiawaseList.map((item) => ({ key: item.toiawaseCd, value: item.toiawaseMei })) : []
        // カテゴリリスト(中)が存在する場合は、空白項目を追加する
        if (this.inputForm.toiawaseList?.length > 0) {
          this.inputForm.toiawaseList.unshift({ key: '', value: '' })
        }
        // カテゴリリスト(小)取得
        this.inputForm.toiawaseSList = response.dataList[0].toiawaseSList?.length > 0 ? response.dataList[0].toiawaseSList.map((item) => ({ key: item.toiawaseCd, value: item.toiawaseMei })) : []
        // カテゴリリスト(小)が存在する場合は、空白項目を追加する
        if (this.inputForm.toiawaseSList?.length > 0) {
          this.inputForm.toiawaseSList.unshift({ key: '', value: '' })
        }
        // 項目変更有りかどうか判断用のため、各項目値をセットする(変更前)
        Object.assign(this.confirmData.beforeData, this.inputForm)
        this.inputForm.kaitouNaiyou = ''
      }
      // カテゴリリスト(大)取得
      this.inputForm.toiawaseDList = response.dataList[0].toiawaseDList.map((item) => ({ key: item.toiawaseCd, value: item.toiawaseMei }))
      this.inputForm.toiawaseDList.unshift({ key: '', value: '' })
    },
    // カテゴリリスト(大)ドロップダウンのonchangeイベント
    toiawaseDCdChangeEvent() {
      this.$refs['formRef']?.clearValidate()
      this.inputForm.toiawaseCd = ''
      this.inputForm.toiawaseSCd = ''
      this.inputForm.toiawaseSList = []
      this.ifaContactInputA007RequestModel = new IfaContactInputA007RequestModel(
        {
          toiawaseDCd: this.inputForm.toiawaseDCd,
          toiawaseDMei: displayToiawaseMei(this.inputForm.toiawaseDList, this.inputForm.toiawaseDCd)
        }
      )
      // 問合せカテゴリリスト（中）取得イベント
      this.$nextTick(() => {
        document.getElementById('ifaContactInputToiawaseDCdChangeA007').click()
      })
    },
    // カテゴリリスト(大)ドロップダウンのonchangeイベント応答処理
    // カテゴリ中のリストを取得する。カテゴリ大が未選択の場合は空のリストとする
    // カテゴリ中の選択状態を未選択に戻し、リストを設定する
    handleToiawaseDCdChange(res) {
      let toiawaseList = []
      if (res?.dataList && res?.dataList[0]?.toiawaseList?.length > 0) {
        toiawaseList = res.dataList[0].toiawaseList.map(item => ({
          key: item.toiawaseCd,
          value: item.toiawaseMei
        }))
        toiawaseList.unshift({ key: '', value: '' })
      }
      this.inputForm.toiawaseList = toiawaseList
    },
    // カテゴリリスト(中)ドロップダウンのonchangeイベント
    toiawaseCdChangeEvent() {
      this.inputForm.toiawaseSCd = ''
      this.ifaContactInputA008RequestModel = new IfaContactInputA008RequestModel(
        {
          toiawaseDCd: this.inputForm.toiawaseDCd,
          toiawaseDMei: displayToiawaseMei(this.inputForm.toiawaseDList, this.inputForm.toiawaseDCd),
          toiawaseCd: this.inputForm.toiawaseCd,
          toiawaseMei: displayToiawaseMei(this.inputForm.toiawaseList, this.inputForm.toiawaseCd)
        }
      )
      // 問合せカテゴリリスト（小）取得イベント
      this.$nextTick(() => {
        document.getElementById('ifaContactInputToiawaseCdChangeA008').click()
      })
    },
    // カテゴリリスト(中)ドロップダウンのonchangeイベント応答処理
    // カテゴリ小のリストを取得する。カテゴリ中が未選択の場合は空のリストとする
    // カテゴリ小の選択状態を未選択に戻し、リストを設定する
    handleToiawaseCdChange(res) {
      let toiawaseSList = []
      if (res?.dataList && res?.dataList[0]?.toiawaseSList?.length > 0) {
        toiawaseSList = res.dataList[0].toiawaseSList.map(item => ({
          key: item.toiawaseCd,
          value: item.toiawaseMei
        }))
        toiawaseSList.unshift({ key: '', value: '' })
      }
      this.inputForm.toiawaseSList = toiawaseSList
    },
    // フォームリセット
    formReset() {
      if (this.operationType === '1') {
        // リセット(接触履歴入力)
        // 注文入力エリアの各項目に初期値をセットする
        Object.assign(this.inputForm, this.resetForm)
      } else {
        // リセット(追加入力/管理項目修正)
        // X001(追加入力/管理項目修正)を呼び出す、各項目に初期値を再設定する
        document.getElementById('ifaContactInputInitializeX001').click()
      }
      // 「内容」高さをリセットする
      initTextareaHeight(document.querySelector('#toiawaseNaiyou'))
      // 「追加入力」高さをリセットする
      initTextareaHeight(document.querySelector('#kaitouNaiyou'))
      // バリデーションクリア
      this.$refs['formRef']?.clearValidate()
    },
    // ダイアログ閉じる
    onDialogClose() {
      // 接触履歴入力ポップアップ画面が閉めた場合は通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      this.$emit('close-modal')
    },
    // 「入力確認」画面遷移前、訂正内容チェックを行う
    preRequestHandlerA002() {
      this.$refs['formRef'].validate(valid => {
        if (valid) {
          if (this.operationType === '1') {
            // (処理区分が'1'(接触履歴入力)の場合)、次の処理へ
            Object.assign(this.confirmData.beforeData, this.inputForm)
            Object.assign(this.confirmData.afterData, this.inputForm)
            this.confirmData.toiawaseNaiyou = this.inputForm.toiawaseNaiyou
            this.confirmData.ifaNyuuryokuFlg = '1'
          } else if (this.operationType === '2') {
            // (処理区分が'2'(問合せ修正)の場合)、訂正内容チェックを行う
            // 項目変更有りかどうか判断用のため、各項目値をセットする(変更後)
            Object.assign(this.confirmData.afterData, this.inputForm)
            // 「クレーム」が未選択の状態であり、かつ「クレーム」の初期値が選択状態の場合、後端から渡された値を「クレーム」に返します
            if (this.confirmData.afterData.cream === '0' && this.confirmData.beforeData.cream !== '0' && this.confirmData.beforeData.cream !== '1') {
              this.confirmData.afterData.cream = this.confirmData.beforeData.cream
            }
            // 「リクエスト」が未選択の状態であり、かつ「リクエスト」の初期値が選択状態の場合、後端から渡された値を「リクエスト」に返します
            if (this.confirmData.afterData.request === '0' && this.confirmData.beforeData.request !== '0' && this.confirmData.beforeData.request !== '1') {
              this.confirmData.afterData.request = this.confirmData.beforeData.request
            }
            const checkEquals = this.compareValues(this.confirmData.beforeData, this.confirmData.afterData, ['toiawaseDCd', 'toiawaseCd', 'toiawaseSCd', 'sessyokuKeiro', 'juuyoudo', 'cream', 'request', 'taiouSts', 'houkou', 'houmonbi', 'nextHoumonbi'])
            // 項目変更なしの場合:エラーを返す
            // 項目変更有りの場合:次の処理へ
            if (checkEquals) {
              // エラーメッセージ：入力内容に変更がないため、接触履歴修正を行いません。
              notifyMessage(-1, getMessage('errors.cmn.question.noModify'), this.inputForm.dialogTitle.name)
              // throw new Error()
              return
            }
            this.confirmData.toiawaseNaiyou = this.inputForm.toiawaseNaiyou
            this.confirmData.ifaToiawaseNo = this.inputForm.ifaToiawaseNo
            this.confirmData.ifaNyuuryokuFlg = this.inputForm.ifaNyuuryokuFlg
          } else {
            // (処理区分が'3'(回答入力)の場合)、次の処理へ
            Object.assign(this.confirmData.afterData, this.inputForm)
            this.confirmData.toiawaseNaiyou = this.inputForm.toiawaseNaiyou
            this.confirmData.kaitouNaiyou = this.inputForm.kaitouNaiyou
            this.confirmData.ifaToiawaseNo = this.inputForm.ifaToiawaseNo
            this.confirmData.ifaNyuuryokuFlg = this.inputForm.ifaNyuuryokuFlg
          }
          // 接触履歴入力フォームデータ作成
          this.ifaContactInputA002RequestModel = new IfaContactInputA002RequestModel(
            {
              ...this.confirmData.afterData,
              toiawaseDCd: this.inputForm.toiawaseDCd,
              toiawaseDMei: displayToiawaseMei(this.inputForm.toiawaseDList, this.confirmData.afterData.toiawaseDCd),
              toiawaseMei: displayToiawaseMei(this.inputForm.toiawaseList, this.confirmData.afterData.toiawaseCd),
              toiawaseSMei: displayToiawaseMei(this.inputForm.toiawaseSList, this.confirmData.afterData.toiawaseSCd),
              sessyokuKeiro: (this.inputForm.sessyokuKeiro === '$NULL') ? '' : this.inputForm.sessyokuKeiro,
              toiawaseNaiyou: this.confirmData.toiawaseNaiyou,
              kaitouNaiyou: this.confirmData.kaitouNaiyou,
              operationType: this.operationType
            }
          )
          if (this.operationType !== '1') {
            this.ifaContactInputA002RequestModel.toiawaseNo = this.inputForm.toiawaseNo
            this.ifaContactInputA002RequestModel.ifaToiawaseNo = this.confirmData.ifaToiawaseNo
          }

          this.$nextTick(() => {
            document.getElementById('ifaContactInputConfirmA002').click()
          })
        }
      })
    },
    // 「入力確認」ダイアログ表示
    comfirmHandlerA002() {
      // 接触履歴入力ポップアップ画面が閉めた場合は通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      // 「入力確認」ダイアログ表示
      this.showConfirmDialog = true
    },
    handleBackModal() {
      // 確認画面から入力画面へ遷移
      // 確認画面クローズ
      this.showConfirmDialog = false
    },
    // 接触履歴完了画面【接触履歴へ】イベント
    // 処理区分が'1'(接触履歴入力)の場合、接触履歴画面を表示する
    handleMoveEvent() {
      // 接触履歴完了画面クローズ
      this.showCompleteDialog = false
      this.$emit('close-modal')
    },
    handleResearch() {
      this.$emit('research')
    },
    // 接触履歴完了画面表示
    handleContactComplete(response) {
      this.showConfirmDialog = false
      this.showCompleteDialog = true
      Object.assign(this.completeData, response)
    },
    compareValues(objA, objB, keys) {
      for (const key of keys) {
        if (objA[key] !== objB[key]) {
          return false
        }
      }
      return true
    },
    // 「カテゴリ中」存在しないチェック
    toiawaseCdScopeValidator(_, value, callback) {
      const exists = this.inputForm.toiawaseList.some(option => option.key === value)
      if (!exists) {
        callback(getMessage('errors.cmn.parameters.notExist', ['カテゴリ中']))
        return
      }
      callback()
    },
    // 「内容」半角１バイト、全角２バイトとして、3910バイトを超えないようチェックを行う
    lengthValidator(fieldName) {
      return (_, value, callback) => {
        if (!value) {
          callback()
          return
        }
        if (calculateTextLength(value) > this.IfaText3910DomainModel.formattedDigit) {
          callback(getMessage('errors.maxSize', [fieldName, this.IfaText3910DomainModel.formattedDigit]))
          return
        }
      }
    }
  }
}
</script>
<style scoped>
.input-table {
  width:97%;
  margin: 10px 0 10px 0;
  border-collapse: collapse;
}
.table-label {
  width:180px;
  font-weight: bold;
  color: #18181A;
  padding: 0 1rem;
  text-align: left;
}
.table-body {
  padding: 0.2rem 0;
}
:deep(.el-textarea__inner) {
  min-height: 136px !important;
}
.table-body-margin-left {
  margin-left: 0
}
:deep(.table-label) .el-form-item {
  margin: 0;
}
:deep(.el-form-item) .el-form-item__label {
  display: none !important;
}
:deep(.table-body) .el-form-item {
  margin: 0.3rem 1rem 0.3rem 0.5rem;
}
:deep(.el-row) .ifa-button {
  margin: 0.3rem 1rem 0.3rem 0.7rem;
}
:deep(.el-radio-group) {
  margin-left: 0px;
}
:deep(.el-dialog__header) {
  justify-content: flex-start;
  display: flex;
}
:deep(.el-dialog__header) span {
  padding-left: 1.2rem;
}
:deep(.el-form-item__content) .el-form-item__error {
  height: 15px;
  display: flex;
}
:deep(.table-label) .el-form-item__error {
  font-weight: normal;
}
:deep(.margin-houkou) .el-radio {
  margin-right: 18px;
}
.required-mark {
  color: red;
  margin-right: 2px;
}
</style>
