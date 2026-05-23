<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.pageTitle.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1200px"
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
              id="btnDisplay"
              name="btnDisplay"
              text="リセット"
              width="110"
              color="secondary"
              :request-model="ifaInfoUpdateA002RequestModel"
              action-id="SUB0501_01-03_1#A002"
              action-type="requestAction"
              @response-handler="responseHandlerIfaInfoUpdateResetA002($event)"
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
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>閲覧者
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item
                    class="message-error-position"
                    prop="corReferenceCondition"
                  >
                    <span style="margin-right: 1rem; color: #18181A; font-weight: bold">
                      {{ form.corReferenceCondition ? $_getCodeValue('PORTAL_USER_NOTIFICATION_REFERENCE_SCOPE', 1, form.corReferenceCondition) : '-' }}
                    </span>
                    <ifa-button
                      class="viewer-setting-button"
                      text="閲覧者設定"
                      small
                      width="100"
                      color="primary"
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
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="title"
                      v-model="form.title"
                      prop="title"
                      size="small"
                      style="width: 650px"
                      original-screen-id="SUB0501_01-03_1"
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
                  <el-form-item class="text-box">
                    <ifa-input-select
                      v-model="form.largeCategory"
                      size="small"
                      code-list-id="original"
                      prop="largeCategory"
                      :original-list="largeCategoryList"
                    ></ifa-input-select>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">内容</td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="contents"
                      v-model="form.contents"
                      type="textarea"
                      :rows="5"
                      style="width: 650px"
                      prop="contents"
                      original-screen-id="SUB0501_01-03_1"
                      :domain="IfaText2000DomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                  <div
                    style="float: right; margin: 0.5rem  0.5rem 0 0; font-size: 13px"
                    :style="wordCount !== 2000 ? 'color: #18181A;' : 'color: red;'"
                  >
                    {{ wordCount }}/2000
                  </div>
                </td>
              </tr>
              <tr>
                <td class="table-label">URL</td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="url"
                      v-model="form.url"
                      size="small"
                      style="width: 650px"
                      prop="url"
                      :domain="IfaUrlDomainModel"
                    ></ifa-input-text>
                  </el-form-item>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="urlComment"
                      v-model="form.urlComment"
                      size="small"
                      style="width: 650px"
                      prop="urlComment"
                      original-screen-id="SUB0501_01-03_1"
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
                <td class="table-label">登録ファイル1</td>
                <td class="table-body">
                  <el-form-item
                    class="text-box text-box-file message-error-position"
                  >
                    <div style="display: flex; flex-wrap: nowrap">
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
                      <span
                        v-if="form.fileDeleteName1"
                        style="margin-left: 1rem; font-weight: normal; cursor: pointer; text-decoration: underline;"
                        @click="a013Action"
                      >
                        {{ form.attachFileName1 }}
                      </span>
                    </div>
                    <div
                      v-if="form.attachFileList[0]"
                      class="register_file_input"
                    >
                      <ifa-input-text
                        id="registerFileName1"
                        v-model="form.registerFileName1"
                        prop="registerFileName1"
                        original-screen-id="SUB0501_01-03_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="fileComment1"
                      v-model="form.attachFileComment1"
                      size="small"
                      style="width: 650px"
                      prop="attachFileComment1"
                      original-screen-id="SUB0501_01-03_1"
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
                  <el-form-item
                    class="text-box text-box-file message-error-position"
                  >
                    <div style="display: flex; flex-wrap: nowrap">
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
                      <span
                        v-if="form.fileDeleteName2"
                        style="margin-left: 1rem; font-weight: normal; cursor: pointer; text-decoration: underline;"
                        @click="a014Action"
                      >
                        {{ form.attachFileName2 }}
                      </span>
                    </div>
                    <div
                      v-if="form.attachFileList[1]"
                      class="register_file_input"
                    >
                      <ifa-input-text
                        id="registerFileName2"
                        v-model="form.registerFileName2"
                        prop="registerFileName2"
                        original-screen-id="SUB0501_01-03_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="fileComment2"
                      v-model="form.attachFileComment2"
                      size="small"
                      prop="attachFileComment2"
                      style="width: 650px"
                      original-screen-id="SUB0501_01-03_1"
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
                <td class="table-label">登録ファイル3 </td>
                <td class="table-body">
                  <el-form-item
                    class="text-box text-box-file"
                  >
                    <div style="display: flex; flex-wrap: nowrap">
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
                      <span
                        v-if="form.fileDeleteName3"
                        style="margin-left: 1rem; font-weight: normal; cursor: pointer; text-decoration: underline;"
                        @click="a015Action"
                      >
                        {{ form.attachFileName3 }}
                      </span>

                    </div>
                    <div
                      v-if="form.attachFileList[2]"
                      class="register_file_input"
                    >
                      <ifa-input-text
                        id="registerFileName3"
                        v-model="form.registerFileName3"
                        prop="registerFileName3"
                        original-screen-id="SUB0501_01-03_1"
                        :domain="IfaText127DomainModel"
                      ></ifa-input-text>
                    </div>
                  </el-form-item>
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="fileComment3"
                      v-model="form.attachFileComment3"
                      size="small"
                      prop="attachFileComment3"
                      style="width: 650px"
                      original-screen-id="SUB0501_01-03_1"
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
                  <el-form-item
                    class="message-error-position"
                    prop="disclaimer"
                  >
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
                  <el-form-item
                    class="message-error-position"
                    prop="corReadFlg"
                  >
                    <el-switch
                      v-model="form.corReadFlg"
                      active-text="要"
                      inactive-text="不要"
                      active-color="#1989fa"
                    ></el-switch>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>メール送信</el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item>
                    <el-switch
                      v-model="form.mailSend"
                      active-text="送信する"
                      inactive-text="送信しない"
                      active-color="#1989fa"
                      :disabled="true"
                    ></el-switch>
                    <label
                      style="margin-left: 2rem; color: red"
                    >
                      ※新規登録時のみメール送信可能です。送信する場合は件名が必須入力となります。
                    </label>
                  </el-form-item>
                  <el-form-item class="text-box">
                    <span style="margin-right: 1rem; color: #18181A;">
                      <label>件名</label>
                    </span>
                    <ifa-input-text
                      id="subject"
                      v-model="form.title"
                      size="small"
                      :disabled="true"
                      style="width: 650px"
                      :domain="IfaText255DomainModel"
                    ></ifa-input-text>
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
            text="更新"
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

    <ifa-info-register-viewer-setting
      ref="ifaInfoRegisterViewerSetting"
      :is-visible="viewerSettingVisible"
      operation="update"
      :selected-info="form"
      :param-data="viewerSettingData"
      @regist-viewer-setting="a005Action($event)"
      @close-modal="viewerSettingVisible = false"
    ></ifa-info-register-viewer-setting>
    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogComfirmVisible"
      :title="titleMessage"
      :message="confirmMessage"
      @close-modal-ok="doOperationA008"
      @close-modal-cancel="dialogComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- アップロードリクエスター -->
    <ifa-requester
      id="ifaInfoUpdateA008a"
      action-id="SUB0501_01-03_1#A008A"
      action-type="uploadAction"
      :request-model="ifaInfoUpdateA008aRequestModel"
      @response-handler="responseHandlerIfaInfoUpdateA008a($event)"
    ></ifa-requester>
    <!-- 更新リクエスター -->
    <ifa-requester
      id="ifaInfoUpdateA008b"
      action-id="SUB0501_01-03_1#A008B"
      action-type="requestAction"
      :request-model="ifaInfoUpdateA008bRequestModel"
      @response-handler="responseHandlerIfaInfoUpdateA008b($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import IfaInfoRegisterViewerSetting from './IfaInfoRegisterViewerSetting'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import IfaUrlDomainModel from '@/resource/domain/IfaUrlDomainModel.json'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText127DomainModel from '@/resource/domain/IfaText127DomainModel.json'
import { IfaInfoUpdateA002RequestModel } from './js/IfaInfoUpdateA002RequestModel'
import { IfaInfoUpdateA008bRequestModel } from './js/IfaInfoUpdateA008bRequestModel'
import { IfaInfoUpdateFormModel } from './js/IfaInfoUpdateFormModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    IfaOkCancelDialog,
    IfaInfoRegisterViewerSetting
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
      IfaText255DomainModel: IfaText255DomainModel,
      IfaUrlDomainModel: IfaUrlDomainModel,
      IfaText2000DomainModel: IfaText2000DomainModel,
      IfaText127DomainModel: IfaText127DomainModel,
      formRef: {},
      form: new IfaInfoUpdateFormModel(),
      viewerSettingData: {},
      wordCount: 0,
      viewerSettingVisible: false,
      dialogComfirmVisible: false,
      titleMessage: '登録の確認',
      confirmMessage: '',
      rules: {
        corReferenceCondition: { required: true, trigger: 'blur', validator: this.corReferenceConditionValidator },
        title: { required: true, trigger: 'blur', validator: this.titleValidator },
        largeCategory: { required: true, trigger: 'blur', validator: this.largeCategoryValidator }
      },
      // 未登録個別担当者リスト
      unRegisteredIndividualRepList: []
    }
  },
  computed: {
    // 大カテゴリ
    largeCategoryList() {
      const newList = this.form.notificationCategoryList.filter(item => item.requiredFlag === '1').map(item => {
        return {
          key: item.t9nInfoCat,
          value: item.t9nName
        }
      })
      newList.unshift({
        key: '',
        value: ''
      })
      return newList
    },
    ifaInfoUpdateA002RequestModel() {
      return new IfaInfoUpdateA002RequestModel(this.selectedInfo)
    },
    ifaInfoUpdateA008aRequestModel() {
      return this.form.attachFileList.map((file, index) => {
        return {
          filename: `registerFile${index + 1}`,
          file: file
        }
      })
    },
    ifaInfoUpdateA008bRequestModel() {
      const notificationReferenceAuthorityListTemp = this.form.notificationReferenceAuthorityList?.length > 0 ? this.form.notificationReferenceAuthorityList : []
      // 新規に追加した担当者のみをセット
      const individualRepListTemp = this.unRegisteredIndividualRepList?.length > 0 ? this.unRegisteredIndividualRepList.map(item => item.userId) : []
      return new IfaInfoUpdateA008bRequestModel(
        {
          ...this.form,
          notificationReferenceAuthorityList: notificationReferenceAuthorityListTemp,
          individualRepList: individualRepListTemp
        }
      )
    }
  },
  watch: {
    'form.contents': function() {
      this.wordCount = this.form.contents ? this.form.contents.length : 0
    },
    'form.attachFileList': {
      handler: function() {
        this.form.registerFileName1 = this.form.attachFileList[0] ? this.form.attachFileList[0].name : ''
        this.form.registerFileName2 = this.form.attachFileList[1] ? this.form.attachFileList[1].name : ''
        this.form.registerFileName3 = this.form.attachFileList[2] ? this.form.attachFileList[2].name : ''
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
      this.form = Object.assign(this.form, this.formData)
      this.form.disclaimer = this.formData.disclaimer === '1'
      this.form.corReadFlg = this.formData.corReadFlg === '1'
      this.form.fileDeleteName1 = this.form.attachFileName1
      this.form.fileDeleteName2 = this.form.attachFileName2
      this.form.fileDeleteName3 = this.form.attachFileName3
      // 大カテゴリー資料種別1が選択された状態
      this.form.largeCategory = this.getLargeCategoryId(this.form.documentKindInput1)
      this.$refs['form'].clearValidate()
    },
    // リセット
    responseHandlerIfaInfoUpdateResetA002(response) {
      this.$refs['select1'].clearFiles()
      this.$refs['select2'].clearFiles()
      this.$refs['select3'].clearFiles()
      this.form = Object.assign(this.form, response.dataList[0])
      this.form.disclaimer = response.dataList[0].disclaimer === '1'
      this.form.corReadFlg = response.dataList[0].corReadFlg === '1'
      this.form.fileDeleteName1 = this.form.attachFileName1
      this.form.fileDeleteName2 = this.form.attachFileName2
      this.form.fileDeleteName3 = this.form.attachFileName3
      this.form.attachFileList = [null, null, null]
      // 大カテゴリー資料種別1が選択された状態
      this.form.largeCategory = this.getLargeCategoryId(this.form.documentKindInput1)
      this.$refs['form'].clearValidate()
    },
    // Information閲覧者設定ダイアログ表示
    openViewerSettingA004() {
      // お知らせID
      this.viewerSettingData.notificationId = this.selectedInfo.notificationId
      // 参照範囲
      this.viewerSettingData.corReferenceCondition = this.form.corReferenceCondition
      // お知らせ参照権限リスト
      this.viewerSettingData.notificationReferenceAuthorityList = this.form.notificationReferenceAuthorityList
      // 個別担当者リスト
      this.viewerSettingData.individualRepList = this.form.individualRepList
      // ダイアログ表示
      this.viewerSettingVisible = true
    },
    a005Action(viewerSettingData) {
      // 設定フラグ
      this.form.settingFlag = viewerSettingData.settingFlag
      // 閲覧者
      this.form.corReferenceCondition = viewerSettingData.viewerSetting
      this.form.viewerSetting = viewerSettingData.viewerSetting
      // お知らせ参照権限リスト(元から登録されていたものを除く)
      this.form.notificationReferenceAuthorityList = viewerSettingData.selectedAuthority.filter(
        item => !viewerSettingData.registeredNotificationReferenceAuthorityList.includes(item)
      )
      // 個別担当者リスト
      this.form.individualRepList = viewerSettingData.repSelectList
      // 未登録の個別担当者リスト
      this.unRegisteredIndividualRepList = viewerSettingData.repSelectList.filter(
        item => !viewerSettingData.registeredIndividualRepList.includes(item.userId)
      )
    },
    // 確認ダイアログ表示
    comfirmHandlerA007() {
      // 「URL」テキストボックスに「"http://"+「URL」テキストボックスの値」を設定
      if (this.form.url && !this.form.url.toLowerCase().startsWith('http')) {
        this.form.url = 'http://' + this.form.url
      }
      // フォーマットデータのバリエーション
      this.$refs['form'].validate(valid => {
        if (valid) {
          const largeCategoryName = this.form.notificationCategoryList.filter(item => item.requiredFlag === '1' && item.t9nInfoCat === this.form.largeCategory)[0].t9nName
          // 情報更新確認ダイアログの「カテゴリ名」の設定
          this.confirmMessage = `『${largeCategoryName}』を登録します。よろしいですか？`
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
              // 更新用リクエスト発行
              this.editInfoUpdateA008bRequestModel(null)
              document.getElementById('ifaInfoUpdateA008b').click()
            } else {
              // アップロード用リクエスト発行
              document.getElementById('ifaInfoUpdateA008a').click()
            }
          })
        } else {
          return false
        }
      })
    },
    // 更新用リクエスト編集
    editInfoUpdateA008bRequestModel(A008aResponse) {
      this.form.notificationId = this.selectedInfo.notificationId
      this.form.viewerSetting = this.form.corReferenceCondition
      this.form.categoryIdList = this.getA008ParamCategoryIdList()
      this.form.registerFileName1 = A008aResponse ? A008aResponse.registerFileName1 : ''
      this.form.fileComment1 = this.form.attachFileComment1
      this.form.registerFileName2 = A008aResponse ? A008aResponse.registerFileName2 : ''
      this.form.fileComment2 = this.form.attachFileComment2
      this.form.registerFileName3 = A008aResponse ? A008aResponse.registerFileName3 : ''
      this.form.fileComment3 = this.form.attachFileComment3
      this.form.readManage = this.form.corReadFlg
      this.form.fileDirectory = A008aResponse ? A008aResponse.fileDirectory : ''
    },
    responseHandlerIfaInfoUpdateA008a(response) {
      if (response.returnCode === 'errors.processingFailed') {
        const categoryIdList = this.getA008ParamCategoryIdList()
        notifyWrapper({
          title: this.$store.getters.pageInfo?.label ?? '',
          message: `${categoryIdList.find(e => e.requiredFlag === '1')?.categoryName}の更新が失敗しました。`,
          type: 'error'
        })
      } else {
        this.editInfoUpdateA008bRequestModel(response.dataList[0])
        this.$nextTick(() => {
          document.getElementById('ifaInfoUpdateA008b').click()
        })
      }
    },
    responseHandlerIfaInfoUpdateA008b() {
      this.onDialogClose()
      this.$emit('update-table')
      notifyWrapper({
        title: 'インフォメーション登録',
        message: '更新完了しました。',
        type: 'success',
        dangerouslyUseHTMLString: true
      })
    },
    a013Action() {
      // 登録ファイル1（削除）の値を空にする。
      this.form.fileDeleteName1 = ''
    },
    a014Action() {
      // 登録ファイル2（削除）の値を空にする。
      this.form.fileDeleteName2 = ''
    },
    a015Action() {
      // 登録ファイル3（削除）の値を空にする。
      this.form.fileDeleteName3 = ''
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$refs['select1'].clearFiles()
      this.$refs['select2'].clearFiles()
      this.$refs['select3'].clearFiles()
      this.form.attachFileList = [null, null, null]
      this.form.settingFlag = false
      this.$refs['ifaInfoRegisterViewerSetting'].refresh()
      this.$emit('close-modal')
    },
    corReferenceConditionValidator(rule, value, callback) {
      if (!this.form.corReferenceCondition) {
        callback('閲覧者を入力してください。')
        return
      }
      // OK
      callback()
    },
    titleValidator(rule, value, callback) {
      if (!this.form.title) {
        callback('タイトルを入力してください。')
        return
      }
      // OK
      callback()
    },
    largeCategoryValidator(rule, value, callback) {
      if (this.form.largeCategory === '') {
        callback('カテゴリを選択してください。')
        return
      }
      // OK
      callback()
    },
    // 添付ファイル
    handleChangeAttachFilesSelection(file, index) {
      this.form.attachFileList[index] = file
    },
    handleRemoveFile(index) {
      this.form.attachFileList[index] = null
    },

    getLargeCategoryId(documentKindInput1) {
      const index = this.largeCategoryList.findIndex(item => item.key === documentKindInput1)
      return index !== -1 ? this.largeCategoryList[index].key : ''
    },
    getA008ParamCategoryIdList() {
      const largeCategoryTmp = this.form.notificationCategoryList.filter(item =>
        item.requiredFlag === '1' && item.t9nInfoCat === this.form.largeCategory).map(item => {
        return {
          categoryId: item.t9nInfoCat,
          requiredFlag: item.requiredFlag,
          categoryName: item.t9nName
        }
      })
      return [...largeCategoryTmp]
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
  white-space: nowrap;
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
.table-body_document {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
  padding: 0.5rem 0;
}
.text-box:deep(.el-form-item){
    margin: 0 !important;
    padding: 0 5px;
}
.check_box {
  width:240px;
  margin-top:1rem;
}
.viewer-setting-button{
  :deep(.el-button){
    margin-top: 0;
  }
}
.message-error-position{
  :deep(.el-form-item__error){
    margin-left: 5px;
  }
}
:deep(.table-body_document) .ifa-button {
  font-size: 12px;
}
:deep(.adjust_font_size) .ifa-button {
  font-size: 11px;
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
.text-box-file{
  :deep(.el-form-item__content){
    display: flex;
    flex-direction: column;
    align-items: flex-start;
 }
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
:deep(.table-label) .el-form-item {
  margin: 0;
}
:deep(.table-body) .el-form-item {
  margin: 0.3rem 1rem;
}
:deep(.table-body_document) .el-form-item {
  margin: 0;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
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
:deep(.el-textarea__inner) {
  font-size: 16px;
  color: #18181A;
  border: 1px solid #A7B1C3;
}
:deep(.el-form-item__error){
    margin-top: 3px;
}
</style>
