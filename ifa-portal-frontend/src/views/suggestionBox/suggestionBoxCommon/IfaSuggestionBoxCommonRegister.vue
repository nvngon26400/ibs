<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="backA005"
      :close-on-click-modal="false"
      width="1050px"
      @open="onShow"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <div style="width:97%; text-align: right; margin: 10px;">
            <!-- リセットボタン -->
            <ifa-button
              id="btnDisplay"
              name="btnDisplay"
              text="リセット"
              width="110"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="ifaSuggestionBoxCommonRegisterResetA004()"
            ></ifa-button>

            <!-- 戻るボタン -->
            <ifa-button
              text="戻る"
              width="90"
              color="secondary"
              style="padding-right: 0;"
              action-type="originalAction"
              @app-action-handler="backA005"
            ></ifa-button>
          </div>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <!-- 要望エリア -->
            <table class="input-table">
              <!-- タイトル -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>タイトル
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="title"
                      v-model="form.title"
                      prop="title"
                      size="small"
                      style="width: 650px"
                      original-screen-id="SUB0511_02-02"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <!-- カテゴリ -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>カテゴリ
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-select
                      v-model="form.category"
                      prop="category"
                      size="small"
                      code-list-id="SUG_BOX_CATEGORY"
                      style="width: 200px;"
                      :disp-pattern="1"
                      :select-pattern="1"
                    >
                    </ifa-input-select>
                  </el-form-item>
                </td>
              </tr>
              <!-- ステータス -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>ステータス
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-select
                      v-model="form.status"
                      prop="status"
                      size="small"
                      code-list-id="SUG_BOX_STATUS"
                      style="width: 200px;"
                      :disp-pattern="1"
                      :select-pattern="2"
                    >
                    </ifa-input-select>
                  </el-form-item>
                </td>
              </tr>
              <!-- 要望内容 -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>要望内容
                  </el-form-item>
                </td>
                <td class="table-body">
                  <!-- 要望内容桁数 -->
                  <div
                    class="text_count_area"
                    :style="form.suggestion.length !== 2000 ? 'color: #6e6e6e;' : 'color: red;'"
                  >
                    {{ form.suggestion.length }}/2000
                  </div>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="suggestion"
                      :key="'suggestion-' + refreshKey"
                      v-model="form.suggestion"
                      :domain="IfaText2000DomainModel"
                      type="textarea"
                      rows="7"
                      style="width: 650px"
                      prop="suggestion"
                      original-screen-id="SUB0511_02-02"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <!-- 要望エリア添付ファイル -->
              <!-- 添付ファイル1 -->
              <tr>
                <td class="table-label">添付ファイル1</td>
                <td class="table-body">
                  <el-form-item
                    class="text-box text-box-file message-error-position"
                  >
                    <div>
                      <ifa-file-select
                        ref="select1"
                        class="file_select"
                        text="ファイルを選択"
                        color="secondary"
                        small
                        width="130"
                        msg-title="ファイルを選択"
                        :show-file-list="true"
                        custom-max-file-size="10"
                        :custom-error-message="fileSelectErrorMsg"
                        @change="fileSelectA002($event, 0)"
                        @before-remove="handlerRemove(0)"
                      ></ifa-file-select>
                      <label
                        v-if="!form.attachFileList[0]"
                        style="font-weight: normal"
                      >
                        ファイルが選択されていません
                      </label>
                      <!-- 添付ファイル名の環境依存文字チェック用の部品（表示はされない） -->
                      <div
                        v-if="form.attachFileList[0]"
                        class="register_file_input text-box text-box-file message-error-position"
                      >
                        <ifa-input-text
                          id="attachFileName1"
                          v-model="form.attachFileName1"
                          prop="attachFileName1"
                          original-screen-id="SUB0511_02-02"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                </td>
              </tr>
              <!-- 添付ファイル2 -->
              <tr>
                <td class="table-label">添付ファイル2</td>
                <td class="table-body">
                  <el-form-item
                    class="text-box text-box-file message-error-position"
                  >
                    <div>
                      <ifa-file-select
                        ref="select2"
                        class="file_select"
                        text="ファイルを選択"
                        color="secondary"
                        small
                        width="130"
                        msg-title="ファイルを選択"
                        :show-file-list="true"
                        custom-max-file-size="10"
                        :custom-error-message="fileSelectErrorMsg"
                        @change="fileSelectA002($event, 1)"
                        @before-remove="handlerRemove(1)"
                      ></ifa-file-select>
                      <label
                        v-if="!form.attachFileList[1]"
                        style="font-weight: normal"
                      >
                        ファイルが選択されていません
                      </label>
                      <!-- 添付ファイル名の環境依存文字チェック用の部品（表示はされない） -->
                      <div
                        v-if="form.attachFileList[1]"
                        class="register_file_input text-box text-box-file message-error-position"
                      >
                        <ifa-input-text
                          id="attachFileName2"
                          v-model="form.attachFileName2"
                          prop="attachFileName2"
                          original-screen-id="SUB0511_02-02"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                </td>
              </tr>
              <!-- 添付ファイル2 -->
              <tr>
                <td class="table-label">添付ファイル3 </td>
                <td class="table-body">
                  <el-form-item
                    class="text-box text-box-file"
                  >
                    <div>
                      <ifa-file-select
                        ref="select3"
                        class="file_select"
                        text="ファイルを選択"
                        color="secondary"
                        small
                        width="130"
                        msg-title="ファイルを選択"
                        :show-file-list="true"
                        custom-max-file-size="10"
                        :custom-error-message="fileSelectErrorMsg"
                        @change="fileSelectA002($event, 2)"
                        @before-remove="handlerRemove(2)"
                      ></ifa-file-select>
                      <label
                        v-if="!form.attachFileList[2]"
                        style="font-weight: normal"
                      >
                        ファイルが選択されていません
                      </label>

                      <!-- 添付ファイル名の環境依存文字チェック用の部品（表示はされない） -->
                      <div
                        v-if="form.attachFileList[2]"
                        class="register_file_input text-box text-box-file message-error-position"
                      >
                        <ifa-input-text
                          id="attachFileName3"
                          v-model="form.attachFileName3"
                          prop="attachFileName3"
                          original-screen-id="SUB0511_02-02"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                </td>
              </tr>
              <!-- 添付ファイル説明文言 -->
              <tr>
                <td
                  class="table-label"
                  colspan="2"
                  style="font-size: 16px; font-weight: normal; padding-bottom: 20px;"
                >
                  ※登録可能な添付ファイルサイズは1ファイルにつき最大10MBです
                </td>
              </tr>
              <!-- 回答エリア -->
              <tr
                v-for="(item, index) in form.answerList"
                :key="index"
              >
                <!-- ラベル （最初の回答にのみ表示） -->
                <td class="table-label">
                  {{ index == 0 ? 'SBI証券からの回答' :'' }} </td>
                <td class="table-body">
                  <!-- 回答登録日 -->
                  <div
                    class="register_textarea"
                  >
                    {{ answerRegisterDateText(item) }}
                  </div>
                  <!-- 回答内容桁数 -->
                  <div
                    class="text_count_area"
                    :style="item.answerContents.length !== 2000 ? 'color: #6e6e6e;' : 'color: red;'"
                  >
                    {{ item.answerContents.length }}/2000
                  </div>
                  <!-- 回答内容 -->
                  <div class="textarea_container">
                    <el-form-item
                      :class="{ 'text-box': true, 'answer_textarea': item.answerConfirmFlg === '0' }"
                    >
                      <ifa-input-text
                        id="contents"
                        :prop="`answerList.${index}.answerContents`"
                        :key="'answerContents-' + index + '-' + refreshKey"
                        v-model="item.answerContents"
                        placeholder="回答を入力"
                        type="textarea"
                        :autosize="item.answerConfirmFlg === '0' ? { minRows: 1, maxRows: 7 } : false"
                        :rows="item.answerConfirmFlg === '0' ? '1' : '7'"
                        :domain="IfaText2000DomainModel"
                        style="width: 650px"
                        original-screen-id="SUB0511_02-02"
                      ></ifa-input-text>
                    </el-form-item>
                    <!-- 回答確定ボタン（回答確定フラグ=0：未確定の場合、表示） -->
                    <el-icon
                      v-if="item.answerConfirmFlg === '0'"
                      class="send_icon"
                      @click="ansewerConfirmedA003(item)"
                    ><Promotion /></el-icon>
                  </div>
                </td>
              </tr>
            </table>
          </el-form>
        </el-col>
        <!-- 登録ボタン -->
        <el-col
          :offset="1"
          style="padding: 10px"
        >
          <ifa-button
            text="登録"
            width="90"
            color="primary"
            style="padding-left: 0"
            :form="formRef"
            action-type="originalAction"
            @app-action-handler="confirmHandlerA006"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 確認ダイアログ.登録確認 -->
    <ifa-ok-cancel-dialog
      :is-visible="confirmDialog.visible"
      :title="confirmDialog.titleMessage"
      :message="confirmDialog.confirmMessage"
      @close-modal-ok="registerA007"
      @close-modal-cancel="confirmDialog.visible = false"
    ></ifa-ok-cancel-dialog>
    <!-- A007a.登録（ファイルアップロード）リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxCommonRegisterA007a"
      action-id="SUB0511_02-02#A007A"
      action-type="uploadAction"
      :request-model="IfaSuggestionBoxCommonRegisterA007aRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxCommonRegisterA007a($event)"
    ></ifa-requester>
    <!-- A007b.登録（DB登録）リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxCommonRegisterA007b"
      action-id="SUB0511_02-02#A007B"
      action-type="requestAction"
      :request-model="ifaSuggestionBoxCommonRegisterA007bRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxCommonRegisterA007b($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaSuggestionBoxCommonRegisterA007bRequestModel } from './js/IfaSuggestionBoxCommonRegisterA007bRequestModel'
import { IfaSugBoxCommonRegisterFormModel } from './js/IfaSugBoxCommonRegisterFormModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getMessage } from '@/utils/errorHandler'
import { notifyMessage } from '@/utils/errorHandler'

export default {
  components: {
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal', 'update-table', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaText2000DomainModel: IfaText2000DomainModel,
      IfaText127DomainModel: IfaText127DomainModel,
      formRef: {},
      form: new IfaSugBoxCommonRegisterFormModel(),
      ifaSuggestionBoxCommonRegisterA007bRequestModel: {},
      confirmDialog: { // 確認ダイアログ
        visible: false,
        titleMessage: '登録の確認',
        confirmMessage: ''
      },
      processing: false, // 「確定」ボタン 連打防止フラグ
      rules: {
        title: { required: true, trigger: 'blur', validator: this.titleValidator },
        category: { required: true, trigger: 'blur', validator: this.categoryValidator },
        status: { required: true, trigger: 'blur', validator: this.statusValidator },
        suggestion: { required: true, trigger: 'blur', validator: this.suggestionValidator }
      },
      fileSelectErrorMsg: getMessage('errors.numberOverRange', ['選択されたファイル', '10MB', '登録']), // ファイル選択用エラーメッセージ
      refreshKey: 0 // テキストエリア再描画用キー
    }
  },
  computed: {
    /* A007aリクエストモデルセット */
    IfaSuggestionBoxCommonRegisterA007aRequestModel() {
      // 添付ファイル
      let requestList = this.form.attachFileList.map((file, index) => {
        return {
          filename: `attachFile${index + 1}`,
          file: file
        }
      })

      // 皆様からの要望No（リネーム処理用）
      requestList.push(
        {
          key: 'sbcNo',
          value: this.form.sbcNo
        }
      )

      return requestList
    }
  },
  watch: {
    /* 添付ファイルの変更があれば添付ファイル名を更新する（添付ファイル名の環境依存文字チェック用） */
    'form.attachFileList': {
      handler: function() {
        this.form.attachFileName1 = this.form.attachFileList[0] ? this.form.attachFileList[0].name : ''
        this.form.attachFileName2 = this.form.attachFileList[1] ? this.form.attachFileList[1].name : ''
        this.form.attachFileName3 = this.form.attachFileList[2] ? this.form.attachFileList[2].name : ''
      },
      deep: true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    /* A001.初期化 */
    onShow() {
      this.form = Object.assign(this.form, [])

      // 回答一覧の事前処理
      this.preAnswerList()

      // 回答確定フラグ＝0(未確定)の回答を追加する
      this.addAnwser()

      this.$refs['form'].clearValidate()
    },
    /* A002.添付ファイル選択 */
    fileSelectA002(file, index) {
      this.form.attachFileList[index] = file
    },
    /* A003.回答確定 */
    ansewerConfirmedA003(item) {
      // 入力がない場合は確定しない（非活性）
      if (!item.answerContents) {
        return
      }

      // 回答一覧の対象明細の回答確定フラグを1(確定)に変更
      item.answerConfirmFlg = '1'

      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      // 回答確定フラグ＝0(未確定)の回答を追加する
      this.addAnwser()
    },
    /* A004.リセット.レスポンスハンドラー */
    ifaSuggestionBoxCommonRegisterResetA004(response) {
      this.reset()

      // 回答一覧の事前処理
      this.preAnswerList()

      // 回答確定フラグ＝0(未確定)の回答を追加する
      this.addAnwser()
    },
    /* A005.戻る */
    backA005() {
      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      this.form = Object.assign(this.form, [])

      this.reset()
      this.$emit('close-modal')
    },
    /* A006.登録確認 */
    confirmHandlerA006() {
      // ステータス=0(回答待ち)かつ(回答一覧に回答確定フラグ=1(確定)の場合
      if (this.form.status === '0' &&
          this.form.answerList &&
          this.form.answerList.find(item => item.answerConfirmFlg === '1')
      ) {
        // エラーメッセージを表示して処理終了。
        notifyMessage(
          -1,
          getMessage('errors.emp.sugBoxStatus.outOfRange'),
          this.form.pageTitle.name,
          Date.now()
        )
        return
      }

      // バリデーションチェック
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 確認ダイアログを表示する。
          // 回答確定フラグ＝0かつ回答内容入力あり　の回答がある場合
          if (
            this.form.answerList &&
            this.form.answerList.find(item => item.answerConfirmFlg === '0' && item.answerContents)
          ) {
            this.confirmDialog.confirmMessage = '未確定の回答があります。確定済の回答のみ登録します。よろしいですか？'
          // 上記以外の場合
          } else {
            this.confirmDialog.confirmMessage = '入力内容で登録します。よろしいですか？'
          }
          this.confirmDialog.visible = true
        } else {
          return
        }
      })
    },
    /* A007.登録 */
    registerA007() {
      this.$nextTick(() => {
        this.confirmDialog.visible = false
        // 添付ファイルが無い場合、A007b.登録（DB登録）リクエスターを呼び出す
        if (this.form.attachFileList.every(item => item === null)) {
          this.form.registerFileName1 = ''
          this.form.registerFileName2 = ''
          this.form.registerFileName3 = ''
          this.form.sbcNo = ''
          this.ifaSuggestionBoxCommonRegisterA007bRequestModel = this.setIfaSuggestionBoxCommonRegisterA007bRequestModel(null)
          document.getElementById('ifaSuggestionBoxCommonRegisterA007b').click()
        //  添付ファイルがある場合、A007a.登録（ファイルアップロード）リクエスターを呼び出す
        } else {
          document.getElementById('ifaSuggestionBoxCommonRegisterA007a').click()
        }
      })
    },
    /* A007a.登録（ファイルアップロード）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxCommonRegisterA007a(response) {
      // A007b.登録（DB登録）リクエスト作成
      this.ifaSuggestionBoxCommonRegisterA007bRequestModel = this.setIfaSuggestionBoxCommonRegisterA007bRequestModel(response)
      // A007b.登録（DB登録）リクエスターを呼び出す
      this.$nextTick(() => {
        document.getElementById('ifaSuggestionBoxCommonRegisterA007b').click()
      })
    },
    /* A007b.登録（DB登録）リクエストモデルセット*/
    setIfaSuggestionBoxCommonRegisterA007bRequestModel(fileList) {
      // A007a.登録（ファイルアップロード）でリネームを行ったファイル名を添付ファイル名1～3にセット
      // A007a.登録（ファイルアップロード）で採番した皆様からの要望Noをセット
      if (fileList) {
        this.form.registerFileName1 = fileList.dataList[0].registerFileName1
        this.form.registerFileName2 = fileList.dataList[0].registerFileName2
        this.form.registerFileName3 = fileList.dataList[0].registerFileName3
        this.form.sbcNo = fileList.dataList[0].sbcNo
      }

      const exUpdateDateAnswerList = this.form.answerList && this.form.answerList.length > 0 ? this.form.answerList.map(item => item) : []

      // 回答一覧から回答確定フラグ＝0：未確定の回答を除外
      const exConfirmFlgZeroAnswerList = exUpdateDateAnswerList.filter(item => item.answerConfirmFlg !== '0')

      return new IfaSuggestionBoxCommonRegisterA007bRequestModel(
        {
          ...this.form,
          answerList: exConfirmFlgZeroAnswerList
        }
      )
    },
    /* A007b.登録（DB登録）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxCommonRegisterA007b() {
      // 自画面（ポップアップ）を閉じる
      this.backA005()
      this.$emit('update-table')
    },
    /* リセット処理 */
    reset() {
      if (this.$refs['select1']) { this.$refs['select1'].clearFiles() }
      if (this.$refs['select2']) { this.$refs['select2'].clearFiles() }
      if (this.$refs['select3']) { this.$refs['select3'].clearFiles() }

      this.form.title = ''
      this.form.category = ''
      this.form.status = ''
      this.form.suggestion = ''
      this.form.sbcNo = ''
      this.form.attachFile1 = ''
      this.form.attachFile2 = ''
      this.form.attachFile3 = ''

      this.form.answerList = []

      this.form.registerFileName1 = ''
      this.form.registerFileName2 = ''
      this.form.registerFileName3 = ''

      this.form.attachFileName1 = ''
      this.form.attachFileName2 = ''
      this.form.attachFileName3 = ''

      this.form.attachFileList = [null, null, null]
      this.$refs['form'].clearValidate()
    },
    /* 回答一覧の事前処理 */
    preAnswerList() {
      this.form.answerList = []
    },
    /* 新規回答の追加 */
    addAnwser() {
      // 回答Noの最大値+1から連番を割り振る
      let calAnswerNo = '1'
      if (this.form.answerList && this.form.answerList.length > 0) {
        calAnswerNo = (Number(this.form.answerList[this.form.answerList.length - 1].answerNo) + 1).toString()
      }

      // 現在日付をYYYYMMDD形式で取得
      const requestedTime = this.$store.getters.requestedTime.split(' ')[0].replaceAll('/', '')

      // 回答一覧へ0：未確定の回答を追加
      this.form.answerList.push(
        {
          answerConfirmFlg: '0', // 回答確定フラグ：0：未確定
          answerNo: calAnswerNo, // 回答No：回答Noの最大値+1
          answerRegisterDate: requestedTime, // 回答登録日：システム日付（YYYY/MM/DD）
          answerContents: ''// 回答内容
        }
      )
    },
    /* 回答登録日 算出処理 */
    answerRegisterDateText(item) {
      // 回答確定フラグ＝0：未確定の場合、非表示
      let registerDateText = ''

      // 回答確定フラグ＝1：確定の場合
      if (item.answerConfirmFlg === '1') {
        // 確定ボタンが押された日(yyyy/MM/dd形式)＋" SBI証券"
        registerDateText = this.$_getFormattedDateValue(item.answerRegisterDate) + ' SBI証券'
      }

      return registerDateText
    },
    /* タイトル用バリデータ */
    titleValidator(rule, value, callback) {
      if (!this.form.title) {
        callback('タイトルを入力してください。')
        return
      }
      callback()
    },
    /* カテゴリ用バリデータ */
    categoryValidator(rule, value, callback) {
      if (this.form.category === '') {
        callback('カテゴリを選択してください。')
        return
      }
      callback()
    },
    /* ステータス用バリデータ */
    statusValidator(rule, value, callback) {
      if (this.form.status === '') {
        callback('ステータスを選択してください。')
        return
      }
      callback()
    },
    /* 要望内容用バリデータ */
    suggestionValidator(rule, value, callback) {
      if (!this.form.suggestion) {
        callback('要望内容を入力してください。')
        return
      }
      callback()
    },
    /* A003.添付ファイル削除 */
    handlerRemove(index) {
      this.form.attachFileList[index] = null
    }
  }
}
</script>

<style lang="scss" scoped>
  @import '@/styles/suggestionBox.scss';
</style>
