<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1500px"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
    >
      <!-- メイン -->
      <el-row
        v-loading="loading"
        class="dialog-loading"
        element-loading-background="rgba(255, 255, 255, 0.4)"
      >
        <el-col
          :span="22"
          :offset="1"
        >
          <div style="width:98%; text-align: right; margin: 10px;">
            <ifa-button
              text="戻る"
              width="90"
              color="secondary"
              style="padding-right: 0;"
              action-type="originalAction"
              @app-action-handler="onDialogClose"
            ></ifa-button>
          </div>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="formRef"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <table class="input-table">
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>タイトル
                  </el-form-item>
                </td>
                <td class="table-body title">
                  <ifa-input-text
                    id="title"
                    v-model="form.title"
                    label="タイトル"
                    prop="title"
                    size="small"
                    style="width: 1100px;"
                    original-screen-id="SUB0512-02"
                    :domain="IfaText127DomainModel"
                  ></ifa-input-text>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>画面表示開始日
                  </el-form-item>
                </td>
                <td class="table-body">
                  <ifa-date-picker
                    ref="displayedDatePicker"
                    v-model="form.displayedDate"
                    prop="displayedDate"
                    size="small"
                  ></ifa-date-picker>
                  <span class="date-font">※本日～翌年までの日付を指定して下さい。</span>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    内容
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item>
                    <el-form
                      ref="formRefDynamicRule"
                      :model="formDynamicModel"
                      :rules="dynamicRules"
                    >
                      <table
                        class="inner-table"
                        cellpadding="5"
                      >
                        <thead>
                          <tr>
                            <th>メニュー名</th>
                            <th>画面名</th>
                            <th>内容</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr
                            v-for="(item, index) in form.contentItemList"
                            :key="index"
                          >
                            <td class="menu-name">
                              <ifa-input-text
                                id="menuName"
                                ref="contentItem"
                                v-model="item.menuName"
                                :prop="`menuName${index}`"
                                size="small"
                                type="textarea"
                                rows="1"
                                :autosize="{ minRows: 1, maxRows: 4 }"
                                style="width: 250px; word-break: break-all;"
                                :maxlength="form.maxLengthMenuName"
                                original-screen-id="SUB0512-02"
                                validate-on-rule-change="false"
                              ></ifa-input-text>
                            </td>
                            <td class="screen-name">
                              <ifa-input-text
                                id="screenName"
                                ref="contentItem"
                                v-model="item.screenName"
                                :prop="`screenName${index}`"
                                size="small"
                                type="textarea"
                                rows="1"
                                :autosize="{ minRows: 1, maxRows: 4 }"
                                style="width: 150px; word-break: break-all;"
                                :maxlength="form.maxLengthScreenName"
                                original-screen-id="SUB0512-02"
                              ></ifa-input-text>
                            </td>
                            <td class="content">
                              <ifa-input-text
                                id="content"
                                ref="contentItem"
                                v-model="item.content"
                                :prop="`content${index}`"
                                size="small"
                                type="textarea"
                                rows="1"
                                :autosize="{ minRows: 1, maxRows: 4 }"
                                style="width: 640px; word-break: break-all;"
                                :maxlength="form.maxLengthContent"
                                original-screen-id="SUB0512-02"
                              ></ifa-input-text>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </el-form>
                    <div class="line-button">
                      <ifa-button
                        text="行を追加"
                        width="90"
                        color="secondary"
                        :disabled="form.contentItemList.length >= form.maxDisplayCount"
                        action-type="originalAction"
                        @app-action-handler="addRow"
                      ></ifa-button>
                      <ifa-button
                        text="行を削除"
                        width="90"
                        color="secondary"
                        :disabled="form.contentItemList.length <= 1"
                        action-type="originalAction"
                        @app-action-handler="removeRow"
                      ></ifa-button>
                    </div>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label ifa-file-select">詳細ファイル</td>
                <td class="table-body">
                  <div class="file_item">
                    <ifa-input-radio
                      v-model="form.fileType"
                      label="ファイルタイプ"
                      code-list-id="FILE_TYPE"
                      :disp-pattern="1"
                      :select-pattern="1"
                    ></ifa-input-radio>
                    <span class="file_item_pdf">
                      <ifa-input-radio
                        v-model="form.pdfSize"
                        prop="pdfSize"
                        label="PDFサイズ"
                        code-list-id="PDF_SIZE"
                        :disp-pattern="1"
                        :select-pattern="1"
                        :visible="setPDFVisible"
                        style="margin-right: 20px;"
                        is-button
                        required
                      ></ifa-input-radio>
                      <ifa-input-radio
                        v-model="form.pdfDirection"
                        prop="pdfDirection"
                        label="向き"
                        code-list-id="VERTICAL_AND_HORIZONTAL"
                        :disp-pattern="1"
                        :select-pattern="1"
                        :visible="setPDFVisible"
                        style="margin-right: 20px;"
                        is-button
                        required
                      ></ifa-input-radio>
                    </span>
                  </div>
                  <el-form-item
                    v-if="setDetailedFileVisible"
                    :class="attchFileAutoWidth"
                  >
                    <ifa-file-select
                      ref="detailedFile"
                      class="file_select"
                      text="ファイルを選択"
                      color="secondary"
                      small
                      width="130"
                      msg-title="ファイルを選択"
                      :show-file-list="true"
                      :accept="setFileAcceptType"
                      @change="handleChangeDetailedFileSelection($event)"
                      @before-remove="handleRemoveFile"
                    ></ifa-file-select>
                    <label
                      v-if="!form.detailedFile"
                      style="font-weight: normal"
                    >
                      ファイルが選択されていません
                    </label>
                    <div
                      v-if="form.detailedFile"
                      class="register_file_input text-box text-box-file message-error-position"
                    >
                      <ifa-input-text
                        id="detailedFileName"
                        ref="detailedFileName"
                        v-model="form.detailedFileName"
                        label="詳細ファイル名"
                        prop="detailedFileName"
                        original-screen-id="SUB0512-02"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </el-form>
        </el-col>
        <el-col
          :offset="1"
          style="padding: 10px"
        >
          <ifa-button
            text="登録"
            width="90"
            color="primary"
            style="padding-left: 0"
            :form="formRefDynamicRule"
            action-type="originalAction"
            @app-action-handler="confirmHandlerA006"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogComfirmVisible"
      :title="setConfirmDialogTitle"
      :message="confirmMessage"
      class="confirm-dialog"
      style="width: 600px"
      @close-modal-ok="doOkOperation"
      @close-modal-cancel="loading = false; dialogComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- 登録確認リクエスター -->
    <ifa-requester
      id="ifaReleaseNoteRegisterConfirmA006"
      action-id="SUB0512-02#A006"
      action-type="requestAction"
      :request-model="ifaReleaseNoteRegisterA006RequestModel"
      @response-handler="responseHandlerIfaReleaseNoteRegisterConfirmA006($event)"
    ></ifa-requester>
    <!-- アップロードリクエスター -->
    <ifa-requester
      id="ifaReleaseNoteRegisterA007a"
      action-id="SUB0512-02#A007A"
      action-type="uploadAction"
      :request-model="ifaReleaseNoteRegisterA007aRequestModel"
      @response-handler="responseHandlerIfaReleaseNoteRegisterA007a($event)"
    ></ifa-requester>
    <!-- 登録リクエスター -->
    <ifa-requester
      id="ifaReleaseNoteRegisterA007b"
      action-id="SUB0512-02#A007B"
      action-type="requestAction"
      :request-model="ifaReleaseNoteRegisterA007bRequestModel"
      @response-handler="responseHandlerIfaReleaseNoteRegisterA007b($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { getMessage } from '@/utils/errorHandler'
import { isReversal } from '@/components/Date/js/IfaDatePickerFunction.js'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaReleaseNoteRegisterFormModel } from './js/IfaReleaseNoteRegisterFormModel'
import { IfaReleaseNoteRegisterA006RequestModel } from './js/IfaReleaseNoteRegisterA006RequestModel'
import { IfaReleaseNoteRegisterA007bRequestModel } from './js/IfaReleaseNoteRegisterA007bRequestModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'

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
      IfaText127DomainModel: IfaText127DomainModel,
      formRef: {},
      formRefDynamicRule: {},
      form: new IfaReleaseNoteRegisterFormModel(),
      formDynamicModel: new IfaReleaseNoteRegisterFormModel(),
      ifaReleaseNoteRegisterA006RequestModel: {},
      ifaReleaseNoteRegisterA007aRequestModel: [],
      ifaReleaseNoteRegisterA007bRequestModel: {},
      dialogType: '', // '0': 登録確認ダイアログ / '1': 削除確認ダイアログ
      dialogComfirmVisible: false, // 登録.削除確認ダイアログ表示フラグ
      confirmMessage: '', // 登録.削除確認ダイアログ本文
      confirmTitle: '', // 登録.削除確認ダイアログタイトル
      loading: false, // loadingダイアログ
      rules: {
        title: { required: true, trigger: 'blur', message: getMessage('errors.required', ['タイトル']) },
        displayedDate: { required: true, validator: this.displayedDateValidator },
        detailedFileName: { trigger: 'change', validator: this.detailedFileTypeValidator }
      },
      dynamicRules: {}
    }
  },
  computed: {
    setPDFVisible() {
      // PDFサイズとPDF向きはファイルタイプ「PDF」を選択した場合のみ表示する
      if (this.form.fileType === '1') {
        return true
      } else {
        return false
      }
    },
    setDetailedFileVisible() {
      // ファイルタイプ「画像」を選択した場合、常に表示する
      // ファイルタイプ「PDF」を選択した場合、「PDFサイズ」、「PDF向き」に値が設定されている場合のみ、表示する
      if (this.form.fileType === '0' || (this.form.fileType === '1' && this.form.pdfSize !== '' && this.form.pdfDirection !== '')) {
        return true
      } else {
        return false
      }
    },
    setFileAcceptType() {
      // ファイルタイプ「画像」を選択した場合、拡張子は「.png」「.jpeg(.jpg)」のみ有効
      // ファイルタイプ「PDF」を選択した場合、拡張子は「.pdf」のみ有効
      if (this.form.fileType === '0') {
        return '.png,.jpg,.jpeg'
      } else {
        return '.pdf'
      }
    },
    setConfirmDialogTitle() {
      // 登録確認ダイアログを表示した場合、タイトル："登録の確認"
      // 削除確認ダイアログを表示した場合、タイトル："行削除の確認"
      if (this.form.dialogType === '0') {
        return '登録の確認'
      } else if (this.form.dialogType === '1') {
        return '行削除の確認'
      } else {
        return ''
      }
    },
    attchFileAutoWidth() {
      // 詳細ファイル名の表示幅の設定
      return this.form.detailedFileName?.length > 70 ? 'ellipsis-name-width' : 'full-name-width'
    }
  },
  watch: {
    isVisible: function() {
      if (this.isVisible) {
        // 初期化イベント(アクションID: A001)
        this.initForm()
      }
    },
    'form.displayedDate'(newValue) {
      // 画面表示開始日 ≠ A001.画面表示開始日 かつ 画面表示開始日 ＜ 当日の場合、当日の日付に置き換える
      // 画面表示開始日は2年後の1/1以降が指定された場合、1年後の12/31に置き換える
      this.$nextTick(() => {
        const currentDate = this.$_getFormattedDateValue(this.formatDate(new Date()))
        const inputDisplayedDate = this.$_getFormattedDateValue(newValue)
        const nextYearLastDay = this.getNextYearLastDay()
        if (isReversal(currentDate, inputDisplayedDate)) {
          this.form.displayedDate = currentDate
        } else if (isReversal(inputDisplayedDate, nextYearLastDay)) {
          this.form.displayedDate = nextYearLastDay
        }
        if (this.$refs.displayedDatePicker && this.$refs.displayedDatePicker.handleBlur) {
          this.$refs.displayedDatePicker.handleBlur()
        }
      })
    },
    'form.fileType': function() {
      // ファイルタイプ切り替え時に、詳細ファイルエリアの値を削除する
      this.clearDetailedFileArea()
    },
    'form.detailedFile': {
      // 詳細ファイル切り替え時に、環境依存文字チェック用詳細ファイル名再設定
      handler: function() {
        this.form.detailedFileName = this.form.detailedFile ? this.form.detailedFile.name : ''
      },
      deep: true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
    this.formRefDynamicRule = this.$refs.formDynamicModel
  },
  unmounted() {
    this.loading = false
  },
  methods: {
    // 行追加イベント(アクションID: A002)
    addRow() {
      // 行数は最大99行まで
      if (this.form.contentItemList.length < this.form.maxDisplayCount) {
        // 内容リストは1行分を追加する
        this.pushContentItemList(1)
      }
    },
    // 行削除イベント(アクションID: A003)
    removeRow() {
      const inputedItem = this.form.contentItemList?.slice(-1).filter(item => item.menuName || item.screenName || item.content) || []
      if (inputedItem.length === 0) {
        const delPos = this.form.contentItemList.length - 1
        // 内容エリア.最下部行を1行削除する
        this.form.contentItemList.splice(delPos, 1)
        // 最下部1行の動的ルールを削除する
        delete this.dynamicRules[`menuName${delPos}`]
        delete this.dynamicRules[`screenName${delPos}`]
        delete this.dynamicRules[`content${delPos}`]
      } else {
        // 削除確認ダイアログ設定
        this.form.dialogType = '1'
        // 削除確認ダイアログの本文の設定
        this.confirmMessage = '入力した内容があります。削除してよろしいですか？'
        // 削除行に入力値がある場合：削除確認ダイアログを表示する。（削除確認ダイアログ表示フラグ = true）
        this.dialogComfirmVisible = true
      }
    },
    // 確認ダイアログの「OK」ボタンが押下後、削除操作を続行する(アクションID: A003)
    doOperationA003() {
      const delPos = this.form.contentItemList.length - 1
      // 確認ダイアログ閉じる
      this.dialogComfirmVisible = false
      // 内容エリア.最下部行を1行削除する
      this.form.contentItemList.splice(delPos, 1)
      // 最下部1行の動的ルールを削除する
      delete this.dynamicRules[`menuName${delPos}`]
      delete this.dynamicRules[`screenName${delPos}`]
      delete this.dynamicRules[`content${delPos}`]
    },
    // 詳細ファイル選択(アクションID: A004)
    handleChangeDetailedFileSelection(file) {
      this.form.detailedFile = file
    },
    // ダイアログ閉じる(アクションID: A005)
    onDialogClose() {
      // 動的ルールを削除する
      this.deleteDynamicRules()
      // 内容の行数をリセットする
      this.form.contentItemList = []
      // 自画面（ポップアップ）を閉じる
      this.$emit('close-modal')
    },
    // 内容エリア.いずれかの項目に入力値があるある場合、入力内容のチェックを行う
    confirmHandlerA006() {
      this.loading = true
      // 動的ルールバリデーションクリア
      if (this.$refs['formRefDynamicRule']) { this.$refs['formRefDynamicRule'].clearValidate() }
      // 静的ルールバリデーション
      this.$refs['formRef'].validate(valid => {
        if (valid) {
          // チェック項目設定
          Object.assign(this.ifaReleaseNoteRegisterA006RequestModel, new IfaReleaseNoteRegisterA006RequestModel(this.form))
          if (this.ifaReleaseNoteRegisterA006RequestModel.contentItemList) {
            this.ifaReleaseNoteRegisterA006RequestModel.contentItemList = this.ifaReleaseNoteRegisterA006RequestModel.contentItemList.map((item, index) => ({
              releaseNoteContentNo: index + 1,
              ...item
            }))
          }
          document.getElementById('ifaReleaseNoteRegisterConfirmA006').click()
        } else {
          this.loading = false
        }
      })
    },
    // 登録確認ダイアログ表示(アクションID: A006)
    responseHandlerIfaReleaseNoteRegisterConfirmA006(response) {
      if (response.dataList[0].contentList) {
        for (let i = 0; i < this.form.contentItemList.length; i++) {
          this.dynamicRules[`menuName${i}`] = {
            validator: (rule, value, callback) => {
              if (response.dataList[0].contentList[i] && response.dataList[0].contentList[i].menuNameErrorMessage) {
                // 内容エリアの「内容.メニュー名」環境依存文字チェック作成
                callback(response.dataList[0].contentList[i].menuNameErrorMessage)
              } else if (this.form.contentItemList[i] && this.form.contentItemList[i].menuName !== null && this.form.contentItemList[i].menuName.length > this.form.maxLengthMenuName) {
                // 内容エリアの「内容.メニュー名」最大長さチェック作成
                callback(getMessage('errors.maxSize', ['メニュー名', this.form.maxLengthMenuName]))
              } else {
                callback()
              }
            }
          }
          this.dynamicRules[`screenName${i}`] = {
            validator: (rule, value, callback) => {
              if (response.dataList[0].contentList[i] && response.dataList[0].contentList[i].screenNameErrorMessage) {
                // 内容エリアの「内容.画面名」環境依存文字チェック作成
                callback(response.dataList[0].contentList[i].screenNameErrorMessage)
              } else if (this.form.contentItemList[i] && this.form.contentItemList[i].screenName !== null && this.form.contentItemList[i].screenName.length > this.form.maxLengthScreenName) {
                // 内容エリアの「内容.画面名」最大長さチェック作成
                callback(getMessage('errors.maxSize', ['画面名', this.form.maxLengthScreenName]))
              } else {
                callback()
              }
            }
          }
          this.dynamicRules[`content${i}`] = {
            validator: (rule, value, callback) => {
              if (response.dataList[0].contentList[i] && response.dataList[0].contentList[i].contentErrorMessage) {
                // 内容エリアの「内容.内容」環境依存文字チェック作成
                callback(response.dataList[0].contentList[i].contentErrorMessage)
              } else if (this.form.contentItemList[i] && this.form.contentItemList[i].content !== null && this.form.contentItemList[i].content.length > this.form.maxLengthContent) {
                // 内容エリアの「内容.画面名」最大長さチェック作成
                callback(getMessage('errors.maxSize', ['内容', this.form.maxLengthContent]))
              } else {
                callback()
              }
            }
          }
        }
      }
      // 動的ルールバリデーション
      this.$refs['formRefDynamicRule'].validate(valid => {
        if (valid) {
          // 登録確認ダイアログ設定
          this.form.dialogType = '0'
          // 登録確認メッセージ本文
          this.confirmMessage = '入力内容で登録します。よろしいですか？'
          // 登録確認ダイアログを表示する。（登録確認ダイアログ表示フラグ = true）
          this.dialogComfirmVisible = true
        } else {
          this.loading = false
        }
      })
    },
    // 確認ダイアログの「OK」ボタンが押下後、登録処理を続行する(アクションID: A007)
    doOperationA007() {
      this.$nextTick(() => {
        // 確認ダイアログ閉じる
        this.dialogComfirmVisible = false
        if (this.form.detailedFile) {
          Object.assign(this.ifaReleaseNoteRegisterA007aRequestModel, [{ filename: 'detailedFile', file: this.form.detailedFile }])
          // アップロード用リクエスト発行
          document.getElementById('ifaReleaseNoteRegisterA007a').click()
        } else {
          Object.assign(this.ifaReleaseNoteRegisterA007bRequestModel, new IfaReleaseNoteRegisterA007bRequestModel(this.form))
          // 空行は登録せず、内容のいずれか項目に入力値がある行のみ登録する、上から順に採番
          this.formatContentItemList()
          // 登録用リクエスト発行
          document.getElementById('ifaReleaseNoteRegisterA007b').click()
        }
      })
    },
    // アップロード用リクエストのレスポンスハンドラー
    // ファイルがない時はスキップ
    responseHandlerIfaReleaseNoteRegisterA007a(response) {
      // 登録用リクエスト作成
      Object.assign(this.ifaReleaseNoteRegisterA007bRequestModel, new IfaReleaseNoteRegisterA007bRequestModel(this.form))
      // 詳細ファイル名を設定する
      this.ifaReleaseNoteRegisterA007bRequestModel.detailedFileName = response.dataList[0].detailedFileName ? response.dataList[0].detailedFileName : ''
      // 採番用リリースノートNoを設定する
      this.ifaReleaseNoteRegisterA007bRequestModel.releaseNoteNo = response.dataList[0].releaseNoteNo ? response.dataList[0].releaseNoteNo : ''
      // 空行は登録せず、内容のいずれか項目に入力値がある行のみ登録する、上から順に採番
      this.formatContentItemList()
      // 登録用リクエスト発行
      this.$nextTick(() => {
        document.getElementById('ifaReleaseNoteRegisterA007b').click()
      })
    },
    // 登録用リクエストのレスポンスハンドラー
    responseHandlerIfaReleaseNoteRegisterA007b() {
      // 自画面（ポップアップ）を閉じる
      this.onDialogClose()
      // リリースノート(社員用)画面のA002を呼び出す
      this.$emit('update-table')
    },
    // フォームリセット
    initForm() {
      // タイトル
      this.form.title = ''
      // 画面表示開始日
      this.form.displayedDate = ''
      // ファイルタイプ
      this.form.fileType = '0'
      // 詳細ファイルエリアクリア
      this.clearDetailedFileArea()
      this.form.contentItemList = []
      // 静的ルールバリデーションクリア
      if (this.$refs['formRef']) { this.$refs['formRef'].clearValidate() }
      // 動的ルールバリデーションクリア
      if (this.$refs['formRefDynamicRule']) { this.$refs['formRefDynamicRule'].clearValidate() }
      // 内容リストは初期状態は5行分の入力欄を表示する
      this.pushContentItemList(this.form.initDisplayCount)
    },
    // 詳細ファイルエリアクリア
    clearDetailedFileArea() {
      // PDFサイズ
      this.form.pdfSize = ''
      // 向き
      this.form.pdfDirection = ''
      // ファイル選択初期化
      if (this.$refs['detailedFile']) { this.$refs['detailedFile'].clearFiles() }
      // 添付ファイル
      this.form.detailedFile = null
    },
    // 登録.削除処理
    doOkOperation() {
      if (this.form.dialogType === '1') {
        // 登録処理
        this.doOperationA003()
      } else {
        // 削除処理
        this.doOperationA007()
      }
    },
    // アップロード詳細ファイル削除
    handleRemoveFile() {
      this.form.detailedFile = null
    },
    // 画面表示開始日: 必須入力バリデーション
    displayedDateValidator(rule, value, callback) {
      if (this.form.displayedDate.length === 0) {
        callback(getMessage('errors.required', ['画面表示開始日']))
      } else {
        callback()
      }
    },
    // 詳細ファイル名: 有効拡張子バリデーション
    detailedFileTypeValidator(rule, value, callback) {
      if (this.form.fileType === '0' && !['png', 'jpg', 'jpeg'].some(key => this.form.detailedFileName.includes(key))) {
        callback(getMessage('errors.cmn.file.invalidExtension', ['「.png」「.jpeg(.jpg)」']))
      } else if (this.form.fileType === '1' && !this.form.detailedFileName.endsWith('.pdf')) {
        callback(getMessage('errors.cmn.file.invalidExtension', ['.pdf']))
      } else {
        callback()
      }
    },
    // 日付を文字列に変換する
    formatDate(date, f = false) {
      date = date || new Date()
      return date.getFullYear() + '/' +
        (('0' + (date.getMonth() + 1)).slice(-2)) + '/' +
        ('0' + date.getDate()).slice(-2)
    },
    // 翌年の12/31を取得する
    getNextYearLastDay() {
      const currentYear = new Date().getFullYear()
      const nextYear = currentYear + 1
      const lastDayOfYear = new Date(nextYear, 11, 31)
      const formattedDate = this.formatDate(lastDayOfYear)
      return formattedDate
    },
    // 内容リストカウント行数追加
    pushContentItemList(count) {
      this.form.contentItemList.push(...Array.from({ length: count }, () => ({
        menuName: '',
        screenName: '',
        content: ''
      })))
    },
    // 内容のいずれか項目に入力値がある行保留、上から順に採番
    formatContentItemList() {
      this.ifaReleaseNoteRegisterA007bRequestModel.contentItemList = this.form.contentItemList ? this.form.contentItemList
        .filter(item => item.menuName || item.screenName || item.content)
        .map((item, index) => ({
          releaseNoteContentNo: index + 1,
          ...item
        })) : []
    },
    // 動的ルールを削除する
    deleteDynamicRules() {
      const regex = /^menuName\d+|screenName\d+|content\d+/
      for (const key in this.dynamicRules) {
        if (regex.test(key)) {
          delete this.dynamicRules[key]
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.input-table {
  width:98%;
  margin: 10px;
  border-collapse: collapse;
  color: rgb(72,116,173);
  border:1px solid #d8e8fa
}
.table-label {
  background-color: #dfdfdf;
  border: 1px solid #c5c5c5;
  text-align: right;
}
.title :deep(.el-form-item__label){
  display: none;
}
.table-body {
  border: 1px solid #c5c5c5;
  padding: 0.5rem 0;
}
:deep(.table-label) .el-form-item {
  margin: 0;
}
:deep(.table-body) .el-form-item {
  margin: 0.0rem 0.5rem;
}
:deep(.table-body) .ifa-button {
  margin: 0.0rem 0.5rem;
}
:deep(.table-body_document) .el-form-item {
  margin: 0;
}
:deep(.table-body_document) .ifa-button {
  font-size: 12px;
}
:deep(.adjust_font_size) .ifa-button {
  font-size: 11px;
}
:deep(.el-form-item__error){
    margin-top: 3px;
}
.file_select{
  :deep( > div) {
   display: flex;
   align-items: center
  }
  :deep(.el-button){
    margin: 0;
  }
  :deep(.el-upload-list){
    display: inline-flex;
    position: relative;
    width: 100%;
    max-width: 300px;
    margin-top: 0;
  }
  :deep(.el-upload-list__item){
    display: inline-flex;
    margin: 0;
  }
}
:deep(.ifa-file-select) .el-form-item__content {
  margin-bottom: 0rem;
  display: flex !important;
  justify-content: center !important;
  align-items: center !important;
}
:deep(.confirm-dialog) .form-button__wrapper {
  align-items: center;
  justify-content: center;
}
:deep(.confirm-dialog) .el-row {
  text-align: center;
  font-size: 15px;
}
:deep(.confirm-dialog) .caption-container {
  align-items: center;
  justify-content: center;
}
:deep(.confirm-dialog) .ifa-button {
  width: 100px
}
:deep(.confirm-dialog) .caption-container {
  border: none !important;
  background-color: transparent !important;
}
.required-mark {
  color: red;
  margin-right: 2px;
}
.register_file_input{
    line-height: 0;
  :deep(.el-input__wrapper){
    width: 0;
    height: 0;
    padding: 0px;
    border: 0;
  }
  :deep(.el-form-item){
    padding: 0px;
  }
  :deep(.el-form-item__error){
    margin-left: 5px;
    margin-top: 4px;
    width: fit-content;
    white-space: pre-line;
  }
  :deep(.el-form-item__label){
    display: none;
  }
}
.message-error-position{
  :deep(.el-form-item__error){
    margin-left: 5px;
  }
}
.text-box:deep(.el-form-item){
    margin: 0 !important;
    padding: 0 5px;
}
.text-box-file{
  :deep(.el-form-item__content){
    display: flex;
    flex-direction: column;
    align-items: flex-start;
 }
}
:deep(.el-date-editor.el-input), :deep(.el-date-editor.el-input__wrapper) {
  height: auto;
}
:deep(.el-textarea__inner) {
  line-height: 1.3;
  color: #000;
  font-size: 16px;
  font-family: element-icons!important;
  border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color)) inset;
  --el-input-focus-border-color: var(--el-color-primary);
}
.date-font{
  color: #000;
  padding-left: 1.5rem;
  letter-spacing: 1px;
}
.line-button {
  text-align: right;
}
.line-button :deep(.el-button){
  height: 30px;
  margin: 0.6rem 0rem 0.1rem 0rem !important;
}
.inner-table{
  border-collapse: collapse;
}
.inner-table th{
  color: #fff;
  background-color: #676b74;
  font-weight: 400;
  font-size: 14px;
  line-height: normal;
  border: 1px solid #c5c5c5;
}
.inner-table td{
  border: 1px solid #c5c5c5;
}
.menu-name :deep(.el-form-item__error){
  width: 250px;
  word-wrap: break-word;
  word-break: break-word;
  white-space: normal;
}
.screen-name :deep(.el-form-item__error){
  width: 150px;
  word-wrap: break-word;
  word-break: break-word;
  white-space: normal;
}
.content :deep(.el-form-item__error){
  width: 670px;
  word-wrap: break-word;
  word-break: break-word;
  white-space: normal;
}
.file_item{
  margin-bottom: 0.2rem;
}
.file_item :deep(.el-form-item__label){
  width: auto !important;
}
file_item :deep(.el-form-item__content){
  display: flex;
}
.file_item_pdf :deep(.el-form-item__content){
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.file_item_pdf :deep(.el-form-item__error){
  margin-top: 4px;
}
.full-name-width :deep(.el-upload-list__item){
  min-width: max-content;
}
.full-name-width :deep(.el-upload-list__item-file-name){
  min-width: max-content;
  padding-right: 20px;
}
.dialog-loading :deep(.el-loading-spinner) {
  display: none !important;
}
</style>
