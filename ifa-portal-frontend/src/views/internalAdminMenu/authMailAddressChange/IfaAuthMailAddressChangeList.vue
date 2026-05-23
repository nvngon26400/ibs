<template>
  <div>
    <screen-title text="認証用メールアドレス一覧"></screen-title>
    <el-form
      ref="formModel"
      :model="form"
      :inline="true"
    >
      <div>
        <el-card class="content-card">
          <div class="filter-container">
            <el-row>
              <el-col class="input-area">
                <ifa-input-text
                  id="loginId"
                  v-model="form.loginId"
                  prop="loginId"
                  label="ログインID"
                  label-class="validation-error-width"
                  class="form_label search-form__item middle_input"
                  size="small"
                  style="margin-right: 2rem;"
                  :domain="IfaLoginIdDomainModel"
                ></ifa-input-text>
                （部分検索）
              </el-col>
              <el-col class="input-area">
                <ifa-input-text
                  id="branchNameBrokerName"
                  v-model="form.branchNameBrokerName"
                  prop="branchNameBrokerName"
                  label="支店名/仲介業者名"
                  class="form_label search-form__item middle_input"
                  size="small"
                  style="margin-right: 2rem;"
                  :domain="IfaTextFullHalf80DomainModel"
                ></ifa-input-text>
                （部分検索）
              </el-col>
              <el-col class="input-area">
                <ifa-input-text
                  id="employeeNameChargeName"
                  v-model="form.employeeNameChargeName"
                  prop="employeeNameChargeName"
                  label="社員名/担当者名"
                  class="form_label search-form__item middle_input"
                  size="small"
                  style="margin-right: 2rem;"
                  :domain="IfaTextFullHalf80DomainModel"
                ></ifa-input-text>
                （部分検索）
              </el-col>
              <el-col>
                <span
                  id="indicator-display"
                  class="form_button"
                >
                  <ifa-button
                    id="btnDisplay"
                    text="表示"
                    width="90"
                    search
                    small
                    :form="formRef"
                    action-type="requestAction"
                    action-id="SUB0404-01#A002"
                    :request-model="IfaAuthMailAddressChangeListA002RequestModel"
                    :pre-request-handler="A002PreRequestHandler"
                    @response-handler="A002ResponseHandler($event)"
                  ></ifa-button>
                  <ifa-button
                    id="btnUpdate"
                    :disabled="detailBtn"
                    text="更新"
                    width="90"
                    small
                    action-type="requestAction"
                    action-id="SUB0404-01#A003"
                    :request-model="IfaAuthMailAddressChangeListA003RequestModel"
                    @response-handler="infoUpdate"
                  ></ifa-button>
                </span>
              </el-col>
            </el-row>
          </div>
        </el-card>
        <el-row>
          <el-card class="content-card">
            <grid-table
              ref="gridTable"
              :options="pqGridOption"
              :auto-refresh="false"
              @click="handleClick"
            ></grid-table>
          </el-card>
        </el-row>
      </div>
    </el-form>
    <ifa-auth-mail-address-update
      ref="ifaAuthMailAddressUpdate"
      :is-visible="isVisible"
      :row-data="rowData"
      @close-modal="isVisible = false"
      @data-update="updatedOk()"
    ></ifa-auth-mail-address-update>
    <ifa-requester
      id="ifaAuthMailAddressChangeListDisplayA002"
      action-id="SUB0404-01#A002"
      action-type="requestAction"
      :request-model="IfaAuthMailAddressChangeListA002RequestModel"
      :pre-request-handler="A002PreRequestHandler"
      @response-handler="A002ResponseHandler($event)"
    ></ifa-requester>
  </div>
</template>

<script>
import GridTable from '@/components/GridTable' // ParamQueryGrid用コンポーネント
import { getConvertedOption } from '@/utils/pqgridHelper'
import IfaAuthMailAddressUpdate from './IfaAuthMailAddressUpdate.vue'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
import { IfaAuthMailAddressChangeListA002RequestModel } from './js/IfaAuthMailAddressChangeListA002RequestModel'
import { IfaAuthMailAddressChangeListFormModel } from './js/IfaAuthMailAddressChangeListFormModel'
import IfaLoginIdDomainModel from '@/resource/domain/IfaLoginIdDomainModel.json'
import IfaTextFullHalf80DomainModel from '@/resource/domain/IfaTextFullHalf80DomainModel.json'
import { IfaAuthMailAddressChangeListA003RequestModel } from './js/IfaAuthMailAddressChangeListA003RequestModel'

export default {
  components: {
    GridTable,
    IfaAuthMailAddressUpdate,
    screenTitle
  },
  data() {
    return {
      form: new IfaAuthMailAddressChangeListFormModel(),
      pqGridOption: getConvertedOption(obj),
      detailBtn: true,
      isVisible: false,
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaTextFullHalf80DomainModel: IfaTextFullHalf80DomainModel,
      rowData: {
        loginId: '',
        userName: '',
        mailAddress: ''
      },
      newMail: '',
      formRef: {}
    }
  },
  computed: {
    IfaAuthMailAddressChangeListA002RequestModel() {
      return new IfaAuthMailAddressChangeListA002RequestModel(this.form)
    },
    IfaAuthMailAddressChangeListA003RequestModel() {
      return new IfaAuthMailAddressChangeListA003RequestModel(this.rowData)
    }
  },
  methods: {
    onShow() {
      this.pqGridOption.dataModel.data = []
      this.$refs['gridTable'].refreshView()
      this.formModel = new IfaAuthMailAddressChangeListFormModel()
      this.$nextTick(() => {
        this.formRef = this.$refs.formModel
      })
    },
    A002PreRequestHandler() {
      this.pqGridOption.dataModel.data = []
      this.detailBtn = true
    },
    A002ResponseHandler(response) {
      this.pqGridOption.dataModel.data = response.dataList[0].authMailAddressList
      this.$refs['gridTable'].refreshView()
    },
    handleClick(ui) {
      this.detailBtn = false
      this.rowData.loginId = ui.rowData.userId
      this.rowData.userName = ui.rowData.userName
      this.rowData.mailAddress = ui.rowData.mailAddress
    },
    infoUpdate() {
      this.isVisible = true
    },
    updatedOk() {
      this.$nextTick(() => {
        document.getElementById('ifaAuthMailAddressChangeListDisplayA002').click()
      })
      this.detailBtn = true
    }
  }
}
const obj = {
  width: 0,
  height: 0,
  title: '認証用メールアドレス変更一覧',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  // Selected single
  selectionModel: { type: 'row', mode: 'single' },
  topVisible: false,
  wrap: false
}
obj.colModel = [
  { title: 'ログインID', dataIndx: 'userId', editable: false, halign: 'center', align: 'left', width: 250 },
  { title: '支店名', dataIndx: 'branchName', editable: false, halign: 'center', align: 'left', width: 300 },
  { title: '仲介業者名', dataIndx: 'brokerName', editable: false, halign: 'center', align: 'left', width: 300 },
  { title: '社員名/担当者名', dataIndx: 'userName', editable: false, halign: 'center', align: 'left', width: 300 },
  { title: '認証用メールアドレス', dataIndx: 'mailAddress', editable: false, halign: 'center', align: 'left', width: 350 },
  { title: '', dataIndx: '', editable: false, halign: 'center', align: 'left', width: 420,
    render: function() {
      return ''
    }
  },
  { title: '', dataIndx: 'userName', hidden: true }]
obj.pageModel = {
  type: 'local',
  curPage: 1,
  rPP: 30,
  rPPOptions: []
}
</script>

<style scoped>
.search-form__item {
  margin: 0 0.2rem 0 0.2rem;
  width: 350px;
}
:deep(.form_label) .el-form-item__label {
  width: 135px;
  padding: 0 0 0 0.5rem;
}
.form_button {
  padding: 0.4rem 2rem 1.2rem 2rem;
}
.content-card {
  margin: 0.5rem 1rem;
}
.filter-container {
  margin: 1rem 0 1rem;
}
.input-area {
  margin-bottom: 0.4rem;
}
:deep(.el-dialog__title) {
  font-size: 18px;
  margin: 0px;
  font-weight: bold;
  padding: 0px;
}
:deep(.el-dialog__header) {
  padding-bottom: 0px;
  padding-top: 30px;
}
.validation-error-width {
  :deep(.el-form-item__error) {
    -ifa-input-validation-error-width: 400px;
  }
}
</style>
