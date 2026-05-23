<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.title"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      :rules="rules"
    >
      <el-row>
        <el-col
          :span="22"
          class="close-button"
        >
          <ifa-button
            id="btnReset"
            text="リセット"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="resetA002"
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
          <el-card
            style="background-color: #eee; margin-bottom: 0.5rem;"
          >
            <el-row style="margin-bottom: 10px;">
              <div><label>銘柄情報</label></div>
            </el-row>
            <el-row>
              <el-col
                :span="22"
                :offset="1"
              >
                <el-row style="margin-bottom: 10px;">
                  <el-col style="display: flex;">
                    <el-col
                      :span="3"
                      class="info_area"
                    >投信銘柄：</el-col>
                    <span>{{ $_out(form.investmentTrustAssociationCode) }} {{ $_out(form.mutualFundBrandName) }}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-bottom: 10px;">
                  <el-col
                    :span="6"
                    style="display: flex;"
                  >
                    <div class="info_area">基準日：</div>
                    <span>{{ $_out($_getFormattedDateValue(form.standardDate, 'date8')) }}</span>
                  </el-col>
                  <el-col
                    :span="6"
                    style="display: flex;"
                  >
                    <div class="info_area">基準価額：</div>
                    <span>{{ $_out($_withCommaInteger(form.price)) }}円</span>
                  </el-col>
                  <el-col
                    :span="6"
                    style="display: flex;"
                  >
                    <div class="info_area">前日比：</div>
                    <span>{{ $_out($_signedWithCommaInteger(form.diff)) }}円</span>
                  </el-col>
                  <el-col
                    :span="6"
                    style="display: flex;"
                  >
                    <div class="info_area">下落率：</div>
                    <span>{{ $_out($_signedWithCommaZeroPadding(form.rateOfDecline, 2)) }}%</span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-card>
          <el-card
            style="background-color: #eee; margin-bottom: 0.5rem;"
          >
            <el-row style="margin-bottom: 10px;">
              <div><label>顧客情報</label></div>
            </el-row>
            <el-row>
              <el-col
                :span="22"
                :offset="1"
              >
                <el-row style="margin-bottom: 10px;">
                  <el-col
                    :span="12"
                    style="display: flex;"
                  >
                    <div class="info_area">部店-口座番号：</div>
                    <span>{{ $_out(form.butenCode) }}-{{ $_out(form.accountNumber) }}</span>
                  </el-col>
                  <el-col
                    :span="12"
                    style="display: flex;"
                  >
                    <el-col
                      :span="4"
                      class="info_area"
                    >顧客名：</el-col>
                    <span>{{ $_out(form.customerName) }}</span>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <el-form
          ref="form"
          :model="form"
          :inline="true"
        >
          <el-col
            :span="22"
            :offset="1"
          >
            <el-row
              style="margin-bottom: 1rem; margin-top: 1rem;"
            >
              <ifa-input-select
                v-model="form.responseMethodClassification"
                label="対応方法"
                prop="way"
                style="width: 280px"
                :required="true"
                code-list-id="MEANS_TYPE"
                :disp-pattern="1"
                :select-pattern="2"
              ></ifa-input-select>
            </el-row>
            <el-row
              style="margin-bottom: 1rem;"
            >
              <ifa-input-select
                v-model="form.otherContentsClassification"
                label="対応方法「その他」の内容"
                prop="wayContent"
                style="width: 280px"
                :required="form.responseMethodClassification === '9'"
                code-list-id="METHOD_OTHER_CONTENTS"
                :disp-pattern="1"
                :select-pattern="2"
                :disabled="form.responseMethodClassification !== '9'"
                :rules="rules.otherContentsClassification"
              ></ifa-input-select>
            </el-row>
            <el-row
              style="margin-bottom: 1rem;"
            >
              <ifa-input-text
                id="otherDetail"
                v-model="form.otherDetail"
                label="「その他」の詳細（自由記入）"
                type="textarea"
                :required="form.otherContentsClassification === '99'"
                prop="otherDetail"
                style="width: 280px"
                original-screen-id="SUB020301_03-01_1"
                :disabled="form.otherContentsClassification !== '99'"
                :domain="IfaText200DomainModel"
              >
              </ifa-input-text>
            </el-row>
            <el-row style="margin-bottom: 1rem;">
              <ifa-date-picker
                v-model="form.customerResponseDate"
                label="顧客対応日"
                :required="form.responseMethodClassification !== '9'"
                prop="customerResponseDate"
                :rules="rules.customerResponseDate"
                :picker-options="pickerOptions"
                :disabled="form.responseMethodClassification === '9'"
              ></ifa-date-picker>
            </el-row>
            <el-row style="margin-bottom: 1rem;">
              <ifa-date-picker
                v-model="form.responseFinishConfirmDate"
                label="対応完了確認日"
                prop="responseFinishConfirmDate"
                :rules="rules.responseFinishConfirmDate"
                :disabled="!confirmDateDisabled"
                :picker-options="pickerOptions"
              ></ifa-date-picker>
            </el-row>
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
              action-type="originalAction"
              @app-action-handler="comfirmHandler"
            ></ifa-button>
          </el-col>
        </el-form>
      </el-row>
    </el-dialog>
    <ifa-ok-cancel-dialog
      :is-visible="okDialogComfirmVisible"
      :title="comfirmTitle"
      :message="comfirmMessage"
      @close-modal-ok="updateFinish"
      @close-modal-cancel="okDialogComfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <ifa-requester
      id="ifaResponseStatusUpdateResponseStatusUpdateConfirmOkA006"
      action-id="SUB020301_03-01_1#A006"
      action-type="requestAction"
      :request-model="A006RequestModel"
      @response-handler="responseHandlerConfirmOkA006($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaResponseStatusUpdateFormModel } from './js/IfaResponseStatusUpdateFormModel'
import { IfaResponseStatusUpdateA006RequestModel } from './js/IfaResponseStatusUpdateA006RequestModel'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog'
import IfaText200DomainModel from '@/resource/domain/IfaText200DomainModel.json'
import { getMessage } from '@/utils/errorHandler'
import store from '@/store'

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
  emits: ['close-modal', 'update:isVisible', 'update-confirm', 'confirm-ok'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaText200DomainModel: IfaText200DomainModel,
      form: new IfaResponseStatusUpdateFormModel(),
      rules: {
        otherContentsClassification: [{ type: 'Object', validator: this.otherContentsClassificationValidator, trigger: 'change' }],
        customerResponseDate: [{ type: 'Object', validator: this.customerResponseDateValidator }],
        responseFinishConfirmDate: [{ type: 'Object', validator: this.completionConfirmationDateValidator }]
      },
      pickerOptions: {
        disabledDate(date) {
          if (new Date(store.getters.requestedTime) < date) {
            return true
          }
          // 上記以外は有効
          return false
        }
      },
      okDialogComfirmVisible: false,
      comfirmTitle: '更新確認',
      comfirmMessage: '入力された内容で更新します。よろしいですか？',
      a001Response: null
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userAccount
    },
    confirmDateDisabled() {
      if (this.userInfo.medUsers?.privId === '1' || this.userInfo.medUsers?.privId === '2' || this.userInfo.medUsers?.privId === '3' || this.userInfo.medUsers?.privId === '6') {
        return true
      } else {
        return false
      }
    },
    A006RequestModel() {
      return new IfaResponseStatusUpdateA006RequestModel(this.form, this.confirmDateDisabled)
    }
  },
  watch: {
    'form.responseMethodClassification'(newValue, oldValue) {
      if (oldValue === '9' && newValue !== '9') {
        this.changeFromNine()
      } else if (oldValue !== '9' && newValue === '9') {
        this.changeToNine()
      }
    },
    'form.otherContentsClassification'(newValue, oldValue) {
      if (oldValue === '99' && newValue !== '99') {
        this.changeFromNineNine()
      }
    }
  },
  methods: {
    onShow(a001Response) {
      // 初期化
      this.resetForm()
      this.form = { ...this.form, ...a001Response }
      if (this.form.customerResponseDate !== '') {
        this.form.customerResponseDate = this.$_getFormattedDateValue(this.form.customerResponseDate, 'date8')

        // 顧客対応日がシステム日付以降であれば空にする。
        if (new Date(store.getters.requestedTime) < new Date(this.form.customerResponseDate)) {
          this.form.customerResponseDate = ''
        }

        // 対応方法_入力 = '9':その他 で顧客対応日に値が入っていた場合はクリアする
        if (this.form.responseMethodClassification === '9') {
          this.form.customerResponseDate = ''
        }
      }

      this.a001Response = a001Response
    },
    changeFromNine() {
      this.form.otherContentsClassification = ''
      this.form.otherDetail = ''
    },
    changeToNine() {
      this.form.customerResponseDate = ''
    },
    changeFromNineNine() {
      this.form.otherDetail = ''
    },
    // 確認ダイアログ表示
    comfirmHandler() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.okDialogComfirmVisible = true
        } else {
          return false
        }
      })
    },
    updateFinish() {
      document.getElementById('ifaResponseStatusUpdateResponseStatusUpdateConfirmOkA006').click()
      this.okDialogComfirmVisible = false
      this.$emit('close-modal')
    },
    // 戻るボタン押下
    onDialogClose() {
      this.$emit('close-modal')
      this.resetForm()
    },
    // フォームリセット
    resetForm() {
      this.$nextTick(() => {
        this.$refs['form'].clearValidate()
      })
      this.form.responseMethodClassification = ''
      this.form.otherContentsClassification = ''
      this.form.otherDetail = ''
      this.form.customerResponseDate = ''
      this.form.responseFinishConfirmDate = ''
    },
    customerResponseDateValidator(rule, value, callback) {
      if (new Date(this.form.customerResponseDate) < new Date(this.form.standardDate)) {
        callback('基準日より後の日付を入力してください。')
        return
      }
      // OK
      callback()
    },
    resetA002() {
      this.resetForm()
      this.form = { ...this.form, ...this.a001Response }
      if (this.form.customerResponseDate !== '') {
        this.form.customerResponseDate = this.$_getFormattedDateValue(this.form.customerResponseDate, 'date8')
      }
    },
    otherContentsClassificationValidator(rule, value, callback) {
      if (
        this.form.responseMethodClassification === '9' && !this.form.otherContentsClassification
      ) {
        callback(getMessage('errors.selected', ['対応方法「その他」の内容']))
        return
      }

      callback()
    },
    completionConfirmationDateValidator(rule, value, callback) {
      if (new Date(this.form.responseFinishConfirmDate) < new Date(this.form.standardDate)) {
        callback('基準日より後の日付を入力してください。')
        return
      }
      if (new Date(this.form.responseFinishConfirmDate) < new Date(this.form.customerResponseDate)) {
        callback('顧客対応日と同一か後の日付を入力してください。')
        return
      }
      // OK
      callback()
    },
    responseHandlerConfirmOkA006() {
      // 更新完了時に再検索
      this.$emit('confirm-ok')
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
:deep(.el-dialog) {
  width: 1000px;
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
:deep(.el-form-item__label) {
  width: 250px !important;
}
:deep(.el-textarea__inner) {
  height: 90px;
}
.info_area {
  font-weight: 700;
  width: 105px;
  text-align: right;
  margin-right: 5px;
}
</style>
