<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      top="3%"
      :close-on-click-modal="false"
      :show-close="false"
      title="コンプライアンス通信情報登録"
      width="1300px"
      :center="true"
      destroy-on-close
      @open="init"
      @close="onDialogClose"
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
      >
        <div>
          <el-row>
            <el-col>
              <div class="form-cancel-button__wrapper">
                <ifa-button
                  text="戻る"
                  color="secondary"
                  style="padding-right: 0"
                  action-type="originalAction"
                  @app-action-handler="onDialogClose"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
        </div>

        <div style="margin-top:0.5rem;">

          <table
            id="t1"
            style="margin-bottom: 0.5rem;width:100%"
          >
            <tbody>
              <tr>
                <th class="_table__header __left"><span style="color:red">*</span>{{ itemTitle.yearMonth }}</th>
                <td class="_table__data __left">
                  <ifa-month-picker
                    v-model="form.yearMonth"
                    size="small"
                    prop="yearMonth"
                    :readonly="true"
                  ></ifa-month-picker>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left"><span style="color:red">*</span>{{ itemTitle.title }}</th>
                <td class="_table__data __left">
                  <ifa-input-text
                    id="complianceReportTitle"
                    v-model="form.title"
                    size="small"
                    style="width: 50em;"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaText120DomainModel"
                    prop="title"
                  >
                  </ifa-input-text>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left">{{ itemTitle.overview }}</th>
                <td class="_table__data __left">
                  <ifa-input-text
                    id="overview"
                    v-model="form.overview"
                    prop="overview"
                    size="small"
                    style="width: 83em;"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaBrandName1000DomainModel"
                    type="textarea"
                  >
                  </ifa-input-text>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left">{{ itemTitle.file1 }}</th>
                <td class="_table__data __left">
                  <div class="file-box">
                    <ifa-file-select
                      id="btnCsvUpload"
                      ref="select"
                      text="ファイル選択"
                      small
                      show-file-list
                      color="secondary"
                      width="130"
                      @change="handleChange($event,1)"
                      @before-remove="handlerRemove(1)"
                    ></ifa-file-select>
                    <span
                      v-if="!form.file1.file"
                      class="flie-label"
                    >選択されていません</span>
                    <el-link
                      type="primary"
                      href="javascript:;"
                      style="margin-left: 40px;"
                      :underline="false"
                      class="file-delete"
                      @click="form.corFileName1Delete = ''"
                    >{{ form.corFileName1Delete }}</el-link>
                  </div>

                  <ifa-input-text
                    id="comment1"
                    v-model="form.file1.comment"
                    prop="file1.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaText120DomainModel"
                  ></ifa-input-text>
                  <span>（コメント）</span>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left">{{ itemTitle.file2 }}</th>
                <td class="_table__data __left">
                  <div class="file-box">
                    <ifa-file-select
                      id="btnCsvUpload"
                      ref="select"
                      text="ファイル選択"
                      small
                      show-file-list
                      color="secondary"
                      width="130"
                      @change="handleChange($event,2)"
                      @before-remove="handlerRemove(2)"
                    ></ifa-file-select>
                    <span
                      v-if="!form.file2.file"
                      class="flie-label"
                    >選択されていません</span>
                    <el-link
                      type="primary"
                      href="javascript:;"
                      style="margin-left: 40px;"
                      :underline="false"
                      class="file-delete"
                      @click="form.corFileName2Delete = ''"
                    >{{ form.corFileName2Delete }}</el-link>
                  </div>
                  <ifa-input-text
                    id="comment2"
                    v-model="form.file2.comment"
                    prop="file2.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaText120DomainModel"
                  ></ifa-input-text>
                  <span>（コメント）</span>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left">{{ itemTitle.file3 }}</th>
                <td class="_table__data __left">
                  <div class="file-box">
                    <ifa-file-select
                      id="btnCsvUpload"
                      ref="select"
                      text="ファイル選択"
                      small
                      show-file-list
                      color="secondary"
                      width="130"
                      @change="handleChange($event,3)"
                      @before-remove="handlerRemove(3)"
                    ></ifa-file-select>
                    <span
                      v-if="!form.file3.file"
                      class="flie-label"
                    >選択されていません</span>
                    <el-link
                      type="primary"
                      href="javascript:;"
                      style="margin-left: 40px;"
                      class="file-delete"
                      :underline="false"
                      @click="form.corFileName3Delete = ''"
                    >{{ form.corFileName3Delete }}</el-link>
                  </div>
                  <ifa-input-text
                    id="comment3"
                    v-model="form.file3.comment"
                    prop="file3.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaText120DomainModel"
                  ></ifa-input-text>
                  <span>（コメント）</span>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left"><span style="color:red">*</span>{{ itemTitle.contentFile }}</th>
                <td class="_table__data __left">
                  <div class="file-box">
                    <el-form-item
                      prop="contentFile"
                      class="content-file"
                    >
                      <ifa-file-select
                        id="btnCsvUpload"
                        ref="select"
                        text="ファイル選択"
                        small
                        show-file-list
                        color="secondary"
                        width="130"
                        @change="handleChange($event,4)"
                        @before-remove="handlerRemove(4)"
                      ></ifa-file-select>
                    </el-form-item>
                    <span
                      v-if="!form.contentFile.file"
                      class="flie-label"
                    >選択されていません</span>
                    <el-link
                      type="primary"
                      href="javascript:;"
                      class="file-delete"
                      style="margin-left: 40px; margin-bottom: 12px;"
                      :underline="false"
                      @click="form.corContentsFileNameDelete = ''"
                    >{{ form.corContentsFileNameDelete }}</el-link>
                  </div>
                  <ifa-input-text
                    id="commentContent"
                    v-model="form.contentFile.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    prop="contentFile.comment"
                    original-screen-id="SUB0505_01-04_1"
                    :domain="IfaText120DomainModel"
                  ></ifa-input-text>
                  <span>（コメント）</span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <el-row style="margin-top: 20px;">
          <el-col
            :offset="0"
            :span="22"
            style="text-align: left;"
          >
            <ifa-button id="btnRegisterConfirm"
                        name="btnRegisterConfirm"
                        text="変更確認"
                        style="padding-left: 2px"
                        action-id="SUB0505_01-04_1#A003"
                        action-type="requestAction"
                        :form="formRef"
                        :request-model="ifaComplianceReportInfoUpdateA003RequestModel"
                        @response-handler="responseA003Handler"
                        @response-error-handler="responseA003ErrorHandler"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
    <!-- 登録確認 ダイアログ -->
    <ifa-compliance-report-info-update-confirm
      ref="IfaComplianceReportInfoUpdateConfirm"
      :form-model="form"
      :is-visible="ifaComplianceReportInfoUpdateConfirmDialog.visible"
      @close-modal="ifaComplianceReportInfoUpdateConfirmDialog.visible = false"
      @compliance-info-registered="onComplianceInfoRegistered"
    ></ifa-compliance-report-info-update-confirm>
    <ifa-requester
      id="ifaComplianceReportInfoUpdate"
      action-id="SUB0505_01-04_1#A001"
      action-type="requestAction"
      :request-model="ifaComplianceReportInfoUpdateA001RequestModel"
      @response-handler="responseA001Handler"
      @response-error-handler="responseA001ErrorHandler"
    ></ifa-requester>
  </div>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import { useVModel } from 'vue-composable'
import IfaBrandName1000DomainModel from '@/resource/domain/IfaBrandName1000DomainModel.json'
import IfaText120DomainModel from '@/resource/domain/IfaText120DomainModel.json'

import { IfaComplianceReportInfoUpdateFormModel } from './js/IfaComplianceReportInfoUpdateFormModel'
import { IfaComplianceReportInfoUpdateA001RequestModel } from './js/IfaComplianceReportInfoUpdateA001RequestModel'
import { IfaComplianceReportInfoUpdateA003RequestModel } from './js/IfaComplianceReportInfoUpdateA003RequestModel'
import IfaComplianceReportInfoUpdateConfirm from './IfaComplianceReportInfoUpdateConfirm'

export default {
  components: {
    IfaComplianceReportInfoUpdateConfirm
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible', 'compliance-info-registered', 'open-modal'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaBrandName1000DomainModel,
      IfaText120DomainModel,
      form: new IfaComplianceReportInfoUpdateFormModel(),
      // 項目名
      itemTitle: {
        yearMonth: '通信年月',
        title: 'タイトル',
        overview: '概要',
        file1: 'ファイル1',
        file2: 'ファイル2',
        file3: 'ファイル3',
        contentFile: 'コンテンツファイル'
      },
      ifaComplianceReportInfoUpdateConfirmDialog: {
        visible: false
      },
      formRef: {},
      rules: {
        yearMonth: [{ required: true, trigger: 'blur', validator: this.yearMonthValidator }],
        title: [{ required: true, trigger: 'blur', validator: this.titleValidator }],
        comment: [{ required: true, trigger: 'blur', validator: this.commentValidator }],
        contentFile: [{ required: true, trigger: 'blur', validator: this.contentFileValidator }]
      }
    }
  },
  computed: {
    ifaComplianceReportInfoUpdateA001RequestModel() {
      return new IfaComplianceReportInfoUpdateA001RequestModel(this.form)
    },
    ifaComplianceReportInfoUpdateA003RequestModel() {
      return new IfaComplianceReportInfoUpdateA003RequestModel(this.form)
    }
  },
  methods: {
    yearMonthValidator(rule, value, callback) {
      if (!this.form.yearMonth) {
        callback(getMessage('errors.required', [this.itemTitle.yearMonth]))
      } else {
        callback()
      }
    },
    titleValidator(rule, value, callback) {
      if (!this.form.title) {
        callback(getMessage('errors.required', [this.itemTitle.title]))
      } else {
        callback()
      }
    },
    commentValidator(rule, value, callback) {
      if (!this.form.contentFile.comment) {
        callback(getMessage('errors.required', ['コンテンツコメント']))
      } else {
        callback()
      }
    },
    contentFileValidator(rule, value, callback) {
      if (!this.form.contentFile.file && !this.form.corContentsFileNameDelete) {
        callback(getMessage('errors.contentFile.notset'))
      } else {
        callback()
      }
    },
    // 初期化
    init() {
      this.formRef = this.$refs.form
    },
    setup({ rowData: { lectureId }}) {
      this.form = new IfaComplianceReportInfoUpdateFormModel()
      this.form.corLecId = lectureId
      document.querySelector('#ifaComplianceReportInfoUpdate').click()
    },
    handleChange(file, fileNo) {
      switch (fileNo) {
        case 1: this.form.file1.file = file
          break
        case 2: this.form.file2.file = file
          break
        case 3: this.form.file3.file = file
          break
        case 4: this.form.contentFile.file = file
          break
      }
    },
    handlerRemove(fileNo) {
      switch (fileNo) {
        case 1: this.form.file1.file = null
          break
        case 2: this.form.file2.file = null
          break
        case 3: this.form.file3.file = null
          break
        case 4: this.form.contentFile.file = null
          break
      }
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    onComplianceInfoRegistered(value) {
      this.ifaComplianceReportInfoUpdateConfirmDialog.visible = false
      this.$emit('compliance-info-registered', value)
    },
    responseA001Handler(data) {
      this.$_logDebug(data)
      this.form.corFileName1BeforeChange = data.dataList[0]?.corFileName1
      this.form.corFileName1Delete = data.dataList[0]?.corFileName1 ? data.dataList[0]?.corFileName1 + '　削除' : ''
      this.form.corFileName2BeforeChange = data.dataList[0]?.corFileName2
      this.form.corFileName2Delete = data.dataList[0]?.corFileName2 ? data.dataList[0]?.corFileName2 + '　削除' : ''
      this.form.corFileName3BeforeChange = data.dataList[0]?.corFileName3
      this.form.corFileName3Delete = data.dataList[0]?.corFileName3 ? data.dataList[0]?.corFileName3 + '　削除' : ''
      this.form.corContentsFileNameBeforeChange = data.dataList[0]?.corContentsFileName
      this.form.corContentsFileNameDelete = data.dataList[0]?.corContentsFileName ? data.dataList[0]?.corContentsFileName + '　削除' : ''
      this.form.yearMonth = data.dataList[0]?.corCommunicationDate ? this.$_getFormattedDateValue(data.dataList[0].corCommunicationDate + '01', 'date8').substr(0, 7) : ''
      this.form.title = data.dataList[0]?.corTitle
      this.form.overview = data.dataList[0]?.corBrief
      this.form.file1.comment = data.dataList[0]?.corFileDesc1
      this.form.file2.comment = data.dataList[0]?.corFileDesc2
      this.form.file3.comment = data.dataList[0]?.corFileDesc3
      this.form.contentFile.comment = data.dataList[0]?.corContents
      this.$emit('open-modal')
    },
    responseA001ErrorHandler(error) {
      this.$_logError(error)
    },
    responseA003Handler(data) {
      this.$_logDebug(data)
      this.ifaComplianceReportInfoUpdateConfirmDialog.visible = true
    },
    responseA003ErrorHandler(error) {
      this.$_logError(error)
    }
  }
}
</script>

<style lang="scss" scoped>
.__bold {
  font-weight: bold;
}
.clickable:hover {
  cursor: pointer
}
.form-button__wrapper {
  display: flex;
  justify-content: flex-start;
  padding: 0.5rem 0 0.2rem 0;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
.form-area__input-number {
  width: 18rem;
  margin-left: 0.1rem;
}
.file-delete-link {
  margin-left:0.5rem;
  text-decoration:underline;
  text-underline-offset:0.1em;
  font-size:12px
}
#t1 tr { line-height: 40px; }
:deep(.el-form-item.form_label.no-label) {
  margin-bottom: 0px !important;
}
:deep(#btnCsvUpload) {
  display: flex !important;
}
.comment {
  display: inline-block
}
:deep(.el-upload>.padding) {
  padding-left: 0px !important;
}
.file-box {
  display: flex;
}
.file-box .flie-label {
    font-size: 16px;
    color: #092987;
}
:deep(.content-file .el-form-item__error) {
  position: absolute !important;
  white-space: nowrap;
}
:deep(.content-file) {
  margin-bottom: 20px !important;
}
:deep(.file-delete span) {
  text-decoration: underline;
}
._table__header {
  text-align: right;
}
</style>
