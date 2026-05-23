<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      width="1200px"
      :title="correctForm.dialogTitle.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      @open="onShow"
    >
      <el-row>
        <div style="width:96.5%; text-align: right; margin: 10px;">
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </div>
      </el-row>
      <el-form
        ref="formRef"
        :model="correctForm"
        :rules="rules"
        :inline="true"
      >
        <el-card>
          <table class="input-table">
            <tr>
              <td class="table-header">
                <div><span style="color:red;">*</span>内容</div>
              </td>
            </tr>
            <tr>
              <td
                class="table-content backgroundColor"
              >
                <ifa-input-text
                  id="naiyou"
                  v-model="correctForm.naiyou"
                  prop="naiyou"
                  type="textarea"
                  label="内容"
                  :rows="6"
                  resize="vertical"
                  class="no-border"
                  original-screen-id="SUB0202_0106-07"
                  :domain="IfaText3910DomainModel"
                  label-class="validation-error-width"
                  style="width: 100%;"
                ></ifa-input-text>
              </td>
            </tr>
          </table>
        </el-card>
        <div class="contactCorrectButton">
          <ifa-button
            text="更新"
            width="110"
            is-visible="false"
            small
            color="primary"
            action-type="originalAction"
            @app-action-handler="contactCorrectConfirmA002()"
          ></ifa-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
  <!-- 問合せ修正 初期化イベント -->
  <ifa-requester
    id="IfaContactCorrectInitializeA001"
    action-id="SUB0202_0106-07#A001"
    action-type="requestAction"
    :request-model="queryData"
    @response-handler="initializeResponse($event)"
  ></ifa-requester>
  <!-- 問合せ修正 更新イベント -->
  <ifa-requester
    id="ifaContactCorrectUpdateA003"
    action-id="SUB0202_0106-07#A003"
    action-type="requestAction"
    :request-model="{...queryData, naiyou: correctForm.naiyou}"
  ></ifa-requester>
</template>

<script>
import { useVModel } from 'vue-composable'
import { getMessage, notifyMessage } from '@/utils/errorHandler'
import { initTextareaHeight, calculateTextLength } from './js/IfaContactUtils'
import IfaText3910DomainModel from '@/resource/domain/IfaText3910DomainModel.json'
import { IfaContactCorrectFormModel } from './js/IfaContactCorrectFormModel'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    queryData: { type: Object, required: true }
  },
  emits: ['update:isVisible', 'close-modal', 'research'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      rules: { naiyou: [{ required: true, message: getMessage('errors.required', ['内容']) }, { validator: this.lengthValidator('内容') }] },
      correctForm: new IfaContactCorrectFormModel(),
      IfaText3910DomainModel: IfaText3910DomainModel,
      isUpdate: false // 更新されましたか
    }
  },
  methods: {
    onShow() {
      // バリデーションクリア
      this.$refs['formRef']?.clearValidate()
      this.isUpdate = false
      // 初期化時に「内容」テキストエリアの高さをリセットする
      initTextareaHeight(document.querySelector('#naiyou'))
      this.$nextTick(() => {
        // 初期化表示イベント
        document.getElementById('IfaContactCorrectInitializeA001').click()
      })
    },
    initializeResponse(response) {
      this.correctForm.beforeNaiyou = ''
      this.correctForm.naiyou = ''
      // 登録グループ=0の場合: 問合せ情報.問合せ内容、回答情報リスト.回答内容を結合して表示
      // 上記以外の場合:回答情報リスト.回答内容を結合して表示
      if (response.dataList[0].tourokuGroup === '0') {
        if (response.dataList[0].naiyou) {
          this.correctForm.naiyou = response.dataList[0].naiyou
        }
        // 修正前「内容」を保存する
        this.correctForm.beforeNaiyou = response.dataList[0].naiyou
      }
      if (response.dataList[0].answerList?.length > 0) {
        response.dataList[0].answerList.forEach(item => {
          if (item.kaitouNaiyou) {
            this.correctForm.naiyou += item.kaitouNaiyou
          }
          // 修正前「内容」を保存する
          this.correctForm.beforeNaiyou += item.kaitouNaiyou
        })
      }
    },
    contactCorrectConfirmA002() {
      // フォームチェック：長さは391を超えないこと
      this.$refs['formRef'].validate(valid => {
        if (valid) {
          // 訂正内容チェック：変更があるかどうか
          if (this.correctForm.beforeNaiyou !== this.correctForm.naiyou) {
            this.isUpdate = true
            this.correctForm.beforeNaiyou = this.correctForm.naiyou
            // 接触履歴修正成功の場合、通知ポップアップを消去する
            this.$store.dispatch('notifications/resetState')
            this.$nextTick(() => {
              // 接触履歴修正更新イベント
              document.getElementById('ifaContactCorrectUpdateA003').click()
            })
          } else {
            // エラーメッセージ：入力内容に変更がないため、接触履歴修正を行いません
            notifyMessage(-1, getMessage('errors.cmn.question.noModify'), this.correctForm.dialogTitle.name)
          }
        }
      })
    },
    // 戻るボタンイベント
    onDialogClose() {
      this.correctForm.beforeNaiyou = ''
      this.correctForm.naiyou = ''
      // 接触履歴修正ポップアップ画面が閉めた場合は通知ポップアップを消去する
      this.$store.dispatch('notifications/resetState')
      this.$emit('close-modal')
      // 更新が行われた場合は、ダイアログボックスを閉じて検索を再実行する
      if (this.isUpdate) {
        this.$emit('research')
      }
    },
    // 半角１バイト、全角２バイトとして、3910バイトを超えないようチェックを行う
    lengthValidator(fieldName) {
      return (_, value, callback) => {
        if (!value) {
          callback()
          return
        }
        if (calculateTextLength(value) > this.IfaText3910DomainModel.formattedDigit) {
          callback(getMessage('errors.maxSize', [fieldName, this.IfaText3910DomainModel.formattedDigit]))
          return
        }
      }
    }
  }
}
</script>
<style scoped>
.input-table {
  width:97%;
  margin: 10px;
  border-collapse: collapse;
  border:1px solid #d8e8fa
}
.table-header {
  height:2rem;
  color: #ffffff;
  background-color: #666666;
  border: 1px solid #c5c5c5;
  text-align: center;
}
.table-content {
  border: 1px solid #c5c5c5;
  background-color: rgb(252, 252, 252);
}
.contactCorrectButton {
  margin-top: 1rem;
}
.contactCorrectButton span {
  padding-left: 0px;
  padding-right: 0px;
}
:deep(.table-content) .el-form-item {
  margin: 0;
}
:deep(.el-form-item) .el-form-item__label {
  display: none !important;
}
:deep(.table-content) .ifa-button {
  margin-top: 20px;
}
:deep(.el-form-item__content) .el-form-item__error {
  margin-top: 4px;
}
:deep(.el-form-item__error) {
  position: absolute !important;
}
:deep(.el-textarea__inner) {
  background-color: rgb(222, 238, 250)
}
:deep(.el-dialog__header) {
  justify-content: flex-start;
  display: flex;
}
:deep(.el-dialog__header) span {
  padding-left: 1rem;
}
:deep(.el-textarea__inner:focus) {
  box-shadow: none !important;
}
:deep(.no-border) .el-textarea__inner {
  box-shadow: none;
}
.backgroundColor {
  background-color: rgb(222, 238, 250);
}
:deep(.el-textarea.is-disabled) .el-textarea__inner {
  background-color: rgb(252, 252, 252);
}
</style>
