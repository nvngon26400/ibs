<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      :close-on-click-modal="false"
      :show-close="false"
      height="800px"
      width="782px"
    >
      <el-row>
        <el-col>
          <page-caption
            text="自己点検項目新規登録"
            background-color="rgba(190, 190, 190, 0.35)"
          ></page-caption>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <div class="form-cancel-button__wrapper">
            <ifa-button
              text="戻る"
              color="secondary"
              small
              @click="onDialogClose"
            ></ifa-button>
          </div>
        </el-col>
      </el-row>

      <div>
        <!-- エラー/警告表示 -->
        <el-row>
          <el-col :span="21">
            <div>
              <ul
                type="disc"
                class="error-message"
              >
                <li
                  v-for="message in messages.errors"
                  :key="message"
                >{{ message }}</li>
              </ul>
              <ul
                type="disc"
                class="warning-message"
              >
                <li
                  v-for="message in messages.warnings"
                  :key="message"
                >{{ message }}</li>
              </ul>
              <ul
                type="disc"
                class="info-message"
              >
                <li
                  v-for="message in messages.infos"
                  :key="message"
                >{{ message }}</li>
              </ul>
            </div>

          </el-col>
        </el-row>
        <el-form
          ref="form"
          :model="form"
          :rules="rules"
          :inline="true"
        >
          <el-card
            class="content-card"
            shadow="always"
          >
            <div class="filter-container">
              <!-- ここに検索条件用のコンテンツを記述する -->
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-input
                    v-model="form.addLineNum"
                    class="search-form__item"
                    size="small"
                    maxlength="12"
                  ></el-input>
                </el-col>
                <el-col
                  :span="1"
                  :pull="1"
                  style="margin-top:0.5rem"
                >
                  <span>行</span>
                </el-col>
                <el-col :span="5">
                  <ifa-button
                    v-if="isNaN(form.addLineNum) || !form.addLineNum > 0"
                    text="表示"
                    width="90"
                    small
                    disabled
                    @click="addRow"
                  ></ifa-button>
                  <ifa-button
                    v-if="form.addLineNum > 0"
                    text="表示"
                    width="90"
                    small
                    @click="addRow"
                  ></ifa-button>
                </el-col>
              </el-row>
            </div>
          </el-card>
          <el-row>
            <el-card class="content-card">
              <el-row>
                <div class="gridButtonArea">
                  <el-col :span="3">
                    <ifa-button
                      v-if="pqGridOption.dataModel.data.length === 0"
                      text="登録"
                      small
                      disabled
                    ></ifa-button>
                    <ifa-button
                      v-if="pqGridOption.dataModel.data.length > 0"
                      text="登録"
                      small
                      @click="registHandler('form')"
                    ></ifa-button>
                  </el-col>
                  <el-col :span="5">
                    <el-form-item
                      class="form_label label_period"
                      prop="period"
                    >
                      <ifa-month-picker
                        v-model="form.period"
                        class="search-form__item"
                        style="width: 150px"
                        size="small"
                        :picker-options="pickerOptions"
                      ></ifa-month-picker>
                    </el-form-item>
                  </el-col>
                </div>
              </el-row>
              <div
                class="pq-grid-title"
                style="margin-top:0.5rem"
              >自己点検項目</div>
              <grid-table :options="pqGridOption"></grid-table>
            </el-card>
          </el-row>
        </el-form>
      </div>
    </el-dialog>
    <!-- ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="okCancelDialog.visible"
      :title="okCancelDialog.title"
      :message="okCancelDialog.message"
      :enable-ok="okCancelDialog.enableOk"
      :enable-cancel="okCancelDialog.enableCancel"
      @close-modal-ok="handleCloseModalOk"
      @close-modal-cancel="handleCloseModalCancel"
    ></ifa-ok-cancel-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import IfaOkCancelDialog from '@/views/companyEmployeeMenu/complianceReport/ComplianceReportInfoRegister/IfaOkCancelDialog.vue'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { notifyWrapper } from '@/utils/errorHandler'

export default {
  components: {
    GridTable,
    IfaOkCancelDialog,
    pageCaption
  },
  props: {
    isVisible: { type: Boolean, required: true },
    managementViewData: { type: Array, required: true }
  },
  emits: ['close-modal', 'update:isVisible'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      form: {
        period: this.getThisMonth(),
        addLineNum: '',
        registeredMonth: ''
      },
      rules: {
        period: { required: true, type: 'date', validator: this.periodValidator }
      },
      pickerOptions: {
        disabledDate(date) {
          if (new Date() >= date) {
            return true
          }
          // 上記以外は有効
          return false
        },
        shortcuts: [{
          text: '当月',
          onClick(picker) {
            const date = new Date()
            picker.$emit('pick', date.getFullYear() + '/' + (date.getMonth() + 1))
          }
        }]
      },
      isInfoMessage: false,
      messageContent: 'ここには通知がある場合に通知内容が表示されます｡',
      pqGridOption: null,
      csvbtn: true,
      okCancelDialog: {
        visible: false,
        title: '登録確認',
        message: '自己点検項目を登録します。よろしいですか？',
        enableOk: true,
        enableCancel: true
      },
      messages: {
        errors: [
        ],
        warnings: [
        ],
        infos: [
        ]
      }
    }
  },
  created() {
    this.pqGridOption = this.getDefaultOption(columns1)
  },
  methods: {
    // 表示ボタン押下処理
    addRow() {
      for (let i = 0; i < this.form.addLineNum; i++) {
        this.pqGridOption.dataModel.data.push({ displayOrder: this.pqGridOption.dataModel.data.length + 1, checkContent: '', defaultConfirmValue: 'はい' })
      }
    },
    // 当月を取得
    getThisMonth() {
      const date = new Date()
      return (date.getFullYear() + '/' + ('0' + (date.getMonth() + 1)).slice(-2))
    },
    registHandler(formName) {
      // 入力値チェックを行う
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.messages.errors = []
          if (this.pqGridOption.dataModel.data.filter(data => data.checkContent === '').length > 0) {
            this.messages.errors.push('チェック項目を入力してください。')
            return false
          } else if (this.pqGridOption.dataModel.data.filter(data => data.checkContent.length > 1000).length > 0) {
            this.messages.errors.push('チェック項目は1000文字以内で入力してください。')
            return false
          } else if (this.pqGridOption.dataModel.data.filter(data => data.displayOrder === '').length > 0) {
            this.messages.errors.push('表示順を入力してください。')
            return false
          } else if (this.pqGridOption.dataModel.data.filter(data => data.displayOrder.length > 3).length > 0) {
            this.messages.errors.push('表示順は3文字以内で入力してください。')
            return false
          } else if (this.pqGridOption.dataModel.data.filter(data => data.displayOrder.toString().match(/[^0-9]+/)).length > 0) {
            this.messages.errors.push('表示順は半角数字で入力してください。')
            return false
          } else if (this.existsSameValue(this.pqGridOption.dataModel.data)) {
            this.messages.errors.push('表示順が重複しています。')
            return false
          } else if (this.managementViewData.filter(data => data.registeredMonth === this.form.period).length > 0) {
            this.messages.errors.push('設定された登録年月の自己点検項目はすでに登録されています。')
            return false
          }
          this.okCancelDialog.visible = true
        } else {
          return false
        }
      })
    },
    async handleCloseModalCancel() {
      this.okCancelDialog.visible = false
    },
    async handleCloseModalOk() {
      notifyWrapper({
        title: '自己点検項目',
        message: '自己点検項目を登録しました。',
        type: 'success'
      })

      this.okCancelDialog.visible = false
      this.onDialogClose()
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    getDefaultOption(colModel) {
      return {
        showTop: false,
        reactive: true,
        locale: 'en',
        height: 'flex',
        numberCell: {
          show: false
        },
        columnTemplate: { width: 100 },
        pageModel: {
          type: 'local',
          rPP: 30,
          rPPOptions: [30, 50, 100, 200, 500],
          layout: ['strDisplay', '|', 'prev', 'next']
        },
        colModel: colModel,
        dataModel: {
          data: []
        },
        wrap: false,
        maxHeight: 750,
        selectionModel: { type: 'row', mode: 'single' }
      }
    },
    // 登録年月のバリデーションチェック処理
    periodValidator(rule, value, callback) {
      if (this.form.period === '') {
        callback('登録年月を指定してください｡')
        return
      }
      if (!this.form.period.match(/^\d{4}\/\d{2}$/)) {
        callback('登録年月はYYYY/MM形式で入力してください。')
        return
      }
      if (this.form.period.replace('/', '') < this.getThisMonth().replace('/', '')) {
        callback('当月、未来月を入力してください。')
        return
      }
      // OK
      callback()
    },
    isDate(str, delim) {
      const arr = str.split(delim)
      if (arr.length !== 2) return false
      const date = new Date(arr[0], arr[1] - 1)
      if (arr[0] !== String(date.getFullYear()) || arr[1] !== ('0' + (date.getMonth() + 1)).slice(-2)) {
        return false
      } else {
        return true
      }
    },
    // 配列内重複チェック
    existsSameValue(data) {
      const s = new Set()
      for (let i = 0; i < this.pqGridOption.dataModel.data.length; i++) {
        s.add(this.pqGridOption.dataModel.data[i].displayOrder.toString())
      }
      return s.size !== data.length
    }
  }
}

// Grid表示用データ(デモ画面用定義)
// 列の定義(まとめて定義すべき)
const columns1 = [
  { title: '表示順', dataType: 'string', dataIndx: 'displayOrder', width: '100', editable: true, align: 'right', halign: 'center' },
  { title: 'チェック項目', dataType: 'string', dataIndx: 'checkContent', width: '500', editable: true, align: 'left', halign: 'center' },
  { title: '確認初期値', dataType: 'string', dataIndx: 'defaultConfirmValue', width: '100', editable: false, align: 'center', halign: 'center' }
]

</script>
<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 200px;
}
.filter-container {
  margin-top:1rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.error-message {
  margin: 0.5rem;
  color: red;
  font-size: 14px;
  font-weight: 600;
}
.warning-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: red;
  font-size: 14px;
}
.info-message {
  margin: 0.5rem;
  padding-left: 4rem;
  color: black;
  font-size: 14px;
}
.form-cancel-button__wrapper {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0 0.2rem 0;
}
:deep(.el-checkbox__label) {
  font-weight: bold;
}
:deep(.el-textarea__inner) {
  width:82%;
  margin-left:85px;
  resize: none;
}
.gridButtonArea {
  margin-bottom: 10px;
}
:deep(.form_label) .el-form-item__label {
  width: 110px;
}
:deep(.label_period) .el-form-item__label {
  width: 135px;
}
.el-icon-info {
  font-size: 30px;
}
:deep(.el-form-item) {
  margin-bottom: 1.2rem;
}
:deep(.el-form-item__error) {
  width: max-content;
}
</style>
