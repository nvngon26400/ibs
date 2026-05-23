<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      @open="onShow"
    >
      <!-- メイン -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <div style="width:97%; text-align: right; margin: 10px;">
            <ifa-button
              text="リセット"
              width="110"
              color="secondary"
              action-type="originalAction"
              @app-action-handler="resetForm"
            ></ifa-button>
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
            ref="form"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <table class="input-table">
              <tr>
                <td
                  class="table-label"
                  style="margin-bottom: 3px;"
                >
                  <el-form-item>
                    <span class="required-mark">*</span>閲覧者
                  </el-form-item>
                </td>
                <td class="table-body message-error-position">
                  <el-form-item prop="viewerSetting">
                    <span v-if="form.viewerSetting && $_getCodeValue('PORTAL_USER_NOTIFICATION_REFERENCE_SCOPE', 1, form.viewerSetting)">
                      <span style="margin-right: 1rem; margin-left: 0.5rem; color: #18181A; font-weight: bold">
                        {{ form.viewerSetting ? $_getCodeValue('PORTAL_USER_NOTIFICATION_REFERENCE_SCOPE', 1, form.viewerSetting) : '' }}
                      </span>
                    </span>
                    <ifa-button
                      text="閲覧者設定"
                      small
                      width="100"
                      color="primary"
                      style="padding-left: 0;"
                      action-type="originalAction"
                      @app-action-handler="openViewerSettingA004"
                    ></ifa-button>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>タイトル
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="table-body-margin-left">
                    <ifa-input-text
                      id="title"
                      v-model="form.title"
                      prop="title"
                      size="small"
                      style="width: 650px;"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>カテゴリ
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-input-select
                      v-model="form.largeCategory"
                      size="small"
                      code-list-id="original"
                      model-value="-1"
                      prop="largeCategory"
                      :original-list="largeCategoryList"
                    ></ifa-input-select>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">内容</td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-input-text
                      id="contents"
                      v-model="form.content"
                      prop="content"
                      type="textarea"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :rows="3"
                      :domain="IfaText2000DomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                  <div
                    style="float: right; margin-right: 0.5rem; font-size: 13px"
                    :style="wordCount < 2000 ? 'color: #18181A;' : 'color: red;'"
                  >
                    {{ wordCount }}/2000
                  </div>
                </td>
              </tr>
              <tr>
                <td class="table-label">URL</td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-input-text
                      id="url"
                      v-model="form.url"
                      size="small"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      :domain="IfaUrlDomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                  <el-form-item>
                    <ifa-input-text
                      id="urlComment"
                      v-model="form.urlComment"
                      prop="urlComment"
                      size="small"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                    <label
                      class="h_label"
                      style="color:#339900"
                    >（コメント）</label>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label ifa-file-select">登録ファイル1</td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-file-select
                      ref="select1"
                      class="file_select"
                      text="ファイルを選択"
                      color="secondary"
                      small
                      width="130"
                      msg-title="ファイルを選択"
                      :show-file-list="true"
                      @change="handleChangeAttachFilesSelection($event, 0)"
                      @before-remove="handleRemoveFile(0)"
                    ></ifa-file-select>
                    <label
                      v-if="!form.attachFileList[0]"
                      style="font-weight: normal"
                    >
                      ファイルが選択されていません
                    </label>
                    <div
                      v-if="form.attachFileList[0]"
                      class="register_file_input text-box text-box-file message-error-position"
                    >
                      <ifa-input-text
                        id="registerFileName1"
                        v-model="form.registerFile1"
                        prop="registerFile1"
                        original-screen-id="SUB0501_01-02_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item><br>
                  <el-form-item>
                    <ifa-input-text
                      id="fileComment1"
                      v-model="form.fileComment1"
                      prop="fileComment1"
                      size="small"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                    <label
                      class="h_label"
                      style="color:#339900"
                    >（コメント）</label>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">登録ファイル2</td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-file-select
                      ref="select2"
                      class="file_select"
                      text="ファイルを選択"
                      color="secondary"
                      small
                      width="130"
                      msg-title="ファイルを選択"
                      :show-file-list="true"
                      @change="handleChangeAttachFilesSelection($event, 1)"
                      @before-remove="handleRemoveFile(1)"
                    ></ifa-file-select>
                    <label
                      v-if="!form.attachFileList[1]"
                      style="font-weight: normal"
                    >
                      ファイルが選択されていません
                    </label>
                    <div
                      v-if="form.attachFileList[1]"
                      class="register_file_input text-box text-box-file message-error-position"
                    >
                      <ifa-input-text
                        id="registerFileName2"
                        v-model="form.registerFile2"
                        prop="registerFile2"
                        original-screen-id="SUB0501_01-02_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item><br>
                  <el-form-item>
                    <ifa-input-text
                      id="fileComment2"
                      v-model="form.fileComment2"
                      prop="fileComment2"
                      size="small"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                    <label
                      class="h_label"
                      style="color:#339900"
                    >（コメント）</label>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">登録ファイル3</td>
                <td class="table-body">
                  <el-form-item>
                    <ifa-file-select
                      ref="select3"
                      class="file_select"
                      text="ファイルを選択"
                      color="secondary"
                      small
                      width="130"
                      msg-title="ファイルを選択"
                      :show-file-list="true"
                      @change="handleChangeAttachFilesSelection($event, 2)"
                      @before-remove="handleRemoveFile(2)"
                    ></ifa-file-select>
                    <label
                      v-if="!form.attachFileList[2]"
                      style="font-weight: normal"
                    >
                      ファイルが選択されていません
                    </label>
                    <div
                      v-if="form.attachFileList[2]"
                      class="register_file_input text-box text-box-file message-error-position"
                    >
                      <ifa-input-text
                        id="registerFileName3"
                        v-model="form.registerFile3"
                        prop="registerFile3"
                        original-screen-id="SUB0501_01-02_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item><br>
                  <el-form-item>
                    <ifa-input-text
                      id="fileComment3"
                      v-model="form.fileComment3"
                      prop="fileComment3"
                      size="small"
                      style="width: 650px"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText127DomainModel"
                    ></ifa-input-text>
                    <label
                      class="h_label"
                      style="color:#339900"
                    >（コメント）</label>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <span class="required-mark">*</span>ディスクレーマー
                </td>
                <td class="table-body">
                  <el-form-item>
                    <el-switch
                      v-model="form.disclaimer"
                      active-text="要"
                      inactive-text="不要"
                      active-color="#1989fa"
                    ></el-switch>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <span class="required-mark">*</span>既読管理
                </td>
                <td class="table-body">
                  <el-form-item>
                    <el-switch
                      v-model="form.readManage"
                      active-text="要"
                      inactive-text="不要"
                      active-color="#1989fa"
                    ></el-switch>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>メール送信
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item>
                    <el-switch
                      v-model="form.subjectSendFlag"
                      active-text="送信する"
                      inactive-text="送信しない"
                      active-color="#1989fa"
                    ></el-switch>
                    <label
                      class="h_label"
                      style="color:#FF0000; margin-left: 2rem;"
                    >
                      ※新規登録時のみメール送信可能です。送信する場合は件名が必須入力となります。
                    </label>
                  </el-form-item><br>
                  <el-form-item>
                    <span style="margin-right: 1rem; margin-left: 0.5rem; color: #18181A;">
                      <label>件名</label>
                    </span>
                    <ifa-input-text
                      id="subject"
                      v-model="form.subject"
                      prop="subject"
                      size="small"
                      style="width: 650px"
                      :disabled="!form.subjectSendFlag"
                      class="ifa-input__text-default"
                      original-screen-id="SUB0501_01-02_1"
                      :domain="IfaText255InputDomainModel"
                    ></ifa-input-text>
                  </el-form-item><br>
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
            :form="formRef"
            action-type="originalAction"
            @app-action-handler="comfirmHandlerA007"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 情報登録閲覧者設定 ダイアログ -->
    <ifa-info-register-viewer-setting
      ref="ifaInfoRegisterViewerSetting"
      :is-visible="viewerSettingVisible"
      operation="insert"
      :selected-info="form"
      @regist-viewer-setting="a005Action($event)"
      @close-modal="viewerSettingVisible = false"
    ></ifa-info-register-viewer-setting>

    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogComfirmVisible"
      title="登録の確認"
      :message="confirmMessage"
      class="confirm-dialog"
      style="width: 600px"
      @close-modal-ok="doOperationA008"
      @close-modal-cancel="dialogComfirmVisible = false"
    ></ifa-ok-cancel-dialog>

    <!-- アップロードリクエスター -->
    <ifa-requester
      id="ifaInfoNewRegisterRegisterA008a"
      action-id="SUB0501_01-02_1#A008A"
      action-type="uploadAction"
      :request-model="ifaInfoNewRegisterA008aRequestModel"
      @response-handler="responseHandlerIfaInfoNewRegisterA008a($event)"
    ></ifa-requester>
    <!-- 登録リクエスター -->
    <ifa-requester
      id="ifaInfoNewRegisterRegisterA008b"
      action-id="SUB0501_01-02_1#A008B"
      action-type="requestAction"
      :request-model="ifaInfoNewRegisterA008bRequestModel"
      @response-handler="responseHandlerIfaInfoNewRegisterA008b($event)"
    ></ifa-requester>

  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaInfoRegisterViewerSetting from './IfaInfoRegisterViewerSetting'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import IfaUrlDomainModel from '@/resource/domain/IfaUrlDomainModel.json'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText255InputDomainModel from '@/resource/domain/IfaText255InputDomainModel.json'
import { IfaInfoNewRegisterFormModel } from './js/IfaInfoNewRegisterFormModel'
import { IfaInfoNewRegisterA008bRequestModel } from './js/IfaInfoNewRegisterA008bRequestModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    IfaInfoRegisterViewerSetting,
    IfaOkCancelDialog
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    selectedInfo: {
      type: Object,
      required: false,
      default: null
    },
    operation: {
      type: String,
      required: false,
      default: 'insert'
    },
    info: { type: Object, required: true }
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
      IfaUrlDomainModel: IfaUrlDomainModel,
      IfaText2000DomainModel: IfaText2000DomainModel,
      IfaText255InputDomainModel: IfaText255InputDomainModel,
      formRef: {},
      form: new IfaInfoNewRegisterFormModel(),
      ifaInfoNewRegisterA008aRequestModel: {},
      ifaInfoNewRegisterA008bRequestModel: {},
      viewerSettingData: {},
      largeCategoryList: [],
      wordCount: 0,
      infoTypeListVisible: false,
      viewerSettingVisible: false,
      dialogComfirmVisible: false,
      categoryNameListMessage: '',
      confirmMessage: '',
      rules: {
        viewerSetting: { required: true, trigger: 'blur', validator: this.viewerSettingValidator },
        title: { required: true, trigger: 'blur', validator: this.titleValidator },
        subject: { required: false, trigger: 'blur', validator: this.mailValidator },
        largeCategory: { required: true, trigger: 'blur', validator: this.largeCategoryValidator }
      },
      infoTypeList: [
        { value: '0', label: '営業サポートツール', color: 'secondary' },
        { value: '1', label: '業務関連資料', color: 'secondary' },
        { value: '2', label: 'コンプライアンス関連', color: 'secondary' },
        { value: '3', label: 'マイサポート', color: 'secondary' }
      ]
    }
  },
  watch: {
    isVisible: function() {
      if (this.isVisible) {
        this.resetForm()
      }
    },
    'form.content': function() {
      this.wordCount = this.form.content.length
    },
    'form.subjectSendFlag': function() {
      if (this.form.subjectSendFlag) {
        this.form.subject = this.form.title
      } else {
        this.form.subject = ''
      }
    },
    'form.attachFileList': {
      handler: function() {
        this.form.registerFile1 = this.form.attachFileList[0] ? this.form.attachFileList[0].name : ''
        this.form.registerFile2 = this.form.attachFileList[1] ? this.form.attachFileList[1].name : ''
        this.form.registerFile3 = this.form.attachFileList[2] ? this.form.attachFileList[2].name : ''
      },
      deep: true
    }
  },
  mounted() {
    this.formRef = this.$refs.form
  },
  methods: {
    // 初期化
    onShow() {
      this.largeCategoryList = this.setLargeCategoryList(this.info.newNotificationCategoryList)
      this.$refs['form'].clearValidate()
      this.form.attachFileList = [null, null, null]
    },
    // 情報登録閲覧者設定ダイアログ表示
    openViewerSettingA004() {
      this.viewerSettingVisible = true
    },
    // 情報閲覧者設定
    a005Action(viewerSettingData) {
      // 閲覧者
      this.form.viewerSetting = viewerSettingData.viewerSetting
      // 設定フラグ
      this.form.settingFlag = viewerSettingData.settingFlag
      // お知らせ参照権限リスト
      if (viewerSettingData.viewerSetting === '1') {
        // 閲覧者が　"1"　の場合（1:全担当）は"3"～"9"を設定
        this.form.notificationReferenceAuthorityList = ['3', '4', '5', '6', '7', '8', '9']
      } else {
        this.form.notificationReferenceAuthorityList = viewerSettingData.selectedAuthority
      }
      // 個別担当者リスト.ユーザID
      this.form.individualRepList = viewerSettingData.repSelectList
    },
    // 確認ダイアログ表示
    comfirmHandlerA007() {
      this.addhttpToUrl()
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 確認ダイアログの「カテゴリ名」の設定
          const largeCategoryName = this.largeCategoryList.filter(item => item.key === this.form.largeCategory)[0].value
          this.confirmMessage = '『' + largeCategoryName + '』を登録します。よろしいですか？'
          // 確認ダイアログ表示
          this.dialogComfirmVisible = true
        } else {
          return false
        }
      })
    },
    // 登録・更新処理
    doOperationA008() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$nextTick(() => {
            // 確認ダイアログ閉じる
            this.dialogComfirmVisible = false
            // 登録ファイルがある場合のみアップロード用リクエストを発行
            if (this.form.attachFileList.every(item => item === null)) {
              // 登録用リクエスト発行
              this.ifaInfoNewRegisterA008bRequestModel = this.setIfaInfoNewRegisterA008bRequestModel(null)
              document.getElementById('ifaInfoNewRegisterRegisterA008b').click()
            } else {
              // アップロード用リクエスト発行
              this.ifaInfoNewRegisterA008aRequestModel = this.setIfaInfoNewRegisterA008aRequestModel()
              document.getElementById('ifaInfoNewRegisterRegisterA008a').click()
            }
          })
        } else {
          return false
        }
      })
    },
    // アップロード用リクエストのレスポンスハンドラー
    // ファイルがない時はスキップ
    responseHandlerIfaInfoNewRegisterA008a(response) {
      if (response.returnCode === 'errors.processingFailed') {
        const categoryIdList = this.getCategoryIdList(this.form.largeCategory)
        notifyWrapper({
          title: this.$store.getters.pageInfo?.label ?? '',
          message: `${categoryIdList.find(e => e.requiredFlag === '1')?.categoryName}の登録が失敗しました。`,
          type: 'error'
        })
      } else {
        // 登録用リクエスト作成
        this.ifaInfoNewRegisterA008bRequestModel = this.setIfaInfoNewRegisterA008bRequestModel(response)
        // 登録用リクエスト発行
        this.$nextTick(() => {
          document.getElementById('ifaInfoNewRegisterRegisterA008b').click()
        })
      }
    },
    // 登録用リクエストのレスポンスハンドラー
    responseHandlerIfaInfoNewRegisterA008b() {
      this.$refs['select1'].clearFiles()
      this.$refs['select2'].clearFiles()
      this.$refs['select3'].clearFiles()
      this.form.attachFileList = [null, null, null]
      // ⑫	親画面(「画面定義書_SUB0501_01-01_情報登録照会.xlsx」)のX001を呼び出す。
      this.updateTable()
      const categoryIdList = this.getCategoryIdList(this.form.largeCategory)
      notifyWrapper({
        title: 'インフォメーション登録',
        message: `${categoryIdList.find(e => e.requiredFlag === '1')?.categoryName}の登録が完了しました。`,
        type: 'success',
        dangerouslyUseHTMLString: true
      })
    },
    // ファイルアップロード用リクエスト作成
    setIfaInfoNewRegisterA008aRequestModel() {
      return this.form.attachFileList.map((file, index) => {
        return {
          filename: `file${index + 1}`,
          file: file
        }
      })
    },
    // 登録用リクエスト作成
    setIfaInfoNewRegisterA008bRequestModel(fileList) {
      // カテゴリIDリスト
      this.form.categoryIdList = this.getCategoryIdList(this.form.largeCategory)
      // アップロードファイル名
      if (fileList) {
        this.form.registerFile1 = fileList.dataList[0].registerFile1
        this.form.registerFile2 = fileList.dataList[0].registerFile2
        this.form.registerFile3 = fileList.dataList[0].registerFile3
      }
      // 登録用リクエスト設定
      const individualRepListTemp = this.form.individualRepList?.length > 0 ? this.form.individualRepList.map(item => item.userId) : []
      return new IfaInfoNewRegisterA008bRequestModel(
        {
          ...this.form,
          individualRepList: individualRepListTemp
        }
      )
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$refs['ifaInfoRegisterViewerSetting'].refresh()
      this.$emit('close-modal')
    },
    // テーブル更新、ダイアログ閉じる
    updateTable() {
      this.onDialogClose()
      this.$emit('update-table')
    },
    // 大カテゴリーリスト設定
    setLargeCategoryList(newNotificationCategoryList) {
      let largeCategoryList = []
      if (newNotificationCategoryList) {
        largeCategoryList = newNotificationCategoryList
          .filter(item => item.requiredFlag === '1')
          .map(item => ({
            key: item.t9nInfoCat,
            value: (item.t9nInfoCat === '-1' ? ' ' : item.t9nName),
            flag: item.requiredFlag
          }))
      }

      return largeCategoryList
    },
    // 添付ファイル
    handleChangeAttachFilesSelection(file, index) {
      this.form.attachFileList[index] = file
    },
    handleRemoveFile(index) {
      this.form.attachFileList[index] = null
    },
    // URLに"http://"を付与
    addhttpToUrl() {
      if (this.form.url && !this.form.url.startsWith('http')) {
        this.form.url = 'http://' + this.form.url
      }
    },
    // フォームリセット
    resetForm() {
      // form初期化
      this.form.viewerSetting = ''
      this.form.title = ''
      this.form.largeCategory = '-1'
      this.form.content = ''
      this.form.url = ''
      this.form.urlComment = ''
      this.form.registerFile1 = ''
      this.form.fileComment1 = ''
      this.form.registerFile2 = ''
      this.form.fileComment2 = ''
      this.form.registerFile3 = ''
      this.form.fileComment3 = ''
      this.attachFileList = [null, null, null]
      this.form.disclaimer = true
      this.form.readManage = true
      this.form.subjectSendFlag = false
      this.form.subject = ''
      this.form.notificationReferenceAuthorityList = []
      this.form.individualRepList = []
      this.form.settingFlag = false
      // ファイル選択初期化
      if (this.$refs['select1']) { this.$refs['select1'].clearFiles() }
      if (this.$refs['select2']) { this.$refs['select2'].clearFiles() }
      if (this.$refs['select3']) { this.$refs['select3'].clearFiles() }
      this.form.attachFileList = [null, null, null]
      // 大カテゴリリスト初期化
      this.largeCategoryList = this.setLargeCategoryList(this.info.newNotificationCategoryList)
      // バリデーションクリア
      if (this.$refs['form']) { this.$refs['form'].clearValidate() }
    },
    // カテゴリIDリストを設定
    getCategoryIdList(largeCategory) {
      return [
        ...this.largeCategoryList
          .filter(item => item.key === largeCategory)
          .map(item => ({
            categoryId: item.key,
            requiredFlag: item.flag,
            categoryName: item.value
          }))
      ]
    },
    // ***** 以下バリデータ *****
    mailValidator(rule, value, callback) {
      if (this.form.subjectSendFlag) {
        if (this.form.subject === '') {
          callback('件名を入力してください。')
          return
        }
      }
      // OK
      callback()
    },
    viewerSettingValidator(rule, value, callback) {
      if (this.form.viewerSetting === '') {
        callback('閲覧者を入力して下さい。')
        return
      }
      callback()
    },
    titleValidator(rule, value, callback) {
      if (this.form.title === '') {
        callback('タイトルを入力してください。')
        return
      }
      // OK
      callback()
    },
    largeCategoryValidator(rule, value, callback) {
      if (this.form.largeCategory === '' || this.form.largeCategory === '-1') {
        callback('カテゴリを選択してください。')
        return
      }
      // OK
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
._bold_black_s {
  font-size: 14px;
  font-weight: 700;
  color: #606266;
  margin-left: 3px;
}
.clickable {
  cursor: pointer;
  &:hover {
    background: rgba(0, 0, 0, .066)
  }
}
.header-icon {
  font-size:1rem;
  margin-left: 0.5rem;
}
.ifa-input__text-default {
  width: 700px;
}
.input-table {
  width:97%;
  margin: 10px;
  border-collapse: collapse;
  color: rgb(72,116,173);
  text-shadow:0 1px 0 #fff;
  border:1px solid #d8e8fa
}
.table-label {
  width:180px;
  font-size: 14px;
  font-weight: bold;
  color: #18181A;
  padding: 0 1rem;
  background-color: #dfdfdf;
  border: 1px solid #c5c5c5;
  text-shadow: none;
  text-align: right;
}
.table-body {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
  padding: 0.5rem 0;
}
.table-body-margin-left {
  margin-left: 0
}
.table-body_document {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
  padding: 0.5rem 0;
}
.chcek_box {
  width:240px;
  margin-top:1rem;
}
:deep(.table-label) .el-form-item {
  margin: 0;
}
:deep(.table-body) .el-form-item {
  margin: 0.3rem 1rem 0.3rem 0.5rem;
}
:deep(.table-body) .el-switch {
  margin: 0.3rem 1rem 0.3rem 0.5rem;
}
:deep(.table-body) .ifa-button {
  margin: 0.3rem 1rem 0.3rem 0.5rem;
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
.close-button {
  margin-bottom: 1rem;
  text-align: right;
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
    position: relative;
    width: 100%;
    max-width: 300px;
    margin-top: 0;
  }
  :deep(.el-upload-list__item){
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
.h_label {
  font-size: 14px;
  margin-left: 1rem;
}
:deep(.el-switch.is-checked) .el-switch__core::after {
  background-color: #FFF
}
:deep(.el-switch__label) {
  color: #18181A;
}
:deep(.el-switch__label.is-active) {
  color: #005FCC;
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
  }
}
.message-error-position{
  :deep(.el-form-item__error){
    margin-left: 5px;
  }
}
.optional_category{
  :deep(.el-form-item__error){
    margin-top: 5px;
    margin-left: 20px;
    position: static;
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
.message-error-position{
  :deep(.el-form-item__error){
    margin-left: 5px;
  }
}
:deep(.el-form-item__error){
    margin-top: 3px;
}
</style>
