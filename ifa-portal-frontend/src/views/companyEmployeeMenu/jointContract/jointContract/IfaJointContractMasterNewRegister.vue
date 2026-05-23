<template>
  <div>
    <el-dialog
      v-model="vmIsVisible"
      left
      top="3%"
      :title="form.pageTitle.name"
      :close-on-click-modal="false"
      :show-close="false"
      width="800px"
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
                  text="リセット"
                  color="secondary"
                  style="padding-right: 0"
                  action-type="originalAction"
                  @app-action-handler="A002Clear"
                ></ifa-button>
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
                <th class="_table__header __left"><span style="color:red">*</span>{{ itemTitle.brokerCode }}</th>
                <td class="_table__data __left">
                  <ifa-input-text
                    id="brokerCode"
                    v-model="form.brokerCode"
                    size="small"
                    style="width:100px"
                    :domain="IfaBrokerCodeDomainModel"
                    prop="brokerCode"
                  >
                  </ifa-input-text>
                </td>
              </tr>
              <tr>
                <th class="_table__header __left"><span style="color:red">*</span>{{ itemTitle.jointContract }}</th>
                <td class="_table__data __left">
                  <el-form-item>
                    <el-switch
                      v-model="form.jointContract"
                      active-text="契約"
                      inactive-text="未契約"
                      active-color="#1989fa"
                    ></el-switch>
                  </el-form-item>
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
            <ifa-button
              id="btnRegister"
              name="btnRegister"
              text="登録"
              style="padding-left: 2px"
              action-id="SUB0513_01-02#A004"
              action-type="requestAction"
              :form="formRef"
              :request-model="A004RequestModel"
              msg-title="共同募集契約マスタ登録"
              @response-handler="A004responseHandler($event)"
              @response-error-handler="A004responseErrorHandler($event)"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
    <!-- 登録 ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="insertConfirmVisible"
      title="登録の確認"
      message="共同募集契約マスタを登録します。よろしいですか？"
      @close-modal-ok="updateCloseModalOk"
      @close-modal-cancel="insertConfirmVisible = false"
    ></ifa-ok-cancel-dialog>
    <!-- A005　登録 のOKボタン押下 -->
    <ifa-requester
      id="insertA005"
      action-id="SUB0513_01-02#A005"
      action-type="requestAction"
      :request-model="A005RequestModel"
      msg-title="共同募集契約マスタ登録"
      @response-handler="A005responseHandler($event)"
      @response-error-handler="A005responseErrorHandler($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import { useVModel } from 'vue-composable'
import { getMessage } from '@/utils/errorHandler'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaBrokerCodeDomainModel from '@/resource/domain/IfaBrokerCodeDomainModel.json'
import { IfaJointContractMasterNewRegisterFormModel } from './js/IfaJointContractMasterNewRegisterFormModel'
import { IfaJointContractMasterNewRegisterA004RequestModel } from './js/IfaJointContractMasterNewRegisterA004RequestModel'
import { IfaJointContractMasterNewRegisterA005RequestModel } from './js/IfaJointContractMasterNewRegisterA005RequestModel'

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
  emits: ['close-modal', 'update:isVisible', 'joint-contract-registered', 'joint-contract-registered-error'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  data() {
    return {
      IfaBrokerCodeDomainModel,
      form: new IfaJointContractMasterNewRegisterFormModel(),
      // 項目名
      itemTitle: {
        brokerCode: '仲介業者コード',
        jointContract: '共募契約'
      },
      formRef: {},
      // 登録　確認ダイアログ
      insertConfirmVisible: false,
      rules: {
        jointContract: [{ required: true, trigger: 'blur', validator: this.jointContractValidator }],
        brokerCode: [{ required: true, trigger: 'blur', validator: this.brokerCodeValidator }]
      }
    }
  },
  computed: {
    A004RequestModel() {
      return new IfaJointContractMasterNewRegisterA004RequestModel(this.form)
    },
    A005RequestModel() {
      return new IfaJointContractMasterNewRegisterA005RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.formRef = this.$refs.form
      this.form = new IfaJointContractMasterNewRegisterFormModel()
      this.form.jointContract = false
    },
    jointContractValidator(rule, value, callback) {
      if (!this.form.jointContract) {
        callback(getMessage('errors.required', [this.itemTitle.jointContract]))
      } else {
        callback()
      }
    },
    brokerCodeValidator(rule, value, callback) {
      if (!this.form.brokerCode) {
        callback(getMessage('errors.required', [this.itemTitle.brokerCode]))
      } else {
        callback()
      }
    },
    onDialogClose() {
      this.$emit('close-modal')
    },
    A002Clear() {
      this.form.jointContract = false
      this.form.brokerCode = ''
      this.$refs['form'].clearValidate()
    },
    A004responseHandler(data) {
      this.$_logDebug(data)
      // 登録ダイアログ表示フラグ
      this.insertConfirmVisible = true
    },
    A004responseErrorHandler(error) {
      this.$_logError(error)
      // 登録ダイアログ表示フラグ
      this.insertConfirmVisible = false
    },
    A005responseHandler(data) {
      this.$_logDebug(data)
      // 登録ダイアログ表示フラグ
      this.insertConfirmVisible = false
      // 正常登録後、一覧へ
      this.$emit('joint-contract-registered')
    },
    A005responseErrorHandler(error) {
      this.$_logError(error)
      // 登録ダイアログ表示フラグ
      this.insertConfirmVisible = false
      // 登録異常、共同募集契約マスタへ
      this.$emit('joint-contract-registered-error')
    },
    // 変更確認
    updateCloseModalOk() {
      this.$nextTick(() => {
        document.getElementById('insertA005').click()
      })
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
:deep(.el-switch.is-checked) .el-switch__core::after {
  background-color: #FFF
}
:deep(.el-switch__label) {
  color: #18181A;
}
:deep(.el-switch__label.is-active) {
  color: #005FCC;
}
._table__header {
  text-align: right;
  width:150px
}
:deep(.el-form-item__error) {
  display: flex;
  word-break: keep-all;
  white-space: nowrap;
  overflow-wrap: break-word;
}
</style>
