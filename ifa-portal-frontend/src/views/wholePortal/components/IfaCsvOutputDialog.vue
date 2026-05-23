<template>
  <!-- ファイル選択ダイアログ -->
  <el-dialog
    :model-value="isVisible"
    width="700px"
    :center="true"
    :show-close="false"
    :before-close="backA003"
    :close-on-click-modal="false"
    :title="form.title.name"
    @open="initializeA001"
  >

    <el-form
      ref="form"
      :model="form"
      :inline="true"
    >

      <!-- 戻るボタン -->
      <el-row
        type="flex"
        justify="end"
      >
        <ifa-button
          text="戻る"
          color="secondary"
          class="cancel-button"
          action-type="originalAction"
          @app-action-handler="backA003"
        ></ifa-button>
      </el-row>

      <el-row>
        <el-row style="margin-left: 60px;">
          <ifa-common-search
            ref="commonSearch"
            display-pattern="pt9"
            list-pattern="pt5"
            :form="form"
            style="margin-top:10px;"
          ></ifa-common-search>

          <customer-alert
            ref="customerAlert"
            id-string="IfaCsvOutputDialogCustomerAlert`"
            label="アラート分類"
            prop="select"
            required
            class="form_label"
            @change-selected="form.alertSelected = $event"
          ></customer-alert>
        </el-row>

        <!-- 出力ボタン -->
        <el-row
          type="flex"
          justify="start"
          style="margin-top:10px;"
        >
          <ifa-button
            id="btnCsvDownload"
            name="btnCsvDownload"
            text="CSV出力"
            class="csv-button"
            color="primary"
            action-id="SUB01-02#A002"
            :request-model="IfaCustomerAlertCsvOutputA002RequestModel"
            :csv-file-name="form.csvFileName"
            action-type="outputCsvAction"
            :form="formRef"
            :pre-request-handler="preRequestHandlerCsvOutputA002"
            :is-check-csv-download-allowed="true"
            :is-check-csv-download-privacy-confirmation="true"
          ></ifa-button>
        </el-row>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
import IfaCommonSearch from '@/components/SearchCondition/IfaCommonSearch.vue'
import CustomerAlert from '@/components/MultiSelect/CustomerAlert'
import { IfaCustomerAlertCsvOutputFormModel } from '../js/IfaCustomerAlertCsvOutputFormModel.js'
import { IfaCustomerAlertCsvOutputA002RequestModel } from '../js/IfaCustomerAlertCsvOutputA002RequestModel.js'
export default {
  components: {
    IfaCommonSearch,
    CustomerAlert
  },
  props: {
    isVisible: { type: Boolean, required: true }
  },
  emits: ['close-modal'],
  data() {
    return {
      form: new IfaCustomerAlertCsvOutputFormModel(),
      formRef: {}
    }
  },
  computed: {
    IfaCustomerAlertCsvOutputA002RequestModel() {
      return new IfaCustomerAlertCsvOutputA002RequestModel(this.form)
    }
  },
  methods: {
    initializeA001() {
      this.formRef = this.$refs['form']
    },
    // 戻るボタン
    backA003() {
      // バリデーションエラーをクリア
      this.$refs.form.clearValidate()
      // 検索条件クリア
      this.$refs.commonSearch.formClear()
      this.$refs.customerAlert.clearHandler()
      // モーダルを閉じる
      this.$emit('close-modal')
    },
    // CSV出力のリクエストパラメータを設定
    preRequestHandlerCsvOutputA002() {
      this.IfaCustomerAlertCsvOutputA002RequestModel.brokerCodeList = this.form.brokerCode
      this.IfaCustomerAlertCsvOutputA002RequestModel.tradeCourseList = this.form.courseSelected.filter(e => e.isSelected).map(e => e.id).join(',')
      this.IfaCustomerAlertCsvOutputA002RequestModel.alertClassList = this.form.alertSelected.filter(e => e.isSelected).map(e => e.id).join(',')
    }
  }
}
</script>

<style lang="scss" scoped>
:deep(.form_label) .el-form-item__label {
  width: 135px !important;
  line-height: 2
}
:deep(.form_label) .el-input__inner {
  width: 200px !important;
  line-height: 2
}

.cancel-button {
  margin: -40px 0 0 auto;
}
.csv-button {
  width: 105px;
  margin-left: 1rem;
  margin-bottom: 1rem;
}
</style>
