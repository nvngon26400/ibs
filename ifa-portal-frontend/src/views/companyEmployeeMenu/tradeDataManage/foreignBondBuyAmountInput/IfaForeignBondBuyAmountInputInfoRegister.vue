<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      :title="form.title.name"
      :show-close="false"
      :center="true"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
      width="1000px"
      @open="onShow"
    >
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <div style="width:97%; text-align: right; margin: 10px;">
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
                    <span class="required-mark">*</span>部店コード
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="butenCode"
                      v-model="form.butenCode"
                      prop="butenCode"
                      label-class="hide-label"
                      label="部店コード"
                      size="small"
                      style="width: 650px"
                      :domain="IfaButenCodeDomainModel"
                      @blur="sendA003()"
                      @change="changeUpper"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>口座番号
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      id="accountNumber"
                      v-model="form.accountNumber"
                      prop="accountNumber"
                      label-class="hide-label"
                      label="口座番号"
                      size="small"
                      style="width: 650px"
                      :domain="IfaAccountNumberDomainModel"
                      @blur="sendA003()"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    顧客名
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-text
                      :disabled="true"
                      id="nameKanji"
                      v-model="form.nameKanji"
                      prop="nameKanji"
                      size="small"
                      style="width: 650px"
                      label-class="hide-label"
                      label="顧客名"
                    ></ifa-input-text>
                  </el-form-item>
                </td>
              </tr>
              <tr>
                <td class="table-label">
                  <el-form-item>
                    <span class="required-mark">*</span>金額
                  </el-form-item>
                </td>
                <td class="table-body">
                  <el-form-item class="text-box">
                    <ifa-input-amount
                      v-model="form.amount"
                      :domain="IfaUsdCurrency174DomainModel"
                      :min="0.0001"
                      prop="amount"
                      label-class="hide-label"
                      label="金額"
                      unit="USD"
                      size="small"
                      support
                    ></ifa-input-amount>
                  </el-form-item>
                </td>
              </tr>
            </table>
          </el-form>
        </el-col>
      </el-row>
      <el-col
        :offset="1"
        style="padding: 10px"
      >
        <ifa-button
          text="登録確認"
          width="90"
          color="primary"
          style="padding-left: 0"
          action-type="originalAction"
          @app-action-handler="comfirmHandlerA005"
        ></ifa-button>
      </el-col>

      <!-- 確認ダイアログ.更新確認 -->
      <ifa-ok-cancel-dialog
        :is-visible="confirmDialog.visible"
        :title="confirmDialog.titleMessage"
        :message="confirmDialog.confirmMessage"
        @close-modal-ok="registerA005"
        @close-modal-cancel="confirmDialog.visible = false"
      ></ifa-ok-cancel-dialog>

      <ifa-requester
        id="IfaForeignBondBuyAmountInputInfoRegisterA003"
        action-type="requestAction"
        action-id="SUB0504_04-02_1#A003"
        :request-model="IfaForeignBondBuyAmountInputInfoA003RequestModel"
        @response-handler="handleA003ResponseNameKanji($event)"
      ></ifa-requester>
      <!-- A005: 登録 -->
      <ifa-requester
        id="IfaForeignBondBuyAmountInputInfoRegisterA005"
        action-id="SUB0504_04-02_1#A005"
        action-type="requestAction"
        :request-model="IfaForeignBondBuyAmountInputInfoRegisterA005Model"
        @response-handler="handleRegisterA005"
        @response-error-handler="handleErrorRegisterA005($event)"
      ></ifa-requester>
    </el-dialog>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaForeignBondBuyAmountInputInfoRegisterModel } from './js/IfaForeignBondBuyAmountInputInfoRegisterModel'
import { IfaForeignBondBuyAmountInputInfoA003RequestModel } from './js/IfaForeignBondBuyAmountInputInfoA003RequestModel'
import { IfaForeignBondBuyAmountInputInfoRegisterA005Model } from './js/IfaForeignBondBuyAmountInputInfoRegisterA005Model'
import IfaAccountNumberDomainModel from '@/resource/domain/IfaAccountNumberDomainModel.json'
import IfaButenCodeDomainModel from '@/resource/domain/IfaButenCodeDomainModel.json'
import { getMessage } from '@/utils/errorHandler'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaUsdCurrency174DomainModel from '@/resource/domain/IfaUsdCurrency174DomainModel.json'

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
      IfaUsdCurrency174DomainModel: IfaUsdCurrency174DomainModel,
      IfaAccountNumberDomainModel: IfaAccountNumberDomainModel,
      IfaButenCodeDomainModel: IfaButenCodeDomainModel,
      form: new IfaForeignBondBuyAmountInputInfoRegisterModel(),
      dialogComfirmVisible: false,
      rules: {
        butenCode: [{ required: true, trigger: 'blur', validator: this.butenCodeValidator }],
        accountNumber: [{ required: true, trigger: 'blur', validator: this.accountNumberValidator }],
        amount: [{ required: true, trigger: 'blur', validator: this.amountValidator }]
      },
      title: '外債買付代金入力',
      confirmDialog: { // 確認ダイアログ
        visible: false,
        titleMessage: '登録の確認',
        confirmMessage: ''
      }
    }
  },
  computed: {
    IfaForeignBondBuyAmountInputInfoA003RequestModel() {
      return new IfaForeignBondBuyAmountInputInfoA003RequestModel(this.form)
    },
    IfaForeignBondBuyAmountInputInfoRegisterA005Model() {
      return new IfaForeignBondBuyAmountInputInfoRegisterA005Model(this.form)
    }
  },
  methods: {
    changeUpper() {
      const str = this.form.butenCode
      this.form.butenCode = str.toUpperCase()
    },
    // 戻る
    onDialogClose() {
      this.$emit('close-modal')
    },
    // 初期化
    onShow() {
      this.$refs['form'].clearValidate()
      this.$refs['form'].resetFields()
    },
    // 部店コード
    butenCodeValidator(rule, value, callback) {
      if (!this.form.butenCode.length) {
        callback(getMessage('errors.required', ['部店コード']))
      } else {
        callback()
      }
    },
    // 口座番号
    accountNumberValidator(rule, value, callback) {
      if (!this.form.accountNumber.length) {
        callback(getMessage('errors.required', ['口座番号']))
      } else {
        callback()
      }
    },
    // 金額（USD）
    amountValidator(rule, value, callback) {
      if (!this.form.amount.length) {
        callback(getMessage('errors.required', ['金額']))
      } else {
        callback()
      }
    },
    // 部店コードと口座番号を入力して、リクエスト
    sendA003() {
      if (this.form.accountNumber.length && this.form.butenCode.length && /^\d+$/.test(this.form.accountNumber)) {
        this.$nextTick(() => {
          document.querySelector('#IfaForeignBondBuyAmountInputInfoRegisterA003').click()
        })
      }
    },
    handleA003ResponseNameKanji(data) {
      this.form.nameKanji = ''
      this.form.nameKanji = data?.dataList?.[0]?.nameKanji ?? ''
    },
    // クライアント処理
    comfirmHandlerA005() {
      // バリデーションチェック
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 確認ダイアログを表示する。
          this.confirmDialog.confirmMessage = '外債買付代金入力情報を登録します。よろしいですか？'
          this.confirmDialog.visible = true
        } else {
          return
        }
      })
    },
    // テーブル更新、ダイアログ閉じる
    handleRegisterA005() {
      this.onDialogClose()
      this.$emit('update-table')
    },
    handleErrorRegisterA005(event) {
      this.onDialogClose()
      this.$emit('update-table')
    },
    /* A005.登録 */
    registerA005() {
      this.confirmDialog.visible = false
      this.$nextTick(() => {
        document.querySelector('#IfaForeignBondBuyAmountInputInfoRegisterA005').click()
      })
    }
  }
}

</script>

<style lang="scss" scoped>
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
:deep(.el-form-item__error){
    margin-top: 3px;
}
.required-mark {
  color: red;
  margin-right: 2px;
}
:deep(.table-body .ifa-button) {
    margin: 0.3rem 0rem 0.3rem 0.5rem;
}
.text-box:deep(.el-form-item){
    margin: 0 !important;
    padding: 0 5px;
}
.hide-label > :deep(.el-form-item__label){
  display: none;
}
</style>
