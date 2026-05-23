<template>
  <el-form
    ref="formModel"
    :model="form"
    :inline="true"
    label-width="180px"
  >
    <el-dialog
      v-model="vmIsVisible"
      title="更新"
      width="700px"
      :show-close="false"
      :center="true"
      :close-on-click-modal="false"
      @open="onShow"
    >
      <el-row>
        <el-col
          :span="23"
          class="close-button"
        >
          <ifa-button
            text="戻る"
            width="90"
            color="secondary"
            action-type="originalAction"
            @app-action-handler="onDialogClose"
          ></ifa-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col class="input-form">
          <ifa-input-text
            id="loginId"
            v-model="form.loginId"
            label="ログインID"
            class="form_label search-form__item middle_input"
            size="small"
            :disabled="true"
            :domain="IfaLoginIdDomainModel"
            style="width: 21rem;"
          ></ifa-input-text>
        </el-col>
        <el-col class="input-form">
          <ifa-input-text
            id="userName"
            v-model="form.userName"
            label="ユーザー名"
            class="form_label search-form__item middle_input"
            size="small"
            :disabled="true"
            :domain="IfaText255InputDomainModel"
            style="width: 21rem;"
          ></ifa-input-text>
        </el-col>
        <el-col class="input-form">
          <ifa-input-text
            id="mailAddress"
            v-model="form.mailAddress"
            label="認証用メールアドレス"
            prop="mailAddress"
            class="form_label search-form__item middle_input"
            size="small"
            required
            :domain="IfaMailAddressDomainModel"
            style="width: 21rem;"
          ></ifa-input-text>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :offset="1"
          class="update-button"
        >
          <ifa-button
            id="btnUpdate"
            text="更新"
            width="90"
            color="primary"
            :form="formRef"
            action-type="originalAction"
            @app-action-handler="infoUpdate"
          ></ifa-button>
        </el-col>
      </el-row>
    </el-dialog>
    <el-dialog
      v-model="infoUpdateConfirm"
      title="更新確認"
      width="350px"
      :close-on-click-modal="false"
      :show-close="false"
      :center="true"
      style="margin-top: 20vh;"
    >
      <span>認証用メールアドレスを更新します。<br>よろしいですか？</span>
      <template #footer="">
        <span class="dialog-footer">
          <ifa-button
            text="OK"
            width="90"
            color="primary"
            small
            action-id="SUB0404-02_1#A004"
            action-type="requestAction"
            :request-model="IfaAuthMailAddressUpdateA004RequestModel"
            @response-handler="responseHandlerA004($event)"
            @response-error-handler="responseErrorHandlerA004()"
          ></ifa-button>
          <ifa-button
            text="キャンセル"
            width="100"
            color="secondary"
            small
            action-type="originalAction"
            @app-action-handler="infoUpdateConfirm = false"
          ></ifa-button>
        </span>
      </template>
    </el-dialog>
  </el-form>
</template>

<script>
import { useVModel } from 'vue-composable'
import { IfaAuthMailAddressUpdateA004RequestModel } from './js/IfaAuthMailAddressUpdateA004RequestModel'
import { IfaAuthMailAddressUpdateFormModel } from './js/IfaAuthMailAddressUpdateFormModel'
import IfaLoginIdDomainModel from '@/resource/domain/IfaLoginIdDomainModel.json'
import IfaText255InputDomainModel from '@/resource/domain/IfaText255InputDomainModel.json'
import IfaMailAddressDomainModel from '@/resource/domain/IfaMailAddressDomainModel.json'

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true
    },
    rowData: {
      type: Object,
      required: true
    }
  },
  emits: ['update:rowData.userName', 'close-modal', 'data-update', 'update:isVisible', 'update:rowData'],
  setup(props) {
    const vmIsVisible = useVModel(props, 'isVisible')
    const vmRowData = useVModel(props, 'rowData')
    return {
      vmIsVisible,
      vmRowData
    }
  },
  data() {
    return {
      form: new IfaAuthMailAddressUpdateFormModel(),
      infoUpdateConfirm: false,
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaText255InputDomainModel: IfaText255InputDomainModel,
      IfaMailAddressDomainModel: IfaMailAddressDomainModel,
      formRef: {}
    }
  },
  computed: {
    IfaAuthMailAddressUpdateA004RequestModel() {
      return new IfaAuthMailAddressUpdateA004RequestModel(this.form)
    }
  },
  methods: {
    onShow() {
      this.form.loginId = this.vmRowData.loginId
      this.form.userName = this.vmRowData.userName
      this.form.mailAddress = this.vmRowData.mailAddress
      this.formModel = new IfaAuthMailAddressUpdateFormModel()
      this.$nextTick(() => {
        this.formRef = this.$refs.formModel
      })
    },
    onDialogClose() {
      this.$refs['formModel'].clearValidate()
      this.$emit('close-modal')
    },
    infoUpdate() {
      // フォームのサブミット前に入力値チェックを行う
      this.$refs['formModel'].validate(valid => {
        if (valid) {
          this.infoUpdateConfirm = true
        } else {
          return false
        }
      })
    },
    responseHandlerA004() {
      this.$emit('data-update')
      this.infoUpdateConfirm = false
      this.onDialogClose()
    },
    responseErrorHandlerA004() {
      this.infoUpdateConfirm = false
      this.onDialogClose()
    }
  }
}
</script>

<style scoped>
.search-form__item {
  margin-right: 0.2rem;
  width: 350px;
}
.close-button {
  margin-bottom: 1rem;
  text-align: right;
}
.update-button {
  margin-top: 1rem;
  text-align: left;
}
.input-form {
  margin: 1rem 0 1rem 0;
}
</style>
