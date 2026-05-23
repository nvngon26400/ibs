<template>
  <div>
    <screen-title :text="formModel.title.name"></screen-title>
    <!-- 画面名：ログイン者管理（管理者用） -->
    <!-- 初期表示時のリクエスト -->
    <ifa-requester
      id="ifaLoginUserManageManagerLookupInitialDisplayA001"
      action-id="SUB0601_01-01#A001"
      action-type="requestAction"
      @response-handler="initialDisplayA001"
    >
    </ifa-requester>
    <!-- 削除時のリクエスト -->
    <ifa-requester
      id="ifaLoginUserManageManagerLookupDeleteA007"
      action-id="SUB0601_01-01#A007"
      action-type="requestAction"
      :request-model="IfaLoginUserManageManagerLookupA007RequestModel"
      @response-handler="deleteA007"
    >
    </ifa-requester>
    <div id="contentAreaInput">
      <el-card>
        <el-form
          ref="userSearchForm"
          :model="userSearchForm"
          :inline="true"
        >
          <div>
            <el-row>
              <el-col>
                <el-row>
                  <ifa-input-text
                    id="loginId"
                    v-model="userSearchForm.loginId"
                    label="ログインID"
                    size="small"
                    input-class="ifa-input__text-default"
                    style="width: 180px;"
                    :domain="IfaLoginIdDomainModel"
                  >
                  </ifa-input-text><br>
                  <ifa-input-text
                    id="branchNameBrokerName"
                    v-model="userSearchForm.branchNameBrokerName"
                    prop="branchNameBrokerName"
                    label="支店名 / 仲介業者名"
                    size="small"
                    input-class="ifa-input__text-default"
                    style="width: 180px;"
                    original-screen-id="SUB0601_01-01"
                    :domain="IfaTextFullHalf80DomainModel"
                  >
                  </ifa-input-text><br>
                  <ifa-input-text
                    id="employeeNameChargeName"
                    v-model="userSearchForm.employeeNameChargeName"
                    prop="employeeNameChargeName"
                    label="社員名 / 担当者名"
                    size="small"
                    input-class="ifa-input__text-default"
                    style="width: 180px;"
                    original-screen-id="SUB0601_01-01"
                    :domain="IfaTextFullHalf80DomainModel"
                  >
                  </ifa-input-text><br>
                </el-row>
              </el-col>
            </el-row>
          </div>
          <div
            id="indicator-display"
            class="form_button"
          >
            <ifa-button
              id="btnSearch"
              text="検索"
              color="primary"
              search
              small
              width="90"
              action-type="requestAction"
              :form="formRef"
              :action-id="'SUB0601_01-01#A002'"
              :request-model="IfaLoginUserManageManagerLookupA002RequestModel"
              @response-handler="searchA002"
            >
            </ifa-button>
          </div>
        </el-form>
      </el-card>
    </div>
    <div id="paneBottom">
      <div id="outputPaneMargins">
        <el-card id="outputTable">
          <div
            id="gridButtonArea"
            name="gridButtonArea"
          >
            <div id="indicator-target">
              <ifa-button
                id="btnNewRegister"
                text="新規作成"
                width="110"
                color="primary"
                small
                action-id="SUB0601_01-02_1#A001"
                action-type="requestAction"
                :request-model="IfaLoginIdNewRegisterA001RequestModel"
                @response-handler="ifaLoginIdNewRegisterA001Handler"
              ></ifa-button>
              <ifa-button
                id="btnUpdate"
                text="更新"
                width="110"
                color="primary"
                small
                :disabled="disabledButton"
                action-id="SUB0601_01-03_1#A001"
                action-type="requestAction"
                :request-model="IfaLoginIdUpdateRegisterA001RequestModel"
                @response-handler="IfaLoginIdUpdateRegisterA001Handler"
              ></ifa-button>
              <ifa-button
                id="btnDelete"
                text="削除"
                width="110"
                color="seconday"
                small
                :disabled="disabledButton"
                action-type="originalAction"
                @app-action-handler="isCancelComfirmVisible = true"
              ></ifa-button>
              <ifa-button
                id="btnRepAdd"
                text="担当追加"
                width="110"
                color="primary"
                small
                :disabled="disabledAddButton"
                action-id="SUB0601_01-06_1#A001"
                :request-model="getA001RequestModel"
                action-type="requestAction"
                @response-handler="ifaRepAddInitialDisplayA001($event)"
              ></ifa-button>
            </div>
          </div>
          <grid-table
            ref="GridTable"
            :options="pqGridOption"
            :auto-refresh="false"
            @click="setGridData"
          ></grid-table>
        </el-card>
      </div>
    </div>

    <!-- 新規作成 ダイアログ -->
    <ifa-login-id-new-register
      :is-visible="newInputVisible"
      :form-data="newformData"
      @update-table="handleUpdateTable"
      @close-modal="newInputVisible = false"
    ></ifa-login-id-new-register>

    <!-- 更新 ダイアログ -->
    <ifa-login-id-update-register
      :is-visible="updateVisible"
      :selected-info="setGridInfo"
      :form-data="updateformData"
      @update-table="handleUpdateTable"
      @close-modal="updateVisible = false"
    ></ifa-login-id-update-register>

    <!-- 削除確認ダイアログ -->
    <ifa-ok-cancel-dialog
      :is-visible="isCancelComfirmVisible"
      title="ユーザーの削除"
      message="ユーザー情報を削除します。よろしいですか？"
      @close-modal-ok="deleteHandleClick"
      @close-modal-cancel="isCancelComfirmVisible = false"
    >
    </ifa-ok-cancel-dialog>

    <ifa-rep-add
      ref="IfaRepAdd"
      :is-visible="addManagerVisible"
      :selected-info="getA001RequestModel"
      :operation="'insert'"
      @call-search-a002="callSearchA002"
      @close-modal="addManagerVisible = false"
    ></ifa-rep-add>
  </div>
</template>

<script>
import IfaLoginIdNewRegister from './IfaLoginIdNewRegister'
import IfaLoginIdUpdateRegister from './IfaLoginIdUpdateRegister'
import IfaRepAdd from './IfaRepAdd'
import GridTable from '@/components/GridTable'
import IfaOkCancelDialog from '@/components/Dialog/IfaOkCancelDialog.vue'
import IfaLoginIdDomainModel from '@/resource/domain/IfaLoginIdDomainModel.json'
import IfaTextFullHalf80DomainModel from '@/resource/domain/IfaTextFullHalf80DomainModel.json'
import { getConvertedOption } from '@/utils/pqgridHelper'
import { IfaLoginUserManageManagerLookupFormModel } from './js/IfaLoginUserManageManagerLookupFormModel'
import { IfaLoginUserManageManagerLookupA002RequestModel } from './js/IfaLoginUserManageManagerLookupA002RequestModel'
import { IfaLoginUserManageManagerLookupA007RequestModel } from './js/IfaLoginUserManageManagerLookupA007RequestModel'
import { IfaLoginIdNewRegisterA001RequestModel } from './js/IfaLoginIdNewRegisterA001RequestModel'
import { IfaLoginIdUpdateRegisterA001RequestModel } from './js/IfaLoginIdUpdateRegisterA001RequestModel'
import { IfaRepAddA001RequestModel } from './js/IfaRepAddA001RequestModel.js'
import screenTitle from '@/views/brokerageMenu/customerMenu/components/screenTitle.vue'
export default {
  components: {
    GridTable,
    IfaOkCancelDialog,
    IfaLoginIdNewRegister,
    IfaLoginIdUpdateRegister,
    IfaRepAdd,
    screenTitle
  },
  data() {
    return {
      formRef: {},
      pqGridOption: getConvertedOption(obj),
      userSearchForm: {
        loginId: '',
        branchNameBrokerName: '',
        employeeNameChargeName: ''
      },
      setGridInfo: {
        loginId: '',
        privId: '',
        sbiSecurityCode: '',
        branchCode: '',
        brokerCode: '',
        subBrokerId: '',
        repNumber: '',
        employeeId: '',
        employeeNameChargeName: '',
        managerCount: '',
        mailAddress: '',
        menuList: []
      },
      newInputVisible: false,
      updateVisible: false,
      isCancelComfirmVisible: false,
      addManagerVisible: false,
      disabledButton: true,
      disabledAddButton: true,
      IfaLoginIdDomainModel: IfaLoginIdDomainModel,
      IfaTextFullHalf80DomainModel: IfaTextFullHalf80DomainModel,
      formModel: new IfaLoginUserManageManagerLookupFormModel(),
      newformData: {},
      updateformData: {}
    }
  },
  computed: {
    IfaLoginUserManageManagerLookupA002RequestModel() {
      return new IfaLoginUserManageManagerLookupA002RequestModel(this.userSearchForm)
    },
    IfaLoginUserManageManagerLookupA007RequestModel() {
      return new IfaLoginUserManageManagerLookupA007RequestModel(this.setGridInfo)
    },
    getA001RequestModel: function() {
      return new IfaRepAddA001RequestModel(
        {
          loginId: this.setGridInfo.loginId, // ログインID
          privId: this.setGridInfo.privId // 権限コード
        }
      )
    },
    IfaLoginIdNewRegisterA001RequestModel() { return new IfaLoginIdNewRegisterA001RequestModel(this.setGridInfo) },
    IfaLoginIdUpdateRegisterA001RequestModel() { return new IfaLoginIdUpdateRegisterA001RequestModel(this.setGridInfo) }
  },
  mounted() {
    this.formRef = this.$refs.userSearchForm
  },
  methods: {
    onShow() {
      this.$nextTick(() => {
        document.getElementById('ifaLoginUserManageManagerLookupInitialDisplayA001').click()
        this.pqGridOption.dataModel.data = []
        this.disabledButton = true
        this.disabledAddButton = true
      })
    },
    initialDisplayA001(data) {
      this.formModel = Object.assign(this.formModel, data.dataList[0])
      this.setGridInfo.menuList = this.formModel.menuList
    },
    searchA002(response) {
      this.disabledButton = true
      this.disabledAddButton = true
      if (response.dataList.length > 0) {
        this.pqGridOption.dataModel.data = response.dataList[0].loginIdManageList
      } else {
        this.pqGridOption.dataModel.data = []
      }
      this.$refs['GridTable'].refreshView()
    },
    ifaLoginIdNewRegisterA001Handler(response) {
      this.newformData = response.dataList[0]
      this.newInputVisible = true
    },
    IfaLoginIdUpdateRegisterA001Handler(response) {
      this.updateformData = response.dataList[0]
      this.updateVisible = true
    },
    callSearchA002() {
      document.getElementById('btnSearch').click()
    },
    setGridData(ui) {
      this.disabledButton = false
      // 権限コード3,4,5,6,7,8,9は担当追加可能
      if (['3', '4', '5', '6', '7', '8', '9'].includes(ui.rowData.privId)) {
        this.disabledAddButton = false
      } else {
        this.disabledAddButton = true
      }
      // 遷移先に渡すグリッド選択行データをセット
      this.setGridInfo.loginId = ui.rowData.loginId
      this.setGridInfo.privId = ui.rowData.privId
      this.setGridInfo.sbiSecurityCode = ui.rowData.branchCode
      this.setGridInfo.branchCode = ui.rowData.branchCode
      this.setGridInfo.brokerCode = ui.rowData.brokerCode
      this.setGridInfo.subBrokerId = ui.rowData.subBrokerId
      this.setGridInfo.employeeId = ui.rowData.employeeId
      this.setGridInfo.userName = ui.rowData.employeeNameChargeName
      this.setGridInfo.managerCount = ui.rowData.managerCount
      this.setGridInfo.repNumber = ui.rowData.managerCount
      this.setGridInfo.mailAddress = ui.rowData.mailAddress
    },
    // 削除
    deleteHandleClick() {
      this.isCancelComfirmVisible = false
      document.getElementById('ifaLoginUserManageManagerLookupDeleteA007').click()
    },
    deleteA007(data) {
      document.getElementById('btnSearch').click()
      this.disabledAddButton = true
      this.disabledButton = true
    },
    ifaRepAddInitialDisplayA001(data) {
      if (data.message.length === 0) {
        this.addManagerVisible = true
        this.$refs.IfaRepAdd.onShow(data)
      } else {
        return
      }
    },
    handleUpdateTable() {
      this.newInputVisible = false
      this.updateVisible = false
      this.refreshData()
    },
    refreshData() {
      this.searchA002Handler()
    },
    searchA002Handler() {
      document.getElementById('btnSearch').click()
    }
  }
}
const renderPrivId = (ui) => {
  switch (ui.cellData) {
    case '1':
      return '本店'
    case '2':
      return '支店'
    case '3':
      return '仲介業者(内部管理責任者)'
    case '4':
      return '仲介業者(営業責任者)'
    case '5':
      return '仲介業者(外務員)'
    case '6':
      return '仲介業者支店(内部管理責任者)'
    case '7':
      return '仲介業者支店(営業責任者)'
    case '8':
      return '仲介業者支店(外務員)'
    case '9':
      return '担当'
    case '10':
      return '担当(全担当者参照可能)'
    case '11':
      return '業務部'
    case '12':
      return '管理部'
    default :
      return ''
  }
}
const managerCount = (data) => {
  return data
}
const obj = {
  width: 0,
  height: 0,
  title: 'ログイン者管理',
  flexHeight: false,
  flexWidth: false,
  collapsible: false,
  showTitle: true,
  numberCell: { show: false },
  topVisible: false,
  selectionModel: { type: 'row', mode: 'single' },
  wrap: false
}
obj.colModel = [
  {
    title: 'ログインID',
    dataIndx: 'loginId',
    width: 120,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'left',
    editable: false
  },
  {
    title: '管理者フラグ',
    dataIndx: 'governorFlag',
    width: 110,
    dataType: 'string',
    halign: 'center',
    align: 'center',
    editable: false,
    render: function(ui) {
      if (ui.cellData === '1') return '管理者'
      return ''
    }
  },
  {
    title: '権限所属',
    dataIndx: 'privId',
    width: 200,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'left',
    editable: false,
    render: renderPrivId
  },
  {
    title: '担当数',
    dataIndx: 'managerCount',
    width: 70,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'right',
    editable: false,
    render: managerCount
  },
  {
    title: '支店名',
    dataIndx: 'headOfficeBranchName',
    width: 250,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'left',
    editable: false
  },
  {
    title: '仲介業者名',
    dataIndx: 'brokerName',
    width: 250,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'left',
    editable: false
  },
  {
    title: '社員名/担当者名',
    dataIndx: 'employeeNameChargeName',
    width: 300,
    dataType: function(val1, val2) {
      return this.$_nullSorting(val1, val2)
    },
    halign: 'center',
    align: 'left',
    editable: false
  },
  {
    title: '&nbsp;',
    dataIndx: '',
    width: 520,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    render: function(ui) {
      return ui.cellData
    }
  },

  // Invisible columns which are editable in the edit form
  {
    title: 'HeadOfficeBranch Code',
    dataIndx: 'branchCode',
    width: 40,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: true
  },
  {
    title: 'Broker ID',
    dataIndx: 'brokerId',
    width: 40,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: true
  },
  {
    title: 'Sub broker ID',
    dataIndx: 'subBrokerId',
    width: 40,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: true
  },
  {
    title: 'Employee ID',
    dataIndx: 'employeeId',
    width: 40,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: true
  },
  {
    title: 'Employee Name',
    dataIndx: 'employeeName',
    width: 40,
    dataType: 'string',
    halign: 'center',
    align: 'left',
    editable: false,
    hidden: true
  }
]
obj.pageModel = {
  type: 'local',
  rPP: 30,
  rPPOptions: [1, 10, 20, 30, 40, 50, 100, 200]
}

</script>

<style lang="scss" scoped>
.ifa-input__text-default {
  width: 250px;
}
:deep(.form_label) .el-form-item__label {
  width: 150px;
  line-height: 2
}
:deep(.el-form-item) {
  margin-bottom: 1.5rem;
}
:deep(.el-card__body) {
  padding-top: 0.8rem
}
.form_button {
  margin: 0 2rem 0.8rem 2.5rem;
  padding: 0;
}
#gridButtonArea {
  margin-bottom: 0.5rem;
}
#contentAreaInput, #outputPaneMargins {
  margin: 0.5rem 0.8rem;
}

//確認ダイアログ
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
</style>
