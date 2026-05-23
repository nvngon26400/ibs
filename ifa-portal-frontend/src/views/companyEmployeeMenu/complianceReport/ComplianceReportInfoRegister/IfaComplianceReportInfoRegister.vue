<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      top="3%"
      title="コンプライアンス通信情報登録"
      :close-on-click-modal="false"
      :show-close="false"
      width="1300px"
      :center="true"
      destroy-on-close
      @open="onShow"
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
                    original-screen-id="SUB0505_01-03_1"
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
                    original-screen-id="SUB0505_01-03_1"
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
                  </div>

                  <ifa-input-text
                    id="comment1"
                    v-model="form.file1.comment"
                    prop="file1.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-03_1"
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
                  </div>
                  <ifa-input-text
                    id="comment2"
                    v-model="form.file2.comment"
                    prop="file2.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-03_1"
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
                  </div>
                  <ifa-input-text
                    id="comment3"
                    v-model="form.file3.comment"
                    prop="file3.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    original-screen-id="SUB0505_01-03_1"
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
                  </div>
                  <ifa-input-text
                    id="commentContent"
                    v-model="form.contentFile.comment"
                    label-class="comment"
                    style="width: 80em;"
                    size="small"
                    prop="contentFile.comment"
                    original-screen-id="SUB0505_01-03_1"
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
                        text="登録確認"
                        style="padding-left: 2px"
                        action-id="SUB0505_01-03_1#A002"
                        action-type="requestAction"
                        :form="formRef"
                        :request-model="ifaComplianceReportInfoRegisterA002RequestModel"
                        @response-handler="responseA002Handler"
                        @response-error-handler="responseA002ErrorHandler"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
    <!-- 登録確認 ダイアログ -->
    <ifa-compliance-report-info-register-confirm
      ref="IfaComplianceReportInfoRegisterConfirm"
      :form-model="form"
      :is-visible="ifaComplianceReportInfoRegisterConfirmDialog.visible"
      @close-modal="ifaComplianceReportInfoRegisterConfirmDialog.visible = false"
      @compliance-info-registered="onComplianceInfoRegistered"
    ></ifa-compliance-report-info-register-confirm>
  </div>
</template>

<script>
import { getMessage } from '@/utils/errorHandler'
import { useVModel } from 'vue-composable'
import IfaBrandName1000DomainModel from '@/resource/domain/IfaBrandName1000DomainModel.json'
import IfaText120DomainModel from '@/resource/domain/IfaText120DomainModel.json'

import { IfaComplianceReportInfoRegisterFormModel } from './js/IfaComplianceReportInfoRegisterFormModel'
import { IfaComplianceReportInfoRegisterA002RequestModel } from './js/IfaComplianceReportInfoRegisterA002RequestModel'
import IfaComplianceReportInfoRegisterConfirm from './IfaComplianceReportInfoRegisterConfirm'

export default {
  components: {
    IfaComplianceReportInfoRegisterConfirm
  },
  props: {
    isVisible: {
      type: Boolean,
      required: true
    }
  },
  emits: ['close-modal', 'update:isVisible', 'compliance-info-registered'],
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
      form: new IfaComplianceReportInfoRegisterFormModel(),
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
      ifaComplianceReportInfoRegisterConfirmDialog: {
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
    ifaComplianceReportInfoRegisterA002RequestModel() {
      return new IfaComplianceReportInfoRegisterA002RequestModel(this.form)
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
      if (!this.form.contentFile.file) {
        callback(getMessage('errors.contentFile.notset'))
      } else {
        callback()
      }
    },
    // 初期化
    init() {
      this.formRef = this.$refs.form
      this.form = new IfaComplianceReportInfoRegisterFormModel()
      this.isUpdateMode = false
    },
    onShow() {
      this.init()
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
      this.ifaComplianceReportInfoRegisterConfirmDialog.visible = false
      this.$emit('compliance-info-registered', value)
    },
    responseA002Handler(data) {
      this.$_logDebug(data)
      this.ifaComplianceReportInfoRegisterConfirmDialog.visible = true
    },
    responseA002ErrorHandler(error) {
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
._table__header {
  text-align: right;
}
</style>
