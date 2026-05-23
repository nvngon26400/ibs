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
                  <el-form-item v-if="statusCheck()">
                    <span class="required-mark">*</span>タイトル
                  </el-form-item>
                  <el-form-item v-else>
                    タイトル
                  </el-form-item>
                </td>
                <td class="table-body">
                  <div v-if="statusCheck()">
                    <el-form-item class="text-box">
                      <ifa-input-text
                        id="title"
                        v-model="form.title"
                        prop="title"
                        size="small"
                        style="width: 650px"
                        original-screen-id="SUB00_01-06_3"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </el-form-item>
                  </div>
                  <div v-else>
                    <td class="suggestion-text">
                      {{ form.title }}
                    </td>
                  </div>
                </td>
              </tr>
              <!-- カテゴリ -->
              <tr>
                <td class="table-label">
                  <el-form-item v-if="statusCheck()">
                    <span class="required-mark">*</span>カテゴリ
                  </el-form-item>
                  <el-form-item v-else>
                    カテゴリ
                  </el-form-item>
                </td>
                <td class="table-body">
                  <div v-if="statusCheck()">
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
                  </div>
                  <div v-else>
                    <td
                      class="suggestion-text"
                    >{{ $_out($_getCodeValue('SUG_BOX_CATEGORY', 1, form.category)) }}
                    </td>
                  </div>
                </td>
              </tr>
              <!-- ステータス -->
              <tr>
                <td class="table-label">
                  <el-form-item>
                    ステータス
                  </el-form-item>
                </td>
                <div class="table-body">
                  <td
                    class="suggestion-text"
                  >{{ $_out($_getCodeValue('SUG_BOX_STATUS', 1, form.status)) }}
                  </td>
                </div>
              </tr>
              <!-- 要望内容 -->
              <tr>
                <td class="table-label">
                  <el-form-item v-if="statusCheck()">
                    <span class="required-mark">*</span>要望内容
                  </el-form-item>
                  <el-form-item v-else>
                    要望内容
                  </el-form-item>
                </td>
                <td class="table-body">
                  <!-- 要望内容桁数 -->
                  <div
                    v-if="statusCheck()"
                    class="text_count_area"
                    :style="form.suggestion.length !== 2000 ? 'color: #6e6e6e;' : 'color: red;'"
                  >
                    {{ form.suggestion.length }}/2000
                  </div>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="suggestion"
                      v-model="form.suggestion"
                      type="textarea"
                      rows="7"
                      style="width: 650px"
                      :readonly="!statusCheck()"
                      prop="suggestion"
                      original-screen-id="SUB00_01-06_3"
                      :domain="IfaText2000DomainModel"
                      :key="'suggestion-' + refreshKey"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>

              <!-- 要望エリア添付ファイル ステータス：回答待ち -->
              <!-- 添付ファイル1 -->
              <tr v-if="statusCheck()">
                <td class="table-label">添付ファイル1</td>
                <td class="table-body">
                  <!-- 登録済添付ファイル1DLが空または登録済添付ファイル1削除フラグが1：削除の場合、ファイル選択部品を表示 -->
                  <el-form-item
                    v-if="form.registeredAttachFile1DeleteFlag === '1' || !form.registeredAttachFile1"
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
                        @change="fileSelectA004($event, 0)"
                        @before-remove="attachFileDeleteA003(0)"
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
                          original-screen-id="SUB00_01-06_3"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                  <div
                    v-else
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile1)"
                    >{{ form.registeredAttachFile1 }}</span>
                    <!-- 削除リンク -->
                    <span
                      class="_link"
                      @click="form.registeredAttachFile1DeleteFlag = '1'"
                    >削除</span>
                  </div>
                </td>
              </tr>
              <!-- 添付ファイル2 -->
              <tr v-if="statusCheck()">
                <td class="table-label">添付ファイル2</td>
                <td class="table-body">
                  <!-- 登録済添付ファイル1DLが空または登録済添付ファイル1削除フラグが1：削除の場合、ファイル選択部品を表示 -->
                  <el-form-item
                    v-if="form.registeredAttachFile2DeleteFlag === '1' || !form.registeredAttachFile2"
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
                        @change="fileSelectA004($event, 1)"
                        @before-remove="attachFileDeleteA003(1)"
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
                          original-screen-id="SUB00_01-06_3"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                  <div
                    v-else
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile2)"
                    >{{ form.registeredAttachFile2 }}</span>
                    <!-- 削除リンク -->
                    <span
                      class="_link"
                      @click="form.registeredAttachFile2DeleteFlag = '1'"
                    >削除</span>
                  </div>
                </td>
              </tr>
              <!-- 添付ファイル3 -->
              <tr v-if="statusCheck()">
                <td class="table-label">添付ファイル3</td>
                <td class="table-body">
                  <!-- 登録済添付ファイル1DLが空または登録済添付ファイル1削除フラグが1：削除の場合、ファイル選択部品を表示 -->
                  <el-form-item
                    v-if="form.registeredAttachFile3DeleteFlag === '1' || !form.registeredAttachFile3"
                    class="text-box text-box-file message-error-position"
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
                        @change="fileSelectA004($event, 2)"
                        @before-remove="attachFileDeleteA003(2)"
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
                          original-screen-id="SUB00_01-06_3"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                  <div
                    v-else
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile3)"
                    >{{ form.registeredAttachFile3 }}</span>
                    <!-- 削除リンク -->
                    <span
                      class="_link"
                      @click="form.registeredAttachFile3DeleteFlag = '1'"
                    >削除</span>
                  </div>
                </td>
              </tr>

              <!-- 添付ファイル説明文言 -->
              <tr v-if="statusCheck()">
                <td
                  class="table-label"
                  colspan="2"
                  style="font-size: 16px; font-weight: normal; padding-bottom: 20px;"
                >
                  ※登録可能な添付ファイルサイズは1ファイルにつき最大10MBです
                </td>
              </tr>

              <!-- 要望エリア添付ファイル ステータス：回答待ち以外 -->
              <!-- 添付ファイル1 -->
              <tr v-if="!statusCheck() && form.registeredAttachFile1 !== null">
                <td class="table-label">添付ファイル1</td>
                <td class="table-body">
                  <div
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile1)"
                    >{{ form.registeredAttachFile1 }}</span>
                  </div>
                </td>
              </tr>
              <!-- 添付ファイル2 -->
              <tr v-if="!statusCheck() && form.registeredAttachFile2 !== null">
                <td class="table-label">添付ファイル2</td>
                <td class="table-body">
                  <div
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile2)"
                    >{{ form.registeredAttachFile2 }}</span>
                  </div>
                </td>
              </tr>
              <!-- 添付ファイル3 -->
              <tr v-if="!statusCheck() && form.registeredAttachFile3 !== null">
                <td class="table-label">添付ファイル3</td>
                <td class="table-body">
                  <div
                    class="attached_file_link"
                  >
                    <!-- DLリンク -->
                    <span
                      class="_link"
                      @click="downloadA002(form.registeredAttachFile3)"
                    >{{ form.registeredAttachFile3 }}</span>
                  </div>
                </td>
              </tr>

              <!-- 回答エリア -->
              <tr
                v-for="(item, index) in form.answerList"
                :key="index"
              >
                <!-- ラベル （最初の回答にのみ表示） -->
                <td class="table-label"> {{ index == 0 ? 'SBI証券からの回答' :'' }} </td>
                <td class="table-body">
                  <!-- 回答登録日 -->
                  <div
                    class="personal_register_textarea"
                  >{{ answerRegisterDateText(item) }}
                  </div>
                  <!-- 回答内容 -->
                  <div class="textarea_container">
                    <el-form-item
                      class="text-box"
                    >
                      <ifa-input-text
                        id="contents"
                        v-model="item.answerContents"
                        type="textarea"
                        :rows="5"
                        style="width: 650px;"
                        :readonly="true"
                        :domain="IfaText2000DomainModel"
                        :key="'answerContents-' + index + '-' + refreshKey"
                      ></ifa-input-text>
                    </el-form-item>
                  </div>
                </td>
              </tr>

            </table>
          </el-form>
        </el-col>
        <!-- 登録ボタン -->
        <el-col
          v-if="statusCheck()"
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
            @app-action-handler="comfirmHandlerA006"
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
    <!-- A002.ダウンロード リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxPersonalDetailDonwloadA002"
      action-id="SUB00_01-06_3#A002"
      action-type="outputPdfAction"
      :request-model="IfaSuggestionBoxPersonalDetailA002RequestModel"
    ></ifa-requester>
    <!-- A007a.登録（ファイルアップロード）リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxPersonalDetailRegisterA007a"
      action-id="SUB00_01-06_3#A007A"
      action-type="uploadAction"
      :request-model="IfaSuggestionBoxPersonalDetailA007aRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxPersonalDetailA007a($event)"
    ></ifa-requester>
    <!-- A007b.登録（DB更新）リクエスター -->
    <ifa-requester
      id="ifaSuggestionBoxPersonalDetailRegisterA007b"
      action-id="SUB00_01-06_3#A007B"
      action-type="requestAction"
      :request-model="IfaSuggestionBoxPersonalDetailA007bRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxPersonalDetailA007b($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaSuggestionBoxPersonalDetailA002RequestModel } from './js/IfaSuggestionBoxPersonalDetailA002RequestModel'
import { IfaSuggestionBoxPersonalDetailA007bRequestModel } from './js/IfaSuggestionBoxPersonalDetailA007bRequestModel'
import { IfaSuggestionBoxPersonalDetailFormModel } from './js/IfaSuggestionBoxPersonalDetailFormModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getMessage } from '@/utils/errorHandler'
import { updateSugBoxUnreadItems } from '@/utils/storeHelper'

export default {
  components: {
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
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
      form: new IfaSuggestionBoxPersonalDetailFormModel(),
      IfaSuggestionBoxPersonalDetailA002RequestModel: {},
      IfaSuggestionBoxPersonalDetailA007bRequestModel: {},
      confirmDialog: { // 確認ダイアログ
        visible: false,
        titleMessage: '登録の確認',
        confirmMessage: ''
      },
      processing: false, // 「確定」ボタン 連打防止フラグ
      rules: {
        title: { required: true, trigger: 'blur', validator: this.titleValidator },
        category: { required: true, trigger: 'blur', validator: this.categoryValidator },
        suggestion: { required: true, trigger: 'blur', validator: this.suggestionValidator }
      },
      fileSelectErrorMsg: getMessage('errors.numberOverRange', ['選択されたファイル', '10MB', '登録']), // ファイル選択用エラーメッセージ
      refreshKey: 0 // テキストエリア再描画用キー
    }
  },
  computed: {
    /* A007aリクエストモデルセット */
    IfaSuggestionBoxPersonalDetailA007aRequestModel() {
      // 添付ファイル
      let requestList = this.form.attachFileList.map((file, index) => {
        return {
          filename: `attachFile${index + 1}`,
          file: file
        }
      })

      // あなたの要望No（リネーム処理用）
      requestList.push(
        {
          key: 'sbpNo',
          value: this.form.sbpNo
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
      this.form = Object.assign(this.form, this.formData)
      this.$refs['form'].clearValidate()
      updateSugBoxUnreadItems(this.form.sugBoxUnreadItems)
    },
    /* A004.添付ファイル選択 */
    fileSelectA004(file, index) {
      this.form.attachFileList[index] = file
    },
    /* A002.ダウンロード */
    downloadA002(attachFile) {
      if (this.processing) return // 連打防止

      this.processing = true
      if (attachFile) {
        // DL対象のファイル名をセットしてA002.ダウンロード.リクエスターを呼び出す
        this.IfaSuggestionBoxPersonalDetailA002RequestModel = new IfaSuggestionBoxPersonalDetailA002RequestModel({ attachFile: attachFile })
        this.$nextTick(() => {
          document.getElementById('ifaSuggestionBoxPersonalDetailDonwloadA002').click()
        })
      }
      this.processing = false
    },
    /* A003.添付ファイル削除 */
    attachFileDeleteA003(index) {
      this.form.attachFileList[index] = null
    },
    /* A005.戻る */
    backA005() {
      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      this.reset()
      this.$emit('close-modal')
    },
    /* A006.登録確認 */
    comfirmHandlerA006() {
      // バリデーションチェック
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 確認ダイアログを表示する。
          this.confirmDialog.confirmMessage = '入力内容で登録します。よろしいですか？'
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
        // 添付ファイルが無い場合、A007b.登録（DB更新）リクエスターを呼び出す
        if (this.form.attachFileList.every(item => item === null)) {
          this.IfaSuggestionBoxPersonalDetailA007bRequestModel = this.setIfaSuggestionBoxPersonalDetailA007bRequestModel(null)
          document.getElementById('ifaSuggestionBoxPersonalDetailRegisterA007b').click()
          //  添付ファイルがある場合、A007a.登録（ファイルアップロード）リクエスターを呼び出す
        } else {
          document.getElementById('ifaSuggestionBoxPersonalDetailRegisterA007a').click()
        }
      })
    },
    /* A007a.登録（ファイルアップロード）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxPersonalDetailA007a(response) {
      // A007b.登録（DB更新）リクエスト作成
      this.IfaSuggestionBoxPersonalDetailA007bRequestModel = this.setIfaSuggestionBoxPersonalDetailA007bRequestModel(response)
      // A007b.登録（DB更新）リクエスターを呼び出す
      this.$nextTick(() => {
        document.getElementById('ifaSuggestionBoxPersonalDetailRegisterA007b').click()
      })
    },
    /* A007b.登録（DB更新）リクエストモデルセット*/
    setIfaSuggestionBoxPersonalDetailA007bRequestModel(fileList) {
      // A007a.登録（ファイルアップロード）でリネームを行ったファイル名を添付ファイル名1～3にセット
      if (fileList) {
        this.form.registerFileName1 = fileList.dataList[0].registerFileName1
        this.form.registerFileName2 = fileList.dataList[0].registerFileName2
        this.form.registerFileName3 = fileList.dataList[0].registerFileName3
      } else {
        this.form.registerFileName1 = ''
        this.form.registerFileName2 = ''
        this.form.registerFileName3 = ''
      }

      return new IfaSuggestionBoxPersonalDetailA007bRequestModel(
        {
          ...this.form
        }
      )
    },
    /* A007b.登録（DB更新）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxPersonalDetailA007b() {
      // 自画面（ポップアップ）を閉じる
      this.backA005()
      this.$emit('update-table')
    },
    /* リセット処理 */
    reset() {
      if (this.$refs['select1']) { this.$refs['select1'].clearFiles() }
      if (this.$refs['select2']) { this.$refs['select2'].clearFiles() }
      if (this.$refs['select3']) { this.$refs['select3'].clearFiles() }
      this.form.attachFileList = [null, null, null]
      this.form.registeredAttachFile1DeleteFlag = '0'
      this.form.registeredAttachFile2DeleteFlag = '0'
      this.form.registeredAttachFile3DeleteFlag = '0'
      this.$refs['form'].clearValidate()
    },
    /* 回答登録日 算出処理 */
    answerRegisterDateText(item) {
      let registerDateText = ''
      if (item.createTime === item.answerUpdateTime) { // 回答一覧.回答登録日=回答一覧.回答更新日 の場合
        registerDateText = this.$_getFormattedDateValue(item.createTime) + ' SBI証券'
      } else {
        registerDateText = this.$_getFormattedDateValue(item.createTime) + '（' + this.$_getFormattedDateValue(item.answerUpdateTime) + '更新） SBI証券'
      }
      return registerDateText
    },
    statusCheck() {
      if (this.form.status === '0') {
        return true
      }
      return false
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
    /* 要望内容用バリデータ */
    suggestionValidator(rule, value, callback) {
      if (!this.form.suggestion) {
        callback('要望内容を入力してください。')
        return
      }
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
.suggestion-text {
    font-size: 16px;
    color: #18181A;
    padding: 8px 0;
    display: flex;
    width: 95%;
    margin-left: 20px;
}
.personal_register_textarea {
  text-align: left;
  margin: 20px 0 0 20px;
  font-size: 15px;
  color: #18181A;
}
.spaced-table {
  padding-bottom: 20px;
}
  @import '@/styles/suggestionBox.scss';
</style>
