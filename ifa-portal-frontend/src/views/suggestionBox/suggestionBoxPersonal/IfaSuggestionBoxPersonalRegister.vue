<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="backA004"
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
              @app-action-handler="ifaSuggestionBoxsPersonalRegisterResetA003()"
            ></ifa-button>

            <!-- 戻るボタン -->
            <ifa-button
              text="戻る"
              width="90"
              color="secondary"
              style="padding-right: 0;"
              action-type="originalAction"
              @app-action-handler="backA004"
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
                      original-screen-id="SUB00_01-06_3"
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
                      v-model="form.suggestion"
                      type="textarea"
                      rows="5"
                      style="width: 650px"
                      prop="suggestion"
                      original-screen-id="SUB00_01-06_3"
                      :domain="IfaText2000DomainModel"
                      :key="'suggestion-' + refreshKey"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>

              <!-- 要望エリア添付ファイル -->
              <!-- 添付ファイル1 -->
              <tr>
                <td class="table-label">添付ファイル1</td>
                <td class="table-body">
                  <el-form-item class="text-box text-box-file message-error-position">
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
                          original-screen-id="SUB00_01-06_2"
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
                  <el-form-item class="text-box text-box-file message-error-position">
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
                          original-screen-id="SUB00_01-06_2"
                          :domain="IfaText127DomainModel"
                        ></ifa-input-text>
                      </div>
                    </div>
                  </el-form-item>
                </td>
              </tr>
              <!-- 添付ファイル3 -->
              <tr>
                <td class="table-label">添付ファイル3 </td>
                <td class="table-body">
                  <el-form-item class="text-box text-box-file">
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
                          original-screen-id="SUB00_01-06_2"
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
            @app-action-handler="comfirmHandlerA005"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 確認ダイアログ.更新確認 -->
    <ifa-ok-cancel-dialog
      :is-visible="confirmDialog.visible"
      :title="confirmDialog.titleMessage"
      :message="confirmDialog.confirmMessage"
      @close-modal-ok="registerA006"
      @close-modal-cancel="confirmDialog.visible = false"
    ></ifa-ok-cancel-dialog>
    <!-- A006a.登録（ファイルアップロード）リクエスター -->
    <ifa-requester
      id="IfaSuggestionBoxPersonalRegisterRegisterA006a"
      action-id="SUB00_01-06_2#A006A"
      action-type="uploadAction"
      :request-model="IfaSuggestionBoxPersonalRegisterA006aRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxPersonalRegisterA006a($event)"
    ></ifa-requester>
    <!-- A006b.登録（DB更新）リクエスター -->
    <ifa-requester
      id="IfaSuggestionBoxPersonalRegisterRegisterA006b"
      action-id="SUB00_01-06_2#A006B"
      action-type="requestAction"
      :request-model="ifaSuggestionBoxPersonalRegisterA006bRequestModel"
      @response-handler="responseHandlerIfaSuggestionBoxPersonalRegisterA006b($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaSuggestionBoxPersonalRegisterFormModel } from './js/IfaSuggestionBoxPersonalRegisterFormModel'
import { IfaSuggestionBoxPersonalRegisterA006bRequestModel } from './js/IfaSuggestionBoxPersonalRegisterA006bRequestModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { getMessage } from '@/utils/errorHandler'

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
      form: new IfaSuggestionBoxPersonalRegisterFormModel(),
      ifaSuggestionBoxPersonalRegisterA006bRequestModel: {},
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
    /* A006aリクエストモデルセット */
    IfaSuggestionBoxPersonalRegisterA006aRequestModel() {
      // 添付ファイル
      let requestList = this.form.attachFileList.map((file, index) => {
        return {
          filename: `attachFile${index + 1}`,
          file: file
        }
      })
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
      this.$refs['form'].clearValidate()
    },
    /* A002.添付ファイル選択 */
    fileSelectA002(file, index) {
      this.form.attachFileList[index] = file
    },
    ifaSuggestionBoxsPersonalRegisterResetA003(response) {
      this.reset()
    },
    /* A003.添付ファイル削除 */
    attachFileDeleteA003(index) {
      this.form.attachFileList[index] = null
    },
    /* A004.戻る */
    backA004() {
      // テキストエリアの再描画のためキーを更新
      this.refreshKey++

      this.reset()
      this.$emit('close-modal')
    },
    /* A005.登録確認 */
    comfirmHandlerA005() {
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
    /* A006.登録 */
    registerA006() {
      this.$nextTick(() => {
        this.confirmDialog.visible = false
        // 添付ファイルが無い場合、A006b.更新（DB更新）リクエスターを呼び出す
        if (this.form.attachFileList.every(item => item === null)) {
          this.ifaSuggestionBoxPersonalRegisterA006bRequestModel = this.setIfaSuggestionBoxPersonalRegisterA006bRequestModel(null)
          document.getElementById('IfaSuggestionBoxPersonalRegisterRegisterA006b').click()
          //  添付ファイルがある場合、A006a.更新（ファイルアップロード）リクエスターを呼び出す
        } else {
          document.getElementById('IfaSuggestionBoxPersonalRegisterRegisterA006a').click()
        }
      })
    },
    /* A006a.登録（ファイルアップロード）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxPersonalRegisterA006a(response) {
      // A006b.登録（DB更新）リクエスト作成
      this.ifaSuggestionBoxPersonalRegisterA006bRequestModel = this.setIfaSuggestionBoxPersonalRegisterA006bRequestModel(response)
      // A006b.登録（DB更新）リクエスターを呼び出7す
      this.$nextTick(() => {
        document.getElementById('IfaSuggestionBoxPersonalRegisterRegisterA006b').click()
      })
    },
    /* A006b.登録（DB更新）リクエストモデルセット*/
    setIfaSuggestionBoxPersonalRegisterA006bRequestModel(fileList) {
      // A006a.更新（ファイルアップロード）でリネームを行ったファイル名を添付ファイル名1～3にセット
      if (fileList) {
        this.form.registerFileName1 = fileList.dataList[0].registerFileName1
        this.form.registerFileName2 = fileList.dataList[0].registerFileName2
        this.form.registerFileName3 = fileList.dataList[0].registerFileName3
        this.form.sbpNo = fileList.dataList[0].sbpNo
      } else {
        this.form.registerFileName1 = ''
        this.form.registerFileName2 = ''
        this.form.registerFileName3 = ''
        this.form.sbpNo = ''
      }
      return new IfaSuggestionBoxPersonalRegisterA006bRequestModel(
        {
          ...this.form
        }
      )
    },
    /* A006b.更新（DB更新）レスポンスハンドラー */
    responseHandlerIfaSuggestionBoxPersonalRegisterA006b() {
      // 自画面（ポップアップ）を閉じる
      this.backA004()
      this.$emit('update-table')
    },
    /* リセット処理 */
    reset() {
      if (this.$refs['select1']) { this.$refs['select1'].clearFiles() }
      if (this.$refs['select2']) { this.$refs['select2'].clearFiles() }
      if (this.$refs['select3']) { this.$refs['select3'].clearFiles() }

      this.form.title = ''
      this.form.category = ''
      this.form.suggestion = ''
      this.form.sbpNo = ''
      this.form.attachFile1 = ''
      this.form.attachFile2 = ''
      this.form.attachFile3 = ''

      this.form.registerFileName1 = ''
      this.form.registerFileName2 = ''
      this.form.registerFileName3 = ''

      this.form.attachFileName1 = ''
      this.form.attachFileName2 = ''
      this.form.attachFileName3 = ''

      this.form.attachFileList = [null, null, null]
      this.$refs['form'].clearValidate()
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
  @import '@/styles/suggestionBox.scss';
</style>
