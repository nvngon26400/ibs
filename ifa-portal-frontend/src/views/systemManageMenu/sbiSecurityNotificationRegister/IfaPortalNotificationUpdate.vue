<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
    >
      <!-- タイトル -->
      <template #header>
        <span>SBI証券からのご連絡更新</span>
      </template>
      <!-- メイン -->
      <el-row>
        <el-col
          :span="22"
          class="close-button"
        >
          <ifa-button
            id="btnReset"
            text="リセット"
            width="110"
            color="secondary"
            action-id="SUB0602-04_1#A004"
            action-type="requestAction"
            :request-model="A004RequestModel"
            @response-handler="responseHandlerResetA004($event)"
          ></ifa-button>
          <ifa-button
            id="btnBack"
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
        <el-col
          :span="22"
          :offset="1"
        >
          <el-form
            ref="formModel"
            :model="form"
            :rules="rules"
            :inline="true"
          >
            <el-row style="margin-bottom: 1rem;">
              <ifa-date-range-picker
                v-model="form.period"
                label="表示期間"
                prop="period"
                class="form_label"
                unlink-panels
                :picker-options="pickerOptions"
                size="small"
                no-swap
                style="position: initial;"
                label-class="validation-error-width"
              ></ifa-date-range-picker>
            </el-row>
            <el-row>
              <ifa-input-text
                id="notificationContent"
                ref="focusTextArea"
                v-model="form.notificationContent"
                label="ご連絡内容"
                prop="notificationContent"
                type="textarea"
                :rows="3"
                maxlength="2000"
                required
                class="form_label ifa-input__text-default"
                label-class="validation-error-width"
                style="width: 35rem;"
                original-screen-id="SUB0602-04_1"
                :domain="IfaText2000DomainModel"
              ></ifa-input-text>
            </el-row>
            <el-row style="margin-left: 43.5rem; margin-bottom: 0.5rem;">
              <div
                style="float: right;"
                :style="form.inputWordCount < 2000 ? 'color: #18181A;' : 'color: red;'"
              >
                {{ form.inputWordCount }}/2000
              </div>
            </el-row>
            <el-row>
              <ifa-input-text
                id="linkAddress"
                v-model="form.linkAddress"
                label="リンクアドレス"
                prop="link"
                size="small"
                maxlength="255"
                class="form_label ifa-input__text-default"
                style="width: 35rem;"
                :domain="IfaText255DomainModel"
              ></ifa-input-text>
            </el-row>
            <el-row style="margin-left: 170px;">
              <ifa-button
                text="リンク設定"
                width="110"
                color="secondary"
                small
                :disabled="!(form.linkAddress.length > 0)"
                style="margin-top: 0.8rem; margin-bottom: 0.8rem"
                action-type="originalAction"
                @app-action-handler="setLink"
              ></ifa-button>
            </el-row>
            <el-row>
              <el-form-item
                label="重要なご連絡"
                class="form_label"
                required
              >
                <el-switch
                  v-model="form.importantNotification"
                  active-color="#1989fa"
                ></el-switch>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item
                label="非表示"
                class="form_label"
                required
              >
                <el-switch
                  v-model="form.nonDisplay"
                  active-color="#1989fa"
                ></el-switch>
              </el-form-item>
            </el-row>
          </el-form>
        </el-col>

        <el-col
          :offset="2"
          style="padding: 0.5rem"
        >
          <ifa-button
            id="btnUpdate"
            text="更新"
            width="90"
            color="primary"
            :form="formRef"
            action-type="originalAction"
            @app-action-handler="comfirmHandler"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="dialogComfirmVisible"
      :title="title"
      :message="message"
      @close-modal-ok="doOperation()"
      @close-modal-cancel="dialogComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="ifaPortalNotificationUpdatePortalNoticeUpdateOkA007"
      action-id="SUB0602-04_1#A007"
      action-type="requestAction"
      :request-model="A007RequestModel"
      @response-handler="responseHandlerA007($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaDate8DomainModel from '@/resource/domain/IfaDate8DomainModel.json'
import IfaText2000DomainModel from '@/resource/domain/IfaText2000DomainModel.json'
import IfaText255DomainModel from '@/resource/domain/IfaText255DomainModel.json'
import { notifyMessage } from '@/utils/errorHandler'
import { useVModel } from 'vue-composable'
import { IfaPortalNotificationUpdateA004RequestModel } from './js/IfaPortalNotificationUpdateA004RequestModel'
import { IfaPortalNotificationUpdateA007RequestModel } from './js/IfaPortalNotificationUpdateA007RequestModel'
import { IfaPortalNotificationUpdateFormModel } from './js/IfaPortalNotificationUpdateFormModel'

export default {
  components: {
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
      IfaText255DomainModel: IfaText255DomainModel,
      form: new IfaPortalNotificationUpdateFormModel(),
      dialogComfirmVisible: false,
      formRef: {},
      IfaDate8DomainModel: IfaDate8DomainModel,
      rules: {
        period: [{ type: 'Object', validator: this.dateToValidator }]
      },
      sel: { start: 0, end: 0 },
      title: '',
      message: '',
      pickerOptions: {
        shortcuts: [
          {
            text: '本日',
            value: () => {
              return [new Date(), new Date()]
            }
          }, {
            text: '前営業日',
            value: () => {
              return [this.previousBusinessDay, this.previousBusinessDay]
            }
          }, {
            text: '当月',
            value: () => {
              // startDateに当月の月初を設定
              const startDate = new Date()
              startDate.setDate(1)
              startDate.setHours(0, 0, 0, 0)
              // endDateに前営業日を設定
              // 前営業日が前月の場合には、当月初日を設定する
              const businessDate = new Date(this.previousBusinessDay)
              const endDate =
               businessDate.getMonth() !== startDate.getMonth() ? startDate : this.previousBusinessDay
              return [startDate, endDate]
            }
          },
          {
            text: '前月',
            value: () => {
              // startDateに前月の月初を設定
              const startDate = new Date()
              startDate.setHours(0, 0, 0, 0)
              startDate.setMonth(startDate.getMonth() - 1, 1)
              // endDateに前月の月末を設定
              const endDate = new Date()
              endDate.setHours(0, 0, 0, 0)
              endDate.setDate(0)
              return [startDate, endDate]
            }
          }]
      }
    }
  },
  computed: {
    A004RequestModel() {
      return new IfaPortalNotificationUpdateA004RequestModel(this.form)
    },
    A007RequestModel() {
      return new IfaPortalNotificationUpdateA007RequestModel(this.form)
    },
    previousBusinessDay() {
      return this.$_getFormattedDateValue(this.$store.getters.previousBusinessDay)
    }
  },
  watch: {
    isVisible: function() {
      if (this.isVisible) {
        this.setParam()
        this.switchTitleMessage()
      }
    },
    'form.notificationContent': function() {
      this.form.inputWordCount = this.form.notificationContent.length
    }
  },
  mounted() {
    this.formModel = new IfaPortalNotificationUpdateFormModel()
    this.formRef = this.$refs.formModel
  },
  methods: {
    setParam() {
      this.form = Object.assign(this.form, this.selectedInfo)
      this.form.importantNotification = this.selectedInfo.importantNotification === '1' // 重要なお知らせ ('1'/'0' → true/false)
      this.form.nonDisplay = this.selectedInfo.nonDisplay === '1' // 非表示 ('1'/'0' → true/false)
      this.form.period = [this.$_getFormattedDateValue(this.selectedInfo.displayPeriodFrom), this.$_getFormattedDateValue(this.selectedInfo.displayPeriodTo)]
      this.form.linkAddress = ''
    },
    // 確認ダイアログ表示
    comfirmHandler() {
      this.$refs['formModel'].validate(valid => {
        if (valid) {
          this.dialogComfirmVisible = true
        } else {
          return false
        }
      })
    },
    // 登録・更新処理
    doOperation() {
      this.dialogComfirmVisible = false
      this.form.appearPeriod.push(this.form.period[0], this.form.period[1])
      this.form.period[0] = this.form.period[0].replace(/(\/)/g, '')
      this.form.period[1] = this.form.period[1].replace(/(\/)/g, '')
      document.getElementById('ifaPortalNotificationUpdatePortalNoticeUpdateOkA007').click()
    },
    responseHandlerResetA004(response) {
      Object.assign(this.form, response.dataList[0])
      this.form.period = [this.$_getFormattedDateValue(response.dataList[0].displayPeriodFrom), this.$_getFormattedDateValue(response.dataList[0].displayPeriodTo)]
      this.form.linkAddress = ''
      this.form.importantNotification = this.form.importantNotification === '1' // 重要なお知らせ ('1'/'0' → true/false)
      this.form.nonDisplay = this.form.nonDisplay === '1' // 非表示 ('1'/'0' → true/false)
    },
    responseHandlerA007() {
      this.updateTable()
    },
    // ダイアログ閉じる
    onDialogClose() {
      this.$emit('close-modal')
      this.$nextTick(() => {
        this.$refs['formModel'].clearValidate()
      })
    },
    // テーブル更新、ダイアログ閉じる
    updateTable() {
      this.onDialogClose()
      this.$emit('update-table')
    },
    // ご連絡内容項目にリンクを挿入
    setLink() {
      this.$nextTick(() => {
        const txt = this.form.notificationContent
        this.sel.start = this.$refs['focusTextArea'].$el.querySelector('textarea').selectionStart
        this.sel.end = this.$refs['focusTextArea'].$el.querySelector('textarea').selectionEnd
        if (this.sel.start === this.sel.end) {
          notifyMessage(-1, 'ご連絡内容を選択してください。', 'リンクアドレス入力エラー')
        } else {
          const linkText = '<a href="' + this.form.linkAddress + '" target="_blank" rel="noopener">' + txt.substr(this.sel.start, (this.sel.end - this.sel.start)) + '</a>'
          this.form.notificationContent = txt.substr(0, this.sel.start) + linkText + txt.substr(this.sel.end)
          this.form.linkAddress = ''
        }
        this.sel.start = 0
        this.sel.end = 0
      })
    },
    switchTitleMessage() {
      this.title = '更新の確認'
      this.message = '入力された内容で更新します。よろしいですか。'
    },
    dateToValidator(rule, value, callback) {
      if (this.form.period[1] !== '' && new Date(this.form.period[1]) < new Date(this.$store.getters.requestedTime?.split(' ')?.[0])) {
        callback('表示期間Toには当日以降の日付を入力してください。')
        return
      }
      if (new Date(this.form.period[0]) > new Date(this.form.period[1])) {
        callback('表示期間ToにはFromと同日以降の日付を指定して下さい。')
        return
      }
      // OK
      callback()
    }
  }
}
</script>

<style lang="scss" scoped>
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
.ifa-input__text-default {
  width: 600px;
}
:deep(.el-form-item__error) {
  top: auto;
  margin-top: 0 !important;
}
:deep(.el-dialog) {
  width: 900px;
  padding: 30px 10px;
}
:deep(.el-dialog__header) {
  color: #18181A;
  padding: 0px;
}
:deep(.el-dialog__header) span{
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__body) {
  padding: 0px;
  margin-bottom: 1rem;
}
// 確認ダイアログ
:deep(.confirm-dialog) .el-dialog{
  padding: 30px 30px 10px 30px;
}
:deep(.confirm-dialog) .el-dialog__header span{
  font-size: 16px;
  margin: 0px;
  font-weight: bold;
}
:deep(.confirm-dialog) .dialog-body{
  padding: 25px 0 25px 0;
}
:deep(.confirm-dialog) .dialog-footer{
  margin-left: 120px
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
:deep(.el-textarea__inner) {
  font-size: 16px;
  color: #18181A;
  border: 1px solid #A7B1C3;
}
.validation-error-width {
  :deep(.el-form-item__error) {
    position: relative;
  }
}
</style>
