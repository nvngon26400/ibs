<template>
  <div>  <!-- 画面：SUB0206_01-03 共同募集　顧客管理　更新(入力) -->
    <el-dialog
      v-model="vmIsVisible"
      left
      top="3%"
      :close-on-click-modal="false"
      :show-close="false"
      width="600px"
      :center="true"
      destroy-on-close
      @open="onShow"
      @close="onDialogCloseA003"
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
      >
        <div>
          <el-row>
            <el-col>  <!-- ページキャプション -->
              <page-caption
                text="共同募集　顧客管理　更新"
              ></page-caption>
            </el-col>
          </el-row>
          <el-row>  <!-- 操作部エリア -->
            <el-col>
              <div class="form-cancel-button__wrapper">
                <ifa-button
                  text="リセット"
                  color="secondary"
                  small
                  style="padding-right: 0"
                  action-type="originalAction"
                  @app-action-handler="onResetFormA006"
                ></ifa-button>
                <ifa-button
                  text="戻る"
                  color="secondary"
                  small
                  style="padding-right: 0"
                  action-type="originalAction"
                  @app-action-handler="onDialogCloseA003"
                ></ifa-button>
              </div>
            </el-col>
          </el-row>
        </div>
        <div style="margin-top:0.5rem;">
          <table
            id="t1"
            class="table_area"
          >  <!-- 更新入力エリア -->
            <tbody>
              <tr>  <!-- 部店 -->
                <th class="_table__header __left">
                  {{ itemTitle.butenCodeTitle }}
                </th>
                <td class="_table__data __left">
                  {{ form.butenCode }}
                </td>
              </tr>
              <tr>  <!-- 口座番号 -->
                <th class="_table__header __left">
                  {{ itemTitle.accountNumberTitle }}
                </th>
                <td class="_table__data __left">
                  {{ form.accountNumber }}
                </td>
              </tr>
              <tr>  <!-- 共募支店コード -->
                <th class="_table__header __left">
                  <span style="color:red">*</span>{{ itemTitle.jointBranchCodeTitle }}
                </th>
                <td class="_table__data __left">
                  <ifa-input-text
                    id="jointBranchCode"
                    v-model="form.jointBranchCode"
                    label-class="no_show_label"
                    label="共募支店コード"
                    size="small"
                    style="width:60px"
                    original-screen-id="SUB0206_01-03"
                    :domain="IfaJointBranchCodeDomainModel"
                    prop="jointBranchCode"
                  >
                  </ifa-input-text>
                </td>
              </tr>
              <tr>  <!-- 契約締結日 -->
                <th class="_table__header __left">
                  <span style="color:red">*</span>{{ itemTitle.contractDateTitle }}
                </th>
                <td class="_table__data __left">
                  <ifa-date-picker
                    v-model="form.contractDate"
                    size="small"
                    prop="contractDate"
                    style="width:150px"
                    :picker-options="sysDatePickerOptions"
                  ></ifa-date-picker>
                </td>
              </tr>
              <tr>  <!-- 同意日 -->
                <th class="_table__header __left">
                  {{ itemTitle.startDateTitle }}
                </th>
                <td class="_table__data __left">
                  {{ form.startDate }}
                </td>
              </tr>
              <tr>  <!-- 終了日 -->
                <th class="_table__header __left">
                  <span style="color:red">*</span>{{ itemTitle.endDateTitle }}
                </th>
                <td class="_table__data __left">
                  <ifa-date-picker
                    v-model="form.endDate"
                    size="small"
                    prop="endDate"
                    style="width:150px"
                    :picker-options="sysDatePickerOptions"
                  ></ifa-date-picker>
                </td>
              </tr>
              <tr>  <!-- 支払率 -->
                <th class="_table__header __left">
                  <span style="color:red">*</span>{{ itemTitle.jointRewardRateTitle }}
                </th>
                <td class="_table__data __left">
                  <ifa-input-rate
                    v-model="form.jointRewardRate"
                    label-class="no_show_label"
                    label="支払率"
                    prop="jointRewardRate"
                    unit="%"
                    style="margin-right: 0;width:150px"
                    :digit="7"
                    :min="0"
                    :max="99.9999999"
                    placeholder=""
                    :domain="IfaJointRewardRateDomainModel"
                    :maxlength="10"
                    size="small"
                  ></ifa-input-rate>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <el-row style="margin-top: 20px;">  <!-- 操作部エリア -->
          <el-col
            :offset="0"
            :span="22"
            style="text-align: left;"
          >
            <ifa-button id="btnCorrectConfirm"
                        name="btnCorrectConfirm"
                        text="更新確認"
                        small
                        action-id="SUB0206_01-03#A002"
                        action-type="requestAction"
                        :form="formRef"
                        :request-model="ifaJointSubscriptCustomerCorrectA002RequestModel"
                        @response-handler="responseHandlerActionA002"
                        @response-error-handler="errorHandlerActionA002"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
    <!-- 以下：アクション処理に対応する画面コンポーネントを実装する -->
    <!-- A002アクションの登録確認のポップアップ -->
    <ifa-joint-subscript-customer-correct-confirm
      ref="IfaJointSubscriptCustomerCorrectConfirm"
      :form-model="a002ResModel"
      :is-visible="ifaJointSubscriptCustomerCorrectConfirmDialog"
      @close-modal="ifaJointSubscriptCustomerCorrectConfirmDialog = false"
      @joint-subscript-customer-corrected="onJointSubscriptCustomerCorrected"
    ></ifa-joint-subscript-customer-correct-confirm>
  </div>
</template>

<script>
/* 以下は各々コンポーネントをエクスポートする */
import { getMessage } from '@/utils/errorHandler'
import { useVModel } from 'vue-composable'
import IfaJointBranchCodeDomainModel from '@/resource/domain/IfaJointBranchCodeDomainModel.json'
import IfaJointRewardRateDomainModel from '@/resource/domain/IfaJointRewardRateDomainModel.json'
import { IfaJointSubscriptCustomerCorrectFormModel } from './js/IfaJointSubscriptCustomerCorrectFormModel'
import { IfaJointSubscriptCustomerCorrectA002RequestModel } from './js/IfaJointSubscriptCustomerCorrectA002RequestModel'
import { IfaJointSubscriptCustomerCorrectA002ResponseModel } from './js/IfaJointSubscriptCustomerCorrectA002ResponseModel'
import IfaJointSubscriptCustomerCorrectConfirm from './IfaJointSubscriptCustomerCorrectConfirm'
import pageCaption from '@/views/brokerageMenu/customerMenu/components/pageCaption'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'

/* 以下はVUEコンポーネントのプロパティ：エクスポートされたオブジェクトを指定する */
export default {
  /* 使用可能な子コンポーネントを定義する */
  components: {
    IfaJointSubscriptCustomerCorrectConfirm,
    pageCaption
  },
  /* 受け取る入力パラメータを定義する */
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    formData: {
      type: Object,
      required: true
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：イベントを宣言する */
  emits: ['close-modal', 'update:isVisible', 'joint-subscript-customer-corrected'],
  /* 以下はVUEコンポーネントのプロパティ：ビルドステップ */
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    return {
      vmIsVisible
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：画面変数を初期化する */
  data() {
    return {
      // 項目名
      itemTitle: {
        butenCodeTitle: '部店',
        accountNumberTitle: '口座番号',
        jointBranchCodeTitle: '共募支店コード',
        contractDateTitle: '契約締結日',
        startDateTitle: '同意日',
        endDateTitle: '終了日',
        jointRewardRateTitle: '支払率'
      },
      IfaJointBranchCodeDomainModel,
      IfaJointRewardRateDomainModel,
      form: new IfaJointSubscriptCustomerCorrectFormModel(),
      resetForm: new IfaJointSubscriptCustomerCorrectFormModel(),
      a002ResModel: new IfaJointSubscriptCustomerCorrectA002ResponseModel(),
      ifaJointSubscriptCustomerCorrectConfirmDialog: false,
      formRef: {},
      rules: {
        jointBranchCode: [{ required: true, trigger: 'blur', validator: this.jointBranchCodeValidator }],
        contractDate: [{ type: 'date', required: true, validator: this.contractDateValidator, trigger: 'change' }],
        endDate: [{ required: true, trigger: 'blur', validator: this.endDateValidator }],
        jointRewardRate: [{ required: true, trigger: 'blur', validator: this.jointRewardRateValidator }]
      },
      sysDatePickerOptions: {
        shortcuts: [
          {
            text: '当日日付',
            value: () => {
              const sysDate = new Date(this.$store.getters.requestedTime)
              return [sysDate]
            }
          }
        ]
      }
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：計算プロパティ */
  computed: {
    // A002アクション 登録確認(更新入力)のリクエストモデル
    ifaJointSubscriptCustomerCorrectA002RequestModel() {
      return new IfaJointSubscriptCustomerCorrectA002RequestModel(this.form)
    }
  },
  /* 以下はVUEコンポーネントのプロパティ：DOMのマウントを呼び出し */
  methods: {
    /**
     * 関数：共募支店コードの自定义のバリデーションを設定する
     * @param rule バリデーションルール
     * @param value バリデーション対象の値
     * @param callback バリデーション結果を通知するコールバック関数
     */
    jointBranchCodeValidator(rule, value, callback) {
      if (!this.form.jointBranchCode) {
        callback(getMessage('errors.required', [this.itemTitle.jointBranchCodeTitle]))
      } else {
        callback()
      }
    },
    /**
     * 関数：契約締結日の自定义のバリデーションを設定する
     * @param rule バリデーションルール
     * @param value バリデーション対象の値
     * @param callback バリデーション結果を通知するコールバック関数
     */
    contractDateValidator(rule, value, callback) {
      if (this.form.contractDate.length === 0) {
        callback(getMessage('errors.required', [this.itemTitle.contractDateTitle]))
      }
      // OK
      callback()
    },
    /**
     * 関数：終了日の自定义のバリデーションを設定する
     * @param rule バリデーションルール
     * @param value バリデーション対象の値
     * @param callback バリデーション結果を通知するコールバック関数
     */
    endDateValidator(rule, value, callback) {
      if (this.form.endDate.length === 0) {
        callback(getMessage('errors.required', [this.itemTitle.endDateTitle]))
      } else {
        callback()
      }
    },
    /**
     * 関数：支払率の自定义のバリデーションを設定する
     * @param rule バリデーションルール
     * @param value バリデーション対象の値
     * @param callback バリデーション結果を通知するコールバック関数
     */
    jointRewardRateValidator(rule, value, callback) {
      if (!this.form.jointRewardRate) {
        callback(getMessage('errors.required', [this.itemTitle.jointRewardRateTitle]))
      } else {
        callback()
      }
    },
    /**
     * 関数：A001アクションの処理：初期化
     * @returns なし
     */
    initA001(obj) {
      Object.assign(this.form, obj)
      this.form.contractDate = getFormattedDateValue(obj.contractDate, 'date8')
      this.form.startDate = getFormattedDateValue(obj.startDate, 'date8')
      this.form.endDate = getFormattedDateValue(obj.endDate, 'date8')
      this.$refs['form']?.clearValidate()
      this.formRef = this.$refs.form
    },
    /**
     * 関数：画面表示
     * @returns なし
     */
    onShow() {
      Object.assign(this.resetForm, this.formData)
      this.initA001(this.formData)
    },
    /**
     * 関数：A003アクションの処理：戻る(更新入力)
     * @returns なし
     */
    onDialogCloseA003() {
      this.$emit('close-modal')
    },
    /**
     * 関数：A006アクションの処理：リセット(更新入力)
     * @returns なし
     */
    onResetFormA006() {
      this.initA001(this.resetForm)
    },
    /**
     * 関数：A004アクションの処理：正常更新の後続処理
     * @returns なし
     */
    onJointSubscriptCustomerCorrected() {
      this.ifaJointSubscriptCustomerCorrectConfirmDialog = false
      this.$emit('joint-subscript-customer-corrected')
    },
    /**
     * 関数：A002アクションのレスポンス処理：更新確認画面を呼び出す
     * @param response レスポンス
     * @returns なし
     */
    responseHandlerActionA002(response) {
      this.a002ResModel = Object.assign(this.a002ResModel, response.dataList[0])
      this.ifaJointSubscriptCustomerCorrectConfirmDialog = true
    },
    errorHandlerActionA002() {
      // なし
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
.table_area {
  margin-bottom: 0.5rem;
  width:550px;
}
#t1 tr { line-height: 32px; }
:deep(.el-form-item.form_label.no-label) {
  margin-bottom: 0px !important;
}
._table__header {
  text-align: right;
}
:deep(._table__header.__left) {
  width: 155px;
}
:deep(._table__data.__left) {
  width: 375px;
}
:deep(.no_show_label) {
  margin-bottom: 0px !important;
}
:deep(.no_show_label) > label.el-form-item__label {
  display: none;
}
:deep(._table__data) {
  font-size: 14px;
  padding: 7px;
}
:deep(.el-date-editor.el-input, .el-date-editor.el-input__wrapper) {
  height: auto;
}
</style>
