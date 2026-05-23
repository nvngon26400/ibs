<template>
  <div>
    <!-- 分配金受取方法変更入力ダイアログ -->
    <el-dialog
      v-model="showDialog"
      class="custom-dialog"
      style="width: 810px;"
      :title="form.title.name"
      :center="true"
      :show-close="false"
      :before-close="onDialogClose"
      :close-on-click-modal="false"
    >
      <!-- 戻るボタン -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
          style="text-align: right;"
        >
          <ifa-button
            text="戻る"
            color="secondary"
            style="padding-right: 0;"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>

      <!-- 翌日向け申込 警告メッセージ -->
      <el-row>
        <el-col
          :span="22"
          :offset="1"
        >
          <p
            v-if="showInfoMsg"
            class="__message"
          >{{ form.infoMsg }}</p>
        </el-col>
      </el-row>

      <!-- 明細部 -->
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
      >
        <el-row v-if="showDatas">
          <el-col
            :span="22"
            :offset="1"
          >
            <span class="__title">投資信託(金額)：</span>
          </el-col>
        </el-row>

        <el-row v-if="showDatas">
          <el-col
            :span="22"
            :offset="1"
          >
            <table class="_table__horizontal _table__body">
              <thead>
                <tr>
                  <th
                    class="_table__header __center"
                    style="width: 230px; min-width: 230px;"
                  >ファンド名</th>
                  <th
                    class="_table__header __center"
                    style="width: 195px; min-width: 195px;"
                  >保有口数<br>(売却注文中)</th>
                  <th
                    class="_table__header __center"
                    style="width: 85px; min-width: 85px;"
                  >分配金<br>受取方法</th>
                  <th
                    class="_table__header __center"
                    style="width: 185px; min-width: 185px;"
                  >変更</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="_table__data __left">{{ $_out(form.fundName) }}</td>
                  <td class="_table__data __right">{{ $_addComma($_out(form.unitVolume)) }}口<br>({{ $_addComma($_out(form.sellingVolume)) }}口)</td>
                  <td class="_table__data __center">
                    {{ $_out(form.method) }}
                  </td>
                  <td
                    class="_table__data __center border-none adjust_form_margin"
                    style="display: flex; align-items: center;"
                  >
                    <span class="required-mark">*</span>
                    <ifa-input-radio
                      code-list-id="DISTRIBUTION_RECEIVE_METHOD"
                      small
                      :required="true"
                      :rules="rules"
                      prop="afterChangeDistributionReceiveMethodList"
                      :model-value="form.afterChangeDistributionReceiveMethodList"
                      :disp-pattern="2"
                      @change="handleChangeDistributionReceiveMethod"
                    ></ifa-input-radio>
                  </td>
                </tr>
              </tbody>
            </table>
          </el-col>
        </el-row>

        <!-- 登録･変更ボタン -->
        <el-row
          v-if="showDatas"
        >
          <el-col
            :span="22"
            :offset="1"
            style="
            margin-top: 24px"
          >
            <ifa-button
              v-if="showDatas"
              id="btnOrderRegister"
              text="登録･変更"
              style="padding-left: 0;"
              action-type="requestAction"
              action-id="SUB0202_010201-02_1#A002"
              :request-model="IfaDistributionReceiveMethodChangeInputA002RequestModel"
              :form="formRef"
              @response-handler="responseHandlerA002($event)"
            ></ifa-button>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>

    <!-- 分配金受取方法変更完了画面の初期化処理のifa-requester -->
    <ifa-requester
      id="IfaDistributionReceiveMethodChangeCompleteA001"
      action-type="requestAction"
      action-id="SUB0202_010201-02_2#A001"
      :request-model="IfaDistributionReceiveMethodChangeCompleteA001RequestModel"
      @response-handler="responseHandlerCompleteA001($event)"
      @response-error-handler="responseErrorHandlerA001()"
    ></ifa-requester>
  </div>
</template>

<script>
import { IfaDistributionReceiveMethodChangeInputFormModel } from './js/IfaDistributionReceiveMethodChangeInputFormModel'
import { IfaDistributionReceiveMethodChangeInputA002RequestModel } from './js/IfaDistributionReceiveMethodChangeInputA002RequestModel'
import { IfaDistributionReceiveMethodChangeCompleteA001RequestModel } from './js/IfaDistributionReceiveMethodChangeCompleteA001RequestModel'

export default {
  props: {
    isVisible: { type: Boolean, required: true },
    dialogData: { type: Object, required: true }
  },

  emits: ['close', 'change-complete'],

  data() {
    return {
      showDialog: false, // ダイアログ全体の表示・非表示を制御する
      showDatas: true, // API001エラー時の画面に画面項目の一部を非表示にする制御をする
      form: new IfaDistributionReceiveMethodChangeInputFormModel(),
      formRef: {},
      rules: {}
    }
  },

  computed: {
    // リクエストモデル
    IfaDistributionReceiveMethodChangeInputA002RequestModel() {
      return new IfaDistributionReceiveMethodChangeInputA002RequestModel(this.form)
    },
    IfaDistributionReceiveMethodChangeCompleteA001RequestModel() {
      return new IfaDistributionReceiveMethodChangeCompleteA001RequestModel(this.form)
    },

    // 翌日向けの取引となる警告メッセージの表示・非表示を制御する
    showInfoMsg() {
      if (this.form.infoMsg) {
        return true
      } else {
        return false
      }
    }
  },

  watch: {
    isVisible(newValue) {
      this.showDialog = newValue
    }
  },

  methods: {
    // 画面表示時の処理
    onShow(event) {
      // formの初期化
      this.form = new IfaDistributionReceiveMethodChangeInputFormModel()

      // fromRefの初期化
      this.formRef = this.$refs.formRef
      this.formRef.resetFields()

      // 親画面から受け取った値を設定
      this.form = { ...this.form, ...event.dataList[0] }
      this.form.afterChangeDistributionReceiveMethodList = event.dataList && event.dataList.length > 0
        ? event.dataList[0].distributionReceiveClassification
        : ''
      this.showDatas = event.dataList && event.dataList.length > 0
    },

    // 戻るボタン
    onDialogClose() {
      this.$emit('close')
    },

    // 受取方法変更
    handleChangeDistributionReceiveMethod(newValue) {
      this.form.afterChangeDistributionReceiveMethodList = newValue
    },

    // A002 登録・変更
    responseHandlerA002(event) {
      this.$nextTick(() => {
        document.getElementById('IfaDistributionReceiveMethodChangeCompleteA001').click()
      })
    },
    // 変更完了A001 初期化
    responseHandlerCompleteA001(event) {
      this.$emit('change-complete', event.dataList[0])
    }
  }
}
</script>

<style lang="scss" scoped>
.close-button__wrapper {
  display: flex;
  justify-content: flex-end;
  margin: -40px 0 0 auto;
}
.__title {
  font-weight: 700;
  font-size: 16px;
  line-height: 4rem;
}
.__message {
  font-size: 16px;
}
:deep(div.el-form-item__error) {
  top: 24px;
}
.required-mark {
  color: #ff2b2b;
  margin-right: 2px;
  display: flex;
  align-items: center;
}
.border-none {
  border: none;
}
:deep(._table__data) .el-form {
  margin-bottom: 0px;
}
:deep(._table__data) .el-radio {
  margin-right: 13px;
}
.adjust_form_margin :deep(.el-form-item) {
  margin-top: 5px;
  margin-bottom: 0;
}
</style>
